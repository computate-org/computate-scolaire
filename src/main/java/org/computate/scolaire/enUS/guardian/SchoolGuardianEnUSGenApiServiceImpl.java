package org.computate.scolaire.enUS.guardian;

import org.computate.scolaire.enUS.enrollment.SchoolEnrollmentEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
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
 * CanonicalName.frFR: org.computate.scolaire.frFR.gardien.GardienScolaireFrFRGenApiServiceImpl
 **/
public class SchoolGuardianEnUSGenApiServiceImpl implements SchoolGuardianEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolGuardianEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SchoolGuardianEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public SchoolGuardianEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
	}

	// POST //

	@Override
	public void postSchoolGuardian(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolGuardian(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("postSchoolGuardian started. "));
			sqlSchoolGuardian(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolGuardian(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							siteRequest.getVertx().eventBus().publish("websocketSchoolGuardian", JsonObject.mapFrom(apiRequest).toString());
							postSchoolGuardianFuture(siteRequest, false, c -> {
								if(c.succeeded()) {
									SchoolGuardian schoolGuardian = c.result();
									apiRequest.setPk(schoolGuardian.getPk());
									apiRequestSchoolGuardian(schoolGuardian);
									postSchoolGuardianResponse(schoolGuardian, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postSchoolGuardian succeeded. "));
										} else {
											LOGGER.error(String.format("postSchoolGuardian failed. ", d.cause()));
											errorSchoolGuardian(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("postSchoolGuardian failed. ", c.cause()));
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("postSchoolGuardian failed. ", b.cause()));
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("postSchoolGuardian failed. ", a.cause()));
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public Future<SchoolGuardian> postSchoolGuardianFuture(SiteRequestEnUS siteRequest, Boolean inheritPk, Handler<AsyncResult<SchoolGuardian>> eventHandler) {
		Promise<SchoolGuardian> promise = Promise.promise();
		try {
			createSchoolGuardian(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolGuardian schoolGuardian = a.result();
					sqlPOSTSchoolGuardian(schoolGuardian, inheritPk, b -> {
						if(b.succeeded()) {
							defineIndexSchoolGuardian(schoolGuardian, c -> {
								if(c.succeeded()) {
									ApiRequest apiRequest = siteRequest.getApiRequest_();
									if(apiRequest != null) {
										apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
										schoolGuardian.apiRequestSchoolGuardian();
										siteRequest.getVertx().eventBus().publish("websocketSchoolGuardian", JsonObject.mapFrom(apiRequest).toString());
									}
									eventHandler.handle(Future.succeededFuture(schoolGuardian));
									promise.complete(schoolGuardian);
								} else {
									errorSchoolGuardian(siteRequest, null, c);
								}
							});
						} else {
							errorSchoolGuardian(siteRequest, null, b);
						}
					});
				} else {
					errorSchoolGuardian(siteRequest, null, a);
				}
			});
		} catch(Exception e) {
			errorSchoolGuardian(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTSchoolGuardian(SchoolGuardian o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

				JsonArray userKeys = Optional.ofNullable(jsonObject.getJsonArray("userKeys")).orElse(null);
				if(userKeys != null && !userKeys.contains(siteRequest.getUserKey()))
					userKeys.add(siteRequest.getUserKey().toString());
				else
					jsonObject.put("userKeys", new JsonArray().add(siteRequest.getUserKey().toString()));
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
					case "enrollmentKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolEnrollment.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "guardianKeys", l));
								}
							}
						}
						break;
					case "personFirstName":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personFirstName", jsonObject.getString(entityVar), pk));
						break;
					case "personFirstNamePreferred":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personFirstNamePreferred", jsonObject.getString(entityVar), pk));
						break;
					case "familyName":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("familyName", jsonObject.getString(entityVar), pk));
						break;
					case "personPhoneNumber":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personPhoneNumber", jsonObject.getString(entityVar), pk));
						break;
					case "personRelation":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personRelation", jsonObject.getString(entityVar), pk));
						break;
					case "personEmergencyContact":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personEmergencyContact", jsonObject.getBoolean(entityVar), pk));
						break;
					case "personPickup":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personPickup", jsonObject.getBoolean(entityVar), pk));
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

	public void postSchoolGuardianResponse(SchoolGuardian schoolGuardian, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = schoolGuardian.getSiteRequest_();
		try {
			response200POSTSchoolGuardian(schoolGuardian, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200POSTSchoolGuardian(SchoolGuardian o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
	public void putimportSchoolGuardian(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolGuardian(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putimportSchoolGuardian started. "));
			sqlSchoolGuardian(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolGuardian(siteRequest, b -> {
						if(b.succeeded()) {
							putimportSchoolGuardianResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolGuardian(siteContext, operationRequest, body);
											try {
												ApiRequest apiRequest = new ApiRequest();
												JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												apiRequest.setRows(jsonArray.size());
												apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketSchoolGuardian", JsonObject.mapFrom(apiRequest).toString());
												sqlSchoolGuardian(siteRequest2, d -> {
													if(d.succeeded()) {
														listPUTImportSchoolGuardian(apiRequest, siteRequest2, e -> {
															if(e.succeeded()) {
																putimportSchoolGuardianResponse(siteRequest2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putimportSchoolGuardian succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putimportSchoolGuardian failed. ", f.cause()));
																		errorSchoolGuardian(siteRequest2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putimportSchoolGuardian failed. ", e.cause()));
																errorSchoolGuardian(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putimportSchoolGuardian failed. ", d.cause()));
														errorSchoolGuardian(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putimportSchoolGuardian failed. ", ex));
												errorSchoolGuardian(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putimportSchoolGuardian failed. ", c.cause()));
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putimportSchoolGuardian failed. ", b.cause()));
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putimportSchoolGuardian failed. ", a.cause()));
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTImportSchoolGuardian(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolGuardian(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());

				SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolGuardian.class);
				searchList.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					SchoolGuardian o = searchList.getList().stream().findFirst().orElse(null);
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
							patchSchoolGuardianFuture(o, true, a -> {
								if(a.succeeded()) {
									SchoolGuardian schoolGuardian = a.result();
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									siteRequest2.getVertx().eventBus().publish("websocketSchoolGuardian", JsonObject.mapFrom(apiRequest).toString());
								} else {
									errorSchoolGuardian(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolGuardianFuture(siteRequest2, true, a -> {
							if(a.succeeded()) {
								SchoolGuardian schoolGuardian = a.result();
								apiRequestSchoolGuardian(schoolGuardian);
							} else {
								errorSchoolGuardian(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
								apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
								siteRequest.getVertx().eventBus().publish("websocketSchoolGuardian", JsonObject.mapFrom(apiRequest).toString());
					response200PUTImportSchoolGuardian(siteRequest, eventHandler);
				} else {
					errorSchoolGuardian(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putimportSchoolGuardianResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTImportSchoolGuardian(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTImportSchoolGuardian(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTMerge //

	@Override
	public void putmergeSchoolGuardian(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolGuardian(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putmergeSchoolGuardian started. "));
			sqlSchoolGuardian(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolGuardian(siteRequest, b -> {
						if(b.succeeded()) {
							putmergeSchoolGuardianResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolGuardian(siteContext, operationRequest, body);
											try {
												ApiRequest apiRequest = new ApiRequest();
												JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												apiRequest.setRows(jsonArray.size());
												apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketSchoolGuardian", JsonObject.mapFrom(apiRequest).toString());
												sqlSchoolGuardian(siteRequest2, d -> {
													if(d.succeeded()) {
														listPUTMergeSchoolGuardian(apiRequest, siteRequest2, e -> {
															if(e.succeeded()) {
																putmergeSchoolGuardianResponse(siteRequest2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putmergeSchoolGuardian succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putmergeSchoolGuardian failed. ", f.cause()));
																		errorSchoolGuardian(siteRequest2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putmergeSchoolGuardian failed. ", e.cause()));
																errorSchoolGuardian(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putmergeSchoolGuardian failed. ", d.cause()));
														errorSchoolGuardian(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putmergeSchoolGuardian failed. ", ex));
												errorSchoolGuardian(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putmergeSchoolGuardian failed. ", c.cause()));
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putmergeSchoolGuardian failed. ", b.cause()));
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putmergeSchoolGuardian failed. ", a.cause()));
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTMergeSchoolGuardian(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolGuardian(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());

				SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolGuardian.class);
				searchList.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					SchoolGuardian o = searchList.getList().stream().findFirst().orElse(null);
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
							patchSchoolGuardianFuture(o, false, a -> {
								if(a.succeeded()) {
									SchoolGuardian schoolGuardian = a.result();
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									siteRequest2.getVertx().eventBus().publish("websocketSchoolGuardian", JsonObject.mapFrom(apiRequest).toString());
								} else {
									errorSchoolGuardian(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolGuardianFuture(siteRequest2, false, a -> {
							if(a.succeeded()) {
								SchoolGuardian schoolGuardian = a.result();
								apiRequestSchoolGuardian(schoolGuardian);
							} else {
								errorSchoolGuardian(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
								apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
								siteRequest.getVertx().eventBus().publish("websocketSchoolGuardian", JsonObject.mapFrom(apiRequest).toString());
					response200PUTMergeSchoolGuardian(siteRequest, eventHandler);
				} else {
					errorSchoolGuardian(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putmergeSchoolGuardianResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTMergeSchoolGuardian(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTMergeSchoolGuardian(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTCopy //

	@Override
	public void putcopySchoolGuardian(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolGuardian(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putcopySchoolGuardian started. "));
			sqlSchoolGuardian(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolGuardian(siteRequest, b -> {
						if(b.succeeded()) {
							putcopySchoolGuardianResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolGuardian(siteContext, operationRequest, body);
											try {
												aSearchSchoolGuardian(siteRequest2, false, true, "/api/guardian/copy", "PUTCopy", d -> {
													if(d.succeeded()) {
														SearchList<SchoolGuardian> listSchoolGuardian = d.result();
														ApiRequest apiRequest = new ApiRequest();
														apiRequest.setRows(listSchoolGuardian.getRows());
														apiRequest.setNumFound(listSchoolGuardian.getQueryResponse().getResults().getNumFound());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest2);
														siteRequest2.setApiRequest_(apiRequest);
														siteRequest2.getVertx().eventBus().publish("websocketSchoolGuardian", JsonObject.mapFrom(apiRequest).toString());
														sqlSchoolGuardian(siteRequest2, e -> {
															if(e.succeeded()) {
																try {
																	listPUTCopySchoolGuardian(apiRequest, listSchoolGuardian, f -> {
																		if(f.succeeded()) {
																			putcopySchoolGuardianResponse(siteRequest2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("putcopySchoolGuardian succeeded. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("putcopySchoolGuardian failed. ", g.cause()));
																					errorSchoolGuardian(siteRequest2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("putcopySchoolGuardian failed. ", f.cause()));
																			errorSchoolGuardian(siteRequest2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("putcopySchoolGuardian failed. ", ex));
																	errorSchoolGuardian(siteRequest2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("putcopySchoolGuardian failed. ", e.cause()));
																errorSchoolGuardian(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putcopySchoolGuardian failed. ", d.cause()));
														errorSchoolGuardian(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putcopySchoolGuardian failed. ", ex));
												errorSchoolGuardian(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putcopySchoolGuardian failed. ", c.cause()));
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putcopySchoolGuardian failed. ", b.cause()));
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putcopySchoolGuardian failed. ", a.cause()));
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTCopySchoolGuardian(ApiRequest apiRequest, SearchList<SchoolGuardian> listSchoolGuardian, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolGuardian.getSiteRequest_();
		listSchoolGuardian.getList().forEach(o -> {
			futures.add(
				putcopySchoolGuardianFuture(siteRequest, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						SchoolGuardian schoolGuardian = a.result();
						apiRequestSchoolGuardian(schoolGuardian);
					} else {
						errorSchoolGuardian(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchoolGuardian.size());
				siteRequest.getVertx().eventBus().publish("websocketSchoolGuardian", JsonObject.mapFrom(apiRequest).toString());
				if(listSchoolGuardian.next()) {
					listPUTCopySchoolGuardian(apiRequest, listSchoolGuardian, eventHandler);
				} else {
					response200PUTCopySchoolGuardian(siteRequest, eventHandler);
				}
			} else {
				errorSchoolGuardian(listSchoolGuardian.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolGuardian> putcopySchoolGuardianFuture(SiteRequestEnUS siteRequest, JsonObject jsonObject, Handler<AsyncResult<SchoolGuardian>> eventHandler) {
		Promise<SchoolGuardian> promise = Promise.promise();
		try {

			jsonObject.put("saves", Optional.ofNullable(jsonObject.getJsonArray("saves")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("saves").add(o.getKey());
			});

			createSchoolGuardian(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolGuardian schoolGuardian = a.result();
					sqlPUTCopySchoolGuardian(schoolGuardian, jsonObject, b -> {
						if(b.succeeded()) {
							defineSchoolGuardian(schoolGuardian, c -> {
								if(c.succeeded()) {
									attributeSchoolGuardian(schoolGuardian, d -> {
										if(d.succeeded()) {
											indexSchoolGuardian(schoolGuardian, e -> {
												if(e.succeeded()) {
													eventHandler.handle(Future.succeededFuture(schoolGuardian));
													promise.complete(schoolGuardian);
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
			errorSchoolGuardian(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopySchoolGuardian(SchoolGuardian o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "enrollmentKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "guardianKeys", l));
						}
						break;
					case "personFirstName":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personFirstName", jsonObject.getString(entityVar), pk));
						break;
					case "personFirstNamePreferred":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personFirstNamePreferred", jsonObject.getString(entityVar), pk));
						break;
					case "familyName":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("familyName", jsonObject.getString(entityVar), pk));
						break;
					case "personPhoneNumber":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personPhoneNumber", jsonObject.getString(entityVar), pk));
						break;
					case "personRelation":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personRelation", jsonObject.getString(entityVar), pk));
						break;
					case "personEmergencyContact":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personEmergencyContact", jsonObject.getBoolean(entityVar), pk));
						break;
					case "personPickup":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personPickup", jsonObject.getBoolean(entityVar), pk));
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

	public void putcopySchoolGuardianResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTCopySchoolGuardian(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTCopySchoolGuardian(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSchoolGuardian(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolGuardian(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("patchSchoolGuardian started. "));
			sqlSchoolGuardian(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolGuardian(siteRequest, b -> {
						if(b.succeeded()) {
							patchSchoolGuardianResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolGuardian(siteContext, operationRequest, body);
											try {
												aSearchSchoolGuardian(siteRequest2, false, true, "/api/guardian", "PATCH", d -> {
													if(d.succeeded()) {
														SearchList<SchoolGuardian> listSchoolGuardian = d.result();
														ApiRequest apiRequest = new ApiRequest();
														apiRequest.setRows(listSchoolGuardian.getRows());
														apiRequest.setNumFound(listSchoolGuardian.getQueryResponse().getResults().getNumFound());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest2);
														siteRequest2.setApiRequest_(apiRequest);
														siteRequest2.getVertx().eventBus().publish("websocketSchoolGuardian", JsonObject.mapFrom(apiRequest).toString());
														SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolGuardian.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
														Date date = null;
														if(facets != null)
															date = (Date)facets.get("max_modified");
														String dt;
														if(date == null)
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
														else
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
														listSchoolGuardian.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));

														SchoolGuardian o = listSchoolGuardian.getList().stream().findFirst().orElse(null);
														sqlSchoolGuardian(siteRequest2, e -> {
															if(e.succeeded()) {
																try {
																	listPATCHSchoolGuardian(apiRequest, listSchoolGuardian, dt, f -> {
																		if(f.succeeded()) {
																			patchSchoolGuardianResponse(siteRequest2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("patchSchoolGuardian succeeded. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("patchSchoolGuardian failed. ", g.cause()));
																					errorSchoolGuardian(siteRequest2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("patchSchoolGuardian failed. ", f.cause()));
																			errorSchoolGuardian(siteRequest2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("patchSchoolGuardian failed. ", ex));
																	errorSchoolGuardian(siteRequest2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("patchSchoolGuardian failed. ", e.cause()));
																errorSchoolGuardian(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("patchSchoolGuardian failed. ", d.cause()));
														errorSchoolGuardian(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("patchSchoolGuardian failed. ", ex));
												errorSchoolGuardian(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("patchSchoolGuardian failed. ", c.cause()));
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("patchSchoolGuardian failed. ", b.cause()));
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("patchSchoolGuardian failed. ", a.cause()));
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPATCHSchoolGuardian(ApiRequest apiRequest, SearchList<SchoolGuardian> listSchoolGuardian, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolGuardian.getSiteRequest_();
		listSchoolGuardian.getList().forEach(o -> {
			futures.add(
				patchSchoolGuardianFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						errorSchoolGuardian(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listSchoolGuardian.next(dt)) {
					listPATCHSchoolGuardian(apiRequest, listSchoolGuardian, dt, eventHandler);
				} else {
					response200PATCHSchoolGuardian(siteRequest, eventHandler);
				}
			} else {
				errorSchoolGuardian(listSchoolGuardian.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolGuardian> patchSchoolGuardianFuture(SchoolGuardian o, Boolean inheritPk, Handler<AsyncResult<SchoolGuardian>> eventHandler) {
		Promise<SchoolGuardian> promise = Promise.promise();
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			if(apiRequest != null && apiRequest.getNumFound() == 1L) {
				apiRequest.setOriginal(o);
				apiRequest.setPk(o.getPk());
			}
			sqlPATCHSchoolGuardian(o, inheritPk, a -> {
				if(a.succeeded()) {
					SchoolGuardian schoolGuardian = a.result();
					defineSchoolGuardian(schoolGuardian, b -> {
						if(b.succeeded()) {
							attributeSchoolGuardian(schoolGuardian, c -> {
								if(c.succeeded()) {
									indexSchoolGuardian(schoolGuardian, d -> {
										if(d.succeeded()) {
											if(apiRequest != null) {
												apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
												apiRequestSchoolGuardian(schoolGuardian);
												if(apiRequest.getNumFound() == 1L) {
													schoolGuardian.apiRequestSchoolGuardian();
												}
												siteRequest.getVertx().eventBus().publish("websocketSchoolGuardian", JsonObject.mapFrom(apiRequest).toString());
											}
											eventHandler.handle(Future.succeededFuture(schoolGuardian));
											promise.complete(schoolGuardian);
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
			errorSchoolGuardian(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHSchoolGuardian(SchoolGuardian o, Boolean inheritPk, Handler<AsyncResult<SchoolGuardian>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = jsonObject.fieldNames();
			SchoolGuardian o2 = new SchoolGuardian();

			if(o.getUserId() == null && siteRequest.getUserId() != null) {
				patchSql.append(SiteContextEnUS.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("userId", siteRequest.getUserId(), pk));
			}
			if(o.getUserKey() == null && siteRequest.getUserKey() != null) {
				patchSql.append(SiteContextEnUS.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("userKey", siteRequest.getUserKey(), pk));

				JsonArray userKeys = Optional.ofNullable(jsonObject.getJsonArray("addUserKeys")).orElse(null);
				if(userKeys != null && !userKeys.contains(siteRequest.getUserKey()))
					userKeys.add(siteRequest.getUserKey().toString());
				else
					jsonObject.put("addUserKeys", new JsonArray().add(siteRequest.getUserKey().toString()));
			}

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.guardian.SchoolGuardian"));
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
					case "addEnrollmentKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolEnrollment.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "guardianKeys", l));
								}
							}
						}
						break;
					case "addAllEnrollmentKeys":
						JsonArray addAllEnrollmentKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllEnrollmentKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllEnrollmentKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolEnrollment.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "guardianKeys", l));
								}
							}
						}
						break;
					case "setEnrollmentKeys":
						JsonArray setEnrollmentKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "guardianKeys"));
						for(Integer i = 0; i <  setEnrollmentKeysValues.size(); i++) {
							Long l = Long.parseLong(setEnrollmentKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolEnrollment.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "guardianKeys", l));
								}
							}
						}
						break;
					case "removeEnrollmentKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolEnrollment.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "guardianKeys", l));
								}
							}
						}
						break;
					case "setPersonFirstName":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personFirstName"));
						} else {
							o2.setPersonFirstName(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personFirstName", o2.jsonPersonFirstName(), pk));
						}
						break;
					case "setPersonFirstNamePreferred":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personFirstNamePreferred"));
						} else {
							o2.setPersonFirstNamePreferred(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personFirstNamePreferred", o2.jsonPersonFirstNamePreferred(), pk));
						}
						break;
					case "setFamilyName":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familyName"));
						} else {
							o2.setFamilyName(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familyName", o2.jsonFamilyName(), pk));
						}
						break;
					case "setPersonPhoneNumber":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personPhoneNumber"));
						} else {
							o2.setPersonPhoneNumber(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personPhoneNumber", o2.jsonPersonPhoneNumber(), pk));
						}
						break;
					case "setPersonRelation":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personRelation"));
						} else {
							o2.setPersonRelation(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personRelation", o2.jsonPersonRelation(), pk));
						}
						break;
					case "setPersonEmergencyContact":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personEmergencyContact"));
						} else {
							o2.setPersonEmergencyContact(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personEmergencyContact", o2.jsonPersonEmergencyContact(), pk));
						}
						break;
					case "setPersonPickup":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personPickup"));
						} else {
							o2.setPersonPickup(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personPickup", o2.jsonPersonPickup(), pk));
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
					SchoolGuardian o3 = new SchoolGuardian();
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

	public void patchSchoolGuardianResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PATCHSchoolGuardian(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PATCHSchoolGuardian(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSchoolGuardian(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolGuardian(siteContext, operationRequest);
		try {
			sqlSchoolGuardian(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolGuardian(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolGuardian(siteRequest, false, true, "/api/guardian/{id}", "GET", c -> {
								if(c.succeeded()) {
									SearchList<SchoolGuardian> listSchoolGuardian = c.result();
									getSchoolGuardianResponse(listSchoolGuardian, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getSchoolGuardian succeeded. "));
										} else {
											LOGGER.error(String.format("getSchoolGuardian failed. ", d.cause()));
											errorSchoolGuardian(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("getSchoolGuardian failed. ", c.cause()));
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("getSchoolGuardian failed. ", b.cause()));
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("getSchoolGuardian failed. ", a.cause()));
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void getSchoolGuardianResponse(SearchList<SchoolGuardian> listSchoolGuardian, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolGuardian.getSiteRequest_();
		try {
			response200GETSchoolGuardian(listSchoolGuardian, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200GETSchoolGuardian(SearchList<SchoolGuardian> listSchoolGuardian, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolGuardian.getSiteRequest_();
			SolrDocumentList solrDocuments = listSchoolGuardian.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listSchoolGuardian.getList().stream().findFirst().orElse(null));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchSchoolGuardian(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolGuardian(siteContext, operationRequest);
		try {
			sqlSchoolGuardian(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolGuardian(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolGuardian(siteRequest, false, true, "/api/guardian", "Search", c -> {
								if(c.succeeded()) {
									SearchList<SchoolGuardian> listSchoolGuardian = c.result();
									searchSchoolGuardianResponse(listSchoolGuardian, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchSchoolGuardian succeeded. "));
										} else {
											LOGGER.error(String.format("searchSchoolGuardian failed. ", d.cause()));
											errorSchoolGuardian(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("searchSchoolGuardian failed. ", c.cause()));
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("searchSchoolGuardian failed. ", b.cause()));
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("searchSchoolGuardian failed. ", a.cause()));
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchSchoolGuardianResponse(SearchList<SchoolGuardian> listSchoolGuardian, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolGuardian.getSiteRequest_();
		try {
			response200SearchSchoolGuardian(listSchoolGuardian, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchSchoolGuardian(SearchList<SchoolGuardian> listSchoolGuardian, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolGuardian.getSiteRequest_();
			QueryResponse responseSearch = listSchoolGuardian.getQueryResponse();
			SolrDocumentList solrDocuments = listSchoolGuardian.getSolrDocumentList();
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
			listSchoolGuardian.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listSchoolGuardian.getFields();
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
	public void searchpageSchoolGuardianId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageSchoolGuardian(operationRequest, eventHandler);
	}

	@Override
	public void searchpageSchoolGuardian(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolGuardian(siteContext, operationRequest);
		try {
			sqlSchoolGuardian(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolGuardian(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolGuardian(siteRequest, false, true, "/guardian", "SearchPage", c -> {
								if(c.succeeded()) {
									SearchList<SchoolGuardian> listSchoolGuardian = c.result();
									searchpageSchoolGuardianResponse(listSchoolGuardian, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchpageSchoolGuardian succeeded. "));
										} else {
											LOGGER.error(String.format("searchpageSchoolGuardian failed. ", d.cause()));
											errorSchoolGuardian(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("searchpageSchoolGuardian failed. ", c.cause()));
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("searchpageSchoolGuardian failed. ", b.cause()));
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("searchpageSchoolGuardian failed. ", a.cause()));
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchpageSchoolGuardianResponse(SearchList<SchoolGuardian> listSchoolGuardian, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolGuardian.getSiteRequest_();
		try {
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(siteRequest, buffer);
			siteRequest.setW(w);
			response200SearchPageSchoolGuardian(listSchoolGuardian, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolGuardian(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolGuardian(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolGuardian(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchoolGuardian failed. ", ex));
			errorSchoolGuardian(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchPageSchoolGuardian(SearchList<SchoolGuardian> listSchoolGuardian, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolGuardian.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listSchoolGuardian.getSiteRequest_(), buffer);
			GuardianPage page = new GuardianPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/guardian");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listSchoolGuardian.size() == 1)
				siteRequest.setRequestPk(listSchoolGuardian.get(0).getPk());
			siteRequest.setW(w);
			page.setListSchoolGuardian(listSchoolGuardian);
			page.setSiteRequest_(siteRequest);
			page.initDeepGuardianPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<SchoolGuardian> defineIndexSchoolGuardian(SchoolGuardian schoolGuardian, Handler<AsyncResult<SchoolGuardian>> eventHandler) {
		Promise<SchoolGuardian> promise = Promise.promise();
		SiteRequestEnUS siteRequest = schoolGuardian.getSiteRequest_();
		defineSchoolGuardian(schoolGuardian, c -> {
			if(c.succeeded()) {
				attributeSchoolGuardian(schoolGuardian, d -> {
					if(d.succeeded()) {
						indexSchoolGuardian(schoolGuardian, e -> {
							if(e.succeeded()) {
								eventHandler.handle(Future.succeededFuture(schoolGuardian));
								promise.complete(schoolGuardian);
							} else {
								errorSchoolGuardian(siteRequest, null, e);
							}
						});
					} else {
						errorSchoolGuardian(siteRequest, null, d);
					}
				});
			} else {
				errorSchoolGuardian(siteRequest, null, c);
			}
		});
		return promise.future();
	}

	public void createSchoolGuardian(SiteRequestEnUS siteRequest, Handler<AsyncResult<SchoolGuardian>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(SchoolGuardian.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				SchoolGuardian o = new SchoolGuardian();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public ApiRequest apiRequestSchoolGuardian(SchoolGuardian o) {
		ApiRequest apiRequest = o.getSiteRequest_().getApiRequest_();
		if(apiRequest != null) {
			List<Long> pks = apiRequest.getPks();
			List<String> classes = apiRequest.getClasses();
			for(Long pk : o.getEnrollmentKeys()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("SchoolEnrollment");
				}
			}
		}
		return apiRequest;
	}

	public void errorSchoolGuardian(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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
		SiteConfig siteConfig = siteRequest.getSiteConfig_();
		SiteContextEnUS siteContext = siteRequest.getSiteContext_();
		MailClient mailClient = siteContext.getMailClient();
		MailMessage message = new MailMessage();
		message.setFrom(siteConfig.getEmailFrom());
		message.setTo(siteConfig.getEmailAdmin());
		message.setText(ExceptionUtils.getStackTrace(e));
		message.setSubject(String.format(siteConfig.getSiteBaseUrl() + " " + e.getMessage()));
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

	public void sqlSchoolGuardian(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolGuardian(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForSchoolGuardian(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolGuardian(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userSchoolGuardian(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
								userSchoolGuardianDefine(siteRequest, jsonObject, false);

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
														errorSchoolGuardian(siteRequest, eventHandler, d);
													}
												});
											} else {
												errorSchoolGuardian(siteRequest, eventHandler, c);
											}
										});
									} else {
										errorSchoolGuardian(siteRequest, eventHandler, b);
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
								Boolean define = userSchoolGuardianDefine(siteRequest, jsonObject, true);
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
													errorSchoolGuardian(siteRequest, eventHandler, d);
												}
											});
										} else {
											errorSchoolGuardian(siteRequest, eventHandler, c);
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

	public Boolean userSchoolGuardianDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void aSearchSchoolGuardianQ(String uri, String apiMethod, SearchList<SchoolGuardian> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
			searchList.setHighlight(true);
			searchList.setHighlightSnippets(3);
			searchList.addHighlightField(varIndexed);
			searchList.setParam("hl.encoder", "html");
		}
	}

	public void aSearchSchoolGuardianFq(String uri, String apiMethod, SearchList<SchoolGuardian> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
	}

	public void aSearchSchoolGuardianSort(String uri, String apiMethod, SearchList<SchoolGuardian> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addSort(varIndexed, ORDER.valueOf(valueIndexed));
	}

	public void aSearchSchoolGuardianRows(String uri, String apiMethod, SearchList<SchoolGuardian> searchList, Integer valueRows) {
			searchList.setRows(apiMethod != null && apiMethod.contains("Search") ? valueRows : 10);
	}

	public void aSearchSchoolGuardianStart(String uri, String apiMethod, SearchList<SchoolGuardian> searchList, Integer valueStart) {
		searchList.setStart(valueStart);
	}

	public void aSearchSchoolGuardianVar(String uri, String apiMethod, SearchList<SchoolGuardian> searchList, String var, String value) {
		searchList.getSiteRequest_().getRequestVars().put(var, value);
	}

	public void aSearchSchoolGuardianUri(String uri, String apiMethod, SearchList<SchoolGuardian> searchList) {
	}

	public void aSearchSchoolGuardian(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String uri, String apiMethod, Handler<AsyncResult<SearchList<SchoolGuardian>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.setQuery("*:*");
			searchList.setC(SchoolGuardian.class);
			searchList.setSiteRequest_(siteRequest);
			if(entityList != null)
				searchList.addFields(entityList);
			searchList.add("json.facet", "{max_modified:'max(modified_indexed_date)'}");

			String id = operationRequest.getParams().getJsonObject("path").getString("id");
			if(id != null) {
				searchList.addFilterQuery("(id:" + ClientUtils.escapeQueryChars(id) + " OR objectId_indexed_string:" + ClientUtils.escapeQueryChars(id) + ")");
			}

			List<String> roles = Arrays.asList("SiteAdmin");
			if(
					!CollectionUtils.containsAny(siteRequest.getUserResourceRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest.getUserRealmRoles(), roles)
					) {
				searchList.addFilterQuery("sessionId_indexed_string:" + ClientUtils.escapeQueryChars(Optional.ofNullable(siteRequest.getSessionId()).orElse("-----"))
						+ " OR userKeys_indexed_longs:" + Optional.ofNullable(siteRequest.getUserKey()).orElse(0L));
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
								varIndexed = "*".equals(entityVar) ? entityVar : SchoolGuardian.varSearchSchoolGuardian(entityVar);
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								valueIndexed = StringUtils.isEmpty(valueIndexed) ? "*" : valueIndexed;
								aSearchSchoolGuardianQ(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "fq":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = SchoolGuardian.varIndexedSchoolGuardian(entityVar);
								aSearchSchoolGuardianFq(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = SchoolGuardian.varIndexedSchoolGuardian(entityVar);
								aSearchSchoolGuardianSort(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "start":
								valueStart = (Integer)paramObject;
								aSearchSchoolGuardianStart(uri, apiMethod, searchList, valueStart);
								break;
							case "rows":
								valueRows = (Integer)paramObject;
								aSearchSchoolGuardianRows(uri, apiMethod, searchList, valueRows);
								break;
							case "var":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								aSearchSchoolGuardianVar(uri, apiMethod, searchList, entityVar, valueIndexed);
								break;
						}
					}
					aSearchSchoolGuardianUri(uri, apiMethod, searchList);
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

	public void defineSchoolGuardian(SchoolGuardian o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void attributeSchoolGuardian(SchoolGuardian o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void indexSchoolGuardian(SchoolGuardian o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			if(BooleanUtils.isFalse(Optional.ofNullable(siteRequest.getApiRequest_()).map(ApiRequest::getEmpty).orElse(null))) {
				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolGuardian(siteContext, siteRequest.getOperationRequest(), new JsonObject());
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolGuardian.class);
				searchList.addFilterQuery("modified_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(siteRequest.getApiRequest_().getCreated().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				searchList.add("json.facet", "{enrollmentKeys:{terms:{field:enrollmentKeys_indexed_longs, limit:1000}}}");
				searchList.setRows(1000);
				searchList.initDeepSearchList(siteRequest2);
				List<Future> futures = new ArrayList<>();

				{
					SchoolEnrollmentEnUSGenApiServiceImpl service = new SchoolEnrollmentEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
					for(Long pk : o.getEnrollmentKeys()) {
					SearchList<SchoolEnrollment> searchList2 = new SearchList<SchoolEnrollment>();
					if(pk != null) {
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolEnrollment.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest2);
					}
					SchoolEnrollment o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1l);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());

							o2.setPk(pk);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolEnrollmentFuture(o2, false, a -> {
									if(a.succeeded()) {
										LOGGER.info(String.format("SchoolEnrollment %s refreshed. ", pk));
									} else {
										LOGGER.info(String.format("SchoolEnrollment %s failed. ", pk));
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
						SchoolGuardianEnUSApiServiceImpl service = new SchoolGuardianEnUSApiServiceImpl(siteRequest2.getSiteContext_());
						List<Future> futures2 = new ArrayList<>();
						for(SchoolGuardian o2 : searchList.getList()) {
							futures2.add(
								service.patchSchoolGuardianFuture(o2, false, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("SchoolGuardian %s refreshed. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("SchoolGuardian %s failed. ", o2.getPk()));
										eventHandler.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Refresh SchoolGuardian succeeded. ");
								eventHandler.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Refresh relations failed. ", b.cause());
								errorSchoolGuardian(siteRequest2, eventHandler, b);
							}
						});
					} else {
						LOGGER.error("Refresh relations failed. ", a.cause());
						errorSchoolGuardian(siteRequest2, eventHandler, a);
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
