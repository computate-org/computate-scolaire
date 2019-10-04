package org.computate.scolaire.enUS.vertx;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.contexte.SiteContextEnUSGen;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.search.SearchList;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.api.OperationResponse;

public class AppRestore extends AbstractVerticle {

	Base64.Decoder decoder;
	Cipher cipher;

	public static void main(String[] args) {

		final Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new AppRestore());
	}

	/**	
	 *	This is called by Vert.x when the verticle instance is deployed. 
	 *	Initialize a new site context object for storing information about the entire site in English. 
	 *	Setup the startFuture to handle the configuration steps and starting the server. 
	 **/
	@Override()
	public void  start(Future<Void> startFuture) throws Exception, Exception {

		try {
			SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.initDeepSiteRequestEnUS();
			siteContext.getSiteConfig()
					.setConfigPath("/usr/local/src/computate-scolaire/config/computate-scolaire-enUS.config");
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			ZonedDateTime dateTime = ZonedDateTime.now();
			configureData(siteRequest);

			decoder = Base64.getDecoder();
			cipher = Cipher.getInstance("AES");
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			byte[] key = Arrays.copyOf(messageDigest.digest((siteRequest.getSiteConfig_().getEncryptionPassword()).getBytes("UTF-8")), 16);
			SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

			sqlCluster(siteRequest, a -> {
				if(a.succeeded()) {
					nextDefinitions(siteRequest, dateTime, b -> {
						if(b.succeeded()) {
							List<JsonArray> rows = b.result();
							listDefinitions(siteRequest, rows, dateTime, d -> {
								if(d.succeeded()) {
									nextClusters(siteRequest, dateTime, e -> {
										if(e.succeeded()) {
											SearchList<Cluster> clusters = e.result();
											listPATCHCluster(clusters, dateTime, f -> {
												if(f.succeeded()) {
													SQLConnection sqlConnection = siteRequest.getSqlConnection();
													if(sqlConnection == null) {
														startFuture.complete();
				//										eventHandler.handle(Future.succeededFuture(d.result()));
													} else {
														sqlConnection.commit(g -> {
															if(g.succeeded()) {
																sqlConnection.close(h -> {
																	if(h.succeeded()) {
																		System.out.println("Completed!!!");
																		startFuture.complete();
				//														eventHandler.handle(Future.succeededFuture(d.result()));
																	} else {
																		startFuture.fail(h.cause());
				//														errorCluster(siteRequest, eventHandler, f);
																	}
																});
															} else {
																startFuture.fail(g.cause());
				//												eventHandler.handle(Future.succeededFuture(d.result()));
															}
														});
													}
												} else {
													startFuture.fail(f.cause());
				//									errorCluster(siteRequest, eventHandler, d);
												}
											});
										} else {
											startFuture.fail(e.cause());
				//							errorCluster(siteRequest, eventHandler, b);
										}
									});
								} else {
									startFuture.fail(d.cause());
				//					errorCluster(siteRequest, eventHandler, a);
								}
							});
						} else {
							startFuture.fail(b.cause());
//							errorCluster(siteRequest, eventHandler, b);
						}
					});
				} else {
					startFuture.fail(a.cause());
//					errorCluster(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			throw e;
//			errorCluster(null, eventHandler, Future.failedFuture(e));
		}
	}

	/**	
	 *	Configure shared database connections across the cluster for massive scaling of the application. 
	 *	Return a future that configures a shared database client connection. 
	 *	Load the database configuration into a shared io.vertx.ext.jdbc.JDBCClient for a scalable, clustered datasource connection pool. 
	 *	Initialize the database tables if not already created for the first time. 
	 **/
	private Future<Void> configureData(SiteRequestEnUS siteRequest) {
		SiteContextEnUSGen<Object> siteContextEnUS = siteRequest.getSiteContext_();
		SiteConfig siteConfig = siteContextEnUS.getSiteConfig();
		Future<Void> future = Future.future();

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
		JDBCClient jdbcClient = JDBCClient.createShared(vertx, jdbcConfig);

		siteContextEnUS.setSqlClient(jdbcClient);
		future.complete();

		return future;
	}

	public void nextDefinitions(SiteRequestEnUS siteRequest, ZonedDateTime dateTime, Handler<AsyncResult<List<JsonArray>>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			sqlConnection.queryWithParams(
					"update d set modified=now() where pk in (select pk from d where not current and modified < ? limit 10) returning pk, pk_c, path, value, current, created, modified;"
					, new JsonArray(Arrays.asList(Timestamp.from(dateTime.toInstant())))
					, updateDAsync
			-> {
				if(updateDAsync.succeeded()) {
					List<JsonArray> rows = updateDAsync.result().getResults();
					System.out.println("d " + rows.stream().map(row -> row.getLong(0)).collect(Collectors.toList()));
					eventHandler.handle(Future.succeededFuture(rows));
				} else {
					eventHandler.handle(Future.failedFuture(new Exception(updateDAsync.cause())));
				}
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void nextClusters(SiteRequestEnUS siteRequest, ZonedDateTime dateTime, Handler<AsyncResult<SearchList<Cluster>>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			sqlConnection.queryWithParams(
					"update c set modified=now() where pk in (select pk from c where current and canonical_name is not null and modified < ? limit 10) returning pk, current, canonical_name, created, modified, user_id;"
					, new JsonArray(Arrays.asList(Timestamp.from(dateTime.toInstant())))
					, selectCAsync
			-> {
				if(selectCAsync.succeeded()) {
					SearchList<Cluster> searchList = new SearchList<Cluster>();
					searchList.setSiteRequest_(siteRequest);
					System.out.println("c " + selectCAsync.result().getResults().stream().map(row -> row.getLong(0)).collect(Collectors.toList()));
					selectCAsync.result().getResults().forEach(clusterValues -> {
						try {
							Long pk = clusterValues.getLong(0);
							String canonicalName = clusterValues.getString(2);
							Cluster cluster = (Cluster)Class.forName(canonicalName).newInstance();
							cluster.setPk(pk);
							cluster.setCreated(ZonedDateTime.ofInstant(clusterValues.getInstant(3), ZoneId.systemDefault()));
							cluster.setModified(ZonedDateTime.ofInstant(clusterValues.getInstant(4), ZoneId.systemDefault()));
							cluster.setSiteRequest_(siteRequest);
							searchList.getList().add(cluster);
						} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
							eventHandler.handle(Future.failedFuture(e));
						}
					});
					eventHandler.handle(Future.succeededFuture(searchList));
				} else {
					eventHandler.handle(Future.failedFuture(new Exception(selectCAsync.cause())));
				}
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlCluster(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void listDefinitions(SiteRequestEnUS siteRequest, List<JsonArray> rows, ZonedDateTime dateTime, Handler<AsyncResult<List<JsonArray>>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		if(rows.size() == 0) {
			eventHandler.handle(Future.succeededFuture(rows));
		}
		else {
			rows.forEach(o -> {
				futures.add(
						futureDefinition(siteRequest, rows, dateTime, o, a -> {
						if(a.succeeded()) {
						} else {
							eventHandler.handle(Future.failedFuture(a.cause()));
						}
					})
				);
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					nextDefinitions(siteRequest, dateTime, b -> {
						if(b.succeeded()) {
							List<JsonArray> clusters = b.result();
							listDefinitions(siteRequest, clusters, dateTime, eventHandler);
						} else {
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		}
	}

	public Future<List<JsonArray>> futureDefinition(SiteRequestEnUS siteRequest, List<JsonArray> rows, ZonedDateTime dateTime, JsonArray o,  Handler<AsyncResult<List<JsonArray>>> eventHandler) {

		Future<List<JsonArray>> future = Future.future();
		try {
			Long pk = o.getLong(0);
			String path = o.getString(2);
			String value = o.getString(3);
			try {
				value = new String(cipher.doFinal(decoder.decode(value)));
			} catch (Exception e1) {
				System.err.println(o);
//				e1.printStackTrace();
			}
			if(StringUtils.equals("personFirstName", path))
				value = getScrambledWord(value);
			if(StringUtils.equalsAny(path, "yearStart", "yearEnd", "seasonStartDate", "sessionStartDate", "sessionEndDate", "personBirthDate", "paymentDate")) {
				try {
					value = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US).format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US).parse(value));
				} catch (Exception e) {
					System.err.println(o);
					e.printStackTrace();
				}
			}
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			sqlConnection.queryWithParams(
					"update d set modified=now(), value=?, current=true where pk=?;"
					, new JsonArray(Arrays.asList(value, pk))
					, selectCAsync
			-> {
				if(selectCAsync.succeeded()) {
					future.complete(rows);
				} else {
					eventHandler.handle(Future.failedFuture(selectCAsync.cause()));
				}
			});
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public void listPATCHCluster(SearchList<Cluster> listCluster, ZonedDateTime dateTime, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listCluster.getSiteRequest_();
		if(listCluster.size() == 0) {
			response200PATCHCluster(listCluster, eventHandler);
		}
		else {
			listCluster.getList().forEach(o -> {
				futures.add(
					futurePATCHCluster(o, a -> {
						if(a.succeeded()) {
						} else {
							errorCluster(siteRequest, eventHandler, a);
						}
					})
				);
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					nextClusters(siteRequest, dateTime, b -> {
						if(b.succeeded()) {
							SearchList<Cluster> clusters = b.result();
							listPATCHCluster(clusters, dateTime, eventHandler);
						} else {
							errorCluster(listCluster.getSiteRequest_(), eventHandler, a);
						}
					});
				} else {
					errorCluster(listCluster.getSiteRequest_(), eventHandler, a);
				}
			});
		}
	}

	public Future<Cluster> futurePATCHCluster(Cluster o,  Handler<AsyncResult<OperationResponse>> eventHandler) {
		Future<Cluster> future = Future.future();
		try {
			defineCluster(o, b -> {
				if(b.succeeded()) {
					attributeCluster(o, c -> {
						if(c.succeeded()) {
							indexCluster(o, d -> {
								if(d.succeeded()) {
									future.complete(o);
//									eventHandler.handle(Future.succeededFuture(d.result()));
								} else {
									eventHandler.handle(Future.failedFuture(d.cause()));
								}
							});
						} else {
							eventHandler.handle(Future.failedFuture(c.cause()));
						}
					});
				} else {
					eventHandler.handle(Future.failedFuture(b.cause()));
				}
			});
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public void defineCluster(Cluster o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_define
					, new JsonArray(Arrays.asList(pk, pk, pk))
					, defineAsync
			-> {
				if(defineAsync.succeeded()) {
					try {
						for(JsonArray definition : defineAsync.result().getResults()) {
							try {
								o.defineForClass(definition.getString(0), definition.getString(1));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								System.out.println(definition);
								e.printStackTrace();
							}
						}
						eventHandler.handle(Future.succeededFuture());
					} catch(Exception e) {
						eventHandler.handle(Future.failedFuture(e));
					}
				} else {
					eventHandler.handle(Future.failedFuture(new Exception(defineAsync.cause())));
				}
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void attributeCluster(Cluster o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_attribute
					, new JsonArray(Arrays.asList(pk, pk))
					, attributeAsync
			-> {
				try {
					if(attributeAsync.succeeded()) {
						if(attributeAsync.result() != null) {
							for(JsonArray definition : attributeAsync.result().getResults()) {
								if(pk.equals(definition.getLong(0)))
									o.attributeForClass(definition.getString(2), definition.getLong(1));
								else
									o.attributeForClass(definition.getString(3), definition.getLong(0));
							}
						}
						eventHandler.handle(Future.succeededFuture());
					} else {
						eventHandler.handle(Future.failedFuture(new Exception(attributeAsync.cause())));
					}
				} catch(Exception e) {
					eventHandler.handle(Future.failedFuture(e));
				}
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void indexCluster(Cluster o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			o.initDeepForClass(siteRequest);
			o.indexForClass();
//			WorkerExecutor workerExecutor = siteRequest.getSiteContext_().getWorkerExecutor();
//			workerExecutor.executeBlocking(future -> {
//					o.indexForClass();
//				}, res -> {
//					eventHandler.handle(Future.succeededFuture());
//			});
			eventHandler.handle(Future.succeededFuture());
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void response200PATCHCluster(SearchList<Cluster> listCluster, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listCluster.getSiteRequest_();
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void errorCluster(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
		Throwable e = resultAsync.cause();
		ExceptionUtils.printRootCauseStackTrace(e);
		OperationResponse responseOperation = new OperationResponse(400, "BAD REQUEST", 
			Buffer.buffer().appendString(
				new JsonObject() {{
					put("error", new JsonObject() {{
					put("message", e.getMessage());
					}});
				}}.encodePrettily()
			)
			, new CaseInsensitiveHeaders()
		);
		if(siteRequest != null) {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			if(sqlConnection != null) {
				sqlConnection.rollback(a -> {
					if(a.succeeded()) {
						sqlConnection.close(b -> {
							if(a.succeeded()) {
								eventHandler.handle(Future.succeededFuture(responseOperation));
							} else {
								eventHandler.handle(Future.succeededFuture(responseOperation));
							}
						});
					} else {
						eventHandler.handle(Future.succeededFuture(responseOperation));
					}
				});
			} else {
				eventHandler.handle(Future.succeededFuture(responseOperation));
			}
		} else {
			eventHandler.handle(Future.succeededFuture(responseOperation));
		}
	}

	public static String getScrambledWord(String str) {
	    char[] character = str.toCharArray();
	    String question1 = new String();

	    ArrayList<Character> chars = new ArrayList<Character>(); //an arraylist is an array wich dynamically changes its size depending on the amount of its elements
	    for (int i = 0; i < character.length; i++) {// first we put all characters of the word into that arraylist
	        chars.add(character[i]);
	    }

	    while(chars.size()>0){//then we iterate over the arraylist as long as it has more than 0 elements
	        int index = (int)(Math.random() * chars.size());//we create a random index in the range of 0 and the arraylists size
	        question1 += chars.get(index);// we add the letter at the index we generated to the scrambled word variable
	        chars.remove(index);// then we remove the character we just added to the scrambled word, from the arraylist, so it cant be in there twice
	    }// thus the size decreases by 1 each iteration until every element of the arrraylist is somewhere in the scrambled word

	    return question1;
	}}