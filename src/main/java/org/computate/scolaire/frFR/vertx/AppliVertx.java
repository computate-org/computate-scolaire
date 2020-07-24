package org.computate.scolaire.frFR.vertx;       

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.xml.datatype.DatatypeFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.computate.scolaire.frFR.age.AgeScolaireFrFRGenApiService;
import org.computate.scolaire.frFR.annee.AnneeScolaireFrFRGenApiService;
import org.computate.scolaire.frFR.bloc.BlocScolaireFrFRGenApiService;
import org.computate.scolaire.frFR.cluster.ClusterFrFRGenApiService;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.design.DesignPageFrFRGenApiService;
import org.computate.scolaire.frFR.ecole.EcoleFrFRGenApiService;
import org.computate.scolaire.frFR.enfant.EnfantScolaire;
import org.computate.scolaire.frFR.enfant.EnfantScolaireFrFRApiServiceImpl;
import org.computate.scolaire.frFR.enfant.EnfantScolaireFrFRGenApiService;
import org.computate.scolaire.frFR.gardien.GardienScolaire;
import org.computate.scolaire.frFR.gardien.GardienScolaireFrFRApiServiceImpl;
import org.computate.scolaire.frFR.gardien.GardienScolaireFrFRGenApiService;
import org.computate.scolaire.frFR.html.part.PartHtmlFrFRGenApiService;
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;
import org.computate.scolaire.frFR.inscription.InscriptionScolaireFrFRApiServiceImpl;
import org.computate.scolaire.frFR.inscription.InscriptionScolaireFrFRGenApiService;
import org.computate.scolaire.frFR.java.LocalDateSerializer;
import org.computate.scolaire.frFR.java.LocalTimeSerializer;
import org.computate.scolaire.frFR.java.ZonedDateTimeSerializer;
import org.computate.scolaire.frFR.mere.MereScolaire;
import org.computate.scolaire.frFR.mere.MereScolaireFrFRApiServiceImpl;
import org.computate.scolaire.frFR.mere.MereScolaireFrFRGenApiService;
import org.computate.scolaire.frFR.paiement.PaiementScolaire;
import org.computate.scolaire.frFR.paiement.PaiementScolaireFrFRApiServiceImpl;
import org.computate.scolaire.frFR.paiement.PaiementScolaireFrFRGenApiService;
import org.computate.scolaire.frFR.paiement.PaiementScolaireFrFRGenApiServiceImpl;
import org.computate.scolaire.frFR.pere.PereScolaire;
import org.computate.scolaire.frFR.pere.PereScolaireFrFRApiServiceImpl;
import org.computate.scolaire.frFR.pere.PereScolaireFrFRGenApiService;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.requete.api.RequeteApi;
import org.computate.scolaire.frFR.saison.SaisonScolaireFrFRGenApiService;
import org.computate.scolaire.frFR.session.SessionScolaireFrFRGenApiService;
import org.computate.scolaire.frFR.utilisateur.UtilisateurSiteFrFRGenApiService;

import com.fasterxml.jackson.databind.module.SimpleModule;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.JksOptions;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.auth.oauth2.AccessToken;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.oauth2.OAuth2ClientOptions;
import io.vertx.ext.auth.oauth2.OAuth2FlowType;
import io.vertx.ext.auth.oauth2.providers.OpenIDConnectAuth;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.healthchecks.HealthCheckHandler;
import io.vertx.ext.healthchecks.Status;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailConfig;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Transaction;
import net.authorize.Environment;
import net.authorize.api.contract.v1.ArrayOfBatchDetailsType;
import net.authorize.api.contract.v1.ArrayOfTransactionSummaryType;
import net.authorize.api.contract.v1.BatchDetailsType;
import net.authorize.api.contract.v1.GetSettledBatchListRequest;
import net.authorize.api.contract.v1.GetSettledBatchListResponse;
import net.authorize.api.contract.v1.GetTransactionListRequest;
import net.authorize.api.contract.v1.GetTransactionListResponse;
import net.authorize.api.contract.v1.MerchantAuthenticationType;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.Paging;
import net.authorize.api.contract.v1.TransactionListOrderFieldEnum;
import net.authorize.api.contract.v1.TransactionListSorting;
import net.authorize.api.contract.v1.TransactionSummaryType;
import net.authorize.api.controller.GetSettledBatchListController;
import net.authorize.api.controller.GetTransactionListController;
import net.authorize.api.controller.base.ApiOperationBase;

/**
 * NomCanonique.enUS: org.computate.scolaire.enUS.vertx.AppVertx
 * enUS: A Java class to start the Vert.x application as a main method. 
 */      
public class AppliVertx extends AppliVertxGen<AbstractVerticle> {

	public final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	/**
	 * enUS: A SQL query for creating a database table "c" to store any type of object in the application. 
	 * r: nom_canonique
	 * r.enUS: canonical_name
	 * r: ajour
	 * r.enUS: current
	 * r: nom_canonique
	 * r.enUS: canonical_name
	 * r: cree
	 * r.enUS: created
	 * r: id_utilisateur
	 * r.enUS: user_id
	 */
	public static final String SQL_createTableC = "create table if not exists c(pk bigserial primary key, ajour boolean, nom_canonique text, cree timestamp with time zone, id_utilisateur text); ";

	/**
	 * enUS: A SQL query for creating a unique index on the "c" table based on the pk, canonical_name, and user_id fields for faster lookup. 
	 * r: nom_canonique
	 * r.enUS: canonical_name
	 * r: ajour
	 * r.enUS: current
	 * r: nom_canonique
	 * r.enUS: canonical_name
	 * r: cree
	 * r.enUS: created
	 * r: id_utilisateur
	 * r.enUS: user_id
	 * r: utilisateur
	 * r.enUS: user
	 */
	public static final String SQL_uniqueIndexC = "create unique index if not exists c_index_utilisateur on c(pk, nom_canonique, id_utilisateur); ";

	/**
	 * enUS: A SQL query for creating a database table "a" to store relations (like entity relations) between one other record in the "c" table with another record in the "c" table. 
	 * r: entite
	 * r.enUS: entity
	 * r: actuel
	 * r.enUS: current
	 * r: cree
	 * r.enUS: created
	 */
	public static final String SQL_createTableA = "create table if not exists a(pk bigserial primary key, pk1 bigint, entite1 text, pk2 bigint, entite2 text, actuel boolean, cree timestamp with time zone, constraint a_constraint unique (pk1, entite1, pk2, entite2)); ";

	/**
	 * enUS: A SQL query for creating an index on the "a" table based on fields for faster lookup. 
	 * r: entite
	 * r.enUS: entity
	 * r: actuel
	 * r.enUS: current
	 * r: cree
	 * r.enUS: created
	 */
	public static final String SQL_uniqueIndexA = "create index if not exists a_index on a(pk1, pk2, current); ";

	/**
	 * enUS: A SQL query for creating a database table "d" to store String values to define fields in an instance of a class based on a record in the "c" table. 
	 * r: actuel
	 * r.enUS: current
	 * r: cree
	 * r.enUS: created
	 * r: valeur
	 * r.enUS: value
	 * r: chemin
	 * r.enUS: path
	 */
	public static final String SQL_createTableD = "create table if not exists d(pk bigserial primary key, pk_c bigint, chemin text, valeur text, actuel boolean, cree timestamp with time zone, constraint d_constraint unique (pk_c, chemin)); ";

	/**
	 * enUS: A SQL query for creating an index on the "d" table based on fields for faster lookup. 
	 * r: actuel
	 * r.enUS: current
	 * r: cree
	 * r.enUS: created
	 * r: valeur
	 * r.enUS: value
	 * r: chemin
	 * r.enUS: path
	 */
	public static final String SQL_uniqueIndexD = "create index if not exists d_index on d(pk_c, current); ";

	/**
	 * enUS: A io.vertx.ext.jdbc.JDBCClient for connecting to the relational database PostgreSQL. 
	 */
	private PgPool pgPool;

	/**
	 * enUS: A site context object for storing information about the entire site in English. 
	 * Var.enUS: siteContextEnUS
	 */
	SiteContexteFrFR siteContexteFrFR;

	/**
	 * enUS: For logging information and errors in the application. 
	 * r: AppliVertx
	 * r.enUS: AppVertx
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AppliVertx.class);

	/**
	 * r: CoureurVertx
	 * r.enUS: RunnerVertx
	 * r: AppliVertx
	 * r.enUS: AppVertx
	 * enUS: The main method for the Vert.x application that runs the Vert.x Runner class
	 */
	public static void main(String[] args) {
		CoureurVertx.run(AppliVertx.class);
	}

	/**
	 * Param1.var.enUS: startPromise
	 * r: SiteContexteFrFR
	 * r.enUS: SiteContextEnUS
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: demarrerPromise
	 * r.enUS: startPromise
	 * r: configurerDonnees
	 * r.enUS: configureData
	 * r: configurerCluster
	 * r.enUS: configureCluster
	 * r: configurerOpenApi
	 * r.enUS: configureOpenApi
	 * r: configurerControlesSante
	 * r.enUS: configureHealthChecks
	 * r: configurerExecuteurTravailleurPartage
	 * r.enUS: configureSharedWorkerExecutor
	 * r: configurerWebsockets
	 * r.enUS: configureWebsockets
	 * r: configurerMail
	 * r.enUS: configureEmail
	 * r: demarrerServeur
	 * r.enUS: startServer
	 * r: etapesPromises
	 * r.enUS: promiseSteps
	 * r: configurerAuthorizeNetPaiements
	 * r.enUS: configureAuthorizeNetPayments
	 * r: configurerAuthorizeNetFrais
	 * r.enUS: configureAuthorizeNetCharges
	 * 
	 * enUS: This is called by Vert.x when the verticle instance is deployed. 
	 * enUS: Initialize a new site context object for storing information about the entire site in English. 
	 * enUS: Setup the startPromise to handle the configuration steps and starting the server. 
	 */
	@Override
	public void start(Promise<Void> demarrerPromise) throws Exception {

		siteContexteFrFR = new SiteContexteFrFR();
		siteContexteFrFR.setVertx(vertx);
		siteContexteFrFR.initLoinSiteContexteFrFR();

		Future<Void> etapesPromises = configurerDonnees().future().compose(a -> 
			configurerCluster().future().compose(b -> 
				configurerOpenApi().future().compose(c -> 
					configurerControlesSante().future().compose(d -> 
						configurerExecuteurTravailleurPartage().future().compose(e -> 
							configurerWebsockets().future().compose(f -> 
								configurerMail().future().compose(g -> 
									configurerAuthorizeNetFrais().future().compose(h -> 
//										configurerAuthorizeNetPaiements(1).future().compose(i -> 
//											configurerAuthorizeNetPaiements(2).future().compose(j -> 
												demarrerServeur().future()
//											)
//										)
									)
								)
							)
						)
					)
				)
			)
		);
		etapesPromises.setHandler(demarrerPromise);
	}

	/**
	 * Var.enUS: configureData
	 * 
	 * Val.ConnectionError.enUS:Could not open the database client connection. 
	 * Val.ErreurConnexion.frFR:Impossible d'ouvrir la connexion du client de base de données. 
	 * Val.ConnectionSuccess.enUS:The database client connection was successful. 
	 * Val.SuccesConnexion.frFR:La connexion du client de base de données a réussi. 
	 * 
	 * Val.InitError.enUS:Could not initialize the database tables. 
	 * Val.ErreurInit.frFR:Impossible d'initialiser les tables de la base de données. 
	 * Val.InitSuccess.enUS:The database tables were created successfully. 
	 * Val.SuccesInit.frFR:Les tables de base de données ont été créées avec succès. 
	 * 
	 * enUS: Configure shared database connections across the cluster for massive scaling of the application. 
	 * enUS: Return a promise that configures a shared database client connection. 
	 * enUS: Load the database configuration into a shared io.vertx.ext.jdbc.JDBCClient for a scalable, clustered datasource connection pool. 
	 * enUS: Initialize the database tables if not already created for the first time. 
	 * 
	 * r: configurerDonnees
	 * r.enUS: configureData
	 * r: ErreurConnexion
	 * r.enUS: ConnectionError
	 * r: SuccesConnexion
	 * r.enUS: ConnectionSuccess
	 * r: ErreurInit
	 * r.enUS: InitError
	 * r: SuccesInit
	 * r.enUS: InitSuccess
	 * r: initTout
	 * r.enUS: initAll
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: getJdbcClassePilote
	 * r.enUS: getJdbcDriverClass
	 * r: getJdbcUtilisateur
	 * r.enUS: getJdbcUsername
	 * r: getJdbcMotDePasse
	 * r.enUS: getJdbcPassword
	 * r: getJdbcTailleMaxPiscine
	 * r.enUS: getJdbcMaxPoolSize
	 * r: getJdbcTailleInitialePiscine
	 * r.enUS: getJdbcInitialPoolSize
	 * r: getJdbcTailleMinPiscine
	 * r.enUS: getJdbcMinPoolSize
	 * r: getJdbcMaxDeclarationsParConnexion
	 * r.enUS: getJdbcMaxStatementsPerConnection
	 * r: getJdbcMaxDeclarations
	 * r.enUS: getJdbcMaxStatements
	 * r: getJdbcTempsInactiviteMax
	 * r.enUS: getJdbcMaxIdleTime
	 * r: getJdbcHote
	 * r.enUS: getJdbcHost
	 * r: getJdbcBaseDeDonnees
	 * r.enUS: getJdbcDatabase
	 * r: JdbcDelaiConnexion
	 * r.enUS: JdbcConnectTimeout
	 * r: JdbcMaxFileAttente
	 * r.enUS: JdbcMaxWaitQueueSize
	 */
	private Promise<Void> configurerDonnees() {
		ConfigSite configSite = siteContexteFrFR.getConfigSite();
		Promise<Void> promise = Promise.promise();

		PgConnectOptions pgOptions = new PgConnectOptions();
		pgOptions.setPort(configSite.getJdbcPort());
		pgOptions.setHost(configSite.getJdbcHote());
		pgOptions.setDatabase(configSite.getJdbcBaseDeDonnees());
		pgOptions.setUser(configSite.getJdbcUtilisateur());
		pgOptions.setPassword(configSite.getJdbcMotDePasse());
		pgOptions.setIdleTimeout(configSite.getJdbcTempsInactiviteMax());
		pgOptions.setIdleTimeoutUnit(TimeUnit.SECONDS);
		pgOptions.setConnectTimeout(configSite.getJdbcDelaiConnexion());

		PoolOptions poolOptions = new PoolOptions();
		poolOptions.setMaxSize(configSite.getJdbcTailleMaxPiscine());
		poolOptions.setMaxWaitQueueSize(configSite.getJdbcMaxFileAttente());

		pgPool = PgPool.pool(vertx, pgOptions, poolOptions);

		siteContexteFrFR.setPgPool(pgPool);


		pgPool.preparedQuery(SQL_createTableC, a -> {
			if (a.succeeded()) {
				pgPool.preparedQuery(SQL_uniqueIndexC, b -> {
					if (b.succeeded()) {
						pgPool.preparedQuery(SQL_createTableA, c -> {
							if (c.succeeded()) {
								pgPool.preparedQuery(SQL_uniqueIndexA, d -> {
									if (d.succeeded()) {
										pgPool.preparedQuery(SQL_createTableD, e -> {
											if (e.succeeded()) {
												pgPool.preparedQuery(SQL_uniqueIndexD, f -> {
													if (f.succeeded()) {
														LOGGER.info(configurerDonneesSuccesInit);
														promise.complete();
													} else {
														LOGGER.error(configurerDonneesErreurInit, f.cause());
														promise.fail(f.cause());
													}
												});
											} else {
												LOGGER.error(configurerDonneesErreurInit, e.cause());
												promise.fail(e.cause());
											}
										});
									} else {
										LOGGER.error(configurerDonneesErreurInit, d.cause());
										promise.fail(d.cause());
									}
								});
							} else {
								LOGGER.error(configurerDonneesErreurInit, c.cause());
								promise.fail(c.cause());
							}
						});
					} else {
						LOGGER.error(configurerDonneesErreurInit, b.cause());
						promise.fail(b.cause());
					}
				});
			} else {
				LOGGER.error(configurerDonneesErreurInit, a.cause());
				promise.fail(a.cause());
			}
		});

		return promise;
	}

	/**  
	 * Var.enUS: configureCluster
	 * 
	 * Val.DataError.enUS:Could not configure the shared cluster data. 
	 * Val.ErreurDonnees.frFR:Impossible de configurer les données du cluster partagé.
	 * Val.DataSuccess.enUS:The shared cluster data was configured successfully. 
	 * Val.SuccesDonnees.frFR:Les données du cluster partagé ont été configurées avec succès. 
	 * 
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: donneesPartagees
	 * r.enUS: sharedData
	 * r: donneesCluster
	 * r.enUS: clusterData
	 * r: configurerCluster
	 * r.enUS: configureCluster
	 * r: ErreurDonnees
	 * r.enUS: DataError
	 * r: SuccesDonnees
	 * r.enUS: DataSuccess
	 * 
	 * enUS: Configure shared data across the cluster for massive scaling of the application. 
	 * enUS: Return a promise that configures a shared cluster data. 
	 */
	private Promise<Void> configurerCluster() {
		ConfigSite configSite = siteContexteFrFR.getConfigSite();
		Promise<Void> promise = Promise.promise();
		SharedData donneesPartagees = vertx.sharedData();
		donneesPartagees.getClusterWideMap("donneesCluster", res -> {
			if (res.succeeded()) {
				AsyncMap<Object, Object> donneesCluster = res.result();
				donneesCluster.put("configSite", configSite, resPut -> {
					if (resPut.succeeded()) {
						LOGGER.info(configurerClusterSuccesDonnees);
						promise.complete();
					} else {
						LOGGER.error(configurerClusterErreurDonnees, res.cause());
						promise.fail(res.cause());
					}
				});
			} else {
				LOGGER.error(configurerClusterErreurDonnees, res.cause());
				promise.fail(res.cause());
			}
		});
		return promise;
	}

	/**
	 * Var.enUS: configureOpenApi
	 * 
	 * enUS: Configure the connection to the auth server and setup the routes based on the OpenAPI definition. 
	 * enUS: Setup a callback route when returning from the auth server after successful authentication. 
	 * enUS: Setup a logout route for logging out completely of the application. 
	 * enUS: Return a promise that configures the authentication server and OpenAPI. 
	 * 
	 * Val.Error.enUS:Could not configure the auth server and API. 
	 * Val.Erreur.frFR:Impossible de configurer le serveur auth et le API.
	 * Val.Success.enUS:The auth server and API was configured successfully. 
	 * Val.Succes.frFR:Le serveur auth et le API ont été configurées avec succès. 
	 * 
	 * Val.PhotoError.enUS:Could not process the photo upload. 
	 * Val.PhotoErreur.frFR:Impossible de traiter le photo téléchargement. 
	 * Val.PhotoSuccess.enUS:Photo successfully uploaded. 
	 * Val.PhotoSucces.frFR:Photo téléchargement a réussi. 
	 * 
	 * r: listeRecherche
	 * r.enUS: searchList
	 * r: initLoinRequeteApi
	 * r.enUS: initDeepApiRequest
	 * r: RequeteApi
	 * r.enUS: ApiRequest
	 * r: requeteApi
	 * r.enUS: apiRequest
	 * r: genererRequeteSiteFrFRPourEnfantScolaire
	 * r.enUS: generateSiteRequestEnUSForSchoolChild
	 * r: genererRequeteSiteFrFRPourMereScolaire
	 * r.enUS: generateSiteRequestEnUSForSchoolMom
	 * r: genererRequeteSiteFrFRPourPereScolaire
	 * r.enUS: generateSiteRequestEnUSForSchoolDad
	 * r: genererRequeteSiteFrFRPourGardienScolaire
	 * r.enUS: generateSiteRequestEnUSForSchoolGuardian
	 * r: genererRequeteSiteFrFRPourInscriptionScolaire
	 * r.enUS: generateSiteRequestEnUSForSchoolEnrollment
	 * r: RequeteSiteFrFR
	 * r.enUS: SiteRequestEnUS
	 * r: setStocker
	 * r.enUS: setStore
	 * r: initLoinPourClasse
	 * r.enUS: initDeepForClass
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: "EnfantScolaire"
	 * r.enUS: "SchoolChild"
	 * r: "MereScolaire"
	 * r.enUS: "SchoolMom"
	 * r: "PereScolaire"
	 * r.enUS: "SchoolDad"
	 * r: "GardienScolaire"
	 * r.enUS: "SchoolGuardian"
	 * r: "InscriptionScolaire"
	 * r.enUS: "SchoolEnrollment"
	 * r: EnfantScolaireFrFRApiServiceImpl
	 * r.enUS: SchoolChildEnUSApiServiceImpl
	 * r: MereScolaireFrFRApiServiceImpl
	 * r.enUS: SchoolMomEnUSApiServiceImpl
	 * r: PereScolaireFrFRApiServiceImpl
	 * r.enUS: SchoolDadEnUSApiServiceImpl
	 * r: GardienScolaireFrFRApiServiceImpl
	 * r.enUS: SchoolGuardianEnUSApiServiceImpl
	 * r: InscriptionScolaireFrFRApiServiceImpl
	 * r.enUS: SchoolEnrollmentEnUSApiServiceImpl
	 * r: EnfantScolaire
	 * r.enUS: SchoolChild
	 * r: MereScolaire
	 * r.enUS: SchoolMom
	 * r: PereScolaire
	 * r.enUS: SchoolDad
	 * r: GardienScolaire
	 * r.enUS: SchoolGuardian
	 * r: InscriptionScolaire
	 * r.enUS: SchoolEnrollment
	 * r: ListeRecherche
	 * r.enUS: SearchList
	 * r: "fichier"
	 * r.enUS: "file"
	 * r: classeNomSimple
	 * r.enUS: classSimpleName
	 * r: telechargement
	 * r.enUS: upload
	 * r: octets
	 * r.enUS: bytes
	 * r: nomFichier
	 * r.enUS: fileName
	 * r: largeur
	 * r.enUS: width
	 * r: hauteur
	 * r.enUS: height
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: siteNomHote
	 * r.enUS: siteHostName
	 * r: routeur
	 * r.enUS: router
	 * r: usineRouteur
	 * r.enUS: routerFactory
	 * r: UsineRouteur
	 * r.enUS: RouterFactory
	 * r: authFournisseur
	 * r.enUS: authProvider
	 * r: getAuthRoyaume
	 * r.enUS: getAuthRealm
	 * r: getAuthRessource
	 * r.enUS: getAuthResource
	 * r: getAuthSslRequis
	 * r.enUS: getAuthSslRequired
	 * r: getSiteNomHote
	 * r.enUS: getSiteHostName
	 * r: gestionnaireAuth
	 * r.enUS: authHandler
	 * r: getSiteUrlBase
	 * r.enUS: getSiteBaseUrl
	 * r: sessionIdAvant
	 * r.enUS: sessionIdBefore
	 * r: setRouteur
	 * r.enUS: setRouter
	 * r: deconnexion
	 * r.enUS: logout
	 * r: Erreur
	 * r.enUS: Error
	 * r: Succes
	 * r.enUS: Success
	 * r: configurerOpenApi
	 * r.enUS: configureOpenApi
	 * r: openapi3-frFR.yaml
	 * r.enUS: openapi3-enUS.yaml
	 * r: /ecole
	 * r.enUS: /school
	 * r: "computate-scolaire-frFR-session"
	 * r.enUS: "computate-scolaire-enUS-session"
	 */
	private Promise<Void> configurerOpenApi() {
		ConfigSite configSite = siteContexteFrFR.getConfigSite();
		Promise<Void> promise = Promise.promise();
		String siteUrlBase = configSite.getSiteUrlBase();

		OAuth2ClientOptions oauth2ClientOptions = new OAuth2ClientOptions();
		oauth2ClientOptions.setSite(configSite.getAuthUrl() + "/realms/" + configSite.getAuthRoyaume());
		oauth2ClientOptions.setClientID(configSite.getAuthRessource());
		oauth2ClientOptions.setClientSecret(configSite.getAuthSecret());
		oauth2ClientOptions.setFlow(OAuth2FlowType.AUTH_CODE);
		JsonObject extraParams = new JsonObject();
		extraParams.put("scope", "openid DefaultAuthScope SiteAdminScope");
		oauth2ClientOptions.setExtraParameters(extraParams);

		OpenIDConnectAuth.discover(vertx, oauth2ClientOptions, a -> {
			if(a.succeeded()) {
				OAuth2Auth authFournisseur = a.result();
	
				OAuth2AuthHandler gestionnaireAuth = OAuth2AuthHandler.create(authFournisseur, siteUrlBase + "/callback");
				gestionnaireAuth.addAuthority("DefaultAuthScope");
				gestionnaireAuth.addAuthority("SiteAdminScope");
				gestionnaireAuth.addAuthority("openid");
				{
					Router tempRouter = Router.router(vertx);
					gestionnaireAuth.setupCallback(tempRouter.get("/callback"));
				}
		
		//		ClusteredSessionStore sessionStore = ClusteredSessionStore.create(vertx);
				LocalSessionStore sessionStore = LocalSessionStore.create(vertx, "computate-scolaire-sessions");
				SessionHandler sessionHandler = SessionHandler.create(sessionStore);
				sessionHandler.setAuthProvider(authFournisseur);
		
				OpenAPI3RouterFactory.create(vertx, "openapi3-frFR.yaml", b -> {
					if (b.succeeded()) {
						OpenAPI3RouterFactory usineRouteur = b.result();
						usineRouteur.mountServicesFromExtensions();
						siteContexteFrFR.setUsineRouteur(usineRouteur);
		
						usineRouteur.addGlobalHandler(sessionHandler);
						usineRouteur.addHandlerByOperationId("callback", ctx -> {
		
							// Handle the callback of the flow
							final String code = ctx.request().getParam("code");
		
							// code is a require value
							if (code == null) {
								ctx.fail(400);
								return;
							}
		
							final String state = ctx.request().getParam("state");
		
							final JsonObject config = new JsonObject().put("code", code);
		
							config.put("redirect_uri", siteUrlBase + "/callback");
		
							authFournisseur.authenticate(config, res -> {
								if (res.failed()) {
									ctx.fail(res.cause());
								} else {
									AccessToken token = (AccessToken) res.result();
//									token.isAuthorized("SiteAdminScope", r -> {
//										if(r.succeeded()) {
											ctx.setUser(res.result());
											Session session = ctx.session();
											if (session != null) {
												// the user has upgraded from unauthenticated to authenticated
												// session should be upgraded as recommended by owasp
												ctx.addCookie(Cookie.cookie("sessionIdAvant", session.id()));
												session.regenerateId();
												// we should redirect the UA so this link becomes invalid
												ctx.response()
														// disable all caching
														.putHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
														.putHeader("Pragma", "no-cache").putHeader(HttpHeaders.EXPIRES, "0")
														// redirect (when there is no state, redirect to home
														.putHeader(HttpHeaders.LOCATION, state != null ? state : "/").setStatusCode(302)
														.end("Redirecting to " + (state != null ? state : "/") + ".");
											} else {
												// there is no session object so we cannot keep state
												ctx.reroute(state != null ? state : "/");
											}
//										} else {
//											String str = new JsonObject()
//													.put("error", new JsonObject())
//													.put("message", "Unauthorized").encodePrettily();
//											Buffer buffer = Buffer.buffer().appendString(str);
//											ctx.response().putHeader("Content-Length", Integer.toString(buffer.length()));
//											ctx.response().write(buffer);
//											ctx.response().setStatusCode(403);
//											ctx.response().end();
//										}
//									});
								}
							});
						});
						usineRouteur.addFailureHandlerByOperationId("callback", c -> {});
		
						usineRouteur.addHandlerByOperationId("deconnexion", rc -> {
							Session session = rc.session();
//							if (session != null) {
//								session.destroy();
//							}
							rc.clearUser();
							rc.reroute("/");
						});
						usineRouteur.addFailureHandlerByOperationId("deconnexion", c -> {});
		
						usineRouteur.addHandlerByOperationId("photo", rc -> {
//							rc.response().setChunked(true);
							Boolean photo = false;
							for(FileUpload telechargement : rc.fileUploads()) {
								if("fichier".equals(telechargement.name())) {
									photo = true;
									String nomFichier = StringUtils.substringBeforeLast(Paths.get(telechargement.uploadedFileName()).getFileName().toString(), ".") + ".png";
									vertx.fileSystem().readFile(telechargement.uploadedFileName(), d -> {
										if (d.succeeded()) {
											try {
												Long pk = Long.parseLong(rc.request().params().get("pk"));
												String classeNomSimple = rc.request().params().get("classeNomSimple");
												Buffer buffer = d.result();
												byte[] octets = buffer.getBytes();
												ByteArrayInputStream is = new ByteArrayInputStream(octets);
							
												BufferedImage img = ImageIO.read(is);
												is.close();
												int maxWidth = 300;
												int maxHeight = 300;
									
												int type = img.getType() == 0? BufferedImage.TYPE_INT_ARGB : img.getType();
									
												//*Special* if the width or height is 0 use image src dimensions
												if (maxWidth == 0) {
													maxWidth = img.getWidth();
												}
												if (maxHeight == 0) {
													maxHeight = img.getHeight();
												}
									
												int hauteur = maxHeight;
												int largeur = maxWidth;
									
												//Work out the resized width/height
												hauteur = maxHeight;
												int wid = maxWidth;
												float sum = (float)img.getWidth() / (float)img.getHeight();
												largeur = Math.round(hauteur * sum);
									
												if (largeur > wid) {
													//rezise again for the width this time
													hauteur = Math.round(wid/sum);
													largeur = wid;
												}
									
												BufferedImage resizedImage = new BufferedImage(largeur, hauteur, type);
												Graphics2D g = resizedImage.createGraphics();
												g.setComposite(AlphaComposite.Src);
									
												g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
												g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
												g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
									
												g.drawImage(img, 0, 0, largeur, hauteur, null);
												g.dispose(); 
									
												ByteArrayOutputStream os = new ByteArrayOutputStream();
												ImageIO.write(resizedImage, "png", os);
												String str = "data:image/png;base64," + Base64.getEncoder().encodeToString(os.toByteArray());
												os.close();

												if("EnfantScolaire".equals(classeNomSimple)) {
													EnfantScolaireFrFRApiServiceImpl service = new EnfantScolaireFrFRApiServiceImpl(siteContexteFrFR);
													JsonObject json = new JsonObject();
													json.put("setPhoto", str);
													RequeteSiteFrFR requeteSite = service.genererRequeteSiteFrFRPourEnfantScolaire(siteContexteFrFR, null, json);

													RequeteApi requeteApi = new RequeteApi();
													requeteApi.setRows(1);
													requeteApi.setNumFound(1l);
													requeteApi.setNumPATCH(0L);
													requeteApi.initLoinRequeteApi(requeteSite);
													requeteSite.setRequeteApi_(requeteApi);
													requeteSite.getVertx().eventBus().publish("websocketEnfantScolaire", JsonObject.mapFrom(requeteApi).toString());
									
													ListeRecherche<EnfantScolaire> listeRecherche = new ListeRecherche<EnfantScolaire>();
													listeRecherche.setStocker(true);
													listeRecherche.setQuery("*:*");
													listeRecherche.setC(EnfantScolaire.class);
													listeRecherche.addFilterQuery("pk_indexed_long:" + pk);
													listeRecherche.initLoinPourClasse(requeteSite);
									
													if(listeRecherche.size() == 1) {
														EnfantScolaire o = listeRecherche.getList().stream().findFirst().orElse(null);
														service.patchEnfantScolaireFuture(o, false, e -> {
															if(e.succeeded()) {
																rc.response().end("{}");
															}
															else {
																LOGGER.error(configurerOpenApiPhotoErreur, e.cause());
																rc.fail(500, e.cause());
															}
														});
													} else {
														rc.response().end("{}");
													}
												}

												if("MereScolaire".equals(classeNomSimple)) {
													MereScolaireFrFRApiServiceImpl service = new MereScolaireFrFRApiServiceImpl(siteContexteFrFR);
													JsonObject json = new JsonObject();
													json.put("setPhoto", str);
													RequeteSiteFrFR requeteSite = service.genererRequeteSiteFrFRPourMereScolaire(siteContexteFrFR, null, json);

													RequeteApi requeteApi = new RequeteApi();
													requeteApi.setRows(1);
													requeteApi.setNumFound(1l);
													requeteApi.setNumPATCH(0L);
													requeteApi.initLoinRequeteApi(requeteSite);
													requeteSite.setRequeteApi_(requeteApi);
													requeteSite.getVertx().eventBus().publish("websocketMereScolaire", JsonObject.mapFrom(requeteApi).toString());
									
													ListeRecherche<MereScolaire> listeRecherche = new ListeRecherche<MereScolaire>();
													listeRecherche.setStocker(true);
													listeRecherche.setQuery("*:*");
													listeRecherche.setC(MereScolaire.class);
													listeRecherche.addFilterQuery("pk_indexed_long:" + pk);
													listeRecherche.initLoinPourClasse(requeteSite);
									
													if(listeRecherche.size() == 1) {
														MereScolaire o = listeRecherche.getList().stream().findFirst().orElse(null);
														service.patchMereScolaireFuture(o, false, e -> {
															if(e.succeeded()) {
																rc.response().end("{}");
															}
															else {
																LOGGER.error(configurerOpenApiPhotoErreur, e.cause());
																rc.fail(500, e.cause());
															}
														});
													} else {
														rc.response().end("{}");
													}
												}

												if("PereScolaire".equals(classeNomSimple)) {
													PereScolaireFrFRApiServiceImpl service = new PereScolaireFrFRApiServiceImpl(siteContexteFrFR);
													JsonObject json = new JsonObject();
													json.put("setPhoto", str);
													RequeteSiteFrFR requeteSite = service.genererRequeteSiteFrFRPourPereScolaire(siteContexteFrFR, null, json);

													RequeteApi requeteApi = new RequeteApi();
													requeteApi.setRows(1);
													requeteApi.setNumFound(1l);
													requeteApi.setNumPATCH(0L);
													requeteApi.initLoinRequeteApi(requeteSite);
													requeteSite.setRequeteApi_(requeteApi);
													requeteSite.getVertx().eventBus().publish("websocketPereScolaire", JsonObject.mapFrom(requeteApi).toString());
									
													ListeRecherche<PereScolaire> listeRecherche = new ListeRecherche<PereScolaire>();
													listeRecherche.setStocker(true);
													listeRecherche.setQuery("*:*");
													listeRecherche.setC(PereScolaire.class);
													listeRecherche.addFilterQuery("pk_indexed_long:" + pk);
													listeRecherche.initLoinPourClasse(requeteSite);
									
													if(listeRecherche.size() == 1) {
														PereScolaire o = listeRecherche.getList().stream().findFirst().orElse(null);
														service.patchPereScolaireFuture(o, false, e -> {
															if(e.succeeded()) {
																rc.response().end("{}");
															}
															else {
																LOGGER.error(configurerOpenApiPhotoErreur, e.cause());
																rc.fail(500, e.cause());
															}
														});
													} else {
														rc.response().end("{}");
													}
												}

												if("GardienScolaire".equals(classeNomSimple)) {
													GardienScolaireFrFRApiServiceImpl service = new GardienScolaireFrFRApiServiceImpl(siteContexteFrFR);
													JsonObject json = new JsonObject();
													json.put("setPhoto", str);
													RequeteSiteFrFR requeteSite = service.genererRequeteSiteFrFRPourGardienScolaire(siteContexteFrFR, null, json);

													RequeteApi requeteApi = new RequeteApi();
													requeteApi.setRows(1);
													requeteApi.setNumFound(1l);
													requeteApi.setNumPATCH(0L);
													requeteApi.initLoinRequeteApi(requeteSite);
													requeteSite.setRequeteApi_(requeteApi);
													requeteSite.getVertx().eventBus().publish("websocketGardienScolaire", JsonObject.mapFrom(requeteApi).toString());
									
													ListeRecherche<GardienScolaire> listeRecherche = new ListeRecherche<GardienScolaire>();
													listeRecherche.setStocker(true);
													listeRecherche.setQuery("*:*");
													listeRecherche.setC(GardienScolaire.class);
													listeRecherche.addFilterQuery("pk_indexed_long:" + pk);
													listeRecherche.initLoinPourClasse(requeteSite);
									
													if(listeRecherche.size() == 1) {
														GardienScolaire o = listeRecherche.getList().stream().findFirst().orElse(null);
														service.patchGardienScolaireFuture(o, false, e -> {
															if(e.succeeded()) {
																rc.response().end("{}");
															}
															else {
																LOGGER.error(configurerOpenApiPhotoErreur, e.cause());
																rc.fail(500, e.cause());
															}
														});
													} else {
														rc.response().end("{}");
													}
												}

												if("InscriptionScolaire".equals(classeNomSimple)) {
													InscriptionScolaireFrFRApiServiceImpl service = new InscriptionScolaireFrFRApiServiceImpl(siteContexteFrFR);
													JsonObject json = new JsonObject();
													json.put("setPhoto", str);
													RequeteSiteFrFR requeteSite = service.genererRequeteSiteFrFRPourInscriptionScolaire(siteContexteFrFR, null, json);

													RequeteApi requeteApi = new RequeteApi();
													requeteApi.setRows(1);
													requeteApi.setNumFound(1l);
													requeteApi.setNumPATCH(0L);
													requeteApi.initLoinRequeteApi(requeteSite);
													requeteSite.setRequeteApi_(requeteApi);
													requeteSite.getVertx().eventBus().publish("websocketInscriptionScolaire", JsonObject.mapFrom(requeteApi).toString());
									
													ListeRecherche<InscriptionScolaire> listeRecherche = new ListeRecherche<InscriptionScolaire>();
													listeRecherche.setStocker(true);
													listeRecherche.setQuery("*:*");
													listeRecherche.setC(InscriptionScolaire.class);
													listeRecherche.addFilterQuery("pk_indexed_long:" + pk);
													listeRecherche.initLoinPourClasse(requeteSite);
									
													if(listeRecherche.size() == 1) {
														InscriptionScolaire o = listeRecherche.getList().stream().findFirst().orElse(null);
														service.patchInscriptionScolaireFuture(o, false, e -> {
															if(e.succeeded()) {
																rc.response().end("{}");
															}
															else {
																LOGGER.error(configurerOpenApiPhotoErreur, e.cause());
																rc.fail(500, e.cause());
															}
														});
													} else {
														rc.response().end("{}");
													}
												}
											} catch(Exception ex) {
												LOGGER.error(configurerOpenApiPhotoErreur, ex);
												rc.fail(500, ex);
											}
										}
									});
								}
								if(!photo)
									rc.response().end("{}");
								telechargement.fileName();
							}
						});
						usineRouteur.addFailureHandlerByOperationId("photo", c -> {
							LOGGER.error("Upload failed. ");
						});
		
//						usineRouteur.addSecurityHandler("openIdConnect", gestionnaireAuth);
						usineRouteur.addSecuritySchemaScopeValidator("openIdConnect", "DefaultAuthScope", gestionnaireAuth);
						usineRouteur.addSecuritySchemaScopeValidator("openIdConnect", "SiteAdminScope", gestionnaireAuth);
						usineRouteur.addSecuritySchemaScopeValidator("openIdConnect", "openid", gestionnaireAuth);
						Router routeur = usineRouteur.getRouter();
						siteContexteFrFR.setRouteur(routeur);
		
						LOGGER.info(configurerOpenApiSucces);
						promise.complete();
					} else {
						LOGGER.error(configurerOpenApiErreur, b.cause());
						promise.fail(b.cause());
					}
				});
			} else {
				LOGGER.error(configurerOpenApiErreur, a.cause());
				promise.fail(a.cause());
			}
		});
		return promise;
	}

	/**
	 * Var.enUS: configureSharedWorkerExecutor
	 * 
	 * enUS: Configure a shared worker executor for running blocking tasks in the background. 
	 * enUS: Return a promise that configures the shared worker executor. 
	 * 
	 * r: executeurTravailleur
	 * r.enUS: workerExecutor
	 * r: ExecuteurTravailleur
	 * r.enUS: WorkerExecutor
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 */
	private Promise<Void> configurerExecuteurTravailleurPartage() {
		Promise<Void> promise = Promise.promise();

		WorkerExecutor executeurTravailleur = vertx.createSharedWorkerExecutor("WorkerExecutor");
		siteContexteFrFR.setExecuteurTravailleur(executeurTravailleur);
		promise.complete();
		return promise;
	}

	/**
	 * Var.enUS: configureHealthChecks
	 * 
	 * Val.ErrorDatabase.enUS:The database is not configured properly. 
	 * Val.ErreurBaseDeDonnees.frFR:La base de données n'est pas configurée correctement. 
	 * 
	 * Val.EmptySolr.enUS:The Solr search engine is empty. 
	 * Val.VideSolr.frFR:Le moteur de recherche Solr est vide. 
	 * 
	 * Val.ErrorSolr.enUS:The Solr search engine is not configured properly. 
	 * Val.ErreurSolr.frFR:Le moteur de recherche Solr n'est pas configuré correctement. 
	 * 
	 * Val.ErrorVertx.enUS:The Vert.x application is not configured properly. 
	 * Val.ErreurVertx.frFR:L'application Vert.x n'est pas configuré correctement. 
	 * 
	 * enUS: Configure health checks for the status of the website and it's dependent services. 
	 * enUS: Return a promise that configures the health checks. 
	 * 
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: UsineRouteur
	 * r.enUS: RouterFactory
	 * r: gestionnaireControlesSante
	 * r.enUS: healthCheckHandler
	 * r: baseDeDonnees
	 * r.enUS: database
	 * r: configurerControlesSanteErreurBaseDeDonnees
	 * r.enUS: configureHealthChecksErrorDatabase
	 * r: configurerControlesSanteErreurSolr
	 * r.enUS: configureHealthChecksErrorSolr
	 * r: configurerControlesSanteVideSolr
	 * r.enUS: configureHealthChecksEmptySolr
	 * r: configurerControlesSanteErreurVertx
	 * r.enUS: configureHealthChecksErrorVertx
	 * r: ClientSolr
	 * r.enUS: SolrClient
	 * r: getRouteur
	 * r.enUS: getRouter
	 */
	private Promise<Void> configurerControlesSante() {
		Promise<Void> promise = Promise.promise();
		Router siteRouteur = siteContexteFrFR.getRouteur();
		HealthCheckHandler gestionnaireControlesSante = HealthCheckHandler.create(vertx);

		gestionnaireControlesSante.register("baseDeDonnees", 2000, a -> {
			siteContexteFrFR.getPgPool().preparedQuery("select current_timestamp" , selectCAsync -> {
				if(selectCAsync.succeeded()) {
					a.complete(Status.OK());
				} else {
					LOGGER.error(configurerControlesSanteErreurBaseDeDonnees, a.future().cause());
					promise.fail(a.future().cause());
				}
			});
		});
		gestionnaireControlesSante.register("solr", 2000, a -> {
			SolrQuery query = new SolrQuery();
			query.setQuery("*:*");
			try {
				QueryResponse r = siteContexteFrFR.getClientSolr().query(query);
				if(r.getResults().size() > 0)
					a.complete(Status.OK());
				else {
					LOGGER.error(configurerControlesSanteVideSolr, a.future().cause());
					promise.fail(a.future().cause());
				}
			} catch (SolrServerException | IOException e) {
				LOGGER.error(configurerControlesSanteErreurSolr, a.future().cause());
				promise.fail(a.future().cause());
			}
		});
		siteRouteur.get("/health").handler(gestionnaireControlesSante);
		promise.complete();
		return promise;
	}

	/**
	 * Var.enUS: configureWebsockets
	 * 
	 * enUS: Configure websockets for realtime messages. 
	 * 
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: gestionnaireSockJs
	 * r.enUS: sockJsHandler
	 * r: GestionnaireSockJs
	 * r.enUS: SockJsHandler
	 * r: siteRouteur
	 * r.enUS: siteRouter
	 * r: usineRouteur
	 * r.enUS: routerFactory
	 * r: UsineRouteur
	 * r.enUS: RouterFactory
	 * r: getRouteur
	 * r.enUS: getRouter
	 */
	private Promise<Void> configurerWebsockets() {
		Promise<Void> promise = Promise.promise();
		Router siteRouteur = siteContexteFrFR.getRouteur();
		BridgeOptions options = new BridgeOptions()
				.addOutboundPermitted(new PermittedOptions().setAddressRegex("websocket.*"));
		SockJSHandler gestionnaireSockJs = SockJSHandler.create(vertx);
		gestionnaireSockJs.bridge(options);
		siteRouteur.route("/eventbus/*").handler(gestionnaireSockJs);
		promise.complete();
		return promise;
	}

	/**
	 * Var.enUS: configureEmail
	 * 
	 * enUS: Configure sending email. 
	 * 
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: MailHote
	 * r.enUS: EmailHost
	 * r: MailPort
	 * r.enUS: EmailPort
	 * r: MailSsl
	 * r.enUS: EmailSsl
	 * r: MailUtilisateur
	 * r.enUS: EmailUsername
	 * r: MailMotDePasse
	 * r.enUS: EmailPassword
	 */
	private Promise<Void> configurerMail() {
		ConfigSite configSite = siteContexteFrFR.getConfigSite();
		Promise<Void> promise = Promise.promise();
		MailConfig config = new MailConfig();
		config.setHostname(configSite.getMailHote());
		config.setPort(configSite.getMailPort());
		config.setSsl(configSite.getMailSsl());
		config.setUsername(configSite.getMailUtilisateur());
		config.setPassword(configSite.getMailMotDePasse());
		MailClient mailClient = MailClient.createShared(vertx, config);
		siteContexteFrFR.setMailClient(mailClient);
		promise.complete();
		return promise;
	}

	/**
	 * Var.enUS: configureAuthorizeNetCharges
	 * 
	 * enUS: Configure charges with Authorize.net. 
	 * 
	 * r: "modifie_indexed_date"
	 * r.enUS: "modified_indexed_date"
	 * r: "supprime_indexed_boolean"
	 * r.enUS: "deleted_indexed_boolean"
	 * r: "archive_indexed_boolean"
	 * r.enUS: "archived_indexed_boolean"
	 * r: "Commencer à peupler les frais nouveaux. "
	 * r.enUS: "Start to populate the new charges. "
	 * r: "Init SQL a réussi. "
	 * r.enUS: "Init SQL succeeded. "
	 * r: "Il y a %s batch à charger. "
	 * r.enUS: "There are %s batches to load. "
	 * r: "batch %s chargé. "
	 * r.enUS: "batch %s loaded. "
	 * r: "inscriptions ont échoués. "
	 * r.enUS: "enrollments failed. "
	 * r: "Il y a %s inscriptions à recharger. "
	 * r.enUS: "There are %s enrollments to reload. "
	 * r: "Il y a %s paiements à recharger. "
	 * r.enUS: "There are %s payments to reload. "
	 * r: "inscription %s rechargé. "
	 * r.enUS: "enrollment %s refreshed. "
	 * r: "inscription %s a échoué. "
	 * r.enUS: "enrollment %s failed. "
	 * r: "paiement %s rechargé. "
	 * r.enUS: "payment %s refreshed. "
	 * r: "paiement %s a échoué. "
	 * r.enUS: "payement %s failed. "
	 * r: "Recharger les inscriptions a réussi. "
	 * r.enUS: "Refreshing the enrollments has succeeded. "
	 * r: "Commit la connexion SQL a réussi. "
	 * r.enUS: "Commit the SQL connection succeeded. "
	 * r: "Fermer la connexion SQL a réussi. "
	 * r.enUS: "Close the SQL connection has succeeded. "
	 * r: "Finir à peupler les transactions nouveaux. "
	 * r.enUS: "Finish populating the new transactions. "
	 * r: "Fermer la connexion SQL a échoué. "
	 * r.enUS: "Close the SQL connection has failed. "
	 * r: "Commit la connexion SQL a échoué. "
	 * r.enUS: "Commit the SQL connection has failed. "
	 * r: "Recharger les inscriptions a échoué. "
	 * r.enUS: "Refresh the enrollments failed. "
	 * r: "Authorize.net paiements a échoué. \n%s"
	 * r.enUS: "Authorize.net payments have failed. \n%s"
	 * r: "Init SQL a échoué. "
	 * r.enUS: "Init SQL failed. "
	 * r: "Authorize.net WorkerExecutor.executeBlocking a réussi. "
	 * r.enUS: "Authorize.net WorkerExecutor.executeBlocking succeeded. "
	 * r: "Authorize.net WorkerExecutor.executeBlocking a échoué. "
	 * r.enUS: "Authorize.net WorkerExecutor.executeBlocking failed. "
	 * r: PaiementScolaireFrFRApiServiceImpl
	 * r.enUS: SchoolPaymentEnUSApiServiceImpl
	 * r: InscriptionScolaireFrFRApiServiceImpl
	 * r.enUS: SchoolEnrollmentEnUSApiServiceImpl
	 * r: inscriptionScolaire
	 * r.enUS: schoolEnrollment
	 * r: InscriptionCles
	 * r.enUS: EnrollmentKeys
	 * r: InscriptionScolaire
	 * r.enUS: SchoolEnrollment
	 * r: listeRechercheInscription
	 * r.enUS: searchListEnrollment
	 * r: enfantPrenomPrefere
	 * r.enUS: childFirstNamePreferred
	 * r: merePrenomPrefere
	 * r.enUS: momFirstNamePreferred
	 * r: perePrenomPrefere
	 * r.enUS: dadFirstNamePreferred
	 * r: enfantPrenom
	 * r.enUS: childFirstName
	 * r: enfantFamilleNom
	 * r.enUS: childFamilyName
	 * r: setSiteContexte
	 * r.enUS: setSiteContext
	 * r: ExecuteurTravailleur
	 * r.enUS: WorkerExecutor
	 * r: initLoinRequeteSiteFrFR
	 * r.enUS: initDeepSiteRequestEnUS
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: RequeteSiteFrFR
	 * r.enUS: SiteRequestEnUS
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: RequeteSite
	 * r.enUS: SiteRequest
	 * r: initLoinListeRecherche
	 * r.enUS: initDeepSearchList
	 * r: ListeRecherche
	 * r.enUS: SearchList
	 * r: initLoinPaiementScolaire
	 * r.enUS: initDeepSchoolPayment
	 * r: indexerPaiementScolaire
	 * r.enUS: indexSchoolPayment
	 * r: PaiementScolaire
	 * r.enUS: SchoolPayment
	 * r: setPaiementPar
	 * r.enUS: setPaymentBy
	 * r: paiement
	 * r.enUS: payment
	 * r: PaiementMontant
	 * r.enUS: PaymentAmount
	 * r: PaiementDate
	 * r.enUS: PaymentDate
	 * r: PaiementSysteme
	 * r.enUS: PaymentSystem
	 * r: setStocker
	 * r.enUS: setStore
	 * r: ConnexionSql
	 * r.enUS: SqlConnection
	 * r: erreurAppliVertx
	 * r.enUS: errorAppVertx
	 * r: sessionDateDebut
	 * r.enUS: sessionStartDate
	 * r: sessionDateFin
	 * r.enUS: sessionEndDate
	 * r: inscriptionDateFrais
	 * r.enUS: enrollmentChargeDate
	 * r: futureAuthorizeNetFraisInscription
	 * r.enUS: futureAuthorizeNetEnrollmentCharges
	 * r: listeRecherche
	 * r.enUS: searchList
	 * 
	 * r: supprime
	 * r.enUS: deleted
	 * r: archive
	 * r.enUS: archived
	 * r: inscriptionCle
	 * r.enUS: enrollmentKey
	 * r: paiementCle
	 * r.enUS: paymentKey
	 * r: paiementService
	 * r.enUS: paymentService
	 * r: inscriptionService
	 * r.enUS: enrollmentService
	 * r: operationRequete
	 * r.enUS: operationRequest
	 * r: ObjetJson
	 * r.enUS: JsonObject
	 * r: debut
	 * r.enUS: start
	 * r: cree
	 * r.enUS: created
	 * r: Paiement
	 * r.enUS: Payment
	 */ 
	private Promise<Void> configurerAuthorizeNetFrais() {
		ConfigSite configSite = siteContexteFrFR.getConfigSite();
		ZoneId zoneId = ZoneId.of(configSite.getSiteZone());
		Promise<Void> promise = Promise.promise();

//		vertx.setPeriodic(1000 * 60 * 60 * 60, a -> {
		vertx.setPeriodic(1000 * 60, a -> {
			WorkerExecutor executeurTravailleur = siteContexteFrFR.getExecuteurTravailleur();
			executeurTravailleur.executeBlocking(
				blockingCodeHandler -> {
					LOGGER.info("Commencer à peupler les frais nouveaux. ");
					ZonedDateTime debut = ZonedDateTime.now(zoneId);
					RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
					requeteSite.setVertx(vertx);
					requeteSite.setSiteContexte_(siteContexteFrFR);
					requeteSite.setConfigSite_(siteContexteFrFR.getConfigSite());
					requeteSite.initLoinRequeteSiteFrFR(requeteSite);
					requeteSite.setObjetJson(new JsonObject());
		
					try {
						PaiementScolaireFrFRApiServiceImpl paiementService = new PaiementScolaireFrFRApiServiceImpl(siteContexteFrFR);
						InscriptionScolaireFrFRApiServiceImpl inscriptionService = new InscriptionScolaireFrFRApiServiceImpl(siteContexteFrFR);

						ZonedDateTime sessionDateDebut = ZonedDateTime.now(zoneId).plusMonths(1);
						// Mar 26 is last late fee. 
						// Mar 1 + 2 month = May 1 < May 20 last day
						ZonedDateTime sessionDateFin = ZonedDateTime.now(zoneId).plusMonths(2);
						ZonedDateTime inscriptionDateFrais = ZonedDateTime.now(zoneId).plusMonths(1);

						ListeRecherche<InscriptionScolaire> listeRechercheInscription = new ListeRecherche<InscriptionScolaire>();
						listeRechercheInscription.setStocker(true);
						listeRechercheInscription.setQuery("*:*");
						listeRechercheInscription.setC(InscriptionScolaire.class);
						listeRechercheInscription.addFilterQuery("supprime_indexed_boolean:false");
						listeRechercheInscription.addFilterQuery("archive_indexed_boolean:false");
						listeRechercheInscription.addFilterQuery("sessionDateDebut_indexed_date:[* TO " + dateFormat.format(sessionDateDebut) + "]");
						listeRechercheInscription.addFilterQuery("sessionDateFin_indexed_date:[" + dateFormat.format(sessionDateFin) + " TO *]");
						listeRechercheInscription.addFilterQuery("(*:* AND -inscriptionDateFrais_indexed_date:[* TO *] OR inscriptionDateFrais_indexed_date:[* TO " + dateFormat.format(inscriptionDateFrais) + "])");
						listeRechercheInscription.setRows(1);
						listeRechercheInscription.addSort("modifie_indexed_date", ORDER.desc);
						listeRechercheInscription.initLoinListeRecherche(requeteSite);
		

						futureAuthorizeNetFraisInscription(listeRechercheInscription, paiementService, inscriptionService, c -> {
							if(c.succeeded()) {
								try {
									ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
									listeRecherche.setStocker(true);
									listeRecherche.setQuery("*:*");
									listeRecherche.setC(PaiementScolaire.class);
									listeRecherche.addFilterQuery("cree_indexed_date:[" + dateFormat.format(ZonedDateTime.ofInstant(debut.toInstant(), ZoneId.of("UTC"))) + " TO *]");
									listeRecherche.add("json.facet", "{inscriptionCles:{terms:{field:inscriptionCle_indexed_long, limit:1000}}}");
									listeRecherche.setRows(100);
									listeRecherche.initLoinListeRecherche(requeteSite);
									SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeRecherche.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
									List<Long> inscriptionCles = Optional.ofNullable((SimpleOrderedMap)facets.get("inscriptionCles")).map(m -> ((List<SimpleOrderedMap>)m.get("buckets"))).orElse(Arrays.asList()).stream().collect(Collectors.mapping(m -> (Long)m.get("val"), Collectors.toList()));
	
									List<Future> futures = new ArrayList<>();
									LOGGER.info(String.format("Il y a %s inscriptions à recharger. ", inscriptionCles.size()));
									for(Long inscriptionCle : inscriptionCles) {
										InscriptionScolaire inscriptionScolaire = new InscriptionScolaire();
										inscriptionScolaire.setPk(inscriptionCle);
										inscriptionScolaire.setRequeteSite_(requeteSite);
										futures.add(
											inscriptionService.patchInscriptionScolaireFuture(inscriptionScolaire, false, d -> {
												if(d.succeeded()) {
													LOGGER.info(String.format("inscription %s rechargé. ", inscriptionCle));
												} else {
													LOGGER.error(String.format("inscription %s a échoué. ", inscriptionCle), d.cause());
													blockingCodeHandler.handle(Future.failedFuture(d.cause()));
												}
											})
										);
									}
									CompositeFuture.all(futures).setHandler(d -> {
										if(d.succeeded()) {
											LOGGER.info("Recharger les inscriptions a réussi. ");
											LOGGER.info("Finir à peupler les transactions nouveaux. ");
											blockingCodeHandler.handle(Future.succeededFuture(d.result()));
										} else {
											LOGGER.error("Recharger les inscriptions a échoué. ", d.cause());
											erreurAppliVertx(requeteSite, d);
										}
									});
								} catch (Exception e) {
									LOGGER.error(String.format("Authorize.net paiements a échoué. \n%s", ExceptionUtils.getStackTrace(e)), ExceptionUtils.getStackTrace(e));
									erreurAppliVertx(requeteSite, c);
								}
							} else {
								LOGGER.error(String.format("inscriptions ont échoués. "), c.cause());
								blockingCodeHandler.handle(Future.failedFuture(c.cause()));
							}
						});
					} catch (Exception e) {
						LOGGER.error(String.format("Authorize.net paiements a échoué. \n%s", ExceptionUtils.getStackTrace(e)), ExceptionUtils.getStackTrace(e));
						erreurAppliVertx(requeteSite, null);
					}
				}, resultHandler -> {
					if(resultHandler.succeeded()) {
						LOGGER.info("Authorize.net WorkerExecutor.executeBlocking a réussi. ");
					} else {
						LOGGER.error("Authorize.net WorkerExecutor.executeBlocking a échoué. ", resultHandler.cause());
					}
				}
			);
		});

		promise.complete();
		return promise;
	}

	/**
	 * Var.enUS: futureAuthorizeNetEnrollmentCharges
	 * Param1.var.enUS: listSchoolEnrollment
	 * Param2.var.enUS: paymentService
	 * Param3.var.enUS: enrollmentService
	 * Param4.var.enUS: eventHandler
	 * r: patchInscriptionScolaireFuture
	 * r.enUS: patchSchoolEnrollmentFuture
	 * r: inscriptionFraisFuture
	 * r.enUS: enrollmentChargesFuture
	 * r: genererRequeteSiteFrFRPourPaiementScolaire
	 * r.enUS: generateSiteRequestEnUSForSchoolPayment
	 * r: genererRequeteSiteFrFRPourInscriptionScolaire
	 * r.enUS: generateSiteRequestEnUSForSchoolEnrollment
	 * r: initLoinListeRecherche
	 * r.enUS: initDeepSearchList
	 * r: RequeteSiteFrFR
	 * r.enUS: SiteRequestEnUS
	 * r: RequeteSite
	 * r.enUS: SiteRequest
	 * r: listeInscriptionScolaire
	 * r.enUS: listSchoolEnrollment
	 * r: authorizeNetEnrollmentPaiementsFuture
	 * r.enUS: authorizeNetEnrollmentPaymentsFuture
	 * r: futureAuthorizeNetFraisInscription
	 * r.enUS: futureAuthorizeNetEnrollmentCharges
	 * r: futureAuthorizeNetFrais
	 * r.enUS: futureAuthorizeNetCharge
	 * r: futureAuthorizeNetInscriptionPaiements
	 * r.enUS: futureAuthorizeNetEnrollmentPayments
	 * r: ListeRecherche
	 * r.enUS: SearchList
	 * r: PaiementScolaireFrFRApiServiceImpl
	 * r.enUS: SchoolPaymentEnUSApiServiceImpl
	 * r: getSiteContexte
	 * r.enUS: getSiteContext
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: OperationRequete
	 * r.enUS: OperationRequest
	 * r: initLoinRequeteApi
	 * r.enUS: initDeepApiRequest
	 * r: RequeteApi
	 * r.enUS: ApiRequest
	 * r: setStocker
	 * r.enUS: setStore
	 * r: PaiementScolaire
	 * r.enUS: SchoolPayment
	 * r: paiementService
	 * r.enUS: paymentService
	 * r: inscriptionService
	 * r.enUS: enrollmentService
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: erreurAppliVertx
	 * r.enUS: errorAppVertx
	 * r: gestionnaireEvenements
	 * r.enUS: eventHandler
	 * r: "Créer un frais a réussi. "
	 * r.enUS: "Create a charge succeeded. "
	 * r: "Créer une liste de frais a réussi. "
	 * r.enUS: "Create a list of charges has succeeded. "
	 */
	public void futureAuthorizeNetFraisInscription(ListeRecherche<InscriptionScolaire> listeInscriptionScolaire, PaiementScolaireFrFRApiServiceImpl paiementService, InscriptionScolaireFrFRApiServiceImpl inscriptionService, Handler<AsyncResult<Void>> gestionnaireEvenements) {
		List<Future> futures = new ArrayList<>();
		RequeteSiteFrFR requeteSite = listeInscriptionScolaire.getRequeteSite_();
		listeInscriptionScolaire.getList().forEach(o -> {
			futures.add(
				inscriptionService.inscriptionFraisFuture(o, a -> {
					if(a.succeeded()) {
						inscriptionService.authorizeNetEnrollmentPaiementsFuture(o, b -> {
							if(b.succeeded()) {
								inscriptionService.patchInscriptionScolaireFuture(o, false, c -> {
									if(c.succeeded()) {
										LOGGER.info("Créer un frais a réussi. ");
									} else {
										erreurAppliVertx(requeteSite, c);
									}
								});
							} else {
								erreurAppliVertx(requeteSite, b);
							}
							if(b.succeeded()) {
								LOGGER.info("Creating payments for customer %s succeeded. ");
								List<Future> futures2 = new ArrayList<>();
		
								ListeRecherche<PaiementScolaire> searchList2 = new ListeRecherche<PaiementScolaire>();
								searchList2.setStocker(true);
								searchList2.setQuery("*:*");
								searchList2.setC(PaiementScolaire.class);
								searchList2.addFilterQuery("enrollmentKey_indexed_long:" + o.getPk());
								searchList2.setRows(100);
								searchList2.initLoinListeRecherche(requeteSite);
		
								for(PaiementScolaire o2 : searchList2.getList()) {
									PaiementScolaireFrFRApiServiceImpl service = new PaiementScolaireFrFRApiServiceImpl(requeteSite.getSiteContexte_());
									RequeteSiteFrFR requeteSite2 = paiementService.genererRequeteSiteFrFRPourPaiementScolaire(siteContexteFrFR, requeteSite.getOperationRequete(), new JsonObject());
									RequeteApi apiRequest2 = new RequeteApi();
									apiRequest2.setRows(1);
									apiRequest2.setNumFound(1l);
									apiRequest2.setNumPATCH(0L);
									apiRequest2.initLoinRequeteApi(requeteSite2);
									requeteSite2.setRequeteApi_(apiRequest2);
									requeteSite2.getVertx().eventBus().publish("websocketPaiementScolaire", JsonObject.mapFrom(apiRequest2).toString());
		
									o2.setRequeteSite_(requeteSite2);
									futures2.add(
										service.patchPaiementScolaireFuture(o2, false, c -> {
											if(c.succeeded()) {
											} else {
												LOGGER.info(String.format("PaiementScolaire %s failed. ", o2.getPk()));
												gestionnaireEvenements.handle(Future.failedFuture(c.cause()));
											}
										})
									);
								}

								CompositeFuture.all(futures2).setHandler(f -> {
									if(f.succeeded()) {
										RequeteSiteFrFR requeteSite2 = inscriptionService.genererRequeteSiteFrFRPourInscriptionScolaire(siteContexteFrFR, requeteSite.getOperationRequete(), new JsonObject());
										RequeteApi apiRequest2 = new RequeteApi();
										apiRequest2.setRows(1);
										apiRequest2.setNumFound(1l);
										apiRequest2.setNumPATCH(0L);
										apiRequest2.initLoinRequeteApi(requeteSite2);
										requeteSite2.setRequeteApi_(apiRequest2);
										requeteSite2.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest2).toString());
			
										o.setRequeteSite_(requeteSite2);

										inscriptionService.patchInscriptionScolaireFuture(o, false, g -> {
											if(g.succeeded()) {
												LOGGER.info("Refreshing enrollment succeeded. ");
											} else {
												LOGGER.error("Refreshing enrollment succeeded. ", g.cause());
												erreurAppliVertx(requeteSite, g);
											}
										});
									} else {
										LOGGER.error("Refresh relations failed. ", f.cause());
										erreurAppliVertx(requeteSite, f);
									}
								});
							} else {
								LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", b.cause()));
								erreurAppliVertx(requeteSite, b);
							}
						});
					} else {
						erreurAppliVertx(requeteSite, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listeInscriptionScolaire.next()) {
//					futureAuthorizeNetFraisInscription(listeInscriptionScolaire, paiementService, inscriptionService, gestionnaireEvenements);
					LOGGER.info("Créer une liste de frais a réussi. ");
				} else {
					gestionnaireEvenements.handle(Future.succeededFuture());
				}
			} else {
				erreurAppliVertx(listeInscriptionScolaire.getRequeteSite_(), a);
			}
		});
	}

	/**
	 * Param1.var.enUS: schoolNumber
	 * Var.enUS: configureAuthorizeNetPayments
	 * 
	 * enUS: Configure payments with Authorize.net. 
	 * 
	 * r: "Commencer à peupler les transactions nouveaux. "
	 * r.enUS: "Start to populate the new transactions. "
	 * r: "Init SQL a réussi. "
	 * r.enUS: "Init SQL succeeded. "
	 * r: "Il y a %s batch à charger. "
	 * r.enUS: "There are %s batches to load. "
	 * r: "batch %s chargé. "
	 * r.enUS: "batch %s loaded. "
	 * r: "batch %s a échoué. "
	 * r.enUS: "batch %s failed. "
	 * r: "Il y a %s inscriptions à recharger. "
	 * r.enUS: "There are %s enrollments to reload. "
	 * r: "Il y a %s paiements à recharger. "
	 * r.enUS: "There are %s payments to reload. "
	 * r: "inscription %s rechargé. "
	 * r.enUS: "enrollment %s refreshed. "
	 * r: "inscription %s a échoué. "
	 * r.enUS: "enrollment %s failed. "
	 * r: "paiement %s rechargé. "
	 * r.enUS: "payment %s refreshed. "
	 * r: "paiement %s a échoué. "
	 * r.enUS: "payement %s failed. "
	 * r: "Recharger les inscriptions a réussi. "
	 * r.enUS: "Refreshing the enrollments has succeeded. "
	 * r: "Commit la connexion SQL a réussi. "
	 * r.enUS: "Commit the SQL connection succeeded. "
	 * r: "Fermer la connexion SQL a réussi. "
	 * r.enUS: "Close the SQL connection has succeeded. "
	 * r: "Finir à peupler les transactions nouveaux. "
	 * r.enUS: "Finish populating the new transactions. "
	 * r: "Fermer la connexion SQL a échoué. "
	 * r.enUS: "Close the SQL connection has failed. "
	 * r: "Commit la connexion SQL a échoué. "
	 * r.enUS: "Commit the SQL connection has failed. "
	 * r: "Recharger les inscriptions a échoué. "
	 * r.enUS: "Refresh the enrollments failed. "
	 * r: "Authorize.net paiements a échoué. \n%s"
	 * r.enUS: "Authorize.net payments have failed. \n%s"
	 * r: "Init SQL a échoué. "
	 * r.enUS: "Init SQL failed. "
	 * r: "Authorize.net WorkerExecutor.executeBlocking a réussi. "
	 * r.enUS: "Authorize.net WorkerExecutor.executeBlocking succeeded. "
	 * r: "Authorize.net WorkerExecutor.executeBlocking a échoué. "
	 * r.enUS: "Authorize.net WorkerExecutor.executeBlocking failed. "
	 * r: PaiementScolaireFrFRApiServiceImpl
	 * r.enUS: SchoolPaymentEnUSApiServiceImpl
	 * r: InscriptionScolaireFrFRApiServiceImpl
	 * r.enUS: SchoolEnrollmentEnUSApiServiceImpl
	 * r: inscriptionScolaire
	 * r.enUS: schoolEnrollment
	 * r: InscriptionCles
	 * r.enUS: EnrollmentKeys
	 * r: InscriptionScolaire
	 * r.enUS: SchoolEnrollment
	 * r: listeRechercheInscription
	 * r.enUS: searchListEnrollment
	 * r: enfantPrenomPrefere
	 * r.enUS: childFirstNamePreferred
	 * r: merePrenomPrefere
	 * r.enUS: momFirstNamePreferred
	 * r: perePrenomPrefere
	 * r.enUS: dadFirstNamePreferred
	 * r: enfantPrenom
	 * r.enUS: childFirstName
	 * r: enfantFamilleNom
	 * r.enUS: childFamilyName
	 * r: setSiteContexte
	 * r.enUS: setSiteContext
	 * r: ExecuteurTravailleur
	 * r.enUS: WorkerExecutor
	 * r: initLoinRequeteSiteFrFR
	 * r.enUS: initDeepSiteRequestEnUS
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: obtenirConfigSite
	 * r.enUS: obtainSiteConfig
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: RequeteSiteFrFR
	 * r.enUS: SiteRequestEnUS
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: RequeteSite
	 * r.enUS: SiteRequest
	 * r: initLoinListeRecherche
	 * r.enUS: initDeepSearchList
	 * r: ListeRecherche
	 * r.enUS: SearchList
	 * r: initLoinPaiementScolaire
	 * r.enUS: initDeepSchoolPayment
	 * r: indexerPaiementScolaire
	 * r.enUS: indexSchoolPayment
	 * r: PaiementScolaire
	 * r.enUS: SchoolPayment
	 * r: setPaiementPar
	 * r.enUS: setPaymentBy
	 * r: paiement
	 * r.enUS: payment
	 * r: PaiementMontant
	 * r.enUS: PaymentAmount
	 * r: PaiementDate
	 * r.enUS: PaymentDate
	 * r: PaiementSysteme
	 * r.enUS: PaymentSystem
	 * r: setStocker
	 * r.enUS: setStore
	 * r: ConnexionSql
	 * r.enUS: SqlConnection
	 * r: erreurAppliVertx
	 * r.enUS: errorAppVertx
	 * r: supprime
	 * r.enUS: deleted
	 * r: archive
	 * r.enUS: archived
	 * r: inscriptionCle
	 * r.enUS: enrollmentKey
	 * r: paiementCle
	 * r.enUS: paymentKey
	 * r: paiementService
	 * r.enUS: paymentService
	 * r: inscriptionService
	 * r.enUS: enrollmentService
	 * r: operationRequete
	 * r.enUS: operationRequest
	 * r: ObjetJson
	 * r.enUS: JsonObject
	 * r: debut
	 * r.enUS: start
	 * r: cree
	 * r.enUS: created
	 * r: Paiement
	 * r.enUS: Payment
	 * r: ecoleNumero
	 * r.enUS: schoolNumber
	 */  
	private Promise<Void> configurerAuthorizeNetPaiements(Integer ecoleNumero) {
		ConfigSite configSite = siteContexteFrFR.getConfigSite();
		ZoneId zoneId = ZoneId.of(configSite.getSiteZone());
		Promise<Void> promise = Promise.promise();

		vertx.setPeriodic(1000 * 60, a -> {
			WorkerExecutor executeurTravailleur = siteContexteFrFR.getExecuteurTravailleur();
			executeurTravailleur.executeBlocking(
				blockingCodeHandler -> {
					LOGGER.info("Commencer à peupler les transactions nouveaux. ");
					ZonedDateTime debut = ZonedDateTime.now(zoneId);
					RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
					requeteSite.setVertx(vertx);
					requeteSite.setSiteContexte_(siteContexteFrFR);
					requeteSite.setConfigSite_(siteContexteFrFR.getConfigSite());
					requeteSite.initLoinRequeteSiteFrFR(requeteSite);
					requeteSite.setObjetJson(new JsonObject());
		
					try {
						PaiementScolaireFrFRApiServiceImpl paiementService = new PaiementScolaireFrFRApiServiceImpl(siteContexteFrFR);
						InscriptionScolaireFrFRApiServiceImpl inscriptionService = new InscriptionScolaireFrFRApiServiceImpl(siteContexteFrFR);
					
						ApiOperationBase.setEnvironment(Environment.valueOf(configSite.getAuthorizeEnvironment()));
		
						MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
						String authorizeApiLoginId = (String)configSite.obtenirConfigSite("authorizeApiLoginId" + ecoleNumero);
						String authorizeTransactionKey = (String)configSite.obtenirConfigSite("authorizeTransactionKey" + ecoleNumero);
						merchantAuthenticationType.setName(authorizeApiLoginId);
						if(authorizeApiLoginId == null || authorizeTransactionKey == null) {
							blockingCodeHandler.handle(Future.succeededFuture());
						}
						else {
							merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
							ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);
							DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
			
							GetSettledBatchListRequest batchRequest = new GetSettledBatchListRequest();
							batchRequest.setMerchantAuthentication(merchantAuthenticationType);
							batchRequest.setFirstSettlementDate(datatypeFactory.newXMLGregorianCalendar(GregorianCalendar.from(LocalDate.now(zoneId)
									.minusDays(7).atStartOfDay(ZoneId.of(configSite.getSiteZone())))));
							batchRequest.setLastSettlementDate(datatypeFactory.newXMLGregorianCalendar(GregorianCalendar.from(LocalDate.now(zoneId)
									.plusDays(1).atStartOfDay(ZoneId.of(configSite.getSiteZone())))));
			
							GetSettledBatchListController batchController = new GetSettledBatchListController(batchRequest);
							GetSettledBatchListController.setEnvironment(Environment.valueOf(configSite.getAuthorizeEnvironment()));
							batchController.execute();
							if(batchController.getErrorResponse() != null)
								throw new RuntimeException(batchController.getResults().toString());
			
							GetSettledBatchListResponse batchResponse = batchController.getApiResponse();
			
							List<Future> futuresBatch = new ArrayList<>();
							List<BatchDetailsType> batches = Optional.ofNullable(batchResponse.getBatchList()).map(ArrayOfBatchDetailsType::getBatch).orElse(Arrays.asList());
							LOGGER.info(String.format("Il y a %s batch à charger. ", batches.size()));
							for(BatchDetailsType batch : batches) {
								futuresBatch.add(
									futureAuthorizeNetBatch(merchantAuthenticationType, batchController, batch, paiementService, inscriptionService, requeteSite, c -> {
										if(c.succeeded()) {
											LOGGER.info(String.format("batch %s chargé. ", batch.getBatchId()));
										} else {
											LOGGER.error(String.format("batch %s a échoué. ", batch.getBatchId()), c.cause());
											blockingCodeHandler.handle(Future.failedFuture(c.cause()));
										}
									})
								);
							}
							CompositeFuture.all(futuresBatch).setHandler( c -> {
								if(c.succeeded()) {
									try {
										ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
										listeRecherche.setStocker(true);
										listeRecherche.setQuery("*:*");
										listeRecherche.setC(PaiementScolaire.class);
										listeRecherche.addFilterQuery("cree_indexed_date:[" + dateFormat.format(ZonedDateTime.ofInstant(debut.toInstant(), ZoneId.of("UTC"))) + " TO *]");
										listeRecherche.add("json.facet", "{inscriptionCles:{terms:{field:inscriptionCle_indexed_long, limit:1000}}}");
										listeRecherche.setRows(1000);
										listeRecherche.initLoinListeRecherche(requeteSite);
										SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeRecherche.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
										List<SimpleOrderedMap> inscriptionCles = (List<SimpleOrderedMap>)Optional.ofNullable((SimpleOrderedMap)facets.get("inscriptionCles")).map(m -> ((List<List<SimpleOrderedMap>>)m.getAll("bucket"))).orElse(Arrays.asList()).stream().findFirst().orElse(new ArrayList<SimpleOrderedMap>());
	//											SimpleOrderedMap inscriptionClesMap = (SimpleOrderedMap)Optional.ofNullable(facets.get("inscriptionCles")).orElse(new SimpleOrderedMap());
	//											List<?> inscriptionClesList = (List<SimpleOrderedMap>)Optional.ofNullable(inscriptionClesMap.getAll("buckets")).orElse(Arrays.asList());
	//											List<SimpleOrderedMap> inscriptionCles = (List<SimpleOrderedMap>)inscriptionClesList.get(0);
		
										List<Future> futures = new ArrayList<>();
										LOGGER.info(String.format("Il y a %s inscriptions à recharger. ", inscriptionCles.size()));
										for(SimpleOrderedMap inscriptionCleMap : inscriptionCles) {
											Long inscriptionCle  = Long.parseLong(inscriptionCleMap.get("val").toString());
											InscriptionScolaire inscriptionScolaire = new InscriptionScolaire();
											inscriptionScolaire.setPk(inscriptionCle);
											inscriptionScolaire.setRequeteSite_(requeteSite);
											futures.add(
												inscriptionService.patchInscriptionScolaireFuture(inscriptionScolaire, false, d -> {
													if(d.succeeded()) {
														LOGGER.info(String.format("inscription %s rechargé. ", inscriptionCle));
													} else {
														LOGGER.error(String.format("inscription %s a échoué. ", inscriptionCle), d.cause());
														blockingCodeHandler.handle(Future.failedFuture(d.cause()));
													}
												})
											);
										}
										CompositeFuture.all(futures).setHandler(d -> {
											if(d.succeeded()) {
												List<Future> futuresPaiement = new ArrayList<>();
												LOGGER.info(String.format("Il y a %s paiements à recharger. ", inscriptionCles.size()));
												for(PaiementScolaire paiement : listeRecherche.getList()) {
													futuresPaiement.add(
														paiementService.patchPaiementScolaireFuture(paiement, false, e -> {
															if(e.succeeded()) {
																LOGGER.info(String.format("paiement %s rechargé. ", paiement.getPk()));
															} else {
																LOGGER.error(String.format("paiement %s a échoué. ", paiement.getPk()), e.cause());
																blockingCodeHandler.handle(Future.failedFuture(e.cause()));
															}
														})
													);
												}
												CompositeFuture.all(futuresPaiement).setHandler(e -> {
													if(e.succeeded()) {
														LOGGER.info("Recharger les inscriptions a réussi. ");
														LOGGER.info("Finir à peupler les transactions nouveaux. ");
														blockingCodeHandler.handle(Future.succeededFuture(e.result()));
													} else {
														LOGGER.error("Commit la connexion SQL a échoué. ", e.cause());
														erreurAppliVertx(requeteSite, e);
													}
												});
											} else {
												LOGGER.error("Recharger les inscriptions a échoué. ", d.cause());
												erreurAppliVertx(requeteSite, d);
											}
										});
									} catch (Exception e) {
										LOGGER.error(String.format("Authorize.net paiements a échoué. \n%s", ExceptionUtils.getStackTrace(e)), ExceptionUtils.getStackTrace(e));
										erreurAppliVertx(requeteSite, c);
									}
								} else {
									LOGGER.error(c.cause());
									erreurAppliVertx(requeteSite, c);
								}
							});
						}
					} catch (Exception e) {
						LOGGER.error(String.format("Authorize.net paiements a échoué. \n%s", ExceptionUtils.getStackTrace(e)), ExceptionUtils.getStackTrace(e));
						erreurAppliVertx(requeteSite, null);
					}
				}, resultHandler -> {
					if(resultHandler.succeeded()) {
						LOGGER.info("Authorize.net WorkerExecutor.executeBlocking a réussi. ");
					} else {
						LOGGER.error("Authorize.net WorkerExecutor.executeBlocking a échoué. ", resultHandler.cause());
					}
				}
			);
		});

		promise.complete();
		return promise;
	}

	/**
	 * Param4.var.enUS: paymentService
	 * Param5.var.enUS: enrollmentService
	 * Param6.var.enUS: siteRequest
	 * 
	 * r: "Il y a %s transactions dans batch %s à charger. "
	 * r.enUS: "There are %s transactions in batch %s to load. "
	 * r: "transaction %s chargé. "
	 * r.enUS: "transaction %s loaded. "
	 * r: "paiement future pour transaction %s a échoué. "
	 * r.enUS: "payment future for transaction %s failed. "
	 * r: "transactions pour batch %s chargé. "
	 * r.enUS: "transactions for batch %s loaded. "
	 * r: "transactions pour batch %s a échoué. "
	 * r.enUS: "transactions for batch %s failed. "
	 * r: PaiementScolaireFrFRGenApiServiceImpl
	 * r.enUS: SchoolPaymentEnUSGenApiServiceImpl
	 * r: InscriptionScolaireFrFRGenApiServiceImpl
	 * r.enUS: SchoolEnrollmentEnUSGenApiServiceImpl
	 * r: futureAuthorizeNetPaiement
	 * r.enUS: futureAuthorizeNetPayment
	 * r: paiementService
	 * r.enUS: paymentService
	 * r: inscriptionService
	 * r.enUS: enrollmentService
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 */
	public Future<Void> futureAuthorizeNetBatch(
			MerchantAuthenticationType merchantAuthenticationType
			, GetSettledBatchListController batchController
			, BatchDetailsType batch
			, PaiementScolaireFrFRApiServiceImpl paiementService
			, InscriptionScolaireFrFRApiServiceImpl inscriptionService
			, RequeteSiteFrFR requeteSite
			,  Handler<AsyncResult<Void>> a) {
		Promise<Void> promise = Promise.promise();
		try {
			ConfigSite configSite = requeteSite.getConfigSite_();
			Paging paging = new Paging();
			paging.setLimit(100);
			paging.setOffset(1);
			
			GetTransactionListRequest getRequest = new GetTransactionListRequest();
			getRequest.setMerchantAuthentication(merchantAuthenticationType);
			getRequest.setBatchId(batch.getBatchId());

			getRequest.setPaging(paging);

			TransactionListSorting sorting = new TransactionListSorting();
			sorting.setOrderBy(TransactionListOrderFieldEnum.SUBMIT_TIME_UTC);
			sorting.setOrderDescending(true);

			getRequest.setSorting(sorting);

			GetTransactionListController controller = new GetTransactionListController(getRequest);
			GetTransactionListController.setEnvironment(Environment.valueOf(configSite.getAuthorizeEnvironment()));
			controller.execute();
			if(controller.getErrorResponse() != null)
				throw new RuntimeException(batchController.getResults().toString());

			List<Future> futures = new ArrayList<>();

			GetTransactionListResponse getResponse = controller.getApiResponse();
			if (getResponse != null) {

				if (getResponse.getMessages().getResultCode() == MessageTypeEnum.OK) {
					List<TransactionSummaryType> transactions = Optional.ofNullable(getResponse).map(GetTransactionListResponse::getTransactions).map(ArrayOfTransactionSummaryType::getTransaction).orElse(Arrays.asList());
					LOGGER.info(String.format("Il y a %s transactions dans batch %s à charger. ", transactions.size(), batch.getBatchId()));
					for(TransactionSummaryType transaction : transactions) {
						futures.add(
							inscriptionService.futureAuthorizeNetPaiement(merchantAuthenticationType, paiementService, requeteSite, transaction, null, b -> {
								if(b.succeeded()) {
									LOGGER.info(String.format("transaction %s chargé. ", transaction.getTransId()));
								} else {
									LOGGER.error(String.format("paiement future pour transaction %s a échoué. ", transaction.getTransId()), b.cause());
								}
							})
						);
					}
					CompositeFuture.all(futures).setHandler(b -> {
						if(b.succeeded()) {
							a.handle(Future.succeededFuture());
							promise.complete();
							LOGGER.info(String.format("transactions pour batch %s chargé. ", batch.getBatchId()));
						} else {
							LOGGER.error(String.format("transactions pour batch %s a échoué. ", batch.getBatchId()));
							promise.fail(b.cause());
						}
					});
				}
			}
			return promise.future();
		} catch(Exception e) {
			a.handle(Future.failedFuture(e));
			return Future.failedFuture(e);
		}
	}

	/**
	 * Var.enUS: errorAppVertx
	 * Param1.var.enUS: siteRequest
	 * 
	 * r: "Rollback la connexion SQL a réussi. "
	 * r.enUS: "Rollback the SQL connection succeded. "
	 * r: "Fermer la connexion SQL a réussi. "
	 * r.enUS: "Close the SQL connection succeded. "
	 * r: "Rollback la connexion SQL a échoué. "
	 * r.enUS: "Rollback the SQL connection failed. "
	 * r: "Fermer la connexion SQL a échoué. "
	 * r.enUS: "Close the SQL connection failed. "
	 * r: ConnexionSql
	 * r.enUS: SqlConnection
	 * r: requeteSite
	 * r.enUS: siteRequest
	 */
	public void erreurAppliVertx(RequeteSiteFrFR requeteSite, AsyncResult<?> a) {
		Throwable e = a.cause();
		if(e != null)
			LOGGER.error(ExceptionUtils.getStackTrace(e));
		if(requeteSite != null) {
			Transaction tx = requeteSite.getTx();
			if(tx != null) {
				tx.rollback(b -> {
					if(b.succeeded()) {
						LOGGER.info("Rollback la connexion SQL a réussi. ");
						try {
							SqlConnection connexionSql = requeteSite.getConnexionSql();
				
							if(connexionSql == null) {
								LOGGER.info("Fermer la connexion SQL a réussi. ");
							} else {
								connexionSql.close();
								requeteSite.setConnexionSql(null);
								LOGGER.info("Fermer la connexion SQL a réussi. ");
							}
						} catch(Exception ex) {
							LOGGER.error(String.format("sqlFermerEcole a échoué. ", ex));
						}
					} else {
						LOGGER.error("Rollback la connexion SQL a échoué. ", b.cause());
					}
				});
			}
		}
	}

	/**
	 * Var.enUS: startServer
	 * 
	 * Val.ErrorServer.enUS:The server is not configured properly. 
	 * Val.ErreurServeur.frFR:Le serveur n'est pas configurée correctement. 
	 * Val.SuccessServer.enUS:The HTTP server is running: %s:%s
	 * Val.SuccesServeur.frFR:Le serveur HTTP est démarré : %s:%s
	 * Val.BeforeServer.enUS:HTTP server starting: %s://%s:%s
	 * Val.AvantServeur.frFR:Le serveur HTTP est démarré : %s:%s
	 * Val.Ssl.enUS:Configuring SSL: %s
	 * Val.Ssl.frFR:Configurer SSL : %s
	 * 
	 * enUS: Start the Vert.x server. 
	 * enUS: Démarrer le serveur Vert.x. 
	 * 
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 * r: configSite
	 * r.enUS: siteConfig
	 * r: UsineRouteur
	 * r.enUS: RouterFactory
	 * r: siteRouteur
	 * r.enUS: siteRouter
	 * r: gestionnaireStatic
	 * r.enUS: staticHandler
	 * r: siteNomHote
	 * r.enUS: siteHostName
	 * r: demarrerServeurErreurServeur
	 * r.enUS: startServerErrorServer
	 * r: demarrerServeurSuccesServeur
	 * r.enUS: startServerSuccessServer
	 * r: demarrerServeurAvantServeur
	 * r.enUS: startServerBeforeServer
	 * r: demarrerServeurSsl
	 * r.enUS: startServerSsl
	 * r: getSiteNomHote
	 * r.enUS: getSiteHostName
	 * r: getSslJksChemin
	 * r.enUS: getSslJksPath
	 * r: getSslJksMotDePasse
	 * r.enUS: getSslJksPassword
	 * r: ClusterFrFRGenApiService
	 * r.enUS: ClusterEnUSGenApiService
	 * r: EcoleFrFRGenApiService
	 * r.enUS: SchoolEnUSGenApiService
	 * r: UtilisateurSiteFrFRGenApiService
	 * r.enUS: SiteUserEnUSGenApiService
	 * r: AnneeScolaireFrFRGenApiService
	 * r.enUS: SchoolYearEnUSGenApiService
	 * r: SaisonScolaireFrFRGenApiService
	 * r.enUS: SchoolSeasonEnUSGenApiService
	 * r: SessionScolaireFrFRGenApiService
	 * r.enUS: SchoolSessionEnUSGenApiService
	 * r: AgeScolaireFrFRGenApiService
	 * r.enUS: SchoolAgeEnUSGenApiService
	 * r: BlocScolaireFrFRGenApiService
	 * r.enUS: SchoolBlockEnUSGenApiService
	 * r: InscriptionScolaireFrFRGenApiService
	 * r.enUS: SchoolEnrollmentEnUSGenApiService
	 * r: EnfantScolaireFrFRGenApiService
	 * r.enUS: SchoolChildEnUSGenApiService
	 * r: MereScolaireFrFRGenApiService
	 * r.enUS: SchoolMomEnUSGenApiService
	 * r: PereScolaireFrFRGenApiService
	 * r.enUS: SchoolDadEnUSGenApiService
	 * r: ContactScolaireFrFRGenApiService
	 * r.enUS: SchoolContactEnUSGenApiService
	 * r: GardienScolaireFrFRGenApiService
	 * r.enUS: SchoolGuardianEnUSGenApiService
	 * r: PaiementScolaireFrFRGenApiService
	 * r.enUS: SchoolPaymentEnUSGenApiService
	 * r: DesignInscriptionFrFRGenApiService
	 * r.enUS: EnrollmentDesignEnUSGenApiService
	 * r: DesignPageFrFRGenApiService
	 * r.enUS: PageDesignEnUSGenApiService
	 * r: PartHtmlFrFRGenApiService
	 * r.enUS: HtmlPartEnUSGenApiService
	 * r: enregistrerService
	 * r.enUS: registerService
	 * r: getRouteur
	 * r.enUS: getRouter
	 */   
	private Promise<Void> demarrerServeur() {
		ConfigSite configSite = siteContexteFrFR.getConfigSite();
		Promise<Void> promise = Promise.promise();

		ClusterFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
		EcoleFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
		UtilisateurSiteFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
		AnneeScolaireFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
		SaisonScolaireFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
		SessionScolaireFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
		AgeScolaireFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
		BlocScolaireFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
		InscriptionScolaireFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
		EnfantScolaireFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
		MereScolaireFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
		PereScolaireFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
		GardienScolaireFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
		PaiementScolaireFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
		DesignPageFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
		PartHtmlFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);

		Router siteRouteur = siteContexteFrFR.getRouteur();

		StaticHandler gestionnaireStatic = StaticHandler.create().setCachingEnabled(false).setFilesReadOnly(true);
		if(configSite.getSiteNomHote().contains("scolaire-dev.")) {
			gestionnaireStatic.setAllowRootFileSystemAccess(true);
			gestionnaireStatic.setWebRoot("/usr/local/src/computate-scolaire-static");
		}
		siteRouteur.route("/static/*").handler(gestionnaireStatic);

		SimpleModule module = new SimpleModule();
		module.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
		module.addSerializer(LocalDate.class, new LocalDateSerializer());
		module.addSerializer(LocalTime.class, new LocalTimeSerializer());
		Json.mapper.registerModule(module);

		String siteNomHote = configSite.getSiteNomHote();
		Integer sitePort = configSite.getSitePort();
		HttpServerOptions options = new HttpServerOptions();
		if(configSite.getSslJksChemin() != null && new File(configSite.getSslJksChemin()).exists()) {
			options.setKeyStoreOptions(new JksOptions().setPath(configSite.getSslJksChemin()).setPassword(configSite.getSslJksMotDePasse()));
			options.setSsl(true);
			LOGGER.info(String.format(demarrerServeurSsl, configSite.getSslJksChemin()));
		}
		options.setPort(sitePort);

		LOGGER.info(String.format(demarrerServeurAvantServeur, "https", siteNomHote, sitePort));
		vertx.createHttpServer(options).requestHandler(siteRouteur).listen(ar -> {
			if (ar.succeeded()) {
				LOGGER.info(String.format(demarrerServeurSuccesServeur, "*", sitePort));
				promise.complete();
			} else {
				LOGGER.error(demarrerServeurErreurServeur, ar.cause());
				promise.fail(ar.cause());
			}
		});

		return promise;
	}

	/**
	 * Param1.var.enUS: stopPromise
	 * r: SiteContexteFrFR
	 * r.enUS: SiteContextEnUS
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: arreterPromise
	 * r.enUS: stopPromise
	 * r: fermerDonnees
	 * r.enUS: closeData
	 * r: etapesPromises
	 * r.enUS: promiseSteps
	 * 
	 * enUS: This is called by Vert.x when the verticle instance is undeployed. 
	 * enUS: Setup the stopPromise to handle tearing down the server. 
	 */
	@Override
	public void stop(Promise<Void> arreterPromise) throws Exception {
		Promise<Void> etapesPromises = fermerDonnees();
		etapesPromises.future().setHandler(arreterPromise);
	}

	/**
	 * Var.enUS: closeData
	 * Val.Error.enUS:Could not close the database client connection. 
	 * Val.Erreur.frFR:Impossible de fermer la connexion du client de base de données. 
	 * Val.Success.enUS:The database client connextion was closed. 
	 * Val.Succes.frFR:La connexion client de la base de données a été fermée.
	 * r: fermerDonneesErreur
	 * r.enUS: closeDataError
	 * r: fermerDonneesSucces
	 * r.enUS: closeDataSuccess
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * 
	 * enUS: Return a promise to close the database client connection. 
	 */        
	private Promise<Void> fermerDonnees() {
		Promise<Void> promise = Promise.promise();
		PgPool pgPool = siteContexteFrFR.getPgPool();

		if(pgPool != null) {
			pgPool.close();
			LOGGER.info(fermerDonneesSucces);
			promise.complete();
		}
		return promise;
	}
}
