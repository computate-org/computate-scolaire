package org.computate.scolaire.enUS.school;

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
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.writer.AllWriter;


/**
 * Translate: false
 **/
public class SchoolEnUSGenApiServiceImpl implements SchoolEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SchoolEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public SchoolEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
		SchoolEnUSGenApiService service = SchoolEnUSGenApiService.createProxy(siteContext.getVertx(), SERVICE_ADDRESS);
	}

	// POST //

	@Override
	public void postSchool(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest, body);
			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					createSchool(siteRequest, b -> {
						if(b.succeeded()) {
						ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							School school = b.result();
							sqlPOSTSchool(school, c -> {
								if(c.succeeded()) {
									defineSchool(school, d -> {
										if(d.succeeded()) {
											attributeSchool(school, e -> {
												if(e.succeeded()) {
													indexSchool(school, f -> {
														if(f.succeeded()) {
															response200POSTSchool(school, g -> {
																if(f.succeeded()) {
																	SQLConnection sqlConnection = siteRequest.getSqlConnection();
																	sqlConnection.commit(h -> {
																		if(a.succeeded()) {
																			sqlConnection.close(i -> {
																				if(a.succeeded()) {
																					siteRequest.getVertx().eventBus().publish("websocketSchool", JsonObject.mapFrom(apiRequest).toString());
																					eventHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					errorSchool(siteRequest, eventHandler, i);
																				}
																			});
																		} else {
																			errorSchool(siteRequest, eventHandler, h);
																		}
																	});
																} else {
																	errorSchool(siteRequest, eventHandler, g);
																}
															});
														} else {
															errorSchool(siteRequest, eventHandler, f);
														}
													});
												} else {
													errorSchool(siteRequest, eventHandler, e);
												}
											});
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
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchool(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void sqlPOSTSchool(School o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			StringBuilder postSql = new StringBuilder();
			List<Object> postSqlParams = new ArrayList<Object>();

			if(jsonObject != null) {
				Set<String> entityVars = jsonObject.fieldNames();
				for(String entityVar : entityVars) {
					switch(entityVar) {
					case "yearKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							postSql.append(SiteContextEnUS.SQL_addA);
							postSqlParams.addAll(Arrays.asList("schoolKey", l, "yearKeys", pk));
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

	public void response200POSTSchool(School o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(o);
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Optional.ofNullable(json).orElse(new JsonObject()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUT //

	@Override
	public void putSchool(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest, body);
			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					userSchool(siteRequest, b -> {
						if(b.succeeded()) {
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									aSearchSchool(siteRequest, false, true, null, d -> {
										if(d.succeeded()) {
											SearchList<School> listSchool = d.result();
											ApiRequest apiRequest = new ApiRequest();
											apiRequest.setRows(listSchool.getRows());
											apiRequest.setNumFound(Optional.ofNullable(listSchool.getQueryResponse()).map(QueryResponse::getResults).map(SolrDocumentList::getNumFound).orElse(new Long(listSchool.size())));
											apiRequest.initDeepApiRequest(siteRequest);
											siteRequest.setApiRequest_(apiRequest);
											WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
											workerExecutor.executeBlocking(
												blockingCodeHandler -> {
													sqlSchool(siteRequest, e -> {
														if(e.succeeded()) {
															try {
																listPUTSchool(apiRequest, listSchool, f -> {
																	if(f.succeeded()) {
																		SQLConnection sqlConnection2 = siteRequest.getSqlConnection();
																		if(sqlConnection2 == null) {
																			blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																		} else {
																			sqlConnection2.commit(g -> {
																				if(f.succeeded()) {
																					sqlConnection2.close(h -> {
																						if(g.succeeded()) {
																							blockingCodeHandler.handle(Future.succeededFuture(h.result()));
																						} else {
																							blockingCodeHandler.handle(Future.failedFuture(h.cause()));
																						}
																					});
																				} else {
																					blockingCodeHandler.handle(Future.failedFuture(g.cause()));
																				}
																			});
																		}
																	} else {
																		blockingCodeHandler.handle(Future.failedFuture(f.cause()));
																	}
																});
															} catch(Exception ex) {
																blockingCodeHandler.handle(Future.failedFuture(ex));
															}
														} else {
															blockingCodeHandler.handle(Future.failedFuture(e.cause()));
														}
													});
												}, resultHandler -> {
												}
											);
											response200PUTSchool(apiRequest, eventHandler);
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
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchool(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void listPUTSchool(ApiRequest apiRequest, SearchList<School> listSchool, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchool.getSiteRequest_();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		if(jsonArray.size() == 0) {
			listSchool.getList().forEach(o -> {
				futures.add(
					futurePUTSchool(siteRequest, JsonObject.mapFrom(o), a -> {
						if(a.succeeded()) {
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
						siteRequest.getVertx().eventBus().publish("websocketSchool", JsonObject.mapFrom(apiRequest).toString());
						listPUTSchool(apiRequest, listSchool, eventHandler);
					} else {
						response200PUTSchool(apiRequest, eventHandler);
					}
				} else {
					errorSchool(listSchool.getSiteRequest_(), eventHandler, a);
				}
			});
		} else {
			jsonArray.forEach(o -> {
				JsonObject jsonObject = (JsonObject)o;
				futures.add(
					futurePUTSchool(siteRequest, jsonObject, a -> {
						if(a.succeeded()) {
						} else {
							errorSchool(siteRequest, eventHandler, a);
						}
					})
				);
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					apiRequest.setNumPATCH(apiRequest.getNumPATCH() + jsonArray.size());
					response200PUTSchool(apiRequest, eventHandler);
				} else {
					errorSchool(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		}
	}

	public Future<School> futurePUTSchool(SiteRequestEnUS siteRequest, JsonObject jsonObject,  Handler<AsyncResult<OperationResponse>> eventHandler) {
		jsonObject.put("saves", Optional.ofNullable(jsonObject.getJsonArray("saves")).orElse(new JsonArray()));
		JsonObject jsonPatch = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
		jsonPatch.stream().forEach(o -> {
			jsonObject.put(o.getKey(), o.getValue());
			jsonObject.getJsonArray("saves").add(o.getKey());
		});
		Future<School> future = Future.future();
		try {
			createSchool(siteRequest, a -> {
				if(a.succeeded()) {
					School school = a.result();
					sqlPUTSchool(school, jsonObject, b -> {
						if(b.succeeded()) {
							defineSchool(school, c -> {
								if(c.succeeded()) {
									attributeSchool(school, d -> {
										if(d.succeeded()) {
											indexSchool(school, e -> {
												if(e.succeeded()) {
													apiRequestSchool(school);
													school.apiRequestSchool();
													future.complete(school);
													eventHandler.handle(Future.succeededFuture(e.result()));
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
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public void remplacerPUTSchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<School>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			Long pk = siteRequest.getRequestPk();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_clear
					, new JsonArray(Arrays.asList(pk, School.class.getCanonicalName(), pk, pk, pk))
					, remplacerAsync
			-> {
				School o = new School();
				o.setPk(pk);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlPUTSchool(School o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			StringBuilder postSql = new StringBuilder();
			List<Object> postSqlParams = new ArrayList<Object>();

			if(jsonObject != null) {
				JsonArray entityVars = jsonObject.getJsonArray("saves");
				for(Integer i = 0; i < entityVars.size(); i++) {
					String entityVar = entityVars.getString(i);
					switch(entityVar) {
					case "yearKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							postSql.append(SiteContextEnUS.SQL_addA);
							postSqlParams.addAll(Arrays.asList("schoolKey", l, "yearKeys", pk));
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

	public void response200PUTSchool(ApiRequest apiRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(JsonObject.mapFrom(apiRequest))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSchool(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest, body);
			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					userSchool(siteRequest, b -> {
						if(b.succeeded()) {
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									aSearchSchool(siteRequest, false, true, null, d -> {
										if(d.succeeded()) {
											SearchList<School> listSchool = d.result();
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

											ApiRequest apiRequest = new ApiRequest();
											apiRequest.setRows(listSchool.getRows());
											apiRequest.setNumFound(Optional.ofNullable(listSchool.getQueryResponse()).map(QueryResponse::getResults).map(SolrDocumentList::getNumFound).orElse(new Long(listSchool.size())));
											apiRequest.initDeepApiRequest(siteRequest);
											siteRequest.setApiRequest_(apiRequest);
											if(listSchool.size() == 1) {
												School o = listSchool.get(0);
												apiRequest.setPk(o.getPk());
												apiRequest.setOriginal(o);
												apiRequestSchool(o);
											}
											WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
											workerExecutor.executeBlocking(
												blockingCodeHandler -> {
													sqlSchool(siteRequest, e -> {
														if(e.succeeded()) {
															try {
																listPATCHSchool(apiRequest, listSchool, dt, f -> {
																	if(f.succeeded()) {
																		SQLConnection sqlConnection2 = siteRequest.getSqlConnection();
																		if(sqlConnection2 == null) {
																			blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																		} else {
																			sqlConnection2.commit(g -> {
																				if(f.succeeded()) {
																					sqlConnection2.close(h -> {
																						if(g.succeeded()) {
																							blockingCodeHandler.handle(Future.succeededFuture(h.result()));
																						} else {
																							blockingCodeHandler.handle(Future.failedFuture(h.cause()));
																						}
																					});
																				} else {
																					blockingCodeHandler.handle(Future.failedFuture(g.cause()));
																				}
																			});
																		}
																	} else {
																		blockingCodeHandler.handle(Future.failedFuture(f.cause()));
																	}
																});
															} catch(Exception ex) {
																blockingCodeHandler.handle(Future.failedFuture(ex));
															}
														} else {
															blockingCodeHandler.handle(Future.failedFuture(e.cause()));
														}
													});
												}, resultHandler -> {
												}
											);
											response200PATCHSchool(apiRequest, eventHandler);
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
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchool(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void listPATCHSchool(ApiRequest apiRequest, SearchList<School> listSchool, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchool.getSiteRequest_();
		listSchool.getList().forEach(o -> {
			futures.add(
				futurePATCHSchool(o, a -> {
					if(a.succeeded()) {
					} else {
						errorSchool(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchool.size());
				if(listSchool.next(dt)) {
					siteRequest.getVertx().eventBus().publish("websocketSchool", JsonObject.mapFrom(apiRequest).toString());
					listPATCHSchool(apiRequest, listSchool, dt, eventHandler);
				} else {
					response200PATCHSchool(apiRequest, eventHandler);
				}
			} else {
				errorSchool(listSchool.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<School> futurePATCHSchool(School o,  Handler<AsyncResult<OperationResponse>> eventHandler) {
		Promise<School> promise = Promise.promise();
		try {
			sqlPATCHSchool(o, a -> {
				if(a.succeeded()) {
					School school = a.result();
					defineSchool(school, b -> {
						if(b.succeeded()) {
							attributeSchool(school, c -> {
								if(c.succeeded()) {
									indexSchool(school, d -> {
										if(d.succeeded()) {
											apiRequestSchool(school);
											school.apiRequestSchool();
											promise.complete(o);
											eventHandler.handle(Future.succeededFuture(d.result()));
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
			return promise.future();
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public void sqlPATCHSchool(School o, Handler<AsyncResult<School>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject requestJson = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = requestJson.fieldNames();
			School o2 = new School();

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.school.School"));
			for(String methodName : methodNames) {
				switch(methodName) {
					case "setCreated":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "created"));
						} else {
							o2.setCreated(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("created", o2.jsonCreated(), pk));
						}
						break;
					case "setModified":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "modified"));
						} else {
							o2.setModified(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("modified", o2.jsonModified(), pk));
						}
						break;
					case "setArchived":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "archived"));
						} else {
							o2.setArchived(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("archived", o2.jsonArchived(), pk));
						}
						break;
					case "setDeleted":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "deleted"));
						} else {
							o2.setDeleted(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("deleted", o2.jsonDeleted(), pk));
						}
						break;
					case "addYearKeys":
						patchSql.append(SiteContextEnUS.SQL_addA);
						patchSqlParams.addAll(Arrays.asList("schoolKey", Long.parseLong(requestJson.getString(methodName)), "yearKeys", pk));
						break;
					case "addAllYearKeys":
						JsonArray addAllYearKeysValues = requestJson.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllYearKeysValues.size(); i++) {
							patchSql.append(SiteContextEnUS.SQL_setA2);
							patchSqlParams.addAll(Arrays.asList("schoolKey", addAllYearKeysValues.getString(i), "yearKeys", pk));
						}
						break;
					case "setYearKeys":
						JsonArray setYearKeysValues = requestJson.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("schoolKey", Long.parseLong(requestJson.getString(methodName)), "yearKeys", pk));
						for(Integer i = 0; i <  setYearKeysValues.size(); i++) {
							patchSql.append(SiteContextEnUS.SQL_setA2);
							patchSqlParams.addAll(Arrays.asList("schoolKey", setYearKeysValues.getString(i), "yearKeys", pk));
						}
						break;
					case "removeYearKeys":
						patchSql.append(SiteContextEnUS.SQL_removeA);
						patchSqlParams.addAll(Arrays.asList("schoolKey", Long.parseLong(requestJson.getString(methodName)), "yearKeys", pk));
						break;
					case "setSchoolName":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "schoolName"));
						} else {
							o2.setSchoolName(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("schoolName", o2.jsonSchoolName(), pk));
						}
						break;
					case "setSchoolPhoneNumber":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "schoolPhoneNumber"));
						} else {
							o2.setSchoolPhoneNumber(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("schoolPhoneNumber", o2.jsonSchoolPhoneNumber(), pk));
						}
						break;
					case "setSchoolAdministratorName":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "schoolAdministratorName"));
						} else {
							o2.setSchoolAdministratorName(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("schoolAdministratorName", o2.jsonSchoolAdministratorName(), pk));
						}
						break;
					case "setSchoolLocation":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "schoolLocation"));
						} else {
							o2.setSchoolLocation(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("schoolLocation", o2.jsonSchoolLocation(), pk));
						}
						break;
					case "setSchoolAddress":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "schoolAddress"));
						} else {
							o2.setSchoolAddress(requestJson.getString(methodName));
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

	public void response200PATCHSchool(ApiRequest apiRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = apiRequest.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(apiRequest);
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Optional.ofNullable(json).orElse(new JsonObject()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSchool(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest);
			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					userSchool(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchool(siteRequest, false, true, null, c -> {
								if(c.succeeded()) {
									SearchList<School> listSchool = c.result();
									response200GETSchool(listSchool, d -> {
										if(d.succeeded()) {
											SQLConnection sqlConnection = siteRequest.getSqlConnection();
											if(sqlConnection == null) {
												eventHandler.handle(Future.succeededFuture(d.result()));
											} else {
												sqlConnection.commit(e -> {
													if(e.succeeded()) {
														sqlConnection.close(f -> {
															if(f.succeeded()) {
																eventHandler.handle(Future.succeededFuture(d.result()));
															} else {
																errorSchool(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
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
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchool(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200GETSchool(SearchList<School> listSchool, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchool.getSiteRequest_();
			SolrDocumentList solrDocuments = listSchool.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listSchool.getList().stream().findFirst().orElse(null));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Optional.ofNullable(json).orElse(new JsonObject()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteSchool(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest);
			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					aSearchSchool(siteRequest, false, true, null, b -> {
						if(b.succeeded()) {
							SearchList<School> listSchool = b.result();
							deleteDELETESchool(siteRequest, c -> {
								if(c.succeeded()) {
									response200DELETESchool(siteRequest, d -> {
										if(d.succeeded()) {
											SQLConnection sqlConnection = siteRequest.getSqlConnection();
											if(sqlConnection == null) {
												eventHandler.handle(Future.succeededFuture(d.result()));
											} else {
												sqlConnection.commit(e -> {
													if(e.succeeded()) {
														sqlConnection.close(f -> {
															if(f.succeeded()) {
																eventHandler.handle(Future.succeededFuture(d.result()));
															} else {
																errorSchool(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
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
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchool(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void deleteDELETESchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			Long pk = siteRequest.getRequestPk();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_delete
					, new JsonArray(Arrays.asList(pk, School.class.getCanonicalName(), pk, pk, pk, pk))
					, deleteAsync
			-> {
				eventHandler.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void response200DELETESchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Optional.ofNullable(json).orElse(new JsonObject()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchSchool(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest);
			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					userSchool(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchool(siteRequest, false, true, "/api/school", c -> {
								if(c.succeeded()) {
									SearchList<School> listSchool = c.result();
									response200SearchSchool(listSchool, d -> {
										if(d.succeeded()) {
											SQLConnection sqlConnection = siteRequest.getSqlConnection();
											if(sqlConnection == null) {
												eventHandler.handle(Future.succeededFuture(d.result()));
											} else {
												sqlConnection.commit(e -> {
													if(e.succeeded()) {
														sqlConnection.close(f -> {
															if(f.succeeded()) {
																eventHandler.handle(Future.succeededFuture(d.result()));
															} else {
																errorSchool(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
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
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchool(null, eventHandler, Future.failedFuture(e));
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
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Optional.ofNullable(json).orElse(new JsonObject()))));
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
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest);
			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					userSchool(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchool(siteRequest, false, true, "/school", c -> {
								if(c.succeeded()) {
									SearchList<School> listSchool = c.result();
									response200SearchPageSchool(listSchool, d -> {
										if(d.succeeded()) {
											SQLConnection sqlConnection = siteRequest.getSqlConnection();
											if(sqlConnection == null) {
												eventHandler.handle(Future.succeededFuture(d.result()));
											} else {
												sqlConnection.commit(e -> {
													if(e.succeeded()) {
														sqlConnection.close(f -> {
															if(f.succeeded()) {
																eventHandler.handle(Future.succeededFuture(d.result()));
															} else {
																errorSchool(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
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
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchool(null, eventHandler, Future.failedFuture(e));
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

	// Partagé //

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

	public void apiRequestSchool(School o) {
		ApiRequest apiRequest = o.getSiteRequest_().getApiRequest_();
		if(apiRequest != null) {
			List<Long> pks = apiRequest.getPks();
			List<String> classes = apiRequest.getClasses();
			for(Long pk : o.getYearKeys()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("SchoolYear");
				}
			}
		}
	}

	public void errorSchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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
						JsonArray userValues = selectCAsync.result().getResults().stream().findFirst().orElse(null);
						if(userValues == null) {
							sqlConnection.queryWithParams(
									SiteContextEnUS.SQL_create
									, new JsonArray(Arrays.asList("org.computate.scolaire.enUS.user.SiteUser", userId))
									, createAsync
							-> {
								JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
								Long pkUser = createLine.getLong(0);
								SiteUser siteUser = new SiteUser();
								siteUser.setSiteRequest_(siteRequest);
								siteUser.setPk(pkUser);

								sqlConnection.queryWithParams(
										SiteContextEnUS.SQL_define
										, new JsonArray(Arrays.asList(pkUser, pkUser, pkUser))
										, defineAsync
								-> {
									if(defineAsync.succeeded()) {
										try {
											for(JsonArray definition : defineAsync.result().getResults()) {
												siteUser.defineForClass(definition.getString(0), definition.getString(1));
											}
											JsonObject userVertx = siteRequest.getOperationRequest().getUser();
											JsonObject jsonPrincipal = KeycloakHelper.parseToken(userVertx.getString("access_token"));
											siteUser.setUserName(jsonPrincipal.getString("preferred_username"));
											siteUser.setUserFirstName(jsonPrincipal.getString("given_name"));
											siteUser.setUserLastName(jsonPrincipal.getString("family_name"));
											siteUser.setUserId(jsonPrincipal.getString("sub"));
											siteUser.initDeepForClass(siteRequest);
											siteUser.indexForClass();
											siteRequest.setSiteUser(siteUser);
											siteRequest.setUserName(jsonPrincipal.getString("preferred_username"));
											siteRequest.setUserFirstName(jsonPrincipal.getString("given_name"));
											siteRequest.setUserLastName(jsonPrincipal.getString("family_name"));
											siteRequest.setUserId(jsonPrincipal.getString("sub"));
											eventHandler.handle(Future.succeededFuture());
										} catch(Exception e) {
											eventHandler.handle(Future.failedFuture(e));
										}
									} else {
										eventHandler.handle(Future.failedFuture(new Exception(defineAsync.cause())));
									}
								});
							});
						} else {
							Long pkUser = userValues.getLong(0);
							SiteUser siteUser = new SiteUser();
								siteUser.setSiteRequest_(siteRequest);
							siteUser.setPk(pkUser);

							sqlConnection.queryWithParams(
									SiteContextEnUS.SQL_define
									, new JsonArray(Arrays.asList(pkUser, pkUser, pkUser))
									, defineAsync
							-> {
								if(defineAsync.succeeded()) {
									try {
										for(JsonArray definition : defineAsync.result().getResults()) {
											siteUser.defineForClass(definition.getString(0), definition.getString(1));
										}
										JsonObject userVertx = siteRequest.getOperationRequest().getUser();
										JsonObject jsonPrincipal = KeycloakHelper.parseToken(userVertx.getString("access_token"));
										siteUser.setUserName(jsonPrincipal.getString("preferred_username"));
										siteUser.setUserFirstName(jsonPrincipal.getString("given_name"));
										siteUser.setUserLastName(jsonPrincipal.getString("family_name"));
										siteUser.setUserId(jsonPrincipal.getString("sub"));
										siteUser.initDeepForClass(siteRequest);
										siteUser.indexForClass();
										siteRequest.setSiteUser(siteUser);
										siteRequest.setUserName(jsonPrincipal.getString("preferred_username"));
										siteRequest.setUserFirstName(jsonPrincipal.getString("given_name"));
										siteRequest.setUserLastName(jsonPrincipal.getString("family_name"));
										siteRequest.setUserId(jsonPrincipal.getString("sub"));
										eventHandler.handle(Future.succeededFuture());
									} catch(Exception e) {
										eventHandler.handle(Future.failedFuture(e));
									}
								} else {
									eventHandler.handle(Future.failedFuture(new Exception(defineAsync.cause())));
								}
							});
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

	public void aSearchSchool(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String classApiUriMethod, Handler<AsyncResult<SearchList<School>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<School> listSearch = new SearchList<School>();
			listSearch.setPopulate(populate);
			listSearch.setStore(store);
			listSearch.setQuery("*:*");
			listSearch.setC(School.class);
			if(entityList != null)
				listSearch.addFields(entityList);
			listSearch.set("json.facet", "{max_modified:'max(modified_indexed_date)'}");

			String id = operationRequest.getParams().getJsonObject("path").getString("id");
			if(id != null) {
				listSearch.addFilterQuery("(id:" + ClientUtils.escapeQueryChars(id) + " OR objectId_indexed_string:" + ClientUtils.escapeQueryChars(id) + ")");
			}

			operationRequest.getParams().getJsonObject("query").forEach(paramRequest -> {
				String entityVar = null;
				String valueIndexed = null;
				String varIndexed = null;
				String valueSort = null;
				Integer aSearchStart = null;
				Integer aSearchNum = null;
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
								listSearch.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
								if(!"*".equals(entityVar)) {
									listSearch.setHighlight(true);
									listSearch.setHighlightSnippets(3);
									listSearch.addHighlightField(varIndexed);
									listSearch.setParam("hl.encoder", "html");
								}
								break;
							case "fq":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = School.varIndexedSchool(entityVar);
								listSearch.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueSort = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = School.varIndexedSchool(entityVar);
								listSearch.addSort(varIndexed, ORDER.valueOf(valueSort));
								break;
							case "start":
								aSearchStart = (Integer)paramObject;
								listSearch.setStart(aSearchStart);
								break;
							case "rows":
								aSearchNum = (Integer)paramObject;
								listSearch.setRows(aSearchNum);
								break;
						}
					}
				} catch(Exception e) {
					eventHandler.handle(Future.failedFuture(e));
				}
			});
			if(listSearch.getSorts().size() == 0)
				listSearch.addSort("created_indexed_date", ORDER.desc);
			listSearch.initDeepForClass(siteRequest);
			eventHandler.handle(Future.succeededFuture(listSearch));
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
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			eventHandler.handle(Future.succeededFuture());
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}
}
