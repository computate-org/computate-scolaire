package org.computate.scolaire.frFR.ecole;

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
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.ecrivain.ToutEcrivain;
import org.computate.scolaire.frFR.ecole.EcolePage;


/**
 * Traduire: false
 **/
public class EcoleFrFRGenApiServiceImpl implements EcoleFrFRGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(EcoleFrFRGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "EcoleFrFRApiServiceImpl";

	protected SiteContexteFrFR siteContexte;

	public EcoleFrFRGenApiServiceImpl(SiteContexteFrFR siteContexte) {
		this.siteContexte = siteContexte;
		EcoleFrFRGenApiService service = EcoleFrFRGenApiService.creerProxy(siteContexte.getVertx(), SERVICE_ADDRESS);
	}

	// POST //

	@Override
	public void postEcole(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete, body);
			sqlEcole(requeteSite, a -> {
				if(a.succeeded()) {
					creerPOSTEcole(requeteSite, b -> {
						if(b.succeeded()) {
							Ecole ecole = b.result();
							sqlPOSTEcole(ecole, c -> {
								if(c.succeeded()) {
									definirEcole(ecole, d -> {
										if(d.succeeded()) {
											attribuerEcole(ecole, e -> {
												if(e.succeeded()) {
													indexerEcole(ecole, f -> {
														if(f.succeeded()) {
															reponse200POSTEcole(ecole, g -> {
																if(f.succeeded()) {
																	SQLConnection connexionSql = requeteSite.getConnexionSql();
																	connexionSql.commit(h -> {
																		if(a.succeeded()) {
																			connexionSql.close(i -> {
																				if(a.succeeded()) {
																					gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																				} else {
																					erreurEcole(requeteSite, gestionnaireEvenements, i);
																				}
																			});
																		} else {
																			erreurEcole(requeteSite, gestionnaireEvenements, h);
																		}
																	});
																} else {
																	erreurEcole(requeteSite, gestionnaireEvenements, g);
																}
															});
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
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEcole(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void creerPOSTEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<Ecole>> gestionnaireEvenements) {
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
				o.initLoinEcole(requeteSite);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlPOSTEcole(Ecole o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
				gestionnaireEvenements.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200POSTEcole(Ecole o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(o.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchEcole(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete, body);
			sqlEcole(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEcole(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheEcole(requeteSite, false, true, null, c -> {
								if(c.succeeded()) {
									ListeRecherche<Ecole> listeEcole = c.result();
									listePATCHEcole(listeEcole, d -> {
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
																erreurEcole(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														erreurEcole(requeteSite, gestionnaireEvenements, e);
													}
												});
											}
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
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEcole(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void listePATCHEcole(ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		listeEcole.getList().forEach(o -> {
			futures.add(
				sqlPATCHEcole(o).compose(
					a -> definirPATCHEcole(a).compose(
						b -> indexerPATCHEcole(b)
					)
				)
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				reponse200PATCHEcole(listeEcole, gestionnaireEvenements);
			} else {
				erreurEcole(listeEcole.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<Ecole> sqlPATCHEcole(Ecole o) {
		Future<Ecole> future = Future.future();
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
					case "setCree":
						o2.setCree(requeteJson.getInstant(methodeNom));
						patchSql.append(SiteContexteFrFR.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("cree", o2.getCree(), pk));
						break;
					case "setModifie":
						o2.setModifie(requeteJson.getInstant(methodeNom));
						patchSql.append(SiteContexteFrFR.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("modifie", o2.getModifie(), pk));
						break;
					case "setArchive":
						o2.setArchive(requeteJson.getBoolean(methodeNom));
						patchSql.append(SiteContexteFrFR.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("archive", o2.getArchive(), pk));
						break;
					case "setSupprime":
						o2.setSupprime(requeteJson.getBoolean(methodeNom));
						patchSql.append(SiteContexteFrFR.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("supprime", o2.getSupprime(), pk));
						break;
					case "setEcoleNom":
						o2.setEcoleNom(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexteFrFR.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("ecoleNom", o2.getEcoleNom(), pk));
						break;
					case "setEcoleNumeroTelephone":
						o2.setEcoleNumeroTelephone(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexteFrFR.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("ecoleNumeroTelephone", o2.getEcoleNumeroTelephone(), pk));
						break;
					case "setEcoleAdministrateurNom":
						o2.setEcoleAdministrateurNom(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexteFrFR.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("ecoleAdministrateurNom", o2.getEcoleAdministrateurNom(), pk));
						break;
					case "setEcoleAddresse":
						o2.setEcoleAddresse(requeteJson.getString(methodeNom));
						patchSql.append(SiteContexteFrFR.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("ecoleAddresse", o2.getEcoleAddresse(), pk));
						break;
				}
			}
			connexionSql.queryWithParams(
					patchSql.toString()
					, new JsonArray(patchSqlParams)
					, patchAsync
			-> {
				o2.setRequeteSite_(o.getRequeteSite_());
				o2.setPk(pk);
				future.complete(o2);
			});
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public Future<Ecole> definirPATCHEcole(Ecole o) {
		Future<Ecole> future = Future.future();
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
					for(JsonArray definition : definirAsync.result().getResults()) {
						o.definirPourClasse(definition.getString(0), definition.getString(1));
					}
					future.complete(o);
				} else {
			future.fail(definirAsync.cause());
				}
			});
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public Future<Void> indexerPATCHEcole(Ecole o) {
		Future<Void> future = Future.future();
		try {
			o.initLoinPourClasse(o.getRequeteSite_());
			o.indexerPourClasse();
				future.complete();
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public void reponse200PATCHEcole(ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeEcole.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			buffer.appendString("{}");
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getEcole(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete);
			rechercheEcole(requeteSite, false, true, null, a -> {
				if(a.succeeded()) {
					ListeRecherche<Ecole> listeEcole = a.result();
					reponse200GETEcole(listeEcole, b -> {
						if(b.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(b.result()));
						} else {
							erreurEcole(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEcole(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200GETEcole(ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeEcole.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			SolrDocumentList documentsSolr = listeEcole.getSolrDocumentList();

			if(listeEcole.size() > 0) {
				SolrDocument documentSolr = documentsSolr.get(0);
				Ecole o = listeEcole.get(0);
				Object entiteValeur;
				Integer entiteNumero = 0;

				w.l("{");

				entiteValeur = o.getPk();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"pk\": ", entiteValeur);

				entiteValeur = o.getCree();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"cree\": ", w.qjs(entiteValeur));

				entiteValeur = o.getModifie();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"modifie\": ", w.qjs(entiteValeur));

				entiteValeur = o.getArchive();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"archive\": ", entiteValeur);

				entiteValeur = o.getSupprime();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"supprime\": ", entiteValeur);

				entiteValeur = o.getClasseNomCanonique();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"classeNomCanonique\": ", w.qjs(entiteValeur));

				entiteValeur = o.getClasseNomSimple();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"classeNomSimple\": ", w.qjs(entiteValeur));

				{
					List<String> entiteValeurs = o.getClasseNomsCanoniques();
					w.t(3, entiteNumero++ == 0 ? "" : ", ");
					w.s("\"classeNomsCanoniques\": [");
					for(int k = 0; k < entiteValeurs.size(); k++) {
						entiteValeur = entiteValeurs.get(k);
						if(k > 0)
							w.s(", ");
						w.s("\"");
						w.s(((String)entiteValeur));
						w.s("\"");
					}
					w.l("]");
				}

				entiteValeur = o.getEcoleCle();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleCle\": ", entiteValeur);

				{
					List<Long> entiteValeurs = o.getEnfantCles();
					w.t(3, entiteNumero++ == 0 ? "" : ", ");
					w.s("\"enfantCles\": [");
					for(int k = 0; k < entiteValeurs.size(); k++) {
						entiteValeur = entiteValeurs.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
					}
					w.l("]");
				}

				{
					List<Long> entiteValeurs = o.getBlocCles();
					w.t(3, entiteNumero++ == 0 ? "" : ", ");
					w.s("\"blocCles\": [");
					for(int k = 0; k < entiteValeurs.size(); k++) {
						entiteValeur = entiteValeurs.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
					}
					w.l("]");
				}

				{
					List<Long> entiteValeurs = o.getGroupeAgeCles();
					w.t(3, entiteNumero++ == 0 ? "" : ", ");
					w.s("\"groupeAgeCles\": [");
					for(int k = 0; k < entiteValeurs.size(); k++) {
						entiteValeur = entiteValeurs.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
					}
					w.l("]");
				}

				{
					List<Long> entiteValeurs = o.getSessionCles();
					w.t(3, entiteNumero++ == 0 ? "" : ", ");
					w.s("\"sessionCles\": [");
					for(int k = 0; k < entiteValeurs.size(); k++) {
						entiteValeur = entiteValeurs.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
					}
					w.l("]");
				}

				{
					List<Long> entiteValeurs = o.getSaisonCles();
					w.t(3, entiteNumero++ == 0 ? "" : ", ");
					w.s("\"saisonCles\": [");
					for(int k = 0; k < entiteValeurs.size(); k++) {
						entiteValeur = entiteValeurs.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
					}
					w.l("]");
				}

				{
					List<Long> entiteValeurs = o.getAnneeCles();
					w.t(3, entiteNumero++ == 0 ? "" : ", ");
					w.s("\"anneeCles\": [");
					for(int k = 0; k < entiteValeurs.size(); k++) {
						entiteValeur = entiteValeurs.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
					}
					w.l("]");
				}

				entiteValeur = o.getScolaireTri();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"scolaireTri\": ", entiteValeur);

				entiteValeur = o.getEcoleTri();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleTri\": ", entiteValeur);

				entiteValeur = o.getEcoleNom();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleNom\": ", w.qjs(entiteValeur));

				entiteValeur = o.getEcoleNumeroTelephone();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleNumeroTelephone\": ", w.qjs(entiteValeur));

				entiteValeur = o.getEcoleAdministrateurNom();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleAdministrateurNom\": ", w.qjs(entiteValeur));

				entiteValeur = o.getEcoleAddresse();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleAddresse\": ", w.qjs(entiteValeur));

				entiteValeur = o.getEcoleNomCourt();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleNomCourt\": ", w.qjs(entiteValeur));

				entiteValeur = o.getEcoleId();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleId\": ", w.qjs(entiteValeur));

				entiteValeur = o.getPageUri();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"pageUri\": ", w.qjs(entiteValeur));

				w.l("}");
			}
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteEcole(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete);
			sqlEcole(requeteSite, a -> {
				if(a.succeeded()) {
					rechercheEcole(requeteSite, false, true, null, b -> {
						if(b.succeeded()) {
							ListeRecherche<Ecole> listeEcole = b.result();
							supprimerDELETEEcole(requeteSite, c -> {
								if(c.succeeded()) {
									reponse200DELETEEcole(requeteSite, d -> {
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
																erreurEcole(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														erreurEcole(requeteSite, gestionnaireEvenements, e);
													}
												});
											}
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
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEcole(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void supprimerDELETEEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			Long pk = requeteSite.getRequetePk();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_supprimer
					, new JsonArray(Arrays.asList(pk, Ecole.class.getCanonicalName(), pk, pk, pk, pk))
					, supprimerAsync
			-> {
				gestionnaireEvenements.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200DELETEEcole(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(requeteSite, buffer);
			requeteSite.setW(w);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void rechercheEcole(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete);
			rechercheEcole(requeteSite, false, true, null, a -> {
				if(a.succeeded()) {
					ListeRecherche<Ecole> listeEcole = a.result();
					reponse200RechercheEcole(listeEcole, b -> {
						if(b.succeeded()) {
							gestionnaireEvenements.handle(Future.succeededFuture(b.result()));
						} else {
							erreurEcole(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEcole(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200RechercheEcole(ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeEcole.getRequeteSite_(), buffer);
			requeteSite.setW(w);
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

			w.l("{");
			w.tl(1, "\"numCommence\": ", numCommence);
			w.tl(1, ", \"numTrouve\": ", numTrouve);
			w.tl(1, ", \"numRetourne\": ", numRetourne);
			w.tl(1, ", \"tempsRecherche\": ", w.q(tempsRecherche));
			w.tl(1, ", \"tempsTransmission\": ", w.q(tempsTransmission));
			w.tl(1, ", \"liste\": [");
			for(int i = 0; i < listeEcole.size(); i++) {
				Ecole o = listeEcole.getList().get(i);
				Object entiteValeur;
				Integer entiteNumero = 0;

				w.t(2);
				if(i > 0)
					w.s(", ");
				w.l("{");

				entiteValeur = o.getPk();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"pk\": ", entiteValeur);

				entiteValeur = o.getCree();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"cree\": ", w.qjs(entiteValeur));

				entiteValeur = o.getModifie();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"modifie\": ", w.qjs(entiteValeur));

				entiteValeur = o.getArchive();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"archive\": ", entiteValeur);

				entiteValeur = o.getSupprime();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"supprime\": ", entiteValeur);

				entiteValeur = o.getClasseNomCanonique();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"classeNomCanonique\": ", w.qjs(entiteValeur));

				entiteValeur = o.getClasseNomSimple();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"classeNomSimple\": ", w.qjs(entiteValeur));

				{
					List<String> entiteValeurs = o.getClasseNomsCanoniques();
					w.t(3, entiteNumero++ == 0 ? "" : ", ");
					w.s("\"classeNomsCanoniques\": [");
					for(int k = 0; k < entiteValeurs.size(); k++) {
						entiteValeur = entiteValeurs.get(k);
						if(k > 0)
							w.s(", ");
						w.s("\"");
						w.s(((String)entiteValeur));
						w.s("\"");
					}
					w.l("]");
				}

				entiteValeur = o.getEcoleCle();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleCle\": ", entiteValeur);

				{
					List<Long> entiteValeurs = o.getEnfantCles();
					w.t(3, entiteNumero++ == 0 ? "" : ", ");
					w.s("\"enfantCles\": [");
					for(int k = 0; k < entiteValeurs.size(); k++) {
						entiteValeur = entiteValeurs.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
					}
					w.l("]");
				}

				{
					List<Long> entiteValeurs = o.getBlocCles();
					w.t(3, entiteNumero++ == 0 ? "" : ", ");
					w.s("\"blocCles\": [");
					for(int k = 0; k < entiteValeurs.size(); k++) {
						entiteValeur = entiteValeurs.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
					}
					w.l("]");
				}

				{
					List<Long> entiteValeurs = o.getGroupeAgeCles();
					w.t(3, entiteNumero++ == 0 ? "" : ", ");
					w.s("\"groupeAgeCles\": [");
					for(int k = 0; k < entiteValeurs.size(); k++) {
						entiteValeur = entiteValeurs.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
					}
					w.l("]");
				}

				{
					List<Long> entiteValeurs = o.getSessionCles();
					w.t(3, entiteNumero++ == 0 ? "" : ", ");
					w.s("\"sessionCles\": [");
					for(int k = 0; k < entiteValeurs.size(); k++) {
						entiteValeur = entiteValeurs.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
					}
					w.l("]");
				}

				{
					List<Long> entiteValeurs = o.getSaisonCles();
					w.t(3, entiteNumero++ == 0 ? "" : ", ");
					w.s("\"saisonCles\": [");
					for(int k = 0; k < entiteValeurs.size(); k++) {
						entiteValeur = entiteValeurs.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
					}
					w.l("]");
				}

				{
					List<Long> entiteValeurs = o.getAnneeCles();
					w.t(3, entiteNumero++ == 0 ? "" : ", ");
					w.s("\"anneeCles\": [");
					for(int k = 0; k < entiteValeurs.size(); k++) {
						entiteValeur = entiteValeurs.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
					}
					w.l("]");
				}

				entiteValeur = o.getScolaireTri();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"scolaireTri\": ", entiteValeur);

				entiteValeur = o.getEcoleTri();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleTri\": ", entiteValeur);

				entiteValeur = o.getEcoleNom();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleNom\": ", w.qjs(entiteValeur));

				entiteValeur = o.getEcoleNumeroTelephone();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleNumeroTelephone\": ", w.qjs(entiteValeur));

				entiteValeur = o.getEcoleAdministrateurNom();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleAdministrateurNom\": ", w.qjs(entiteValeur));

				entiteValeur = o.getEcoleAddresse();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleAddresse\": ", w.qjs(entiteValeur));

				entiteValeur = o.getEcoleNomCourt();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleNomCourt\": ", w.qjs(entiteValeur));

				entiteValeur = o.getEcoleId();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"ecoleId\": ", w.qjs(entiteValeur));

				entiteValeur = o.getPageUri();
				if(entiteValeur != null)
					w.tl(3, entiteNumero++ == 0 ? "" : ", ", "\"pageUri\": ", w.qjs(entiteValeur));

				w.tl(2, "}");
			}
			w.tl(1, "]");
			if(exceptionRecherche != null) {
				w.tl(1, ", \"exceptionRecherche\": ", w.q(exceptionRecherche.getMessage()));
			}
			w.l("}");
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
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
		try {
			RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourEcole(siteContexte, operationRequete);
			sqlEcole(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurEcole(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheEcole(requeteSite, false, true, "/frFR/ecole", c -> {
								if(c.succeeded()) {
									ListeRecherche<Ecole> listeEcole = c.result();
									reponse200PageRechercheEcole(listeEcole, d -> {
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
																erreurEcole(requeteSite, gestionnaireEvenements, f);
															}
														});
													} else {
														erreurEcole(requeteSite, gestionnaireEvenements, e);
													}
												});
											}
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
					erreurEcole(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception e) {
			erreurEcole(null, gestionnaireEvenements, Future.failedFuture(e));
		}
	}

	public void reponse200PageRechercheEcole(ListeRecherche<Ecole> listeEcole, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSiteFrFR requeteSite = listeEcole.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeEcole.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			EcolePage page = new EcolePage();
			SolrDocument pageDocumentSolr = new SolrDocument();

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/frFR/ecole");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			page.setListeEcole(listeEcole);
			page.initLoinEcolePage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, new CaseInsensitiveHeaders())));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public String varIndexeEcole(String entiteVar) {
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
			case "ecoleCle":
				return "ecoleCle_indexed_long";
			case "enfantCles":
				return "enfantCles_indexed_longs";
			case "blocCles":
				return "blocCles_indexed_longs";
			case "groupeAgeCles":
				return "groupeAgeCles_indexed_longs";
			case "sessionCles":
				return "sessionCles_indexed_longs";
			case "saisonCles":
				return "saisonCles_indexed_longs";
			case "anneeCles":
				return "anneeCles_indexed_longs";
			case "scolaireTri":
				return "scolaireTri_indexed_int";
			case "ecoleTri":
				return "ecoleTri_indexed_int";
			case "ecoleNom":
				return "ecoleNom_indexed_string";
			case "ecoleNumeroTelephone":
				return "ecoleNumeroTelephone_indexed_string";
			case "ecoleAdministrateurNom":
				return "ecoleAdministrateurNom_indexed_string";
			case "ecoleAddresse":
				return "ecoleAddresse_indexed_string";
			case "objetSuggere":
				return "objetSuggere_indexed_string";
			case "pageUri":
				return "pageUri_indexed_string";
			default:
				throw new RuntimeException(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	public String varRechercheEcole(String entiteVar) {
		switch(entiteVar) {
			default:
				throw new RuntimeException(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	public String varSuggereEcole(String entiteVar) {
		switch(entiteVar) {
			case "objetSuggere":
				return "objetSuggere_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	// Partagé //

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
						gestionnaireEvenements.handle(Future.failedFuture(sqlAsync.cause()));
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
										gestionnaireEvenements.handle(Future.failedFuture(definirAsync.cause()));
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
									gestionnaireEvenements.handle(Future.failedFuture(definirAsync.cause()));
								}
							});
						}
					} else {
						gestionnaireEvenements.handle(Future.failedFuture(selectCAsync.cause()));
					}
				});
			}
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void rechercheEcole(RequeteSiteFrFR requeteSite, Boolean peupler, Boolean stocker, String classeApiUriMethode, Handler<AsyncResult<ListeRecherche<Ecole>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<Ecole> listeRecherche = new ListeRecherche<Ecole>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(Ecole.class);
			if(entiteListe != null)
			listeRecherche.setFields(entiteListe);
			listeRecherche.addSort("archive_indexed_boolean", ORDER.asc);
			listeRecherche.addSort("supprime_indexed_boolean", ORDER.asc);
			listeRecherche.addFilterQuery("classeNomsCanoniques_indexed_strings:" + ClientUtils.escapeQueryChars("org.computate.scolaire.frFR.ecole.Ecole"));
			UtilisateurSite utilisateurSite = requeteSite.getUtilisateurSite();
			if(utilisateurSite != null && !utilisateurSite.getVoirSupprime())
				listeRecherche.addFilterQuery("supprime_indexed_boolean:false");
			if(utilisateurSite != null && !utilisateurSite.getVoirArchive())
				listeRecherche.addFilterQuery("archive_indexed_boolean:false");

			String pageUri = null;
			String id = operationRequete.getParams().getJsonObject("path").getString("id");
			if(id != null) {
				pageUri = classeApiUriMethode + "/" + id;
				listeRecherche.addFilterQuery("pageUri_indexed_string:" + ClientUtils.escapeQueryChars(pageUri));
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
								varIndexe = "*".equals(entiteVar) ? entiteVar : varRechercheEcole(entiteVar);
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
								varIndexe = varIndexeEcole(entiteVar);
								listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
								break;
							case "sort":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
								valeurTri = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
								varIndexe = varIndexeEcole(entiteVar);
								listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurTri));
								break;
							case "fl":
								entiteVar = StringUtils.trim((String)paramObjet);
								varIndexe = varIndexeEcole(entiteVar);
								listeRecherche.addField(varIndexe);
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
					for(JsonArray definition : definirAsync.result().getResults()) {
						o.definirPourClasse(definition.getString(0), definition.getString(1));
					}
					gestionnaireEvenements.handle(Future.succeededFuture());
				} else {
					gestionnaireEvenements.handle(Future.failedFuture(definirAsync.cause()));
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
				if(attribuerAsync.succeeded()) {
					if(attribuerAsync.result() != null) {
						for(JsonArray definition : attribuerAsync.result().getResults()) {
							o.attribuerPourClasse(definition.getString(0), definition.getString(1));
						}
					}
					gestionnaireEvenements.handle(Future.succeededFuture());
				} else {
					gestionnaireEvenements.handle(Future.failedFuture(attribuerAsync.cause()));
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
			gestionnaireEvenements.handle(Future.succeededFuture());
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}
}
