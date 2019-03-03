package org.computate.frFR.scolaire.ecole;

import org.computate.frFR.scolaire.ecrivain.ToutEcrivain;
import java.util.Arrays;
import io.vertx.ext.web.api.validation.ParameterTypeValidator;
import org.apache.solr.common.SolrDocumentList;
import java.util.Date;
import io.vertx.core.MultiMap;
import io.vertx.ext.web.Router;
import io.vertx.ext.reactivestreams.ReactiveReadStream;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.api.OperationResponse;
import org.apache.commons.lang3.StringUtils;
import java.math.BigDecimal;
import java.util.Map;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.core.json.JsonObject;
import org.computate.frFR.scolaire.utilisateur.UtilisateurSite;
import io.vertx.core.logging.Logger;
import io.vertx.core.http.CaseInsensitiveHeaders;
import java.io.PrintWriter;
import org.computate.frFR.scolaire.contexte.SiteContexte;
import java.util.Collection;
import java.sql.Timestamp;
import org.computate.frFR.scolaire.config.ConfigSite;
import java.util.Set;
import io.netty.handler.codec.http.HttpResponseStatus;
import java.util.stream.Collectors;
import io.vertx.core.Future;
import java.time.ZoneId;
import org.computate.frFR.scolaire.recherche.ListeRecherche;
import java.util.List;
import java.security.Principal;
import java.util.stream.Stream;
import io.vertx.core.buffer.Buffer;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.http.client.utils.URLEncodedUtils;
import java.util.Optional;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import org.apache.solr.client.solrj.util.ClientUtils;
import io.vertx.ext.sql.SQLClient;
import org.apache.http.NameValuePair;
import org.apache.commons.lang3.exception.ExceptionUtils;
import io.vertx.core.json.Json;
import java.time.LocalDateTime;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import io.vertx.core.CompositeFuture;
import java.nio.charset.Charset;
import io.vertx.ext.web.api.validation.HTTPRequestValidationHandler;
import io.vertx.core.AsyncResult;
import io.vertx.ext.web.api.validation.ValidationException;
import org.apache.solr.client.solrj.response.QueryResponse;
import io.vertx.core.Vertx;
import java.io.IOException;
import org.computate.frFR.scolaire.recherche.ResultatRecherche;
import io.vertx.ext.reactivestreams.ReactiveWriteStream;
import java.util.concurrent.TimeUnit;
import org.apache.solr.common.SolrDocument;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.api.OperationRequest;
import org.computate.frFR.scolaire.ecole.EcoleScolairePage;
import java.time.format.DateTimeFormatter;
import io.vertx.ext.sql.SQLConnection;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import io.vertx.core.Handler;
import java.util.Collections;
import org.computate.frFR.scolaire.requete.RequeteSite;


public class EcoleScolaireGenApiServiceImpl implements EcoleScolaireGenApiService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EcoleScolaireGenApiServiceImpl.class);

	private static final String SERVICE_ADDRESS = "EcoleScolaireApiServiceImpl";

	protected SiteContexte siteContexte;

	public EcoleScolaireGenApiServiceImpl(SiteContexte siteContexte) {
		this.siteContexte = siteContexte;
		EcoleScolaireGenApiService service = EcoleScolaireGenApiService.creerProxy(siteContexte.getVertx(), SERVICE_ADDRESS);
	}

	// Recherche //

	@Override
	public void rechercheEcoleScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSite requeteSite = genererRequeteSitePourEcoleScolaire(siteContexte, operationRequete);
		rechercheEcoleScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				ListeRecherche<EcoleScolaire> listeEcoleScolaire = a.result();
				reponse200RechercheEcoleScolaire(listeEcoleScolaire, b -> {
					if(b.succeeded()) {
						gestionnaireEvenements.handle(Future.succeededFuture(b.result()));
					} else {
						erreurEcoleScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEcoleScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}

	public void rechercheEcoleScolaire(RequeteSite requeteSite, Handler<AsyncResult<ListeRecherche<EcoleScolaire>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<EcoleScolaire> listeRecherche = new ListeRecherche<EcoleScolaire>();
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(EcoleScolaire.class);
			listeRecherche.setRows(1000000);
			if(entiteListe != null)
			listeRecherche.setFields(entiteListe);
			listeRecherche.addSort("partNumero_indexed_int", ORDER.asc);
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

				for(Object paramObjet : paramObjets) {
					switch(paramNom) {
						case "q":
							entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
							valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":"));
							varIndexe = "*".equals(entiteVar) ? entiteVar : varIndexeEcoleScolaire(entiteVar);
							listeRecherche.setQuery(varIndexe + ":" + ("*".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));
							break;
						case "fq":
							entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
							valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":"));
							varIndexe = varIndexeEcoleScolaire(entiteVar);
							listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
							break;
						case "sort":
							entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
							valeurTri = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
							varIndexe = varIndexeEcoleScolaire(entiteVar);
							listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurTri));
							break;
						case "fl":
							entiteVar = StringUtils.trim((String)paramObjet);
							varIndexe = varIndexeEcoleScolaire(entiteVar);
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
			});
			listeRecherche.initLoinPourClasse(requeteSite);
			gestionnaireEvenements.handle(Future.succeededFuture(listeRecherche));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200RechercheEcoleScolaire(ListeRecherche<EcoleScolaire> listeEcoleScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSite requeteSite = listeEcoleScolaire.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeEcoleScolaire.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			QueryResponse reponseRecherche = listeEcoleScolaire.getQueryResponse();
			SolrDocumentList documentsSolr = listeEcoleScolaire.getSolrDocumentList();
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
			for(int i = 0; i < listeEcoleScolaire.size(); i++) {
				EcoleScolaire o = listeEcoleScolaire.getList().get(i);
				Object entiteValeur;
				Integer entiteNumero = 0;

				w.t(2);
				if(i > 0)
					w.s(", ");
				w.s("{");

				entiteValeur = o.getEcoleCle();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"ecoleCle\": ", entiteValeur);

				{
					List<Long> entiteValeurs = o.getEnfantCles();
					w.s(entiteNumero++ == 0 ? "" : ", ");
					w.s("\"enfantCles\": [");
					int k = 0;
					while(entiteValeur != null) {
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
						entiteValeur = entiteValeurs.iterator().next();
					}
					w.s("]");
				}

				{
					List<Long> entiteValeurs = o.getBlocCles();
					w.s(entiteNumero++ == 0 ? "" : ", ");
					w.s("\"blocCles\": [");
					int k = 0;
					while(entiteValeur != null) {
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
						entiteValeur = entiteValeurs.iterator().next();
					}
					w.s("]");
				}

				{
					List<Long> entiteValeurs = o.getGroupeAgeCles();
					w.s(entiteNumero++ == 0 ? "" : ", ");
					w.s("\"groupeAgeCles\": [");
					int k = 0;
					while(entiteValeur != null) {
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
						entiteValeur = entiteValeurs.iterator().next();
					}
					w.s("]");
				}

				{
					List<Long> entiteValeurs = o.getSessionCles();
					w.s(entiteNumero++ == 0 ? "" : ", ");
					w.s("\"sessionCles\": [");
					int k = 0;
					while(entiteValeur != null) {
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
						entiteValeur = entiteValeurs.iterator().next();
					}
					w.s("]");
				}

				{
					List<Long> entiteValeurs = o.getSaisonCles();
					w.s(entiteNumero++ == 0 ? "" : ", ");
					w.s("\"saisonCles\": [");
					int k = 0;
					while(entiteValeur != null) {
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
						entiteValeur = entiteValeurs.iterator().next();
					}
					w.s("]");
				}

				{
					List<Long> entiteValeurs = o.getAnneeCles();
					w.s(entiteNumero++ == 0 ? "" : ", ");
					w.s("\"anneeCles\": [");
					int k = 0;
					while(entiteValeur != null) {
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
						entiteValeur = entiteValeurs.iterator().next();
					}
					w.s("]");
				}

				entiteValeur = o.getSupprime();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"supprime\": ", entiteValeur);

				entiteValeur = o.getArchive();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"archive\": ", entiteValeur);

				entiteValeur = o.getScolaireTri();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"scolaireTri\": ", entiteValeur);

				entiteValeur = o.getEcoleTri();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"ecoleTri\": ", entiteValeur);

				entiteValeur = o.getEcoleNom();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"ecoleNom\": ", w.q(entiteValeur));

				entiteValeur = o.getEcoleNumeroTelephone();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"ecoleNumeroTelephone\": ", w.q(entiteValeur));

				entiteValeur = o.getEcoleAddresse();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"ecoleAddresse\": ", w.q(entiteValeur));

				entiteValeur = o.getEcoleAdministrateurNom();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"ecoleAdministrateurNom\": ", w.q(entiteValeur));

				entiteValeur = o.getEcoleNomCourt();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"ecoleNomCourt\": ", w.q(entiteValeur));

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

	// POST //

	@Override
	public void postEcoleScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSite requeteSite = genererRequeteSitePourEcoleScolaire(siteContexte, operationRequete, body);
		sqlEcoleScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				creerPOSTEcoleScolaire(requeteSite, b -> {
					if(b.succeeded()) {
						EcoleScolaire ecoleScolaire = b.result();
						sqlPOSTEcoleScolaire(ecoleScolaire, c -> {
							if(c.succeeded()) {
								definirEcoleScolaire(ecoleScolaire, d -> {
									if(d.succeeded()) {
										attribuerEcoleScolaire(ecoleScolaire, e -> {
											if(e.succeeded()) {
												indexerEcoleScolaire(ecoleScolaire, f -> {
													if(f.succeeded()) {
														reponse200POSTEcoleScolaire(ecoleScolaire, g -> {
															if(f.succeeded()) {
																SQLConnection connexionSql = requeteSite.getConnexionSql();
																connexionSql.commit(h -> {
																	if(a.succeeded()) {
																		connexionSql.close(i -> {
																			if(a.succeeded()) {
																				gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																			} else {
																				erreurEcoleScolaire(requeteSite, gestionnaireEvenements, i);
																			}
																		});
																	} else {
																		erreurEcoleScolaire(requeteSite, gestionnaireEvenements, h);
																	}
																});
															} else {
																erreurEcoleScolaire(requeteSite, gestionnaireEvenements, g);
															}
														});
													} else {
														erreurEcoleScolaire(requeteSite, gestionnaireEvenements, f);
													}
												});
											} else {
												erreurEcoleScolaire(requeteSite, gestionnaireEvenements, e);
											}
										});
									} else {
										erreurEcoleScolaire(requeteSite, gestionnaireEvenements, d);
									}
								});
							} else {
								erreurEcoleScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEcoleScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEcoleScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}

	public void creerPOSTEcoleScolaire(RequeteSite requeteSite, Handler<AsyncResult<EcoleScolaire>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();

			connexionSql.queryWithParams(
					SiteContexte.SQL_creer
					, new JsonArray(Arrays.asList(EcoleScolaire.class.getCanonicalName(), utilisateurId))
					, creerAsync
			-> {
				JsonArray patchLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = patchLigne.getLong(0);
				EcoleScolaire o = new EcoleScolaire();
				o.setPk(pk);
				o.initLoinEcoleScolaire(requeteSite);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlPOSTEcoleScolaire(EcoleScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject jsonObject = requeteSite.getObjetJson();
			StringBuilder postSql = new StringBuilder();
			List<Object> postSqlParams = new ArrayList<Object>();

			if(jsonObject != null) {
				Set<String> entiteVars = jsonObject.fieldNames();
				for(String entiteVar : entiteVars) {
					switch(entiteVar) {
					case "ecoleCle":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("ecoleCle", jsonObject.getLong(entiteVar), pk));
						break;
					case "enfantCles":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("enfantCles", jsonObject.getJsonArray(entiteVar), pk));
						break;
					case "blocCles":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("blocCles", jsonObject.getJsonArray(entiteVar), pk));
						break;
					case "groupeAgeCles":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("groupeAgeCles", jsonObject.getJsonArray(entiteVar), pk));
						break;
					case "sessionCles":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("sessionCles", jsonObject.getJsonArray(entiteVar), pk));
						break;
					case "saisonCles":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("saisonCles", jsonObject.getJsonArray(entiteVar), pk));
						break;
					case "anneeCles":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("anneeCles", jsonObject.getJsonArray(entiteVar), pk));
						break;
					case "supprime":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("supprime", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "archive":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("archive", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "scolaireTri":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("scolaireTri", jsonObject.getInteger(entiteVar), pk));
						break;
					case "ecoleTri":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("ecoleTri", jsonObject.getInteger(entiteVar), pk));
						break;
					case "ecoleNom":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("ecoleNom", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleNumeroTelephone":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("ecoleNumeroTelephone", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleAddresse":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("ecoleAddresse", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleAdministrateurNom":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("ecoleAdministrateurNom", jsonObject.getString(entiteVar), pk));
						break;
					case "objetSuggerePoids":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("objetSuggerePoids", jsonObject.getDouble(entiteVar), pk));
						break;
					case "objetSuggere":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("objetSuggere", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleNomCourt":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("ecoleNomCourt", jsonObject.getString(entiteVar), pk));
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

	public void reponse200POSTEcoleScolaire(EcoleScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSite requeteSite = o.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(o.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchEcoleScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSite requeteSite = genererRequeteSitePourEcoleScolaire(siteContexte, operationRequete, body);
		sqlEcoleScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				rechercheEcoleScolaire(requeteSite, b -> {
					if(b.succeeded()) {
						ListeRecherche<EcoleScolaire> listeEcoleScolaire = b.result();
						listePATCHEcoleScolaire(listeEcoleScolaire, c -> {
							if(c.succeeded()) {
								SQLConnection connexionSql = requeteSite.getConnexionSql();
								connexionSql.commit(d -> {
									if(a.succeeded()) {
										connexionSql.close(e -> {
											if(a.succeeded()) {
												gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
											} else {
												erreurEcoleScolaire(requeteSite, gestionnaireEvenements, e);
											}
										});
									} else {
										erreurEcoleScolaire(requeteSite, gestionnaireEvenements, d);
									}
								});
							} else {
								erreurEcoleScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEcoleScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEcoleScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}

	public Future<OperationResponse> listePATCHEcoleScolaire(ListeRecherche<EcoleScolaire> listeEcoleScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		return null;
	}

	public void sqlPATCHEcoleScolaire(EcoleScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject requeteJson = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = requeteJson.fieldNames();

			for(String methodeNom : methodeNoms) {
				switch(methodeNom) {
					case "setEcoleCle":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("ecoleCle", requeteJson.getLong(methodeNom), pk));
						break;
					case "addEnfantCles":
						patchSql.append(SiteContexte.SQL_addA);
						patchSqlParams.addAll(Arrays.asList("enfantCles", requeteJson.getJsonArray(methodeNom), pk));
					case "setEnfantCles":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("enfantCles", requeteJson.getJsonArray(methodeNom), pk));
						break;
					case "addBlocCles":
						patchSql.append(SiteContexte.SQL_addA);
						patchSqlParams.addAll(Arrays.asList("blocCles", requeteJson.getJsonArray(methodeNom), pk));
					case "setBlocCles":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("blocCles", requeteJson.getJsonArray(methodeNom), pk));
						break;
					case "addGroupeAgeCles":
						patchSql.append(SiteContexte.SQL_addA);
						patchSqlParams.addAll(Arrays.asList("groupeAgeCles", requeteJson.getJsonArray(methodeNom), pk));
					case "setGroupeAgeCles":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("groupeAgeCles", requeteJson.getJsonArray(methodeNom), pk));
						break;
					case "addSessionCles":
						patchSql.append(SiteContexte.SQL_addA);
						patchSqlParams.addAll(Arrays.asList("sessionCles", requeteJson.getJsonArray(methodeNom), pk));
					case "setSessionCles":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("sessionCles", requeteJson.getJsonArray(methodeNom), pk));
						break;
					case "addSaisonCles":
						patchSql.append(SiteContexte.SQL_addA);
						patchSqlParams.addAll(Arrays.asList("saisonCles", requeteJson.getJsonArray(methodeNom), pk));
					case "setSaisonCles":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("saisonCles", requeteJson.getJsonArray(methodeNom), pk));
						break;
					case "addAnneeCles":
						patchSql.append(SiteContexte.SQL_addA);
						patchSqlParams.addAll(Arrays.asList("anneeCles", requeteJson.getJsonArray(methodeNom), pk));
					case "setAnneeCles":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("anneeCles", requeteJson.getJsonArray(methodeNom), pk));
						break;
					case "setSupprime":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("supprime", requeteJson.getBoolean(methodeNom), pk));
						break;
					case "setArchive":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("archive", requeteJson.getBoolean(methodeNom), pk));
						break;
					case "setScolaireTri":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("scolaireTri", requeteJson.getInteger(methodeNom), pk));
						break;
					case "setEcoleTri":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("ecoleTri", requeteJson.getInteger(methodeNom), pk));
						break;
					case "setEcoleNom":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("ecoleNom", requeteJson.getString(methodeNom), pk));
						break;
					case "setEcoleNumeroTelephone":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("ecoleNumeroTelephone", requeteJson.getString(methodeNom), pk));
						break;
					case "setEcoleAddresse":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("ecoleAddresse", requeteJson.getString(methodeNom), pk));
						break;
					case "setEcoleAdministrateurNom":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("ecoleAdministrateurNom", requeteJson.getString(methodeNom), pk));
						break;
					case "setObjetSuggerePoids":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("objetSuggerePoids", requeteJson.getDouble(methodeNom), pk));
						break;
					case "setObjetSuggere":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("objetSuggere", requeteJson.getString(methodeNom), pk));
						break;
					case "setEcoleNomCourt":
						patchSql.append(SiteContexte.SQL_setP);
						patchSqlParams.addAll(Arrays.asList("ecoleNomCourt", requeteJson.getString(methodeNom), pk));
						break;
				}
			}
			connexionSql.queryWithParams(
					patchSql.toString()
					, new JsonArray(patchSqlParams)
					, patchAsync
			-> {
				gestionnaireEvenements.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200PATCHEcoleScolaire(ListeRecherche<EcoleScolaire> listeEcoleScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSite requeteSite = listeEcoleScolaire.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeEcoleScolaire.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getEcoleScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSite requeteSite = genererRequeteSitePourEcoleScolaire(siteContexte, operationRequete);
		rechercheEcoleScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				ListeRecherche<EcoleScolaire> listeEcoleScolaire = a.result();
				reponse200GETEcoleScolaire(listeEcoleScolaire, b -> {
					if(b.succeeded()) {
						gestionnaireEvenements.handle(Future.succeededFuture(b.result()));
					} else {
						erreurEcoleScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEcoleScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}

	public void reponse200GETEcoleScolaire(ListeRecherche<EcoleScolaire> listeEcoleScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSite requeteSite = listeEcoleScolaire.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeEcoleScolaire.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			SolrDocumentList documentsSolr = listeEcoleScolaire.getSolrDocumentList();

			if(listeEcoleScolaire.size() > 0) {
				SolrDocument documentSolr = documentsSolr.get(0);
				EcoleScolaire o = listeEcoleScolaire.get(0);
				Object entiteValeur;
				Integer entiteNumero = 0;

				w.l("{");

				entiteValeur = o.getEcoleCle();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"ecoleCle\": ", entiteValeur);

				{
					List<Long> entiteValeurs = o.getEnfantCles();
					w.s(entiteNumero++ == 0 ? "" : ", ");
					w.s("\"enfantCles\": [");
					int k = 0;
					while(entiteValeur != null) {
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
						entiteValeur = entiteValeurs.iterator().next();
					}
					w.s("]");
				}

				{
					List<Long> entiteValeurs = o.getBlocCles();
					w.s(entiteNumero++ == 0 ? "" : ", ");
					w.s("\"blocCles\": [");
					int k = 0;
					while(entiteValeur != null) {
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
						entiteValeur = entiteValeurs.iterator().next();
					}
					w.s("]");
				}

				{
					List<Long> entiteValeurs = o.getGroupeAgeCles();
					w.s(entiteNumero++ == 0 ? "" : ", ");
					w.s("\"groupeAgeCles\": [");
					int k = 0;
					while(entiteValeur != null) {
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
						entiteValeur = entiteValeurs.iterator().next();
					}
					w.s("]");
				}

				{
					List<Long> entiteValeurs = o.getSessionCles();
					w.s(entiteNumero++ == 0 ? "" : ", ");
					w.s("\"sessionCles\": [");
					int k = 0;
					while(entiteValeur != null) {
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
						entiteValeur = entiteValeurs.iterator().next();
					}
					w.s("]");
				}

				{
					List<Long> entiteValeurs = o.getSaisonCles();
					w.s(entiteNumero++ == 0 ? "" : ", ");
					w.s("\"saisonCles\": [");
					int k = 0;
					while(entiteValeur != null) {
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
						entiteValeur = entiteValeurs.iterator().next();
					}
					w.s("]");
				}

				{
					List<Long> entiteValeurs = o.getAnneeCles();
					w.s(entiteNumero++ == 0 ? "" : ", ");
					w.s("\"anneeCles\": [");
					int k = 0;
					while(entiteValeur != null) {
						if(k > 0)
							w.s(", ");
						w.s(((Long)entiteValeur).toString());
						entiteValeur = entiteValeurs.iterator().next();
					}
					w.s("]");
				}

				entiteValeur = o.getSupprime();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"supprime\": ", entiteValeur);

				entiteValeur = o.getArchive();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"archive\": ", entiteValeur);

				entiteValeur = o.getScolaireTri();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"scolaireTri\": ", entiteValeur);

				entiteValeur = o.getEcoleTri();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"ecoleTri\": ", entiteValeur);

				entiteValeur = o.getEcoleNom();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"ecoleNom\": ", w.q(entiteValeur));

				entiteValeur = o.getEcoleNumeroTelephone();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"ecoleNumeroTelephone\": ", w.q(entiteValeur));

				entiteValeur = o.getEcoleAddresse();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"ecoleAddresse\": ", w.q(entiteValeur));

				entiteValeur = o.getEcoleAdministrateurNom();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"ecoleAdministrateurNom\": ", w.q(entiteValeur));

				entiteValeur = o.getEcoleNomCourt();
				if(entiteValeur != null)
					w.l(entiteNumero++ == 0 ? "" : ", ", "\"ecoleNomCourt\": ", w.q(entiteValeur));

				w.l("}");
			}
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUT //

	@Override
	public void putEcoleScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSite requeteSite = genererRequeteSitePourEcoleScolaire(siteContexte, operationRequete, body);
		sqlEcoleScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				remplacerPUTEcoleScolaire(requeteSite, b -> {
					if(b.succeeded()) {
						EcoleScolaire ecoleScolaire = b.result();
						sqlPUTEcoleScolaire(ecoleScolaire, c -> {
							if(c.succeeded()) {
								definirEcoleScolaire(ecoleScolaire, d -> {
									if(d.succeeded()) {
										attribuerEcoleScolaire(ecoleScolaire, e -> {
											if(e.succeeded()) {
												indexerEcoleScolaire(ecoleScolaire, f -> {
													if(f.succeeded()) {
														reponse200PUTEcoleScolaire(ecoleScolaire, g -> {
															if(g.succeeded()) {
																SQLConnection connexionSql = requeteSite.getConnexionSql();
																connexionSql.commit(h -> {
																	if(a.succeeded()) {
																		connexionSql.close(i -> {
																			if(a.succeeded()) {
																				gestionnaireEvenements.handle(Future.succeededFuture(g.result()));
																			} else {
																				erreurEcoleScolaire(requeteSite, gestionnaireEvenements, i);
																			}
																		});
																	} else {
																		erreurEcoleScolaire(requeteSite, gestionnaireEvenements, h);
																	}
																});
															} else {
																erreurEcoleScolaire(requeteSite, gestionnaireEvenements, g);
															}
														});
													} else {
														erreurEcoleScolaire(requeteSite, gestionnaireEvenements, f);
													}
												});
											} else {
												erreurEcoleScolaire(requeteSite, gestionnaireEvenements, e);
											}
										});
									} else {
										erreurEcoleScolaire(requeteSite, gestionnaireEvenements, d);
									}
								});
							} else {
								erreurEcoleScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEcoleScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEcoleScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}

	public void remplacerPUTEcoleScolaire(RequeteSite requeteSite, Handler<AsyncResult<EcoleScolaire>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			Long pk = requeteSite.getRequetePk();

			connexionSql.queryWithParams(
					SiteContexte.SQL_vider
					, new JsonArray(Arrays.asList(pk, EcoleScolaire.class.getCanonicalName(), pk, pk, pk))
					, remplacerAsync
			-> {
				EcoleScolaire o = new EcoleScolaire();
				o.setPk(pk);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void sqlPUTEcoleScolaire(EcoleScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject jsonObject = requeteSite.getObjetJson();
			StringBuilder postSql = new StringBuilder();
			List<Object> postSqlParams = new ArrayList<Object>();

			if(jsonObject != null) {
				Set<String> entiteVars = jsonObject.fieldNames();
				for(String entiteVar : entiteVars) {
					switch(entiteVar) {
					case "ecoleCle":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("ecoleCle", jsonObject.getLong(entiteVar), pk));
						break;
					case "enfantCles":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("enfantCles", jsonObject.getJsonArray(entiteVar), pk));
						break;
					case "blocCles":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("blocCles", jsonObject.getJsonArray(entiteVar), pk));
						break;
					case "groupeAgeCles":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("groupeAgeCles", jsonObject.getJsonArray(entiteVar), pk));
						break;
					case "sessionCles":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("sessionCles", jsonObject.getJsonArray(entiteVar), pk));
						break;
					case "saisonCles":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("saisonCles", jsonObject.getJsonArray(entiteVar), pk));
						break;
					case "anneeCles":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("anneeCles", jsonObject.getJsonArray(entiteVar), pk));
						break;
					case "supprime":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("supprime", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "archive":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("archive", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "scolaireTri":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("scolaireTri", jsonObject.getInteger(entiteVar), pk));
						break;
					case "ecoleTri":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("ecoleTri", jsonObject.getInteger(entiteVar), pk));
						break;
					case "ecoleNom":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("ecoleNom", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleNumeroTelephone":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("ecoleNumeroTelephone", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleAddresse":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("ecoleAddresse", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleAdministrateurNom":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("ecoleAdministrateurNom", jsonObject.getString(entiteVar), pk));
						break;
					case "objetSuggerePoids":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("objetSuggerePoids", jsonObject.getDouble(entiteVar), pk));
						break;
					case "objetSuggere":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("objetSuggere", jsonObject.getString(entiteVar), pk));
						break;
					case "ecoleNomCourt":
						postSql.append(SiteContexte.SQL_setP);
						postSqlParams.addAll(Arrays.asList("ecoleNomCourt", jsonObject.getString(entiteVar), pk));
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

	public void reponse200PUTEcoleScolaire(EcoleScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSite requeteSite = o.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(o.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteEcoleScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSite requeteSite = genererRequeteSitePourEcoleScolaire(siteContexte, operationRequete);
		sqlEcoleScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				rechercheEcoleScolaire(requeteSite, b -> {
					if(b.succeeded()) {
						ListeRecherche<EcoleScolaire> listeEcoleScolaire = b.result();
						supprimerDELETEEcoleScolaire(requeteSite, c -> {
							if(c.succeeded()) {
								reponse200DELETEEcoleScolaire(requeteSite, d -> {
									if(d.succeeded()) {
										SQLConnection connexionSql = requeteSite.getConnexionSql();
										connexionSql.commit(e -> {
											if(a.succeeded()) {
												connexionSql.close(f -> {
													if(a.succeeded()) {
														gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
													} else {
														erreurEcoleScolaire(requeteSite, gestionnaireEvenements, f);
													}
												});
											} else {
												erreurEcoleScolaire(requeteSite, gestionnaireEvenements, e);
											}
										});
									} else {
										erreurEcoleScolaire(requeteSite, gestionnaireEvenements, d);
									}
								});
							} else {
								erreurEcoleScolaire(requeteSite, gestionnaireEvenements, c);
							}
						});
					} else {
						erreurEcoleScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEcoleScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}

	public void supprimerDELETEEcoleScolaire(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();
			Long pk = requeteSite.getRequetePk();

			connexionSql.queryWithParams(
					SiteContexte.SQL_supprimer
					, new JsonArray(Arrays.asList(pk, EcoleScolaire.class.getCanonicalName(), pk, pk, pk, pk))
					, supprimerAsync
			-> {
				gestionnaireEvenements.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200DELETEEcoleScolaire(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(requeteSite, buffer);
			requeteSite.setW(w);
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// RecherchePage //

	@Override
	public void recherchepageEcoleScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSite requeteSite = genererRequeteSitePourEcoleScolaire(siteContexte, operationRequete);
		recherchepageEcoleScolaire(requeteSite, a -> {
			if(a.succeeded()) {
				ListeRecherche<EcoleScolaire> listeEcoleScolaire = a.result();
				reponse200RecherchePageEcoleScolaire(listeEcoleScolaire, b -> {
					if(b.succeeded()) {
						gestionnaireEvenements.handle(Future.succeededFuture(b.result()));
					} else {
						erreurEcoleScolaire(requeteSite, gestionnaireEvenements, b);
					}
				});
			} else {
				erreurEcoleScolaire(requeteSite, gestionnaireEvenements, a);
			}
		});
	}

	public void recherchepageEcoleScolaire(RequeteSite requeteSite, Handler<AsyncResult<ListeRecherche<EcoleScolaire>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<EcoleScolaire> listeRecherche = new ListeRecherche<EcoleScolaire>();
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(EcoleScolaire.class);
			listeRecherche.setRows(1000000);
			if(entiteListe != null)
			listeRecherche.setFields(entiteListe);
			listeRecherche.addSort("partNumero_indexed_int", ORDER.asc);
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

				for(Object paramObjet : paramObjets) {
					switch(paramNom) {
						case "q":
							entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
							valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":"));
							varIndexe = "*".equals(entiteVar) ? entiteVar : varIndexeEcoleScolaire(entiteVar);
							listeRecherche.setQuery(varIndexe + ":" + ("*".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));
							break;
						case "fq":
							entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
							valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":"));
							varIndexe = varIndexeEcoleScolaire(entiteVar);
							listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
							break;
						case "sort":
							entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
							valeurTri = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
							varIndexe = varIndexeEcoleScolaire(entiteVar);
							listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurTri));
							break;
						case "fl":
							entiteVar = StringUtils.trim((String)paramObjet);
							varIndexe = varIndexeEcoleScolaire(entiteVar);
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
			});
			listeRecherche.initLoinPourClasse(requeteSite);
			gestionnaireEvenements.handle(Future.succeededFuture(listeRecherche));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void reponse200RecherchePageEcoleScolaire(ListeRecherche<EcoleScolaire> listeEcoleScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			Buffer buffer = Buffer.buffer();
			RequeteSite requeteSite = listeEcoleScolaire.getRequeteSite_();
			ToutEcrivain w = ToutEcrivain.creer(listeEcoleScolaire.getRequeteSite_(), buffer);
			requeteSite.setW(w);
			EcoleScolairePage page = new EcoleScolairePage();
			page.setPageUrl("/api/v1/ecole");
			SolrDocument pageDocumentSolr = new SolrDocument();

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/ecole");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			page.setListeEcoleScolaire(listeEcoleScolaire);
			page.initLoinEcoleScolairePage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, new CaseInsensitiveHeaders())));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public String varIndexeEcoleScolaire(String entiteVar) {
		switch(entiteVar) {
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
			case "supprime":
				return "supprime_indexed_boolean";
			case "archive":
				return "archive_indexed_boolean";
			case "scolaireTri":
				return "scolaireTri_indexed_int";
			case "ecoleTri":
				return "ecoleTri_indexed_int";
			default:
				throw new RuntimeException(String.format("\"%s\" n'est pas une entité indexé. ", entiteVar));
		}
	}

	// Partagé //

	public void erreurEcoleScolaire(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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
	}

	public void sqlEcoleScolaire(RequeteSite requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			SQLClient clientSql = requeteSite.getSiteContexte_().getClientSql();

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
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public RequeteSite genererRequeteSitePourEcoleScolaire(SiteContexte siteContexte, OperationRequest operationRequete) {
		return genererRequeteSitePourEcoleScolaire(siteContexte, operationRequete, null);
	}

	public RequeteSite genererRequeteSitePourEcoleScolaire(SiteContexte siteContexte, OperationRequest operationRequete, JsonObject body) {
		Vertx vertx = siteContexte.getVertx();
		RequeteSite requeteSite = new RequeteSite();
		requeteSite.setObjetJson(body);
		requeteSite.setVertx(vertx);
		requeteSite.setSiteContexte_(siteContexte);
		requeteSite.setConfigSite_(siteContexte.getConfigSite());
		requeteSite.setOperationRequete(operationRequete);
		requeteSite.initLoinRequeteSite(requeteSite);

		UtilisateurSite utilisateurSite = new UtilisateurSite();
		utilisateurSite.initLoinUtilisateurSite(requeteSite);
		requeteSite.setUtilisateurSite(utilisateurSite);
		utilisateurSite.setRequeteSite_(requeteSite);
		return requeteSite;
	}

	public void definirEcoleScolaire(EcoleScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			connexionSql.queryWithParams(
					SiteContexte.SQL_definir
					, new JsonArray(Arrays.asList(pk))
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

	public void attribuerEcoleScolaire(EcoleScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSite requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			connexionSql.queryWithParams(
					SiteContexte.SQL_attribuer
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

	public void indexerEcoleScolaire(EcoleScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSite requeteSite = o.getRequeteSite_();
		try {
			o.initLoinPourClasse(requeteSite);
			o.indexerPourClasse();
			gestionnaireEvenements.handle(Future.succeededFuture());
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}
}
