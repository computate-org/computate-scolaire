package org.computate.scolaire.enUS.design;

import org.computate.scolaire.enUS.design.PageDesignEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.design.PageDesign;
import org.computate.scolaire.enUS.design.PageDesignEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.design.PageDesign;
import org.computate.scolaire.enUS.html.part.HtmlPartEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.html.part.HtmlPart;
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
 * CanonicalName.frFR: org.computate.scolaire.frFR.design.DesignPageFrFRGenApiServiceImpl
 **/
public class PageDesignEnUSGenApiServiceImpl implements PageDesignEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(PageDesignEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "PageDesignEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public PageDesignEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
	}

	// POST //

	@Override
	public void postPageDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("postPageDesign started. "));

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

			sqlPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userPageDesign(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							siteRequest.getVertx().eventBus().publish("websocketPageDesign", JsonObject.mapFrom(apiRequest).toString());
							postPageDesignFuture(siteRequest, false, c -> {
								if(c.succeeded()) {
									PageDesign pageDesign = c.result();
									apiRequest.setPk(pageDesign.getPk());
									postPageDesignResponse(pageDesign, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postPageDesign succeeded. "));
										} else {
											LOGGER.error(String.format("postPageDesign failed. ", d.cause()));
											errorPageDesign(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("postPageDesign failed. ", c.cause()));
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("postPageDesign failed. ", b.cause()));
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("postPageDesign failed. ", a.cause()));
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postPageDesign failed. ", ex));
			errorPageDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public Future<PageDesign> postPageDesignFuture(SiteRequestEnUS siteRequest, Boolean inheritPk, Handler<AsyncResult<PageDesign>> eventHandler) {
		Promise<PageDesign> promise = Promise.promise();
		try {
			createPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					PageDesign pageDesign = a.result();
					sqlPOSTPageDesign(pageDesign, inheritPk, b -> {
						if(b.succeeded()) {
							defineIndexPageDesign(pageDesign, c -> {
								if(c.succeeded()) {
									ApiRequest apiRequest = siteRequest.getApiRequest_();
									if(apiRequest != null) {
										apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
										pageDesign.apiRequestPageDesign();
										siteRequest.getVertx().eventBus().publish("websocketPageDesign", JsonObject.mapFrom(apiRequest).toString());
									}
									eventHandler.handle(Future.succeededFuture(pageDesign));
									promise.complete(pageDesign);
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
			errorPageDesign(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTPageDesign(PageDesign o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "childDesignKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								SearchList<PageDesign> searchList = new SearchList<PageDesign>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(PageDesign.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("childDesignKeys", pk, "parentDesignKeys", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PageDesign");
									}
								}
							}
						}
						break;
					case "parentDesignKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								SearchList<PageDesign> searchList = new SearchList<PageDesign>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(PageDesign.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("childDesignKeys", l, "parentDesignKeys", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PageDesign");
									}
								}
							}
						}
						break;
					case "htmlPartKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								SearchList<HtmlPart> searchList = new SearchList<HtmlPart>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(HtmlPart.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("htmlPartKeys", pk, "pageDesignKeys", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("HtmlPart");
									}
								}
							}
						}
						break;
					case "pageDesignCompleteName":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("pageDesignCompleteName", jsonObject.getString(entityVar), pk));
						break;
					case "designHidden":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("designHidden", jsonObject.getBoolean(entityVar), pk));
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

	public void postPageDesignResponse(PageDesign pageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = pageDesign.getSiteRequest_();
		try {
			response200POSTPageDesign(pageDesign, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postPageDesign failed. ", ex));
			errorPageDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200POSTPageDesign(PageDesign o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
	public void putimportPageDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putimportPageDesign started. "));

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

			sqlPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userPageDesign(siteRequest, b -> {
						if(b.succeeded()) {
							putimportPageDesignResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest, body);
											try {
												ApiRequest apiRequest = new ApiRequest();
												JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												apiRequest.setRows(jsonArray.size());
												apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketPageDesign", JsonObject.mapFrom(apiRequest).toString());
												sqlPageDesign(siteRequest2, d -> {
													if(d.succeeded()) {
														listPUTImportPageDesign(apiRequest, siteRequest2, e -> {
															if(e.succeeded()) {
																putimportPageDesignResponse(siteRequest2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putimportPageDesign succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putimportPageDesign failed. ", f.cause()));
																		errorPageDesign(siteRequest2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putimportPageDesign failed. ", e.cause()));
																errorPageDesign(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putimportPageDesign failed. ", d.cause()));
														errorPageDesign(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putimportPageDesign failed. ", ex));
												errorPageDesign(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putimportPageDesign failed. ", c.cause()));
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putimportPageDesign failed. ", b.cause()));
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putimportPageDesign failed. ", a.cause()));
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportPageDesign failed. ", ex));
			errorPageDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTImportPageDesign(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForPageDesign(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				siteRequest2.setApiRequest_(apiRequest);

				SearchList<PageDesign> searchList = new SearchList<PageDesign>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(PageDesign.class);
				searchList.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					PageDesign o = searchList.getList().stream().findFirst().orElse(null);
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
							patchPageDesignFuture(o, true, a -> {
								if(a.succeeded()) {
								} else {
									errorPageDesign(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postPageDesignFuture(siteRequest2, true, a -> {
							if(a.succeeded()) {
							} else {
								errorPageDesign(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
					response200PUTImportPageDesign(siteRequest, eventHandler);
				} else {
					errorPageDesign(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportPageDesign failed. ", ex));
			errorPageDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putimportPageDesignResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTImportPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportPageDesign failed. ", ex));
			errorPageDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTImportPageDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTMerge //

	@Override
	public void putmergePageDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putmergePageDesign started. "));

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

			sqlPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userPageDesign(siteRequest, b -> {
						if(b.succeeded()) {
							putmergePageDesignResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest, body);
											try {
												ApiRequest apiRequest = new ApiRequest();
												JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												apiRequest.setRows(jsonArray.size());
												apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketPageDesign", JsonObject.mapFrom(apiRequest).toString());
												sqlPageDesign(siteRequest2, d -> {
													if(d.succeeded()) {
														listPUTMergePageDesign(apiRequest, siteRequest2, e -> {
															if(e.succeeded()) {
																putmergePageDesignResponse(siteRequest2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putmergePageDesign succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putmergePageDesign failed. ", f.cause()));
																		errorPageDesign(siteRequest2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putmergePageDesign failed. ", e.cause()));
																errorPageDesign(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putmergePageDesign failed. ", d.cause()));
														errorPageDesign(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putmergePageDesign failed. ", ex));
												errorPageDesign(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putmergePageDesign failed. ", c.cause()));
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putmergePageDesign failed. ", b.cause()));
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putmergePageDesign failed. ", a.cause()));
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergePageDesign failed. ", ex));
			errorPageDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTMergePageDesign(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForPageDesign(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				siteRequest2.setApiRequest_(apiRequest);

				SearchList<PageDesign> searchList = new SearchList<PageDesign>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(PageDesign.class);
				searchList.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					PageDesign o = searchList.getList().stream().findFirst().orElse(null);
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
							patchPageDesignFuture(o, false, a -> {
								if(a.succeeded()) {
								} else {
									errorPageDesign(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postPageDesignFuture(siteRequest2, false, a -> {
							if(a.succeeded()) {
							} else {
								errorPageDesign(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
					response200PUTMergePageDesign(siteRequest, eventHandler);
				} else {
					errorPageDesign(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergePageDesign failed. ", ex));
			errorPageDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putmergePageDesignResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTMergePageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergePageDesign failed. ", ex));
			errorPageDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTMergePageDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTCopy //

	@Override
	public void putcopyPageDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putcopyPageDesign started. "));

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

			sqlPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userPageDesign(siteRequest, b -> {
						if(b.succeeded()) {
							putcopyPageDesignResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest, body);
											try {
												aSearchPageDesign(siteRequest2, false, true, "/api/page-design/copy", "PUTCopy", d -> {
													if(d.succeeded()) {
														SearchList<PageDesign> listPageDesign = d.result();
														ApiRequest apiRequest = new ApiRequest();
														apiRequest.setRows(listPageDesign.getRows());
														apiRequest.setNumFound(listPageDesign.getQueryResponse().getResults().getNumFound());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest2);
														siteRequest2.setApiRequest_(apiRequest);
														siteRequest2.getVertx().eventBus().publish("websocketPageDesign", JsonObject.mapFrom(apiRequest).toString());
														sqlPageDesign(siteRequest2, e -> {
															if(e.succeeded()) {
																try {
																	listPUTCopyPageDesign(apiRequest, listPageDesign, f -> {
																		if(f.succeeded()) {
																			putcopyPageDesignResponse(siteRequest2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("putcopyPageDesign succeeded. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("putcopyPageDesign failed. ", g.cause()));
																					errorPageDesign(siteRequest2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("putcopyPageDesign failed. ", f.cause()));
																			errorPageDesign(siteRequest2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("putcopyPageDesign failed. ", ex));
																	errorPageDesign(siteRequest2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("putcopyPageDesign failed. ", e.cause()));
																errorPageDesign(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putcopyPageDesign failed. ", d.cause()));
														errorPageDesign(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putcopyPageDesign failed. ", ex));
												errorPageDesign(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("putcopyPageDesign failed. ", c.cause()));
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("putcopyPageDesign failed. ", b.cause()));
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("putcopyPageDesign failed. ", a.cause()));
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopyPageDesign failed. ", ex));
			errorPageDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTCopyPageDesign(ApiRequest apiRequest, SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
		listPageDesign.getList().forEach(o -> {
			futures.add(
				putcopyPageDesignFuture(siteRequest, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						PageDesign pageDesign = a.result();
					} else {
						errorPageDesign(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listPageDesign.size());
				if(listPageDesign.next()) {
					listPUTCopyPageDesign(apiRequest, listPageDesign, eventHandler);
				} else {
					response200PUTCopyPageDesign(siteRequest, eventHandler);
				}
			} else {
				errorPageDesign(listPageDesign.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<PageDesign> putcopyPageDesignFuture(SiteRequestEnUS siteRequest, JsonObject jsonObject, Handler<AsyncResult<PageDesign>> eventHandler) {
		Promise<PageDesign> promise = Promise.promise();
		try {

			jsonObject.put("saves", Optional.ofNullable(jsonObject.getJsonArray("saves")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("saves").add(o.getKey());
			});

			createPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					PageDesign pageDesign = a.result();
					sqlPUTCopyPageDesign(pageDesign, jsonObject, b -> {
						if(b.succeeded()) {
							definePageDesign(pageDesign, c -> {
								if(c.succeeded()) {
									attributePageDesign(pageDesign, d -> {
										if(d.succeeded()) {
											indexPageDesign(pageDesign, e -> {
												if(e.succeeded()) {
													ApiRequest apiRequest = siteRequest.getApiRequest_();
													if(apiRequest != null) {
														apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
														if(apiRequest.getNumFound() == 1L) {
															pageDesign.apiRequestPageDesign();
														}
														siteRequest.getVertx().eventBus().publish("websocketPageDesign", JsonObject.mapFrom(apiRequest).toString());
													}
													eventHandler.handle(Future.succeededFuture(pageDesign));
													promise.complete(pageDesign);
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
			errorPageDesign(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopyPageDesign(PageDesign o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "childDesignKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("childDesignKeys", pk, "parentDesignKeys", l));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("PageDesign");
							}
						}
						break;
					case "parentDesignKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("childDesignKeys", l, "parentDesignKeys", pk));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("PageDesign");
							}
						}
						break;
					case "htmlPartKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("htmlPartKeys", pk, "pageDesignKeys", l));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("HtmlPart");
							}
						}
						break;
					case "pageDesignCompleteName":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("pageDesignCompleteName", jsonObject.getString(entityVar), pk));
						break;
					case "designHidden":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("designHidden", jsonObject.getBoolean(entityVar), pk));
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

	public void putcopyPageDesignResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTCopyPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopyPageDesign failed. ", ex));
			errorPageDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTCopyPageDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchPageDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("patchPageDesign started. "));

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

			sqlPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userPageDesign(siteRequest, b -> {
						if(b.succeeded()) {
							patchPageDesignResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest, body);
											try {
												aSearchPageDesign(siteRequest2, false, true, "/api/page-design", "PATCH", d -> {
													if(d.succeeded()) {
														SearchList<PageDesign> listPageDesign = d.result();
														ApiRequest apiRequest = new ApiRequest();
														apiRequest.setRows(listPageDesign.getRows());
														apiRequest.setNumFound(listPageDesign.getQueryResponse().getResults().getNumFound());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest2);
														siteRequest2.setApiRequest_(apiRequest);
														siteRequest2.getVertx().eventBus().publish("websocketPageDesign", JsonObject.mapFrom(apiRequest).toString());
														SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listPageDesign.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
														Date date = null;
														if(facets != null)
															date = (Date)facets.get("max_modified");
														String dt;
														if(date == null)
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
														else
															dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
														listPageDesign.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));

														PageDesign o = listPageDesign.getList().stream().findFirst().orElse(null);
														sqlPageDesign(siteRequest2, e -> {
															if(e.succeeded()) {
																try {
																	listPATCHPageDesign(apiRequest, listPageDesign, dt, f -> {
																		if(f.succeeded()) {
																			patchPageDesignResponse(siteRequest2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("patchPageDesign succeeded. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("patchPageDesign failed. ", g.cause()));
																					errorPageDesign(siteRequest2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("patchPageDesign failed. ", f.cause()));
																			errorPageDesign(siteRequest2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("patchPageDesign failed. ", ex));
																	errorPageDesign(siteRequest2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("patchPageDesign failed. ", e.cause()));
																errorPageDesign(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("patchPageDesign failed. ", d.cause()));
														errorPageDesign(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("patchPageDesign failed. ", ex));
												errorPageDesign(siteRequest2, null, Future.failedFuture(ex));
											}
										}, resultHandler -> {
										}
									);
								} else {
									LOGGER.error(String.format("patchPageDesign failed. ", c.cause()));
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("patchPageDesign failed. ", b.cause()));
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("patchPageDesign failed. ", a.cause()));
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchPageDesign failed. ", ex));
			errorPageDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPATCHPageDesign(ApiRequest apiRequest, SearchList<PageDesign> listPageDesign, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
		listPageDesign.getList().forEach(o -> {
			futures.add(
				patchPageDesignFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						errorPageDesign(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listPageDesign.next(dt)) {
					listPATCHPageDesign(apiRequest, listPageDesign, dt, eventHandler);
				} else {
					response200PATCHPageDesign(siteRequest, eventHandler);
				}
			} else {
				errorPageDesign(listPageDesign.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<PageDesign> patchPageDesignFuture(PageDesign o, Boolean inheritPk, Handler<AsyncResult<PageDesign>> eventHandler) {
		Promise<PageDesign> promise = Promise.promise();
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			if(apiRequest != null && apiRequest.getNumFound() == 1L) {
				apiRequest.setOriginal(o);
				apiRequest.setPk(o.getPk());
			}
			sqlPATCHPageDesign(o, inheritPk, a -> {
				if(a.succeeded()) {
					PageDesign pageDesign = a.result();
					definePageDesign(pageDesign, b -> {
						if(b.succeeded()) {
							attributePageDesign(pageDesign, c -> {
								if(c.succeeded()) {
									indexPageDesign(pageDesign, d -> {
										if(d.succeeded()) {
											if(apiRequest != null) {
												apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
												if(apiRequest.getNumFound() == 1L) {
													pageDesign.apiRequestPageDesign();
												}
												siteRequest.getVertx().eventBus().publish("websocketPageDesign", JsonObject.mapFrom(apiRequest).toString());
											}
											eventHandler.handle(Future.succeededFuture(pageDesign));
											promise.complete(pageDesign);
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
			errorPageDesign(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHPageDesign(PageDesign o, Boolean inheritPk, Handler<AsyncResult<PageDesign>> eventHandler) {
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
			PageDesign o2 = new PageDesign();

			if(o.getUserId() == null && siteRequest.getUserId() != null) {
				patchSql.append(SiteContextEnUS.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("userId", siteRequest.getUserId(), pk));
			}
			if(o.getUserKey() == null && siteRequest.getUserKey() != null) {
				patchSql.append(SiteContextEnUS.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("userKey", siteRequest.getUserKey(), pk));
			}

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.design.PageDesign"));
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
					case "addChildDesignKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<PageDesign> searchList = new SearchList<PageDesign>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(PageDesign.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("childDesignKeys", pk, "parentDesignKeys", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PageDesign");
									}
								}
							}
						}
						break;
					case "addAllChildDesignKeys":
						JsonArray addAllChildDesignKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllChildDesignKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllChildDesignKeysValues.getString(i));
							if(l != null) {
								SearchList<PageDesign> searchList = new SearchList<PageDesign>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(PageDesign.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("childDesignKeys", pk, "parentDesignKeys", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PageDesign");
									}
								}
							}
						}
						break;
					case "setChildDesignKeys":
						JsonArray setChildDesignKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("childDesignKeys", pk, "parentDesignKeys"));
						for(Integer i = 0; i <  setChildDesignKeysValues.size(); i++) {
							Long l = Long.parseLong(setChildDesignKeysValues.getString(i));
							if(l != null) {
								SearchList<PageDesign> searchList = new SearchList<PageDesign>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(PageDesign.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("childDesignKeys", pk, "parentDesignKeys", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PageDesign");
									}
								}
							}
						}
						break;
					case "removeChildDesignKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<PageDesign> searchList = new SearchList<PageDesign>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(PageDesign.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("childDesignKeys", pk, "parentDesignKeys", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PageDesign");
									}
								}
							}
						}
						break;
					case "addParentDesignKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<PageDesign> searchList = new SearchList<PageDesign>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(PageDesign.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("childDesignKeys", l, "parentDesignKeys", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PageDesign");
									}
								}
							}
						}
						break;
					case "addAllParentDesignKeys":
						JsonArray addAllParentDesignKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllParentDesignKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllParentDesignKeysValues.getString(i));
							if(l != null) {
								SearchList<PageDesign> searchList = new SearchList<PageDesign>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(PageDesign.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("childDesignKeys", l, "parentDesignKeys", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PageDesign");
									}
								}
							}
						}
						break;
					case "setParentDesignKeys":
						JsonArray setParentDesignKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("childDesignKeys", "parentDesignKeys", pk));
						for(Integer i = 0; i <  setParentDesignKeysValues.size(); i++) {
							Long l = Long.parseLong(setParentDesignKeysValues.getString(i));
							if(l != null) {
								SearchList<PageDesign> searchList = new SearchList<PageDesign>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(PageDesign.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("childDesignKeys", l, "parentDesignKeys", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PageDesign");
									}
								}
							}
						}
						break;
					case "removeParentDesignKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<PageDesign> searchList = new SearchList<PageDesign>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(PageDesign.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("childDesignKeys", l, "parentDesignKeys", pk));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("PageDesign");
									}
								}
							}
						}
						break;
					case "addHtmlPartKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<HtmlPart> searchList = new SearchList<HtmlPart>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(HtmlPart.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("htmlPartKeys", pk, "pageDesignKeys", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("HtmlPart");
									}
								}
							}
						}
						break;
					case "addAllHtmlPartKeys":
						JsonArray addAllHtmlPartKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllHtmlPartKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllHtmlPartKeysValues.getString(i));
							if(l != null) {
								SearchList<HtmlPart> searchList = new SearchList<HtmlPart>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(HtmlPart.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("htmlPartKeys", pk, "pageDesignKeys", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("HtmlPart");
									}
								}
							}
						}
						break;
					case "setHtmlPartKeys":
						JsonArray setHtmlPartKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("htmlPartKeys", pk, "pageDesignKeys"));
						for(Integer i = 0; i <  setHtmlPartKeysValues.size(); i++) {
							Long l = Long.parseLong(setHtmlPartKeysValues.getString(i));
							if(l != null) {
								SearchList<HtmlPart> searchList = new SearchList<HtmlPart>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(HtmlPart.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("htmlPartKeys", pk, "pageDesignKeys", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("HtmlPart");
									}
								}
							}
						}
						break;
					case "removeHtmlPartKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<HtmlPart> searchList = new SearchList<HtmlPart>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(HtmlPart.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("htmlPartKeys", pk, "pageDesignKeys", l));
									if(!pks.contains(l)) {
										pks.add(l);
										classes.add("HtmlPart");
									}
								}
							}
						}
						break;
					case "setPageDesignCompleteName":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "pageDesignCompleteName"));
						} else {
							o2.setPageDesignCompleteName(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("pageDesignCompleteName", o2.jsonPageDesignCompleteName(), pk));
						}
						break;
					case "setDesignHidden":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "designHidden"));
						} else {
							o2.setDesignHidden(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("designHidden", o2.jsonDesignHidden(), pk));
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
					PageDesign o3 = new PageDesign();
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

	public void patchPageDesignResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PATCHPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchPageDesign failed. ", ex));
			errorPageDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PATCHPageDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getPageDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest);
		try {
			sqlPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userPageDesign(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchPageDesign(siteRequest, false, true, "/api/page-design/{id}", "GET", c -> {
								if(c.succeeded()) {
									SearchList<PageDesign> listPageDesign = c.result();
									getPageDesignResponse(listPageDesign, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getPageDesign succeeded. "));
										} else {
											LOGGER.error(String.format("getPageDesign failed. ", d.cause()));
											errorPageDesign(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("getPageDesign failed. ", c.cause()));
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("getPageDesign failed. ", b.cause()));
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("getPageDesign failed. ", a.cause()));
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getPageDesign failed. ", ex));
			errorPageDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void getPageDesignResponse(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
		try {
			response200GETPageDesign(listPageDesign, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getPageDesign failed. ", ex));
			errorPageDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200GETPageDesign(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
			SolrDocumentList solrDocuments = listPageDesign.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listPageDesign.getList().stream().findFirst().orElse(null));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchPageDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest);
		try {
			sqlPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userPageDesign(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchPageDesign(siteRequest, false, true, "/api/page-design", "Search", c -> {
								if(c.succeeded()) {
									SearchList<PageDesign> listPageDesign = c.result();
									searchPageDesignResponse(listPageDesign, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchPageDesign succeeded. "));
										} else {
											LOGGER.error(String.format("searchPageDesign failed. ", d.cause()));
											errorPageDesign(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("searchPageDesign failed. ", c.cause()));
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("searchPageDesign failed. ", b.cause()));
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("searchPageDesign failed. ", a.cause()));
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchPageDesign failed. ", ex));
			errorPageDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchPageDesignResponse(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
		try {
			response200SearchPageDesign(listPageDesign, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchPageDesign failed. ", ex));
			errorPageDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchPageDesign(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
			QueryResponse responseSearch = listPageDesign.getQueryResponse();
			SolrDocumentList solrDocuments = listPageDesign.getSolrDocumentList();
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
			listPageDesign.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listPageDesign.getFields();
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

	// AdminSearch //

	@Override
	public void adminsearchPageDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest);
		try {
			sqlPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userPageDesign(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchPageDesign(siteRequest, false, true, "/api/admin/page-design", "AdminSearch", c -> {
								if(c.succeeded()) {
									SearchList<PageDesign> listPageDesign = c.result();
									adminsearchPageDesignResponse(listPageDesign, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("adminsearchPageDesign succeeded. "));
										} else {
											LOGGER.error(String.format("adminsearchPageDesign failed. ", d.cause()));
											errorPageDesign(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("adminsearchPageDesign failed. ", c.cause()));
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("adminsearchPageDesign failed. ", b.cause()));
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("adminsearchPageDesign failed. ", a.cause()));
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("adminsearchPageDesign failed. ", ex));
			errorPageDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void adminsearchPageDesignResponse(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
		try {
			response200AdminSearchPageDesign(listPageDesign, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("adminsearchPageDesign failed. ", ex));
			errorPageDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200AdminSearchPageDesign(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
			QueryResponse responseSearch = listPageDesign.getQueryResponse();
			SolrDocumentList solrDocuments = listPageDesign.getSolrDocumentList();
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
			listPageDesign.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listPageDesign.getFields();
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
	public void searchpagePageDesignId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpagePageDesign(operationRequest, eventHandler);
	}

	@Override
	public void searchpagePageDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest);
		try {
			sqlPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userPageDesign(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchPageDesign(siteRequest, false, true, "/page-design", "SearchPage", c -> {
								if(c.succeeded()) {
									SearchList<PageDesign> listPageDesign = c.result();
									searchpagePageDesignResponse(listPageDesign, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchpagePageDesign succeeded. "));
										} else {
											LOGGER.error(String.format("searchpagePageDesign failed. ", d.cause()));
											errorPageDesign(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("searchpagePageDesign failed. ", c.cause()));
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("searchpagePageDesign failed. ", b.cause()));
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("searchpagePageDesign failed. ", a.cause()));
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpagePageDesign failed. ", ex));
			errorPageDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchpagePageDesignResponse(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
		try {
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(siteRequest, buffer);
			siteRequest.setW(w);
			response200SearchPagePageDesign(listPageDesign, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpagePageDesign failed. ", ex));
			errorPageDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchPagePageDesign(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listPageDesign.getSiteRequest_(), buffer);
			PageDesignPage page = new PageDesignPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/page-design");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listPageDesign.size() == 1)
				siteRequest.setRequestPk(listPageDesign.get(0).getPk());
			siteRequest.setW(w);
			page.setListPageDesign(listPageDesign);
			page.setSiteRequest_(siteRequest);
			page.initDeepPageDesignPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// DesignDisplaySearchPage //

	@Override
	public void designdisplaysearchpagePageDesignId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		designdisplaysearchpagePageDesign(operationRequest, eventHandler);
	}

	@Override
	public void designdisplaysearchpagePageDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest);
		try {
			sqlPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userPageDesign(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchPageDesign(siteRequest, false, true, "/page", "DesignDisplaySearchPage", c -> {
								if(c.succeeded()) {
									SearchList<PageDesign> listPageDesign = c.result();
									designdisplaysearchpagePageDesignResponse(listPageDesign, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("designdisplaysearchpagePageDesign succeeded. "));
										} else {
											LOGGER.error(String.format("designdisplaysearchpagePageDesign failed. ", d.cause()));
											errorPageDesign(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("designdisplaysearchpagePageDesign failed. ", c.cause()));
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("designdisplaysearchpagePageDesign failed. ", b.cause()));
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("designdisplaysearchpagePageDesign failed. ", a.cause()));
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("designdisplaysearchpagePageDesign failed. ", ex));
			errorPageDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void designdisplaysearchpagePageDesignResponse(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
		try {
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(siteRequest, buffer);
			siteRequest.setW(w);
			response200DesignDisplaySearchPagePageDesign(listPageDesign, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("designdisplaysearchpagePageDesign failed. ", ex));
			errorPageDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200DesignDisplaySearchPagePageDesign(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listPageDesign.getSiteRequest_(), buffer);
			DesignDisplayPage page = new DesignDisplayPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/page");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listPageDesign.size() == 1)
				siteRequest.setRequestPk(listPageDesign.get(0).getPk());
			siteRequest.setW(w);
			page.setListPageDesign(listPageDesign);
			page.setSiteRequest_(siteRequest);
			page.initDeepDesignDisplayPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// DesignPdfSearchPage //

	@Override
	public void designpdfsearchpagePageDesignId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		designpdfsearchpagePageDesign(operationRequest, eventHandler);
	}

	@Override
	public void designpdfsearchpagePageDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest);
		try {
			sqlPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userPageDesign(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchPageDesign(siteRequest, false, true, "/pdf", "DesignPdfSearchPage", c -> {
								if(c.succeeded()) {
									SearchList<PageDesign> listPageDesign = c.result();
									designpdfsearchpagePageDesignResponse(listPageDesign, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("designpdfsearchpagePageDesign succeeded. "));
										} else {
											LOGGER.error(String.format("designpdfsearchpagePageDesign failed. ", d.cause()));
											errorPageDesign(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("designpdfsearchpagePageDesign failed. ", c.cause()));
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("designpdfsearchpagePageDesign failed. ", b.cause()));
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("designpdfsearchpagePageDesign failed. ", a.cause()));
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("designpdfsearchpagePageDesign failed. ", ex));
			errorPageDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void designpdfsearchpagePageDesignResponse(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
		try {
			response200DesignPdfSearchPagePageDesign(listPageDesign, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("designpdfsearchpagePageDesign failed. ", ex));
			errorPageDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200DesignPdfSearchPagePageDesign(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listPageDesign.getSiteRequest_(), buffer);
			DesignPdfPage page = new DesignPdfPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/pdf");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listPageDesign.size() == 1)
				siteRequest.setRequestPk(listPageDesign.get(0).getPk());
			siteRequest.setW(w);
			page.setListPageDesign(listPageDesign);
			page.setSiteRequest_(siteRequest);
			page.initDeepDesignPdfPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// DesignEmailSearchPage //

	@Override
	public void designemailsearchpagePageDesignId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		designemailsearchpagePageDesign(operationRequest, eventHandler);
	}

	@Override
	public void designemailsearchpagePageDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest);
		try {
			sqlPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userPageDesign(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchPageDesign(siteRequest, false, true, "/email", "DesignEmailSearchPage", c -> {
								if(c.succeeded()) {
									SearchList<PageDesign> listPageDesign = c.result();
									designemailsearchpagePageDesignResponse(listPageDesign, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("designemailsearchpagePageDesign succeeded. "));
										} else {
											LOGGER.error(String.format("designemailsearchpagePageDesign failed. ", d.cause()));
											errorPageDesign(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("designemailsearchpagePageDesign failed. ", c.cause()));
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("designemailsearchpagePageDesign failed. ", b.cause()));
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("designemailsearchpagePageDesign failed. ", a.cause()));
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("designemailsearchpagePageDesign failed. ", ex));
			errorPageDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void designemailsearchpagePageDesignResponse(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
		try {
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(siteRequest, buffer);
			siteRequest.setW(w);
			response200DesignEmailSearchPagePageDesign(listPageDesign, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("designemailsearchpagePageDesign failed. ", ex));
			errorPageDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200DesignEmailSearchPagePageDesign(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listPageDesign.getSiteRequest_(), buffer);
			DesignEmailPage page = new DesignEmailPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/email");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listPageDesign.size() == 1)
				siteRequest.setRequestPk(listPageDesign.get(0).getPk());
			siteRequest.setW(w);
			page.setListPageDesign(listPageDesign);
			page.setSiteRequest_(siteRequest);
			page.initDeepDesignEmailPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// HomePageSearchPage //

	@Override
	public void homepagesearchpagePageDesignId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		homepagesearchpagePageDesign(operationRequest, eventHandler);
	}

	@Override
	public void homepagesearchpagePageDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest);
		try {
			sqlPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userPageDesign(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchPageDesign(siteRequest, false, true, "/", "HomePageSearchPage", c -> {
								if(c.succeeded()) {
									SearchList<PageDesign> listPageDesign = c.result();
									homepagesearchpagePageDesignResponse(listPageDesign, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("homepagesearchpagePageDesign succeeded. "));
										} else {
											LOGGER.error(String.format("homepagesearchpagePageDesign failed. ", d.cause()));
											errorPageDesign(siteRequest, eventHandler, d);
										}
									});
								} else {
									LOGGER.error(String.format("homepagesearchpagePageDesign failed. ", c.cause()));
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("homepagesearchpagePageDesign failed. ", b.cause()));
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("homepagesearchpagePageDesign failed. ", a.cause()));
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("homepagesearchpagePageDesign failed. ", ex));
			errorPageDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void homepagesearchpagePageDesignResponse(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
		try {
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(siteRequest, buffer);
			siteRequest.setW(w);
			response200HomePageSearchPagePageDesign(listPageDesign, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("homepagesearchpagePageDesign failed. ", ex));
			errorPageDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200HomePageSearchPagePageDesign(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listPageDesign.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listPageDesign.getSiteRequest_(), buffer);
			DesignDisplayPage page = new DesignDisplayPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listPageDesign.size() == 1)
				siteRequest.setRequestPk(listPageDesign.get(0).getPk());
			siteRequest.setW(w);
			page.setListPageDesign(listPageDesign);
			page.setSiteRequest_(siteRequest);
			page.initDeepDesignDisplayPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<PageDesign> defineIndexPageDesign(PageDesign pageDesign, Handler<AsyncResult<PageDesign>> eventHandler) {
		Promise<PageDesign> promise = Promise.promise();
		SiteRequestEnUS siteRequest = pageDesign.getSiteRequest_();
		definePageDesign(pageDesign, c -> {
			if(c.succeeded()) {
				attributePageDesign(pageDesign, d -> {
					if(d.succeeded()) {
						indexPageDesign(pageDesign, e -> {
							if(e.succeeded()) {
								eventHandler.handle(Future.succeededFuture(pageDesign));
								promise.complete(pageDesign);
							} else {
								errorPageDesign(siteRequest, null, e);
							}
						});
					} else {
						errorPageDesign(siteRequest, null, d);
					}
				});
			} else {
				errorPageDesign(siteRequest, null, c);
			}
		});
		return promise.future();
	}

	public void createPageDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<PageDesign>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(PageDesign.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				PageDesign o = new PageDesign();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void errorPageDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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

	public void sqlPageDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public SiteRequestEnUS generateSiteRequestEnUSForPageDesign(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForPageDesign(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForPageDesign(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userPageDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
								userPageDesignDefine(siteRequest, jsonObject, false);

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
														errorPageDesign(siteRequest, eventHandler, d);
													}
												});
											} else {
												errorPageDesign(siteRequest, eventHandler, c);
											}
										});
									} else {
										errorPageDesign(siteRequest, eventHandler, b);
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
								Boolean define = userPageDesignDefine(siteRequest, jsonObject, true);
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
													errorPageDesign(siteRequest, eventHandler, d);
												}
											});
										} else {
											errorPageDesign(siteRequest, eventHandler, c);
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

	public Boolean userPageDesignDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void aSearchPageDesignQ(String uri, String apiMethod, SearchList<PageDesign> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
			searchList.setHighlight(true);
			searchList.setHighlightSnippets(3);
			searchList.addHighlightField(varIndexed);
			searchList.setParam("hl.encoder", "html");
		}
	}

	public void aSearchPageDesignFq(String uri, String apiMethod, SearchList<PageDesign> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
	}

	public void aSearchPageDesignSort(String uri, String apiMethod, SearchList<PageDesign> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addSort(varIndexed, ORDER.valueOf(valueIndexed));
	}

	public void aSearchPageDesignRows(String uri, String apiMethod, SearchList<PageDesign> searchList, Integer valueRows) {
			searchList.setRows(apiMethod != null && apiMethod.contains("Search") ? valueRows : 10);
	}

	public void aSearchPageDesignStart(String uri, String apiMethod, SearchList<PageDesign> searchList, Integer valueStart) {
		searchList.setStart(valueStart);
	}

	public void aSearchPageDesignVar(String uri, String apiMethod, SearchList<PageDesign> searchList, String var, String value) {
		searchList.getSiteRequest_().getRequestVars().put(var, value);
	}

	public void aSearchPageDesignUri(String uri, String apiMethod, SearchList<PageDesign> searchList) {
	}

	public void aSearchPageDesign(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String uri, String apiMethod, Handler<AsyncResult<SearchList<PageDesign>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<PageDesign> searchList = new SearchList<PageDesign>();
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.setQuery("*:*");
			searchList.setC(PageDesign.class);
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
								varIndexed = "*".equals(entityVar) ? entityVar : PageDesign.varSearchPageDesign(entityVar);
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								valueIndexed = StringUtils.isEmpty(valueIndexed) ? "*" : valueIndexed;
								aSearchPageDesignQ(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "fq":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = PageDesign.varIndexedPageDesign(entityVar);
								aSearchPageDesignFq(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = PageDesign.varIndexedPageDesign(entityVar);
								aSearchPageDesignSort(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "start":
								valueStart = (Integer)paramObject;
								aSearchPageDesignStart(uri, apiMethod, searchList, valueStart);
								break;
							case "rows":
								valueRows = (Integer)paramObject;
								aSearchPageDesignRows(uri, apiMethod, searchList, valueRows);
								break;
							case "var":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								aSearchPageDesignVar(uri, apiMethod, searchList, entityVar, valueIndexed);
								break;
						}
					}
					aSearchPageDesignUri(uri, apiMethod, searchList);
				} catch(Exception e) {
					eventHandler.handle(Future.failedFuture(e));
				}
			});
			if(searchList.getSorts().size() == 0) {
				searchList.addSort("pageDesignCompleteName_indexed_string", ORDER.asc);
			}
			searchList.initDeepForClass(siteRequest);
			eventHandler.handle(Future.succeededFuture(searchList));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void definePageDesign(PageDesign o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void attributePageDesign(PageDesign o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void indexPageDesign(PageDesign o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			if(BooleanUtils.isFalse(Optional.ofNullable(siteRequest.getApiRequest_()).map(ApiRequest::getEmpty).orElse(true))) {
				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForPageDesign(siteContext, siteRequest.getOperationRequest(), new JsonObject());
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				SearchList<PageDesign> searchList = new SearchList<PageDesign>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(PageDesign.class);
				searchList.addFilterQuery("modified_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(siteRequest.getApiRequest_().getCreated().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				searchList.add("json.facet", "{childDesignKeys:{terms:{field:childDesignKeys_indexed_longs, limit:1000}}}");
				searchList.add("json.facet", "{htmlPartKeys:{terms:{field:htmlPartKeys_indexed_longs, limit:1000}}}");
				searchList.setRows(1000);
				searchList.initDeepSearchList(siteRequest2);
				List<Future> futures = new ArrayList<>();

				for(int i=0; i < pks.size(); i++) {
					Long pk2 = pks.get(i);
					String classSimpleName2 = classes.get(i);

					if("PageDesign".equals(classSimpleName2) && pk2 != null) {
						PageDesignEnUSGenApiServiceImpl service = new PageDesignEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
						SearchList<PageDesign> searchList2 = new SearchList<PageDesign>();
						if(pk2 != null) {
							searchList2.setStore(true);
							searchList2.setQuery("*:*");
							searchList2.setC(PageDesign.class);
							searchList2.addFilterQuery("pk_indexed_long:" + pk2);
							searchList2.setRows(1);
							searchList2.initDeepSearchList(siteRequest2);
							PageDesign o2 = searchList2.getList().stream().findFirst().orElse(null);

							if(o2 != null) {
								ApiRequest apiRequest2 = new ApiRequest();
								apiRequest2.setRows(1);
								apiRequest2.setNumFound(1l);
								apiRequest2.setNumPATCH(0L);
								apiRequest2.initDeepApiRequest(siteRequest2);
								siteRequest2.setApiRequest_(apiRequest2);
								siteRequest2.getVertx().eventBus().publish("websocketPageDesign", JsonObject.mapFrom(apiRequest2).toString());

								o2.setPk(pk2);
								o2.setSiteRequest_(siteRequest2);
								futures.add(
									service.patchPageDesignFuture(o2, false, a -> {
										if(a.succeeded()) {
											LOGGER.info(String.format("PageDesign %s refreshed. ", pk2));
										} else {
											LOGGER.info(String.format("PageDesign %s failed. ", pk2));
											eventHandler.handle(Future.failedFuture(a.cause()));
										}
									})
								);
							}
						}
					}

					if("HtmlPart".equals(classSimpleName2) && pk2 != null) {
						HtmlPartEnUSGenApiServiceImpl service = new HtmlPartEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
						SearchList<HtmlPart> searchList2 = new SearchList<HtmlPart>();
						if(pk2 != null) {
							searchList2.setStore(true);
							searchList2.setQuery("*:*");
							searchList2.setC(HtmlPart.class);
							searchList2.addFilterQuery("pk_indexed_long:" + pk2);
							searchList2.setRows(1);
							searchList2.initDeepSearchList(siteRequest2);
							HtmlPart o2 = searchList2.getList().stream().findFirst().orElse(null);

							if(o2 != null) {
								ApiRequest apiRequest2 = new ApiRequest();
								apiRequest2.setRows(1);
								apiRequest2.setNumFound(1l);
								apiRequest2.setNumPATCH(0L);
								apiRequest2.initDeepApiRequest(siteRequest2);
								siteRequest2.setApiRequest_(apiRequest2);
								siteRequest2.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest2).toString());

								o2.setPk(pk2);
								o2.setSiteRequest_(siteRequest2);
								futures.add(
									service.patchHtmlPartFuture(o2, false, a -> {
										if(a.succeeded()) {
											LOGGER.info(String.format("HtmlPart %s refreshed. ", pk2));
										} else {
											LOGGER.info(String.format("HtmlPart %s failed. ", pk2));
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
						PageDesignEnUSApiServiceImpl service = new PageDesignEnUSApiServiceImpl(siteRequest2.getSiteContext_());
						List<Future> futures2 = new ArrayList<>();
						for(PageDesign o2 : searchList.getList()) {
							futures2.add(
								service.patchPageDesignFuture(o2, false, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("PageDesign %s refreshed. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("PageDesign %s failed. ", o2.getPk()));
										eventHandler.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Refresh PageDesign succeeded. ");
								eventHandler.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Refresh relations failed. ", b.cause());
								errorPageDesign(siteRequest2, eventHandler, b);
							}
						});
					} else {
						LOGGER.error("Refresh relations failed. ", a.cause());
						errorPageDesign(siteRequest2, eventHandler, a);
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
