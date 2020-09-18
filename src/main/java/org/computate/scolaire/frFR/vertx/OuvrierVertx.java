package org.computate.scolaire.frFR.vertx;       

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.inscription.InscriptionScolaire;
import org.computate.scolaire.frFR.inscription.InscriptionScolaireFrFRApiServiceImpl;
import org.computate.scolaire.frFR.paiement.PaiementScolaire;
import org.computate.scolaire.frFR.paiement.PaiementScolaireFrFRApiServiceImpl;
import org.computate.scolaire.frFR.recherche.ListeRecherche;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
import org.computate.scolaire.frFR.requete.api.RequeteApi;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailConfig;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Transaction;

/**
 * NomCanonique.enUS: org.computate.scolaire.enUS.vertx.WorkerVertx
 * enUS: A Java class to start the Vert.x application as a main method. 
 */      
public class OuvrierVertx extends OuvrierVertxGen<AbstractVerticle> {

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
	public static final String SQL_uniqueIndexC = "create index if not exists c_index_utilisateur on c(nom_canonique, id_utilisateur); ";

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
	public static final String SQL_uniqueIndexA1 = "create index if not exists a_index_1 on a(pk1); ";

	/**
	 * enUS: A SQL query for creating an index on the "a" table based on fields for faster lookup. 
	 * r: entite
	 * r.enUS: entity
	 * r: actuel
	 * r.enUS: current
	 * r: cree
	 * r.enUS: created
	 */
	public static final String SQL_uniqueIndexA2 = "create index if not exists a_index_2 on a(pk2); ";

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
	public static final String SQL_uniqueIndexD = "create index if not exists d_index on d(pk_c); ";

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
		run();
	}

	/**
	 * r: zookeeperNomHote
	 * r.enUS: zookeeperHostName
	 * r: OuvrierVertx
	 * r.enUS: WorkerVertx
	 */
	public static void run() {
		Class<?> c = OuvrierVertx.class;
		VertxOptions optionsVertx = new VertxOptions();
		EventBusOptions eventBusOptions = new EventBusOptions();
		optionsVertx.setEventBusOptions(eventBusOptions);
		DeploymentOptions deploymentOptions = new DeploymentOptions();
		deploymentOptions.setInstances(1);

		String verticleID = c.getName();

		Consumer<Vertx> runner = vertx -> {
			vertx.deployVerticle(verticleID, deploymentOptions);
		};
		Vertx vertx = Vertx.vertx(optionsVertx);
		EventBus eventBus = vertx.eventBus();
		LOGGER.info("We now have a worker event bus: {}", eventBus);
		runner.accept(vertx);
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
				configurerExecuteurTravailleurPartage().future().compose(c -> 
					configurerMail().future().compose(d -> 
						configurerAuthorizeNetFrais().future()
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
								pgPool.preparedQuery(SQL_uniqueIndexA1, d -> {
									if (d.succeeded()) {
										pgPool.preparedQuery(SQL_uniqueIndexA2, e -> {
											if (d.succeeded()) {
												pgPool.preparedQuery(SQL_createTableD, f -> {
													if (f.succeeded()) {
														pgPool.preparedQuery(SQL_uniqueIndexD, g -> {
															if (g.succeeded()) {
																LOGGER.info(configurerDonneesSuccesInit);
																promise.complete();
															} else {
																LOGGER.error(configurerDonneesErreurInit, g.cause());
																promise.fail(g.cause());
															}
														});
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
		donneesPartagees.getLocalAsyncMap("donneesCluster", res -> {
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
	 * r: fraisCrees
	 * r.enUS: chargesCreated
	 */ 
	private Promise<Void> configurerAuthorizeNetFrais() {
		ConfigSite configSite = siteContexteFrFR.getConfigSite();
		ZoneId zoneId = ZoneId.of(configSite.getSiteZone());
		Promise<Void> promise = Promise.promise();
		if(configSite.getAuthorizeEnvironment() != null) {

			vertx.setPeriodic(1000 * 5, a -> {
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
	//						listeRechercheInscription.addFilterQuery("(*:* AND -inscriptionDateFrais_indexed_date:[* TO *] OR inscriptionDateFrais_indexed_date:[* TO " + dateFormat.format(inscriptionDateFrais) + "])");
							listeRechercheInscription.setRows(1);
							listeRechercheInscription.addSort("fraisCrees_indexed_boolean", ORDER.asc);
							listeRechercheInscription.addSort("modifie_indexed_date", ORDER.asc);
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
		}

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
//						inscriptionService.authorizeNetEnrollmentPaiementsFuture(o, b -> {
//							if(b.succeeded()) {
								LOGGER.info("Creating charges for customer %s succeeded. ");
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
//							} else {
//								LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", b.cause()));
//								erreurAppliVertx(requeteSite, b);
//							}
//						});
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
//
//	/**
//	 * Param1.var.enUS: schoolNumber
//	 * Var.enUS: configureAuthorizeNetPayments
//	 * 
//	 * enUS: Configure payments with Authorize.net. 
//	 * 
//	 * r: "Commencer à peupler les transactions nouveaux. "
//	 * r.enUS: "Start to populate the new transactions. "
//	 * r: "Init SQL a réussi. "
//	 * r.enUS: "Init SQL succeeded. "
//	 * r: "Il y a %s batch à charger. "
//	 * r.enUS: "There are %s batches to load. "
//	 * r: "batch %s chargé. "
//	 * r.enUS: "batch %s loaded. "
//	 * r: "batch %s a échoué. "
//	 * r.enUS: "batch %s failed. "
//	 * r: "Il y a %s inscriptions à recharger. "
//	 * r.enUS: "There are %s enrollments to reload. "
//	 * r: "Il y a %s paiements à recharger. "
//	 * r.enUS: "There are %s payments to reload. "
//	 * r: "inscription %s rechargé. "
//	 * r.enUS: "enrollment %s refreshed. "
//	 * r: "inscription %s a échoué. "
//	 * r.enUS: "enrollment %s failed. "
//	 * r: "paiement %s rechargé. "
//	 * r.enUS: "payment %s refreshed. "
//	 * r: "paiement %s a échoué. "
//	 * r.enUS: "payement %s failed. "
//	 * r: "Recharger les inscriptions a réussi. "
//	 * r.enUS: "Refreshing the enrollments has succeeded. "
//	 * r: "Commit la connexion SQL a réussi. "
//	 * r.enUS: "Commit the SQL connection succeeded. "
//	 * r: "Fermer la connexion SQL a réussi. "
//	 * r.enUS: "Close the SQL connection has succeeded. "
//	 * r: "Finir à peupler les transactions nouveaux. "
//	 * r.enUS: "Finish populating the new transactions. "
//	 * r: "Fermer la connexion SQL a échoué. "
//	 * r.enUS: "Close the SQL connection has failed. "
//	 * r: "Commit la connexion SQL a échoué. "
//	 * r.enUS: "Commit the SQL connection has failed. "
//	 * r: "Recharger les inscriptions a échoué. "
//	 * r.enUS: "Refresh the enrollments failed. "
//	 * r: "Authorize.net paiements a échoué. \n%s"
//	 * r.enUS: "Authorize.net payments have failed. \n%s"
//	 * r: "Init SQL a échoué. "
//	 * r.enUS: "Init SQL failed. "
//	 * r: "Authorize.net WorkerExecutor.executeBlocking a réussi. "
//	 * r.enUS: "Authorize.net WorkerExecutor.executeBlocking succeeded. "
//	 * r: "Authorize.net WorkerExecutor.executeBlocking a échoué. "
//	 * r.enUS: "Authorize.net WorkerExecutor.executeBlocking failed. "
//	 * r: PaiementScolaireFrFRApiServiceImpl
//	 * r.enUS: SchoolPaymentEnUSApiServiceImpl
//	 * r: InscriptionScolaireFrFRApiServiceImpl
//	 * r.enUS: SchoolEnrollmentEnUSApiServiceImpl
//	 * r: inscriptionScolaire
//	 * r.enUS: schoolEnrollment
//	 * r: InscriptionCles
//	 * r.enUS: EnrollmentKeys
//	 * r: InscriptionScolaire
//	 * r.enUS: SchoolEnrollment
//	 * r: listeRechercheInscription
//	 * r.enUS: searchListEnrollment
//	 * r: enfantPrenomPrefere
//	 * r.enUS: childFirstNamePreferred
//	 * r: merePrenomPrefere
//	 * r.enUS: momFirstNamePreferred
//	 * r: perePrenomPrefere
//	 * r.enUS: dadFirstNamePreferred
//	 * r: enfantPrenom
//	 * r.enUS: childFirstName
//	 * r: enfantFamilleNom
//	 * r.enUS: childFamilyName
//	 * r: setSiteContexte
//	 * r.enUS: setSiteContext
//	 * r: ExecuteurTravailleur
//	 * r.enUS: WorkerExecutor
//	 * r: initLoinRequeteSiteFrFR
//	 * r.enUS: initDeepSiteRequestEnUS
//	 * r: siteContexteFrFR
//	 * r.enUS: siteContextEnUS
//	 * r: obtenirConfigSite
//	 * r.enUS: obtainSiteConfig
//	 * r: ConfigSite
//	 * r.enUS: SiteConfig
//	 * r: configSite
//	 * r.enUS: siteConfig
//	 * r: RequeteSiteFrFR
//	 * r.enUS: SiteRequestEnUS
//	 * r: requeteSite
//	 * r.enUS: siteRequest
//	 * r: RequeteSite
//	 * r.enUS: SiteRequest
//	 * r: initLoinListeRecherche
//	 * r.enUS: initDeepSearchList
//	 * r: ListeRecherche
//	 * r.enUS: SearchList
//	 * r: initLoinPaiementScolaire
//	 * r.enUS: initDeepSchoolPayment
//	 * r: indexerPaiementScolaire
//	 * r.enUS: indexSchoolPayment
//	 * r: PaiementScolaire
//	 * r.enUS: SchoolPayment
//	 * r: setPaiementPar
//	 * r.enUS: setPaymentBy
//	 * r: paiement
//	 * r.enUS: payment
//	 * r: PaiementMontant
//	 * r.enUS: PaymentAmount
//	 * r: PaiementDate
//	 * r.enUS: PaymentDate
//	 * r: PaiementSysteme
//	 * r.enUS: PaymentSystem
//	 * r: setStocker
//	 * r.enUS: setStore
//	 * r: ConnexionSql
//	 * r.enUS: SqlConnection
//	 * r: erreurAppliVertx
//	 * r.enUS: errorAppVertx
//	 * r: supprime
//	 * r.enUS: deleted
//	 * r: archive
//	 * r.enUS: archived
//	 * r: inscriptionCle
//	 * r.enUS: enrollmentKey
//	 * r: paiementCle
//	 * r.enUS: paymentKey
//	 * r: paiementService
//	 * r.enUS: paymentService
//	 * r: inscriptionService
//	 * r.enUS: enrollmentService
//	 * r: operationRequete
//	 * r.enUS: operationRequest
//	 * r: ObjetJson
//	 * r.enUS: JsonObject
//	 * r: debut
//	 * r.enUS: start
//	 * r: cree
//	 * r.enUS: created
//	 * r: Paiement
//	 * r.enUS: Payment
//	 * r: ecoleNumero
//	 * r.enUS: schoolNumber
//	 */  
//	private Promise<Void> configurerAuthorizeNetPaiements(Integer ecoleNumero) {
//		ConfigSite configSite = siteContexteFrFR.getConfigSite();
//		ZoneId zoneId = ZoneId.of(configSite.getSiteZone());
//		Promise<Void> promise = Promise.promise();
//
//		vertx.setPeriodic(1000 * 60, a -> {
//			WorkerExecutor executeurTravailleur = siteContexteFrFR.getExecuteurTravailleur();
//			executeurTravailleur.executeBlocking(
//				blockingCodeHandler -> {
//					LOGGER.info("Commencer à peupler les transactions nouveaux. ");
//					ZonedDateTime debut = ZonedDateTime.now(zoneId);
//					RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
//					requeteSite.setVertx(vertx);
//					requeteSite.setSiteContexte_(siteContexteFrFR);
//					requeteSite.setConfigSite_(siteContexteFrFR.getConfigSite());
//					requeteSite.initLoinRequeteSiteFrFR(requeteSite);
//					requeteSite.setObjetJson(new JsonObject());
//		
//					try {
//						PaiementScolaireFrFRApiServiceImpl paiementService = new PaiementScolaireFrFRApiServiceImpl(siteContexteFrFR);
//						InscriptionScolaireFrFRApiServiceImpl inscriptionService = new InscriptionScolaireFrFRApiServiceImpl(siteContexteFrFR);
//					
//						ApiOperationBase.setEnvironment(Environment.valueOf(configSite.getAuthorizeEnvironment()));
//		
//						MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
//						String authorizeApiLoginId = (String)configSite.obtenirConfigSite("authorizeApiLoginId" + ecoleNumero);
//						String authorizeTransactionKey = (String)configSite.obtenirConfigSite("authorizeTransactionKey" + ecoleNumero);
//						merchantAuthenticationType.setName(authorizeApiLoginId);
//						if(authorizeApiLoginId == null || authorizeTransactionKey == null) {
//							blockingCodeHandler.handle(Future.succeededFuture());
//						}
//						else {
//							merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
//							ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);
//							DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
//			
//							GetSettledBatchListRequest batchRequest = new GetSettledBatchListRequest();
//							batchRequest.setMerchantAuthentication(merchantAuthenticationType);
//							batchRequest.setFirstSettlementDate(datatypeFactory.newXMLGregorianCalendar(GregorianCalendar.from(LocalDate.now(zoneId)
//									.minusDays(7).atStartOfDay(ZoneId.of(configSite.getSiteZone())))));
//							batchRequest.setLastSettlementDate(datatypeFactory.newXMLGregorianCalendar(GregorianCalendar.from(LocalDate.now(zoneId)
//									.plusDays(1).atStartOfDay(ZoneId.of(configSite.getSiteZone())))));
//			
//							GetSettledBatchListController batchController = new GetSettledBatchListController(batchRequest);
//							GetSettledBatchListController.setEnvironment(Environment.valueOf(configSite.getAuthorizeEnvironment()));
//							batchController.execute();
//							if(batchController.getErrorResponse() != null)
//								throw new RuntimeException(batchController.getResults().toString());
//			
//							GetSettledBatchListResponse batchResponse = batchController.getApiResponse();
//			
//							List<Future> futuresBatch = new ArrayList<>();
//							List<BatchDetailsType> batches = Optional.ofNullable(batchResponse.getBatchList()).map(ArrayOfBatchDetailsType::getBatch).orElse(Arrays.asList());
//							LOGGER.info(String.format("Il y a %s batch à charger. ", batches.size()));
//							for(BatchDetailsType batch : batches) {
//								futuresBatch.add(
//									futureAuthorizeNetBatch(merchantAuthenticationType, batchController, batch, paiementService, inscriptionService, requeteSite, c -> {
//										if(c.succeeded()) {
//											LOGGER.info(String.format("batch %s chargé. ", batch.getBatchId()));
//										} else {
//											LOGGER.error(String.format("batch %s a échoué. ", batch.getBatchId()), c.cause());
//											blockingCodeHandler.handle(Future.failedFuture(c.cause()));
//										}
//									})
//								);
//							}
//							CompositeFuture.all(futuresBatch).setHandler( c -> {
//								if(c.succeeded()) {
//									try {
//										ListeRecherche<PaiementScolaire> listeRecherche = new ListeRecherche<PaiementScolaire>();
//										listeRecherche.setStocker(true);
//										listeRecherche.setQuery("*:*");
//										listeRecherche.setC(PaiementScolaire.class);
//										listeRecherche.addFilterQuery("cree_indexed_date:[" + dateFormat.format(ZonedDateTime.ofInstant(debut.toInstant(), ZoneId.of("UTC"))) + " TO *]");
//										listeRecherche.add("json.facet", "{inscriptionCles:{terms:{field:inscriptionCle_indexed_long, limit:1000}}}");
//										listeRecherche.setRows(1000);
//										listeRecherche.initLoinListeRecherche(requeteSite);
//										SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeRecherche.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
//										List<SimpleOrderedMap> inscriptionCles = (List<SimpleOrderedMap>)Optional.ofNullable((SimpleOrderedMap)facets.get("inscriptionCles")).map(m -> ((List<List<SimpleOrderedMap>>)m.getAll("bucket"))).orElse(Arrays.asList()).stream().findFirst().orElse(new ArrayList<SimpleOrderedMap>());
//	//											SimpleOrderedMap inscriptionClesMap = (SimpleOrderedMap)Optional.ofNullable(facets.get("inscriptionCles")).orElse(new SimpleOrderedMap());
//	//											List<?> inscriptionClesList = (List<SimpleOrderedMap>)Optional.ofNullable(inscriptionClesMap.getAll("buckets")).orElse(Arrays.asList());
//	//											List<SimpleOrderedMap> inscriptionCles = (List<SimpleOrderedMap>)inscriptionClesList.get(0);
//		
//										List<Future> futures = new ArrayList<>();
//										LOGGER.info(String.format("Il y a %s inscriptions à recharger. ", inscriptionCles.size()));
//										for(SimpleOrderedMap inscriptionCleMap : inscriptionCles) {
//											Long inscriptionCle  = Long.parseLong(inscriptionCleMap.get("val").toString());
//											InscriptionScolaire inscriptionScolaire = new InscriptionScolaire();
//											inscriptionScolaire.setPk(inscriptionCle);
//											inscriptionScolaire.setRequeteSite_(requeteSite);
//											futures.add(
//												inscriptionService.patchInscriptionScolaireFuture(inscriptionScolaire, false, d -> {
//													if(d.succeeded()) {
//														LOGGER.info(String.format("inscription %s rechargé. ", inscriptionCle));
//													} else {
//														LOGGER.error(String.format("inscription %s a échoué. ", inscriptionCle), d.cause());
//														blockingCodeHandler.handle(Future.failedFuture(d.cause()));
//													}
//												})
//											);
//										}
//										CompositeFuture.all(futures).setHandler(d -> {
//											if(d.succeeded()) {
//												List<Future> futuresPaiement = new ArrayList<>();
//												LOGGER.info(String.format("Il y a %s paiements à recharger. ", inscriptionCles.size()));
//												for(PaiementScolaire paiement : listeRecherche.getList()) {
//													futuresPaiement.add(
//														paiementService.patchPaiementScolaireFuture(paiement, false, e -> {
//															if(e.succeeded()) {
//																LOGGER.info(String.format("paiement %s rechargé. ", paiement.getPk()));
//															} else {
//																LOGGER.error(String.format("paiement %s a échoué. ", paiement.getPk()), e.cause());
//																blockingCodeHandler.handle(Future.failedFuture(e.cause()));
//															}
//														})
//													);
//												}
//												CompositeFuture.all(futuresPaiement).setHandler(e -> {
//													if(e.succeeded()) {
//														LOGGER.info("Recharger les inscriptions a réussi. ");
//														LOGGER.info("Finir à peupler les transactions nouveaux. ");
//														blockingCodeHandler.handle(Future.succeededFuture(e.result()));
//													} else {
//														LOGGER.error("Commit la connexion SQL a échoué. ", e.cause());
//														erreurAppliVertx(requeteSite, e);
//													}
//												});
//											} else {
//												LOGGER.error("Recharger les inscriptions a échoué. ", d.cause());
//												erreurAppliVertx(requeteSite, d);
//											}
//										});
//									} catch (Exception e) {
//										LOGGER.error(String.format("Authorize.net paiements a échoué. \n%s", ExceptionUtils.getStackTrace(e)), ExceptionUtils.getStackTrace(e));
//										erreurAppliVertx(requeteSite, c);
//									}
//								} else {
//									LOGGER.error(c.cause());
//									erreurAppliVertx(requeteSite, c);
//								}
//							});
//						}
//					} catch (Exception e) {
//						LOGGER.error(String.format("Authorize.net paiements a échoué. \n%s", ExceptionUtils.getStackTrace(e)), ExceptionUtils.getStackTrace(e));
//						erreurAppliVertx(requeteSite, null);
//					}
//				}, resultHandler -> {
//					if(resultHandler.succeeded()) {
//						LOGGER.info("Authorize.net WorkerExecutor.executeBlocking a réussi. ");
//					} else {
//						LOGGER.error("Authorize.net WorkerExecutor.executeBlocking a échoué. ", resultHandler.cause());
//					}
//				}
//			);
//		});
//
//		promise.complete();
//		return promise;
//	}
//
//	/**
//	 * Param4.var.enUS: paymentService
//	 * Param5.var.enUS: enrollmentService
//	 * Param6.var.enUS: siteRequest
//	 * 
//	 * r: "Il y a %s transactions dans batch %s à charger. "
//	 * r.enUS: "There are %s transactions in batch %s to load. "
//	 * r: "transaction %s chargé. "
//	 * r.enUS: "transaction %s loaded. "
//	 * r: "paiement future pour transaction %s a échoué. "
//	 * r.enUS: "payment future for transaction %s failed. "
//	 * r: "transactions pour batch %s chargé. "
//	 * r.enUS: "transactions for batch %s loaded. "
//	 * r: "transactions pour batch %s a échoué. "
//	 * r.enUS: "transactions for batch %s failed. "
//	 * r: PaiementScolaireFrFRGenApiServiceImpl
//	 * r.enUS: SchoolPaymentEnUSGenApiServiceImpl
//	 * r: InscriptionScolaireFrFRGenApiServiceImpl
//	 * r.enUS: SchoolEnrollmentEnUSGenApiServiceImpl
//	 * r: futureAuthorizeNetPaiement
//	 * r.enUS: futureAuthorizeNetPayment
//	 * r: paiementService
//	 * r.enUS: paymentService
//	 * r: inscriptionService
//	 * r.enUS: enrollmentService
//	 * r: siteContexteFrFR
//	 * r.enUS: siteContextEnUS
//	 * r: requeteSite
//	 * r.enUS: siteRequest
//	 * r: ConfigSite
//	 * r.enUS: SiteConfig
//	 */
//	public Future<Void> futureAuthorizeNetBatch(
//			MerchantAuthenticationType merchantAuthenticationType
//			, GetSettledBatchListController batchController
//			, BatchDetailsType batch
//			, PaiementScolaireFrFRApiServiceImpl paiementService
//			, InscriptionScolaireFrFRApiServiceImpl inscriptionService
//			, RequeteSiteFrFR requeteSite
//			,  Handler<AsyncResult<Void>> a) {
//		Promise<Void> promise = Promise.promise();
//		try {
//			ConfigSite configSite = requeteSite.getConfigSite_();
//			Paging paging = new Paging();
//			paging.setLimit(100);
//			paging.setOffset(1);
//			
//			GetTransactionListRequest getRequest = new GetTransactionListRequest();
//			getRequest.setMerchantAuthentication(merchantAuthenticationType);
//			getRequest.setBatchId(batch.getBatchId());
//
//			getRequest.setPaging(paging);
//
//			TransactionListSorting sorting = new TransactionListSorting();
//			sorting.setOrderBy(TransactionListOrderFieldEnum.SUBMIT_TIME_UTC);
//			sorting.setOrderDescending(true);
//
//			getRequest.setSorting(sorting);
//
//			GetTransactionListController controller = new GetTransactionListController(getRequest);
//			GetTransactionListController.setEnvironment(Environment.valueOf(configSite.getAuthorizeEnvironment()));
//			controller.execute();
//			if(controller.getErrorResponse() != null)
//				throw new RuntimeException(batchController.getResults().toString());
//
//			List<Future> futures = new ArrayList<>();
//
//			GetTransactionListResponse getResponse = controller.getApiResponse();
//			if (getResponse != null) {
//
//				if (getResponse.getMessages().getResultCode() == MessageTypeEnum.OK) {
//					List<TransactionSummaryType> transactions = Optional.ofNullable(getResponse).map(GetTransactionListResponse::getTransactions).map(ArrayOfTransactionSummaryType::getTransaction).orElse(Arrays.asList());
//					LOGGER.info(String.format("Il y a %s transactions dans batch %s à charger. ", transactions.size(), batch.getBatchId()));
//					for(TransactionSummaryType transaction : transactions) {
//						futures.add(
//							inscriptionService.futureAuthorizeNetPaiement(merchantAuthenticationType, paiementService, requeteSite, transaction, null, b -> {
//								if(b.succeeded()) {
//									LOGGER.info(String.format("transaction %s chargé. ", transaction.getTransId()));
//								} else {
//									LOGGER.error(String.format("paiement future pour transaction %s a échoué. ", transaction.getTransId()), b.cause());
//								}
//							})
//						);
//					}
//					CompositeFuture.all(futures).setHandler(b -> {
//						if(b.succeeded()) {
//							a.handle(Future.succeededFuture());
//							promise.complete();
//							LOGGER.info(String.format("transactions pour batch %s chargé. ", batch.getBatchId()));
//						} else {
//							LOGGER.error(String.format("transactions pour batch %s a échoué. ", batch.getBatchId()));
//							promise.fail(b.cause());
//						}
//					});
//				}
//			}
//			return promise.future();
//		} catch(Exception e) {
//			a.handle(Future.failedFuture(e));
//			return Future.failedFuture(e);
//		}
//	}

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

