package org.computate.scolaire.frFR.gardien;

import org.computate.scolaire.frFR.inscription.InscriptionScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;
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
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;


/**
 * Traduire: false
 * classeNomCanonique.enUS: org.computate.scolaire.enUS.guardian.SchoolGuardianEnUSGenApiServiceImpl
 **/
public class GardienScolaireFrFRGenApiServiceImpl implements GardienScolaireFrFRGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(GardienScolaireFrFRGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "GardienScolaireFrFRApiServiceImpl";

	protected SiteContexteFrFR siteContexte;

	public GardienScolaireFrFRGenApiServiceImpl(SiteContexteFrFR siteContexte) {
		this.siteContexte = siteContexte;
	}

	// POST //

	@Override
	public void postGardienScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourGardienScolaire(siteContexte, operationRequete, body);
			sqlGardienScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					creerGardienScolaire(requeteSite, b -> {
						if(b.succeeded()) {
						RequeteApi requeteApi = new RequeteApi();
							requeteApi.setRows(1);
							requeteApi.setNumFound(1L);
							requeteApi.initLoinRequeteApi(requeteSite);
							requeteSite.setRequeteApi_(requeteApi);
							GardienScolaire gardienScolaire = b.result();
							sqlPOSTGardienScolaire(gardienScolaire, c -> {
								if(c.succeeded()) {
									definirGardienScolaire(gardienScolaire, d -> {
										if(d.succeeded()) {
											attribuerGardienScolaire(gardienScolaire, e -> {
												if(e.succeeded()) {
													indexerGardienScolaire(gardienScolaire, f -> {
														if(f.succeeded()) {
															reponse200POSTGardienScolaire(gardienScolaire, g -> {
																if(f.succeeded()) {
																	SQLConnection connexionSql = requeteSite.getConnexionSql();
																	connexionSql.commit(h -> {
																		if(a.succeeded()) {
																			connexionSql.close(i -> {
																				if(a.succeeded()) {
																					requeteApiGardienScolaire(gardienScolaire);
																					gardienScolaire.requeteApiGardienScolaire();
																					requeteSite.getVertx().eventBus().publish("websocketGardienScolaire", JsonObject.mapFrom(requeteApi).toString());
																					gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																				} else {
																					erreurGardienScolaire(requeteSite, gestionnaireEvenements, i);
																				}
																			});
																		} else {
																			erreurGardienScolaire(requeteSite, gestionnaireEvenements, h);
																		}
																	});
																} else {
																	erreurGardienScolaire(requeteSite, gestionnaireEvenements, g);
																}
															});
														} else {
															erreurGardienScolaire(requeteSite, gestionnaireEvenements, f);
														}
													});
												} else {
													erreurGardienScolaire(requeteSite, gestionnaireEvenements, e);
												}
											});
										} else {
											erreurGardienScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurGardienScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurGardienScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurGardienScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurGardienScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void sqlPOSTGardienScolaire(GardienScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
					case "inscriptionCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							postSql.append(SiteContexteFrFR.SQL_addA);
							postSqlParams.addAll(Arrays.asList("gardienCles", l, "inscriptionCles", pk));
						}
						break;
					case "personnePrenom":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personnePrenom", jsonObject.getString(entiteVar), pk));
						break;
					case "personnePrenomPrefere":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personnePrenomPrefere", jsonObject.getString(entiteVar), pk));
						break;
					case "familleNom":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("familleNom", jsonObject.getString(entiteVar), pk));
						break;
					case "personneNumeroTelephone":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personneNumeroTelephone", jsonObject.getString(entiteVar), pk));
						break;
					case "personneRelation":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personneRelation", jsonObject.getString(entiteVar), pk));
						break;
					case "personneContactUrgence":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personneContactUrgence", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "personneChercher":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personneChercher", jsonObject.getBoolean(entiteVar), pk));
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

	public void reponse200POSTGardienScolaire(GardienScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			JsonObject json = JsonObject.mapFrom(o);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Optional.ofNullable(json).orElse(new JsonObject()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUT //

	@Override
	public void putGardienScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourGardienScolaire(siteContexte, operationRequete, body);
			sqlGardienScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurGardienScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							SQLConnection connexionSql = requeteSite.getConnexionSql();
							connexionSql.close(c -> {
								if(c.succeeded()) {
									rechercheGardienScolaire(requeteSite, false, true, null, d -> {
										if(d.succeeded()) {
											ListeRecherche<GardienScolaire> listeGardienScolaire = d.result();
											RequeteApi requeteApi = new RequeteApi();
											requeteApi.setRows(listeGardienScolaire.getRows());
											requeteApi.setNumFound(Optional.ofNullable(listeGardienScolaire.getQueryResponse()).map(QueryResponse::getResults).map(SolrDocumentList::getNumFound).orElse(new Long(listeGardienScolaire.size())));
											requeteApi.initLoinRequeteApi(requeteSite);
											requeteSite.setRequeteApi_(requeteApi);
											WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
											executeurTravailleur.executeBlocking(
												blockingCodeHandler -> {
													sqlGardienScolaire(requeteSite, e -> {
														if(e.succeeded()) {
															try {
																listePUTGardienScolaire(requeteApi, listeGardienScolaire, f -> {
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
											reponse200PUTGardienScolaire(requeteApi, gestionnaireEvenements);
										} else {
											erreurGardienScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurGardienScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurGardienScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurGardienScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurGardienScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void listePUTGardienScolaire(RequeteApi requeteApi, ListeRecherche<GardienScolaire> listeGardienScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeGardienScolaire.getRequeteSite_();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		if(jsonArray.size() == 0) {
			listeGardienScolaire.getList().forEach(o -> {
				futures.add(
					futurePUTGardienScolaire(requeteSite, JsonObject.mapFrom(o), a -> {
						if(a.succeeded()) {
						} else {
							erreurGardienScolaire(requeteSite, gestionnaireEvenements, a);
						}
					})
				);
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeGardienScolaire.size());
					if(listeGardienScolaire.next()) {
						requeteSite.getVertx().eventBus().publish("websocketGardienScolaire", JsonObject.mapFrom(requeteApi).toString());
						listePUTGardienScolaire(requeteApi, listeGardienScolaire, gestionnaireEvenements);
					} else {
						reponse200PUTGardienScolaire(requeteApi, gestionnaireEvenements);
					}
				} else {
					erreurGardienScolaire(listeGardienScolaire.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} else {
			jsonArray.forEach(o -> {
				JsonObject jsonObject = (JsonObject)o;
				futures.add(
					futurePUTGardienScolaire(requeteSite, jsonObject, a -> {
						if(a.succeeded()) {
						} else {
							erreurGardienScolaire(requeteSite, gestionnaireEvenements, a);
						}
					})
				);
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					requeteApi.setNumPATCH(requeteApi.getNumPATCH() + jsonArray.size());
					reponse200PUTGardienScolaire(requeteApi, gestionnaireEvenements);
				} else {
					erreurGardienScolaire(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		}
	}

	public Future<GardienScolaire> futurePUTGardienScolaire(RequeteSiteFrFR requeteSite, JsonObject jsonObject,  Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		jsonObject.put("sauvegardes", Optional.ofNullable(jsonObject.getJsonArray("sauvegardes")).orElse(new JsonArray()));
		JsonObject jsonPatch = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
		jsonPatch.stream().forEach(o -> {
			jsonObject.put(o.getKey(), o.getValue());
			jsonObject.getJsonArray("sauvegardes").add(o.getKey());
		});
		Promise<GardienScolaire> promise = Promise.promise();
		try {
			creerGardienScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					GardienScolaire gardienScolaire = a.result();
					sqlPUTGardienScolaire(gardienScolaire, jsonObject, b -> {
						if(b.succeeded()) {
							definirGardienScolaire(gardienScolaire, c -> {
								if(c.succeeded()) {
									attribuerGardienScolaire(gardienScolaire, d -> {
										if(d.succeeded()) {
											indexerGardienScolaire(gardienScolaire, e -> {
												if(e.succeeded()) {
													requeteApiGardienScolaire(gardienScolaire);
													gardienScolaire.requeteApiGardienScolaire();
													promise.complete(gardienScolaire);
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
			return promise.future();
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public void remplacerPUTGardienScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<GardienScolaire>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			Long pk = requeteSite.getRequetePk();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_vider
					, new JsonArray(Arrays.asList(pk, GardienScolaire.class.getCanonicalName(), pk, pk, pk))
					, remplacerAsync
			-> {
				GardienScolaire o = new GardienScolaire();
				o.setPk(pk);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlPUTGardienScolaire(GardienScolaire o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
					case "inscriptionCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							postSql.append(SiteContexteFrFR.SQL_addA);
							postSqlParams.addAll(Arrays.asList("gardienCles", l, "inscriptionCles", pk));
						}
						break;
					case "personnePrenom":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personnePrenom", jsonObject.getString(entiteVar), pk));
						break;
					case "personnePrenomPrefere":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personnePrenomPrefere", jsonObject.getString(entiteVar), pk));
						break;
					case "familleNom":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("familleNom", jsonObject.getString(entiteVar), pk));
						break;
					case "personneNumeroTelephone":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personneNumeroTelephone", jsonObject.getString(entiteVar), pk));
						break;
					case "personneRelation":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personneRelation", jsonObject.getString(entiteVar), pk));
						break;
					case "personneContactUrgence":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personneContactUrgence", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "personneChercher":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personneChercher", jsonObject.getBoolean(entiteVar), pk));
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

	public void reponse200PUTGardienScolaire(RequeteApi requeteApi, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(JsonObject.mapFrom(requeteApi))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchGardienScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourGardienScolaire(siteContexte, operationRequete, body);
			sqlGardienScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurGardienScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							SQLConnection connexionSql = requeteSite.getConnexionSql();
							connexionSql.close(c -> {
								if(c.succeeded()) {
									rechercheGardienScolaire(requeteSite, false, true, null, d -> {
										if(d.succeeded()) {
											ListeRecherche<GardienScolaire> listeGardienScolaire = d.result();
											SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeGardienScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
											Date date = null;
											if(facets != null)
												date = (Date)facets.get("max_modifie");
											String dt;
											if(date == null)
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
											else
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
											listeGardienScolaire.addFilterQuery(String.format("modifie_indexed_date:[* TO %s]", dt));

											RequeteApi requeteApi = new RequeteApi();
											requeteApi.setRows(listeGardienScolaire.getRows());
											requeteApi.setNumFound(Optional.ofNullable(listeGardienScolaire.getQueryResponse()).map(QueryResponse::getResults).map(SolrDocumentList::getNumFound).orElse(new Long(listeGardienScolaire.size())));
											requeteApi.initLoinRequeteApi(requeteSite);
											requeteSite.setRequeteApi_(requeteApi);
											if(listeGardienScolaire.size() == 1) {
												GardienScolaire o = listeGardienScolaire.get(0);
												requeteApi.setPk(o.getPk());
												requeteApi.setOriginal(o);
												requeteApiGardienScolaire(o);
											}
											WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
											executeurTravailleur.executeBlocking(
												blockingCodeHandler -> {
													sqlGardienScolaire(requeteSite, e -> {
														if(e.succeeded()) {
															try {
																listePATCHGardienScolaire(requeteApi, listeGardienScolaire, dt, f -> {
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
											reponse200PATCHGardienScolaire(requeteApi, gestionnaireEvenements);
										} else {
											erreurGardienScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurGardienScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurGardienScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurGardienScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurGardienScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void listePATCHGardienScolaire(RequeteApi requeteApi, ListeRecherche<GardienScolaire> listeGardienScolaire, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeGardienScolaire.getRequeteSite_();
		listeGardienScolaire.getList().forEach(o -> {
			futures.add(
				futurePATCHGardienScolaire(o, a -> {
					if(a.succeeded()) {
					} else {
						erreurGardienScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeGardienScolaire.size());
				if(listeGardienScolaire.next(dt)) {
					requeteSite.getVertx().eventBus().publish("websocketGardienScolaire", JsonObject.mapFrom(requeteApi).toString());
					listePATCHGardienScolaire(requeteApi, listeGardienScolaire, dt, gestionnaireEvenements);
				} else {
					reponse200PATCHGardienScolaire(requeteApi, gestionnaireEvenements);
				}
			} else {
				erreurGardienScolaire(listeGardienScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<GardienScolaire> futurePATCHGardienScolaire(GardienScolaire o,  Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		Promise<GardienScolaire> promise = Promise.promise();
		try {
			sqlPATCHGardienScolaire(o, a -> {
				if(a.succeeded()) {
					GardienScolaire gardienScolaire = a.result();
					definirGardienScolaire(gardienScolaire, b -> {
						if(b.succeeded()) {
							attribuerGardienScolaire(gardienScolaire, c -> {
								if(c.succeeded()) {
									indexerGardienScolaire(gardienScolaire, d -> {
										if(d.succeeded()) {
											requeteApiGardienScolaire(gardienScolaire);
											gardienScolaire.requeteApiGardienScolaire();
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

	public void sqlPATCHGardienScolaire(GardienScolaire o, Handler<AsyncResult<GardienScolaire>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject requeteJson = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = requeteJson.fieldNames();
			GardienScolaire o2 = new GardienScolaire();

			patchSql.append(SiteContexteFrFR.SQL_modifier);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.frFR.gardien.GardienScolaire"));
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
					case "addInscriptionCles":
						patchSql.append(SiteContexteFrFR.SQL_addA);
						patchSqlParams.addAll(Arrays.asList("gardienCles", Long.parseLong(requeteJson.getString(methodeNom)), "inscriptionCles", pk));
						break;
					case "addAllInscriptionCles":
						JsonArray addAllInscriptionClesValeurs = requeteJson.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllInscriptionClesValeurs.size(); i++) {
							patchSql.append(SiteContexteFrFR.SQL_setA2);
							patchSqlParams.addAll(Arrays.asList("gardienCles", addAllInscriptionClesValeurs.getString(i), "inscriptionCles", pk));
						}
						break;
					case "setInscriptionCles":
						JsonArray setInscriptionClesValeurs = requeteJson.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("gardienCles", Long.parseLong(requeteJson.getString(methodeNom)), "inscriptionCles", pk));
						for(Integer i = 0; i <  setInscriptionClesValeurs.size(); i++) {
							patchSql.append(SiteContexteFrFR.SQL_setA2);
							patchSqlParams.addAll(Arrays.asList("gardienCles", setInscriptionClesValeurs.getString(i), "inscriptionCles", pk));
						}
						break;
					case "removeInscriptionCles":
						patchSql.append(SiteContexteFrFR.SQL_removeA);
						patchSqlParams.addAll(Arrays.asList("gardienCles", Long.parseLong(requeteJson.getString(methodeNom)), "inscriptionCles", pk));
						break;
					case "setPersonnePrenom":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personnePrenom"));
						} else {
							o2.setPersonnePrenom(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personnePrenom", o2.jsonPersonnePrenom(), pk));
						}
						break;
					case "setPersonnePrenomPrefere":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personnePrenomPrefere"));
						} else {
							o2.setPersonnePrenomPrefere(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personnePrenomPrefere", o2.jsonPersonnePrenomPrefere(), pk));
						}
						break;
					case "setFamilleNom":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familleNom"));
						} else {
							o2.setFamilleNom(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familleNom", o2.jsonFamilleNom(), pk));
						}
						break;
					case "setPersonneContactUrgence":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personneContactUrgence"));
						} else {
							o2.setPersonneContactUrgence(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personneContactUrgence", o2.jsonPersonneContactUrgence(), pk));
						}
						break;
					case "setPersonneChercher":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personneChercher"));
						} else {
							o2.setPersonneChercher(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personneChercher", o2.jsonPersonneChercher(), pk));
						}
						break;
					case "setPersonneNumeroTelephone":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personneNumeroTelephone"));
						} else {
							o2.setPersonneNumeroTelephone(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personneNumeroTelephone", o2.jsonPersonneNumeroTelephone(), pk));
						}
						break;
					case "setPersonneRelation":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personneRelation"));
						} else {
							o2.setPersonneRelation(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personneRelation", o2.jsonPersonneRelation(), pk));
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
					GardienScolaire o3 = new GardienScolaire();
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

	public void reponse200PATCHGardienScolaire(RequeteApi requeteApi, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = requeteApi.getRequeteSite_();
			JsonObject json = JsonObject.mapFrom(requeteApi);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Optional.ofNullable(json).orElse(new JsonObject()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getGardienScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourGardienScolaire(siteContexte, operationRequete);
			sqlGardienScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurGardienScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheGardienScolaire(requeteSite, false, true, null, c -> {
								if(c.succeeded()) {
									ListeRecherche<GardienScolaire> listeGardienScolaire = c.result();
									reponse200GETGardienScolaire(listeGardienScolaire, d -> {
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
																erreurGardienScolaire(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											erreurGardienScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurGardienScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurGardienScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurGardienScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurGardienScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200GETGardienScolaire(ListeRecherche<GardienScolaire> listeGardienScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeGardienScolaire.getRequeteSite_();
			SolrDocumentList documentsSolr = listeGardienScolaire.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listeGardienScolaire.getList().stream().findFirst().orElse(null));
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Optional.ofNullable(json).orElse(new JsonObject()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteGardienScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourGardienScolaire(siteContexte, operationRequete);
			sqlGardienScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					rechercheGardienScolaire(requeteSite, false, true, null, b -> {
						if(b.succeeded()) {
							ListeRecherche<GardienScolaire> listeGardienScolaire = b.result();
							supprimerDELETEGardienScolaire(requeteSite, c -> {
								if(c.succeeded()) {
									reponse200DELETEGardienScolaire(requeteSite, d -> {
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
																erreurGardienScolaire(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											erreurGardienScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurGardienScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurGardienScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurGardienScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurGardienScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void supprimerDELETEGardienScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			Long pk = requeteSite.getRequetePk();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_supprimer
					, new JsonArray(Arrays.asList(pk, GardienScolaire.class.getCanonicalName(), pk, pk, pk, pk))
					, supprimerAsync
			-> {
				gestionnaireEvenements.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200DELETEGardienScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Optional.ofNullable(json).orElse(new JsonObject()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void rechercheGardienScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourGardienScolaire(siteContexte, operationRequete);
			sqlGardienScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurGardienScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheGardienScolaire(requeteSite, false, true, "/api/gardien", c -> {
								if(c.succeeded()) {
									ListeRecherche<GardienScolaire> listeGardienScolaire = c.result();
									reponse200RechercheGardienScolaire(listeGardienScolaire, d -> {
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
																erreurGardienScolaire(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											erreurGardienScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurGardienScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurGardienScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurGardienScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurGardienScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200RechercheGardienScolaire(ListeRecherche<GardienScolaire> listeGardienScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeGardienScolaire.getRequeteSite_();
			QueryResponse reponseRecherche = listeGardienScolaire.getQueryResponse();
			SolrDocumentList documentsSolr = listeGardienScolaire.getSolrDocumentList();
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
			listeGardienScolaire.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listeGardienScolaire.getFields();
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
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Optional.ofNullable(json).orElse(new JsonObject()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PageRecherche //

	@Override
	public void pagerechercheGardienScolaireId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		pagerechercheGardienScolaire(operationRequete, gestionnaireEvenements);
	}

	@Override
	public void pagerechercheGardienScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourGardienScolaire(siteContexte, operationRequete);
			sqlGardienScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurGardienScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheGardienScolaire(requeteSite, false, true, "/gardien", c -> {
								if(c.succeeded()) {
									ListeRecherche<GardienScolaire> listeGardienScolaire = c.result();
									reponse200PageRechercheGardienScolaire(listeGardienScolaire, d -> {
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
																erreurGardienScolaire(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											erreurGardienScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurGardienScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurGardienScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurGardienScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurGardienScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200PageRechercheGardienScolaire(ListeRecherche<GardienScolaire> listeGardienScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeGardienScolaire.getRequeteSite_();
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(listeGardienScolaire.getRequeteSite_(), buffer);
			GardienPage page = new GardienPage();
			SolrDocument pageDocumentSolr = new SolrDocument();
			CaseInsensitiveHeaders requeteEnTetes = new CaseInsensitiveHeaders();
			requeteSite.setRequeteEnTetes(requeteEnTetes);

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/gardien");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			if(listeGardienScolaire.size() == 1)
				requeteSite.setRequetePk(listeGardienScolaire.get(0).getPk());
			requeteSite.setW(w);
			page.setListeGardienScolaire(listeGardienScolaire);
			page.setRequeteSite_(requeteSite);
			page.initLoinGardienPage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requeteEnTetes)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Partagé //

	public void creerGardienScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<GardienScolaire>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_creer
					, new JsonArray(Arrays.asList(GardienScolaire.class.getCanonicalName(), utilisateurId))
					, creerAsync
			-> {
				JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = creerLigne.getLong(0);
				GardienScolaire o = new GardienScolaire();
				o.setPk(pk);
				o.setRequeteSite_(requeteSite);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void requeteApiGardienScolaire(GardienScolaire o) {
		RequeteApi requeteApi = o.getRequeteSite_().getRequeteApi_();
		if(requeteApi != null) {
			List<Long> pks = requeteApi.getPks();
			List<String> classes = requeteApi.getClasses();
			for(Long pk : o.getInscriptionCles()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("InscriptionScolaire");
				}
			}
		}
	}

	public void erreurGardienScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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

	public void sqlGardienScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public RequeteSiteFrFR genererRequeteSiteFrFRPourGardienScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete) {
		return genererRequeteSiteFrFRPourGardienScolaire(siteContexte, operationRequete, null);
	}

	public RequeteSiteFrFR genererRequeteSiteFrFRPourGardienScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete, JsonObject body) {
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

	public void utilisateurGardienScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void rechercheGardienScolaire(RequeteSiteFrFR requeteSite, Boolean peupler, Boolean stocker, String classeApiUriMethode, Handler<AsyncResult<ListeRecherche<GardienScolaire>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<GardienScolaire> listeRecherche = new ListeRecherche<GardienScolaire>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(GardienScolaire.class);
			if(entiteListe != null)
				listeRecherche.addFields(entiteListe);
			listeRecherche.set("json.facet", "{max_modifie:'max(modifie_indexed_date)'}");

			String id = operationRequete.getParams().getJsonObject("path").getString("id");
			if(id != null) {
				listeRecherche.addFilterQuery("(id:" + ClientUtils.escapeQueryChars(id) + " OR objetId_indexed_string:" + ClientUtils.escapeQueryChars(id) + ")");
			}

			List<String> roles = Arrays.asList("SiteAdmin");
			if(
					!CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRessource(), roles)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRoyaume(), roles)
					) {
				listeRecherche.addFilterQuery("sessionId_indexed_string:" + ClientUtils.escapeQueryChars(Optional.ofNullable(requeteSite.getSessionId()).orElse("-----")));
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
								varIndexe = "*".equals(entiteVar) ? entiteVar : GardienScolaire.varRechercheGardienScolaire(entiteVar);
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
								varIndexe = GardienScolaire.varIndexeGardienScolaire(entiteVar);
								listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
								break;
							case "sort":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
								valeurTri = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
								varIndexe = GardienScolaire.varIndexeGardienScolaire(entiteVar);
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

	public void definirGardienScolaire(GardienScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void attribuerGardienScolaire(GardienScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void indexerGardienScolaire(GardienScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			o.initLoinPourClasse(requeteSite);
			o.indexerPourClasse();
			if(!requeteSite.getRequeteApi_().getEmpty()) {
				ListeRecherche<GardienScolaire> listeRecherche = new ListeRecherche<GardienScolaire>();
				listeRecherche.setPeupler(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(GardienScolaire.class);
				listeRecherche.addFilterQuery("modifie_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(requeteSite.getRequeteApi_().getCree().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				listeRecherche.add("json.facet", "{inscriptionCles:{terms:{field:inscriptionCles_indexed_longs, limit:1000}}}");
				listeRecherche.setRows(1000);
				listeRecherche.initLoinListeRecherche(requeteSite);
				List<Future> futures = new ArrayList<>();

				{
					InscriptionScolaireFrFRGenApiServiceImpl service = new InscriptionScolaireFrFRGenApiServiceImpl(requeteSite.getSiteContexte_());
					for(Long pk : o.getInscriptionCles()) {
						InscriptionScolaire o2 = new InscriptionScolaire();

						o2.setPk(pk);
						o2.setRequeteSite_(requeteSite);
						futures.add(
							service.futurePATCHInscriptionScolaire(o2, a -> {
								if(a.succeeded()) {
									LOGGER.info(String.format("InscriptionScolaire %s rechargé. ", pk));
								} else {
									LOGGER.info(String.format("InscriptionScolaire %s a échoué. ", pk));
									gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
								}
							})
						);
					}
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						LOGGER.info("Recharger relations a réussi. ");
						GardienScolaireFrFRGenApiServiceImpl service = new GardienScolaireFrFRGenApiServiceImpl(requeteSite.getSiteContexte_());
						List<Future> futures2 = new ArrayList<>();
						for(GardienScolaire o2 : listeRecherche.getList()) {
							futures2.add(
								service.futurePATCHGardienScolaire(o2, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("GardienScolaire %s rechargé. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("GardienScolaire %s a échoué. ", o2.getPk()));
										gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Recharger GardienScolaire a réussi. ");
								gestionnaireEvenements.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Recharger relations a échoué. ", b.cause());
								erreurGardienScolaire(requeteSite, gestionnaireEvenements, b);
							}
						});
					} else {
						LOGGER.error("Recharger relations a échoué. ", a.cause());
						erreurGardienScolaire(requeteSite, gestionnaireEvenements, a);
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
