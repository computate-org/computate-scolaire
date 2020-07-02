package org.computate.scolaire.frFR.html.part;

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
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPartHtml(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("postPartHtml a démarré. "));

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

			utilisateurPartHtml(requeteSite, b -> {
				if(b.succeeded()) {
					RequeteApi requeteApi = new RequeteApi();
					requeteApi.setRows(1);
					requeteApi.setNumFound(1L);
					requeteApi.setNumPATCH(0L);
					requeteApi.initLoinRequeteApi(requeteSite);
					requeteSite.setRequeteApi_(requeteApi);
					requeteSite.getVertx().eventBus().publish("websocketPartHtml", JsonObject.mapFrom(requeteApi).toString());
					postPartHtmlFuture(requeteSite, false, c -> {
						if(c.succeeded()) {
							PartHtml partHtml = c.result();
							requeteApi.setPk(partHtml.getPk());
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
							LOGGER.error(String.format("postPartHtml a échoué. ", c.cause()));
							erreurPartHtml(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("postPartHtml a échoué. ", b.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postPartHtml a échoué. ", ex));
			erreurPartHtml(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public Future<PartHtml> postPartHtmlFuture(RequeteSiteFrFR requeteSite, Boolean inheritPk, Handler<AsyncResult<PartHtml>> gestionnaireEvenements) {
		Promise<PartHtml> promise = Promise.promise();
		try {
			sqlConnexionPartHtml(requeteSite, a -> {
				if(a.succeeded()) {
					sqlTransactionPartHtml(requeteSite, b -> {
						if(b.succeeded()) {
							creerPartHtml(requeteSite, c -> {
								if(c.succeeded()) {
									PartHtml partHtml = c.result();
									sqlPOSTPartHtml(partHtml, inheritPk, d -> {
										if(d.succeeded()) {
											definirIndexerPartHtml(partHtml, e -> {
												if(e.succeeded()) {
													RequeteApi requeteApi = requeteSite.getRequeteApi_();
													if(requeteApi != null) {
														requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
														partHtml.requeteApiPartHtml();
														requeteSite.getVertx().eventBus().publish("websocketPartHtml", JsonObject.mapFrom(requeteApi).toString());
													}
													gestionnaireEvenements.handle(Future.succeededFuture(partHtml));
													promise.complete(partHtml);
												} else {
													LOGGER.error(String.format("postPartHtmlFuture a échoué. ", e.cause()));
													gestionnaireEvenements.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											LOGGER.error(String.format("postPartHtmlFuture a échoué. ", d.cause()));
											gestionnaireEvenements.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("postPartHtmlFuture a échoué. ", c.cause()));
									gestionnaireEvenements.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("postPartHtmlFuture a échoué. ", b.cause()));
							gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("postPartHtmlFuture a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("postPartHtmlFuture a échoué. ", e));
			erreurPartHtml(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTPartHtml(PartHtml o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.inheritPk a échoué", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.archive a échoué", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.supprime a échoué", b.cause())));
							});
						}));
						break;
					case "designPageCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(DesignPage.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(pk, "designPageCles", l2, "partHtmlCles")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur PartHtml.designPageCles a échoué", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("DesignPage");
									}
								}
							}
						}
						break;
					case "htmlLien":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlLien", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlLien a échoué", b.cause())));
							});
						}));
						break;
					case "htmlElement":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlElement", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlElement a échoué", b.cause())));
							});
						}));
						break;
					case "htmlId":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlId", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlId a échoué", b.cause())));
							});
						}));
						break;
					case "htmlClasses":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlClasses", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlClasses a échoué", b.cause())));
							});
						}));
						break;
					case "htmlStyle":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlStyle", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlStyle a échoué", b.cause())));
							});
						}));
						break;
					case "htmlAvant":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlAvant", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlAvant a échoué", b.cause())));
							});
						}));
						break;
					case "htmlApres":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlApres", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlApres a échoué", b.cause())));
							});
						}));
						break;
					case "htmlTexte":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlTexte", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlTexte a échoué", b.cause())));
							});
						}));
						break;
					case "htmlVar":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlVar", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVar a échoué", b.cause())));
							});
						}));
						break;
					case "htmlVarSpan":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlVarSpan", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarSpan a échoué", b.cause())));
							});
						}));
						break;
					case "htmlVarForm":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlVarForm", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarForm a échoué", b.cause())));
							});
						}));
						break;
					case "htmlVarInput":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlVarInput", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarInput a échoué", b.cause())));
							});
						}));
						break;
					case "htmlVarForEach":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlVarForEach", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarForEach a échoué", b.cause())));
							});
						}));
						break;
					case "htmlExclure":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlExclure", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlExclure a échoué", b.cause())));
							});
						}));
						break;
					case "pdfExclure":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "pdfExclure", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.pdfExclure a échoué", b.cause())));
							});
						}));
						break;
					case "connecterDeconnecter":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "connecterDeconnecter", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.connecterDeconnecter a échoué", b.cause())));
							});
						}));
						break;
					case "tri1":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri1", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri1 a échoué", b.cause())));
							});
						}));
						break;
					case "tri2":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri2", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri2 a échoué", b.cause())));
							});
						}));
						break;
					case "tri3":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri3", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri3 a échoué", b.cause())));
							});
						}));
						break;
					case "tri4":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri4", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri4 a échoué", b.cause())));
							});
						}));
						break;
					case "tri5":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri5", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri5 a échoué", b.cause())));
							});
						}));
						break;
					case "tri6":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri6", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri6 a échoué", b.cause())));
							});
						}));
						break;
					case "tri7":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri7", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri7 a échoué", b.cause())));
							});
						}));
						break;
					case "tri8":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri8", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri8 a échoué", b.cause())));
							});
						}));
						break;
					case "tri9":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri9", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri9 a échoué", b.cause())));
							});
						}));
						break;
					case "tri10":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri10", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri10 a échoué", b.cause())));
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
					LOGGER.error(String.format("sqlPOSTPartHtml a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPOSTPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void postPartHtmlReponse(PartHtml partHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = partHtml.getRequeteSite_();
		try {
			reponse200POSTPartHtml(partHtml, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("postPartHtmlReponse a échoué. ", a.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postPartHtmlReponse a échoué. ", ex));
			erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200POSTPartHtml(PartHtml o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			JsonObject json = JsonObject.mapFrom(o);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200POSTPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTImport //

	@Override
	public void putimportPartHtml(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPartHtml(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putimportPartHtml a démarré. "));

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

			utilisateurPartHtml(requeteSite, b -> {
				if(b.succeeded()) {
					putimportPartHtmlReponse(requeteSite, c -> {
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
										requeteSite.getVertx().eventBus().publish("websocketPartHtml", JsonObject.mapFrom(requeteApi).toString());
										varsPartHtml(requeteSite, d -> {
											if(d.succeeded()) {
												listePUTImportPartHtml(requeteApi, requeteSite, e -> {
													if(e.succeeded()) {
														putimportPartHtmlReponse(requeteSite, f -> {
															if(e.succeeded()) {
																LOGGER.info(String.format("putimportPartHtml a réussi. "));
																blockingCodeHandler.handle(Future.succeededFuture(e.result()));
															} else {
																LOGGER.error(String.format("putimportPartHtml a échoué. ", f.cause()));
																erreurPartHtml(requeteSite, null, f);
															}
														});
													} else {
														LOGGER.error(String.format("putimportPartHtml a échoué. ", e.cause()));
														erreurPartHtml(requeteSite, null, e);
													}
												});
											} else {
												LOGGER.error(String.format("putimportPartHtml a échoué. ", d.cause()));
												erreurPartHtml(requeteSite, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putimportPartHtml a échoué. ", ex));
										erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("putimportPartHtml a échoué. ", c.cause()));
							erreurPartHtml(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("putimportPartHtml a échoué. ", b.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportPartHtml a échoué. ", ex));
			erreurPartHtml(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePUTImportPartHtml(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				json.put("cree", json.getValue("cree"));

				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourPartHtml(siteContexte, requeteSite.getOperationRequete(), json);
				requeteSite2.setRequeteApi_(requeteApi);
				requeteSite2.setRequeteVars(requeteSite.getRequeteVars());

				ListeRecherche<PartHtml> listeRecherche = new ListeRecherche<PartHtml>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(PartHtml.class);
				listeRecherche.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				listeRecherche.initLoinPourClasse(requeteSite2);

				if(listeRecherche.size() == 1) {
					PartHtml o = listeRecherche.getList().stream().findFirst().orElse(null);
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
							patchPartHtmlFuture(o, true, a -> {
								if(a.succeeded()) {
								} else {
									LOGGER.error(String.format("listePUTImportPartHtml a échoué. ", a.cause()));
									erreurPartHtml(requeteSite2, gestionnaireEvenements, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postPartHtmlFuture(requeteSite2, true, a -> {
							if(a.succeeded()) {
							} else {
								LOGGER.error(String.format("listePUTImportPartHtml a échoué. ", a.cause()));
								erreurPartHtml(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
					reponse200PUTImportPartHtml(requeteSite, gestionnaireEvenements);
				} else {
					LOGGER.error(String.format("listePUTImportPartHtml a échoué. ", a.cause()));
					erreurPartHtml(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("listePUTImportPartHtml a échoué. ", ex));
			erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
		}
	}

	public void putimportPartHtmlReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PUTImportPartHtml(requeteSite, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putimportPartHtmlReponse a échoué. ", a.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportPartHtmlReponse a échoué. ", ex));
			erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PUTImportPartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200PUTImportPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTFusion //

	@Override
	public void putfusionPartHtml(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPartHtml(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putfusionPartHtml a démarré. "));

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

			utilisateurPartHtml(requeteSite, b -> {
				if(b.succeeded()) {
					putfusionPartHtmlReponse(requeteSite, c -> {
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
										requeteSite.getVertx().eventBus().publish("websocketPartHtml", JsonObject.mapFrom(requeteApi).toString());
										varsPartHtml(requeteSite, d -> {
											if(d.succeeded()) {
												listePUTFusionPartHtml(requeteApi, requeteSite, e -> {
													if(e.succeeded()) {
														putfusionPartHtmlReponse(requeteSite, f -> {
															if(e.succeeded()) {
																LOGGER.info(String.format("putfusionPartHtml a réussi. "));
																blockingCodeHandler.handle(Future.succeededFuture(e.result()));
															} else {
																LOGGER.error(String.format("putfusionPartHtml a échoué. ", f.cause()));
																erreurPartHtml(requeteSite, null, f);
															}
														});
													} else {
														LOGGER.error(String.format("putfusionPartHtml a échoué. ", e.cause()));
														erreurPartHtml(requeteSite, null, e);
													}
												});
											} else {
												LOGGER.error(String.format("putfusionPartHtml a échoué. ", d.cause()));
												erreurPartHtml(requeteSite, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putfusionPartHtml a échoué. ", ex));
										erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("putfusionPartHtml a échoué. ", c.cause()));
							erreurPartHtml(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("putfusionPartHtml a échoué. ", b.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putfusionPartHtml a échoué. ", ex));
			erreurPartHtml(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePUTFusionPartHtml(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourPartHtml(siteContexte, requeteSite.getOperationRequete(), json);
				requeteSite2.setRequeteApi_(requeteApi);
				requeteSite2.setRequeteVars(requeteSite.getRequeteVars());

				ListeRecherche<PartHtml> listeRecherche = new ListeRecherche<PartHtml>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(PartHtml.class);
				listeRecherche.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				listeRecherche.initLoinPourClasse(requeteSite2);

				if(listeRecherche.size() == 1) {
					PartHtml o = listeRecherche.getList().stream().findFirst().orElse(null);
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
							patchPartHtmlFuture(o, false, a -> {
								if(a.succeeded()) {
								} else {
									LOGGER.error(String.format("listePUTFusionPartHtml a échoué. ", a.cause()));
									erreurPartHtml(requeteSite2, gestionnaireEvenements, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postPartHtmlFuture(requeteSite2, false, a -> {
							if(a.succeeded()) {
							} else {
								LOGGER.error(String.format("listePUTFusionPartHtml a échoué. ", a.cause()));
								erreurPartHtml(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
					reponse200PUTFusionPartHtml(requeteSite, gestionnaireEvenements);
				} else {
					LOGGER.error(String.format("listePUTFusionPartHtml a échoué. ", a.cause()));
					erreurPartHtml(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("listePUTFusionPartHtml a échoué. ", ex));
			erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
		}
	}

	public void putfusionPartHtmlReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PUTFusionPartHtml(requeteSite, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putfusionPartHtmlReponse a échoué. ", a.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putfusionPartHtmlReponse a échoué. ", ex));
			erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PUTFusionPartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200PUTFusionPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTCopie //

	@Override
	public void putcopiePartHtml(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPartHtml(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putcopiePartHtml a démarré. "));

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

			utilisateurPartHtml(requeteSite, b -> {
				if(b.succeeded()) {
					putcopiePartHtmlReponse(requeteSite, c -> {
						if(c.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
							WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
							executeurTravailleur.executeBlocking(
								blockingCodeHandler -> {
									try {
										recherchePartHtml(requeteSite, false, true, "/api/part-html/copie", "PUTCopie", d -> {
											if(d.succeeded()) {
												ListeRecherche<PartHtml> listePartHtml = d.result();
												RequeteApi requeteApi = new RequeteApi();
												requeteApi.setRows(listePartHtml.getRows());
												requeteApi.setNumFound(listePartHtml.getQueryResponse().getResults().getNumFound());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite);
												requeteSite.setRequeteApi_(requeteApi);
												requeteSite.getVertx().eventBus().publish("websocketPartHtml", JsonObject.mapFrom(requeteApi).toString());
												try {
													listePUTCopiePartHtml(requeteApi, listePartHtml, e -> {
														if(e.succeeded()) {
															putcopiePartHtmlReponse(requeteSite, f -> {
																if(f.succeeded()) {
																	LOGGER.info(String.format("putcopiePartHtml a réussi. "));
																	blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																} else {
																	LOGGER.error(String.format("putcopiePartHtml a échoué. ", f.cause()));
																	erreurPartHtml(requeteSite, null, f);
																}
															});
														} else {
															LOGGER.error(String.format("putcopiePartHtml a échoué. ", e.cause()));
															erreurPartHtml(requeteSite, null, e);
														}
													});
												} catch(Exception ex) {
													LOGGER.error(String.format("putcopiePartHtml a échoué. ", ex));
													erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
												}
											} else {
												LOGGER.error(String.format("putcopiePartHtml a échoué. ", d.cause()));
												erreurPartHtml(requeteSite, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putcopiePartHtml a échoué. ", ex));
										erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("putcopiePartHtml a échoué. ", c.cause()));
							erreurPartHtml(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("putcopiePartHtml a échoué. ", b.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopiePartHtml a échoué. ", ex));
			erreurPartHtml(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePUTCopiePartHtml(RequeteApi requeteApi, ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
		listePartHtml.getList().forEach(o -> {
			RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourPartHtml(siteContexte, requeteSite.getOperationRequete(), requeteSite.getObjetJson());
			requeteSite2.setRequeteApi_(requeteSite.getRequeteApi_());
			o.setRequeteSite_(requeteSite2);
			futures.add(
				putcopiePartHtmlFuture(requeteSite, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
					} else {
						LOGGER.error(String.format("listePUTCopiePartHtml a échoué. ", a.cause()));
						erreurPartHtml(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listePartHtml.size());
				if(listePartHtml.next()) {
					listePUTCopiePartHtml(requeteApi, listePartHtml, gestionnaireEvenements);
				} else {
					reponse200PUTCopiePartHtml(requeteSite, gestionnaireEvenements);
				}
			} else {
				LOGGER.error(String.format("listePUTCopiePartHtml a échoué. ", a.cause()));
				erreurPartHtml(listePartHtml.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<PartHtml> putcopiePartHtmlFuture(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Handler<AsyncResult<PartHtml>> gestionnaireEvenements) {
		Promise<PartHtml> promise = Promise.promise();
		try {

			jsonObject.put("sauvegardes", Optional.ofNullable(jsonObject.getJsonArray("sauvegardes")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("sauvegardes").add(o.getKey());
			});

			sqlConnexionPartHtml(requeteSite, a -> {
				if(a.succeeded()) {
					sqlTransactionPartHtml(requeteSite, b -> {
						if(b.succeeded()) {
							creerPartHtml(requeteSite, c -> {
								if(c.succeeded()) {
									PartHtml partHtml = c.result();
									sqlPUTCopiePartHtml(partHtml, jsonObject, d -> {
										if(d.succeeded()) {
											definirIndexerPartHtml(partHtml, e -> {
												if(e.succeeded()) {
													RequeteApi requeteApi = requeteSite.getRequeteApi_();
													if(requeteApi != null) {
														requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
														if(requeteApi.getNumFound() == 1L) {
															partHtml.requeteApiPartHtml();
														}
														requeteSite.getVertx().eventBus().publish("websocketPartHtml", JsonObject.mapFrom(requeteApi).toString());
													}
													gestionnaireEvenements.handle(Future.succeededFuture(partHtml));
													promise.complete(partHtml);
												} else {
													LOGGER.error(String.format("putcopiePartHtmlFuture a échoué. ", e.cause()));
													gestionnaireEvenements.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											LOGGER.error(String.format("putcopiePartHtmlFuture a échoué. ", d.cause()));
											gestionnaireEvenements.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("putcopiePartHtmlFuture a échoué. ", c.cause()));
									gestionnaireEvenements.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("putcopiePartHtmlFuture a échoué. ", b.cause()));
							gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("putcopiePartHtmlFuture a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("putcopiePartHtmlFuture a échoué. ", e));
			erreurPartHtml(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopiePartHtml(PartHtml o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.inheritPk a échoué", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.archive a échoué", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.supprime a échoué", b.cause())));
							});
						}));
						break;
					case "designPageCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_addA
										, Tuple.of(pk, "designPageCles", l, "partHtmlCles")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.designPageCles a échoué", b.cause())));
								});
							}));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("DesignPage");
							}
						}
						break;
					case "htmlLien":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlLien", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlLien a échoué", b.cause())));
							});
						}));
						break;
					case "htmlElement":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlElement", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlElement a échoué", b.cause())));
							});
						}));
						break;
					case "htmlId":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlId", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlId a échoué", b.cause())));
							});
						}));
						break;
					case "htmlClasses":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlClasses", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlClasses a échoué", b.cause())));
							});
						}));
						break;
					case "htmlStyle":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlStyle", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlStyle a échoué", b.cause())));
							});
						}));
						break;
					case "htmlAvant":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlAvant", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlAvant a échoué", b.cause())));
							});
						}));
						break;
					case "htmlApres":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlApres", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlApres a échoué", b.cause())));
							});
						}));
						break;
					case "htmlTexte":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlTexte", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlTexte a échoué", b.cause())));
							});
						}));
						break;
					case "htmlVar":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlVar", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVar a échoué", b.cause())));
							});
						}));
						break;
					case "htmlVarSpan":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlVarSpan", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarSpan a échoué", b.cause())));
							});
						}));
						break;
					case "htmlVarForm":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlVarForm", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarForm a échoué", b.cause())));
							});
						}));
						break;
					case "htmlVarInput":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlVarInput", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarInput a échoué", b.cause())));
							});
						}));
						break;
					case "htmlVarForEach":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlVarForEach", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarForEach a échoué", b.cause())));
							});
						}));
						break;
					case "htmlExclure":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "htmlExclure", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlExclure a échoué", b.cause())));
							});
						}));
						break;
					case "pdfExclure":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "pdfExclure", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.pdfExclure a échoué", b.cause())));
							});
						}));
						break;
					case "connecterDeconnecter":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "connecterDeconnecter", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.connecterDeconnecter a échoué", b.cause())));
							});
						}));
						break;
					case "tri1":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri1", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri1 a échoué", b.cause())));
							});
						}));
						break;
					case "tri2":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri2", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri2 a échoué", b.cause())));
							});
						}));
						break;
					case "tri3":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri3", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri3 a échoué", b.cause())));
							});
						}));
						break;
					case "tri4":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri4", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri4 a échoué", b.cause())));
							});
						}));
						break;
					case "tri5":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri5", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri5 a échoué", b.cause())));
							});
						}));
						break;
					case "tri6":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri6", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri6 a échoué", b.cause())));
							});
						}));
						break;
					case "tri7":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri7", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri7 a échoué", b.cause())));
							});
						}));
						break;
					case "tri8":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri8", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri8 a échoué", b.cause())));
							});
						}));
						break;
					case "tri9":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri9", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri9 a échoué", b.cause())));
							});
						}));
						break;
					case "tri10":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContexteFrFR.SQL_setD
									, Tuple.of(pk, "tri10", Optional.ofNullable(jsonObject.getValue(entiteVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri10 a échoué", b.cause())));
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
					LOGGER.error(String.format("sqlPUTCopiePartHtml a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPUTCopiePartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void putcopiePartHtmlReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PUTCopiePartHtml(requeteSite, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putcopiePartHtmlReponse a échoué. ", a.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopiePartHtmlReponse a échoué. ", ex));
			erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PUTCopiePartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200PUTCopiePartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchPartHtml(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPartHtml(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("patchPartHtml a démarré. "));

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

			utilisateurPartHtml(requeteSite, b -> {
				if(b.succeeded()) {
					patchPartHtmlReponse(requeteSite, c -> {
						if(c.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
							WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
							executeurTravailleur.executeBlocking(
								blockingCodeHandler -> {
									try {
										recherchePartHtml(requeteSite, false, true, "/api/part-html", "PATCH", d -> {
											if(d.succeeded()) {
												ListeRecherche<PartHtml> listePartHtml = d.result();
												RequeteApi requeteApi = new RequeteApi();
												requeteApi.setRows(listePartHtml.getRows());
												requeteApi.setNumFound(listePartHtml.getQueryResponse().getResults().getNumFound());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite);
												requeteSite.setRequeteApi_(requeteApi);
												requeteSite.getVertx().eventBus().publish("websocketPartHtml", JsonObject.mapFrom(requeteApi).toString());
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

												try {
													listePATCHPartHtml(requeteApi, listePartHtml, dt, e -> {
														if(e.succeeded()) {
															patchPartHtmlReponse(requeteSite, f -> {
																if(f.succeeded()) {
																	LOGGER.info(String.format("patchPartHtml a réussi. "));
																	blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																} else {
																	LOGGER.error(String.format("patchPartHtml a échoué. ", f.cause()));
																	erreurPartHtml(requeteSite, null, f);
																}
															});
														} else {
															LOGGER.error(String.format("patchPartHtml a échoué. ", e.cause()));
															erreurPartHtml(requeteSite, null, e);
														}
													});
												} catch(Exception ex) {
													LOGGER.error(String.format("patchPartHtml a échoué. ", ex));
													erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
												}
											} else {
												LOGGER.error(String.format("patchPartHtml a échoué. ", d.cause()));
												erreurPartHtml(requeteSite, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("patchPartHtml a échoué. ", ex));
										erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("patchPartHtml a échoué. ", c.cause()));
							erreurPartHtml(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("patchPartHtml a échoué. ", b.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchPartHtml a échoué. ", ex));
			erreurPartHtml(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePATCHPartHtml(RequeteApi requeteApi, ListeRecherche<PartHtml> listePartHtml, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
		listePartHtml.getList().forEach(o -> {
			RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourPartHtml(siteContexte, requeteSite.getOperationRequete(), requeteSite.getObjetJson());
			requeteSite2.setRequeteApi_(requeteSite.getRequeteApi_());
			o.setRequeteSite_(requeteSite2);
			futures.add(
				patchPartHtmlFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						erreurPartHtml(requeteSite2, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listePartHtml.next(dt)) {
					listePATCHPartHtml(requeteApi, listePartHtml, dt, gestionnaireEvenements);
				} else {
					reponse200PATCHPartHtml(requeteSite, gestionnaireEvenements);
				}
			} else {
				LOGGER.error(String.format("listePATCHPartHtml a échoué. ", a.cause()));
				erreurPartHtml(listePartHtml.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<PartHtml> patchPartHtmlFuture(PartHtml o, Boolean inheritPk, Handler<AsyncResult<PartHtml>> gestionnaireEvenements) {
		Promise<PartHtml> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			if(requeteApi != null && requeteApi.getNumFound() == 1L) {
				requeteApi.setOriginal(o);
				requeteApi.setPk(o.getPk());
			}
			sqlConnexionPartHtml(requeteSite, a -> {
				if(a.succeeded()) {
					sqlTransactionPartHtml(requeteSite, b -> {
						if(b.succeeded()) {
							sqlPATCHPartHtml(o, inheritPk, c -> {
								if(c.succeeded()) {
									PartHtml partHtml = c.result();
									definirIndexerPartHtml(partHtml, d -> {
										if(d.succeeded()) {
											if(requeteApi != null) {
												requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
												if(requeteApi.getNumFound() == 1L) {
													partHtml.requeteApiPartHtml();
												}
												requeteSite.getVertx().eventBus().publish("websocketPartHtml", JsonObject.mapFrom(requeteApi).toString());
											}
											gestionnaireEvenements.handle(Future.succeededFuture(partHtml));
											promise.complete(partHtml);
										} else {
											LOGGER.error(String.format("patchPartHtmlFuture a échoué. ", d.cause()));
											gestionnaireEvenements.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("patchPartHtmlFuture a échoué. ", c.cause()));
									gestionnaireEvenements.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("patchPartHtmlFuture a échoué. ", b.cause()));
							gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("patchPartHtmlFuture a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("patchPartHtmlFuture a échoué. ", e));
			erreurPartHtml(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHPartHtml(PartHtml o, Boolean inheritPk, Handler<AsyncResult<PartHtml>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Transaction tx = requeteSite.getTx();
			Long pk = o.getPk();
			JsonObject jsonObject = requeteSite.getObjetJson();
			Set<String> methodeNoms = jsonObject.fieldNames();
			PartHtml o2 = new PartHtml();
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
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.inheritPk a échoué", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.inheritPk a échoué", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.archive a échoué", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.archive a échoué", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.supprime a échoué", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.supprime a échoué", b.cause())));
								});
							}));
						}
						break;
					case "addDesignPageCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(DesignPage.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getDesignPageCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(pk, "designPageCles", l2, "partHtmlCles")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur PartHtml.designPageCles a échoué", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("DesignPage");
									}
								}
							}
						}
						break;
					case "addAllDesignPageCles":
						JsonArray addAllDesignPageClesValeurs = jsonObject.getJsonArray(methodeNom);
						if(addAllDesignPageClesValeurs != null) {
							for(Integer i = 0; i <  addAllDesignPageClesValeurs.size(); i++) {
								Long l = Long.parseLong(addAllDesignPageClesValeurs.getString(i));
								if(l != null) {
									ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
									listeRecherche.setQuery("*:*");
									listeRecherche.setStocker(true);
									listeRecherche.setC(DesignPage.class);
									listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									listeRecherche.initLoinListeRecherche(requeteSite);
									Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getDesignPageCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(pk, "designPageCles", l2, "partHtmlCles")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur PartHtml.designPageCles a échoué", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("DesignPage");
										}
									}
								}
							}
						}
						break;
					case "setDesignPageCles":
						JsonArray setDesignPageClesValeurs = jsonObject.getJsonArray(methodeNom);
						if(setDesignPageClesValeurs != null) {
							for(Integer i = 0; i <  setDesignPageClesValeurs.size(); i++) {
								Long l = Long.parseLong(setDesignPageClesValeurs.getString(i));
								if(l != null) {
									ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
									listeRecherche.setQuery("*:*");
									listeRecherche.setStocker(true);
									listeRecherche.setC(DesignPage.class);
									listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									listeRecherche.initLoinListeRecherche(requeteSite);
									Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getDesignPageCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_addA
												, Tuple.of(pk, "designPageCles", l2, "partHtmlCles")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur PartHtml.designPageCles a échoué", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("DesignPage");
										}
									}
								}
							}
						}
						if(o.getDesignPageCles() != null) {
							for(Long l :  o.getDesignPageCles()) {
								if(l != null && (setDesignPageClesValeurs == null || !setDesignPageClesValeurs.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_removeA
												, Tuple.of(pk, "designPageCles", l, "partHtmlCles")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur PartHtml.designPageCles a échoué", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removeDesignPageCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<DesignPage> listeRecherche = new ListeRecherche<DesignPage>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(DesignPage.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								Long l2 = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getDesignPageCles().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContexteFrFR.SQL_removeA
												, Tuple.of(pk, "designPageCles", l2, "partHtmlCles")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("valeur PartHtml.designPageCles a échoué", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("DesignPage");
									}
								}
							}
						}
						break;
					case "setHtmlLien":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "htmlLien")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlLien a échoué", b.cause())));
								});
							}));
						} else {
							o2.setHtmlLien(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "htmlLien", o2.jsonHtmlLien())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlLien a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setHtmlElement":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "htmlElement")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlElement a échoué", b.cause())));
								});
							}));
						} else {
							o2.setHtmlElement(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "htmlElement", o2.jsonHtmlElement())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlElement a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setHtmlId":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "htmlId")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlId a échoué", b.cause())));
								});
							}));
						} else {
							o2.setHtmlId(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "htmlId", o2.jsonHtmlId())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlId a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setHtmlClasses":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "htmlClasses")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlClasses a échoué", b.cause())));
								});
							}));
						} else {
							o2.setHtmlClasses(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "htmlClasses", o2.jsonHtmlClasses())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlClasses a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setHtmlStyle":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "htmlStyle")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlStyle a échoué", b.cause())));
								});
							}));
						} else {
							o2.setHtmlStyle(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "htmlStyle", o2.jsonHtmlStyle())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlStyle a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setHtmlAvant":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "htmlAvant")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlAvant a échoué", b.cause())));
								});
							}));
						} else {
							o2.setHtmlAvant(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "htmlAvant", o2.jsonHtmlAvant())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlAvant a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setHtmlApres":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "htmlApres")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlApres a échoué", b.cause())));
								});
							}));
						} else {
							o2.setHtmlApres(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "htmlApres", o2.jsonHtmlApres())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlApres a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setHtmlTexte":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "htmlTexte")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlTexte a échoué", b.cause())));
								});
							}));
						} else {
							o2.setHtmlTexte(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "htmlTexte", o2.jsonHtmlTexte())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlTexte a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setHtmlVar":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "htmlVar")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVar a échoué", b.cause())));
								});
							}));
						} else {
							o2.setHtmlVar(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "htmlVar", o2.jsonHtmlVar())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVar a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setHtmlVarSpan":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "htmlVarSpan")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarSpan a échoué", b.cause())));
								});
							}));
						} else {
							o2.setHtmlVarSpan(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "htmlVarSpan", o2.jsonHtmlVarSpan())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarSpan a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setHtmlVarForm":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "htmlVarForm")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarForm a échoué", b.cause())));
								});
							}));
						} else {
							o2.setHtmlVarForm(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "htmlVarForm", o2.jsonHtmlVarForm())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarForm a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setHtmlVarInput":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "htmlVarInput")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarInput a échoué", b.cause())));
								});
							}));
						} else {
							o2.setHtmlVarInput(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "htmlVarInput", o2.jsonHtmlVarInput())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarInput a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setHtmlVarForEach":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "htmlVarForEach")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarForEach a échoué", b.cause())));
								});
							}));
						} else {
							o2.setHtmlVarForEach(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "htmlVarForEach", o2.jsonHtmlVarForEach())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlVarForEach a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setHtmlExclure":
						if(jsonObject.getBoolean(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "htmlExclure")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlExclure a échoué", b.cause())));
								});
							}));
						} else {
							o2.setHtmlExclure(jsonObject.getBoolean(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "htmlExclure", o2.jsonHtmlExclure())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.htmlExclure a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setPdfExclure":
						if(jsonObject.getBoolean(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "pdfExclure")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.pdfExclure a échoué", b.cause())));
								});
							}));
						} else {
							o2.setPdfExclure(jsonObject.getBoolean(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "pdfExclure", o2.jsonPdfExclure())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.pdfExclure a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setConnecterDeconnecter":
						if(jsonObject.getBoolean(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "connecterDeconnecter")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.connecterDeconnecter a échoué", b.cause())));
								});
							}));
						} else {
							o2.setConnecterDeconnecter(jsonObject.getBoolean(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "connecterDeconnecter", o2.jsonConnecterDeconnecter())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.connecterDeconnecter a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setTri1":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "tri1")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri1 a échoué", b.cause())));
								});
							}));
						} else {
							o2.setTri1(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "tri1", o2.jsonTri1())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri1 a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setTri2":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "tri2")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri2 a échoué", b.cause())));
								});
							}));
						} else {
							o2.setTri2(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "tri2", o2.jsonTri2())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri2 a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setTri3":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "tri3")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri3 a échoué", b.cause())));
								});
							}));
						} else {
							o2.setTri3(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "tri3", o2.jsonTri3())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri3 a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setTri4":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "tri4")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri4 a échoué", b.cause())));
								});
							}));
						} else {
							o2.setTri4(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "tri4", o2.jsonTri4())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri4 a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setTri5":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "tri5")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri5 a échoué", b.cause())));
								});
							}));
						} else {
							o2.setTri5(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "tri5", o2.jsonTri5())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri5 a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setTri6":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "tri6")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri6 a échoué", b.cause())));
								});
							}));
						} else {
							o2.setTri6(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "tri6", o2.jsonTri6())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri6 a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setTri7":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "tri7")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri7 a échoué", b.cause())));
								});
							}));
						} else {
							o2.setTri7(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "tri7", o2.jsonTri7())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri7 a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setTri8":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "tri8")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri8 a échoué", b.cause())));
								});
							}));
						} else {
							o2.setTri8(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "tri8", o2.jsonTri8())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri8 a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setTri9":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "tri9")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri9 a échoué", b.cause())));
								});
							}));
						} else {
							o2.setTri9(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "tri9", o2.jsonTri9())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri9 a échoué", b.cause())));
								});
							}));
						}
						break;
					case "setTri10":
						if(jsonObject.getString(methodeNom) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_removeD
										, Tuple.of(pk, "tri10")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri10 a échoué", b.cause())));
								});
							}));
						} else {
							o2.setTri10(jsonObject.getString(methodeNom));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContexteFrFR.SQL_setD
										, Tuple.of(pk, "tri10", o2.jsonTri10())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("valeur PartHtml.tri10 a échoué", b.cause())));
								});
							}));
						}
						break;
				}
			}
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					PartHtml o3 = new PartHtml();
					o3.setRequeteSite_(o.getRequeteSite_());
					o3.setPk(pk);
					gestionnaireEvenements.handle(Future.succeededFuture(o3));
				} else {
					LOGGER.error(String.format("sqlPATCHPartHtml a échoué. ", a.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPATCHPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void patchPartHtmlReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PATCHPartHtml(requeteSite, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("patchPartHtmlReponse a échoué. ", a.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchPartHtmlReponse a échoué. ", ex));
			erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PATCHPartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200PATCHPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getPartHtml(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPartHtml(siteContexte, operationRequete);
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

			utilisateurPartHtml(requeteSite, b -> {
				if(b.succeeded()) {
					recherchePartHtml(requeteSite, false, true, "/api/part-html/{id}", "GET", c -> {
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
							LOGGER.error(String.format("getPartHtml a échoué. ", c.cause()));
							erreurPartHtml(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("getPartHtml a échoué. ", b.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getPartHtml a échoué. ", ex));
			erreurPartHtml(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void getPartHtmlReponse(ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
		try {
			reponse200GETPartHtml(listePartHtml, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("getPartHtmlReponse a échoué. ", a.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getPartHtmlReponse a échoué. ", ex));
			erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200GETPartHtml(ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
			SolrDocumentList documentsSolr = listePartHtml.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listePartHtml.getList().stream().findFirst().orElse(null));
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("reponse200GETPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void recherchePartHtml(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPartHtml(siteContexte, operationRequete);
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

			utilisateurPartHtml(requeteSite, b -> {
				if(b.succeeded()) {
					recherchePartHtml(requeteSite, false, true, "/api/part-html", "Recherche", c -> {
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
							LOGGER.error(String.format("recherchePartHtml a échoué. ", c.cause()));
							erreurPartHtml(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("recherchePartHtml a échoué. ", b.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("recherchePartHtml a échoué. ", ex));
			erreurPartHtml(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void recherchePartHtmlReponse(ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
		try {
			reponse200RecherchePartHtml(listePartHtml, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("recherchePartHtmlReponse a échoué. ", a.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("recherchePartHtmlReponse a échoué. ", ex));
			erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
		}
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
			LOGGER.error(String.format("reponse200RecherchePartHtml a échoué. ", e));
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
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourPartHtml(siteContexte, operationRequete);
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

			utilisateurPartHtml(requeteSite, b -> {
				if(b.succeeded()) {
					recherchePartHtml(requeteSite, false, true, "/part-html", "PageRecherche", c -> {
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
							LOGGER.error(String.format("pagerecherchePartHtml a échoué. ", c.cause()));
							erreurPartHtml(requeteSite, gestionnaireEvenements, c);
						}
					});
				} else {
					LOGGER.error(String.format("pagerecherchePartHtml a échoué. ", b.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("pagerecherchePartHtml a échoué. ", ex));
			erreurPartHtml(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void pagerecherchePartHtmlReponse(ListeRecherche<PartHtml> listePartHtml, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listePartHtml.getRequeteSite_();
		try {
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(requeteSite, buffer);
			requeteSite.setW(w);
			reponse200PageRecherchePartHtml(listePartHtml, a -> {
				if(a.succeeded()) {
					gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("pagerecherchePartHtmlReponse a échoué. ", a.cause()));
					erreurPartHtml(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("pagerecherchePartHtmlReponse a échoué. ", ex));
			erreurPartHtml(requeteSite, null, Future.failedFuture(ex));
		}
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
			LOGGER.error(String.format("reponse200PageRecherchePartHtml a échoué. ", e));
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
								sqlCommitPartHtml(requeteSite, f -> {
									if(f.succeeded()) {
										sqlFermerPartHtml(requeteSite, g -> {
											if(g.succeeded()) {
												rechargerPartHtml(partHtml, h -> {
													if(h.succeeded()) {
														gestionnaireEvenements.handle(Future.succeededFuture(partHtml));
														promise.complete(partHtml);
													} else {
														LOGGER.error(String.format("rechargerPartHtml a échoué. ", h.cause()));
														erreurPartHtml(requeteSite, null, h);
													}
												});
											} else {
												LOGGER.error(String.format("definirIndexerPartHtml a échoué. ", g.cause()));
												erreurPartHtml(requeteSite, null, g);
											}
										});
									} else {
										LOGGER.error(String.format("definirIndexerPartHtml a échoué. ", f.cause()));
										erreurPartHtml(requeteSite, null, f);
									}
								});
							} else {
								LOGGER.error(String.format("definirIndexerPartHtml a échoué. ", e.cause()));
								erreurPartHtml(requeteSite, null, e);
							}
						});
					} else {
						LOGGER.error(String.format("definirIndexerPartHtml a échoué. ", d.cause()));
						erreurPartHtml(requeteSite, null, d);
					}
				});
			} else {
				LOGGER.error(String.format("definirIndexerPartHtml a échoué. ", c.cause()));
				erreurPartHtml(requeteSite, null, c);
			}
		});
		return promise.future();
	}

	public void creerPartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<PartHtml>> gestionnaireEvenements) {
		try {
			Transaction tx = requeteSite.getTx();
			String utilisateurId = requeteSite.getUtilisateurId();
			ZonedDateTime cree = Optional.ofNullable(requeteSite.getObjetJson()).map(j -> j.getString("cree")).map(s -> ZonedDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of(requeteSite.getConfigSite_().getSiteZone())))).orElse(ZonedDateTime.now(ZoneId.of(requeteSite.getConfigSite_().getSiteZone())));

			tx.preparedQuery(
					SiteContexteFrFR.SQL_creer
					, Tuple.of(PartHtml.class.getCanonicalName(), utilisateurId, cree.toOffsetDateTime())
					, Collectors.toList()
					, creerAsync
			-> {
				if(creerAsync.succeeded()) {
					Row creerLigne = creerAsync.result().value().stream().findFirst().orElseGet(() -> null);
					Long pk = creerLigne.getLong(0);
					PartHtml o = new PartHtml();
					o.setPk(pk);
					o.setRequeteSite_(requeteSite);
					gestionnaireEvenements.handle(Future.succeededFuture(o));
				} else {
					LOGGER.error(String.format("creerPartHtml a échoué. ", creerAsync.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(creerAsync.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("creerPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void erreurPartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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
		sqlRollbackPartHtml(requeteSite, a -> {
			if(a.succeeded()) {
				LOGGER.info(String.format("sql rollback. "));
				sqlFermerPartHtml(requeteSite, b -> {
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

	public void sqlConnexionPartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
						LOGGER.error(String.format("sqlConnexionPartHtml a échoué. ", a.cause()));
						gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlTransactionPartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
			LOGGER.error(String.format("sqlTransactionPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlCommitPartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
						LOGGER.error(String.format("sqlCommitPartHtml a échoué. ", a.cause()));
						gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlRollbackPartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
						LOGGER.error(String.format("sqlRollbackPartHtml a échoué. ", a.cause()));
						gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlFermerPartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
			LOGGER.error(String.format("sqlFermerPartHtml a échoué. ", e));
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
			String utilisateurId = requeteSite.getUtilisateurId();
			if(utilisateurId == null) {
				gestionnaireEvenements.handle(Future.succeededFuture());
			} else {
				sqlConnexionPartHtml(requeteSite, a -> {
					if(a.succeeded()) {
						sqlTransactionPartHtml(requeteSite, b -> {
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
												utilisateurPartHtmlDefinir(requeteSite, jsonObject, false);

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
																	erreurPartHtml(requeteSite, gestionnaireEvenements, e);
																}
															});
														} else {
															erreurPartHtml(requeteSite, gestionnaireEvenements, d);
														}
													});
												} else {
													requeteSite.setUtilisateurSite(utilisateurSite1);
													requeteSite.setUtilisateurNom(utilisateurSite1.getUtilisateurNom());
													requeteSite.setUtilisateurPrenom(utilisateurSite1.getUtilisateurPrenom());
													requeteSite.setUtilisateurNomFamille(utilisateurSite1.getUtilisateurNomFamille());
													requeteSite.setUtilisateurId(utilisateurSite1.getUtilisateurId());
													requeteSite.setUtilisateurCle(utilisateurSite1.getPk());
													sqlRollbackPartHtml(requeteSite, c -> {
														if(c.succeeded()) {
															gestionnaireEvenements.handle(Future.succeededFuture());
														} else {
															gestionnaireEvenements.handle(Future.failedFuture(c.cause()));
															erreurPartHtml(requeteSite, gestionnaireEvenements, c);
														}
													});
												}
											}
										} catch(Exception e) {
											LOGGER.error(String.format("utilisateurPartHtml a échoué. ", e));
											gestionnaireEvenements.handle(Future.failedFuture(e));
										}
									} else {
										LOGGER.error(String.format("utilisateurPartHtml a échoué. ", selectCAsync.cause()));
										gestionnaireEvenements.handle(Future.failedFuture(selectCAsync.cause()));
									}
								});
							} else {
								LOGGER.error(String.format("utilisateurPartHtml a échoué. ", b.cause()));
								gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
							}
						});
					} else {
						LOGGER.error(String.format("utilisateurPartHtml a échoué. ", a.cause()));
						gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("utilisateurPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public Boolean utilisateurPartHtmlDefinir(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Boolean patch) {
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

	public void recherchePartHtmlQ(String uri, String apiMethode, ListeRecherche<PartHtml> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		listeRecherche.setQuery(varIndexe + ":" + ("*".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));
		if(!"*".equals(entiteVar)) {
			listeRecherche.setHighlight(true);
			listeRecherche.setHighlightSnippets(3);
			listeRecherche.addHighlightField(varIndexe);
			listeRecherche.setParam("hl.encoder", "html");
		}
	}

	public void recherchePartHtmlFq(String uri, String apiMethode, ListeRecherche<PartHtml> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
	}

	public void recherchePartHtmlSort(String uri, String apiMethode, ListeRecherche<PartHtml> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurIndexe));
	}

	public void recherchePartHtmlRows(String uri, String apiMethode, ListeRecherche<PartHtml> listeRecherche, Integer valeurRows) {
			listeRecherche.setRows(apiMethode != null && apiMethode.contains("Recherche") ? valeurRows : 10);
	}

	public void recherchePartHtmlStart(String uri, String apiMethode, ListeRecherche<PartHtml> listeRecherche, Integer valeurStart) {
		listeRecherche.setStart(valeurStart);
	}

	public void recherchePartHtmlVar(String uri, String apiMethode, ListeRecherche<PartHtml> listeRecherche, String var, String valeur) {
		listeRecherche.getRequeteSite_().getRequeteVars().put(var, valeur);
	}

	public void recherchePartHtmlUri(String uri, String apiMethode, ListeRecherche<PartHtml> listeRecherche) {
	}

	public void varsPartHtml(RequeteSiteFrFR requeteSite, Handler<AsyncResult<ListeRecherche<OperationResponse>>> gestionnaireEvenements) {
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
					LOGGER.error(String.format("recherchePartHtml a échoué. ", e));
					gestionnaireEvenements.handle(Future.failedFuture(e));
				}
			});
			gestionnaireEvenements.handle(Future.succeededFuture());
		} catch(Exception e) {
			LOGGER.error(String.format("recherchePartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void recherchePartHtml(RequeteSiteFrFR requeteSite, Boolean peupler, Boolean stocker, String uri, String apiMethode, Handler<AsyncResult<ListeRecherche<PartHtml>>> gestionnaireEvenements) {
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
								recherchePartHtmlQ(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "fq":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								varIndexe = PartHtml.varIndexePartHtml(entiteVar);
								recherchePartHtmlFq(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "sort":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
								valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
								varIndexe = PartHtml.varIndexePartHtml(entiteVar);
								recherchePartHtmlSort(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "start":
								valeurStart = (Integer)paramObjet;
								recherchePartHtmlStart(uri, apiMethode, listeRecherche, valeurStart);
								break;
							case "rows":
								valeurRows = (Integer)paramObjet;
								recherchePartHtmlRows(uri, apiMethode, listeRecherche, valeurRows);
								break;
							case "var":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								recherchePartHtmlVar(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe);
								break;
						}
					}
					recherchePartHtmlUri(uri, apiMethode, listeRecherche);
				} catch(Exception e) {
					LOGGER.error(String.format("recherchePartHtml a échoué. ", e));
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
			LOGGER.error(String.format("recherchePartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void definirPartHtml(PartHtml o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
						LOGGER.error(String.format("definirPartHtml a échoué. ", e));
						gestionnaireEvenements.handle(Future.failedFuture(e));
					}
				} else {
					LOGGER.error(String.format("definirPartHtml a échoué. ", definirAsync.cause()));
					gestionnaireEvenements.handle(Future.failedFuture(definirAsync.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("definirPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void attribuerPartHtml(PartHtml o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
						LOGGER.error(String.format("attribuerPartHtml a échoué. ", attribuerAsync.cause()));
						gestionnaireEvenements.handle(Future.failedFuture(attribuerAsync.cause()));
					}
				} catch(Exception e) {
					LOGGER.error(String.format("attribuerPartHtml a échoué. ", e));
					gestionnaireEvenements.handle(Future.failedFuture(e));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("attribuerPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void indexerPartHtml(PartHtml o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
			o.initLoinPourClasse(requeteSite);
			o.indexerPourClasse();
			gestionnaireEvenements.handle(Future.succeededFuture());
		} catch(Exception e) {
			LOGGER.error(String.format("indexerPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void rechargerPartHtml(PartHtml o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Boolean recharger = !"false".equals(requeteSite.getRequeteVars().get("recharger"));
			if(recharger && BooleanUtils.isFalse(Optional.ofNullable(requeteSite.getRequeteApi_()).map(RequeteApi::getEmpty).orElse(true))) {
				ListeRecherche<PartHtml> listeRecherche = new ListeRecherche<PartHtml>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(PartHtml.class);
				listeRecherche.addFilterQuery("modifie_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(requeteSite.getRequeteApi_().getCree().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				listeRecherche.add("json.facet", "{designPageCles:{terms:{field:designPageCles_indexed_longs, limit:1000}}}");
				listeRecherche.setRows(1000);
				listeRecherche.initLoinListeRecherche(requeteSite);
				List<Future> futures = new ArrayList<>();

				for(int i=0; i < pks.size(); i++) {
					Long pk2 = pks.get(i);
					String classeNomSimple2 = classes.get(i);

					if("DesignPage".equals(classeNomSimple2) && pk2 != null) {
						ListeRecherche<DesignPage> listeRecherche2 = new ListeRecherche<DesignPage>();
						listeRecherche2.setStocker(true);
						listeRecherche2.setQuery("*:*");
						listeRecherche2.setC(DesignPage.class);
						listeRecherche2.addFilterQuery("pk_indexed_long:" + pk2);
						listeRecherche2.setRows(1);
						listeRecherche2.initLoinListeRecherche(requeteSite);
						DesignPage o2 = listeRecherche2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							DesignPageFrFRGenApiServiceImpl service = new DesignPageFrFRGenApiServiceImpl(requeteSite.getSiteContexte_());
							RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourPartHtml(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
							RequeteApi requeteApi2 = new RequeteApi();
							requeteApi2.setRows(1);
							requeteApi2.setNumFound(1l);
							requeteApi2.setNumPATCH(0L);
							requeteApi2.initLoinRequeteApi(requeteSite2);
							requeteSite2.setRequeteApi_(requeteApi2);
							requeteSite2.getVertx().eventBus().publish("websocketDesignPage", JsonObject.mapFrom(requeteApi2).toString());

							o2.setPk(pk2);
							o2.setRequeteSite_(requeteSite2);
							futures.add(
								service.patchDesignPageFuture(o2, false, a -> {
									if(a.succeeded()) {
									} else {
										LOGGER.info(String.format("DesignPage %s a échoué. ", pk2));
										gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						PartHtmlFrFRApiServiceImpl service = new PartHtmlFrFRApiServiceImpl(requeteSite.getSiteContexte_());
						List<Future> futures2 = new ArrayList<>();
						for(PartHtml o2 : listeRecherche.getList()) {
							RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourPartHtml(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
							o2.setRequeteSite_(requeteSite2);
							futures2.add(
								service.patchPartHtmlFuture(o2, false, b -> {
									if(b.succeeded()) {
									} else {
										LOGGER.info(String.format("PartHtml %s a échoué. ", o2.getPk()));
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
								erreurPartHtml(requeteSite, gestionnaireEvenements, b);
							}
						});
					} else {
						LOGGER.error("Recharger relations a échoué. ", a.cause());
						erreurPartHtml(requeteSite, gestionnaireEvenements, a);
					}
				});
			} else {
				gestionnaireEvenements.handle(Future.succeededFuture());
			}
		} catch(Exception e) {
			LOGGER.error(String.format("rechargerPartHtml a échoué. ", e));
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}
}
