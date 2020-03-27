package org.computate.scolaire.frFR.html.part;

import org.computate.scolaire.frFR.inscription.design.DesignInscriptionFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.inscription.design.DesignInscription;
import org.computate.scolaire.frFR.design.DesignPageFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.design.DesignPage;
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
 * NomCanonique.enUS: org.computate.scolaire.enUS.html.part.HtmlPartEnUSGenApiServiceImpl
 **/
public class PartHtmlFrFRGenApiServiceImpl implements PartHtmlFrFRGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(PartHtmlFrFRGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "PartHtmlFrFRApiServiceImpl";

	protected SiteContexteFrFR siteContexte;

	public PartHtmlFrFRGenApiServiceImpl(SiteContexteFrFR siteContexte) {
		this.siteContexte = siteContexte;
	}

	// POST //

	@Override
	public void postPartHtml(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPartHtml(siteContexte, operationRequete, body);

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

			sqlPartHtml(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurPartHtml(requeteSite, b -> {
						if(b.succeeded()) {
							RequeteApi requeteApi = new RequeteApi();
							requeteApi.setRows(1);
							requeteApi.setNumFound(1L);
							requeteApi.initLoinRequeteApi(requeteSite);
							requeteSite.setRequeteApi_(requeteApi);
							postPartHtmlFuture(requeteSite, c -> {
								if(c.succeeded()) {
									PartHtml partHtml = c.result();
									requeteApiPartHtml(partHtml);
									postPartHtmlReponse(partHtml, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postPartHtml a réussi. "));
										} else {
											LOGGER.error(String.format("postPartHtml a échoué. ", d.cause()));
											erreurPartHtml(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurPartHtml(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurPartHtml(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurPartHtml(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurPartHtml(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public Future<PartHtml> postPartHtmlFuture(RequeteSiteFrFR requeteSite, Handler<AsyncResult<PartHtml>> gestionnaireEvenements) {
		Promise<PartHtml> promise = Promise.promise();
		try {
			creerPartHtml(requeteSite, a -> {
				if(a.succeeded()) {
					PartHtml partHtml = a.result();
					sqlPOSTPartHtml(partHtml, b -> {
						if(b.succeeded()) {
							definirIndexerPartHtml(partHtml, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(partHtml));
									promise.complete(partHtml);
								} else {
									erreurPartHtml(requeteSite, null, c);
								}
							});
						} else {
							erreurPartHtml(requeteSite, null, b);
						}
					});
				} else {
					erreurPartHtml(requeteSite, null, a);
				}
			});
		} catch(Exception e) {
			erreurPartHtml(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTPartHtml(PartHtml o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
					case "designInscriptionCle":
						postSql.append(SiteContexteFrFR.SQL_addA);
						postSqlParams.addAll(Arrays.asList("designInscriptionCle", pk, "partHtmlCles", Long.parseLong(jsonObject.getString(entiteVar))));
						break;
					case "designPageCle":
						postSql.append(SiteContexteFrFR.SQL_addA);
						postSqlParams.addAll(Arrays.asList("designPageCle", pk, "partHtmlCles", Long.parseLong(jsonObject.getString(entiteVar))));
						break;
					case "htmlLien":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlLien", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlElement":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlElement", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlId":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlId", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlClasses":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlClasses", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlStyle":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlStyle", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlAvant":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlAvant", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlApres":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlApres", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlTexte":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlTexte", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlVar":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlVar", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlVarSpan":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlVarSpan", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlVarForm":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlVarForm", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlVarInput":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlVarInput", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlVarForEach":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlVarForEach", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlExclure":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlExclure", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "pdfExclure":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("pdfExclure", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "tri1":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri1", jsonObject.getString(entiteVar), pk));
						break;
					case "tri2":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri2", jsonObject.getString(entiteVar), pk));
						break;
					case "tri3":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri3", jsonObject.getString(entiteVar), pk));
						break;
					case "tri4":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri4", jsonObject.getString(entiteVar), pk));
						break;
					case "tri5":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri5", jsonObject.getString(entiteVar), pk));
						break;
					case "tri6":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri6", jsonObject.getString(entiteVar), pk));
						break;
					case "tri7":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri7", jsonObject.getString(entiteVar), pk));
						break;
					case "tri8":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri8", jsonObject.getString(entiteVar), pk));
						break;
					case "tri9":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri9", jsonObject.getString(entiteVar), pk));
						break;
					case "tri10":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri10", jsonObject.getString(entiteVar), pk));
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

	public void postPartHtmlReponse(PartHtml partHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = partHtml.getRequeteSite_();
		reponse200POSTPartHtml(partHtml, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								RequeteApi requeteApi = requeteApiPartHtml(partHtml);
								partHtml.requeteApiPartHtml();
								requeteSite.getVertx().eventBus().publish("websocketPartHtml", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurPartHtml(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurPartHtml(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurPartHtml(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200POSTPartHtml(PartHtml o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			JsonObject json = JsonObject.mapFrom(o);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUT //

	@Override
	public void putPartHtml(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPartHtml(siteContexte, operationRequete, body);

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

			sqlPartHtml(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurPartHtml(requeteSite, b -> {
						if(b.succeeded()) {
							RequeteApi requeteApi = new RequeteApi();
							requeteApi.setRows(1);
							requeteApi.setNumFound(1L);
							requeteApi.initLoinRequeteApi(requeteSite);
							requeteSite.setRequeteApi_(requeteApi);
							SQLConnection connexionSql = requeteSite.getConnexionSql();
							connexionSql.close(c -> {
								if(c.succeeded()) {
									recherchePartHtml(requeteSite, false, true, null, d -> {
										if(d.succeeded()) {
											ListeRecherche<PartHtml> listePartHtml = d.result();
											WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
											executeurTravailleur.executeBlocking(
												blockingCodeHandler -> {
													sqlPartHtml(requeteSite, e -> {
														if(e.succeeded()) {
															try {
																listePUTPartHtml(requeteApi, listePartHtml, f -> {
																	if(f.succeeded()) {
																		putPartHtmlReponse(listePartHtml, g -> {
																			if(g.succeeded()) {
																				gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("putPartHtml a réussi. "));
																			} else {
																				LOGGER.error(String.format("putPartHtml a échoué. ", g.cause()));
																				erreurPartHtml(requeteSite, gestionnaireEvenements, d);
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
											erreurPartHtml(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurPartHtml(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurPartHtml(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurPartHtml(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurPartHtml(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTPartHtml(RequeteApi requeteApi, ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		if(jsonArray.size() == 0) {
			listePartHtml.getList().forEach(o -> {
				futures.add(
					putPartHtmlFuture(requeteSite, JsonObject.mapFrom(o), a -> {
						if(a.succeeded()) {
							PartHtml partHtml = a.result();
							requeteApiPartHtml(partHtml);
						} else {
							erreurPartHtml(requeteSite, gestionnaireEvenements, a);
						}
					})
				);
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listePartHtml.size());
					if(listePartHtml.next()) {
						requeteSite.getVertx().eventBus().publish("websocketPartHtml", JsonObject.mapFrom(requeteApi).toString());
						listePUTPartHtml(requeteApi, listePartHtml, gestionnaireEvenements);
					} else {
						reponse200PUTPartHtml(listePartHtml, gestionnaireEvenements);
					}
				} else {
					erreurPartHtml(listePartHtml.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} else {
			jsonArray.forEach(o -> {
				JsonObject jsonObject = (JsonObject)o;
				futures.add(
					putPartHtmlFuture(requeteSite, jsonObject, a -> {
						if(a.succeeded()) {
							PartHtml partHtml = a.result();
							requeteApiPartHtml(partHtml);
						} else {
							erreurPartHtml(requeteSite, gestionnaireEvenements, a);
						}
					})
				);
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					requeteApi.setNumPATCH(requeteApi.getNumPATCH() + jsonArray.size());
					reponse200PUTPartHtml(listePartHtml, gestionnaireEvenements);
				} else {
					erreurPartHtml(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		}
	}

	public Future<PartHtml> putPartHtmlFuture(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Handler<AsyncResult<PartHtml>> gestionnaireEvenements) {
		Promise<PartHtml> promise = Promise.promise();
		try {

			jsonObject.put("sauvegardes", Optional.ofNullable(jsonObject.getJsonArray("sauvegardes")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("sauvegardes").add(o.getKey());

			});
			creerPartHtml(requeteSite, a -> {
				if(a.succeeded()) {
					PartHtml partHtml = a.result();
					sqlPUTPartHtml(partHtml, jsonObject, b -> {
						if(b.succeeded()) {
							definirPartHtml(partHtml, c -> {
								if(c.succeeded()) {
									attribuerPartHtml(partHtml, d -> {
										if(d.succeeded()) {
											indexerPartHtml(partHtml, e -> {
												if(e.succeeded()) {
													gestionnaireEvenements.handle(Future.succeededFuture(partHtml));
													promise.complete(partHtml);
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
			erreurPartHtml(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTPartHtml(PartHtml o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			StringBuilder postSql = new StringBuilder();
			List<Object> postSqlParams = new ArrayList<Object>();

			if(jsonObject != null) {
				JsonArray entiteVars = jsonObject.getJsonArray("sauvegardes");
				for(Integer i = 0; i < entiteVars.size(); i++) {
					String entiteVar = entiteVars.getString(i);
					switch(entiteVar) {
					case "designInscriptionCle":
						postSql.append(SiteContexteFrFR.SQL_addA);
						postSqlParams.addAll(Arrays.asList("designInscriptionCle", pk, "partHtmlCles", Long.parseLong(jsonObject.getString(entiteVar))));
						break;
					case "designPageCle":
						postSql.append(SiteContexteFrFR.SQL_addA);
						postSqlParams.addAll(Arrays.asList("designPageCle", pk, "partHtmlCles", Long.parseLong(jsonObject.getString(entiteVar))));
						break;
					case "htmlLien":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlLien", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlElement":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlElement", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlId":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlId", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlClasses":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlClasses", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlStyle":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlStyle", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlAvant":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlAvant", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlApres":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlApres", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlTexte":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlTexte", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlVar":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlVar", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlVarSpan":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlVarSpan", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlVarForm":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlVarForm", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlVarInput":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlVarInput", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlVarForEach":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlVarForEach", jsonObject.getString(entiteVar), pk));
						break;
					case "htmlExclure":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlExclure", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "pdfExclure":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("pdfExclure", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "tri1":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri1", jsonObject.getString(entiteVar), pk));
						break;
					case "tri2":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri2", jsonObject.getString(entiteVar), pk));
						break;
					case "tri3":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri3", jsonObject.getString(entiteVar), pk));
						break;
					case "tri4":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri4", jsonObject.getString(entiteVar), pk));
						break;
					case "tri5":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri5", jsonObject.getString(entiteVar), pk));
						break;
					case "tri6":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri6", jsonObject.getString(entiteVar), pk));
						break;
					case "tri7":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri7", jsonObject.getString(entiteVar), pk));
						break;
					case "tri8":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri8", jsonObject.getString(entiteVar), pk));
						break;
					case "tri9":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri9", jsonObject.getString(entiteVar), pk));
						break;
					case "tri10":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("tri10", jsonObject.getString(entiteVar), pk));
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

	public void putPartHtmlReponse(ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
		reponse200PUTPartHtml(listePartHtml, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								RequeteApi requeteApi = requeteSite.getRequeteApi_();
								requeteSite.getVertx().eventBus().publish("websocketPartHtml", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurPartHtml(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurPartHtml(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurPartHtml(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTPartHtml(ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(requeteApi).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchPartHtml(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPartHtml(siteContexte, operationRequete, body);

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

			sqlPartHtml(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurPartHtml(requeteSite, b -> {
						if(b.succeeded()) {
							RequeteApi requeteApi = new RequeteApi();
							requeteApi.setRows(1);
							requeteApi.setNumFound(1L);
							requeteApi.initLoinRequeteApi(requeteSite);
							requeteSite.setRequeteApi_(requeteApi);
							SQLConnection connexionSql = requeteSite.getConnexionSql();
							connexionSql.close(c -> {
								if(c.succeeded()) {
									recherchePartHtml(requeteSite, false, true, null, d -> {
										if(d.succeeded()) {
											ListeRecherche<PartHtml> listePartHtml = d.result();
											SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listePartHtml.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
											Date date = null;
											if(facets != null)
												date = (Date)facets.get("max_modifie");
											String dt;
											if(date == null)
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
											else
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
											listePartHtml.addFilterQuery(String.format("modifie_indexed_date:[* TO %s]", dt));

											if(listePartHtml.size() == 1) {
												PartHtml o = listePartHtml.get(0);
												requeteApi.setPk(o.getPk());
												requeteApi.setOriginal(o);
												requeteApiPartHtml(o);
											o.requeteApiPartHtml();
											}
											WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
											executeurTravailleur.executeBlocking(
												blockingCodeHandler -> {
													sqlPartHtml(requeteSite, e -> {
														if(e.succeeded()) {
															try {
																listePATCHPartHtml(requeteApi, listePartHtml, dt, f -> {
																	if(f.succeeded()) {
																		patchPartHtmlReponse(listePartHtml, g -> {
																			if(g.succeeded()) {
																				gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("patchPartHtml a réussi. "));
																			} else {
																				LOGGER.error(String.format("patchPartHtml a échoué. ", g.cause()));
																				erreurPartHtml(requeteSite, gestionnaireEvenements, d);
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
											erreurPartHtml(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurPartHtml(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurPartHtml(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurPartHtml(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurPartHtml(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePATCHPartHtml(RequeteApi requeteApi, ListeRecherche<PartHtml> listePartHtml, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
		listePartHtml.getList().forEach(o -> {
			futures.add(
				patchPartHtmlFuture(o, a -> {
					if(a.succeeded()) {
							PartHtml partHtml = a.result();
							requeteApiPartHtml(partHtml);
					} else {
						erreurPartHtml(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listePartHtml.size());
				if(listePartHtml.next(dt)) {
					requeteSite.getVertx().eventBus().publish("websocketPartHtml", JsonObject.mapFrom(requeteApi).toString());
					listePATCHPartHtml(requeteApi, listePartHtml, dt, gestionnaireEvenements);
				} else {
					reponse200PATCHPartHtml(listePartHtml, gestionnaireEvenements);
				}
			} else {
				erreurPartHtml(listePartHtml.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<PartHtml> patchPartHtmlFuture(PartHtml o, Handler<AsyncResult<PartHtml>> gestionnaireEvenements) {
		Promise<PartHtml> promise = Promise.promise();
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			sqlPATCHPartHtml(o, a -> {
				if(a.succeeded()) {
					PartHtml partHtml = a.result();
					definirPartHtml(partHtml, b -> {
						if(b.succeeded()) {
							attribuerPartHtml(partHtml, c -> {
								if(c.succeeded()) {
									indexerPartHtml(partHtml, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(partHtml));
											promise.complete(partHtml);
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
			erreurPartHtml(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHPartHtml(PartHtml o, Handler<AsyncResult<PartHtml>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject requeteJson = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = requeteJson.fieldNames();
			PartHtml o2 = new PartHtml();

			patchSql.append(SiteContexteFrFR.SQL_modifier);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.frFR.html.part.PartHtml"));
			for(String methodeNom : methodeNoms) {
				switch(methodeNom) {
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
					case "setDesignInscriptionCle":
						o2.setDesignInscriptionCle(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexteFrFR.SQL_setA1);
						patchSqlParams.addAll(Arrays.asList("designInscriptionCle", pk, "partHtmlCles", o2.getDesignInscriptionCle()));
						break;
					case "removeDesignInscriptionCle":
						o2.setDesignInscriptionCle(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexteFrFR.SQL_removeA);
						patchSqlParams.addAll(Arrays.asList("designInscriptionCle", pk, "partHtmlCles", o2.getDesignInscriptionCle()));
						break;
					case "setDesignPageCle":
						o2.setDesignPageCle(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexteFrFR.SQL_setA1);
						patchSqlParams.addAll(Arrays.asList("designPageCle", pk, "partHtmlCles", o2.getDesignPageCle()));
						break;
					case "removeDesignPageCle":
						o2.setDesignPageCle(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexteFrFR.SQL_removeA);
						patchSqlParams.addAll(Arrays.asList("designPageCle", pk, "partHtmlCles", o2.getDesignPageCle()));
						break;
					case "setHtmlLien":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlLien"));
						} else {
							o2.setHtmlLien(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlLien", o2.jsonHtmlLien(), pk));
						}
						break;
					case "setHtmlElement":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlElement"));
						} else {
							o2.setHtmlElement(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlElement", o2.jsonHtmlElement(), pk));
						}
						break;
					case "setHtmlId":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlId"));
						} else {
							o2.setHtmlId(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlId", o2.jsonHtmlId(), pk));
						}
						break;
					case "setHtmlClasses":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlClasses"));
						} else {
							o2.setHtmlClasses(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlClasses", o2.jsonHtmlClasses(), pk));
						}
						break;
					case "setHtmlStyle":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlStyle"));
						} else {
							o2.setHtmlStyle(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlStyle", o2.jsonHtmlStyle(), pk));
						}
						break;
					case "setHtmlAvant":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlAvant"));
						} else {
							o2.setHtmlAvant(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlAvant", o2.jsonHtmlAvant(), pk));
						}
						break;
					case "setHtmlApres":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlApres"));
						} else {
							o2.setHtmlApres(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlApres", o2.jsonHtmlApres(), pk));
						}
						break;
					case "setHtmlTexte":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlTexte"));
						} else {
							o2.setHtmlTexte(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlTexte", o2.jsonHtmlTexte(), pk));
						}
						break;
					case "setHtmlVar":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlVar"));
						} else {
							o2.setHtmlVar(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlVar", o2.jsonHtmlVar(), pk));
						}
						break;
					case "setHtmlVarSpan":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlVarSpan"));
						} else {
							o2.setHtmlVarSpan(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlVarSpan", o2.jsonHtmlVarSpan(), pk));
						}
						break;
					case "setHtmlVarForm":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlVarForm"));
						} else {
							o2.setHtmlVarForm(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlVarForm", o2.jsonHtmlVarForm(), pk));
						}
						break;
					case "setHtmlVarInput":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlVarInput"));
						} else {
							o2.setHtmlVarInput(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlVarInput", o2.jsonHtmlVarInput(), pk));
						}
						break;
					case "setHtmlVarForEach":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlVarForEach"));
						} else {
							o2.setHtmlVarForEach(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlVarForEach", o2.jsonHtmlVarForEach(), pk));
						}
						break;
					case "setHtmlExclure":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlExclure"));
						} else {
							o2.setHtmlExclure(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlExclure", o2.jsonHtmlExclure(), pk));
						}
						break;
					case "setPdfExclure":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "pdfExclure"));
						} else {
							o2.setPdfExclure(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("pdfExclure", o2.jsonPdfExclure(), pk));
						}
						break;
					case "setTri1":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "tri1"));
						} else {
							o2.setTri1(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("tri1", o2.jsonTri1(), pk));
						}
						break;
					case "setTri2":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "tri2"));
						} else {
							o2.setTri2(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("tri2", o2.jsonTri2(), pk));
						}
						break;
					case "setTri3":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "tri3"));
						} else {
							o2.setTri3(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("tri3", o2.jsonTri3(), pk));
						}
						break;
					case "setTri4":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "tri4"));
						} else {
							o2.setTri4(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("tri4", o2.jsonTri4(), pk));
						}
						break;
					case "setTri5":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "tri5"));
						} else {
							o2.setTri5(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("tri5", o2.jsonTri5(), pk));
						}
						break;
					case "setTri6":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "tri6"));
						} else {
							o2.setTri6(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("tri6", o2.jsonTri6(), pk));
						}
						break;
					case "setTri7":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "tri7"));
						} else {
							o2.setTri7(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("tri7", o2.jsonTri7(), pk));
						}
						break;
					case "setTri8":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "tri8"));
						} else {
							o2.setTri8(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("tri8", o2.jsonTri8(), pk));
						}
						break;
					case "setTri9":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "tri9"));
						} else {
							o2.setTri9(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("tri9", o2.jsonTri9(), pk));
						}
						break;
					case "setTri10":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "tri10"));
						} else {
							o2.setTri10(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("tri10", o2.jsonTri10(), pk));
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
					PartHtml o3 = new PartHtml();
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

	public void patchPartHtmlReponse(ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
		reponse200PATCHPartHtml(listePartHtml, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								RequeteApi requeteApi = requeteSite.getRequeteApi_();
								requeteSite.getVertx().eventBus().publish("websocketPartHtml", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurPartHtml(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurPartHtml(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurPartHtml(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PATCHPartHtml(ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			JsonObject json = JsonObject.mapFrom(requeteApi);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getPartHtml(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPartHtml(siteContexte, operationRequete);

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

			sqlPartHtml(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurPartHtml(requeteSite, b -> {
						if(b.succeeded()) {
							recherchePartHtml(requeteSite, false, true, null, c -> {
								if(c.succeeded()) {
									ListeRecherche<PartHtml> listePartHtml = c.result();
									getPartHtmlReponse(listePartHtml, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getPartHtml a réussi. "));
										} else {
											LOGGER.error(String.format("getPartHtml a échoué. ", d.cause()));
											erreurPartHtml(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurPartHtml(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurPartHtml(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurPartHtml(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurPartHtml(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void getPartHtmlReponse(ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
		reponse200GETPartHtml(listePartHtml, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurPartHtml(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurPartHtml(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurPartHtml(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200GETPartHtml(ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
			SolrDocumentList documentsSolr = listePartHtml.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listePartHtml.getList().stream().findFirst().orElse(null));
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void recherchePartHtml(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPartHtml(siteContexte, operationRequete);

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

			sqlPartHtml(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurPartHtml(requeteSite, b -> {
						if(b.succeeded()) {
							recherchePartHtml(requeteSite, false, true, "/api/part-html", c -> {
								if(c.succeeded()) {
									ListeRecherche<PartHtml> listePartHtml = c.result();
									recherchePartHtmlReponse(listePartHtml, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("recherchePartHtml a réussi. "));
										} else {
											LOGGER.error(String.format("recherchePartHtml a échoué. ", d.cause()));
											erreurPartHtml(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurPartHtml(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurPartHtml(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurPartHtml(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurPartHtml(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void recherchePartHtmlReponse(ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
		reponse200RecherchePartHtml(listePartHtml, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurPartHtml(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurPartHtml(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurPartHtml(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200RecherchePartHtml(ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
			QueryResponse reponseRecherche = listePartHtml.getQueryResponse();
			SolrDocumentList documentsSolr = listePartHtml.getSolrDocumentList();
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
			listePartHtml.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listePartHtml.getFields();
				if(fls.size() > 0) {
					Set<String> fieldNames = new HashSet<String>();
					fieldNames.addAll(json2.fieldNames());
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
	public void pagerecherchePartHtmlId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		pagerecherchePartHtml(operationRequete, gestionnaireEvenements);
	}

	@Override
	public void pagerecherchePartHtml(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPartHtml(siteContexte, operationRequete);

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

			sqlPartHtml(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurPartHtml(requeteSite, b -> {
						if(b.succeeded()) {
							recherchePartHtml(requeteSite, false, true, "/part-html", c -> {
								if(c.succeeded()) {
									ListeRecherche<PartHtml> listePartHtml = c.result();
									pagerecherchePartHtmlReponse(listePartHtml, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("pagerecherchePartHtml a réussi. "));
										} else {
											LOGGER.error(String.format("pagerecherchePartHtml a échoué. ", d.cause()));
											erreurPartHtml(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurPartHtml(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurPartHtml(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurPartHtml(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurPartHtml(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void pagerecherchePartHtmlReponse(ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
		reponse200PageRecherchePartHtml(listePartHtml, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurPartHtml(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurPartHtml(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurPartHtml(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PageRecherchePartHtml(ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(listePartHtml.getRequeteSite_(), buffer);
			PartHtmlPage page = new PartHtmlPage();
			SolrDocument pageDocumentSolr = new SolrDocument();
			CaseInsensitiveHeaders requeteEnTetes = new CaseInsensitiveHeaders();
			requeteSite.setRequeteEnTetes(requeteEnTetes);

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/part-html");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			if(listePartHtml.size() == 1)
				requeteSite.setRequetePk(listePartHtml.get(0).getPk());
			requeteSite.setW(w);
			page.setListePartHtml(listePartHtml);
			page.setRequeteSite_(requeteSite);
			page.initLoinPartHtmlPage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requeteEnTetes)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<PartHtml> definirIndexerPartHtml(PartHtml partHtml, Handler<AsyncResult<PartHtml>> gestionnaireEvenements) {
		Promise<PartHtml> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = partHtml.getRequeteSite_();
		definirPartHtml(partHtml, c -> {
			if(c.succeeded()) {
				attribuerPartHtml(partHtml, d -> {
					if(d.succeeded()) {
						indexerPartHtml(partHtml, e -> {
							if(e.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(partHtml));
								promise.complete(partHtml);
							} else {
								erreurPartHtml(requeteSite, null, e);
							}
						});
					} else {
						erreurPartHtml(requeteSite, null, d);
					}
				});
			} else {
				erreurPartHtml(requeteSite, null, c);
			}
		});
		return promise.future();
	}

	public void creerPartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<PartHtml>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_creer
					, new JsonArray(Arrays.asList(PartHtml.class.getCanonicalName(), utilisateurId))
					, creerAsync
			-> {
				JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = creerLigne.getLong(0);
				PartHtml o = new PartHtml();
				o.setPk(pk);
				o.setRequeteSite_(requeteSite);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public RequeteApi requeteApiPartHtml(PartHtml o) {
		RequeteApi requeteApi = o.getRequeteSite_().getRequeteApi_();
		if(requeteApi != null) {
			List<Long> pks = requeteApi.getPks();
			List<String> classes = requeteApi.getClasses();
			if(o.getDesignInscriptionCle() != null) {
				if(!pks.contains(o.getDesignInscriptionCle())) {
					pks.add(o.getDesignInscriptionCle());
					classes.add("DesignInscription");
				}
			}
			if(o.getDesignPageCle() != null) {
				if(!pks.contains(o.getDesignPageCle())) {
					pks.add(o.getDesignPageCle());
					classes.add("DesignPage");
				}
			}
		}
		return requeteApi;
	}

	public void erreurPartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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

	public void sqlPartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public RequeteSiteFrFR genererRequeteSiteFrFRPourPartHtml(SiteContexteFrFR siteContexte, OperationRequest operationRequete) {
		return genererRequeteSiteFrFRPourPartHtml(siteContexte, operationRequete, null);
	}

	public RequeteSiteFrFR genererRequeteSiteFrFRPourPartHtml(SiteContexteFrFR siteContexte, OperationRequest operationRequete, JsonObject body) {
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

	public void utilisateurPartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
								utilisateurPartHtmlDefinir(requeteSite, jsonObject, false);

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
										utilisateurService.sqlPOSTUtilisateurSite(utilisateurSite, c -> {
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
																		erreurPartHtml(requeteSite, gestionnaireEvenements, f);
																	}
																});
															} else {
																erreurPartHtml(requeteSite, gestionnaireEvenements, e);
															}
														});
													} else {
														erreurPartHtml(requeteSite, gestionnaireEvenements, d);
													}
												});
											} else {
												erreurPartHtml(requeteSite, gestionnaireEvenements, c);
											}
										});
									} else {
										erreurPartHtml(requeteSite, gestionnaireEvenements, b);
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
								Boolean definir = utilisateurPartHtmlDefinir(requeteSite, jsonObject, true);
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

									utilisateurService.sqlPATCHUtilisateurSite(utilisateurSite, c -> {
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
																	erreurPartHtml(requeteSite, gestionnaireEvenements, f);
																}
															});
														} else {
															erreurPartHtml(requeteSite, gestionnaireEvenements, e);
														}
													});
												} else {
													erreurPartHtml(requeteSite, gestionnaireEvenements, d);
												}
											});
										} else {
											erreurPartHtml(requeteSite, gestionnaireEvenements, c);
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

	public Boolean utilisateurPartHtmlDefinir(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void recherchePartHtmlQ(ListeRecherche<PartHtml> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		listeRecherche.setQuery(varIndexe + ":" + ("*".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));
		if(!"*".equals(entiteVar)) {
			listeRecherche.setHighlight(true);
			listeRecherche.setHighlightSnippets(3);
			listeRecherche.addHighlightField(varIndexe);
			listeRecherche.setParam("hl.encoder", "html");
		}
	}

	public void recherchePartHtmlFq(ListeRecherche<PartHtml> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
	}

	public void recherchePartHtmlSort(ListeRecherche<PartHtml> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurIndexe));
	}

	public void recherchePartHtmlRows(ListeRecherche<PartHtml> listeRecherche, Integer valeurRows) {
		listeRecherche.setRows(valeurRows);
	}

	public void recherchePartHtmlStart(ListeRecherche<PartHtml> listeRecherche, Integer valeurStart) {
		listeRecherche.setStart(valeurStart);
	}

	public void recherchePartHtmlVar(ListeRecherche<PartHtml> listeRecherche, String var, String valeur) {
		listeRecherche.getRequeteSite_().getRequeteVars().put(var, valeur);
	}

	public void recherchePartHtml(RequeteSiteFrFR requeteSite, Boolean peupler, Boolean stocker, String classeApiUriMethode, Handler<AsyncResult<ListeRecherche<PartHtml>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<PartHtml> listeRecherche = new ListeRecherche<PartHtml>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(PartHtml.class);
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
								varIndexe = "*".equals(entiteVar) ? entiteVar : PartHtml.varRecherchePartHtml(entiteVar);
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								valeurIndexe = StringUtils.isEmpty(valeurIndexe) ? "*" : valeurIndexe;
								recherchePartHtmlQ(listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "fq":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								varIndexe = PartHtml.varIndexePartHtml(entiteVar);
								recherchePartHtmlFq(listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "sort":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
								valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
								varIndexe = PartHtml.varIndexePartHtml(entiteVar);
								recherchePartHtmlSort(listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "start":
								valeurStart = (Integer)paramObjet;
								recherchePartHtmlStart(listeRecherche, valeurStart);
								break;
							case "rows":
								valeurRows = (Integer)paramObjet;
								recherchePartHtmlRows(listeRecherche, valeurRows);
								break;
							case "var":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								recherchePartHtmlVar(listeRecherche, entiteVar, valeurIndexe);
								break;
						}
					}
				} catch(Exception e) {
					gestionnaireEvenements.handle(Future.failedFuture(e));
				}
			});
			if(listeRecherche.getSorts().size() == 0) {
				listeRecherche.addSort("tri1_indexed_double", ORDER.asc);
				listeRecherche.addSort("tri2_indexed_double", ORDER.asc);
				listeRecherche.addSort("tri3_indexed_double", ORDER.asc);
				listeRecherche.addSort("tri4_indexed_double", ORDER.asc);
				listeRecherche.addSort("tri5_indexed_double", ORDER.asc);
				listeRecherche.addSort("tri6_indexed_double", ORDER.asc);
				listeRecherche.addSort("tri7_indexed_double", ORDER.asc);
				listeRecherche.addSort("tri8_indexed_double", ORDER.asc);
				listeRecherche.addSort("tri9_indexed_double", ORDER.asc);
				listeRecherche.addSort("tri10_indexed_double", ORDER.asc);
			}
			listeRecherche.initLoinPourClasse(requeteSite);
			gestionnaireEvenements.handle(Future.succeededFuture(listeRecherche));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void definirPartHtml(PartHtml o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void attribuerPartHtml(PartHtml o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void indexerPartHtml(PartHtml o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			o.initLoinPourClasse(requeteSite);
			o.indexerPourClasse();
			if(BooleanUtils.isFalse(Optional.ofNullable(requeteSite.getRequeteApi_()).map(RequeteApi::getEmpty).orElse(null))) {
				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourPartHtml(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());
				ListeRecherche<PartHtml> listeRecherche = new ListeRecherche<PartHtml>();
				listeRecherche.setPeupler(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(PartHtml.class);
				listeRecherche.addFilterQuery("modifie_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(requeteSite.getRequeteApi_().getCree().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				listeRecherche.add("json.facet", "{designInscriptionCle:{terms:{field:designInscriptionCle_indexed_longs, limit:1000}}}");
				listeRecherche.add("json.facet", "{designPageCle:{terms:{field:designPageCle_indexed_longs, limit:1000}}}");
				listeRecherche.setRows(1000);
				listeRecherche.initLoinListeRecherche(requeteSite2);
				List<Future> futures = new ArrayList<>();

				{
					DesignInscription o2 = new DesignInscription();
					DesignInscriptionFrFRGenApiServiceImpl service = new DesignInscriptionFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
					Long pk = o.getDesignInscriptionCle();

					if(pk != null) {
						o2.setPk(pk);
						o2.setRequeteSite_(requeteSite2);
						futures.add(
							service.patchDesignInscriptionFuture(o2, a -> {
								if(a.succeeded()) {
									LOGGER.info(String.format("DesignInscription %s rechargé. ", pk));
								} else {
									LOGGER.info(String.format("DesignInscription %s a échoué. ", pk));
									gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
								}
							})
						);
					}
				}

				{
					DesignPage o2 = new DesignPage();
					DesignPageFrFRGenApiServiceImpl service = new DesignPageFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
					Long pk = o.getDesignPageCle();

					if(pk != null) {
						o2.setPk(pk);
						o2.setRequeteSite_(requeteSite2);
						futures.add(
							service.patchDesignPageFuture(o2, a -> {
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

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						LOGGER.info("Recharger relations a réussi. ");
						PartHtmlFrFRGenApiServiceImpl service = new PartHtmlFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
						List<Future> futures2 = new ArrayList<>();
						for(PartHtml o2 : listeRecherche.getList()) {
							futures2.add(
								service.patchPartHtmlFuture(o2, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("PartHtml %s rechargé. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("PartHtml %s a échoué. ", o2.getPk()));
										gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Recharger PartHtml a réussi. ");
								gestionnaireEvenements.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Recharger relations a échoué. ", b.cause());
								erreurPartHtml(requeteSite2, gestionnaireEvenements, b);
							}
						});
					} else {
						LOGGER.error("Recharger relations a échoué. ", a.cause());
						erreurPartHtml(requeteSite2, gestionnaireEvenements, a);
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
