package org.computate.frFR.scolaire.cluster;

import org.computate.frFR.scolaire.ecrivain.ToutEcrivain;
import java.util.Arrays;
import io.vertx.ext.web.api.validation.ParameterTypeValidator;
import org.apache.solr.common.SolrDocumentList;
import java.util.Date;
import io.vertx.core.MultiMap;
import io.vertx.ext.web.Router;
import io.vertx.ext.reactivestreams.ReactiveReadStream;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.api.OperationResponse;
import org.apache.commons.lang3.StringUtils;
import java.math.BigDecimal;
import java.util.Map;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.core.json.JsonObject;
import org.computate.frFR.scolaire.utilisateur.UtilisateurSite;
import io.vertx.core.logging.Logger;
import io.vertx.core.http.CaseInsensitiveHeaders;
import java.io.PrintWriter;
import org.computate.frFR.scolaire.contexte.SiteContexte;
import java.util.Collection;
import java.sql.Timestamp;
import org.computate.frFR.scolaire.config.ConfigSite;
import java.util.Set;
import io.netty.handler.codec.http.HttpResponseStatus;
import java.util.stream.Collectors;
import io.vertx.core.Future;
import java.time.ZoneId;
import org.computate.frFR.scolaire.recherche.ListeRecherche;
import java.util.List;
import java.security.Principal;
import java.util.stream.Stream;
import io.vertx.core.buffer.Buffer;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.http.client.utils.URLEncodedUtils;
import java.util.Optional;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import org.apache.solr.client.solrj.util.ClientUtils;
import io.vertx.ext.sql.SQLClient;
import org.apache.http.NameValuePair;
import org.apache.commons.lang3.exception.ExceptionUtils;
import io.vertx.core.json.Json;
import java.time.LocalDateTime;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import io.vertx.core.CompositeFuture;
import java.nio.charset.Charset;
import io.vertx.ext.web.api.validation.HTTPRequestValidationHandler;
import io.vertx.core.AsyncResult;
import io.vertx.ext.web.api.validation.ValidationException;
import org.apache.solr.client.solrj.response.QueryResponse;
import io.vertx.core.Vertx;
import java.io.IOException;
import org.computate.frFR.scolaire.recherche.ResultatRecherche;
import io.vertx.ext.reactivestreams.ReactiveWriteStream;
import java.util.concurrent.TimeUnit;
import org.apache.solr.common.SolrDocument;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.api.OperationRequest;
import java.time.format.DateTimeFormatter;
import io.vertx.ext.sql.SQLConnection;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import io.vertx.core.Handler;
import java.util.Collections;
import org.computate.frFR.scolaire.requete.RequeteSite;


public class ClusterGenApiServiceImpl implements ClusterGenApiService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClusterGenApiServiceImpl.class);

	private static final String SERVICE_ADDRESS = "ClusterApiServiceImpl";

	protected SiteContexte siteContexte;

	public ClusterGenApiServiceImpl(SiteContexte siteContexte) {
		this.siteContexte = siteContexte;
		ClusterGenApiService service = ClusterGenApiService.creerProxy(siteContexte.getVertx(), SERVICE_ADDRESS);
	}

	// Recherche //

	@Override
	public void rechercheCluster(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSite requeteSite = genererRequeteSitePourCluster(siteContexte, operationRequete);
		rechercheCluster(requeteSite, false, true, a -> {
			if(a.succeeded()) {
				ListeRecherche<Cluster> listeCluster = a.result();
				reponse200RechercheCluster(listeCluster, b -> {
					if(b.succeeded()) {
						gestionnaireEvenements.handle(Future.succeededFuture(b.result()));
					} else {
						erreurCluster(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurCluster(requeteSite, gestionnaireEvenements, a);
			}
		});
	}

	public void rechercheCluster(RequeteSite requeteSite, Boolean peupler, Boolean stocker, Handler<AsyncResult<ListeRecherche<Cluster>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<Cluster> listeRecherche = new ListeRecherche<Cluster>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(Cluster.class);
			listeRecherche.setRows(1000000);
			if(entiteListe != null)
			listeRecherche.setFields(entiteListe);
			listeRecherche.addSort("archive_indexed_boolean", ORDER.asc);
			listeRecherche.addSort("supprime_indexed_boolean", ORDER.asc);

			String pageUri = null;
			String id = operationRequete.getParams().getJsonObject("path").getString("id");
			if(id != null) {
				pageUri = "/api/warfarin/cluster/" + id;
				listeRecherche.addFilterQuery("pageUri_indexed_string:" + ClientUtils.escapeQueryChars(pageUri));
			}

			operationRequete.getParams().getJsonObject("query").forEach(paramRequete -> {
				String entiteVar = null;
				String valeurIndexe = null;
				String varIndexe = null;
				String valeurTri = null;
				Integer rechercheDebut = null;
				Integer rechercheNum = null;
				String paramNom = paramRequete.getKey();
				Object paramValeursObjet = paramRequete.getValue();
				JsonArray paramObjets = paramValeursObjet instanceof JsonArray ? (JsonArray)paramValeursObjet : new JsonArray().add(paramValeursObjet);

				for(Object paramObjet : paramObjets) {
					switch(paramNom) {
						case "q":
							entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
							valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":"));
							varIndexe = "*".equals(entiteVar) ? entiteVar : varIndexeCluster(entiteVar);
							listeRecherche.setQuery(varIndexe + ":" + ("*".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));
							break;
						case "fq":
							entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
							valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":"));
							varIndexe = varIndexeCluster(entiteVar);
							listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
							break;
						case "sort":
							entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
							valeurTri = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
							varIndexe = varIndexeCluster(entiteVar);
							listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurTri));
							break;
						case "fl":
							entiteVar = StringUtils.trim((String)paramObjet);
							varIndexe = varIndexeCluster(entiteVar);
							listeRecherche.addField(varIndexe);
							break;
						case "start":
							rechercheDebut = (Integer)paramObjet;
							listeRecherche.setStart(rechercheDebut);
							break;
						case "rows":
							rechercheNum = (Integer)paramObjet;
							listeRecherche.setRows(rechercheNum);
							break;
					}
				}
			});
			listeRecherche.initLoinPourClasse(requeteSite);
			gestionnaireEvenements.handle(Future.succeededFuture(listeRecherche));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200RechercheCluster(ListeRecherche<Cluster> listeCluster, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSite requeteSite = listeCluster.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeCluster.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			QueryResponse reponseRecherche = listeCluster.getQueryResponse();
			SolrDocumentList documentsSolr = listeCluster.getSolrDocumentList();
			Long millisRecherche = Long.valueOf(reponseRecherche.getQTime());
			Long millisTransmission = reponseRecherche.getElapsedTime();
			Long numCommence = reponseRecherche.getResults().getStart();
			Long numTrouve = reponseRecherche.getResults().getNumFound();
			Integer numRetourne = reponseRecherche.getResults().size();
			String tempsRecherche = String.format("%d.%03d sec", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));
			String tempsTransmission = String.format("%d.%03d sec", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));
			Exception exceptionRecherche = reponseRecherche.getException();

			w.l("{");
			w.tl(1, "\"numCommence\": ", numCommence);
			w.tl(1, ", \"numTrouve\": ", numTrouve);
			w.tl(1, ", \"numRetourne\": ", numRetourne);
			w.tl(1, ", \"tempsRecherche\": ", w.q(tempsRecherche));
			w.tl(1, ", \"tempsTransmission\": ", w.q(tempsTransmission));
			w.tl(1, ", \"liste\": [");
			for(int i = 0; i < listeCluster.size(); i++) {
				Cluster o = listeCluster.getList().get(i);
				Object entiteValeur;
				Integer entiteNumero = 0;

				w.t(2);
				if(i > 0)
					w.s(", ");
				w.l("{");

				entiteValeur = o.getPk();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"pk\": ", entiteValeur);

				entiteValeur = o.getUtilisateurId();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"utilisateurId\": ", w.qjs(entiteValeur));

				entiteValeur = o.getCree();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"cree\": ", w.qjs(entiteValeur));

				entiteValeur = o.getModifie();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"modifie\": ", w.qjs(entiteValeur));

				entiteValeur = o.getClasseNomCanonique();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"classeNomCanonique\": ", w.qjs(entiteValeur));

				entiteValeur = o.getClasseNomSimple();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"classeNomSimple\": ", w.qjs(entiteValeur));

				w.tl(2, "}");
			}
			w.tl(1, "]");
			if(exceptionRecherche != null) {
				w.tl(1, ", \"exceptionRecherche\": ", w.q(exceptionRecherche.getMessage()));
			}
			w.l("}");
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// POST //

	@Override
	public void postCluster(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSite requeteSite = genererRequeteSitePourCluster(siteContexte, operationRequete, body);
		sqlCluster(requeteSite, a -> {
			if(a.succeeded()) {
				creerPOSTCluster(requeteSite, b -> {
					if(b.succeeded()) {
						Cluster cluster = b.result();
						sqlPOSTCluster(cluster, c -> {
							if(c.succeeded()) {
								definirCluster(cluster, d -> {
									if(d.succeeded()) {
										attribuerCluster(cluster, e -> {
											if(e.succeeded()) {
												indexerCluster(cluster, f -> {
													if(f.succeeded()) {
														reponse200POSTCluster(cluster, g -> {
															if(f.succeeded()) {
																SQLConnection connexionSql = requeteSite.getConnexionSql();
																connexionSql.commit(h -> {
																	if(a.succeeded()) {
																		connexionSql.close(i -> {
																			if(a.succeeded()) {
																				gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																			} else {
																				erreurCluster(requeteSite, gestionnaireEvenements, i);
																			}
																		});
																	} else {
																		erreurCluster(requeteSite, gestionnaireEvenements, h);
																	}
																});
															} else {
																erreurCluster(requeteSite, gestionnaireEvenements, g);
															}
														});
													} else {
														erreurCluster(requeteSite, gestionnaireEvenements, f);
													}
												});
											} else {
												erreurCluster(requeteSite, gestionnaireEvenements, e);
											}
										});
									} else {
										erreurCluster(requeteSite, gestionnaireEvenements, d);
									}
								});
							} else {
								erreurCluster(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurCluster(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurCluster(requeteSite, gestionnaireEvenements, a);
			}
		});
	}

	public void creerPOSTCluster(RequeteSite requeteSite, Handler<AsyncResult<Cluster>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();

			connexionSql.queryWithParams(
					SiteContexte.SQL_creer
					, new JsonArray(Arrays.asList(Cluster.class.getCanonicalName(), utilisateurId))
					, creerAsync
			-> {
				JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = creerLigne.getLong(0);
				Cluster o = new Cluster();
				o.setPk(pk);
				o.initLoinCluster(requeteSite);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlPOSTCluster(Cluster o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject jsonObject = requeteSite.getObjetJson();
			StringBuilder postSql = new StringBuilder();
			List<Object> postSqlParams = new ArrayList<Object>();

			if(jsonObject != null) {
				Set<String> entiteVars = jsonObject.fieldNames();
				for(String entiteVar : entiteVars) {
					switch(entiteVar) {
					case "pk":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("pk", jsonObject.getLong(entiteVar), pk));
						break;
					case "id":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("id", jsonObject.getString(entiteVar), pk));
						break;
					case "supprime":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("supprime", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "utilisateurId":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("utilisateurId", jsonObject.getString(entiteVar), pk));
						break;
					case "cree":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("cree", jsonObject.getInstant(entiteVar), pk));
						break;
					case "modifie":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("modifie", jsonObject.getInstant(entiteVar), pk));
						break;
					case "classeNomCanonique":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("classeNomCanonique", jsonObject.getString(entiteVar), pk));
						break;
					case "classeNomSimple":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("classeNomSimple", jsonObject.getString(entiteVar), pk));
						break;
					}
				}
			}
			connexionSql.queryWithParams(
					postSql.toString()
					, new JsonArray(postSqlParams)
					, postAsync
			-> {
				gestionnaireEvenements.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200POSTCluster(Cluster o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSite requeteSite = o.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(o.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchCluster(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSite requeteSite = genererRequeteSitePourCluster(siteContexte, operationRequete, body);
		sqlCluster(requeteSite, a -> {
			if(a.succeeded()) {
				rechercheCluster(requeteSite, false, true, b -> {
					if(b.succeeded()) {
						ListeRecherche<Cluster> listeCluster = b.result();
						listePATCHCluster(listeCluster, c -> {
							if(c.succeeded()) {
								SQLConnection connexionSql = requeteSite.getConnexionSql();
								connexionSql.commit(d -> {
									if(a.succeeded()) {
										connexionSql.close(e -> {
											if(a.succeeded()) {
												gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
											} else {
												erreurCluster(requeteSite, gestionnaireEvenements, e);
											}
										});
									} else {
										erreurCluster(requeteSite, gestionnaireEvenements, d);
									}
								});
							} else {
								erreurCluster(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurCluster(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurCluster(requeteSite, gestionnaireEvenements, a);
			}
		});
	}

	public void listePATCHCluster(ListeRecherche<Cluster> listeCluster, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		listeCluster.getList().forEach(o -> {
			futures.add(
				sqlPATCHCluster(o).compose(
					a -> definirPATCHCluster(a).compose(
						b -> indexerPATCHCluster(b)
					)
				)
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				reponse200PATCHCluster(listeCluster, gestionnaireEvenements);
			} else {
				erreurCluster(listeCluster.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<Cluster> sqlPATCHCluster(Cluster o) {
		Future<Cluster> future = Future.future();
		try {
			RequeteSite requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject requeteJson = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = requeteJson.fieldNames();
			Cluster o2 = new Cluster();

			for(String methodeNom : methodeNoms) {
				switch(methodeNom) {
					case "setPk":
						o2.setPk(requeteJson.getLong(methodeNom));
						patchSql.append(SiteContexte.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("pk", o2.getPk(), pk));
						break;
					case "setId":
						o2.setId(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexte.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("id", o2.getId(), pk));
						break;
					case "setSupprime":
						o2.setSupprime(requeteJson.getBoolean(methodeNom));
						patchSql.append(SiteContexte.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("supprime", o2.getSupprime(), pk));
						break;
					case "setUtilisateurId":
						o2.setUtilisateurId(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexte.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("utilisateurId", o2.getUtilisateurId(), pk));
						break;
					case "setCree":
						o2.setCree(requeteJson.getInstant(methodeNom));
						patchSql.append(SiteContexte.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("cree", o2.getCree(), pk));
						break;
					case "setModifie":
						o2.setModifie(requeteJson.getInstant(methodeNom));
						patchSql.append(SiteContexte.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("modifie", o2.getModifie(), pk));
						break;
					case "setClasseNomCanonique":
						o2.setClasseNomCanonique(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexte.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("classeNomCanonique", o2.getClasseNomCanonique(), pk));
						break;
					case "setClasseNomSimple":
						o2.setClasseNomSimple(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexte.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("classeNomSimple", o2.getClasseNomSimple(), pk));
						break;
				}
			}
			connexionSql.queryWithParams(
					patchSql.toString()
					, new JsonArray(patchSqlParams)
					, patchAsync
			-> {
				o2.setRequeteSite_(o.getRequeteSite_());
				o2.setPk(pk);
				future.complete(o2);
			});
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public Future<Cluster> definirPATCHCluster(Cluster o) {
		Future<Cluster> future = Future.future();
		try {
			RequeteSite requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			connexionSql.queryWithParams(
					SiteContexte.SQL_definir
					, new JsonArray(Arrays.asList(pk))
					, definirAsync
			-> {
				if(definirAsync.succeeded()) {
					for(JsonArray definition : definirAsync.result().getResults()) {
						o.definirPourClasse(definition.getString(0), definition.getString(1));
					}
					future.complete(o);
				} else {
			future.fail(definirAsync.cause());
				}
			});
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public Future<Void> indexerPATCHCluster(Cluster o) {
		Future<Void> future = Future.future();
		try {
			o.initLoinPourClasse(o.getRequeteSite_());
			o.indexerPourClasse();
				future.complete();
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public void reponse200PATCHCluster(ListeRecherche<Cluster> listeCluster, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSite requeteSite = listeCluster.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeCluster.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getCluster(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSite requeteSite = genererRequeteSitePourCluster(siteContexte, operationRequete);
		rechercheCluster(requeteSite, false, true, a -> {
			if(a.succeeded()) {
				ListeRecherche<Cluster> listeCluster = a.result();
				reponse200GETCluster(listeCluster, b -> {
					if(b.succeeded()) {
						gestionnaireEvenements.handle(Future.succeededFuture(b.result()));
					} else {
						erreurCluster(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurCluster(requeteSite, gestionnaireEvenements, a);
			}
		});
	}

	public void reponse200GETCluster(ListeRecherche<Cluster> listeCluster, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSite requeteSite = listeCluster.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeCluster.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			SolrDocumentList documentsSolr = listeCluster.getSolrDocumentList();

			if(listeCluster.size() > 0) {
				SolrDocument documentSolr = documentsSolr.get(0);
				Cluster o = listeCluster.get(0);
				Object entiteValeur;
				Integer entiteNumero = 0;

				w.l("{");

				entiteValeur = o.getPk();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"pk\": ", entiteValeur);

				entiteValeur = o.getUtilisateurId();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"utilisateurId\": ", w.qjs(entiteValeur));

				entiteValeur = o.getCree();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"cree\": ", w.qjs(entiteValeur));

				entiteValeur = o.getModifie();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"modifie\": ", w.qjs(entiteValeur));

				entiteValeur = o.getClasseNomCanonique();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"classeNomCanonique\": ", w.qjs(entiteValeur));

				entiteValeur = o.getClasseNomSimple();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"classeNomSimple\": ", w.qjs(entiteValeur));

				w.l("}");
			}
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUT //

	@Override
	public void putCluster(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSite requeteSite = genererRequeteSitePourCluster(siteContexte, operationRequete, body);
		sqlCluster(requeteSite, a -> {
			if(a.succeeded()) {
				remplacerPUTCluster(requeteSite, b -> {
					if(b.succeeded()) {
						Cluster cluster = b.result();
						sqlPUTCluster(cluster, c -> {
							if(c.succeeded()) {
								definirCluster(cluster, d -> {
									if(d.succeeded()) {
										attribuerCluster(cluster, e -> {
											if(e.succeeded()) {
												indexerCluster(cluster, f -> {
													if(f.succeeded()) {
														reponse200PUTCluster(cluster, g -> {
															if(g.succeeded()) {
																SQLConnection connexionSql = requeteSite.getConnexionSql();
																connexionSql.commit(h -> {
																	if(a.succeeded()) {
																		connexionSql.close(i -> {
																			if(a.succeeded()) {
																				gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																			} else {
																				erreurCluster(requeteSite, gestionnaireEvenements, i);
																			}
																		});
																	} else {
																		erreurCluster(requeteSite, gestionnaireEvenements, h);
																	}
																});
															} else {
																erreurCluster(requeteSite, gestionnaireEvenements, g);
															}
														});
													} else {
														erreurCluster(requeteSite, gestionnaireEvenements, f);
													}
												});
											} else {
												erreurCluster(requeteSite, gestionnaireEvenements, e);
											}
										});
									} else {
										erreurCluster(requeteSite, gestionnaireEvenements, d);
									}
								});
							} else {
								erreurCluster(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurCluster(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurCluster(requeteSite, gestionnaireEvenements, a);
			}
		});
	}

	public void remplacerPUTCluster(RequeteSite requeteSite, Handler<AsyncResult<Cluster>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			Long pk = requeteSite.getRequetePk();

			connexionSql.queryWithParams(
					SiteContexte.SQL_vider
					, new JsonArray(Arrays.asList(pk, Cluster.class.getCanonicalName(), pk, pk, pk))
					, remplacerAsync
			-> {
				Cluster o = new Cluster();
				o.setPk(pk);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlPUTCluster(Cluster o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject jsonObject = requeteSite.getObjetJson();
			StringBuilder postSql = new StringBuilder();
			List<Object> postSqlParams = new ArrayList<Object>();

			if(jsonObject != null) {
				Set<String> entiteVars = jsonObject.fieldNames();
				for(String entiteVar : entiteVars) {
					switch(entiteVar) {
					case "pk":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("pk", jsonObject.getLong(entiteVar), pk));
						break;
					case "id":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("id", jsonObject.getString(entiteVar), pk));
						break;
					case "supprime":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("supprime", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "utilisateurId":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("utilisateurId", jsonObject.getString(entiteVar), pk));
						break;
					case "cree":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("cree", jsonObject.getInstant(entiteVar), pk));
						break;
					case "modifie":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("modifie", jsonObject.getInstant(entiteVar), pk));
						break;
					case "classeNomCanonique":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("classeNomCanonique", jsonObject.getString(entiteVar), pk));
						break;
					case "classeNomSimple":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("classeNomSimple", jsonObject.getString(entiteVar), pk));
						break;
					}
				}
			}
			connexionSql.queryWithParams(
					postSql.toString()
					, new JsonArray(postSqlParams)
					, postAsync
			-> {
				gestionnaireEvenements.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200PUTCluster(Cluster o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSite requeteSite = o.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(o.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteCluster(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSite requeteSite = genererRequeteSitePourCluster(siteContexte, operationRequete);
		sqlCluster(requeteSite, a -> {
			if(a.succeeded()) {
				rechercheCluster(requeteSite, false, true, b -> {
					if(b.succeeded()) {
						ListeRecherche<Cluster> listeCluster = b.result();
						supprimerDELETECluster(requeteSite, c -> {
							if(c.succeeded()) {
								reponse200DELETECluster(requeteSite, d -> {
									if(d.succeeded()) {
										SQLConnection connexionSql = requeteSite.getConnexionSql();
										connexionSql.commit(e -> {
											if(a.succeeded()) {
												connexionSql.close(f -> {
													if(a.succeeded()) {
														gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
													} else {
														erreurCluster(requeteSite, gestionnaireEvenements, f);
													}
												});
											} else {
												erreurCluster(requeteSite, gestionnaireEvenements, e);
											}
										});
									} else {
										erreurCluster(requeteSite, gestionnaireEvenements, d);
									}
								});
							} else {
								erreurCluster(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurCluster(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurCluster(requeteSite, gestionnaireEvenements, a);
			}
		});
	}

	public void supprimerDELETECluster(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			Long pk = requeteSite.getRequetePk();

			connexionSql.queryWithParams(
					SiteContexte.SQL_supprimer
					, new JsonArray(Arrays.asList(pk, Cluster.class.getCanonicalName(), pk, pk, pk, pk))
					, supprimerAsync
			-> {
				gestionnaireEvenements.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200DELETECluster(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(requeteSite, buffer);
			requeteSite.setW(w);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public String varIndexeCluster(String entiteVar) {
		switch(entiteVar) {
			case "pk":
				return "pk_indexed_long";
			case "id":
				return "id_indexed_string";
			case "utilisateurId":
				return "utilisateurId_indexed_string";
			case "cree":
				return "cree_indexed_date";
			case "modifie":
				return "modifie_indexed_date";
			case "classeNomCanonique":
				return "classeNomCanonique_indexed_string";
			case "classeNomSimple":
				return "classeNomSimple_indexed_string";
			default:
				throw new RuntimeException(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	// Partagé //

	public void erreurCluster(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
		Throwable e = resultatAsync.cause();
		ExceptionUtils.printRootCauseStackTrace(e);
		OperationResponse reponseOperation = new OperationResponse(400, "BAD REQUEST", 
			Buffer.buffer().appendString(
				new JsonObject() {{
					put("erreur", new JsonObject() {{
					put("message", e.getMessage());
					}});
				}}.encodePrettily()
			)
			, new CaseInsensitiveHeaders()
		);
		SQLConnection connexionSql = requeteSite.getConnexionSql();
		if(connexionSql != null) {
			connexionSql.rollback(a -> {
				if(a.succeeded()) {
					connexionSql.close(b -> {
						if(a.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));
						} else {
							gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));
						}
					});
				} else {
					gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));
				}
			});
		} else {
			gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));
		}
	}

	public void sqlCluster(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SQLClient clientSql = requeteSite.getSiteContexte_().getClientSql();

			clientSql.getConnection(sqlAsync -> {
				if(sqlAsync.succeeded()) {
					SQLConnection connexionSql = sqlAsync.result();
					connexionSql.setAutoCommit(false, a -> {
						if(a.succeeded()) {
							requeteSite.setConnexionSql(connexionSql);
							gestionnaireEvenements.handle(Future.succeededFuture());
						} else {
							gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
						}
					});
				} else {
					gestionnaireEvenements.handle(Future.failedFuture(sqlAsync.cause()));
				}
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public RequeteSite genererRequeteSitePourCluster(SiteContexte siteContexte, OperationRequest operationRequete) {
		return genererRequeteSitePourCluster(siteContexte, operationRequete, null);
	}

	public RequeteSite genererRequeteSitePourCluster(SiteContexte siteContexte, OperationRequest operationRequete, JsonObject body) {
		Vertx vertx = siteContexte.getVertx();
		RequeteSite requeteSite = new RequeteSite();
		requeteSite.setObjetJson(body);
		requeteSite.setVertx(vertx);
		requeteSite.setSiteContexte_(siteContexte);
		requeteSite.setConfigSite_(siteContexte.getConfigSite());
		requeteSite.setOperationRequete(operationRequete);
		requeteSite.initLoinRequeteSite(requeteSite);

		UtilisateurSite utilisateurSite = new UtilisateurSite();
		utilisateurSite.initLoinUtilisateurSite(requeteSite);
		requeteSite.setUtilisateurSite(utilisateurSite);
		utilisateurSite.setRequeteSite_(requeteSite);
		return requeteSite;
	}

	public void utilisateurCluster(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			if(utilisateurId == null) {
				gestionnaireEvenements.handle(Future.succeededFuture());
			} else {
				connexionSql.queryWithParams(
						SiteContexte.SQL_selectC
						, new JsonArray(Arrays.asList("org.computate.frFR.scolaire.utilisateur.UtilisateurSite", utilisateurId))
						, selectCAsync
				-> {
					if(selectCAsync.succeeded()) {
						JsonArray utilisateurValeurs = selectCAsync.result().getResults().stream().findFirst().orElse(null);
						if(utilisateurValeurs == null) {
							connexionSql.queryWithParams(
									SiteContexte.SQL_creer
									, new JsonArray(Arrays.asList(UtilisateurSite.class.getCanonicalName(), utilisateurId))
									, creerAsync
							-> {
								JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
								Long pkUtilisateur = creerLigne.getLong(0);
								UtilisateurSite utilisateurSite = new UtilisateurSite();

								connexionSql.queryWithParams(
										SiteContexte.SQL_definir
										, new JsonArray(Arrays.asList(pkUtilisateur))
										, definirAsync
								-> {
									if(definirAsync.succeeded()) {
										for(JsonArray definition : definirAsync.result().getResults()) {
											utilisateurSite.definirPourClasse(definition.getString(0), definition.getString(1));
										}
										gestionnaireEvenements.handle(Future.succeededFuture());
									} else {
										gestionnaireEvenements.handle(Future.failedFuture(definirAsync.cause()));
									}
								});
							});
						} else {
							Long pkUtilisateur = utilisateurValeurs.getLong(0);
							UtilisateurSite utilisateurSite = new UtilisateurSite();

							connexionSql.queryWithParams(
									SiteContexte.SQL_definir
									, new JsonArray(Arrays.asList(pkUtilisateur))
									, definirAsync
							-> {
								if(definirAsync.succeeded()) {
									for(JsonArray definition : definirAsync.result().getResults()) {
										utilisateurSite.definirPourClasse(definition.getString(0), definition.getString(1));
									}
									gestionnaireEvenements.handle(Future.succeededFuture());
								} else {
									gestionnaireEvenements.handle(Future.failedFuture(definirAsync.cause()));
								}
							});
						}
						gestionnaireEvenements.handle(Future.succeededFuture());
					} else {
						gestionnaireEvenements.handle(Future.failedFuture(selectCAsync.cause()));
					}
				});
			}
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void definirCluster(Cluster o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			connexionSql.queryWithParams(
					SiteContexte.SQL_definir
					, new JsonArray(Arrays.asList(pk))
					, definirAsync
			-> {
				if(definirAsync.succeeded()) {
					for(JsonArray definition : definirAsync.result().getResults()) {
						o.definirPourClasse(definition.getString(0), definition.getString(1));
					}
					gestionnaireEvenements.handle(Future.succeededFuture());
				} else {
					gestionnaireEvenements.handle(Future.failedFuture(definirAsync.cause()));
				}
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void attribuerCluster(Cluster o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			connexionSql.queryWithParams(
					SiteContexte.SQL_attribuer
					, new JsonArray(Arrays.asList(pk, pk))
					, attribuerAsync
			-> {
				if(attribuerAsync.succeeded()) {
					if(attribuerAsync.result() != null) {
						for(JsonArray definition : attribuerAsync.result().getResults()) {
							o.attribuerPourClasse(definition.getString(0), definition.getString(1));
						}
					}
					gestionnaireEvenements.handle(Future.succeededFuture());
				} else {
					gestionnaireEvenements.handle(Future.failedFuture(attribuerAsync.cause()));
				}
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void indexerCluster(Cluster o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSite requeteSite = o.getRequeteSite_();
		try {
			o.initLoinPourClasse(requeteSite);
			o.indexerPourClasse();
			gestionnaireEvenements.handle(Future.succeededFuture());
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}
}
