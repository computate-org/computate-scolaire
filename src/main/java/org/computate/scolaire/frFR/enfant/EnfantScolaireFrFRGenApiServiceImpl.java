package org.computate.scolaire.frFR.enfant;

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
import org.apache.commons.lang3.BooleanUtils;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSiteFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;


/**
 * Traduire: false
 * NomCanonique.enUS: org.computate.scolaire.enUS.child.SchoolChildEnUSGenApiServiceImpl
 **/
public class EnfantScolaireFrFRGenApiServiceImpl implements EnfantScolaireFrFRGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(EnfantScolaireFrFRGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "EnfantScolaireFrFRApiServiceImpl";

	protected SiteContexteFrFR siteContexte;

	public EnfantScolaireFrFRGenApiServiceImpl(SiteContexteFrFR siteContexte) {
		this.siteContexte = siteContexte;
	}

	// POST //

	@Override
	public void postEnfantScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEnfantScolaire(siteContexte, operationRequete, body);

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

			sqlEnfantScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEnfantScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							RequeteApi requeteApi = new RequeteApi();
							requeteApi.setRows(1);
							requeteApi.setNumFound(1L);
							requeteApi.initLoinRequeteApi(requeteSite);
							requeteSite.setRequeteApi_(requeteApi);
							postEnfantScolaireFuture(requeteSite, false, c -> {
								if(c.succeeded()) {
									EnfantScolaire enfantScolaire = c.result();
									requeteApiEnfantScolaire(enfantScolaire);
									postEnfantScolaireReponse(enfantScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postEnfantScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("postEnfantScolaire a échoué. ", d.cause()));
											erreurEnfantScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEnfantScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public Future<EnfantScolaire> postEnfantScolaireFuture(RequeteSiteFrFR requeteSite, Boolean inheritPk, Handler<AsyncResult<EnfantScolaire>> gestionnaireEvenements) {
		Promise<EnfantScolaire> promise = Promise.promise();
		try {
			creerEnfantScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					EnfantScolaire enfantScolaire = a.result();
					sqlPOSTEnfantScolaire(enfantScolaire, inheritPk, b -> {
						if(b.succeeded()) {
							definirIndexerEnfantScolaire(enfantScolaire, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(enfantScolaire));
									promise.complete(enfantScolaire);
								} else {
									erreurEnfantScolaire(requeteSite, null, c);
								}
							});
						} else {
							erreurEnfantScolaire(requeteSite, null, b);
						}
					});
				} else {
					erreurEnfantScolaire(requeteSite, null, a);
				}
			});
		} catch(Exception e) {
			erreurEnfantScolaire(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTEnfantScolaire(EnfantScolaire o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
							ListeRecherche<InscriptionScolaire> listeRecherche = new ListeRecherche<InscriptionScolaire>();
							listeRecherche.setQuery("*:*");
							listeRecherche.setStocker(true);
							listeRecherche.setC(InscriptionScolaire.class);
							listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							listeRecherche.initLoinListeRecherche(requeteSite);
							l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								postSql.append(SiteContexteFrFR.SQL_addA);
								postSqlParams.addAll(Arrays.asList("enfantCle", l, "inscriptionCles", pk));
							}
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
					case "personneDateNaissance":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personneDateNaissance", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
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

	public void postEnfantScolaireReponse(EnfantScolaire enfantScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = enfantScolaire.getRequeteSite_();
		reponse200POSTEnfantScolaire(enfantScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								RequeteApi requeteApi = requeteApiEnfantScolaire(enfantScolaire);
								enfantScolaire.requeteApiEnfantScolaire();
								requeteSite.getVertx().eventBus().publish("websocketEnfantScolaire", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200POSTEnfantScolaire(EnfantScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
	public void putimportEnfantScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEnfantScolaire(siteContexte, operationRequete, body);

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

			sqlEnfantScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEnfantScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							SQLConnection connexionSql = requeteSite.getConnexionSql();
							connexionSql.close(c -> {
								if(c.succeeded()) {
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											sqlEnfantScolaire(requeteSite, d -> {
												if(d.succeeded()) {
													try {
														RequeteApi requeteApi = new RequeteApi();
														JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
														requeteApi.setRows(jsonArray.size());
														requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
														requeteApi.initLoinRequeteApi(requeteSite);
														requeteSite.setRequeteApi_(requeteApi);
														listePUTImportEnfantScolaire(requeteApi, requeteSite, e -> {
															if(e.succeeded()) {
																putimportEnfantScolaireReponse(requeteSite, f -> {
																	if(f.succeeded()) {
																		gestionnaireEvenements.handle(Future.succeededFuture(f.result()));
																		LOGGER.info(String.format("putimportEnfantScolaire a réussi. "));
																	} else {
																		LOGGER.error(String.format("putimportEnfantScolaire a échoué. ", f.cause()));
																		erreurEnfantScolaire(requeteSite, gestionnaireEvenements, f);
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
									erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEnfantScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTImportEnfantScolaire(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		jsonArray.forEach(obj -> {
			JsonObject json = (JsonObject)obj;

			RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEnfantScolaire(siteContexte, requeteSite.getOperationRequete(), json);
			requeteSite2.setConnexionSql(requeteSite.getConnexionSql());

			ListeRecherche<EnfantScolaire> listeRecherche = new ListeRecherche<EnfantScolaire>();
			listeRecherche.setStocker(true);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(EnfantScolaire.class);
			listeRecherche.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
			listeRecherche.initLoinPourClasse(requeteSite2);

			if(listeRecherche.size() == 1) {
				EnfantScolaire o = listeRecherche.get(0);
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
					patchEnfantScolaireFuture(o, true, a -> {
						if(a.succeeded()) {
							EnfantScolaire enfantScolaire = a.result();
							requeteApiEnfantScolaire(enfantScolaire);
						} else {
							erreurEnfantScolaire(requeteSite2, gestionnaireEvenements, a);
						}
					})
				);
			} else {
				futures.add(
					postEnfantScolaireFuture(requeteSite2, true, a -> {
						if(a.succeeded()) {
							EnfantScolaire enfantScolaire = a.result();
							requeteApiEnfantScolaire(enfantScolaire);
						} else {
							erreurEnfantScolaire(requeteSite2, gestionnaireEvenements, a);
						}
					})
				);
			}
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + jsonArray.size());
				reponse200PUTImportEnfantScolaire(requeteSite, gestionnaireEvenements);
			} else {
				erreurEnfantScolaire(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public void putimportEnfantScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PUTImportEnfantScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								RequeteApi requeteApi = requeteSite.getRequeteApi_();
								requeteSite.getVertx().eventBus().publish("websocketEnfantScolaire", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTImportEnfantScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(requeteApi).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTFusion //

	@Override
	public void putfusionEnfantScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEnfantScolaire(siteContexte, operationRequete, body);

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

			sqlEnfantScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEnfantScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							SQLConnection connexionSql = requeteSite.getConnexionSql();
							connexionSql.close(c -> {
								if(c.succeeded()) {
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											sqlEnfantScolaire(requeteSite, d -> {
												if(d.succeeded()) {
													try {
														RequeteApi requeteApi = new RequeteApi();
														JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
														requeteApi.setRows(jsonArray.size());
														requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
														requeteApi.initLoinRequeteApi(requeteSite);
														requeteSite.setRequeteApi_(requeteApi);
														listePUTFusionEnfantScolaire(requeteApi, requeteSite, e -> {
															if(e.succeeded()) {
																putfusionEnfantScolaireReponse(requeteSite, f -> {
																	if(f.succeeded()) {
																		gestionnaireEvenements.handle(Future.succeededFuture(f.result()));
																		LOGGER.info(String.format("putfusionEnfantScolaire a réussi. "));
																	} else {
																		LOGGER.error(String.format("putfusionEnfantScolaire a échoué. ", f.cause()));
																		erreurEnfantScolaire(requeteSite, gestionnaireEvenements, f);
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
									erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEnfantScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTFusionEnfantScolaire(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		jsonArray.forEach(obj -> {
			JsonObject json = (JsonObject)obj;

			RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEnfantScolaire(siteContexte, requeteSite.getOperationRequete(), json);
			requeteSite2.setConnexionSql(requeteSite.getConnexionSql());

			ListeRecherche<EnfantScolaire> listeRecherche = new ListeRecherche<EnfantScolaire>();
			listeRecherche.setStocker(true);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(EnfantScolaire.class);
			listeRecherche.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
			listeRecherche.initLoinPourClasse(requeteSite2);

			if(listeRecherche.size() == 1) {
				EnfantScolaire o = listeRecherche.get(0);
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
					patchEnfantScolaireFuture(o, false, a -> {
						if(a.succeeded()) {
							EnfantScolaire enfantScolaire = a.result();
							requeteApiEnfantScolaire(enfantScolaire);
						} else {
							erreurEnfantScolaire(requeteSite2, gestionnaireEvenements, a);
						}
					})
				);
			} else {
				futures.add(
					postEnfantScolaireFuture(requeteSite2, false, a -> {
						if(a.succeeded()) {
							EnfantScolaire enfantScolaire = a.result();
							requeteApiEnfantScolaire(enfantScolaire);
						} else {
							erreurEnfantScolaire(requeteSite2, gestionnaireEvenements, a);
						}
					})
				);
			}
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + jsonArray.size());
				reponse200PUTFusionEnfantScolaire(requeteSite, gestionnaireEvenements);
			} else {
				erreurEnfantScolaire(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public void putfusionEnfantScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PUTFusionEnfantScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								RequeteApi requeteApi = requeteSite.getRequeteApi_();
								requeteSite.getVertx().eventBus().publish("websocketEnfantScolaire", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTFusionEnfantScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(requeteApi).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTCopie //

	@Override
	public void putcopieEnfantScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEnfantScolaire(siteContexte, operationRequete, body);

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

			sqlEnfantScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEnfantScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							SQLConnection connexionSql = requeteSite.getConnexionSql();
							connexionSql.close(c -> {
								if(c.succeeded()) {
									rechercheEnfantScolaire(requeteSite, false, true, null, d -> {
										if(d.succeeded()) {
											ListeRecherche<EnfantScolaire> listeEnfantScolaire = d.result();
											RequeteApi requeteApi = new RequeteApi();
											requeteApi.setRows(listeEnfantScolaire.getRows());
											requeteApi.setNumFound(listeEnfantScolaire.getQueryResponse().getResults().getNumFound());
											requeteApi.initLoinRequeteApi(requeteSite);
											requeteSite.setRequeteApi_(requeteApi);
											WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
											executeurTravailleur.executeBlocking(
												blockingCodeHandler -> {
													sqlEnfantScolaire(requeteSite, e -> {
														if(e.succeeded()) {
															try {
																listePUTCopieEnfantScolaire(requeteApi, listeEnfantScolaire, f -> {
																	if(f.succeeded()) {
																		putcopieEnfantScolaireReponse(listeEnfantScolaire, g -> {
																			if(g.succeeded()) {
																				gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("putcopieEnfantScolaire a réussi. "));
																			} else {
																				LOGGER.error(String.format("putcopieEnfantScolaire a échoué. ", g.cause()));
																				erreurEnfantScolaire(requeteSite, gestionnaireEvenements, d);
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
											erreurEnfantScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEnfantScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTCopieEnfantScolaire(RequeteApi requeteApi, ListeRecherche<EnfantScolaire> listeEnfantScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeEnfantScolaire.getRequeteSite_();
		listeEnfantScolaire.getList().forEach(o -> {
			futures.add(
				putcopieEnfantScolaireFuture(requeteSite, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						EnfantScolaire enfantScolaire = a.result();
						requeteApiEnfantScolaire(enfantScolaire);
					} else {
						erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeEnfantScolaire.size());
				if(listeEnfantScolaire.next()) {
					requeteSite.getVertx().eventBus().publish("websocketEnfantScolaire", JsonObject.mapFrom(requeteApi).toString());
					listePUTCopieEnfantScolaire(requeteApi, listeEnfantScolaire, gestionnaireEvenements);
				} else {
					reponse200PUTCopieEnfantScolaire(listeEnfantScolaire, gestionnaireEvenements);
				}
			} else {
				erreurEnfantScolaire(listeEnfantScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<EnfantScolaire> putcopieEnfantScolaireFuture(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Handler<AsyncResult<EnfantScolaire>> gestionnaireEvenements) {
		Promise<EnfantScolaire> promise = Promise.promise();
		try {

			jsonObject.put("sauvegardes", Optional.ofNullable(jsonObject.getJsonArray("sauvegardes")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("sauvegardes").add(o.getKey());
			});

			creerEnfantScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					EnfantScolaire enfantScolaire = a.result();
					sqlPUTCopieEnfantScolaire(enfantScolaire, jsonObject, b -> {
						if(b.succeeded()) {
							definirEnfantScolaire(enfantScolaire, c -> {
								if(c.succeeded()) {
									attribuerEnfantScolaire(enfantScolaire, d -> {
										if(d.succeeded()) {
											indexerEnfantScolaire(enfantScolaire, e -> {
												if(e.succeeded()) {
													gestionnaireEvenements.handle(Future.succeededFuture(enfantScolaire));
													promise.complete(enfantScolaire);
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
			erreurEnfantScolaire(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopieEnfantScolaire(EnfantScolaire o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
					case "inscriptionCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContexteFrFR.SQL_addA);
							putSqlParams.addAll(Arrays.asList("enfantCle", l, "inscriptionCles", pk));
						}
						break;
					case "personnePrenom":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personnePrenom", jsonObject.getString(entiteVar), pk));
						break;
					case "personnePrenomPrefere":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personnePrenomPrefere", jsonObject.getString(entiteVar), pk));
						break;
					case "familleNom":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("familleNom", jsonObject.getString(entiteVar), pk));
						break;
					case "personneDateNaissance":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personneDateNaissance", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
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

	public void putcopieEnfantScolaireReponse(ListeRecherche<EnfantScolaire> listeEnfantScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeEnfantScolaire.getRequeteSite_();
		reponse200PUTCopieEnfantScolaire(listeEnfantScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								RequeteApi requeteApi = requeteSite.getRequeteApi_();
								requeteSite.getVertx().eventBus().publish("websocketEnfantScolaire", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTCopieEnfantScolaire(ListeRecherche<EnfantScolaire> listeEnfantScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeEnfantScolaire.getRequeteSite_();
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(requeteApi).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchEnfantScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEnfantScolaire(siteContexte, operationRequete, body);

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

			sqlEnfantScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEnfantScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							SQLConnection connexionSql = requeteSite.getConnexionSql();
							connexionSql.close(c -> {
								if(c.succeeded()) {
									rechercheEnfantScolaire(requeteSite, false, true, null, d -> {
										if(d.succeeded()) {
											ListeRecherche<EnfantScolaire> listeEnfantScolaire = d.result();
											RequeteApi requeteApi = new RequeteApi();
											requeteApi.setRows(listeEnfantScolaire.getRows());
											requeteApi.setNumFound(listeEnfantScolaire.getQueryResponse().getResults().getNumFound());
											requeteApi.initLoinRequeteApi(requeteSite);
											requeteSite.setRequeteApi_(requeteApi);
											SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeEnfantScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
											Date date = null;
											if(facets != null)
												date = (Date)facets.get("max_modifie");
											String dt;
											if(date == null)
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
											else
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
											listeEnfantScolaire.addFilterQuery(String.format("modifie_indexed_date:[* TO %s]", dt));

											if(listeEnfantScolaire.size() == 1) {
												EnfantScolaire o = listeEnfantScolaire.get(0);
												requeteApi.setPk(o.getPk());
												requeteApi.setOriginal(o);
												requeteApiEnfantScolaire(o);
											o.requeteApiEnfantScolaire();
											}
											WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
											executeurTravailleur.executeBlocking(
												blockingCodeHandler -> {
													sqlEnfantScolaire(requeteSite, e -> {
														if(e.succeeded()) {
															try {
																listePATCHEnfantScolaire(requeteApi, listeEnfantScolaire, dt, f -> {
																	if(f.succeeded()) {
																		patchEnfantScolaireReponse(listeEnfantScolaire, g -> {
																			if(g.succeeded()) {
																				gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("patchEnfantScolaire a réussi. "));
																			} else {
																				LOGGER.error(String.format("patchEnfantScolaire a échoué. ", g.cause()));
																				erreurEnfantScolaire(requeteSite, gestionnaireEvenements, d);
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
											erreurEnfantScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEnfantScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePATCHEnfantScolaire(RequeteApi requeteApi, ListeRecherche<EnfantScolaire> listeEnfantScolaire, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeEnfantScolaire.getRequeteSite_();
		listeEnfantScolaire.getList().forEach(o -> {
			futures.add(
				patchEnfantScolaireFuture(o, false, a -> {
					if(a.succeeded()) {
							EnfantScolaire enfantScolaire = a.result();
							requeteApiEnfantScolaire(enfantScolaire);
					} else {
						erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeEnfantScolaire.size());
				if(listeEnfantScolaire.next(dt)) {
					requeteSite.getVertx().eventBus().publish("websocketEnfantScolaire", JsonObject.mapFrom(requeteApi).toString());
					listePATCHEnfantScolaire(requeteApi, listeEnfantScolaire, dt, gestionnaireEvenements);
				} else {
					reponse200PATCHEnfantScolaire(listeEnfantScolaire, gestionnaireEvenements);
				}
			} else {
				erreurEnfantScolaire(listeEnfantScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<EnfantScolaire> patchEnfantScolaireFuture(EnfantScolaire o, Boolean inheritPk, Handler<AsyncResult<EnfantScolaire>> gestionnaireEvenements) {
		Promise<EnfantScolaire> promise = Promise.promise();
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			sqlPATCHEnfantScolaire(o, inheritPk, a -> {
				if(a.succeeded()) {
					EnfantScolaire enfantScolaire = a.result();
					definirEnfantScolaire(enfantScolaire, b -> {
						if(b.succeeded()) {
							attribuerEnfantScolaire(enfantScolaire, c -> {
								if(c.succeeded()) {
									indexerEnfantScolaire(enfantScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(enfantScolaire));
											promise.complete(enfantScolaire);
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
			erreurEnfantScolaire(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHEnfantScolaire(EnfantScolaire o, Boolean inheritPk, Handler<AsyncResult<EnfantScolaire>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject requeteJson = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = requeteJson.fieldNames();
			EnfantScolaire o2 = new EnfantScolaire();

			patchSql.append(SiteContexteFrFR.SQL_modifier);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.frFR.enfant.EnfantScolaire"));
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
						{
							Long l = Long.parseLong(requeteJson.getString(methodeNom));
							ListeRecherche<InscriptionScolaire> listeRecherche = new ListeRecherche<InscriptionScolaire>();
							listeRecherche.setQuery("*:*");
							listeRecherche.setStocker(true);
							listeRecherche.setC(InscriptionScolaire.class);
							listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							listeRecherche.initLoinListeRecherche(requeteSite);
							l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								patchSql.append(SiteContexteFrFR.SQL_addA);
								patchSqlParams.addAll(Arrays.asList("enfantCle", l, "inscriptionCles", pk));
							}
						}
						break;
					case "addAllInscriptionCles":
						JsonArray addAllInscriptionClesValeurs = requeteJson.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllInscriptionClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllInscriptionClesValeurs.getString(i));
							ListeRecherche<InscriptionScolaire> listeRecherche = new ListeRecherche<InscriptionScolaire>();
							listeRecherche.setQuery("*:*");
							listeRecherche.setStocker(true);
							listeRecherche.setC(InscriptionScolaire.class);
							listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							listeRecherche.initLoinListeRecherche(requeteSite);
							l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								patchSql.append(SiteContexteFrFR.SQL_setA2);
								patchSqlParams.addAll(Arrays.asList("enfantCle", l, "inscriptionCles", pk));
							}
						}
						break;
					case "setInscriptionCles":
						JsonArray setInscriptionClesValeurs = requeteJson.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("enfantCle", "inscriptionCles", pk));
						for(Integer i = 0; i <  setInscriptionClesValeurs.size(); i++) {
							Long l = Long.parseLong(setInscriptionClesValeurs.getString(i));
							ListeRecherche<InscriptionScolaire> listeRecherche = new ListeRecherche<InscriptionScolaire>();
							listeRecherche.setQuery("*:*");
							listeRecherche.setStocker(true);
							listeRecherche.setC(InscriptionScolaire.class);
							listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							listeRecherche.initLoinListeRecherche(requeteSite);
							l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								patchSql.append(SiteContexteFrFR.SQL_setA2);
								patchSqlParams.addAll(Arrays.asList("enfantCle", l, "inscriptionCles", pk));
							}
						}
						break;
					case "removeInscriptionCles":
						{
							Long l = Long.parseLong(requeteJson.getString(methodeNom));
							ListeRecherche<InscriptionScolaire> listeRecherche = new ListeRecherche<InscriptionScolaire>();
							listeRecherche.setQuery("*:*");
							listeRecherche.setStocker(true);
							listeRecherche.setC(InscriptionScolaire.class);
							listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							listeRecherche.initLoinListeRecherche(requeteSite);
							l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								patchSql.append(SiteContexteFrFR.SQL_removeA);
								patchSqlParams.addAll(Arrays.asList("enfantCle", l, "inscriptionCles", pk));
							}
						}
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
					case "setPersonneDateNaissance":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personneDateNaissance"));
						} else {
							o2.setPersonneDateNaissance(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personneDateNaissance", o2.jsonPersonneDateNaissance(), pk));
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
					EnfantScolaire o3 = new EnfantScolaire();
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

	public void patchEnfantScolaireReponse(ListeRecherche<EnfantScolaire> listeEnfantScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeEnfantScolaire.getRequeteSite_();
		reponse200PATCHEnfantScolaire(listeEnfantScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								RequeteApi requeteApi = requeteSite.getRequeteApi_();
								requeteSite.getVertx().eventBus().publish("websocketEnfantScolaire", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PATCHEnfantScolaire(ListeRecherche<EnfantScolaire> listeEnfantScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeEnfantScolaire.getRequeteSite_();
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			JsonObject json = JsonObject.mapFrom(requeteApi);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getEnfantScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEnfantScolaire(siteContexte, operationRequete);
			sqlEnfantScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEnfantScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheEnfantScolaire(requeteSite, false, true, null, c -> {
								if(c.succeeded()) {
									ListeRecherche<EnfantScolaire> listeEnfantScolaire = c.result();
									getEnfantScolaireReponse(listeEnfantScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getEnfantScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("getEnfantScolaire a échoué. ", d.cause()));
											erreurEnfantScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEnfantScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void getEnfantScolaireReponse(ListeRecherche<EnfantScolaire> listeEnfantScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeEnfantScolaire.getRequeteSite_();
		reponse200GETEnfantScolaire(listeEnfantScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200GETEnfantScolaire(ListeRecherche<EnfantScolaire> listeEnfantScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeEnfantScolaire.getRequeteSite_();
			SolrDocumentList documentsSolr = listeEnfantScolaire.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listeEnfantScolaire.getList().stream().findFirst().orElse(null));
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void rechercheEnfantScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEnfantScolaire(siteContexte, operationRequete);
			sqlEnfantScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEnfantScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheEnfantScolaire(requeteSite, false, true, "/api/enfant", c -> {
								if(c.succeeded()) {
									ListeRecherche<EnfantScolaire> listeEnfantScolaire = c.result();
									rechercheEnfantScolaireReponse(listeEnfantScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("rechercheEnfantScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("rechercheEnfantScolaire a échoué. ", d.cause()));
											erreurEnfantScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEnfantScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void rechercheEnfantScolaireReponse(ListeRecherche<EnfantScolaire> listeEnfantScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeEnfantScolaire.getRequeteSite_();
		reponse200RechercheEnfantScolaire(listeEnfantScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200RechercheEnfantScolaire(ListeRecherche<EnfantScolaire> listeEnfantScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeEnfantScolaire.getRequeteSite_();
			QueryResponse reponseRecherche = listeEnfantScolaire.getQueryResponse();
			SolrDocumentList documentsSolr = listeEnfantScolaire.getSolrDocumentList();
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
			listeEnfantScolaire.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listeEnfantScolaire.getFields();
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
	public void pagerechercheEnfantScolaireId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		pagerechercheEnfantScolaire(operationRequete, gestionnaireEvenements);
	}

	@Override
	public void pagerechercheEnfantScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEnfantScolaire(siteContexte, operationRequete);
			sqlEnfantScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEnfantScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheEnfantScolaire(requeteSite, false, true, "/enfant", c -> {
								if(c.succeeded()) {
									ListeRecherche<EnfantScolaire> listeEnfantScolaire = c.result();
									pagerechercheEnfantScolaireReponse(listeEnfantScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("pagerechercheEnfantScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("pagerechercheEnfantScolaire a échoué. ", d.cause()));
											erreurEnfantScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEnfantScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void pagerechercheEnfantScolaireReponse(ListeRecherche<EnfantScolaire> listeEnfantScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeEnfantScolaire.getRequeteSite_();
		reponse200PageRechercheEnfantScolaire(listeEnfantScolaire, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						connexionSql.close(c -> {
							if(c.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEnfantScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PageRechercheEnfantScolaire(ListeRecherche<EnfantScolaire> listeEnfantScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeEnfantScolaire.getRequeteSite_();
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(listeEnfantScolaire.getRequeteSite_(), buffer);
			EnfantPage page = new EnfantPage();
			SolrDocument pageDocumentSolr = new SolrDocument();
			CaseInsensitiveHeaders requeteEnTetes = new CaseInsensitiveHeaders();
			requeteSite.setRequeteEnTetes(requeteEnTetes);

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/enfant");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			if(listeEnfantScolaire.size() == 1)
				requeteSite.setRequetePk(listeEnfantScolaire.get(0).getPk());
			requeteSite.setW(w);
			page.setListeEnfantScolaire(listeEnfantScolaire);
			page.setRequeteSite_(requeteSite);
			page.initLoinEnfantPage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requeteEnTetes)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<EnfantScolaire> definirIndexerEnfantScolaire(EnfantScolaire enfantScolaire, Handler<AsyncResult<EnfantScolaire>> gestionnaireEvenements) {
		Promise<EnfantScolaire> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = enfantScolaire.getRequeteSite_();
		definirEnfantScolaire(enfantScolaire, c -> {
			if(c.succeeded()) {
				attribuerEnfantScolaire(enfantScolaire, d -> {
					if(d.succeeded()) {
						indexerEnfantScolaire(enfantScolaire, e -> {
							if(e.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(enfantScolaire));
								promise.complete(enfantScolaire);
							} else {
								erreurEnfantScolaire(requeteSite, null, e);
							}
						});
					} else {
						erreurEnfantScolaire(requeteSite, null, d);
					}
				});
			} else {
				erreurEnfantScolaire(requeteSite, null, c);
			}
		});
		return promise.future();
	}

	public void creerEnfantScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<EnfantScolaire>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_creer
					, new JsonArray(Arrays.asList(EnfantScolaire.class.getCanonicalName(), utilisateurId))
					, creerAsync
			-> {
				JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = creerLigne.getLong(0);
				EnfantScolaire o = new EnfantScolaire();
				o.setPk(pk);
				o.setRequeteSite_(requeteSite);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public RequeteApi requeteApiEnfantScolaire(EnfantScolaire o) {
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
		return requeteApi;
	}

	public void erreurEnfantScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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

	public void sqlEnfantScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public RequeteSiteFrFR genererRequeteSiteFrFRPourEnfantScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete) {
		return genererRequeteSiteFrFRPourEnfantScolaire(siteContexte, operationRequete, null);
	}

	public RequeteSiteFrFR genererRequeteSiteFrFRPourEnfantScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete, JsonObject body) {
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

	public void utilisateurEnfantScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
								utilisateurEnfantScolaireDefinir(requeteSite, jsonObject, false);

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
																		erreurEnfantScolaire(requeteSite, gestionnaireEvenements, f);
																	}
																});
															} else {
																erreurEnfantScolaire(requeteSite, gestionnaireEvenements, e);
															}
														});
													} else {
														erreurEnfantScolaire(requeteSite, gestionnaireEvenements, d);
													}
												});
											} else {
												erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
											}
										});
									} else {
										erreurEnfantScolaire(requeteSite, gestionnaireEvenements, b);
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
								Boolean definir = utilisateurEnfantScolaireDefinir(requeteSite, jsonObject, true);
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
																	erreurEnfantScolaire(requeteSite, gestionnaireEvenements, f);
																}
															});
														} else {
															erreurEnfantScolaire(requeteSite, gestionnaireEvenements, e);
														}
													});
												} else {
													erreurEnfantScolaire(requeteSite, gestionnaireEvenements, d);
												}
											});
										} else {
											erreurEnfantScolaire(requeteSite, gestionnaireEvenements, c);
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

	public Boolean utilisateurEnfantScolaireDefinir(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void rechercheEnfantScolaireQ(String classeApiUriMethode, ListeRecherche<EnfantScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		listeRecherche.setQuery(varIndexe + ":" + ("*".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));
		if(!"*".equals(entiteVar)) {
			listeRecherche.setHighlight(true);
			listeRecherche.setHighlightSnippets(3);
			listeRecherche.addHighlightField(varIndexe);
			listeRecherche.setParam("hl.encoder", "html");
		}
	}

	public void rechercheEnfantScolaireFq(String classeApiUriMethode, ListeRecherche<EnfantScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
	}

	public void rechercheEnfantScolaireSort(String classeApiUriMethode, ListeRecherche<EnfantScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurIndexe));
	}

	public void rechercheEnfantScolaireRows(String classeApiUriMethode, ListeRecherche<EnfantScolaire> listeRecherche, Integer valeurRows) {
		listeRecherche.setRows(valeurRows);
	}

	public void rechercheEnfantScolaireStart(String classeApiUriMethode, ListeRecherche<EnfantScolaire> listeRecherche, Integer valeurStart) {
		listeRecherche.setStart(valeurStart);
	}

	public void rechercheEnfantScolaireVar(String classeApiUriMethode, ListeRecherche<EnfantScolaire> listeRecherche, String var, String valeur) {
		listeRecherche.getRequeteSite_().getRequeteVars().put(var, valeur);
	}

	public void rechercheEnfantScolaire(RequeteSiteFrFR requeteSite, Boolean peupler, Boolean stocker, String classeApiUriMethode, Handler<AsyncResult<ListeRecherche<EnfantScolaire>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<EnfantScolaire> listeRecherche = new ListeRecherche<EnfantScolaire>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(EnfantScolaire.class);
			listeRecherche.setRequeteSite_(requeteSite);
			if(entiteListe != null)
				listeRecherche.addFields(entiteListe);
			listeRecherche.add("json.facet", "{max_modifie:'max(modifie_indexed_date)'}");

			String id = operationRequete.getParams().getJsonObject("path").getString("id");
			if(id != null) {
				listeRecherche.addFilterQuery("(id:" + ClientUtils.escapeQueryChars(id) + " OR objetId_indexed_string:" + ClientUtils.escapeQueryChars(id) + ")");
			}

			List<String> roles = Arrays.asList("SiteAdmin");
			if(
					!CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRessource(), roles)
					&& !CollectionUtils.containsAny(requeteSite.getUtilisateurRolesRoyaume(), roles)
					) {
				listeRecherche.addFilterQuery("sessionId_indexed_string:" + ClientUtils.escapeQueryChars(Optional.ofNullable(requeteSite.getSessionId()).orElse("-----"))
						+ " OR utilisateurCles_indexed_longs:" + Optional.ofNullable(requeteSite.getUtilisateurCle()).orElse(0L));
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
								varIndexe = "*".equals(entiteVar) ? entiteVar : EnfantScolaire.varRechercheEnfantScolaire(entiteVar);
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								valeurIndexe = StringUtils.isEmpty(valeurIndexe) ? "*" : valeurIndexe;
								rechercheEnfantScolaireQ(classeApiUriMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "fq":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								varIndexe = EnfantScolaire.varIndexeEnfantScolaire(entiteVar);
								rechercheEnfantScolaireFq(classeApiUriMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "sort":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
								valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
								varIndexe = EnfantScolaire.varIndexeEnfantScolaire(entiteVar);
								rechercheEnfantScolaireSort(classeApiUriMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "start":
								valeurStart = (Integer)paramObjet;
								rechercheEnfantScolaireStart(classeApiUriMethode, listeRecherche, valeurStart);
								break;
							case "rows":
								valeurRows = (Integer)paramObjet;
								rechercheEnfantScolaireRows(classeApiUriMethode, listeRecherche, valeurRows);
								break;
							case "var":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								rechercheEnfantScolaireVar(classeApiUriMethode, listeRecherche, entiteVar, valeurIndexe);
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

	public void definirEnfantScolaire(EnfantScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void attribuerEnfantScolaire(EnfantScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void indexerEnfantScolaire(EnfantScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			o.initLoinPourClasse(requeteSite);
			o.indexerPourClasse();
			if(BooleanUtils.isFalse(Optional.ofNullable(requeteSite.getRequeteApi_()).map(RequeteApi::getEmpty).orElse(null))) {
				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEnfantScolaire(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());
				ListeRecherche<EnfantScolaire> listeRecherche = new ListeRecherche<EnfantScolaire>();
				listeRecherche.setPeupler(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(EnfantScolaire.class);
				listeRecherche.addFilterQuery("modifie_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(requeteSite.getRequeteApi_().getCree().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				listeRecherche.add("json.facet", "{inscriptionCles:{terms:{field:inscriptionCles_indexed_longs, limit:1000}}}");
				listeRecherche.setRows(1000);
				listeRecherche.initLoinListeRecherche(requeteSite2);
				List<Future> futures = new ArrayList<>();

				{
					InscriptionScolaireFrFRGenApiServiceImpl service = new InscriptionScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
					for(Long pk : o.getInscriptionCles()) {
						InscriptionScolaire o2 = new InscriptionScolaire();

						o2.setPk(pk);
						o2.setRequeteSite_(requeteSite2);
						futures.add(
							service.patchInscriptionScolaireFuture(o2, false, a -> {
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
						EnfantScolaireFrFRGenApiServiceImpl service = new EnfantScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
						List<Future> futures2 = new ArrayList<>();
						for(EnfantScolaire o2 : listeRecherche.getList()) {
							futures2.add(
								service.patchEnfantScolaireFuture(o2, false, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("EnfantScolaire %s rechargé. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("EnfantScolaire %s a échoué. ", o2.getPk()));
										gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Recharger EnfantScolaire a réussi. ");
								gestionnaireEvenements.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Recharger relations a échoué. ", b.cause());
								erreurEnfantScolaire(requeteSite2, gestionnaireEvenements, b);
							}
						});
					} else {
						LOGGER.error("Recharger relations a échoué. ", a.cause());
						erreurEnfantScolaire(requeteSite2, gestionnaireEvenements, a);
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
