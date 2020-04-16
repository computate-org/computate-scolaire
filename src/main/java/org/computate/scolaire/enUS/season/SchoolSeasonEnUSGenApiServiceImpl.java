package org.computate.scolaire.enUS.season;

import org.computate.scolaire.enUS.year.SchoolYearEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.year.SchoolYear;
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
import org.computate.scolaire.enUS.user.SiteUserEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.writer.AllWriter;


/**
 * Translate: false
 * CanonicalName.frFR: org.computate.scolaire.frFR.saison.SaisonScolaireFrFRGenApiServiceImpl
 **/
public class SchoolSeasonEnUSGenApiServiceImpl implements SchoolSeasonEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolSeasonEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SchoolSeasonEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public SchoolSeasonEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
	}

	// POST //

	@Override
	public void postSchoolSeason(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolSeason(siteContext, operationRequest, body);

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

			sqlSchoolSeason(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolSeason(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							postSchoolSeasonFuture(siteRequest, c -> {
								if(c.succeeded()) {
									SchoolSeason schoolSeason = c.result();
									apiRequestSchoolSeason(schoolSeason);
									postSchoolSeasonResponse(schoolSeason, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postSchoolSeason succeeded. "));
										} else {
											LOGGER.error(String.format("postSchoolSeason failed. ", d.cause()));
											errorSchoolSeason(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolSeason(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolSeason(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolSeason(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolSeason(null, eventHandler, Future.failedFuture(e));
		}
	}


	public Future<SchoolSeason> postSchoolSeasonFuture(SiteRequestEnUS siteRequest, Handler<AsyncResult<SchoolSeason>> eventHandler) {
		Promise<SchoolSeason> promise = Promise.promise();
		try {
			createSchoolSeason(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolSeason schoolSeason = a.result();
					sqlPOSTSchoolSeason(schoolSeason, false, b -> {
						if(b.succeeded()) {
							defineIndexSchoolSeason(schoolSeason, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(schoolSeason));
									promise.complete(schoolSeason);
								} else {
									errorSchoolSeason(siteRequest, null, c);
								}
							});
						} else {
							errorSchoolSeason(siteRequest, null, b);
						}
					});
				} else {
					errorSchoolSeason(siteRequest, null, a);
				}
			});
		} catch(Exception e) {
			errorSchoolSeason(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTSchoolSeason(SchoolSeason o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "yearKey":
						{
							Long l = Long.parseLong(jsonObject.getString(entityVar));
							SearchList<SchoolYear> searchList = new SearchList<SchoolYear>();
							searchList.setQuery("*:*");
							searchList.setStore(true);
							searchList.setC(SchoolYear.class);
							searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							searchList.initDeepSearchList(siteRequest);
							l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								postSql.append(SiteContextEnUS.SQL_addA);
								postSqlParams.addAll(Arrays.asList("seasonKeys", l, "yearKey", pk));
							}
						}
						break;
					case "sessionKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							SearchList<SchoolSession> searchList = new SearchList<SchoolSession>();
							searchList.setQuery("*:*");
							searchList.setStore(true);
							searchList.setC(SchoolSession.class);
							searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							searchList.initDeepSearchList(siteRequest);
							l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								postSql.append(SiteContextEnUS.SQL_addA);
								postSqlParams.addAll(Arrays.asList("seasonKey", l, "sessionKeys", pk));
							}
						}
						break;
					case "seasonStartDate":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("seasonStartDate", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "seasonSummer":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("seasonSummer", jsonObject.getBoolean(entityVar), pk));
						break;
					case "seasonWinter":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("seasonWinter", jsonObject.getBoolean(entityVar), pk));
						break;
					case "seasonFuture":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("seasonFuture", jsonObject.getBoolean(entityVar), pk));
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

	public void postSchoolSeasonResponse(SchoolSeason schoolSeason, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = schoolSeason.getSiteRequest_();
		response200POSTSchoolSeason(schoolSeason, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								ApiRequest apiRequest = apiRequestSchoolSeason(schoolSeason);
								schoolSeason.apiRequestSchoolSeason();
								siteRequest.getVertx().eventBus().publish("websocketSchoolSeason", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolSeason(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolSeason(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolSeason(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200POSTSchoolSeason(SchoolSeason o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
	public void putimportSchoolSeason(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolSeason(siteContext, operationRequest, body);

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

			sqlSchoolSeason(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolSeason(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											sqlSchoolSeason(siteRequest, d -> {
												if(d.succeeded()) {
													try {
														listPUTImportSchoolSeason(apiRequest, siteRequest, e -> {
															if(e.succeeded()) {
																putimportSchoolSeasonResponse(siteRequest, f -> {
																	if(f.succeeded()) {
																		eventHandler.handle(Future.succeededFuture(f.result()));
																		LOGGER.info(String.format("putimportSchoolSeason succeeded. "));
																	} else {
																		LOGGER.error(String.format("putimportSchoolSeason failed. ", f.cause()));
																		errorSchoolSeason(siteRequest, eventHandler, f);
																	}
																});
															} else {
																blockingCodeHandler.handle(Future.failedFuture(e.cause()));
															}
														});
													} catch(Exception ex) {
												blockingCodeHandler.handle(Future.failedFuture(ex));
													}
												} else {
													blockingCodeHandler.handle(Future.failedFuture(d.cause()));
												}
											});
										}, resultHandler -> {
										}
									);
								} else {
									errorSchoolSeason(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolSeason(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolSeason(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolSeason(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPUTImportSchoolSeason(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		jsonArray.forEach(obj -> {
			JsonObject json = (JsonObject)obj;

			SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolSeason(siteContext, siteRequest.getOperationRequest(), json);
			siteRequest2.setSqlConnection(siteRequest.getSqlConnection());

			SearchList<SchoolSeason> searchList = new SearchList<SchoolSeason>();
			searchList.setStore(true);
			searchList.setQuery("*:*");
			searchList.setC(SchoolSeason.class);
			searchList.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
			searchList.initDeepForClass(siteRequest2);

			if(searchList.size() == 1) {
				SchoolSeason o = searchList.get(0);
				JsonObject json2 = new JsonObject();
				for(String f : json.fieldNames()) {
					json2.put("set" + StringUtils.capitalize(f), json.getValue(f));
				}
				for(String f : o.getSaves()) {
					if(!json.fieldNames().contains(f))
						json2.putNull("set" + StringUtils.capitalize(f));
				}
				siteRequest2.setJsonObject(json2);
				futures.add(
					patchSchoolSeasonFuture(o, a -> {
						if(a.succeeded()) {
							SchoolSeason schoolSeason = a.result();
							apiRequestSchoolSeason(schoolSeason);
						} else {
							errorSchoolSeason(siteRequest2, eventHandler, a);
						}
					})
				);
			} else {
				futures.add(
					postSchoolSeasonFuture(siteRequest2, a -> {
						if(a.succeeded()) {
							SchoolSeason schoolSeason = a.result();
							apiRequestSchoolSeason(schoolSeason);
						} else {
							errorSchoolSeason(siteRequest2, eventHandler, a);
						}
					})
				);
			}
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + jsonArray.size());
				response200PUTImportSchoolSeason(siteRequest, eventHandler);
			} else {
				errorSchoolSeason(apiRequest.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public void sqlPUTImportSchoolSeason(SchoolSeason o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void putimportSchoolSeasonResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		response200PUTImportSchoolSeason(siteRequest, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketSchoolSeason", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolSeason(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolSeason(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolSeason(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PUTImportSchoolSeason(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(apiRequest).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTMerge //

	@Override
	public void putmergeSchoolSeason(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolSeason(siteContext, operationRequest, body);

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

			sqlSchoolSeason(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolSeason(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											sqlSchoolSeason(siteRequest, d -> {
												if(d.succeeded()) {
													try {
														listPUTMergeSchoolSeason(apiRequest, siteRequest, e -> {
															if(e.succeeded()) {
																putmergeSchoolSeasonResponse(siteRequest, f -> {
																	if(f.succeeded()) {
																		eventHandler.handle(Future.succeededFuture(f.result()));
																		LOGGER.info(String.format("putmergeSchoolSeason succeeded. "));
																	} else {
																		LOGGER.error(String.format("putmergeSchoolSeason failed. ", f.cause()));
																		errorSchoolSeason(siteRequest, eventHandler, f);
																	}
																});
															} else {
																blockingCodeHandler.handle(Future.failedFuture(e.cause()));
															}
														});
													} catch(Exception ex) {
												blockingCodeHandler.handle(Future.failedFuture(ex));
													}
												} else {
													blockingCodeHandler.handle(Future.failedFuture(d.cause()));
												}
											});
										}, resultHandler -> {
										}
									);
								} else {
									errorSchoolSeason(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolSeason(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolSeason(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolSeason(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPUTMergeSchoolSeason(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		jsonArray.forEach(obj -> {
			JsonObject json = (JsonObject)obj;

			SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolSeason(siteContext, siteRequest.getOperationRequest(), json);
			siteRequest2.setSqlConnection(siteRequest.getSqlConnection());

			SearchList<SchoolSeason> searchList = new SearchList<SchoolSeason>();
			searchList.setStore(true);
			searchList.setQuery("*:*");
			searchList.setC(SchoolSeason.class);
			searchList.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
			searchList.initDeepForClass(siteRequest2);

			if(searchList.size() == 1) {
				SchoolSeason o = searchList.get(0);
				JsonObject json2 = new JsonObject();
				for(String f : json.fieldNames()) {
					json2.put("set" + StringUtils.capitalize(f), json.getValue(f));
				}
				for(String f : o.getSaves()) {
					if(!json.fieldNames().contains(f))
						json2.putNull("set" + StringUtils.capitalize(f));
				}
				siteRequest2.setJsonObject(json2);
				futures.add(
					patchSchoolSeasonFuture(o, a -> {
						if(a.succeeded()) {
							SchoolSeason schoolSeason = a.result();
							apiRequestSchoolSeason(schoolSeason);
						} else {
							errorSchoolSeason(siteRequest2, eventHandler, a);
						}
					})
				);
			} else {
				futures.add(
					postSchoolSeasonFuture(siteRequest2, a -> {
						if(a.succeeded()) {
							SchoolSeason schoolSeason = a.result();
							apiRequestSchoolSeason(schoolSeason);
						} else {
							errorSchoolSeason(siteRequest2, eventHandler, a);
						}
					})
				);
			}
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + jsonArray.size());
				response200PUTMergeSchoolSeason(siteRequest, eventHandler);
			} else {
				errorSchoolSeason(apiRequest.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public void sqlPUTMergeSchoolSeason(SchoolSeason o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void putmergeSchoolSeasonResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		response200PUTMergeSchoolSeason(siteRequest, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketSchoolSeason", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolSeason(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolSeason(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolSeason(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PUTMergeSchoolSeason(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(apiRequest).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTCopy //

	@Override
	public void putcopySchoolSeason(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolSeason(siteContext, operationRequest, body);

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

			sqlSchoolSeason(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolSeason(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									aSearchSchoolSeason(siteRequest, false, true, null, d -> {
										if(d.succeeded()) {
											SearchList<SchoolSeason> listSchoolSeason = d.result();
											WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
											workerExecutor.executeBlocking(
												blockingCodeHandler -> {
													sqlSchoolSeason(siteRequest, e -> {
														if(e.succeeded()) {
															try {
																listPUTCopySchoolSeason(apiRequest, listSchoolSeason, f -> {
																	if(f.succeeded()) {
																		putcopySchoolSeasonResponse(listSchoolSeason, g -> {
																			if(g.succeeded()) {
																				eventHandler.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("putcopySchoolSeason succeeded. "));
																			} else {
																				LOGGER.error(String.format("putcopySchoolSeason failed. ", g.cause()));
																				errorSchoolSeason(siteRequest, eventHandler, d);
																			}
																		});
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
										} else {
											errorSchoolSeason(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolSeason(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolSeason(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolSeason(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolSeason(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPUTCopySchoolSeason(ApiRequest apiRequest, SearchList<SchoolSeason> listSchoolSeason, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolSeason.getSiteRequest_();
		listSchoolSeason.getList().forEach(o -> {
			futures.add(
				putcopySchoolSeasonFuture(siteRequest, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						SchoolSeason schoolSeason = a.result();
						apiRequestSchoolSeason(schoolSeason);
					} else {
						errorSchoolSeason(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchoolSeason.size());
				if(listSchoolSeason.next()) {
					siteRequest.getVertx().eventBus().publish("websocketSchoolSeason", JsonObject.mapFrom(apiRequest).toString());
					listPUTCopySchoolSeason(apiRequest, listSchoolSeason, eventHandler);
				} else {
					response200PUTCopySchoolSeason(listSchoolSeason, eventHandler);
				}
			} else {
				errorSchoolSeason(listSchoolSeason.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolSeason> putcopySchoolSeasonFuture(SiteRequestEnUS siteRequest, JsonObject jsonObject, Handler<AsyncResult<SchoolSeason>> eventHandler) {
		Promise<SchoolSeason> promise = Promise.promise();
		try {

			jsonObject.put("saves", Optional.ofNullable(jsonObject.getJsonArray("saves")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("saves").add(o.getKey());
			});

			createSchoolSeason(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolSeason schoolSeason = a.result();
					sqlPUTCopySchoolSeason(schoolSeason, jsonObject, b -> {
						if(b.succeeded()) {
							defineSchoolSeason(schoolSeason, c -> {
								if(c.succeeded()) {
									attributeSchoolSeason(schoolSeason, d -> {
										if(d.succeeded()) {
											indexSchoolSeason(schoolSeason, e -> {
												if(e.succeeded()) {
													eventHandler.handle(Future.succeededFuture(schoolSeason));
													promise.complete(schoolSeason);
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
			errorSchoolSeason(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopySchoolSeason(SchoolSeason o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "yearKey":
						putSql.append(SiteContextEnUS.SQL_addA);
						putSqlParams.addAll(Arrays.asList("seasonKeys", Long.parseLong(jsonObject.getString(entityVar)), "yearKey", pk));
						break;
					case "sessionKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("seasonKey", l, "sessionKeys", pk));
						}
						break;
					case "seasonStartDate":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("seasonStartDate", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "seasonSummer":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("seasonSummer", jsonObject.getBoolean(entityVar), pk));
						break;
					case "seasonWinter":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("seasonWinter", jsonObject.getBoolean(entityVar), pk));
						break;
					case "seasonFuture":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("seasonFuture", jsonObject.getBoolean(entityVar), pk));
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

	public void putcopySchoolSeasonResponse(SearchList<SchoolSeason> listSchoolSeason, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolSeason.getSiteRequest_();
		response200PUTCopySchoolSeason(listSchoolSeason, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketSchoolSeason", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolSeason(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolSeason(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolSeason(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PUTCopySchoolSeason(SearchList<SchoolSeason> listSchoolSeason, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolSeason.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(apiRequest).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSchoolSeason(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolSeason(siteContext, operationRequest, body);

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

			sqlSchoolSeason(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolSeason(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									aSearchSchoolSeason(siteRequest, false, true, null, d -> {
										if(d.succeeded()) {
											SearchList<SchoolSeason> listSchoolSeason = d.result();
											SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolSeason.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
											Date date = null;
											if(facets != null)
												date = (Date)facets.get("max_modified");
											String dt;
											if(date == null)
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
											else
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
											listSchoolSeason.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));

											if(listSchoolSeason.size() == 1) {
												SchoolSeason o = listSchoolSeason.get(0);
												apiRequest.setPk(o.getPk());
												apiRequest.setOriginal(o);
												apiRequestSchoolSeason(o);
											o.apiRequestSchoolSeason();
											}
											WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
											workerExecutor.executeBlocking(
												blockingCodeHandler -> {
													sqlSchoolSeason(siteRequest, e -> {
														if(e.succeeded()) {
															try {
																listPATCHSchoolSeason(apiRequest, listSchoolSeason, dt, f -> {
																	if(f.succeeded()) {
																		patchSchoolSeasonResponse(listSchoolSeason, g -> {
																			if(g.succeeded()) {
																				eventHandler.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("patchSchoolSeason succeeded. "));
																			} else {
																				LOGGER.error(String.format("patchSchoolSeason failed. ", g.cause()));
																				errorSchoolSeason(siteRequest, eventHandler, d);
																			}
																		});
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
										} else {
											errorSchoolSeason(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolSeason(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolSeason(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolSeason(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolSeason(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPATCHSchoolSeason(ApiRequest apiRequest, SearchList<SchoolSeason> listSchoolSeason, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolSeason.getSiteRequest_();
		listSchoolSeason.getList().forEach(o -> {
			futures.add(
				patchSchoolSeasonFuture(o, a -> {
					if(a.succeeded()) {
							SchoolSeason schoolSeason = a.result();
							apiRequestSchoolSeason(schoolSeason);
					} else {
						errorSchoolSeason(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchoolSeason.size());
				if(listSchoolSeason.next(dt)) {
					siteRequest.getVertx().eventBus().publish("websocketSchoolSeason", JsonObject.mapFrom(apiRequest).toString());
					listPATCHSchoolSeason(apiRequest, listSchoolSeason, dt, eventHandler);
				} else {
					response200PATCHSchoolSeason(listSchoolSeason, eventHandler);
				}
			} else {
				errorSchoolSeason(listSchoolSeason.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolSeason> patchSchoolSeasonFuture(SchoolSeason o, Handler<AsyncResult<SchoolSeason>> eventHandler) {
		Promise<SchoolSeason> promise = Promise.promise();
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			sqlPATCHSchoolSeason(o, false, a -> {
				if(a.succeeded()) {
					SchoolSeason schoolSeason = a.result();
					defineSchoolSeason(schoolSeason, b -> {
						if(b.succeeded()) {
							attributeSchoolSeason(schoolSeason, c -> {
								if(c.succeeded()) {
									indexSchoolSeason(schoolSeason, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(schoolSeason));
											promise.complete(schoolSeason);
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
			errorSchoolSeason(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHSchoolSeason(SchoolSeason o, Boolean inheritPk, Handler<AsyncResult<SchoolSeason>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject requestJson = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = requestJson.fieldNames();
			SchoolSeason o2 = new SchoolSeason();

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.season.SchoolSeason"));
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
					case "setYearKey":
						{
							Long l = o2.getYearKey();
							SearchList<SchoolYear> searchList = new SearchList<SchoolYear>();
							searchList.setQuery("*:*");
							searchList.setStore(true);
							searchList.setC(SchoolYear.class);
							searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							searchList.initDeepSearchList(siteRequest);
							l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								o2.setYearKey(requestJson.getString(methodName));
								patchSql.append(SiteContextEnUS.SQL_setA2);
								patchSqlParams.addAll(Arrays.asList("seasonKeys", l, "yearKey", pk));
							}
						}
						break;
					case "removeYearKey":
						{
							Long l = o2.getYearKey();
							SearchList<SchoolYear> searchList = new SearchList<SchoolYear>();
							searchList.setQuery("*:*");
							searchList.setStore(true);
							searchList.setC(SchoolYear.class);
							searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							searchList.initDeepSearchList(siteRequest);
							l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								o2.setYearKey(requestJson.getString(methodName));
								patchSql.append(SiteContextEnUS.SQL_removeA);
								patchSqlParams.addAll(Arrays.asList("seasonKeys", l, "yearKey", pk));
							}
						}
						break;
					case "addSessionKeys":
						{
							Long l = Long.parseLong(requestJson.getString(methodName));
							SearchList<SchoolSession> searchList = new SearchList<SchoolSession>();
							searchList.setQuery("*:*");
							searchList.setStore(true);
							searchList.setC(SchoolSession.class);
							searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							searchList.initDeepSearchList(siteRequest);
							l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								patchSql.append(SiteContextEnUS.SQL_addA);
								patchSqlParams.addAll(Arrays.asList("seasonKey", l, "sessionKeys", pk));
							}
						}
						break;
					case "addAllSessionKeys":
						JsonArray addAllSessionKeysValues = requestJson.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllSessionKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllSessionKeysValues.getString(i));
							SearchList<SchoolSession> searchList = new SearchList<SchoolSession>();
							searchList.setQuery("*:*");
							searchList.setStore(true);
							searchList.setC(SchoolSession.class);
							searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							searchList.initDeepSearchList(siteRequest);
							l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								patchSql.append(SiteContextEnUS.SQL_setA2);
								patchSqlParams.addAll(Arrays.asList("seasonKey", l, "sessionKeys", pk));
							}
						}
						break;
					case "setSessionKeys":
						JsonArray setSessionKeysValues = requestJson.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("seasonKey", "sessionKeys", pk));
						for(Integer i = 0; i <  setSessionKeysValues.size(); i++) {
							Long l = Long.parseLong(setSessionKeysValues.getString(i));
							SearchList<SchoolSession> searchList = new SearchList<SchoolSession>();
							searchList.setQuery("*:*");
							searchList.setStore(true);
							searchList.setC(SchoolSession.class);
							searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							searchList.initDeepSearchList(siteRequest);
							l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								patchSql.append(SiteContextEnUS.SQL_setA2);
								patchSqlParams.addAll(Arrays.asList("seasonKey", l, "sessionKeys", pk));
							}
						}
						break;
					case "removeSessionKeys":
						{
							Long l = Long.parseLong(requestJson.getString(methodName));
							SearchList<SchoolSession> searchList = new SearchList<SchoolSession>();
							searchList.setQuery("*:*");
							searchList.setStore(true);
							searchList.setC(SchoolSession.class);
							searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
							searchList.initDeepSearchList(siteRequest);
							l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
							if(l != null) {
								patchSql.append(SiteContextEnUS.SQL_removeA);
								patchSqlParams.addAll(Arrays.asList("seasonKey", l, "sessionKeys", pk));
							}
						}
						break;
					case "setSeasonStartDate":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "seasonStartDate"));
						} else {
							o2.setSeasonStartDate(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("seasonStartDate", o2.jsonSeasonStartDate(), pk));
						}
						break;
					case "setSeasonSummer":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "seasonSummer"));
						} else {
							o2.setSeasonSummer(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("seasonSummer", o2.jsonSeasonSummer(), pk));
						}
						break;
					case "setSeasonWinter":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "seasonWinter"));
						} else {
							o2.setSeasonWinter(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("seasonWinter", o2.jsonSeasonWinter(), pk));
						}
						break;
					case "setSeasonFuture":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "seasonFuture"));
						} else {
							o2.setSeasonFuture(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("seasonFuture", o2.jsonSeasonFuture(), pk));
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
					SchoolSeason o3 = new SchoolSeason();
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

	public void patchSchoolSeasonResponse(SearchList<SchoolSeason> listSchoolSeason, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolSeason.getSiteRequest_();
		response200PATCHSchoolSeason(listSchoolSeason, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketSchoolSeason", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolSeason(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolSeason(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolSeason(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PATCHSchoolSeason(SearchList<SchoolSeason> listSchoolSeason, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolSeason.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			JsonObject json = JsonObject.mapFrom(apiRequest);
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSchoolSeason(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolSeason(siteContext, operationRequest);

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

			sqlSchoolSeason(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolSeason(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolSeason(siteRequest, false, true, null, c -> {
								if(c.succeeded()) {
									SearchList<SchoolSeason> listSchoolSeason = c.result();
									getSchoolSeasonResponse(listSchoolSeason, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getSchoolSeason succeeded. "));
										} else {
											LOGGER.error(String.format("getSchoolSeason failed. ", d.cause()));
											errorSchoolSeason(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolSeason(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolSeason(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolSeason(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolSeason(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void getSchoolSeasonResponse(SearchList<SchoolSeason> listSchoolSeason, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolSeason.getSiteRequest_();
		response200GETSchoolSeason(listSchoolSeason, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolSeason(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolSeason(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolSeason(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200GETSchoolSeason(SearchList<SchoolSeason> listSchoolSeason, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolSeason.getSiteRequest_();
			SolrDocumentList solrDocuments = listSchoolSeason.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listSchoolSeason.getList().stream().findFirst().orElse(null));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchSchoolSeason(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolSeason(siteContext, operationRequest);

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

			sqlSchoolSeason(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolSeason(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolSeason(siteRequest, false, true, "/api/season", c -> {
								if(c.succeeded()) {
									SearchList<SchoolSeason> listSchoolSeason = c.result();
									searchSchoolSeasonResponse(listSchoolSeason, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchSchoolSeason succeeded. "));
										} else {
											LOGGER.error(String.format("searchSchoolSeason failed. ", d.cause()));
											errorSchoolSeason(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolSeason(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolSeason(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolSeason(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolSeason(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void searchSchoolSeasonResponse(SearchList<SchoolSeason> listSchoolSeason, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolSeason.getSiteRequest_();
		response200SearchSchoolSeason(listSchoolSeason, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolSeason(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolSeason(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolSeason(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200SearchSchoolSeason(SearchList<SchoolSeason> listSchoolSeason, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolSeason.getSiteRequest_();
			QueryResponse responseSearch = listSchoolSeason.getQueryResponse();
			SolrDocumentList solrDocuments = listSchoolSeason.getSolrDocumentList();
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
			listSchoolSeason.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listSchoolSeason.getFields();
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
	public void searchpageSchoolSeasonId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageSchoolSeason(operationRequest, eventHandler);
	}

	@Override
	public void searchpageSchoolSeason(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolSeason(siteContext, operationRequest);

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

			sqlSchoolSeason(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolSeason(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolSeason(siteRequest, false, true, "/season", c -> {
								if(c.succeeded()) {
									SearchList<SchoolSeason> listSchoolSeason = c.result();
									searchpageSchoolSeasonResponse(listSchoolSeason, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchpageSchoolSeason succeeded. "));
										} else {
											LOGGER.error(String.format("searchpageSchoolSeason failed. ", d.cause()));
											errorSchoolSeason(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolSeason(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolSeason(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolSeason(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolSeason(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void searchpageSchoolSeasonResponse(SearchList<SchoolSeason> listSchoolSeason, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolSeason.getSiteRequest_();
		response200SearchPageSchoolSeason(listSchoolSeason, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolSeason(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolSeason(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolSeason(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200SearchPageSchoolSeason(SearchList<SchoolSeason> listSchoolSeason, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolSeason.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listSchoolSeason.getSiteRequest_(), buffer);
			SeasonPage page = new SeasonPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/season");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listSchoolSeason.size() == 1)
				siteRequest.setRequestPk(listSchoolSeason.get(0).getPk());
			siteRequest.setW(w);
			page.setListSchoolSeason(listSchoolSeason);
			page.setSiteRequest_(siteRequest);
			page.initDeepSeasonPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<SchoolSeason> defineIndexSchoolSeason(SchoolSeason schoolSeason, Handler<AsyncResult<SchoolSeason>> eventHandler) {
		Promise<SchoolSeason> promise = Promise.promise();
		SiteRequestEnUS siteRequest = schoolSeason.getSiteRequest_();
		defineSchoolSeason(schoolSeason, c -> {
			if(c.succeeded()) {
				attributeSchoolSeason(schoolSeason, d -> {
					if(d.succeeded()) {
						indexSchoolSeason(schoolSeason, e -> {
							if(e.succeeded()) {
								eventHandler.handle(Future.succeededFuture(schoolSeason));
								promise.complete(schoolSeason);
							} else {
								errorSchoolSeason(siteRequest, null, e);
							}
						});
					} else {
						errorSchoolSeason(siteRequest, null, d);
					}
				});
			} else {
				errorSchoolSeason(siteRequest, null, c);
			}
		});
		return promise.future();
	}

	public void createSchoolSeason(SiteRequestEnUS siteRequest, Handler<AsyncResult<SchoolSeason>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(SchoolSeason.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				SchoolSeason o = new SchoolSeason();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public ApiRequest apiRequestSchoolSeason(SchoolSeason o) {
		ApiRequest apiRequest = o.getSiteRequest_().getApiRequest_();
		if(apiRequest != null) {
			List<Long> pks = apiRequest.getPks();
			List<String> classes = apiRequest.getClasses();
			if(o.getYearKey() != null) {
				if(!pks.contains(o.getYearKey())) {
					pks.add(o.getYearKey());
					classes.add("SchoolYear");
				}
			}
			for(Long pk : o.getSessionKeys()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("SchoolSession");
				}
			}
		}
		return apiRequest;
	}

	public void errorSchoolSeason(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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

	public void sqlSchoolSeason(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolSeason(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForSchoolSeason(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolSeason(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userSchoolSeason(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
							SiteUserEnUSGenApiServiceImpl userService = new SiteUserEnUSGenApiServiceImpl(siteContext);
							if(userValues == null) {
								JsonObject userVertx = siteRequest.getOperationRequest().getUser();
								JsonObject jsonPrincipal = KeycloakHelper.parseToken(userVertx.getString("access_token"));

								JsonObject jsonObject = new JsonObject();
								jsonObject.put("userName", jsonPrincipal.getString("preferred_username"));
								jsonObject.put("userFirstName", jsonPrincipal.getString("given_name"));
								jsonObject.put("userLastName", jsonPrincipal.getString("family_name"));
								jsonObject.put("userId", jsonPrincipal.getString("sub"));
								userSchoolSeasonDefine(siteRequest, jsonObject, false);

								SiteRequestEnUS siteRequest2 = new SiteRequestEnUS();
								siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
								siteRequest2.setJsonObject(jsonObject);
								siteRequest2.setVertx(siteRequest.getVertx());
								siteRequest2.setSiteContext_(siteContext);
								siteRequest2.setSiteConfig_(siteContext.getSiteConfig());
								siteRequest2.setUserId(siteRequest.getUserId());
								siteRequest2.initDeepSiteRequestEnUS(siteRequest);

								userService.createSiteUser(siteRequest2, b -> {
									if(b.succeeded()) {
										SiteUser siteUser = b.result();
										userService.sqlPOSTSiteUser(siteUser, false, c -> {
											if(c.succeeded()) {
												userService.defineSiteUser(siteUser, d -> {
													if(d.succeeded()) {
														userService.attributeSiteUser(siteUser, e -> {
															if(e.succeeded()) {
																userService.indexSiteUser(siteUser, f -> {
																	if(f.succeeded()) {
																		siteRequest.setSiteUser(siteUser);
																		siteRequest.setUserName(jsonPrincipal.getString("preferred_username"));
																		siteRequest.setUserFirstName(jsonPrincipal.getString("given_name"));
																		siteRequest.setUserLastName(jsonPrincipal.getString("family_name"));
																		siteRequest.setUserId(jsonPrincipal.getString("sub"));
																		siteRequest.setUserKey(siteUser.getPk());
																		eventHandler.handle(Future.succeededFuture());
																	} else {
																		errorSchoolSeason(siteRequest, eventHandler, f);
																	}
																});
															} else {
																errorSchoolSeason(siteRequest, eventHandler, e);
															}
														});
													} else {
														errorSchoolSeason(siteRequest, eventHandler, d);
													}
												});
											} else {
												errorSchoolSeason(siteRequest, eventHandler, c);
											}
										});
									} else {
										errorSchoolSeason(siteRequest, eventHandler, b);
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
								Boolean define = userSchoolSeasonDefine(siteRequest, jsonObject, true);
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

									userService.sqlPATCHSiteUser(siteUser, false, c -> {
										if(c.succeeded()) {
											SiteUser siteUser2 = c.result();
											userService.defineSiteUser(siteUser2, d -> {
												if(d.succeeded()) {
													userService.attributeSiteUser(siteUser2, e -> {
														if(e.succeeded()) {
															userService.indexSiteUser(siteUser2, f -> {
																if(f.succeeded()) {
																	siteRequest.setSiteUser(siteUser2);
																	siteRequest.setUserName(siteUser2.getUserName());
																	siteRequest.setUserFirstName(siteUser2.getUserFirstName());
																	siteRequest.setUserLastName(siteUser2.getUserLastName());
																	siteRequest.setUserId(siteUser2.getUserId());
																	siteRequest.setUserKey(siteUser2.getPk());
																	eventHandler.handle(Future.succeededFuture());
																} else {
																	errorSchoolSeason(siteRequest, eventHandler, f);
																}
															});
														} else {
															errorSchoolSeason(siteRequest, eventHandler, e);
														}
													});
												} else {
													errorSchoolSeason(siteRequest, eventHandler, d);
												}
											});
										} else {
											errorSchoolSeason(siteRequest, eventHandler, c);
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

	public Boolean userSchoolSeasonDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void aSearchSchoolSeasonQ(String classApiUriMethod, SearchList<SchoolSeason> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
			searchList.setHighlight(true);
			searchList.setHighlightSnippets(3);
			searchList.addHighlightField(varIndexed);
			searchList.setParam("hl.encoder", "html");
		}
	}

	public void aSearchSchoolSeasonFq(String classApiUriMethod, SearchList<SchoolSeason> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
	}

	public void aSearchSchoolSeasonSort(String classApiUriMethod, SearchList<SchoolSeason> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addSort(varIndexed, ORDER.valueOf(valueIndexed));
	}

	public void aSearchSchoolSeasonRows(String classApiUriMethod, SearchList<SchoolSeason> searchList, Integer valueRows) {
		searchList.setRows(valueRows);
	}

	public void aSearchSchoolSeasonStart(String classApiUriMethod, SearchList<SchoolSeason> searchList, Integer valueStart) {
		searchList.setStart(valueStart);
	}

	public void aSearchSchoolSeasonVar(String classApiUriMethod, SearchList<SchoolSeason> searchList, String var, String value) {
		searchList.getSiteRequest_().getRequestVars().put(var, value);
	}

	public void aSearchSchoolSeason(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String classApiUriMethod, Handler<AsyncResult<SearchList<SchoolSeason>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<SchoolSeason> searchList = new SearchList<SchoolSeason>();
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.setQuery("*:*");
			searchList.setC(SchoolSeason.class);
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
								varIndexed = "*".equals(entityVar) ? entityVar : SchoolSeason.varSearchSchoolSeason(entityVar);
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								valueIndexed = StringUtils.isEmpty(valueIndexed) ? "*" : valueIndexed;
								aSearchSchoolSeasonQ(classApiUriMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "fq":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = SchoolSeason.varIndexedSchoolSeason(entityVar);
								aSearchSchoolSeasonFq(classApiUriMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = SchoolSeason.varIndexedSchoolSeason(entityVar);
								aSearchSchoolSeasonSort(classApiUriMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "start":
								valueStart = (Integer)paramObject;
								aSearchSchoolSeasonStart(classApiUriMethod, searchList, valueStart);
								break;
							case "rows":
								valueRows = (Integer)paramObject;
								aSearchSchoolSeasonRows(classApiUriMethod, searchList, valueRows);
								break;
							case "var":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								aSearchSchoolSeasonVar(classApiUriMethod, searchList, entityVar, valueIndexed);
								break;
						}
					}
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

	public void defineSchoolSeason(SchoolSeason o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void attributeSchoolSeason(SchoolSeason o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void indexSchoolSeason(SchoolSeason o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			if(BooleanUtils.isFalse(Optional.ofNullable(siteRequest.getApiRequest_()).map(ApiRequest::getEmpty).orElse(null))) {
				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolSeason(siteContext, siteRequest.getOperationRequest(), new JsonObject());
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				SearchList<SchoolSeason> searchList = new SearchList<SchoolSeason>();
				searchList.setPopulate(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolSeason.class);
				searchList.addFilterQuery("modified_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(siteRequest.getApiRequest_().getCreated().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				searchList.add("json.facet", "{yearKey:{terms:{field:yearKey_indexed_longs, limit:1000}}}");
				searchList.add("json.facet", "{sessionKeys:{terms:{field:sessionKeys_indexed_longs, limit:1000}}}");
				searchList.setRows(1000);
				searchList.initDeepSearchList(siteRequest2);
				List<Future> futures = new ArrayList<>();

				{
					SchoolYear o2 = new SchoolYear();
					SchoolYearEnUSGenApiServiceImpl service = new SchoolYearEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
					Long pk = o.getYearKey();

					if(pk != null) {
						o2.setPk(pk);
						o2.setSiteRequest_(siteRequest2);
						futures.add(
							service.patchSchoolYearFuture(o2, a -> {
								if(a.succeeded()) {
									LOGGER.info(String.format("SchoolYear %s refreshed. ", pk));
								} else {
									LOGGER.info(String.format("SchoolYear %s failed. ", pk));
									eventHandler.handle(Future.failedFuture(a.cause()));
								}
							})
						);
					}
				}

				{
					SchoolSessionEnUSGenApiServiceImpl service = new SchoolSessionEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
					for(Long pk : o.getSessionKeys()) {
						SchoolSession o2 = new SchoolSession();

						o2.setPk(pk);
						o2.setSiteRequest_(siteRequest2);
						futures.add(
							service.patchSchoolSessionFuture(o2, a -> {
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

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						LOGGER.info("Refresh relations succeeded. ");
						SchoolSeasonEnUSGenApiServiceImpl service = new SchoolSeasonEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
						List<Future> futures2 = new ArrayList<>();
						for(SchoolSeason o2 : searchList.getList()) {
							futures2.add(
								service.patchSchoolSeasonFuture(o2, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("SchoolSeason %s refreshed. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("SchoolSeason %s failed. ", o2.getPk()));
										eventHandler.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Refresh SchoolSeason succeeded. ");
								eventHandler.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Refresh relations failed. ", b.cause());
								errorSchoolSeason(siteRequest2, eventHandler, b);
							}
						});
					} else {
						LOGGER.error("Refresh relations failed. ", a.cause());
						errorSchoolSeason(siteRequest2, eventHandler, a);
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
