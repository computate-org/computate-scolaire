package org.computate.scolaire.enUS.vertx;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Optional;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
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
import org.computate.scolaire.enUS.enrollment.SchoolEnrollmentEnUSGenApiService;
import org.computate.scolaire.enUS.enrollment.design.EnrollmentDesignEnUSGenApiService;
import org.computate.scolaire.enUS.java.LocalDateSerializer;
import org.computate.scolaire.enUS.java.LocalTimeSerializer;
import org.computate.scolaire.enUS.java.ZonedDateTimeSerializer;
import org.computate.scolaire.enUS.mom.SchoolMomEnUSGenApiService;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import org.computate.scolaire.enUS.payment.SchoolPaymentEnUSGenApiService;
import org.computate.scolaire.enUS.dad.SchoolDadEnUSGenApiService;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.season.SchoolSeasonEnUSGenApiService;
import org.computate.scolaire.enUS.session.SchoolSessionEnUSGenApiService;
import org.computate.scolaire.enUS.user.SiteUserEnUSGenApiService;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.WorkerExecutor;
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
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.api.contract.openapi3.impl.AppOpenAPI3RouterFactory;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import net.authorize.Environment;
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

		vertx.setPeriodic(1000 * 60 * 60 * 24, a -> {
			WorkerExecutor executeurTravailleur = siteContextEnUS.getWorkerExecutor();
			executeurTravailleur.executeBlocking(
				blockingCodeHandler -> {
					LOGGER.info("Start to populate the new transactions. ");
					try {
						SiteRequestEnUS siteRequest = new SiteRequestEnUS();
						siteRequest.setVertx(vertx);
						siteRequest.setSiteContext_(siteContextEnUS);
						siteRequest.setSiteConfig_(siteContextEnUS.getSiteConfig());
						siteRequest.initDeepSiteRequestEnUS(siteRequest);
		
						SchoolPaymentEnUSGenApiService service = new org.computate.scolaire.enUS.payment.SchoolPaymentEnUSGenApiServiceImpl(siteContextEnUS);
					
						ApiOperationBase.setEnvironment(Environment.PRODUCTION);
		
						MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
						String authorizeApiLoginId = siteConfig.getAuthorizeApiLoginId();
						String authorizeTransactionKey = siteConfig.getAuthorizeTransactionKey();
						merchantAuthenticationType.setName(authorizeApiLoginId);
						merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
						ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);
		
						Paging paging = new Paging();
						paging.setLimit(100);
						paging.setOffset(1);
						DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
		
						GetSettledBatchListRequest batchRequest = new GetSettledBatchListRequest();
						batchRequest.setMerchantAuthentication(merchantAuthenticationType);
						batchRequest.setFirstSettlementDate(datatypeFactory.newXMLGregorianCalendar(GregorianCalendar.from(LocalDate.now().minusDays(7).atStartOfDay(ZoneId.of("America/Denver")))));
						batchRequest.setLastSettlementDate(datatypeFactory.newXMLGregorianCalendar(GregorianCalendar.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.of("America/Denver")))));
		
						GetSettledBatchListController batchController = new GetSettledBatchListController(batchRequest);
						GetSettledBatchListController.setEnvironment(Environment.PRODUCTION);
						batchController.execute();
		
						GetSettledBatchListResponse batchResponse = batchController.getApiResponse();
		
						for(BatchDetailsType batch : batchResponse.getBatchList().getBatch()) {
							
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
			
							GetTransactionListResponse getResponse = controller.getApiResponse();
							if (getResponse != null) {
			
								if (getResponse.getMessages().getResultCode() == MessageTypeEnum.OK) {
									for(TransactionSummaryType transaction : Optional.ofNullable(getResponse).map(GetTransactionListResponse::getTransactions).map(ArrayOfTransactionSummaryType::getTransaction).orElse(Arrays.asList())) {
			
										transaction.getProfile().getCustomerProfileId();
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
											SearchList<SchoolPayment> listeRecherche = new SearchList<SchoolPayment>();
											listeRecherche.setPopulate(true);
											listeRecherche.setQuery("*:*");
											listeRecherche.setC(SchoolPayment.class);
											listeRecherche.addFilterQuery("transactionId_indexed_string:" + ClientUtils.escapeQueryChars(transaction.getTransId()));
											listeRecherche.initDeepSearchList(siteRequest);
			
											if(listeRecherche.size() == 0) {
												service.postSchoolPayment(JsonObject.mapFrom(payment), null, handler -> {
													if(handler.succeeded()) {
														LOGGER.info("payment créé. ");
													} else {
														LOGGER.error(handler.cause());
													}
												});
											}
										}
										break;
									}
								}
							}
							break;
						}
					} catch (Exception e) {
						ExceptionUtils.rethrow(e);
					}
					LOGGER.info("Finish populating the new transactions. ");
				}, resultHandler -> {
				}
			);
		});

		promise.complete();
		return promise;
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
