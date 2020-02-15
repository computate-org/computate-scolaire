package org.computate.scolaire.enUS.vertx;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.computate.scolaire.enUS.age.SchoolAgeEnUSGenApiService;
import org.computate.scolaire.enUS.year.SchoolYearEnUSGenApiService;
import org.computate.scolaire.enUS.block.SchoolBlockEnUSGenApiService;
import org.computate.scolaire.enUS.cluster.ClusterEnUSGenApiService;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.school.SchoolEnUSGenApiService;
import org.computate.scolaire.enUS.child.SchoolChildEnUSGenApiService;
import org.computate.scolaire.enUS.guardian.SchoolGuardianEnUSGenApiService;
import org.computate.scolaire.enUS.html.part.HtmlPartEnUSGenApiService;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollmentEnUSGenApiService;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollmentEnUSGenApiServiceImpl;
import org.computate.scolaire.frFR.inscription.InscriptionScolaireFrFRGenApiServiceVertxEBProxy;
import org.computate.scolaire.enUS.enrollment.design.EnrollmentDesignEnUSGenApiService;
import org.computate.scolaire.enUS.java.LocalDateSerializer;
import org.computate.scolaire.enUS.java.LocalTimeSerializer;
import org.computate.scolaire.enUS.java.ZonedDateTimeSerializer;
import org.computate.scolaire.enUS.mom.SchoolMomEnUSGenApiService;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import org.computate.scolaire.enUS.payment.SchoolPaymentEnUSGenApiService;
import org.computate.scolaire.enUS.payment.SchoolPaymentEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.dad.SchoolDadEnUSGenApiService;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.season.SchoolSeasonEnUSGenApiService;
import org.computate.scolaire.enUS.session.SchoolSessionEnUSGenApiService;
import org.computate.scolaire.enUS.user.SiteUserEnUSGenApiService;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.Json;
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
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.healthchecks.HealthCheckHandler;
import io.vertx.ext.healthchecks.Status;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailConfig;
import io.vertx.ext.mail.MailMessage;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.api.OperationResponse;
import io.vertx.ext.web.api.contract.openapi3.impl.AppOpenAPI3RouterFactory;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import net.authorize.Environment;
import net.authorize.api.contract.v1.ArrayOfBatchDetailsType;
import net.authorize.api.contract.v1.ArrayOfTransactionSummaryType;
import net.authorize.api.contract.v1.BatchDetailsType;
import net.authorize.api.contract.v1.CustomerProfileIdType;
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
 *	A Java class to start the Vert.x application as a main method. 
 **/
public class AppVertx extends AppVertxGen<AbstractVerticle> {

	/**	
	 *	A SQL query for creating a database table "c" to store any type of object in the application. 
	 **/
	public static final String SQL_createTableC = "create table if not exists c(pk bigserial primary key, current boolean, canonical_name text, created timestamp with time zone default now(), modified timestamp with time zone default now(), user_id text); ";

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
	public static final String SQL_createTableD = "create table if not exists d(pk bigserial primary key, path text, value text, current boolean, created timestamp with time zone default now(), modified timestamp with time zone default now(), pk_c bigint, foreign key(pk_c) references c(pk)); ";

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
	 *	Setup the startPromise to handle the configuration steps and starting the server. 
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
									configureAuthorizeNet().future().compose(h -> 
										startServer().future()
									)
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
	 *	Configure shared database connections across the cluster for massive scaling of the application. 
	 *	Return a promise that configures a shared database client connection. 
	 *	Load the database configuration into a shared io.vertx.ext.jdbc.JDBCClient for a scalable, clustered datasource connection pool. 
	 *	Initialize the database tables if not already created for the first time. 
	 **/
	private Promise<Void> configureData() {
		SiteConfig siteConfig = siteContextEnUS.getSiteConfig();
		Promise<Void> promise = Promise.promise();

		JsonObject jdbcConfig = new JsonObject();
		if (StringUtils.isNotEmpty(siteConfig.getJdbcUrl()))
			jdbcConfig.put("url", siteConfig.getJdbcUrl());
		if (StringUtils.isNotEmpty(siteConfig.getJdbcDriverClass()))
			jdbcConfig.put("driver_class", siteConfig.getJdbcDriverClass());
		if (StringUtils.isNotEmpty(siteConfig.getJdbcUsername()))
			jdbcConfig.put("user", siteConfig.getJdbcUsername());
		if (StringUtils.isNotEmpty(siteConfig.getJdbcPassword()))
			jdbcConfig.put("password", siteConfig.getJdbcPassword());
		if (siteConfig.getJdbcMaxPoolSize() != null)
			jdbcConfig.put("max_pool_size", siteConfig.getJdbcMaxPoolSize());
		if (siteConfig.getJdbcInitialPoolSize() != null)
			jdbcConfig.put("initial_pool_size", siteConfig.getJdbcInitialPoolSize());
		if (siteConfig.getJdbcMinPoolSize() != null)
			jdbcConfig.put("min_pool_size", siteConfig.getJdbcMinPoolSize());
		if (siteConfig.getJdbcMaxStatements() != null)
			jdbcConfig.put("max_statements", siteConfig.getJdbcMaxStatements());
		if (siteConfig.getJdbcMaxStatementsPerConnection() != null)
			jdbcConfig.put("max_statements_per_connection", siteConfig.getJdbcMaxStatementsPerConnection());
		if (siteConfig.getJdbcMaxIdleTime() != null)
			jdbcConfig.put("max_idle_time", siteConfig.getJdbcMaxIdleTime());
		jdbcConfig.put("castUUID", false);
		jdbcConfig.put("castDateTime", false);
		jdbcConfig.put("castTime", false);
		jdbcConfig.put("castDate", false);
		jdbcClient = JDBCClient.createShared(vertx, jdbcConfig);

		siteContextEnUS.setSqlClient(jdbcClient);

		jdbcClient.getConnection(a -> {
			if (a.failed()) {
				LOGGER.error(configureDataConnectionError, a.cause());
				promise.fail(a.cause());
			} else {
				LOGGER.info(configureDataConnectionSuccess);
				SQLConnection connection = a.result();
				connection.execute(SQL_initAll, create -> {
					connection.close();
					if (create.failed()) {
						LOGGER.error(configureDataInitError, create.cause());
						promise.fail(create.cause());
					} else {
						LOGGER.info(configureDataInitSuccess);
						promise.complete();
					}
				});
			}
		});

		return promise;
	}

	/**	
	 *	Configure shared data across the cluster for massive scaling of the application. 
	 *	Return a promise that configures a shared cluster data. 
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
	 *	Configure the connection to the auth server and setup the routes based on the OpenAPI definition. 
	 *	Setup a callback route when returning from the auth server after successful authentication. 
	 *	Setup a logout route for logging out completely of the application. 
	 *	Return a promise that configures the authentication server and OpenAPI. 
	 **/
	private Promise<Void> configureOpenApi() {
		SiteConfig siteConfig = siteContextEnUS.getSiteConfig();
		Promise<Void> promise = Promise.promise();
		Router router = Router.router(vertx);

		AppOpenAPI3RouterFactory.create(vertx, router, "openapi3-enUS.yaml", ar -> {
			if (ar.succeeded()) {
				AppOpenAPI3RouterFactory routerFactory = ar.result();
				routerFactory.mountServicesFromExtensions();
				siteContextEnUS.setRouterFactory(routerFactory);

				JsonObject keycloakJson = new JsonObject() {
					{
						put("realm", siteConfig.getAuthRealm());
						put("resource", siteConfig.getAuthResource());
						put("auth-server-url", siteConfig.getAuthUrl());
						put("ssl-required", siteConfig.getAuthSslRequired());
						put("use-resource-role-mappings", false);
						put("bearer-only", false);
						put("enable-basic-auth", false);
						put("expose-token", true);
						put("credentials", new JsonObject().put("secret", siteConfig.getAuthSecret()));
						put("connection-pool-size", 20);
						put("disable-trust-manager", false);
						put("allow-any-hostname", false);
						put("policy-enforcer", new JsonObject());
						put("redirect-rewrite-rules", new JsonObject().put("^(.*)$", "$1"));
					}
				};

				String siteHostName = siteConfig.getSiteHostName();
				Integer sitePort = siteConfig.getSitePort();
				String siteUrlBase = siteConfig.getSiteBaseUrl();
				OAuth2Auth authFournisseur = KeycloakAuth.create(vertx, OAuth2FlowType.AUTH_CODE, keycloakJson);

				router.route().handler(CookieHandler.create());
				LocalSessionStore sessionStore = LocalSessionStore.create(vertx);
//				ClusteredSessionStore sessionStore = ClusteredSessionStore.create(vertx, "computate-scolaire-enUS-session");
				SessionHandler sessionHandler = SessionHandler.create(sessionStore);
				sessionHandler.setAuthProvider(authFournisseur);
				router.route().handler(sessionHandler);

//				router.route().handler(UserSessionHandler.create(authFournisseur));

				OAuth2AuthHandler gestionnaireAuth = OAuth2AuthHandler.create(authFournisseur, siteUrlBase + "/callback");

				gestionnaireAuth.setupCallback(router.get("/callback"));

				router.get("/logout").handler(rc -> {
					Session session = rc.session();
					if (session != null) {
						session.destroy();
					}
					rc.clearUser();
					rc.reroute("/school");
				});

				routerFactory.addSecurityHandler("openIdConnect", gestionnaireAuth);

				routerFactory.initRouter();

				LOGGER.info(configureOpenApiSuccess);
				promise.complete();
			} else {
				LOGGER.error(configureOpenApiError, ar.cause());
				promise.fail(ar.cause());
			}
		});
		return promise;
	}

	/**	
	 *	Configure a shared worker executor for running blocking tasks in the background. 
	 *	Return a promise that configures the shared worker executor. 
	 **/
	private Promise<Void> configureSharedWorkerExecutor() {
		Promise<Void> promise = Promise.promise();

		WorkerExecutor workerExecutor = vertx.createSharedWorkerExecutor("WorkerExecutor");
		siteContextEnUS.setWorkerExecutor(workerExecutor);
		promise.complete();
		return promise;
	}

	/**	
	 *	Configure health checks for the status of the website and it's dependent services. 
	 *	Return a promise that configures the health checks. 
	 **/
	private Promise<Void> configureHealthChecks() {
		Promise<Void> promise = Promise.promise();
		Router siteRouteur = siteContextEnUS.getRouterFactory().getRouter();
		HealthCheckHandler healthCheckHandler = HealthCheckHandler.create(vertx);

		healthCheckHandler.register("database", 2000, a -> {
			siteContextEnUS.getSqlClient().queryWithParams("select current_timestamp"
					, new JsonArray(Arrays.asList())
					, selectCAsync
			-> {
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
	 *	Configure websockets for realtime messages. 
	 **/
	private Promise<Void> configureWebsockets() {
		Promise<Void> promise = Promise.promise();
		Router siteRouter = siteContextEnUS.getRouterFactory().getRouter();
		BridgeOptions options = new BridgeOptions()
				.addOutboundPermitted(new PermittedOptions().setAddressRegex("websocket.*"));
		SockJSHandler sockJsHandler = SockJSHandler.create(vertx);
		sockJsHandler.bridge(options);
		siteRouter.route("/eventbus/*").handler(sockJsHandler);
		promise.complete();
		return promise;
	}

	/**	
	 *	Configure sending email. 
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

	/**	
	 *	Configure payments with Authorize.net. 
	 **/
	private Promise<Void> configureAuthorizeNet() {
		SiteConfig siteConfig = siteContextEnUS.getSiteConfig();
		Promise<Void> promise = Promise.promise();

		vertx.setPeriodic(1000 * 60 * 60, a -> {
			WorkerExecutor executeurTravailleur = siteContextEnUS.getWorkerExecutor();
			executeurTravailleur.executeBlocking(
				blockingCodeHandler -> {
					LOGGER.info("Start to populate the new transactions. ");
					ZonedDateTime start = ZonedDateTime.now();
					SiteRequestEnUS siteRequest = new SiteRequestEnUS();
					siteRequest.setVertx(vertx);
					siteRequest.setSiteContext_(siteContextEnUS);
					siteRequest.setSiteConfig_(siteContextEnUS.getSiteConfig());
					siteRequest.initDeepSiteRequestEnUS(siteRequest);
					siteRequest.setJsonObject(new JsonObject());
		
					sqlInit(siteRequest, b -> {
						if(b.succeeded()) {
							LOGGER.info("Init SQL succeeded. ");
							try {
								SchoolPaymentEnUSGenApiServiceImpl paymentService = new SchoolPaymentEnUSGenApiServiceImpl(siteContextEnUS);
								SchoolEnrollmentEnUSGenApiServiceImpl enrollmentService = new SchoolEnrollmentEnUSGenApiServiceImpl(siteContextEnUS);
							
								ApiOperationBase.setEnvironment(Environment.PRODUCTION);
				
								MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
								String authorizeApiLoginId = siteConfig.getAuthorizeApiLoginId();
								String authorizeTransactionKey = siteConfig.getAuthorizeTransactionKey();
								merchantAuthenticationType.setName(authorizeApiLoginId);
								merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
								ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);
								DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
				
								GetSettledBatchListRequest batchRequest = new GetSettledBatchListRequest();
								batchRequest.setMerchantAuthentication(merchantAuthenticationType);
								batchRequest.setFirstSettlementDate(datatypeFactory.newXMLGregorianCalendar(GregorianCalendar.from(LocalDate.now()
										.minusDays(7).atStartOfDay(ZoneId.of("America/Denver")))));
								batchRequest.setLastSettlementDate(datatypeFactory.newXMLGregorianCalendar(GregorianCalendar.from(LocalDate.now()
										.plusDays(1).atStartOfDay(ZoneId.of("America/Denver")))));
				
								GetSettledBatchListController batchController = new GetSettledBatchListController(batchRequest);
								GetSettledBatchListController.setEnvironment(Environment.PRODUCTION);
								batchController.execute();
								if(batchController.getErrorResponse() != null)
									throw new RuntimeException(batchController.getResults().toString());
				
								GetSettledBatchListResponse batchResponse = batchController.getApiResponse();
				
								List<Future> futuresBatch = new ArrayList<>();
								List<BatchDetailsType> batches = Optional.ofNullable(batchResponse.getBatchList()).map(ArrayOfBatchDetailsType::getBatch).orElse(Arrays.asList());
								LOGGER.info(String.format("There are %s batches to load. ", batches.size()));
								for(BatchDetailsType batch : batches) {
									futuresBatch.add(
										futureAuthorizeNetBatch(merchantAuthenticationType, batchController, batch, siteRequest, c -> {
											if(c.succeeded()) {
												LOGGER.info(String.format("batch %s loaded. ", batch.getBatchId()));
											} else {
												LOGGER.error(String.format("batch %s failed. ", batch.getBatchId()), c.cause());
												blockingCodeHandler.handle(Future.failedFuture(c.cause()));
											}
										})
									);
								}
								CompositeFuture.all(futuresBatch).setHandler( c -> {
									if(c.succeeded()) {
										try {
											SearchList<SchoolPayment> listeRecherche = new SearchList<SchoolPayment>();
											listeRecherche.setPopulate(true);
											listeRecherche.setQuery("*:*");
											listeRecherche.setC(SchoolPayment.class);
											listeRecherche.addFilterQuery("created_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(start.toInstant(), ZoneId.of("UTC"))) + " TO *]");
											listeRecherche.add("json.facet", "{enrollmentKeys:{terms:{field:enrollmentKeys_indexed_longs, limit:1000}}}");
											listeRecherche.setRows(1000);
											listeRecherche.initDeepSearchList(siteRequest);
											SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listeRecherche.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
											List<SimpleOrderedMap> enrollmentKeys = (List<SimpleOrderedMap>)Optional.ofNullable((SimpleOrderedMap)facets.get("enrollmentKeys")).map(m -> ((List<List<SimpleOrderedMap>>)m.getAll("bucket"))).orElse(Arrays.asList()).stream().findFirst().orElse(new ArrayList<SimpleOrderedMap>());
//											SimpleOrderedMap enrollmentKeysMap = (SimpleOrderedMap)Optional.ofNullable(facets.get("enrollmentKeys")).orElse(new SimpleOrderedMap());
//											List<?> enrollmentKeysList = (List<SimpleOrderedMap>)Optional.ofNullable(enrollmentKeysMap.getAll("buckets")).orElse(Arrays.asList());
//											List<SimpleOrderedMap> enrollmentKeys = (List<SimpleOrderedMap>)enrollmentKeysList.get(0);
			
											List<Future> futures = new ArrayList<>();
											LOGGER.info(String.format("There are %s enrollments to reload. ", enrollmentKeys.size()));
											for(SimpleOrderedMap enrollmentKeyMap : enrollmentKeys) {
												Long enrollmentKey  = Long.parseLong(enrollmentKeyMap.get("val").toString());
												SchoolEnrollment schoolEnrollment = new SchoolEnrollment();
												schoolEnrollment.setPk(enrollmentKey);
												schoolEnrollment.setSiteRequest_(siteRequest);
												futures.add(
													enrollmentService.futurePATCHSchoolEnrollment(schoolEnrollment, d -> {
														if(d.succeeded()) {
															LOGGER.info(String.format("enrollment %s refreshed. ", enrollmentKey));
														} else {
															LOGGER.error(String.format("enrollment %s failed. ", enrollmentKey), d.cause());
															blockingCodeHandler.handle(Future.failedFuture(d.cause()));
														}
													})
												);
											}
											CompositeFuture.all(futures).setHandler(d -> {
												if(d.succeeded()) {
													List<Future> futuresPayment = new ArrayList<>();
													LOGGER.info(String.format("There are %s payments to reload. ", enrollmentKeys.size()));
													for(SchoolPayment payment : listeRecherche.getList()) {
														futuresPayment.add(
															paymentService.futurePATCHSchoolPayment(payment, e -> {
																if(e.succeeded()) {
																	LOGGER.info(String.format("payment %s refreshed. ", payment.getPk()));
																} else {
																	LOGGER.error(String.format("payement %s failed. ", payment.getPk()), e.cause());
																	blockingCodeHandler.handle(Future.failedFuture(e.cause()));
																}
															})
														);
													}
													CompositeFuture.all(futuresPayment).setHandler(e -> {
														if(e.succeeded()) {
															LOGGER.info("Refreshing the enrollments has succeeded. ");
															SQLConnection connexionSql = siteRequest.getSqlConnection();
															connexionSql.commit(f -> {
																if(f.succeeded()) {
																	LOGGER.info("Commit the SQL connection succeeded. ");
																	connexionSql.close(g -> {
																		if(f.succeeded()) {
																			LOGGER.info("Close the SQL connection has succeeded. ");
																			LOGGER.info("Finish populating the new transactions. ");
																			blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																		} else {
																			LOGGER.error("Close the SQL connection has failed. ", g.cause());
																			errorAppVertx(siteRequest, g);
																		}
																	});
																} else {
																	LOGGER.error("Commit the SQL connection has failed. ", f.cause());
																	errorAppVertx(siteRequest, f);
																}
															});
														} else {
															LOGGER.error("Commit the SQL connection has failed. ", e.cause());
															errorAppVertx(siteRequest, e);
														}
													});
												} else {
													LOGGER.error("Refresh the enrollments failed. ", d.cause());
													errorAppVertx(siteRequest, d);
												}
											});
										} catch (Exception e) {
											LOGGER.error(String.format("Authorize.net payments have failed. n%s", ExceptionUtils.getStackTrace(e)), ExceptionUtils.getStackTrace(e));
											errorAppVertx(siteRequest, c);
										}
									} else {
										LOGGER.error(c.cause());
										errorAppVertx(siteRequest, c);
									}
								});
							} catch (Exception e) {
								LOGGER.error(String.format("Authorize.net payments have failed. n%s", ExceptionUtils.getStackTrace(e)), ExceptionUtils.getStackTrace(e));
								errorAppVertx(siteRequest, b);
							}
						} else {
							LOGGER.info("Init SQL failed. ");
							errorAppVertx(siteRequest, b);
						}
					});
				}, resultHandler -> {
					if(resultHandler.succeeded()) {
						LOGGER.info("Authorize.net WorkerExecutor.executeBlocking succeeded. ");
					} else {
						LOGGER.error("Authorize.net WorkerExecutor.executeBlocking failed. ", resultHandler.cause());
					}
				}
			);
		});

		promise.complete();
		return promise;
	}

	public void  errorAppVertx(SiteRequestEnUS siteRequest, AsyncResult<?> a) {
		Throwable e = a.cause();
		if(e != null)
			LOGGER.error(ExceptionUtils.getStackTrace(e));
		if(siteRequest != null) {
			SQLConnection connexionSql = siteRequest.getSqlConnection();
			if(connexionSql != null) {
				connexionSql.rollback(b -> {
					if(b.succeeded()) {
						LOGGER.info("Rollback the SQL connection succeded. ");
						connexionSql.close(c -> {
							if(c.succeeded()) {
								LOGGER.info("Close the SQL connection succeded. ");
							} else {
								LOGGER.error("Close the SQL connection failed. ", c.cause());
							}
						});
					} else {
						LOGGER.error("Rollback the SQL connection failed. ", b.cause());
					}
				});
			}
		}
	}

	public Future<Void> futureAuthorizeNetBatch(MerchantAuthenticationType merchantAuthenticationType, GetSettledBatchListController batchController, BatchDetailsType batch, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> a) {
		Promise<Void> promise = Promise.promise();
		try {
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
			GetTransactionListController.setEnvironment(Environment.PRODUCTION);
			controller.execute();
			if(controller.getErrorResponse() != null)
				throw new RuntimeException(batchController.getResults().toString());

			SchoolPaymentEnUSGenApiServiceImpl paymentService = new SchoolPaymentEnUSGenApiServiceImpl(siteContextEnUS);

			List<Future> futures = new ArrayList<>();

			GetTransactionListResponse getResponse = controller.getApiResponse();
			if (getResponse != null) {

				if (getResponse.getMessages().getResultCode() == MessageTypeEnum.OK) {
					List<TransactionSummaryType> transactions = Optional.ofNullable(getResponse).map(GetTransactionListResponse::getTransactions).map(ArrayOfTransactionSummaryType::getTransaction).orElse(Arrays.asList());
					LOGGER.info(String.format("There are %s transactions in batch %s to load. ", transactions.size(), batch.getBatchId()));
					for(TransactionSummaryType transaction : transactions) {
						futures.add(
							futureAuthorizeNetPaiement(merchantAuthenticationType, batchController, paymentService, batch, siteRequest, transaction, b -> {
								if(b.succeeded()) {
									LOGGER.info(String.format("transaction %s loaded. ", transaction.getTransId()));
								} else {
									LOGGER.error(String.format("payment future for transaction %s failed. ", transaction.getTransId()), b.cause());
								}
							})
						);
					}
					CompositeFuture.all(futures).setHandler(b -> {
						if(b.succeeded()) {
							promise.complete();
							LOGGER.info(String.format("transactions for batch %s loaded. ", batch.getBatchId()));
						} else {
							LOGGER.error(String.format("transactions for batch %s failed. ", batch.getBatchId()));
							promise.fail(b.cause());
						}
					});
				}
			}
			return promise.future();
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public Future<Void> futureAuthorizeNetPaiement(MerchantAuthenticationType merchantAuthenticationType, GetSettledBatchListController batchController, SchoolPaymentEnUSGenApiServiceImpl paymentService, BatchDetailsType batch, SiteRequestEnUS siteRequest, TransactionSummaryType transaction, Handler<AsyncResult<OperationResponse>> a) {
		Promise<Void> promise = Promise.promise();
		try {

			XMLGregorianCalendar submitTimeLocal = transaction.getSubmitTimeLocal();
			SchoolPayment payment = new SchoolPayment();
			payment.setSiteRequest_(siteRequest);
			payment.setPaymentAmount(transaction.getSettleAmount());
			payment.setPaymentDate(submitTimeLocal.toGregorianCalendar().getTime());
			payment.setPaymentSystem(true);
			payment.setTransactionId(transaction.getTransId());
			payment.setCustomerProfileId(Optional.ofNullable(transaction.getProfile()).map(CustomerProfileIdType::getCustomerProfileId).orElse(null));
			payment.setTransactionStatus(transaction.getTransactionStatus());
			payment.setPaymentBy(String.format("%s %s", transaction.getFirstName(), transaction.getLastName()).trim());

			if(transaction.getTransId() != null) {
				SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
				searchList.setPopulate(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolPayment.class);
				searchList.addFilterQuery("transactionId_indexed_string:" + ClientUtils.escapeQueryChars(transaction.getTransId()));
				searchList.initDeepSearchList(siteRequest);

				if(searchList.size() == 0) {
					String firstName = StringUtils.substringBefore(transaction.getFirstName(), " ");
					String lastName = transaction.getLastName();
					SearchList<SchoolEnrollment> searchListEnrollment = new SearchList<SchoolEnrollment>();
					searchListEnrollment.setPopulate(true);
					searchListEnrollment.setQuery("*:*");
					searchListEnrollment.setC(SchoolEnrollment.class);
					searchListEnrollment.addFilterQuery(
							"(childFirstName_indexed_string:" + ClientUtils.escapeQueryChars(firstName) 
							+ " OR childFirstNamePreferred_indexed_string:" + ClientUtils.escapeQueryChars(firstName) 
							+ " OR momFirstName_indexed_string:" + ClientUtils.escapeQueryChars(firstName) 
							+ " OR momFirstNamePreferred_indexed_string:" + ClientUtils.escapeQueryChars(firstName) 
							+ " OR dadFirstName_indexed_string:" + ClientUtils.escapeQueryChars(firstName) 
							+ " OR dadFirstNamePreferred_indexed_string:" + ClientUtils.escapeQueryChars(firstName) 
							+ ")");
					searchListEnrollment.addFilterQuery("childFamilyName_indexed_string:" + ClientUtils.escapeQueryChars(lastName));
					searchListEnrollment.initDeepSearchList(siteRequest);

					if(searchListEnrollment.getList().size() == 1) {
						Long enrollmentKey = searchListEnrollment.getList().get(0).getPk();
						payment.addEnrollmentKeys(enrollmentKey);
					}
					paymentService.postSchoolPayment(JsonObject.mapFrom(payment), null, c -> {
						if(c.succeeded()) {
							promise.complete();
							LOGGER.info(String.format("payment %s created for transaction %s. ", c.result().getPayload().toJsonObject().getString("pk"), transaction.getTransId()));
						} else {
							LOGGER.error("creating payment %s failed for transaction %s. ", c.cause());
							promise.fail(c.cause());
						}
					});
				}
				else {
					promise.complete();
				}
			}

			return promise.future();
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public void  sqlInit(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SQLClient sqlClient = siteRequest.getSiteContext_().getSqlClient();

			if(sqlClient == null) {
				eventHandler.handle(Future.succeededFuture());
			} else {
				sqlClient.getConnection(sqlAsync -> {
					if(sqlAsync.succeeded()) {
						SQLConnection sqlConnection = sqlAsync.result();
						sqlConnection.setAutoCommit(false, a -> {
							if(a.succeeded()) {
								siteRequest.setSqlConnection(sqlConnection);
								eventHandler.handle(Future.succeededFuture());
							} else {
								eventHandler.handle(Future.failedFuture(a.cause()));
							}
						});
					} else {
						eventHandler.handle(Future.failedFuture(new Exception(sqlAsync.cause())));
					}
				});
			}
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	/**	
	 *	Start the Vert.x server. 
	 *	Démarrer le serveur Vert.x. 
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
		EnrollmentDesignEnUSGenApiService.registerService(siteContextEnUS, vertx);
		HtmlPartEnUSGenApiService.registerService(siteContextEnUS, vertx);

		Router siteRouter = siteContextEnUS.getRouterFactory().getRouter();

		StaticHandler staticHandler = StaticHandler.create().setCachingEnabled(false).setFilesReadOnly(true);
		if("scolaire-dev.computate.org".equals(siteConfig.getSiteHostName())) {
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
		Integer sitePort = siteConfig.getSitePort();
		HttpServerOptions options = new HttpServerOptions();
		if(new File(siteConfig.getSslJksPath()).exists()) {
			options.setKeyStoreOptions(new JksOptions().setPath(siteConfig.getSslJksPath()).setPassword(siteConfig.getSslJksPassword()));
			options.setSsl(true);
		}
		options.setPort(sitePort);

		LOGGER.info(String.format(startServerBeforeServer, "https", siteHostName, sitePort));
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
	 *	This is called by Vert.x when the verticle instance is undeployed. 
	 *	Setup the stopPromise to handle tearing down the server. 
	 **/
	@Override()
	public void  stop(Promise<Void> stopPromise) throws Exception, Exception {
		Promise<Void> promiseSteps = closeData();
		promiseSteps.future().setHandler(stopPromise);
	}

	/**	
	 *	Return a promise to close the database client connection. 
	 **/
	private Promise<Void> closeData() {
		Promise<Void> promise = Promise.promise();
		SQLClient clientSql = siteContextEnUS.getSqlClient();

		if(clientSql != null) {
			clientSql.close(a -> {
				if (a.failed()) {
					LOGGER.error(closeDataError, a.cause());
					promise.fail(a.cause());
				} else {
					LOGGER.error(closeDataSuccess, a.cause());
					promise.complete();
				}
			});
		}
		return promise;
	}
}
