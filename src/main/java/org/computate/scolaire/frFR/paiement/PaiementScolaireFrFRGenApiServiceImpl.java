package org.computate.scolaire.frFR.paiement;

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
import org.computate.scolaire.frFR.utilisateur.UtilisateurSiteFrFRApiServiceImpl;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;


/**
 * Traduire: false
 * NomCanonique.enUS: org.computate.scolaire.enUS.payment.SchoolPaymentEnUSGenApiServiceImpl
 **/
public class PaiementScolaireFrFRGenApiServiceImpl implements PaiementScolaireFrFRGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(PaiementScolaireFrFRGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "PaiementScolaireFrFRApiServiceImpl";

	protected SiteContexteFrFR siteContexte;

	public PaiementScolaireFrFRGenApiServiceImpl(SiteContexteFrFR siteContexte) {
		this.siteContexte = siteContexte;
	}

	// POST //

	@Override
	public void postPaiementScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("postPaiementScolaire a démarré. "));
			sqlPaiementScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurPaiementScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							RequeteApi requeteApi = new RequeteApi();
							requeteApi.setRows(1);
							requeteApi.setNumFound(1L);
							requeteApi.setNumPATCH(0L);
							requeteApi.initLoinRequeteApi(requeteSite);
							requeteSite.setRequeteApi_(requeteApi);
							requeteSite.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
							postPaiementScolaireFuture(requeteSite, false, c -> {
								if(c.succeeded()) {
									PaiementScolaire paiementScolaire = c.result();
									requeteApi.setPk(paiementScolaire.getPk());
									requeteApiPaiementScolaire(paiementScolaire);
									postPaiementScolaireReponse(paiementScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postPaiementScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("postPaiementScolaire a échoué. ", d.cause()));
											erreurPaiementScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("postPaiementScolaire a échoué. ", c.cause()));
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("postPaiementScolaire a échoué. ", b.cause()));
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("postPaiementScolaire a échoué. ", a.cause()));
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postPaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public Future<PaiementScolaire> postPaiementScolaireFuture(RequeteSiteFrFR requeteSite, Boolean inheritPk, Handler<AsyncResult<PaiementScolaire>> gestionnaireEvenements) {
		Promise<PaiementScolaire> promise = Promise.promise();
		try {
			creerPaiementScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					PaiementScolaire paiementScolaire = a.result();
					sqlPOSTPaiementScolaire(paiementScolaire, inheritPk, b -> {
						if(b.succeeded()) {
							definirIndexerPaiementScolaire(paiementScolaire, c -> {
								if(c.succeeded()) {
									RequeteApi requeteApi = requeteSite.getRequeteApi_();
									if(requeteApi != null) {
										requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
										paiementScolaire.requeteApiPaiementScolaire();
										requeteSite.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
									}
									gestionnaireEvenements.handle(Future.succeededFuture(paiementScolaire));
									promise.complete(paiementScolaire);
								} else {
									erreurPaiementScolaire(requeteSite, null, c);
								}
							});
						} else {
							erreurPaiementScolaire(requeteSite, null, b);
						}
					});
				} else {
					erreurPaiementScolaire(requeteSite, null, a);
				}
			});
		} catch(Exception e) {
			erreurPaiementScolaire(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTPaiementScolaire(PaiementScolaire o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject jsonObject = requeteSite.getObjetJson();
			StringBuilder postSql = new StringBuilder();
			List<Object> postSqlParams = new ArrayList<Object>();

			if(requeteSite.getSessionId() != null) {
				postSql.append(SiteContexteFrFR.SQL_setD);
				postSqlParams.addAll(Arrays.asList("sessionId", requeteSite.getSessionId(), pk));
			}
			if(requeteSite.getUtilisateurId() != null) {
				postSql.append(SiteContexteFrFR.SQL_setD);
				postSqlParams.addAll(Arrays.asList("utilisateurId", requeteSite.getUtilisateurId(), pk));
			}
			if(requeteSite.getUtilisateurCle() != null) {
				postSql.append(SiteContexteFrFR.SQL_setD);
				postSqlParams.addAll(Arrays.asList("utilisateurCle", requeteSite.getUtilisateurCle(), pk));

				JsonArray utilisateurCles = Optional.ofNullable(jsonObject.getJsonArray("utilisateurCles")).orElse(null);
				if(utilisateurCles != null && !utilisateurCles.contains(requeteSite.getUtilisateurCle()))
					utilisateurCles.add(requeteSite.getUtilisateurCle().toString());
				else
					jsonObject.put("utilisateurCles", new JsonArray().add(requeteSite.getUtilisateurCle().toString()));
			}

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
					case "sessionId":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("sessionId", jsonObject.getString(entiteVar), pk));
						break;
					case "utilisateurId":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("utilisateurId", jsonObject.getString(entiteVar), pk));
						break;
					case "utilisateurCle":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("utilisateurCle", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionCle":
						{
							Long l = Long.parseLong(jsonObject.getString(entiteVar));
							if(l != null) {
								ListeRecherche<InscriptionScolaire> listeRecherche = new ListeRecherche<InscriptionScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(InscriptionScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContexteFrFR.SQL_addA);
									postSqlParams.addAll(Arrays.asList("inscriptionCle", pk, "paiementCles", l));
								}
							}
						}
						break;
					case "enfantNomCompletPrefere":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enfantNomCompletPrefere", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantDateNaissance":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enfantDateNaissance", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "mereNomCompletPrefere":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("mereNomCompletPrefere", jsonObject.getString(entiteVar), pk));
						break;
					case "pereNomCompletPrefere":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("pereNomCompletPrefere", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionPaimentChaqueMois":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionPaimentChaqueMois", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "inscriptionPaimentComplet":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionPaimentComplet", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "paiementDescription":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("paiementDescription", jsonObject.getString(entiteVar), pk));
						break;
					case "paiementDate":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("paiementDate", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "paiementMontant":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("paiementMontant", jsonObject.getString(entiteVar), pk));
						break;
					case "fraisMontant":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("fraisMontant", jsonObject.getString(entiteVar), pk));
						break;
					case "fraisMontantFuture":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("fraisMontantFuture", jsonObject.getString(entiteVar), pk));
						break;
					case "fraisInscription":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("fraisInscription", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "fraisPremierDernier":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("fraisPremierDernier", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "fraisMois":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("fraisMois", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "fraisRetard":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("fraisRetard", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "paiementEspeces":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("paiementEspeces", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "paiementCheque":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("paiementCheque", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "paiementSysteme":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("paiementSysteme", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "paiementPar":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("paiementPar", jsonObject.getString(entiteVar), pk));
						break;
					case "transactionId":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("transactionId", jsonObject.getString(entiteVar), pk));
						break;
					case "customerProfileId":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("customerProfileId", jsonObject.getString(entiteVar), pk));
						break;
					case "transactionStatus":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("transactionStatus", jsonObject.getString(entiteVar), pk));
						break;
					case "paiementRecu":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("paiementRecu", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "paiementNomCourt":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("paiementNomCourt", jsonObject.getString(entiteVar), pk));
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

	public void postPaiementScolaireReponse(PaiementScolaire paiementScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = paiementScolaire.getRequeteSite_();
		try {
			reponse200POSTPaiementScolaire(paiementScolaire, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							LOGGER.info(String.format("postPaiementScolaire sql commit. "));
							connexionSql.close(c -> {
								if(c.succeeded()) {
									LOGGER.info(String.format("postPaiementScolaire sql close. "));
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postPaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200POSTPaiementScolaire(PaiementScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
	public void putimportPaiementScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putimportPaiementScolaire a démarré. "));
			sqlPaiementScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurPaiementScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							putimportPaiementScolaireReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, operationRequete, body);
											try {
												RequeteApi requeteApi = new RequeteApi();
												JsonArray jsonArray = Optional.ofNullable(requeteSite2.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												requeteApi.setRows(jsonArray.size());
												requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite2);
												requeteSite2.setRequeteApi_(requeteApi);
												requeteSite2.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
												sqlPaiementScolaire(requeteSite2, d -> {
													if(d.succeeded()) {
														listePUTImportPaiementScolaire(requeteApi, requeteSite2, e -> {
															if(e.succeeded()) {
																putimportPaiementScolaireReponse(requeteSite2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putimportPaiementScolaire a réussi. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putimportPaiementScolaire a échoué. ", f.cause()));
																		erreurPaiementScolaire(requeteSite2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putimportPaiementScolaire a échoué. ", e.cause()));
																erreurPaiementScolaire(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putimportPaiementScolaire a échoué. ", d.cause()));
														erreurPaiementScolaire(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putimportPaiementScolaire a échoué. ", ex));
												erreurPaiementScolaire(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putimportPaiementScolaire a échoué. ", c.cause()));
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("putimportPaiementScolaire a échoué. ", b.cause()));
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("putimportPaiementScolaire a échoué. ", a.cause()));
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportPaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePUTImportPaiementScolaire(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, requeteSite.getOperationRequete(), json);
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());

				ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(PaiementScolaire.class);
				listeRecherche.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				listeRecherche.initLoinPourClasse(requeteSite2);

				if(listeRecherche.size() == 1) {
					PaiementScolaire o = listeRecherche.getList().stream().findFirst().orElse(null);
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
							patchPaiementScolaireFuture(o, true, a -> {
								if(a.succeeded()) {
									PaiementScolaire paiementScolaire = a.result();
									requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
									requeteSite2.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
								} else {
									erreurPaiementScolaire(requeteSite2, gestionnaireEvenements, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postPaiementScolaireFuture(requeteSite2, true, a -> {
							if(a.succeeded()) {
								PaiementScolaire paiementScolaire = a.result();
								requeteApiPaiementScolaire(paiementScolaire);
							} else {
								erreurPaiementScolaire(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
								requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
								requeteSite.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
					reponse200PUTImportPaiementScolaire(requeteSite, gestionnaireEvenements);
				} else {
					erreurPaiementScolaire(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportPaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}

	public void putimportPaiementScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PUTImportPaiementScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							LOGGER.info(String.format("putimportPaiementScolaire sql commit. "));
							connexionSql.close(c -> {
								if(c.succeeded()) {
									LOGGER.info(String.format("putimportPaiementScolaire sql close. "));
									RequeteApi requeteApi = requeteSite.getRequeteApi_();
									if(requeteApi != null)
										requeteSite.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportPaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PUTImportPaiementScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTFusion //

	@Override
	public void putfusionPaiementScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putfusionPaiementScolaire a démarré. "));
			sqlPaiementScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurPaiementScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							putfusionPaiementScolaireReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, operationRequete, body);
											try {
												RequeteApi requeteApi = new RequeteApi();
												JsonArray jsonArray = Optional.ofNullable(requeteSite2.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												requeteApi.setRows(jsonArray.size());
												requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite2);
												requeteSite2.setRequeteApi_(requeteApi);
												requeteSite2.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
												sqlPaiementScolaire(requeteSite2, d -> {
													if(d.succeeded()) {
														listePUTFusionPaiementScolaire(requeteApi, requeteSite2, e -> {
															if(e.succeeded()) {
																putfusionPaiementScolaireReponse(requeteSite2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putfusionPaiementScolaire a réussi. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putfusionPaiementScolaire a échoué. ", f.cause()));
																		erreurPaiementScolaire(requeteSite2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putfusionPaiementScolaire a échoué. ", e.cause()));
																erreurPaiementScolaire(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putfusionPaiementScolaire a échoué. ", d.cause()));
														erreurPaiementScolaire(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putfusionPaiementScolaire a échoué. ", ex));
												erreurPaiementScolaire(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putfusionPaiementScolaire a échoué. ", c.cause()));
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("putfusionPaiementScolaire a échoué. ", b.cause()));
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("putfusionPaiementScolaire a échoué. ", a.cause()));
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putfusionPaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePUTFusionPaiementScolaire(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, requeteSite.getOperationRequete(), json);
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());

				ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(PaiementScolaire.class);
				listeRecherche.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				listeRecherche.initLoinPourClasse(requeteSite2);

				if(listeRecherche.size() == 1) {
					PaiementScolaire o = listeRecherche.getList().stream().findFirst().orElse(null);
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
							patchPaiementScolaireFuture(o, false, a -> {
								if(a.succeeded()) {
									PaiementScolaire paiementScolaire = a.result();
									requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
									requeteSite2.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
								} else {
									erreurPaiementScolaire(requeteSite2, gestionnaireEvenements, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postPaiementScolaireFuture(requeteSite2, false, a -> {
							if(a.succeeded()) {
								PaiementScolaire paiementScolaire = a.result();
								requeteApiPaiementScolaire(paiementScolaire);
							} else {
								erreurPaiementScolaire(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
								requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
								requeteSite.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
					reponse200PUTFusionPaiementScolaire(requeteSite, gestionnaireEvenements);
				} else {
					erreurPaiementScolaire(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putfusionPaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}

	public void putfusionPaiementScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PUTFusionPaiementScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							LOGGER.info(String.format("putfusionPaiementScolaire sql commit. "));
							connexionSql.close(c -> {
								if(c.succeeded()) {
									LOGGER.info(String.format("putfusionPaiementScolaire sql close. "));
									RequeteApi requeteApi = requeteSite.getRequeteApi_();
									if(requeteApi != null)
										requeteSite.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putfusionPaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PUTFusionPaiementScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTCopie //

	@Override
	public void putcopiePaiementScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putcopiePaiementScolaire a démarré. "));
			sqlPaiementScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurPaiementScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							putcopiePaiementScolaireReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, operationRequete, body);
											try {
												recherchePaiementScolaire(requeteSite2, false, true, "/api/paiement/copie", "PUTCopie", d -> {
													if(d.succeeded()) {
														ListeRecherche<PaiementScolaire> listePaiementScolaire = d.result();
														RequeteApi requeteApi = new RequeteApi();
														requeteApi.setRows(listePaiementScolaire.getRows());
														requeteApi.setNumFound(listePaiementScolaire.getQueryResponse().getResults().getNumFound());
														requeteApi.setNumPATCH(0L);
														requeteApi.initLoinRequeteApi(requeteSite2);
														requeteSite2.setRequeteApi_(requeteApi);
														requeteSite2.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
														sqlPaiementScolaire(requeteSite2, e -> {
															if(e.succeeded()) {
																try {
																	listePUTCopiePaiementScolaire(requeteApi, listePaiementScolaire, f -> {
																		if(f.succeeded()) {
																			putcopiePaiementScolaireReponse(requeteSite2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("putcopiePaiementScolaire a réussi. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("putcopiePaiementScolaire a échoué. ", g.cause()));
																					erreurPaiementScolaire(requeteSite2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("putcopiePaiementScolaire a échoué. ", f.cause()));
																			erreurPaiementScolaire(requeteSite2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("putcopiePaiementScolaire a échoué. ", ex));
																	erreurPaiementScolaire(requeteSite2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("putcopiePaiementScolaire a échoué. ", e.cause()));
																erreurPaiementScolaire(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putcopiePaiementScolaire a échoué. ", d.cause()));
														erreurPaiementScolaire(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putcopiePaiementScolaire a échoué. ", ex));
												erreurPaiementScolaire(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putcopiePaiementScolaire a échoué. ", c.cause()));
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("putcopiePaiementScolaire a échoué. ", b.cause()));
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("putcopiePaiementScolaire a échoué. ", a.cause()));
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopiePaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePUTCopiePaiementScolaire(RequeteApi requeteApi, ListeRecherche<PaiementScolaire> listePaiementScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listePaiementScolaire.getRequeteSite_();
		listePaiementScolaire.getList().forEach(o -> {
			futures.add(
				putcopiePaiementScolaireFuture(requeteSite, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						PaiementScolaire paiementScolaire = a.result();
						requeteApiPaiementScolaire(paiementScolaire);
					} else {
						erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listePaiementScolaire.size());
				requeteSite.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
				if(listePaiementScolaire.next()) {
					listePUTCopiePaiementScolaire(requeteApi, listePaiementScolaire, gestionnaireEvenements);
				} else {
					reponse200PUTCopiePaiementScolaire(requeteSite, gestionnaireEvenements);
				}
			} else {
				erreurPaiementScolaire(listePaiementScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<PaiementScolaire> putcopiePaiementScolaireFuture(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Handler<AsyncResult<PaiementScolaire>> gestionnaireEvenements) {
		Promise<PaiementScolaire> promise = Promise.promise();
		try {

			jsonObject.put("sauvegardes", Optional.ofNullable(jsonObject.getJsonArray("sauvegardes")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("sauvegardes").add(o.getKey());
			});

			creerPaiementScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					PaiementScolaire paiementScolaire = a.result();
					sqlPUTCopiePaiementScolaire(paiementScolaire, jsonObject, b -> {
						if(b.succeeded()) {
							definirPaiementScolaire(paiementScolaire, c -> {
								if(c.succeeded()) {
									attribuerPaiementScolaire(paiementScolaire, d -> {
										if(d.succeeded()) {
											indexerPaiementScolaire(paiementScolaire, e -> {
												if(e.succeeded()) {
													gestionnaireEvenements.handle(Future.succeededFuture(paiementScolaire));
													promise.complete(paiementScolaire);
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
			erreurPaiementScolaire(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopiePaiementScolaire(PaiementScolaire o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
					case "sessionId":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("sessionId", jsonObject.getString(entiteVar), pk));
						break;
					case "utilisateurId":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("utilisateurId", jsonObject.getString(entiteVar), pk));
						break;
					case "utilisateurCle":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("utilisateurCle", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionCle":
						putSql.append(SiteContexteFrFR.SQL_addA);
						putSqlParams.addAll(Arrays.asList("inscriptionCle", pk, "paiementCles", Long.parseLong(jsonObject.getString(entiteVar))));
						break;
					case "enfantNomCompletPrefere":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enfantNomCompletPrefere", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantDateNaissance":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enfantDateNaissance", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "mereNomCompletPrefere":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("mereNomCompletPrefere", jsonObject.getString(entiteVar), pk));
						break;
					case "pereNomCompletPrefere":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("pereNomCompletPrefere", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionPaimentChaqueMois":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionPaimentChaqueMois", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "inscriptionPaimentComplet":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionPaimentComplet", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "paiementDescription":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("paiementDescription", jsonObject.getString(entiteVar), pk));
						break;
					case "paiementDate":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("paiementDate", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "paiementMontant":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("paiementMontant", jsonObject.getString(entiteVar), pk));
						break;
					case "fraisMontant":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("fraisMontant", jsonObject.getString(entiteVar), pk));
						break;
					case "fraisMontantFuture":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("fraisMontantFuture", jsonObject.getString(entiteVar), pk));
						break;
					case "fraisInscription":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("fraisInscription", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "fraisPremierDernier":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("fraisPremierDernier", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "fraisMois":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("fraisMois", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "fraisRetard":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("fraisRetard", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "paiementEspeces":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("paiementEspeces", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "paiementCheque":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("paiementCheque", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "paiementSysteme":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("paiementSysteme", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "paiementPar":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("paiementPar", jsonObject.getString(entiteVar), pk));
						break;
					case "transactionId":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("transactionId", jsonObject.getString(entiteVar), pk));
						break;
					case "customerProfileId":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("customerProfileId", jsonObject.getString(entiteVar), pk));
						break;
					case "transactionStatus":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("transactionStatus", jsonObject.getString(entiteVar), pk));
						break;
					case "paiementRecu":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("paiementRecu", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "paiementNomCourt":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("paiementNomCourt", jsonObject.getString(entiteVar), pk));
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

	public void putcopiePaiementScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PUTCopiePaiementScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							LOGGER.info(String.format("putcopiePaiementScolaire sql commit. "));
							connexionSql.close(c -> {
								if(c.succeeded()) {
									LOGGER.info(String.format("putcopiePaiementScolaire sql close. "));
									RequeteApi requeteApi = requeteSite.getRequeteApi_();
									if(requeteApi != null)
										requeteSite.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopiePaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PUTCopiePaiementScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchPaiementScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("patchPaiementScolaire a démarré. "));
			sqlPaiementScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurPaiementScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							patchPaiementScolaireReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, operationRequete, body);
											try {
												recherchePaiementScolaire(requeteSite2, false, true, "/api/paiement", "PATCH", d -> {
													if(d.succeeded()) {
														ListeRecherche<PaiementScolaire> listePaiementScolaire = d.result();
														RequeteApi requeteApi = new RequeteApi();
														requeteApi.setRows(listePaiementScolaire.getRows());
														requeteApi.setNumFound(listePaiementScolaire.getQueryResponse().getResults().getNumFound());
														requeteApi.setNumPATCH(0L);
														requeteApi.initLoinRequeteApi(requeteSite2);
														requeteSite2.setRequeteApi_(requeteApi);
														requeteSite2.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
														SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listePaiementScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
														Date date = null;
														if(facets != null)
															date = (Date)facets.get("max_modifie");
														String dt;
														if(date == null)
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
														else
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
														listePaiementScolaire.addFilterQuery(String.format("modifie_indexed_date:[* TO %s]", dt));

														PaiementScolaire o = listePaiementScolaire.getList().stream().findFirst().orElse(null);
														sqlPaiementScolaire(requeteSite2, e -> {
															if(e.succeeded()) {
																try {
																	listePATCHPaiementScolaire(requeteApi, listePaiementScolaire, dt, f -> {
																		if(f.succeeded()) {
																			patchPaiementScolaireReponse(requeteSite2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("patchPaiementScolaire a réussi. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("patchPaiementScolaire a échoué. ", g.cause()));
																					erreurPaiementScolaire(requeteSite2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("patchPaiementScolaire a échoué. ", f.cause()));
																			erreurPaiementScolaire(requeteSite2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("patchPaiementScolaire a échoué. ", ex));
																	erreurPaiementScolaire(requeteSite2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("patchPaiementScolaire a échoué. ", e.cause()));
																erreurPaiementScolaire(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("patchPaiementScolaire a échoué. ", d.cause()));
														erreurPaiementScolaire(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("patchPaiementScolaire a échoué. ", ex));
												erreurPaiementScolaire(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("patchPaiementScolaire a échoué. ", c.cause()));
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("patchPaiementScolaire a échoué. ", b.cause()));
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("patchPaiementScolaire a échoué. ", a.cause()));
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchPaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePATCHPaiementScolaire(RequeteApi requeteApi, ListeRecherche<PaiementScolaire> listePaiementScolaire, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listePaiementScolaire.getRequeteSite_();
		listePaiementScolaire.getList().forEach(o -> {
			futures.add(
				patchPaiementScolaireFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listePaiementScolaire.next(dt)) {
					listePATCHPaiementScolaire(requeteApi, listePaiementScolaire, dt, gestionnaireEvenements);
				} else {
					reponse200PATCHPaiementScolaire(requeteSite, gestionnaireEvenements);
				}
			} else {
				erreurPaiementScolaire(listePaiementScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<PaiementScolaire> patchPaiementScolaireFuture(PaiementScolaire o, Boolean inheritPk, Handler<AsyncResult<PaiementScolaire>> gestionnaireEvenements) {
		Promise<PaiementScolaire> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			if(requeteApi != null && requeteApi.getNumFound() == 1L) {
				requeteApi.setOriginal(o);
				requeteApi.setPk(o.getPk());
			}
			sqlPATCHPaiementScolaire(o, inheritPk, a -> {
				if(a.succeeded()) {
					PaiementScolaire paiementScolaire = a.result();
					definirPaiementScolaire(paiementScolaire, b -> {
						if(b.succeeded()) {
							attribuerPaiementScolaire(paiementScolaire, c -> {
								if(c.succeeded()) {
									indexerPaiementScolaire(paiementScolaire, d -> {
										if(d.succeeded()) {
											if(requeteApi != null) {
												requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
												requeteApiPaiementScolaire(paiementScolaire);
												if(requeteApi.getNumFound() == 1L) {
													paiementScolaire.requeteApiPaiementScolaire();
												}
												requeteSite.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
											}
											gestionnaireEvenements.handle(Future.succeededFuture(paiementScolaire));
											promise.complete(paiementScolaire);
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
			erreurPaiementScolaire(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHPaiementScolaire(PaiementScolaire o, Boolean inheritPk, Handler<AsyncResult<PaiementScolaire>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject jsonObject = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = jsonObject.fieldNames();
			PaiementScolaire o2 = new PaiementScolaire();

			if(o.getUtilisateurId() == null && requeteSite.getUtilisateurId() != null) {
				patchSql.append(SiteContexteFrFR.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("utilisateurId", requeteSite.getUtilisateurId(), pk));
			}
			if(o.getUtilisateurCle() == null && requeteSite.getUtilisateurCle() != null) {
				patchSql.append(SiteContexteFrFR.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("utilisateurCle", requeteSite.getUtilisateurCle(), pk));

				JsonArray utilisateurCles = Optional.ofNullable(jsonObject.getJsonArray("addUtilisateurCles")).orElse(null);
				if(utilisateurCles != null && !utilisateurCles.contains(requeteSite.getUtilisateurCle()))
					utilisateurCles.add(requeteSite.getUtilisateurCle().toString());
				else
					jsonObject.put("addUtilisateurCles", new JsonArray().add(requeteSite.getUtilisateurCle().toString()));
			}

			patchSql.append(SiteContexteFrFR.SQL_modifier);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.frFR.paiement.PaiementScolaire"));
			for(String methodeNom : methodeNoms) {
				switch(methodeNom) {
					case "setInheritPk":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inheritPk"));
						} else {
							o2.setInheritPk(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inheritPk", o2.jsonInheritPk(), pk));
						}
						break;
					case "setCree":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "cree"));
						} else {
							o2.setCree(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("cree", o2.jsonCree(), pk));
						}
						break;
					case "setModifie":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "modifie"));
						} else {
							o2.setModifie(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("modifie", o2.jsonModifie(), pk));
						}
						break;
					case "setArchive":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "archive"));
						} else {
							o2.setArchive(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("archive", o2.jsonArchive(), pk));
						}
						break;
					case "setSupprime":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "supprime"));
						} else {
							o2.setSupprime(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("supprime", o2.jsonSupprime(), pk));
						}
						break;
					case "setSessionId":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sessionId"));
						} else {
							o2.setSessionId(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sessionId", o2.jsonSessionId(), pk));
						}
						break;
					case "setUtilisateurId":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "utilisateurId"));
						} else {
							o2.setUtilisateurId(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("utilisateurId", o2.jsonUtilisateurId(), pk));
						}
						break;
					case "setUtilisateurCle":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "utilisateurCle"));
						} else {
							o2.setUtilisateurCle(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("utilisateurCle", o2.jsonUtilisateurCle(), pk));
						}
						break;
					case "setInscriptionCle":
						{
							Long l = o2.getInscriptionCle();
							if(l != null) {
								ListeRecherche<InscriptionScolaire> listeRecherche = new ListeRecherche<InscriptionScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(InscriptionScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setInscriptionCle(jsonObject.getString(methodeNom));
									patchSql.append(SiteContexteFrFR.SQL_setA1);
									patchSqlParams.addAll(Arrays.asList("inscriptionCle", pk, "paiementCles", l));
								}
							}
						}
						break;
					case "removeInscriptionCle":
						{
							Long l = o2.getInscriptionCle();
							if(l != null) {
								ListeRecherche<InscriptionScolaire> listeRecherche = new ListeRecherche<InscriptionScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(InscriptionScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setInscriptionCle(jsonObject.getString(methodeNom));
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCle", pk, "paiementCles", l));
								}
							}
						}
						break;
					case "setEnfantNomCompletPrefere":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enfantNomCompletPrefere"));
						} else {
							o2.setEnfantNomCompletPrefere(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enfantNomCompletPrefere", o2.jsonEnfantNomCompletPrefere(), pk));
						}
						break;
					case "setEnfantDateNaissance":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enfantDateNaissance"));
						} else {
							o2.setEnfantDateNaissance(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enfantDateNaissance", o2.jsonEnfantDateNaissance(), pk));
						}
						break;
					case "setMereNomCompletPrefere":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "mereNomCompletPrefere"));
						} else {
							o2.setMereNomCompletPrefere(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("mereNomCompletPrefere", o2.jsonMereNomCompletPrefere(), pk));
						}
						break;
					case "setPereNomCompletPrefere":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "pereNomCompletPrefere"));
						} else {
							o2.setPereNomCompletPrefere(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("pereNomCompletPrefere", o2.jsonPereNomCompletPrefere(), pk));
						}
						break;
					case "setInscriptionPaimentChaqueMois":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionPaimentChaqueMois"));
						} else {
							o2.setInscriptionPaimentChaqueMois(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionPaimentChaqueMois", o2.jsonInscriptionPaimentChaqueMois(), pk));
						}
						break;
					case "setInscriptionPaimentComplet":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionPaimentComplet"));
						} else {
							o2.setInscriptionPaimentComplet(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionPaimentComplet", o2.jsonInscriptionPaimentComplet(), pk));
						}
						break;
					case "setPaiementDescription":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "paiementDescription"));
						} else {
							o2.setPaiementDescription(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("paiementDescription", o2.jsonPaiementDescription(), pk));
						}
						break;
					case "setPaiementDate":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "paiementDate"));
						} else {
							o2.setPaiementDate(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("paiementDate", o2.jsonPaiementDate(), pk));
						}
						break;
					case "setPaiementMontant":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "paiementMontant"));
						} else {
							o2.setPaiementMontant(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("paiementMontant", o2.jsonPaiementMontant(), pk));
						}
						break;
					case "setFraisMontant":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "fraisMontant"));
						} else {
							o2.setFraisMontant(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("fraisMontant", o2.jsonFraisMontant(), pk));
						}
						break;
					case "setFraisMontantFuture":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "fraisMontantFuture"));
						} else {
							o2.setFraisMontantFuture(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("fraisMontantFuture", o2.jsonFraisMontantFuture(), pk));
						}
						break;
					case "setFraisInscription":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "fraisInscription"));
						} else {
							o2.setFraisInscription(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("fraisInscription", o2.jsonFraisInscription(), pk));
						}
						break;
					case "setFraisPremierDernier":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "fraisPremierDernier"));
						} else {
							o2.setFraisPremierDernier(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("fraisPremierDernier", o2.jsonFraisPremierDernier(), pk));
						}
						break;
					case "setFraisMois":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "fraisMois"));
						} else {
							o2.setFraisMois(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("fraisMois", o2.jsonFraisMois(), pk));
						}
						break;
					case "setFraisRetard":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "fraisRetard"));
						} else {
							o2.setFraisRetard(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("fraisRetard", o2.jsonFraisRetard(), pk));
						}
						break;
					case "setPaiementEspeces":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "paiementEspeces"));
						} else {
							o2.setPaiementEspeces(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("paiementEspeces", o2.jsonPaiementEspeces(), pk));
						}
						break;
					case "setPaiementCheque":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "paiementCheque"));
						} else {
							o2.setPaiementCheque(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("paiementCheque", o2.jsonPaiementCheque(), pk));
						}
						break;
					case "setPaiementSysteme":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "paiementSysteme"));
						} else {
							o2.setPaiementSysteme(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("paiementSysteme", o2.jsonPaiementSysteme(), pk));
						}
						break;
					case "setPaiementPar":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "paiementPar"));
						} else {
							o2.setPaiementPar(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("paiementPar", o2.jsonPaiementPar(), pk));
						}
						break;
					case "setTransactionId":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "transactionId"));
						} else {
							o2.setTransactionId(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("transactionId", o2.jsonTransactionId(), pk));
						}
						break;
					case "setCustomerProfileId":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "customerProfileId"));
						} else {
							o2.setCustomerProfileId(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("customerProfileId", o2.jsonCustomerProfileId(), pk));
						}
						break;
					case "setTransactionStatus":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "transactionStatus"));
						} else {
							o2.setTransactionStatus(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("transactionStatus", o2.jsonTransactionStatus(), pk));
						}
						break;
					case "setPaiementRecu":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "paiementRecu"));
						} else {
							o2.setPaiementRecu(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("paiementRecu", o2.jsonPaiementRecu(), pk));
						}
						break;
					case "setPaiementNomCourt":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "paiementNomCourt"));
						} else {
							o2.setPaiementNomCourt(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("paiementNomCourt", o2.jsonPaiementNomCourt(), pk));
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
					PaiementScolaire o3 = new PaiementScolaire();
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

	public void patchPaiementScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PATCHPaiementScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							LOGGER.info(String.format("patchPaiementScolaire sql commit. "));
							connexionSql.close(c -> {
								if(c.succeeded()) {
									LOGGER.info(String.format("patchPaiementScolaire sql close. "));
									RequeteApi requeteApi = requeteSite.getRequeteApi_();
									if(requeteApi != null)
										requeteSite.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi).toString());
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchPaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PATCHPaiementScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getPaiementScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, operationRequete);
		try {
			sqlPaiementScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurPaiementScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							recherchePaiementScolaire(requeteSite, false, true, "/api/paiement/{id}", "GET", c -> {
								if(c.succeeded()) {
									ListeRecherche<PaiementScolaire> listePaiementScolaire = c.result();
									getPaiementScolaireReponse(listePaiementScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getPaiementScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("getPaiementScolaire a échoué. ", d.cause()));
											erreurPaiementScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("getPaiementScolaire a échoué. ", c.cause()));
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("getPaiementScolaire a échoué. ", b.cause()));
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("getPaiementScolaire a échoué. ", a.cause()));
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getPaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void getPaiementScolaireReponse(ListeRecherche<PaiementScolaire> listePaiementScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listePaiementScolaire.getRequeteSite_();
		try {
			reponse200GETPaiementScolaire(listePaiementScolaire, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							LOGGER.info(String.format("getPaiementScolaire sql commit. "));
							connexionSql.close(c -> {
								if(c.succeeded()) {
									LOGGER.info(String.format("getPaiementScolaire sql close. "));
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getPaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200GETPaiementScolaire(ListeRecherche<PaiementScolaire> listePaiementScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listePaiementScolaire.getRequeteSite_();
			SolrDocumentList documentsSolr = listePaiementScolaire.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listePaiementScolaire.getList().stream().findFirst().orElse(null));
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void recherchePaiementScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, operationRequete);
		try {
			sqlPaiementScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurPaiementScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							recherchePaiementScolaire(requeteSite, false, true, "/api/paiement", "Recherche", c -> {
								if(c.succeeded()) {
									ListeRecherche<PaiementScolaire> listePaiementScolaire = c.result();
									recherchePaiementScolaireReponse(listePaiementScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("recherchePaiementScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("recherchePaiementScolaire a échoué. ", d.cause()));
											erreurPaiementScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("recherchePaiementScolaire a échoué. ", c.cause()));
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("recherchePaiementScolaire a échoué. ", b.cause()));
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("recherchePaiementScolaire a échoué. ", a.cause()));
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("recherchePaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void recherchePaiementScolaireReponse(ListeRecherche<PaiementScolaire> listePaiementScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listePaiementScolaire.getRequeteSite_();
		try {
			reponse200RecherchePaiementScolaire(listePaiementScolaire, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							LOGGER.info(String.format("recherchePaiementScolaire sql commit. "));
							connexionSql.close(c -> {
								if(c.succeeded()) {
									LOGGER.info(String.format("recherchePaiementScolaire sql close. "));
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("recherchePaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200RecherchePaiementScolaire(ListeRecherche<PaiementScolaire> listePaiementScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listePaiementScolaire.getRequeteSite_();
			QueryResponse reponseRecherche = listePaiementScolaire.getQueryResponse();
			SolrDocumentList documentsSolr = listePaiementScolaire.getSolrDocumentList();
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
			listePaiementScolaire.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listePaiementScolaire.getFields();
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
	public void pagerecherchePaiementScolaireId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		pagerecherchePaiementScolaire(operationRequete, gestionnaireEvenements);
	}

	@Override
	public void pagerecherchePaiementScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, operationRequete);
		try {
			sqlPaiementScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurPaiementScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							recherchePaiementScolaire(requeteSite, false, true, "/paiement", "PageRecherche", c -> {
								if(c.succeeded()) {
									ListeRecherche<PaiementScolaire> listePaiementScolaire = c.result();
									pagerecherchePaiementScolaireReponse(listePaiementScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("pagerecherchePaiementScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("pagerecherchePaiementScolaire a échoué. ", d.cause()));
											erreurPaiementScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("pagerecherchePaiementScolaire a échoué. ", c.cause()));
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("pagerecherchePaiementScolaire a échoué. ", b.cause()));
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("pagerecherchePaiementScolaire a échoué. ", a.cause()));
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("pagerecherchePaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void pagerecherchePaiementScolaireReponse(ListeRecherche<PaiementScolaire> listePaiementScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listePaiementScolaire.getRequeteSite_();
		try {
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(requeteSite, buffer);
			requeteSite.setW(w);
			reponse200PageRecherchePaiementScolaire(listePaiementScolaire, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							LOGGER.info(String.format("pagerecherchePaiementScolaire sql commit. "));
							connexionSql.close(c -> {
								if(c.succeeded()) {
									LOGGER.info(String.format("pagerecherchePaiementScolaire sql close. "));
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurPaiementScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("pagerecherchePaiementScolaire a échoué. ", ex));
			erreurPaiementScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PageRecherchePaiementScolaire(ListeRecherche<PaiementScolaire> listePaiementScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listePaiementScolaire.getRequeteSite_();
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(listePaiementScolaire.getRequeteSite_(), buffer);
			PaiementPage page = new PaiementPage();
			SolrDocument pageDocumentSolr = new SolrDocument();
			CaseInsensitiveHeaders requeteEnTetes = new CaseInsensitiveHeaders();
			requeteSite.setRequeteEnTetes(requeteEnTetes);

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/paiement");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			if(listePaiementScolaire.size() == 1)
				requeteSite.setRequetePk(listePaiementScolaire.get(0).getPk());
			requeteSite.setW(w);
			page.setListePaiementScolaire(listePaiementScolaire);
			page.setRequeteSite_(requeteSite);
			page.initLoinPaiementPage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requeteEnTetes)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<PaiementScolaire> definirIndexerPaiementScolaire(PaiementScolaire paiementScolaire, Handler<AsyncResult<PaiementScolaire>> gestionnaireEvenements) {
		Promise<PaiementScolaire> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = paiementScolaire.getRequeteSite_();
		definirPaiementScolaire(paiementScolaire, c -> {
			if(c.succeeded()) {
				attribuerPaiementScolaire(paiementScolaire, d -> {
					if(d.succeeded()) {
						indexerPaiementScolaire(paiementScolaire, e -> {
							if(e.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(paiementScolaire));
								promise.complete(paiementScolaire);
							} else {
								erreurPaiementScolaire(requeteSite, null, e);
							}
						});
					} else {
						erreurPaiementScolaire(requeteSite, null, d);
					}
				});
			} else {
				erreurPaiementScolaire(requeteSite, null, c);
			}
		});
		return promise.future();
	}

	public void creerPaiementScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<PaiementScolaire>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_creer
					, new JsonArray(Arrays.asList(PaiementScolaire.class.getCanonicalName(), utilisateurId))
					, creerAsync
			-> {
				JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = creerLigne.getLong(0);
				PaiementScolaire o = new PaiementScolaire();
				o.setPk(pk);
				o.setRequeteSite_(requeteSite);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public RequeteApi requeteApiPaiementScolaire(PaiementScolaire o) {
		RequeteApi requeteApi = o.getRequeteSite_().getRequeteApi_();
		if(requeteApi != null) {
			List<Long> pks = requeteApi.getPks();
			List<String> classes = requeteApi.getClasses();
			if(o.getInscriptionCle() != null) {
				if(!pks.contains(o.getInscriptionCle())) {
					pks.add(o.getInscriptionCle());
					classes.add("InscriptionScolaire");
				}
			}
		}
		return requeteApi;
	}

	public void erreurPaiementScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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

	public void sqlPaiementScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public RequeteSiteFrFR genererRequeteSiteFrFRPourPaiementScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete) {
		return genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, operationRequete, null);
	}

	public RequeteSiteFrFR genererRequeteSiteFrFRPourPaiementScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete, JsonObject body) {
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

	public void utilisateurPaiementScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
							UtilisateurSiteFrFRApiServiceImpl utilisateurService = new UtilisateurSiteFrFRApiServiceImpl(siteContexte);
							if(utilisateurValeurs == null) {
								JsonObject utilisateurVertx = requeteSite.getOperationRequete().getUser();
								JsonObject principalJson = KeycloakHelper.parseToken(utilisateurVertx.getString("access_token"));

								JsonObject jsonObject = new JsonObject();
								jsonObject.put("utilisateurNom", principalJson.getString("preferred_username"));
								jsonObject.put("utilisateurPrenom", principalJson.getString("given_name"));
								jsonObject.put("utilisateurNomFamille", principalJson.getString("family_name"));
								jsonObject.put("utilisateurId", principalJson.getString("sub"));
								utilisateurPaiementScolaireDefinir(requeteSite, jsonObject, false);

								RequeteSiteFrFR requeteSite2 = new RequeteSiteFrFR();
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

								utilisateurService.creerUtilisateurSite(requeteSite2, b -> {
									if(b.succeeded()) {
										UtilisateurSite utilisateurSite = b.result();
										utilisateurService.sqlPOSTUtilisateurSite(utilisateurSite, false, c -> {
											if(c.succeeded()) {
												utilisateurService.definirIndexerUtilisateurSite(utilisateurSite, d -> {
													if(d.succeeded()) {
														requeteSite.setUtilisateurSite(utilisateurSite);
														requeteSite.setUtilisateurNom(principalJson.getString("preferred_username"));
														requeteSite.setUtilisateurPrenom(principalJson.getString("given_name"));
														requeteSite.setUtilisateurNomFamille(principalJson.getString("family_name"));
														requeteSite.setUtilisateurId(principalJson.getString("sub"));
														requeteSite.setUtilisateurCle(utilisateurSite.getPk());
														gestionnaireEvenements.handle(Future.succeededFuture());
													} else {
														erreurPaiementScolaire(requeteSite, gestionnaireEvenements, d);
													}
												});
											} else {
												erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
											}
										});
									} else {
										erreurPaiementScolaire(requeteSite, gestionnaireEvenements, b);
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
								Boolean definir = utilisateurPaiementScolaireDefinir(requeteSite, jsonObject, true);
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

									RequeteApi requeteApi = new RequeteApi();
									requeteApi.setRows(1);
									requeteApi.setNumFound(1L);
									requeteApi.setNumPATCH(0L);
									requeteApi.initLoinRequeteApi(requeteSite2);
									requeteSite2.setRequeteApi_(requeteApi);

									utilisateurService.sqlPATCHUtilisateurSite(utilisateurSite, false, c -> {
										if(c.succeeded()) {
											UtilisateurSite utilisateurSite2 = c.result();
											utilisateurService.definirIndexerUtilisateurSite(utilisateurSite2, d -> {
												if(d.succeeded()) {
													requeteSite.setUtilisateurSite(utilisateurSite2);
													requeteSite.setUtilisateurNom(utilisateurSite2.getUtilisateurNom());
													requeteSite.setUtilisateurPrenom(utilisateurSite2.getUtilisateurPrenom());
													requeteSite.setUtilisateurNomFamille(utilisateurSite2.getUtilisateurNomFamille());
													requeteSite.setUtilisateurId(utilisateurSite2.getUtilisateurId());
													requeteSite.setUtilisateurCle(utilisateurSite2.getPk());
													gestionnaireEvenements.handle(Future.succeededFuture());
												} else {
													erreurPaiementScolaire(requeteSite, gestionnaireEvenements, d);
												}
											});
										} else {
											erreurPaiementScolaire(requeteSite, gestionnaireEvenements, c);
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

	public Boolean utilisateurPaiementScolaireDefinir(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void recherchePaiementScolaireQ(String uri, String apiMethode, ListeRecherche<PaiementScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		listeRecherche.setQuery(varIndexe + ":" + ("*".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));
		if(!"*".equals(entiteVar)) {
			listeRecherche.setHighlight(true);
			listeRecherche.setHighlightSnippets(3);
			listeRecherche.addHighlightField(varIndexe);
			listeRecherche.setParam("hl.encoder", "html");
		}
	}

	public void recherchePaiementScolaireFq(String uri, String apiMethode, ListeRecherche<PaiementScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
	}

	public void recherchePaiementScolaireSort(String uri, String apiMethode, ListeRecherche<PaiementScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurIndexe));
	}

	public void recherchePaiementScolaireRows(String uri, String apiMethode, ListeRecherche<PaiementScolaire> listeRecherche, Integer valeurRows) {
			listeRecherche.setRows(apiMethode != null && apiMethode.contains("Recherche") ? valeurRows : 10);
	}

	public void recherchePaiementScolaireStart(String uri, String apiMethode, ListeRecherche<PaiementScolaire> listeRecherche, Integer valeurStart) {
		listeRecherche.setStart(valeurStart);
	}

	public void recherchePaiementScolaireVar(String uri, String apiMethode, ListeRecherche<PaiementScolaire> listeRecherche, String var, String valeur) {
		listeRecherche.getRequeteSite_().getRequeteVars().put(var, valeur);
	}

	public void recherchePaiementScolaireUri(String uri, String apiMethode, ListeRecherche<PaiementScolaire> listeRecherche) {
	}

	public void recherchePaiementScolaire(RequeteSiteFrFR requeteSite, Boolean peupler, Boolean stocker, String uri, String apiMethode, Handler<AsyncResult<ListeRecherche<PaiementScolaire>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(PaiementScolaire.class);
			listeRecherche.setRequeteSite_(requeteSite);
			if(entiteListe != null)
				listeRecherche.addFields(entiteListe);
			listeRecherche.add("json.facet", "{max_modifie:'max(modifie_indexed_date)'}");
			listeRecherche.add("json.facet", "{terms_enfantNomCompletPrefere:{terms:{field:enfantNomCompletPrefere_indexed_string}}}");
			listeRecherche.add("json.facet", "{sum_paiementMontant:'sum(paiementMontant_indexed_double)'}");
			listeRecherche.add("json.facet", "{sum_fraisMontant:'sum(fraisMontant_indexed_double)'}");
			listeRecherche.add("json.facet", "{sum_fraisMontantFuture:'sum(fraisMontantFuture_indexed_double)'}");

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
								varIndexe = "*".equals(entiteVar) ? entiteVar : PaiementScolaire.varRecherchePaiementScolaire(entiteVar);
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								valeurIndexe = StringUtils.isEmpty(valeurIndexe) ? "*" : valeurIndexe;
								recherchePaiementScolaireQ(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "fq":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								varIndexe = PaiementScolaire.varIndexePaiementScolaire(entiteVar);
								recherchePaiementScolaireFq(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "sort":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
								valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
								varIndexe = PaiementScolaire.varIndexePaiementScolaire(entiteVar);
								recherchePaiementScolaireSort(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "start":
								valeurStart = (Integer)paramObjet;
								recherchePaiementScolaireStart(uri, apiMethode, listeRecherche, valeurStart);
								break;
							case "rows":
								valeurRows = (Integer)paramObjet;
								recherchePaiementScolaireRows(uri, apiMethode, listeRecherche, valeurRows);
								break;
							case "var":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								recherchePaiementScolaireVar(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe);
								break;
						}
					}
					recherchePaiementScolaireUri(uri, apiMethode, listeRecherche);
				} catch(Exception e) {
					gestionnaireEvenements.handle(Future.failedFuture(e));
				}
			});
			if(listeRecherche.getSorts().size() == 0) {
				listeRecherche.addSort("paiementDate_indexed_date", ORDER.desc);
				listeRecherche.addSort("paiementPar_indexed_string", ORDER.desc);
			}
			listeRecherche.initLoinPourClasse(requeteSite);
			gestionnaireEvenements.handle(Future.succeededFuture(listeRecherche));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void definirPaiementScolaire(PaiementScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void attribuerPaiementScolaire(PaiementScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void indexerPaiementScolaire(PaiementScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			o.initLoinPourClasse(requeteSite);
			o.indexerPourClasse();
			if(BooleanUtils.isFalse(Optional.ofNullable(requeteSite.getRequeteApi_()).map(RequeteApi::getEmpty).orElse(null))) {
				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourPaiementScolaire(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());
				ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(PaiementScolaire.class);
				listeRecherche.addFilterQuery("modifie_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(requeteSite.getRequeteApi_().getCree().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				listeRecherche.add("json.facet", "{inscriptionCle:{terms:{field:inscriptionCle_indexed_longs, limit:1000}}}");
				listeRecherche.setRows(1000);
				listeRecherche.initLoinListeRecherche(requeteSite2);
				List<Future> futures = new ArrayList<>();

				{
					Long pk = o.getInscriptionCle();
					ListeRecherche<InscriptionScolaire> listeRecherche2 = new ListeRecherche<InscriptionScolaire>();
					if(pk != null) {
						listeRecherche2.setStocker(true);
						listeRecherche2.setQuery("*:*");
						listeRecherche2.setC(InscriptionScolaire.class);
						listeRecherche2.addFilterQuery("pk_indexed_long:" + pk);
						listeRecherche2.setRows(1);
						listeRecherche2.initLoinListeRecherche(requeteSite2);
					}
					InscriptionScolaire o2 = listeRecherche2.getList().stream().findFirst().orElse(null);

					if(o2 != null) {
						InscriptionScolaireFrFRGenApiServiceImpl service = new InscriptionScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());

						RequeteApi requeteApi = new RequeteApi();
						requeteApi.setRows(1);
						requeteApi.setNumFound(1L);
						requeteApi.setNumPATCH(0L);
						requeteApi.initLoinRequeteApi(requeteSite2);
						requeteSite2.setRequeteApi_(requeteApi);
						requeteSite2.getVertx().eventBus().publish("websocketInscriptionScolaire", JsonObject.mapFrom(requeteApi).toString());

						if(pk != null) {
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
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						LOGGER.info("Recharger relations a réussi. ");
						PaiementScolaireFrFRApiServiceImpl service = new PaiementScolaireFrFRApiServiceImpl(requeteSite2.getSiteContexte_());
						List<Future> futures2 = new ArrayList<>();
						for(PaiementScolaire o2 : listeRecherche.getList()) {
							futures2.add(
								service.patchPaiementScolaireFuture(o2, false, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("PaiementScolaire %s rechargé. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("PaiementScolaire %s a échoué. ", o2.getPk()));
										gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Recharger PaiementScolaire a réussi. ");
								gestionnaireEvenements.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Recharger relations a échoué. ", b.cause());
								erreurPaiementScolaire(requeteSite2, gestionnaireEvenements, b);
							}
						});
					} else {
						LOGGER.error("Recharger relations a échoué. ", a.cause());
						erreurPaiementScolaire(requeteSite2, gestionnaireEvenements, a);
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
