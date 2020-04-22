package org.computate.scolaire.frFR.design;

import org.computate.scolaire.frFR.design.DesignPageFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.design.DesignPage;
import org.computate.scolaire.frFR.design.DesignPageFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.design.DesignPage;
import org.computate.scolaire.frFR.html.part.PartHtmlFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.html.part.PartHtml;
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
 * NomCanonique.enUS: org.computate.scolaire.enUS.design.PageDesignEnUSGenApiServiceImpl
 **/
public class DesignPageFrFRGenApiServiceImpl implements DesignPageFrFRGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(DesignPageFrFRGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "DesignPageFrFRApiServiceImpl";

	protected SiteContexteFrFR siteContexte;

	public DesignPageFrFRGenApiServiceImpl(SiteContexteFrFR siteContexte) {
		this.siteContexte = siteContexte;
	}

	// POST //

	@Override
	public void postDesignPage(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourDesignPage(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("postDesignPage a démarré. "));

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

			sqlDesignPage(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurDesignPage(requeteSite, b -> {
						if(b.succeeded()) {
							RequeteApi requeteApi = new RequeteApi();
							requeteApi.setRows(1);
							requeteApi.setNumFound(1L);
							requeteApi.setNumPATCH(0L);
							requeteApi.initLoinRequeteApi(requeteSite);
							requeteSite.setRequeteApi_(requeteApi);
							requeteSite.getVertx().eventBus().publish("websocketDesignPage", JsonObject.mapFrom(requeteApi).toString());
							postDesignPageFuture(requeteSite, false, c -> {
								if(c.succeeded()) {
									DesignPage designPage = c.result();
									requeteApiDesignPage(designPage);
									postDesignPageReponse(designPage, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postDesignPage a réussi. "));
										} else {
											LOGGER.error(String.format("postDesignPage a échoué. ", d.cause()));
											erreurDesignPage(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("postDesignPage a échoué. ", c.cause()));
									erreurDesignPage(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurDesignPage(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurDesignPage(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurDesignPage(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public Future<DesignPage> postDesignPageFuture(RequeteSiteFrFR requeteSite, Boolean inheritPk, Handler<AsyncResult<DesignPage>> gestionnaireEvenements) {
		Promise<DesignPage> promise = Promise.promise();
		try {
			creerDesignPage(requeteSite, a -> {
				if(a.succeeded()) {
					DesignPage designPage = a.result();
					sqlPOSTDesignPage(designPage, inheritPk, b -> {
						if(b.succeeded()) {
							definirIndexerDesignPage(designPage, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(designPage));
									promise.complete(designPage);
								} else {
									erreurDesignPage(requeteSite, null, c);
								}
							});
						} else {
							erreurDesignPage(requeteSite, null, b);
						}
					});
				} else {
					erreurDesignPage(requeteSite, null, a);
				}
			});
		} catch(Exception e) {
			erreurDesignPage(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTDesignPage(DesignPage o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
					case "designEnfantCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(DesignPage.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContexteFrFR.SQL_addA);
									postSqlParams.addAll(Arrays.asList("designEnfantCles", pk, "designParentCles", l));
								}
							}
						}
						break;
					case "designParentCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(DesignPage.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContexteFrFR.SQL_addA);
									postSqlParams.addAll(Arrays.asList("designEnfantCles", l, "designParentCles", pk));
								}
							}
						}
						break;
					case "partHtmlCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								ListeRecherche<PartHtml> listeRecherche = new ListeRecherche<PartHtml>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PartHtml.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContexteFrFR.SQL_addA);
									postSqlParams.addAll(Arrays.asList("designPageCles", l, "partHtmlCles", pk));
								}
							}
						}
						break;
					case "designPageNomComplet":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("designPageNomComplet", jsonObject.getString(entiteVar), pk));
						break;
					case "designCache":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("designCache", jsonObject.getBoolean(entiteVar), pk));
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

	public void postDesignPageReponse(DesignPage designPage, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = designPage.getRequeteSite_();
		reponse200POSTDesignPage(designPage, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("postDesignPage sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("postDesignPage sql close. "));
								RequeteApi requeteApi = requeteApiDesignPage(designPage);
								designPage.requeteApiDesignPage();
								requeteSite.getVertx().eventBus().publish("websocketDesignPage", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurDesignPage(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurDesignPage(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurDesignPage(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200POSTDesignPage(DesignPage o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
	public void putimportDesignPage(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourDesignPage(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putimportDesignPage a démarré. "));

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

			sqlDesignPage(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurDesignPage(requeteSite, b -> {
						if(b.succeeded()) {
							putimportDesignPageReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourDesignPage(siteContexte, operationRequete, body);
											try {
												RequeteApi requeteApi = new RequeteApi();
												JsonArray jsonArray = Optional.ofNullable(requeteSite2.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												requeteApi.setRows(jsonArray.size());
												requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite2);
												requeteSite2.setRequeteApi_(requeteApi);
												requeteSite2.getVertx().eventBus().publish("websocketDesignPage", JsonObject.mapFrom(requeteApi).toString());
												sqlDesignPage(requeteSite2, d -> {
													if(d.succeeded()) {
														listePUTImportDesignPage(requeteApi, requeteSite2, e -> {
															if(e.succeeded()) {
																putimportDesignPageReponse(requeteSite2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putimportDesignPage a réussi. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putimportDesignPage a échoué. ", f.cause()));
																		erreurDesignPage(requeteSite2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putimportDesignPage a échoué. ", e.cause()));
																erreurDesignPage(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putimportDesignPage a échoué. ", d.cause()));
														erreurDesignPage(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putimportDesignPage a échoué. ", ex));
												erreurDesignPage(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putimportDesignPage a échoué. ", c.cause()));
									erreurDesignPage(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurDesignPage(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurDesignPage(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurDesignPage(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTImportDesignPage(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourDesignPage(siteContexte, requeteSite.getOperationRequete(), json);
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());

				ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(DesignPage.class);
				listeRecherche.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				listeRecherche.initLoinPourClasse(requeteSite2);

				if(listeRecherche.size() == 1) {
					DesignPage o = listeRecherche.getList().stream().findFirst().orElse(null);
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
							patchDesignPageFuture(o, true, a -> {
								if(a.succeeded()) {
									DesignPage designPage = a.result();
									requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
									requeteSite2.getVertx().eventBus().publish("websocketDesignPage", JsonObject.mapFrom(requeteApi).toString());
								} else {
									erreurDesignPage(requeteSite2, gestionnaireEvenements, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postDesignPageFuture(requeteSite2, true, a -> {
							if(a.succeeded()) {
								DesignPage designPage = a.result();
								requeteApiDesignPage(designPage);
							} else {
								erreurDesignPage(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
								requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
								requeteSite.getVertx().eventBus().publish("websocketDesignPage", JsonObject.mapFrom(requeteApi).toString());
					reponse200PUTImportDesignPage(requeteSite, gestionnaireEvenements);
				} else {
					erreurDesignPage(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportDesignPage a échoué. ", ex));
			erreurDesignPage(requeteSite, null, Future.failedFuture(ex));
		}
	}

	public void putimportDesignPageReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PUTImportDesignPage(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("putimportDesignPage sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("putimportDesignPage sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurDesignPage(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurDesignPage(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurDesignPage(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTImportDesignPage(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTFusion //

	@Override
	public void putfusionDesignPage(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourDesignPage(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putfusionDesignPage a démarré. "));

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

			sqlDesignPage(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurDesignPage(requeteSite, b -> {
						if(b.succeeded()) {
							putfusionDesignPageReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourDesignPage(siteContexte, operationRequete, body);
											try {
												RequeteApi requeteApi = new RequeteApi();
												JsonArray jsonArray = Optional.ofNullable(requeteSite2.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												requeteApi.setRows(jsonArray.size());
												requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite2);
												requeteSite2.setRequeteApi_(requeteApi);
												requeteSite2.getVertx().eventBus().publish("websocketDesignPage", JsonObject.mapFrom(requeteApi).toString());
												sqlDesignPage(requeteSite2, d -> {
													if(d.succeeded()) {
														listePUTFusionDesignPage(requeteApi, requeteSite2, e -> {
															if(e.succeeded()) {
																putfusionDesignPageReponse(requeteSite2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putfusionDesignPage a réussi. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putfusionDesignPage a échoué. ", f.cause()));
																		erreurDesignPage(requeteSite2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putfusionDesignPage a échoué. ", e.cause()));
																erreurDesignPage(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putfusionDesignPage a échoué. ", d.cause()));
														erreurDesignPage(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putfusionDesignPage a échoué. ", ex));
												erreurDesignPage(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putfusionDesignPage a échoué. ", c.cause()));
									erreurDesignPage(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurDesignPage(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurDesignPage(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurDesignPage(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTFusionDesignPage(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourDesignPage(siteContexte, requeteSite.getOperationRequete(), json);
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());

				ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(DesignPage.class);
				listeRecherche.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				listeRecherche.initLoinPourClasse(requeteSite2);

				if(listeRecherche.size() == 1) {
					DesignPage o = listeRecherche.getList().stream().findFirst().orElse(null);
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
							patchDesignPageFuture(o, false, a -> {
								if(a.succeeded()) {
									DesignPage designPage = a.result();
									requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
									requeteSite2.getVertx().eventBus().publish("websocketDesignPage", JsonObject.mapFrom(requeteApi).toString());
								} else {
									erreurDesignPage(requeteSite2, gestionnaireEvenements, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postDesignPageFuture(requeteSite2, false, a -> {
							if(a.succeeded()) {
								DesignPage designPage = a.result();
								requeteApiDesignPage(designPage);
							} else {
								erreurDesignPage(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
								requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
								requeteSite.getVertx().eventBus().publish("websocketDesignPage", JsonObject.mapFrom(requeteApi).toString());
					reponse200PUTFusionDesignPage(requeteSite, gestionnaireEvenements);
				} else {
					erreurDesignPage(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putfusionDesignPage a échoué. ", ex));
			erreurDesignPage(requeteSite, null, Future.failedFuture(ex));
		}
	}

	public void putfusionDesignPageReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PUTFusionDesignPage(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("putfusionDesignPage sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("putfusionDesignPage sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurDesignPage(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurDesignPage(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurDesignPage(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTFusionDesignPage(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTCopie //

	@Override
	public void putcopieDesignPage(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourDesignPage(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putcopieDesignPage a démarré. "));

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

			sqlDesignPage(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurDesignPage(requeteSite, b -> {
						if(b.succeeded()) {
							putcopieDesignPageReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourDesignPage(siteContexte, operationRequete, body);
											try {
												rechercheDesignPage(requeteSite2, false, true, "/api/design-page/copie", "PUTCopie", d -> {
													if(d.succeeded()) {
														ListeRecherche<DesignPage> listeDesignPage = d.result();
														RequeteApi requeteApi = new RequeteApi();
														requeteApi.setRows(listeDesignPage.getRows());
														requeteApi.setNumFound(listeDesignPage.getQueryResponse().getResults().getNumFound());
														requeteApi.setNumPATCH(0L);
														requeteApi.initLoinRequeteApi(requeteSite2);
														requeteSite2.setRequeteApi_(requeteApi);
														requeteSite2.getVertx().eventBus().publish("websocketDesignPage", JsonObject.mapFrom(requeteApi).toString());
														sqlDesignPage(requeteSite2, e -> {
															if(e.succeeded()) {
																try {
																	listePUTCopieDesignPage(requeteApi, listeDesignPage, f -> {
																		if(f.succeeded()) {
																			putcopieDesignPageReponse(requeteSite2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("putcopieDesignPage a réussi. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("putcopieDesignPage a échoué. ", g.cause()));
																					erreurDesignPage(requeteSite2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("putcopieDesignPage a échoué. ", f.cause()));
																			erreurDesignPage(requeteSite2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("putcopieDesignPage a échoué. ", ex));
																	erreurDesignPage(requeteSite2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("putcopieDesignPage a échoué. ", e.cause()));
																erreurDesignPage(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putcopieDesignPage a échoué. ", d.cause()));
														erreurDesignPage(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putcopieDesignPage a échoué. ", ex));
												erreurDesignPage(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putcopieDesignPage a échoué. ", c.cause()));
									erreurDesignPage(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurDesignPage(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurDesignPage(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurDesignPage(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTCopieDesignPage(RequeteApi requeteApi, ListeRecherche<DesignPage> listeDesignPage, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeDesignPage.getRequeteSite_();
		listeDesignPage.getList().forEach(o -> {
			futures.add(
				putcopieDesignPageFuture(requeteSite, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						DesignPage designPage = a.result();
						requeteApiDesignPage(designPage);
					} else {
						erreurDesignPage(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeDesignPage.size());
				if(listeDesignPage.next()) {
					requeteSite.getVertx().eventBus().publish("websocketDesignPage", JsonObject.mapFrom(requeteApi).toString());
					listePUTCopieDesignPage(requeteApi, listeDesignPage, gestionnaireEvenements);
				} else {
					reponse200PUTCopieDesignPage(requeteSite, gestionnaireEvenements);
				}
			} else {
				erreurDesignPage(listeDesignPage.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<DesignPage> putcopieDesignPageFuture(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Handler<AsyncResult<DesignPage>> gestionnaireEvenements) {
		Promise<DesignPage> promise = Promise.promise();
		try {

			jsonObject.put("sauvegardes", Optional.ofNullable(jsonObject.getJsonArray("sauvegardes")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("sauvegardes").add(o.getKey());
			});

			creerDesignPage(requeteSite, a -> {
				if(a.succeeded()) {
					DesignPage designPage = a.result();
					sqlPUTCopieDesignPage(designPage, jsonObject, b -> {
						if(b.succeeded()) {
							definirDesignPage(designPage, c -> {
								if(c.succeeded()) {
									attribuerDesignPage(designPage, d -> {
										if(d.succeeded()) {
											indexerDesignPage(designPage, e -> {
												if(e.succeeded()) {
													gestionnaireEvenements.handle(Future.succeededFuture(designPage));
													promise.complete(designPage);
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
			erreurDesignPage(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopieDesignPage(DesignPage o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
					case "designEnfantCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContexteFrFR.SQL_addA);
							putSqlParams.addAll(Arrays.asList("designEnfantCles", pk, "designParentCles", l));
						}
						break;
					case "designParentCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContexteFrFR.SQL_addA);
							putSqlParams.addAll(Arrays.asList("designEnfantCles", l, "designParentCles", pk));
						}
						break;
					case "partHtmlCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContexteFrFR.SQL_addA);
							putSqlParams.addAll(Arrays.asList("designPageCles", l, "partHtmlCles", pk));
						}
						break;
					case "designPageNomComplet":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("designPageNomComplet", jsonObject.getString(entiteVar), pk));
						break;
					case "designCache":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("designCache", jsonObject.getBoolean(entiteVar), pk));
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

	public void putcopieDesignPageReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PUTCopieDesignPage(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("putcopieDesignPage sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("putcopieDesignPage sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurDesignPage(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurDesignPage(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurDesignPage(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTCopieDesignPage(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchDesignPage(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourDesignPage(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("patchDesignPage a démarré. "));

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

			sqlDesignPage(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurDesignPage(requeteSite, b -> {
						if(b.succeeded()) {
							patchDesignPageReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourDesignPage(siteContexte, operationRequete, body);
											try {
												rechercheDesignPage(requeteSite2, false, true, "/api/design-page", "PATCH", d -> {
													if(d.succeeded()) {
														ListeRecherche<DesignPage> listeDesignPage = d.result();
														RequeteApi requeteApi = new RequeteApi();
														requeteApi.setRows(listeDesignPage.getRows());
														requeteApi.setNumFound(listeDesignPage.getQueryResponse().getResults().getNumFound());
														requeteApi.setNumPATCH(0L);
														requeteApi.initLoinRequeteApi(requeteSite2);
														requeteSite2.setRequeteApi_(requeteApi);
														requeteSite2.getVertx().eventBus().publish("websocketDesignPage", JsonObject.mapFrom(requeteApi).toString());
														SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeDesignPage.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
														Date date = null;
														if(facets != null)
															date = (Date)facets.get("max_modifie");
														String dt;
														if(date == null)
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
														else
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
														listeDesignPage.addFilterQuery(String.format("modifie_indexed_date:[* TO %s]", dt));

														DesignPage o = listeDesignPage.getList().stream().findFirst().orElse(null);
														if(o != null) {
															requeteApi.setPk(o.getPk());
															requeteApi.setOriginal(o);
															requeteApiDesignPage(o);
															o.requeteApiDesignPage();
														}
														sqlDesignPage(requeteSite2, e -> {
															if(e.succeeded()) {
																try {
																	listePATCHDesignPage(requeteApi, listeDesignPage, dt, f -> {
																		if(f.succeeded()) {
																			patchDesignPageReponse(requeteSite2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("patchDesignPage a réussi. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("patchDesignPage a échoué. ", g.cause()));
																					erreurDesignPage(requeteSite2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("patchDesignPage a échoué. ", f.cause()));
																			erreurDesignPage(requeteSite2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("patchDesignPage a échoué. ", ex));
																	erreurDesignPage(requeteSite2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("patchDesignPage a échoué. ", e.cause()));
																erreurDesignPage(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("patchDesignPage a échoué. ", d.cause()));
														erreurDesignPage(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("patchDesignPage a échoué. ", ex));
												erreurDesignPage(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("patchDesignPage a échoué. ", c.cause()));
									erreurDesignPage(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurDesignPage(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurDesignPage(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurDesignPage(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePATCHDesignPage(RequeteApi requeteApi, ListeRecherche<DesignPage> listeDesignPage, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeDesignPage.getRequeteSite_();
		listeDesignPage.getList().forEach(o -> {
			futures.add(
				patchDesignPageFuture(o, false, a -> {
					if(a.succeeded()) {
							DesignPage designPage = a.result();
							requeteApiDesignPage(designPage);
					} else {
						erreurDesignPage(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeDesignPage.size());
				if(listeDesignPage.next(dt)) {
					requeteSite.getVertx().eventBus().publish("websocketDesignPage", JsonObject.mapFrom(requeteApi).toString());
					listePATCHDesignPage(requeteApi, listeDesignPage, dt, gestionnaireEvenements);
				} else {
					reponse200PATCHDesignPage(requeteSite, gestionnaireEvenements);
				}
			} else {
				erreurDesignPage(listeDesignPage.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<DesignPage> patchDesignPageFuture(DesignPage o, Boolean inheritPk, Handler<AsyncResult<DesignPage>> gestionnaireEvenements) {
		Promise<DesignPage> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			sqlPATCHDesignPage(o, inheritPk, a -> {
				if(a.succeeded()) {
					DesignPage designPage = a.result();
					definirDesignPage(designPage, b -> {
						if(b.succeeded()) {
							attribuerDesignPage(designPage, c -> {
								if(c.succeeded()) {
									indexerDesignPage(designPage, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(designPage));
											promise.complete(designPage);
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
			erreurDesignPage(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHDesignPage(DesignPage o, Boolean inheritPk, Handler<AsyncResult<DesignPage>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject requeteJson = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = requeteJson.fieldNames();
			DesignPage o2 = new DesignPage();

			patchSql.append(SiteContexteFrFR.SQL_modifier);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.frFR.design.DesignPage"));
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
					case "addDesignEnfantCles":
						{
							Long l = Long.parseLong(requeteJson.getString(methodeNom));
							if(l != null) {
								ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(DesignPage.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("designEnfantCles", pk, "designParentCles", l));
								}
							}
						}
						break;
					case "addAllDesignEnfantCles":
						JsonArray addAllDesignEnfantClesValeurs = requeteJson.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllDesignEnfantClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllDesignEnfantClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(DesignPage.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("designEnfantCles", pk, "designParentCles", l));
								}
							}
						}
						break;
					case "setDesignEnfantCles":
						JsonArray setDesignEnfantClesValeurs = requeteJson.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("designEnfantCles", pk, "designParentCles"));
						for(Integer i = 0; i <  setDesignEnfantClesValeurs.size(); i++) {
							Long l = Long.parseLong(setDesignEnfantClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(DesignPage.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("designEnfantCles", pk, "designParentCles", l));
								}
							}
						}
						break;
					case "removeDesignEnfantCles":
						{
							Long l = Long.parseLong(requeteJson.getString(methodeNom));
							if(l != null) {
								ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(DesignPage.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("designEnfantCles", pk, "designParentCles", l));
								}
							}
						}
						break;
					case "addDesignParentCles":
						{
							Long l = Long.parseLong(requeteJson.getString(methodeNom));
							if(l != null) {
								ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(DesignPage.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("designEnfantCles", l, "designParentCles", pk));
								}
							}
						}
						break;
					case "addAllDesignParentCles":
						JsonArray addAllDesignParentClesValeurs = requeteJson.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllDesignParentClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllDesignParentClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(DesignPage.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("designEnfantCles", l, "designParentCles", pk));
								}
							}
						}
						break;
					case "setDesignParentCles":
						JsonArray setDesignParentClesValeurs = requeteJson.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("designEnfantCles", "designParentCles", pk));
						for(Integer i = 0; i <  setDesignParentClesValeurs.size(); i++) {
							Long l = Long.parseLong(setDesignParentClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(DesignPage.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("designEnfantCles", l, "designParentCles", pk));
								}
							}
						}
						break;
					case "removeDesignParentCles":
						{
							Long l = Long.parseLong(requeteJson.getString(methodeNom));
							if(l != null) {
								ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(DesignPage.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("designEnfantCles", l, "designParentCles", pk));
								}
							}
						}
						break;
					case "addPartHtmlCles":
						{
							Long l = Long.parseLong(requeteJson.getString(methodeNom));
							if(l != null) {
								ListeRecherche<PartHtml> listeRecherche = new ListeRecherche<PartHtml>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PartHtml.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("designPageCles", l, "partHtmlCles", pk));
								}
							}
						}
						break;
					case "addAllPartHtmlCles":
						JsonArray addAllPartHtmlClesValeurs = requeteJson.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllPartHtmlClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllPartHtmlClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<PartHtml> listeRecherche = new ListeRecherche<PartHtml>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PartHtml.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("designPageCles", l, "partHtmlCles", pk));
								}
							}
						}
						break;
					case "setPartHtmlCles":
						JsonArray setPartHtmlClesValeurs = requeteJson.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("designPageCles", "partHtmlCles", pk));
						for(Integer i = 0; i <  setPartHtmlClesValeurs.size(); i++) {
							Long l = Long.parseLong(setPartHtmlClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<PartHtml> listeRecherche = new ListeRecherche<PartHtml>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PartHtml.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("designPageCles", l, "partHtmlCles", pk));
								}
							}
						}
						break;
					case "removePartHtmlCles":
						{
							Long l = Long.parseLong(requeteJson.getString(methodeNom));
							if(l != null) {
								ListeRecherche<PartHtml> listeRecherche = new ListeRecherche<PartHtml>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PartHtml.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("designPageCles", l, "partHtmlCles", pk));
								}
							}
						}
						break;
					case "setDesignPageNomComplet":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "designPageNomComplet"));
						} else {
							o2.setDesignPageNomComplet(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("designPageNomComplet", o2.jsonDesignPageNomComplet(), pk));
						}
						break;
					case "setDesignCache":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "designCache"));
						} else {
							o2.setDesignCache(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("designCache", o2.jsonDesignCache(), pk));
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
					DesignPage o3 = new DesignPage();
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

	public void patchDesignPageReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PATCHDesignPage(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("patchDesignPage sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("patchDesignPage sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurDesignPage(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurDesignPage(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurDesignPage(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PATCHDesignPage(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getDesignPage(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourDesignPage(siteContexte, operationRequete);
		try {
			sqlDesignPage(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurDesignPage(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheDesignPage(requeteSite, false, true, "/api/design-page/{id}", "GET", c -> {
								if(c.succeeded()) {
									ListeRecherche<DesignPage> listeDesignPage = c.result();
									getDesignPageReponse(listeDesignPage, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getDesignPage a réussi. "));
										} else {
											LOGGER.error(String.format("getDesignPage a échoué. ", d.cause()));
											erreurDesignPage(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("getDesignPage a échoué. ", c.cause()));
									erreurDesignPage(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurDesignPage(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurDesignPage(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurDesignPage(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void getDesignPageReponse(ListeRecherche<DesignPage> listeDesignPage, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeDesignPage.getRequeteSite_();
		reponse200GETDesignPage(listeDesignPage, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("getDesignPage sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("getDesignPage sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurDesignPage(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurDesignPage(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurDesignPage(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200GETDesignPage(ListeRecherche<DesignPage> listeDesignPage, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeDesignPage.getRequeteSite_();
			SolrDocumentList documentsSolr = listeDesignPage.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listeDesignPage.getList().stream().findFirst().orElse(null));
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void rechercheDesignPage(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourDesignPage(siteContexte, operationRequete);
		try {
			sqlDesignPage(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurDesignPage(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheDesignPage(requeteSite, false, true, "/api/design-page", "Recherche", c -> {
								if(c.succeeded()) {
									ListeRecherche<DesignPage> listeDesignPage = c.result();
									rechercheDesignPageReponse(listeDesignPage, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("rechercheDesignPage a réussi. "));
										} else {
											LOGGER.error(String.format("rechercheDesignPage a échoué. ", d.cause()));
											erreurDesignPage(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("rechercheDesignPage a échoué. ", c.cause()));
									erreurDesignPage(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurDesignPage(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurDesignPage(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurDesignPage(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void rechercheDesignPageReponse(ListeRecherche<DesignPage> listeDesignPage, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeDesignPage.getRequeteSite_();
		reponse200RechercheDesignPage(listeDesignPage, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("rechercheDesignPage sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("rechercheDesignPage sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurDesignPage(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurDesignPage(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurDesignPage(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200RechercheDesignPage(ListeRecherche<DesignPage> listeDesignPage, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeDesignPage.getRequeteSite_();
			QueryResponse reponseRecherche = listeDesignPage.getQueryResponse();
			SolrDocumentList documentsSolr = listeDesignPage.getSolrDocumentList();
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
			listeDesignPage.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listeDesignPage.getFields();
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
	public void pagerechercheDesignPageId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		pagerechercheDesignPage(operationRequete, gestionnaireEvenements);
	}

	@Override
	public void pagerechercheDesignPage(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourDesignPage(siteContexte, operationRequete);
		try {
			sqlDesignPage(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurDesignPage(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheDesignPage(requeteSite, false, true, "/design-page", "PageRecherche", c -> {
								if(c.succeeded()) {
									ListeRecherche<DesignPage> listeDesignPage = c.result();
									pagerechercheDesignPageReponse(listeDesignPage, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("pagerechercheDesignPage a réussi. "));
										} else {
											LOGGER.error(String.format("pagerechercheDesignPage a échoué. ", d.cause()));
											erreurDesignPage(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("pagerechercheDesignPage a échoué. ", c.cause()));
									erreurDesignPage(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurDesignPage(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurDesignPage(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurDesignPage(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void pagerechercheDesignPageReponse(ListeRecherche<DesignPage> listeDesignPage, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeDesignPage.getRequeteSite_();
		reponse200PageRechercheDesignPage(listeDesignPage, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("pagerechercheDesignPage sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("pagerechercheDesignPage sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurDesignPage(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurDesignPage(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurDesignPage(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PageRechercheDesignPage(ListeRecherche<DesignPage> listeDesignPage, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeDesignPage.getRequeteSite_();
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(listeDesignPage.getRequeteSite_(), buffer);
			DesignPagePage page = new DesignPagePage();
			SolrDocument pageDocumentSolr = new SolrDocument();
			CaseInsensitiveHeaders requeteEnTetes = new CaseInsensitiveHeaders();
			requeteSite.setRequeteEnTetes(requeteEnTetes);

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/design-page");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			if(listeDesignPage.size() == 1)
				requeteSite.setRequetePk(listeDesignPage.get(0).getPk());
			requeteSite.setW(w);
			page.setListeDesignPage(listeDesignPage);
			page.setRequeteSite_(requeteSite);
			page.initLoinDesignPagePage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requeteEnTetes)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<DesignPage> definirIndexerDesignPage(DesignPage designPage, Handler<AsyncResult<DesignPage>> gestionnaireEvenements) {
		Promise<DesignPage> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = designPage.getRequeteSite_();
		definirDesignPage(designPage, c -> {
			if(c.succeeded()) {
				attribuerDesignPage(designPage, d -> {
					if(d.succeeded()) {
						indexerDesignPage(designPage, e -> {
							if(e.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(designPage));
								promise.complete(designPage);
							} else {
								erreurDesignPage(requeteSite, null, e);
							}
						});
					} else {
						erreurDesignPage(requeteSite, null, d);
					}
				});
			} else {
				erreurDesignPage(requeteSite, null, c);
			}
		});
		return promise.future();
	}

	public void creerDesignPage(RequeteSiteFrFR requeteSite, Handler<AsyncResult<DesignPage>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_creer
					, new JsonArray(Arrays.asList(DesignPage.class.getCanonicalName(), utilisateurId))
					, creerAsync
			-> {
				JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = creerLigne.getLong(0);
				DesignPage o = new DesignPage();
				o.setPk(pk);
				o.setRequeteSite_(requeteSite);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public RequeteApi requeteApiDesignPage(DesignPage o) {
		RequeteApi requeteApi = o.getRequeteSite_().getRequeteApi_();
		if(requeteApi != null) {
			List<Long> pks = requeteApi.getPks();
			List<String> classes = requeteApi.getClasses();
			for(Long pk : o.getDesignEnfantCles()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("DesignPage");
				}
			}
			for(Long pk : o.getDesignParentCles()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("DesignPage");
				}
			}
			for(Long pk : o.getPartHtmlCles()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("PartHtml");
				}
			}
		}
		return requeteApi;
	}

	public void erreurDesignPage(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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

	public void sqlDesignPage(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public RequeteSiteFrFR genererRequeteSiteFrFRPourDesignPage(SiteContexteFrFR siteContexte, OperationRequest operationRequete) {
		return genererRequeteSiteFrFRPourDesignPage(siteContexte, operationRequete, null);
	}

	public RequeteSiteFrFR genererRequeteSiteFrFRPourDesignPage(SiteContexteFrFR siteContexte, OperationRequest operationRequete, JsonObject body) {
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

	public void utilisateurDesignPage(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
								utilisateurDesignPageDefinir(requeteSite, jsonObject, false);

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
																		erreurDesignPage(requeteSite, gestionnaireEvenements, f);
																	}
																});
															} else {
																erreurDesignPage(requeteSite, gestionnaireEvenements, e);
															}
														});
													} else {
														erreurDesignPage(requeteSite, gestionnaireEvenements, d);
													}
												});
											} else {
												erreurDesignPage(requeteSite, gestionnaireEvenements, c);
											}
										});
									} else {
										erreurDesignPage(requeteSite, gestionnaireEvenements, b);
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
								Boolean definir = utilisateurDesignPageDefinir(requeteSite, jsonObject, true);
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
																	erreurDesignPage(requeteSite, gestionnaireEvenements, f);
																}
															});
														} else {
															erreurDesignPage(requeteSite, gestionnaireEvenements, e);
														}
													});
												} else {
													erreurDesignPage(requeteSite, gestionnaireEvenements, d);
												}
											});
										} else {
											erreurDesignPage(requeteSite, gestionnaireEvenements, c);
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

	public Boolean utilisateurDesignPageDefinir(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void rechercheDesignPageQ(String uri, String apiMethode, ListeRecherche<DesignPage> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		listeRecherche.setQuery(varIndexe + ":" + ("*".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));
		if(!"*".equals(entiteVar)) {
			listeRecherche.setHighlight(true);
			listeRecherche.setHighlightSnippets(3);
			listeRecherche.addHighlightField(varIndexe);
			listeRecherche.setParam("hl.encoder", "html");
		}
	}

	public void rechercheDesignPageFq(String uri, String apiMethode, ListeRecherche<DesignPage> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
	}

	public void rechercheDesignPageSort(String uri, String apiMethode, ListeRecherche<DesignPage> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurIndexe));
	}

	public void rechercheDesignPageRows(String uri, String apiMethode, ListeRecherche<DesignPage> listeRecherche, Integer valeurRows) {
			listeRecherche.setRows(apiMethode != null && apiMethode.contains("Recherche") ? valeurRows : 10);
	}

	public void rechercheDesignPageStart(String uri, String apiMethode, ListeRecherche<DesignPage> listeRecherche, Integer valeurStart) {
		listeRecherche.setStart(valeurStart);
	}

	public void rechercheDesignPageVar(String uri, String apiMethode, ListeRecherche<DesignPage> listeRecherche, String var, String valeur) {
		listeRecherche.getRequeteSite_().getRequeteVars().put(var, valeur);
	}

	public void rechercheDesignPage(RequeteSiteFrFR requeteSite, Boolean peupler, Boolean stocker, String uri, String apiMethode, Handler<AsyncResult<ListeRecherche<DesignPage>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(DesignPage.class);
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
								varIndexe = "*".equals(entiteVar) ? entiteVar : DesignPage.varRechercheDesignPage(entiteVar);
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								valeurIndexe = StringUtils.isEmpty(valeurIndexe) ? "*" : valeurIndexe;
								rechercheDesignPageQ(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "fq":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								varIndexe = DesignPage.varIndexeDesignPage(entiteVar);
								rechercheDesignPageFq(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "sort":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
								valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
								varIndexe = DesignPage.varIndexeDesignPage(entiteVar);
								rechercheDesignPageSort(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "start":
								valeurStart = (Integer)paramObjet;
								rechercheDesignPageStart(uri, apiMethode, listeRecherche, valeurStart);
								break;
							case "rows":
								valeurRows = (Integer)paramObjet;
								rechercheDesignPageRows(uri, apiMethode, listeRecherche, valeurRows);
								break;
							case "var":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								rechercheDesignPageVar(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe);
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

	public void definirDesignPage(DesignPage o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void attribuerDesignPage(DesignPage o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void indexerDesignPage(DesignPage o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			o.initLoinPourClasse(requeteSite);
			o.indexerPourClasse();
			if(BooleanUtils.isFalse(Optional.ofNullable(requeteSite.getRequeteApi_()).map(RequeteApi::getEmpty).orElse(null))) {
				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourDesignPage(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());
				ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
				listeRecherche.setPeupler(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(DesignPage.class);
				listeRecherche.addFilterQuery("modifie_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(requeteSite.getRequeteApi_().getCree().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				listeRecherche.add("json.facet", "{designEnfantCles:{terms:{field:designEnfantCles_indexed_longs, limit:1000}}}");
				listeRecherche.add("json.facet", "{designParentCles:{terms:{field:designParentCles_indexed_longs, limit:1000}}}");
				listeRecherche.add("json.facet", "{partHtmlCles:{terms:{field:partHtmlCles_indexed_longs, limit:1000}}}");
				listeRecherche.setRows(1000);
				listeRecherche.initLoinListeRecherche(requeteSite2);
				List<Future> futures = new ArrayList<>();

				{
					DesignPageFrFRGenApiServiceImpl service = new DesignPageFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
					for(Long pk : o.getDesignEnfantCles()) {
						DesignPage o2 = new DesignPage();

						o2.setPk(pk);
						o2.setRequeteSite_(requeteSite2);
						futures.add(
							service.patchDesignPageFuture(o2, false, a -> {
								if(a.succeeded()) {
									LOGGER.info(String.format("DesignPage %s rechargé. ", pk));
								} else {
									LOGGER.info(String.format("DesignPage %s a échoué. ", pk));
									gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
								}
							})
						);
					}
				}

				{
					DesignPageFrFRGenApiServiceImpl service = new DesignPageFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
					for(Long pk : o.getDesignParentCles()) {
						DesignPage o2 = new DesignPage();

						o2.setPk(pk);
						o2.setRequeteSite_(requeteSite2);
						futures.add(
							service.patchDesignPageFuture(o2, false, a -> {
								if(a.succeeded()) {
									LOGGER.info(String.format("DesignPage %s rechargé. ", pk));
								} else {
									LOGGER.info(String.format("DesignPage %s a échoué. ", pk));
									gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
								}
							})
						);
					}
				}

				{
					PartHtmlFrFRGenApiServiceImpl service = new PartHtmlFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
					for(Long pk : o.getPartHtmlCles()) {
						PartHtml o2 = new PartHtml();

						o2.setPk(pk);
						o2.setRequeteSite_(requeteSite2);
						futures.add(
							service.patchPartHtmlFuture(o2, false, a -> {
								if(a.succeeded()) {
									LOGGER.info(String.format("PartHtml %s rechargé. ", pk));
								} else {
									LOGGER.info(String.format("PartHtml %s a échoué. ", pk));
									gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
								}
							})
						);
					}
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						LOGGER.info("Recharger relations a réussi. ");
						DesignPageFrFRGenApiServiceImpl service = new DesignPageFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
						List<Future> futures2 = new ArrayList<>();
						for(DesignPage o2 : listeRecherche.getList()) {
							futures2.add(
								service.patchDesignPageFuture(o2, false, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("DesignPage %s rechargé. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("DesignPage %s a échoué. ", o2.getPk()));
										gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Recharger DesignPage a réussi. ");
								gestionnaireEvenements.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Recharger relations a échoué. ", b.cause());
								erreurDesignPage(requeteSite2, gestionnaireEvenements, b);
							}
						});
					} else {
						LOGGER.error("Recharger relations a échoué. ", a.cause());
						erreurDesignPage(requeteSite2, gestionnaireEvenements, a);
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
