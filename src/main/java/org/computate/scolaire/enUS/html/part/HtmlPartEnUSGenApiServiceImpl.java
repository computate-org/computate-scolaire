package org.computate.scolaire.enUS.html.part;

import org.computate.scolaire.enUS.design.PageDesignEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.design.PageDesign;
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
 * CanonicalName.frFR: org.computate.scolaire.frFR.html.part.PartHtmlFrFRGenApiServiceImpl
 **/
public class HtmlPartEnUSGenApiServiceImpl implements HtmlPartEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(HtmlPartEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "HtmlPartEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public HtmlPartEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
	}

	// POST //

	@Override
	public void postHtmlPart(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			LOGGER.info(String.format("postHtmlPart started. "));
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForHtmlPart(siteContext, operationRequest, body);

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

			sqlHtmlPart(siteRequest, a -> {
				if(a.succeeded()) {
					userHtmlPart(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
							postHtmlPartFuture(siteRequest, false, c -> {
								if(c.succeeded()) {
									HtmlPart htmlPart = c.result();
									apiRequestHtmlPart(htmlPart);
									postHtmlPartResponse(htmlPart, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postHtmlPart succeeded. "));
										} else {
											LOGGER.error(String.format("postHtmlPart failed. ", d.cause()));
											errorHtmlPart(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorHtmlPart(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorHtmlPart(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorHtmlPart(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorHtmlPart(null, eventHandler, Future.failedFuture(e));
		}
	}


	public Future<HtmlPart> postHtmlPartFuture(SiteRequestEnUS siteRequest, Boolean inheritPk, Handler<AsyncResult<HtmlPart>> eventHandler) {
		Promise<HtmlPart> promise = Promise.promise();
		try {
			createHtmlPart(siteRequest, a -> {
				if(a.succeeded()) {
					HtmlPart htmlPart = a.result();
					sqlPOSTHtmlPart(htmlPart, inheritPk, b -> {
						if(b.succeeded()) {
							defineIndexHtmlPart(htmlPart, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(htmlPart));
									promise.complete(htmlPart);
								} else {
									errorHtmlPart(siteRequest, null, c);
								}
							});
						} else {
							errorHtmlPart(siteRequest, null, b);
						}
					});
				} else {
					errorHtmlPart(siteRequest, null, a);
				}
			});
		} catch(Exception e) {
			errorHtmlPart(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTHtmlPart(HtmlPart o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "pageDesignKeys":
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
									postSqlParams.addAll(Arrays.asList("htmlPartKeys", l, "pageDesignKeys", pk));
								}
							}
						}
						break;
					case "htmlLink":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlLink", jsonObject.getString(entityVar), pk));
						break;
					case "htmlElement":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlElement", jsonObject.getString(entityVar), pk));
						break;
					case "htmlId":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlId", jsonObject.getString(entityVar), pk));
						break;
					case "htmlClasses":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlClasses", jsonObject.getString(entityVar), pk));
						break;
					case "htmlStyle":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlStyle", jsonObject.getString(entityVar), pk));
						break;
					case "htmlBefore":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlBefore", jsonObject.getString(entityVar), pk));
						break;
					case "htmlAfter":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlAfter", jsonObject.getString(entityVar), pk));
						break;
					case "htmlText":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlText", jsonObject.getString(entityVar), pk));
						break;
					case "htmlVar":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlVar", jsonObject.getString(entityVar), pk));
						break;
					case "htmlVarSpan":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlVarSpan", jsonObject.getString(entityVar), pk));
						break;
					case "htmlVarForm":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlVarForm", jsonObject.getString(entityVar), pk));
						break;
					case "htmlVarInput":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlVarInput", jsonObject.getString(entityVar), pk));
						break;
					case "htmlVarForEach":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlVarForEach", jsonObject.getString(entityVar), pk));
						break;
					case "htmlExclude":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlExclude", jsonObject.getBoolean(entityVar), pk));
						break;
					case "pdfExclude":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("pdfExclude", jsonObject.getBoolean(entityVar), pk));
						break;
					case "loginLogout":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("loginLogout", jsonObject.getBoolean(entityVar), pk));
						break;
					case "sort1":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("sort1", jsonObject.getString(entityVar), pk));
						break;
					case "sort2":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("sort2", jsonObject.getString(entityVar), pk));
						break;
					case "sort3":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("sort3", jsonObject.getString(entityVar), pk));
						break;
					case "sort4":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("sort4", jsonObject.getString(entityVar), pk));
						break;
					case "sort5":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("sort5", jsonObject.getString(entityVar), pk));
						break;
					case "sort6":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("sort6", jsonObject.getString(entityVar), pk));
						break;
					case "sort7":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("sort7", jsonObject.getString(entityVar), pk));
						break;
					case "sort8":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("sort8", jsonObject.getString(entityVar), pk));
						break;
					case "sort9":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("sort9", jsonObject.getString(entityVar), pk));
						break;
					case "sort10":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("sort10", jsonObject.getString(entityVar), pk));
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

	public void postHtmlPartResponse(HtmlPart htmlPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = htmlPart.getSiteRequest_();
		response200POSTHtmlPart(htmlPart, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("postHtmlPart sql commit. "));
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("postHtmlPart sql close. "));
								ApiRequest apiRequest = apiRequestHtmlPart(htmlPart);
								htmlPart.apiRequestHtmlPart();
								siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorHtmlPart(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorHtmlPart(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorHtmlPart(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200POSTHtmlPart(HtmlPart o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
	public void putimportHtmlPart(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			LOGGER.info(String.format("putimportHtmlPart started. "));
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForHtmlPart(siteContext, operationRequest, body);

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

			sqlHtmlPart(siteRequest, a -> {
				if(a.succeeded()) {
					userHtmlPart(siteRequest, b -> {
						if(b.succeeded()) {
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											sqlHtmlPart(siteRequest, d -> {
												if(d.succeeded()) {
													try {
														ApiRequest apiRequest = new ApiRequest();
														JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
														apiRequest.setRows(jsonArray.size());
														apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest);
														siteRequest.setApiRequest_(apiRequest);
														siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
														listPUTImportHtmlPart(apiRequest, siteRequest, e -> {
															if(e.succeeded()) {
																putimportHtmlPartResponse(siteRequest, f -> {
																	if(f.succeeded()) {
																		eventHandler.handle(Future.succeededFuture(f.result()));
																		LOGGER.info(String.format("putimportHtmlPart succeeded. "));
																	} else {
																		LOGGER.error(String.format("putimportHtmlPart failed. ", f.cause()));
																		errorHtmlPart(siteRequest, eventHandler, f);
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
									errorHtmlPart(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorHtmlPart(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorHtmlPart(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorHtmlPart(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPUTImportHtmlPart(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		jsonArray.forEach(obj -> {
			JsonObject json = (JsonObject)obj;

			json.put("inheritPk", json.getValue("pk"));

			SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForHtmlPart(siteContext, siteRequest.getOperationRequest(), json);
			siteRequest2.setSqlConnection(siteRequest.getSqlConnection());

			SearchList<HtmlPart> searchList = new SearchList<HtmlPart>();
			searchList.setStore(true);
			searchList.setQuery("*:*");
			searchList.setC(HtmlPart.class);
			searchList.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
			searchList.initDeepForClass(siteRequest2);

			if(searchList.size() == 1) {
				HtmlPart o = searchList.get(0);
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
					patchHtmlPartFuture(o, true, a -> {
						if(a.succeeded()) {
							HtmlPart htmlPart = a.result();
							apiRequestHtmlPart(htmlPart);
							apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
							siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
						} else {
							errorHtmlPart(siteRequest2, eventHandler, a);
						}
					})
				);
			} else {
				futures.add(
					postHtmlPartFuture(siteRequest2, true, a -> {
						if(a.succeeded()) {
							HtmlPart htmlPart = a.result();
							apiRequestHtmlPart(htmlPart);
						} else {
							errorHtmlPart(siteRequest2, eventHandler, a);
						}
					})
				);
			}
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				response200PUTImportHtmlPart(siteRequest, eventHandler);
				siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
			} else {
				errorHtmlPart(apiRequest.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public void putimportHtmlPartResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		response200PUTImportHtmlPart(siteRequest, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("putimportHtmlPart sql commit. "));
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("putimportHtmlPart sql close. "));
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorHtmlPart(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorHtmlPart(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorHtmlPart(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PUTImportHtmlPart(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(apiRequest).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTMerge //

	@Override
	public void putmergeHtmlPart(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			LOGGER.info(String.format("putmergeHtmlPart started. "));
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForHtmlPart(siteContext, operationRequest, body);

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

			sqlHtmlPart(siteRequest, a -> {
				if(a.succeeded()) {
					userHtmlPart(siteRequest, b -> {
						if(b.succeeded()) {
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											sqlHtmlPart(siteRequest, d -> {
												if(d.succeeded()) {
													try {
														ApiRequest apiRequest = new ApiRequest();
														JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
														apiRequest.setRows(jsonArray.size());
														apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest);
														siteRequest.setApiRequest_(apiRequest);
														siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
														listPUTMergeHtmlPart(apiRequest, siteRequest, e -> {
															if(e.succeeded()) {
																putmergeHtmlPartResponse(siteRequest, f -> {
																	if(f.succeeded()) {
																		eventHandler.handle(Future.succeededFuture(f.result()));
																		LOGGER.info(String.format("putmergeHtmlPart succeeded. "));
																	} else {
																		LOGGER.error(String.format("putmergeHtmlPart failed. ", f.cause()));
																		errorHtmlPart(siteRequest, eventHandler, f);
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
									errorHtmlPart(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorHtmlPart(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorHtmlPart(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorHtmlPart(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPUTMergeHtmlPart(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		jsonArray.forEach(obj -> {
			JsonObject json = (JsonObject)obj;

			json.put("inheritPk", json.getValue("pk"));

			SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForHtmlPart(siteContext, siteRequest.getOperationRequest(), json);
			siteRequest2.setSqlConnection(siteRequest.getSqlConnection());

			SearchList<HtmlPart> searchList = new SearchList<HtmlPart>();
			searchList.setStore(true);
			searchList.setQuery("*:*");
			searchList.setC(HtmlPart.class);
			searchList.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
			searchList.initDeepForClass(siteRequest2);

			if(searchList.size() == 1) {
				HtmlPart o = searchList.get(0);
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
					patchHtmlPartFuture(o, false, a -> {
						if(a.succeeded()) {
							HtmlPart htmlPart = a.result();
							apiRequestHtmlPart(htmlPart);
							apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
							siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
						} else {
							errorHtmlPart(siteRequest2, eventHandler, a);
						}
					})
				);
			} else {
				futures.add(
					postHtmlPartFuture(siteRequest2, false, a -> {
						if(a.succeeded()) {
							HtmlPart htmlPart = a.result();
							apiRequestHtmlPart(htmlPart);
						} else {
							errorHtmlPart(siteRequest2, eventHandler, a);
						}
					})
				);
			}
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				response200PUTMergeHtmlPart(siteRequest, eventHandler);
				siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
			} else {
				errorHtmlPart(apiRequest.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public void putmergeHtmlPartResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		response200PUTMergeHtmlPart(siteRequest, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("putmergeHtmlPart sql commit. "));
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("putmergeHtmlPart sql close. "));
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorHtmlPart(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorHtmlPart(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorHtmlPart(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PUTMergeHtmlPart(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(apiRequest).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTCopy //

	@Override
	public void putcopyHtmlPart(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			LOGGER.info(String.format("putcopyHtmlPart started. "));
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForHtmlPart(siteContext, operationRequest, body);

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

			sqlHtmlPart(siteRequest, a -> {
				if(a.succeeded()) {
					userHtmlPart(siteRequest, b -> {
						if(b.succeeded()) {
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									aSearchHtmlPart(siteRequest, false, true, "/api/html-part/copy", "PUTCopy", d -> {
										if(d.succeeded()) {
											SearchList<HtmlPart> listHtmlPart = d.result();
											ApiRequest apiRequest = new ApiRequest();
											apiRequest.setRows(listHtmlPart.getRows());
											apiRequest.setNumFound(listHtmlPart.getQueryResponse().getResults().getNumFound());
											apiRequest.setNumPATCH(0L);
											apiRequest.initDeepApiRequest(siteRequest);
											siteRequest.setApiRequest_(apiRequest);
											siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
											WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
											workerExecutor.executeBlocking(
												blockingCodeHandler -> {
													sqlHtmlPart(siteRequest, e -> {
														if(e.succeeded()) {
															try {
																listPUTCopyHtmlPart(apiRequest, listHtmlPart, f -> {
																	if(f.succeeded()) {
																		putcopyHtmlPartResponse(listHtmlPart, g -> {
																			if(g.succeeded()) {
																				eventHandler.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("putcopyHtmlPart succeeded. "));
																			} else {
																				LOGGER.error(String.format("putcopyHtmlPart failed. ", g.cause()));
																				errorHtmlPart(siteRequest, eventHandler, d);
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
											errorHtmlPart(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorHtmlPart(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorHtmlPart(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorHtmlPart(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorHtmlPart(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPUTCopyHtmlPart(ApiRequest apiRequest, SearchList<HtmlPart> listHtmlPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listHtmlPart.getSiteRequest_();
		listHtmlPart.getList().forEach(o -> {
			futures.add(
				putcopyHtmlPartFuture(siteRequest, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						HtmlPart htmlPart = a.result();
						apiRequestHtmlPart(htmlPart);
					} else {
						errorHtmlPart(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listHtmlPart.size());
				if(listHtmlPart.next()) {
					siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
					listPUTCopyHtmlPart(apiRequest, listHtmlPart, eventHandler);
				} else {
					response200PUTCopyHtmlPart(listHtmlPart, eventHandler);
				}
			} else {
				errorHtmlPart(listHtmlPart.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<HtmlPart> putcopyHtmlPartFuture(SiteRequestEnUS siteRequest, JsonObject jsonObject, Handler<AsyncResult<HtmlPart>> eventHandler) {
		Promise<HtmlPart> promise = Promise.promise();
		try {

			jsonObject.put("saves", Optional.ofNullable(jsonObject.getJsonArray("saves")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("saves").add(o.getKey());
			});

			createHtmlPart(siteRequest, a -> {
				if(a.succeeded()) {
					HtmlPart htmlPart = a.result();
					sqlPUTCopyHtmlPart(htmlPart, jsonObject, b -> {
						if(b.succeeded()) {
							defineHtmlPart(htmlPart, c -> {
								if(c.succeeded()) {
									attributeHtmlPart(htmlPart, d -> {
										if(d.succeeded()) {
											indexHtmlPart(htmlPart, e -> {
												if(e.succeeded()) {
													eventHandler.handle(Future.succeededFuture(htmlPart));
													promise.complete(htmlPart);
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
			errorHtmlPart(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopyHtmlPart(HtmlPart o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "pageDesignKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("htmlPartKeys", l, "pageDesignKeys", pk));
						}
						break;
					case "htmlLink":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("htmlLink", jsonObject.getString(entityVar), pk));
						break;
					case "htmlElement":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("htmlElement", jsonObject.getString(entityVar), pk));
						break;
					case "htmlId":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("htmlId", jsonObject.getString(entityVar), pk));
						break;
					case "htmlClasses":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("htmlClasses", jsonObject.getString(entityVar), pk));
						break;
					case "htmlStyle":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("htmlStyle", jsonObject.getString(entityVar), pk));
						break;
					case "htmlBefore":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("htmlBefore", jsonObject.getString(entityVar), pk));
						break;
					case "htmlAfter":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("htmlAfter", jsonObject.getString(entityVar), pk));
						break;
					case "htmlText":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("htmlText", jsonObject.getString(entityVar), pk));
						break;
					case "htmlVar":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("htmlVar", jsonObject.getString(entityVar), pk));
						break;
					case "htmlVarSpan":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("htmlVarSpan", jsonObject.getString(entityVar), pk));
						break;
					case "htmlVarForm":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("htmlVarForm", jsonObject.getString(entityVar), pk));
						break;
					case "htmlVarInput":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("htmlVarInput", jsonObject.getString(entityVar), pk));
						break;
					case "htmlVarForEach":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("htmlVarForEach", jsonObject.getString(entityVar), pk));
						break;
					case "htmlExclude":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("htmlExclude", jsonObject.getBoolean(entityVar), pk));
						break;
					case "pdfExclude":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("pdfExclude", jsonObject.getBoolean(entityVar), pk));
						break;
					case "loginLogout":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("loginLogout", jsonObject.getBoolean(entityVar), pk));
						break;
					case "sort1":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("sort1", jsonObject.getString(entityVar), pk));
						break;
					case "sort2":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("sort2", jsonObject.getString(entityVar), pk));
						break;
					case "sort3":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("sort3", jsonObject.getString(entityVar), pk));
						break;
					case "sort4":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("sort4", jsonObject.getString(entityVar), pk));
						break;
					case "sort5":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("sort5", jsonObject.getString(entityVar), pk));
						break;
					case "sort6":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("sort6", jsonObject.getString(entityVar), pk));
						break;
					case "sort7":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("sort7", jsonObject.getString(entityVar), pk));
						break;
					case "sort8":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("sort8", jsonObject.getString(entityVar), pk));
						break;
					case "sort9":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("sort9", jsonObject.getString(entityVar), pk));
						break;
					case "sort10":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("sort10", jsonObject.getString(entityVar), pk));
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

	public void putcopyHtmlPartResponse(SearchList<HtmlPart> listHtmlPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listHtmlPart.getSiteRequest_();
		response200PUTCopyHtmlPart(listHtmlPart, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("putcopyHtmlPart sql commit. "));
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("putcopyHtmlPart sql close. "));
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorHtmlPart(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorHtmlPart(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorHtmlPart(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PUTCopyHtmlPart(SearchList<HtmlPart> listHtmlPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listHtmlPart.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(apiRequest).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchHtmlPart(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			LOGGER.info(String.format("patchHtmlPart started. "));
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForHtmlPart(siteContext, operationRequest, body);

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

			sqlHtmlPart(siteRequest, a -> {
				if(a.succeeded()) {
					userHtmlPart(siteRequest, b -> {
						if(b.succeeded()) {
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									aSearchHtmlPart(siteRequest, false, true, "/api/html-part", "PATCH", d -> {
										if(d.succeeded()) {
											SearchList<HtmlPart> listHtmlPart = d.result();
											ApiRequest apiRequest = new ApiRequest();
											apiRequest.setRows(listHtmlPart.getRows());
											apiRequest.setNumFound(listHtmlPart.getQueryResponse().getResults().getNumFound());
											apiRequest.setNumPATCH(0L);
											apiRequest.initDeepApiRequest(siteRequest);
											siteRequest.setApiRequest_(apiRequest);
											siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
											SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listHtmlPart.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
											Date date = null;
											if(facets != null)
												date = (Date)facets.get("max_modified");
											String dt;
											if(date == null)
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
											else
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
											listHtmlPart.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));

											if(listHtmlPart.size() == 1) {
												HtmlPart o = listHtmlPart.get(0);
												apiRequest.setPk(o.getPk());
												apiRequest.setOriginal(o);
												apiRequestHtmlPart(o);
											o.apiRequestHtmlPart();
											}
											WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
											workerExecutor.executeBlocking(
												blockingCodeHandler -> {
													sqlHtmlPart(siteRequest, e -> {
														if(e.succeeded()) {
															try {
																listPATCHHtmlPart(apiRequest, listHtmlPart, dt, f -> {
																	if(f.succeeded()) {
																		patchHtmlPartResponse(listHtmlPart, g -> {
																			if(g.succeeded()) {
																				eventHandler.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("patchHtmlPart succeeded. "));
																			} else {
																				LOGGER.error(String.format("patchHtmlPart failed. ", g.cause()));
																				errorHtmlPart(siteRequest, eventHandler, d);
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
											errorHtmlPart(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorHtmlPart(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorHtmlPart(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorHtmlPart(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorHtmlPart(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPATCHHtmlPart(ApiRequest apiRequest, SearchList<HtmlPart> listHtmlPart, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listHtmlPart.getSiteRequest_();
		listHtmlPart.getList().forEach(o -> {
			futures.add(
				patchHtmlPartFuture(o, false, a -> {
					if(a.succeeded()) {
							HtmlPart htmlPart = a.result();
							apiRequestHtmlPart(htmlPart);
					} else {
						errorHtmlPart(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listHtmlPart.size());
				if(listHtmlPart.next(dt)) {
					siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
					listPATCHHtmlPart(apiRequest, listHtmlPart, dt, eventHandler);
				} else {
					response200PATCHHtmlPart(listHtmlPart, eventHandler);
				}
			} else {
				errorHtmlPart(listHtmlPart.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<HtmlPart> patchHtmlPartFuture(HtmlPart o, Boolean inheritPk, Handler<AsyncResult<HtmlPart>> eventHandler) {
		Promise<HtmlPart> promise = Promise.promise();
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			sqlPATCHHtmlPart(o, inheritPk, a -> {
				if(a.succeeded()) {
					HtmlPart htmlPart = a.result();
					defineHtmlPart(htmlPart, b -> {
						if(b.succeeded()) {
							attributeHtmlPart(htmlPart, c -> {
								if(c.succeeded()) {
									indexHtmlPart(htmlPart, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(htmlPart));
											promise.complete(htmlPart);
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
			errorHtmlPart(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHHtmlPart(HtmlPart o, Boolean inheritPk, Handler<AsyncResult<HtmlPart>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject requestJson = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = requestJson.fieldNames();
			HtmlPart o2 = new HtmlPart();

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.html.part.HtmlPart"));
			for(String methodName : methodNames) {
				switch(methodName) {
					case "setInheritPk":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inheritPk"));
						} else {
							o2.setInheritPk(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inheritPk", o2.jsonInheritPk(), pk));
						}
						break;
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
					case "addPageDesignKeys":
						{
							Long l = Long.parseLong(requestJson.getString(methodName));
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
									patchSqlParams.addAll(Arrays.asList("htmlPartKeys", l, "pageDesignKeys", pk));
								}
							}
						}
						break;
					case "addAllPageDesignKeys":
						JsonArray addAllPageDesignKeysValues = requestJson.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllPageDesignKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllPageDesignKeysValues.getString(i));
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
									patchSqlParams.addAll(Arrays.asList("htmlPartKeys", l, "pageDesignKeys", pk));
								}
							}
						}
						break;
					case "setPageDesignKeys":
						JsonArray setPageDesignKeysValues = requestJson.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("htmlPartKeys", "pageDesignKeys", pk));
						for(Integer i = 0; i <  setPageDesignKeysValues.size(); i++) {
							Long l = Long.parseLong(setPageDesignKeysValues.getString(i));
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
									patchSqlParams.addAll(Arrays.asList("htmlPartKeys", l, "pageDesignKeys", pk));
								}
							}
						}
						break;
					case "removePageDesignKeys":
						{
							Long l = Long.parseLong(requestJson.getString(methodName));
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
									patchSqlParams.addAll(Arrays.asList("htmlPartKeys", l, "pageDesignKeys", pk));
								}
							}
						}
						break;
					case "setHtmlLink":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlLink"));
						} else {
							o2.setHtmlLink(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlLink", o2.jsonHtmlLink(), pk));
						}
						break;
					case "setHtmlElement":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlElement"));
						} else {
							o2.setHtmlElement(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlElement", o2.jsonHtmlElement(), pk));
						}
						break;
					case "setHtmlId":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlId"));
						} else {
							o2.setHtmlId(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlId", o2.jsonHtmlId(), pk));
						}
						break;
					case "setHtmlClasses":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlClasses"));
						} else {
							o2.setHtmlClasses(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlClasses", o2.jsonHtmlClasses(), pk));
						}
						break;
					case "setHtmlStyle":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlStyle"));
						} else {
							o2.setHtmlStyle(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlStyle", o2.jsonHtmlStyle(), pk));
						}
						break;
					case "setHtmlBefore":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlBefore"));
						} else {
							o2.setHtmlBefore(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlBefore", o2.jsonHtmlBefore(), pk));
						}
						break;
					case "setHtmlAfter":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlAfter"));
						} else {
							o2.setHtmlAfter(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlAfter", o2.jsonHtmlAfter(), pk));
						}
						break;
					case "setHtmlText":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlText"));
						} else {
							o2.setHtmlText(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlText", o2.jsonHtmlText(), pk));
						}
						break;
					case "setHtmlVar":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlVar"));
						} else {
							o2.setHtmlVar(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlVar", o2.jsonHtmlVar(), pk));
						}
						break;
					case "setHtmlVarSpan":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlVarSpan"));
						} else {
							o2.setHtmlVarSpan(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlVarSpan", o2.jsonHtmlVarSpan(), pk));
						}
						break;
					case "setHtmlVarForm":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlVarForm"));
						} else {
							o2.setHtmlVarForm(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlVarForm", o2.jsonHtmlVarForm(), pk));
						}
						break;
					case "setHtmlVarInput":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlVarInput"));
						} else {
							o2.setHtmlVarInput(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlVarInput", o2.jsonHtmlVarInput(), pk));
						}
						break;
					case "setHtmlVarForEach":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlVarForEach"));
						} else {
							o2.setHtmlVarForEach(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlVarForEach", o2.jsonHtmlVarForEach(), pk));
						}
						break;
					case "setHtmlExclude":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlExclude"));
						} else {
							o2.setHtmlExclude(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlExclude", o2.jsonHtmlExclude(), pk));
						}
						break;
					case "setPdfExclude":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "pdfExclude"));
						} else {
							o2.setPdfExclude(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("pdfExclude", o2.jsonPdfExclude(), pk));
						}
						break;
					case "setLoginLogout":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "loginLogout"));
						} else {
							o2.setLoginLogout(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("loginLogout", o2.jsonLoginLogout(), pk));
						}
						break;
					case "setSort1":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sort1"));
						} else {
							o2.setSort1(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sort1", o2.jsonSort1(), pk));
						}
						break;
					case "setSort2":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sort2"));
						} else {
							o2.setSort2(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sort2", o2.jsonSort2(), pk));
						}
						break;
					case "setSort3":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sort3"));
						} else {
							o2.setSort3(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sort3", o2.jsonSort3(), pk));
						}
						break;
					case "setSort4":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sort4"));
						} else {
							o2.setSort4(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sort4", o2.jsonSort4(), pk));
						}
						break;
					case "setSort5":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sort5"));
						} else {
							o2.setSort5(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sort5", o2.jsonSort5(), pk));
						}
						break;
					case "setSort6":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sort6"));
						} else {
							o2.setSort6(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sort6", o2.jsonSort6(), pk));
						}
						break;
					case "setSort7":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sort7"));
						} else {
							o2.setSort7(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sort7", o2.jsonSort7(), pk));
						}
						break;
					case "setSort8":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sort8"));
						} else {
							o2.setSort8(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sort8", o2.jsonSort8(), pk));
						}
						break;
					case "setSort9":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sort9"));
						} else {
							o2.setSort9(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sort9", o2.jsonSort9(), pk));
						}
						break;
					case "setSort10":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sort10"));
						} else {
							o2.setSort10(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sort10", o2.jsonSort10(), pk));
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
					HtmlPart o3 = new HtmlPart();
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

	public void patchHtmlPartResponse(SearchList<HtmlPart> listHtmlPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listHtmlPart.getSiteRequest_();
		response200PATCHHtmlPart(listHtmlPart, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("patchHtmlPart sql commit. "));
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("patchHtmlPart sql close. "));
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketHtmlPart", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorHtmlPart(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorHtmlPart(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorHtmlPart(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PATCHHtmlPart(SearchList<HtmlPart> listHtmlPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listHtmlPart.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			JsonObject json = JsonObject.mapFrom(apiRequest);
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getHtmlPart(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForHtmlPart(siteContext, operationRequest);

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

			sqlHtmlPart(siteRequest, a -> {
				if(a.succeeded()) {
					userHtmlPart(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchHtmlPart(siteRequest, false, true, "/api/html-part/{id}", "GET", c -> {
								if(c.succeeded()) {
									SearchList<HtmlPart> listHtmlPart = c.result();
									getHtmlPartResponse(listHtmlPart, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getHtmlPart succeeded. "));
										} else {
											LOGGER.error(String.format("getHtmlPart failed. ", d.cause()));
											errorHtmlPart(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorHtmlPart(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorHtmlPart(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorHtmlPart(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorHtmlPart(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void getHtmlPartResponse(SearchList<HtmlPart> listHtmlPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listHtmlPart.getSiteRequest_();
		response200GETHtmlPart(listHtmlPart, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("getHtmlPart sql commit. "));
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("getHtmlPart sql close. "));
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorHtmlPart(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorHtmlPart(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorHtmlPart(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200GETHtmlPart(SearchList<HtmlPart> listHtmlPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listHtmlPart.getSiteRequest_();
			SolrDocumentList solrDocuments = listHtmlPart.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listHtmlPart.getList().stream().findFirst().orElse(null));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchHtmlPart(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForHtmlPart(siteContext, operationRequest);

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

			sqlHtmlPart(siteRequest, a -> {
				if(a.succeeded()) {
					userHtmlPart(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchHtmlPart(siteRequest, false, true, "/api/html-part", "Search", c -> {
								if(c.succeeded()) {
									SearchList<HtmlPart> listHtmlPart = c.result();
									searchHtmlPartResponse(listHtmlPart, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchHtmlPart succeeded. "));
										} else {
											LOGGER.error(String.format("searchHtmlPart failed. ", d.cause()));
											errorHtmlPart(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorHtmlPart(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorHtmlPart(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorHtmlPart(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorHtmlPart(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void searchHtmlPartResponse(SearchList<HtmlPart> listHtmlPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listHtmlPart.getSiteRequest_();
		response200SearchHtmlPart(listHtmlPart, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("searchHtmlPart sql commit. "));
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("searchHtmlPart sql close. "));
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorHtmlPart(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorHtmlPart(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorHtmlPart(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200SearchHtmlPart(SearchList<HtmlPart> listHtmlPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listHtmlPart.getSiteRequest_();
			QueryResponse responseSearch = listHtmlPart.getQueryResponse();
			SolrDocumentList solrDocuments = listHtmlPart.getSolrDocumentList();
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
			listHtmlPart.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listHtmlPart.getFields();
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
	public void searchpageHtmlPartId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageHtmlPart(operationRequest, eventHandler);
	}

	@Override
	public void searchpageHtmlPart(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForHtmlPart(siteContext, operationRequest);

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

			sqlHtmlPart(siteRequest, a -> {
				if(a.succeeded()) {
					userHtmlPart(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchHtmlPart(siteRequest, false, true, "/html-part", "SearchPage", c -> {
								if(c.succeeded()) {
									SearchList<HtmlPart> listHtmlPart = c.result();
									searchpageHtmlPartResponse(listHtmlPart, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchpageHtmlPart succeeded. "));
										} else {
											LOGGER.error(String.format("searchpageHtmlPart failed. ", d.cause()));
											errorHtmlPart(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorHtmlPart(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorHtmlPart(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorHtmlPart(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorHtmlPart(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void searchpageHtmlPartResponse(SearchList<HtmlPart> listHtmlPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listHtmlPart.getSiteRequest_();
		response200SearchPageHtmlPart(listHtmlPart, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("searchpageHtmlPart sql commit. "));
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								LOGGER.info(String.format("searchpageHtmlPart sql close. "));
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorHtmlPart(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorHtmlPart(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorHtmlPart(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200SearchPageHtmlPart(SearchList<HtmlPart> listHtmlPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listHtmlPart.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listHtmlPart.getSiteRequest_(), buffer);
			HtmlPartPage page = new HtmlPartPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/html-part");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listHtmlPart.size() == 1)
				siteRequest.setRequestPk(listHtmlPart.get(0).getPk());
			siteRequest.setW(w);
			page.setListHtmlPart(listHtmlPart);
			page.setSiteRequest_(siteRequest);
			page.initDeepHtmlPartPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<HtmlPart> defineIndexHtmlPart(HtmlPart htmlPart, Handler<AsyncResult<HtmlPart>> eventHandler) {
		Promise<HtmlPart> promise = Promise.promise();
		SiteRequestEnUS siteRequest = htmlPart.getSiteRequest_();
		defineHtmlPart(htmlPart, c -> {
			if(c.succeeded()) {
				attributeHtmlPart(htmlPart, d -> {
					if(d.succeeded()) {
						indexHtmlPart(htmlPart, e -> {
							if(e.succeeded()) {
								eventHandler.handle(Future.succeededFuture(htmlPart));
								promise.complete(htmlPart);
							} else {
								errorHtmlPart(siteRequest, null, e);
							}
						});
					} else {
						errorHtmlPart(siteRequest, null, d);
					}
				});
			} else {
				errorHtmlPart(siteRequest, null, c);
			}
		});
		return promise.future();
	}

	public void createHtmlPart(SiteRequestEnUS siteRequest, Handler<AsyncResult<HtmlPart>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(HtmlPart.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				HtmlPart o = new HtmlPart();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public ApiRequest apiRequestHtmlPart(HtmlPart o) {
		ApiRequest apiRequest = o.getSiteRequest_().getApiRequest_();
		if(apiRequest != null) {
			List<Long> pks = apiRequest.getPks();
			List<String> classes = apiRequest.getClasses();
			for(Long pk : o.getPageDesignKeys()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("PageDesign");
				}
			}
		}
		return apiRequest;
	}

	public void errorHtmlPart(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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

	public void sqlHtmlPart(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public SiteRequestEnUS generateSiteRequestEnUSForHtmlPart(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForHtmlPart(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForHtmlPart(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userHtmlPart(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
								userHtmlPartDefine(siteRequest, jsonObject, false);

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
																		errorHtmlPart(siteRequest, eventHandler, f);
																	}
																});
															} else {
																errorHtmlPart(siteRequest, eventHandler, e);
															}
														});
													} else {
														errorHtmlPart(siteRequest, eventHandler, d);
													}
												});
											} else {
												errorHtmlPart(siteRequest, eventHandler, c);
											}
										});
									} else {
										errorHtmlPart(siteRequest, eventHandler, b);
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
								Boolean define = userHtmlPartDefine(siteRequest, jsonObject, true);
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
																	errorHtmlPart(siteRequest, eventHandler, f);
																}
															});
														} else {
															errorHtmlPart(siteRequest, eventHandler, e);
														}
													});
												} else {
													errorHtmlPart(siteRequest, eventHandler, d);
												}
											});
										} else {
											errorHtmlPart(siteRequest, eventHandler, c);
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

	public Boolean userHtmlPartDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void aSearchHtmlPartQ(String uri, String apiMethod, SearchList<HtmlPart> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
			searchList.setHighlight(true);
			searchList.setHighlightSnippets(3);
			searchList.addHighlightField(varIndexed);
			searchList.setParam("hl.encoder", "html");
		}
	}

	public void aSearchHtmlPartFq(String uri, String apiMethod, SearchList<HtmlPart> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
	}

	public void aSearchHtmlPartSort(String uri, String apiMethod, SearchList<HtmlPart> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addSort(varIndexed, ORDER.valueOf(valueIndexed));
	}

	public void aSearchHtmlPartRows(String uri, String apiMethod, SearchList<HtmlPart> searchList, Integer valueRows) {
			searchList.setRows(apiMethod != null && apiMethod.contains("Search") ? valueRows : 10);
	}

	public void aSearchHtmlPartStart(String uri, String apiMethod, SearchList<HtmlPart> searchList, Integer valueStart) {
		searchList.setStart(valueStart);
	}

	public void aSearchHtmlPartVar(String uri, String apiMethod, SearchList<HtmlPart> searchList, String var, String value) {
		searchList.getSiteRequest_().getRequestVars().put(var, value);
	}

	public void aSearchHtmlPart(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String uri, String apiMethod, Handler<AsyncResult<SearchList<HtmlPart>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<HtmlPart> searchList = new SearchList<HtmlPart>();
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.setQuery("*:*");
			searchList.setC(HtmlPart.class);
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
								varIndexed = "*".equals(entityVar) ? entityVar : HtmlPart.varSearchHtmlPart(entityVar);
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								valueIndexed = StringUtils.isEmpty(valueIndexed) ? "*" : valueIndexed;
								aSearchHtmlPartQ(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "fq":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = HtmlPart.varIndexedHtmlPart(entityVar);
								aSearchHtmlPartFq(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = HtmlPart.varIndexedHtmlPart(entityVar);
								aSearchHtmlPartSort(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "start":
								valueStart = (Integer)paramObject;
								aSearchHtmlPartStart(uri, apiMethod, searchList, valueStart);
								break;
							case "rows":
								valueRows = (Integer)paramObject;
								aSearchHtmlPartRows(uri, apiMethod, searchList, valueRows);
								break;
							case "var":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								aSearchHtmlPartVar(uri, apiMethod, searchList, entityVar, valueIndexed);
								break;
						}
					}
				} catch(Exception e) {
					eventHandler.handle(Future.failedFuture(e));
				}
			});
			if(searchList.getSorts().size() == 0) {
				searchList.addSort("sort1_indexed_double", ORDER.asc);
				searchList.addSort("sort2_indexed_double", ORDER.asc);
				searchList.addSort("sort3_indexed_double", ORDER.asc);
				searchList.addSort("sort4_indexed_double", ORDER.asc);
				searchList.addSort("sort5_indexed_double", ORDER.asc);
				searchList.addSort("sort6_indexed_double", ORDER.asc);
				searchList.addSort("sort7_indexed_double", ORDER.asc);
				searchList.addSort("sort8_indexed_double", ORDER.asc);
				searchList.addSort("sort9_indexed_double", ORDER.asc);
				searchList.addSort("sort10_indexed_double", ORDER.asc);
			}
			searchList.initDeepForClass(siteRequest);
			eventHandler.handle(Future.succeededFuture(searchList));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void defineHtmlPart(HtmlPart o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void attributeHtmlPart(HtmlPart o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void indexHtmlPart(HtmlPart o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			if(BooleanUtils.isFalse(Optional.ofNullable(siteRequest.getApiRequest_()).map(ApiRequest::getEmpty).orElse(null))) {
				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForHtmlPart(siteContext, siteRequest.getOperationRequest(), new JsonObject());
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				SearchList<HtmlPart> searchList = new SearchList<HtmlPart>();
				searchList.setPopulate(true);
				searchList.setQuery("*:*");
				searchList.setC(HtmlPart.class);
				searchList.addFilterQuery("modified_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(siteRequest.getApiRequest_().getCreated().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				searchList.add("json.facet", "{pageDesignKeys:{terms:{field:pageDesignKeys_indexed_longs, limit:1000}}}");
				searchList.setRows(1000);
				searchList.initDeepSearchList(siteRequest2);
				List<Future> futures = new ArrayList<>();

				{
					PageDesignEnUSGenApiServiceImpl service = new PageDesignEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
					for(Long pk : o.getPageDesignKeys()) {
						PageDesign o2 = new PageDesign();

						o2.setPk(pk);
						o2.setSiteRequest_(siteRequest2);
						futures.add(
							service.patchPageDesignFuture(o2, false, a -> {
								if(a.succeeded()) {
									LOGGER.info(String.format("PageDesign %s refreshed. ", pk));
								} else {
									LOGGER.info(String.format("PageDesign %s failed. ", pk));
									eventHandler.handle(Future.failedFuture(a.cause()));
								}
							})
						);
					}
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						LOGGER.info("Refresh relations succeeded. ");
						HtmlPartEnUSGenApiServiceImpl service = new HtmlPartEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
						List<Future> futures2 = new ArrayList<>();
						for(HtmlPart o2 : searchList.getList()) {
							futures2.add(
								service.patchHtmlPartFuture(o2, false, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("HtmlPart %s refreshed. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("HtmlPart %s failed. ", o2.getPk()));
										eventHandler.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Refresh HtmlPart succeeded. ");
								eventHandler.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Refresh relations failed. ", b.cause());
								errorHtmlPart(siteRequest2, eventHandler, b);
							}
						});
					} else {
						LOGGER.error("Refresh relations failed. ", a.cause());
						errorHtmlPart(siteRequest2, eventHandler, a);
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
