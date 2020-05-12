package org.computate.scolaire.frFR.inscription;

import org.computate.scolaire.frFR.annee.AnneeScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.annee.AnneeScolaire;
import org.computate.scolaire.frFR.bloc.BlocScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.bloc.BlocScolaire;
import org.computate.scolaire.frFR.enfant.EnfantScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.enfant.EnfantScolaire;
import org.computate.scolaire.frFR.mere.MereScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.mere.MereScolaire;
import org.computate.scolaire.frFR.pere.PereScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.pere.PereScolaire;
import org.computate.scolaire.frFR.gardien.GardienScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.gardien.GardienScolaire;
import org.computate.scolaire.frFR.paiement.PaiementScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.paiement.PaiementScolaire;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSiteFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSite;
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
 * NomCanonique.enUS: org.computate.scolaire.enUS.enrollment.SchoolEnrollmentEnUSGenApiServiceImpl
 **/
public class InscriptionScolaireFrFRGenApiServiceImpl implements InscriptionScolaireFrFRGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(InscriptionScolaireFrFRGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "InscriptionScolaireFrFRApiServiceImpl";

	protected SiteContexteFrFR siteContexte;

	public InscriptionScolaireFrFRGenApiServiceImpl(SiteContexteFrFR siteContexte) {
		this.siteContexte = siteContexte;
	}

	// POST //

	@Override
	public void postInscriptionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("postInscriptionScolaire a démarré. "));
			sqlInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurInscriptionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							RequeteApi requeteApi = new RequeteApi();
							requeteApi.setRows(1);
							requeteApi.setNumFound(1L);
							requeteApi.setNumPATCH(0L);
							requeteApi.initLoinRequeteApi(requeteSite);
							requeteSite.setRequeteApi_(requeteApi);
							requeteSite.getVertx().eventBus().publish("websocketInscriptionScolaire", JsonObject.mapFrom(requeteApi).toString());
							postInscriptionScolaireFuture(requeteSite, false, c -> {
								if(c.succeeded()) {
									InscriptionScolaire inscriptionScolaire = c.result();
									requeteApi.setPk(inscriptionScolaire.getPk());
									postInscriptionScolaireReponse(inscriptionScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postInscriptionScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("postInscriptionScolaire a échoué. ", d.cause()));
											erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("postInscriptionScolaire a échoué. ", c.cause()));
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("postInscriptionScolaire a échoué. ", b.cause()));
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("postInscriptionScolaire a échoué. ", a.cause()));
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public Future<InscriptionScolaire> postInscriptionScolaireFuture(RequeteSiteFrFR requeteSite, Boolean inheritPk, Handler<AsyncResult<InscriptionScolaire>> gestionnaireEvenements) {
		Promise<InscriptionScolaire> promise = Promise.promise();
		try {
			creerInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					InscriptionScolaire inscriptionScolaire = a.result();
					sqlPOSTInscriptionScolaire(inscriptionScolaire, inheritPk, b -> {
						if(b.succeeded()) {
							definirIndexerInscriptionScolaire(inscriptionScolaire, c -> {
								if(c.succeeded()) {
									RequeteApi requeteApi = requeteSite.getRequeteApi_();
									if(requeteApi != null) {
										requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
										inscriptionScolaire.requeteApiInscriptionScolaire();
										requeteSite.getVertx().eventBus().publish("websocketInscriptionScolaire", JsonObject.mapFrom(requeteApi).toString());
									}
									gestionnaireEvenements.handle(Future.succeededFuture(inscriptionScolaire));
									promise.complete(inscriptionScolaire);
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
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTInscriptionScolaire(InscriptionScolaire o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
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
					case "anneeCle":
						{
							Long l = Long.parseLong(jsonObject.getString(entiteVar));
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
									postSqlParams.addAll(Arrays.asList("anneeCle", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("AnneeScolaire");
									}
								}
							}
						}
						break;
					case "blocCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								ListeRecherche<BlocScolaire> listeRecherche = new ListeRecherche<BlocScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(BlocScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContexteFrFR.SQL_addA);
									postSqlParams.addAll(Arrays.asList("blocCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("BlocScolaire");
									}
								}
							}
						}
						break;
					case "enfantCle":
						{
							Long l = Long.parseLong(jsonObject.getString(entiteVar));
							if(l != null) {
								ListeRecherche<EnfantScolaire> listeRecherche = new ListeRecherche<EnfantScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(EnfantScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContexteFrFR.SQL_addA);
									postSqlParams.addAll(Arrays.asList("enfantCle", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("EnfantScolaire");
									}
								}
							}
						}
						break;
					case "mereCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								ListeRecherche<MereScolaire> listeRecherche = new ListeRecherche<MereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(MereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContexteFrFR.SQL_addA);
									postSqlParams.addAll(Arrays.asList("inscriptionCles", l, "mereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("MereScolaire");
									}
								}
							}
						}
						break;
					case "pereCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								ListeRecherche<PereScolaire> listeRecherche = new ListeRecherche<PereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContexteFrFR.SQL_addA);
									postSqlParams.addAll(Arrays.asList("inscriptionCles", l, "pereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PereScolaire");
									}
								}
							}
						}
						break;
					case "gardienCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								ListeRecherche<GardienScolaire> listeRecherche = new ListeRecherche<GardienScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(GardienScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContexteFrFR.SQL_addA);
									postSqlParams.addAll(Arrays.asList("gardienCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("GardienScolaire");
									}
								}
							}
						}
						break;
					case "paiementCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PaiementScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContexteFrFR.SQL_addA);
									postSqlParams.addAll(Arrays.asList("inscriptionCle", l, "paiementCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PaiementScolaire");
									}
								}
							}
						}
						break;
					case "utilisateurCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								ListeRecherche<UtilisateurSite> listeRecherche = new ListeRecherche<UtilisateurSite>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(UtilisateurSite.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContexteFrFR.SQL_addA);
									postSqlParams.addAll(Arrays.asList("inscriptionCles", l, "utilisateurCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("UtilisateurSite");
									}
								}
							}
						}
						break;
					case "enfantNomComplet":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enfantNomComplet", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantNomCompletPrefere":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enfantNomCompletPrefere", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantDateNaissance":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enfantDateNaissance", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "ecoleAddresse":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ecoleAddresse", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionApprouve":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionApprouve", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "inscriptionImmunisations":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionImmunisations", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "familleMarie":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("familleMarie", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "familleSepare":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("familleSepare", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "familleDivorce":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("familleDivorce", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "familleAddresse":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("familleAddresse", jsonObject.getString(entiteVar), pk));
						break;
					case "familleCommentVousConnaissezEcole":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("familleCommentVousConnaissezEcole", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionConsiderationsSpeciales":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionConsiderationsSpeciales", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantConditionsMedicales":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enfantConditionsMedicales", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantEcolesPrecedemmentFrequentees":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enfantEcolesPrecedemmentFrequentees", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantDescription":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enfantDescription", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantObjectifs":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enfantObjectifs", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantPropre":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enfantPropre", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "inscriptionNomGroupe":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionNomGroupe", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionPaimentChaqueMois":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionPaimentChaqueMois", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "inscriptionPaimentComplet":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionPaimentComplet", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "customerProfileId":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("customerProfileId", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionDateFrais":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionDateFrais", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionSignature1":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionSignature1", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature2":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionSignature2", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature3":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionSignature3", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature4":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionSignature4", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature5":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionSignature5", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature6":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionSignature6", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature7":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionSignature7", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature8":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionSignature8", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature9":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionSignature9", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature10":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionSignature10", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionDate1":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionDate1", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate2":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionDate2", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate3":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionDate3", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate4":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionDate4", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate5":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionDate5", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate6":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionDate6", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate7":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionDate7", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate8":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionDate8", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate9":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionDate9", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate10":
						postSql.append(SiteContexteFrFR.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inscriptionDate10", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
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

	public void postInscriptionScolaireReponse(InscriptionScolaire inscriptionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = inscriptionScolaire.getRequeteSite_();
		try {
			reponse200POSTInscriptionScolaire(inscriptionScolaire, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							connexionSql.close(c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200POSTInscriptionScolaire(InscriptionScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
	public void putimportInscriptionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putimportInscriptionScolaire a démarré. "));
			sqlInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurInscriptionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							putimportInscriptionScolaireReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete, body);
											try {
												RequeteApi requeteApi = new RequeteApi();
												JsonArray jsonArray = Optional.ofNullable(requeteSite2.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												requeteApi.setRows(jsonArray.size());
												requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite2);
												requeteSite2.setRequeteApi_(requeteApi);
												requeteSite2.getVertx().eventBus().publish("websocketInscriptionScolaire", JsonObject.mapFrom(requeteApi).toString());
												sqlInscriptionScolaire(requeteSite2, d -> {
													if(d.succeeded()) {
														listePUTImportInscriptionScolaire(requeteApi, requeteSite2, e -> {
															if(e.succeeded()) {
																putimportInscriptionScolaireReponse(requeteSite2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putimportInscriptionScolaire a réussi. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putimportInscriptionScolaire a échoué. ", f.cause()));
																		erreurInscriptionScolaire(requeteSite2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putimportInscriptionScolaire a échoué. ", e.cause()));
																erreurInscriptionScolaire(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putimportInscriptionScolaire a échoué. ", d.cause()));
														erreurInscriptionScolaire(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putimportInscriptionScolaire a échoué. ", ex));
												erreurInscriptionScolaire(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putimportInscriptionScolaire a échoué. ", c.cause()));
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("putimportInscriptionScolaire a échoué. ", b.cause()));
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("putimportInscriptionScolaire a échoué. ", a.cause()));
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePUTImportInscriptionScolaire(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, requeteSite.getOperationRequete(), json);
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());
				requeteSite2.setRequeteApi_(requeteApi);

				ListeRecherche<InscriptionScolaire> listeRecherche = new ListeRecherche<InscriptionScolaire>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(InscriptionScolaire.class);
				listeRecherche.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				listeRecherche.initLoinPourClasse(requeteSite2);

				if(listeRecherche.size() == 1) {
					InscriptionScolaire o = listeRecherche.getList().stream().findFirst().orElse(null);
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
							patchInscriptionScolaireFuture(o, true, a -> {
								if(a.succeeded()) {
								} else {
									erreurInscriptionScolaire(requeteSite2, gestionnaireEvenements, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postInscriptionScolaireFuture(requeteSite2, true, a -> {
							if(a.succeeded()) {
							} else {
								erreurInscriptionScolaire(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
					reponse200PUTImportInscriptionScolaire(requeteSite, gestionnaireEvenements);
				} else {
					erreurInscriptionScolaire(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}

	public void putimportInscriptionScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PUTImportInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							connexionSql.close(c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PUTImportInscriptionScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTFusion //

	@Override
	public void putfusionInscriptionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putfusionInscriptionScolaire a démarré. "));
			sqlInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurInscriptionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							putfusionInscriptionScolaireReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete, body);
											try {
												RequeteApi requeteApi = new RequeteApi();
												JsonArray jsonArray = Optional.ofNullable(requeteSite2.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												requeteApi.setRows(jsonArray.size());
												requeteApi.setNumFound(new Integer(jsonArray.size()).longValue());
												requeteApi.setNumPATCH(0L);
												requeteApi.initLoinRequeteApi(requeteSite2);
												requeteSite2.setRequeteApi_(requeteApi);
												requeteSite2.getVertx().eventBus().publish("websocketInscriptionScolaire", JsonObject.mapFrom(requeteApi).toString());
												sqlInscriptionScolaire(requeteSite2, d -> {
													if(d.succeeded()) {
														listePUTFusionInscriptionScolaire(requeteApi, requeteSite2, e -> {
															if(e.succeeded()) {
																putfusionInscriptionScolaireReponse(requeteSite2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putfusionInscriptionScolaire a réussi. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putfusionInscriptionScolaire a échoué. ", f.cause()));
																		erreurInscriptionScolaire(requeteSite2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putfusionInscriptionScolaire a échoué. ", e.cause()));
																erreurInscriptionScolaire(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putfusionInscriptionScolaire a échoué. ", d.cause()));
														erreurInscriptionScolaire(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putfusionInscriptionScolaire a échoué. ", ex));
												erreurInscriptionScolaire(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putfusionInscriptionScolaire a échoué. ", c.cause()));
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("putfusionInscriptionScolaire a échoué. ", b.cause()));
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("putfusionInscriptionScolaire a échoué. ", a.cause()));
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putfusionInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePUTFusionInscriptionScolaire(RequeteApi requeteApi, RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, requeteSite.getOperationRequete(), json);
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());
				requeteSite2.setRequeteApi_(requeteApi);

				ListeRecherche<InscriptionScolaire> listeRecherche = new ListeRecherche<InscriptionScolaire>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(InscriptionScolaire.class);
				listeRecherche.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				listeRecherche.initLoinPourClasse(requeteSite2);

				if(listeRecherche.size() == 1) {
					InscriptionScolaire o = listeRecherche.getList().stream().findFirst().orElse(null);
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
							patchInscriptionScolaireFuture(o, false, a -> {
								if(a.succeeded()) {
								} else {
									erreurInscriptionScolaire(requeteSite2, gestionnaireEvenements, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postInscriptionScolaireFuture(requeteSite2, false, a -> {
							if(a.succeeded()) {
							} else {
								erreurInscriptionScolaire(requeteSite2, gestionnaireEvenements, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
					reponse200PUTFusionInscriptionScolaire(requeteSite, gestionnaireEvenements);
				} else {
					erreurInscriptionScolaire(requeteApi.getRequeteSite_(), gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putfusionInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}

	public void putfusionInscriptionScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PUTFusionInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							connexionSql.close(c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putfusionInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PUTFusionInscriptionScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PUTCopie //

	@Override
	public void putcopieInscriptionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("putcopieInscriptionScolaire a démarré. "));
			sqlInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurInscriptionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							putcopieInscriptionScolaireReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete, body);
											try {
												rechercheInscriptionScolaire(requeteSite2, false, true, "/api/inscription/copie", "PUTCopie", d -> {
													if(d.succeeded()) {
														ListeRecherche<InscriptionScolaire> listeInscriptionScolaire = d.result();
														RequeteApi requeteApi = new RequeteApi();
														requeteApi.setRows(listeInscriptionScolaire.getRows());
														requeteApi.setNumFound(listeInscriptionScolaire.getQueryResponse().getResults().getNumFound());
														requeteApi.setNumPATCH(0L);
														requeteApi.initLoinRequeteApi(requeteSite2);
														requeteSite2.setRequeteApi_(requeteApi);
														requeteSite2.getVertx().eventBus().publish("websocketInscriptionScolaire", JsonObject.mapFrom(requeteApi).toString());
														sqlInscriptionScolaire(requeteSite2, e -> {
															if(e.succeeded()) {
																try {
																	listePUTCopieInscriptionScolaire(requeteApi, listeInscriptionScolaire, f -> {
																		if(f.succeeded()) {
																			putcopieInscriptionScolaireReponse(requeteSite2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("putcopieInscriptionScolaire a réussi. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("putcopieInscriptionScolaire a échoué. ", g.cause()));
																					erreurInscriptionScolaire(requeteSite2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("putcopieInscriptionScolaire a échoué. ", f.cause()));
																			erreurInscriptionScolaire(requeteSite2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("putcopieInscriptionScolaire a échoué. ", ex));
																	erreurInscriptionScolaire(requeteSite2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("putcopieInscriptionScolaire a échoué. ", e.cause()));
																erreurInscriptionScolaire(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putcopieInscriptionScolaire a échoué. ", d.cause()));
														erreurInscriptionScolaire(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putcopieInscriptionScolaire a échoué. ", ex));
												erreurInscriptionScolaire(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putcopieInscriptionScolaire a échoué. ", c.cause()));
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("putcopieInscriptionScolaire a échoué. ", b.cause()));
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("putcopieInscriptionScolaire a échoué. ", a.cause()));
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopieInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePUTCopieInscriptionScolaire(RequeteApi requeteApi, ListeRecherche<InscriptionScolaire> listeInscriptionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeInscriptionScolaire.getRequeteSite_();
		listeInscriptionScolaire.getList().forEach(o -> {
			futures.add(
				putcopieInscriptionScolaireFuture(requeteSite, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						InscriptionScolaire inscriptionScolaire = a.result();
					} else {
						erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				requeteApi.setNumPATCH(requeteApi.getNumPATCH() + listeInscriptionScolaire.size());
				if(listeInscriptionScolaire.next()) {
					listePUTCopieInscriptionScolaire(requeteApi, listeInscriptionScolaire, gestionnaireEvenements);
				} else {
					reponse200PUTCopieInscriptionScolaire(requeteSite, gestionnaireEvenements);
				}
			} else {
				erreurInscriptionScolaire(listeInscriptionScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<InscriptionScolaire> putcopieInscriptionScolaireFuture(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Handler<AsyncResult<InscriptionScolaire>> gestionnaireEvenements) {
		Promise<InscriptionScolaire> promise = Promise.promise();
		try {

			jsonObject.put("sauvegardes", Optional.ofNullable(jsonObject.getJsonArray("sauvegardes")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(requeteSite.getObjetJson()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("sauvegardes").add(o.getKey());
			});

			creerInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					InscriptionScolaire inscriptionScolaire = a.result();
					sqlPUTCopieInscriptionScolaire(inscriptionScolaire, jsonObject, b -> {
						if(b.succeeded()) {
							definirInscriptionScolaire(inscriptionScolaire, c -> {
								if(c.succeeded()) {
									attribuerInscriptionScolaire(inscriptionScolaire, d -> {
										if(d.succeeded()) {
											indexerInscriptionScolaire(inscriptionScolaire, e -> {
												if(e.succeeded()) {
													RequeteApi requeteApi = requeteSite.getRequeteApi_();
													if(requeteApi != null) {
														requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
														if(requeteApi.getNumFound() == 1L) {
															inscriptionScolaire.requeteApiInscriptionScolaire();
														}
														requeteSite.getVertx().eventBus().publish("websocketInscriptionScolaire", JsonObject.mapFrom(requeteApi).toString());
													}
													gestionnaireEvenements.handle(Future.succeededFuture(inscriptionScolaire));
													promise.complete(inscriptionScolaire);
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
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopieInscriptionScolaire(InscriptionScolaire o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
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
					case "anneeCle":
							{
						Long l = Long.parseLong(jsonObject.getString(entiteVar));
						putSql.append(SiteContexteFrFR.SQL_addA);
						putSqlParams.addAll(Arrays.asList("anneeCle", pk, "inscriptionCles", l));
						}
						break;
					case "blocCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContexteFrFR.SQL_addA);
							putSqlParams.addAll(Arrays.asList("blocCles", pk, "inscriptionCles", l));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("BlocScolaire");
							}
						}
						break;
					case "enfantCle":
							{
						Long l = Long.parseLong(jsonObject.getString(entiteVar));
						putSql.append(SiteContexteFrFR.SQL_addA);
						putSqlParams.addAll(Arrays.asList("enfantCle", pk, "inscriptionCles", l));
						}
						break;
					case "mereCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContexteFrFR.SQL_addA);
							putSqlParams.addAll(Arrays.asList("inscriptionCles", l, "mereCles", pk));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("MereScolaire");
							}
						}
						break;
					case "pereCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContexteFrFR.SQL_addA);
							putSqlParams.addAll(Arrays.asList("inscriptionCles", l, "pereCles", pk));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("PereScolaire");
							}
						}
						break;
					case "gardienCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContexteFrFR.SQL_addA);
							putSqlParams.addAll(Arrays.asList("gardienCles", pk, "inscriptionCles", l));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("GardienScolaire");
							}
						}
						break;
					case "paiementCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContexteFrFR.SQL_addA);
							putSqlParams.addAll(Arrays.asList("inscriptionCle", l, "paiementCles", pk));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("PaiementScolaire");
							}
						}
						break;
					case "utilisateurCles":
						for(Long l : jsonObject.getJsonArray(entiteVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContexteFrFR.SQL_addA);
							putSqlParams.addAll(Arrays.asList("inscriptionCles", l, "utilisateurCles", pk));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("UtilisateurSite");
							}
						}
						break;
					case "enfantNomComplet":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enfantNomComplet", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantNomCompletPrefere":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enfantNomCompletPrefere", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantDateNaissance":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enfantDateNaissance", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "ecoleAddresse":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("ecoleAddresse", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionApprouve":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionApprouve", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "inscriptionImmunisations":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionImmunisations", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "familleMarie":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("familleMarie", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "familleSepare":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("familleSepare", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "familleDivorce":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("familleDivorce", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "familleAddresse":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("familleAddresse", jsonObject.getString(entiteVar), pk));
						break;
					case "familleCommentVousConnaissezEcole":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("familleCommentVousConnaissezEcole", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionConsiderationsSpeciales":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionConsiderationsSpeciales", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantConditionsMedicales":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enfantConditionsMedicales", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantEcolesPrecedemmentFrequentees":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enfantEcolesPrecedemmentFrequentees", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantDescription":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enfantDescription", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantObjectifs":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enfantObjectifs", jsonObject.getString(entiteVar), pk));
						break;
					case "enfantPropre":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enfantPropre", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "inscriptionNomGroupe":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionNomGroupe", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionPaimentChaqueMois":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionPaimentChaqueMois", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "inscriptionPaimentComplet":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionPaimentComplet", jsonObject.getBoolean(entiteVar), pk));
						break;
					case "customerProfileId":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("customerProfileId", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionDateFrais":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionDateFrais", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionSignature1":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionSignature1", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature2":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionSignature2", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature3":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionSignature3", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature4":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionSignature4", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature5":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionSignature5", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature6":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionSignature6", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature7":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionSignature7", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature8":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionSignature8", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature9":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionSignature9", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionSignature10":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionSignature10", jsonObject.getString(entiteVar), pk));
						break;
					case "inscriptionDate1":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionDate1", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate2":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionDate2", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate3":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionDate3", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate4":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionDate4", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate5":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionDate5", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate6":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionDate6", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate7":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionDate7", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate8":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionDate8", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate9":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionDate9", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
						break;
					case "inscriptionDate10":
						putSql.append(SiteContexteFrFR.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inscriptionDate10", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entiteVar))), pk));
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

	public void putcopieInscriptionScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PUTCopieInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							connexionSql.close(c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopieInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PUTCopieInscriptionScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchInscriptionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("patchInscriptionScolaire a démarré. "));
			sqlInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurInscriptionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							patchInscriptionScolaireReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete, body);
											try {
												rechercheInscriptionScolaire(requeteSite2, false, true, "/api/inscription", "PATCH", d -> {
													if(d.succeeded()) {
														ListeRecherche<InscriptionScolaire> listeInscriptionScolaire = d.result();
														RequeteApi requeteApi = new RequeteApi();
														requeteApi.setRows(listeInscriptionScolaire.getRows());
														requeteApi.setNumFound(listeInscriptionScolaire.getQueryResponse().getResults().getNumFound());
														requeteApi.setNumPATCH(0L);
														requeteApi.initLoinRequeteApi(requeteSite2);
														requeteSite2.setRequeteApi_(requeteApi);
														requeteSite2.getVertx().eventBus().publish("websocketInscriptionScolaire", JsonObject.mapFrom(requeteApi).toString());
														SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeInscriptionScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
														Date date = null;
														if(facets != null)
															date = (Date)facets.get("max_modifie");
														String dt;
														if(date == null)
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
														else
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
														listeInscriptionScolaire.addFilterQuery(String.format("modifie_indexed_date:[* TO %s]", dt));

														InscriptionScolaire o = listeInscriptionScolaire.getList().stream().findFirst().orElse(null);
														sqlInscriptionScolaire(requeteSite2, e -> {
															if(e.succeeded()) {
																try {
																	listePATCHInscriptionScolaire(requeteApi, listeInscriptionScolaire, dt, f -> {
																		if(f.succeeded()) {
																			patchInscriptionScolaireReponse(requeteSite2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("patchInscriptionScolaire a réussi. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("patchInscriptionScolaire a échoué. ", g.cause()));
																					erreurInscriptionScolaire(requeteSite2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("patchInscriptionScolaire a échoué. ", f.cause()));
																			erreurInscriptionScolaire(requeteSite2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("patchInscriptionScolaire a échoué. ", ex));
																	erreurInscriptionScolaire(requeteSite2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("patchInscriptionScolaire a échoué. ", e.cause()));
																erreurInscriptionScolaire(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("patchInscriptionScolaire a échoué. ", d.cause()));
														erreurInscriptionScolaire(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("patchInscriptionScolaire a échoué. ", ex));
												erreurInscriptionScolaire(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("patchInscriptionScolaire a échoué. ", c.cause()));
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("patchInscriptionScolaire a échoué. ", b.cause()));
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("patchInscriptionScolaire a échoué. ", a.cause()));
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePATCHInscriptionScolaire(RequeteApi requeteApi, ListeRecherche<InscriptionScolaire> listeInscriptionScolaire, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeInscriptionScolaire.getRequeteSite_();
		listeInscriptionScolaire.getList().forEach(o -> {
			futures.add(
				patchInscriptionScolaireFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listeInscriptionScolaire.next(dt)) {
					listePATCHInscriptionScolaire(requeteApi, listeInscriptionScolaire, dt, gestionnaireEvenements);
				} else {
					reponse200PATCHInscriptionScolaire(requeteSite, gestionnaireEvenements);
				}
			} else {
				erreurInscriptionScolaire(listeInscriptionScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<InscriptionScolaire> patchInscriptionScolaireFuture(InscriptionScolaire o, Boolean inheritPk, Handler<AsyncResult<InscriptionScolaire>> gestionnaireEvenements) {
		Promise<InscriptionScolaire> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			if(requeteApi != null && requeteApi.getNumFound() == 1L) {
				requeteApi.setOriginal(o);
				requeteApi.setPk(o.getPk());
			}
			sqlPATCHInscriptionScolaire(o, inheritPk, a -> {
				if(a.succeeded()) {
					InscriptionScolaire inscriptionScolaire = a.result();
					definirInscriptionScolaire(inscriptionScolaire, b -> {
						if(b.succeeded()) {
							attribuerInscriptionScolaire(inscriptionScolaire, c -> {
								if(c.succeeded()) {
									indexerInscriptionScolaire(inscriptionScolaire, d -> {
										if(d.succeeded()) {
											if(requeteApi != null) {
												requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
												if(requeteApi.getNumFound() == 1L) {
													inscriptionScolaire.requeteApiInscriptionScolaire();
												}
												requeteSite.getVertx().eventBus().publish("websocketInscriptionScolaire", JsonObject.mapFrom(requeteApi).toString());
											}
											gestionnaireEvenements.handle(Future.succeededFuture(inscriptionScolaire));
											promise.complete(inscriptionScolaire);
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
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHInscriptionScolaire(InscriptionScolaire o, Boolean inheritPk, Handler<AsyncResult<InscriptionScolaire>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject jsonObject = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = jsonObject.fieldNames();
			InscriptionScolaire o2 = new InscriptionScolaire();

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
					case "setAnneeCle":
						{
							Long l = o2.getAnneeCle();
							if(l != null) {
								ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AnneeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setAnneeCle(jsonObject.getString(methodeNom));
									patchSql.append(SiteContexteFrFR.SQL_setA1);
									patchSqlParams.addAll(Arrays.asList("anneeCle", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("AnneeScolaire");
									}
								}
							}
						}
						break;
					case "removeAnneeCle":
						{
							Long l = o2.getAnneeCle();
							if(l != null) {
								ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AnneeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setAnneeCle(jsonObject.getString(methodeNom));
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("anneeCle", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("AnneeScolaire");
									}
								}
							}
						}
						break;
					case "addBlocCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<BlocScolaire> listeRecherche = new ListeRecherche<BlocScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(BlocScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("blocCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("BlocScolaire");
									}
								}
							}
						}
						break;
					case "addAllBlocCles":
						JsonArray addAllBlocClesValeurs = jsonObject.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllBlocClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllBlocClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<BlocScolaire> listeRecherche = new ListeRecherche<BlocScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(BlocScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("blocCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("BlocScolaire");
									}
								}
							}
						}
						break;
					case "setBlocCles":
						JsonArray setBlocClesValeurs = jsonObject.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("blocCles", pk, "inscriptionCles"));
						for(Integer i = 0; i <  setBlocClesValeurs.size(); i++) {
							Long l = Long.parseLong(setBlocClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<BlocScolaire> listeRecherche = new ListeRecherche<BlocScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(BlocScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("blocCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("BlocScolaire");
									}
								}
							}
						}
						break;
					case "removeBlocCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<BlocScolaire> listeRecherche = new ListeRecherche<BlocScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(BlocScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("blocCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("BlocScolaire");
									}
								}
							}
						}
						break;
					case "setEnfantCle":
						{
							Long l = o2.getEnfantCle();
							if(l != null) {
								ListeRecherche<EnfantScolaire> listeRecherche = new ListeRecherche<EnfantScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(EnfantScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setEnfantCle(jsonObject.getString(methodeNom));
									patchSql.append(SiteContexteFrFR.SQL_setA1);
									patchSqlParams.addAll(Arrays.asList("enfantCle", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("EnfantScolaire");
									}
								}
							}
						}
						break;
					case "removeEnfantCle":
						{
							Long l = o2.getEnfantCle();
							if(l != null) {
								ListeRecherche<EnfantScolaire> listeRecherche = new ListeRecherche<EnfantScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(EnfantScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setEnfantCle(jsonObject.getString(methodeNom));
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("enfantCle", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("EnfantScolaire");
									}
								}
							}
						}
						break;
					case "addMereCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<MereScolaire> listeRecherche = new ListeRecherche<MereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(MereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "mereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("MereScolaire");
									}
								}
							}
						}
						break;
					case "addAllMereCles":
						JsonArray addAllMereClesValeurs = jsonObject.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllMereClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllMereClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<MereScolaire> listeRecherche = new ListeRecherche<MereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(MereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "mereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("MereScolaire");
									}
								}
							}
						}
						break;
					case "setMereCles":
						JsonArray setMereClesValeurs = jsonObject.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("inscriptionCles", "mereCles", pk));
						for(Integer i = 0; i <  setMereClesValeurs.size(); i++) {
							Long l = Long.parseLong(setMereClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<MereScolaire> listeRecherche = new ListeRecherche<MereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(MereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "mereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("MereScolaire");
									}
								}
							}
						}
						break;
					case "removeMereCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<MereScolaire> listeRecherche = new ListeRecherche<MereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(MereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "mereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("MereScolaire");
									}
								}
							}
						}
						break;
					case "addPereCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<PereScolaire> listeRecherche = new ListeRecherche<PereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "pereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PereScolaire");
									}
								}
							}
						}
						break;
					case "addAllPereCles":
						JsonArray addAllPereClesValeurs = jsonObject.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllPereClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllPereClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<PereScolaire> listeRecherche = new ListeRecherche<PereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "pereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PereScolaire");
									}
								}
							}
						}
						break;
					case "setPereCles":
						JsonArray setPereClesValeurs = jsonObject.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("inscriptionCles", "pereCles", pk));
						for(Integer i = 0; i <  setPereClesValeurs.size(); i++) {
							Long l = Long.parseLong(setPereClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<PereScolaire> listeRecherche = new ListeRecherche<PereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "pereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PereScolaire");
									}
								}
							}
						}
						break;
					case "removePereCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<PereScolaire> listeRecherche = new ListeRecherche<PereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "pereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PereScolaire");
									}
								}
							}
						}
						break;
					case "addGardienCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<GardienScolaire> listeRecherche = new ListeRecherche<GardienScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(GardienScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("gardienCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("GardienScolaire");
									}
								}
							}
						}
						break;
					case "addAllGardienCles":
						JsonArray addAllGardienClesValeurs = jsonObject.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllGardienClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllGardienClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<GardienScolaire> listeRecherche = new ListeRecherche<GardienScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(GardienScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("gardienCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("GardienScolaire");
									}
								}
							}
						}
						break;
					case "setGardienCles":
						JsonArray setGardienClesValeurs = jsonObject.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("gardienCles", pk, "inscriptionCles"));
						for(Integer i = 0; i <  setGardienClesValeurs.size(); i++) {
							Long l = Long.parseLong(setGardienClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<GardienScolaire> listeRecherche = new ListeRecherche<GardienScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(GardienScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("gardienCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("GardienScolaire");
									}
								}
							}
						}
						break;
					case "removeGardienCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<GardienScolaire> listeRecherche = new ListeRecherche<GardienScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(GardienScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("gardienCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("GardienScolaire");
									}
								}
							}
						}
						break;
					case "addPaiementCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PaiementScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCle", l, "paiementCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PaiementScolaire");
									}
								}
							}
						}
						break;
					case "addAllPaiementCles":
						JsonArray addAllPaiementClesValeurs = jsonObject.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllPaiementClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllPaiementClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PaiementScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCle", l, "paiementCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PaiementScolaire");
									}
								}
							}
						}
						break;
					case "setPaiementCles":
						JsonArray setPaiementClesValeurs = jsonObject.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("inscriptionCle", "paiementCles", pk));
						for(Integer i = 0; i <  setPaiementClesValeurs.size(); i++) {
							Long l = Long.parseLong(setPaiementClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PaiementScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCle", l, "paiementCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PaiementScolaire");
									}
								}
							}
						}
						break;
					case "removePaiementCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PaiementScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCle", l, "paiementCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PaiementScolaire");
									}
								}
							}
						}
						break;
					case "addUtilisateurCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<UtilisateurSite> listeRecherche = new ListeRecherche<UtilisateurSite>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(UtilisateurSite.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "utilisateurCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("UtilisateurSite");
									}
								}
							}
						}
						break;
					case "addAllUtilisateurCles":
						JsonArray addAllUtilisateurClesValeurs = jsonObject.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllUtilisateurClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllUtilisateurClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<UtilisateurSite> listeRecherche = new ListeRecherche<UtilisateurSite>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(UtilisateurSite.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "utilisateurCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("UtilisateurSite");
									}
								}
							}
						}
						break;
					case "setUtilisateurCles":
						JsonArray setUtilisateurClesValeurs = jsonObject.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("inscriptionCles", "utilisateurCles", pk));
						for(Integer i = 0; i <  setUtilisateurClesValeurs.size(); i++) {
							Long l = Long.parseLong(setUtilisateurClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<UtilisateurSite> listeRecherche = new ListeRecherche<UtilisateurSite>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(UtilisateurSite.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "utilisateurCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("UtilisateurSite");
									}
								}
							}
						}
						break;
					case "removeUtilisateurCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<UtilisateurSite> listeRecherche = new ListeRecherche<UtilisateurSite>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(UtilisateurSite.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "utilisateurCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("UtilisateurSite");
									}
								}
							}
						}
						break;
					case "setEnfantNomComplet":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enfantNomComplet"));
						} else {
							o2.setEnfantNomComplet(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enfantNomComplet", o2.jsonEnfantNomComplet(), pk));
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
					case "setEcoleAddresse":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "ecoleAddresse"));
						} else {
							o2.setEcoleAddresse(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("ecoleAddresse", o2.jsonEcoleAddresse(), pk));
						}
						break;
					case "setInscriptionApprouve":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionApprouve"));
						} else {
							o2.setInscriptionApprouve(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionApprouve", o2.jsonInscriptionApprouve(), pk));
						}
						break;
					case "setInscriptionImmunisations":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionImmunisations"));
						} else {
							o2.setInscriptionImmunisations(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionImmunisations", o2.jsonInscriptionImmunisations(), pk));
						}
						break;
					case "setFamilleMarie":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familleMarie"));
						} else {
							o2.setFamilleMarie(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familleMarie", o2.jsonFamilleMarie(), pk));
						}
						break;
					case "setFamilleSepare":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familleSepare"));
						} else {
							o2.setFamilleSepare(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familleSepare", o2.jsonFamilleSepare(), pk));
						}
						break;
					case "setFamilleDivorce":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familleDivorce"));
						} else {
							o2.setFamilleDivorce(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familleDivorce", o2.jsonFamilleDivorce(), pk));
						}
						break;
					case "setFamilleAddresse":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familleAddresse"));
						} else {
							o2.setFamilleAddresse(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familleAddresse", o2.jsonFamilleAddresse(), pk));
						}
						break;
					case "setFamilleCommentVousConnaissezEcole":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familleCommentVousConnaissezEcole"));
						} else {
							o2.setFamilleCommentVousConnaissezEcole(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familleCommentVousConnaissezEcole", o2.jsonFamilleCommentVousConnaissezEcole(), pk));
						}
						break;
					case "setInscriptionConsiderationsSpeciales":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionConsiderationsSpeciales"));
						} else {
							o2.setInscriptionConsiderationsSpeciales(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionConsiderationsSpeciales", o2.jsonInscriptionConsiderationsSpeciales(), pk));
						}
						break;
					case "setEnfantConditionsMedicales":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enfantConditionsMedicales"));
						} else {
							o2.setEnfantConditionsMedicales(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enfantConditionsMedicales", o2.jsonEnfantConditionsMedicales(), pk));
						}
						break;
					case "setEnfantEcolesPrecedemmentFrequentees":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enfantEcolesPrecedemmentFrequentees"));
						} else {
							o2.setEnfantEcolesPrecedemmentFrequentees(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enfantEcolesPrecedemmentFrequentees", o2.jsonEnfantEcolesPrecedemmentFrequentees(), pk));
						}
						break;
					case "setEnfantDescription":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enfantDescription"));
						} else {
							o2.setEnfantDescription(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enfantDescription", o2.jsonEnfantDescription(), pk));
						}
						break;
					case "setEnfantObjectifs":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enfantObjectifs"));
						} else {
							o2.setEnfantObjectifs(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enfantObjectifs", o2.jsonEnfantObjectifs(), pk));
						}
						break;
					case "setEnfantPropre":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enfantPropre"));
						} else {
							o2.setEnfantPropre(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enfantPropre", o2.jsonEnfantPropre(), pk));
						}
						break;
					case "setInscriptionNomGroupe":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionNomGroupe"));
						} else {
							o2.setInscriptionNomGroupe(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionNomGroupe", o2.jsonInscriptionNomGroupe(), pk));
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
					case "setInscriptionDateFrais":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDateFrais"));
						} else {
							o2.setInscriptionDateFrais(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDateFrais", o2.jsonInscriptionDateFrais(), pk));
						}
						break;
					case "setInscriptionSignature1":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature1"));
						} else {
							o2.setInscriptionSignature1(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature1", o2.jsonInscriptionSignature1(), pk));
						}
						break;
					case "setInscriptionSignature2":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature2"));
						} else {
							o2.setInscriptionSignature2(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature2", o2.jsonInscriptionSignature2(), pk));
						}
						break;
					case "setInscriptionSignature3":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature3"));
						} else {
							o2.setInscriptionSignature3(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature3", o2.jsonInscriptionSignature3(), pk));
						}
						break;
					case "setInscriptionSignature4":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature4"));
						} else {
							o2.setInscriptionSignature4(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature4", o2.jsonInscriptionSignature4(), pk));
						}
						break;
					case "setInscriptionSignature5":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature5"));
						} else {
							o2.setInscriptionSignature5(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature5", o2.jsonInscriptionSignature5(), pk));
						}
						break;
					case "setInscriptionSignature6":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature6"));
						} else {
							o2.setInscriptionSignature6(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature6", o2.jsonInscriptionSignature6(), pk));
						}
						break;
					case "setInscriptionSignature7":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature7"));
						} else {
							o2.setInscriptionSignature7(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature7", o2.jsonInscriptionSignature7(), pk));
						}
						break;
					case "setInscriptionSignature8":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature8"));
						} else {
							o2.setInscriptionSignature8(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature8", o2.jsonInscriptionSignature8(), pk));
						}
						break;
					case "setInscriptionSignature9":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature9"));
						} else {
							o2.setInscriptionSignature9(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature9", o2.jsonInscriptionSignature9(), pk));
						}
						break;
					case "setInscriptionSignature10":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature10"));
						} else {
							o2.setInscriptionSignature10(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature10", o2.jsonInscriptionSignature10(), pk));
						}
						break;
					case "setInscriptionDate1":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate1"));
						} else {
							o2.setInscriptionDate1(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate1", o2.jsonInscriptionDate1(), pk));
						}
						break;
					case "setInscriptionDate2":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate2"));
						} else {
							o2.setInscriptionDate2(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate2", o2.jsonInscriptionDate2(), pk));
						}
						break;
					case "setInscriptionDate3":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate3"));
						} else {
							o2.setInscriptionDate3(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate3", o2.jsonInscriptionDate3(), pk));
						}
						break;
					case "setInscriptionDate4":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate4"));
						} else {
							o2.setInscriptionDate4(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate4", o2.jsonInscriptionDate4(), pk));
						}
						break;
					case "setInscriptionDate5":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate5"));
						} else {
							o2.setInscriptionDate5(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate5", o2.jsonInscriptionDate5(), pk));
						}
						break;
					case "setInscriptionDate6":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate6"));
						} else {
							o2.setInscriptionDate6(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate6", o2.jsonInscriptionDate6(), pk));
						}
						break;
					case "setInscriptionDate7":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate7"));
						} else {
							o2.setInscriptionDate7(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate7", o2.jsonInscriptionDate7(), pk));
						}
						break;
					case "setInscriptionDate8":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate8"));
						} else {
							o2.setInscriptionDate8(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate8", o2.jsonInscriptionDate8(), pk));
						}
						break;
					case "setInscriptionDate9":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate9"));
						} else {
							o2.setInscriptionDate9(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate9", o2.jsonInscriptionDate9(), pk));
						}
						break;
					case "setInscriptionDate10":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate10"));
						} else {
							o2.setInscriptionDate10(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate10", o2.jsonInscriptionDate10(), pk));
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
					InscriptionScolaire o3 = new InscriptionScolaire();
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

	public void patchInscriptionScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PATCHInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							connexionSql.close(c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PATCHInscriptionScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getInscriptionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete);
		try {
			sqlInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurInscriptionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheInscriptionScolaire(requeteSite, false, true, "/api/inscription/{id}", "GET", c -> {
								if(c.succeeded()) {
									ListeRecherche<InscriptionScolaire> listeInscriptionScolaire = c.result();
									getInscriptionScolaireReponse(listeInscriptionScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getInscriptionScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("getInscriptionScolaire a échoué. ", d.cause()));
											erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("getInscriptionScolaire a échoué. ", c.cause()));
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("getInscriptionScolaire a échoué. ", b.cause()));
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("getInscriptionScolaire a échoué. ", a.cause()));
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void getInscriptionScolaireReponse(ListeRecherche<InscriptionScolaire> listeInscriptionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeInscriptionScolaire.getRequeteSite_();
		try {
			reponse200GETInscriptionScolaire(listeInscriptionScolaire, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							connexionSql.close(c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200GETInscriptionScolaire(ListeRecherche<InscriptionScolaire> listeInscriptionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeInscriptionScolaire.getRequeteSite_();
			SolrDocumentList documentsSolr = listeInscriptionScolaire.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listeInscriptionScolaire.getList().stream().findFirst().orElse(null));
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void rechercheInscriptionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete);
		try {
			sqlInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurInscriptionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheInscriptionScolaire(requeteSite, false, true, "/api/inscription", "Recherche", c -> {
								if(c.succeeded()) {
									ListeRecherche<InscriptionScolaire> listeInscriptionScolaire = c.result();
									rechercheInscriptionScolaireReponse(listeInscriptionScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("rechercheInscriptionScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("rechercheInscriptionScolaire a échoué. ", d.cause()));
											erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("rechercheInscriptionScolaire a échoué. ", c.cause()));
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("rechercheInscriptionScolaire a échoué. ", b.cause()));
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("rechercheInscriptionScolaire a échoué. ", a.cause()));
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("rechercheInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void rechercheInscriptionScolaireReponse(ListeRecherche<InscriptionScolaire> listeInscriptionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeInscriptionScolaire.getRequeteSite_();
		try {
			reponse200RechercheInscriptionScolaire(listeInscriptionScolaire, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							connexionSql.close(c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("rechercheInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200RechercheInscriptionScolaire(ListeRecherche<InscriptionScolaire> listeInscriptionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeInscriptionScolaire.getRequeteSite_();
			QueryResponse reponseRecherche = listeInscriptionScolaire.getQueryResponse();
			SolrDocumentList documentsSolr = listeInscriptionScolaire.getSolrDocumentList();
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
			listeInscriptionScolaire.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listeInscriptionScolaire.getFields();
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

	// RechercheAdmin //

	@Override
	public void rechercheadminInscriptionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete);
		try {
			sqlInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurInscriptionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheInscriptionScolaire(requeteSite, false, true, "/api/admin/inscription", "RechercheAdmin", c -> {
								if(c.succeeded()) {
									ListeRecherche<InscriptionScolaire> listeInscriptionScolaire = c.result();
									rechercheadminInscriptionScolaireReponse(listeInscriptionScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("rechercheadminInscriptionScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("rechercheadminInscriptionScolaire a échoué. ", d.cause()));
											erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("rechercheadminInscriptionScolaire a échoué. ", c.cause()));
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("rechercheadminInscriptionScolaire a échoué. ", b.cause()));
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("rechercheadminInscriptionScolaire a échoué. ", a.cause()));
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("rechercheadminInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void rechercheadminInscriptionScolaireReponse(ListeRecherche<InscriptionScolaire> listeInscriptionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeInscriptionScolaire.getRequeteSite_();
		try {
			reponse200RechercheAdminInscriptionScolaire(listeInscriptionScolaire, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							connexionSql.close(c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("rechercheadminInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200RechercheAdminInscriptionScolaire(ListeRecherche<InscriptionScolaire> listeInscriptionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeInscriptionScolaire.getRequeteSite_();
			QueryResponse reponseRecherche = listeInscriptionScolaire.getQueryResponse();
			SolrDocumentList documentsSolr = listeInscriptionScolaire.getSolrDocumentList();
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
			listeInscriptionScolaire.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listeInscriptionScolaire.getFields();
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

	// PATCHPaiements //

	@Override
	public void patchpaiementsInscriptionScolaire(JsonObject body, OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete, body);
		try {
			LOGGER.info(String.format("patchpaiementsInscriptionScolaire a démarré. "));
			sqlInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurInscriptionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							patchpaiementsInscriptionScolaireReponse(requeteSite, c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(c.result()));
									WorkerExecutor executeurTravailleur = siteContexte.getExecuteurTravailleur();
									executeurTravailleur.executeBlocking(
										blockingCodeHandler -> {
											RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete, body);
											try {
												rechercheInscriptionScolaire(requeteSite2, false, true, "/api/inscription", "PATCHPaiements", d -> {
													if(d.succeeded()) {
														ListeRecherche<InscriptionScolaire> listeInscriptionScolaire = d.result();
														RequeteApi requeteApi = new RequeteApi();
														requeteApi.setRows(listeInscriptionScolaire.getRows());
														requeteApi.setNumFound(listeInscriptionScolaire.getQueryResponse().getResults().getNumFound());
														requeteApi.setNumPATCH(0L);
														requeteApi.initLoinRequeteApi(requeteSite2);
														requeteSite2.setRequeteApi_(requeteApi);
														requeteSite2.getVertx().eventBus().publish("websocketInscriptionScolaire", JsonObject.mapFrom(requeteApi).toString());
														SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeInscriptionScolaire.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
														Date date = null;
														if(facets != null)
															date = (Date)facets.get("max_modifie");
														String dt;
														if(date == null)
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
														else
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
														listeInscriptionScolaire.addFilterQuery(String.format("modifie_indexed_date:[* TO %s]", dt));

														InscriptionScolaire o = listeInscriptionScolaire.getList().stream().findFirst().orElse(null);
														sqlInscriptionScolaire(requeteSite2, e -> {
															if(e.succeeded()) {
																try {
																	listePATCHPaiementsInscriptionScolaire(requeteApi, listeInscriptionScolaire, dt, f -> {
																		if(f.succeeded()) {
																			patchpaiementsInscriptionScolaireReponse(requeteSite2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("patchpaiementsInscriptionScolaire a réussi. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("patchpaiementsInscriptionScolaire a échoué. ", g.cause()));
																					erreurInscriptionScolaire(requeteSite2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("patchpaiementsInscriptionScolaire a échoué. ", f.cause()));
																			erreurInscriptionScolaire(requeteSite2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("patchpaiementsInscriptionScolaire a échoué. ", ex));
																	erreurInscriptionScolaire(requeteSite2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("patchpaiementsInscriptionScolaire a échoué. ", e.cause()));
																erreurInscriptionScolaire(requeteSite2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("patchpaiementsInscriptionScolaire a échoué. ", d.cause()));
														erreurInscriptionScolaire(requeteSite2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("patchpaiementsInscriptionScolaire a échoué. ", ex));
												erreurInscriptionScolaire(requeteSite2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("patchpaiementsInscriptionScolaire a échoué. ", c.cause()));
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("patchpaiementsInscriptionScolaire a échoué. ", b.cause()));
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("patchpaiementsInscriptionScolaire a échoué. ", a.cause()));
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchpaiementsInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void listePATCHPaiementsInscriptionScolaire(RequeteApi requeteApi, ListeRecherche<InscriptionScolaire> listeInscriptionScolaire, String dt, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeInscriptionScolaire.getRequeteSite_();
		listeInscriptionScolaire.getList().forEach(o -> {
			futures.add(
				patchpaiementsInscriptionScolaireFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listeInscriptionScolaire.next(dt)) {
					listePATCHPaiementsInscriptionScolaire(requeteApi, listeInscriptionScolaire, dt, gestionnaireEvenements);
				} else {
					reponse200PATCHPaiementsInscriptionScolaire(requeteSite, gestionnaireEvenements);
				}
			} else {
				erreurInscriptionScolaire(listeInscriptionScolaire.getRequeteSite_(), gestionnaireEvenements, a);
			}
		});
	}

	public Future<InscriptionScolaire> patchpaiementsInscriptionScolaireFuture(InscriptionScolaire o, Boolean inheritPk, Handler<AsyncResult<InscriptionScolaire>> gestionnaireEvenements) {
		Promise<InscriptionScolaire> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			if(requeteApi != null && requeteApi.getNumFound() == 1L) {
				requeteApi.setOriginal(o);
				requeteApi.setPk(o.getPk());
			}
			sqlPATCHPaiementsInscriptionScolaire(o, inheritPk, a -> {
				if(a.succeeded()) {
					InscriptionScolaire inscriptionScolaire = a.result();
					definirInscriptionScolaire(inscriptionScolaire, b -> {
						if(b.succeeded()) {
							attribuerInscriptionScolaire(inscriptionScolaire, c -> {
								if(c.succeeded()) {
									indexerInscriptionScolaire(inscriptionScolaire, d -> {
										if(d.succeeded()) {
											if(requeteApi != null) {
												requeteApi.setNumPATCH(requeteApi.getNumPATCH() + 1);
												if(requeteApi.getNumFound() == 1L) {
													inscriptionScolaire.requeteApiInscriptionScolaire();
												}
												requeteSite.getVertx().eventBus().publish("websocketInscriptionScolaire", JsonObject.mapFrom(requeteApi).toString());
											}
											gestionnaireEvenements.handle(Future.succeededFuture(inscriptionScolaire));
											promise.complete(inscriptionScolaire);
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
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHPaiementsInscriptionScolaire(InscriptionScolaire o, Boolean inheritPk, Handler<AsyncResult<InscriptionScolaire>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			JsonObject jsonObject = requeteSite.getObjetJson();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodeNoms = jsonObject.fieldNames();
			InscriptionScolaire o2 = new InscriptionScolaire();

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
					case "setAnneeCle":
						{
							Long l = o2.getAnneeCle();
							if(l != null) {
								ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AnneeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setAnneeCle(jsonObject.getString(methodeNom));
									patchSql.append(SiteContexteFrFR.SQL_setA1);
									patchSqlParams.addAll(Arrays.asList("anneeCle", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("AnneeScolaire");
									}
								}
							}
						}
						break;
					case "removeAnneeCle":
						{
							Long l = o2.getAnneeCle();
							if(l != null) {
								ListeRecherche<AnneeScolaire> listeRecherche = new ListeRecherche<AnneeScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(AnneeScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setAnneeCle(jsonObject.getString(methodeNom));
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("anneeCle", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("AnneeScolaire");
									}
								}
							}
						}
						break;
					case "addBlocCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<BlocScolaire> listeRecherche = new ListeRecherche<BlocScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(BlocScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("blocCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("BlocScolaire");
									}
								}
							}
						}
						break;
					case "addAllBlocCles":
						JsonArray addAllBlocClesValeurs = jsonObject.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllBlocClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllBlocClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<BlocScolaire> listeRecherche = new ListeRecherche<BlocScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(BlocScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("blocCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("BlocScolaire");
									}
								}
							}
						}
						break;
					case "setBlocCles":
						JsonArray setBlocClesValeurs = jsonObject.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("blocCles", pk, "inscriptionCles"));
						for(Integer i = 0; i <  setBlocClesValeurs.size(); i++) {
							Long l = Long.parseLong(setBlocClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<BlocScolaire> listeRecherche = new ListeRecherche<BlocScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(BlocScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("blocCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("BlocScolaire");
									}
								}
							}
						}
						break;
					case "removeBlocCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<BlocScolaire> listeRecherche = new ListeRecherche<BlocScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(BlocScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("blocCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("BlocScolaire");
									}
								}
							}
						}
						break;
					case "setEnfantCle":
						{
							Long l = o2.getEnfantCle();
							if(l != null) {
								ListeRecherche<EnfantScolaire> listeRecherche = new ListeRecherche<EnfantScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(EnfantScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setEnfantCle(jsonObject.getString(methodeNom));
									patchSql.append(SiteContexteFrFR.SQL_setA1);
									patchSqlParams.addAll(Arrays.asList("enfantCle", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("EnfantScolaire");
									}
								}
							}
						}
						break;
					case "removeEnfantCle":
						{
							Long l = o2.getEnfantCle();
							if(l != null) {
								ListeRecherche<EnfantScolaire> listeRecherche = new ListeRecherche<EnfantScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(EnfantScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setEnfantCle(jsonObject.getString(methodeNom));
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("enfantCle", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("EnfantScolaire");
									}
								}
							}
						}
						break;
					case "addMereCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<MereScolaire> listeRecherche = new ListeRecherche<MereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(MereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "mereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("MereScolaire");
									}
								}
							}
						}
						break;
					case "addAllMereCles":
						JsonArray addAllMereClesValeurs = jsonObject.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllMereClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllMereClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<MereScolaire> listeRecherche = new ListeRecherche<MereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(MereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "mereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("MereScolaire");
									}
								}
							}
						}
						break;
					case "setMereCles":
						JsonArray setMereClesValeurs = jsonObject.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("inscriptionCles", "mereCles", pk));
						for(Integer i = 0; i <  setMereClesValeurs.size(); i++) {
							Long l = Long.parseLong(setMereClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<MereScolaire> listeRecherche = new ListeRecherche<MereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(MereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "mereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("MereScolaire");
									}
								}
							}
						}
						break;
					case "removeMereCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<MereScolaire> listeRecherche = new ListeRecherche<MereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(MereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "mereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("MereScolaire");
									}
								}
							}
						}
						break;
					case "addPereCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<PereScolaire> listeRecherche = new ListeRecherche<PereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "pereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PereScolaire");
									}
								}
							}
						}
						break;
					case "addAllPereCles":
						JsonArray addAllPereClesValeurs = jsonObject.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllPereClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllPereClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<PereScolaire> listeRecherche = new ListeRecherche<PereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "pereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PereScolaire");
									}
								}
							}
						}
						break;
					case "setPereCles":
						JsonArray setPereClesValeurs = jsonObject.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("inscriptionCles", "pereCles", pk));
						for(Integer i = 0; i <  setPereClesValeurs.size(); i++) {
							Long l = Long.parseLong(setPereClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<PereScolaire> listeRecherche = new ListeRecherche<PereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "pereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PereScolaire");
									}
								}
							}
						}
						break;
					case "removePereCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<PereScolaire> listeRecherche = new ListeRecherche<PereScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PereScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "pereCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PereScolaire");
									}
								}
							}
						}
						break;
					case "addGardienCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<GardienScolaire> listeRecherche = new ListeRecherche<GardienScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(GardienScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("gardienCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("GardienScolaire");
									}
								}
							}
						}
						break;
					case "addAllGardienCles":
						JsonArray addAllGardienClesValeurs = jsonObject.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllGardienClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllGardienClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<GardienScolaire> listeRecherche = new ListeRecherche<GardienScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(GardienScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("gardienCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("GardienScolaire");
									}
								}
							}
						}
						break;
					case "setGardienCles":
						JsonArray setGardienClesValeurs = jsonObject.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("gardienCles", pk, "inscriptionCles"));
						for(Integer i = 0; i <  setGardienClesValeurs.size(); i++) {
							Long l = Long.parseLong(setGardienClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<GardienScolaire> listeRecherche = new ListeRecherche<GardienScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(GardienScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("gardienCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("GardienScolaire");
									}
								}
							}
						}
						break;
					case "removeGardienCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<GardienScolaire> listeRecherche = new ListeRecherche<GardienScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(GardienScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("gardienCles", pk, "inscriptionCles", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("GardienScolaire");
									}
								}
							}
						}
						break;
					case "addPaiementCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PaiementScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCle", l, "paiementCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PaiementScolaire");
									}
								}
							}
						}
						break;
					case "addAllPaiementCles":
						JsonArray addAllPaiementClesValeurs = jsonObject.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllPaiementClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllPaiementClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PaiementScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCle", l, "paiementCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PaiementScolaire");
									}
								}
							}
						}
						break;
					case "setPaiementCles":
						JsonArray setPaiementClesValeurs = jsonObject.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("inscriptionCle", "paiementCles", pk));
						for(Integer i = 0; i <  setPaiementClesValeurs.size(); i++) {
							Long l = Long.parseLong(setPaiementClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PaiementScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCle", l, "paiementCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PaiementScolaire");
									}
								}
							}
						}
						break;
					case "removePaiementCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(PaiementScolaire.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCle", l, "paiementCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PaiementScolaire");
									}
								}
							}
						}
						break;
					case "addUtilisateurCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<UtilisateurSite> listeRecherche = new ListeRecherche<UtilisateurSite>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(UtilisateurSite.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "utilisateurCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("UtilisateurSite");
									}
								}
							}
						}
						break;
					case "addAllUtilisateurCles":
						JsonArray addAllUtilisateurClesValeurs = jsonObject.getJsonArray(methodeNom);
						for(Integer i = 0; i <  addAllUtilisateurClesValeurs.size(); i++) {
							Long l = Long.parseLong(addAllUtilisateurClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<UtilisateurSite> listeRecherche = new ListeRecherche<UtilisateurSite>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(UtilisateurSite.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "utilisateurCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("UtilisateurSite");
									}
								}
							}
						}
						break;
					case "setUtilisateurCles":
						JsonArray setUtilisateurClesValeurs = jsonObject.getJsonArray(methodeNom);
						patchSql.append(SiteContexteFrFR.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("inscriptionCles", "utilisateurCles", pk));
						for(Integer i = 0; i <  setUtilisateurClesValeurs.size(); i++) {
							Long l = Long.parseLong(setUtilisateurClesValeurs.getString(i));
							if(l != null) {
								ListeRecherche<UtilisateurSite> listeRecherche = new ListeRecherche<UtilisateurSite>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(UtilisateurSite.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "utilisateurCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("UtilisateurSite");
									}
								}
							}
						}
						break;
					case "removeUtilisateurCles":
						{
							Long l = Long.parseLong(jsonObject.getString(methodeNom));
							if(l != null) {
								ListeRecherche<UtilisateurSite> listeRecherche = new ListeRecherche<UtilisateurSite>();
								listeRecherche.setQuery("*:*");
								listeRecherche.setStocker(true);
								listeRecherche.setC(UtilisateurSite.class);
								listeRecherche.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								listeRecherche.initLoinListeRecherche(requeteSite);
								l = Optional.ofNullable(listeRecherche.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContexteFrFR.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("inscriptionCles", l, "utilisateurCles", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("UtilisateurSite");
									}
								}
							}
						}
						break;
					case "setEnfantNomComplet":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enfantNomComplet"));
						} else {
							o2.setEnfantNomComplet(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enfantNomComplet", o2.jsonEnfantNomComplet(), pk));
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
					case "setEcoleAddresse":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "ecoleAddresse"));
						} else {
							o2.setEcoleAddresse(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("ecoleAddresse", o2.jsonEcoleAddresse(), pk));
						}
						break;
					case "setInscriptionApprouve":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionApprouve"));
						} else {
							o2.setInscriptionApprouve(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionApprouve", o2.jsonInscriptionApprouve(), pk));
						}
						break;
					case "setInscriptionImmunisations":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionImmunisations"));
						} else {
							o2.setInscriptionImmunisations(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionImmunisations", o2.jsonInscriptionImmunisations(), pk));
						}
						break;
					case "setFamilleMarie":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familleMarie"));
						} else {
							o2.setFamilleMarie(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familleMarie", o2.jsonFamilleMarie(), pk));
						}
						break;
					case "setFamilleSepare":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familleSepare"));
						} else {
							o2.setFamilleSepare(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familleSepare", o2.jsonFamilleSepare(), pk));
						}
						break;
					case "setFamilleDivorce":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familleDivorce"));
						} else {
							o2.setFamilleDivorce(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familleDivorce", o2.jsonFamilleDivorce(), pk));
						}
						break;
					case "setFamilleAddresse":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familleAddresse"));
						} else {
							o2.setFamilleAddresse(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familleAddresse", o2.jsonFamilleAddresse(), pk));
						}
						break;
					case "setFamilleCommentVousConnaissezEcole":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familleCommentVousConnaissezEcole"));
						} else {
							o2.setFamilleCommentVousConnaissezEcole(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familleCommentVousConnaissezEcole", o2.jsonFamilleCommentVousConnaissezEcole(), pk));
						}
						break;
					case "setInscriptionConsiderationsSpeciales":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionConsiderationsSpeciales"));
						} else {
							o2.setInscriptionConsiderationsSpeciales(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionConsiderationsSpeciales", o2.jsonInscriptionConsiderationsSpeciales(), pk));
						}
						break;
					case "setEnfantConditionsMedicales":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enfantConditionsMedicales"));
						} else {
							o2.setEnfantConditionsMedicales(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enfantConditionsMedicales", o2.jsonEnfantConditionsMedicales(), pk));
						}
						break;
					case "setEnfantEcolesPrecedemmentFrequentees":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enfantEcolesPrecedemmentFrequentees"));
						} else {
							o2.setEnfantEcolesPrecedemmentFrequentees(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enfantEcolesPrecedemmentFrequentees", o2.jsonEnfantEcolesPrecedemmentFrequentees(), pk));
						}
						break;
					case "setEnfantDescription":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enfantDescription"));
						} else {
							o2.setEnfantDescription(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enfantDescription", o2.jsonEnfantDescription(), pk));
						}
						break;
					case "setEnfantObjectifs":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enfantObjectifs"));
						} else {
							o2.setEnfantObjectifs(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enfantObjectifs", o2.jsonEnfantObjectifs(), pk));
						}
						break;
					case "setEnfantPropre":
						if(jsonObject.getBoolean(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enfantPropre"));
						} else {
							o2.setEnfantPropre(jsonObject.getBoolean(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enfantPropre", o2.jsonEnfantPropre(), pk));
						}
						break;
					case "setInscriptionNomGroupe":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionNomGroupe"));
						} else {
							o2.setInscriptionNomGroupe(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionNomGroupe", o2.jsonInscriptionNomGroupe(), pk));
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
					case "setInscriptionDateFrais":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDateFrais"));
						} else {
							o2.setInscriptionDateFrais(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDateFrais", o2.jsonInscriptionDateFrais(), pk));
						}
						break;
					case "setInscriptionSignature1":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature1"));
						} else {
							o2.setInscriptionSignature1(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature1", o2.jsonInscriptionSignature1(), pk));
						}
						break;
					case "setInscriptionSignature2":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature2"));
						} else {
							o2.setInscriptionSignature2(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature2", o2.jsonInscriptionSignature2(), pk));
						}
						break;
					case "setInscriptionSignature3":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature3"));
						} else {
							o2.setInscriptionSignature3(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature3", o2.jsonInscriptionSignature3(), pk));
						}
						break;
					case "setInscriptionSignature4":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature4"));
						} else {
							o2.setInscriptionSignature4(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature4", o2.jsonInscriptionSignature4(), pk));
						}
						break;
					case "setInscriptionSignature5":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature5"));
						} else {
							o2.setInscriptionSignature5(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature5", o2.jsonInscriptionSignature5(), pk));
						}
						break;
					case "setInscriptionSignature6":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature6"));
						} else {
							o2.setInscriptionSignature6(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature6", o2.jsonInscriptionSignature6(), pk));
						}
						break;
					case "setInscriptionSignature7":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature7"));
						} else {
							o2.setInscriptionSignature7(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature7", o2.jsonInscriptionSignature7(), pk));
						}
						break;
					case "setInscriptionSignature8":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature8"));
						} else {
							o2.setInscriptionSignature8(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature8", o2.jsonInscriptionSignature8(), pk));
						}
						break;
					case "setInscriptionSignature9":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature9"));
						} else {
							o2.setInscriptionSignature9(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature9", o2.jsonInscriptionSignature9(), pk));
						}
						break;
					case "setInscriptionSignature10":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionSignature10"));
						} else {
							o2.setInscriptionSignature10(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionSignature10", o2.jsonInscriptionSignature10(), pk));
						}
						break;
					case "setInscriptionDate1":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate1"));
						} else {
							o2.setInscriptionDate1(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate1", o2.jsonInscriptionDate1(), pk));
						}
						break;
					case "setInscriptionDate2":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate2"));
						} else {
							o2.setInscriptionDate2(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate2", o2.jsonInscriptionDate2(), pk));
						}
						break;
					case "setInscriptionDate3":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate3"));
						} else {
							o2.setInscriptionDate3(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate3", o2.jsonInscriptionDate3(), pk));
						}
						break;
					case "setInscriptionDate4":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate4"));
						} else {
							o2.setInscriptionDate4(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate4", o2.jsonInscriptionDate4(), pk));
						}
						break;
					case "setInscriptionDate5":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate5"));
						} else {
							o2.setInscriptionDate5(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate5", o2.jsonInscriptionDate5(), pk));
						}
						break;
					case "setInscriptionDate6":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate6"));
						} else {
							o2.setInscriptionDate6(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate6", o2.jsonInscriptionDate6(), pk));
						}
						break;
					case "setInscriptionDate7":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate7"));
						} else {
							o2.setInscriptionDate7(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate7", o2.jsonInscriptionDate7(), pk));
						}
						break;
					case "setInscriptionDate8":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate8"));
						} else {
							o2.setInscriptionDate8(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate8", o2.jsonInscriptionDate8(), pk));
						}
						break;
					case "setInscriptionDate9":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate9"));
						} else {
							o2.setInscriptionDate9(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate9", o2.jsonInscriptionDate9(), pk));
						}
						break;
					case "setInscriptionDate10":
						if(jsonObject.getString(methodeNom) == null) {
							patchSql.append(SiteContexteFrFR.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inscriptionDate10"));
						} else {
							o2.setInscriptionDate10(jsonObject.getString(methodeNom));
							patchSql.append(SiteContexteFrFR.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inscriptionDate10", o2.jsonInscriptionDate10(), pk));
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
					InscriptionScolaire o3 = new InscriptionScolaire();
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

	public void patchpaiementsInscriptionScolaireReponse(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			reponse200PATCHPaiementsInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							connexionSql.close(c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchpaiementsInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PATCHPaiementsInscriptionScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			JsonObject json = new JsonObject();
			gestionnaireEvenements.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// PageRecherche //

	@Override
	public void pagerechercheInscriptionScolaireId(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		pagerechercheInscriptionScolaire(operationRequete, gestionnaireEvenements);
	}

	@Override
	public void pagerechercheInscriptionScolaire(OperationRequest operationRequete, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete);
		try {
			sqlInscriptionScolaire(requeteSite, a -> {
				if(a.succeeded()) {
					utilisateurInscriptionScolaire(requeteSite, b -> {
						if(b.succeeded()) {
							rechercheInscriptionScolaire(requeteSite, false, true, "/inscription", "PageRecherche", c -> {
								if(c.succeeded()) {
									ListeRecherche<InscriptionScolaire> listeInscriptionScolaire = c.result();
									pagerechercheInscriptionScolaireReponse(listeInscriptionScolaire, d -> {
										if(d.succeeded()) {
											gestionnaireEvenements.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("pagerechercheInscriptionScolaire a réussi. "));
										} else {
											LOGGER.error(String.format("pagerechercheInscriptionScolaire a échoué. ", d.cause()));
											erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, d);
										}
									});
								} else {
									LOGGER.error(String.format("pagerechercheInscriptionScolaire a échoué. ", c.cause()));
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							LOGGER.error(String.format("pagerechercheInscriptionScolaire a échoué. ", b.cause()));
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					LOGGER.error(String.format("pagerechercheInscriptionScolaire a échoué. ", a.cause()));
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("pagerechercheInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, Future.failedFuture(ex));
		}
	}


	public void pagerechercheInscriptionScolaireReponse(ListeRecherche<InscriptionScolaire> listeInscriptionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = listeInscriptionScolaire.getRequeteSite_();
		try {
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(requeteSite, buffer);
			requeteSite.setW(w);
			reponse200PageRechercheInscriptionScolaire(listeInscriptionScolaire, a -> {
				if(a.succeeded()) {
					SQLConnection connexionSql = requeteSite.getConnexionSql();
					connexionSql.commit(b -> {
						if(b.succeeded()) {
							connexionSql.close(c -> {
								if(c.succeeded()) {
									gestionnaireEvenements.handle(Future.succeededFuture(a.result()));
								} else {
									erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
								}
							});
						} else {
							erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
						}
					});
				} else {
					erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("pagerechercheInscriptionScolaire a échoué. ", ex));
			erreurInscriptionScolaire(requeteSite, null, Future.failedFuture(ex));
		}
	}
	public void reponse200PageRechercheInscriptionScolaire(ListeRecherche<InscriptionScolaire> listeInscriptionScolaire, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = listeInscriptionScolaire.getRequeteSite_();
			Buffer buffer = Buffer.buffer();
			ToutEcrivain w = ToutEcrivain.creer(listeInscriptionScolaire.getRequeteSite_(), buffer);
			InscriptionPage page = new InscriptionPage();
			SolrDocument pageDocumentSolr = new SolrDocument();
			CaseInsensitiveHeaders requeteEnTetes = new CaseInsensitiveHeaders();
			requeteSite.setRequeteEnTetes(requeteEnTetes);

			pageDocumentSolr.setField("pageUri_frFR_stored_string", "/inscription");
			page.setPageDocumentSolr(pageDocumentSolr);
			page.setW(w);
			if(listeInscriptionScolaire.size() == 1)
				requeteSite.setRequetePk(listeInscriptionScolaire.get(0).getPk());
			requeteSite.setW(w);
			page.setListeInscriptionScolaire(listeInscriptionScolaire);
			page.setRequeteSite_(requeteSite);
			page.initLoinInscriptionPage(requeteSite);
			page.html();
			gestionnaireEvenements.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requeteEnTetes)));
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<InscriptionScolaire> definirIndexerInscriptionScolaire(InscriptionScolaire inscriptionScolaire, Handler<AsyncResult<InscriptionScolaire>> gestionnaireEvenements) {
		Promise<InscriptionScolaire> promise = Promise.promise();
		RequeteSiteFrFR requeteSite = inscriptionScolaire.getRequeteSite_();
		definirInscriptionScolaire(inscriptionScolaire, c -> {
			if(c.succeeded()) {
				attribuerInscriptionScolaire(inscriptionScolaire, d -> {
					if(d.succeeded()) {
						indexerInscriptionScolaire(inscriptionScolaire, e -> {
							if(e.succeeded()) {
								gestionnaireEvenements.handle(Future.succeededFuture(inscriptionScolaire));
								promise.complete(inscriptionScolaire);
							} else {
								erreurInscriptionScolaire(requeteSite, null, e);
							}
						});
					} else {
						erreurInscriptionScolaire(requeteSite, null, d);
					}
				});
			} else {
				erreurInscriptionScolaire(requeteSite, null, c);
			}
		});
		return promise.future();
	}

	public void creerInscriptionScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<InscriptionScolaire>> gestionnaireEvenements) {
		try {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			String utilisateurId = requeteSite.getUtilisateurId();

			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_creer
					, new JsonArray(Arrays.asList(InscriptionScolaire.class.getCanonicalName(), utilisateurId))
					, creerAsync
			-> {
				JsonArray creerLigne = creerAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = creerLigne.getLong(0);
				InscriptionScolaire o = new InscriptionScolaire();
				o.setPk(pk);
				o.setRequeteSite_(requeteSite);
				gestionnaireEvenements.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			gestionnaireEvenements.handle(Future.failedFuture(e));
		}
	}

	public void erreurInscriptionScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements, AsyncResult<?> resultatAsync) {
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
		if(requeteSite != null) {
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			if(connexionSql != null) {
				connexionSql.rollback(a -> {
					if(a.succeeded()) {
						LOGGER.info(String.format("sql rollback. "));
						connexionSql.close(b -> {
							if(a.succeeded()) {
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
			} else {
				if(gestionnaireEvenements != null)
					gestionnaireEvenements.handle(Future.succeededFuture(reponseOperation));
			}
		}
	}

	public void sqlInscriptionScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public RequeteSiteFrFR genererRequeteSiteFrFRPourInscriptionScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete) {
		return genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, operationRequete, null);
	}

	public RequeteSiteFrFR genererRequeteSiteFrFRPourInscriptionScolaire(SiteContexteFrFR siteContexte, OperationRequest operationRequete, JsonObject body) {
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

	public void utilisateurInscriptionScolaire(RequeteSiteFrFR requeteSite, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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
								utilisateurInscriptionScolaireDefinir(requeteSite, jsonObject, false);

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
														erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, d);
													}
												});
											} else {
												erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
											}
										});
									} else {
										erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, b);
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
								Boolean definir = utilisateurInscriptionScolaireDefinir(requeteSite, jsonObject, true);
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
													erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, d);
												}
											});
										} else {
											erreurInscriptionScolaire(requeteSite, gestionnaireEvenements, c);
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

	public Boolean utilisateurInscriptionScolaireDefinir(RequeteSiteFrFR requeteSite, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void rechercheInscriptionScolaireQ(String uri, String apiMethode, ListeRecherche<InscriptionScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		listeRecherche.setQuery(varIndexe + ":" + ("*".equals(valeurIndexe) ? valeurIndexe : ClientUtils.escapeQueryChars(valeurIndexe)));
		if(!"*".equals(entiteVar)) {
			listeRecherche.setHighlight(true);
			listeRecherche.setHighlightSnippets(3);
			listeRecherche.addHighlightField(varIndexe);
			listeRecherche.setParam("hl.encoder", "html");
		}
	}

	public void rechercheInscriptionScolaireFq(String uri, String apiMethode, ListeRecherche<InscriptionScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addFilterQuery(varIndexe + ":" + ClientUtils.escapeQueryChars(valeurIndexe));
	}

	public void rechercheInscriptionScolaireSort(String uri, String apiMethode, ListeRecherche<InscriptionScolaire> listeRecherche, String entiteVar, String valeurIndexe, String varIndexe) {
		if(varIndexe == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entiteVar));
		listeRecherche.addSort(varIndexe, ORDER.valueOf(valeurIndexe));
	}

	public void rechercheInscriptionScolaireRows(String uri, String apiMethode, ListeRecherche<InscriptionScolaire> listeRecherche, Integer valeurRows) {
			listeRecherche.setRows(apiMethode != null && apiMethode.contains("Recherche") ? valeurRows : 10);
	}

	public void rechercheInscriptionScolaireStart(String uri, String apiMethode, ListeRecherche<InscriptionScolaire> listeRecherche, Integer valeurStart) {
		listeRecherche.setStart(valeurStart);
	}

	public void rechercheInscriptionScolaireVar(String uri, String apiMethode, ListeRecherche<InscriptionScolaire> listeRecherche, String var, String valeur) {
		listeRecherche.getRequeteSite_().getRequeteVars().put(var, valeur);
	}

	public void rechercheInscriptionScolaireUri(String uri, String apiMethode, ListeRecherche<InscriptionScolaire> listeRecherche) {
	}

	public void rechercheInscriptionScolaire(RequeteSiteFrFR requeteSite, Boolean peupler, Boolean stocker, String uri, String apiMethode, Handler<AsyncResult<ListeRecherche<InscriptionScolaire>>> gestionnaireEvenements) {
		try {
			OperationRequest operationRequete = requeteSite.getOperationRequete();
			String entiteListeStr = requeteSite.getOperationRequete().getParams().getJsonObject("query").getString("fl");
			String[] entiteListe = entiteListeStr == null ? null : entiteListeStr.split(",\\s*");
			ListeRecherche<InscriptionScolaire> listeRecherche = new ListeRecherche<InscriptionScolaire>();
			listeRecherche.setPeupler(peupler);
			listeRecherche.setStocker(stocker);
			listeRecherche.setQuery("*:*");
			listeRecherche.setC(InscriptionScolaire.class);
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
								varIndexe = "*".equals(entiteVar) ? entiteVar : InscriptionScolaire.varRechercheInscriptionScolaire(entiteVar);
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								valeurIndexe = StringUtils.isEmpty(valeurIndexe) ? "*" : valeurIndexe;
								rechercheInscriptionScolaireQ(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "fq":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								varIndexe = InscriptionScolaire.varIndexeInscriptionScolaire(entiteVar);
								rechercheInscriptionScolaireFq(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "sort":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, " "));
								valeurIndexe = StringUtils.trim(StringUtils.substringAfter((String)paramObjet, " "));
								varIndexe = InscriptionScolaire.varIndexeInscriptionScolaire(entiteVar);
								rechercheInscriptionScolaireSort(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe, varIndexe);
								break;
							case "start":
								valeurStart = (Integer)paramObjet;
								rechercheInscriptionScolaireStart(uri, apiMethode, listeRecherche, valeurStart);
								break;
							case "rows":
								valeurRows = (Integer)paramObjet;
								rechercheInscriptionScolaireRows(uri, apiMethode, listeRecherche, valeurRows);
								break;
							case "var":
								entiteVar = StringUtils.trim(StringUtils.substringBefore((String)paramObjet, ":"));
								valeurIndexe = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObjet, ":")), "UTF-8");
								rechercheInscriptionScolaireVar(uri, apiMethode, listeRecherche, entiteVar, valeurIndexe);
								break;
						}
					}
					rechercheInscriptionScolaireUri(uri, apiMethode, listeRecherche);
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

	public void definirInscriptionScolaire(InscriptionScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		try {
			RequeteSiteFrFR requeteSite = o.getRequeteSite_();
			SQLConnection connexionSql = requeteSite.getConnexionSql();
			Long pk = o.getPk();
			connexionSql.queryWithParams(
					SiteContexteFrFR.SQL_definir
					, new JsonArray(Arrays.asList(pk, pk))
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

	public void attribuerInscriptionScolaire(InscriptionScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
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

	public void indexerInscriptionScolaire(InscriptionScolaire o, Handler<AsyncResult<OperationResponse>> gestionnaireEvenements) {
		RequeteSiteFrFR requeteSite = o.getRequeteSite_();
		try {
			RequeteApi requeteApi = requeteSite.getRequeteApi_();
			List<Long> pks = Optional.ofNullable(requeteApi).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(requeteApi).map(r -> r.getClasses()).orElse(new ArrayList<>());
			o.initLoinPourClasse(requeteSite);
			o.indexerPourClasse();
			if(BooleanUtils.isFalse(Optional.ofNullable(requeteSite.getRequeteApi_()).map(RequeteApi::getEmpty).orElse(true))) {
				RequeteSiteFrFR requeteSite2 = genererRequeteSiteFrFRPourInscriptionScolaire(siteContexte, requeteSite.getOperationRequete(), new JsonObject());
				requeteSite2.setConnexionSql(requeteSite.getConnexionSql());
				ListeRecherche<InscriptionScolaire> listeRecherche = new ListeRecherche<InscriptionScolaire>();
				listeRecherche.setStocker(true);
				listeRecherche.setQuery("*:*");
				listeRecherche.setC(InscriptionScolaire.class);
				listeRecherche.addFilterQuery("modifie_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(requeteSite.getRequeteApi_().getCree().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				listeRecherche.add("json.facet", "{anneeCle:{terms:{field:anneeCle_indexed_longs, limit:1000}}}");
				listeRecherche.add("json.facet", "{blocCles:{terms:{field:blocCles_indexed_longs, limit:1000}}}");
				listeRecherche.add("json.facet", "{enfantCle:{terms:{field:enfantCle_indexed_longs, limit:1000}}}");
				listeRecherche.add("json.facet", "{mereCles:{terms:{field:mereCles_indexed_longs, limit:1000}}}");
				listeRecherche.add("json.facet", "{pereCles:{terms:{field:pereCles_indexed_longs, limit:1000}}}");
				listeRecherche.add("json.facet", "{gardienCles:{terms:{field:gardienCles_indexed_longs, limit:1000}}}");
				listeRecherche.add("json.facet", "{paiementCles:{terms:{field:paiementCles_indexed_longs, limit:1000}}}");
				listeRecherche.add("json.facet", "{utilisateurCles:{terms:{field:utilisateurCles_indexed_longs, limit:1000}}}");
				listeRecherche.setRows(1000);
				listeRecherche.initLoinListeRecherche(requeteSite2);
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
						listeRecherche2.initLoinListeRecherche(requeteSite2);
						AnneeScolaire o2 = listeRecherche2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							AnneeScolaireFrFRGenApiServiceImpl service = new AnneeScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());

							RequeteApi requeteApi2 = new RequeteApi();
							requeteApi2.setRows(1);
							requeteApi2.setNumFound(1L);
							requeteApi2.setNumPATCH(0L);
							requeteApi2.initLoinRequeteApi(requeteSite2);
							requeteSite2.setRequeteApi_(requeteApi2);
							requeteSite2.getVertx().eventBus().publish("websocketAnneeScolaire", JsonObject.mapFrom(requeteApi2).toString());

							if(pk2 != null) {
								o2.setPk(pk2);
								o2.setRequeteSite_(requeteSite2);
								futures.add(
									service.patchAnneeScolaireFuture(o2, false, a -> {
										if(a.succeeded()) {
											LOGGER.info(String.format("AnneeScolaire %s rechargé. ", pk2));
										} else {
											LOGGER.info(String.format("AnneeScolaire %s a échoué. ", pk2));
											gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
										}
									})
								);
							}
						}
					}

					if("BlocScolaire".equals(classeNomSimple2) && pk2 != null) {
						BlocScolaireFrFRGenApiServiceImpl service = new BlocScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
						ListeRecherche<BlocScolaire> listeRecherche2 = new ListeRecherche<BlocScolaire>();
						if(pk2 != null) {
							listeRecherche2.setStocker(true);
							listeRecherche2.setQuery("*:*");
							listeRecherche2.setC(BlocScolaire.class);
							listeRecherche2.addFilterQuery("pk_indexed_long:" + pk2);
							listeRecherche2.setRows(1);
							listeRecherche2.initLoinListeRecherche(requeteSite2);
							BlocScolaire o2 = listeRecherche2.getList().stream().findFirst().orElse(null);

							if(o2 != null) {
								RequeteApi requeteApi2 = new RequeteApi();
								requeteApi2.setRows(1);
								requeteApi2.setNumFound(1l);
								requeteApi2.setNumPATCH(0L);
								requeteApi2.initLoinRequeteApi(requeteSite2);
								requeteSite2.setRequeteApi_(requeteApi2);
								requeteSite2.getVertx().eventBus().publish("websocketBlocScolaire", JsonObject.mapFrom(requeteApi2).toString());

								o2.setPk(pk2);
								o2.setRequeteSite_(requeteSite2);
								futures.add(
									service.patchBlocScolaireFuture(o2, false, a -> {
										if(a.succeeded()) {
											LOGGER.info(String.format("BlocScolaire %s rechargé. ", pk2));
										} else {
											LOGGER.info(String.format("BlocScolaire %s a échoué. ", pk2));
											gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
										}
									})
								);
							}
						}
					}

					if("EnfantScolaire".equals(classeNomSimple2) && pk2 != null) {
						ListeRecherche<EnfantScolaire> listeRecherche2 = new ListeRecherche<EnfantScolaire>();
						listeRecherche2.setStocker(true);
						listeRecherche2.setQuery("*:*");
						listeRecherche2.setC(EnfantScolaire.class);
						listeRecherche2.addFilterQuery("pk_indexed_long:" + pk2);
						listeRecherche2.setRows(1);
						listeRecherche2.initLoinListeRecherche(requeteSite2);
						EnfantScolaire o2 = listeRecherche2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							EnfantScolaireFrFRGenApiServiceImpl service = new EnfantScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());

							RequeteApi requeteApi2 = new RequeteApi();
							requeteApi2.setRows(1);
							requeteApi2.setNumFound(1L);
							requeteApi2.setNumPATCH(0L);
							requeteApi2.initLoinRequeteApi(requeteSite2);
							requeteSite2.setRequeteApi_(requeteApi2);
							requeteSite2.getVertx().eventBus().publish("websocketEnfantScolaire", JsonObject.mapFrom(requeteApi2).toString());

							if(pk2 != null) {
								o2.setPk(pk2);
								o2.setRequeteSite_(requeteSite2);
								futures.add(
									service.patchEnfantScolaireFuture(o2, false, a -> {
										if(a.succeeded()) {
											LOGGER.info(String.format("EnfantScolaire %s rechargé. ", pk2));
										} else {
											LOGGER.info(String.format("EnfantScolaire %s a échoué. ", pk2));
											gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
										}
									})
								);
							}
						}
					}

					if("MereScolaire".equals(classeNomSimple2) && pk2 != null) {
						MereScolaireFrFRGenApiServiceImpl service = new MereScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
						ListeRecherche<MereScolaire> listeRecherche2 = new ListeRecherche<MereScolaire>();
						if(pk2 != null) {
							listeRecherche2.setStocker(true);
							listeRecherche2.setQuery("*:*");
							listeRecherche2.setC(MereScolaire.class);
							listeRecherche2.addFilterQuery("pk_indexed_long:" + pk2);
							listeRecherche2.setRows(1);
							listeRecherche2.initLoinListeRecherche(requeteSite2);
							MereScolaire o2 = listeRecherche2.getList().stream().findFirst().orElse(null);

							if(o2 != null) {
								RequeteApi requeteApi2 = new RequeteApi();
								requeteApi2.setRows(1);
								requeteApi2.setNumFound(1l);
								requeteApi2.setNumPATCH(0L);
								requeteApi2.initLoinRequeteApi(requeteSite2);
								requeteSite2.setRequeteApi_(requeteApi2);
								requeteSite2.getVertx().eventBus().publish("websocketMereScolaire", JsonObject.mapFrom(requeteApi2).toString());

								o2.setPk(pk2);
								o2.setRequeteSite_(requeteSite2);
								futures.add(
									service.patchMereScolaireFuture(o2, false, a -> {
										if(a.succeeded()) {
											LOGGER.info(String.format("MereScolaire %s rechargé. ", pk2));
										} else {
											LOGGER.info(String.format("MereScolaire %s a échoué. ", pk2));
											gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
										}
									})
								);
							}
						}
					}

					if("PereScolaire".equals(classeNomSimple2) && pk2 != null) {
						PereScolaireFrFRGenApiServiceImpl service = new PereScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
						ListeRecherche<PereScolaire> listeRecherche2 = new ListeRecherche<PereScolaire>();
						if(pk2 != null) {
							listeRecherche2.setStocker(true);
							listeRecherche2.setQuery("*:*");
							listeRecherche2.setC(PereScolaire.class);
							listeRecherche2.addFilterQuery("pk_indexed_long:" + pk2);
							listeRecherche2.setRows(1);
							listeRecherche2.initLoinListeRecherche(requeteSite2);
							PereScolaire o2 = listeRecherche2.getList().stream().findFirst().orElse(null);

							if(o2 != null) {
								RequeteApi requeteApi2 = new RequeteApi();
								requeteApi2.setRows(1);
								requeteApi2.setNumFound(1l);
								requeteApi2.setNumPATCH(0L);
								requeteApi2.initLoinRequeteApi(requeteSite2);
								requeteSite2.setRequeteApi_(requeteApi2);
								requeteSite2.getVertx().eventBus().publish("websocketPereScolaire", JsonObject.mapFrom(requeteApi2).toString());

								o2.setPk(pk2);
								o2.setRequeteSite_(requeteSite2);
								futures.add(
									service.patchPereScolaireFuture(o2, false, a -> {
										if(a.succeeded()) {
											LOGGER.info(String.format("PereScolaire %s rechargé. ", pk2));
										} else {
											LOGGER.info(String.format("PereScolaire %s a échoué. ", pk2));
											gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
										}
									})
								);
							}
						}
					}

					if("GardienScolaire".equals(classeNomSimple2) && pk2 != null) {
						GardienScolaireFrFRGenApiServiceImpl service = new GardienScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
						ListeRecherche<GardienScolaire> listeRecherche2 = new ListeRecherche<GardienScolaire>();
						if(pk2 != null) {
							listeRecherche2.setStocker(true);
							listeRecherche2.setQuery("*:*");
							listeRecherche2.setC(GardienScolaire.class);
							listeRecherche2.addFilterQuery("pk_indexed_long:" + pk2);
							listeRecherche2.setRows(1);
							listeRecherche2.initLoinListeRecherche(requeteSite2);
							GardienScolaire o2 = listeRecherche2.getList().stream().findFirst().orElse(null);

							if(o2 != null) {
								RequeteApi requeteApi2 = new RequeteApi();
								requeteApi2.setRows(1);
								requeteApi2.setNumFound(1l);
								requeteApi2.setNumPATCH(0L);
								requeteApi2.initLoinRequeteApi(requeteSite2);
								requeteSite2.setRequeteApi_(requeteApi2);
								requeteSite2.getVertx().eventBus().publish("websocketGardienScolaire", JsonObject.mapFrom(requeteApi2).toString());

								o2.setPk(pk2);
								o2.setRequeteSite_(requeteSite2);
								futures.add(
									service.patchGardienScolaireFuture(o2, false, a -> {
										if(a.succeeded()) {
											LOGGER.info(String.format("GardienScolaire %s rechargé. ", pk2));
										} else {
											LOGGER.info(String.format("GardienScolaire %s a échoué. ", pk2));
											gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
										}
									})
								);
							}
						}
					}

					if("PaiementScolaire".equals(classeNomSimple2) && pk2 != null) {
						PaiementScolaireFrFRGenApiServiceImpl service = new PaiementScolaireFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
						ListeRecherche<PaiementScolaire> listeRecherche2 = new ListeRecherche<PaiementScolaire>();
						if(pk2 != null) {
							listeRecherche2.setStocker(true);
							listeRecherche2.setQuery("*:*");
							listeRecherche2.setC(PaiementScolaire.class);
							listeRecherche2.addFilterQuery("pk_indexed_long:" + pk2);
							listeRecherche2.setRows(1);
							listeRecherche2.initLoinListeRecherche(requeteSite2);
							PaiementScolaire o2 = listeRecherche2.getList().stream().findFirst().orElse(null);

							if(o2 != null) {
								RequeteApi requeteApi2 = new RequeteApi();
								requeteApi2.setRows(1);
								requeteApi2.setNumFound(1l);
								requeteApi2.setNumPATCH(0L);
								requeteApi2.initLoinRequeteApi(requeteSite2);
								requeteSite2.setRequeteApi_(requeteApi2);
								requeteSite2.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(requeteApi2).toString());

								o2.setPk(pk2);
								o2.setRequeteSite_(requeteSite2);
								futures.add(
									service.patchPaiementScolaireFuture(o2, false, a -> {
										if(a.succeeded()) {
											LOGGER.info(String.format("PaiementScolaire %s rechargé. ", pk2));
										} else {
											LOGGER.info(String.format("PaiementScolaire %s a échoué. ", pk2));
											gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
										}
									})
								);
							}
						}
					}

					if("UtilisateurSite".equals(classeNomSimple2) && pk2 != null) {
						UtilisateurSiteFrFRGenApiServiceImpl service = new UtilisateurSiteFrFRGenApiServiceImpl(requeteSite2.getSiteContexte_());
						ListeRecherche<UtilisateurSite> listeRecherche2 = new ListeRecherche<UtilisateurSite>();
						if(pk2 != null) {
							listeRecherche2.setStocker(true);
							listeRecherche2.setQuery("*:*");
							listeRecherche2.setC(UtilisateurSite.class);
							listeRecherche2.addFilterQuery("pk_indexed_long:" + pk2);
							listeRecherche2.setRows(1);
							listeRecherche2.initLoinListeRecherche(requeteSite2);
							UtilisateurSite o2 = listeRecherche2.getList().stream().findFirst().orElse(null);

							if(o2 != null) {
								RequeteApi requeteApi2 = new RequeteApi();
								requeteApi2.setRows(1);
								requeteApi2.setNumFound(1l);
								requeteApi2.setNumPATCH(0L);
								requeteApi2.initLoinRequeteApi(requeteSite2);
								requeteSite2.setRequeteApi_(requeteApi2);
								requeteSite2.getVertx().eventBus().publish("websocketUtilisateurSite", JsonObject.mapFrom(requeteApi2).toString());

								o2.setPk(pk2);
								o2.setRequeteSite_(requeteSite2);
								futures.add(
									service.patchUtilisateurSiteFuture(o2, false, a -> {
										if(a.succeeded()) {
											LOGGER.info(String.format("UtilisateurSite %s rechargé. ", pk2));
										} else {
											LOGGER.info(String.format("UtilisateurSite %s a échoué. ", pk2));
											gestionnaireEvenements.handle(Future.failedFuture(a.cause()));
										}
									})
								);
							}
						}
					}
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						LOGGER.info("Recharger relations a réussi. ");
						InscriptionScolaireFrFRApiServiceImpl service = new InscriptionScolaireFrFRApiServiceImpl(requeteSite2.getSiteContexte_());
						List<Future> futures2 = new ArrayList<>();
						for(InscriptionScolaire o2 : listeRecherche.getList()) {
							futures2.add(
								service.patchInscriptionScolaireFuture(o2, false, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("InscriptionScolaire %s rechargé. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("InscriptionScolaire %s a échoué. ", o2.getPk()));
										gestionnaireEvenements.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Recharger InscriptionScolaire a réussi. ");
								gestionnaireEvenements.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Recharger relations a échoué. ", b.cause());
								erreurInscriptionScolaire(requeteSite2, gestionnaireEvenements, b);
							}
						});
					} else {
						LOGGER.error("Recharger relations a échoué. ", a.cause());
						erreurInscriptionScolaire(requeteSite2, gestionnaireEvenements, a);
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
