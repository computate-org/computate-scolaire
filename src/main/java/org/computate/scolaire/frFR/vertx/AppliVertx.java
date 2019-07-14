package org.computate.scolaire.frFR.vertx; 

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.frFR.cluster.ClusterFrFRGenApiService;
import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.ecole.EcoleFrFRGenApiService;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.JksOptions;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.oauth2.OAuth2FlowType;
import io.vertx.ext.auth.oauth2.providers.KeycloakAuth;
import io.vertx.ext.healthchecks.HealthCheckHandler;
import io.vertx.ext.healthchecks.Status;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.ext.web.api.contract.openapi3.impl.AppOpenAPI3RouterFactory;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.UserSessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.serviceproxy.ServiceBinder;

/**
 * NomCanonique.enUS: org.computate.scolaire.enUS.vertx.AppVertx
 * enUS: A Java class to start the Vert.x application as a main method. 
 */ 
public class AppliVertx extends AppliVertxGen<AbstractVerticle> {

	/**
	 * enUS: A SQL query for creating a database table "c" to store any type of object in the application. 
	 */
	public static final String SQL_createTableC = "create table if not exists c(pk bigserial primary key, ajour boolean, nom_canonique text, cree timestamp with time zone default now(), modifie timestamp with time zone default now(), id_utilisateur text); ";

	/**
	 * enUS: A SQL query for creating a unique index on the "c" table based on the pk, canonical_name, and user_id fields for faster lookup. 
	 * r: nom_canonique
	 * r.enUS: canonical_name
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
	 * r: modifie
	 * r.enUS: modified
	 */
	public static final String SQL_createTableA = "create table if not exists a(pk bigserial primary key, pk1 bigint, pk2 bigint, entite1 text, entite2 text, actuel boolean, cree timestamp with time zone default now(), modifie timestamp with time zone default now(), foreign key(pk1) references c(pk), foreign key(pk2) references c(pk)); ";

	/**
	 * enUS: A SQL query for creating a database table "d" to store String values to define fields in an instance of a class based on a record in the "c" table. 
	 * r: actuel
	 * r.enUS: current
	 * r: cree
	 * r.enUS: created
	 * r: modifie
	 * r.enUS: modified
	 */
	public static final String SQL_createTableD = "create table if not exists d(pk bigserial primary key, chemin text, valeur text, actuel boolean, cree timestamp with time zone default now(), modifie timestamp with time zone default now(), pk_c bigint, foreign key(pk_c) references c(pk)); ";

	/**
	 * enUS: Concatenate all of the SQL together to execute when the server starts. 
	 * Var.enUS: SQL_initAll
	 */
	public static final String SQL_initTout = SQL_createTableC + SQL_uniqueIndexC + SQL_createTableA + SQL_createTableD;

	/**
	 * enUS: A io.vertx.ext.jdbc.JDBCClient for connecting to the relational database PostgreSQL. 
	 */
	private JDBCClient jdbcClient;

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
	 * Param1.var.enUS: startFuture
	 * r: SiteContexteFrFR
	 * r.enUS: SiteContextEnUS
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: demarrerFuture
	 * r.enUS: startFuture
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
	 * r: demarrerServeur
	 * r.enUS: startServer
	 * r: etapesFutures
	 * r.enUS: futureSteps
	 * 
	 * enUS: This is called by Vert.x when the verticle instance is deployed. 
	 * enUS: Initialize a new site context object for storing information about the entire site in English. 
	 * enUS: Setup the startFuture to handle the configuration steps and starting the server. 
	 */
	@Override
	public void start(Future<Void> demarrerFuture) throws Exception {

		siteContexteFrFR = new SiteContexteFrFR();
		siteContexteFrFR.setVertx(vertx);
		siteContexteFrFR.initLoinSiteContexteFrFR();

		Future<Void> etapesFutures = configurerDonnees().compose(a -> 
			configurerCluster().compose(b -> 
				configurerOpenApi().compose(c -> 
					configurerControlesSante().compose(d -> 
						configurerExecuteurTravailleurPartage().compose(e -> 
							demarrerServeur()
						)
					)
				)
			)
		);
		etapesFutures.setHandler(demarrerFuture.completer());
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
	 * enUS: Return a future that configures a shared database client connection. 
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
	 */
	private Future<Void> configurerDonnees() {
		ConfigSite configSite = siteContexteFrFR.getConfigSite();
		Future<Void> future = Future.future();

		JsonObject jdbcConfig = new JsonObject();
		if (StringUtils.isNotEmpty(configSite.getJdbcUrl()))
			jdbcConfig.put("url", configSite.getJdbcUrl());
		if (StringUtils.isNotEmpty(configSite.getJdbcClassePilote()))
			jdbcConfig.put("driver_class", configSite.getJdbcClassePilote());
		if (StringUtils.isNotEmpty(configSite.getJdbcUtilisateur()))
			jdbcConfig.put("user", configSite.getJdbcUtilisateur());
		if (StringUtils.isNotEmpty(configSite.getJdbcMotDePasse()))
			jdbcConfig.put("password", configSite.getJdbcMotDePasse());
		if (configSite.getJdbcTailleMaxPiscine() != null)
			jdbcConfig.put("max_pool_size", configSite.getJdbcTailleMaxPiscine());
		if (configSite.getJdbcTailleInitialePiscine() != null)
			jdbcConfig.put("initial_pool_size", configSite.getJdbcTailleInitialePiscine());
		if (configSite.getJdbcTailleMinPiscine() != null)
			jdbcConfig.put("min_pool_size", configSite.getJdbcTailleMinPiscine());
		if (configSite.getJdbcMaxDeclarations() != null)
			jdbcConfig.put("max_statements", configSite.getJdbcMaxDeclarations());
		if (configSite.getJdbcMaxDeclarationsParConnexion() != null)
			jdbcConfig.put("max_statements_per_connection", configSite.getJdbcMaxDeclarationsParConnexion());
		if (configSite.getJdbcTempsInactiviteMax() != null)
			jdbcConfig.put("max_idle_time", configSite.getJdbcTempsInactiviteMax());
		jdbcClient = JDBCClient.createShared(vertx, jdbcConfig);

		siteContexteFrFR.setClientSql(jdbcClient);

		jdbcClient.getConnection(a -> {
			if (a.failed()) {
				LOGGER.error(configurerDonneesErreurConnexion, a.cause());
				future.fail(a.cause());
			} else {
				LOGGER.info(configurerDonneesSuccesConnexion);
				SQLConnection connection = a.result();
				connection.execute(SQL_initTout, create -> {
					connection.close();
					if (create.failed()) {
						LOGGER.error(configurerDonneesErreurInit, create.cause());
						future.fail(create.cause());
					} else {
						LOGGER.info(configurerDonneesSuccesInit);
						future.complete();
					}
				});
			}
		});

		return future;
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
	 * enUS: Return a future that configures a shared cluster data. 
	 */
	private Future<Void> configurerCluster() {
		ConfigSite configSite = siteContexteFrFR.getConfigSite();
		Future<Void> future = Future.future();
		SharedData donneesPartagees = vertx.sharedData();
		donneesPartagees.getClusterWideMap("donneesCluster", res -> {
			if (res.succeeded()) {
				AsyncMap<Object, Object> donneesCluster = res.result();
				donneesCluster.put("configSite", configSite, resPut -> {
					if (resPut.succeeded()) {
						LOGGER.error(configurerClusterSuccesDonnees);
						future.complete();
					} else {
						LOGGER.error(configurerClusterErreurDonnees, res.cause());
						future.fail(res.cause());
					}
				});
			} else {
				LOGGER.error(configurerClusterErreurDonnees, res.cause());
				future.fail(res.cause());
			}
		});
		return future;
	}

	private Future<Void> configurerOpenApi() {
		ConfigSite configSite = siteContexteFrFR.getConfigSite();
		Future<Void> future = Future.future();
		Router routeur = Router.router(vertx);

		AppOpenAPI3RouterFactory.create(vertx, routeur, "openapi3.yaml", ar -> {
			if (ar.succeeded()) {
				AppOpenAPI3RouterFactory usineRouteur = ar.result();
				usineRouteur.mountServicesFromExtensions();
				siteContexteFrFR.setUsineRouteur(usineRouteur);

				JsonObject keycloakJson = new JsonObject() {
					{
						put("realm", configSite.getAuthRoyaume());
						put("resource", configSite.getAuthRessource());
						put("auth-server-url", configSite.getAuthUrl());
						put("ssl-required", configSite.getAuthSslRequis());
						put("use-resource-role-mappings", false);
						put("bearer-only", false);
						put("enable-basic-auth", false);
						put("expose-token", true);
						put("credentials", new JsonObject().put("secret", configSite.getAuthSecret()));
						put("connection-pool-size", 20);
						put("disable-trust-manager", false);
						put("allow-any-hostname", false);
						put("policy-enforcer", new JsonObject());
						put("redirect-rewrite-rules", new JsonObject().put("^(.*)$", "$1"));
					}
				};

				String siteNomHote = configSite.getSiteNomHote();
				Integer sitePort = configSite.getSitePort();
				String siteUrlBase = configSite.getSiteUrlBase();
				OAuth2Auth authFournisseur = KeycloakAuth.create(vertx, OAuth2FlowType.AUTH_CODE, keycloakJson);

				routeur.route().handler(CookieHandler.create());
				routeur.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

				routeur.route().handler(UserSessionHandler.create(authFournisseur));

				OAuth2AuthHandler gestionnaireAuth = OAuth2AuthHandler.create(authFournisseur,
						siteUrlBase + "/callback");

				gestionnaireAuth.setupCallback(routeur.get("/callback"));

				routeur.get("/deconnexion").handler(rc -> {
					Session session = rc.session();
					if (session != null) {
						session.destroy();
					}
					rc.clearUser();
					rc.reroute("/");
				});

				usineRouteur.addSecurityHandler("openIdConnect", gestionnaireAuth);

				usineRouteur.initRouter();

				future.complete();
			} else {
				LOGGER.error("Could not configure the api", ar.cause());
				future.fail(ar.cause());
			}
		});
		return future;
	}

	public AppliVertx setupCallback(SiteContexteFrFR siteContexte, String callbackPath) { 
		OpenAPI3RouterFactory usineRouteur = siteContexte.getUsineRouteur();
		OAuth2AuthHandler gestionnaireAuth = siteContexte.getGestionnaireAuth();
		ConfigSite configSite = siteContexte.getConfigSite();
		String siteNomHote = configSite.getSiteNomHote();
		Integer sitePort = configSite.getSitePort();
		String siteUrlBase = "https://" + siteNomHote + ":" + sitePort;
		OAuth2Auth authFournisseur = siteContexte.getAuthFournisseur();

		Route route = usineRouteur.getRouter().get(callbackPath);

		if (callbackPath != null && !"".equals(callbackPath)) {
			// no matter what path was provided we will make sure it is the correct one
			route.path(callbackPath);
		}

		route.method(HttpMethod.GET);

		route.handler(ctx -> {
			// Handle the callback of the flow
			final String code = ctx.request().getParam("code");

			// code is a require value
			if (code == null) {
				ctx.fail(400);
				return;
			}

			final String state = ctx.request().getParam("state");

			final JsonObject config = new JsonObject().put("code", code);

			if (siteUrlBase != null) {
				config.put("redirect_uri", siteUrlBase + route.getPath());
			}

			// if (extraParams != null) {
			// config.mergeIn(extraParams);
			// }

			authFournisseur.authenticate(config, res -> {
				if (res.failed()) {
					ctx.fail(res.cause());
				} else {
					ctx.setUser(res.result());
					Session session = ctx.session();
					if (session != null) {
						// the user has upgraded from unauthenticated to authenticated
						// session should be upgraded as recommended by owasp
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
				}
			});
		});

		return this;
	}

	private <T> void enregistrerService(ServiceBinder serviceBinder, T service) {
		Class<T> c = (Class<T>)service.getClass();
		MessageConsumer<JsonObject> calculInrApiConsumer = serviceBinder.register(c, service);
	}

//	private Future<Void> configurerExecuteurTravailleurPartage(SiteContexteFrFR siteContexteFrFR, SiteContexteEnUS siteContexteEnUS) {
	private Future<Void> configurerExecuteurTravailleurPartage() {
		Future<Void> future = Future.future();

		WorkerExecutor executeurTravailleur = vertx.createSharedWorkerExecutor("WorkerExecutor");
		siteContexteFrFR.setExecuteurTravailleur(executeurTravailleur);
//		siteContexteEnUS.setExecuteurTravailleur(executeurTravailleur);
		future.complete();
		return future;
	}

//	private Future<Void> configurerControlesSante(SiteContexteFrFR siteContexteFrFR, SiteContexteEnUS siteContexteEnUS) {
	private Future<Void> configurerControlesSante() {
		Future<Void> future = Future.future();
		Router siteRouteur = siteContexteFrFR.getUsineRouteur().getRouter();
		HealthCheckHandler healthCheckHandler = HealthCheckHandler.create(vertx);

		healthCheckHandler.register("database", 2000, a -> {
			siteContexteFrFR.getClientSql().queryWithParams("select current_timestamp"
					, new JsonArray(Arrays.asList())
					, selectCAsync
			-> {
				if(selectCAsync.succeeded()) {
					a.complete(Status.OK());
				} else {
					LOGGER.error("The vertx application is down. ", a.cause());
					future.fail(a.cause());
				}
			});
		});
		healthCheckHandler.register("solr", 2000, a -> {
			SolrQuery query = new SolrQuery();
			query.setQuery("*:*");
			try {
				QueryResponse r = siteContexteFrFR.getClientSolr().query(query);
				if(r.getResults().size() > 0)
					a.complete(Status.OK());
				else {
					LOGGER.error("The solr application is empty. ", a.cause());
					future.fail(a.cause());
				}
			} catch (SolrServerException | IOException e) {
				LOGGER.error("The solr application is down. ", a.cause());
				future.fail(a.cause());
			}
		});
		siteRouteur.get("/health").handler(healthCheckHandler);
		future.complete();
		return future;
	}

	/**
	 * r: FrFR
	 * r.enUS: EnUS
	 */ 
//	private Future<Void> demarrerServeur(SiteContexteFrFR siteContexteFrFR, SiteContexteEnUS siteContexteEnUS) {
	private Future<Void> demarrerServeur() {
		ConfigSite configSite = siteContexteFrFR.getConfigSite();
		Future<Void> future = Future.future();

		ClusterFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);
//		ClusterEnUSGenApiService.enregistrerService(siteContexteEnUS, vertx);

		EcoleFrFRGenApiService.enregistrerService(siteContexteFrFR, vertx);

		Router siteRouteur = siteContexteFrFR.getUsineRouteur().getRouter();
		// siteContexte.setSiteRouteur_(siteRouteur);


//		siteRouteur.route().order(-2).handler(siteContexteFrFR.getSiteTracingHandler()).failureHandler(siteContexteFrFR.getSiteTracingHandler());

		StaticHandler staticHandler = StaticHandler.create().setCachingEnabled(false).setFilesReadOnly(true);
		if("scolaire.computate.org".equals(configSite.getSiteNomHote())) {
			staticHandler.setAllowRootFileSystemAccess(true);
			staticHandler.setWebRoot("/usr/local/src/computate-scolaire-static");
		}
		siteRouteur.route("/static/*").handler(staticHandler);

		String siteNomHote = configSite.getSiteNomHote();
		Integer sitePort = configSite.getSitePort();
		HttpServerOptions options = new HttpServerOptions();
		// options.setMaxWebsocketFrameSize(1000000);
		if(new File(configSite.getSslJksChemin()).exists()) {
			options.setKeyStoreOptions(
					new JksOptions().setPath(configSite.getSslJksChemin()).setPassword(configSite.getSslJksMotDePasse()));
			options.setSsl(true);
		}
		options.setPort(sitePort);
//		options.setHost("localhost");

		LOGGER.info(String.format("HTTP server starting: %s://%s:%s", "https", siteNomHote, sitePort));
		vertx.createHttpServer(options).requestHandler(siteRouteur).listen(ar -> {
			if (ar.succeeded()) {
				LOGGER.info(String.format("HTTP server running: %s:%s", "*", sitePort));
				future.complete();
			} else {
				LOGGER.error("Could not start a HTTP server", ar.cause());
				future.fail(ar.cause());
			}
		});

		return future;
	}

	/**
	 * Param1.var.enUS: stopFuture
	 * r: SiteContexteFrFR
	 * r.enUS: SiteContextEnUS
	 * r: siteContexteFrFR
	 * r.enUS: siteContextEnUS
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: arreterFuture
	 * r.enUS: stopFuture
	 * 
	 * enUS: This is called by Vert.x when the verticle instance is undeployed. 
	 * enUS: Setup the stopFuture to handle tearing down the server. 
	 */
	@Override
	public void stop(Future<Void> arreterFuture) throws Exception {
		Future<Void> etapesFutures = fermerDonnees();
		etapesFutures.setHandler(arreterFuture.completer());
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
	 * enUS: Return a future to close the database client connection. 
	 */        
	private Future<Void> fermerDonnees() {
		Future<Void> future = Future.future();
		SQLClient clientSql = siteContexteFrFR.getClientSql();

		if(clientSql != null) {
			clientSql.close(a -> {
				if (a.failed()) {
					LOGGER.error(fermerDonneesErreur, a.cause());
					future.fail(a.cause());
				} else {
					LOGGER.error(fermerDonneesSucces, a.cause());
					future.complete();
				}
			});
		}
		return future;
	}
}
