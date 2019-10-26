package org.computate.scolaire.frFR.mere;

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
public class MereScolaireFrFRGenApiServiceImpl implements MereScolaireFrFRGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(MereScolaireFrFRGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "MereScolaireFrFRApiServiceImpl";

	protected SiteContexteFrFR siteContexte;

	public MereScolaireFrFRGenApiServiceImpl(SiteContexteFrFR siteContexte) {
		this.siteContexte = siteContexte;
		MereScolaireFrFRGenApiService service = MereScolaireFrFRGenApiService.creerProxy(siteContexte.getVertx(), SERVICE_ADDRESS);
	}

	// POST //

	@Override
	public void postMereScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourMereScolaire(siteContexte, operationRequete, body);
			sqlMereScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					creerPOSTMereScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							MereScolaire mereScolaire = b.result();
							sqlPOSTMereScolaire(mereScolaire, c -> {
								if(c.succeeded()) {
									definirMereScolaire(mereScolaire, d -> {
										if(d.succeeded()) {
											attribuerMereScolaire(mereScolaire, e -> {
												if(e.succeeded()) {
													indexerMereScolaire(mereScolaire, f -> {
														if(f.succeeded()) {
															reponse200POSTMereScolaire(mereScolaire, g -> {
																if(f.succeeded()) {
																	SQLConnection connexionSql = requeteSite.getConnexionSql();
																	connexionSql.commit(h -> {
																		if(a.succeeded()) {
																			connexionSql.close(i -> {
																				if(a.succeeded()) {
																					gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																				} else {
																					erreurMereScolaire(requeteSite, gestionnaireEvenements, i);
																				}
																			});
																		} else {
																			erreurMereScolaire(requeteSite, gestionnaireEvenements, h);
																		}
																	});
																} else {
																	erreurMereScolaire(requeteSite, gestionnaireEvenements, g);
																}
															});
														} else {
															erreurMereScolaire(requeteSite, gestionnaireEvenements, f);
														}
													});
												} else {
													erreurMereScolaire(requeteSite, gestionnaireEvenements, e);
												}
											});
										} else {
											erreurMereScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurMereScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurMereScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurMereScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurMereScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void creerPOSTMereScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<MereScolaire>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_creer
					, new JsonArray(Arrays.asList(MereScolaire.class.getCanonicalName(), utilisateurId))
					, creerAsync
			-> {
				JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = creerLigne.getLong(0);
				MereScolaire o = new MereScolaire();
				o.setPk(pk);
				o.setRequeteSite_(requeteSite);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlPOSTMereScolaire(MereScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
							postSqlParams.addAll(Arrays.asList("inscriptionCles", pk, "mereCles", l));
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
					case "personneOccupation":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personneOccupation", jsonObject.getString(entiteVar), pk));
						break;
					case "personneNumeroTelephone":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personneNumeroTelephone", jsonObject.getString(entiteVar), pk));
						break;
					case "personneMail":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personneMail", jsonObject.getString(entiteVar), pk));
						break;
					case "personneSms":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personneSms", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "personneRecevoirMail":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personneRecevoirMail", jsonObject.getBoolean(entiteVar), pk));
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

	public void reponse200POSTMereScolaire(MereScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
	public void patchMereScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourMereScolaire(siteContexte, operationRequete, body);
			sqlMereScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurMereScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheMereScolaire(requeteSite, false, true, null, c -> {
								if(c.succeeded()) {
									ListeRecherche<MereScolaire> listeMereScolaire = c.result();
									SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeMereScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
									Date date = null;
									if(facets != null)
										date = (Date)facets.get("max_modifie");
									String dt;
									if(date == null)
										dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
									else
										dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
									listeMereScolaire.addFilterQuery(String.format("modifie_indexed_date:[* TO %s]", dt));
									listePATCHMereScolaire(listeMereScolaire, dt, d -> {
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
																erreurMereScolaire(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											erreurMereScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurMereScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurMereScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurMereScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurMereScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void listePATCHMereScolaire(ListeRecherche<MereScolaire> listeMereScolaire, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeMereScolaire.getRequeteSite_();
		listeMereScolaire.getList().forEach(o -> {
			futures.add(
				futurePATCHMereScolaire(o, a -> {
					if(a.succeeded()) {
					} else {
						erreurMereScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listeMereScolaire.next(dt)) {
					listePATCHMereScolaire(listeMereScolaire, dt, gestionnaireEvenements);
				} else {
					reponse200PATCHMereScolaire(listeMereScolaire, gestionnaireEvenements);
				}
			} else {
				erreurMereScolaire(listeMereScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<MereScolaire> futurePATCHMereScolaire(MereScolaire o,  Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		Future<MereScolaire> future = Future.future();
		try {
			sqlPATCHMereScolaire(o, a -> {
				if(a.succeeded()) {
					MereScolaire mereScolaire = a.result();
					definirMereScolaire(mereScolaire, b -> {
						if(b.succeeded()) {
							attribuerMereScolaire(mereScolaire, c -> {
								if(c.succeeded()) {
									indexerMereScolaire(mereScolaire, d -> {
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

	public void sqlPATCHMereScolaire(MereScolaire o, Handler<AsyncResult<MereScolaire>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject requeteJson = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = requeteJson.fieldNames();
			MereScolaire o2 = new MereScolaire();

			patchSql.append(SiteContexteFrFR.SQL_modifier);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.frFR.mere.MereScolaire"));
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
						patchSqlParams.addAll(Arrays.asList("inscriptionCles", pk, "mereCles", Long.parseLong(requeteJson.getString(methodeNom))));
						break;
					case "addAllInscriptionCles":
						JsonArray addAllInscriptionClesValeurs = requeteJson.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllInscriptionClesValeurs.size(); i++) {
							patchSql.append(SiteContexteFrFR.SQL_addA);
							patchSqlParams.addAll(Arrays.asList("inscriptionCles", pk, "mereCles", addAllInscriptionClesValeurs.getString(i)));
						}
						break;
					case "setInscriptionCles":
						JsonArray setInscriptionClesValeurs = requeteJson.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("inscriptionCles", pk, "mereCles"));
						for(Integer i = 0; i <  setInscriptionClesValeurs.size(); i++) {
							patchSql.append(SiteContexteFrFR.SQL_addA);
							patchSqlParams.addAll(Arrays.asList("inscriptionCles", pk, "mereCles", setInscriptionClesValeurs.getString(i)));
						}
						break;
					case "removeInscriptionCles":
						patchSql.append(SiteContexteFrFR.SQL_removeA);
						patchSqlParams.addAll(Arrays.asList("inscriptionCles", pk, "mereCles", Long.parseLong(requeteJson.getString(methodeNom))));
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
					case "setPersonneOccupation":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personneOccupation"));
						} else {
							o2.setPersonneOccupation(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personneOccupation", o2.jsonPersonneOccupation(), pk));
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
					case "setPersonneMail":
						if(requeteJson.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personneMail"));
						} else {
							o2.setPersonneMail(requeteJson.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personneMail", o2.jsonPersonneMail(), pk));
						}
						break;
					case "setPersonneSms":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personneSms"));
						} else {
							o2.setPersonneSms(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personneSms", o2.jsonPersonneSms(), pk));
						}
						break;
					case "setPersonneRecevoirMail":
						if(requeteJson.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personneRecevoirMail"));
						} else {
							o2.setPersonneRecevoirMail(requeteJson.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personneRecevoirMail", o2.jsonPersonneRecevoirMail(), pk));
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
				}
			}
			connexionSql.queryWithParams(
					patchSql.toString()
					, new JsonArray(patchSqlParams)
					, patchAsync
			-> {
				if(patchAsync.succeeded()) {
					MereScolaire o3 = new MereScolaire();
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

	public void reponse200PATCHMereScolaire(ListeRecherche<MereScolaire> listeMereScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeMereScolaire.getRequeteSite_();
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getMereScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourMereScolaire(siteContexte, operationRequete);
			rechercheMereScolaire(requeteSite, false, true, null, a -> {
				if(a.succeeded()) {
					ListeRecherche<MereScolaire> listeMereScolaire = a.result();
					reponse200GETMereScolaire(listeMereScolaire, b -> {
						if(b.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(b.result()));
						} else {
							erreurMereScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurMereScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurMereScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200GETMereScolaire(ListeRecherche<MereScolaire> listeMereScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeMereScolaire.getRequeteSite_();
			SolrDocumentList documentsSolr = listeMereScolaire.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listeMereScolaire.get(0));
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteMereScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourMereScolaire(siteContexte, operationRequete);
			sqlMereScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					rechercheMereScolaire(requeteSite, false, true, null, b -> {
						if(b.succeeded()) {
							ListeRecherche<MereScolaire> listeMereScolaire = b.result();
							supprimerDELETEMereScolaire(requeteSite, c -> {
								if(c.succeeded()) {
									reponse200DELETEMereScolaire(requeteSite, d -> {
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
																erreurMereScolaire(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											erreurMereScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurMereScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurMereScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurMereScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurMereScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void supprimerDELETEMereScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			Long pk = requeteSite.getRequetePk();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_supprimer
					, new JsonArray(Arrays.asList(pk, MereScolaire.class.getCanonicalName(), pk, pk, pk, pk))
					, supprimerAsync
			-> {
				gestionnaireEvenements.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200DELETEMereScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void rechercheMereScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourMereScolaire(siteContexte, operationRequete);
			rechercheMereScolaire(requeteSite, false, true, null, a -> {
				if(a.succeeded()) {
					ListeRecherche<MereScolaire> listeMereScolaire = a.result();
					reponse200RechercheMereScolaire(listeMereScolaire, b -> {
						if(b.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(b.result()));
						} else {
							erreurMereScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurMereScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurMereScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200RechercheMereScolaire(ListeRecherche<MereScolaire> listeMereScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeMereScolaire.getRequeteSite_();
			QueryResponse reponseRecherche = listeMereScolaire.getQueryResponse();
			SolrDocumentList documentsSolr = listeMereScolaire.getSolrDocumentList();
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
			listeMereScolaire.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listeMereScolaire.getFields();
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
	public void pagerechercheMereScolaireId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		pagerechercheMereScolaire(operationRequete, gestionnaireEvenements);
	}

	@Override
	public void pagerechercheMereScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourMereScolaire(siteContexte, operationRequete);
			sqlMereScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurMereScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheMereScolaire(requeteSite, false, true, "/mere", c -> {
								if(c.succeeded()) {
									ListeRecherche<MereScolaire> listeMereScolaire = c.result();
									reponse200PageRechercheMereScolaire(listeMereScolaire, d -> {
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
																erreurMereScolaire(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											erreurMereScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									erreurMereScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurMereScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurMereScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurMereScolaire(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200PageRechercheMereScolaire(ListeRecherche<MereScolaire> listeMereScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeMereScolaire.getRequeteSite_();
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(listeMereScolaire.getRequeteSite_(), buffer);
			MerePage page = new MerePage();
			SolrDocument pageDocumentSolr = new SolrDocument();

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/mere");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			page.setListeMereScolaire(listeMereScolaire);
			page.initLoinMerePage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, new CaseInsensitiveHeaders())));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public String varIndexeMereScolaire(String entiteVar) {
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
			case "mereCle":
				return "mereCle_indexed_long";
			case "inscriptionCles":
				return "inscriptionCles_indexed_longs";
			case "familleTri":
				return "familleTri_indexed_int";
			case "mereTri":
				return "mereTri_indexed_int";
			case "ecoleCles":
				return "ecoleCles_indexed_longs";
			case "anneeCles":
				return "anneeCles_indexed_longs";
			case "saisonCles":
				return "saisonCles_indexed_longs";
			case "sessionCles":
				return "sessionCles_indexed_longs";
			case "ageCles":
				return "ageCles_indexed_longs";
			case "personnePrenom":
				return "personnePrenom_indexed_string";
			case "personnePrenomPrefere":
				return "personnePrenomPrefere_indexed_string";
			case "familleNom":
				return "familleNom_indexed_string";
			case "personneNomComplet":
				return "personneNomComplet_indexed_string";
			case "personneNomCompletPrefere":
				return "personneNomCompletPrefere_indexed_string";
			case "personneNomFormel":
				return "personneNomFormel_indexed_string";
			case "personneOccupation":
				return "personneOccupation_indexed_string";
			case "personneNumeroTelephone":
				return "personneNumeroTelephone_indexed_string";
			case "personneMail":
				return "personneMail_indexed_string";
			case "personneRelation":
				return "personneRelation_indexed_string";
			case "personneSms":
				return "personneSms_indexed_boolean";
			case "personneRecevoirMail":
				return "personneRecevoirMail_indexed_boolean";
			case "personneContactUrgence":
				return "personneContactUrgence_indexed_boolean";
			case "personneChercher":
				return "personneChercher_indexed_boolean";
			case "mereNomComplet":
				return "mereNomComplet_indexed_string";
			default:
				throw new RuntimeException(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	public String varRechercheMereScolaire(String entiteVar) {
		switch(entiteVar) {
			case "objetSuggere":
				return "objetSuggere_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	public String varSuggereMereScolaire(String entiteVar) {
		switch(entiteVar) {
			case "objetSuggere":
				return "objetSuggere_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	// Partagé //

	public void erreurMereScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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

	public void sqlMereScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public RequeteSiteFrFR genererRequeteSiteFrFRPourMereScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete) {
		return genererRequeteSiteFrFRPourMereScolaire(siteContexte, operationRequete, null);
	}

	public RequeteSiteFrFR genererRequeteSiteFrFRPourMereScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete, JsonObject body) {
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

	public void utilisateurMereScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void rechercheMereScolaire(RequeteSiteFrFR requeteSite, Boolean peupler, Boolean stocker, String classeApiUriMethode, Handler<AsyncResult<ListeRecherche<MereScolaire>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<MereScolaire> listeRecherche = new ListeRecherche<MereScolaire>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(MereScolaire.class);
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
								varIndexe = "*".equals(entiteVar) ? entiteVar : varRechercheMereScolaire(entiteVar);
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
								varIndexe = varIndexeMereScolaire(entiteVar);
								listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
								break;
							case "sort":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
								valeurTri = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
								varIndexe = varIndexeMereScolaire(entiteVar);
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

	public void definirMereScolaire(MereScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void attribuerMereScolaire(MereScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void indexerMereScolaire(MereScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
