package org.computate.scolaire.enUS.age;

import org.computate.scolaire.enUS.block.SchoolBlockEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.block.SchoolBlock;
import org.computate.scolaire.enUS.session.SchoolSessionEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.session.SchoolSession;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.computate.scolaire.enUS.search.SearchResult;
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
import io.netty.handler.codec.http.HttpResponseStatus;
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
import org.computate.scolaire.enUS.user.SiteUserEnUSApiServiceImpl;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.writer.AllWriter;


/**
 * Translate: false
 * CanonicalName.frFR: org.computate.scolaire.frFR.age.AgeScolaireFrFRGenApiServiceImpl
 **/
public class SchoolAgeEnUSGenApiServiceImpl implements SchoolAgeEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolAgeEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SchoolAgeEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public SchoolAgeEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
	}

	// POST //

	@Override
	public void postSchoolAge(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("postSchoolAge started. "));

			List<String> roles = Arrays.asList("SiteAdmin");
			if(
					!CollectionUtils.containsAny(siteRequest.getUserResourceRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest.getUserRealmRoles(), roles)
					) {
				eventHandler.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "roles required: " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolAge(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							siteRequest.getVertx().eventBus().publish("websocketSchoolAge", JsonObject.mapFrom(apiRequest).toString());
							postSchoolAgeFuture(siteRequest, false, c -> {
								if(c.succeeded()) {
									SchoolAge schoolAge = c.result();
									apiRequest.setPk(schoolAge.getPk());
									apiRequestSchoolAge(schoolAge);
									postSchoolAgeResponse(schoolAge, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postSchoolAge succeeded. "));
										} else {
											LOGGER.error(String.format("postSchoolAge failed. ", d.cause()));
											errorSchoolAge(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("postSchoolAge failed. ", c.cause()));
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("postSchoolAge failed. ", b.cause()));
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("postSchoolAge failed. ", a.cause()));
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public Future<SchoolAge> postSchoolAgeFuture(SiteRequestEnUS siteRequest, Boolean inheritPk, Handler<AsyncResult<SchoolAge>> eventHandler) {
		Promise<SchoolAge> promise = Promise.promise();
		try {
			createSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolAge schoolAge = a.result();
					sqlPOSTSchoolAge(schoolAge, inheritPk, b -> {
						if(b.succeeded()) {
							defineIndexSchoolAge(schoolAge, c -> {
								if(c.succeeded()) {
									ApiRequest apiRequest = siteRequest.getApiRequest_();
									if(apiRequest != null) {
										apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
										schoolAge.apiRequestSchoolAge();
										siteRequest.getVertx().eventBus().publish("websocketSchoolAge", JsonObject.mapFrom(apiRequest).toString());
									}
									eventHandler.handle(Future.succeededFuture(schoolAge));
									promise.complete(schoolAge);
								} else {
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			errorSchoolAge(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTSchoolAge(SchoolAge o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			StringBuilder postSql = new StringBuilder();
			List<Object> postSqlParams = new ArrayList<Object>();

			if(siteRequest.getSessionId() != null) {
				postSql.append(SiteContextEnUS.SQL_setD);
				postSqlParams.addAll(Arrays.asList("sessionId", siteRequest.getSessionId(), pk));
			}
			if(siteRequest.getUserId() != null) {
				postSql.append(SiteContextEnUS.SQL_setD);
				postSqlParams.addAll(Arrays.asList("userId", siteRequest.getUserId(), pk));
			}
			if(siteRequest.getUserKey() != null) {
				postSql.append(SiteContextEnUS.SQL_setD);
				postSqlParams.addAll(Arrays.asList("userKey", siteRequest.getUserKey(), pk));
			}

			if(jsonObject != null) {
				Set<String> entityVars = jsonObject.fieldNames();
				for(String entityVar : entityVars) {
					switch(entityVar) {
					case "inheritPk":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inheritPk", jsonObject.getString(entityVar), pk));
						break;
					case "created":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("created", jsonObject.getString(entityVar), pk));
						break;
					case "modified":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("modified", jsonObject.getString(entityVar), pk));
						break;
					case "archived":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("archived", jsonObject.getBoolean(entityVar), pk));
						break;
					case "deleted":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("deleted", jsonObject.getBoolean(entityVar), pk));
						break;
					case "sessionId":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("sessionId", jsonObject.getString(entityVar), pk));
						break;
					case "userId":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("userId", jsonObject.getString(entityVar), pk));
						break;
					case "userKey":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("userKey", jsonObject.getString(entityVar), pk));
						break;
					case "blockKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolBlock.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("ageKey", l, "blockKeys", pk));
								}
							}
						}
						break;
					case "sessionKey":
						{
							Long l = Long.parseLong(jsonObject.getString(entityVar));
							if(l != null) {
								SearchList<SchoolSession> searchList = new SearchList<SchoolSession>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolSession.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("ageKeys", l, "sessionKey", pk));
								}
							}
						}
						break;
					case "schoolAddress":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("schoolAddress", jsonObject.getString(entityVar), pk));
						break;
					case "ageStart":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ageStart", jsonObject.getString(entityVar), pk));
						break;
					case "ageEnd":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ageEnd", jsonObject.getString(entityVar), pk));
						break;
					}
				}
			}
			sqlConnection.queryWithParams(
					postSql.toString()
					, new JsonArray(postSqlParams)
					, postAsync
			-> {
				if(postAsync.succeeded()) {
					eventHandler.handle(Future.succeededFuture());
				} else {
					eventHandler.handle(Future.failedFuture(new Exception(postAsync.cause())));
				}
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void postSchoolAgeResponse(SchoolAge schoolAge, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = schoolAge.getSiteRequest_();
		try {
			response200POSTSchoolAge(schoolAge, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200POSTSchoolAge(SchoolAge o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(o);
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTImport //

	@Override
	public void putimportSchoolAge(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putimportSchoolAge started. "));

			List<String> roles = Arrays.asList("SiteAdmin");
			if(
					!CollectionUtils.containsAny(siteRequest.getUserResourceRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest.getUserRealmRoles(), roles)
					) {
				eventHandler.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "roles required: " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolAge(siteRequest, b -> {
						if(b.succeeded()) {
							putimportSchoolAgeResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest, body);
											try {
												ApiRequest apiRequest = new ApiRequest();
												JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												apiRequest.setRows(jsonArray.size());
												apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketSchoolAge", JsonObject.mapFrom(apiRequest).toString());
												sqlSchoolAge(siteRequest2, d -> {
													if(d.succeeded()) {
														listPUTImportSchoolAge(apiRequest, siteRequest2, e -> {
															if(e.succeeded()) {
																putimportSchoolAgeResponse(siteRequest2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putimportSchoolAge succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putimportSchoolAge failed. ", f.cause()));
																		errorSchoolAge(siteRequest2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putimportSchoolAge failed. ", e.cause()));
																errorSchoolAge(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putimportSchoolAge failed. ", d.cause()));
														errorSchoolAge(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putimportSchoolAge failed. ", ex));
												errorSchoolAge(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putimportSchoolAge failed. ", c.cause()));
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putimportSchoolAge failed. ", b.cause()));
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putimportSchoolAge failed. ", a.cause()));
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTImportSchoolAge(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolAge(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());

				SearchList<SchoolAge> searchList = new SearchList<SchoolAge>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolAge.class);
				searchList.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					SchoolAge o = searchList.getList().stream().findFirst().orElse(null);
					JsonObject json2 = new JsonObject();
					for(String f : json.fieldNames()) {
						json2.put("set" + StringUtils.capitalize(f), json.getValue(f));
					}
					if(o != null) {
						for(String f : Optional.ofNullable(o.getSaves()).orElse(Arrays.asList())) {
							if(!json.fieldNames().contains(f))
								json2.putNull("set" + StringUtils.capitalize(f));
						}
						siteRequest2.setJsonObject(json2);
						futures.add(
							patchSchoolAgeFuture(o, true, a -> {
								if(a.succeeded()) {
									SchoolAge schoolAge = a.result();
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									siteRequest2.getVertx().eventBus().publish("websocketSchoolAge", JsonObject.mapFrom(apiRequest).toString());
								} else {
									errorSchoolAge(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolAgeFuture(siteRequest2, true, a -> {
							if(a.succeeded()) {
								SchoolAge schoolAge = a.result();
								apiRequestSchoolAge(schoolAge);
							} else {
								errorSchoolAge(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
								apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
								siteRequest.getVertx().eventBus().publish("websocketSchoolAge", JsonObject.mapFrom(apiRequest).toString());
					response200PUTImportSchoolAge(siteRequest, eventHandler);
				} else {
					errorSchoolAge(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putimportSchoolAgeResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTImportSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTImportSchoolAge(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTMerge //

	@Override
	public void putmergeSchoolAge(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putmergeSchoolAge started. "));

			List<String> roles = Arrays.asList("SiteAdmin");
			if(
					!CollectionUtils.containsAny(siteRequest.getUserResourceRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest.getUserRealmRoles(), roles)
					) {
				eventHandler.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "roles required: " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolAge(siteRequest, b -> {
						if(b.succeeded()) {
							putmergeSchoolAgeResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest, body);
											try {
												ApiRequest apiRequest = new ApiRequest();
												JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												apiRequest.setRows(jsonArray.size());
												apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketSchoolAge", JsonObject.mapFrom(apiRequest).toString());
												sqlSchoolAge(siteRequest2, d -> {
													if(d.succeeded()) {
														listPUTMergeSchoolAge(apiRequest, siteRequest2, e -> {
															if(e.succeeded()) {
																putmergeSchoolAgeResponse(siteRequest2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putmergeSchoolAge succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putmergeSchoolAge failed. ", f.cause()));
																		errorSchoolAge(siteRequest2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putmergeSchoolAge failed. ", e.cause()));
																errorSchoolAge(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putmergeSchoolAge failed. ", d.cause()));
														errorSchoolAge(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putmergeSchoolAge failed. ", ex));
												errorSchoolAge(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putmergeSchoolAge failed. ", c.cause()));
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putmergeSchoolAge failed. ", b.cause()));
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putmergeSchoolAge failed. ", a.cause()));
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTMergeSchoolAge(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolAge(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());

				SearchList<SchoolAge> searchList = new SearchList<SchoolAge>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolAge.class);
				searchList.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					SchoolAge o = searchList.getList().stream().findFirst().orElse(null);
					JsonObject json2 = new JsonObject();
					for(String f : json.fieldNames()) {
						json2.put("set" + StringUtils.capitalize(f), json.getValue(f));
					}
					if(o != null) {
						for(String f : Optional.ofNullable(o.getSaves()).orElse(Arrays.asList())) {
							if(!json.fieldNames().contains(f))
								json2.putNull("set" + StringUtils.capitalize(f));
						}
						siteRequest2.setJsonObject(json2);
						futures.add(
							patchSchoolAgeFuture(o, false, a -> {
								if(a.succeeded()) {
									SchoolAge schoolAge = a.result();
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									siteRequest2.getVertx().eventBus().publish("websocketSchoolAge", JsonObject.mapFrom(apiRequest).toString());
								} else {
									errorSchoolAge(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolAgeFuture(siteRequest2, false, a -> {
							if(a.succeeded()) {
								SchoolAge schoolAge = a.result();
								apiRequestSchoolAge(schoolAge);
							} else {
								errorSchoolAge(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
								apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
								siteRequest.getVertx().eventBus().publish("websocketSchoolAge", JsonObject.mapFrom(apiRequest).toString());
					response200PUTMergeSchoolAge(siteRequest, eventHandler);
				} else {
					errorSchoolAge(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putmergeSchoolAgeResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTMergeSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTMergeSchoolAge(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTCopy //

	@Override
	public void putcopySchoolAge(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putcopySchoolAge started. "));

			List<String> roles = Arrays.asList("SiteAdmin");
			if(
					!CollectionUtils.containsAny(siteRequest.getUserResourceRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest.getUserRealmRoles(), roles)
					) {
				eventHandler.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "roles required: " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolAge(siteRequest, b -> {
						if(b.succeeded()) {
							putcopySchoolAgeResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest, body);
											try {
												aSearchSchoolAge(siteRequest2, false, true, "/api/age/copy", "PUTCopy", d -> {
													if(d.succeeded()) {
														SearchList<SchoolAge> listSchoolAge = d.result();
														ApiRequest apiRequest = new ApiRequest();
														apiRequest.setRows(listSchoolAge.getRows());
														apiRequest.setNumFound(listSchoolAge.getQueryResponse().getResults().getNumFound());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest2);
														siteRequest2.setApiRequest_(apiRequest);
														siteRequest2.getVertx().eventBus().publish("websocketSchoolAge", JsonObject.mapFrom(apiRequest).toString());
														sqlSchoolAge(siteRequest2, e -> {
															if(e.succeeded()) {
																try {
																	listPUTCopySchoolAge(apiRequest, listSchoolAge, f -> {
																		if(f.succeeded()) {
																			putcopySchoolAgeResponse(siteRequest2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("putcopySchoolAge succeeded. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("putcopySchoolAge failed. ", g.cause()));
																					errorSchoolAge(siteRequest2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("putcopySchoolAge failed. ", f.cause()));
																			errorSchoolAge(siteRequest2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("putcopySchoolAge failed. ", ex));
																	errorSchoolAge(siteRequest2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("putcopySchoolAge failed. ", e.cause()));
																errorSchoolAge(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putcopySchoolAge failed. ", d.cause()));
														errorSchoolAge(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putcopySchoolAge failed. ", ex));
												errorSchoolAge(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putcopySchoolAge failed. ", c.cause()));
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putcopySchoolAge failed. ", b.cause()));
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putcopySchoolAge failed. ", a.cause()));
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTCopySchoolAge(ApiRequest apiRequest, SearchList<SchoolAge> listSchoolAge, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolAge.getSiteRequest_();
		listSchoolAge.getList().forEach(o -> {
			futures.add(
				putcopySchoolAgeFuture(siteRequest, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						SchoolAge schoolAge = a.result();
						apiRequestSchoolAge(schoolAge);
					} else {
						errorSchoolAge(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchoolAge.size());
				siteRequest.getVertx().eventBus().publish("websocketSchoolAge", JsonObject.mapFrom(apiRequest).toString());
				if(listSchoolAge.next()) {
					listPUTCopySchoolAge(apiRequest, listSchoolAge, eventHandler);
				} else {
					response200PUTCopySchoolAge(siteRequest, eventHandler);
				}
			} else {
				errorSchoolAge(listSchoolAge.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolAge> putcopySchoolAgeFuture(SiteRequestEnUS siteRequest, JsonObject jsonObject, Handler<AsyncResult<SchoolAge>> eventHandler) {
		Promise<SchoolAge> promise = Promise.promise();
		try {

			jsonObject.put("saves", Optional.ofNullable(jsonObject.getJsonArray("saves")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("saves").add(o.getKey());
			});

			createSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolAge schoolAge = a.result();
					sqlPUTCopySchoolAge(schoolAge, jsonObject, b -> {
						if(b.succeeded()) {
							defineSchoolAge(schoolAge, c -> {
								if(c.succeeded()) {
									attributeSchoolAge(schoolAge, d -> {
										if(d.succeeded()) {
											indexSchoolAge(schoolAge, e -> {
												if(e.succeeded()) {
													eventHandler.handle(Future.succeededFuture(schoolAge));
													promise.complete(schoolAge);
												} else {
													eventHandler.handle(Future.failedFuture(e.cause()));
												}
											});
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
				} else {
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			errorSchoolAge(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopySchoolAge(SchoolAge o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			StringBuilder putSql = new StringBuilder();
			List<Object> putSqlParams = new ArrayList<Object>();

			if(jsonObject != null) {
				JsonArray entityVars = jsonObject.getJsonArray("saves");
				for(Integer i = 0; i < entityVars.size(); i++) {
					String entityVar = entityVars.getString(i);
					switch(entityVar) {
					case "inheritPk":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inheritPk", jsonObject.getString(entityVar), pk));
						break;
					case "created":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("created", jsonObject.getString(entityVar), pk));
						break;
					case "modified":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("modified", jsonObject.getString(entityVar), pk));
						break;
					case "archived":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("archived", jsonObject.getBoolean(entityVar), pk));
						break;
					case "deleted":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("deleted", jsonObject.getBoolean(entityVar), pk));
						break;
					case "sessionId":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("sessionId", jsonObject.getString(entityVar), pk));
						break;
					case "userId":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("userId", jsonObject.getString(entityVar), pk));
						break;
					case "userKey":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("userKey", jsonObject.getString(entityVar), pk));
						break;
					case "blockKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("ageKey", l, "blockKeys", pk));
						}
						break;
					case "sessionKey":
						putSql.append(SiteContextEnUS.SQL_addA);
						putSqlParams.addAll(Arrays.asList("ageKeys", Long.parseLong(jsonObject.getString(entityVar)), "sessionKey", pk));
						break;
					case "schoolAddress":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("schoolAddress", jsonObject.getString(entityVar), pk));
						break;
					case "ageStart":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("ageStart", jsonObject.getString(entityVar), pk));
						break;
					case "ageEnd":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("ageEnd", jsonObject.getString(entityVar), pk));
						break;
					}
				}
			}
			sqlConnection.queryWithParams(
					putSql.toString()
					, new JsonArray(putSqlParams)
					, postAsync
			-> {
				if(postAsync.succeeded()) {
					eventHandler.handle(Future.succeededFuture());
				} else {
					eventHandler.handle(Future.failedFuture(new Exception(postAsync.cause())));
				}
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void putcopySchoolAgeResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTCopySchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTCopySchoolAge(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSchoolAge(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("patchSchoolAge started. "));

			List<String> roles = Arrays.asList("SiteAdmin");
			if(
					!CollectionUtils.containsAny(siteRequest.getUserResourceRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest.getUserRealmRoles(), roles)
					) {
				eventHandler.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "roles required: " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolAge(siteRequest, b -> {
						if(b.succeeded()) {
							patchSchoolAgeResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest, body);
											try {
												aSearchSchoolAge(siteRequest2, false, true, "/api/age", "PATCH", d -> {
													if(d.succeeded()) {
														SearchList<SchoolAge> listSchoolAge = d.result();
														ApiRequest apiRequest = new ApiRequest();
														apiRequest.setRows(listSchoolAge.getRows());
														apiRequest.setNumFound(listSchoolAge.getQueryResponse().getResults().getNumFound());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest2);
														siteRequest2.setApiRequest_(apiRequest);
														siteRequest2.getVertx().eventBus().publish("websocketSchoolAge", JsonObject.mapFrom(apiRequest).toString());
														SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolAge.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
														Date date = null;
														if(facets != null)
															date = (Date)facets.get("max_modified");
														String dt;
														if(date == null)
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
														else
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
														listSchoolAge.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));

														SchoolAge o = listSchoolAge.getList().stream().findFirst().orElse(null);
														sqlSchoolAge(siteRequest2, e -> {
															if(e.succeeded()) {
																try {
																	listPATCHSchoolAge(apiRequest, listSchoolAge, dt, f -> {
																		if(f.succeeded()) {
																			patchSchoolAgeResponse(siteRequest2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("patchSchoolAge succeeded. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("patchSchoolAge failed. ", g.cause()));
																					errorSchoolAge(siteRequest2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("patchSchoolAge failed. ", f.cause()));
																			errorSchoolAge(siteRequest2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("patchSchoolAge failed. ", ex));
																	errorSchoolAge(siteRequest2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("patchSchoolAge failed. ", e.cause()));
																errorSchoolAge(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("patchSchoolAge failed. ", d.cause()));
														errorSchoolAge(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("patchSchoolAge failed. ", ex));
												errorSchoolAge(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("patchSchoolAge failed. ", c.cause()));
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("patchSchoolAge failed. ", b.cause()));
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("patchSchoolAge failed. ", a.cause()));
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPATCHSchoolAge(ApiRequest apiRequest, SearchList<SchoolAge> listSchoolAge, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolAge.getSiteRequest_();
		listSchoolAge.getList().forEach(o -> {
			futures.add(
				patchSchoolAgeFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						errorSchoolAge(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listSchoolAge.next(dt)) {
					listPATCHSchoolAge(apiRequest, listSchoolAge, dt, eventHandler);
				} else {
					response200PATCHSchoolAge(siteRequest, eventHandler);
				}
			} else {
				errorSchoolAge(listSchoolAge.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolAge> patchSchoolAgeFuture(SchoolAge o, Boolean inheritPk, Handler<AsyncResult<SchoolAge>> eventHandler) {
		Promise<SchoolAge> promise = Promise.promise();
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			if(apiRequest != null && apiRequest.getNumFound() == 1L) {
				apiRequest.setOriginal(o);
				apiRequest.setPk(o.getPk());
			}
			sqlPATCHSchoolAge(o, inheritPk, a -> {
				if(a.succeeded()) {
					SchoolAge schoolAge = a.result();
					defineSchoolAge(schoolAge, b -> {
						if(b.succeeded()) {
							attributeSchoolAge(schoolAge, c -> {
								if(c.succeeded()) {
									indexSchoolAge(schoolAge, d -> {
										if(d.succeeded()) {
											if(apiRequest != null) {
												apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
												apiRequestSchoolAge(schoolAge);
												if(apiRequest.getNumFound() == 1L) {
													schoolAge.apiRequestSchoolAge();
												}
												siteRequest.getVertx().eventBus().publish("websocketSchoolAge", JsonObject.mapFrom(apiRequest).toString());
											}
											eventHandler.handle(Future.succeededFuture(schoolAge));
											promise.complete(schoolAge);
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
				} else {
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			errorSchoolAge(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHSchoolAge(SchoolAge o, Boolean inheritPk, Handler<AsyncResult<SchoolAge>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = jsonObject.fieldNames();
			SchoolAge o2 = new SchoolAge();

			if(o.getUserId() == null && siteRequest.getUserId() != null) {
				patchSql.append(SiteContextEnUS.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("userId", siteRequest.getUserId(), pk));
			}
			if(o.getUserKey() == null && siteRequest.getUserKey() != null) {
				patchSql.append(SiteContextEnUS.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("userKey", siteRequest.getUserKey(), pk));
			}

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.age.SchoolAge"));
			for(String methodName : methodNames) {
				switch(methodName) {
					case "setInheritPk":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inheritPk"));
						} else {
							o2.setInheritPk(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inheritPk", o2.jsonInheritPk(), pk));
						}
						break;
					case "setCreated":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "created"));
						} else {
							o2.setCreated(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("created", o2.jsonCreated(), pk));
						}
						break;
					case "setModified":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "modified"));
						} else {
							o2.setModified(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("modified", o2.jsonModified(), pk));
						}
						break;
					case "setArchived":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "archived"));
						} else {
							o2.setArchived(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("archived", o2.jsonArchived(), pk));
						}
						break;
					case "setDeleted":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "deleted"));
						} else {
							o2.setDeleted(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("deleted", o2.jsonDeleted(), pk));
						}
						break;
					case "setSessionId":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sessionId"));
						} else {
							o2.setSessionId(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sessionId", o2.jsonSessionId(), pk));
						}
						break;
					case "setUserId":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "userId"));
						} else {
							o2.setUserId(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("userId", o2.jsonUserId(), pk));
						}
						break;
					case "setUserKey":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "userKey"));
						} else {
							o2.setUserKey(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("userKey", o2.jsonUserKey(), pk));
						}
						break;
					case "addBlockKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolBlock.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("ageKey", l, "blockKeys", pk));
								}
							}
						}
						break;
					case "addAllBlockKeys":
						JsonArray addAllBlockKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllBlockKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllBlockKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolBlock.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("ageKey", l, "blockKeys", pk));
								}
							}
						}
						break;
					case "setBlockKeys":
						JsonArray setBlockKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("ageKey", "blockKeys", pk));
						for(Integer i = 0; i <  setBlockKeysValues.size(); i++) {
							Long l = Long.parseLong(setBlockKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolBlock.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("ageKey", l, "blockKeys", pk));
								}
							}
						}
						break;
					case "removeBlockKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolBlock.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("ageKey", l, "blockKeys", pk));
								}
							}
						}
						break;
					case "setSessionKey":
						{
							Long l = o2.getSessionKey();
							if(l != null) {
								SearchList<SchoolSession> searchList = new SearchList<SchoolSession>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolSession.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setSessionKey(jsonObject.getString(methodName));
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("ageKeys", l, "sessionKey", pk));
								}
							}
						}
						break;
					case "removeSessionKey":
						{
							Long l = o2.getSessionKey();
							if(l != null) {
								SearchList<SchoolSession> searchList = new SearchList<SchoolSession>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolSession.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setSessionKey(jsonObject.getString(methodName));
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("ageKeys", l, "sessionKey", pk));
								}
							}
						}
						break;
					case "setSchoolAddress":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "schoolAddress"));
						} else {
							o2.setSchoolAddress(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("schoolAddress", o2.jsonSchoolAddress(), pk));
						}
						break;
					case "setAgeStart":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "ageStart"));
						} else {
							o2.setAgeStart(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("ageStart", o2.jsonAgeStart(), pk));
						}
						break;
					case "setAgeEnd":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "ageEnd"));
						} else {
							o2.setAgeEnd(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("ageEnd", o2.jsonAgeEnd(), pk));
						}
						break;
				}
			}
			sqlConnection.queryWithParams(
					patchSql.toString()
					, new JsonArray(patchSqlParams)
					, patchAsync
			-> {
				if(patchAsync.succeeded()) {
					SchoolAge o3 = new SchoolAge();
					o3.setSiteRequest_(o.getSiteRequest_());
					o3.setPk(pk);
					eventHandler.handle(Future.succeededFuture(o3));
				} else {
					eventHandler.handle(Future.failedFuture(new Exception(patchAsync.cause())));
				}
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void patchSchoolAgeResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PATCHSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PATCHSchoolAge(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSchoolAge(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest);
		try {

			List<String> roles = Arrays.asList("SiteAdmin");
			List<String> roleLires = Arrays.asList("");
			if(
					!CollectionUtils.containsAny(siteRequest.getUserResourceRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest.getUserRealmRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest.getUserResourceRoles(), roleLires)
					&& !CollectionUtils.containsAny(siteRequest.getUserRealmRoles(), roleLires)
					) {
				eventHandler.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "roles required: " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolAge(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolAge(siteRequest, false, true, "/api/age/{id}", "GET", c -> {
								if(c.succeeded()) {
									SearchList<SchoolAge> listSchoolAge = c.result();
									getSchoolAgeResponse(listSchoolAge, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getSchoolAge succeeded. "));
										} else {
											LOGGER.error(String.format("getSchoolAge failed. ", d.cause()));
											errorSchoolAge(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("getSchoolAge failed. ", c.cause()));
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("getSchoolAge failed. ", b.cause()));
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("getSchoolAge failed. ", a.cause()));
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void getSchoolAgeResponse(SearchList<SchoolAge> listSchoolAge, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolAge.getSiteRequest_();
		try {
			response200GETSchoolAge(listSchoolAge, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200GETSchoolAge(SearchList<SchoolAge> listSchoolAge, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolAge.getSiteRequest_();
			SolrDocumentList solrDocuments = listSchoolAge.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listSchoolAge.getList().stream().findFirst().orElse(null));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchSchoolAge(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest);
		try {

			List<String> roles = Arrays.asList("SiteAdmin");
			List<String> roleLires = Arrays.asList("");
			if(
					!CollectionUtils.containsAny(siteRequest.getUserResourceRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest.getUserRealmRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest.getUserResourceRoles(), roleLires)
					&& !CollectionUtils.containsAny(siteRequest.getUserRealmRoles(), roleLires)
					) {
				eventHandler.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "roles required: " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolAge(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolAge(siteRequest, false, true, "/api/age", "Search", c -> {
								if(c.succeeded()) {
									SearchList<SchoolAge> listSchoolAge = c.result();
									searchSchoolAgeResponse(listSchoolAge, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchSchoolAge succeeded. "));
										} else {
											LOGGER.error(String.format("searchSchoolAge failed. ", d.cause()));
											errorSchoolAge(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("searchSchoolAge failed. ", c.cause()));
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("searchSchoolAge failed. ", b.cause()));
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("searchSchoolAge failed. ", a.cause()));
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchSchoolAgeResponse(SearchList<SchoolAge> listSchoolAge, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolAge.getSiteRequest_();
		try {
			response200SearchSchoolAge(listSchoolAge, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchSchoolAge(SearchList<SchoolAge> listSchoolAge, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolAge.getSiteRequest_();
			QueryResponse responseSearch = listSchoolAge.getQueryResponse();
			SolrDocumentList solrDocuments = listSchoolAge.getSolrDocumentList();
			Long searchInMillis = Long.valueOf(responseSearch.getQTime());
			Long transmissionInMillis = responseSearch.getElapsedTime();
			Long startNum = responseSearch.getResults().getStart();
			Long foundNum = responseSearch.getResults().getNumFound();
			Integer returnedNum = responseSearch.getResults().size();
			String searchTime = String.format("%d.%03d sec", TimeUnit.MILLISECONDS.toSeconds(searchInMillis), TimeUnit.MILLISECONDS.toMillis(searchInMillis) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(searchInMillis)));
			String transmissionTime = String.format("%d.%03d sec", TimeUnit.MILLISECONDS.toSeconds(transmissionInMillis), TimeUnit.MILLISECONDS.toMillis(transmissionInMillis) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(transmissionInMillis)));
			Exception exceptionSearch = responseSearch.getException();

			JsonObject json = new JsonObject();
			json.put("startNum", startNum);
			json.put("foundNum", foundNum);
			json.put("returnedNum", returnedNum);
			json.put("searchTime", searchTime);
			json.put("transmissionTime", transmissionTime);
			JsonArray l = new JsonArray();
			listSchoolAge.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listSchoolAge.getFields();
				if(fls.size() > 0) {
					Set<String> fieldNames = new HashSet<String>();
					fieldNames.addAll(json2.fieldNames());
					if(fls.size() == 1 && fls.stream().findFirst().orElse(null).equals("saves")) {
						fieldNames.removeAll(Optional.ofNullable(json2.getJsonArray("saves")).orElse(new JsonArray()).stream().map(s -> s.toString()).collect(Collectors.toList()));
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
			json.put("list", l);
			if(exceptionSearch != null) {
				json.put("exceptionSearch", exceptionSearch.getMessage());
			}
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// SearchPage //

	@Override
	public void searchpageSchoolAgeId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageSchoolAge(operationRequest, eventHandler);
	}

	@Override
	public void searchpageSchoolAge(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest);
		try {

			List<String> roles = Arrays.asList("SiteAdmin");
			List<String> roleLires = Arrays.asList("");
			if(
					!CollectionUtils.containsAny(siteRequest.getUserResourceRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest.getUserRealmRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest.getUserResourceRoles(), roleLires)
					&& !CollectionUtils.containsAny(siteRequest.getUserRealmRoles(), roleLires)
					) {
				eventHandler.handle(Future.succeededFuture(
					new OperationResponse(401, "UNAUTHORIZED", 
						Buffer.buffer().appendString(
							new JsonObject()
								.put("errorCode", "401")
								.put("errorMessage", "roles required: " + String.join(", ", roles))
								.encodePrettily()
							), new CaseInsensitiveHeaders()
					)
				));
			}

			sqlSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolAge(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolAge(siteRequest, false, true, "/age", "SearchPage", c -> {
								if(c.succeeded()) {
									SearchList<SchoolAge> listSchoolAge = c.result();
									searchpageSchoolAgeResponse(listSchoolAge, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchpageSchoolAge succeeded. "));
										} else {
											LOGGER.error(String.format("searchpageSchoolAge failed. ", d.cause()));
											errorSchoolAge(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("searchpageSchoolAge failed. ", c.cause()));
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("searchpageSchoolAge failed. ", b.cause()));
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("searchpageSchoolAge failed. ", a.cause()));
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchpageSchoolAgeResponse(SearchList<SchoolAge> listSchoolAge, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolAge.getSiteRequest_();
		try {
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(siteRequest, buffer);
			siteRequest.setW(w);
			response200SearchPageSchoolAge(listSchoolAge, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchoolAge failed. ", ex));
			errorSchoolAge(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchPageSchoolAge(SearchList<SchoolAge> listSchoolAge, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolAge.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listSchoolAge.getSiteRequest_(), buffer);
			AgePage page = new AgePage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/age");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listSchoolAge.size() == 1)
				siteRequest.setRequestPk(listSchoolAge.get(0).getPk());
			siteRequest.setW(w);
			page.setListSchoolAge(listSchoolAge);
			page.setSiteRequest_(siteRequest);
			page.initDeepAgePage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<SchoolAge> defineIndexSchoolAge(SchoolAge schoolAge, Handler<AsyncResult<SchoolAge>> eventHandler) {
		Promise<SchoolAge> promise = Promise.promise();
		SiteRequestEnUS siteRequest = schoolAge.getSiteRequest_();
		defineSchoolAge(schoolAge, c -> {
			if(c.succeeded()) {
				attributeSchoolAge(schoolAge, d -> {
					if(d.succeeded()) {
						indexSchoolAge(schoolAge, e -> {
							if(e.succeeded()) {
								eventHandler.handle(Future.succeededFuture(schoolAge));
								promise.complete(schoolAge);
							} else {
								errorSchoolAge(siteRequest, null, e);
							}
						});
					} else {
						errorSchoolAge(siteRequest, null, d);
					}
				});
			} else {
				errorSchoolAge(siteRequest, null, c);
			}
		});
		return promise.future();
	}

	public void createSchoolAge(SiteRequestEnUS siteRequest, Handler<AsyncResult<SchoolAge>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(SchoolAge.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				SchoolAge o = new SchoolAge();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public ApiRequest apiRequestSchoolAge(SchoolAge o) {
		ApiRequest apiRequest = o.getSiteRequest_().getApiRequest_();
		if(apiRequest != null) {
			List<Long> pks = apiRequest.getPks();
			List<String> classes = apiRequest.getClasses();
			for(Long pk : o.getBlockKeys()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("SchoolBlock");
				}
			}
			if(o.getSessionKey() != null) {
				if(!pks.contains(o.getSessionKey())) {
					pks.add(o.getSessionKey());
					classes.add("SchoolSession");
				}
			}
		}
		return apiRequest;
	}

	public void errorSchoolAge(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
		Throwable e = resultAsync.cause();
		ExceptionUtils.printRootCauseStackTrace(e);
		OperationResponse responseOperation = new OperationResponse(400, "BAD REQUEST", 
			Buffer.buffer().appendString(
				new JsonObject() {{
					put("error", new JsonObject()
						.put("message", Optional.ofNullable(e).map(Throwable::getMessage).orElse(null))
					);
				}}.encodePrettily()
			)
			, new CaseInsensitiveHeaders()
		);
		SiteConfig siteConfig = siteRequest.getSiteConfig_();
		SiteContextEnUS siteContext = siteRequest.getSiteContext_();
		MailClient mailClient = siteContext.getMailClient();
		MailMessage message = new MailMessage();
		message.setFrom(siteConfig.getEmailFrom());
		message.setTo(siteConfig.getEmailAdmin());
		if(e != null)
			message.setText(ExceptionUtils.getStackTrace(e));
		message.setSubject(String.format(siteConfig.getSiteBaseUrl() + " " + Optional.ofNullable(e).map(Throwable::getMessage).orElse(null)));
		WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
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
		if(siteRequest != null) {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			if(sqlConnection != null) {
				sqlConnection.rollback(a -> {
					if(a.succeeded()) {
						LOGGER.info(String.format("sql rollback. "));
						sqlConnection.close(b -> {
							if(a.succeeded()) {
								LOGGER.info(String.format("sql close. "));
								if(eventHandler != null)
									eventHandler.handle(Future.succeededFuture(responseOperation));
							} else {
								if(eventHandler != null)
									eventHandler.handle(Future.succeededFuture(responseOperation));
							}
						});
					} else {
						if(eventHandler != null)
							eventHandler.handle(Future.succeededFuture(responseOperation));
					}
				});
			} else {
				if(eventHandler != null)
					eventHandler.handle(Future.succeededFuture(responseOperation));
			}
		}
	}

	public void sqlSchoolAge(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolAge(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolAge(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
		Vertx vertx = siteContext.getVertx();
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
		siteRequest.setJsonObject(body);
		siteRequest.setVertx(vertx);
		siteRequest.setSiteContext_(siteContext);
		siteRequest.setSiteConfig_(siteContext.getSiteConfig());
		siteRequest.setOperationRequest(operationRequest);
		siteRequest.initDeepSiteRequestEnUS(siteRequest);

		return siteRequest;
	}

	public void userSchoolAge(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			if(userId == null) {
				eventHandler.handle(Future.succeededFuture());
			} else {
				sqlConnection.queryWithParams(
						SiteContextEnUS.SQL_selectC
						, new JsonArray(Arrays.asList("org.computate.scolaire.enUS.user.SiteUser", userId))
						, selectCAsync
				-> {
					if(selectCAsync.succeeded()) {
						try {
							JsonArray userValues = selectCAsync.result().getResults().stream().findFirst().orElse(null);
							SiteUserEnUSApiServiceImpl userService = new SiteUserEnUSApiServiceImpl(siteContext);
							if(userValues == null) {
								JsonObject userVertx = siteRequest.getOperationRequest().getUser();
								JsonObject jsonPrincipal = KeycloakHelper.parseToken(userVertx.getString("access_token"));

								JsonObject jsonObject = new JsonObject();
								jsonObject.put("userName", jsonPrincipal.getString("preferred_username"));
								jsonObject.put("userFirstName", jsonPrincipal.getString("given_name"));
								jsonObject.put("userLastName", jsonPrincipal.getString("family_name"));
								jsonObject.put("userId", jsonPrincipal.getString("sub"));
								userSchoolAgeDefine(siteRequest, jsonObject, false);

								SiteRequestEnUS siteRequest2 = new SiteRequestEnUS();
								siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
								siteRequest2.setJsonObject(jsonObject);
								siteRequest2.setVertx(siteRequest.getVertx());
								siteRequest2.setSiteContext_(siteContext);
								siteRequest2.setSiteConfig_(siteContext.getSiteConfig());
								siteRequest2.setUserId(siteRequest.getUserId());
								siteRequest2.initDeepSiteRequestEnUS(siteRequest);

								ApiRequest apiRequest = new ApiRequest();
								apiRequest.setRows(1);
								apiRequest.setNumFound(1L);
								apiRequest.setNumPATCH(0L);
								apiRequest.initDeepApiRequest(siteRequest2);
								siteRequest2.setApiRequest_(apiRequest);

								userService.createSiteUser(siteRequest2, b -> {
									if(b.succeeded()) {
										SiteUser siteUser = b.result();
										userService.sqlPOSTSiteUser(siteUser, false, c -> {
											if(c.succeeded()) {
												userService.defineIndexSiteUser(siteUser, d -> {
													if(d.succeeded()) {
														siteRequest.setSiteUser(siteUser);
														siteRequest.setUserName(jsonPrincipal.getString("preferred_username"));
														siteRequest.setUserFirstName(jsonPrincipal.getString("given_name"));
														siteRequest.setUserLastName(jsonPrincipal.getString("family_name"));
														siteRequest.setUserId(jsonPrincipal.getString("sub"));
														siteRequest.setUserKey(siteUser.getPk());
														eventHandler.handle(Future.succeededFuture());
													} else {
														errorSchoolAge(siteRequest, eventHandler, d);
													}
												});
											} else {
												errorSchoolAge(siteRequest, eventHandler, c);
											}
										});
									} else {
										errorSchoolAge(siteRequest, eventHandler, b);
									}
								});
							} else {
								Long pkUser = userValues.getLong(0);
								SearchList<SiteUser> searchList = new SearchList<SiteUser>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SiteUser.class);
								searchList.addFilterQuery("userId_indexed_string:" + ClientUtils.escapeQueryChars(userId));
								searchList.addFilterQuery("pk_indexed_long:" + pkUser);
								searchList.initDeepSearchList(siteRequest);
								SiteUser siteUser1 = searchList.getList().stream().findFirst().orElse(null);

								JsonObject userVertx = siteRequest.getOperationRequest().getUser();
								JsonObject jsonPrincipal = KeycloakHelper.parseToken(userVertx.getString("access_token"));

								JsonObject jsonObject = new JsonObject();
								jsonObject.put("setUserName", jsonPrincipal.getString("preferred_username"));
								jsonObject.put("setUserFirstName", jsonPrincipal.getString("given_name"));
								jsonObject.put("setUserLastName", jsonPrincipal.getString("family_name"));
								jsonObject.put("setUserCompleteName", jsonPrincipal.getString("name"));
								jsonObject.put("setCustomerProfileId", Optional.ofNullable(siteUser1).map(u -> u.getCustomerProfileId()).orElse(null));
								jsonObject.put("setUserId", jsonPrincipal.getString("sub"));
								jsonObject.put("setUserEmail", jsonPrincipal.getString("email"));
								Boolean define = userSchoolAgeDefine(siteRequest, jsonObject, true);
								if(define) {
									SiteUser siteUser;
									if(siteUser1 == null) {
										siteUser = new SiteUser();
										siteUser.setPk(pkUser);
										siteUser.setSiteRequest_(siteRequest);
									} else {
										siteUser = siteUser1;
									}

									SiteRequestEnUS siteRequest2 = new SiteRequestEnUS();
									siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
									siteRequest2.setJsonObject(jsonObject);
									siteRequest2.setVertx(siteRequest.getVertx());
									siteRequest2.setSiteContext_(siteContext);
									siteRequest2.setSiteConfig_(siteContext.getSiteConfig());
									siteRequest2.setUserId(siteRequest.getUserId());
									siteRequest2.setUserKey(siteRequest.getUserKey());
									siteRequest2.initDeepSiteRequestEnUS(siteRequest);
									siteUser.setSiteRequest_(siteRequest2);

									ApiRequest apiRequest = new ApiRequest();
									apiRequest.setRows(1);
									apiRequest.setNumFound(1L);
									apiRequest.setNumPATCH(0L);
									apiRequest.initDeepApiRequest(siteRequest2);
									siteRequest2.setApiRequest_(apiRequest);

									userService.sqlPATCHSiteUser(siteUser, false, c -> {
										if(c.succeeded()) {
											SiteUser siteUser2 = c.result();
											userService.defineIndexSiteUser(siteUser2, d -> {
												if(d.succeeded()) {
													siteRequest.setSiteUser(siteUser2);
													siteRequest.setUserName(siteUser2.getUserName());
													siteRequest.setUserFirstName(siteUser2.getUserFirstName());
													siteRequest.setUserLastName(siteUser2.getUserLastName());
													siteRequest.setUserId(siteUser2.getUserId());
													siteRequest.setUserKey(siteUser2.getPk());
													eventHandler.handle(Future.succeededFuture());
												} else {
													errorSchoolAge(siteRequest, eventHandler, d);
												}
											});
										} else {
											errorSchoolAge(siteRequest, eventHandler, c);
										}
									});
								} else {
									siteRequest.setSiteUser(siteUser1);
									siteRequest.setUserName(siteUser1.getUserName());
									siteRequest.setUserFirstName(siteUser1.getUserFirstName());
									siteRequest.setUserLastName(siteUser1.getUserLastName());
									siteRequest.setUserId(siteUser1.getUserId());
									siteRequest.setUserKey(siteUser1.getPk());
									eventHandler.handle(Future.succeededFuture());
								}
							}
						} catch(Exception e) {
							eventHandler.handle(Future.failedFuture(e));
						}
					} else {
						eventHandler.handle(Future.failedFuture(new Exception(selectCAsync.cause())));
					}
				});
			}
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public Boolean userSchoolAgeDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void aSearchSchoolAgeQ(String uri, String apiMethod, SearchList<SchoolAge> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
			searchList.setHighlight(true);
			searchList.setHighlightSnippets(3);
			searchList.addHighlightField(varIndexed);
			searchList.setParam("hl.encoder", "html");
		}
	}

	public void aSearchSchoolAgeFq(String uri, String apiMethod, SearchList<SchoolAge> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
	}

	public void aSearchSchoolAgeSort(String uri, String apiMethod, SearchList<SchoolAge> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addSort(varIndexed, ORDER.valueOf(valueIndexed));
	}

	public void aSearchSchoolAgeRows(String uri, String apiMethod, SearchList<SchoolAge> searchList, Integer valueRows) {
			searchList.setRows(apiMethod != null && apiMethod.contains("Search") ? valueRows : 10);
	}

	public void aSearchSchoolAgeStart(String uri, String apiMethod, SearchList<SchoolAge> searchList, Integer valueStart) {
		searchList.setStart(valueStart);
	}

	public void aSearchSchoolAgeVar(String uri, String apiMethod, SearchList<SchoolAge> searchList, String var, String value) {
		searchList.getSiteRequest_().getRequestVars().put(var, value);
	}

	public void aSearchSchoolAgeUri(String uri, String apiMethod, SearchList<SchoolAge> searchList) {
	}

	public void aSearchSchoolAge(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String uri, String apiMethod, Handler<AsyncResult<SearchList<SchoolAge>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<SchoolAge> searchList = new SearchList<SchoolAge>();
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.setQuery("*:*");
			searchList.setC(SchoolAge.class);
			searchList.setSiteRequest_(siteRequest);
			if(entityList != null)
				searchList.addFields(entityList);
			searchList.add("json.facet", "{max_modified:'max(modified_indexed_date)'}");

			String id = operationRequest.getParams().getJsonObject("path").getString("id");
			if(id != null) {
				searchList.addFilterQuery("(id:" + ClientUtils.escapeQueryChars(id) + " OR objectId_indexed_string:" + ClientUtils.escapeQueryChars(id) + ")");
			}

			operationRequest.getParams().getJsonObject("query").forEach(paramRequest -> {
				String entityVar = null;
				String valueIndexed = null;
				String varIndexed = null;
				String valueSort = null;
				Integer valueStart = null;
				Integer valueRows = null;
				String paramName = paramRequest.getKey();
				Object paramValuesObject = paramRequest.getValue();
				JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);

				try {
					for(Object paramObject : paramObjects) {
						switch(paramName) {
							case "q":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								varIndexed = "*".equals(entityVar) ? entityVar : SchoolAge.varSearchSchoolAge(entityVar);
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								valueIndexed = StringUtils.isEmpty(valueIndexed) ? "*" : valueIndexed;
								aSearchSchoolAgeQ(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "fq":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = SchoolAge.varIndexedSchoolAge(entityVar);
								aSearchSchoolAgeFq(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = SchoolAge.varIndexedSchoolAge(entityVar);
								aSearchSchoolAgeSort(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "start":
								valueStart = (Integer)paramObject;
								aSearchSchoolAgeStart(uri, apiMethod, searchList, valueStart);
								break;
							case "rows":
								valueRows = (Integer)paramObject;
								aSearchSchoolAgeRows(uri, apiMethod, searchList, valueRows);
								break;
							case "var":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								aSearchSchoolAgeVar(uri, apiMethod, searchList, entityVar, valueIndexed);
								break;
						}
					}
					aSearchSchoolAgeUri(uri, apiMethod, searchList);
				} catch(Exception e) {
					eventHandler.handle(Future.failedFuture(e));
				}
			});
			if(searchList.getSorts().size() == 0) {
				searchList.addSort("created_indexed_date", ORDER.desc);
			}
			searchList.initDeepForClass(siteRequest);
			eventHandler.handle(Future.succeededFuture(searchList));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void defineSchoolAge(SchoolAge o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
							} catch(Exception e) {
								LOGGER.error(e);
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

	public void attributeSchoolAge(SchoolAge o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void indexSchoolAge(SchoolAge o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			if(BooleanUtils.isFalse(Optional.ofNullable(siteRequest.getApiRequest_()).map(ApiRequest::getEmpty).orElse(true))) {
				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolAge(siteContext, siteRequest.getOperationRequest(), new JsonObject());
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				SearchList<SchoolAge> searchList = new SearchList<SchoolAge>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolAge.class);
				searchList.addFilterQuery("modified_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(siteRequest.getApiRequest_().getCreated().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				searchList.add("json.facet", "{blockKeys:{terms:{field:blockKeys_indexed_longs, limit:1000}}}");
				searchList.add("json.facet", "{sessionKey:{terms:{field:sessionKey_indexed_longs, limit:1000}}}");
				searchList.setRows(1000);
				searchList.initDeepSearchList(siteRequest2);
				List<Future> futures = new ArrayList<>();

				{
					SchoolBlockEnUSGenApiServiceImpl service = new SchoolBlockEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
					for(Long pk : o.getBlockKeys()) {
					SearchList<SchoolBlock> searchList2 = new SearchList<SchoolBlock>();
					if(pk != null) {
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolBlock.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest2);
					}
					SchoolBlock o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1l);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());

							o2.setPk(pk);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolBlockFuture(o2, false, a -> {
									if(a.succeeded()) {
										LOGGER.info(String.format("SchoolBlock %s refreshed. ", pk));
									} else {
										LOGGER.info(String.format("SchoolBlock %s failed. ", pk));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				{
					Long pk = o.getSessionKey();
					SearchList<SchoolSession> searchList2 = new SearchList<SchoolSession>();
					if(pk != null) {
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolSession.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest2);
					}
					SchoolSession o2 = searchList2.getList().stream().findFirst().orElse(null);

					if(o2 != null) {
						SchoolSessionEnUSGenApiServiceImpl service = new SchoolSessionEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());

						ApiRequest apiRequest = new ApiRequest();
						apiRequest.setRows(1);
						apiRequest.setNumFound(1L);
						apiRequest.setNumPATCH(0L);
						apiRequest.initDeepApiRequest(siteRequest2);
						siteRequest2.setApiRequest_(apiRequest);
						siteRequest2.getVertx().eventBus().publish("websocketSchoolSession", JsonObject.mapFrom(apiRequest).toString());

						if(pk != null) {
							o2.setPk(pk);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolSessionFuture(o2, false, a -> {
									if(a.succeeded()) {
										LOGGER.info(String.format("SchoolSession %s refreshed. ", pk));
									} else {
										LOGGER.info(String.format("SchoolSession %s failed. ", pk));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						LOGGER.info("Refresh relations succeeded. ");
						SchoolAgeEnUSApiServiceImpl service = new SchoolAgeEnUSApiServiceImpl(siteRequest2.getSiteContext_());
						List<Future> futures2 = new ArrayList<>();
						for(SchoolAge o2 : searchList.getList()) {
							futures2.add(
								service.patchSchoolAgeFuture(o2, false, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("SchoolAge %s refreshed. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("SchoolAge %s failed. ", o2.getPk()));
										eventHandler.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Refresh SchoolAge succeeded. ");
								eventHandler.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Refresh relations failed. ", b.cause());
								errorSchoolAge(siteRequest2, eventHandler, b);
							}
						});
					} else {
						LOGGER.error("Refresh relations failed. ", a.cause());
						errorSchoolAge(siteRequest2, eventHandler, a);
					}
				});
			} else {
				eventHandler.handle(Future.succeededFuture());
			}
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}
}
