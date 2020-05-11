package org.computate.scolaire.enUS.block;

import org.computate.scolaire.enUS.enrollment.SchoolEnrollmentEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.age.SchoolAgeEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.age.SchoolAge;
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
 * CanonicalName.frFR: org.computate.scolaire.frFR.bloc.BlocScolaireFrFRGenApiServiceImpl
 **/
public class SchoolBlockEnUSGenApiServiceImpl implements SchoolBlockEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolBlockEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SchoolBlockEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public SchoolBlockEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
	}

	// POST //

	@Override
	public void postSchoolBlock(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("postSchoolBlock started. "));

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

			sqlSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolBlock(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							siteRequest.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
							postSchoolBlockFuture(siteRequest, false, c -> {
								if(c.succeeded()) {
									SchoolBlock schoolBlock = c.result();
									apiRequest.setPk(schoolBlock.getPk());
									postSchoolBlockResponse(schoolBlock, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postSchoolBlock succeeded. "));
										} else {
											LOGGER.error(String.format("postSchoolBlock failed. ", d.cause()));
											errorSchoolBlock(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("postSchoolBlock failed. ", c.cause()));
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("postSchoolBlock failed. ", b.cause()));
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("postSchoolBlock failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public Future<SchoolBlock> postSchoolBlockFuture(SiteRequestEnUS siteRequest, Boolean inheritPk, Handler<AsyncResult<SchoolBlock>> eventHandler) {
		Promise<SchoolBlock> promise = Promise.promise();
		try {
			createSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolBlock schoolBlock = a.result();
					sqlPOSTSchoolBlock(schoolBlock, inheritPk, b -> {
						if(b.succeeded()) {
							defineIndexSchoolBlock(schoolBlock, c -> {
								if(c.succeeded()) {
									ApiRequest apiRequest = siteRequest.getApiRequest_();
									if(apiRequest != null) {
										apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
										schoolBlock.apiRequestSchoolBlock();
										siteRequest.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
									}
									eventHandler.handle(Future.succeededFuture(schoolBlock));
									promise.complete(schoolBlock);
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
			errorSchoolBlock(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTSchoolBlock(SchoolBlock o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
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
									postSqlParams.addAll(Arrays.asList("blockKeys", l, "enrollmentKeys", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("SchoolEnrollment");
									}
								}
							}
						}
						break;
					case "ageKey":
						{
							Long l = Long.parseLong(jsonObject.getString(entityVar));
							if(l != null) {
								SearchList<SchoolAge> searchList = new SearchList<SchoolAge>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolAge.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("ageKey", pk, "blockKeys", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("SchoolAge");
									}
								}
							}
						}
						break;
					case "schoolAddress":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("schoolAddress", jsonObject.getString(entityVar), pk));
						break;
					case "blockStartTime":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockStartTime", jsonObject.getString(entityVar), pk));
						break;
					case "blockEndTime":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockEndTime", jsonObject.getString(entityVar), pk));
						break;
					case "blockPricePerMonth":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockPricePerMonth", jsonObject.getString(entityVar), pk));
						break;
					case "blockMonday":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockMonday", jsonObject.getBoolean(entityVar), pk));
						break;
					case "blockTuesday":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockTuesday", jsonObject.getBoolean(entityVar), pk));
						break;
					case "blockWednesday":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockWednesday", jsonObject.getBoolean(entityVar), pk));
						break;
					case "blockThursday":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockThursday", jsonObject.getBoolean(entityVar), pk));
						break;
					case "blockFriday":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockFriday", jsonObject.getBoolean(entityVar), pk));
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

	public void postSchoolBlockResponse(SchoolBlock schoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = schoolBlock.getSiteRequest_();
		try {
			response200POSTSchoolBlock(schoolBlock, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200POSTSchoolBlock(SchoolBlock o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
	public void putimportSchoolBlock(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putimportSchoolBlock started. "));

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

			sqlSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolBlock(siteRequest, b -> {
						if(b.succeeded()) {
							putimportSchoolBlockResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
											try {
												ApiRequest apiRequest = new ApiRequest();
												JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												apiRequest.setRows(jsonArray.size());
												apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
												sqlSchoolBlock(siteRequest2, d -> {
													if(d.succeeded()) {
														listPUTImportSchoolBlock(apiRequest, siteRequest2, e -> {
															if(e.succeeded()) {
																putimportSchoolBlockResponse(siteRequest2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putimportSchoolBlock succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putimportSchoolBlock failed. ", f.cause()));
																		errorSchoolBlock(siteRequest2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putimportSchoolBlock failed. ", e.cause()));
																errorSchoolBlock(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putimportSchoolBlock failed. ", d.cause()));
														errorSchoolBlock(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putimportSchoolBlock failed. ", ex));
												errorSchoolBlock(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putimportSchoolBlock failed. ", c.cause()));
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putimportSchoolBlock failed. ", b.cause()));
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putimportSchoolBlock failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTImportSchoolBlock(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolBlock(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				siteRequest2.setApiRequest_(apiRequest);

				SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolBlock.class);
				searchList.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					SchoolBlock o = searchList.getList().stream().findFirst().orElse(null);
					JsonObject json2 = new JsonObject();
					for(String f : json.fieldNames()) {
						json2.put("set" + StringUtils.capitalize(f), json.getValue(f));
					}
					if(o != null) {
						for(String f : Optional.ofNullable(o.getSaves()).orElse(new ArrayList<>())) {
							if(!json.fieldNames().contains(f))
								json2.putNull("set" + StringUtils.capitalize(f));
						}
						siteRequest2.setJsonObject(json2);
						futures.add(
							patchSchoolBlockFuture(o, true, a -> {
								if(a.succeeded()) {
									SchoolBlock schoolBlock = a.result();
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
								} else {
									errorSchoolBlock(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolBlockFuture(siteRequest2, true, a -> {
							if(a.succeeded()) {
								SchoolBlock schoolBlock = a.result();
							} else {
								errorSchoolBlock(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
					response200PUTImportSchoolBlock(siteRequest, eventHandler);
				} else {
					errorSchoolBlock(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putimportSchoolBlockResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTImportSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTImportSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTMerge //

	@Override
	public void putmergeSchoolBlock(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putmergeSchoolBlock started. "));

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

			sqlSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolBlock(siteRequest, b -> {
						if(b.succeeded()) {
							putmergeSchoolBlockResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
											try {
												ApiRequest apiRequest = new ApiRequest();
												JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												apiRequest.setRows(jsonArray.size());
												apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
												sqlSchoolBlock(siteRequest2, d -> {
													if(d.succeeded()) {
														listPUTMergeSchoolBlock(apiRequest, siteRequest2, e -> {
															if(e.succeeded()) {
																putmergeSchoolBlockResponse(siteRequest2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putmergeSchoolBlock succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putmergeSchoolBlock failed. ", f.cause()));
																		errorSchoolBlock(siteRequest2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putmergeSchoolBlock failed. ", e.cause()));
																errorSchoolBlock(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putmergeSchoolBlock failed. ", d.cause()));
														errorSchoolBlock(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putmergeSchoolBlock failed. ", ex));
												errorSchoolBlock(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putmergeSchoolBlock failed. ", c.cause()));
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putmergeSchoolBlock failed. ", b.cause()));
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putmergeSchoolBlock failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTMergeSchoolBlock(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolBlock(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				siteRequest2.setApiRequest_(apiRequest);

				SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolBlock.class);
				searchList.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					SchoolBlock o = searchList.getList().stream().findFirst().orElse(null);
					JsonObject json2 = new JsonObject();
					for(String f : json.fieldNames()) {
						json2.put("set" + StringUtils.capitalize(f), json.getValue(f));
					}
					if(o != null) {
						for(String f : Optional.ofNullable(o.getSaves()).orElse(new ArrayList<>())) {
							if(!json.fieldNames().contains(f))
								json2.putNull("set" + StringUtils.capitalize(f));
						}
						siteRequest2.setJsonObject(json2);
						futures.add(
							patchSchoolBlockFuture(o, false, a -> {
								if(a.succeeded()) {
									SchoolBlock schoolBlock = a.result();
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
								} else {
									errorSchoolBlock(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolBlockFuture(siteRequest2, false, a -> {
							if(a.succeeded()) {
								SchoolBlock schoolBlock = a.result();
							} else {
								errorSchoolBlock(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
					response200PUTMergeSchoolBlock(siteRequest, eventHandler);
				} else {
					errorSchoolBlock(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putmergeSchoolBlockResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTMergeSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTMergeSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTCopy //

	@Override
	public void putcopySchoolBlock(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putcopySchoolBlock started. "));

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

			sqlSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolBlock(siteRequest, b -> {
						if(b.succeeded()) {
							putcopySchoolBlockResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
											try {
												aSearchSchoolBlock(siteRequest2, false, true, "/api/block/copy", "PUTCopy", d -> {
													if(d.succeeded()) {
														SearchList<SchoolBlock> listSchoolBlock = d.result();
														ApiRequest apiRequest = new ApiRequest();
														apiRequest.setRows(listSchoolBlock.getRows());
														apiRequest.setNumFound(listSchoolBlock.getQueryResponse().getResults().getNumFound());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest2);
														siteRequest2.setApiRequest_(apiRequest);
														siteRequest2.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
														sqlSchoolBlock(siteRequest2, e -> {
															if(e.succeeded()) {
																try {
																	listPUTCopySchoolBlock(apiRequest, listSchoolBlock, f -> {
																		if(f.succeeded()) {
																			putcopySchoolBlockResponse(siteRequest2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("putcopySchoolBlock succeeded. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("putcopySchoolBlock failed. ", g.cause()));
																					errorSchoolBlock(siteRequest2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("putcopySchoolBlock failed. ", f.cause()));
																			errorSchoolBlock(siteRequest2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("putcopySchoolBlock failed. ", ex));
																	errorSchoolBlock(siteRequest2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("putcopySchoolBlock failed. ", e.cause()));
																errorSchoolBlock(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putcopySchoolBlock failed. ", d.cause()));
														errorSchoolBlock(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putcopySchoolBlock failed. ", ex));
												errorSchoolBlock(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putcopySchoolBlock failed. ", c.cause()));
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putcopySchoolBlock failed. ", b.cause()));
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putcopySchoolBlock failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTCopySchoolBlock(ApiRequest apiRequest, SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
		listSchoolBlock.getList().forEach(o -> {
			futures.add(
				putcopySchoolBlockFuture(siteRequest, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						SchoolBlock schoolBlock = a.result();
					} else {
						errorSchoolBlock(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchoolBlock.size());
				if(listSchoolBlock.next()) {
					listPUTCopySchoolBlock(apiRequest, listSchoolBlock, eventHandler);
				} else {
					response200PUTCopySchoolBlock(siteRequest, eventHandler);
				}
			} else {
				errorSchoolBlock(listSchoolBlock.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolBlock> putcopySchoolBlockFuture(SiteRequestEnUS siteRequest, JsonObject jsonObject, Handler<AsyncResult<SchoolBlock>> eventHandler) {
		Promise<SchoolBlock> promise = Promise.promise();
		try {

			jsonObject.put("saves", Optional.ofNullable(jsonObject.getJsonArray("saves")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("saves").add(o.getKey());
			});

			createSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolBlock schoolBlock = a.result();
					sqlPUTCopySchoolBlock(schoolBlock, jsonObject, b -> {
						if(b.succeeded()) {
							defineSchoolBlock(schoolBlock, c -> {
								if(c.succeeded()) {
									attributeSchoolBlock(schoolBlock, d -> {
										if(d.succeeded()) {
											indexSchoolBlock(schoolBlock, e -> {
												if(e.succeeded()) {
													ApiRequest apiRequest = siteRequest.getApiRequest_();
													if(apiRequest != null) {
														apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
														if(apiRequest.getNumFound() == 1L) {
															schoolBlock.apiRequestSchoolBlock();
														}
														siteRequest.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
													}
													eventHandler.handle(Future.succeededFuture(schoolBlock));
													promise.complete(schoolBlock);
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
			errorSchoolBlock(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopySchoolBlock(SchoolBlock o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
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
							putSqlParams.addAll(Arrays.asList("blockKeys", l, "enrollmentKeys", pk));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("SchoolEnrollment");
							}
						}
						break;
					case "ageKey":
							{
						Long l = Long.parseLong(jsonObject.getString(entityVar));
						putSql.append(SiteContextEnUS.SQL_addA);
						putSqlParams.addAll(Arrays.asList("ageKey", pk, "blockKeys", l));
						}
						break;
					case "schoolAddress":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("schoolAddress", jsonObject.getString(entityVar), pk));
						break;
					case "blockStartTime":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("blockStartTime", jsonObject.getString(entityVar), pk));
						break;
					case "blockEndTime":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("blockEndTime", jsonObject.getString(entityVar), pk));
						break;
					case "blockPricePerMonth":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("blockPricePerMonth", jsonObject.getString(entityVar), pk));
						break;
					case "blockMonday":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("blockMonday", jsonObject.getBoolean(entityVar), pk));
						break;
					case "blockTuesday":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("blockTuesday", jsonObject.getBoolean(entityVar), pk));
						break;
					case "blockWednesday":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("blockWednesday", jsonObject.getBoolean(entityVar), pk));
						break;
					case "blockThursday":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("blockThursday", jsonObject.getBoolean(entityVar), pk));
						break;
					case "blockFriday":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("blockFriday", jsonObject.getBoolean(entityVar), pk));
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

	public void putcopySchoolBlockResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTCopySchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTCopySchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSchoolBlock(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("patchSchoolBlock started. "));

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

			sqlSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolBlock(siteRequest, b -> {
						if(b.succeeded()) {
							patchSchoolBlockResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
											try {
												aSearchSchoolBlock(siteRequest2, false, true, "/api/block", "PATCH", d -> {
													if(d.succeeded()) {
														SearchList<SchoolBlock> listSchoolBlock = d.result();
														ApiRequest apiRequest = new ApiRequest();
														apiRequest.setRows(listSchoolBlock.getRows());
														apiRequest.setNumFound(listSchoolBlock.getQueryResponse().getResults().getNumFound());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest2);
														siteRequest2.setApiRequest_(apiRequest);
														siteRequest2.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
														SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolBlock.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
														Date date = null;
														if(facets != null)
															date = (Date)facets.get("max_modified");
														String dt;
														if(date == null)
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
														else
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
														listSchoolBlock.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));

														SchoolBlock o = listSchoolBlock.getList().stream().findFirst().orElse(null);
														sqlSchoolBlock(siteRequest2, e -> {
															if(e.succeeded()) {
																try {
																	listPATCHSchoolBlock(apiRequest, listSchoolBlock, dt, f -> {
																		if(f.succeeded()) {
																			patchSchoolBlockResponse(siteRequest2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("patchSchoolBlock succeeded. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("patchSchoolBlock failed. ", g.cause()));
																					errorSchoolBlock(siteRequest2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("patchSchoolBlock failed. ", f.cause()));
																			errorSchoolBlock(siteRequest2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("patchSchoolBlock failed. ", ex));
																	errorSchoolBlock(siteRequest2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("patchSchoolBlock failed. ", e.cause()));
																errorSchoolBlock(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("patchSchoolBlock failed. ", d.cause()));
														errorSchoolBlock(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("patchSchoolBlock failed. ", ex));
												errorSchoolBlock(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("patchSchoolBlock failed. ", c.cause()));
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("patchSchoolBlock failed. ", b.cause()));
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("patchSchoolBlock failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPATCHSchoolBlock(ApiRequest apiRequest, SearchList<SchoolBlock> listSchoolBlock, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
		listSchoolBlock.getList().forEach(o -> {
			futures.add(
				patchSchoolBlockFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						errorSchoolBlock(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listSchoolBlock.next(dt)) {
					listPATCHSchoolBlock(apiRequest, listSchoolBlock, dt, eventHandler);
				} else {
					response200PATCHSchoolBlock(siteRequest, eventHandler);
				}
			} else {
				errorSchoolBlock(listSchoolBlock.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolBlock> patchSchoolBlockFuture(SchoolBlock o, Boolean inheritPk, Handler<AsyncResult<SchoolBlock>> eventHandler) {
		Promise<SchoolBlock> promise = Promise.promise();
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			if(apiRequest != null && apiRequest.getNumFound() == 1L) {
				apiRequest.setOriginal(o);
				apiRequest.setPk(o.getPk());
			}
			sqlPATCHSchoolBlock(o, inheritPk, a -> {
				if(a.succeeded()) {
					SchoolBlock schoolBlock = a.result();
					defineSchoolBlock(schoolBlock, b -> {
						if(b.succeeded()) {
							attributeSchoolBlock(schoolBlock, c -> {
								if(c.succeeded()) {
									indexSchoolBlock(schoolBlock, d -> {
										if(d.succeeded()) {
											if(apiRequest != null) {
												apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
												if(apiRequest.getNumFound() == 1L) {
													schoolBlock.apiRequestSchoolBlock();
												}
												siteRequest.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
											}
											eventHandler.handle(Future.succeededFuture(schoolBlock));
											promise.complete(schoolBlock);
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
			errorSchoolBlock(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHSchoolBlock(SchoolBlock o, Boolean inheritPk, Handler<AsyncResult<SchoolBlock>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = jsonObject.fieldNames();
			SchoolBlock o2 = new SchoolBlock();

			if(o.getUserId() == null && siteRequest.getUserId() != null) {
				patchSql.append(SiteContextEnUS.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("userId", siteRequest.getUserId(), pk));
			}
			if(o.getUserKey() == null && siteRequest.getUserKey() != null) {
				patchSql.append(SiteContextEnUS.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("userKey", siteRequest.getUserKey(), pk));
			}

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.block.SchoolBlock"));
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
									patchSqlParams.addAll(Arrays.asList("blockKeys", l, "enrollmentKeys", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("SchoolEnrollment");
									}
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
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("blockKeys", l, "enrollmentKeys", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("SchoolEnrollment");
									}
								}
							}
						}
						break;
					case "setEnrollmentKeys":
						JsonArray setEnrollmentKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("blockKeys", "enrollmentKeys", pk));
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
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("blockKeys", l, "enrollmentKeys", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("SchoolEnrollment");
									}
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
									patchSqlParams.addAll(Arrays.asList("blockKeys", l, "enrollmentKeys", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("SchoolEnrollment");
									}
								}
							}
						}
						break;
					case "setAgeKey":
						{
							Long l = o2.getAgeKey();
							if(l != null) {
								SearchList<SchoolAge> searchList = new SearchList<SchoolAge>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolAge.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setAgeKey(jsonObject.getString(methodName));
									patchSql.append(SiteContextEnUS.SQL_setA1);
									patchSqlParams.addAll(Arrays.asList("ageKey", pk, "blockKeys", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("SchoolAge");
									}
								}
							}
						}
						break;
					case "removeAgeKey":
						{
							Long l = o2.getAgeKey();
							if(l != null) {
								SearchList<SchoolAge> searchList = new SearchList<SchoolAge>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolAge.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setAgeKey(jsonObject.getString(methodName));
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("ageKey", pk, "blockKeys", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("SchoolAge");
									}
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
					case "setBlockStartTime":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockStartTime"));
						} else {
							o2.setBlockStartTime(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockStartTime", o2.jsonBlockStartTime(), pk));
						}
						break;
					case "setBlockEndTime":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockEndTime"));
						} else {
							o2.setBlockEndTime(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockEndTime", o2.jsonBlockEndTime(), pk));
						}
						break;
					case "setBlockPricePerMonth":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockPricePerMonth"));
						} else {
							o2.setBlockPricePerMonth(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockPricePerMonth", o2.jsonBlockPricePerMonth(), pk));
						}
						break;
					case "setBlockMonday":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockMonday"));
						} else {
							o2.setBlockMonday(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockMonday", o2.jsonBlockMonday(), pk));
						}
						break;
					case "setBlockTuesday":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockTuesday"));
						} else {
							o2.setBlockTuesday(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockTuesday", o2.jsonBlockTuesday(), pk));
						}
						break;
					case "setBlockWednesday":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockWednesday"));
						} else {
							o2.setBlockWednesday(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockWednesday", o2.jsonBlockWednesday(), pk));
						}
						break;
					case "setBlockThursday":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockThursday"));
						} else {
							o2.setBlockThursday(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockThursday", o2.jsonBlockThursday(), pk));
						}
						break;
					case "setBlockFriday":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockFriday"));
						} else {
							o2.setBlockFriday(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockFriday", o2.jsonBlockFriday(), pk));
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
					SchoolBlock o3 = new SchoolBlock();
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

	public void patchSchoolBlockResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PATCHSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PATCHSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSchoolBlock(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest);
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

			sqlSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolBlock(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolBlock(siteRequest, false, true, "/api/block/{id}", "GET", c -> {
								if(c.succeeded()) {
									SearchList<SchoolBlock> listSchoolBlock = c.result();
									getSchoolBlockResponse(listSchoolBlock, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getSchoolBlock succeeded. "));
										} else {
											LOGGER.error(String.format("getSchoolBlock failed. ", d.cause()));
											errorSchoolBlock(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("getSchoolBlock failed. ", c.cause()));
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("getSchoolBlock failed. ", b.cause()));
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("getSchoolBlock failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void getSchoolBlockResponse(SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
		try {
			response200GETSchoolBlock(listSchoolBlock, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200GETSchoolBlock(SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
			SolrDocumentList solrDocuments = listSchoolBlock.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listSchoolBlock.getList().stream().findFirst().orElse(null));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchSchoolBlock(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest);
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

			sqlSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolBlock(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolBlock(siteRequest, false, true, "/api/block", "Search", c -> {
								if(c.succeeded()) {
									SearchList<SchoolBlock> listSchoolBlock = c.result();
									searchSchoolBlockResponse(listSchoolBlock, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchSchoolBlock succeeded. "));
										} else {
											LOGGER.error(String.format("searchSchoolBlock failed. ", d.cause()));
											errorSchoolBlock(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("searchSchoolBlock failed. ", c.cause()));
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("searchSchoolBlock failed. ", b.cause()));
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("searchSchoolBlock failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchSchoolBlockResponse(SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
		try {
			response200SearchSchoolBlock(listSchoolBlock, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchSchoolBlock(SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
			QueryResponse responseSearch = listSchoolBlock.getQueryResponse();
			SolrDocumentList solrDocuments = listSchoolBlock.getSolrDocumentList();
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
			listSchoolBlock.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listSchoolBlock.getFields();
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
	public void searchpageSchoolBlockId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageSchoolBlock(operationRequest, eventHandler);
	}

	@Override
	public void searchpageSchoolBlock(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest);
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

			sqlSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolBlock(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolBlock(siteRequest, false, true, "/block", "SearchPage", c -> {
								if(c.succeeded()) {
									SearchList<SchoolBlock> listSchoolBlock = c.result();
									searchpageSchoolBlockResponse(listSchoolBlock, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchpageSchoolBlock succeeded. "));
										} else {
											LOGGER.error(String.format("searchpageSchoolBlock failed. ", d.cause()));
											errorSchoolBlock(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("searchpageSchoolBlock failed. ", c.cause()));
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("searchpageSchoolBlock failed. ", b.cause()));
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("searchpageSchoolBlock failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchpageSchoolBlockResponse(SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
		try {
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(siteRequest, buffer);
			siteRequest.setW(w);
			response200SearchPageSchoolBlock(listSchoolBlock, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchPageSchoolBlock(SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listSchoolBlock.getSiteRequest_(), buffer);
			BlockPage page = new BlockPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/block");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listSchoolBlock.size() == 1)
				siteRequest.setRequestPk(listSchoolBlock.get(0).getPk());
			siteRequest.setW(w);
			page.setListSchoolBlock(listSchoolBlock);
			page.setSiteRequest_(siteRequest);
			page.initDeepBlockPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<SchoolBlock> defineIndexSchoolBlock(SchoolBlock schoolBlock, Handler<AsyncResult<SchoolBlock>> eventHandler) {
		Promise<SchoolBlock> promise = Promise.promise();
		SiteRequestEnUS siteRequest = schoolBlock.getSiteRequest_();
		defineSchoolBlock(schoolBlock, c -> {
			if(c.succeeded()) {
				attributeSchoolBlock(schoolBlock, d -> {
					if(d.succeeded()) {
						indexSchoolBlock(schoolBlock, e -> {
							if(e.succeeded()) {
								eventHandler.handle(Future.succeededFuture(schoolBlock));
								promise.complete(schoolBlock);
							} else {
								errorSchoolBlock(siteRequest, null, e);
							}
						});
					} else {
						errorSchoolBlock(siteRequest, null, d);
					}
				});
			} else {
				errorSchoolBlock(siteRequest, null, c);
			}
		});
		return promise.future();
	}

	public void createSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<SchoolBlock>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(SchoolBlock.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				SchoolBlock o = new SchoolBlock();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void errorSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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

	public void sqlSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolBlock(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolBlock(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
								userSchoolBlockDefine(siteRequest, jsonObject, false);

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
														errorSchoolBlock(siteRequest, eventHandler, d);
													}
												});
											} else {
												errorSchoolBlock(siteRequest, eventHandler, c);
											}
										});
									} else {
										errorSchoolBlock(siteRequest, eventHandler, b);
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
								Boolean define = userSchoolBlockDefine(siteRequest, jsonObject, true);
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
													errorSchoolBlock(siteRequest, eventHandler, d);
												}
											});
										} else {
											errorSchoolBlock(siteRequest, eventHandler, c);
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

	public Boolean userSchoolBlockDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void aSearchSchoolBlockQ(String uri, String apiMethod, SearchList<SchoolBlock> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
			searchList.setHighlight(true);
			searchList.setHighlightSnippets(3);
			searchList.addHighlightField(varIndexed);
			searchList.setParam("hl.encoder", "html");
		}
	}

	public void aSearchSchoolBlockFq(String uri, String apiMethod, SearchList<SchoolBlock> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
	}

	public void aSearchSchoolBlockSort(String uri, String apiMethod, SearchList<SchoolBlock> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addSort(varIndexed, ORDER.valueOf(valueIndexed));
	}

	public void aSearchSchoolBlockRows(String uri, String apiMethod, SearchList<SchoolBlock> searchList, Integer valueRows) {
			searchList.setRows(apiMethod != null && apiMethod.contains("Search") ? valueRows : 10);
	}

	public void aSearchSchoolBlockStart(String uri, String apiMethod, SearchList<SchoolBlock> searchList, Integer valueStart) {
		searchList.setStart(valueStart);
	}

	public void aSearchSchoolBlockVar(String uri, String apiMethod, SearchList<SchoolBlock> searchList, String var, String value) {
		searchList.getSiteRequest_().getRequestVars().put(var, value);
	}

	public void aSearchSchoolBlockUri(String uri, String apiMethod, SearchList<SchoolBlock> searchList) {
	}

	public void aSearchSchoolBlock(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String uri, String apiMethod, Handler<AsyncResult<SearchList<SchoolBlock>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.setQuery("*:*");
			searchList.setC(SchoolBlock.class);
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
								varIndexed = "*".equals(entityVar) ? entityVar : SchoolBlock.varSearchSchoolBlock(entityVar);
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								valueIndexed = StringUtils.isEmpty(valueIndexed) ? "*" : valueIndexed;
								aSearchSchoolBlockQ(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "fq":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = SchoolBlock.varIndexedSchoolBlock(entityVar);
								aSearchSchoolBlockFq(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = SchoolBlock.varIndexedSchoolBlock(entityVar);
								aSearchSchoolBlockSort(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "start":
								valueStart = (Integer)paramObject;
								aSearchSchoolBlockStart(uri, apiMethod, searchList, valueStart);
								break;
							case "rows":
								valueRows = (Integer)paramObject;
								aSearchSchoolBlockRows(uri, apiMethod, searchList, valueRows);
								break;
							case "var":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								aSearchSchoolBlockVar(uri, apiMethod, searchList, entityVar, valueIndexed);
								break;
						}
					}
					aSearchSchoolBlockUri(uri, apiMethod, searchList);
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

	public void defineSchoolBlock(SchoolBlock o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void attributeSchoolBlock(SchoolBlock o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void indexSchoolBlock(SchoolBlock o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			if(BooleanUtils.isFalse(Optional.ofNullable(siteRequest.getApiRequest_()).map(ApiRequest::getEmpty).orElse(true))) {
				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolBlock(siteContext, siteRequest.getOperationRequest(), new JsonObject());
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolBlock.class);
				searchList.addFilterQuery("modified_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(siteRequest.getApiRequest_().getCreated().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				searchList.add("json.facet", "{enrollmentKeys:{terms:{field:enrollmentKeys_indexed_longs, limit:1000}}}");
				searchList.add("json.facet", "{ageKey:{terms:{field:ageKey_indexed_longs, limit:1000}}}");
				searchList.setRows(1000);
				searchList.initDeepSearchList(siteRequest2);
				List<Future> futures = new ArrayList<>();

				for(int i=0; i < pks.size(); i++) {
					Long pk2 = pks.get(i);
					String classSimpleName2 = classes.get(i);

					if("SchoolEnrollment".equals(classSimpleName2) && pk2 != null) {
						SchoolEnrollmentEnUSGenApiServiceImpl service = new SchoolEnrollmentEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
						SearchList<SchoolEnrollment> searchList2 = new SearchList<SchoolEnrollment>();
						if(pk2 != null) {
							searchList2.setStore(true);
							searchList2.setQuery("*:*");
							searchList2.setC(SchoolEnrollment.class);
							searchList2.addFilterQuery("pk_indexed_long:" + pk2);
							searchList2.setRows(1);
							searchList2.initDeepSearchList(siteRequest2);
							SchoolEnrollment o2 = searchList2.getList().stream().findFirst().orElse(null);

							if(o2 != null) {
								ApiRequest apiRequest2 = new ApiRequest();
								apiRequest2.setRows(1);
								apiRequest2.setNumFound(1l);
								apiRequest2.setNumPATCH(0L);
								apiRequest2.initDeepApiRequest(siteRequest2);
								siteRequest2.setApiRequest_(apiRequest2);
								siteRequest2.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest2).toString());

								o2.setPk(pk2);
								o2.setSiteRequest_(siteRequest2);
								futures.add(
									service.patchSchoolEnrollmentFuture(o2, false, a -> {
										if(a.succeeded()) {
											LOGGER.info(String.format("SchoolEnrollment %s refreshed. ", pk2));
										} else {
											LOGGER.info(String.format("SchoolEnrollment %s failed. ", pk2));
											eventHandler.handle(Future.failedFuture(a.cause()));
										}
									})
								);
							}
						}
					}

					if("SchoolAge".equals(classSimpleName2) && pk2 != null) {
						SearchList<SchoolAge> searchList2 = new SearchList<SchoolAge>();
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolAge.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk2);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest2);
						SchoolAge o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							SchoolAgeEnUSGenApiServiceImpl service = new SchoolAgeEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());

							ApiRequest apiRequest2 = new ApiRequest();
							apiRequest2.setRows(1);
							apiRequest2.setNumFound(1L);
							apiRequest2.setNumPATCH(0L);
							apiRequest2.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest2);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolAge", JsonObject.mapFrom(apiRequest2).toString());

							if(pk2 != null) {
								o2.setPk(pk2);
								o2.setSiteRequest_(siteRequest2);
								futures.add(
									service.patchSchoolAgeFuture(o2, false, a -> {
										if(a.succeeded()) {
											LOGGER.info(String.format("SchoolAge %s refreshed. ", pk2));
										} else {
											LOGGER.info(String.format("SchoolAge %s failed. ", pk2));
											eventHandler.handle(Future.failedFuture(a.cause()));
										}
									})
								);
							}
						}
					}
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						LOGGER.info("Refresh relations succeeded. ");
						SchoolBlockEnUSApiServiceImpl service = new SchoolBlockEnUSApiServiceImpl(siteRequest2.getSiteContext_());
						List<Future> futures2 = new ArrayList<>();
						for(SchoolBlock o2 : searchList.getList()) {
							futures2.add(
								service.patchSchoolBlockFuture(o2, false, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("SchoolBlock %s refreshed. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("SchoolBlock %s failed. ", o2.getPk()));
										eventHandler.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Refresh SchoolBlock succeeded. ");
								eventHandler.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Refresh relations failed. ", b.cause());
								errorSchoolBlock(siteRequest2, eventHandler, b);
							}
						});
					} else {
						LOGGER.error("Refresh relations failed. ", a.cause());
						errorSchoolBlock(siteRequest2, eventHandler, a);
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
