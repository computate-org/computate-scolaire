package org.computate.scolaire.enUS.vertx;

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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.config.ApiCounter;
import org.computate.scolaire.enUS.config.ConfigKeys;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollmentEnUSApiServiceImpl;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import org.computate.scolaire.enUS.payment.SchoolPaymentEnUSApiServiceImpl;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.computate.scolaire.enUS.search.SearchList;

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
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailConfig;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.PreparedStatement;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.RowStream;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Transaction;
import io.vertx.sqlclient.Tuple;

/**
 * A Java class to start the Vert.x application as a main method. 
 * CanonicalName: org.computate.scolaire.frFR.vertx.OuvrierVertx
 **/
public class WorkerVertx extends WorkerVertxGen<AbstractVerticle> {
	protected static final Logger LOG = LoggerFactory.getLogger(WorkerVertx.class);

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
	}

	public static void  run() {
		Class<?> c = WorkerVertx.class;
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
				configureSharedWorkerExecutor().future().compose(c -> 
					configureEmail().future().compose(d -> 
						configureAuthorizeNetCharges().future().compose(e -> 
							syncDbToSolr()
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
		sharedData.getLocalAsyncMap("clusterData", res -> {
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

	/**
	 * Configure charges with Authorize.net. 
	 **/
	private Promise<Void> configureAuthorizeNetCharges() {
		SiteConfig siteConfig = siteContextEnUS.getSiteConfig();
		ZoneId zoneId = ZoneId.of(siteConfig.getSiteZone());
		Promise<Void> promise = Promise.promise();
		if(StringUtils.isNotBlank(siteConfig.getAuthorizeEnvironment())) {

			vertx.setPeriodic(1000 * 10, a -> {
				WorkerExecutor executeurTravailleur = siteContextEnUS.getWorkerExecutor();
				executeurTravailleur.executeBlocking(
					blockingCodeHandler -> {
						LOGGER.info("Start to populate the new charges. ");
						ZonedDateTime start = ZonedDateTime.now(zoneId);
						SiteRequestEnUS siteRequest = new SiteRequestEnUS();
						siteRequest.setVertx(vertx);
						siteRequest.setSiteContext_(siteContextEnUS);
						siteRequest.setSiteConfig_(siteContextEnUS.getSiteConfig());
						siteRequest.initDeepSiteRequestEnUS(siteRequest);
						siteRequest.setJsonObject(new JsonObject());
			
						try {
							SchoolPaymentEnUSApiServiceImpl paymentService = new SchoolPaymentEnUSApiServiceImpl(siteContextEnUS);
							SchoolEnrollmentEnUSApiServiceImpl enrollmentService = new SchoolEnrollmentEnUSApiServiceImpl(siteContextEnUS);
	
							ZonedDateTime sessionStartDate = ZonedDateTime.now(zoneId).plusMonths(1);
							// Mar 26 is last late fee. 
							// Mar 1 + 2 month = May 1 < May 20 last day
							ZonedDateTime sessionEndDate = ZonedDateTime.now(zoneId);
							ZonedDateTime enrollmentChargeDate = ZonedDateTime.now(zoneId).plusMonths(1);
	
							SearchList<SchoolEnrollment> searchListEnrollment = new SearchList<SchoolEnrollment>();
							searchListEnrollment.setStore(true);
							searchListEnrollment.setQuery("*:*");
							searchListEnrollment.setC(SchoolEnrollment.class);
							searchListEnrollment.addFilterQuery("deleted_indexed_boolean:false");
							searchListEnrollment.addFilterQuery("archived_indexed_boolean:false");
							searchListEnrollment.addFilterQuery("sessionStartDate_indexed_date:[* TO " + dateFormat.format(sessionStartDate) + "]");
							searchListEnrollment.addFilterQuery("sessionEndDate_indexed_date:[" + dateFormat.format(sessionEndDate) + " TO *]");
	//						searchListEnrollment.addFilterQuery("(*:* AND -enrollmentChargeDate_indexed_date:[* TO *] OR enrollmentChargeDate_indexed_date:[* TO " + dateFormat.format(enrollmentChargeDate) + "])");
							searchListEnrollment.setRows(1);
							//searchListEnrollment.addSort("chargesCreated_indexed_boolean", ORDER.asc);
							searchListEnrollment.addSort("modified_indexed_date", ORDER.asc);
							searchListEnrollment.initDeepSearchList(siteRequest);
	
							futureAuthorizeNetEnrollmentCharges(searchListEnrollment, paymentService, enrollmentService, c -> {
								if(c.succeeded()) {
									try {
										SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
										searchList.setStore(true);
										searchList.setQuery("*:*");
										searchList.setC(SchoolPayment.class);
										searchList.addFilterQuery("created_indexed_date:[" + dateFormat.format(ZonedDateTime.ofInstant(start.toInstant(), ZoneId.of("UTC"))) + " TO *]");
										searchList.add("json.facet", "{enrollmentKeys:{terms:{field:enrollmentKey_indexed_long, limit:1000}}}");
										searchList.setRows(100);
										searchList.initDeepSearchList(siteRequest);
										SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(searchList.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
										List<Long> enrollmentKeys = Optional.ofNullable((SimpleOrderedMap)facets.get("enrollmentKeys")).map(m -> ((List<SimpleOrderedMap>)m.get("buckets"))).orElse(Arrays.asList()).stream().collect(Collectors.mapping(m -> (Long)m.get("val"), Collectors.toList()));
		
										List<Future> futures = new ArrayList<>();
										LOGGER.info(String.format("There are %s enrollments to reload. ", enrollmentKeys.size()));
										for(Long enrollmentKey : enrollmentKeys) {
											SchoolEnrollment schoolEnrollment = new SchoolEnrollment();
											schoolEnrollment.setPk(enrollmentKey);
											schoolEnrollment.setSiteRequest_(siteRequest);
											futures.add(
												enrollmentService.patchSchoolEnrollmentFuture(schoolEnrollment, false, d -> {
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
												LOGGER.info("Refreshing the enrollments has succeeded. ");
												LOGGER.info("Finish populating the new transactions. ");
												blockingCodeHandler.handle(Future.succeededFuture(d.result()));
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
									LOGGER.error(String.format("enrollments failed. "), c.cause());
									blockingCodeHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} catch (Exception e) {
							LOGGER.error(String.format("Authorize.net payments have failed. n%s", ExceptionUtils.getStackTrace(e)), ExceptionUtils.getStackTrace(e));
							errorAppVertx(siteRequest, null);
						}
					}, resultHandler -> {
						if(resultHandler.succeeded()) {
							LOGGER.info("Authorize.net WorkerExecutor.executeBlocking succeeded. ");
						} else {
							LOGGER.error("Authorize.net WorkerExecutor.executeBlocking failed. ", resultHandler.cause());
						}
					}
				);
			});
		}

		promise.complete();
		return promise;
	}

	public void  futureAuthorizeNetEnrollmentCharges(SearchList<SchoolEnrollment> listSchoolEnrollment, SchoolPaymentEnUSApiServiceImpl paymentService, SchoolEnrollmentEnUSApiServiceImpl enrollmentService, Handler<AsyncResult<Void>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolEnrollment.getSiteRequest_();
		listSchoolEnrollment.getList().forEach(o -> {
			futures.add(
				Future.future(a -> {
				enrollmentService.enrollmentChargesFuture(o, a2 -> {
					if(a2.succeeded()) {
//						enrollmentService.authorizeNetEnrollmentPaymentsFuture(o, b -> {
//							if(b.succeeded()) {
								LOGGER.info("Creating charges for customer %s succeeded. ");
								List<Future> futures2 = new ArrayList<>();
		
								SearchList<SchoolPayment> searchList2 = new SearchList<SchoolPayment>();
								searchList2.setStore(true);
								searchList2.setQuery("*:*");
								searchList2.setC(SchoolPayment.class);
								searchList2.addFilterQuery("enrollmentKey_indexed_long:" + o.getPk());
								searchList2.setRows(100);
								searchList2.initDeepSearchList(siteRequest);
		
								for(SchoolPayment o2 : searchList2.getList()) {
									SchoolPaymentEnUSApiServiceImpl service = new SchoolPaymentEnUSApiServiceImpl(siteRequest.getSiteContext_());
									SiteRequestEnUS siteRequest2 = paymentService.generateSiteRequestEnUSForSchoolPayment(siteContextEnUS, siteRequest.getOperationRequest(), new JsonObject());
									ApiRequest apiRequest2 = new ApiRequest();
									apiRequest2.setRows(1);
									apiRequest2.setNumFound(1l);
									apiRequest2.setNumPATCH(0L);
									apiRequest2.initDeepApiRequest(siteRequest2);
									siteRequest2.setApiRequest_(apiRequest2);
									siteRequest2.getVertx().eventBus().publish("websocketSchoolPayment", JsonObject.mapFrom(apiRequest2).toString());
		
									o2.setSiteRequest_(siteRequest2);
									futures2.add(
										service.patchSchoolPaymentFuture(o2, false, c -> {
											if(c.succeeded()) {
											} else {
												LOGGER.info(String.format("SchoolPayment %s failed. ", o2.getPk()));
												eventHandler.handle(Future.failedFuture(c.cause()));
											}
										})
									);
								}

								CompositeFuture.all(futures2).setHandler(f -> {
									if(f.succeeded()) {
										SiteRequestEnUS siteRequest2 = enrollmentService.generateSiteRequestEnUSForSchoolEnrollment(siteContextEnUS, siteRequest.getOperationRequest(), new JsonObject());
										ApiRequest apiRequest2 = new ApiRequest();
										apiRequest2.setRows(1);
										apiRequest2.setNumFound(1l);
										apiRequest2.setNumPATCH(0L);
										apiRequest2.initDeepApiRequest(siteRequest2);
										siteRequest2.setApiRequest_(apiRequest2);
										siteRequest2.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest2).toString());
			
										o.setSiteRequest_(siteRequest2);

										enrollmentService.patchSchoolEnrollmentFuture(o, false, g -> {
											if(g.succeeded()) {
												LOGGER.info("Refreshing enrollment succeeded. ");
											} else {
												LOGGER.error("Refreshing enrollment succeeded. ", g.cause());
												errorAppVertx(siteRequest, g);
											}
										});
									} else {
										LOGGER.error("Refresh relations failed. ", f.cause());
										errorAppVertx(siteRequest, f);
									}
								});
//							} else {
//								LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", b.cause()));
//								errorAppVertx(siteRequest, b);
//							}
//						});
					} else {
						errorAppVertx(siteRequest, a2);
					}
				});
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listSchoolEnrollment.next()) {
//					futureAuthorizeNetEnrollmentCharges(listSchoolEnrollment, paymentService, enrollmentService, eventHandler);
					LOGGER.info("Create a list of charges has succeeded. ");
				} else {
					eventHandler.handle(Future.succeededFuture());
				}
			} else {
				errorAppVertx(listSchoolEnrollment.getSiteRequest_(), a);
			}
		});
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

	public static final String syncDbToSolrComplete1 = "Syncing database to Solr completed. ";
	public static final String syncDbToSolrComplete = syncDbToSolrComplete1;
	public static final String syncDbToSolrFail1 = "Syncing database to Solr failed. ";
	public static final String syncDbToSolrFail = syncDbToSolrFail1;
	public static final String syncDbToSolrSkip1 = "Skip syncing database to Solr. ";
	public static final String syncDbToSolrSkip = syncDbToSolrSkip1;

	/**	
	 * Val.Complete.enUS:Syncing database to Solr completed. 
	 * Val.Fail.enUS:Syncing database to Solr failed. 
	 * Val.Skip.enUS:Skip syncing database to Solr. 
	 **/
	private Future<Void> syncDbToSolr() {
		Promise<Void> promise = Promise.promise();
		try {
			if("true".equals(System.getenv(ConfigKeys.ENABLE_DB_SOLR_SYNC))) {
				syncData("org.computate.scolaire.enUS.payment.SchoolPayment").onSuccess(q -> {
					syncData("org.computate.scolaire.enUS.school.School").onSuccess(a -> {
						syncData("org.computate.scolaire.enUS.year.SchoolYear").onSuccess(p -> {
							syncData("org.computate.scolaire.enUS.season.SchoolSeason").onSuccess(b -> {
								syncData("org.computate.scolaire.enUS.session.SchoolSession").onSuccess(c -> {
									syncData("org.computate.scolaire.enUS.age.SchoolAge").onSuccess(d -> {
										syncData("org.computate.scolaire.enUS.block.SchoolBlock").onSuccess(e -> {
											syncData("org.computate.scolaire.enUS.mom.SchoolMom").onSuccess(f -> {
												syncData("org.computate.scolaire.enUS.dad.SchoolDad").onSuccess(g -> {
													syncData("org.computate.scolaire.enUS.child.SchoolChild").onSuccess(h -> {
														syncData("org.computate.scolaire.enUS.enrollment.SchoolEnrollment").onSuccess(i -> {
															syncData("org.computate.scolaire.enUS.guardian.SchoolGuardian").onSuccess(j -> {
																syncData("org.computate.scolaire.enUS.user.SiteUser").onSuccess(k -> {
																	syncData("org.computate.scolaire.enUS.receipt.SchoolReceipt").onSuccess(l -> {
																		syncData("org.computate.scolaire.enUS.design.PageDesign").onSuccess(n -> {
																			syncData("org.computate.scolaire.enUS.html.part.HtmlPart").onSuccess(o -> {
																				LOG.info(syncDbToSolrComplete);
																				promise.complete();
																			}).onFailure(ex -> {
																				LOG.error(syncDbToSolrFail, ex);
																				promise.fail(ex);
																			});
																		}).onFailure(ex -> {
																			LOG.error(syncDbToSolrFail, ex);
																			promise.fail(ex);
																		});
																	}).onFailure(ex -> {
																		LOG.error(syncDbToSolrFail, ex);
																		promise.fail(ex);
																	});
																}).onFailure(ex -> {
																	LOG.error(syncDbToSolrFail, ex);
																	promise.fail(ex);
																});
															}).onFailure(ex -> {
																LOG.error(syncDbToSolrFail, ex);
																promise.fail(ex);
															});
														}).onFailure(ex -> {
															LOG.error(syncDbToSolrFail, ex);
															promise.fail(ex);
														});
													}).onFailure(ex -> {
														LOG.error(syncDbToSolrFail, ex);
														promise.fail(ex);
													});
												}).onFailure(ex -> {
													LOG.error(syncDbToSolrFail, ex);
													promise.fail(ex);
												});
											}).onFailure(ex -> {
												LOG.error(syncDbToSolrFail, ex);
												promise.fail(ex);
											});
										}).onFailure(ex -> {
											LOG.error(syncDbToSolrFail, ex);
											promise.fail(ex);
										});
									}).onFailure(ex -> {
										LOG.error(syncDbToSolrFail, ex);
										promise.fail(ex);
									});
								}).onFailure(ex -> {
									LOG.error(syncDbToSolrFail, ex);
									promise.fail(ex);
								});
							}).onFailure(ex -> {
								LOG.error(syncDbToSolrFail, ex);
								promise.fail(ex);
							});
						}).onFailure(ex -> {
							LOG.error(syncDbToSolrFail, ex);
							promise.fail(ex);
						});
					}).onFailure(ex -> {
						LOG.error(syncDbToSolrFail, ex);
						promise.fail(ex);
					});
				}).onFailure(ex -> {
					LOG.error(syncDbToSolrFail, ex);
					promise.fail(ex);
				});
			} else {
				LOG.info(syncDbToSolrSkip);
				promise.complete();
			}
		} catch(Exception ex) {
			LOG.error(syncDbToSolrFail, ex);
			promise.fail(ex);
		}
		return promise.future();
	}

	public static final String syncDataComplete1 = "%s data sync completed. ";
	public static final String syncDataComplete = syncDataComplete1;
	public static final String syncDataFail1 = "%s data sync failed. ";
	public static final String syncDataFail = syncDataFail1;
	public static final String syncDataCounterResetFail1 = "%s data sync failed to reset counter. ";
	public static final String syncDataCounterResetFail = syncDataCounterResetFail1;
	public static final String syncDataSkip1 = "%s data sync skipped. ";
	public static final String syncDataSkip = syncDataSkip1;
	public static final String syncDataStarted1 = "%s data sync started. ";
	public static final String syncDataStarted = syncDataStarted1;

	/**	
	 * Sync %s data from the database to Solr. 
	 * Val.Complete.enUS:%s data sync completed. 
	 * Val.Fail.enUS:%s data sync failed. 
	 * Val.CounterResetFail.enUS:%s data sync failed to reset counter. 
	 * Val.Skip.enUS:%s data sync skipped. 
	 * Val.Started.enUS:%s data sync started. 
	 **/
	private Future<Void> syncData(String classCanonicalName) {
		Promise<Void> promise = Promise.promise();
		vertx.setTimer(10000, timer -> {
			String tableName = StringUtils.substringAfterLast(classCanonicalName, ".");
			try {
				LOG.info(String.format(syncDataStarted, classCanonicalName));
				pgPool.getConnection(a -> {
					if(a.succeeded()) {
						SqlConnection sqlConnection = a.result();
						Transaction tx = sqlConnection.begin();
						sqlConnection.query(String.format("SELECT count(pk) FROM c where canonical_name='%s'", classCanonicalName)).execute(b -> {
							if(b.succeeded()) {
								try {
									RowSet<Row> countRowSet = b.result();
									Optional<Long> rowCountOptional = Optional.ofNullable(countRowSet.iterator().next()).map(row -> row.getLong(0));
									if(rowCountOptional.isPresent()) {
										Long apiCounterResume = 0L;
										Long apiCounterFetch = 1L;
										ApiCounter apiCounter = new ApiCounter();
			
										SiteRequestEnUS siteRequest = new SiteRequestEnUS();
										siteRequest.setSiteConfig_(siteContextEnUS.getSiteConfig());
										siteRequest.setSiteContext_(siteContextEnUS);
										siteRequest.initDeepSiteRequestEnUS(siteRequest);
				
										ApiRequest apiRequest = new ApiRequest();
										apiRequest.setRows(apiCounterFetch.intValue());
										apiRequest.setNumFound(rowCountOptional.get());
										apiRequest.setNumPATCH(apiCounter.getQueueNum());
										apiRequest.setCreated(ZonedDateTime.now(ZoneId.of(siteRequest.getSiteConfig_().getSiteZone())));
										apiRequest.initDeepApiRequest(siteRequest);
										vertx.eventBus().publish(String.format("websocket%s", classCanonicalName), JsonObject.mapFrom(apiRequest));
				
										sqlConnection.prepare(String.format("SELECT pk FROM c where canonical_name='%s'", classCanonicalName), c -> {
											if(c.succeeded()) {
												PreparedStatement preparedStatement = c.result();
												apiCounter.setQueueNum(0L);
												apiCounter.setTotalNum(0L);
												try {
													RowStream<Row> stream = preparedStatement.createStream(apiCounterFetch.intValue());
													stream.pause();
													stream.fetch(apiCounterFetch);
													stream.exceptionHandler(ex -> {
														LOG.error(String.format(syncDataFail, classCanonicalName), new RuntimeException(ex));
														tx.rollback();
														sqlConnection.close();
														promise.fail(ex);
													});
													stream.endHandler(v -> {
														LOG.info(String.format(syncDataComplete, classCanonicalName));
														tx.rollback();
														sqlConnection.close();
														promise.complete();
													});
													stream.handler(row -> {
	//													pgPool.getConnection(f -> {
	//														if(f.succeeded()) {
																try {
	//																SqlConnection sqlConnection2 = a.result();
																	apiCounter.incrementQueueNum();
					
																	SiteRequestEnUS siteRequest2 = new SiteRequestEnUS();
																	siteRequest2.setSiteConfig_(siteContextEnUS.getSiteConfig());
																	siteRequest2.setSiteContext_(siteContextEnUS);
																	siteRequest2.initDeepSiteRequestEnUS(siteRequest2);
			
																	Cluster o = (Cluster)Class.forName(classCanonicalName).newInstance();
																	o.setSiteRequest_(siteRequest2);
																	Long pk = row.getLong(0);
																	o.setPk(pk);
	//																Transaction tx = sqlConnection2.begin();
																	sqlConnection.preparedQuery(SiteContextEnUS.SQL_define)
																			.collecting(Collectors.toList())
																			.execute(Tuple.of(pk)
																			, defineAsync
																	-> {
																		if(defineAsync.succeeded()) {
																			try {
																				for(Row definition : defineAsync.result().value()) {
																					try {
																						o.defineForClass(definition.getString(0), definition.getString(1));
																					} catch(Exception e) {
																						LOGGER.error(String.format("defineSchool failed. "), e);
																						LOGGER.error(e);
																					}
																				}
																				sqlConnection.preparedQuery(SiteContextEnUS.SQL_attribute)
																						.collecting(Collectors.toList())
																						.execute(Tuple.of(pk, pk)
																						, attributeAsync
																				-> {
																					try {
																						if(attributeAsync.succeeded()) {
																							if(attributeAsync.result() != null) {
																								for(Row definition : attributeAsync.result().value()) {
																									if(pk.equals(definition.getLong(0)))
																										o.attributeForClass(definition.getString(2), definition.getLong(1));
																									else
																										o.attributeForClass(definition.getString(3), definition.getLong(0));
																								}
																							}
																							try {
			
																								o.initDeepForClass(siteRequest2);
																								o.indexForClass();
	//																							tx.commit();
	//																							tx.close();
	//																							sqlConnection2.close();
																								LOG.info(String.format("Index %s %s", classCanonicalName, pk));
																								stream.fetch(apiCounterFetch);
																							} catch (Exception ex) {
																								LOG.error(String.format(syncDataFail, classCanonicalName), ex);
	//																							tx.rollback();
	//																							tx.close();
	//																							sqlConnection2.close();
																							}
																						} else {
																							LOGGER.error(String.format("attributeSchool failed. "), attributeAsync.cause());
	//																						tx.rollback();
	//																						tx.close();
	//																						sqlConnection2.close();
																						}
																					} catch(Exception e) {
																						LOGGER.error(String.format("attributeSchool failed. "), e);
	//																					tx.rollback();
	//																					tx.close();
	//																					sqlConnection2.close();
																					}
																				});
																			} catch(Exception e) {
																				LOGGER.error(String.format("defineSchool failed. "), e);
	//																			tx.rollback();
	//																			tx.close();
	//																			sqlConnection2.close();
																			}
																		} else {
																			LOGGER.error(String.format("defineSchool failed. "), defineAsync.cause());
	//																		tx.rollback();
	//																		tx.close();
	//																		sqlConnection2.close();
																		}
																	});
																} catch(Exception e) {
																	LOGGER.error(String.format("defineSchool failed. "), e);
																}
	//														} else {
	//															LOG.error(String.format(syncDataFail, classCanonicalName), f.cause());
	//															promise.fail(c.cause());
	//														}
	//													});
													});
												} catch (Exception ex) {
													LOG.error(String.format(syncDataFail, classCanonicalName), ex);
													promise.fail(ex);
												}
											} else {
												LOG.error(String.format(syncDataFail, classCanonicalName), c.cause());
												promise.fail(c.cause());
											}
										});
									} else {
										promise.complete();
									}
								} catch (Exception ex) {
									LOG.error(String.format(syncDataFail, classCanonicalName), ex);
									promise.fail(ex);
								}
							} else {
								LOG.error(String.format(syncDataFail, classCanonicalName), b.cause());
								promise.fail(b.cause());
							}
						});
					} else {
						LOG.error(String.format(syncDataFail, classCanonicalName), a.cause());
						promise.fail(a.cause());
					}
				});
			} catch (Exception ex) {
				LOG.error(String.format(syncDataFail, classCanonicalName), ex);
				promise.fail(ex);
			}
		});
		return promise.future();
	}
}
