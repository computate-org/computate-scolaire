package org.computate.scolaire.enUS.vertx;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
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
 *	A Java class to start the Vert.x application as a main method. 
 **/
public class AppVertx extends AppVertxGen<AbstractVerticle> {

	/**	
	 *	A SQL query for creating a database table "c" to store any type of object in the application. 
	 **/
	public static final String SQL_createTableC = "create table if not exists c(pk bigserial primary key, ajour boolean, nom_canonique text, cree timestamp with time zone default now(), modifie timestamp with time zone default now(), id_utilisateur text); ";

	/**	
	 *	A SQL query for creating a unique index on the "c" table based on the pk, canonical_name, and user_id fields for faster lookup. 
	 **/
	public static final String SQL_uniqueIndexC = "create unique index if not exists c_index_user on c(pk, canonical_name, user_id); ";

	/**	
	 *	A SQL query for creating a database table "a" to store relations (like entity relations) between one other record in the "c" table with another record in the "c" table. 
	 **/
	public static final String SQL_createTableA = "create table if not exists a(pk bigserial primary key, pk1 bigint, pk2 bigint, entity1 text, entity2 text, current boolean, created timestamp with time zone default now(), modified timestamp with time zone default now(), foreign key(pk1) references c(pk), foreign key(pk2) references c(pk)); ";

	/**	
	 *	A SQL query for creating a database table "d" to store String values to define fields in an instance of a class based on a record in the "c" table. 
	 **/
	public static final String SQL_createTableD = "create table if not exists d(pk bigserial primary key, chemin text, valeur text, current boolean, created timestamp with time zone default now(), modified timestamp with time zone default now(), pk_c bigint, foreign key(pk_c) references c(pk)); ";

	/**	
	 *	Concatenate all of the SQL together to execute when the server starts. 
	 **/
	public static final String SQL_initAll = SQL_createTableC + SQL_uniqueIndexC + SQL_createTableA + SQL_createTableD;

	/**	
	 *	A io.vertx.ext.jdbc.JDBCClient for connecting to the relational database PostgreSQL. 
	 **/
	private JDBCClient jdbcClient;

	/**	
	 *	A site context object for storing information about the entire site in English. 
	 **/
	SiteContextEnUS siteContextEnUS;

	/**	
	 *	For logging information and errors in the application. 
	 **/
	private static final Logger LOGGER = LoggerFactory.getLogger(AppVertx.class);

	/**	
	 *	The main method for the Vert.x application that runs the Vert.x Runner class
	 **/
	public static void  main(String[] args) {
		RunnerVertx.run(AppVertx.class);
	}

	/**	
	 *	This is called by Vert.x when the verticle instance is deployed. 
	 *	Initialize a new site context object for storing information about the entire site in English. 
	 *	Setup the startFuture to handle the configuration steps and starting the server. 
	 **/
	@Override()
	public void  start(Future<Void> startFuture) throws Exception, Exception {

		siteContextEnUS = new SiteContextEnUS();
		siteContextEnUS.setVertx(vertx);
		siteContextEnUS.initDeepSiteContextEnUS();

		Future<Void> futureSteps = configureData().compose(a -> 
			configureCluster().compose(b -> 
				configureOpenApi().compose(c -> 
					configureHealthChecks().compose(d -> 
						configureSharedWorkerExecutor().compose(e -> 
							startServer()
						)
					)
				)
			)
		);
		futureSteps.setHandler(startFuture.completer());
	}

	/**	
	 *	Configure shared database connections across the cluster for massive scaling of the application. 
	 *	Return a future that configures a shared database client connection. 
	 *	Load the database configuration into a shared io.vertx.ext.jdbc.JDBCClient for a scalable, clustered datasource connection pool. 
	 *	Initialize the database tables if not already created for the first time. 
	 **/
	private Future<Void> configureData() {
		SiteConfig siteConfig = siteContextEnUS.getSiteConfig();
		Future<Void> future = Future.future();

		JsonObject jdbcConfig = new JsonObject();
		if (StringUtils.isNotEmpty(siteConfig.getJdbcUrl()))
			jdbcConfig.put("url", siteConfig.getJdbcUrl());
		if (StringUtils.isNotEmpty(siteConfig.getJdbcClassePilote()))
			jdbcConfig.put("driver_class", siteConfig.getJdbcClassePilote());
		if (StringUtils.isNotEmpty(siteConfig.getJdbcUtilisateur()))
			jdbcConfig.put("user", siteConfig.getJdbcUtilisateur());
		if (StringUtils.isNotEmpty(siteConfig.getJdbcMotDePasse()))
			jdbcConfig.put("password", siteConfig.getJdbcMotDePasse());
		if (siteConfig.getJdbcTailleMaxPiscine() != null)
			jdbcConfig.put("max_pool_size", siteConfig.getJdbcTailleMaxPiscine());
		if (siteConfig.getJdbcTailleInitialePiscine() != null)
			jdbcConfig.put("initial_pool_size", siteConfig.getJdbcTailleInitialePiscine());
		if (siteConfig.getJdbcTailleMinPiscine() != null)
			jdbcConfig.put("min_pool_size", siteConfig.getJdbcTailleMinPiscine());
		if (siteConfig.getJdbcMaxDeclarations() != null)
			jdbcConfig.put("max_statements", siteConfig.getJdbcMaxDeclarations());
		if (siteConfig.getJdbcMaxDeclarationsParConnexion() != null)
			jdbcConfig.put("max_statements_per_connection", siteConfig.getJdbcMaxDeclarationsParConnexion());
		if (siteConfig.getJdbcTempsInactiviteMax() != null)
			jdbcConfig.put("max_idle_time", siteConfig.getJdbcTempsInactiviteMax());
		jdbcClient = JDBCClient.createShared(vertx, jdbcConfig);

		siteContextEnUS.setClientSql(jdbcClient);

		jdbcClient.getConnection(a -> {
			if (a.failed()) {
				LOGGER.error(configureDataConnectionError, a.cause());
				future.fail(a.cause());
			} else {
				LOGGER.info(configureDataConnectionSuccess);
				SQLConnection connection = a.result();
				connection.execute(SQL_initAll, create -> {
					connection.close();
					if (create.failed()) {
						LOGGER.error(configureDataInitError, create.cause());
						future.fail(create.cause());
					} else {
						LOGGER.info(configureDataInitSuccess);
						future.complete();
					}
				});
			}
		});

		return future;
	}

	/**	
	 *	Configure shared data across the cluster for massive scaling of the application. 
	 *	Return a future that configures a shared cluster data. 
	 **/
	private Future<Void> configureCluster() {
		SiteConfig siteConfig = siteContextEnUS.getSiteConfig();
		Future<Void> future = Future.future();
		SharedData sharedData = vertx.sharedData();
		sharedData.getClusterWideMap("clusterData", res -> {
			if (res.succeeded()) {
				AsyncMap<Object, Object> clusterData = res.result();
				clusterData.put("siteConfig", siteConfig, resPut -> {
					if (resPut.succeeded()) {
						LOGGER.error(configureClusterDataSuccess);
						future.complete();
					} else {
						LOGGER.error(configureClusterDataError, res.cause());
						future.fail(res.cause());
					}
				});
			} else {
				LOGGER.error(configureClusterDataError, res.cause());
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

	public AppVertx setupCallback(SiteContextEnUS siteContexte, String callbackPath) { 
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

	private <T> void  enregistrerService(ServiceBinder serviceBinder, T service) {
		Class<T> c = (Class<T>)service.getClass();
		MessageConsumer<JsonObject> calculInrApiConsumer = serviceBinder.register(c, service);
	}

	private Future<Void> configurerExecuteurTravailleurPartage() {
		Future<Void> future = Future.future();

		WorkerExecutor executeurTravailleur = vertx.createSharedWorkerExecutor("WorkerExecutor");
		siteContexteFrFR.setExecuteurTravailleur(executeurTravailleur);
//		siteContexteEnUS.setExecuteurTravailleur(executeurTravailleur);
		future.complete();
		return future;
	}

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

	private Future<Void> demarrerServeur() {
		ConfigSite configSite = siteContexteEnUS.getConfigSite();
		Future<Void> future = Future.future();

		ClusterEnUSGenApiService.enregistrerService(siteContexteEnUS, vertx);
//		ClusterEnUSGenApiService.enregistrerService(siteContexteEnUS, vertx);

		EcoleEnUSGenApiService.enregistrerService(siteContexteEnUS, vertx);

		Router siteRouteur = siteContexteEnUS.getUsineRouteur().getRouter();
		// siteContexte.setSiteRouteur_(siteRouteur);


//		siteRouteur.route().order(-2).handler(siteContexteEnUS.getSiteTracingHandler()).failureHandler(siteContexteEnUS.getSiteTracingHandler());

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
	 *	This is called by Vert.x when the verticle instance is undeployed. 
	 *	Setup the stopFuture to handle tearing down the server. 
	 **/
	@Override()
	public void  stop(Future<Void> stopFuture) throws Exception, Exception {
		Future<Void> etapesFutures = fermerDonnees();
		etapesFutures.setHandler(stopFuture.completer());
	}

	/**	
	 *	Return a future to close the database client connection. 
	 **/
	private Future<Void> closeData() {
		Future<Void> future = Future.future();
		SQLClient clientSql = siteContexteFrFR.getClientSql();

		if(clientSql != null) {
			clientSql.close(a -> {
				if (a.failed()) {
					LOGGER.error(closeDataError, a.cause());
					future.fail(a.cause());
				} else {
					LOGGER.error(closeDataSuccess, a.cause());
					future.complete();
				}
			});
		}
		return future;
	}
}
