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
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.api.validation.HTTPRequestValidationHandler;
import io.vertx.ext.web.api.validation.ParameterTypeValidator;
import io.vertx.ext.web.api.validation.ValidationException;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Transaction;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Tuple;
import io.vertx.sqlclient.Row;
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
import org.computate.scolaire.frFR.utilisateur.UtilisateurSiteFrFRApiServiceImpl;
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
							requeteApi.setPk(ecole.getPk());
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
					LOGGER.error(String.format("postEcole a échoué. ", b.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postEcole a échoué. ", ex));
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public Future<Ecole> postEcoleFuture(RequeteSiteFrFR requeteSite, Boolean inheritPk, Handler<AsyncResult<Ecole>> gestionnaireEvenements) {
		Promise<Ecole> promise = Promise.promise();
		try {
			sqlConnexionEcole(requeteSite, a -> {
				if(a.succeeded()) {
					sqlTransactionEcole(requeteSite, b -> {
						if(b.succeeded()) {
							creerEcole(requeteSite, c -> {
								if(c.succeeded()) {
									Ecole ecole = c.result();
									sqlPOSTEcole(ecole, inheritPk, d -> {
										if(d.succeeded()) {
											definirIndexerEcole(ecole, e -> {
												if(e.succeeded()) {
													RequeteApi requeteApi = requeteSite.getRequeteApi_();
													if(requeteApi != null) {
														requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
														ecole.requeteApiEcole();
														requeteSite.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
													}
													gestionnaireEvenements.handle(Future.succeededFuture(ecole));
													promise.complete(ecole);
												} else {
													LOGGER.error(String.format("postEcoleFuture a échoué. ", e.cause()));
													gestionnaireEvenements.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											LOGGER.error(String.format("postEcoleFuture a échoué. ", d.cause()));
											gestionnaireEvenements.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("postEcoleFuture a échoué. ", c.cause()));
									gestionnaireEvenements.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("postEcoleFuture a échoué. ", b.cause()));
							gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("postEcoleFuture a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("postEcoleFuture a échoué. ", e));
			erreurEcole(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTEcole(Ecole o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Transaction tx = requeteSite.getTx();
			Long pk = o.getPk();
			JsonObject jsonObject = requeteSite.getObjetJson();
			List<Future> futures = new ArrayList<>();

			if(requeteSite.getSessionId() != null) {
				futures.add(Future.future(a -> {
					tx.preparedQuery(SiteContexteFrFR.SQL_setD
				, Tuple.of(pk, "sessionId", requeteSite.getSessionId())
							, b
					-> {
						if(b.succeeded())
							a.handle(Future.succeededFuture());
						else
							a.handle(Future.failedFuture(b.cause()));
					});
				}));
			}
			if(requeteSite.getUtilisateurId() != null) {
				futures.add(Future.future(a -> {
					tx.preparedQuery(SiteContexteFrFR.SQL_setD
				, Tuple.of(pk, "utilisateurId", requeteSite.getUtilisateurId())
							, b
					-> {
						if(b.succeeded())
							a.handle(Future.succeededFuture());
						else
							a.handle(Future.failedFuture(b.cause()));
					});
				}));
			}
			if(requeteSite.getUtilisateurCle() != null) {
				futures.add(Future.future(a -> {
					tx.preparedQuery(SiteContexteFrFR.SQL_setD
				, Tuple.of(pk, "utilisateurCle", requeteSite.getUtilisateurCle().toString())
							, b
					-> {
						if(b.succeeded())
							a.handle(Future.succeededFuture());
						else
							a.handle(Future.failedFuture(b.cause()));
					});
				}));
			}

			if(jsonObject != null) {
				Set<String> entiteVars = jsonObject.fieldNames();
				for(String entiteVar : entiteVars) {
					switch(entiteVar) {
					case "inheritPk":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "inheritPk", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.inheritPk a échoué", b.cause())));
							});
						}));
						break;
					case "archive":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "archive", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.archive a échoué", b.cause())));
							});
						}));
						break;
					case "supprime":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "supprime", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.supprime a échoué", b.cause())));
							});
						}));
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
								Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(pk, "anneeCles", l2, "ecoleCle")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur Ecole.anneeCles a échoué", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("AnneeScolaire");
									}
								}
							}
						}
						break;
					case "ecoleNom":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleNom", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleNom a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleNumeroTelephone":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleNumeroTelephone", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleNumeroTelephone a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleNumero":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleNumero", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleNumero a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleAdministrateurNom":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleAdministrateurNom", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleAdministrateurNom a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleMail":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleMail", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleMail a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleMailDe":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleMailDe", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleMailDe a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleMailA":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleMailA", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleMailA a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleEmplacement":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleEmplacement", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleEmplacement a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleAddresse":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleAddresse", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleAddresse a échoué", b.cause())));
							});
						}));
						break;
					}
				}
			}
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture());
				} else {
					LOGGER.error(String.format("sqlPOSTEcole a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPOSTEcole a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void postEcoleReponse(Ecole ecole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = ecole.getRequeteSite_();
		try {
			reponse200POSTEcole(ecole, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("postEcoleReponse a échoué. ", a.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postEcoleReponse a échoué. ", ex));
			erreurEcole(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200POSTEcole(Ecole o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			JsonObject json = JsonObject.mapFrom(o);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200POSTEcole a échoué. ", e));
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

			utilisateurEcole(requeteSite, b -> {
				if(b.succeeded()) {
					patchEcoleReponse(requeteSite, c -> {
						if(c.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
							WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
							executeurTravailleur.executeBlocking(
								blockingCodeHandler -> {
									try {
										rechercheEcole(requeteSite, false, true, "/api/ecole", "PATCH", d -> {
											if(d.succeeded()) {
												ListeRecherche<Ecole> listeEcole = d.result();
												RequeteApi requeteApi = new RequeteApi();
												requeteApi.setRows(listeEcole.getRows());
												requeteApi.setNumFound(listeEcole.getQueryResponse().getResults().getNumFound());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite);
												requeteSite.setRequeteApi_(requeteApi);
												requeteSite.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
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

												try {
													listePATCHEcole(requeteApi, listeEcole, dt, e -> {
														if(e.succeeded()) {
															patchEcoleReponse(requeteSite, f -> {
																if(f.succeeded()) {
																	LOGGER.info(String.format("patchEcole a réussi. "));
																	blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																} else {
																	LOGGER.error(String.format("patchEcole a échoué. ", f.cause()));
																	erreurEcole(requeteSite, null, f);
																}
															});
														} else {
															LOGGER.error(String.format("patchEcole a échoué. ", e.cause()));
															erreurEcole(requeteSite, null, e);
														}
													});
												} catch(Exception ex) {
													LOGGER.error(String.format("patchEcole a échoué. ", ex));
													erreurEcole(requeteSite, null, Future.failedFuture(ex));
												}
											} else {
												LOGGER.error(String.format("patchEcole a échoué. ", d.cause()));
												erreurEcole(requeteSite, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("patchEcole a échoué. ", ex));
										erreurEcole(requeteSite, null, Future.failedFuture(ex));
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
					LOGGER.error(String.format("patchEcole a échoué. ", b.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchEcole a échoué. ", ex));
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePATCHEcole(RequeteApi requeteApi, ListeRecherche<Ecole> listeEcole, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
		listeEcole.getList().forEach(o -> {
			RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEcole(siteContexte, requeteSite.getOperationRequete(), requeteSite.getObjetJson());
			requeteSite2.setRequeteApi_(requeteSite.getRequeteApi_());
			o.setRequeteSite_(requeteSite2);
			futures.add(
				patchEcoleFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						erreurEcole(requeteSite2, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listeEcole.next(dt)) {
					listePATCHEcole(requeteApi, listeEcole, dt, gestionnaireEvenements);
				} else {
					reponse200PATCHEcole(requeteSite, gestionnaireEvenements);
				}
			} else {
				LOGGER.error(String.format("listePATCHEcole a échoué. ", a.cause()));
				erreurEcole(listeEcole.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<Ecole> patchEcoleFuture(Ecole o, Boolean inheritPk, Handler<AsyncResult<Ecole>> gestionnaireEvenements) {
		Promise<Ecole> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			if(requeteApi != null && requeteApi.getNumFound() == 1L) {
				requeteApi.setOriginal(o);
				requeteApi.setPk(o.getPk());
			}
			sqlConnexionEcole(requeteSite, a -> {
				if(a.succeeded()) {
					sqlTransactionEcole(requeteSite, b -> {
						if(b.succeeded()) {
							sqlPATCHEcole(o, inheritPk, c -> {
								if(c.succeeded()) {
									Ecole ecole = c.result();
									definirIndexerEcole(ecole, d -> {
										if(d.succeeded()) {
											if(requeteApi != null) {
												requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
												if(requeteApi.getNumFound() == 1L) {
													ecole.requeteApiEcole();
												}
												requeteSite.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
											}
											gestionnaireEvenements.handle(Future.succeededFuture(ecole));
											promise.complete(ecole);
										} else {
											LOGGER.error(String.format("patchEcoleFuture a échoué. ", d.cause()));
											gestionnaireEvenements.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("patchEcoleFuture a échoué. ", c.cause()));
									gestionnaireEvenements.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("patchEcoleFuture a échoué. ", b.cause()));
							gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("patchEcoleFuture a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("patchEcoleFuture a échoué. ", e));
			erreurEcole(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHEcole(Ecole o, Boolean inheritPk, Handler<AsyncResult<Ecole>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Transaction tx = requeteSite.getTx();
			Long pk = o.getPk();
			JsonObject jsonObject = requeteSite.getObjetJson();
			Set<String> methodeNoms = jsonObject.fieldNames();
			Ecole o2 = new Ecole();
			List<Future> futures = new ArrayList<>();

			if(o.getUtilisateurId() == null && requeteSite.getUtilisateurId() != null) {
				futures.add(Future.future(a -> {
					tx.preparedQuery(SiteContexteFrFR.SQL_setD
							, Tuple.of(pk, "utilisateurId", requeteSite.getUtilisateurId())
							, b
					-> {
						if(b.succeeded())
							a.handle(Future.succeededFuture());
						else
							a.handle(Future.failedFuture(b.cause()));
					});
				}));
			}
			if(o.getUtilisateurCle() == null && requeteSite.getUtilisateurCle() != null) {
				futures.add(Future.future(a -> {
					tx.preparedQuery(SiteContexteFrFR.SQL_setD
				, Tuple.of(pk, "utilisateurCle", requeteSite.getUtilisateurCle().toString())
							, b
					-> {
						if(b.succeeded())
							a.handle(Future.succeededFuture());
						else
							a.handle(Future.failedFuture(b.cause()));
					});
				}));
			}

			for(String methodeNom : methodeNoms) {
				switch(methodeNom) {
					case "setInheritPk":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "inheritPk")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.inheritPk a échoué", b.cause())));
								});
							}));
						} else {
							o2.setInheritPk(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "inheritPk", o2.jsonInheritPk())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.inheritPk a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setArchive":
						if(jsonObject.getBoolean(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "archive")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.archive a échoué", b.cause())));
								});
							}));
						} else {
							o2.setArchive(jsonObject.getBoolean(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "archive", o2.jsonArchive())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.archive a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setSupprime":
						if(jsonObject.getBoolean(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "supprime")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.supprime a échoué", b.cause())));
								});
							}));
						} else {
							o2.setSupprime(jsonObject.getBoolean(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "supprime", o2.jsonSupprime())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.supprime a échoué", b.cause())));
								});
							}));
						}
						break;
					case "addAnneeCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AnneeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getAnneeCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(pk, "anneeCles", l2, "ecoleCle")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur Ecole.anneeCles a échoué", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("AnneeScolaire");
									}
								}
							}
						}
						break;
					case "addAllAnneeCles":
						JsonArray addAllAnneeClesValeurs = jsonObject.getJsonArray(methodeNom);
						if(addAllAnneeClesValeurs != null) {
							for(Integer i = 0; i <  addAllAnneeClesValeurs.size(); i++) {
								Long l = Long.parseLong(addAllAnneeClesValeurs.getString(i));
								if(l != null) {
									ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
									listeRecherche.setQuery("*:*");
									listeRecherche.setStocker(true);
									listeRecherche.setC(AnneeScolaire.class);
									listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									listeRecherche.initLoinListeRecherche(requeteSite);
									Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getAnneeCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(pk, "anneeCles", l2, "ecoleCle")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur Ecole.anneeCles a échoué", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("AnneeScolaire");
										}
									}
								}
							}
						}
						break;
					case "setAnneeCles":
						JsonArray setAnneeClesValeurs = jsonObject.getJsonArray(methodeNom);
						if(setAnneeClesValeurs != null) {
							for(Integer i = 0; i <  setAnneeClesValeurs.size(); i++) {
								Long l = Long.parseLong(setAnneeClesValeurs.getString(i));
								if(l != null) {
									ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
									listeRecherche.setQuery("*:*");
									listeRecherche.setStocker(true);
									listeRecherche.setC(AnneeScolaire.class);
									listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									listeRecherche.initLoinListeRecherche(requeteSite);
									Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getAnneeCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(pk, "anneeCles", l2, "ecoleCle")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur Ecole.anneeCles a échoué", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("AnneeScolaire");
										}
									}
								}
							}
						}
						if(o.getAnneeCles() != null) {
							for(Long l :  o.getAnneeCles()) {
								if(l != null && (setAnneeClesValeurs == null || !setAnneeClesValeurs.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_removeA
												, Tuple.of(pk, "anneeCles", l, "ecoleCle")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur Ecole.anneeCles a échoué", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removeAnneeCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AnneeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getAnneeCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_removeA
												, Tuple.of(pk, "anneeCles", l2, "ecoleCle")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur Ecole.anneeCles a échoué", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("AnneeScolaire");
									}
								}
							}
						}
						break;
					case "setEcoleNom":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "ecoleNom")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleNom a échoué", b.cause())));
								});
							}));
						} else {
							o2.setEcoleNom(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "ecoleNom", o2.jsonEcoleNom())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleNom a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setEcoleNumeroTelephone":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "ecoleNumeroTelephone")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleNumeroTelephone a échoué", b.cause())));
								});
							}));
						} else {
							o2.setEcoleNumeroTelephone(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "ecoleNumeroTelephone", o2.jsonEcoleNumeroTelephone())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleNumeroTelephone a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setEcoleNumero":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "ecoleNumero")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleNumero a échoué", b.cause())));
								});
							}));
						} else {
							o2.setEcoleNumero(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "ecoleNumero", o2.jsonEcoleNumero())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleNumero a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setEcoleAdministrateurNom":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "ecoleAdministrateurNom")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleAdministrateurNom a échoué", b.cause())));
								});
							}));
						} else {
							o2.setEcoleAdministrateurNom(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "ecoleAdministrateurNom", o2.jsonEcoleAdministrateurNom())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleAdministrateurNom a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setEcoleMail":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "ecoleMail")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleMail a échoué", b.cause())));
								});
							}));
						} else {
							o2.setEcoleMail(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "ecoleMail", o2.jsonEcoleMail())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleMail a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setEcoleMailDe":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "ecoleMailDe")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleMailDe a échoué", b.cause())));
								});
							}));
						} else {
							o2.setEcoleMailDe(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "ecoleMailDe", o2.jsonEcoleMailDe())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleMailDe a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setEcoleMailA":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "ecoleMailA")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleMailA a échoué", b.cause())));
								});
							}));
						} else {
							o2.setEcoleMailA(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "ecoleMailA", o2.jsonEcoleMailA())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleMailA a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setEcoleEmplacement":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "ecoleEmplacement")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleEmplacement a échoué", b.cause())));
								});
							}));
						} else {
							o2.setEcoleEmplacement(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "ecoleEmplacement", o2.jsonEcoleEmplacement())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleEmplacement a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setEcoleAddresse":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "ecoleAddresse")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleAddresse a échoué", b.cause())));
								});
							}));
						} else {
							o2.setEcoleAddresse(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "ecoleAddresse", o2.jsonEcoleAddresse())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleAddresse a échoué", b.cause())));
								});
							}));
						}
						break;
				}
			}
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					Ecole o3 = new Ecole();
					o3.setRequeteSite_(o.getRequeteSite_());
					o3.setPk(pk);
					gestionnaireEvenements.handle(Future.succeededFuture(o3));
				} else {
					LOGGER.error(String.format("sqlPATCHEcole a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPATCHEcole a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void patchEcoleReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PATCHEcole(requeteSite, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("patchEcoleReponse a échoué. ", a.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchEcoleReponse a échoué. ", ex));
			erreurEcole(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PATCHEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200PATCHEcole a échoué. ", e));
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
					LOGGER.error(String.format("getEcole a échoué. ", b.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getEcole a échoué. ", ex));
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void getEcoleReponse(ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
		try {
			reponse200GETEcole(listeEcole, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("getEcoleReponse a échoué. ", a.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getEcoleReponse a échoué. ", ex));
			erreurEcole(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200GETEcole(ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
			SolrDocumentList documentsSolr = listeEcole.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listeEcole.getList().stream().findFirst().orElse(null));
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200GETEcole a échoué. ", e));
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
					LOGGER.error(String.format("rechercheEcole a échoué. ", b.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("rechercheEcole a échoué. ", ex));
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void rechercheEcoleReponse(ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
		try {
			reponse200RechercheEcole(listeEcole, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("rechercheEcoleReponse a échoué. ", a.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("rechercheEcoleReponse a échoué. ", ex));
			erreurEcole(requeteSite, null, Future.failedFuture(ex));
		}
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
						fieldNames.remove("cree");
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
			LOGGER.error(String.format("reponse200RechercheEcole a échoué. ", e));
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

			utilisateurEcole(requeteSite, b -> {
				if(b.succeeded()) {
					putimportEcoleReponse(requeteSite, c -> {
						if(c.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
							WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
							executeurTravailleur.executeBlocking(
								blockingCodeHandler -> {
									try {
										RequeteApi requeteApi = new RequeteApi();
										JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
										requeteApi.setRows(jsonArray.size());
										requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
										requeteApi.setNumPATCH(0L);
										requeteApi.initLoinRequeteApi(requeteSite);
										requeteSite.setRequeteApi_(requeteApi);
										requeteSite.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
										varsEcole(requeteSite, d -> {
											if(d.succeeded()) {
												listePUTImportEcole(requeteApi, requeteSite, e -> {
													if(e.succeeded()) {
														putimportEcoleReponse(requeteSite, f -> {
															if(e.succeeded()) {
																LOGGER.info(String.format("putimportEcole a réussi. "));
																blockingCodeHandler.handle(Future.succeededFuture(e.result()));
															} else {
																LOGGER.error(String.format("putimportEcole a échoué. ", f.cause()));
																erreurEcole(requeteSite, null, f);
															}
														});
													} else {
														LOGGER.error(String.format("putimportEcole a échoué. ", e.cause()));
														erreurEcole(requeteSite, null, e);
													}
												});
											} else {
												LOGGER.error(String.format("putimportEcole a échoué. ", d.cause()));
												erreurEcole(requeteSite, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putimportEcole a échoué. ", ex));
										erreurEcole(requeteSite, null, Future.failedFuture(ex));
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
					LOGGER.error(String.format("putimportEcole a échoué. ", b.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportEcole a échoué. ", ex));
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePUTImportEcole(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				json.put("cree", json.getValue("cree"));

				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEcole(siteContexte, requeteSite.getOperationRequete(), json);
				requeteSite2.setRequeteApi_(requeteApi);
				requeteSite2.setRequeteVars(requeteSite.getRequeteVars());

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
						for(String f : Optional.ofNullable(o.getSauvegardes()).orElse(new ArrayList<>())) {
							if(!json.fieldNames().contains(f))
								json2.putNull("set" + StringUtils.capitalize(f));
						}
						requeteSite2.setObjetJson(json2);
						futures.add(
							patchEcoleFuture(o, true, a -> {
								if(a.succeeded()) {
								} else {
									LOGGER.error(String.format("listePUTImportEcole a échoué. ", a.cause()));
									erreurEcole(requeteSite2, gestionnaireEvenements, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postEcoleFuture(requeteSite2, true, a -> {
							if(a.succeeded()) {
							} else {
								LOGGER.error(String.format("listePUTImportEcole a échoué. ", a.cause()));
								erreurEcole(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
					reponse200PUTImportEcole(requeteSite, gestionnaireEvenements);
				} else {
					LOGGER.error(String.format("listePUTImportEcole a échoué. ", a.cause()));
					erreurEcole(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("listePUTImportEcole a échoué. ", ex));
			erreurEcole(requeteSite, null, Future.failedFuture(ex));
		}
	}

	public void putimportEcoleReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PUTImportEcole(requeteSite, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putimportEcoleReponse a échoué. ", a.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportEcoleReponse a échoué. ", ex));
			erreurEcole(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PUTImportEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200PUTImportEcole a échoué. ", e));
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

			utilisateurEcole(requeteSite, b -> {
				if(b.succeeded()) {
					putfusionEcoleReponse(requeteSite, c -> {
						if(c.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
							WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
							executeurTravailleur.executeBlocking(
								blockingCodeHandler -> {
									try {
										RequeteApi requeteApi = new RequeteApi();
										JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
										requeteApi.setRows(jsonArray.size());
										requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
										requeteApi.setNumPATCH(0L);
										requeteApi.initLoinRequeteApi(requeteSite);
										requeteSite.setRequeteApi_(requeteApi);
										requeteSite.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
										varsEcole(requeteSite, d -> {
											if(d.succeeded()) {
												listePUTFusionEcole(requeteApi, requeteSite, e -> {
													if(e.succeeded()) {
														putfusionEcoleReponse(requeteSite, f -> {
															if(e.succeeded()) {
																LOGGER.info(String.format("putfusionEcole a réussi. "));
																blockingCodeHandler.handle(Future.succeededFuture(e.result()));
															} else {
																LOGGER.error(String.format("putfusionEcole a échoué. ", f.cause()));
																erreurEcole(requeteSite, null, f);
															}
														});
													} else {
														LOGGER.error(String.format("putfusionEcole a échoué. ", e.cause()));
														erreurEcole(requeteSite, null, e);
													}
												});
											} else {
												LOGGER.error(String.format("putfusionEcole a échoué. ", d.cause()));
												erreurEcole(requeteSite, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putfusionEcole a échoué. ", ex));
										erreurEcole(requeteSite, null, Future.failedFuture(ex));
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
					LOGGER.error(String.format("putfusionEcole a échoué. ", b.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putfusionEcole a échoué. ", ex));
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePUTFusionEcole(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEcole(siteContexte, requeteSite.getOperationRequete(), json);
				requeteSite2.setRequeteApi_(requeteApi);
				requeteSite2.setRequeteVars(requeteSite.getRequeteVars());

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
						for(String f : Optional.ofNullable(o.getSauvegardes()).orElse(new ArrayList<>())) {
							if(!json.fieldNames().contains(f))
								json2.putNull("set" + StringUtils.capitalize(f));
						}
						requeteSite2.setObjetJson(json2);
						futures.add(
							patchEcoleFuture(o, false, a -> {
								if(a.succeeded()) {
								} else {
									LOGGER.error(String.format("listePUTFusionEcole a échoué. ", a.cause()));
									erreurEcole(requeteSite2, gestionnaireEvenements, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postEcoleFuture(requeteSite2, false, a -> {
							if(a.succeeded()) {
							} else {
								LOGGER.error(String.format("listePUTFusionEcole a échoué. ", a.cause()));
								erreurEcole(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
					reponse200PUTFusionEcole(requeteSite, gestionnaireEvenements);
				} else {
					LOGGER.error(String.format("listePUTFusionEcole a échoué. ", a.cause()));
					erreurEcole(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("listePUTFusionEcole a échoué. ", ex));
			erreurEcole(requeteSite, null, Future.failedFuture(ex));
		}
	}

	public void putfusionEcoleReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PUTFusionEcole(requeteSite, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putfusionEcoleReponse a échoué. ", a.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putfusionEcoleReponse a échoué. ", ex));
			erreurEcole(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PUTFusionEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200PUTFusionEcole a échoué. ", e));
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

			utilisateurEcole(requeteSite, b -> {
				if(b.succeeded()) {
					putcopieEcoleReponse(requeteSite, c -> {
						if(c.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
							WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
							executeurTravailleur.executeBlocking(
								blockingCodeHandler -> {
									try {
										rechercheEcole(requeteSite, false, true, "/api/ecole/copie", "PUTCopie", d -> {
											if(d.succeeded()) {
												ListeRecherche<Ecole> listeEcole = d.result();
												RequeteApi requeteApi = new RequeteApi();
												requeteApi.setRows(listeEcole.getRows());
												requeteApi.setNumFound(listeEcole.getQueryResponse().getResults().getNumFound());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite);
												requeteSite.setRequeteApi_(requeteApi);
												requeteSite.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
												try {
													listePUTCopieEcole(requeteApi, listeEcole, e -> {
														if(e.succeeded()) {
															putcopieEcoleReponse(requeteSite, f -> {
																if(f.succeeded()) {
																	LOGGER.info(String.format("putcopieEcole a réussi. "));
																	blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																} else {
																	LOGGER.error(String.format("putcopieEcole a échoué. ", f.cause()));
																	erreurEcole(requeteSite, null, f);
																}
															});
														} else {
															LOGGER.error(String.format("putcopieEcole a échoué. ", e.cause()));
															erreurEcole(requeteSite, null, e);
														}
													});
												} catch(Exception ex) {
													LOGGER.error(String.format("putcopieEcole a échoué. ", ex));
													erreurEcole(requeteSite, null, Future.failedFuture(ex));
												}
											} else {
												LOGGER.error(String.format("putcopieEcole a échoué. ", d.cause()));
												erreurEcole(requeteSite, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putcopieEcole a échoué. ", ex));
										erreurEcole(requeteSite, null, Future.failedFuture(ex));
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
					LOGGER.error(String.format("putcopieEcole a échoué. ", b.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopieEcole a échoué. ", ex));
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePUTCopieEcole(RequeteApi requeteApi, ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
		listeEcole.getList().forEach(o -> {
			RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEcole(siteContexte, requeteSite.getOperationRequete(), requeteSite.getObjetJson());
			requeteSite2.setRequeteApi_(requeteSite.getRequeteApi_());
			o.setRequeteSite_(requeteSite2);
			futures.add(
				putcopieEcoleFuture(requeteSite, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
					} else {
						LOGGER.error(String.format("listePUTCopieEcole a échoué. ", a.cause()));
						erreurEcole(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeEcole.size());
				if(listeEcole.next()) {
					listePUTCopieEcole(requeteApi, listeEcole, gestionnaireEvenements);
				} else {
					reponse200PUTCopieEcole(requeteSite, gestionnaireEvenements);
				}
			} else {
				LOGGER.error(String.format("listePUTCopieEcole a échoué. ", a.cause()));
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

			sqlConnexionEcole(requeteSite, a -> {
				if(a.succeeded()) {
					sqlTransactionEcole(requeteSite, b -> {
						if(b.succeeded()) {
							creerEcole(requeteSite, c -> {
								if(c.succeeded()) {
									Ecole ecole = c.result();
									sqlPUTCopieEcole(ecole, jsonObject, d -> {
										if(d.succeeded()) {
											definirIndexerEcole(ecole, e -> {
												if(e.succeeded()) {
													RequeteApi requeteApi = requeteSite.getRequeteApi_();
													if(requeteApi != null) {
														requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
														if(requeteApi.getNumFound() == 1L) {
															ecole.requeteApiEcole();
														}
														requeteSite.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi).toString());
													}
													gestionnaireEvenements.handle(Future.succeededFuture(ecole));
													promise.complete(ecole);
												} else {
													LOGGER.error(String.format("putcopieEcoleFuture a échoué. ", e.cause()));
													gestionnaireEvenements.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											LOGGER.error(String.format("putcopieEcoleFuture a échoué. ", d.cause()));
											gestionnaireEvenements.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("putcopieEcoleFuture a échoué. ", c.cause()));
									gestionnaireEvenements.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("putcopieEcoleFuture a échoué. ", b.cause()));
							gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("putcopieEcoleFuture a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("putcopieEcoleFuture a échoué. ", e));
			erreurEcole(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopieEcole(Ecole o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Transaction tx = requeteSite.getTx();
			Long pk = o.getPk();
			List<Future> futures = new ArrayList<>();

			if(jsonObject != null) {
				JsonArray entiteVars = jsonObject.getJsonArray("sauvegardes");
				for(Integer i = 0; i < entiteVars.size(); i++) {
					String entiteVar = entiteVars.getString(i);
					switch(entiteVar) {
					case "inheritPk":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "inheritPk", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.inheritPk a échoué", b.cause())));
							});
						}));
						break;
					case "archive":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "archive", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.archive a échoué", b.cause())));
							});
						}));
						break;
					case "supprime":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "supprime", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.supprime a échoué", b.cause())));
							});
						}));
						break;
					case "anneeCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_addA
										, Tuple.of(pk, "anneeCles", l, "ecoleCle")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur Ecole.anneeCles a échoué", b.cause())));
								});
							}));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("AnneeScolaire");
							}
						}
						break;
					case "ecoleNom":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleNom", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleNom a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleNumeroTelephone":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleNumeroTelephone", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleNumeroTelephone a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleNumero":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleNumero", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleNumero a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleAdministrateurNom":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleAdministrateurNom", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleAdministrateurNom a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleMail":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleMail", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleMail a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleMailDe":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleMailDe", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleMailDe a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleMailA":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleMailA", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleMailA a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleEmplacement":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleEmplacement", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleEmplacement a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleAddresse":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "ecoleAddresse", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur Ecole.ecoleAddresse a échoué", b.cause())));
							});
						}));
						break;
					}
				}
			}
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture());
				} else {
					LOGGER.error(String.format("sqlPUTCopieEcole a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPUTCopieEcole a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void putcopieEcoleReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PUTCopieEcole(requeteSite, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putcopieEcoleReponse a échoué. ", a.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopieEcoleReponse a échoué. ", ex));
			erreurEcole(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PUTCopieEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200PUTCopieEcole a échoué. ", e));
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
					LOGGER.error(String.format("pagerechercheEcole a échoué. ", b.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("pagerechercheEcole a échoué. ", ex));
			erreurEcole(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void pagerechercheEcoleReponse(ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
		try {
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(requeteSite, buffer);
			requeteSite.setW(w);
			reponse200PageRechercheEcole(listeEcole, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("pagerechercheEcoleReponse a échoué. ", a.cause()));
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("pagerechercheEcoleReponse a échoué. ", ex));
			erreurEcole(requeteSite, null, Future.failedFuture(ex));
		}
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
			LOGGER.error(String.format("reponse200PageRechercheEcole a échoué. ", e));
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
								sqlCommitEcole(requeteSite, f -> {
									if(f.succeeded()) {
										sqlFermerEcole(requeteSite, g -> {
											if(g.succeeded()) {
												rechargerEcole(ecole, h -> {
													if(h.succeeded()) {
														gestionnaireEvenements.handle(Future.succeededFuture(ecole));
														promise.complete(ecole);
													} else {
														LOGGER.error(String.format("rechargerEcole a échoué. ", h.cause()));
														erreurEcole(requeteSite, null, h);
													}
												});
											} else {
												LOGGER.error(String.format("definirIndexerEcole a échoué. ", g.cause()));
												erreurEcole(requeteSite, null, g);
											}
										});
									} else {
										LOGGER.error(String.format("definirIndexerEcole a échoué. ", f.cause()));
										erreurEcole(requeteSite, null, f);
									}
								});
							} else {
								LOGGER.error(String.format("definirIndexerEcole a échoué. ", e.cause()));
								erreurEcole(requeteSite, null, e);
							}
						});
					} else {
						LOGGER.error(String.format("definirIndexerEcole a échoué. ", d.cause()));
						erreurEcole(requeteSite, null, d);
					}
				});
			} else {
				LOGGER.error(String.format("definirIndexerEcole a échoué. ", c.cause()));
				erreurEcole(requeteSite, null, c);
			}
		});
		return promise.future();
	}

	public void creerEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<Ecole>> gestionnaireEvenements) {
		try {
			Transaction tx = requeteSite.getTx();
			String utilisateurId = requeteSite.getUtilisateurId();
			ZonedDateTime cree = Optional.ofNullable(requeteSite.getObjetJson()).map(j -> j.getString("cree")).map(s -> ZonedDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of(requeteSite.getConfigSite_().getSiteZone())))).orElse(ZonedDateTime.now(ZoneId.of(requeteSite.getConfigSite_().getSiteZone())));

			tx.preparedQuery(
					SiteContexteFrFR.SQL_creer
					, Tuple.of(Ecole.class.getCanonicalName(), utilisateurId, cree.toOffsetDateTime())
					, Collectors.toList()
					, creerAsync
			-> {
				if(creerAsync.succeeded()) {
					Row creerLigne = creerAsync.result().value().stream().findFirst().orElseGet(() -> null);
					Long pk = creerLigne.getLong(0);
					Ecole o = new Ecole();
					o.setPk(pk);
					o.setRequeteSite_(requeteSite);
					gestionnaireEvenements.handle(Future.succeededFuture(o));
				} else {
					LOGGER.error(String.format("creerEcole a échoué. ", creerAsync.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(creerAsync.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("creerEcole a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void erreurEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
		Throwable e = resultatAsync.cause();
		ExceptionUtils.printRootCauseStackTrace(e);
		OperationResponse reponseOperation = new OperationResponse(400, "BAD REQUEST", 
			Buffer.buffer().appendString(
				new JsonObject() {{
					put("erreur", new JsonObject()
						.put("message", Optional.ofNullable(e).map(Throwable::getMessage).orElse(null))
					);
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
		if(e != null)
			message.setText(ExceptionUtils.getStackTrace(e));
		message.setSubject(String.format(configSite.getSiteUrlBase() + " " + Optional.ofNullable(e).map(Throwable::getMessage).orElse(null)));
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
		sqlRollbackEcole(requeteSite, a -> {
			if(a.succeeded()) {
				LOGGER.info(String.format("sql rollback. "));
				sqlFermerEcole(requeteSite, b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("sql close. "));
						if(gestionnaireEvenements != null)
							gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));
					} else {
						if(gestionnaireEvenements != null)
							gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));
					}
				});
			} else {
				if(gestionnaireEvenements != null)
					gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));
			}
		});
	}

	public void sqlConnexionEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			PgPool pgPool = requeteSite.getSiteContexte_().getPgPool();

			if(pgPool == null) {
				gestionnaireEvenements.handle(Future.succeededFuture());
			} else {
				pgPool.getConnection(a -> {
					if(a.succeeded()) {
						SqlConnection connexionSql = a.result();
						requeteSite.setConnexionSql(connexionSql);
						gestionnaireEvenements.handle(Future.succeededFuture());
					} else {
						LOGGER.error(String.format("sqlConnexionEcole a échoué. ", a.cause()));
						gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlEcole a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlTransactionEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SqlConnection connexionSql = requeteSite.getConnexionSql();

			if(connexionSql == null) {
				gestionnaireEvenements.handle(Future.succeededFuture());
			} else {
				Transaction tx = connexionSql.begin();
				requeteSite.setTx(tx);
				gestionnaireEvenements.handle(Future.succeededFuture());
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlTransactionEcole a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlCommitEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Transaction tx = requeteSite.getTx();

			if(tx == null) {
				gestionnaireEvenements.handle(Future.succeededFuture());
			} else {
				tx.commit(a -> {
					if(a.succeeded()) {
						requeteSite.setTx(null);
						gestionnaireEvenements.handle(Future.succeededFuture());
					} else if("Transaction already completed".equals(a.cause().getMessage())) {
						requeteSite.setTx(null);
						gestionnaireEvenements.handle(Future.succeededFuture());
					} else {
						LOGGER.error(String.format("sqlCommitEcole a échoué. ", a.cause()));
						gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlEcole a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlRollbackEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Transaction tx = requeteSite.getTx();

			if(tx == null) {
				gestionnaireEvenements.handle(Future.succeededFuture());
			} else {
				tx.rollback(a -> {
					if(a.succeeded()) {
						requeteSite.setTx(null);
						gestionnaireEvenements.handle(Future.succeededFuture());
					} else if("Transaction already completed".equals(a.cause().getMessage())) {
						requeteSite.setTx(null);
						gestionnaireEvenements.handle(Future.succeededFuture());
					} else {
						LOGGER.error(String.format("sqlRollbackEcole a échoué. ", a.cause()));
						gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlEcole a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlFermerEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SqlConnection connexionSql = requeteSite.getConnexionSql();

			if(connexionSql == null) {
				gestionnaireEvenements.handle(Future.succeededFuture());
			} else {
				connexionSql.close();
				requeteSite.setConnexionSql(null);
				gestionnaireEvenements.handle(Future.succeededFuture());
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlFermerEcole a échoué. ", e));
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
			String utilisateurId = requeteSite.getUtilisateurId();
			if(utilisateurId == null) {
				gestionnaireEvenements.handle(Future.succeededFuture());
			} else {
				sqlConnexionEcole(requeteSite, a -> {
					if(a.succeeded()) {
						sqlTransactionEcole(requeteSite, b -> {
							if(b.succeeded()) {
								Transaction tx = requeteSite.getTx();
								tx.preparedQuery(
										SiteContexteFrFR.SQL_selectC
										, Tuple.of("org.computate.scolaire.frFR.utilisateur.UtilisateurSite", utilisateurId)
										, Collectors.toList()
										, selectCAsync
								-> {
									if(selectCAsync.succeeded()) {
										try {
											Row utilisateurValeurs = selectCAsync.result().value().stream().findFirst().orElse(null);
											UtilisateurSiteFrFRApiServiceImpl utilisateurService = new UtilisateurSiteFrFRApiServiceImpl(siteContexte);
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
												requeteSite2.setTx(requeteSite.getTx());
												requeteSite2.setConnexionSql(requeteSite.getConnexionSql());
												requeteSite2.setObjetJson(jsonObject);
												requeteSite2.setVertx(requeteSite.getVertx());
												requeteSite2.setSiteContexte_(siteContexte);
												requeteSite2.setConfigSite_(siteContexte.getConfigSite());
												requeteSite2.setUtilisateurId(requeteSite.getUtilisateurId());
												requeteSite2.initLoinRequeteSiteFrFR(requeteSite);

												RequeteApi requeteApi = new RequeteApi();
												requeteApi.setRows(1);
												requeteApi.setNumFound(1L);
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite2);
												requeteSite2.setRequeteApi_(requeteApi);

												utilisateurService.creerUtilisateurSite(requeteSite2, c -> {
													if(c.succeeded()) {
														UtilisateurSite utilisateurSite = c.result();
														utilisateurService.sqlPOSTUtilisateurSite(utilisateurSite, false, d -> {
															if(d.succeeded()) {
																utilisateurService.definirIndexerUtilisateurSite(utilisateurSite, e -> {
																	if(e.succeeded()) {
																		requeteSite.setUtilisateurSite(utilisateurSite);
																		requeteSite.setUtilisateurNom(principalJson.getString("preferred_username"));
																		requeteSite.setUtilisateurPrenom(principalJson.getString("given_name"));
																		requeteSite.setUtilisateurNomFamille(principalJson.getString("family_name"));
																		requeteSite.setUtilisateurId(principalJson.getString("sub"));
																		requeteSite.setUtilisateurCle(utilisateurSite.getPk());
																		gestionnaireEvenements.handle(Future.succeededFuture());
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
												jsonObject.put("setCustomerProfileId1", Optional.ofNullable(utilisateurSite1).map(u -> u.getCustomerProfileId1()).orElse(null));
												jsonObject.put("setCustomerProfileId2", Optional.ofNullable(utilisateurSite1).map(u -> u.getCustomerProfileId2()).orElse(null));
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
													requeteSite2.setTx(requeteSite.getTx());
													requeteSite2.setConnexionSql(requeteSite.getConnexionSql());
													requeteSite2.setObjetJson(jsonObject);
													requeteSite2.setVertx(requeteSite.getVertx());
													requeteSite2.setSiteContexte_(siteContexte);
													requeteSite2.setConfigSite_(siteContexte.getConfigSite());
													requeteSite2.setUtilisateurId(requeteSite.getUtilisateurId());
													requeteSite2.setUtilisateurCle(requeteSite.getUtilisateurCle());
													requeteSite2.initLoinRequeteSiteFrFR(requeteSite);
													utilisateurSite.setRequeteSite_(requeteSite2);

													RequeteApi requeteApi = new RequeteApi();
													requeteApi.setRows(1);
													requeteApi.setNumFound(1L);
													requeteApi.setNumPATCH(0L);
													requeteApi.initLoinRequeteApi(requeteSite2);
													requeteSite2.setRequeteApi_(requeteApi);

													utilisateurService.sqlPATCHUtilisateurSite(utilisateurSite, false, d -> {
														if(d.succeeded()) {
															UtilisateurSite utilisateurSite2 = d.result();
															utilisateurService.definirIndexerUtilisateurSite(utilisateurSite2, e -> {
																if(e.succeeded()) {
																	requeteSite.setUtilisateurSite(utilisateurSite2);
																	requeteSite.setUtilisateurNom(utilisateurSite2.getUtilisateurNom());
																	requeteSite.setUtilisateurPrenom(utilisateurSite2.getUtilisateurPrenom());
																	requeteSite.setUtilisateurNomFamille(utilisateurSite2.getUtilisateurNomFamille());
																	requeteSite.setUtilisateurId(utilisateurSite2.getUtilisateurId());
																	requeteSite.setUtilisateurCle(utilisateurSite2.getPk());
																	gestionnaireEvenements.handle(Future.succeededFuture());
																} else {
																	erreurEcole(requeteSite, gestionnaireEvenements, e);
																}
															});
														} else {
															erreurEcole(requeteSite, gestionnaireEvenements, d);
														}
													});
												} else {
													requeteSite.setUtilisateurSite(utilisateurSite1);
													requeteSite.setUtilisateurNom(utilisateurSite1.getUtilisateurNom());
													requeteSite.setUtilisateurPrenom(utilisateurSite1.getUtilisateurPrenom());
													requeteSite.setUtilisateurNomFamille(utilisateurSite1.getUtilisateurNomFamille());
													requeteSite.setUtilisateurId(utilisateurSite1.getUtilisateurId());
													requeteSite.setUtilisateurCle(utilisateurSite1.getPk());
													sqlRollbackEcole(requeteSite, c -> {
														if(c.succeeded()) {
															gestionnaireEvenements.handle(Future.succeededFuture());
														} else {
															gestionnaireEvenements.handle(Future.failedFuture(c.cause()));
															erreurEcole(requeteSite, gestionnaireEvenements, c);
														}
													});
												}
											}
										} catch(Exception e) {
											LOGGER.error(String.format("utilisateurEcole a échoué. ", e));
											gestionnaireEvenements.handle(Future.failedFuture(e));
										}
									} else {
										LOGGER.error(String.format("utilisateurEcole a échoué. ", selectCAsync.cause()));
										gestionnaireEvenements.handle(Future.failedFuture(selectCAsync.cause()));
									}
								});
							} else {
								LOGGER.error(String.format("utilisateurEcole a échoué. ", b.cause()));
								gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
							}
						});
					} else {
						LOGGER.error(String.format("utilisateurEcole a échoué. ", a.cause()));
						gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("utilisateurEcole a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public Boolean utilisateurEcoleDefinir(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			if(jsonObject.getString("setCustomerProfileId1") == null)
				return true;
			if(jsonObject.getString("setCustomerProfileId2") == null)
				return true;
			return false;
		} else {
			if(jsonObject.getString("setCustomerProfileId1") == null)
				return true;
			if(jsonObject.getString("setCustomerProfileId2") == null)
				return true;
			return false;
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

	public void rechercheEcoleUri(String uri, String apiMethode, ListeRecherche<Ecole> listeRecherche) {
	}

	public void varsEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<ListeRecherche<OperationResponse>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();

			operationRequete.getParams().getJsonObject("query").forEach(paramRequete -> {
				String entiteVar = null;
				String valeurIndexe = null;
				String paramNom = paramRequete.getKey();
				Object paramValeursObjet = paramRequete.getValue();
				JsonArray paramObjets = paramValeursObjet instanceof JsonArray ? (JsonArray)paramValeursObjet : new JsonArray().add(paramValeursObjet);

				try {
					for(Object paramObjet : paramObjets) {
						switch(paramNom) {
							case "var":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								requeteSite.getRequeteVars().put(entiteVar, valeurIndexe);
								break;
						}
					}
				} catch(Exception e) {
					LOGGER.error(String.format("rechercheEcole a échoué. ", e));
					gestionnaireEvenements.handle(Future.failedFuture(e));
				}
			});
			gestionnaireEvenements.handle(Future.succeededFuture());
		} catch(Exception e) {
			LOGGER.error(String.format("rechercheEcole a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
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
					rechercheEcoleUri(uri, apiMethode, listeRecherche);
				} catch(Exception e) {
					LOGGER.error(String.format("rechercheEcole a échoué. ", e));
					gestionnaireEvenements.handle(Future.failedFuture(e));
				}
			});
			if(listeRecherche.getSorts().size() == 0) {
				listeRecherche.addSort("cree_indexed_date", ORDER.desc);
			}
			listeRecherche.initLoinPourClasse(requeteSite);
			gestionnaireEvenements.handle(Future.succeededFuture(listeRecherche));
		} catch(Exception e) {
			LOGGER.error(String.format("rechercheEcole a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void definirEcole(Ecole o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			Transaction tx = requeteSite.getTx();
			Long pk = o.getPk();
			tx.preparedQuery(
					SiteContexteFrFR.SQL_definir
					, Tuple.of(pk)
					, Collectors.toList()
					, definirAsync
			-> {
				if(definirAsync.succeeded()) {
					try {
						for(Row definition : definirAsync.result().value()) {
							try {
								o.definirPourClasse(definition.getString(0), definition.getString(1));
							} catch(Exception e) {
								LOGGER.error(e);
							}
						}
						gestionnaireEvenements.handle(Future.succeededFuture());
					} catch(Exception e) {
						LOGGER.error(String.format("definirEcole a échoué. ", e));
						gestionnaireEvenements.handle(Future.failedFuture(e));
					}
				} else {
					LOGGER.error(String.format("definirEcole a échoué. ", definirAsync.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(definirAsync.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("definirEcole a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void attribuerEcole(Ecole o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			Transaction tx = requeteSite.getTx();
			Long pk = o.getPk();
			tx.preparedQuery(
					SiteContexteFrFR.SQL_attribuer
					, Tuple.of(pk, pk)
					, Collectors.toList()
					, attribuerAsync
			-> {
				try {
					if(attribuerAsync.succeeded()) {
						if(attribuerAsync.result() != null) {
							for(Row definition : attribuerAsync.result().value()) {
								if(pk.equals(definition.getLong(0)))
									o.attribuerPourClasse(definition.getString(2), definition.getLong(1));
								else
									o.attribuerPourClasse(definition.getString(3), definition.getLong(0));
							}
						}
						gestionnaireEvenements.handle(Future.succeededFuture());
					} else {
						LOGGER.error(String.format("attribuerEcole a échoué. ", attribuerAsync.cause()));
						gestionnaireEvenements.handle(Future.failedFuture(attribuerAsync.cause()));
					}
				} catch(Exception e) {
					LOGGER.error(String.format("attribuerEcole a échoué. ", e));
					gestionnaireEvenements.handle(Future.failedFuture(e));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("attribuerEcole a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void indexerEcole(Ecole o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
			o.initLoinPourClasse(requeteSite);
			o.indexerPourClasse();
			gestionnaireEvenements.handle(Future.succeededFuture());
		} catch(Exception e) {
			LOGGER.error(String.format("indexerEcole a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void rechargerEcole(Ecole o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Boolean recharger = !"false".equals(requeteSite.getRequeteVars().get("recharger"));
			if(recharger && BooleanUtils.isFalse(Optional.ofNullable(requeteSite.getRequeteApi_()).map(RequeteApi::getEmpty).orElse(true))) {
				ListeRecherche<Ecole> listeRecherche = new ListeRecherche<Ecole>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(Ecole.class);
				listeRecherche.addFilterQuery("modifie_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(requeteSite.getRequeteApi_().getCree().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				listeRecherche.add("json.facet", "{anneeCles:{terms:{field:anneeCles_indexed_longs, limit:1000}}}");
				listeRecherche.setRows(1000);
				listeRecherche.initLoinListeRecherche(requeteSite);
				List<Future> futures = new ArrayList<>();

				for(int i=0; i < pks.size(); i++) {
					Long pk2 = pks.get(i);
					String classeNomSimple2 = classes.get(i);

					if("AnneeScolaire".equals(classeNomSimple2) && pk2 != null) {
						ListeRecherche<AnneeScolaire> listeRecherche2 = new ListeRecherche<AnneeScolaire>();
						listeRecherche2.setStocker(true);
						listeRecherche2.setQuery("*:*");
						listeRecherche2.setC(AnneeScolaire.class);
						listeRecherche2.addFilterQuery("pk_indexed_long:" + pk2);
						listeRecherche2.setRows(1);
						listeRecherche2.initLoinListeRecherche(requeteSite);
						AnneeScolaire o2 = listeRecherche2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							AnneeScolaireFrFRGenApiServiceImpl service = new AnneeScolaireFrFRGenApiServiceImpl(requeteSite.getSiteContexte_());
							RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEcole(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
							RequeteApi requeteApi2 = new RequeteApi();
							requeteApi2.setRows(1);
							requeteApi2.setNumFound(1l);
							requeteApi2.setNumPATCH(0L);
							requeteApi2.initLoinRequeteApi(requeteSite2);
							requeteSite2.setRequeteApi_(requeteApi2);
							requeteSite2.getVertx().eventBus().publish("websocketAnneeScolaire", JsonObject.mapFrom(requeteApi2).toString());

							o2.setPk(pk2);
							o2.setRequeteSite_(requeteSite2);
							futures.add(
								service.patchAnneeScolaireFuture(o2, false, a -> {
									if(a.succeeded()) {
									} else {
										LOGGER.info(String.format("AnneeScolaire %s a échoué. ", pk2));
										gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						EcoleFrFRApiServiceImpl service = new EcoleFrFRApiServiceImpl(requeteSite.getSiteContexte_());
						List<Future> futures2 = new ArrayList<>();
						for(Ecole o2 : listeRecherche.getList()) {
							RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourEcole(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
							o2.setRequeteSite_(requeteSite2);
							futures2.add(
								service.patchEcoleFuture(o2, false, b -> {
									if(b.succeeded()) {
									} else {
										LOGGER.info(String.format("Ecole %s a échoué. ", o2.getPk()));
										gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Recharger relations a échoué. ", b.cause());
								erreurEcole(requeteSite, gestionnaireEvenements, b);
							}
						});
					} else {
						LOGGER.error("Recharger relations a échoué. ", a.cause());
						erreurEcole(requeteSite, gestionnaireEvenements, a);
					}
				});
			} else {
				gestionnaireEvenements.handle(Future.succeededFuture());
			}
		} catch(Exception e) {
			LOGGER.error(String.format("rechargerEcole a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}
}
