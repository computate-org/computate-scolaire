package org.computate.scolaire.enUS.cluster;

import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import org.computate.scolaire.frFR.recherche.ResultatRecherche;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import io.vertx.core.json.Json;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.commons.lang3.StringUtils;
import java.security.Principal;
import org.apache.commons.lang3.exception.ExceptionUtils;
import java.io.PrintWriter;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrDocument;
import java.util.Collection;
import java.math.BigDecimal;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Router;
import io.vertx.core.Vertx;
import io.vertx.ext.reactivestreams.ReactiveReadStream;
import io.vertx.ext.reactivestreams.ReactiveWriteStream;
import io.vertx.core.MultiMap;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.api.validation.HTTPRequestValidationHandler;
import io.vertx.ext.web.api.validation.ParameterTypeValidator;
import io.vertx.ext.web.api.validation.ValidationException;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import io.vertx.core.Future;
import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.api.OperationResponse;
import io.vertx.core.CompositeFuture;
import org.apache.http.client.utils.URLEncodedUtils;
import java.nio.charset.Charset;
import org.apache.http.NameValuePair;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.auth.oauth2.KeycloakHelper;
import java.util.Optional;
import java.util.stream.Stream;
import java.net.URLDecoder;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.enUS.school.writer.AllWriter;
import org.computate.scolaire.frFR.cluster.ClusterFrFRPage;
import org.computate.scolaire.enUS.cluster.ClusterEnUSPage;


/**
 * Traduire: false
 **/
public class ClusterEnUSGenApiServiceImpl implements ClusterEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(ClusterEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "ClusterEnUSApiServiceImpl";

	protected SiteContextEnUS siteContexte;

	public ClusterEnUSGenApiServiceImpl(SiteContextEnUS siteContexte) {
		this.siteContexte = siteContexte;
		ClusterEnUSGenApiService service = ClusterEnUSGenApiService.creerProxy(siteContexte.getVertx(), SERVICE_ADDRESS);
	}

	// RechercheEnUSPage //

	@Override
	public void rechercheenuspageClusterId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		rechercheenuspageCluster(operationRequete, gestionnaireEvenements);
	}

	@Override
	public void rechercheenuspageCluster(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SiteRequestEnUS requeteSite = genererSiteRequestEnUSPourCluster(siteContexte, operationRequete);
			sqlCluster(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurCluster(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheCluster(requeteSite, false, true, "/enUS/cluster", c -> {
								if(c.succeeded()) {
									ListeRecherche<Cluster> listeCluster = c.result();
									reponse200RechercheEnUSPageCluster(listeCluster, d -> {
										if(d.succeeded()) {
											SQLConnection connexionSql = requeteSite.getConnexionSql();
											if(connexionSql == null) {
												gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											} else {
												connexionSql.commit(e -> {
													if(e.succeeded()) {
														connexionSql.close(f -> {
															if(f.succeeded()) {
																gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
															} else {
																erreurCluster(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														erreurCluster(requeteSite, gestionnaireEvenements, e);
													}
												});
											}
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
		} catch(Exception e) {
			erreurCluster(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200RechercheEnUSPageCluster(ListeRecherche<Cluster> listeCluster, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			SiteRequestEnUS requeteSite = listeCluster.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeCluster.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			ClusterEnUSPage page = new ClusterEnUSPage();
			SolrDocument pageDocumentSolr = new SolrDocument();

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/enUS/cluster");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			page.setListeCluster(listeCluster);
			page.initLoinClusterEnUSPage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, new CaseInsensitiveHeaders())));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void rechercheCluster(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SiteRequestEnUS requeteSite = genererSiteRequestEnUSPourCluster(siteContexte, operationRequete);
			rechercheCluster(requeteSite, false, true, null, a -> {
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
		} catch(Exception e) {
			erreurCluster(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200RechercheCluster(ListeRecherche<Cluster> listeCluster, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			SiteRequestEnUS requeteSite = listeCluster.getRequeteSite_();
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
		try {
			SiteRequestEnUS requeteSite = genererSiteRequestEnUSPourCluster(siteContexte, operationRequete, body);
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
		} catch(Exception e) {
			erreurCluster(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void creerPOSTCluster(SiteRequestEnUS requeteSite, Handler<AsyncResult<Cluster>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();

			connexionSql.queryWithParams(
					SiteContextEnUS.SQL_creer
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
			SiteRequestEnUS requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject jsonObject = requeteSite.getObjetJson();
			StringBuilder postSql = new StringBuilder();
			List<Object> postSqlParams = new ArrayList<Object>();

			if(jsonObject != null) {
				Set<String> entiteVars = jsonObject.fieldNames();
				for(String entiteVar : entiteVars) {
					switch(entiteVar) {
					case "created":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("created", jsonObject.getInstant(entiteVar), pk));
						break;
					case "modified":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("modified", jsonObject.getInstant(entiteVar), pk));
						break;
					case "archived":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("archived", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "deleted":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("deleted", jsonObject.getBoolean(entiteVar), pk));
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
			SiteRequestEnUS requeteSite = o.getRequeteSite_();
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
		try {
			SiteRequestEnUS requeteSite = genererSiteRequestEnUSPourCluster(siteContexte, operationRequete, body);
			sqlCluster(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurCluster(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheCluster(requeteSite, false, true, null, c -> {
								if(c.succeeded()) {
									ListeRecherche<Cluster> listeCluster = c.result();
									listePATCHCluster(listeCluster, d -> {
										if(d.succeeded()) {
											SQLConnection connexionSql = requeteSite.getConnexionSql();
											if(connexionSql == null) {
												gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											} else {
												connexionSql.commit(e -> {
													if(e.succeeded()) {
														connexionSql.close(f -> {
															if(f.succeeded()) {
																gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
															} else {
																erreurCluster(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														erreurCluster(requeteSite, gestionnaireEvenements, e);
													}
												});
											}
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
		} catch(Exception e) {
			erreurCluster(null, gestionnaireEvenements, Future.failedFuture(e));
		}
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
			SiteRequestEnUS requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject requeteJson = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = requeteJson.fieldNames();
			Cluster o2 = new Cluster();

			patchSql.append(SiteContextEnUS.SQL_modifier);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.cluster.Cluster"));
			for(String methodeNom : methodeNoms) {
				switch(methodeNom) {
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
			SiteRequestEnUS requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			connexionSql.queryWithParams(
					SiteContextEnUS.SQL_definir
					, new JsonArray(Arrays.asList(pk, pk, pk))
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
			SiteRequestEnUS requeteSite = listeCluster.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeCluster.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			buffer.appendString("{}");
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getCluster(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SiteRequestEnUS requeteSite = genererSiteRequestEnUSPourCluster(siteContexte, operationRequete);
			rechercheCluster(requeteSite, false, true, null, a -> {
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
		} catch(Exception e) {
			erreurCluster(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200GETCluster(ListeRecherche<Cluster> listeCluster, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			SiteRequestEnUS requeteSite = listeCluster.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeCluster.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			SolrDocumentList documentsSolr = listeCluster.getSolrDocumentList();

			if(listeCluster.size() > 0) {
				SolrDocument documentSolr = documentsSolr.get(0);
				Cluster o = listeCluster.get(0);
				Object entiteValeur;
				Integer entiteNumero = 0;

				w.l("{");

				w.l("}");
			}
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteCluster(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SiteRequestEnUS requeteSite = genererSiteRequestEnUSPourCluster(siteContexte, operationRequete);
			sqlCluster(requeteSite, a -> {
				if(a.succeeded()) {
					rechercheCluster(requeteSite, false, true, null, b -> {
						if(b.succeeded()) {
							ListeRecherche<Cluster> listeCluster = b.result();
							supprimerDELETECluster(requeteSite, c -> {
								if(c.succeeded()) {
									reponse200DELETECluster(requeteSite, d -> {
										if(d.succeeded()) {
											SQLConnection connexionSql = requeteSite.getConnexionSql();
											if(connexionSql == null) {
												gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											} else {
												connexionSql.commit(e -> {
													if(e.succeeded()) {
														connexionSql.close(f -> {
															if(f.succeeded()) {
																gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
															} else {
																erreurCluster(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														erreurCluster(requeteSite, gestionnaireEvenements, e);
													}
												});
											}
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
		} catch(Exception e) {
			erreurCluster(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void supprimerDELETECluster(SiteRequestEnUS requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			Long pk = requeteSite.getRequetePk();

			connexionSql.queryWithParams(
					SiteContextEnUS.SQL_supprimer
					, new JsonArray(Arrays.asList(pk, Cluster.class.getCanonicalName(), pk, pk, pk, pk))
					, supprimerAsync
			-> {
				gestionnaireEvenements.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200DELETECluster(SiteRequestEnUS requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
			case "cree":
				return "cree_indexed_date";
			case "modifie":
				return "modifie_indexed_date";
			case "archive":
				return "archive_indexed_boolean";
			case "supprime":
				return "supprime_indexed_boolean";
			case "classeNomCanonique":
				return "classeNomCanonique_indexed_string";
			case "classeNomSimple":
				return "classeNomSimple_indexed_string";
			case "classeNomsCanoniques":
				return "classeNomsCanoniques_indexed_strings";
			default:
				throw new RuntimeException(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	public String varRechercheCluster(String entiteVar) {
		switch(entiteVar) {
			default:
				throw new RuntimeException(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	public String varSuggereCluster(String entiteVar) {
		switch(entiteVar) {
			default:
				throw new RuntimeException(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	// Partagé //

	public void erreurCluster(SiteRequestEnUS requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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
		if(requeteSite != null) {
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
		} else {
			gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));
		}
	}

	public void sqlCluster(SiteRequestEnUS requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SQLClient clientSql = requeteSite.getSiteContexte_().getClientSql();

			if(clientSql == null) {
				gestionnaireEvenements.handle(Future.succeededFuture());
			} else {
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
			}
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public SiteRequestEnUS genererSiteRequestEnUSPourCluster(SiteContextEnUS siteContexte, OperationRequest operationRequete) {
		return genererSiteRequestEnUSPourCluster(siteContexte, operationRequete, null);
	}

	public SiteRequestEnUS genererSiteRequestEnUSPourCluster(SiteContextEnUS siteContexte, OperationRequest operationRequete, JsonObject body) {
		Vertx vertx = siteContexte.getVertx();
		SiteRequestEnUS requeteSite = new SiteRequestEnUS();
		requeteSite.setObjetJson(body);
		requeteSite.setVertx(vertx);
		requeteSite.setSiteContexte_(siteContexte);
		requeteSite.setConfigSite_(siteContexte.getConfigSite());
		requeteSite.setOperationRequete(operationRequete);
		requeteSite.initLoinSiteRequestEnUS(requeteSite);

		return requeteSite;
	}

	public void utilisateurCluster(SiteRequestEnUS requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			if(utilisateurId == null) {
				gestionnaireEvenements.handle(Future.succeededFuture());
			} else {
				connexionSql.queryWithParams(
						SiteContextEnUS.SQL_selectC
						, new JsonArray(Arrays.asList("org.computate.scolaire.frFR.utilisateur.UtilisateurSite", utilisateurId))
						, selectCAsync
				-> {
					if(selectCAsync.succeeded()) {
						JsonArray utilisateurValeurs = selectCAsync.result().getResults().stream().findFirst().orElse(null);
						if(utilisateurValeurs == null) {
							connexionSql.queryWithParams(
									SiteContextEnUS.SQL_creer
									, new JsonArray(Arrays.asList("org.computate.scolaire.frFR.utilisateur.UtilisateurSite", utilisateurId))
									, creerAsync
							-> {
								JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
								Long pkUtilisateur = creerLigne.getLong(0);
								UtilisateurSite utilisateurSite = new UtilisateurSite();
								utilisateurSite.setPk(pkUtilisateur);

								connexionSql.queryWithParams(
										SiteContextEnUS.SQL_definir
										, new JsonArray(Arrays.asList(pkUtilisateur, pkUtilisateur, pkUtilisateur))
										, definirAsync
								-> {
									if(definirAsync.succeeded()) {
										try {
											for(JsonArray definition : definirAsync.result().getResults()) {
												utilisateurSite.definirPourClasse(definition.getString(0), definition.getString(1));
											}
											JsonObject utilisateurVertx = requeteSite.getOperationRequete().getUser();
											JsonObject principalJson = KeycloakHelper.parseToken(utilisateurVertx.getString("access_token"));
											utilisateurSite.setUtilisateurNom(principalJson.getString("preferred_username"));
											utilisateurSite.setUtilisateurPrenom(principalJson.getString("given_name"));
											utilisateurSite.setUtilisateurNomFamille(principalJson.getString("family_name"));
											utilisateurSite.setUtilisateurId(principalJson.getString("sub"));
											utilisateurSite.initLoinPourClasse(requeteSite);
											utilisateurSite.indexerPourClasse();
											requeteSite.setUtilisateurSite(utilisateurSite);
											requeteSite.setUtilisateurNom(principalJson.getString("preferred_username"));
											requeteSite.setUtilisateurPrenom(principalJson.getString("given_name"));
											requeteSite.setUtilisateurNomFamille(principalJson.getString("family_name"));
											requeteSite.setUtilisateurId(principalJson.getString("sub"));
											gestionnaireEvenements.handle(Future.succeededFuture());
										} catch(Exception e) {
											gestionnaireEvenements.handle(Future.failedFuture(e));
										}
									} else {
										gestionnaireEvenements.handle(Future.failedFuture(definirAsync.cause()));
									}
								});
							});
						} else {
							Long pkUtilisateur = utilisateurValeurs.getLong(0);
							UtilisateurSite utilisateurSite = new UtilisateurSite();
							utilisateurSite.setPk(pkUtilisateur);

							connexionSql.queryWithParams(
									SiteContextEnUS.SQL_definir
									, new JsonArray(Arrays.asList(pkUtilisateur, pkUtilisateur, pkUtilisateur))
									, definirAsync
							-> {
								if(definirAsync.succeeded()) {
									for(JsonArray definition : definirAsync.result().getResults()) {
										utilisateurSite.definirPourClasse(definition.getString(0), definition.getString(1));
									}
									JsonObject utilisateurVertx = requeteSite.getOperationRequete().getUser();
									JsonObject principalJson = KeycloakHelper.parseToken(utilisateurVertx.getString("access_token"));
									utilisateurSite.setUtilisateurNom(principalJson.getString("preferred_username"));
									utilisateurSite.setUtilisateurPrenom(principalJson.getString("given_name"));
									utilisateurSite.setUtilisateurNomFamille(principalJson.getString("family_name"));
									utilisateurSite.setUtilisateurId(principalJson.getString("sub"));
									utilisateurSite.initLoinPourClasse(requeteSite);
									utilisateurSite.indexerPourClasse();
									requeteSite.setUtilisateurSite(utilisateurSite);
									requeteSite.setUtilisateurNom(principalJson.getString("preferred_username"));
									requeteSite.setUtilisateurPrenom(principalJson.getString("given_name"));
									requeteSite.setUtilisateurNomFamille(principalJson.getString("family_name"));
									requeteSite.setUtilisateurId(principalJson.getString("sub"));
									gestionnaireEvenements.handle(Future.succeededFuture());
								} else {
									gestionnaireEvenements.handle(Future.failedFuture(definirAsync.cause()));
								}
							});
						}
					} else {
						gestionnaireEvenements.handle(Future.failedFuture(selectCAsync.cause()));
					}
				});
			}
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void rechercheCluster(SiteRequestEnUS requeteSite, Boolean peupler, Boolean stocker, String classeApiUriMethode, Handler<AsyncResult<ListeRecherche<Cluster>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<Cluster> listeRecherche = new ListeRecherche<Cluster>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(Cluster.class);
			if(entiteListe != null)
			listeRecherche.setFields(entiteListe);
			listeRecherche.addSort("archive_indexed_boolean", ORDER.asc);
			listeRecherche.addSort("supprime_indexed_boolean", ORDER.asc);
			listeRecherche.addFilterQuery("classeNomsCanoniques_indexed_strings:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.cluster.Cluster"));
			UtilisateurSite utilisateurSite = requeteSite.getUtilisateurSite();
			if(utilisateurSite != null && !utilisateurSite.getVoirSupprime())
				listeRecherche.addFilterQuery("supprime_indexed_boolean:false");
			if(utilisateurSite != null && !utilisateurSite.getVoirArchive())
				listeRecherche.addFilterQuery("archive_indexed_boolean:false");

			String pageUri = null;
			String id = operationRequete.getParams().getJsonObject("path").getString("id");
			if(id != null) {
				pageUri = classeApiUriMethode + "/" + id;
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

				try {
					for(Object paramObjet : paramObjets) {
						switch(paramNom) {
							case "q":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								varIndexe = "*".equals(entiteVar) ? entiteVar : varRechercheCluster(entiteVar);
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								valeurIndexe = StringUtils.isEmpty(valeurIndexe) ? "*" : valeurIndexe;
								listeRecherche.setQuery(varIndexe + ":" + ("*".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));
								if(!"*".equals(entiteVar)) {
									listeRecherche.setHighlight(true);
									listeRecherche.setHighlightSnippets(3);
									listeRecherche.addHighlightField(varIndexe);
									listeRecherche.setParam("hl.encoder", "html");
								}
								break;
							case "fq":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
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
				} catch(Exception e) {
					gestionnaireEvenements.handle(Future.failedFuture(e));
				}
			});
			listeRecherche.initLoinPourClasse(requeteSite);
			gestionnaireEvenements.handle(Future.succeededFuture(listeRecherche));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void definirCluster(Cluster o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SiteRequestEnUS requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			connexionSql.queryWithParams(
					SiteContextEnUS.SQL_definir
					, new JsonArray(Arrays.asList(pk, pk, pk))
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
			SiteRequestEnUS requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			connexionSql.queryWithParams(
					SiteContextEnUS.SQL_attribuer
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
		SiteRequestEnUS requeteSite = o.getRequeteSite_();
		try {
			o.initLoinPourClasse(requeteSite);
			o.indexerPourClasse();
			gestionnaireEvenements.handle(Future.succeededFuture());
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}
}
