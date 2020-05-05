package org.computate.scolaire.enUS.mom;

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
 * CanonicalName.frFR: org.computate.scolaire.frFR.mere.MereScolaireFrFRGenApiServiceImpl
 **/
public class SchoolMomEnUSGenApiServiceImpl implements SchoolMomEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolMomEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SchoolMomEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public SchoolMomEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
	}

	// POST //

	@Override
	public void postSchoolMom(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolMom(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("postSchoolMom started. "));
			sqlSchoolMom(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolMom(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							siteRequest.getVertx().eventBus().publish("websocketSchoolMom", JsonObject.mapFrom(apiRequest).toString());
							postSchoolMomFuture(siteRequest, false, c -> {
								if(c.succeeded()) {
									SchoolMom schoolMom = c.result();
									apiRequest.setPk(schoolMom.getPk());
									apiRequestSchoolMom(schoolMom);
									postSchoolMomResponse(schoolMom, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postSchoolMom succeeded. "));
										} else {
											LOGGER.error(String.format("postSchoolMom failed. ", d.cause()));
											errorSchoolMom(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("postSchoolMom failed. ", c.cause()));
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("postSchoolMom failed. ", b.cause()));
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("postSchoolMom failed. ", a.cause()));
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public Future<SchoolMom> postSchoolMomFuture(SiteRequestEnUS siteRequest, Boolean inheritPk, Handler<AsyncResult<SchoolMom>> eventHandler) {
		Promise<SchoolMom> promise = Promise.promise();
		try {
			createSchoolMom(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolMom schoolMom = a.result();
					sqlPOSTSchoolMom(schoolMom, inheritPk, b -> {
						if(b.succeeded()) {
							defineIndexSchoolMom(schoolMom, c -> {
								if(c.succeeded()) {
									ApiRequest apiRequest = siteRequest.getApiRequest_();
									if(apiRequest != null) {
										apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
										schoolMom.apiRequestSchoolMom();
										siteRequest.getVertx().eventBus().publish("websocketSchoolMom", JsonObject.mapFrom(apiRequest).toString());
									}
									eventHandler.handle(Future.succeededFuture(schoolMom));
									promise.complete(schoolMom);
								} else {
									errorSchoolMom(siteRequest, null, c);
								}
							});
						} else {
							errorSchoolMom(siteRequest, null, b);
						}
					});
				} else {
					errorSchoolMom(siteRequest, null, a);
				}
			});
		} catch(Exception e) {
			errorSchoolMom(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTSchoolMom(SchoolMom o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
									postSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "momKeys", l));
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
					case "personOccupation":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personOccupation", jsonObject.getString(entityVar), pk));
						break;
					case "personPhoneNumber":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personPhoneNumber", jsonObject.getString(entityVar), pk));
						break;
					case "personEmail":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personEmail", jsonObject.getString(entityVar), pk));
						break;
					case "personSms":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personSms", jsonObject.getBoolean(entityVar), pk));
						break;
					case "personReceiveEmail":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personReceiveEmail", jsonObject.getBoolean(entityVar), pk));
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

	public void postSchoolMomResponse(SchoolMom schoolMom, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = schoolMom.getSiteRequest_();
		try {
			response200POSTSchoolMom(schoolMom, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200POSTSchoolMom(SchoolMom o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
	public void putimportSchoolMom(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolMom(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putimportSchoolMom started. "));
			sqlSchoolMom(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolMom(siteRequest, b -> {
						if(b.succeeded()) {
							putimportSchoolMomResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolMom(siteContext, operationRequest, body);
											try {
												ApiRequest apiRequest = new ApiRequest();
												JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												apiRequest.setRows(jsonArray.size());
												apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketSchoolMom", JsonObject.mapFrom(apiRequest).toString());
												sqlSchoolMom(siteRequest2, d -> {
													if(d.succeeded()) {
														listPUTImportSchoolMom(apiRequest, siteRequest2, e -> {
															if(e.succeeded()) {
																putimportSchoolMomResponse(siteRequest2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putimportSchoolMom succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putimportSchoolMom failed. ", f.cause()));
																		errorSchoolMom(siteRequest2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putimportSchoolMom failed. ", e.cause()));
																errorSchoolMom(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putimportSchoolMom failed. ", d.cause()));
														errorSchoolMom(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putimportSchoolMom failed. ", ex));
												errorSchoolMom(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putimportSchoolMom failed. ", c.cause()));
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putimportSchoolMom failed. ", b.cause()));
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putimportSchoolMom failed. ", a.cause()));
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTImportSchoolMom(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolMom(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());

				SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolMom.class);
				searchList.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					SchoolMom o = searchList.getList().stream().findFirst().orElse(null);
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
							patchSchoolMomFuture(o, true, a -> {
								if(a.succeeded()) {
									SchoolMom schoolMom = a.result();
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									siteRequest2.getVertx().eventBus().publish("websocketSchoolMom", JsonObject.mapFrom(apiRequest).toString());
								} else {
									errorSchoolMom(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolMomFuture(siteRequest2, true, a -> {
							if(a.succeeded()) {
								SchoolMom schoolMom = a.result();
								apiRequestSchoolMom(schoolMom);
							} else {
								errorSchoolMom(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
								apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
								siteRequest.getVertx().eventBus().publish("websocketSchoolMom", JsonObject.mapFrom(apiRequest).toString());
					response200PUTImportSchoolMom(siteRequest, eventHandler);
				} else {
					errorSchoolMom(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putimportSchoolMomResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTImportSchoolMom(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTImportSchoolMom(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTMerge //

	@Override
	public void putmergeSchoolMom(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolMom(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putmergeSchoolMom started. "));
			sqlSchoolMom(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolMom(siteRequest, b -> {
						if(b.succeeded()) {
							putmergeSchoolMomResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolMom(siteContext, operationRequest, body);
											try {
												ApiRequest apiRequest = new ApiRequest();
												JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												apiRequest.setRows(jsonArray.size());
												apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketSchoolMom", JsonObject.mapFrom(apiRequest).toString());
												sqlSchoolMom(siteRequest2, d -> {
													if(d.succeeded()) {
														listPUTMergeSchoolMom(apiRequest, siteRequest2, e -> {
															if(e.succeeded()) {
																putmergeSchoolMomResponse(siteRequest2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putmergeSchoolMom succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putmergeSchoolMom failed. ", f.cause()));
																		errorSchoolMom(siteRequest2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putmergeSchoolMom failed. ", e.cause()));
																errorSchoolMom(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putmergeSchoolMom failed. ", d.cause()));
														errorSchoolMom(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putmergeSchoolMom failed. ", ex));
												errorSchoolMom(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putmergeSchoolMom failed. ", c.cause()));
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putmergeSchoolMom failed. ", b.cause()));
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putmergeSchoolMom failed. ", a.cause()));
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTMergeSchoolMom(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolMom(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());

				SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolMom.class);
				searchList.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					SchoolMom o = searchList.getList().stream().findFirst().orElse(null);
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
							patchSchoolMomFuture(o, false, a -> {
								if(a.succeeded()) {
									SchoolMom schoolMom = a.result();
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									siteRequest2.getVertx().eventBus().publish("websocketSchoolMom", JsonObject.mapFrom(apiRequest).toString());
								} else {
									errorSchoolMom(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolMomFuture(siteRequest2, false, a -> {
							if(a.succeeded()) {
								SchoolMom schoolMom = a.result();
								apiRequestSchoolMom(schoolMom);
							} else {
								errorSchoolMom(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
								apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
								siteRequest.getVertx().eventBus().publish("websocketSchoolMom", JsonObject.mapFrom(apiRequest).toString());
					response200PUTMergeSchoolMom(siteRequest, eventHandler);
				} else {
					errorSchoolMom(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putmergeSchoolMomResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTMergeSchoolMom(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTMergeSchoolMom(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTCopy //

	@Override
	public void putcopySchoolMom(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolMom(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putcopySchoolMom started. "));
			sqlSchoolMom(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolMom(siteRequest, b -> {
						if(b.succeeded()) {
							putcopySchoolMomResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolMom(siteContext, operationRequest, body);
											try {
												aSearchSchoolMom(siteRequest2, false, true, "/api/mom/copy", "PUTCopy", d -> {
													if(d.succeeded()) {
														SearchList<SchoolMom> listSchoolMom = d.result();
														ApiRequest apiRequest = new ApiRequest();
														apiRequest.setRows(listSchoolMom.getRows());
														apiRequest.setNumFound(listSchoolMom.getQueryResponse().getResults().getNumFound());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest2);
														siteRequest2.setApiRequest_(apiRequest);
														siteRequest2.getVertx().eventBus().publish("websocketSchoolMom", JsonObject.mapFrom(apiRequest).toString());
														sqlSchoolMom(siteRequest2, e -> {
															if(e.succeeded()) {
																try {
																	listPUTCopySchoolMom(apiRequest, listSchoolMom, f -> {
																		if(f.succeeded()) {
																			putcopySchoolMomResponse(siteRequest2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("putcopySchoolMom succeeded. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("putcopySchoolMom failed. ", g.cause()));
																					errorSchoolMom(siteRequest2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("putcopySchoolMom failed. ", f.cause()));
																			errorSchoolMom(siteRequest2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("putcopySchoolMom failed. ", ex));
																	errorSchoolMom(siteRequest2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("putcopySchoolMom failed. ", e.cause()));
																errorSchoolMom(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putcopySchoolMom failed. ", d.cause()));
														errorSchoolMom(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putcopySchoolMom failed. ", ex));
												errorSchoolMom(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putcopySchoolMom failed. ", c.cause()));
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putcopySchoolMom failed. ", b.cause()));
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putcopySchoolMom failed. ", a.cause()));
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTCopySchoolMom(ApiRequest apiRequest, SearchList<SchoolMom> listSchoolMom, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolMom.getSiteRequest_();
		listSchoolMom.getList().forEach(o -> {
			futures.add(
				putcopySchoolMomFuture(siteRequest, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						SchoolMom schoolMom = a.result();
						apiRequestSchoolMom(schoolMom);
					} else {
						errorSchoolMom(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchoolMom.size());
				siteRequest.getVertx().eventBus().publish("websocketSchoolMom", JsonObject.mapFrom(apiRequest).toString());
				if(listSchoolMom.next()) {
					listPUTCopySchoolMom(apiRequest, listSchoolMom, eventHandler);
				} else {
					response200PUTCopySchoolMom(siteRequest, eventHandler);
				}
			} else {
				errorSchoolMom(listSchoolMom.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolMom> putcopySchoolMomFuture(SiteRequestEnUS siteRequest, JsonObject jsonObject, Handler<AsyncResult<SchoolMom>> eventHandler) {
		Promise<SchoolMom> promise = Promise.promise();
		try {

			jsonObject.put("saves", Optional.ofNullable(jsonObject.getJsonArray("saves")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("saves").add(o.getKey());
			});

			createSchoolMom(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolMom schoolMom = a.result();
					sqlPUTCopySchoolMom(schoolMom, jsonObject, b -> {
						if(b.succeeded()) {
							defineSchoolMom(schoolMom, c -> {
								if(c.succeeded()) {
									attributeSchoolMom(schoolMom, d -> {
										if(d.succeeded()) {
											indexSchoolMom(schoolMom, e -> {
												if(e.succeeded()) {
													eventHandler.handle(Future.succeededFuture(schoolMom));
													promise.complete(schoolMom);
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
			errorSchoolMom(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopySchoolMom(SchoolMom o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
							putSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "momKeys", l));
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
					case "personOccupation":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personOccupation", jsonObject.getString(entityVar), pk));
						break;
					case "personPhoneNumber":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personPhoneNumber", jsonObject.getString(entityVar), pk));
						break;
					case "personEmail":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personEmail", jsonObject.getString(entityVar), pk));
						break;
					case "personSms":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personSms", jsonObject.getBoolean(entityVar), pk));
						break;
					case "personReceiveEmail":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personReceiveEmail", jsonObject.getBoolean(entityVar), pk));
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

	public void putcopySchoolMomResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTCopySchoolMom(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTCopySchoolMom(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSchoolMom(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolMom(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("patchSchoolMom started. "));
			sqlSchoolMom(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolMom(siteRequest, b -> {
						if(b.succeeded()) {
							patchSchoolMomResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolMom(siteContext, operationRequest, body);
											try {
												aSearchSchoolMom(siteRequest2, false, true, "/api/mom", "PATCH", d -> {
													if(d.succeeded()) {
														SearchList<SchoolMom> listSchoolMom = d.result();
														ApiRequest apiRequest = new ApiRequest();
														apiRequest.setRows(listSchoolMom.getRows());
														apiRequest.setNumFound(listSchoolMom.getQueryResponse().getResults().getNumFound());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest2);
														siteRequest2.setApiRequest_(apiRequest);
														siteRequest2.getVertx().eventBus().publish("websocketSchoolMom", JsonObject.mapFrom(apiRequest).toString());
														SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolMom.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
														Date date = null;
														if(facets != null)
															date = (Date)facets.get("max_modified");
														String dt;
														if(date == null)
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
														else
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
														listSchoolMom.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));

														SchoolMom o = listSchoolMom.getList().stream().findFirst().orElse(null);
														sqlSchoolMom(siteRequest2, e -> {
															if(e.succeeded()) {
																try {
																	listPATCHSchoolMom(apiRequest, listSchoolMom, dt, f -> {
																		if(f.succeeded()) {
																			patchSchoolMomResponse(siteRequest2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("patchSchoolMom succeeded. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("patchSchoolMom failed. ", g.cause()));
																					errorSchoolMom(siteRequest2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("patchSchoolMom failed. ", f.cause()));
																			errorSchoolMom(siteRequest2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("patchSchoolMom failed. ", ex));
																	errorSchoolMom(siteRequest2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("patchSchoolMom failed. ", e.cause()));
																errorSchoolMom(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("patchSchoolMom failed. ", d.cause()));
														errorSchoolMom(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("patchSchoolMom failed. ", ex));
												errorSchoolMom(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("patchSchoolMom failed. ", c.cause()));
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("patchSchoolMom failed. ", b.cause()));
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("patchSchoolMom failed. ", a.cause()));
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPATCHSchoolMom(ApiRequest apiRequest, SearchList<SchoolMom> listSchoolMom, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolMom.getSiteRequest_();
		listSchoolMom.getList().forEach(o -> {
			futures.add(
				patchSchoolMomFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						errorSchoolMom(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listSchoolMom.next(dt)) {
					listPATCHSchoolMom(apiRequest, listSchoolMom, dt, eventHandler);
				} else {
					response200PATCHSchoolMom(siteRequest, eventHandler);
				}
			} else {
				errorSchoolMom(listSchoolMom.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolMom> patchSchoolMomFuture(SchoolMom o, Boolean inheritPk, Handler<AsyncResult<SchoolMom>> eventHandler) {
		Promise<SchoolMom> promise = Promise.promise();
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			if(apiRequest != null && apiRequest.getNumFound() == 1L) {
				apiRequest.setOriginal(o);
				apiRequest.setPk(o.getPk());
			}
			sqlPATCHSchoolMom(o, inheritPk, a -> {
				if(a.succeeded()) {
					SchoolMom schoolMom = a.result();
					defineSchoolMom(schoolMom, b -> {
						if(b.succeeded()) {
							attributeSchoolMom(schoolMom, c -> {
								if(c.succeeded()) {
									indexSchoolMom(schoolMom, d -> {
										if(d.succeeded()) {
											if(apiRequest != null) {
												apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
												apiRequestSchoolMom(schoolMom);
												if(apiRequest.getNumFound() == 1L) {
													schoolMom.apiRequestSchoolMom();
												}
												siteRequest.getVertx().eventBus().publish("websocketSchoolMom", JsonObject.mapFrom(apiRequest).toString());
											}
											eventHandler.handle(Future.succeededFuture(schoolMom));
											promise.complete(schoolMom);
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
			errorSchoolMom(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHSchoolMom(SchoolMom o, Boolean inheritPk, Handler<AsyncResult<SchoolMom>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = jsonObject.fieldNames();
			SchoolMom o2 = new SchoolMom();

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
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.mom.SchoolMom"));
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
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "momKeys", l));
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
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "momKeys", l));
								}
							}
						}
						break;
					case "setEnrollmentKeys":
						JsonArray setEnrollmentKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "momKeys"));
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
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "momKeys", l));
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
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "momKeys", l));
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
					case "setPersonOccupation":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personOccupation"));
						} else {
							o2.setPersonOccupation(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personOccupation", o2.jsonPersonOccupation(), pk));
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
					case "setPersonEmail":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personEmail"));
						} else {
							o2.setPersonEmail(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personEmail", o2.jsonPersonEmail(), pk));
						}
						break;
					case "setPersonSms":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personSms"));
						} else {
							o2.setPersonSms(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personSms", o2.jsonPersonSms(), pk));
						}
						break;
					case "setPersonReceiveEmail":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personReceiveEmail"));
						} else {
							o2.setPersonReceiveEmail(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personReceiveEmail", o2.jsonPersonReceiveEmail(), pk));
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
					SchoolMom o3 = new SchoolMom();
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

	public void patchSchoolMomResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PATCHSchoolMom(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PATCHSchoolMom(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSchoolMom(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolMom(siteContext, operationRequest);
		try {
			sqlSchoolMom(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolMom(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolMom(siteRequest, false, true, "/api/mom/{id}", "GET", c -> {
								if(c.succeeded()) {
									SearchList<SchoolMom> listSchoolMom = c.result();
									getSchoolMomResponse(listSchoolMom, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getSchoolMom succeeded. "));
										} else {
											LOGGER.error(String.format("getSchoolMom failed. ", d.cause()));
											errorSchoolMom(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("getSchoolMom failed. ", c.cause()));
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("getSchoolMom failed. ", b.cause()));
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("getSchoolMom failed. ", a.cause()));
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void getSchoolMomResponse(SearchList<SchoolMom> listSchoolMom, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolMom.getSiteRequest_();
		try {
			response200GETSchoolMom(listSchoolMom, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200GETSchoolMom(SearchList<SchoolMom> listSchoolMom, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolMom.getSiteRequest_();
			SolrDocumentList solrDocuments = listSchoolMom.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listSchoolMom.getList().stream().findFirst().orElse(null));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchSchoolMom(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolMom(siteContext, operationRequest);
		try {
			sqlSchoolMom(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolMom(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolMom(siteRequest, false, true, "/api/mom", "Search", c -> {
								if(c.succeeded()) {
									SearchList<SchoolMom> listSchoolMom = c.result();
									searchSchoolMomResponse(listSchoolMom, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchSchoolMom succeeded. "));
										} else {
											LOGGER.error(String.format("searchSchoolMom failed. ", d.cause()));
											errorSchoolMom(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("searchSchoolMom failed. ", c.cause()));
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("searchSchoolMom failed. ", b.cause()));
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("searchSchoolMom failed. ", a.cause()));
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchSchoolMomResponse(SearchList<SchoolMom> listSchoolMom, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolMom.getSiteRequest_();
		try {
			response200SearchSchoolMom(listSchoolMom, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchSchoolMom(SearchList<SchoolMom> listSchoolMom, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolMom.getSiteRequest_();
			QueryResponse responseSearch = listSchoolMom.getQueryResponse();
			SolrDocumentList solrDocuments = listSchoolMom.getSolrDocumentList();
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
			listSchoolMom.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listSchoolMom.getFields();
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
	public void searchpageSchoolMomId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageSchoolMom(operationRequest, eventHandler);
	}

	@Override
	public void searchpageSchoolMom(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolMom(siteContext, operationRequest);
		try {
			sqlSchoolMom(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolMom(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolMom(siteRequest, false, true, "/mom", "SearchPage", c -> {
								if(c.succeeded()) {
									SearchList<SchoolMom> listSchoolMom = c.result();
									searchpageSchoolMomResponse(listSchoolMom, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchpageSchoolMom succeeded. "));
										} else {
											LOGGER.error(String.format("searchpageSchoolMom failed. ", d.cause()));
											errorSchoolMom(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("searchpageSchoolMom failed. ", c.cause()));
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("searchpageSchoolMom failed. ", b.cause()));
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("searchpageSchoolMom failed. ", a.cause()));
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchpageSchoolMomResponse(SearchList<SchoolMom> listSchoolMom, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolMom.getSiteRequest_();
		try {
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(siteRequest, buffer);
			siteRequest.setW(w);
			response200SearchPageSchoolMom(listSchoolMom, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolMom(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolMom(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolMom(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchoolMom failed. ", ex));
			errorSchoolMom(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchPageSchoolMom(SearchList<SchoolMom> listSchoolMom, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolMom.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listSchoolMom.getSiteRequest_(), buffer);
			MomPage page = new MomPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/mom");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listSchoolMom.size() == 1)
				siteRequest.setRequestPk(listSchoolMom.get(0).getPk());
			siteRequest.setW(w);
			page.setListSchoolMom(listSchoolMom);
			page.setSiteRequest_(siteRequest);
			page.initDeepMomPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<SchoolMom> defineIndexSchoolMom(SchoolMom schoolMom, Handler<AsyncResult<SchoolMom>> eventHandler) {
		Promise<SchoolMom> promise = Promise.promise();
		SiteRequestEnUS siteRequest = schoolMom.getSiteRequest_();
		defineSchoolMom(schoolMom, c -> {
			if(c.succeeded()) {
				attributeSchoolMom(schoolMom, d -> {
					if(d.succeeded()) {
						indexSchoolMom(schoolMom, e -> {
							if(e.succeeded()) {
								eventHandler.handle(Future.succeededFuture(schoolMom));
								promise.complete(schoolMom);
							} else {
								errorSchoolMom(siteRequest, null, e);
							}
						});
					} else {
						errorSchoolMom(siteRequest, null, d);
					}
				});
			} else {
				errorSchoolMom(siteRequest, null, c);
			}
		});
		return promise.future();
	}

	public void createSchoolMom(SiteRequestEnUS siteRequest, Handler<AsyncResult<SchoolMom>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(SchoolMom.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				SchoolMom o = new SchoolMom();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public ApiRequest apiRequestSchoolMom(SchoolMom o) {
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

	public void errorSchoolMom(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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

	public void sqlSchoolMom(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolMom(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForSchoolMom(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolMom(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userSchoolMom(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
								userSchoolMomDefine(siteRequest, jsonObject, false);

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
														errorSchoolMom(siteRequest, eventHandler, d);
													}
												});
											} else {
												errorSchoolMom(siteRequest, eventHandler, c);
											}
										});
									} else {
										errorSchoolMom(siteRequest, eventHandler, b);
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
								Boolean define = userSchoolMomDefine(siteRequest, jsonObject, true);
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
													errorSchoolMom(siteRequest, eventHandler, d);
												}
											});
										} else {
											errorSchoolMom(siteRequest, eventHandler, c);
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

	public Boolean userSchoolMomDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void aSearchSchoolMomQ(String uri, String apiMethod, SearchList<SchoolMom> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
			searchList.setHighlight(true);
			searchList.setHighlightSnippets(3);
			searchList.addHighlightField(varIndexed);
			searchList.setParam("hl.encoder", "html");
		}
	}

	public void aSearchSchoolMomFq(String uri, String apiMethod, SearchList<SchoolMom> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
	}

	public void aSearchSchoolMomSort(String uri, String apiMethod, SearchList<SchoolMom> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addSort(varIndexed, ORDER.valueOf(valueIndexed));
	}

	public void aSearchSchoolMomRows(String uri, String apiMethod, SearchList<SchoolMom> searchList, Integer valueRows) {
			searchList.setRows(apiMethod != null && apiMethod.contains("Search") ? valueRows : 10);
	}

	public void aSearchSchoolMomStart(String uri, String apiMethod, SearchList<SchoolMom> searchList, Integer valueStart) {
		searchList.setStart(valueStart);
	}

	public void aSearchSchoolMomVar(String uri, String apiMethod, SearchList<SchoolMom> searchList, String var, String value) {
		searchList.getSiteRequest_().getRequestVars().put(var, value);
	}

	public void aSearchSchoolMomUri(String uri, String apiMethod, SearchList<SchoolMom> searchList) {
	}

	public void aSearchSchoolMom(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String uri, String apiMethod, Handler<AsyncResult<SearchList<SchoolMom>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.setQuery("*:*");
			searchList.setC(SchoolMom.class);
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
								varIndexed = "*".equals(entityVar) ? entityVar : SchoolMom.varSearchSchoolMom(entityVar);
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								valueIndexed = StringUtils.isEmpty(valueIndexed) ? "*" : valueIndexed;
								aSearchSchoolMomQ(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "fq":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = SchoolMom.varIndexedSchoolMom(entityVar);
								aSearchSchoolMomFq(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = SchoolMom.varIndexedSchoolMom(entityVar);
								aSearchSchoolMomSort(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "start":
								valueStart = (Integer)paramObject;
								aSearchSchoolMomStart(uri, apiMethod, searchList, valueStart);
								break;
							case "rows":
								valueRows = (Integer)paramObject;
								aSearchSchoolMomRows(uri, apiMethod, searchList, valueRows);
								break;
							case "var":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								aSearchSchoolMomVar(uri, apiMethod, searchList, entityVar, valueIndexed);
								break;
						}
					}
					aSearchSchoolMomUri(uri, apiMethod, searchList);
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

	public void defineSchoolMom(SchoolMom o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void attributeSchoolMom(SchoolMom o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void indexSchoolMom(SchoolMom o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			if(BooleanUtils.isFalse(Optional.ofNullable(siteRequest.getApiRequest_()).map(ApiRequest::getEmpty).orElse(null))) {
				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolMom(siteContext, siteRequest.getOperationRequest(), new JsonObject());
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolMom.class);
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
						SchoolMomEnUSApiServiceImpl service = new SchoolMomEnUSApiServiceImpl(siteRequest2.getSiteContext_());
						List<Future> futures2 = new ArrayList<>();
						for(SchoolMom o2 : searchList.getList()) {
							futures2.add(
								service.patchSchoolMomFuture(o2, false, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("SchoolMom %s refreshed. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("SchoolMom %s failed. ", o2.getPk()));
										eventHandler.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Refresh SchoolMom succeeded. ");
								eventHandler.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Refresh relations failed. ", b.cause());
								errorSchoolMom(siteRequest2, eventHandler, b);
							}
						});
					} else {
						LOGGER.error("Refresh relations failed. ", a.cause());
						errorSchoolMom(siteRequest2, eventHandler, a);
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
