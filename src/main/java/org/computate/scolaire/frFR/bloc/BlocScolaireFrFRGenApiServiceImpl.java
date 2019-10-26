package org.computate.scolaire.frFR.bloc;

import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;
import org.computate.scolaire.frFR.recherche.ResultatRecherche;
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
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;


/**
 * Traduire: false
 **/
public class BlocScolaireFrFRGenApiServiceImpl implements BlocScolaireFrFRGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(BlocScolaireFrFRGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "BlocScolaireFrFRApiServiceImpl";

	protected SiteContexteFrFR siteContexte;

	public BlocScolaireFrFRGenApiServiceImpl(SiteContexteFrFR siteContexte) {
		this.siteContexte = siteContexte;
		BlocScolaireFrFRGenApiService service = BlocScolaireFrFRGenApiService.creerProxy(siteContexte.getVertx(), SERVICE_ADDRESS);
	}

	// POST //

	@Override
	public void postBlocScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourBlocScolaire(siteContexte, operationRequete, body);
			sqlBlocScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					creerPOSTBlocScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							BlocScolaire blocScolaire = b.result();
							sqlPOSTBlocScolaire(blocScolaire, c -> {
								if(c.succeeded()) {
									definirBlocScolaire(blocScolaire, d -> {
										if(d.succeeded()) {
											attribuerBlocScolaire(blocScolaire, e -> {
												if(e.succeeded()) {
													indexerBlocScolaire(blocScolaire, f -> {
														if(f.succeeded()) {
															reponse200POSTBlocScolaire(blocScolaire, g -> {
																if(f.succeeded()) {
																	SQLConnection connexionSql = requeteSite.getConnexionSql();
																	connexionSql.commit(h -> {
																		if(a.succeeded()) {
																			connexionSql.close(i -> {
																				if(a.succeeded()) {
																					gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																				} else {
																					erreurBlocScolaire(requeteSite, gestionnaireEvenements, i);
																				}
																			});
																		} else {
																			erreurBlocScolaire(requeteSite, gestionnaireEvenements, h);
																		}
																	});
																} else {
																	erreurBlocScolaire(requeteSite, gestionnaireEvenements, g);
																}
															});
														} else {
															erreurBlocScolaire(requeteSite, gestionnaireEvenements, f);
														}
													});
												} else {
													erreurBlocScolaire(requeteSite, gestionnaireEvenements, e);
												}
											});
										} else {
											erreurBlocScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurBlocScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurBlocScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurBlocScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurBlocScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void creerPOSTBlocScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<BlocScolaire>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_creer
					, new JsonArray(Arrays.asList(BlocScolaire.class.getCanonicalName(), utilisateurId))
					, creerAsync
			-> {
				JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = creerLigne.getLong(0);
				BlocScolaire o = new BlocScolaire();
				o.setPk(pk);
				o.setRequeteSite_(requeteSite);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlPOSTBlocScolaire(BlocScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
							postSqlParams.addAll(Arrays.asList("blocCles", l, "inscriptionCles", pk));
						}
						break;
					case "blocHeureDebut":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blocHeureDebut", jsonObject.getString(entiteVar), pk));
						break;
					case "blocHeureFin":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blocHeureFin", jsonObject.getString(entiteVar), pk));
						break;
					case "blocPrixParMois":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blocPrixParMois", jsonObject.getString(entiteVar), pk));
						break;
					case "blocDimanche":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blocDimanche", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "blocLundi":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blocLundi", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "blocMardi":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blocMardi", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "blocMercredi":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blocMercredi", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "blocJeudi":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blocJeudi", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "blocVendredi":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blocVendredi", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "blocSamedi":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blocSamedi", jsonObject.getBoolean(entiteVar), pk));
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

	public void reponse200POSTBlocScolaire(BlocScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			JsonObject json = JsonObject.mapFrom(o);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchBlocScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourBlocScolaire(siteContexte, operationRequete, body);
			sqlBlocScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurBlocScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheBlocScolaire(requeteSite, false, true, null, c -> {
								if(c.succeeded()) {
									ListeRecherche<BlocScolaire> listeBlocScolaire = c.result();
									SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeBlocScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
									Date date = null;
									if(facets != null)
										date = (Date)facets.get("max_modifie");
									String dt;
									if(date == null)
										dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
									else
										dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
									listeBlocScolaire.addFilterQuery(String.format("modifie_indexed_date:[* TO %s]", dt));
									listePATCHBlocScolaire(listeBlocScolaire, dt, d -> {
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
																erreurBlocScolaire(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											erreurBlocScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurBlocScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurBlocScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurBlocScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurBlocScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void listePATCHBlocScolaire(ListeRecherche<BlocScolaire> listeBlocScolaire, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeBlocScolaire.getRequeteSite_();
		listeBlocScolaire.getList().forEach(o -> {
			futures.add(
				futurePATCHBlocScolaire(o, a -> {
					if(a.succeeded()) {
					} else {
						erreurBlocScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listeBlocScolaire.next(dt)) {
					listePATCHBlocScolaire(listeBlocScolaire, dt, gestionnaireEvenements);
				} else {
					reponse200PATCHBlocScolaire(listeBlocScolaire, gestionnaireEvenements);
				}
			} else {
				erreurBlocScolaire(listeBlocScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<BlocScolaire> futurePATCHBlocScolaire(BlocScolaire o,  Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		Future<BlocScolaire> future = Future.future();
		try {
			sqlPATCHBlocScolaire(o, a -> {
				if(a.succeeded()) {
					BlocScolaire blocScolaire = a.result();
					definirBlocScolaire(blocScolaire, b -> {
						if(b.succeeded()) {
							attribuerBlocScolaire(blocScolaire, c -> {
								if(c.succeeded()) {
									indexerBlocScolaire(blocScolaire, d -> {
										if(d.succeeded()) {
											future.complete(o);
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
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public void sqlPATCHBlocScolaire(BlocScolaire o, Handler<AsyncResult<BlocScolaire>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject requeteJson = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = requeteJson.fieldNames();
			BlocScolaire o2 = new BlocScolaire();

			patchSql.append(SiteContexteFrFR.SQL_modifier);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.frFR.bloc.BlocScolaire"));
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
						patchSqlParams.addAll(Arrays.asList("blocCles", Long.parseLong(requeteJson.getString(methodeNom)), "inscriptionCles", pk));
						break;
					case "addAllInscriptionCles":
						JsonArray addAllInscriptionClesValeurs = requeteJson.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllInscriptionClesValeurs.size(); i++) {
							patchSql.append(SiteContexteFrFR.SQL_setA2);
							patchSqlParams.addAll(Arrays.asList("blocCles", addAllInscriptionClesValeurs.getString(i), "inscriptionCles", pk));
						}
						break;
					case "setInscriptionCles":
						JsonArray setInscriptionClesValeurs = requeteJson.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("blocCles", Long.parseLong(requeteJson.getString(methodeNom)), "inscriptionCles", pk));
						for(Integer i = 0; i <  setInscriptionClesValeurs.size(); i++) {
							patchSql.append(SiteContexteFrFR.SQL_setA2);
							patchSqlParams.addAll(Arrays.asList("blocCles", setInscriptionClesValeurs.getString(i), "inscriptionCles", pk));
						}
						break;
					case "removeInscriptionCles":
						patchSql.append(SiteContexteFrFR.SQL_removeA);
						patchSqlParams.addAll(Arrays.asList("blocCles", Long.parseLong(requeteJson.getString(methodeNom)), "inscriptionCles", pk));
						break;
					case "setBlocHeureDebut":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blocHeureDebut"));
						} else {
							o2.setBlocHeureDebut(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blocHeureDebut", o2.jsonBlocHeureDebut(), pk));
						}
						break;
					case "setBlocHeureFin":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blocHeureFin"));
						} else {
							o2.setBlocHeureFin(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blocHeureFin", o2.jsonBlocHeureFin(), pk));
						}
						break;
					case "setBlocPrixParMois":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blocPrixParMois"));
						} else {
							o2.setBlocPrixParMois(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blocPrixParMois", o2.jsonBlocPrixParMois(), pk));
						}
						break;
					case "setBlocDimanche":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blocDimanche"));
						} else {
							o2.setBlocDimanche(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blocDimanche", o2.jsonBlocDimanche(), pk));
						}
						break;
					case "setBlocLundi":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blocLundi"));
						} else {
							o2.setBlocLundi(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blocLundi", o2.jsonBlocLundi(), pk));
						}
						break;
					case "setBlocMardi":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blocMardi"));
						} else {
							o2.setBlocMardi(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blocMardi", o2.jsonBlocMardi(), pk));
						}
						break;
					case "setBlocMercredi":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blocMercredi"));
						} else {
							o2.setBlocMercredi(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blocMercredi", o2.jsonBlocMercredi(), pk));
						}
						break;
					case "setBlocJeudi":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blocJeudi"));
						} else {
							o2.setBlocJeudi(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blocJeudi", o2.jsonBlocJeudi(), pk));
						}
						break;
					case "setBlocVendredi":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blocVendredi"));
						} else {
							o2.setBlocVendredi(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blocVendredi", o2.jsonBlocVendredi(), pk));
						}
						break;
					case "setBlocSamedi":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blocSamedi"));
						} else {
							o2.setBlocSamedi(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blocSamedi", o2.jsonBlocSamedi(), pk));
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
					BlocScolaire o3 = new BlocScolaire();
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

	public void reponse200PATCHBlocScolaire(ListeRecherche<BlocScolaire> listeBlocScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeBlocScolaire.getRequeteSite_();
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getBlocScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourBlocScolaire(siteContexte, operationRequete);
			rechercheBlocScolaire(requeteSite, false, true, null, a -> {
				if(a.succeeded()) {
					ListeRecherche<BlocScolaire> listeBlocScolaire = a.result();
					reponse200GETBlocScolaire(listeBlocScolaire, b -> {
						if(b.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(b.result()));
						} else {
							erreurBlocScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurBlocScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurBlocScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200GETBlocScolaire(ListeRecherche<BlocScolaire> listeBlocScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeBlocScolaire.getRequeteSite_();
			SolrDocumentList documentsSolr = listeBlocScolaire.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listeBlocScolaire.get(0));
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteBlocScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourBlocScolaire(siteContexte, operationRequete);
			sqlBlocScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					rechercheBlocScolaire(requeteSite, false, true, null, b -> {
						if(b.succeeded()) {
							ListeRecherche<BlocScolaire> listeBlocScolaire = b.result();
							supprimerDELETEBlocScolaire(requeteSite, c -> {
								if(c.succeeded()) {
									reponse200DELETEBlocScolaire(requeteSite, d -> {
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
																erreurBlocScolaire(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											erreurBlocScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurBlocScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurBlocScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurBlocScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurBlocScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void supprimerDELETEBlocScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			Long pk = requeteSite.getRequetePk();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_supprimer
					, new JsonArray(Arrays.asList(pk, BlocScolaire.class.getCanonicalName(), pk, pk, pk, pk))
					, supprimerAsync
			-> {
				gestionnaireEvenements.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200DELETEBlocScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void rechercheBlocScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourBlocScolaire(siteContexte, operationRequete);
			rechercheBlocScolaire(requeteSite, false, true, null, a -> {
				if(a.succeeded()) {
					ListeRecherche<BlocScolaire> listeBlocScolaire = a.result();
					reponse200RechercheBlocScolaire(listeBlocScolaire, b -> {
						if(b.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(b.result()));
						} else {
							erreurBlocScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurBlocScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurBlocScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200RechercheBlocScolaire(ListeRecherche<BlocScolaire> listeBlocScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeBlocScolaire.getRequeteSite_();
			QueryResponse reponseRecherche = listeBlocScolaire.getQueryResponse();
			SolrDocumentList documentsSolr = listeBlocScolaire.getSolrDocumentList();
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
			listeBlocScolaire.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listeBlocScolaire.getFields();
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
	public void pagerechercheBlocScolaireId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		pagerechercheBlocScolaire(operationRequete, gestionnaireEvenements);
	}

	@Override
	public void pagerechercheBlocScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourBlocScolaire(siteContexte, operationRequete);
			sqlBlocScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurBlocScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheBlocScolaire(requeteSite, false, true, "/bloc", c -> {
								if(c.succeeded()) {
									ListeRecherche<BlocScolaire> listeBlocScolaire = c.result();
									reponse200PageRechercheBlocScolaire(listeBlocScolaire, d -> {
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
																erreurBlocScolaire(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											erreurBlocScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurBlocScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurBlocScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurBlocScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurBlocScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200PageRechercheBlocScolaire(ListeRecherche<BlocScolaire> listeBlocScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeBlocScolaire.getRequeteSite_();
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(listeBlocScolaire.getRequeteSite_(), buffer);
			BlocPage page = new BlocPage();
			SolrDocument pageDocumentSolr = new SolrDocument();

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/bloc");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			page.setListeBlocScolaire(listeBlocScolaire);
			page.initLoinBlocPage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, new CaseInsensitiveHeaders())));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public String varIndexeBlocScolaire(String entiteVar) {
		switch(entiteVar) {
			case "pk":
				return "pk_indexed_long";
			case "id":
				return "id_indexed_string";
			case "cree":
				return "cree_indexed_date";
			case "modifie":
				return "modifie_indexed_date";
			case "archive":
				return "archive_indexed_boolean";
			case "supprime":
				return "supprime_indexed_boolean";
			case "classeNomCanonique":
				return "classeNomCanonique_indexed_string";
			case "classeNomSimple":
				return "classeNomSimple_indexed_string";
			case "classeNomsCanoniques":
				return "classeNomsCanoniques_indexed_strings";
			case "objetTitre":
				return "objetTitre_indexed_string";
			case "objetId":
				return "objetId_indexed_string";
			case "objetSuggere":
				return "objetSuggere_indexed_string";
			case "pageUrl":
				return "pageUrl_indexed_string";
			case "blocCle":
				return "blocCle_indexed_long";
			case "enfantCle":
				return "enfantCle_indexed_long";
			case "inscriptionCles":
				return "inscriptionCles_indexed_longs";
			case "scolaireTri":
				return "scolaireTri_indexed_int";
			case "ecoleTri":
				return "ecoleTri_indexed_int";
			case "anneeTri":
				return "anneeTri_indexed_int";
			case "saisonTri":
				return "saisonTri_indexed_int";
			case "sessionTri":
				return "sessionTri_indexed_int";
			case "ageTri":
				return "ageTri_indexed_int";
			case "ecoleCle":
				return "ecoleCle_indexed_long";
			case "anneeCle":
				return "anneeCle_indexed_long";
			case "saisonCle":
				return "saisonCle_indexed_long";
			case "sessionCle":
				return "sessionCle_indexed_long";
			case "ageCle":
				return "ageCle_indexed_long";
			case "ecoleNomComplet":
				return "ecoleNomComplet_indexed_string";
			case "ecoleEmplacement":
				return "ecoleEmplacement_indexed_string";
			case "anneeDebut":
				return "anneeDebut_indexed_int";
			case "anneeFin":
				return "anneeFin_indexed_int";
			case "saisonJourDebut":
				return "saisonJourDebut_indexed_date";
			case "saisonEte":
				return "saisonEte_indexed_boolean";
			case "saisonHiver":
				return "saisonHiver_indexed_boolean";
			case "saisonFraisInscription":
				return "saisonFraisInscription_indexed_double";
			case "saisonNomComplet":
				return "saisonNomComplet_indexed_string";
			case "sessionJourDebut":
				return "sessionJourDebut_indexed_date";
			case "sessionJourFin":
				return "sessionJourFin_indexed_date";
			case "ageNomComplet":
				return "ageNomComplet_indexed_string";
			case "ageDebut":
				return "ageDebut_indexed_int";
			case "ageFin":
				return "ageFin_indexed_int";
			case "blocHeureDebut":
				return "blocHeureDebut_indexed_string";
			case "blocHeureFin":
				return "blocHeureFin_indexed_string";
			case "blocPrixParMois":
				return "blocPrixParMois_indexed_double";
			case "blocDimanche":
				return "blocDimanche_indexed_boolean";
			case "blocLundi":
				return "blocLundi_indexed_boolean";
			case "blocMardi":
				return "blocMardi_indexed_boolean";
			case "blocMercredi":
				return "blocMercredi_indexed_boolean";
			case "blocJeudi":
				return "blocJeudi_indexed_boolean";
			case "blocVendredi":
				return "blocVendredi_indexed_boolean";
			case "blocSamedi":
				return "blocSamedi_indexed_boolean";
			case "blocPrixTotal":
				return "blocPrixTotal_indexed_double";
			case "blocNomComplet":
				return "blocNomComplet_indexed_string";
			default:
				throw new RuntimeException(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	public String varRechercheBlocScolaire(String entiteVar) {
		switch(entiteVar) {
			case "objetSuggere":
				return "objetSuggere_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	public String varSuggereBlocScolaire(String entiteVar) {
		switch(entiteVar) {
			case "objetSuggere":
				return "objetSuggere_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	// Partagé //

	public void erreurBlocScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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

	public void sqlBlocScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public RequeteSiteFrFR genererRequeteSiteFrFRPourBlocScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete) {
		return genererRequeteSiteFrFRPourBlocScolaire(siteContexte, operationRequete, null);
	}

	public RequeteSiteFrFR genererRequeteSiteFrFRPourBlocScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete, JsonObject body) {
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

	public void utilisateurBlocScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void rechercheBlocScolaire(RequeteSiteFrFR requeteSite, Boolean peupler, Boolean stocker, String classeApiUriMethode, Handler<AsyncResult<ListeRecherche<BlocScolaire>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<BlocScolaire> listeRecherche = new ListeRecherche<BlocScolaire>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(BlocScolaire.class);
			if(entiteListe != null)
				listeRecherche.addFields(entiteListe);
			listeRecherche.addSort("cree_indexed_date", ORDER.desc);
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
								varIndexe = "*".equals(entiteVar) ? entiteVar : varRechercheBlocScolaire(entiteVar);
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
								varIndexe = varIndexeBlocScolaire(entiteVar);
								listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
								break;
							case "sort":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
								valeurTri = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
								varIndexe = varIndexeBlocScolaire(entiteVar);
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
			listeRecherche.initLoinPourClasse(requeteSite);
			gestionnaireEvenements.handle(Future.succeededFuture(listeRecherche));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void definirBlocScolaire(BlocScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
							o.definirPourClasse(definition.getString(0), definition.getString(1));
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

	public void attribuerBlocScolaire(BlocScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void indexerBlocScolaire(BlocScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
