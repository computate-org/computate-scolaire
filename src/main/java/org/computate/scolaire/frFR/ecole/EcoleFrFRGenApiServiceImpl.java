package org.computate.scolaire.frFR.ecole;

import org.computate.scolaire.frFR.annee.AnneeScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.annee.AnneeScolaire;
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
 * NomCanonique.enUS: org.computate.scolaire.enUS.school.SchoolEnUSGenApiServiceImpl
 **/
public class EcoleFrFRGenApiServiceImpl implements EcoleFrFRGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(EcoleFrFRGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "EcoleFrFRApiServiceImpl";

	protected SiteContexteFrFR siteContexte;

	public EcoleFrFRGenApiServiceImpl(SiteContexteFrFR siteContexte) {
		this.siteContexte = siteContexte;
	}

	// POST //

	@Override
	public void postEcole(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("postEcole a démarré. "));

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

			sqlEcole(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEcole(requeteSite, b -> {
						if(b.succeeded()) {
							RequeteApi requeteApi = new RequeteApi();
							requeteApi.setRows(1);
							requeteApi.setNumFound(1L);
							requeteApi.setNumPATCH(0L);
							requeteApi.initLoinRequeteApi(requeteSite);
							requeteSite.setRequeteApi_(requeteApi);
							requeteSite.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
							postEcoleFuture(requeteSite, false, c -> {
								if(c.succeeded()) {
									Ecole ecole = c.result();
									requeteApiEcole(ecole);
									postEcoleReponse(ecole, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postEcole a réussi. "));
										} else {
											LOGGER.error(String.format("postEcole a échoué. ", d.cause()));
											erreurEcole(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("postEcole a échoué. ", c.cause()));
									erreurEcole(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEcole(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public Future<Ecole> postEcoleFuture(RequeteSiteFrFR requeteSite, Boolean inheritPk, Handler<AsyncResult<Ecole>> gestionnaireEvenements) {
		Promise<Ecole> promise = Promise.promise();
		try {
			creerEcole(requeteSite, a -> {
				if(a.succeeded()) {
					Ecole ecole = a.result();
					sqlPOSTEcole(ecole, inheritPk, b -> {
						if(b.succeeded()) {
							definirIndexerEcole(ecole, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(ecole));
									promise.complete(ecole);
								} else {
									erreurEcole(requeteSite, null, c);
								}
							});
						} else {
							erreurEcole(requeteSite, null, b);
						}
					});
				} else {
					erreurEcole(requeteSite, null, a);
				}
			});
		} catch(Exception e) {
			erreurEcole(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTEcole(Ecole o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
					case "anneeCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
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
									postSqlParams.addAll(Arrays.asList("anneeCles", pk, "ecoleCle", l));
								}
							}
						}
						break;
					case "ecoleNom":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ecoleNom", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleNumeroTelephone":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ecoleNumeroTelephone", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleAdministrateurNom":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ecoleAdministrateurNom", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleMail":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ecoleMail", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleEmplacement":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ecoleEmplacement", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleAddresse":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ecoleAddresse", jsonObject.getString(entiteVar), pk));
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

	public void postEcoleReponse(Ecole ecole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = ecole.getRequeteSite_();
		reponse200POSTEcole(ecole, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("postEcole sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("postEcole sql close. "));
								RequeteApi requeteApi = requeteApiEcole(ecole);
								ecole.requeteApiEcole();
								requeteSite.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEcole(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEcole(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEcole(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200POSTEcole(Ecole o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			JsonObject json = JsonObject.mapFrom(o);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchEcole(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("patchEcole a démarré. "));

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

			sqlEcole(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEcole(requeteSite, b -> {
						if(b.succeeded()) {
							patchEcoleReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete, body);
											try {
												rechercheEcole(requeteSite2, false, true, "/api/ecole", "PATCH", d -> {
													if(d.succeeded()) {
														ListeRecherche<Ecole> listeEcole = d.result();
														RequeteApi requeteApi = new RequeteApi();
														requeteApi.setRows(listeEcole.getRows());
														requeteApi.setNumFound(listeEcole.getQueryResponse().getResults().getNumFound());
														requeteApi.setNumPATCH(0L);
														requeteApi.initLoinRequeteApi(requeteSite2);
														requeteSite2.setRequeteApi_(requeteApi);
														requeteSite2.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
														SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeEcole.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
														Date date = null;
														if(facets != null)
															date = (Date)facets.get("max_modifie");
														String dt;
														if(date == null)
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
														else
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
														listeEcole.addFilterQuery(String.format("modifie_indexed_date:[* TO %s]", dt));

														Ecole o = listeEcole.getList().stream().findFirst().orElse(null);
														if(o != null) {
															requeteApi.setPk(o.getPk());
															requeteApi.setOriginal(o);
															requeteApiEcole(o);
															o.requeteApiEcole();
														}
														sqlEcole(requeteSite2, e -> {
															if(e.succeeded()) {
																try {
																	listePATCHEcole(requeteApi, listeEcole, dt, f -> {
																		if(f.succeeded()) {
																			patchEcoleReponse(requeteSite2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("patchEcole a réussi. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("patchEcole a échoué. ", g.cause()));
																					erreurEcole(requeteSite2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("patchEcole a échoué. ", f.cause()));
																			erreurEcole(requeteSite2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("patchEcole a échoué. ", ex));
																	erreurEcole(requeteSite2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("patchEcole a échoué. ", e.cause()));
																erreurEcole(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("patchEcole a échoué. ", d.cause()));
														erreurEcole(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("patchEcole a échoué. ", ex));
												erreurEcole(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("patchEcole a échoué. ", c.cause()));
									erreurEcole(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEcole(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePATCHEcole(RequeteApi requeteApi, ListeRecherche<Ecole> listeEcole, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
		listeEcole.getList().forEach(o -> {
			futures.add(
				patchEcoleFuture(o, false, a -> {
					if(a.succeeded()) {
							Ecole ecole = a.result();
							requeteApiEcole(ecole);
					} else {
						erreurEcole(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeEcole.size());
				if(listeEcole.next(dt)) {
					requeteSite.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
					listePATCHEcole(requeteApi, listeEcole, dt, gestionnaireEvenements);
				} else {
					reponse200PATCHEcole(requeteSite, gestionnaireEvenements);
				}
			} else {
				erreurEcole(listeEcole.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<Ecole> patchEcoleFuture(Ecole o, Boolean inheritPk, Handler<AsyncResult<Ecole>> gestionnaireEvenements) {
		Promise<Ecole> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			sqlPATCHEcole(o, inheritPk, a -> {
				if(a.succeeded()) {
					Ecole ecole = a.result();
					definirEcole(ecole, b -> {
						if(b.succeeded()) {
							attribuerEcole(ecole, c -> {
								if(c.succeeded()) {
									indexerEcole(ecole, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(ecole));
											promise.complete(ecole);
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
			erreurEcole(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHEcole(Ecole o, Boolean inheritPk, Handler<AsyncResult<Ecole>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject requeteJson = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = requeteJson.fieldNames();
			Ecole o2 = new Ecole();

			patchSql.append(SiteContexteFrFR.SQL_modifier);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.frFR.ecole.Ecole"));
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
					case "addAnneeCles":
						{
							Long l = Long.parseLong(requeteJson.getString(methodeNom));
							if(l != null) {
								ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AnneeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("anneeCles", pk, "ecoleCle", l));
								}
							}
						}
						break;
					case "addAllAnneeCles":
						JsonArray addAllAnneeClesValeurs = requeteJson.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllAnneeClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllAnneeClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AnneeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("anneeCles", pk, "ecoleCle", l));
								}
							}
						}
						break;
					case "setAnneeCles":
						JsonArray setAnneeClesValeurs = requeteJson.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("anneeCles", pk, "ecoleCle"));
						for(Integer i = 0; i <  setAnneeClesValeurs.size(); i++) {
							Long l = Long.parseLong(setAnneeClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AnneeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("anneeCles", pk, "ecoleCle", l));
								}
							}
						}
						break;
					case "removeAnneeCles":
						{
							Long l = Long.parseLong(requeteJson.getString(methodeNom));
							if(l != null) {
								ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AnneeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("anneeCles", pk, "ecoleCle", l));
								}
							}
						}
						break;
					case "setEcoleNom":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "ecoleNom"));
						} else {
							o2.setEcoleNom(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("ecoleNom", o2.jsonEcoleNom(), pk));
						}
						break;
					case "setEcoleNumeroTelephone":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "ecoleNumeroTelephone"));
						} else {
							o2.setEcoleNumeroTelephone(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("ecoleNumeroTelephone", o2.jsonEcoleNumeroTelephone(), pk));
						}
						break;
					case "setEcoleAdministrateurNom":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "ecoleAdministrateurNom"));
						} else {
							o2.setEcoleAdministrateurNom(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("ecoleAdministrateurNom", o2.jsonEcoleAdministrateurNom(), pk));
						}
						break;
					case "setEcoleMail":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "ecoleMail"));
						} else {
							o2.setEcoleMail(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("ecoleMail", o2.jsonEcoleMail(), pk));
						}
						break;
					case "setEcoleEmplacement":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "ecoleEmplacement"));
						} else {
							o2.setEcoleEmplacement(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("ecoleEmplacement", o2.jsonEcoleEmplacement(), pk));
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
				}
			}
			connexionSql.queryWithParams(
					patchSql.toString()
					, new JsonArray(patchSqlParams)
					, patchAsync
			-> {
				if(patchAsync.succeeded()) {
					Ecole o3 = new Ecole();
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

	public void patchEcoleReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PATCHEcole(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("patchEcole sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("patchEcole sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEcole(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEcole(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEcole(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PATCHEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getEcole(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete);
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

			sqlEcole(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEcole(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheEcole(requeteSite, false, true, "/api/ecole/{id}", "GET", c -> {
								if(c.succeeded()) {
									ListeRecherche<Ecole> listeEcole = c.result();
									getEcoleReponse(listeEcole, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getEcole a réussi. "));
										} else {
											LOGGER.error(String.format("getEcole a échoué. ", d.cause()));
											erreurEcole(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("getEcole a échoué. ", c.cause()));
									erreurEcole(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEcole(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void getEcoleReponse(ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
		reponse200GETEcole(listeEcole, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("getEcole sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("getEcole sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEcole(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEcole(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEcole(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200GETEcole(ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
			SolrDocumentList documentsSolr = listeEcole.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listeEcole.getList().stream().findFirst().orElse(null));
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void rechercheEcole(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete);
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

			sqlEcole(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEcole(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheEcole(requeteSite, false, true, "/api/ecole", "Recherche", c -> {
								if(c.succeeded()) {
									ListeRecherche<Ecole> listeEcole = c.result();
									rechercheEcoleReponse(listeEcole, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("rechercheEcole a réussi. "));
										} else {
											LOGGER.error(String.format("rechercheEcole a échoué. ", d.cause()));
											erreurEcole(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("rechercheEcole a échoué. ", c.cause()));
									erreurEcole(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEcole(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void rechercheEcoleReponse(ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
		reponse200RechercheEcole(listeEcole, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("rechercheEcole sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("rechercheEcole sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEcole(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEcole(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEcole(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200RechercheEcole(ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
			QueryResponse reponseRecherche = listeEcole.getQueryResponse();
			SolrDocumentList documentsSolr = listeEcole.getSolrDocumentList();
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
			listeEcole.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listeEcole.getFields();
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

	// PUTImport //

	@Override
	public void putimportEcole(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putimportEcole a démarré. "));

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

			sqlEcole(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEcole(requeteSite, b -> {
						if(b.succeeded()) {
							putimportEcoleReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete, body);
											try {
												RequeteApi requeteApi = new RequeteApi();
												JsonArray jsonArray = Optional.ofNullable(requeteSite2.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												requeteApi.setRows(jsonArray.size());
												requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite2);
												requeteSite2.setRequeteApi_(requeteApi);
												requeteSite2.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
												sqlEcole(requeteSite2, d -> {
													if(d.succeeded()) {
														listePUTImportEcole(requeteApi, requeteSite2, e -> {
															if(e.succeeded()) {
																putimportEcoleReponse(requeteSite2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putimportEcole a réussi. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putimportEcole a échoué. ", f.cause()));
																		erreurEcole(requeteSite2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putimportEcole a échoué. ", e.cause()));
																erreurEcole(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putimportEcole a échoué. ", d.cause()));
														erreurEcole(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putimportEcole a échoué. ", ex));
												erreurEcole(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putimportEcole a échoué. ", c.cause()));
									erreurEcole(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEcole(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTImportEcole(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		jsonArray.forEach(obj -> {
			JsonObject json = (JsonObject)obj;

			json.put("inheritPk", json.getValue("pk"));

			RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEcole(siteContexte, requeteSite.getOperationRequete(), json);
			requeteSite2.setConnexionSql(requeteSite.getConnexionSql());

			ListeRecherche<Ecole> listeRecherche = new ListeRecherche<Ecole>();
			listeRecherche.setStocker(true);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(Ecole.class);
			listeRecherche.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
			listeRecherche.initLoinPourClasse(requeteSite2);

			if(listeRecherche.size() == 1) {
				Ecole o = listeRecherche.getList().stream().findFirst().orElse(null);
				JsonObject json2 = new JsonObject();
				for(String f : json.fieldNames()) {
					json2.put("set" + StringUtils.capitalize(f), json.getValue(f));
				}
				if(o != null) {
					for(String f : o.getSauvegardes()) {
						if(!json.fieldNames().contains(f))
							json2.putNull("set" + StringUtils.capitalize(f));
					}
					requeteSite2.setObjetJson(json2);
					futures.add(
						patchEcoleFuture(o, true, a -> {
							if(a.succeeded()) {
								Ecole ecole = a.result();
								requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
								requeteSite2.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
							} else {
								erreurEcole(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			} else {
				futures.add(
					postEcoleFuture(requeteSite2, true, a -> {
						if(a.succeeded()) {
							Ecole ecole = a.result();
							requeteApiEcole(ecole);
						} else {
							erreurEcole(requeteSite2, gestionnaireEvenements, a);
						}
					})
				);
			}
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
							requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
							requeteSite.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
				reponse200PUTImportEcole(requeteSite, gestionnaireEvenements);
			} else {
				erreurEcole(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public void putimportEcoleReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PUTImportEcole(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("putimportEcole sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("putimportEcole sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEcole(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEcole(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEcole(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTImportEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTFusion //

	@Override
	public void putfusionEcole(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putfusionEcole a démarré. "));

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

			sqlEcole(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEcole(requeteSite, b -> {
						if(b.succeeded()) {
							putfusionEcoleReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete, body);
											try {
												RequeteApi requeteApi = new RequeteApi();
												JsonArray jsonArray = Optional.ofNullable(requeteSite2.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												requeteApi.setRows(jsonArray.size());
												requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite2);
												requeteSite2.setRequeteApi_(requeteApi);
												requeteSite2.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
												sqlEcole(requeteSite2, d -> {
													if(d.succeeded()) {
														listePUTFusionEcole(requeteApi, requeteSite2, e -> {
															if(e.succeeded()) {
																putfusionEcoleReponse(requeteSite2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putfusionEcole a réussi. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putfusionEcole a échoué. ", f.cause()));
																		erreurEcole(requeteSite2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putfusionEcole a échoué. ", e.cause()));
																erreurEcole(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putfusionEcole a échoué. ", d.cause()));
														erreurEcole(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putfusionEcole a échoué. ", ex));
												erreurEcole(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putfusionEcole a échoué. ", c.cause()));
									erreurEcole(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEcole(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTFusionEcole(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		jsonArray.forEach(obj -> {
			JsonObject json = (JsonObject)obj;

			json.put("inheritPk", json.getValue("pk"));

			RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEcole(siteContexte, requeteSite.getOperationRequete(), json);
			requeteSite2.setConnexionSql(requeteSite.getConnexionSql());

			ListeRecherche<Ecole> listeRecherche = new ListeRecherche<Ecole>();
			listeRecherche.setStocker(true);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(Ecole.class);
			listeRecherche.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
			listeRecherche.initLoinPourClasse(requeteSite2);

			if(listeRecherche.size() == 1) {
				Ecole o = listeRecherche.getList().stream().findFirst().orElse(null);
				JsonObject json2 = new JsonObject();
				for(String f : json.fieldNames()) {
					json2.put("set" + StringUtils.capitalize(f), json.getValue(f));
				}
				if(o != null) {
					for(String f : o.getSauvegardes()) {
						if(!json.fieldNames().contains(f))
							json2.putNull("set" + StringUtils.capitalize(f));
					}
					requeteSite2.setObjetJson(json2);
					futures.add(
						patchEcoleFuture(o, false, a -> {
							if(a.succeeded()) {
								Ecole ecole = a.result();
								requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
								requeteSite2.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
							} else {
								erreurEcole(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			} else {
				futures.add(
					postEcoleFuture(requeteSite2, false, a -> {
						if(a.succeeded()) {
							Ecole ecole = a.result();
							requeteApiEcole(ecole);
						} else {
							erreurEcole(requeteSite2, gestionnaireEvenements, a);
						}
					})
				);
			}
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
							requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
							requeteSite.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
				reponse200PUTFusionEcole(requeteSite, gestionnaireEvenements);
			} else {
				erreurEcole(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public void putfusionEcoleReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PUTFusionEcole(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("putfusionEcole sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("putfusionEcole sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEcole(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEcole(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEcole(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTFusionEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTCopie //

	@Override
	public void putcopieEcole(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putcopieEcole a démarré. "));

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

			sqlEcole(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEcole(requeteSite, b -> {
						if(b.succeeded()) {
							putcopieEcoleReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete, body);
											try {
												rechercheEcole(requeteSite2, false, true, "/api/ecole/copie", "PUTCopie", d -> {
													if(d.succeeded()) {
														ListeRecherche<Ecole> listeEcole = d.result();
														RequeteApi requeteApi = new RequeteApi();
														requeteApi.setRows(listeEcole.getRows());
														requeteApi.setNumFound(listeEcole.getQueryResponse().getResults().getNumFound());
														requeteApi.setNumPATCH(0L);
														requeteApi.initLoinRequeteApi(requeteSite2);
														requeteSite2.setRequeteApi_(requeteApi);
														requeteSite2.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
														sqlEcole(requeteSite2, e -> {
															if(e.succeeded()) {
																try {
																	listePUTCopieEcole(requeteApi, listeEcole, f -> {
																		if(f.succeeded()) {
																			putcopieEcoleReponse(requeteSite2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("putcopieEcole a réussi. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("putcopieEcole a échoué. ", g.cause()));
																					erreurEcole(requeteSite2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("putcopieEcole a échoué. ", f.cause()));
																			erreurEcole(requeteSite2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("putcopieEcole a échoué. ", ex));
																	erreurEcole(requeteSite2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("putcopieEcole a échoué. ", e.cause()));
																erreurEcole(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putcopieEcole a échoué. ", d.cause()));
														erreurEcole(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putcopieEcole a échoué. ", ex));
												erreurEcole(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putcopieEcole a échoué. ", c.cause()));
									erreurEcole(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEcole(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void listePUTCopieEcole(RequeteApi requeteApi, ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
		listeEcole.getList().forEach(o -> {
			futures.add(
				putcopieEcoleFuture(requeteSite, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						Ecole ecole = a.result();
						requeteApiEcole(ecole);
					} else {
						erreurEcole(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeEcole.size());
				if(listeEcole.next()) {
					requeteSite.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
					listePUTCopieEcole(requeteApi, listeEcole, gestionnaireEvenements);
				} else {
					reponse200PUTCopieEcole(requeteSite, gestionnaireEvenements);
				}
			} else {
				erreurEcole(listeEcole.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<Ecole> putcopieEcoleFuture(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Handler<AsyncResult<Ecole>> gestionnaireEvenements) {
		Promise<Ecole> promise = Promise.promise();
		try {

			jsonObject.put("sauvegardes", Optional.ofNullable(jsonObject.getJsonArray("sauvegardes")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("sauvegardes").add(o.getKey());
			});

			creerEcole(requeteSite, a -> {
				if(a.succeeded()) {
					Ecole ecole = a.result();
					sqlPUTCopieEcole(ecole, jsonObject, b -> {
						if(b.succeeded()) {
							definirEcole(ecole, c -> {
								if(c.succeeded()) {
									attribuerEcole(ecole, d -> {
										if(d.succeeded()) {
											indexerEcole(ecole, e -> {
												if(e.succeeded()) {
													gestionnaireEvenements.handle(Future.succeededFuture(ecole));
													promise.complete(ecole);
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
			erreurEcole(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopieEcole(Ecole o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
					case "anneeCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContexteFrFR.SQL_addA);
							putSqlParams.addAll(Arrays.asList("anneeCles", pk, "ecoleCle", l));
						}
						break;
					case "ecoleNom":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("ecoleNom", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleNumeroTelephone":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("ecoleNumeroTelephone", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleAdministrateurNom":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("ecoleAdministrateurNom", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleMail":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("ecoleMail", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleEmplacement":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("ecoleEmplacement", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleAddresse":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("ecoleAddresse", jsonObject.getString(entiteVar), pk));
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

	public void putcopieEcoleReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		reponse200PUTCopieEcole(requeteSite, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("putcopieEcole sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("putcopieEcole sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEcole(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEcole(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEcole(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PUTCopieEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PageRecherche //

	@Override
	public void pagerechercheEcoleId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		pagerechercheEcole(operationRequete, gestionnaireEvenements);
	}

	@Override
	public void pagerechercheEcole(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete);
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

			sqlEcole(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEcole(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheEcole(requeteSite, false, true, "/ecole", "PageRecherche", c -> {
								if(c.succeeded()) {
									ListeRecherche<Ecole> listeEcole = c.result();
									pagerechercheEcoleReponse(listeEcole, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("pagerechercheEcole a réussi. "));
										} else {
											LOGGER.error(String.format("pagerechercheEcole a échoué. ", d.cause()));
											erreurEcole(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("pagerechercheEcole a échoué. ", c.cause()));
									erreurEcole(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurEcole(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(e));
		}
	}


	public void pagerechercheEcoleReponse(ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
		reponse200PageRechercheEcole(listeEcole, a -> {
			if(a.succeeded()) {
				SQLConnection connexionSql = requeteSite.getConnexionSql();
				connexionSql.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("pagerechercheEcole sql commit. "));
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("pagerechercheEcole sql close. "));
								gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
							} else {
								erreurEcole(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEcole(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEcole(requeteSite, gestionnaireEvenements, a);
			}
		});
	}
	public void reponse200PageRechercheEcole(ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(listeEcole.getRequeteSite_(), buffer);
			EcolePage page = new EcolePage();
			SolrDocument pageDocumentSolr = new SolrDocument();
			CaseInsensitiveHeaders requeteEnTetes = new CaseInsensitiveHeaders();
			requeteSite.setRequeteEnTetes(requeteEnTetes);

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/ecole");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			if(listeEcole.size() == 1)
				requeteSite.setRequetePk(listeEcole.get(0).getPk());
			requeteSite.setW(w);
			page.setListeEcole(listeEcole);
			page.setRequeteSite_(requeteSite);
			page.initLoinEcolePage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requeteEnTetes)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<Ecole> definirIndexerEcole(Ecole ecole, Handler<AsyncResult<Ecole>> gestionnaireEvenements) {
		Promise<Ecole> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = ecole.getRequeteSite_();
		definirEcole(ecole, c -> {
			if(c.succeeded()) {
				attribuerEcole(ecole, d -> {
					if(d.succeeded()) {
						indexerEcole(ecole, e -> {
							if(e.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(ecole));
								promise.complete(ecole);
							} else {
								erreurEcole(requeteSite, null, e);
							}
						});
					} else {
						erreurEcole(requeteSite, null, d);
					}
				});
			} else {
				erreurEcole(requeteSite, null, c);
			}
		});
		return promise.future();
	}

	public void creerEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<Ecole>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_creer
					, new JsonArray(Arrays.asList(Ecole.class.getCanonicalName(), utilisateurId))
					, creerAsync
			-> {
				JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = creerLigne.getLong(0);
				Ecole o = new Ecole();
				o.setPk(pk);
				o.setRequeteSite_(requeteSite);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public RequeteApi requeteApiEcole(Ecole o) {
		RequeteApi requeteApi = o.getRequeteSite_().getRequeteApi_();
		if(requeteApi != null) {
			List<Long> pks = requeteApi.getPks();
			List<String> classes = requeteApi.getClasses();
			for(Long pk : o.getAnneeCles()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("AnneeScolaire");
				}
			}
		}
		return requeteApi;
	}

	public void erreurEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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

	public void sqlEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public RequeteSiteFrFR genererRequeteSiteFrFRPourEcole(SiteContexteFrFR siteContexte, OperationRequest operationRequete) {
		return genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete, null);
	}

	public RequeteSiteFrFR genererRequeteSiteFrFRPourEcole(SiteContexteFrFR siteContexte, OperationRequest operationRequete, JsonObject body) {
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

	public void utilisateurEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
								utilisateurEcoleDefinir(requeteSite, jsonObject, false);

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
																		erreurEcole(requeteSite, gestionnaireEvenements, f);
																	}
																});
															} else {
																erreurEcole(requeteSite, gestionnaireEvenements, e);
															}
														});
													} else {
														erreurEcole(requeteSite, gestionnaireEvenements, d);
													}
												});
											} else {
												erreurEcole(requeteSite, gestionnaireEvenements, c);
											}
										});
									} else {
										erreurEcole(requeteSite, gestionnaireEvenements, b);
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
								Boolean definir = utilisateurEcoleDefinir(requeteSite, jsonObject, true);
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
																	erreurEcole(requeteSite, gestionnaireEvenements, f);
																}
															});
														} else {
															erreurEcole(requeteSite, gestionnaireEvenements, e);
														}
													});
												} else {
													erreurEcole(requeteSite, gestionnaireEvenements, d);
												}
											});
										} else {
											erreurEcole(requeteSite, gestionnaireEvenements, c);
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

	public Boolean utilisateurEcoleDefinir(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void rechercheEcoleQ(String uri, String apiMethode, ListeRecherche<Ecole> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		listeRecherche.setQuery(varIndexe + ":" + ("*".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));
		if(!"*".equals(entiteVar)) {
			listeRecherche.setHighlight(true);
			listeRecherche.setHighlightSnippets(3);
			listeRecherche.addHighlightField(varIndexe);
			listeRecherche.setParam("hl.encoder", "html");
		}
	}

	public void rechercheEcoleFq(String uri, String apiMethode, ListeRecherche<Ecole> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
	}

	public void rechercheEcoleSort(String uri, String apiMethode, ListeRecherche<Ecole> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurIndexe));
	}

	public void rechercheEcoleRows(String uri, String apiMethode, ListeRecherche<Ecole> listeRecherche, Integer valeurRows) {
			listeRecherche.setRows(apiMethode != null && apiMethode.contains("Recherche") ? valeurRows : 10);
	}

	public void rechercheEcoleStart(String uri, String apiMethode, ListeRecherche<Ecole> listeRecherche, Integer valeurStart) {
		listeRecherche.setStart(valeurStart);
	}

	public void rechercheEcoleVar(String uri, String apiMethode, ListeRecherche<Ecole> listeRecherche, String var, String valeur) {
		listeRecherche.getRequeteSite_().getRequeteVars().put(var, valeur);
	}

	public void rechercheEcole(RequeteSiteFrFR requeteSite, Boolean peupler, Boolean stocker, String uri, String apiMethode, Handler<AsyncResult<ListeRecherche<Ecole>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<Ecole> listeRecherche = new ListeRecherche<Ecole>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(Ecole.class);
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
								varIndexe = "*".equals(entiteVar) ? entiteVar : Ecole.varRechercheEcole(entiteVar);
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								valeurIndexe = StringUtils.isEmpty(valeurIndexe) ? "*" : valeurIndexe;
								rechercheEcoleQ(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "fq":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								varIndexe = Ecole.varIndexeEcole(entiteVar);
								rechercheEcoleFq(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "sort":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
								valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
								varIndexe = Ecole.varIndexeEcole(entiteVar);
								rechercheEcoleSort(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "start":
								valeurStart = (Integer)paramObjet;
								rechercheEcoleStart(uri, apiMethode, listeRecherche, valeurStart);
								break;
							case "rows":
								valeurRows = (Integer)paramObjet;
								rechercheEcoleRows(uri, apiMethode, listeRecherche, valeurRows);
								break;
							case "var":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								rechercheEcoleVar(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe);
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

	public void definirEcole(Ecole o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void attribuerEcole(Ecole o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void indexerEcole(Ecole o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			o.initLoinPourClasse(requeteSite);
			o.indexerPourClasse();
			if(BooleanUtils.isFalse(Optional.ofNullable(requeteSite.getRequeteApi_()).map(RequeteApi::getEmpty).orElse(null))) {
				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEcole(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());
				ListeRecherche<Ecole> listeRecherche = new ListeRecherche<Ecole>();
				listeRecherche.setPeupler(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(Ecole.class);
				listeRecherche.addFilterQuery("modifie_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(requeteSite.getRequeteApi_().getCree().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				listeRecherche.add("json.facet", "{anneeCles:{terms:{field:anneeCles_indexed_longs, limit:1000}}}");
				listeRecherche.setRows(1000);
				listeRecherche.initLoinListeRecherche(requeteSite2);
				List<Future> futures = new ArrayList<>();

				{
					AnneeScolaireFrFRGenApiServiceImpl service = new AnneeScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
					for(Long pk : o.getAnneeCles()) {
						AnneeScolaire o2 = new AnneeScolaire();

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

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						LOGGER.info("Recharger relations a réussi. ");
						EcoleFrFRGenApiServiceImpl service = new EcoleFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
						List<Future> futures2 = new ArrayList<>();
						for(Ecole o2 : listeRecherche.getList()) {
							futures2.add(
								service.patchEcoleFuture(o2, false, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("Ecole %s rechargé. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("Ecole %s a échoué. ", o2.getPk()));
										gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Recharger Ecole a réussi. ");
								gestionnaireEvenements.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Recharger relations a échoué. ", b.cause());
								erreurEcole(requeteSite2, gestionnaireEvenements, b);
							}
						});
					} else {
						LOGGER.error("Recharger relations a échoué. ", a.cause());
						erreurEcole(requeteSite2, gestionnaireEvenements, a);
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
