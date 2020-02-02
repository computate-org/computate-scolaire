package org.computate.scolaire.frFR.saison;

import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.computate.scolaire.frFR.recherche.ResultatRecherche;
import io.vertx.core.WorkerExecutor;
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
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;


/**
 * Traduire: false
 **/
public class SaisonScolaireFrFRGenApiServiceImpl implements SaisonScolaireFrFRGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SaisonScolaireFrFRGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SaisonScolaireFrFRApiServiceImpl";

	protected SiteContexteFrFR siteContexte;

	public SaisonScolaireFrFRGenApiServiceImpl(SiteContexteFrFR siteContexte) {
		this.siteContexte = siteContexte;
		SaisonScolaireFrFRGenApiService service = SaisonScolaireFrFRGenApiService.creerProxy(siteContexte.getVertx(), SERVICE_ADDRESS);
	}

	// POST //

	@Override
	public void postSaisonScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete, body);
			sqlSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					creerSaisonScolaire(requeteSite, b -> {
						if(b.succeeded()) {
						RequeteApi requeteApi = new RequeteApi();
							requeteApi.setRows(1);
							requeteApi.setNumFound(1L);
							requeteApi.initLoinRequeteApi(requeteSite);
							requeteSite.setRequeteApi_(requeteApi);
							SaisonScolaire saisonScolaire = b.result();
							sqlPOSTSaisonScolaire(saisonScolaire, c -> {
								if(c.succeeded()) {
									definirSaisonScolaire(saisonScolaire, d -> {
										if(d.succeeded()) {
											attribuerSaisonScolaire(saisonScolaire, e -> {
												if(e.succeeded()) {
													indexerSaisonScolaire(saisonScolaire, f -> {
														if(f.succeeded()) {
															reponse200POSTSaisonScolaire(saisonScolaire, g -> {
																if(f.succeeded()) {
																	SQLConnection connexionSql = requeteSite.getConnexionSql();
																	connexionSql.commit(h -> {
																		if(a.succeeded()) {
																			connexionSql.close(i -> {
																				if(a.succeeded()) {
																					requeteSite.getVertx().eventBus().publish("websocketSaisonScolaire", JsonObject.mapFrom(requeteApi).toString());
																					gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																				} else {
																					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, i);
																				}
																			});
																		} else {
																			erreurSaisonScolaire(requeteSite, gestionnaireEvenements, h);
																		}
																	});
																} else {
																	erreurSaisonScolaire(requeteSite, gestionnaireEvenements, g);
																}
															});
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
					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void sqlPOSTSaisonScolaire(SaisonScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
					case "anneeCle":
						postSql.append(SiteContexteFrFR.SQL_addA);
						postSqlParams.addAll(Arrays.asList("anneeCle", pk, "saisonCles", Long.parseLong(jsonObject.getString(entiteVar))));
						break;
					case "sessionCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							postSql.append(SiteContexteFrFR.SQL_addA);
							postSqlParams.addAll(Arrays.asList("saisonCle", l, "sessionCles", pk));
						}
						break;
					case "saisonJourDebut":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("saisonJourDebut", jsonObject.getString(entiteVar), pk));
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

	public void reponse200POSTSaisonScolaire(SaisonScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			JsonObject json = JsonObject.mapFrom(o);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUT //

	@Override
	public void putSaisonScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete, body);
			sqlSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSaisonScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							SQLConnection connexionSql = requeteSite.getConnexionSql();
							connexionSql.close(c -> {
								if(c.succeeded()) {
									rechercheSaisonScolaire(requeteSite, false, true, null, d -> {
										if(d.succeeded()) {
											ListeRecherche<SaisonScolaire> listeSaisonScolaire = d.result();
											RequeteApi requeteApi = new RequeteApi();
											requeteApi.setRows(listeSaisonScolaire.getRows());
											requeteApi.setNumFound(Optional.ofNullable(listeSaisonScolaire.getQueryResponse()).map(QueryResponse::getResults).map(SolrDocumentList::getNumFound).orElse(new Long(listeSaisonScolaire.size())));
											requeteApi.initLoinRequeteApi(requeteSite);
											requeteSite.setRequeteApi_(requeteApi);
											WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
											executeurTravailleur.executeBlocking(
												blockingCodeHandler -> {
													sqlSaisonScolaire(requeteSite, e -> {
														if(e.succeeded()) {
															try {
																listePUTSaisonScolaire(requeteApi, listeSaisonScolaire, f -> {
																	if(f.succeeded()) {
																		SQLConnection connexionSql2 = requeteSite.getConnexionSql();
																		if(connexionSql2 == null) {
																			blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																		} else {
																			connexionSql2.commit(g -> {
																				if(f.succeeded()) {
																					connexionSql2.close(h -> {
																						if(g.succeeded()) {
																							blockingCodeHandler.handle(Future.succeededFuture(h.result()));
																						} else {
																							blockingCodeHandler.handle(Future.failedFuture(h.cause()));
																						}
																					});
																				} else {
																					blockingCodeHandler.handle(Future.failedFuture(g.cause()));
																				}
																			});
																		}
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
											reponse200PUTSaisonScolaire(requeteApi, gestionnaireEvenements);
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
					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void listePUTSaisonScolaire(RequeteApi requeteApi, ListeRecherche<SaisonScolaire> listeSaisonScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeSaisonScolaire.getRequeteSite_();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		if(jsonArray.size() == 0) {
			listeSaisonScolaire.getList().forEach(o -> {
				futures.add(
					futurePUTSaisonScolaire(requeteSite, JsonObject.mapFrom(o), a -> {
						if(a.succeeded()) {
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
						listePUTSaisonScolaire(requeteApi, listeSaisonScolaire, gestionnaireEvenements);
					} else {
						reponse200PUTSaisonScolaire(requeteApi, gestionnaireEvenements);
					}
				} else {
					erreurSaisonScolaire(listeSaisonScolaire.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} else {
			jsonArray.forEach(o -> {
				JsonObject jsonObject = (JsonObject)o;
				futures.add(
					futurePUTSaisonScolaire(requeteSite, jsonObject, a -> {
						if(a.succeeded()) {
						} else {
							erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
						}
					})
				);
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					requeteApi.setNumPATCH(requeteApi.getNumPATCH() + jsonArray.size());
					reponse200PUTSaisonScolaire(requeteApi, gestionnaireEvenements);
				} else {
					erreurSaisonScolaire(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		}
	}

	public Future<SaisonScolaire> futurePUTSaisonScolaire(RequeteSiteFrFR requeteSite, JsonObject jsonObject,  Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		jsonObject.put("sauvegardes", Optional.ofNullable(jsonObject.getJsonArray("sauvegardes")).orElse(new JsonArray()));
		JsonObject jsonPatch = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
		jsonPatch.stream().forEach(o -> {
			jsonObject.put(o.getKey(), o.getValue());
			jsonObject.getJsonArray("sauvegardes").add(o.getKey());
		});
		Future<SaisonScolaire> future = Future.future();
		try {
			creerSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					SaisonScolaire saisonScolaire = a.result();
					sqlPUTSaisonScolaire(saisonScolaire, jsonObject, b -> {
						if(b.succeeded()) {
							definirSaisonScolaire(saisonScolaire, c -> {
								if(c.succeeded()) {
									attribuerSaisonScolaire(saisonScolaire, d -> {
										if(d.succeeded()) {
											indexerSaisonScolaire(saisonScolaire, e -> {
												if(e.succeeded()) {
													requeteApiSaisonScolaire(saisonScolaire);
													saisonScolaire.requeteApiSaisonScolaire();
													future.complete(saisonScolaire);
													gestionnaireEvenements.handle(Future.succeededFuture(e.result()));
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
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public void remplacerPUTSaisonScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<SaisonScolaire>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			Long pk = requeteSite.getRequetePk();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_vider
					, new JsonArray(Arrays.asList(pk, SaisonScolaire.class.getCanonicalName(), pk, pk, pk))
					, remplacerAsync
			-> {
				SaisonScolaire o = new SaisonScolaire();
				o.setPk(pk);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlPUTSaisonScolaire(SaisonScolaire o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
					case "anneeCle":
						postSql.append(SiteContexteFrFR.SQL_addA);
						postSqlParams.addAll(Arrays.asList("anneeCle", pk, "saisonCles", Long.parseLong(jsonObject.getString(entiteVar))));
						break;
					case "sessionCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							postSql.append(SiteContexteFrFR.SQL_addA);
							postSqlParams.addAll(Arrays.asList("saisonCle", l, "sessionCles", pk));
						}
						break;
					case "saisonJourDebut":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("saisonJourDebut", jsonObject.getString(entiteVar), pk));
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

	public void reponse200PUTSaisonScolaire(RequeteApi requeteApi, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(JsonObject.mapFrom(requeteApi))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSaisonScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete, body);
			sqlSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSaisonScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							SQLConnection connexionSql = requeteSite.getConnexionSql();
							connexionSql.close(c -> {
								if(c.succeeded()) {
									rechercheSaisonScolaire(requeteSite, false, true, null, d -> {
										if(d.succeeded()) {
											ListeRecherche<SaisonScolaire> listeSaisonScolaire = d.result();
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

											RequeteApi requeteApi = new RequeteApi();
											requeteApi.setRows(listeSaisonScolaire.getRows());
											requeteApi.setNumFound(Optional.ofNullable(listeSaisonScolaire.getQueryResponse()).map(QueryResponse::getResults).map(SolrDocumentList::getNumFound).orElse(new Long(listeSaisonScolaire.size())));
											requeteApi.initLoinRequeteApi(requeteSite);
											requeteSite.setRequeteApi_(requeteApi);
											if(listeSaisonScolaire.size() == 1) {
												SaisonScolaire o = listeSaisonScolaire.get(0);
												requeteApi.setPk(o.getPk());
												requeteApi.setOriginal(o);
												requeteApiSaisonScolaire(o);
											}
											WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
											executeurTravailleur.executeBlocking(
												blockingCodeHandler -> {
													sqlSaisonScolaire(requeteSite, e -> {
														if(e.succeeded()) {
															try {
																listePATCHSaisonScolaire(requeteApi, listeSaisonScolaire, dt, f -> {
																	if(f.succeeded()) {
																		SQLConnection connexionSql2 = requeteSite.getConnexionSql();
																		if(connexionSql2 == null) {
																			blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																		} else {
																			connexionSql2.commit(g -> {
																				if(f.succeeded()) {
																					connexionSql2.close(h -> {
																						if(g.succeeded()) {
																							blockingCodeHandler.handle(Future.succeededFuture(h.result()));
																						} else {
																							blockingCodeHandler.handle(Future.failedFuture(h.cause()));
																						}
																					});
																				} else {
																					blockingCodeHandler.handle(Future.failedFuture(g.cause()));
																				}
																			});
																		}
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
											reponse200PATCHSaisonScolaire(requeteApi, gestionnaireEvenements);
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
					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void listePATCHSaisonScolaire(RequeteApi requeteApi, ListeRecherche<SaisonScolaire> listeSaisonScolaire, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeSaisonScolaire.getRequeteSite_();
		listeSaisonScolaire.getList().forEach(o -> {
			futures.add(
				futurePATCHSaisonScolaire(o, a -> {
					if(a.succeeded()) {
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
					reponse200PATCHSaisonScolaire(requeteApi, gestionnaireEvenements);
				}
			} else {
				erreurSaisonScolaire(listeSaisonScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<SaisonScolaire> futurePATCHSaisonScolaire(SaisonScolaire o,  Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		Promise<SaisonScolaire> promise = Promise.promise();
		try {
			sqlPATCHSaisonScolaire(o, a -> {
				if(a.succeeded()) {
					SaisonScolaire saisonScolaire = a.result();
					definirSaisonScolaire(saisonScolaire, b -> {
						if(b.succeeded()) {
							attribuerSaisonScolaire(saisonScolaire, c -> {
								if(c.succeeded()) {
									indexerSaisonScolaire(saisonScolaire, d -> {
										if(d.succeeded()) {
											requeteApiSaisonScolaire(saisonScolaire);
											saisonScolaire.requeteApiSaisonScolaire();
											promise.complete(o);
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
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
			return promise.future();
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public void sqlPATCHSaisonScolaire(SaisonScolaire o, Handler<AsyncResult<SaisonScolaire>> gestionnaireEvenements) {
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
						o2.setAnneeCle(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexteFrFR.SQL_setA1);
						patchSqlParams.addAll(Arrays.asList("anneeCle", pk, "saisonCles", o2.getAnneeCle()));
						break;
					case "removeAnneeCle":
						o2.setAnneeCle(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexteFrFR.SQL_removeA);
						patchSqlParams.addAll(Arrays.asList("anneeCle", pk, "saisonCles", o2.getAnneeCle()));
						break;
					case "addSessionCles":
						patchSql.append(SiteContexteFrFR.SQL_addA);
						patchSqlParams.addAll(Arrays.asList("saisonCle", Long.parseLong(requeteJson.getString(methodeNom)), "sessionCles", pk));
						break;
					case "addAllSessionCles":
						JsonArray addAllSessionClesValeurs = requeteJson.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllSessionClesValeurs.size(); i++) {
							patchSql.append(SiteContexteFrFR.SQL_setA2);
							patchSqlParams.addAll(Arrays.asList("saisonCle", addAllSessionClesValeurs.getString(i), "sessionCles", pk));
						}
						break;
					case "setSessionCles":
						JsonArray setSessionClesValeurs = requeteJson.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("saisonCle", Long.parseLong(requeteJson.getString(methodeNom)), "sessionCles", pk));
						for(Integer i = 0; i <  setSessionClesValeurs.size(); i++) {
							patchSql.append(SiteContexteFrFR.SQL_setA2);
							patchSqlParams.addAll(Arrays.asList("saisonCle", setSessionClesValeurs.getString(i), "sessionCles", pk));
						}
						break;
					case "removeSessionCles":
						patchSql.append(SiteContexteFrFR.SQL_removeA);
						patchSqlParams.addAll(Arrays.asList("saisonCle", Long.parseLong(requeteJson.getString(methodeNom)), "sessionCles", pk));
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

	public void reponse200PATCHSaisonScolaire(RequeteApi requeteApi, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = requeteApi.getRequeteSite_();
			JsonObject json = JsonObject.mapFrom(requeteApi);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSaisonScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete);
			rechercheSaisonScolaire(requeteSite, false, true, null, a -> {
				if(a.succeeded()) {
					ListeRecherche<SaisonScolaire> listeSaisonScolaire = a.result();
					reponse200GETSaisonScolaire(listeSaisonScolaire, b -> {
						if(b.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(b.result()));
						} else {
							erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200GETSaisonScolaire(ListeRecherche<SaisonScolaire> listeSaisonScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeSaisonScolaire.getRequeteSite_();
			SolrDocumentList documentsSolr = listeSaisonScolaire.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listeSaisonScolaire.get(0));
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteSaisonScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete);
			sqlSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					rechercheSaisonScolaire(requeteSite, false, true, null, b -> {
						if(b.succeeded()) {
							ListeRecherche<SaisonScolaire> listeSaisonScolaire = b.result();
							supprimerDELETESaisonScolaire(requeteSite, c -> {
								if(c.succeeded()) {
									reponse200DELETESaisonScolaire(requeteSite, d -> {
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
																erreurSaisonScolaire(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
													}
												});
											}
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
					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void supprimerDELETESaisonScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			Long pk = requeteSite.getRequetePk();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_supprimer
					, new JsonArray(Arrays.asList(pk, SaisonScolaire.class.getCanonicalName(), pk, pk, pk, pk))
					, supprimerAsync
			-> {
				gestionnaireEvenements.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200DELETESaisonScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void rechercheSaisonScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete);
			rechercheSaisonScolaire(requeteSite, false, true, null, a -> {
				if(a.succeeded()) {
					ListeRecherche<SaisonScolaire> listeSaisonScolaire = a.result();
					reponse200RechercheSaisonScolaire(listeSaisonScolaire, b -> {
						if(b.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(b.result()));
						} else {
							erreurSaisonScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
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
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
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
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourSaisonScolaire(siteContexte, operationRequete);
			sqlSaisonScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurSaisonScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheSaisonScolaire(requeteSite, false, true, "/saison", c -> {
								if(c.succeeded()) {
									ListeRecherche<SaisonScolaire> listeSaisonScolaire = c.result();
									reponse200PageRechercheSaisonScolaire(listeSaisonScolaire, d -> {
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
																erreurSaisonScolaire(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
													}
												});
											}
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
					erreurSaisonScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurSaisonScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
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

	// Partagé //

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

	public void requeteApiSaisonScolaire(SaisonScolaire o) {
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
						JsonArray utilisateurValeurs = selectCAsync.result().getResults().stream().findFirst().orElse(null);
						if(utilisateurValeurs == null) {
							connexionSql.queryWithParams(
									SiteContexteFrFR.SQL_creer
									, new JsonArray(Arrays.asList("org.computate.scolaire.frFR.utilisateur.UtilisateurSite", utilisateurId))
									, creerAsync
							-> {
								JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
								Long pkUtilisateur = creerLigne.getLong(0);
								UtilisateurSite utilisateurSite = new UtilisateurSite();
								utilisateurSite.setRequeteSite_(requeteSite);
								utilisateurSite.setPk(pkUtilisateur);

								connexionSql.queryWithParams(
										SiteContexteFrFR.SQL_definir
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
										gestionnaireEvenements.handle(Future.failedFuture(new Exception(definirAsync.cause())));
									}
								});
							});
						} else {
							Long pkUtilisateur = utilisateurValeurs.getLong(0);
							UtilisateurSite utilisateurSite = new UtilisateurSite();
								utilisateurSite.setRequeteSite_(requeteSite);
							utilisateurSite.setPk(pkUtilisateur);

							connexionSql.queryWithParams(
									SiteContexteFrFR.SQL_definir
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
									gestionnaireEvenements.handle(Future.failedFuture(new Exception(definirAsync.cause())));
								}
							});
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

	public void rechercheSaisonScolaire(RequeteSiteFrFR requeteSite, Boolean peupler, Boolean stocker, String classeApiUriMethode, Handler<AsyncResult<ListeRecherche<SaisonScolaire>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<SaisonScolaire> listeRecherche = new ListeRecherche<SaisonScolaire>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(SaisonScolaire.class);
			if(entiteListe != null)
				listeRecherche.addFields(entiteListe);
			listeRecherche.set("json.facet", "{max_modifie:'max(modifie_indexed_date)'}");

			String id = operationRequete.getParams().getJsonObject("path").getString("id");
			if(id != null) {
				listeRecherche.addFilterQuery("(id:" + ClientUtils.escapeQueryChars(id) + " OR objetId_indexed_string:" + ClientUtils.escapeQueryChars(id) + ")");
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
								varIndexe = "*".equals(entiteVar) ? entiteVar : SaisonScolaire.varRechercheSaisonScolaire(entiteVar);
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
								varIndexe = SaisonScolaire.varIndexeSaisonScolaire(entiteVar);
								listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
								break;
							case "sort":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
								valeurTri = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
								varIndexe = SaisonScolaire.varIndexeSaisonScolaire(entiteVar);
								listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurTri));
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
			if(listeRecherche.getSorts().size() == 0)
				listeRecherche.addSort("cree_indexed_date", ORDER.desc);
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
			gestionnaireEvenements.handle(Future.succeededFuture());
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}
}
