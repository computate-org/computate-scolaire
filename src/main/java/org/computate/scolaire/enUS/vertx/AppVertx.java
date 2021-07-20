package org.computate.scolaire.enUS.vertx;

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
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.enUS.age.SchoolAgeEnUSGenApiService;
import org.computate.scolaire.enUS.year.SchoolYearEnUSGenApiService;
import org.computate.scolaire.enUS.block.SchoolBlockEnUSGenApiService;
import org.computate.scolaire.enUS.cluster.ClusterEnUSGenApiService;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.design.PageDesignEnUSGenApiService;
import org.computate.scolaire.enUS.school.SchoolEnUSGenApiService;
import org.computate.scolaire.enUS.child.SchoolChild;
import org.computate.scolaire.enUS.child.SchoolChildEnUSApiServiceImpl;
import org.computate.scolaire.enUS.child.SchoolChildEnUSGenApiService;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import org.computate.scolaire.enUS.guardian.SchoolGuardianEnUSApiServiceImpl;
import org.computate.scolaire.enUS.guardian.SchoolGuardianEnUSGenApiService;
import org.computate.scolaire.enUS.html.part.HtmlPartEnUSGenApiService;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollmentEnUSApiServiceImpl;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollmentEnUSGenApiService;
import org.computate.scolaire.frFR.java.LocalDateSerializer;
import org.computate.scolaire.frFR.java.LocalTimeSerializer;
import org.computate.scolaire.frFR.java.ZonedDateTimeSerializer;
import org.computate.scolaire.enUS.mom.SchoolMom;
import org.computate.scolaire.enUS.mom.SchoolMomEnUSApiServiceImpl;
import org.computate.scolaire.enUS.mom.SchoolMomEnUSGenApiService;
import org.computate.scolaire.enUS.payment.SchoolPaymentEnUSGenApiService;
import org.computate.scolaire.enUS.dad.SchoolDad;
import org.computate.scolaire.enUS.dad.SchoolDadEnUSApiServiceImpl;
import org.computate.scolaire.enUS.dad.SchoolDadEnUSGenApiService;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.receipt.SchoolReceiptEnUSGenApiService;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.computate.scolaire.enUS.season.SchoolSeasonEnUSGenApiService;
import org.computate.scolaire.enUS.session.SchoolSessionEnUSGenApiService;
import org.computate.scolaire.enUS.user.SiteUserEnUSGenApiService;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.EventBusOptions;
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
import io.vertx.core.spi.cluster.ClusterManager;
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
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Transaction;

/**
 * A Java class to start the Vert.x application as a main method. 
 * CanonicalName: org.computate.scolaire.frFR.vertx.AppliVertx
 **/
public class AppVertx extends AppVertxGen<AbstractVerticle> {

	public final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	/**
	 * A SQL query for creating a database table "c" to store any type of object in the application. 
	 **/
	public static final String SQL_createTableC = "create table if not exists c(pk bigserial primary key, current boolean, canonical_name text, created timestamp with time zone, user_id text); ";

	/**
	 * A SQL query for creating a unique index on the "c" table based on the pk, canonical_name, and user_id fields for faster lookup. 
	 **/
	public static final String SQL_uniqueIndexC = "create index if not exists c_index_user on c(canonical_name, user_id); ";

	/**
	 * A SQL query for creating a database table "a" to store relations (like entity relations) between one other record in the "c" table with another record in the "c" table. 
	 **/
	public static final String SQL_createTableA = "create table if not exists a(pk bigserial primary key, pk1 bigint, entity1 text, pk2 bigint, entity2 text, current boolean, created timestamp with time zone, constraint a_constraint unique (pk1, entity1, pk2, entity2)); ";

	/**
	 * A SQL query for creating an index on the "a" table based on fields for faster lookup. 
	 **/
	public static final String SQL_uniqueIndexA1 = "create index if not exists a_index_1 on a(pk1); ";

	/**
	 * A SQL query for creating an index on the "a" table based on fields for faster lookup. 
	 **/
	public static final String SQL_uniqueIndexA2 = "create index if not exists a_index_2 on a(pk2); ";

	/**
	 * A SQL query for creating a database table "d" to store String values to define fields in an instance of a class based on a record in the "c" table. 
	 **/
	public static final String SQL_createTableD = "create table if not exists d(pk bigserial primary key, pk_c bigint, path text, value text, current boolean, created timestamp with time zone, constraint d_constraint unique (pk_c, path)); ";

	/**
	 * A SQL query for creating an index on the "d" table based on fields for faster lookup. 
	 **/
	public static final String SQL_uniqueIndexD = "create index if not exists d_index on d(pk_c); ";

	/**
	 * A io.vertx.ext.jdbc.JDBCClient for connecting to the relational database PostgreSQL. 
	 **/
	private PgPool pgPool;

	/**
	 * A site context object for storing information about the entire site in English. 
	 **/
	SiteContextEnUS siteContextEnUS;

	/**
	 * For logging information and errors in the application. 
	 **/
	private static final Logger LOGGER = LoggerFactory.getLogger(AppVertx.class);

	/**
	 * The main method for the Vert.x application that runs the Vert.x Runner class
	 **/
	public static void  main(String[] args) {
		run();
		WorkerVertx.run();
	}

	public static void  run() {
		Class<?> c = AppVertx.class;
		JsonObject zkConfig = new JsonObject();
		String zookeeperHostName = System.getenv("zookeeperHostName");
		Integer zookeeperPort = Integer.parseInt(System.getenv("zookeeperPort"));
		Integer clusterPort = System.getenv("clusterPort") == null ? null : Integer.parseInt(System.getenv("clusterPort"));
		String clusterHost = System.getenv("clusterHost");
		Integer clusterPublicPort = System.getenv("clusterPublicPort") == null ? null : Integer.parseInt(System.getenv("clusterPublicPort"));
		Integer siteInstances = System.getenv("siteInstances") == null ? 1 : Integer.parseInt(System.getenv("siteInstances"));
		String clusterPublicHost = System.getenv("clusterPublicHost");
		String zookeeperHosts = zookeeperHostName + ":" + zookeeperPort;
		zkConfig.put("zookeeperHosts", zookeeperHosts);
		zkConfig.put("sessionTimeout", 20000);
		zkConfig.put("connectTimeout", 3000);
		zkConfig.put("rootPath", "io.vertx");
		zkConfig.put("retry", new JsonObject() {
			{
				put("initialSleepTime", 100);
				put("intervalTimes", 10000);
				put("maxTimes", 3);
			}
		});
		ClusterManager gestionnaireCluster = new ZookeeperClusterManager(zkConfig);
		VertxOptions optionsVertx = new VertxOptions();
		// For OpenShift
		EventBusOptions eventBusOptions = new EventBusOptions();
		String hostname = System.getenv("HOSTNAME");
		String openshiftService = System.getenv("openshiftService");
		if(clusterHost == null) {
			clusterHost = hostname;
		}
		if(clusterPublicHost == null) {
			if(hostname != null && openshiftService != null) {
				clusterPublicHost = hostname + "." + openshiftService;
			}
		}
		if(clusterHost != null) {
			LOGGER.info(String.format("clusterHost: %s", clusterHost));
			eventBusOptions.setHost(clusterHost);
		}
		if(clusterPort != null) {
			LOGGER.info(String.format("clusterPort: %s", clusterPort));
			eventBusOptions.setPort(clusterPort);
		}
		if(clusterPublicHost != null) {
			LOGGER.info(String.format("clusterPublicHost: %s", clusterPublicHost));
			eventBusOptions.setClusterPublicHost(clusterPublicHost);
		}
		if(clusterPublicPort != null) {
			LOGGER.info(String.format("clusterPublicPort: %s", clusterPublicPort));
			eventBusOptions.setClusterPublicPort(clusterPublicPort);
		}
		eventBusOptions.setClustered(true);
		optionsVertx.setEventBusOptions(eventBusOptions);
		optionsVertx.setClusterManager(gestionnaireCluster);
		DeploymentOptions deploymentOptions = new DeploymentOptions();
		deploymentOptions.setInstances(siteInstances);

		String verticleID = c.getName();

		Consumer<Vertx> runner = vertx -> {
			vertx.deployVerticle(verticleID, deploymentOptions);
		};
		Vertx.clusteredVertx(optionsVertx, res -> {
			if (res.succeeded()) {
				Vertx vertx = res.result();
				EventBus eventBus = vertx.eventBus();
				LOGGER.info("We now have a clustered event bus: {}", eventBus);
				runner.accept(vertx);
			} else {
				res.cause().printStackTrace();
			}
		});
	}

	/**
	 * This is called by Vert.x when the verticle instance is deployed. 
	 * Initialize a new site context object for storing information about the entire site in English. 
	 * Setup the startPromise to handle the configuration steps and starting the server. 
	 **/
	@Override()
	public void  start(Promise<Void> startPromise) throws Exception, Exception {

		siteContextEnUS = new SiteContextEnUS();
		siteContextEnUS.setVertx(vertx);
		siteContextEnUS.initDeepSiteContextEnUS();

		Future<Void> promiseSteps = configureData().future().compose(a -> 
			configureCluster().future().compose(b -> 
				configureOpenApi().future().compose(c -> 
					configureHealthChecks().future().compose(d -> 
						configureSharedWorkerExecutor().future().compose(e -> 
							configureWebsockets().future().compose(f -> 
								configureEmail().future().compose(g -> 
//									configureAuthorizeNetCharges().future().compose(h -> 
//										configureAuthorizeNetPayments(1).future().compose(i -> 
//											configureAuthorizeNetPayments(2).future().compose(j -> 
												startServer().future()
//											)
//										)
//									)
								)
							)
						)
					)
				)
			)
		);
		promiseSteps.setHandler(startPromise);
	}

	/**
	 * Configure shared database connections across the cluster for massive scaling of the application. 
	 * Return a promise that configures a shared database client connection. 
	 * Load the database configuration into a shared io.vertx.ext.jdbc.JDBCClient for a scalable, clustered datasource connection pool. 
	 * Initialize the database tables if not already created for the first time. 
	 **/
	private Promise<Void> configureData() {
		SiteConfig siteConfig = siteContextEnUS.getSiteConfig();
		Promise<Void> promise = Promise.promise();

		PgConnectOptions pgOptions = new PgConnectOptions();
		pgOptions.setPort(siteConfig.getJdbcPort());
		pgOptions.setHost(siteConfig.getJdbcHost());
		pgOptions.setDatabase(siteConfig.getJdbcDatabase());
		pgOptions.setUser(siteConfig.getJdbcUsername());
		pgOptions.setPassword(siteConfig.getJdbcPassword());
		pgOptions.setIdleTimeout(siteConfig.getJdbcMaxIdleTime());
		pgOptions.setIdleTimeoutUnit(TimeUnit.SECONDS);
		pgOptions.setConnectTimeout(siteConfig.getJdbcConnectTimeout());

		PoolOptions poolOptions = new PoolOptions();
		poolOptions.setMaxSize(siteConfig.getJdbcMaxPoolSize());
		poolOptions.setMaxWaitQueueSize(siteConfig.getJdbcMaxWaitQueueSize());

		pgPool = PgPool.pool(vertx, pgOptions, poolOptions);

		siteContextEnUS.setPgPool(pgPool);


		pgPool.preparedQuery(SQL_createTableC).execute(a -> {
			if (a.succeeded()) {
				pgPool.preparedQuery(SQL_uniqueIndexC).execute(b -> {
					if (b.succeeded()) {
						pgPool.preparedQuery(SQL_createTableA).execute(c -> {
							if (c.succeeded()) {
								pgPool.preparedQuery(SQL_uniqueIndexA1).execute(d -> {
									if (d.succeeded()) {
										pgPool.preparedQuery(SQL_uniqueIndexA2).execute(e -> {
											if (d.succeeded()) {
												pgPool.preparedQuery(SQL_createTableD).execute(f -> {
													if (f.succeeded()) {
														pgPool.preparedQuery(SQL_uniqueIndexD).execute(g -> {
															if (g.succeeded()) {
																LOGGER.info(configureDataInitSuccess);
																promise.complete();
															} else {
																LOGGER.error(configureDataInitError, g.cause());
																promise.fail(g.cause());
															}
														});
													} else {
														LOGGER.error(configureDataInitError, f.cause());
														promise.fail(f.cause());
													}
												});
											} else {
												LOGGER.error(configureDataInitError, e.cause());
												promise.fail(e.cause());
											}
										});
									} else {
										LOGGER.error(configureDataInitError, d.cause());
										promise.fail(d.cause());
									}
								});
							} else {
								LOGGER.error(configureDataInitError, c.cause());
								promise.fail(c.cause());
							}
						});
					} else {
						LOGGER.error(configureDataInitError, b.cause());
						promise.fail(b.cause());
					}
				});
			} else {
				LOGGER.error(configureDataInitError, a.cause());
				promise.fail(a.cause());
			}
		});

		return promise;
	}

	/**
	 * Configure shared data across the cluster for massive scaling of the application. 
	 * Return a promise that configures a shared cluster data. 
	 **/
	private Promise<Void> configureCluster() {
		SiteConfig siteConfig = siteContextEnUS.getSiteConfig();
		Promise<Void> promise = Promise.promise();
		SharedData sharedData = vertx.sharedData();
		sharedData.getClusterWideMap("clusterData", res -> {
			if (res.succeeded()) {
				AsyncMap<Object, Object> clusterData = res.result();
				clusterData.put("siteConfig", siteConfig, resPut -> {
					if (resPut.succeeded()) {
						LOGGER.info(configureClusterDataSuccess);
						promise.complete();
					} else {
						LOGGER.error(configureClusterDataError, res.cause());
						promise.fail(res.cause());
					}
				});
			} else {
				LOGGER.error(configureClusterDataError, res.cause());
				promise.fail(res.cause());
			}
		});
		return promise;
	}

	/**
	 * Configure the connection to the auth server and setup the routes based on the OpenAPI definition. 
	 * Setup a callback route when returning from the auth server after successful authentication. 
	 * Setup a logout route for logging out completely of the application. 
	 * Return a promise that configures the authentication server and OpenAPI. 
	 **/
	private Promise<Void> configureOpenApi() {
		SiteConfig siteConfig = siteContextEnUS.getSiteConfig();
		Promise<Void> promise = Promise.promise();
		String siteUrlBase = siteConfig.getSiteBaseUrl();

		OAuth2ClientOptions oauth2ClientOptions = new OAuth2ClientOptions();
		oauth2ClientOptions.setSite(siteConfig.getAuthUrl() + "/realms/" + siteConfig.getAuthRealm());
		oauth2ClientOptions.setClientID(siteConfig.getAuthResource());
		oauth2ClientOptions.setClientSecret(siteConfig.getAuthSecret());
		oauth2ClientOptions.setFlow(OAuth2FlowType.AUTH_CODE);
		JsonObject extraParams = new JsonObject();
		extraParams.put("scope", "openid DefaultAuthScope SiteAdminScope");
		oauth2ClientOptions.setExtraParameters(extraParams);

		OpenIDConnectAuth.discover(vertx, oauth2ClientOptions, a -> {
			if(a.succeeded()) {
				OAuth2Auth authProvider = a.result();
	
				OAuth2AuthHandler authHandler = OAuth2AuthHandler.create(authProvider, siteUrlBase + "/callback");
				authHandler.addAuthority("DefaultAuthScope");
				authHandler.addAuthority("SiteAdminScope");
				authHandler.addAuthority("openid");
				{
					Router tempRouter = Router.router(vertx);
					authHandler.setupCallback(tempRouter.get("/callback"));
				}
		
		//		ClusteredSessionStore sessionStore = ClusteredSessionStore.create(vertx);
				LocalSessionStore sessionStore = LocalSessionStore.create(vertx, "computate-scolaire-sessions");
				SessionHandler sessionHandler = SessionHandler.create(sessionStore);
				sessionHandler.setAuthProvider(authProvider);
				siteContextEnUS.setAuthProvider(authProvider);
		
				OpenAPI3RouterFactory.create(vertx, "openapi3-enUS.yaml", b -> {
					if (b.succeeded()) {
						OpenAPI3RouterFactory routerFactory = b.result();
						routerFactory.mountServicesFromExtensions();
						siteContextEnUS.setRouterFactory(routerFactory);
		
						routerFactory.addGlobalHandler(sessionHandler);
						routerFactory.addHandlerByOperationId("callback", ctx -> {
		
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
		
							authProvider.authenticate(config, res -> {
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
												ctx.addCookie(Cookie.cookie("sessionIdBefore", session.id()));
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
						routerFactory.addFailureHandlerByOperationId("callback", c -> {});
		
						routerFactory.addHandlerByOperationId("logout", rc -> {
							Session session = rc.session();
//							if (session != null) {
//								session.destroy();
//							}
							rc.clearUser();
							rc.reroute("/");
						});
						routerFactory.addFailureHandlerByOperationId("logout", c -> {});
		
						routerFactory.addHandlerByOperationId("photo", rc -> {
//							rc.response().setChunked(true);
							Boolean photo = false;
							for(FileUpload upload : rc.fileUploads()) {
								if("file".equals(upload.name())) {
									photo = true;
									String fileName = StringUtils.substringBeforeLast(Paths.get(upload.uploadedFileName()).getFileName().toString(), ".") + ".png";
									vertx.fileSystem().readFile(upload.uploadedFileName(), d -> {
										if (d.succeeded()) {
											try {
												Long pk = Long.parseLong(rc.request().params().get("pk"));
												String classSimpleName = rc.request().params().get("classSimpleName");
												Buffer buffer = d.result();
												byte[] bytes = buffer.getBytes();
												ByteArrayInputStream is = new ByteArrayInputStream(bytes);
							
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
									
												int height = maxHeight;
												int width = maxWidth;
									
												//Work out the resized width/height
												height = maxHeight;
												int wid = maxWidth;
												float sum = (float)img.getWidth() / (float)img.getHeight();
												width = Math.round(height * sum);
									
												if (width > wid) {
													//rezise again for the width this time
													height = Math.round(wid/sum);
													width = wid;
												}
									
												BufferedImage resizedImage = new BufferedImage(width, height, type);
												Graphics2D g = resizedImage.createGraphics();
												g.setComposite(AlphaComposite.Src);
									
												g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
												g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
												g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
									
												g.drawImage(img, 0, 0, width, height, null);
												g.dispose(); 
									
												ByteArrayOutputStream os = new ByteArrayOutputStream();
												ImageIO.write(resizedImage, "png", os);
												String str = "data:image/png;base64," + Base64.getEncoder().encodeToString(os.toByteArray());
												os.close();

												if("SchoolChild".equals(classSimpleName)) {
													SchoolChildEnUSApiServiceImpl service = new SchoolChildEnUSApiServiceImpl(siteContextEnUS);
													JsonObject json = new JsonObject();
													json.put("setPhoto", str);
													SiteRequestEnUS requeteSite = service.generateSiteRequestEnUSForSchoolChild(siteContextEnUS, null, json);

													ApiRequest apiRequest = new ApiRequest();
													apiRequest.setRows(1);
													apiRequest.setNumFound(1l);
													apiRequest.setNumPATCH(0L);
													apiRequest.initDeepApiRequest(requeteSite);
													requeteSite.setApiRequest_(apiRequest);
													requeteSite.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
									
													SearchList<SchoolChild> searchList = new SearchList<SchoolChild>();
													searchList.setStore(true);
													searchList.setQuery("*:*");
													searchList.setC(SchoolChild.class);
													searchList.addFilterQuery("pk_indexed_long:" + pk);
													searchList.initDeepForClass(requeteSite);
									
													if(searchList.size() == 1) {
														SchoolChild o = searchList.getList().stream().findFirst().orElse(null);
														service.patchSchoolChildFuture(o, false, e -> {
															if(e.succeeded()) {
																rc.response().end("{}");
															}
															else {
																LOGGER.error(configureOpenApiPhotoError, e.cause());
																rc.fail(500, e.cause());
															}
														});
													} else {
														rc.response().end("{}");
													}
												}

												if("SchoolMom".equals(classSimpleName)) {
													SchoolMomEnUSApiServiceImpl service = new SchoolMomEnUSApiServiceImpl(siteContextEnUS);
													JsonObject json = new JsonObject();
													json.put("setPhoto", str);
													SiteRequestEnUS requeteSite = service.generateSiteRequestEnUSForSchoolMom(siteContextEnUS, null, json);

													ApiRequest apiRequest = new ApiRequest();
													apiRequest.setRows(1);
													apiRequest.setNumFound(1l);
													apiRequest.setNumPATCH(0L);
													apiRequest.initDeepApiRequest(requeteSite);
													requeteSite.setApiRequest_(apiRequest);
													requeteSite.getVertx().eventBus().publish("websocketSchoolMom", JsonObject.mapFrom(apiRequest).toString());
									
													SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
													searchList.setStore(true);
													searchList.setQuery("*:*");
													searchList.setC(SchoolMom.class);
													searchList.addFilterQuery("pk_indexed_long:" + pk);
													searchList.initDeepForClass(requeteSite);
									
													if(searchList.size() == 1) {
														SchoolMom o = searchList.getList().stream().findFirst().orElse(null);
														service.patchSchoolMomFuture(o, false, e -> {
															if(e.succeeded()) {
																rc.response().end("{}");
															}
															else {
																LOGGER.error(configureOpenApiPhotoError, e.cause());
																rc.fail(500, e.cause());
															}
														});
													} else {
														rc.response().end("{}");
													}
												}

												if("SchoolDad".equals(classSimpleName)) {
													SchoolDadEnUSApiServiceImpl service = new SchoolDadEnUSApiServiceImpl(siteContextEnUS);
													JsonObject json = new JsonObject();
													json.put("setPhoto", str);
													SiteRequestEnUS requeteSite = service.generateSiteRequestEnUSForSchoolDad(siteContextEnUS, null, json);

													ApiRequest apiRequest = new ApiRequest();
													apiRequest.setRows(1);
													apiRequest.setNumFound(1l);
													apiRequest.setNumPATCH(0L);
													apiRequest.initDeepApiRequest(requeteSite);
													requeteSite.setApiRequest_(apiRequest);
													requeteSite.getVertx().eventBus().publish("websocketSchoolDad", JsonObject.mapFrom(apiRequest).toString());
									
													SearchList<SchoolDad> searchList = new SearchList<SchoolDad>();
													searchList.setStore(true);
													searchList.setQuery("*:*");
													searchList.setC(SchoolDad.class);
													searchList.addFilterQuery("pk_indexed_long:" + pk);
													searchList.initDeepForClass(requeteSite);
									
													if(searchList.size() == 1) {
														SchoolDad o = searchList.getList().stream().findFirst().orElse(null);
														service.patchSchoolDadFuture(o, false, e -> {
															if(e.succeeded()) {
																rc.response().end("{}");
															}
															else {
																LOGGER.error(configureOpenApiPhotoError, e.cause());
																rc.fail(500, e.cause());
															}
														});
													} else {
														rc.response().end("{}");
													}
												}

												if("SchoolGuardian".equals(classSimpleName)) {
													SchoolGuardianEnUSApiServiceImpl service = new SchoolGuardianEnUSApiServiceImpl(siteContextEnUS);
													JsonObject json = new JsonObject();
													json.put("setPhoto", str);
													SiteRequestEnUS requeteSite = service.generateSiteRequestEnUSForSchoolGuardian(siteContextEnUS, null, json);

													ApiRequest apiRequest = new ApiRequest();
													apiRequest.setRows(1);
													apiRequest.setNumFound(1l);
													apiRequest.setNumPATCH(0L);
													apiRequest.initDeepApiRequest(requeteSite);
													requeteSite.setApiRequest_(apiRequest);
													requeteSite.getVertx().eventBus().publish("websocketSchoolGuardian", JsonObject.mapFrom(apiRequest).toString());
									
													SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
													searchList.setStore(true);
													searchList.setQuery("*:*");
													searchList.setC(SchoolGuardian.class);
													searchList.addFilterQuery("pk_indexed_long:" + pk);
													searchList.initDeepForClass(requeteSite);
									
													if(searchList.size() == 1) {
														SchoolGuardian o = searchList.getList().stream().findFirst().orElse(null);
														service.patchSchoolGuardianFuture(o, false, e -> {
															if(e.succeeded()) {
																rc.response().end("{}");
															}
															else {
																LOGGER.error(configureOpenApiPhotoError, e.cause());
																rc.fail(500, e.cause());
															}
														});
													} else {
														rc.response().end("{}");
													}
												}

												if("SchoolEnrollment".equals(classSimpleName)) {
													SchoolEnrollmentEnUSApiServiceImpl service = new SchoolEnrollmentEnUSApiServiceImpl(siteContextEnUS);
													JsonObject json = new JsonObject();
													json.put("setPhoto", str);
													SiteRequestEnUS requeteSite = service.generateSiteRequestEnUSForSchoolEnrollment(siteContextEnUS, null, json);

													ApiRequest apiRequest = new ApiRequest();
													apiRequest.setRows(1);
													apiRequest.setNumFound(1l);
													apiRequest.setNumPATCH(0L);
													apiRequest.initDeepApiRequest(requeteSite);
													requeteSite.setApiRequest_(apiRequest);
													requeteSite.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
									
													SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
													searchList.setStore(true);
													searchList.setQuery("*:*");
													searchList.setC(SchoolEnrollment.class);
													searchList.addFilterQuery("pk_indexed_long:" + pk);
													searchList.initDeepForClass(requeteSite);
									
													if(searchList.size() == 1) {
														SchoolEnrollment o = searchList.getList().stream().findFirst().orElse(null);
														service.patchSchoolEnrollmentFuture(o, false, e -> {
															if(e.succeeded()) {
																rc.response().end("{}");
															}
															else {
																LOGGER.error(configureOpenApiPhotoError, e.cause());
																rc.fail(500, e.cause());
															}
														});
													} else {
														rc.response().end("{}");
													}
												}
											} catch(Exception ex) {
												LOGGER.error(configureOpenApiPhotoError, ex);
												rc.fail(500, ex);
											}
										}
									});
								}
								if(!photo)
									rc.response().end("{}");
								upload.fileName();
							}
						});
						routerFactory.addFailureHandlerByOperationId("photo", c -> {
							LOGGER.error("Upload failed. ");
						});
		
//						routerFactory.addSecurityHandler("openIdConnect", authHandler);
						routerFactory.addSecuritySchemaScopeValidator("openIdConnect", "DefaultAuthScope", authHandler);
						routerFactory.addSecuritySchemaScopeValidator("openIdConnect", "SiteAdminScope", authHandler);
						routerFactory.addSecuritySchemaScopeValidator("openIdConnect", "openid", authHandler);
						Router router = routerFactory.getRouter();
						siteContextEnUS.setRouter(router);
		
						LOGGER.info(configureOpenApiSuccess);
						promise.complete();
					} else {
						LOGGER.error(configureOpenApiError, b.cause());
						promise.fail(b.cause());
					}
				});
			} else {
				LOGGER.error(configureOpenApiError, a.cause());
				promise.fail(a.cause());
			}
		});
		return promise;
	}

	/**
	 * Configure a shared worker executor for running blocking tasks in the background. 
	 * Return a promise that configures the shared worker executor. 
	 **/
	private Promise<Void> configureSharedWorkerExecutor() {
		Promise<Void> promise = Promise.promise();

		WorkerExecutor workerExecutor = vertx.createSharedWorkerExecutor("WorkerExecutor");
		siteContextEnUS.setWorkerExecutor(workerExecutor);
		promise.complete();
		return promise;
	}

	/**
	 * Configure health checks for the status of the website and it's dependent services. 
	 * Return a promise that configures the health checks. 
	 **/
	private Promise<Void> configureHealthChecks() {
		Promise<Void> promise = Promise.promise();
		Router siteRouteur = siteContextEnUS.getRouter();
		HealthCheckHandler healthCheckHandler = HealthCheckHandler.create(vertx);

		healthCheckHandler.register("database", 2000, a -> {
			siteContextEnUS.getPgPool().preparedQuery("select current_timestamp" ).execute(selectCAsync -> {
				if(selectCAsync.succeeded()) {
					a.complete(Status.OK());
				} else {
					LOGGER.error(configureHealthChecksErrorDatabase, a.future().cause());
					promise.fail(a.future().cause());
				}
			});
		});
		healthCheckHandler.register("solr", 2000, a -> {
			SolrQuery query = new SolrQuery();
			query.setQuery("*:*");
			try {
				QueryResponse r = siteContextEnUS.getSolrClient().query(query);
				if(r.getResults().size() > 0)
					a.complete(Status.OK());
				else {
					LOGGER.error(configureHealthChecksEmptySolr, a.future().cause());
					promise.fail(a.future().cause());
				}
			} catch (SolrServerException | IOException e) {
				LOGGER.error(configureHealthChecksErrorSolr, a.future().cause());
				promise.fail(a.future().cause());
			}
		});
		siteRouteur.get("/health").handler(healthCheckHandler);
		promise.complete();
		return promise;
	}

	/**
	 * Configure websockets for realtime messages. 
	 **/
	private Promise<Void> configureWebsockets() {
		Promise<Void> promise = Promise.promise();
		Router siteRouter = siteContextEnUS.getRouter();
		SockJSBridgeOptions options = new SockJSBridgeOptions()
				.addOutboundPermitted(new PermittedOptions().setAddressRegex("websocket.*"));
		SockJSHandler sockJsHandler = SockJSHandler.create(vertx);
		sockJsHandler.bridge(options);
		siteRouter.route("/eventbus/*").handler(sockJsHandler);
		promise.complete();
		return promise;
	}

	/**
	 * Configure sending email. 
	 **/
	private Promise<Void> configureEmail() {
		SiteConfig siteConfig = siteContextEnUS.getSiteConfig();
		Promise<Void> promise = Promise.promise();
		MailConfig config = new MailConfig();
		config.setHostname(siteConfig.getEmailHost());
		config.setPort(siteConfig.getEmailPort());
		config.setSsl(siteConfig.getEmailSsl());
		config.setUsername(siteConfig.getEmailUsername());
		config.setPassword(siteConfig.getEmailPassword());
		MailClient mailClient = MailClient.createShared(vertx, config);
		siteContextEnUS.setMailClient(mailClient);
		promise.complete();
		return promise;
	}

	public void  errorAppVertx(SiteRequestEnUS siteRequest, AsyncResult<?> a) {
		Throwable e = Optional.ofNullable(a).map(b -> b.cause()).orElse(null);
		if(e != null)
			LOGGER.error(ExceptionUtils.getStackTrace(e));
		if(siteRequest != null) {
			Transaction tx = siteRequest.getTx();
			if(tx != null) {
				tx.rollback(b -> {
					if(b.succeeded()) {
						LOGGER.info("Rollback the SQL connection succeded. ");
						try {
							SqlConnection connexionSql = siteRequest.getSqlConnection();
				
							if(connexionSql == null) {
								LOGGER.info("Close the SQL connection succeded. ");
							} else {
								connexionSql.close();
								siteRequest.setSqlConnection(null);
								LOGGER.info("Close the SQL connection succeded. ");
							}
						} catch(Exception ex) {
							LOGGER.error(String.format("sqlFermerEcole a échoué. ", ex));
						}
					} else {
						LOGGER.error("Rollback the SQL connection failed. ", b.cause());
					}
				});
			}
		}
	}

	/**
	 * Start the Vert.x server. 
	 * Démarrer le serveur Vert.x. 
	 **/
	private Promise<Void> startServer() {
		SiteConfig siteConfig = siteContextEnUS.getSiteConfig();
		Promise<Void> promise = Promise.promise();

		ClusterEnUSGenApiService.registerService(siteContextEnUS, vertx);
		SchoolEnUSGenApiService.registerService(siteContextEnUS, vertx);
		SiteUserEnUSGenApiService.registerService(siteContextEnUS, vertx);
		SchoolYearEnUSGenApiService.registerService(siteContextEnUS, vertx);
		SchoolSeasonEnUSGenApiService.registerService(siteContextEnUS, vertx);
		SchoolSessionEnUSGenApiService.registerService(siteContextEnUS, vertx);
		SchoolAgeEnUSGenApiService.registerService(siteContextEnUS, vertx);
		SchoolBlockEnUSGenApiService.registerService(siteContextEnUS, vertx);
		SchoolEnrollmentEnUSGenApiService.registerService(siteContextEnUS, vertx);
		SchoolChildEnUSGenApiService.registerService(siteContextEnUS, vertx);
		SchoolMomEnUSGenApiService.registerService(siteContextEnUS, vertx);
		SchoolDadEnUSGenApiService.registerService(siteContextEnUS, vertx);
		SchoolGuardianEnUSGenApiService.registerService(siteContextEnUS, vertx);
		SchoolPaymentEnUSGenApiService.registerService(siteContextEnUS, vertx);
		PageDesignEnUSGenApiService.registerService(siteContextEnUS, vertx);
		HtmlPartEnUSGenApiService.registerService(siteContextEnUS, vertx);
		SchoolReceiptEnUSGenApiService.registerService(siteContextEnUS, vertx);

		Router siteRouter = siteContextEnUS.getRouter();

		StaticHandler staticHandler = StaticHandler.create().setCachingEnabled(false).setFilesReadOnly(true);
		if(siteConfig.getSiteHostName().contains("scolaire-dev.")) {
			staticHandler.setAllowRootFileSystemAccess(true);
			staticHandler.setWebRoot("/usr/local/src/computate-scolaire-static");
		}
		siteRouter.route("/static/*").handler(staticHandler);

		SimpleModule module = new SimpleModule();
		module.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
		module.addSerializer(LocalDate.class, new LocalDateSerializer());
		module.addSerializer(LocalTime.class, new LocalTimeSerializer());
		Json.mapper.registerModule(module);

		String siteHostName = siteConfig.getSiteHostName();
		String siteBaseUrl = siteConfig.getSiteBaseUrl();
		Integer sitePort = siteConfig.getSitePort();
		HttpServerOptions options = new HttpServerOptions();
		Boolean ssl = false;
		if(StringUtils.isNotBlank(siteConfig.getSslJksPath()) && StringUtils.startsWith(siteBaseUrl, "https://") && new File(siteConfig.getSslJksPath()).exists()) {
			options.setKeyStoreOptions(new JksOptions().setPath(siteConfig.getSslJksPath()).setPassword(siteConfig.getSslJksPassword()));
			ssl = true;
			options.setSsl(ssl);
			LOGGER.info(String.format(startServerSsl, siteConfig.getSslJksPath()));
		}
		options.setPort(sitePort);

		LOGGER.info(String.format(startServerBeforeServer, (ssl ? "https" : "http"), siteHostName, sitePort));
		vertx.createHttpServer(options).requestHandler(siteRouter).listen(ar -> {
			if (ar.succeeded()) {
				LOGGER.info(String.format(startServerSuccessServer, "*", sitePort));
				promise.complete();
			} else {
				LOGGER.error(startServerErrorServer, ar.cause());
				promise.fail(ar.cause());
			}
		});

		return promise;
	}

	/**
	 * This is called by Vert.x when the verticle instance is undeployed. 
	 * Setup the stopPromise to handle tearing down the server. 
	 **/
	@Override()
	public void  stop(Promise<Void> stopPromise) throws Exception, Exception {
		Promise<Void> promiseSteps = closeData();
		promiseSteps.future().setHandler(stopPromise);
	}

	/**
	 * Return a promise to close the database client connection. 
	 **/
	private Promise<Void> closeData() {
		Promise<Void> promise = Promise.promise();
		PgPool pgPool = siteContextEnUS.getPgPool();

		if(pgPool != null) {
			pgPool.close();
			LOGGER.info(closeDataSuccess);
			promise.complete();
		}
		return promise;
	}
}
