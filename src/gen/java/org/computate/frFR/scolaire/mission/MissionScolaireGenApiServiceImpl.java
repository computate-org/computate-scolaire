package org.computate.frFR.scolaire.mission;

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
import io.vertx.ext.auth.oauth2.KeycloakHelper;
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
import org.computate.frFR.scolaire.mission.MissionScolairePage;
import org.apache.solr.common.SolrDocument;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.api.OperationRequest;
import java.time.format.DateTimeFormatter;
import io.vertx.ext.sql.SQLConnection;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import io.vertx.core.Handler;
import java.util.Collections;
import org.computate.frFR.scolaire.requete.RequeteSite;


public class MissionScolaireGenApiServiceImpl implements MissionScolaireGenApiService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MissionScolaireGenApiServiceImpl.class);

	private static final String SERVICE_ADDRESS = "MissionScolaireApiServiceImpl";

	protected SiteContexte siteContexte;

	public MissionScolaireGenApiServiceImpl(SiteContexte siteContexte) {
		this.siteContexte = siteContexte;
		MissionScolaireGenApiService service = MissionScolaireGenApiService.creerProxy(siteContexte.getVertx(), SERVICE_ADDRESS);
	}

	// Recherche //

	@Override
	public void rechercheMissionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = genererRequeteSitePourMissionScolaire(siteContexte, operationRequete);
			rechercheMissionScolaire(requeteSite, false, true, null, a -> {
				if(a.succeeded()) {
					ListeRecherche<MissionScolaire> listeMissionScolaire = a.result();
					reponse200RechercheMissionScolaire(listeMissionScolaire, b -> {
						if(b.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(b.result()));
						} else {
							erreurMissionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurMissionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurMissionScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200RechercheMissionScolaire(ListeRecherche<MissionScolaire> listeMissionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSite requeteSite = listeMissionScolaire.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeMissionScolaire.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			QueryResponse reponseRecherche = listeMissionScolaire.getQueryResponse();
			SolrDocumentList documentsSolr = listeMissionScolaire.getSolrDocumentList();
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
			for(int i = 0; i < listeMissionScolaire.size(); i++) {
				MissionScolaire o = listeMissionScolaire.getList().get(i);
				Object entiteValeur;
				Integer entiteNumero = 0;

				w.t(2);
				if(i > 0)
					w.s(", ");
				w.l("{");

				entiteValeur = o.getPk();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"pk\": ", entiteValeur);

				entiteValeur = o.getCree();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"cree\": ", w.qjs(entiteValeur));

				entiteValeur = o.getModifie();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"modifie\": ", w.qjs(entiteValeur));

				entiteValeur = o.getArchive();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"archive\": ", entiteValeur);

				entiteValeur = o.getSupprime();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"supprime\": ", entiteValeur);

				entiteValeur = o.getClasseNomCanonique();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"classeNomCanonique\": ", w.qjs(entiteValeur));

				entiteValeur = o.getClasseNomSimple();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"classeNomSimple\": ", w.qjs(entiteValeur));

				entiteValeur = o.getMissionNom();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"missionNom\": ", w.qjs(entiteValeur));

				entiteValeur = o.getEcoleNumeroTelephone();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleNumeroTelephone\": ", w.qjs(entiteValeur));

				entiteValeur = o.getMissionId();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"missionId\": ", w.qjs(entiteValeur));

				entiteValeur = o.getPageUri();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"pageUri\": ", w.qjs(entiteValeur));

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
	public void postMissionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = genererRequeteSitePourMissionScolaire(siteContexte, operationRequete, body);
			sqlMissionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					creerPOSTMissionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							MissionScolaire missionScolaire = b.result();
							sqlPOSTMissionScolaire(missionScolaire, c -> {
								if(c.succeeded()) {
									definirMissionScolaire(missionScolaire, d -> {
										if(d.succeeded()) {
											attribuerMissionScolaire(missionScolaire, e -> {
												if(e.succeeded()) {
													indexerMissionScolaire(missionScolaire, f -> {
														if(f.succeeded()) {
															reponse200POSTMissionScolaire(missionScolaire, g -> {
																if(f.succeeded()) {
																	SQLConnection connexionSql = requeteSite.getConnexionSql();
																	connexionSql.commit(h -> {
																		if(a.succeeded()) {
																			connexionSql.close(i -> {
																				if(a.succeeded()) {
																					gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																				} else {
																					erreurMissionScolaire(requeteSite, gestionnaireEvenements, i);
																				}
																			});
																		} else {
																			erreurMissionScolaire(requeteSite, gestionnaireEvenements, h);
																		}
																	});
																} else {
																	erreurMissionScolaire(requeteSite, gestionnaireEvenements, g);
																}
															});
														} else {
															erreurMissionScolaire(requeteSite, gestionnaireEvenements, f);
														}
													});
												} else {
													erreurMissionScolaire(requeteSite, gestionnaireEvenements, e);
												}
											});
										} else {
											erreurMissionScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurMissionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurMissionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurMissionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurMissionScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void creerPOSTMissionScolaire(RequeteSite requeteSite, Handler<AsyncResult<MissionScolaire>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();

			connexionSql.queryWithParams(
					SiteContexte.SQL_creer
					, new JsonArray(Arrays.asList(MissionScolaire.class.getCanonicalName(), utilisateurId))
					, creerAsync
			-> {
				JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = creerLigne.getLong(0);
				MissionScolaire o = new MissionScolaire();
				o.setPk(pk);
				o.initLoinMissionScolaire(requeteSite);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlPOSTMissionScolaire(MissionScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
					case "missionNom":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("missionNom", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleNumeroTelephone":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ecoleNumeroTelephone", jsonObject.getString(entiteVar), pk));
						break;
					case "missionId":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("missionId", jsonObject.getString(entiteVar), pk));
						break;
					case "pageUri":
						postSql.append(SiteContexte.SQL_setD);
						postSqlParams.addAll(Arrays.asList("pageUri", jsonObject.getString(entiteVar), pk));
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

	public void reponse200POSTMissionScolaire(MissionScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
	public void patchMissionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = genererRequeteSitePourMissionScolaire(siteContexte, operationRequete, body);
			sqlMissionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurMissionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheMissionScolaire(requeteSite, false, true, null, c -> {
								if(c.succeeded()) {
									ListeRecherche<MissionScolaire> listeMissionScolaire = c.result();
									listePATCHMissionScolaire(listeMissionScolaire, d -> {
										if(d.succeeded()) {
											SQLConnection connexionSql = requeteSite.getConnexionSql();
											connexionSql.commit(e -> {
												if(e.succeeded()) {
													connexionSql.close(f -> {
														if(f.succeeded()) {
															gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
														} else {
															erreurMissionScolaire(requeteSite, gestionnaireEvenements, f);
														}
													});
												} else {
													erreurMissionScolaire(requeteSite, gestionnaireEvenements, e);
												}
											});
										} else {
											erreurMissionScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurMissionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurMissionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurMissionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurMissionScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void listePATCHMissionScolaire(ListeRecherche<MissionScolaire> listeMissionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		listeMissionScolaire.getList().forEach(o -> {
			futures.add(
				sqlPATCHMissionScolaire(o).compose(
					a -> definirPATCHMissionScolaire(a).compose(
						b -> indexerPATCHMissionScolaire(b)
					)
				)
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				reponse200PATCHMissionScolaire(listeMissionScolaire, gestionnaireEvenements);
			} else {
				erreurMissionScolaire(listeMissionScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<MissionScolaire> sqlPATCHMissionScolaire(MissionScolaire o) {
		Future<MissionScolaire> future = Future.future();
		try {
			RequeteSite requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject requeteJson = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = requeteJson.fieldNames();
			MissionScolaire o2 = new MissionScolaire();

			patchSql.append(SiteContexte.SQL_modifier);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.frFR.scolaire.mission.MissionScolaire"));
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
					case "setArchive":
						o2.setArchive(requeteJson.getBoolean(methodeNom));
						patchSql.append(SiteContexte.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("archive", o2.getArchive(), pk));
						break;
					case "setSupprime":
						o2.setSupprime(requeteJson.getBoolean(methodeNom));
						patchSql.append(SiteContexte.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("supprime", o2.getSupprime(), pk));
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
					case "setMissionNom":
						o2.setMissionNom(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexte.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("missionNom", o2.getMissionNom(), pk));
						break;
					case "setEcoleNumeroTelephone":
						o2.setEcoleNumeroTelephone(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexte.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("ecoleNumeroTelephone", o2.getEcoleNumeroTelephone(), pk));
						break;
					case "setMissionId":
						o2.setMissionId(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexte.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("missionId", o2.getMissionId(), pk));
						break;
					case "setPageUri":
						o2.setPageUri(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexte.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("pageUri", o2.getPageUri(), pk));
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

	public Future<MissionScolaire> definirPATCHMissionScolaire(MissionScolaire o) {
		Future<MissionScolaire> future = Future.future();
		try {
			RequeteSite requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			connexionSql.queryWithParams(
					SiteContexte.SQL_definir
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

	public Future<Void> indexerPATCHMissionScolaire(MissionScolaire o) {
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

	public void reponse200PATCHMissionScolaire(ListeRecherche<MissionScolaire> listeMissionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSite requeteSite = listeMissionScolaire.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeMissionScolaire.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getMissionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = genererRequeteSitePourMissionScolaire(siteContexte, operationRequete);
			rechercheMissionScolaire(requeteSite, false, true, null, a -> {
				if(a.succeeded()) {
					ListeRecherche<MissionScolaire> listeMissionScolaire = a.result();
					reponse200GETMissionScolaire(listeMissionScolaire, b -> {
						if(b.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(b.result()));
						} else {
							erreurMissionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurMissionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurMissionScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200GETMissionScolaire(ListeRecherche<MissionScolaire> listeMissionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSite requeteSite = listeMissionScolaire.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeMissionScolaire.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			SolrDocumentList documentsSolr = listeMissionScolaire.getSolrDocumentList();

			if(listeMissionScolaire.size() > 0) {
				SolrDocument documentSolr = documentsSolr.get(0);
				MissionScolaire o = listeMissionScolaire.get(0);
				Object entiteValeur;
				Integer entiteNumero = 0;

				w.l("{");

				entiteValeur = o.getPk();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"pk\": ", entiteValeur);

				entiteValeur = o.getCree();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"cree\": ", w.qjs(entiteValeur));

				entiteValeur = o.getModifie();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"modifie\": ", w.qjs(entiteValeur));

				entiteValeur = o.getArchive();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"archive\": ", entiteValeur);

				entiteValeur = o.getSupprime();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"supprime\": ", entiteValeur);

				entiteValeur = o.getClasseNomCanonique();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"classeNomCanonique\": ", w.qjs(entiteValeur));

				entiteValeur = o.getClasseNomSimple();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"classeNomSimple\": ", w.qjs(entiteValeur));

				entiteValeur = o.getMissionNom();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"missionNom\": ", w.qjs(entiteValeur));

				entiteValeur = o.getEcoleNumeroTelephone();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleNumeroTelephone\": ", w.qjs(entiteValeur));

				entiteValeur = o.getMissionId();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"missionId\": ", w.qjs(entiteValeur));

				entiteValeur = o.getPageUri();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"pageUri\": ", w.qjs(entiteValeur));

				w.l("}");
			}
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteMissionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = genererRequeteSitePourMissionScolaire(siteContexte, operationRequete);
			sqlMissionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					rechercheMissionScolaire(requeteSite, false, true, null, b -> {
						if(b.succeeded()) {
							ListeRecherche<MissionScolaire> listeMissionScolaire = b.result();
							supprimerDELETEMissionScolaire(requeteSite, c -> {
								if(c.succeeded()) {
									reponse200DELETEMissionScolaire(requeteSite, d -> {
										if(d.succeeded()) {
											SQLConnection connexionSql = requeteSite.getConnexionSql();
											connexionSql.commit(e -> {
												if(e.succeeded()) {
													connexionSql.close(f -> {
														if(f.succeeded()) {
															gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
														} else {
															erreurMissionScolaire(requeteSite, gestionnaireEvenements, f);
														}
													});
												} else {
													erreurMissionScolaire(requeteSite, gestionnaireEvenements, e);
												}
											});
										} else {
											erreurMissionScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurMissionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurMissionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurMissionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurMissionScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void supprimerDELETEMissionScolaire(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			Long pk = requeteSite.getRequetePk();

			connexionSql.queryWithParams(
					SiteContexte.SQL_supprimer
					, new JsonArray(Arrays.asList(pk, MissionScolaire.class.getCanonicalName(), pk, pk, pk, pk))
					, supprimerAsync
			-> {
				gestionnaireEvenements.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200DELETEMissionScolaire(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(requeteSite, buffer);
			requeteSite.setW(w);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// RecherchePage //

	@Override
	public void recherchepageMissionScolaireId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		recherchepageMissionScolaire(operationRequete, gestionnaireEvenements);
	}

	@Override
	public void recherchepageMissionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = genererRequeteSitePourMissionScolaire(siteContexte, operationRequete);
			sqlMissionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurMissionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheMissionScolaire(requeteSite, false, true, "/mission", c -> {
								if(c.succeeded()) {
									ListeRecherche<MissionScolaire> listeMissionScolaire = c.result();
									reponse200RecherchePageMissionScolaire(listeMissionScolaire, d -> {
										if(d.succeeded()) {
											SQLConnection connexionSql = requeteSite.getConnexionSql();
											connexionSql.commit(e -> {
												if(e.succeeded()) {
													connexionSql.close(f -> {
														if(f.succeeded()) {
															gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
														} else {
															erreurMissionScolaire(requeteSite, gestionnaireEvenements, f);
														}
													});
												} else {
													erreurMissionScolaire(requeteSite, gestionnaireEvenements, e);
												}
											});
										} else {
											erreurMissionScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurMissionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurMissionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurMissionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurMissionScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200RecherchePageMissionScolaire(ListeRecherche<MissionScolaire> listeMissionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSite requeteSite = listeMissionScolaire.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeMissionScolaire.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			MissionScolairePage page = new MissionScolairePage();
			page.setPageUrl("/api/mission");
			SolrDocument pageDocumentSolr = new SolrDocument();

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/mission");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			page.setListeMissionScolaire(listeMissionScolaire);
			page.initLoinMissionScolairePage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, new CaseInsensitiveHeaders())));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public String varIndexeMissionScolaire(String entiteVar) {
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
			case "missionNom":
				return "missionNom_indexed_string";
			case "ecoleNumeroTelephone":
				return "ecoleNumeroTelephone_indexed_string";
			case "pageUri":
				return "pageUri_indexed_string";
			default:
				throw new RuntimeException(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	// Partagé //

	public void erreurMissionScolaire(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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

	public void sqlMissionScolaire(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public RequeteSite genererRequeteSitePourMissionScolaire(SiteContexte siteContexte, OperationRequest operationRequete) {
		return genererRequeteSitePourMissionScolaire(siteContexte, operationRequete, null);
	}

	public RequeteSite genererRequeteSitePourMissionScolaire(SiteContexte siteContexte, OperationRequest operationRequete, JsonObject body) {
		Vertx vertx = siteContexte.getVertx();
		RequeteSite requeteSite = new RequeteSite();
		requeteSite.setObjetJson(body);
		requeteSite.setVertx(vertx);
		requeteSite.setSiteContexte_(siteContexte);
		requeteSite.setConfigSite_(siteContexte.getConfigSite());
		requeteSite.setOperationRequete(operationRequete);
		requeteSite.initLoinRequeteSite(requeteSite);

		return requeteSite;
	}

	public void utilisateurMissionScolaire(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
								utilisateurSite.setPk(pkUtilisateur);

								connexionSql.queryWithParams(
										SiteContexte.SQL_definir
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
									SiteContexte.SQL_definir
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
									requeteSite.setUtilisateurSite(utilisateurSite);
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

	public void rechercheMissionScolaire(RequeteSite requeteSite, Boolean peupler, Boolean stocker, String classeApiUriMethode, Handler<AsyncResult<ListeRecherche<MissionScolaire>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<MissionScolaire> listeRecherche = new ListeRecherche<MissionScolaire>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(MissionScolaire.class);
			if(entiteListe != null)
			listeRecherche.setFields(entiteListe);
			listeRecherche.addSort("archive_indexed_boolean", ORDER.asc);
			listeRecherche.addSort("supprime_indexed_boolean", ORDER.asc);
			listeRecherche.addFilterQuery("classeNomCanonique_indexed_string:" + ClientUtils.escapeQueryChars("org.computate.frFR.scolaire.mission.MissionScolaire"));
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

				for(Object paramObjet : paramObjets) {
					switch(paramNom) {
						case "q":
							entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
							valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":"));
							varIndexe = "*".equals(entiteVar) ? entiteVar : varIndexeMissionScolaire(entiteVar);
							listeRecherche.setQuery(varIndexe + ":" + ("*".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));
							break;
						case "fq":
							entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
							valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":"));
							varIndexe = varIndexeMissionScolaire(entiteVar);
							listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
							break;
						case "sort":
							entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
							valeurTri = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
							varIndexe = varIndexeMissionScolaire(entiteVar);
							listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurTri));
							break;
						case "fl":
							entiteVar = StringUtils.trim((String)paramObjet);
							varIndexe = varIndexeMissionScolaire(entiteVar);
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

	public void definirMissionScolaire(MissionScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			connexionSql.queryWithParams(
					SiteContexte.SQL_definir
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

	public void attribuerMissionScolaire(MissionScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void indexerMissionScolaire(MissionScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
