package org.computate.scolaire.frFR.saison;

import org.computate.scolaire.frFR.annee.AnneeScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.annee.AnneeScolaire;
import org.computate.scolaire.frFR.session.SessionScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.session.SessionScolaire;
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
 * NomCanonique.enUS: org.computate.scolaire.enUS.season.SchoolSeasonEnUSGenApiServiceImpl
 **/
public class SaisonScolaireFrFRGenApiServiceImpl implements SaisonScolaireFrFRGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SaisonScolaireFrFRGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SaisonScolaireFrFRApiServiceImpl";

	protected SiteContexteFrFR siteContexte;

	public SaisonScolaireFrFRGenApiServiceImpl(SiteContexteFrFR siteContexte) {
		this.siteContexte = siteContexte;
	}

	// POST //

	@Override
	public void postSaisonScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("postSaisonScolaire a démarré. "));

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

			sqlSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSaisonScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							RequeteApi requeteApi = new RequeteApi();
							requeteApi.setRows(1);
							requeteApi.setNumFound(1L);
							requeteApi.setNumPATCH(0L);
							requeteApi.initLoinRequeteApi(requeteSite);
							requeteSite.setRequeteApi_(requeteApi);
							requeteSite.getVertx().eventBus().publish("websocketSaisonScolaire", JsonObject.mapFrom(requeteApi).toString());
							postSaisonScolaireFuture(requeteSite, false, c -> {
								if(c.succeeded()) {
									SaisonScolaire saisonScolaire = c.result();
									requeteApiSaisonScolaire(saisonScolaire);
									postSaisonScolaireReponse(saisonScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postSaisonScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("postSaisonScolaire a échoué. ", d.cause()));
											erreurSaisonScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("postSaisonScolaire a échoué. ", c.cause()));
									erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public Future<SaisonScolaire> postSaisonScolaireFuture(RequeteSiteFrFR requeteSite, Boolean inheritPk, Handler<AsyncResult<SaisonScolaire>> gestionnaireEvenements) {
		Promise<SaisonScolaire> promise = Promise.promise();
		try {
			creerSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					SaisonScolaire saisonScolaire = a.result();
					sqlPOSTSaisonScolaire(saisonScolaire, inheritPk, b -> {
						if(b.succeeded()) {
							definirIndexerSaisonScolaire(saisonScolaire, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(saisonScolaire));
									promise.complete(saisonScolaire);
								} else {
									erreurSaisonScolaire(requeteSite, null, c);
								}
							});
						} else {
							erreurSaisonScolaire(requeteSite, null, b);
						}
					});
				} else {
					erreurSaisonScolaire(requeteSite, null, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTSaisonScolaire(SaisonScolaire o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
					case "anneeCle":
						{
							Long l = Long.parseLong(jsonObject.getString(entiteVar));
							if(l != null) {
								ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AnneeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContexteFrFR.SQL_addA);
									postSqlParams.addAll(Arrays.asList("anneeCle", pk, "saisonCles", l));
								}
							}
						}
						break;
					case "sessionCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								ListeRecherche<SessionScolaire> listeRecherche = new ListeRecherche<SessionScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(SessionScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContexteFrFR.SQL_addA);
									postSqlParams.addAll(Arrays.asList("saisonCle", l, "sessionCles", pk));
								}
							}
						}
						break;
					case "saisonJourDebut":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("saisonJourDebut", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "saisonEte":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("saisonEte", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "saisonHiver":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("saisonHiver", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "saisonFuture":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("saisonFuture", jsonObject.getBoolean(entiteVar), pk));
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

	public void postSaisonScolaireReponse(SaisonScolaire saisonScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = saisonScolaire.getRequeteSite_();
		reponse200POSTSaisonScolaire(saisonScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("postSaisonScolaire sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("postSaisonScolaire sql close. "));
								RequeteApi requeteApi = requeteApiSaisonScolaire(saisonScolaire);
								saisonScolaire.requeteApiSaisonScolaire();
								requeteSite.getVertx().eventBus().publish("websocketSaisonScolaire", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200POSTSaisonScolaire(SaisonScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
	public void putimportSaisonScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putimportSaisonScolaire a démarré. "));

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

			sqlSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSaisonScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							putimportSaisonScolaireReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete, body);
											try {
												RequeteApi requeteApi = new RequeteApi();
												JsonArray jsonArray = Optional.ofNullable(requeteSite2.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												requeteApi.setRows(jsonArray.size());
												requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite2);
												requeteSite2.setRequeteApi_(requeteApi);
												requeteSite2.getVertx().eventBus().publish("websocketSaisonScolaire", JsonObject.mapFrom(requeteApi).toString());
												sqlSaisonScolaire(requeteSite2, d -> {
													if(d.succeeded()) {
														listePUTImportSaisonScolaire(requeteApi, requeteSite2, e -> {
															if(e.succeeded()) {
																putimportSaisonScolaireReponse(requeteSite2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putimportSaisonScolaire a réussi. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putimportSaisonScolaire a échoué. ", f.cause()));
																		erreurSaisonScolaire(requeteSite2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putimportSaisonScolaire a échoué. ", e.cause()));
																erreurSaisonScolaire(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putimportSaisonScolaire a échoué. ", d.cause()));
														erreurSaisonScolaire(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putimportSaisonScolaire a échoué. ", ex));
												erreurSaisonScolaire(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putimportSaisonScolaire a échoué. ", c.cause()));
									erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTImportSaisonScolaire(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, requeteSite.getOperationRequete(), json);
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());

				ListeRecherche<SaisonScolaire> listeRecherche = new ListeRecherche<SaisonScolaire>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(SaisonScolaire.class);
				listeRecherche.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				listeRecherche.initLoinPourClasse(requeteSite2);

				if(listeRecherche.size() == 1) {
					SaisonScolaire o = listeRecherche.getList().stream().findFirst().orElse(null);
					JsonObject json2 = new JsonObject();
					for(String f : json.fieldNames()) {
						json2.put("set" + StringUtils.capitalize(f), json.getValue(f));
					}
					if(o != null) {
						for(String f : Optional.ofNullable(o.getSauvegardes()).orElse(Arrays.asList())) {
							if(!json.fieldNames().contains(f))
								json2.putNull("set" + StringUtils.capitalize(f));
						}
						requeteSite2.setObjetJson(json2);
						futures.add(
							patchSaisonScolaireFuture(o, true, a -> {
								if(a.succeeded()) {
									SaisonScolaire saisonScolaire = a.result();
									requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
									requeteSite2.getVertx().eventBus().publish("websocketSaisonScolaire", JsonObject.mapFrom(requeteApi).toString());
								} else {
									erreurSaisonScolaire(requeteSite2, gestionnaireEvenements, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSaisonScolaireFuture(requeteSite2, true, a -> {
							if(a.succeeded()) {
								SaisonScolaire saisonScolaire = a.result();
								requeteApiSaisonScolaire(saisonScolaire);
							} else {
								erreurSaisonScolaire(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
								requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
								requeteSite.getVertx().eventBus().publish("websocketSaisonScolaire", JsonObject.mapFrom(requeteApi).toString());
					reponse200PUTImportSaisonScolaire(requeteSite, gestionnaireEvenements);
				} else {
					erreurSaisonScolaire(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSaisonScolaire a échoué. ", ex));
			erreurSaisonScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}

	public void putimportSaisonScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PUTImportSaisonScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("putimportSaisonScolaire sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("putimportSaisonScolaire sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTImportSaisonScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTFusion //

	@Override
	public void putfusionSaisonScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putfusionSaisonScolaire a démarré. "));

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

			sqlSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSaisonScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							putfusionSaisonScolaireReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete, body);
											try {
												RequeteApi requeteApi = new RequeteApi();
												JsonArray jsonArray = Optional.ofNullable(requeteSite2.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												requeteApi.setRows(jsonArray.size());
												requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite2);
												requeteSite2.setRequeteApi_(requeteApi);
												requeteSite2.getVertx().eventBus().publish("websocketSaisonScolaire", JsonObject.mapFrom(requeteApi).toString());
												sqlSaisonScolaire(requeteSite2, d -> {
													if(d.succeeded()) {
														listePUTFusionSaisonScolaire(requeteApi, requeteSite2, e -> {
															if(e.succeeded()) {
																putfusionSaisonScolaireReponse(requeteSite2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putfusionSaisonScolaire a réussi. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putfusionSaisonScolaire a échoué. ", f.cause()));
																		erreurSaisonScolaire(requeteSite2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putfusionSaisonScolaire a échoué. ", e.cause()));
																erreurSaisonScolaire(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putfusionSaisonScolaire a échoué. ", d.cause()));
														erreurSaisonScolaire(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putfusionSaisonScolaire a échoué. ", ex));
												erreurSaisonScolaire(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putfusionSaisonScolaire a échoué. ", c.cause()));
									erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTFusionSaisonScolaire(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, requeteSite.getOperationRequete(), json);
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());

				ListeRecherche<SaisonScolaire> listeRecherche = new ListeRecherche<SaisonScolaire>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(SaisonScolaire.class);
				listeRecherche.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				listeRecherche.initLoinPourClasse(requeteSite2);

				if(listeRecherche.size() == 1) {
					SaisonScolaire o = listeRecherche.getList().stream().findFirst().orElse(null);
					JsonObject json2 = new JsonObject();
					for(String f : json.fieldNames()) {
						json2.put("set" + StringUtils.capitalize(f), json.getValue(f));
					}
					if(o != null) {
						for(String f : Optional.ofNullable(o.getSauvegardes()).orElse(Arrays.asList())) {
							if(!json.fieldNames().contains(f))
								json2.putNull("set" + StringUtils.capitalize(f));
						}
						requeteSite2.setObjetJson(json2);
						futures.add(
							patchSaisonScolaireFuture(o, false, a -> {
								if(a.succeeded()) {
									SaisonScolaire saisonScolaire = a.result();
									requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
									requeteSite2.getVertx().eventBus().publish("websocketSaisonScolaire", JsonObject.mapFrom(requeteApi).toString());
								} else {
									erreurSaisonScolaire(requeteSite2, gestionnaireEvenements, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSaisonScolaireFuture(requeteSite2, false, a -> {
							if(a.succeeded()) {
								SaisonScolaire saisonScolaire = a.result();
								requeteApiSaisonScolaire(saisonScolaire);
							} else {
								erreurSaisonScolaire(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
								requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
								requeteSite.getVertx().eventBus().publish("websocketSaisonScolaire", JsonObject.mapFrom(requeteApi).toString());
					reponse200PUTFusionSaisonScolaire(requeteSite, gestionnaireEvenements);
				} else {
					erreurSaisonScolaire(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putfusionSaisonScolaire a échoué. ", ex));
			erreurSaisonScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}

	public void putfusionSaisonScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PUTFusionSaisonScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("putfusionSaisonScolaire sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("putfusionSaisonScolaire sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTFusionSaisonScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTCopie //

	@Override
	public void putcopieSaisonScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putcopieSaisonScolaire a démarré. "));

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

			sqlSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSaisonScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							putcopieSaisonScolaireReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete, body);
											try {
												rechercheSaisonScolaire(requeteSite2, false, true, "/api/saison/copie", "PUTCopie", d -> {
													if(d.succeeded()) {
														ListeRecherche<SaisonScolaire> listeSaisonScolaire = d.result();
														RequeteApi requeteApi = new RequeteApi();
														requeteApi.setRows(listeSaisonScolaire.getRows());
														requeteApi.setNumFound(listeSaisonScolaire.getQueryResponse().getResults().getNumFound());
														requeteApi.setNumPATCH(0L);
														requeteApi.initLoinRequeteApi(requeteSite2);
														requeteSite2.setRequeteApi_(requeteApi);
														requeteSite2.getVertx().eventBus().publish("websocketSaisonScolaire", JsonObject.mapFrom(requeteApi).toString());
														sqlSaisonScolaire(requeteSite2, e -> {
															if(e.succeeded()) {
																try {
																	listePUTCopieSaisonScolaire(requeteApi, listeSaisonScolaire, f -> {
																		if(f.succeeded()) {
																			putcopieSaisonScolaireReponse(requeteSite2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("putcopieSaisonScolaire a réussi. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("putcopieSaisonScolaire a échoué. ", g.cause()));
																					erreurSaisonScolaire(requeteSite2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("putcopieSaisonScolaire a échoué. ", f.cause()));
																			erreurSaisonScolaire(requeteSite2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("putcopieSaisonScolaire a échoué. ", ex));
																	erreurSaisonScolaire(requeteSite2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("putcopieSaisonScolaire a échoué. ", e.cause()));
																erreurSaisonScolaire(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putcopieSaisonScolaire a échoué. ", d.cause()));
														erreurSaisonScolaire(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putcopieSaisonScolaire a échoué. ", ex));
												erreurSaisonScolaire(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putcopieSaisonScolaire a échoué. ", c.cause()));
									erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTCopieSaisonScolaire(RequeteApi requeteApi, ListeRecherche<SaisonScolaire> listeSaisonScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeSaisonScolaire.getRequeteSite_();
		listeSaisonScolaire.getList().forEach(o -> {
			futures.add(
				putcopieSaisonScolaireFuture(requeteSite, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						SaisonScolaire saisonScolaire = a.result();
						requeteApiSaisonScolaire(saisonScolaire);
					} else {
						erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeSaisonScolaire.size());
				if(listeSaisonScolaire.next()) {
					requeteSite.getVertx().eventBus().publish("websocketSaisonScolaire", JsonObject.mapFrom(requeteApi).toString());
					listePUTCopieSaisonScolaire(requeteApi, listeSaisonScolaire, gestionnaireEvenements);
				} else {
					reponse200PUTCopieSaisonScolaire(requeteSite, gestionnaireEvenements);
				}
			} else {
				erreurSaisonScolaire(listeSaisonScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<SaisonScolaire> putcopieSaisonScolaireFuture(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Handler<AsyncResult<SaisonScolaire>> gestionnaireEvenements) {
		Promise<SaisonScolaire> promise = Promise.promise();
		try {

			jsonObject.put("sauvegardes", Optional.ofNullable(jsonObject.getJsonArray("sauvegardes")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("sauvegardes").add(o.getKey());
			});

			creerSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					SaisonScolaire saisonScolaire = a.result();
					sqlPUTCopieSaisonScolaire(saisonScolaire, jsonObject, b -> {
						if(b.succeeded()) {
							definirSaisonScolaire(saisonScolaire, c -> {
								if(c.succeeded()) {
									attribuerSaisonScolaire(saisonScolaire, d -> {
										if(d.succeeded()) {
											indexerSaisonScolaire(saisonScolaire, e -> {
												if(e.succeeded()) {
													gestionnaireEvenements.handle(Future.succeededFuture(saisonScolaire));
													promise.complete(saisonScolaire);
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
			erreurSaisonScolaire(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopieSaisonScolaire(SaisonScolaire o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
					case "anneeCle":
						putSql.append(SiteContexteFrFR.SQL_addA);
						putSqlParams.addAll(Arrays.asList("anneeCle", pk, "saisonCles", Long.parseLong(jsonObject.getString(entiteVar))));
						break;
					case "sessionCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContexteFrFR.SQL_addA);
							putSqlParams.addAll(Arrays.asList("saisonCle", l, "sessionCles", pk));
						}
						break;
					case "saisonJourDebut":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("saisonJourDebut", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "saisonEte":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("saisonEte", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "saisonHiver":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("saisonHiver", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "saisonFuture":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("saisonFuture", jsonObject.getBoolean(entiteVar), pk));
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

	public void putcopieSaisonScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PUTCopieSaisonScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("putcopieSaisonScolaire sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("putcopieSaisonScolaire sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTCopieSaisonScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSaisonScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("patchSaisonScolaire a démarré. "));

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

			sqlSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSaisonScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							patchSaisonScolaireReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete, body);
											try {
												rechercheSaisonScolaire(requeteSite2, false, true, "/api/saison", "PATCH", d -> {
													if(d.succeeded()) {
														ListeRecherche<SaisonScolaire> listeSaisonScolaire = d.result();
														RequeteApi requeteApi = new RequeteApi();
														requeteApi.setRows(listeSaisonScolaire.getRows());
														requeteApi.setNumFound(listeSaisonScolaire.getQueryResponse().getResults().getNumFound());
														requeteApi.setNumPATCH(0L);
														requeteApi.initLoinRequeteApi(requeteSite2);
														requeteSite2.setRequeteApi_(requeteApi);
														requeteSite2.getVertx().eventBus().publish("websocketSaisonScolaire", JsonObject.mapFrom(requeteApi).toString());
														SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeSaisonScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
														Date date = null;
														if(facets != null)
															date = (Date)facets.get("max_modifie");
														String dt;
														if(date == null)
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
														else
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
														listeSaisonScolaire.addFilterQuery(String.format("modifie_indexed_date:[* TO %s]", dt));

														SaisonScolaire o = listeSaisonScolaire.getList().stream().findFirst().orElse(null);
														if(o != null) {
															requeteApi.setPk(o.getPk());
															requeteApi.setOriginal(o);
															requeteApiSaisonScolaire(o);
															o.requeteApiSaisonScolaire();
														}
														sqlSaisonScolaire(requeteSite2, e -> {
															if(e.succeeded()) {
																try {
																	listePATCHSaisonScolaire(requeteApi, listeSaisonScolaire, dt, f -> {
																		if(f.succeeded()) {
																			patchSaisonScolaireReponse(requeteSite2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("patchSaisonScolaire a réussi. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("patchSaisonScolaire a échoué. ", g.cause()));
																					erreurSaisonScolaire(requeteSite2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("patchSaisonScolaire a échoué. ", f.cause()));
																			erreurSaisonScolaire(requeteSite2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("patchSaisonScolaire a échoué. ", ex));
																	erreurSaisonScolaire(requeteSite2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("patchSaisonScolaire a échoué. ", e.cause()));
																erreurSaisonScolaire(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("patchSaisonScolaire a échoué. ", d.cause()));
														erreurSaisonScolaire(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("patchSaisonScolaire a échoué. ", ex));
												erreurSaisonScolaire(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("patchSaisonScolaire a échoué. ", c.cause()));
									erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePATCHSaisonScolaire(RequeteApi requeteApi, ListeRecherche<SaisonScolaire> listeSaisonScolaire, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeSaisonScolaire.getRequeteSite_();
		listeSaisonScolaire.getList().forEach(o -> {
			futures.add(
				patchSaisonScolaireFuture(o, false, a -> {
					if(a.succeeded()) {
							SaisonScolaire saisonScolaire = a.result();
							requeteApiSaisonScolaire(saisonScolaire);
					} else {
						erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeSaisonScolaire.size());
				if(listeSaisonScolaire.next(dt)) {
					requeteSite.getVertx().eventBus().publish("websocketSaisonScolaire", JsonObject.mapFrom(requeteApi).toString());
					listePATCHSaisonScolaire(requeteApi, listeSaisonScolaire, dt, gestionnaireEvenements);
				} else {
					reponse200PATCHSaisonScolaire(requeteSite, gestionnaireEvenements);
				}
			} else {
				erreurSaisonScolaire(listeSaisonScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<SaisonScolaire> patchSaisonScolaireFuture(SaisonScolaire o, Boolean inheritPk, Handler<AsyncResult<SaisonScolaire>> gestionnaireEvenements) {
		Promise<SaisonScolaire> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			sqlPATCHSaisonScolaire(o, inheritPk, a -> {
				if(a.succeeded()) {
					SaisonScolaire saisonScolaire = a.result();
					definirSaisonScolaire(saisonScolaire, b -> {
						if(b.succeeded()) {
							attribuerSaisonScolaire(saisonScolaire, c -> {
								if(c.succeeded()) {
									indexerSaisonScolaire(saisonScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(saisonScolaire));
											promise.complete(saisonScolaire);
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
			erreurSaisonScolaire(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHSaisonScolaire(SaisonScolaire o, Boolean inheritPk, Handler<AsyncResult<SaisonScolaire>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject requeteJson = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = requeteJson.fieldNames();
			SaisonScolaire o2 = new SaisonScolaire();

			patchSql.append(SiteContexteFrFR.SQL_modifier);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.frFR.saison.SaisonScolaire"));
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
					case "setAnneeCle":
						{
							Long l = o2.getAnneeCle();
							if(l != null) {
								ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AnneeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setAnneeCle(requeteJson.getString(methodeNom));
									patchSql.append(SiteContexteFrFR.SQL_setA1);
									patchSqlParams.addAll(Arrays.asList("anneeCle", pk, "saisonCles", l));
								}
							}
						}
						break;
					case "removeAnneeCle":
						{
							Long l = o2.getAnneeCle();
							if(l != null) {
								ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AnneeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setAnneeCle(requeteJson.getString(methodeNom));
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("anneeCle", pk, "saisonCles", l));
								}
							}
						}
						break;
					case "addSessionCles":
						{
							Long l = Long.parseLong(requeteJson.getString(methodeNom));
							if(l != null) {
								ListeRecherche<SessionScolaire> listeRecherche = new ListeRecherche<SessionScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(SessionScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("saisonCle", l, "sessionCles", pk));
								}
							}
						}
						break;
					case "addAllSessionCles":
						JsonArray addAllSessionClesValeurs = requeteJson.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllSessionClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllSessionClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<SessionScolaire> listeRecherche = new ListeRecherche<SessionScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(SessionScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("saisonCle", l, "sessionCles", pk));
								}
							}
						}
						break;
					case "setSessionCles":
						JsonArray setSessionClesValeurs = requeteJson.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("saisonCle", "sessionCles", pk));
						for(Integer i = 0; i <  setSessionClesValeurs.size(); i++) {
							Long l = Long.parseLong(setSessionClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<SessionScolaire> listeRecherche = new ListeRecherche<SessionScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(SessionScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("saisonCle", l, "sessionCles", pk));
								}
							}
						}
						break;
					case "removeSessionCles":
						{
							Long l = Long.parseLong(requeteJson.getString(methodeNom));
							if(l != null) {
								ListeRecherche<SessionScolaire> listeRecherche = new ListeRecherche<SessionScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(SessionScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("saisonCle", l, "sessionCles", pk));
								}
							}
						}
						break;
					case "setSaisonJourDebut":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "saisonJourDebut"));
						} else {
							o2.setSaisonJourDebut(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("saisonJourDebut", o2.jsonSaisonJourDebut(), pk));
						}
						break;
					case "setSaisonEte":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "saisonEte"));
						} else {
							o2.setSaisonEte(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("saisonEte", o2.jsonSaisonEte(), pk));
						}
						break;
					case "setSaisonHiver":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "saisonHiver"));
						} else {
							o2.setSaisonHiver(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("saisonHiver", o2.jsonSaisonHiver(), pk));
						}
						break;
					case "setSaisonFuture":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "saisonFuture"));
						} else {
							o2.setSaisonFuture(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("saisonFuture", o2.jsonSaisonFuture(), pk));
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
					SaisonScolaire o3 = new SaisonScolaire();
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

	public void patchSaisonScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PATCHSaisonScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("patchSaisonScolaire sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("patchSaisonScolaire sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PATCHSaisonScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSaisonScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete);
		try {

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

			sqlSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSaisonScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheSaisonScolaire(requeteSite, false, true, "/api/saison/{id}", "GET", c -> {
								if(c.succeeded()) {
									ListeRecherche<SaisonScolaire> listeSaisonScolaire = c.result();
									getSaisonScolaireReponse(listeSaisonScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getSaisonScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("getSaisonScolaire a échoué. ", d.cause()));
											erreurSaisonScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("getSaisonScolaire a échoué. ", c.cause()));
									erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void getSaisonScolaireReponse(ListeRecherche<SaisonScolaire> listeSaisonScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeSaisonScolaire.getRequeteSite_();
		reponse200GETSaisonScolaire(listeSaisonScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("getSaisonScolaire sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("getSaisonScolaire sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200GETSaisonScolaire(ListeRecherche<SaisonScolaire> listeSaisonScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeSaisonScolaire.getRequeteSite_();
			SolrDocumentList documentsSolr = listeSaisonScolaire.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listeSaisonScolaire.getList().stream().findFirst().orElse(null));
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void rechercheSaisonScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete);
		try {

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

			sqlSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSaisonScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheSaisonScolaire(requeteSite, false, true, "/api/saison", "Recherche", c -> {
								if(c.succeeded()) {
									ListeRecherche<SaisonScolaire> listeSaisonScolaire = c.result();
									rechercheSaisonScolaireReponse(listeSaisonScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("rechercheSaisonScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("rechercheSaisonScolaire a échoué. ", d.cause()));
											erreurSaisonScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("rechercheSaisonScolaire a échoué. ", c.cause()));
									erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void rechercheSaisonScolaireReponse(ListeRecherche<SaisonScolaire> listeSaisonScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeSaisonScolaire.getRequeteSite_();
		reponse200RechercheSaisonScolaire(listeSaisonScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("rechercheSaisonScolaire sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("rechercheSaisonScolaire sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200RechercheSaisonScolaire(ListeRecherche<SaisonScolaire> listeSaisonScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeSaisonScolaire.getRequeteSite_();
			QueryResponse reponseRecherche = listeSaisonScolaire.getQueryResponse();
			SolrDocumentList documentsSolr = listeSaisonScolaire.getSolrDocumentList();
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
			listeSaisonScolaire.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listeSaisonScolaire.getFields();
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
	public void pagerechercheSaisonScolaireId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		pagerechercheSaisonScolaire(operationRequete, gestionnaireEvenements);
	}

	@Override
	public void pagerechercheSaisonScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete);
		try {

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

			sqlSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSaisonScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheSaisonScolaire(requeteSite, false, true, "/saison", "PageRecherche", c -> {
								if(c.succeeded()) {
									ListeRecherche<SaisonScolaire> listeSaisonScolaire = c.result();
									pagerechercheSaisonScolaireReponse(listeSaisonScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("pagerechercheSaisonScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("pagerechercheSaisonScolaire a échoué. ", d.cause()));
											erreurSaisonScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("pagerechercheSaisonScolaire a échoué. ", c.cause()));
									erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void pagerechercheSaisonScolaireReponse(ListeRecherche<SaisonScolaire> listeSaisonScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeSaisonScolaire.getRequeteSite_();
		reponse200PageRechercheSaisonScolaire(listeSaisonScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("pagerechercheSaisonScolaire sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("pagerechercheSaisonScolaire sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PageRechercheSaisonScolaire(ListeRecherche<SaisonScolaire> listeSaisonScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeSaisonScolaire.getRequeteSite_();
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(listeSaisonScolaire.getRequeteSite_(), buffer);
			SaisonPage page = new SaisonPage();
			SolrDocument pageDocumentSolr = new SolrDocument();
			CaseInsensitiveHeaders requeteEnTetes = new CaseInsensitiveHeaders();
			requeteSite.setRequeteEnTetes(requeteEnTetes);

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/saison");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			if(listeSaisonScolaire.size() == 1)
				requeteSite.setRequetePk(listeSaisonScolaire.get(0).getPk());
			requeteSite.setW(w);
			page.setListeSaisonScolaire(listeSaisonScolaire);
			page.setRequeteSite_(requeteSite);
			page.initLoinSaisonPage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requeteEnTetes)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<SaisonScolaire> definirIndexerSaisonScolaire(SaisonScolaire saisonScolaire, Handler<AsyncResult<SaisonScolaire>> gestionnaireEvenements) {
		Promise<SaisonScolaire> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = saisonScolaire.getRequeteSite_();
		definirSaisonScolaire(saisonScolaire, c -> {
			if(c.succeeded()) {
				attribuerSaisonScolaire(saisonScolaire, d -> {
					if(d.succeeded()) {
						indexerSaisonScolaire(saisonScolaire, e -> {
							if(e.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(saisonScolaire));
								promise.complete(saisonScolaire);
							} else {
								erreurSaisonScolaire(requeteSite, null, e);
							}
						});
					} else {
						erreurSaisonScolaire(requeteSite, null, d);
					}
				});
			} else {
				erreurSaisonScolaire(requeteSite, null, c);
			}
		});
		return promise.future();
	}

	public void creerSaisonScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<SaisonScolaire>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_creer
					, new JsonArray(Arrays.asList(SaisonScolaire.class.getCanonicalName(), utilisateurId))
					, creerAsync
			-> {
				JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = creerLigne.getLong(0);
				SaisonScolaire o = new SaisonScolaire();
				o.setPk(pk);
				o.setRequeteSite_(requeteSite);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public RequeteApi requeteApiSaisonScolaire(SaisonScolaire o) {
		RequeteApi requeteApi = o.getRequeteSite_().getRequeteApi_();
		if(requeteApi != null) {
			List<Long> pks = requeteApi.getPks();
			List<String> classes = requeteApi.getClasses();
			if(o.getAnneeCle() != null) {
				if(!pks.contains(o.getAnneeCle())) {
					pks.add(o.getAnneeCle());
					classes.add("AnneeScolaire");
				}
			}
			for(Long pk : o.getSessionCles()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("SessionScolaire");
				}
			}
		}
		return requeteApi;
	}

	public void erreurSaisonScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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
						LOGGER.info(String.format("sql rollback. "));
						connexionSql.close(b -> {
							if(a.succeeded()) {
								LOGGER.info(String.format("sql close. "));
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

	public void sqlSaisonScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public RequeteSiteFrFR genererRequeteSiteFrFRPourSaisonScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete) {
		return genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete, null);
	}

	public RequeteSiteFrFR genererRequeteSiteFrFRPourSaisonScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete, JsonObject body) {
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

	public void utilisateurSaisonScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
								utilisateurSaisonScolaireDefinir(requeteSite, jsonObject, false);

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
																		erreurSaisonScolaire(requeteSite, gestionnaireEvenements, f);
																	}
																});
															} else {
																erreurSaisonScolaire(requeteSite, gestionnaireEvenements, e);
															}
														});
													} else {
														erreurSaisonScolaire(requeteSite, gestionnaireEvenements, d);
													}
												});
											} else {
												erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
											}
										});
									} else {
										erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
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
								Boolean definir = utilisateurSaisonScolaireDefinir(requeteSite, jsonObject, true);
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
																	erreurSaisonScolaire(requeteSite, gestionnaireEvenements, f);
																}
															});
														} else {
															erreurSaisonScolaire(requeteSite, gestionnaireEvenements, e);
														}
													});
												} else {
													erreurSaisonScolaire(requeteSite, gestionnaireEvenements, d);
												}
											});
										} else {
											erreurSaisonScolaire(requeteSite, gestionnaireEvenements, c);
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

	public Boolean utilisateurSaisonScolaireDefinir(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void rechercheSaisonScolaireQ(String uri, String apiMethode, ListeRecherche<SaisonScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		listeRecherche.setQuery(varIndexe + ":" + ("*".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));
		if(!"*".equals(entiteVar)) {
			listeRecherche.setHighlight(true);
			listeRecherche.setHighlightSnippets(3);
			listeRecherche.addHighlightField(varIndexe);
			listeRecherche.setParam("hl.encoder", "html");
		}
	}

	public void rechercheSaisonScolaireFq(String uri, String apiMethode, ListeRecherche<SaisonScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
	}

	public void rechercheSaisonScolaireSort(String uri, String apiMethode, ListeRecherche<SaisonScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurIndexe));
	}

	public void rechercheSaisonScolaireRows(String uri, String apiMethode, ListeRecherche<SaisonScolaire> listeRecherche, Integer valeurRows) {
			listeRecherche.setRows(apiMethode != null && apiMethode.contains("Recherche") ? valeurRows : 10);
	}

	public void rechercheSaisonScolaireStart(String uri, String apiMethode, ListeRecherche<SaisonScolaire> listeRecherche, Integer valeurStart) {
		listeRecherche.setStart(valeurStart);
	}

	public void rechercheSaisonScolaireVar(String uri, String apiMethode, ListeRecherche<SaisonScolaire> listeRecherche, String var, String valeur) {
		listeRecherche.getRequeteSite_().getRequeteVars().put(var, valeur);
	}

	public void rechercheSaisonScolaire(RequeteSiteFrFR requeteSite, Boolean peupler, Boolean stocker, String uri, String apiMethode, Handler<AsyncResult<ListeRecherche<SaisonScolaire>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<SaisonScolaire> listeRecherche = new ListeRecherche<SaisonScolaire>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(SaisonScolaire.class);
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
								varIndexe = "*".equals(entiteVar) ? entiteVar : SaisonScolaire.varRechercheSaisonScolaire(entiteVar);
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								valeurIndexe = StringUtils.isEmpty(valeurIndexe) ? "*" : valeurIndexe;
								rechercheSaisonScolaireQ(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "fq":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								varIndexe = SaisonScolaire.varIndexeSaisonScolaire(entiteVar);
								rechercheSaisonScolaireFq(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "sort":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
								valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
								varIndexe = SaisonScolaire.varIndexeSaisonScolaire(entiteVar);
								rechercheSaisonScolaireSort(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "start":
								valeurStart = (Integer)paramObjet;
								rechercheSaisonScolaireStart(uri, apiMethode, listeRecherche, valeurStart);
								break;
							case "rows":
								valeurRows = (Integer)paramObjet;
								rechercheSaisonScolaireRows(uri, apiMethode, listeRecherche, valeurRows);
								break;
							case "var":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								rechercheSaisonScolaireVar(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe);
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

	public void definirSaisonScolaire(SaisonScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void attribuerSaisonScolaire(SaisonScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void indexerSaisonScolaire(SaisonScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			o.initLoinPourClasse(requeteSite);
			o.indexerPourClasse();
			if(BooleanUtils.isFalse(Optional.ofNullable(requeteSite.getRequeteApi_()).map(RequeteApi::getEmpty).orElse(null))) {
				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());
				ListeRecherche<SaisonScolaire> listeRecherche = new ListeRecherche<SaisonScolaire>();
				listeRecherche.setPeupler(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(SaisonScolaire.class);
				listeRecherche.addFilterQuery("modifie_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(requeteSite.getRequeteApi_().getCree().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				listeRecherche.add("json.facet", "{anneeCle:{terms:{field:anneeCle_indexed_longs, limit:1000}}}");
				listeRecherche.add("json.facet", "{sessionCles:{terms:{field:sessionCles_indexed_longs, limit:1000}}}");
				listeRecherche.setRows(1000);
				listeRecherche.initLoinListeRecherche(requeteSite2);
				List<Future> futures = new ArrayList<>();

				{
					AnneeScolaire o2 = new AnneeScolaire();
					AnneeScolaireFrFRGenApiServiceImpl service = new AnneeScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
					Long pk = o.getAnneeCle();

					if(pk != null) {
						o2.setPk(pk);
						o2.setRequeteSite_(requeteSite2);
						futures.add(
							service.patchAnneeScolaireFuture(o2, false, a -> {
								if(a.succeeded()) {
									LOGGER.info(String.format("AnneeScolaire %s rechargé. ", pk));
								} else {
									LOGGER.info(String.format("AnneeScolaire %s a échoué. ", pk));
									gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
								}
							})
						);
					}
				}

				{
					SessionScolaireFrFRGenApiServiceImpl service = new SessionScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
					for(Long pk : o.getSessionCles()) {
						SessionScolaire o2 = new SessionScolaire();

						o2.setPk(pk);
						o2.setRequeteSite_(requeteSite2);
						futures.add(
							service.patchSessionScolaireFuture(o2, false, a -> {
								if(a.succeeded()) {
									LOGGER.info(String.format("SessionScolaire %s rechargé. ", pk));
								} else {
									LOGGER.info(String.format("SessionScolaire %s a échoué. ", pk));
									gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
								}
							})
						);
					}
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						LOGGER.info("Recharger relations a réussi. ");
						SaisonScolaireFrFRGenApiServiceImpl service = new SaisonScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
						List<Future> futures2 = new ArrayList<>();
						for(SaisonScolaire o2 : listeRecherche.getList()) {
							futures2.add(
								service.patchSaisonScolaireFuture(o2, false, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("SaisonScolaire %s rechargé. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("SaisonScolaire %s a échoué. ", o2.getPk()));
										gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Recharger SaisonScolaire a réussi. ");
								gestionnaireEvenements.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Recharger relations a échoué. ", b.cause());
								erreurSaisonScolaire(requeteSite2, gestionnaireEvenements, b);
							}
						});
					} else {
						LOGGER.error("Recharger relations a échoué. ", a.cause());
						erreurSaisonScolaire(requeteSite2, gestionnaireEvenements, a);
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
