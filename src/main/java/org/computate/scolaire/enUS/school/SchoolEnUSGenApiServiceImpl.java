package org.computate.scolaire.enUS.school;

import org.computate.scolaire.enUS.year.SchoolYearEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.year.SchoolYear;
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
 * CanonicalName.frFR: org.computate.scolaire.frFR.ecole.EcoleFrFRGenApiServiceImpl
 **/
public class SchoolEnUSGenApiServiceImpl implements SchoolEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SchoolEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public SchoolEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
	}

	// POST //

	@Override
	public void postSchool(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("postSchool started. "));

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

			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					userSchool(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							siteRequest.getVertx().eventBus().publish("websocketSchool", JsonObject.mapFrom(apiRequest).toString());
							postSchoolFuture(siteRequest, false, c -> {
								if(c.succeeded()) {
									School school = c.result();
									apiRequest.setPk(school.getPk());
									postSchoolResponse(school, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postSchool succeeded. "));
										} else {
											LOGGER.error(String.format("postSchool failed. ", d.cause()));
											errorSchool(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("postSchool failed. ", c.cause()));
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("postSchool failed. ", b.cause()));
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("postSchool failed. ", a.cause()));
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchool failed. ", ex));
			errorSchool(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public Future<School> postSchoolFuture(SiteRequestEnUS siteRequest, Boolean inheritPk, Handler<AsyncResult<School>> eventHandler) {
		Promise<School> promise = Promise.promise();
		try {
			createSchool(siteRequest, a -> {
				if(a.succeeded()) {
					School school = a.result();
					sqlPOSTSchool(school, inheritPk, b -> {
						if(b.succeeded()) {
							defineIndexSchool(school, c -> {
								if(c.succeeded()) {
									ApiRequest apiRequest = siteRequest.getApiRequest_();
									if(apiRequest != null) {
										apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
										school.apiRequestSchool();
										siteRequest.getVertx().eventBus().publish("websocketSchool", JsonObject.mapFrom(apiRequest).toString());
									}
									eventHandler.handle(Future.succeededFuture(school));
									promise.complete(school);
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
			errorSchool(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTSchool(School o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "yearKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								SearchList<SchoolYear> searchList = new SearchList<SchoolYear>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolYear.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("schoolKey", l, "yearKeys", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("SchoolYear");
									}
								}
							}
						}
						break;
					case "schoolName":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("schoolName", jsonObject.getString(entityVar), pk));
						break;
					case "schoolPhoneNumber":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("schoolPhoneNumber", jsonObject.getString(entityVar), pk));
						break;
					case "schoolAdministratorName":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("schoolAdministratorName", jsonObject.getString(entityVar), pk));
						break;
					case "schoolEmailFrom":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("schoolEmailFrom", jsonObject.getString(entityVar), pk));
						break;
					case "schoolEmailTo":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("schoolEmailTo", jsonObject.getString(entityVar), pk));
						break;
					case "schoolLocation":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("schoolLocation", jsonObject.getString(entityVar), pk));
						break;
					case "schoolAddress":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("schoolAddress", jsonObject.getString(entityVar), pk));
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

	public void postSchoolResponse(School school, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = school.getSiteRequest_();
		try {
			response200POSTSchool(school, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchool failed. ", ex));
			errorSchool(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200POSTSchool(School o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(o);
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSchool(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("patchSchool started. "));

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

			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					userSchool(siteRequest, b -> {
						if(b.succeeded()) {
							patchSchoolResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchool(siteContext, operationRequest, body);
											try {
												aSearchSchool(siteRequest2, false, true, "/api/school", "PATCH", d -> {
													if(d.succeeded()) {
														SearchList<School> listSchool = d.result();
														ApiRequest apiRequest = new ApiRequest();
														apiRequest.setRows(listSchool.getRows());
														apiRequest.setNumFound(listSchool.getQueryResponse().getResults().getNumFound());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest2);
														siteRequest2.setApiRequest_(apiRequest);
														siteRequest2.getVertx().eventBus().publish("websocketSchool", JsonObject.mapFrom(apiRequest).toString());
														SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchool.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
														Date date = null;
														if(facets != null)
															date = (Date)facets.get("max_modified");
														String dt;
														if(date == null)
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
														else
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
														listSchool.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));

														School o = listSchool.getList().stream().findFirst().orElse(null);
														sqlSchool(siteRequest2, e -> {
															if(e.succeeded()) {
																try {
																	listPATCHSchool(apiRequest, listSchool, dt, f -> {
																		if(f.succeeded()) {
																			patchSchoolResponse(siteRequest2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("patchSchool succeeded. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("patchSchool failed. ", g.cause()));
																					errorSchool(siteRequest2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("patchSchool failed. ", f.cause()));
																			errorSchool(siteRequest2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("patchSchool failed. ", ex));
																	errorSchool(siteRequest2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("patchSchool failed. ", e.cause()));
																errorSchool(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("patchSchool failed. ", d.cause()));
														errorSchool(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("patchSchool failed. ", ex));
												errorSchool(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("patchSchool failed. ", c.cause()));
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("patchSchool failed. ", b.cause()));
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("patchSchool failed. ", a.cause()));
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchool failed. ", ex));
			errorSchool(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPATCHSchool(ApiRequest apiRequest, SearchList<School> listSchool, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchool.getSiteRequest_();
		listSchool.getList().forEach(o -> {
			futures.add(
				patchSchoolFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						errorSchool(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listSchool.next(dt)) {
					listPATCHSchool(apiRequest, listSchool, dt, eventHandler);
				} else {
					response200PATCHSchool(siteRequest, eventHandler);
				}
			} else {
				errorSchool(listSchool.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<School> patchSchoolFuture(School o, Boolean inheritPk, Handler<AsyncResult<School>> eventHandler) {
		Promise<School> promise = Promise.promise();
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			if(apiRequest != null && apiRequest.getNumFound() == 1L) {
				apiRequest.setOriginal(o);
				apiRequest.setPk(o.getPk());
			}
			sqlPATCHSchool(o, inheritPk, a -> {
				if(a.succeeded()) {
					School school = a.result();
					defineSchool(school, b -> {
						if(b.succeeded()) {
							attributeSchool(school, c -> {
								if(c.succeeded()) {
									indexSchool(school, d -> {
										if(d.succeeded()) {
											if(apiRequest != null) {
												apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
												if(apiRequest.getNumFound() == 1L) {
													school.apiRequestSchool();
												}
												siteRequest.getVertx().eventBus().publish("websocketSchool", JsonObject.mapFrom(apiRequest).toString());
											}
											eventHandler.handle(Future.succeededFuture(school));
											promise.complete(school);
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
			errorSchool(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHSchool(School o, Boolean inheritPk, Handler<AsyncResult<School>> eventHandler) {
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
			School o2 = new School();

			if(o.getUserId() == null && siteRequest.getUserId() != null) {
				patchSql.append(SiteContextEnUS.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("userId", siteRequest.getUserId(), pk));
			}
			if(o.getUserKey() == null && siteRequest.getUserKey() != null) {
				patchSql.append(SiteContextEnUS.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("userKey", siteRequest.getUserKey(), pk));
			}

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.school.School"));
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
					case "addYearKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolYear> searchList = new SearchList<SchoolYear>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolYear.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("schoolKey", l, "yearKeys", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("SchoolYear");
									}
								}
							}
						}
						break;
					case "addAllYearKeys":
						JsonArray addAllYearKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllYearKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllYearKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolYear> searchList = new SearchList<SchoolYear>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolYear.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("schoolKey", l, "yearKeys", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("SchoolYear");
									}
								}
							}
						}
						break;
					case "setYearKeys":
						JsonArray setYearKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("schoolKey", "yearKeys", pk));
						for(Integer i = 0; i <  setYearKeysValues.size(); i++) {
							Long l = Long.parseLong(setYearKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolYear> searchList = new SearchList<SchoolYear>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolYear.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("schoolKey", l, "yearKeys", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("SchoolYear");
									}
								}
							}
						}
						break;
					case "removeYearKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolYear> searchList = new SearchList<SchoolYear>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolYear.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("schoolKey", l, "yearKeys", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("SchoolYear");
									}
								}
							}
						}
						break;
					case "setSchoolName":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "schoolName"));
						} else {
							o2.setSchoolName(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("schoolName", o2.jsonSchoolName(), pk));
						}
						break;
					case "setSchoolPhoneNumber":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "schoolPhoneNumber"));
						} else {
							o2.setSchoolPhoneNumber(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("schoolPhoneNumber", o2.jsonSchoolPhoneNumber(), pk));
						}
						break;
					case "setSchoolAdministratorName":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "schoolAdministratorName"));
						} else {
							o2.setSchoolAdministratorName(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("schoolAdministratorName", o2.jsonSchoolAdministratorName(), pk));
						}
						break;
					case "setSchoolEmailFrom":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "schoolEmailFrom"));
						} else {
							o2.setSchoolEmailFrom(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("schoolEmailFrom", o2.jsonSchoolEmailFrom(), pk));
						}
						break;
					case "setSchoolEmailTo":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "schoolEmailTo"));
						} else {
							o2.setSchoolEmailTo(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("schoolEmailTo", o2.jsonSchoolEmailTo(), pk));
						}
						break;
					case "setSchoolLocation":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "schoolLocation"));
						} else {
							o2.setSchoolLocation(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("schoolLocation", o2.jsonSchoolLocation(), pk));
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
				}
			}
			sqlConnection.queryWithParams(
					patchSql.toString()
					, new JsonArray(patchSqlParams)
					, patchAsync
			-> {
				if(patchAsync.succeeded()) {
					School o3 = new School();
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

	public void patchSchoolResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PATCHSchool(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchool failed. ", ex));
			errorSchool(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PATCHSchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSchool(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest);
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

			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					userSchool(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchool(siteRequest, false, true, "/api/school/{id}", "GET", c -> {
								if(c.succeeded()) {
									SearchList<School> listSchool = c.result();
									getSchoolResponse(listSchool, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getSchool succeeded. "));
										} else {
											LOGGER.error(String.format("getSchool failed. ", d.cause()));
											errorSchool(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("getSchool failed. ", c.cause()));
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("getSchool failed. ", b.cause()));
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("getSchool failed. ", a.cause()));
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchool failed. ", ex));
			errorSchool(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void getSchoolResponse(SearchList<School> listSchool, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchool.getSiteRequest_();
		try {
			response200GETSchool(listSchool, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchool failed. ", ex));
			errorSchool(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200GETSchool(SearchList<School> listSchool, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchool.getSiteRequest_();
			SolrDocumentList solrDocuments = listSchool.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listSchool.getList().stream().findFirst().orElse(null));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchSchool(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest);
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

			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					userSchool(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchool(siteRequest, false, true, "/api/school", "Search", c -> {
								if(c.succeeded()) {
									SearchList<School> listSchool = c.result();
									searchSchoolResponse(listSchool, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchSchool succeeded. "));
										} else {
											LOGGER.error(String.format("searchSchool failed. ", d.cause()));
											errorSchool(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("searchSchool failed. ", c.cause()));
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("searchSchool failed. ", b.cause()));
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("searchSchool failed. ", a.cause()));
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchool failed. ", ex));
			errorSchool(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchSchoolResponse(SearchList<School> listSchool, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchool.getSiteRequest_();
		try {
			response200SearchSchool(listSchool, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchool failed. ", ex));
			errorSchool(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchSchool(SearchList<School> listSchool, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchool.getSiteRequest_();
			QueryResponse responseSearch = listSchool.getQueryResponse();
			SolrDocumentList solrDocuments = listSchool.getSolrDocumentList();
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
			listSchool.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listSchool.getFields();
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

	// PUTImport //

	@Override
	public void putimportSchool(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putimportSchool started. "));

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

			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					userSchool(siteRequest, b -> {
						if(b.succeeded()) {
							putimportSchoolResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchool(siteContext, operationRequest, body);
											try {
												ApiRequest apiRequest = new ApiRequest();
												JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												apiRequest.setRows(jsonArray.size());
												apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketSchool", JsonObject.mapFrom(apiRequest).toString());
												sqlSchool(siteRequest2, d -> {
													if(d.succeeded()) {
														listPUTImportSchool(apiRequest, siteRequest2, e -> {
															if(e.succeeded()) {
																putimportSchoolResponse(siteRequest2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putimportSchool succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putimportSchool failed. ", f.cause()));
																		errorSchool(siteRequest2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putimportSchool failed. ", e.cause()));
																errorSchool(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putimportSchool failed. ", d.cause()));
														errorSchool(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putimportSchool failed. ", ex));
												errorSchool(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putimportSchool failed. ", c.cause()));
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putimportSchool failed. ", b.cause()));
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putimportSchool failed. ", a.cause()));
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchool failed. ", ex));
			errorSchool(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTImportSchool(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchool(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				siteRequest2.setApiRequest_(apiRequest);

				SearchList<School> searchList = new SearchList<School>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(School.class);
				searchList.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					School o = searchList.getList().stream().findFirst().orElse(null);
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
							patchSchoolFuture(o, true, a -> {
								if(a.succeeded()) {
									School school = a.result();
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
								} else {
									errorSchool(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolFuture(siteRequest2, true, a -> {
							if(a.succeeded()) {
								School school = a.result();
							} else {
								errorSchool(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
					response200PUTImportSchool(siteRequest, eventHandler);
				} else {
					errorSchool(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchool failed. ", ex));
			errorSchool(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putimportSchoolResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTImportSchool(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchool failed. ", ex));
			errorSchool(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTImportSchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTMerge //

	@Override
	public void putmergeSchool(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putmergeSchool started. "));

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

			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					userSchool(siteRequest, b -> {
						if(b.succeeded()) {
							putmergeSchoolResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchool(siteContext, operationRequest, body);
											try {
												ApiRequest apiRequest = new ApiRequest();
												JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												apiRequest.setRows(jsonArray.size());
												apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketSchool", JsonObject.mapFrom(apiRequest).toString());
												sqlSchool(siteRequest2, d -> {
													if(d.succeeded()) {
														listPUTMergeSchool(apiRequest, siteRequest2, e -> {
															if(e.succeeded()) {
																putmergeSchoolResponse(siteRequest2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putmergeSchool succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putmergeSchool failed. ", f.cause()));
																		errorSchool(siteRequest2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putmergeSchool failed. ", e.cause()));
																errorSchool(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putmergeSchool failed. ", d.cause()));
														errorSchool(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putmergeSchool failed. ", ex));
												errorSchool(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putmergeSchool failed. ", c.cause()));
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putmergeSchool failed. ", b.cause()));
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putmergeSchool failed. ", a.cause()));
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchool failed. ", ex));
			errorSchool(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTMergeSchool(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchool(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				siteRequest2.setApiRequest_(apiRequest);

				SearchList<School> searchList = new SearchList<School>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(School.class);
				searchList.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					School o = searchList.getList().stream().findFirst().orElse(null);
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
							patchSchoolFuture(o, false, a -> {
								if(a.succeeded()) {
									School school = a.result();
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
								} else {
									errorSchool(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolFuture(siteRequest2, false, a -> {
							if(a.succeeded()) {
								School school = a.result();
							} else {
								errorSchool(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
					response200PUTMergeSchool(siteRequest, eventHandler);
				} else {
					errorSchool(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchool failed. ", ex));
			errorSchool(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putmergeSchoolResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTMergeSchool(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchool failed. ", ex));
			errorSchool(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTMergeSchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTCopy //

	@Override
	public void putcopySchool(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putcopySchool started. "));

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

			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					userSchool(siteRequest, b -> {
						if(b.succeeded()) {
							putcopySchoolResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchool(siteContext, operationRequest, body);
											try {
												aSearchSchool(siteRequest2, false, true, "/api/school/copy", "PUTCopy", d -> {
													if(d.succeeded()) {
														SearchList<School> listSchool = d.result();
														ApiRequest apiRequest = new ApiRequest();
														apiRequest.setRows(listSchool.getRows());
														apiRequest.setNumFound(listSchool.getQueryResponse().getResults().getNumFound());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest2);
														siteRequest2.setApiRequest_(apiRequest);
														siteRequest2.getVertx().eventBus().publish("websocketSchool", JsonObject.mapFrom(apiRequest).toString());
														sqlSchool(siteRequest2, e -> {
															if(e.succeeded()) {
																try {
																	listPUTCopySchool(apiRequest, listSchool, f -> {
																		if(f.succeeded()) {
																			putcopySchoolResponse(siteRequest2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("putcopySchool succeeded. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("putcopySchool failed. ", g.cause()));
																					errorSchool(siteRequest2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("putcopySchool failed. ", f.cause()));
																			errorSchool(siteRequest2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("putcopySchool failed. ", ex));
																	errorSchool(siteRequest2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("putcopySchool failed. ", e.cause()));
																errorSchool(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putcopySchool failed. ", d.cause()));
														errorSchool(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putcopySchool failed. ", ex));
												errorSchool(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putcopySchool failed. ", c.cause()));
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putcopySchool failed. ", b.cause()));
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putcopySchool failed. ", a.cause()));
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchool failed. ", ex));
			errorSchool(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTCopySchool(ApiRequest apiRequest, SearchList<School> listSchool, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchool.getSiteRequest_();
		listSchool.getList().forEach(o -> {
			futures.add(
				putcopySchoolFuture(siteRequest, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						School school = a.result();
					} else {
						errorSchool(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchool.size());
				if(listSchool.next()) {
					listPUTCopySchool(apiRequest, listSchool, eventHandler);
				} else {
					response200PUTCopySchool(siteRequest, eventHandler);
				}
			} else {
				errorSchool(listSchool.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<School> putcopySchoolFuture(SiteRequestEnUS siteRequest, JsonObject jsonObject, Handler<AsyncResult<School>> eventHandler) {
		Promise<School> promise = Promise.promise();
		try {

			jsonObject.put("saves", Optional.ofNullable(jsonObject.getJsonArray("saves")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("saves").add(o.getKey());
			});

			createSchool(siteRequest, a -> {
				if(a.succeeded()) {
					School school = a.result();
					sqlPUTCopySchool(school, jsonObject, b -> {
						if(b.succeeded()) {
							defineSchool(school, c -> {
								if(c.succeeded()) {
									attributeSchool(school, d -> {
										if(d.succeeded()) {
											indexSchool(school, e -> {
												if(e.succeeded()) {
													ApiRequest apiRequest = siteRequest.getApiRequest_();
													if(apiRequest != null) {
														apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
														if(apiRequest.getNumFound() == 1L) {
															school.apiRequestSchool();
														}
														siteRequest.getVertx().eventBus().publish("websocketSchool", JsonObject.mapFrom(apiRequest).toString());
													}
													eventHandler.handle(Future.succeededFuture(school));
													promise.complete(school);
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
			errorSchool(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopySchool(School o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "yearKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("schoolKey", l, "yearKeys", pk));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("SchoolYear");
							}
						}
						break;
					case "schoolName":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("schoolName", jsonObject.getString(entityVar), pk));
						break;
					case "schoolPhoneNumber":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("schoolPhoneNumber", jsonObject.getString(entityVar), pk));
						break;
					case "schoolAdministratorName":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("schoolAdministratorName", jsonObject.getString(entityVar), pk));
						break;
					case "schoolEmailFrom":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("schoolEmailFrom", jsonObject.getString(entityVar), pk));
						break;
					case "schoolEmailTo":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("schoolEmailTo", jsonObject.getString(entityVar), pk));
						break;
					case "schoolLocation":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("schoolLocation", jsonObject.getString(entityVar), pk));
						break;
					case "schoolAddress":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("schoolAddress", jsonObject.getString(entityVar), pk));
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

	public void putcopySchoolResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTCopySchool(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchool failed. ", ex));
			errorSchool(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTCopySchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// SearchPage //

	@Override
	public void searchpageSchoolId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageSchool(operationRequest, eventHandler);
	}

	@Override
	public void searchpageSchool(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest);
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

			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					userSchool(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchool(siteRequest, false, true, "/school", "SearchPage", c -> {
								if(c.succeeded()) {
									SearchList<School> listSchool = c.result();
									searchpageSchoolResponse(listSchool, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchpageSchool succeeded. "));
										} else {
											LOGGER.error(String.format("searchpageSchool failed. ", d.cause()));
											errorSchool(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("searchpageSchool failed. ", c.cause()));
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("searchpageSchool failed. ", b.cause()));
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("searchpageSchool failed. ", a.cause()));
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchool failed. ", ex));
			errorSchool(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchpageSchoolResponse(SearchList<School> listSchool, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchool.getSiteRequest_();
		try {
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(siteRequest, buffer);
			siteRequest.setW(w);
			response200SearchPageSchool(listSchool, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchool failed. ", ex));
			errorSchool(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchPageSchool(SearchList<School> listSchool, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchool.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listSchool.getSiteRequest_(), buffer);
			SchoolPage page = new SchoolPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/school");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listSchool.size() == 1)
				siteRequest.setRequestPk(listSchool.get(0).getPk());
			siteRequest.setW(w);
			page.setListSchool(listSchool);
			page.setSiteRequest_(siteRequest);
			page.initDeepSchoolPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<School> defineIndexSchool(School school, Handler<AsyncResult<School>> eventHandler) {
		Promise<School> promise = Promise.promise();
		SiteRequestEnUS siteRequest = school.getSiteRequest_();
		defineSchool(school, c -> {
			if(c.succeeded()) {
				attributeSchool(school, d -> {
					if(d.succeeded()) {
						indexSchool(school, e -> {
							if(e.succeeded()) {
								eventHandler.handle(Future.succeededFuture(school));
								promise.complete(school);
							} else {
								errorSchool(siteRequest, null, e);
							}
						});
					} else {
						errorSchool(siteRequest, null, d);
					}
				});
			} else {
				errorSchool(siteRequest, null, c);
			}
		});
		return promise.future();
	}

	public void createSchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<School>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(School.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				School o = new School();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void errorSchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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

	public void sqlSchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public SiteRequestEnUS generateSiteRequestEnUSForSchool(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForSchool(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForSchool(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userSchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
								userSchoolDefine(siteRequest, jsonObject, false);

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
														errorSchool(siteRequest, eventHandler, d);
													}
												});
											} else {
												errorSchool(siteRequest, eventHandler, c);
											}
										});
									} else {
										errorSchool(siteRequest, eventHandler, b);
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
								Boolean define = userSchoolDefine(siteRequest, jsonObject, true);
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
													errorSchool(siteRequest, eventHandler, d);
												}
											});
										} else {
											errorSchool(siteRequest, eventHandler, c);
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

	public Boolean userSchoolDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void aSearchSchoolQ(String uri, String apiMethod, SearchList<School> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
			searchList.setHighlight(true);
			searchList.setHighlightSnippets(3);
			searchList.addHighlightField(varIndexed);
			searchList.setParam("hl.encoder", "html");
		}
	}

	public void aSearchSchoolFq(String uri, String apiMethod, SearchList<School> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
	}

	public void aSearchSchoolSort(String uri, String apiMethod, SearchList<School> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addSort(varIndexed, ORDER.valueOf(valueIndexed));
	}

	public void aSearchSchoolRows(String uri, String apiMethod, SearchList<School> searchList, Integer valueRows) {
			searchList.setRows(apiMethod != null && apiMethod.contains("Search") ? valueRows : 10);
	}

	public void aSearchSchoolStart(String uri, String apiMethod, SearchList<School> searchList, Integer valueStart) {
		searchList.setStart(valueStart);
	}

	public void aSearchSchoolVar(String uri, String apiMethod, SearchList<School> searchList, String var, String value) {
		searchList.getSiteRequest_().getRequestVars().put(var, value);
	}

	public void aSearchSchoolUri(String uri, String apiMethod, SearchList<School> searchList) {
	}

	public void aSearchSchool(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String uri, String apiMethod, Handler<AsyncResult<SearchList<School>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<School> searchList = new SearchList<School>();
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.setQuery("*:*");
			searchList.setC(School.class);
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
								varIndexed = "*".equals(entityVar) ? entityVar : School.varSearchSchool(entityVar);
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								valueIndexed = StringUtils.isEmpty(valueIndexed) ? "*" : valueIndexed;
								aSearchSchoolQ(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "fq":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = School.varIndexedSchool(entityVar);
								aSearchSchoolFq(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = School.varIndexedSchool(entityVar);
								aSearchSchoolSort(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "start":
								valueStart = (Integer)paramObject;
								aSearchSchoolStart(uri, apiMethod, searchList, valueStart);
								break;
							case "rows":
								valueRows = (Integer)paramObject;
								aSearchSchoolRows(uri, apiMethod, searchList, valueRows);
								break;
							case "var":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								aSearchSchoolVar(uri, apiMethod, searchList, entityVar, valueIndexed);
								break;
						}
					}
					aSearchSchoolUri(uri, apiMethod, searchList);
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

	public void defineSchool(School o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void attributeSchool(School o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void indexSchool(School o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			if(BooleanUtils.isFalse(Optional.ofNullable(siteRequest.getApiRequest_()).map(ApiRequest::getEmpty).orElse(true))) {
				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchool(siteContext, siteRequest.getOperationRequest(), new JsonObject());
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				SearchList<School> searchList = new SearchList<School>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(School.class);
				searchList.addFilterQuery("modified_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(siteRequest.getApiRequest_().getCreated().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				searchList.add("json.facet", "{yearKeys:{terms:{field:yearKeys_indexed_longs, limit:1000}}}");
				searchList.setRows(1000);
				searchList.initDeepSearchList(siteRequest2);
				List<Future> futures = new ArrayList<>();

				for(int i=0; i < pks.size(); i++) {
					Long pk2 = pks.get(i);
					String classSimpleName2 = classes.get(i);

					if("SchoolYear".equals(classSimpleName2) && pk2 != null) {
						SchoolYearEnUSGenApiServiceImpl service = new SchoolYearEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
						SearchList<SchoolYear> searchList2 = new SearchList<SchoolYear>();
						if(pk2 != null) {
							searchList2.setStore(true);
							searchList2.setQuery("*:*");
							searchList2.setC(SchoolYear.class);
							searchList2.addFilterQuery("pk_indexed_long:" + pk2);
							searchList2.setRows(1);
							searchList2.initDeepSearchList(siteRequest2);
							SchoolYear o2 = searchList2.getList().stream().findFirst().orElse(null);

							if(o2 != null) {
								ApiRequest apiRequest2 = new ApiRequest();
								apiRequest2.setRows(1);
								apiRequest2.setNumFound(1l);
								apiRequest2.setNumPATCH(0L);
								apiRequest2.initDeepApiRequest(siteRequest2);
								siteRequest2.setApiRequest_(apiRequest2);
								siteRequest2.getVertx().eventBus().publish("websocketSchoolYear", JsonObject.mapFrom(apiRequest2).toString());

								o2.setPk(pk2);
								o2.setSiteRequest_(siteRequest2);
								futures.add(
									service.patchSchoolYearFuture(o2, false, a -> {
										if(a.succeeded()) {
											LOGGER.info(String.format("SchoolYear %s refreshed. ", pk2));
										} else {
											LOGGER.info(String.format("SchoolYear %s failed. ", pk2));
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
						SchoolEnUSApiServiceImpl service = new SchoolEnUSApiServiceImpl(siteRequest2.getSiteContext_());
						List<Future> futures2 = new ArrayList<>();
						for(School o2 : searchList.getList()) {
							futures2.add(
								service.patchSchoolFuture(o2, false, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("School %s refreshed. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("School %s failed. ", o2.getPk()));
										eventHandler.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Refresh School succeeded. ");
								eventHandler.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Refresh relations failed. ", b.cause());
								errorSchool(siteRequest2, eventHandler, b);
							}
						});
					} else {
						LOGGER.error("Refresh relations failed. ", a.cause());
						errorSchool(siteRequest2, eventHandler, a);
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
