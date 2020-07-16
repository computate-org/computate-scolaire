package org.computate.scolaire.frFR.annee;

import org.computate.scolaire.frFR.ecole.EcoleFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.ecole.Ecole;
import org.computate.scolaire.frFR.saison.SaisonScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.saison.SaisonScolaire;
import org.computate.scolaire.frFR.age.AgeScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.age.AgeScolaire;
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
 * NomCanonique.enUS: org.computate.scolaire.enUS.year.SchoolYearEnUSGenApiServiceImpl
 **/
public class AnneeScolaireFrFRGenApiServiceImpl implements AnneeScolaireFrFRGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(AnneeScolaireFrFRGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "AnneeScolaireFrFRApiServiceImpl";

	protected SiteContexteFrFR siteContexte;

	public AnneeScolaireFrFRGenApiServiceImpl(SiteContexteFrFR siteContexte) {
		this.siteContexte = siteContexte;
	}

	// POST //

	@Override
	public void postAnneeScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("postAnneeScolaire a démarré. "));

			List<String> roles = Arrays.asList("SiteManager");
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

			utilisateurAnneeScolaire(requeteSite, b -> {
				if(b.succeeded()) {
					RequeteApi requeteApi = new RequeteApi();
					requeteApi.setRows(1);
					requeteApi.setNumFound(1L);
					requeteApi.setNumPATCH(0L);
					requeteApi.initLoinRequeteApi(requeteSite);
					requeteSite.setRequeteApi_(requeteApi);
					requeteSite.getVertx().eventBus().publish("websocketAnneeScolaire", JsonObject.mapFrom(requeteApi).toString());
					postAnneeScolaireFuture(requeteSite, false, c -> {
						if(c.succeeded()) {
							AnneeScolaire anneeScolaire = c.result();
							requeteApi.setPk(anneeScolaire.getPk());
							postAnneeScolaireReponse(anneeScolaire, d -> {
									if(d.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
									LOGGER.info(String.format("postAnneeScolaire a réussi. "));
								} else {
									LOGGER.error(String.format("postAnneeScolaire a échoué. ", d.cause()));
									erreurAnneeScolaire(requeteSite, gestionnaireEvenements, d);
								}
							});
						} else {
							LOGGER.error(String.format("postAnneeScolaire a échoué. ", c.cause()));
							erreurAnneeScolaire(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("postAnneeScolaire a échoué. ", b.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postAnneeScolaire a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public Future<AnneeScolaire> postAnneeScolaireFuture(RequeteSiteFrFR requeteSite, Boolean inheritPk, Handler<AsyncResult<AnneeScolaire>> gestionnaireEvenements) {
		Promise<AnneeScolaire> promise = Promise.promise();
		try {
			sqlConnexionAnneeScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					sqlTransactionAnneeScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							creerAnneeScolaire(requeteSite, c -> {
								if(c.succeeded()) {
									AnneeScolaire anneeScolaire = c.result();
									sqlPOSTAnneeScolaire(anneeScolaire, inheritPk, d -> {
										if(d.succeeded()) {
											definirIndexerAnneeScolaire(anneeScolaire, e -> {
												if(e.succeeded()) {
													RequeteApi requeteApi = requeteSite.getRequeteApi_();
													if(requeteApi != null) {
														requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
														anneeScolaire.requeteApiAnneeScolaire();
														requeteSite.getVertx().eventBus().publish("websocketAnneeScolaire", JsonObject.mapFrom(requeteApi).toString());
													}
													gestionnaireEvenements.handle(Future.succeededFuture(anneeScolaire));
													promise.complete(anneeScolaire);
												} else {
													LOGGER.error(String.format("postAnneeScolaireFuture a échoué. ", e.cause()));
													gestionnaireEvenements.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											LOGGER.error(String.format("postAnneeScolaireFuture a échoué. ", d.cause()));
											gestionnaireEvenements.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("postAnneeScolaireFuture a échoué. ", c.cause()));
									gestionnaireEvenements.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("postAnneeScolaireFuture a échoué. ", b.cause()));
							gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("postAnneeScolaireFuture a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("postAnneeScolaireFuture a échoué. ", e));
			erreurAnneeScolaire(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTAnneeScolaire(AnneeScolaire o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.inheritPk a échoué", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.archive a échoué", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.supprime a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleCle":
						{
							Long l = Long.parseLong(jsonObject.getString(entiteVar));
							if(l != null) {
								ListeRecherche<Ecole> listeRecherche = new ListeRecherche<Ecole>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(Ecole.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(l2, "anneeCles", pk, "ecoleCle")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.ecoleCle a échoué", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("Ecole");
									}
								}
							}
						}
						break;
					case "saisonCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								ListeRecherche<SaisonScolaire> listeRecherche = new ListeRecherche<SaisonScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(SaisonScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(l2, "anneeCle", pk, "saisonCles")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.saisonCles a échoué", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SaisonScolaire");
									}
								}
							}
						}
						break;
					case "ageCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								ListeRecherche<AgeScolaire> listeRecherche = new ListeRecherche<AgeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AgeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(pk, "ageCles", l2, "anneeCle")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.ageCles a échoué", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("AgeScolaire");
									}
								}
							}
						}
						break;
					case "sessionDateDebut":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "sessionDateDebut", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.sessionDateDebut a échoué", b.cause())));
							});
						}));
						break;
					case "saisonDateDebut":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "saisonDateDebut", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.saisonDateDebut a échoué", b.cause())));
							});
						}));
						break;
					case "sessionDateFin":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "sessionDateFin", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.sessionDateFin a échoué", b.cause())));
							});
						}));
						break;
					case "anneeDebut":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "anneeDebut", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.anneeDebut a échoué", b.cause())));
							});
						}));
						break;
					case "anneeFin":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "anneeFin", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.anneeFin a échoué", b.cause())));
							});
						}));
						break;
					case "anneeFraisInscription":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "anneeFraisInscription", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.anneeFraisInscription a échoué", b.cause())));
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
					LOGGER.error(String.format("sqlPOSTAnneeScolaire a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPOSTAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void postAnneeScolaireReponse(AnneeScolaire anneeScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = anneeScolaire.getRequeteSite_();
		try {
			reponse200POSTAnneeScolaire(anneeScolaire, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("postAnneeScolaireReponse a échoué. ", a.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postAnneeScolaireReponse a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200POSTAnneeScolaire(AnneeScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			JsonObject json = JsonObject.mapFrom(o);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200POSTAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTImport //

	@Override
	public void putimportAnneeScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putimportAnneeScolaire a démarré. "));

			List<String> roles = Arrays.asList("SiteManager");
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

			utilisateurAnneeScolaire(requeteSite, b -> {
				if(b.succeeded()) {
					putimportAnneeScolaireReponse(requeteSite, c -> {
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
										requeteSite.getVertx().eventBus().publish("websocketAnneeScolaire", JsonObject.mapFrom(requeteApi).toString());
										varsAnneeScolaire(requeteSite, d -> {
											if(d.succeeded()) {
												listePUTImportAnneeScolaire(requeteApi, requeteSite, e -> {
													if(e.succeeded()) {
														putimportAnneeScolaireReponse(requeteSite, f -> {
															if(e.succeeded()) {
																LOGGER.info(String.format("putimportAnneeScolaire a réussi. "));
																blockingCodeHandler.handle(Future.succeededFuture(e.result()));
															} else {
																LOGGER.error(String.format("putimportAnneeScolaire a échoué. ", f.cause()));
																erreurAnneeScolaire(requeteSite, null, f);
															}
														});
													} else {
														LOGGER.error(String.format("putimportAnneeScolaire a échoué. ", e.cause()));
														erreurAnneeScolaire(requeteSite, null, e);
													}
												});
											} else {
												LOGGER.error(String.format("putimportAnneeScolaire a échoué. ", d.cause()));
												erreurAnneeScolaire(requeteSite, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putimportAnneeScolaire a échoué. ", ex));
										erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("putimportAnneeScolaire a échoué. ", c.cause()));
							erreurAnneeScolaire(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("putimportAnneeScolaire a échoué. ", b.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportAnneeScolaire a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePUTImportAnneeScolaire(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				json.put("cree", json.getValue("cree"));

				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, requeteSite.getOperationRequete(), json);
				requeteSite2.setRequeteApi_(requeteApi);
				requeteSite2.setRequeteVars(requeteSite.getRequeteVars());

				ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(AnneeScolaire.class);
				listeRecherche.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				listeRecherche.initLoinPourClasse(requeteSite2);

				if(listeRecherche.size() == 1) {
					AnneeScolaire o = listeRecherche.getList().stream().findFirst().orElse(null);
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
							patchAnneeScolaireFuture(o, true, a -> {
								if(a.succeeded()) {
								} else {
									LOGGER.error(String.format("listePUTImportAnneeScolaire a échoué. ", a.cause()));
									erreurAnneeScolaire(requeteSite2, gestionnaireEvenements, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postAnneeScolaireFuture(requeteSite2, true, a -> {
							if(a.succeeded()) {
							} else {
								LOGGER.error(String.format("listePUTImportAnneeScolaire a échoué. ", a.cause()));
								erreurAnneeScolaire(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
					reponse200PUTImportAnneeScolaire(requeteSite, gestionnaireEvenements);
				} else {
					LOGGER.error(String.format("listePUTImportAnneeScolaire a échoué. ", a.cause()));
					erreurAnneeScolaire(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("listePUTImportAnneeScolaire a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}

	public void putimportAnneeScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PUTImportAnneeScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putimportAnneeScolaireReponse a échoué. ", a.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportAnneeScolaireReponse a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PUTImportAnneeScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200PUTImportAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTFusion //

	@Override
	public void putfusionAnneeScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putfusionAnneeScolaire a démarré. "));

			List<String> roles = Arrays.asList("SiteManager");
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

			utilisateurAnneeScolaire(requeteSite, b -> {
				if(b.succeeded()) {
					putfusionAnneeScolaireReponse(requeteSite, c -> {
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
										requeteSite.getVertx().eventBus().publish("websocketAnneeScolaire", JsonObject.mapFrom(requeteApi).toString());
										varsAnneeScolaire(requeteSite, d -> {
											if(d.succeeded()) {
												listePUTFusionAnneeScolaire(requeteApi, requeteSite, e -> {
													if(e.succeeded()) {
														putfusionAnneeScolaireReponse(requeteSite, f -> {
															if(e.succeeded()) {
																LOGGER.info(String.format("putfusionAnneeScolaire a réussi. "));
																blockingCodeHandler.handle(Future.succeededFuture(e.result()));
															} else {
																LOGGER.error(String.format("putfusionAnneeScolaire a échoué. ", f.cause()));
																erreurAnneeScolaire(requeteSite, null, f);
															}
														});
													} else {
														LOGGER.error(String.format("putfusionAnneeScolaire a échoué. ", e.cause()));
														erreurAnneeScolaire(requeteSite, null, e);
													}
												});
											} else {
												LOGGER.error(String.format("putfusionAnneeScolaire a échoué. ", d.cause()));
												erreurAnneeScolaire(requeteSite, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putfusionAnneeScolaire a échoué. ", ex));
										erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("putfusionAnneeScolaire a échoué. ", c.cause()));
							erreurAnneeScolaire(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("putfusionAnneeScolaire a échoué. ", b.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putfusionAnneeScolaire a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePUTFusionAnneeScolaire(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, requeteSite.getOperationRequete(), json);
				requeteSite2.setRequeteApi_(requeteApi);
				requeteSite2.setRequeteVars(requeteSite.getRequeteVars());

				ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(AnneeScolaire.class);
				listeRecherche.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				listeRecherche.initLoinPourClasse(requeteSite2);

				if(listeRecherche.size() == 1) {
					AnneeScolaire o = listeRecherche.getList().stream().findFirst().orElse(null);
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
							patchAnneeScolaireFuture(o, false, a -> {
								if(a.succeeded()) {
								} else {
									LOGGER.error(String.format("listePUTFusionAnneeScolaire a échoué. ", a.cause()));
									erreurAnneeScolaire(requeteSite2, gestionnaireEvenements, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postAnneeScolaireFuture(requeteSite2, false, a -> {
							if(a.succeeded()) {
							} else {
								LOGGER.error(String.format("listePUTFusionAnneeScolaire a échoué. ", a.cause()));
								erreurAnneeScolaire(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
					reponse200PUTFusionAnneeScolaire(requeteSite, gestionnaireEvenements);
				} else {
					LOGGER.error(String.format("listePUTFusionAnneeScolaire a échoué. ", a.cause()));
					erreurAnneeScolaire(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("listePUTFusionAnneeScolaire a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}

	public void putfusionAnneeScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PUTFusionAnneeScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putfusionAnneeScolaireReponse a échoué. ", a.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putfusionAnneeScolaireReponse a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PUTFusionAnneeScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200PUTFusionAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTCopie //

	@Override
	public void putcopieAnneeScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putcopieAnneeScolaire a démarré. "));

			List<String> roles = Arrays.asList("SiteManager");
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

			utilisateurAnneeScolaire(requeteSite, b -> {
				if(b.succeeded()) {
					putcopieAnneeScolaireReponse(requeteSite, c -> {
						if(c.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
							WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
							executeurTravailleur.executeBlocking(
								blockingCodeHandler -> {
									try {
										rechercheAnneeScolaire(requeteSite, false, true, "/api/annee/copie", "PUTCopie", d -> {
											if(d.succeeded()) {
												ListeRecherche<AnneeScolaire> listeAnneeScolaire = d.result();
												RequeteApi requeteApi = new RequeteApi();
												requeteApi.setRows(listeAnneeScolaire.getRows());
												requeteApi.setNumFound(listeAnneeScolaire.getQueryResponse().getResults().getNumFound());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite);
												requeteSite.setRequeteApi_(requeteApi);
												requeteSite.getVertx().eventBus().publish("websocketAnneeScolaire", JsonObject.mapFrom(requeteApi).toString());
												try {
													listePUTCopieAnneeScolaire(requeteApi, listeAnneeScolaire, e -> {
														if(e.succeeded()) {
															putcopieAnneeScolaireReponse(requeteSite, f -> {
																if(f.succeeded()) {
																	LOGGER.info(String.format("putcopieAnneeScolaire a réussi. "));
																	blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																} else {
																	LOGGER.error(String.format("putcopieAnneeScolaire a échoué. ", f.cause()));
																	erreurAnneeScolaire(requeteSite, null, f);
																}
															});
														} else {
															LOGGER.error(String.format("putcopieAnneeScolaire a échoué. ", e.cause()));
															erreurAnneeScolaire(requeteSite, null, e);
														}
													});
												} catch(Exception ex) {
													LOGGER.error(String.format("putcopieAnneeScolaire a échoué. ", ex));
													erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
												}
											} else {
												LOGGER.error(String.format("putcopieAnneeScolaire a échoué. ", d.cause()));
												erreurAnneeScolaire(requeteSite, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putcopieAnneeScolaire a échoué. ", ex));
										erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("putcopieAnneeScolaire a échoué. ", c.cause()));
							erreurAnneeScolaire(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("putcopieAnneeScolaire a échoué. ", b.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopieAnneeScolaire a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePUTCopieAnneeScolaire(RequeteApi requeteApi, ListeRecherche<AnneeScolaire> listeAnneeScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeAnneeScolaire.getRequeteSite_();
		listeAnneeScolaire.getList().forEach(o -> {
			RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, requeteSite.getOperationRequete(), requeteSite.getObjetJson());
			requeteSite2.setRequeteApi_(requeteSite.getRequeteApi_());
			o.setRequeteSite_(requeteSite2);
			futures.add(
				putcopieAnneeScolaireFuture(requeteSite2, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
					} else {
						LOGGER.error(String.format("listePUTCopieAnneeScolaire a échoué. ", a.cause()));
						erreurAnneeScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeAnneeScolaire.size());
				if(listeAnneeScolaire.next()) {
					listePUTCopieAnneeScolaire(requeteApi, listeAnneeScolaire, gestionnaireEvenements);
				} else {
					reponse200PUTCopieAnneeScolaire(requeteSite, gestionnaireEvenements);
				}
			} else {
				LOGGER.error(String.format("listePUTCopieAnneeScolaire a échoué. ", a.cause()));
				erreurAnneeScolaire(listeAnneeScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<AnneeScolaire> putcopieAnneeScolaireFuture(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Handler<AsyncResult<AnneeScolaire>> gestionnaireEvenements) {
		Promise<AnneeScolaire> promise = Promise.promise();
		try {

			jsonObject.put("sauvegardes", Optional.ofNullable(jsonObject.getJsonArray("sauvegardes")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("sauvegardes").add(o.getKey());
			});

			sqlConnexionAnneeScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					sqlTransactionAnneeScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							creerAnneeScolaire(requeteSite, c -> {
								if(c.succeeded()) {
									AnneeScolaire anneeScolaire = c.result();
									sqlPUTCopieAnneeScolaire(anneeScolaire, jsonObject, d -> {
										if(d.succeeded()) {
											definirIndexerAnneeScolaire(anneeScolaire, e -> {
												if(e.succeeded()) {
													RequeteApi requeteApi = requeteSite.getRequeteApi_();
													if(requeteApi != null) {
														requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
														if(requeteApi.getNumFound() == 1L) {
															anneeScolaire.requeteApiAnneeScolaire();
														}
														requeteSite.getVertx().eventBus().publish("websocketAnneeScolaire", JsonObject.mapFrom(requeteApi).toString());
													}
													gestionnaireEvenements.handle(Future.succeededFuture(anneeScolaire));
													promise.complete(anneeScolaire);
												} else {
													LOGGER.error(String.format("putcopieAnneeScolaireFuture a échoué. ", e.cause()));
													gestionnaireEvenements.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											LOGGER.error(String.format("putcopieAnneeScolaireFuture a échoué. ", d.cause()));
											gestionnaireEvenements.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("putcopieAnneeScolaireFuture a échoué. ", c.cause()));
									gestionnaireEvenements.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("putcopieAnneeScolaireFuture a échoué. ", b.cause()));
							gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("putcopieAnneeScolaireFuture a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("putcopieAnneeScolaireFuture a échoué. ", e));
			erreurAnneeScolaire(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopieAnneeScolaire(AnneeScolaire o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.inheritPk a échoué", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.archive a échoué", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.supprime a échoué", b.cause())));
							});
						}));
						break;
					case "ecoleCle":
							{
						Long l = Long.parseLong(jsonObject.getString(entiteVar));
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_addA
									, Tuple.of(l, "anneeCles", pk, "ecoleCle")
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.ecoleCle a échoué", b.cause())));
							});
						}));
						}
						break;
					case "saisonCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_addA
										, Tuple.of(l, "anneeCle", pk, "saisonCles")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.saisonCles a échoué", b.cause())));
								});
							}));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("SaisonScolaire");
							}
						}
						break;
					case "ageCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_addA
										, Tuple.of(pk, "ageCles", l, "anneeCle")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.ageCles a échoué", b.cause())));
								});
							}));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("AgeScolaire");
							}
						}
						break;
					case "sessionDateDebut":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "sessionDateDebut", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.sessionDateDebut a échoué", b.cause())));
							});
						}));
						break;
					case "saisonDateDebut":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "saisonDateDebut", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.saisonDateDebut a échoué", b.cause())));
							});
						}));
						break;
					case "sessionDateFin":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "sessionDateFin", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.sessionDateFin a échoué", b.cause())));
							});
						}));
						break;
					case "anneeDebut":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "anneeDebut", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.anneeDebut a échoué", b.cause())));
							});
						}));
						break;
					case "anneeFin":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "anneeFin", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.anneeFin a échoué", b.cause())));
							});
						}));
						break;
					case "anneeFraisInscription":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "anneeFraisInscription", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.anneeFraisInscription a échoué", b.cause())));
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
					LOGGER.error(String.format("sqlPUTCopieAnneeScolaire a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPUTCopieAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void putcopieAnneeScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PUTCopieAnneeScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putcopieAnneeScolaireReponse a échoué. ", a.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopieAnneeScolaireReponse a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PUTCopieAnneeScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200PUTCopieAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchAnneeScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("patchAnneeScolaire a démarré. "));

			List<String> roles = Arrays.asList("SiteManager");
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

			utilisateurAnneeScolaire(requeteSite, b -> {
				if(b.succeeded()) {
					patchAnneeScolaireReponse(requeteSite, c -> {
						if(c.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
							WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
							executeurTravailleur.executeBlocking(
								blockingCodeHandler -> {
									try {
										rechercheAnneeScolaire(requeteSite, false, true, "/api/annee", "PATCH", d -> {
											if(d.succeeded()) {
												ListeRecherche<AnneeScolaire> listeAnneeScolaire = d.result();
												RequeteApi requeteApi = new RequeteApi();
												requeteApi.setRows(listeAnneeScolaire.getRows());
												requeteApi.setNumFound(listeAnneeScolaire.getQueryResponse().getResults().getNumFound());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite);
												requeteSite.setRequeteApi_(requeteApi);
												requeteSite.getVertx().eventBus().publish("websocketAnneeScolaire", JsonObject.mapFrom(requeteApi).toString());
												SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeAnneeScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
												Date date = null;
												if(facets != null)
													date = (Date)facets.get("max_modifie");
												String dt;
												if(date == null)
													dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
												else
													dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
												listeAnneeScolaire.addFilterQuery(String.format("modifie_indexed_date:[* TO %s]", dt));

												try {
													listePATCHAnneeScolaire(requeteApi, listeAnneeScolaire, dt, e -> {
														if(e.succeeded()) {
															patchAnneeScolaireReponse(requeteSite, f -> {
																if(f.succeeded()) {
																	LOGGER.info(String.format("patchAnneeScolaire a réussi. "));
																	blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																} else {
																	LOGGER.error(String.format("patchAnneeScolaire a échoué. ", f.cause()));
																	erreurAnneeScolaire(requeteSite, null, f);
																}
															});
														} else {
															LOGGER.error(String.format("patchAnneeScolaire a échoué. ", e.cause()));
															erreurAnneeScolaire(requeteSite, null, e);
														}
													});
												} catch(Exception ex) {
													LOGGER.error(String.format("patchAnneeScolaire a échoué. ", ex));
													erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
												}
											} else {
												LOGGER.error(String.format("patchAnneeScolaire a échoué. ", d.cause()));
												erreurAnneeScolaire(requeteSite, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("patchAnneeScolaire a échoué. ", ex));
										erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("patchAnneeScolaire a échoué. ", c.cause()));
							erreurAnneeScolaire(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("patchAnneeScolaire a échoué. ", b.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchAnneeScolaire a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePATCHAnneeScolaire(RequeteApi requeteApi, ListeRecherche<AnneeScolaire> listeAnneeScolaire, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeAnneeScolaire.getRequeteSite_();
		listeAnneeScolaire.getList().forEach(o -> {
			RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, requeteSite.getOperationRequete(), requeteSite.getObjetJson());
			requeteSite2.setRequeteApi_(requeteSite.getRequeteApi_());
			o.setRequeteSite_(requeteSite2);
			futures.add(
				patchAnneeScolaireFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						erreurAnneeScolaire(requeteSite2, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listeAnneeScolaire.next(dt)) {
					listePATCHAnneeScolaire(requeteApi, listeAnneeScolaire, dt, gestionnaireEvenements);
				} else {
					reponse200PATCHAnneeScolaire(requeteSite, gestionnaireEvenements);
				}
			} else {
				LOGGER.error(String.format("listePATCHAnneeScolaire a échoué. ", a.cause()));
				erreurAnneeScolaire(listeAnneeScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<AnneeScolaire> patchAnneeScolaireFuture(AnneeScolaire o, Boolean inheritPk, Handler<AsyncResult<AnneeScolaire>> gestionnaireEvenements) {
		Promise<AnneeScolaire> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			if(requeteApi != null && requeteApi.getNumFound() == 1L) {
				requeteApi.setOriginal(o);
				requeteApi.setPk(o.getPk());
			}
			sqlConnexionAnneeScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					sqlTransactionAnneeScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							sqlPATCHAnneeScolaire(o, inheritPk, c -> {
								if(c.succeeded()) {
									AnneeScolaire anneeScolaire = c.result();
									definirIndexerAnneeScolaire(anneeScolaire, d -> {
										if(d.succeeded()) {
											if(requeteApi != null) {
												requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
												if(requeteApi.getNumFound() == 1L) {
													anneeScolaire.requeteApiAnneeScolaire();
												}
												requeteSite.getVertx().eventBus().publish("websocketAnneeScolaire", JsonObject.mapFrom(requeteApi).toString());
											}
											gestionnaireEvenements.handle(Future.succeededFuture(anneeScolaire));
											promise.complete(anneeScolaire);
										} else {
											LOGGER.error(String.format("patchAnneeScolaireFuture a échoué. ", d.cause()));
											gestionnaireEvenements.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("patchAnneeScolaireFuture a échoué. ", c.cause()));
									gestionnaireEvenements.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("patchAnneeScolaireFuture a échoué. ", b.cause()));
							gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("patchAnneeScolaireFuture a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("patchAnneeScolaireFuture a échoué. ", e));
			erreurAnneeScolaire(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHAnneeScolaire(AnneeScolaire o, Boolean inheritPk, Handler<AsyncResult<AnneeScolaire>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Transaction tx = requeteSite.getTx();
			Long pk = o.getPk();
			JsonObject jsonObject = requeteSite.getObjetJson();
			Set<String> methodeNoms = jsonObject.fieldNames();
			AnneeScolaire o2 = new AnneeScolaire();
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
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.inheritPk a échoué", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.inheritPk a échoué", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.archive a échoué", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.archive a échoué", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.supprime a échoué", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.supprime a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setEcoleCle":
						{
							Long l = o2.getEcoleCle();
							if(l != null && !l.equals(o.getEcoleCle())) {
								ListeRecherche<Ecole> listeRecherche = new ListeRecherche<Ecole>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(Ecole.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									o2.setEcoleCle(jsonObject.getString(methodeNom));
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(l2, "anneeCles", pk, "ecoleCle")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.ecoleCle a échoué", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("Ecole");
									}
								}
							}
						}
						break;
					case "removeEcoleCle":
						{
							Long l = o2.getEcoleCle();
							if(l != null) {
								ListeRecherche<Ecole> listeRecherche = new ListeRecherche<Ecole>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(Ecole.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && l2.equals(o.getEcoleCle())) {
									o2.setEcoleCle(jsonObject.getString(methodeNom));
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_removeA
												, Tuple.of(l2, "anneeCles", pk, "ecoleCle")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.ecoleCle a échoué", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("Ecole");
									}
								}
							}
						}
						break;
					case "addSaisonCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<SaisonScolaire> listeRecherche = new ListeRecherche<SaisonScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(SaisonScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getSaisonCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(l2, "anneeCle", pk, "saisonCles")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.saisonCles a échoué", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SaisonScolaire");
									}
								}
							}
						}
						break;
					case "addAllSaisonCles":
						JsonArray addAllSaisonClesValeurs = jsonObject.getJsonArray(methodeNom);
						if(addAllSaisonClesValeurs != null) {
							for(Integer i = 0; i <  addAllSaisonClesValeurs.size(); i++) {
								Long l = Long.parseLong(addAllSaisonClesValeurs.getString(i));
								if(l != null) {
									ListeRecherche<SaisonScolaire> listeRecherche = new ListeRecherche<SaisonScolaire>();
									listeRecherche.setQuery("*:*");
									listeRecherche.setStocker(true);
									listeRecherche.setC(SaisonScolaire.class);
									listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									listeRecherche.initLoinListeRecherche(requeteSite);
									Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getSaisonCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(l2, "anneeCle", pk, "saisonCles")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.saisonCles a échoué", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SaisonScolaire");
										}
									}
								}
							}
						}
						break;
					case "setSaisonCles":
						JsonArray setSaisonClesValeurs = jsonObject.getJsonArray(methodeNom);
						if(setSaisonClesValeurs != null) {
							for(Integer i = 0; i <  setSaisonClesValeurs.size(); i++) {
								Long l = Long.parseLong(setSaisonClesValeurs.getString(i));
								if(l != null) {
									ListeRecherche<SaisonScolaire> listeRecherche = new ListeRecherche<SaisonScolaire>();
									listeRecherche.setQuery("*:*");
									listeRecherche.setStocker(true);
									listeRecherche.setC(SaisonScolaire.class);
									listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									listeRecherche.initLoinListeRecherche(requeteSite);
									Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getSaisonCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(l2, "anneeCle", pk, "saisonCles")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.saisonCles a échoué", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SaisonScolaire");
										}
									}
								}
							}
						}
						if(o.getSaisonCles() != null) {
							for(Long l :  o.getSaisonCles()) {
								if(l != null && (setSaisonClesValeurs == null || !setSaisonClesValeurs.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_removeA
												, Tuple.of(l, "anneeCle", pk, "saisonCles")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.saisonCles a échoué", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removeSaisonCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<SaisonScolaire> listeRecherche = new ListeRecherche<SaisonScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(SaisonScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getSaisonCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_removeA
												, Tuple.of(l2, "anneeCle", pk, "saisonCles")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.saisonCles a échoué", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SaisonScolaire");
									}
								}
							}
						}
						break;
					case "addAgeCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<AgeScolaire> listeRecherche = new ListeRecherche<AgeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AgeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getAgeCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(pk, "ageCles", l2, "anneeCle")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.ageCles a échoué", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("AgeScolaire");
									}
								}
							}
						}
						break;
					case "addAllAgeCles":
						JsonArray addAllAgeClesValeurs = jsonObject.getJsonArray(methodeNom);
						if(addAllAgeClesValeurs != null) {
							for(Integer i = 0; i <  addAllAgeClesValeurs.size(); i++) {
								Long l = Long.parseLong(addAllAgeClesValeurs.getString(i));
								if(l != null) {
									ListeRecherche<AgeScolaire> listeRecherche = new ListeRecherche<AgeScolaire>();
									listeRecherche.setQuery("*:*");
									listeRecherche.setStocker(true);
									listeRecherche.setC(AgeScolaire.class);
									listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									listeRecherche.initLoinListeRecherche(requeteSite);
									Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getAgeCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(pk, "ageCles", l2, "anneeCle")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.ageCles a échoué", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("AgeScolaire");
										}
									}
								}
							}
						}
						break;
					case "setAgeCles":
						JsonArray setAgeClesValeurs = jsonObject.getJsonArray(methodeNom);
						if(setAgeClesValeurs != null) {
							for(Integer i = 0; i <  setAgeClesValeurs.size(); i++) {
								Long l = Long.parseLong(setAgeClesValeurs.getString(i));
								if(l != null) {
									ListeRecherche<AgeScolaire> listeRecherche = new ListeRecherche<AgeScolaire>();
									listeRecherche.setQuery("*:*");
									listeRecherche.setStocker(true);
									listeRecherche.setC(AgeScolaire.class);
									listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									listeRecherche.initLoinListeRecherche(requeteSite);
									Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getAgeCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(pk, "ageCles", l2, "anneeCle")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.ageCles a échoué", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("AgeScolaire");
										}
									}
								}
							}
						}
						if(o.getAgeCles() != null) {
							for(Long l :  o.getAgeCles()) {
								if(l != null && (setAgeClesValeurs == null || !setAgeClesValeurs.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_removeA
												, Tuple.of(pk, "ageCles", l, "anneeCle")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.ageCles a échoué", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removeAgeCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<AgeScolaire> listeRecherche = new ListeRecherche<AgeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AgeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getAgeCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_removeA
												, Tuple.of(pk, "ageCles", l2, "anneeCle")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.ageCles a échoué", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("AgeScolaire");
									}
								}
							}
						}
						break;
					case "setSessionDateDebut":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "sessionDateDebut")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.sessionDateDebut a échoué", b.cause())));
								});
							}));
						} else {
							o2.setSessionDateDebut(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "sessionDateDebut", o2.jsonSessionDateDebut())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.sessionDateDebut a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setSaisonDateDebut":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "saisonDateDebut")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.saisonDateDebut a échoué", b.cause())));
								});
							}));
						} else {
							o2.setSaisonDateDebut(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "saisonDateDebut", o2.jsonSaisonDateDebut())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.saisonDateDebut a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setSessionDateFin":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "sessionDateFin")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.sessionDateFin a échoué", b.cause())));
								});
							}));
						} else {
							o2.setSessionDateFin(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "sessionDateFin", o2.jsonSessionDateFin())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.sessionDateFin a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setAnneeDebut":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "anneeDebut")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.anneeDebut a échoué", b.cause())));
								});
							}));
						} else {
							o2.setAnneeDebut(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "anneeDebut", o2.jsonAnneeDebut())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.anneeDebut a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setAnneeFin":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "anneeFin")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.anneeFin a échoué", b.cause())));
								});
							}));
						} else {
							o2.setAnneeFin(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "anneeFin", o2.jsonAnneeFin())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.anneeFin a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setAnneeFraisInscription":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "anneeFraisInscription")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.anneeFraisInscription a échoué", b.cause())));
								});
							}));
						} else {
							o2.setAnneeFraisInscription(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "anneeFraisInscription", o2.jsonAnneeFraisInscription())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur AnneeScolaire.anneeFraisInscription a échoué", b.cause())));
								});
							}));
						}
						break;
				}
			}
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					AnneeScolaire o3 = new AnneeScolaire();
					o3.setRequeteSite_(o.getRequeteSite_());
					o3.setPk(pk);
					gestionnaireEvenements.handle(Future.succeededFuture(o3));
				} else {
					LOGGER.error(String.format("sqlPATCHAnneeScolaire a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPATCHAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void patchAnneeScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PATCHAnneeScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("patchAnneeScolaireReponse a échoué. ", a.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchAnneeScolaireReponse a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PATCHAnneeScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200PATCHAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getAnneeScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, operationRequete);
		try {

			List<String> roles = Arrays.asList("SiteManager");
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

			utilisateurAnneeScolaire(requeteSite, b -> {
				if(b.succeeded()) {
					rechercheAnneeScolaire(requeteSite, false, true, "/api/annee/{id}", "GET", c -> {
						if(c.succeeded()) {
							ListeRecherche<AnneeScolaire> listeAnneeScolaire = c.result();
							getAnneeScolaireReponse(listeAnneeScolaire, d -> {
								if(d.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
									LOGGER.info(String.format("getAnneeScolaire a réussi. "));
								} else {
									LOGGER.error(String.format("getAnneeScolaire a échoué. ", d.cause()));
									erreurAnneeScolaire(requeteSite, gestionnaireEvenements, d);
								}
							});
						} else {
							LOGGER.error(String.format("getAnneeScolaire a échoué. ", c.cause()));
							erreurAnneeScolaire(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("getAnneeScolaire a échoué. ", b.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getAnneeScolaire a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void getAnneeScolaireReponse(ListeRecherche<AnneeScolaire> listeAnneeScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeAnneeScolaire.getRequeteSite_();
		try {
			reponse200GETAnneeScolaire(listeAnneeScolaire, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("getAnneeScolaireReponse a échoué. ", a.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getAnneeScolaireReponse a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200GETAnneeScolaire(ListeRecherche<AnneeScolaire> listeAnneeScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeAnneeScolaire.getRequeteSite_();
			SolrDocumentList documentsSolr = listeAnneeScolaire.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listeAnneeScolaire.getList().stream().findFirst().orElse(null));
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200GETAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void rechercheAnneeScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, operationRequete);
		try {

			List<String> roles = Arrays.asList("SiteManager");
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

			utilisateurAnneeScolaire(requeteSite, b -> {
				if(b.succeeded()) {
					rechercheAnneeScolaire(requeteSite, false, true, "/api/annee", "Recherche", c -> {
						if(c.succeeded()) {
							ListeRecherche<AnneeScolaire> listeAnneeScolaire = c.result();
							rechercheAnneeScolaireReponse(listeAnneeScolaire, d -> {
								if(d.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
									LOGGER.info(String.format("rechercheAnneeScolaire a réussi. "));
								} else {
									LOGGER.error(String.format("rechercheAnneeScolaire a échoué. ", d.cause()));
									erreurAnneeScolaire(requeteSite, gestionnaireEvenements, d);
								}
							});
						} else {
							LOGGER.error(String.format("rechercheAnneeScolaire a échoué. ", c.cause()));
							erreurAnneeScolaire(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("rechercheAnneeScolaire a échoué. ", b.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("rechercheAnneeScolaire a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void rechercheAnneeScolaireReponse(ListeRecherche<AnneeScolaire> listeAnneeScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeAnneeScolaire.getRequeteSite_();
		try {
			reponse200RechercheAnneeScolaire(listeAnneeScolaire, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("rechercheAnneeScolaireReponse a échoué. ", a.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("rechercheAnneeScolaireReponse a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200RechercheAnneeScolaire(ListeRecherche<AnneeScolaire> listeAnneeScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeAnneeScolaire.getRequeteSite_();
			QueryResponse reponseRecherche = listeAnneeScolaire.getQueryResponse();
			SolrDocumentList documentsSolr = listeAnneeScolaire.getSolrDocumentList();
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
			listeAnneeScolaire.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listeAnneeScolaire.getFields();
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
			LOGGER.error(String.format("reponse200RechercheAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PageRecherche //

	@Override
	public void pagerechercheAnneeScolaireId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		pagerechercheAnneeScolaire(operationRequete, gestionnaireEvenements);
	}

	@Override
	public void pagerechercheAnneeScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, operationRequete);
		try {

			List<String> roles = Arrays.asList("SiteManager");
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

			utilisateurAnneeScolaire(requeteSite, b -> {
				if(b.succeeded()) {
					rechercheAnneeScolaire(requeteSite, false, true, "/annee", "PageRecherche", c -> {
						if(c.succeeded()) {
							ListeRecherche<AnneeScolaire> listeAnneeScolaire = c.result();
							pagerechercheAnneeScolaireReponse(listeAnneeScolaire, d -> {
								if(d.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
									LOGGER.info(String.format("pagerechercheAnneeScolaire a réussi. "));
								} else {
									LOGGER.error(String.format("pagerechercheAnneeScolaire a échoué. ", d.cause()));
									erreurAnneeScolaire(requeteSite, gestionnaireEvenements, d);
								}
							});
						} else {
							LOGGER.error(String.format("pagerechercheAnneeScolaire a échoué. ", c.cause()));
							erreurAnneeScolaire(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("pagerechercheAnneeScolaire a échoué. ", b.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("pagerechercheAnneeScolaire a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void pagerechercheAnneeScolaireReponse(ListeRecherche<AnneeScolaire> listeAnneeScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeAnneeScolaire.getRequeteSite_();
		try {
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(requeteSite, buffer);
			requeteSite.setW(w);
			reponse200PageRechercheAnneeScolaire(listeAnneeScolaire, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("pagerechercheAnneeScolaireReponse a échoué. ", a.cause()));
					erreurAnneeScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("pagerechercheAnneeScolaireReponse a échoué. ", ex));
			erreurAnneeScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PageRechercheAnneeScolaire(ListeRecherche<AnneeScolaire> listeAnneeScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeAnneeScolaire.getRequeteSite_();
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(listeAnneeScolaire.getRequeteSite_(), buffer);
			AnneePage page = new AnneePage();
			SolrDocument pageDocumentSolr = new SolrDocument();
			CaseInsensitiveHeaders requeteEnTetes = new CaseInsensitiveHeaders();
			requeteSite.setRequeteEnTetes(requeteEnTetes);

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/annee");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			if(listeAnneeScolaire.size() == 1)
				requeteSite.setRequetePk(listeAnneeScolaire.get(0).getPk());
			requeteSite.setW(w);
			page.setListeAnneeScolaire(listeAnneeScolaire);
			page.setRequeteSite_(requeteSite);
			page.initLoinAnneePage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requeteEnTetes)));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200PageRechercheAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<AnneeScolaire> definirIndexerAnneeScolaire(AnneeScolaire anneeScolaire, Handler<AsyncResult<AnneeScolaire>> gestionnaireEvenements) {
		Promise<AnneeScolaire> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = anneeScolaire.getRequeteSite_();
		definirAnneeScolaire(anneeScolaire, c -> {
			if(c.succeeded()) {
				attribuerAnneeScolaire(anneeScolaire, d -> {
					if(d.succeeded()) {
						indexerAnneeScolaire(anneeScolaire, e -> {
							if(e.succeeded()) {
								sqlCommitAnneeScolaire(requeteSite, f -> {
									if(f.succeeded()) {
										sqlFermerAnneeScolaire(requeteSite, g -> {
											if(g.succeeded()) {
												rechargerAnneeScolaire(anneeScolaire, h -> {
													if(h.succeeded()) {
														gestionnaireEvenements.handle(Future.succeededFuture(anneeScolaire));
														promise.complete(anneeScolaire);
													} else {
														LOGGER.error(String.format("rechargerAnneeScolaire a échoué. ", h.cause()));
														erreurAnneeScolaire(requeteSite, null, h);
													}
												});
											} else {
												LOGGER.error(String.format("definirIndexerAnneeScolaire a échoué. ", g.cause()));
												erreurAnneeScolaire(requeteSite, null, g);
											}
										});
									} else {
										LOGGER.error(String.format("definirIndexerAnneeScolaire a échoué. ", f.cause()));
										erreurAnneeScolaire(requeteSite, null, f);
									}
								});
							} else {
								LOGGER.error(String.format("definirIndexerAnneeScolaire a échoué. ", e.cause()));
								erreurAnneeScolaire(requeteSite, null, e);
							}
						});
					} else {
						LOGGER.error(String.format("definirIndexerAnneeScolaire a échoué. ", d.cause()));
						erreurAnneeScolaire(requeteSite, null, d);
					}
				});
			} else {
				LOGGER.error(String.format("definirIndexerAnneeScolaire a échoué. ", c.cause()));
				erreurAnneeScolaire(requeteSite, null, c);
			}
		});
		return promise.future();
	}

	public void creerAnneeScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<AnneeScolaire>> gestionnaireEvenements) {
		try {
			Transaction tx = requeteSite.getTx();
			String utilisateurId = requeteSite.getUtilisateurId();
			ZonedDateTime cree = Optional.ofNullable(requeteSite.getObjetJson()).map(j -> j.getString("cree")).map(s -> ZonedDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of(requeteSite.getConfigSite_().getSiteZone())))).orElse(ZonedDateTime.now(ZoneId.of(requeteSite.getConfigSite_().getSiteZone())));

			tx.preparedQuery(
					SiteContexteFrFR.SQL_creer
					, Tuple.of(AnneeScolaire.class.getCanonicalName(), utilisateurId, cree.toOffsetDateTime())
					, Collectors.toList()
					, creerAsync
			-> {
				if(creerAsync.succeeded()) {
					Row creerLigne = creerAsync.result().value().stream().findFirst().orElseGet(() -> null);
					Long pk = creerLigne.getLong(0);
					AnneeScolaire o = new AnneeScolaire();
					o.setPk(pk);
					o.setRequeteSite_(requeteSite);
					gestionnaireEvenements.handle(Future.succeededFuture(o));
				} else {
					LOGGER.error(String.format("creerAnneeScolaire a échoué. ", creerAsync.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(creerAsync.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("creerAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void erreurAnneeScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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
		sqlRollbackAnneeScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				LOGGER.info(String.format("sql rollback. "));
				sqlFermerAnneeScolaire(requeteSite, b -> {
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

	public void sqlConnexionAnneeScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
						LOGGER.error(String.format("sqlConnexionAnneeScolaire a échoué. ", a.cause()));
						gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlTransactionAnneeScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
			LOGGER.error(String.format("sqlTransactionAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlCommitAnneeScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
						LOGGER.error(String.format("sqlCommitAnneeScolaire a échoué. ", a.cause()));
						gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlRollbackAnneeScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
						LOGGER.error(String.format("sqlRollbackAnneeScolaire a échoué. ", a.cause()));
						gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlFermerAnneeScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
			LOGGER.error(String.format("sqlFermerAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public RequeteSiteFrFR genererRequeteSiteFrFRPourAnneeScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete) {
		return genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, operationRequete, null);
	}

	public RequeteSiteFrFR genererRequeteSiteFrFRPourAnneeScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete, JsonObject body) {
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

	public void utilisateurAnneeScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			String utilisateurId = requeteSite.getUtilisateurId();
			if(utilisateurId == null) {
				gestionnaireEvenements.handle(Future.succeededFuture());
			} else {
				sqlConnexionAnneeScolaire(requeteSite, a -> {
					if(a.succeeded()) {
						sqlTransactionAnneeScolaire(requeteSite, b -> {
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
												jsonObject.put("utilisateurMail", principalJson.getString("email"));
												utilisateurAnneeScolaireDefinir(requeteSite, jsonObject, false);

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
																		erreurAnneeScolaire(requeteSite, gestionnaireEvenements, e);
																	}
																});
															} else {
																erreurAnneeScolaire(requeteSite, gestionnaireEvenements, d);
															}
														});
													} else {
														erreurAnneeScolaire(requeteSite, gestionnaireEvenements, c);
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
												Boolean definir = utilisateurAnneeScolaireDefinir(requeteSite, jsonObject, true);
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
																	erreurAnneeScolaire(requeteSite, gestionnaireEvenements, e);
																}
															});
														} else {
															erreurAnneeScolaire(requeteSite, gestionnaireEvenements, d);
														}
													});
												} else {
													requeteSite.setUtilisateurSite(utilisateurSite1);
													requeteSite.setUtilisateurNom(utilisateurSite1.getUtilisateurNom());
													requeteSite.setUtilisateurPrenom(utilisateurSite1.getUtilisateurPrenom());
													requeteSite.setUtilisateurNomFamille(utilisateurSite1.getUtilisateurNomFamille());
													requeteSite.setUtilisateurId(utilisateurSite1.getUtilisateurId());
													requeteSite.setUtilisateurCle(utilisateurSite1.getPk());
													sqlRollbackAnneeScolaire(requeteSite, c -> {
														if(c.succeeded()) {
															gestionnaireEvenements.handle(Future.succeededFuture());
														} else {
															gestionnaireEvenements.handle(Future.failedFuture(c.cause()));
															erreurAnneeScolaire(requeteSite, gestionnaireEvenements, c);
														}
													});
												}
											}
										} catch(Exception e) {
											LOGGER.error(String.format("utilisateurAnneeScolaire a échoué. ", e));
											gestionnaireEvenements.handle(Future.failedFuture(e));
										}
									} else {
										LOGGER.error(String.format("utilisateurAnneeScolaire a échoué. ", selectCAsync.cause()));
										gestionnaireEvenements.handle(Future.failedFuture(selectCAsync.cause()));
									}
								});
							} else {
								LOGGER.error(String.format("utilisateurAnneeScolaire a échoué. ", b.cause()));
								gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
							}
						});
					} else {
						LOGGER.error(String.format("utilisateurAnneeScolaire a échoué. ", a.cause()));
						gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("utilisateurAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public Boolean utilisateurAnneeScolaireDefinir(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Boolean patch) {
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

	public void rechercheAnneeScolaireQ(String uri, String apiMethode, ListeRecherche<AnneeScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		listeRecherche.setQuery(varIndexe + ":" + ("*".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));
		if(!"*".equals(entiteVar)) {
			listeRecherche.setHighlight(true);
			listeRecherche.setHighlightSnippets(3);
			listeRecherche.addHighlightField(varIndexe);
			listeRecherche.setParam("hl.encoder", "html");
		}
	}

	public void rechercheAnneeScolaireFq(String uri, String apiMethode, ListeRecherche<AnneeScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
	}

	public void rechercheAnneeScolaireSort(String uri, String apiMethode, ListeRecherche<AnneeScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurIndexe));
	}

	public void rechercheAnneeScolaireRows(String uri, String apiMethode, ListeRecherche<AnneeScolaire> listeRecherche, Integer valeurRows) {
			listeRecherche.setRows(apiMethode != null && apiMethode.contains("Recherche") ? valeurRows : 10);
	}

	public void rechercheAnneeScolaireStart(String uri, String apiMethode, ListeRecherche<AnneeScolaire> listeRecherche, Integer valeurStart) {
		listeRecherche.setStart(valeurStart);
	}

	public void rechercheAnneeScolaireVar(String uri, String apiMethode, ListeRecherche<AnneeScolaire> listeRecherche, String var, String valeur) {
		listeRecherche.getRequeteSite_().getRequeteVars().put(var, valeur);
	}

	public void rechercheAnneeScolaireUri(String uri, String apiMethode, ListeRecherche<AnneeScolaire> listeRecherche) {
	}

	public void varsAnneeScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<ListeRecherche<OperationResponse>>> gestionnaireEvenements) {
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
					LOGGER.error(String.format("rechercheAnneeScolaire a échoué. ", e));
					gestionnaireEvenements.handle(Future.failedFuture(e));
				}
			});
			gestionnaireEvenements.handle(Future.succeededFuture());
		} catch(Exception e) {
			LOGGER.error(String.format("rechercheAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void rechercheAnneeScolaire(RequeteSiteFrFR requeteSite, Boolean peupler, Boolean stocker, String uri, String apiMethode, Handler<AsyncResult<ListeRecherche<AnneeScolaire>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(AnneeScolaire.class);
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
								varIndexe = "*".equals(entiteVar) ? entiteVar : AnneeScolaire.varRechercheAnneeScolaire(entiteVar);
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								valeurIndexe = StringUtils.isEmpty(valeurIndexe) ? "*" : valeurIndexe;
								rechercheAnneeScolaireQ(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "fq":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								varIndexe = AnneeScolaire.varIndexeAnneeScolaire(entiteVar);
								rechercheAnneeScolaireFq(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "sort":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
								valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
								varIndexe = AnneeScolaire.varIndexeAnneeScolaire(entiteVar);
								rechercheAnneeScolaireSort(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "start":
								valeurStart = (Integer)paramObjet;
								rechercheAnneeScolaireStart(uri, apiMethode, listeRecherche, valeurStart);
								break;
							case "rows":
								valeurRows = (Integer)paramObjet;
								rechercheAnneeScolaireRows(uri, apiMethode, listeRecherche, valeurRows);
								break;
							case "var":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								rechercheAnneeScolaireVar(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe);
								break;
						}
					}
					rechercheAnneeScolaireUri(uri, apiMethode, listeRecherche);
				} catch(Exception e) {
					LOGGER.error(String.format("rechercheAnneeScolaire a échoué. ", e));
					gestionnaireEvenements.handle(Future.failedFuture(e));
				}
			});
			if(listeRecherche.getSorts().size() == 0) {
				listeRecherche.addSort("cree_indexed_date", ORDER.desc);
			}
			listeRecherche.initLoinPourClasse(requeteSite);
			gestionnaireEvenements.handle(Future.succeededFuture(listeRecherche));
		} catch(Exception e) {
			LOGGER.error(String.format("rechercheAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void definirAnneeScolaire(AnneeScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
						LOGGER.error(String.format("definirAnneeScolaire a échoué. ", e));
						gestionnaireEvenements.handle(Future.failedFuture(e));
					}
				} else {
					LOGGER.error(String.format("definirAnneeScolaire a échoué. ", definirAsync.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(definirAsync.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("definirAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void attribuerAnneeScolaire(AnneeScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
						LOGGER.error(String.format("attribuerAnneeScolaire a échoué. ", attribuerAsync.cause()));
						gestionnaireEvenements.handle(Future.failedFuture(attribuerAsync.cause()));
					}
				} catch(Exception e) {
					LOGGER.error(String.format("attribuerAnneeScolaire a échoué. ", e));
					gestionnaireEvenements.handle(Future.failedFuture(e));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("attribuerAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void indexerAnneeScolaire(AnneeScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
			o.initLoinPourClasse(requeteSite);
			o.indexerPourClasse();
			gestionnaireEvenements.handle(Future.succeededFuture());
		} catch(Exception e) {
			LOGGER.error(String.format("indexerAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void rechargerAnneeScolaire(AnneeScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Boolean recharger = !"false".equals(requeteSite.getRequeteVars().get("recharger"));
			if(recharger && BooleanUtils.isFalse(Optional.ofNullable(requeteSite.getRequeteApi_()).map(RequeteApi::getEmpty).orElse(true))) {
				ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(AnneeScolaire.class);
				listeRecherche.addFilterQuery("modifie_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(requeteSite.getRequeteApi_().getCree().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				listeRecherche.add("json.facet", "{ecoleCle:{terms:{field:ecoleCle_indexed_longs, limit:1000}}}");
				listeRecherche.add("json.facet", "{saisonCles:{terms:{field:saisonCles_indexed_longs, limit:1000}}}");
				listeRecherche.add("json.facet", "{ageCles:{terms:{field:ageCles_indexed_longs, limit:1000}}}");
				listeRecherche.setRows(1000);
				listeRecherche.initLoinListeRecherche(requeteSite);
				List<Future> futures = new ArrayList<>();

				for(int i=0; i < pks.size(); i++) {
					Long pk2 = pks.get(i);
					String classeNomSimple2 = classes.get(i);

					if("Ecole".equals(classeNomSimple2) && pk2 != null) {
						ListeRecherche<Ecole> listeRecherche2 = new ListeRecherche<Ecole>();
						listeRecherche2.setStocker(true);
						listeRecherche2.setQuery("*:*");
						listeRecherche2.setC(Ecole.class);
						listeRecherche2.addFilterQuery("pk_indexed_long:" + pk2);
						listeRecherche2.setRows(1);
						listeRecherche2.initLoinListeRecherche(requeteSite);
						Ecole o2 = listeRecherche2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							EcoleFrFRGenApiServiceImpl service = new EcoleFrFRGenApiServiceImpl(requeteSite.getSiteContexte_());
							RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
							RequeteApi requeteApi2 = new RequeteApi();
							requeteApi2.setRows(1);
							requeteApi2.setNumFound(1l);
							requeteApi2.setNumPATCH(0L);
							requeteApi2.initLoinRequeteApi(requeteSite2);
							requeteSite2.setRequeteApi_(requeteApi2);
							requeteSite2.getVertx().eventBus().publish("websocketEcole", JsonObject.mapFrom(requeteApi2).toString());

							o2.setPk(pk2);
							o2.setRequeteSite_(requeteSite2);
							futures.add(
								service.patchEcoleFuture(o2, false, a -> {
									if(a.succeeded()) {
									} else {
										LOGGER.info(String.format("Ecole %s a échoué. ", pk2));
										gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}

					if("SaisonScolaire".equals(classeNomSimple2) && pk2 != null) {
						ListeRecherche<SaisonScolaire> listeRecherche2 = new ListeRecherche<SaisonScolaire>();
						listeRecherche2.setStocker(true);
						listeRecherche2.setQuery("*:*");
						listeRecherche2.setC(SaisonScolaire.class);
						listeRecherche2.addFilterQuery("pk_indexed_long:" + pk2);
						listeRecherche2.setRows(1);
						listeRecherche2.initLoinListeRecherche(requeteSite);
						SaisonScolaire o2 = listeRecherche2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							SaisonScolaireFrFRGenApiServiceImpl service = new SaisonScolaireFrFRGenApiServiceImpl(requeteSite.getSiteContexte_());
							RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
							RequeteApi requeteApi2 = new RequeteApi();
							requeteApi2.setRows(1);
							requeteApi2.setNumFound(1l);
							requeteApi2.setNumPATCH(0L);
							requeteApi2.initLoinRequeteApi(requeteSite2);
							requeteSite2.setRequeteApi_(requeteApi2);
							requeteSite2.getVertx().eventBus().publish("websocketSaisonScolaire", JsonObject.mapFrom(requeteApi2).toString());

							o2.setPk(pk2);
							o2.setRequeteSite_(requeteSite2);
							futures.add(
								service.patchSaisonScolaireFuture(o2, false, a -> {
									if(a.succeeded()) {
									} else {
										LOGGER.info(String.format("SaisonScolaire %s a échoué. ", pk2));
										gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}

					if("AgeScolaire".equals(classeNomSimple2) && pk2 != null) {
						ListeRecherche<AgeScolaire> listeRecherche2 = new ListeRecherche<AgeScolaire>();
						listeRecherche2.setStocker(true);
						listeRecherche2.setQuery("*:*");
						listeRecherche2.setC(AgeScolaire.class);
						listeRecherche2.addFilterQuery("pk_indexed_long:" + pk2);
						listeRecherche2.setRows(1);
						listeRecherche2.initLoinListeRecherche(requeteSite);
						AgeScolaire o2 = listeRecherche2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							AgeScolaireFrFRGenApiServiceImpl service = new AgeScolaireFrFRGenApiServiceImpl(requeteSite.getSiteContexte_());
							RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
							RequeteApi requeteApi2 = new RequeteApi();
							requeteApi2.setRows(1);
							requeteApi2.setNumFound(1l);
							requeteApi2.setNumPATCH(0L);
							requeteApi2.initLoinRequeteApi(requeteSite2);
							requeteSite2.setRequeteApi_(requeteApi2);
							requeteSite2.getVertx().eventBus().publish("websocketAgeScolaire", JsonObject.mapFrom(requeteApi2).toString());

							o2.setPk(pk2);
							o2.setRequeteSite_(requeteSite2);
							futures.add(
								service.patchAgeScolaireFuture(o2, false, a -> {
									if(a.succeeded()) {
									} else {
										LOGGER.info(String.format("AgeScolaire %s a échoué. ", pk2));
										gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						AnneeScolaireFrFRApiServiceImpl service = new AnneeScolaireFrFRApiServiceImpl(requeteSite.getSiteContexte_());
						List<Future> futures2 = new ArrayList<>();
						for(AnneeScolaire o2 : listeRecherche.getList()) {
							RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourAnneeScolaire(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
							o2.setRequeteSite_(requeteSite2);
							futures2.add(
								service.patchAnneeScolaireFuture(o2, false, b -> {
									if(b.succeeded()) {
									} else {
										LOGGER.info(String.format("AnneeScolaire %s a échoué. ", o2.getPk()));
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
								erreurAnneeScolaire(requeteSite, gestionnaireEvenements, b);
							}
						});
					} else {
						LOGGER.error("Recharger relations a échoué. ", a.cause());
						erreurAnneeScolaire(requeteSite, gestionnaireEvenements, a);
					}
				});
			} else {
				gestionnaireEvenements.handle(Future.succeededFuture());
			}
		} catch(Exception e) {
			LOGGER.error(String.format("rechargerAnneeScolaire a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}
}
