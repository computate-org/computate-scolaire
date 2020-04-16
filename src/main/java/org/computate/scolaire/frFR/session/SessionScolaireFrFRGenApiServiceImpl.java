package org.computate.scolaire.frFR.session;

import org.computate.scolaire.frFR.age.AgeScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.age.AgeScolaire;
import org.computate.scolaire.frFR.saison.SaisonScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.saison.SaisonScolaire;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.computate.scolaire.frFR.recherche.ResultatRecherche;
import io.vertx.core.WorkerExecutor;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailMessage;
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
import java.util.HashSet;
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
import java.time.LocalTime;
import java.sql.Timestamp;
import io.vertx.core.Future;
import io.vertx.core.Promise;
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
import java.time.ZonedDateTime;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSiteFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;


/**
 * Traduire: false
 * NomCanonique.enUS: org.computate.scolaire.enUS.session.SchoolSessionEnUSGenApiServiceImpl
 **/
public class SessionScolaireFrFRGenApiServiceImpl implements SessionScolaireFrFRGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SessionScolaireFrFRGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SessionScolaireFrFRApiServiceImpl";

	protected SiteContexteFrFR siteContexte;

	public SessionScolaireFrFRGenApiServiceImpl(SiteContexteFrFR siteContexte) {
		this.siteContexte = siteContexte;
	}

	// POST //

	@Override
	public void postSessionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSessionScolaire(siteContexte, operationRequete, body);

			List<String> roles = Arrays.asList("SiteAdmin");
			if(
					!CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRessource(), roles)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRoyaume(), roles)
					) {
				gestionnaireEvenements.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "rôles requis : " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSessionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSessionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							RequeteApi requeteApi = new RequeteApi();
							requeteApi.setRows(1);
							requeteApi.setNumFound(1L);
							requeteApi.initLoinRequeteApi(requeteSite);
							requeteSite.setRequeteApi_(requeteApi);
							postSessionScolaireFuture(requeteSite, false, c -> {
								if(c.succeeded()) {
									SessionScolaire sessionScolaire = c.result();
									requeteApiSessionScolaire(sessionScolaire);
									postSessionScolaireReponse(sessionScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postSessionScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("postSessionScolaire a échoué. ", d.cause()));
											erreurSessionScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSessionScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public Future<SessionScolaire> postSessionScolaireFuture(RequeteSiteFrFR requeteSite, Boolean inheritPk, Handler<AsyncResult<SessionScolaire>> gestionnaireEvenements) {
		Promise<SessionScolaire> promise = Promise.promise();
		try {
			creerSessionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					SessionScolaire sessionScolaire = a.result();
					sqlPOSTSessionScolaire(sessionScolaire, inheritPk, b -> {
						if(b.succeeded()) {
							definirIndexerSessionScolaire(sessionScolaire, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(sessionScolaire));
									promise.complete(sessionScolaire);
								} else {
									erreurSessionScolaire(requeteSite, null, c);
								}
							});
						} else {
							erreurSessionScolaire(requeteSite, null, b);
						}
					});
				} else {
					erreurSessionScolaire(requeteSite, null, a);
				}
			});
		} catch(Exception e) {
			erreurSessionScolaire(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTSessionScolaire(SessionScolaire o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject jsonObject = requeteSite.getObjetJson();
			StringBuilder postSql = new StringBuilder();
			List<Object> postSqlParams = new ArrayList<Object>();

			if(jsonObject != null) {
				Set<String> entiteVars = jsonObject.fieldNames();
				for(String entiteVar : entiteVars) {
					switch(entiteVar) {
					case "inheritPk":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inheritPk", jsonObject.getString(entiteVar), pk));
						break;
					case "cree":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("cree", jsonObject.getString(entiteVar), pk));
						break;
					case "modifie":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("modifie", jsonObject.getString(entiteVar), pk));
						break;
					case "archive":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("archive", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "supprime":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("supprime", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "ageCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							ListeRecherche<AgeScolaire> listeRecherche = new ListeRecherche<AgeScolaire>();
							listeRecherche.setQuery("*:*");
							listeRecherche.setStocker(true);
							listeRecherche.setC(AgeScolaire.class);
							listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							listeRecherche.initLoinListeRecherche(requeteSite);
							l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								postSql.append(SiteContexteFrFR.SQL_addA);
								postSqlParams.addAll(Arrays.asList("ageCles", pk, "sessionCle", l));
							}
						}
						break;
					case "saisonCle":
						{
							Long l = Long.parseLong(jsonObject.getString(entiteVar));
							ListeRecherche<SaisonScolaire> listeRecherche = new ListeRecherche<SaisonScolaire>();
							listeRecherche.setQuery("*:*");
							listeRecherche.setStocker(true);
							listeRecherche.setC(SaisonScolaire.class);
							listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							listeRecherche.initLoinListeRecherche(requeteSite);
							l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								postSql.append(SiteContexteFrFR.SQL_addA);
								postSqlParams.addAll(Arrays.asList("saisonCle", pk, "sessionCles", Long.parseLong(jsonObject.getString(entiteVar))));
							}
						}
						break;
					case "ecoleAddresse":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ecoleAddresse", jsonObject.getString(entiteVar), pk));
						break;
					case "sessionJourDebut":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("sessionJourDebut", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "sessionJourFin":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("sessionJourFin", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					}
				}
			}
			connexionSql.queryWithParams(
					postSql.toString()
					, new JsonArray(postSqlParams)
					, postAsync
			-> {
				if(postAsync.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture());
				} else {
					gestionnaireEvenements.handle(Future.failedFuture(new Exception(postAsync.cause())));
				}
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void postSessionScolaireReponse(SessionScolaire sessionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = sessionScolaire.getRequeteSite_();
		reponse200POSTSessionScolaire(sessionScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								RequeteApi requeteApi = requeteApiSessionScolaire(sessionScolaire);
								sessionScolaire.requeteApiSessionScolaire();
								requeteSite.getVertx().eventBus().publish("websocketSessionScolaire", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200POSTSessionScolaire(SessionScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			JsonObject json = JsonObject.mapFrom(o);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTImport //

	@Override
	public void putimportSessionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSessionScolaire(siteContexte, operationRequete, body);

			List<String> roles = Arrays.asList("SiteAdmin");
			if(
					!CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRessource(), roles)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRoyaume(), roles)
					) {
				gestionnaireEvenements.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "rôles requis : " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSessionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSessionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							SQLConnection connexionSql = requeteSite.getConnexionSql();
							connexionSql.close(c -> {
								if(c.succeeded()) {
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											sqlSessionScolaire(requeteSite, d -> {
												if(d.succeeded()) {
													try {
														RequeteApi requeteApi = new RequeteApi();
														JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
														requeteApi.setRows(jsonArray.size());
														requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
														requeteApi.initLoinRequeteApi(requeteSite);
														requeteSite.setRequeteApi_(requeteApi);
														listePUTImportSessionScolaire(requeteApi, requeteSite, e -> {
															if(e.succeeded()) {
																putimportSessionScolaireReponse(requeteSite, f -> {
																	if(f.succeeded()) {
																		gestionnaireEvenements.handle(Future.succeededFuture(f.result()));
																		LOGGER.info(String.format("putimportSessionScolaire a réussi. "));
																	} else {
																		LOGGER.error(String.format("putimportSessionScolaire a échoué. ", f.cause()));
																		erreurSessionScolaire(requeteSite, gestionnaireEvenements, f);
																	}
																});
															} else {
																blockingCodeHandler.handle(Future.failedFuture(e.cause()));
															}
														});
													} catch(Exception ex) {
												blockingCodeHandler.handle(Future.failedFuture(ex));
													}
												} else {
													blockingCodeHandler.handle(Future.failedFuture(d.cause()));
												}
											});
										}, resultHandler -> {
										}
									);
								} else {
									erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSessionScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTImportSessionScolaire(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		jsonArray.forEach(obj -> {
			JsonObject json = (JsonObject)obj;

			json.put("inheritPk", json.getValue("pk"));

			RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourSessionScolaire(siteContexte, requeteSite.getOperationRequete(), json);
			requeteSite2.setConnexionSql(requeteSite.getConnexionSql());

			ListeRecherche<SessionScolaire> listeRecherche = new ListeRecherche<SessionScolaire>();
			listeRecherche.setStocker(true);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(SessionScolaire.class);
			listeRecherche.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
			listeRecherche.initLoinPourClasse(requeteSite2);

			if(listeRecherche.size() == 1) {
				SessionScolaire o = listeRecherche.get(0);
				JsonObject json2 = new JsonObject();
				for(String f : json.fieldNames()) {
					json2.put("set" + StringUtils.capitalize(f), json.getValue(f));
				}
				for(String f : o.getSauvegardes()) {
					if(!json.fieldNames().contains(f))
						json2.putNull("set" + StringUtils.capitalize(f));
				}
				requeteSite2.setObjetJson(json2);
				futures.add(
					patchSessionScolaireFuture(o, true, a -> {
						if(a.succeeded()) {
							SessionScolaire sessionScolaire = a.result();
							requeteApiSessionScolaire(sessionScolaire);
						} else {
							erreurSessionScolaire(requeteSite2, gestionnaireEvenements, a);
						}
					})
				);
			} else {
				futures.add(
					postSessionScolaireFuture(requeteSite2, true, a -> {
						if(a.succeeded()) {
							SessionScolaire sessionScolaire = a.result();
							requeteApiSessionScolaire(sessionScolaire);
						} else {
							erreurSessionScolaire(requeteSite2, gestionnaireEvenements, a);
						}
					})
				);
			}
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + jsonArray.size());
				reponse200PUTImportSessionScolaire(requeteSite, gestionnaireEvenements);
			} else {
				erreurSessionScolaire(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public void putimportSessionScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PUTImportSessionScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								RequeteApi requeteApi = requeteSite.getRequeteApi_();
								requeteSite.getVertx().eventBus().publish("websocketSessionScolaire", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTImportSessionScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(requeteApi).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTFusion //

	@Override
	public void putfusionSessionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSessionScolaire(siteContexte, operationRequete, body);

			List<String> roles = Arrays.asList("SiteAdmin");
			if(
					!CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRessource(), roles)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRoyaume(), roles)
					) {
				gestionnaireEvenements.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "rôles requis : " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSessionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSessionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							SQLConnection connexionSql = requeteSite.getConnexionSql();
							connexionSql.close(c -> {
								if(c.succeeded()) {
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											sqlSessionScolaire(requeteSite, d -> {
												if(d.succeeded()) {
													try {
														RequeteApi requeteApi = new RequeteApi();
														JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
														requeteApi.setRows(jsonArray.size());
														requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
														requeteApi.initLoinRequeteApi(requeteSite);
														requeteSite.setRequeteApi_(requeteApi);
														listePUTFusionSessionScolaire(requeteApi, requeteSite, e -> {
															if(e.succeeded()) {
																putfusionSessionScolaireReponse(requeteSite, f -> {
																	if(f.succeeded()) {
																		gestionnaireEvenements.handle(Future.succeededFuture(f.result()));
																		LOGGER.info(String.format("putfusionSessionScolaire a réussi. "));
																	} else {
																		LOGGER.error(String.format("putfusionSessionScolaire a échoué. ", f.cause()));
																		erreurSessionScolaire(requeteSite, gestionnaireEvenements, f);
																	}
																});
															} else {
																blockingCodeHandler.handle(Future.failedFuture(e.cause()));
															}
														});
													} catch(Exception ex) {
												blockingCodeHandler.handle(Future.failedFuture(ex));
													}
												} else {
													blockingCodeHandler.handle(Future.failedFuture(d.cause()));
												}
											});
										}, resultHandler -> {
										}
									);
								} else {
									erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSessionScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTFusionSessionScolaire(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		jsonArray.forEach(obj -> {
			JsonObject json = (JsonObject)obj;

			json.put("inheritPk", json.getValue("pk"));

			RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourSessionScolaire(siteContexte, requeteSite.getOperationRequete(), json);
			requeteSite2.setConnexionSql(requeteSite.getConnexionSql());

			ListeRecherche<SessionScolaire> listeRecherche = new ListeRecherche<SessionScolaire>();
			listeRecherche.setStocker(true);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(SessionScolaire.class);
			listeRecherche.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
			listeRecherche.initLoinPourClasse(requeteSite2);

			if(listeRecherche.size() == 1) {
				SessionScolaire o = listeRecherche.get(0);
				JsonObject json2 = new JsonObject();
				for(String f : json.fieldNames()) {
					json2.put("set" + StringUtils.capitalize(f), json.getValue(f));
				}
				for(String f : o.getSauvegardes()) {
					if(!json.fieldNames().contains(f))
						json2.putNull("set" + StringUtils.capitalize(f));
				}
				requeteSite2.setObjetJson(json2);
				futures.add(
					patchSessionScolaireFuture(o, false, a -> {
						if(a.succeeded()) {
							SessionScolaire sessionScolaire = a.result();
							requeteApiSessionScolaire(sessionScolaire);
						} else {
							erreurSessionScolaire(requeteSite2, gestionnaireEvenements, a);
						}
					})
				);
			} else {
				futures.add(
					postSessionScolaireFuture(requeteSite2, false, a -> {
						if(a.succeeded()) {
							SessionScolaire sessionScolaire = a.result();
							requeteApiSessionScolaire(sessionScolaire);
						} else {
							erreurSessionScolaire(requeteSite2, gestionnaireEvenements, a);
						}
					})
				);
			}
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + jsonArray.size());
				reponse200PUTFusionSessionScolaire(requeteSite, gestionnaireEvenements);
			} else {
				erreurSessionScolaire(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public void putfusionSessionScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PUTFusionSessionScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								RequeteApi requeteApi = requeteSite.getRequeteApi_();
								requeteSite.getVertx().eventBus().publish("websocketSessionScolaire", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTFusionSessionScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(requeteApi).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTCopie //

	@Override
	public void putcopieSessionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSessionScolaire(siteContexte, operationRequete, body);

			List<String> roles = Arrays.asList("SiteAdmin");
			if(
					!CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRessource(), roles)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRoyaume(), roles)
					) {
				gestionnaireEvenements.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "rôles requis : " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSessionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSessionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							SQLConnection connexionSql = requeteSite.getConnexionSql();
							connexionSql.close(c -> {
								if(c.succeeded()) {
									rechercheSessionScolaire(requeteSite, false, true, null, d -> {
										if(d.succeeded()) {
											ListeRecherche<SessionScolaire> listeSessionScolaire = d.result();
											RequeteApi requeteApi = new RequeteApi();
											requeteApi.setRows(listeSessionScolaire.getRows());
											requeteApi.setNumFound(listeSessionScolaire.getQueryResponse().getResults().getNumFound());
											requeteApi.initLoinRequeteApi(requeteSite);
											requeteSite.setRequeteApi_(requeteApi);
											WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
											executeurTravailleur.executeBlocking(
												blockingCodeHandler -> {
													sqlSessionScolaire(requeteSite, e -> {
														if(e.succeeded()) {
															try {
																listePUTCopieSessionScolaire(requeteApi, listeSessionScolaire, f -> {
																	if(f.succeeded()) {
																		putcopieSessionScolaireReponse(listeSessionScolaire, g -> {
																			if(g.succeeded()) {
																				gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("putcopieSessionScolaire a réussi. "));
																			} else {
																				LOGGER.error(String.format("putcopieSessionScolaire a échoué. ", g.cause()));
																				erreurSessionScolaire(requeteSite, gestionnaireEvenements, d);
																			}
																		});
																	} else {
																		blockingCodeHandler.handle(Future.failedFuture(f.cause()));
																	}
																});
															} catch(Exception ex) {
																blockingCodeHandler.handle(Future.failedFuture(ex));
															}
														} else {
															blockingCodeHandler.handle(Future.failedFuture(e.cause()));
														}
													});
												}, resultHandler -> {
												}
											);
										} else {
											erreurSessionScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSessionScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTCopieSessionScolaire(RequeteApi requeteApi, ListeRecherche<SessionScolaire> listeSessionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeSessionScolaire.getRequeteSite_();
		listeSessionScolaire.getList().forEach(o -> {
			futures.add(
				putcopieSessionScolaireFuture(requeteSite, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						SessionScolaire sessionScolaire = a.result();
						requeteApiSessionScolaire(sessionScolaire);
					} else {
						erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeSessionScolaire.size());
				if(listeSessionScolaire.next()) {
					requeteSite.getVertx().eventBus().publish("websocketSessionScolaire", JsonObject.mapFrom(requeteApi).toString());
					listePUTCopieSessionScolaire(requeteApi, listeSessionScolaire, gestionnaireEvenements);
				} else {
					reponse200PUTCopieSessionScolaire(listeSessionScolaire, gestionnaireEvenements);
				}
			} else {
				erreurSessionScolaire(listeSessionScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<SessionScolaire> putcopieSessionScolaireFuture(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Handler<AsyncResult<SessionScolaire>> gestionnaireEvenements) {
		Promise<SessionScolaire> promise = Promise.promise();
		try {

			jsonObject.put("sauvegardes", Optional.ofNullable(jsonObject.getJsonArray("sauvegardes")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("sauvegardes").add(o.getKey());
			});

			creerSessionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					SessionScolaire sessionScolaire = a.result();
					sqlPUTCopieSessionScolaire(sessionScolaire, jsonObject, b -> {
						if(b.succeeded()) {
							definirSessionScolaire(sessionScolaire, c -> {
								if(c.succeeded()) {
									attribuerSessionScolaire(sessionScolaire, d -> {
										if(d.succeeded()) {
											indexerSessionScolaire(sessionScolaire, e -> {
												if(e.succeeded()) {
													gestionnaireEvenements.handle(Future.succeededFuture(sessionScolaire));
													promise.complete(sessionScolaire);
												} else {
													gestionnaireEvenements.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											gestionnaireEvenements.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									gestionnaireEvenements.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			erreurSessionScolaire(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopieSessionScolaire(SessionScolaire o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			StringBuilder putSql = new StringBuilder();
			List<Object> putSqlParams = new ArrayList<Object>();

			if(jsonObject != null) {
				JsonArray entiteVars = jsonObject.getJsonArray("sauvegardes");
				for(Integer i = 0; i < entiteVars.size(); i++) {
					String entiteVar = entiteVars.getString(i);
					switch(entiteVar) {
					case "inheritPk":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inheritPk", jsonObject.getString(entiteVar), pk));
						break;
					case "cree":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("cree", jsonObject.getString(entiteVar), pk));
						break;
					case "modifie":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("modifie", jsonObject.getString(entiteVar), pk));
						break;
					case "archive":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("archive", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "supprime":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("supprime", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "ageCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContexteFrFR.SQL_addA);
							putSqlParams.addAll(Arrays.asList("ageCles", pk, "sessionCle", l));
						}
						break;
					case "saisonCle":
						putSql.append(SiteContexteFrFR.SQL_addA);
						putSqlParams.addAll(Arrays.asList("saisonCle", pk, "sessionCles", Long.parseLong(jsonObject.getString(entiteVar))));
						break;
					case "ecoleAddresse":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("ecoleAddresse", jsonObject.getString(entiteVar), pk));
						break;
					case "sessionJourDebut":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("sessionJourDebut", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "sessionJourFin":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("sessionJourFin", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					}
				}
			}
			connexionSql.queryWithParams(
					putSql.toString()
					, new JsonArray(putSqlParams)
					, postAsync
			-> {
				if(postAsync.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture());
				} else {
					gestionnaireEvenements.handle(Future.failedFuture(new Exception(postAsync.cause())));
				}
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void putcopieSessionScolaireReponse(ListeRecherche<SessionScolaire> listeSessionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeSessionScolaire.getRequeteSite_();
		reponse200PUTCopieSessionScolaire(listeSessionScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								RequeteApi requeteApi = requeteSite.getRequeteApi_();
								requeteSite.getVertx().eventBus().publish("websocketSessionScolaire", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTCopieSessionScolaire(ListeRecherche<SessionScolaire> listeSessionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeSessionScolaire.getRequeteSite_();
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(requeteApi).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSessionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSessionScolaire(siteContexte, operationRequete, body);

			List<String> roles = Arrays.asList("SiteAdmin");
			if(
					!CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRessource(), roles)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRoyaume(), roles)
					) {
				gestionnaireEvenements.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "rôles requis : " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSessionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSessionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							SQLConnection connexionSql = requeteSite.getConnexionSql();
							connexionSql.close(c -> {
								if(c.succeeded()) {
									rechercheSessionScolaire(requeteSite, false, true, null, d -> {
										if(d.succeeded()) {
											ListeRecherche<SessionScolaire> listeSessionScolaire = d.result();
											RequeteApi requeteApi = new RequeteApi();
											requeteApi.setRows(listeSessionScolaire.getRows());
											requeteApi.setNumFound(listeSessionScolaire.getQueryResponse().getResults().getNumFound());
											requeteApi.initLoinRequeteApi(requeteSite);
											requeteSite.setRequeteApi_(requeteApi);
											SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeSessionScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
											Date date = null;
											if(facets != null)
												date = (Date)facets.get("max_modifie");
											String dt;
											if(date == null)
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
											else
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
											listeSessionScolaire.addFilterQuery(String.format("modifie_indexed_date:[* TO %s]", dt));

											if(listeSessionScolaire.size() == 1) {
												SessionScolaire o = listeSessionScolaire.get(0);
												requeteApi.setPk(o.getPk());
												requeteApi.setOriginal(o);
												requeteApiSessionScolaire(o);
											o.requeteApiSessionScolaire();
											}
											WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
											executeurTravailleur.executeBlocking(
												blockingCodeHandler -> {
													sqlSessionScolaire(requeteSite, e -> {
														if(e.succeeded()) {
															try {
																listePATCHSessionScolaire(requeteApi, listeSessionScolaire, dt, f -> {
																	if(f.succeeded()) {
																		patchSessionScolaireReponse(listeSessionScolaire, g -> {
																			if(g.succeeded()) {
																				gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("patchSessionScolaire a réussi. "));
																			} else {
																				LOGGER.error(String.format("patchSessionScolaire a échoué. ", g.cause()));
																				erreurSessionScolaire(requeteSite, gestionnaireEvenements, d);
																			}
																		});
																	} else {
																		blockingCodeHandler.handle(Future.failedFuture(f.cause()));
																	}
																});
															} catch(Exception ex) {
																blockingCodeHandler.handle(Future.failedFuture(ex));
															}
														} else {
															blockingCodeHandler.handle(Future.failedFuture(e.cause()));
														}
													});
												}, resultHandler -> {
												}
											);
										} else {
											erreurSessionScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSessionScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePATCHSessionScolaire(RequeteApi requeteApi, ListeRecherche<SessionScolaire> listeSessionScolaire, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeSessionScolaire.getRequeteSite_();
		listeSessionScolaire.getList().forEach(o -> {
			futures.add(
				patchSessionScolaireFuture(o, false, a -> {
					if(a.succeeded()) {
							SessionScolaire sessionScolaire = a.result();
							requeteApiSessionScolaire(sessionScolaire);
					} else {
						erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeSessionScolaire.size());
				if(listeSessionScolaire.next(dt)) {
					requeteSite.getVertx().eventBus().publish("websocketSessionScolaire", JsonObject.mapFrom(requeteApi).toString());
					listePATCHSessionScolaire(requeteApi, listeSessionScolaire, dt, gestionnaireEvenements);
				} else {
					reponse200PATCHSessionScolaire(listeSessionScolaire, gestionnaireEvenements);
				}
			} else {
				erreurSessionScolaire(listeSessionScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<SessionScolaire> patchSessionScolaireFuture(SessionScolaire o, Boolean inheritPk, Handler<AsyncResult<SessionScolaire>> gestionnaireEvenements) {
		Promise<SessionScolaire> promise = Promise.promise();
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			sqlPATCHSessionScolaire(o, inheritPk, a -> {
				if(a.succeeded()) {
					SessionScolaire sessionScolaire = a.result();
					definirSessionScolaire(sessionScolaire, b -> {
						if(b.succeeded()) {
							attribuerSessionScolaire(sessionScolaire, c -> {
								if(c.succeeded()) {
									indexerSessionScolaire(sessionScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(sessionScolaire));
											promise.complete(sessionScolaire);
										} else {
											gestionnaireEvenements.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									gestionnaireEvenements.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			erreurSessionScolaire(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHSessionScolaire(SessionScolaire o, Boolean inheritPk, Handler<AsyncResult<SessionScolaire>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject requeteJson = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = requeteJson.fieldNames();
			SessionScolaire o2 = new SessionScolaire();

			patchSql.append(SiteContexteFrFR.SQL_modifier);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.frFR.session.SessionScolaire"));
			for(String methodeNom : methodeNoms) {
				switch(methodeNom) {
					case "setInheritPk":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inheritPk"));
						} else {
							o2.setInheritPk(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inheritPk", o2.jsonInheritPk(), pk));
						}
						break;
					case "setCree":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "cree"));
						} else {
							o2.setCree(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("cree", o2.jsonCree(), pk));
						}
						break;
					case "setModifie":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "modifie"));
						} else {
							o2.setModifie(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("modifie", o2.jsonModifie(), pk));
						}
						break;
					case "setArchive":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "archive"));
						} else {
							o2.setArchive(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("archive", o2.jsonArchive(), pk));
						}
						break;
					case "setSupprime":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "supprime"));
						} else {
							o2.setSupprime(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("supprime", o2.jsonSupprime(), pk));
						}
						break;
					case "addAgeCles":
						{
							Long l = Long.parseLong(requeteJson.getString(methodeNom));
							ListeRecherche<AgeScolaire> listeRecherche = new ListeRecherche<AgeScolaire>();
							listeRecherche.setQuery("*:*");
							listeRecherche.setStocker(true);
							listeRecherche.setC(AgeScolaire.class);
							listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							listeRecherche.initLoinListeRecherche(requeteSite);
							l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								patchSql.append(SiteContexteFrFR.SQL_addA);
								patchSqlParams.addAll(Arrays.asList("ageCles", pk, "sessionCle", l));
							}
						}
						break;
					case "addAllAgeCles":
						JsonArray addAllAgeClesValeurs = requeteJson.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllAgeClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllAgeClesValeurs.getString(i));
							ListeRecherche<AgeScolaire> listeRecherche = new ListeRecherche<AgeScolaire>();
							listeRecherche.setQuery("*:*");
							listeRecherche.setStocker(true);
							listeRecherche.setC(AgeScolaire.class);
							listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							listeRecherche.initLoinListeRecherche(requeteSite);
							l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								patchSql.append(SiteContexteFrFR.SQL_addA);
								patchSqlParams.addAll(Arrays.asList("ageCles", pk, "sessionCle", l));
							}
						}
						break;
					case "setAgeCles":
						JsonArray setAgeClesValeurs = requeteJson.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("ageCles", pk, "sessionCle"));
						for(Integer i = 0; i <  setAgeClesValeurs.size(); i++) {
							Long l = Long.parseLong(setAgeClesValeurs.getString(i));
							ListeRecherche<AgeScolaire> listeRecherche = new ListeRecherche<AgeScolaire>();
							listeRecherche.setQuery("*:*");
							listeRecherche.setStocker(true);
							listeRecherche.setC(AgeScolaire.class);
							listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							listeRecherche.initLoinListeRecherche(requeteSite);
							l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								patchSql.append(SiteContexteFrFR.SQL_addA);
								patchSqlParams.addAll(Arrays.asList("ageCles", pk, "sessionCle", l));
							}
						}
						break;
					case "removeAgeCles":
						{
							Long l = Long.parseLong(requeteJson.getString(methodeNom));
							ListeRecherche<AgeScolaire> listeRecherche = new ListeRecherche<AgeScolaire>();
							listeRecherche.setQuery("*:*");
							listeRecherche.setStocker(true);
							listeRecherche.setC(AgeScolaire.class);
							listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							listeRecherche.initLoinListeRecherche(requeteSite);
							l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								patchSql.append(SiteContexteFrFR.SQL_removeA);
								patchSqlParams.addAll(Arrays.asList("ageCles", pk, "sessionCle", l));
							}
						}
						break;
					case "setSaisonCle":
						{
							Long l = o2.getSaisonCle();
							ListeRecherche<SaisonScolaire> listeRecherche = new ListeRecherche<SaisonScolaire>();
							listeRecherche.setQuery("*:*");
							listeRecherche.setStocker(true);
							listeRecherche.setC(SaisonScolaire.class);
							listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							listeRecherche.initLoinListeRecherche(requeteSite);
							l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								o2.setSaisonCle(requeteJson.getString(methodeNom));
								patchSql.append(SiteContexteFrFR.SQL_setA1);
								patchSqlParams.addAll(Arrays.asList("saisonCle", pk, "sessionCles", l));
							}
						}
						break;
					case "removeSaisonCle":
						{
							Long l = o2.getSaisonCle();
							ListeRecherche<SaisonScolaire> listeRecherche = new ListeRecherche<SaisonScolaire>();
							listeRecherche.setQuery("*:*");
							listeRecherche.setStocker(true);
							listeRecherche.setC(SaisonScolaire.class);
							listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							listeRecherche.initLoinListeRecherche(requeteSite);
							l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								o2.setSaisonCle(requeteJson.getString(methodeNom));
								patchSql.append(SiteContexteFrFR.SQL_removeA);
								patchSqlParams.addAll(Arrays.asList("saisonCle", pk, "sessionCles", l));
							}
						}
						break;
					case "setEcoleAddresse":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "ecoleAddresse"));
						} else {
							o2.setEcoleAddresse(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("ecoleAddresse", o2.jsonEcoleAddresse(), pk));
						}
						break;
					case "setSessionJourDebut":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sessionJourDebut"));
						} else {
							o2.setSessionJourDebut(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sessionJourDebut", o2.jsonSessionJourDebut(), pk));
						}
						break;
					case "setSessionJourFin":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sessionJourFin"));
						} else {
							o2.setSessionJourFin(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sessionJourFin", o2.jsonSessionJourFin(), pk));
						}
						break;
				}
			}
			connexionSql.queryWithParams(
					patchSql.toString()
					, new JsonArray(patchSqlParams)
					, patchAsync
			-> {
				if(patchAsync.succeeded()) {
					SessionScolaire o3 = new SessionScolaire();
					o3.setRequeteSite_(o.getRequeteSite_());
					o3.setPk(pk);
					gestionnaireEvenements.handle(Future.succeededFuture(o3));
				} else {
					gestionnaireEvenements.handle(Future.failedFuture(new Exception(patchAsync.cause())));
				}
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void patchSessionScolaireReponse(ListeRecherche<SessionScolaire> listeSessionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeSessionScolaire.getRequeteSite_();
		reponse200PATCHSessionScolaire(listeSessionScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								RequeteApi requeteApi = requeteSite.getRequeteApi_();
								requeteSite.getVertx().eventBus().publish("websocketSessionScolaire", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PATCHSessionScolaire(ListeRecherche<SessionScolaire> listeSessionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeSessionScolaire.getRequeteSite_();
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			JsonObject json = JsonObject.mapFrom(requeteApi);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSessionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSessionScolaire(siteContexte, operationRequete);

			List<String> roles = Arrays.asList("SiteAdmin");
			List<String> roleLires = Arrays.asList("");
			if(
					!CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRessource(), roles)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRoyaume(), roles)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRessource(), roleLires)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRoyaume(), roleLires)
					) {
				gestionnaireEvenements.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "rôles requis : " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSessionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSessionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheSessionScolaire(requeteSite, false, true, null, c -> {
								if(c.succeeded()) {
									ListeRecherche<SessionScolaire> listeSessionScolaire = c.result();
									getSessionScolaireReponse(listeSessionScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getSessionScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("getSessionScolaire a échoué. ", d.cause()));
											erreurSessionScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSessionScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void getSessionScolaireReponse(ListeRecherche<SessionScolaire> listeSessionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeSessionScolaire.getRequeteSite_();
		reponse200GETSessionScolaire(listeSessionScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200GETSessionScolaire(ListeRecherche<SessionScolaire> listeSessionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeSessionScolaire.getRequeteSite_();
			SolrDocumentList documentsSolr = listeSessionScolaire.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listeSessionScolaire.getList().stream().findFirst().orElse(null));
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void rechercheSessionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSessionScolaire(siteContexte, operationRequete);

			List<String> roles = Arrays.asList("SiteAdmin");
			List<String> roleLires = Arrays.asList("");
			if(
					!CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRessource(), roles)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRoyaume(), roles)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRessource(), roleLires)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRoyaume(), roleLires)
					) {
				gestionnaireEvenements.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "rôles requis : " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSessionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSessionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheSessionScolaire(requeteSite, false, true, "/api/session", c -> {
								if(c.succeeded()) {
									ListeRecherche<SessionScolaire> listeSessionScolaire = c.result();
									rechercheSessionScolaireReponse(listeSessionScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("rechercheSessionScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("rechercheSessionScolaire a échoué. ", d.cause()));
											erreurSessionScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSessionScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void rechercheSessionScolaireReponse(ListeRecherche<SessionScolaire> listeSessionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeSessionScolaire.getRequeteSite_();
		reponse200RechercheSessionScolaire(listeSessionScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200RechercheSessionScolaire(ListeRecherche<SessionScolaire> listeSessionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeSessionScolaire.getRequeteSite_();
			QueryResponse reponseRecherche = listeSessionScolaire.getQueryResponse();
			SolrDocumentList documentsSolr = listeSessionScolaire.getSolrDocumentList();
			Long millisRecherche = Long.valueOf(reponseRecherche.getQTime());
			Long millisTransmission = reponseRecherche.getElapsedTime();
			Long numCommence = reponseRecherche.getResults().getStart();
			Long numTrouve = reponseRecherche.getResults().getNumFound();
			Integer numRetourne = reponseRecherche.getResults().size();
			String tempsRecherche = String.format("%d.%03d sec", TimeUnit.MILLISECONDS.toSeconds(millisRecherche), TimeUnit.MILLISECONDS.toMillis(millisRecherche) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisRecherche)));
			String tempsTransmission = String.format("%d.%03d sec", TimeUnit.MILLISECONDS.toSeconds(millisTransmission), TimeUnit.MILLISECONDS.toMillis(millisTransmission) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(millisTransmission)));
			Exception exceptionRecherche = reponseRecherche.getException();

			JsonObject json = new JsonObject();
			json.put("numCommence", numCommence);
			json.put("numTrouve", numTrouve);
			json.put("numRetourne", numRetourne);
			json.put("tempsRecherche", tempsRecherche);
			json.put("tempsTransmission", tempsTransmission);
			JsonArray l = new JsonArray();
			listeSessionScolaire.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listeSessionScolaire.getFields();
				if(fls.size() > 0) {
					Set<String> fieldNames = new HashSet<String>();
					fieldNames.addAll(json2.fieldNames());
					if(fls.size() == 1 && fls.stream().findFirst().orElse(null).equals("sauvegardes")) {
						fieldNames.removeAll(Optional.ofNullable(json2.getJsonArray("sauvegardes")).orElse(new JsonArray()).stream().map(s -> s.toString()).collect(Collectors.toList()));
						fieldNames.remove("pk");
					}
					else if(fls.size() >= 1) {
						fieldNames.removeAll(fls);
					}
					for(String fieldName : fieldNames) {
						if(!fls.contains(fieldName))
							json2.remove(fieldName);
					}
				}
				l.add(json2);
			});
			json.put("liste", l);
			if(exceptionRecherche != null) {
				json.put("exceptionRecherche", exceptionRecherche.getMessage());
			}
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PageRecherche //

	@Override
	public void pagerechercheSessionScolaireId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		pagerechercheSessionScolaire(operationRequete, gestionnaireEvenements);
	}

	@Override
	public void pagerechercheSessionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSessionScolaire(siteContexte, operationRequete);

			List<String> roles = Arrays.asList("SiteAdmin");
			List<String> roleLires = Arrays.asList("");
			if(
					!CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRessource(), roles)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRoyaume(), roles)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRessource(), roleLires)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRoyaume(), roleLires)
					) {
				gestionnaireEvenements.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "rôles requis : " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSessionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSessionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheSessionScolaire(requeteSite, false, true, "/session", c -> {
								if(c.succeeded()) {
									ListeRecherche<SessionScolaire> listeSessionScolaire = c.result();
									pagerechercheSessionScolaireReponse(listeSessionScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("pagerechercheSessionScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("pagerechercheSessionScolaire a échoué. ", d.cause()));
											erreurSessionScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSessionScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void pagerechercheSessionScolaireReponse(ListeRecherche<SessionScolaire> listeSessionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeSessionScolaire.getRequeteSite_();
		reponse200PageRechercheSessionScolaire(listeSessionScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSessionScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PageRechercheSessionScolaire(ListeRecherche<SessionScolaire> listeSessionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeSessionScolaire.getRequeteSite_();
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(listeSessionScolaire.getRequeteSite_(), buffer);
			SessionPage page = new SessionPage();
			SolrDocument pageDocumentSolr = new SolrDocument();
			CaseInsensitiveHeaders requeteEnTetes = new CaseInsensitiveHeaders();
			requeteSite.setRequeteEnTetes(requeteEnTetes);

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/session");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			if(listeSessionScolaire.size() == 1)
				requeteSite.setRequetePk(listeSessionScolaire.get(0).getPk());
			requeteSite.setW(w);
			page.setListeSessionScolaire(listeSessionScolaire);
			page.setRequeteSite_(requeteSite);
			page.initLoinSessionPage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requeteEnTetes)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<SessionScolaire> definirIndexerSessionScolaire(SessionScolaire sessionScolaire, Handler<AsyncResult<SessionScolaire>> gestionnaireEvenements) {
		Promise<SessionScolaire> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = sessionScolaire.getRequeteSite_();
		definirSessionScolaire(sessionScolaire, c -> {
			if(c.succeeded()) {
				attribuerSessionScolaire(sessionScolaire, d -> {
					if(d.succeeded()) {
						indexerSessionScolaire(sessionScolaire, e -> {
							if(e.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(sessionScolaire));
								promise.complete(sessionScolaire);
							} else {
								erreurSessionScolaire(requeteSite, null, e);
							}
						});
					} else {
						erreurSessionScolaire(requeteSite, null, d);
					}
				});
			} else {
				erreurSessionScolaire(requeteSite, null, c);
			}
		});
		return promise.future();
	}

	public void creerSessionScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<SessionScolaire>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_creer
					, new JsonArray(Arrays.asList(SessionScolaire.class.getCanonicalName(), utilisateurId))
					, creerAsync
			-> {
				JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = creerLigne.getLong(0);
				SessionScolaire o = new SessionScolaire();
				o.setPk(pk);
				o.setRequeteSite_(requeteSite);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public RequeteApi requeteApiSessionScolaire(SessionScolaire o) {
		RequeteApi requeteApi = o.getRequeteSite_().getRequeteApi_();
		if(requeteApi != null) {
			List<Long> pks = requeteApi.getPks();
			List<String> classes = requeteApi.getClasses();
			for(Long pk : o.getAgeCles()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("AgeScolaire");
				}
			}
			if(o.getSaisonCle() != null) {
				if(!pks.contains(o.getSaisonCle())) {
					pks.add(o.getSaisonCle());
					classes.add("SaisonScolaire");
				}
			}
		}
		return requeteApi;
	}

	public void erreurSessionScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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
		ConfigSite configSite = requeteSite.getConfigSite_();
		SiteContexteFrFR siteContexte = requeteSite.getSiteContexte_();
		MailClient mailClient = siteContexte.getMailClient();
		MailMessage message = new MailMessage();
		message.setFrom(configSite.getMailDe());
		message.setTo(configSite.getMailAdmin());
		message.setText(ExceptionUtils.getStackTrace(e));
		message.setSubject(String.format(configSite.getSiteUrlBase() + " " + e.getMessage()));
		WorkerExecutor workerExecutor = siteContexte.getExecuteurTravailleur();
		workerExecutor.executeBlocking(
			blockingCodeHandler -> {
				mailClient.sendMail(message, result -> {
					if (result.succeeded()) {
						LOGGER.info(result.result());
					} else {
						LOGGER.error(result.cause());
					}
				});
			}, resultHandler -> {
			}
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

	public void sqlSessionScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
						gestionnaireEvenements.handle(Future.failedFuture(new Exception(sqlAsync.cause())));
					}
				});
			}
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public RequeteSiteFrFR genererRequeteSiteFrFRPourSessionScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete) {
		return genererRequeteSiteFrFRPourSessionScolaire(siteContexte, operationRequete, null);
	}

	public RequeteSiteFrFR genererRequeteSiteFrFRPourSessionScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete, JsonObject body) {
		Vertx vertx = siteContexte.getVertx();
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
		requeteSite.setObjetJson(body);
		requeteSite.setVertx(vertx);
		requeteSite.setSiteContexte_(siteContexte);
		requeteSite.setConfigSite_(siteContexte.getConfigSite());
		requeteSite.setOperationRequete(operationRequete);
		requeteSite.initLoinRequeteSiteFrFR(requeteSite);

		return requeteSite;
	}

	public void utilisateurSessionScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			if(utilisateurId == null) {
				gestionnaireEvenements.handle(Future.succeededFuture());
			} else {
				connexionSql.queryWithParams(
						SiteContexteFrFR.SQL_selectC
						, new JsonArray(Arrays.asList("org.computate.scolaire.frFR.utilisateur.UtilisateurSite", utilisateurId))
						, selectCAsync
				-> {
					if(selectCAsync.succeeded()) {
						try {
							JsonArray utilisateurValeurs = selectCAsync.result().getResults().stream().findFirst().orElse(null);
							UtilisateurSiteFrFRGenApiServiceImpl utilisateurService = new UtilisateurSiteFrFRGenApiServiceImpl(siteContexte);
							if(utilisateurValeurs == null) {
								JsonObject utilisateurVertx = requeteSite.getOperationRequete().getUser();
								JsonObject principalJson = KeycloakHelper.parseToken(utilisateurVertx.getString("access_token"));

								JsonObject jsonObject = new JsonObject();
								jsonObject.put("utilisateurNom", principalJson.getString("preferred_username"));
								jsonObject.put("utilisateurPrenom", principalJson.getString("given_name"));
								jsonObject.put("utilisateurNomFamille", principalJson.getString("family_name"));
								jsonObject.put("utilisateurId", principalJson.getString("sub"));
								utilisateurSessionScolaireDefinir(requeteSite, jsonObject, false);

								RequeteSiteFrFR requeteSite2 = new RequeteSiteFrFR();
								requeteSite2.setConnexionSql(requeteSite.getConnexionSql());
								requeteSite2.setObjetJson(jsonObject);
								requeteSite2.setVertx(requeteSite.getVertx());
								requeteSite2.setSiteContexte_(siteContexte);
								requeteSite2.setConfigSite_(siteContexte.getConfigSite());
								requeteSite2.setUtilisateurId(requeteSite.getUtilisateurId());
								requeteSite2.initLoinRequeteSiteFrFR(requeteSite);

								utilisateurService.creerUtilisateurSite(requeteSite2, b -> {
									if(b.succeeded()) {
										UtilisateurSite utilisateurSite = b.result();
										utilisateurService.sqlPOSTUtilisateurSite(utilisateurSite, false, c -> {
											if(c.succeeded()) {
												utilisateurService.definirUtilisateurSite(utilisateurSite, d -> {
													if(d.succeeded()) {
														utilisateurService.attribuerUtilisateurSite(utilisateurSite, e -> {
															if(e.succeeded()) {
																utilisateurService.indexerUtilisateurSite(utilisateurSite, f -> {
																	if(f.succeeded()) {
																		requeteSite.setUtilisateurSite(utilisateurSite);
																		requeteSite.setUtilisateurNom(principalJson.getString("preferred_username"));
																		requeteSite.setUtilisateurPrenom(principalJson.getString("given_name"));
																		requeteSite.setUtilisateurNomFamille(principalJson.getString("family_name"));
																		requeteSite.setUtilisateurId(principalJson.getString("sub"));
																		requeteSite.setUtilisateurCle(utilisateurSite.getPk());
																		gestionnaireEvenements.handle(Future.succeededFuture());
																	} else {
																		erreurSessionScolaire(requeteSite, gestionnaireEvenements, f);
																	}
																});
															} else {
																erreurSessionScolaire(requeteSite, gestionnaireEvenements, e);
															}
														});
													} else {
														erreurSessionScolaire(requeteSite, gestionnaireEvenements, d);
													}
												});
											} else {
												erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
											}
										});
									} else {
										erreurSessionScolaire(requeteSite, gestionnaireEvenements, b);
									}
								});
							} else {
								Long pkUtilisateur = utilisateurValeurs.getLong(0);
								ListeRecherche<UtilisateurSite> listeRecherche = new ListeRecherche<UtilisateurSite>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(UtilisateurSite.class);
								listeRecherche.addFilterQuery("utilisateurId_indexed_string:" + ClientUtils.escapeQueryChars(utilisateurId));
								listeRecherche.addFilterQuery("pk_indexed_long:" + pkUtilisateur);
								listeRecherche.initLoinListeRecherche(requeteSite);
								UtilisateurSite utilisateurSite1 = listeRecherche.getList().stream().findFirst().orElse(null);

								JsonObject utilisateurVertx = requeteSite.getOperationRequete().getUser();
								JsonObject principalJson = KeycloakHelper.parseToken(utilisateurVertx.getString("access_token"));

								JsonObject jsonObject = new JsonObject();
								jsonObject.put("setUtilisateurNom", principalJson.getString("preferred_username"));
								jsonObject.put("setUtilisateurPrenom", principalJson.getString("given_name"));
								jsonObject.put("setUtilisateurNomFamille", principalJson.getString("family_name"));
								jsonObject.put("setUtilisateurNomComplet", principalJson.getString("name"));
								jsonObject.put("setCustomerProfileId", Optional.ofNullable(utilisateurSite1).map(u -> u.getCustomerProfileId()).orElse(null));
								jsonObject.put("setUtilisateurId", principalJson.getString("sub"));
								jsonObject.put("setUtilisateurMail", principalJson.getString("email"));
								Boolean definir = utilisateurSessionScolaireDefinir(requeteSite, jsonObject, true);
								if(definir) {
									UtilisateurSite utilisateurSite;
									if(utilisateurSite1 == null) {
										utilisateurSite = new UtilisateurSite();
										utilisateurSite.setPk(pkUtilisateur);
										utilisateurSite.setRequeteSite_(requeteSite);
									} else {
										utilisateurSite = utilisateurSite1;
									}

									RequeteSiteFrFR requeteSite2 = new RequeteSiteFrFR();
									requeteSite2.setConnexionSql(requeteSite.getConnexionSql());
									requeteSite2.setObjetJson(jsonObject);
									requeteSite2.setVertx(requeteSite.getVertx());
									requeteSite2.setSiteContexte_(siteContexte);
									requeteSite2.setConfigSite_(siteContexte.getConfigSite());
									requeteSite2.setUtilisateurId(requeteSite.getUtilisateurId());
									requeteSite2.setUtilisateurCle(requeteSite.getUtilisateurCle());
									requeteSite2.initLoinRequeteSiteFrFR(requeteSite);
									utilisateurSite.setRequeteSite_(requeteSite2);

									utilisateurService.sqlPATCHUtilisateurSite(utilisateurSite, false, c -> {
										if(c.succeeded()) {
											UtilisateurSite utilisateurSite2 = c.result();
											utilisateurService.definirUtilisateurSite(utilisateurSite2, d -> {
												if(d.succeeded()) {
													utilisateurService.attribuerUtilisateurSite(utilisateurSite2, e -> {
														if(e.succeeded()) {
															utilisateurService.indexerUtilisateurSite(utilisateurSite2, f -> {
																if(f.succeeded()) {
																	requeteSite.setUtilisateurSite(utilisateurSite2);
																	requeteSite.setUtilisateurNom(utilisateurSite2.getUtilisateurNom());
																	requeteSite.setUtilisateurPrenom(utilisateurSite2.getUtilisateurPrenom());
																	requeteSite.setUtilisateurNomFamille(utilisateurSite2.getUtilisateurNomFamille());
																	requeteSite.setUtilisateurId(utilisateurSite2.getUtilisateurId());
																	requeteSite.setUtilisateurCle(utilisateurSite2.getPk());
																	gestionnaireEvenements.handle(Future.succeededFuture());
																} else {
																	erreurSessionScolaire(requeteSite, gestionnaireEvenements, f);
																}
															});
														} else {
															erreurSessionScolaire(requeteSite, gestionnaireEvenements, e);
														}
													});
												} else {
													erreurSessionScolaire(requeteSite, gestionnaireEvenements, d);
												}
											});
										} else {
											erreurSessionScolaire(requeteSite, gestionnaireEvenements, c);
										}
									});
								} else {
									requeteSite.setUtilisateurSite(utilisateurSite1);
									requeteSite.setUtilisateurNom(utilisateurSite1.getUtilisateurNom());
									requeteSite.setUtilisateurPrenom(utilisateurSite1.getUtilisateurPrenom());
									requeteSite.setUtilisateurNomFamille(utilisateurSite1.getUtilisateurNomFamille());
									requeteSite.setUtilisateurId(utilisateurSite1.getUtilisateurId());
									requeteSite.setUtilisateurCle(utilisateurSite1.getPk());
									gestionnaireEvenements.handle(Future.succeededFuture());
								}
							}
						} catch(Exception e) {
							gestionnaireEvenements.handle(Future.failedFuture(e));
						}
					} else {
						gestionnaireEvenements.handle(Future.failedFuture(new Exception(selectCAsync.cause())));
					}
				});
			}
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public Boolean utilisateurSessionScolaireDefinir(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void rechercheSessionScolaireQ(String classeApiUriMethode, ListeRecherche<SessionScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		listeRecherche.setQuery(varIndexe + ":" + ("*".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));
		if(!"*".equals(entiteVar)) {
			listeRecherche.setHighlight(true);
			listeRecherche.setHighlightSnippets(3);
			listeRecherche.addHighlightField(varIndexe);
			listeRecherche.setParam("hl.encoder", "html");
		}
	}

	public void rechercheSessionScolaireFq(String classeApiUriMethode, ListeRecherche<SessionScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
	}

	public void rechercheSessionScolaireSort(String classeApiUriMethode, ListeRecherche<SessionScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurIndexe));
	}

	public void rechercheSessionScolaireRows(String classeApiUriMethode, ListeRecherche<SessionScolaire> listeRecherche, Integer valeurRows) {
		listeRecherche.setRows(valeurRows);
	}

	public void rechercheSessionScolaireStart(String classeApiUriMethode, ListeRecherche<SessionScolaire> listeRecherche, Integer valeurStart) {
		listeRecherche.setStart(valeurStart);
	}

	public void rechercheSessionScolaireVar(String classeApiUriMethode, ListeRecherche<SessionScolaire> listeRecherche, String var, String valeur) {
		listeRecherche.getRequeteSite_().getRequeteVars().put(var, valeur);
	}

	public void rechercheSessionScolaire(RequeteSiteFrFR requeteSite, Boolean peupler, Boolean stocker, String classeApiUriMethode, Handler<AsyncResult<ListeRecherche<SessionScolaire>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<SessionScolaire> listeRecherche = new ListeRecherche<SessionScolaire>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(SessionScolaire.class);
			listeRecherche.setRequeteSite_(requeteSite);
			if(entiteListe != null)
				listeRecherche.addFields(entiteListe);
			listeRecherche.add("json.facet", "{max_modifie:'max(modifie_indexed_date)'}");

			String id = operationRequete.getParams().getJsonObject("path").getString("id");
			if(id != null) {
				listeRecherche.addFilterQuery("(id:" + ClientUtils.escapeQueryChars(id) + " OR objetId_indexed_string:" + ClientUtils.escapeQueryChars(id) + ")");
			}

			operationRequete.getParams().getJsonObject("query").forEach(paramRequete -> {
				String entiteVar = null;
				String valeurIndexe = null;
				String varIndexe = null;
				String valeurTri = null;
				Integer valeurStart = null;
				Integer valeurRows = null;
				String paramNom = paramRequete.getKey();
				Object paramValeursObjet = paramRequete.getValue();
				JsonArray paramObjets = paramValeursObjet instanceof JsonArray ? (JsonArray)paramValeursObjet : new JsonArray().add(paramValeursObjet);

				try {
					for(Object paramObjet : paramObjets) {
						switch(paramNom) {
							case "q":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								varIndexe = "*".equals(entiteVar) ? entiteVar : SessionScolaire.varRechercheSessionScolaire(entiteVar);
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								valeurIndexe = StringUtils.isEmpty(valeurIndexe) ? "*" : valeurIndexe;
								rechercheSessionScolaireQ(classeApiUriMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "fq":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								varIndexe = SessionScolaire.varIndexeSessionScolaire(entiteVar);
								rechercheSessionScolaireFq(classeApiUriMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "sort":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
								valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
								varIndexe = SessionScolaire.varIndexeSessionScolaire(entiteVar);
								rechercheSessionScolaireSort(classeApiUriMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "start":
								valeurStart = (Integer)paramObjet;
								rechercheSessionScolaireStart(classeApiUriMethode, listeRecherche, valeurStart);
								break;
							case "rows":
								valeurRows = (Integer)paramObjet;
								rechercheSessionScolaireRows(classeApiUriMethode, listeRecherche, valeurRows);
								break;
							case "var":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								rechercheSessionScolaireVar(classeApiUriMethode, listeRecherche, entiteVar, valeurIndexe);
								break;
						}
					}
				} catch(Exception e) {
					gestionnaireEvenements.handle(Future.failedFuture(e));
				}
			});
			if(listeRecherche.getSorts().size() == 0) {
				listeRecherche.addSort("cree_indexed_date", ORDER.desc);
			}
			listeRecherche.initLoinPourClasse(requeteSite);
			gestionnaireEvenements.handle(Future.succeededFuture(listeRecherche));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void definirSessionScolaire(SessionScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_definir
					, new JsonArray(Arrays.asList(pk, pk, pk))
					, definirAsync
			-> {
				if(definirAsync.succeeded()) {
					try {
						for(JsonArray definition : definirAsync.result().getResults()) {
							try {
								o.definirPourClasse(definition.getString(0), definition.getString(1));
							} catch(Exception e) {
								LOGGER.error(e);
							}
						}
						gestionnaireEvenements.handle(Future.succeededFuture());
					} catch(Exception e) {
						gestionnaireEvenements.handle(Future.failedFuture(e));
					}
				} else {
					gestionnaireEvenements.handle(Future.failedFuture(new Exception(definirAsync.cause())));
				}
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void attribuerSessionScolaire(SessionScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_attribuer
					, new JsonArray(Arrays.asList(pk, pk))
					, attribuerAsync
			-> {
				try {
					if(attribuerAsync.succeeded()) {
						if(attribuerAsync.result() != null) {
							for(JsonArray definition : attribuerAsync.result().getResults()) {
								if(pk.equals(definition.getLong(0)))
									o.attribuerPourClasse(definition.getString(2), definition.getLong(1));
								else
									o.attribuerPourClasse(definition.getString(3), definition.getLong(0));
							}
						}
						gestionnaireEvenements.handle(Future.succeededFuture());
					} else {
						gestionnaireEvenements.handle(Future.failedFuture(new Exception(attribuerAsync.cause())));
					}
				} catch(Exception e) {
					gestionnaireEvenements.handle(Future.failedFuture(e));
				}
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void indexerSessionScolaire(SessionScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			o.initLoinPourClasse(requeteSite);
			o.indexerPourClasse();
			if(BooleanUtils.isFalse(Optional.ofNullable(requeteSite.getRequeteApi_()).map(RequeteApi::getEmpty).orElse(null))) {
				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourSessionScolaire(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());
				ListeRecherche<SessionScolaire> listeRecherche = new ListeRecherche<SessionScolaire>();
				listeRecherche.setPeupler(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(SessionScolaire.class);
				listeRecherche.addFilterQuery("modifie_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(requeteSite.getRequeteApi_().getCree().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				listeRecherche.add("json.facet", "{ageCles:{terms:{field:ageCles_indexed_longs, limit:1000}}}");
				listeRecherche.add("json.facet", "{saisonCle:{terms:{field:saisonCle_indexed_longs, limit:1000}}}");
				listeRecherche.setRows(1000);
				listeRecherche.initLoinListeRecherche(requeteSite2);
				List<Future> futures = new ArrayList<>();

				{
					AgeScolaireFrFRGenApiServiceImpl service = new AgeScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
					for(Long pk : o.getAgeCles()) {
						AgeScolaire o2 = new AgeScolaire();

						o2.setPk(pk);
						o2.setRequeteSite_(requeteSite2);
						futures.add(
							service.patchAgeScolaireFuture(o2, false, a -> {
								if(a.succeeded()) {
									LOGGER.info(String.format("AgeScolaire %s rechargé. ", pk));
								} else {
									LOGGER.info(String.format("AgeScolaire %s a échoué. ", pk));
									gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
								}
							})
						);
					}
				}

				{
					SaisonScolaire o2 = new SaisonScolaire();
					SaisonScolaireFrFRGenApiServiceImpl service = new SaisonScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
					Long pk = o.getSaisonCle();

					if(pk != null) {
						o2.setPk(pk);
						o2.setRequeteSite_(requeteSite2);
						futures.add(
							service.patchSaisonScolaireFuture(o2, false, a -> {
								if(a.succeeded()) {
									LOGGER.info(String.format("SaisonScolaire %s rechargé. ", pk));
								} else {
									LOGGER.info(String.format("SaisonScolaire %s a échoué. ", pk));
									gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
								}
							})
						);
					}
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						LOGGER.info("Recharger relations a réussi. ");
						SessionScolaireFrFRGenApiServiceImpl service = new SessionScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
						List<Future> futures2 = new ArrayList<>();
						for(SessionScolaire o2 : listeRecherche.getList()) {
							futures2.add(
								service.patchSessionScolaireFuture(o2, false, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("SessionScolaire %s rechargé. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("SessionScolaire %s a échoué. ", o2.getPk()));
										gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Recharger SessionScolaire a réussi. ");
								gestionnaireEvenements.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Recharger relations a échoué. ", b.cause());
								erreurSessionScolaire(requeteSite2, gestionnaireEvenements, b);
							}
						});
					} else {
						LOGGER.error("Recharger relations a échoué. ", a.cause());
						erreurSessionScolaire(requeteSite2, gestionnaireEvenements, a);
					}
				});
			} else {
				gestionnaireEvenements.handle(Future.succeededFuture());
			}
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}
}
