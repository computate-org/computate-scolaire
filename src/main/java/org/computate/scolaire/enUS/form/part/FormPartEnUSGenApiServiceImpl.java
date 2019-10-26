package org.computate.scolaire.enUS.form.part;

import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import org.computate.scolaire.enUS.search.SearchResult;
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
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.writer.AllWriter;


/**
 * Translate: false
 **/
public class FormPartEnUSGenApiServiceImpl implements FormPartEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(FormPartEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "FormPartEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public FormPartEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
		FormPartEnUSGenApiService service = FormPartEnUSGenApiService.createProxy(siteContext.getVertx(), SERVICE_ADDRESS);
	}

	// POST //

	@Override
	public void postFormPart(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForFormPart(siteContext, operationRequest, body);
			sqlFormPart(siteRequest, a -> {
				if(a.succeeded()) {
					createPOSTFormPart(siteRequest, b -> {
						if(b.succeeded()) {
							FormPart formPart = b.result();
							sqlPOSTFormPart(formPart, c -> {
								if(c.succeeded()) {
									defineFormPart(formPart, d -> {
										if(d.succeeded()) {
											attributeFormPart(formPart, e -> {
												if(e.succeeded()) {
													indexFormPart(formPart, f -> {
														if(f.succeeded()) {
															response200POSTFormPart(formPart, g -> {
																if(f.succeeded()) {
																	SQLConnection sqlConnection = siteRequest.getSqlConnection();
																	sqlConnection.commit(h -> {
																		if(a.succeeded()) {
																			sqlConnection.close(i -> {
																				if(a.succeeded()) {
																					eventHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					errorFormPart(siteRequest, eventHandler, i);
																				}
																			});
																		} else {
																			errorFormPart(siteRequest, eventHandler, h);
																		}
																	});
																} else {
																	errorFormPart(siteRequest, eventHandler, g);
																}
															});
														} else {
															errorFormPart(siteRequest, eventHandler, f);
														}
													});
												} else {
													errorFormPart(siteRequest, eventHandler, e);
												}
											});
										} else {
											errorFormPart(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorFormPart(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorFormPart(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorFormPart(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorFormPart(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void createPOSTFormPart(SiteRequestEnUS siteRequest, Handler<AsyncResult<FormPart>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(FormPart.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				FormPart o = new FormPart();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlPOSTFormPart(FormPart o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "htmlOrder":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlOrder", jsonObject.getString(entityVar), pk));
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
					case "htmlText":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlText", jsonObject.getString(entityVar), pk));
						break;
					case "htmlAfter":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("htmlAfter", jsonObject.getString(entityVar), pk));
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

	public void response200POSTFormPart(FormPart o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(o);
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchFormPart(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForFormPart(siteContext, operationRequest, body);
			sqlFormPart(siteRequest, a -> {
				if(a.succeeded()) {
					userFormPart(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchFormPart(siteRequest, false, true, null, c -> {
								if(c.succeeded()) {
									SearchList<FormPart> listFormPart = c.result();
									SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listFormPart.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
									Date date = null;
									if(facets != null)
										date = (Date)facets.get("max_modified");
									String dt;
									if(date == null)
										dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
									else
										dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
									listFormPart.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));
									listPATCHFormPart(listFormPart, dt, d -> {
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
																errorFormPart(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											errorFormPart(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorFormPart(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorFormPart(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorFormPart(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorFormPart(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void listPATCHFormPart(SearchList<FormPart> listFormPart, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listFormPart.getSiteRequest_();
		listFormPart.getList().forEach(o -> {
			futures.add(
				futurePATCHFormPart(o, a -> {
					if(a.succeeded()) {
					} else {
						errorFormPart(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listFormPart.next(dt)) {
					listPATCHFormPart(listFormPart, dt, eventHandler);
				} else {
					response200PATCHFormPart(listFormPart, eventHandler);
				}
			} else {
				errorFormPart(listFormPart.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<FormPart> futurePATCHFormPart(FormPart o,  Handler<AsyncResult<OperationResponse>> eventHandler) {
		Future<FormPart> future = Future.future();
		try {
			sqlPATCHFormPart(o, a -> {
				if(a.succeeded()) {
					FormPart formPart = a.result();
					defineFormPart(formPart, b -> {
						if(b.succeeded()) {
							attributeFormPart(formPart, c -> {
								if(c.succeeded()) {
									indexFormPart(formPart, d -> {
										if(d.succeeded()) {
											future.complete(o);
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
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public void sqlPATCHFormPart(FormPart o, Handler<AsyncResult<FormPart>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject requestJson = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = requestJson.fieldNames();
			FormPart o2 = new FormPart();

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.form.part.FormPart"));
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
					case "setHtmlOrder":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "htmlOrder"));
						} else {
							o2.setHtmlOrder(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("htmlOrder", o2.jsonHtmlOrder(), pk));
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
				}
			}
			sqlConnection.queryWithParams(
					patchSql.toString()
					, new JsonArray(patchSqlParams)
					, patchAsync
			-> {
				if(patchAsync.succeeded()) {
					FormPart o3 = new FormPart();
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

	public void response200PATCHFormPart(SearchList<FormPart> listFormPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listFormPart.getSiteRequest_();
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getFormPart(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForFormPart(siteContext, operationRequest);
			aSearchFormPart(siteRequest, false, true, null, a -> {
				if(a.succeeded()) {
					SearchList<FormPart> listFormPart = a.result();
					response200GETFormPart(listFormPart, b -> {
						if(b.succeeded()) {
							eventHandler.handle(Future.succeededFuture(b.result()));
						} else {
							errorFormPart(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorFormPart(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorFormPart(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200GETFormPart(SearchList<FormPart> listFormPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listFormPart.getSiteRequest_();
			SolrDocumentList solrDocuments = listFormPart.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listFormPart.get(0));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteFormPart(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForFormPart(siteContext, operationRequest);
			sqlFormPart(siteRequest, a -> {
				if(a.succeeded()) {
					aSearchFormPart(siteRequest, false, true, null, b -> {
						if(b.succeeded()) {
							SearchList<FormPart> listFormPart = b.result();
							deleteDELETEFormPart(siteRequest, c -> {
								if(c.succeeded()) {
									response200DELETEFormPart(siteRequest, d -> {
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
																errorFormPart(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											errorFormPart(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorFormPart(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorFormPart(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorFormPart(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorFormPart(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void deleteDELETEFormPart(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			Long pk = siteRequest.getRequestPk();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_delete
					, new JsonArray(Arrays.asList(pk, FormPart.class.getCanonicalName(), pk, pk, pk, pk))
					, deleteAsync
			-> {
				eventHandler.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void response200DELETEFormPart(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchFormPart(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForFormPart(siteContext, operationRequest);
			aSearchFormPart(siteRequest, false, true, null, a -> {
				if(a.succeeded()) {
					SearchList<FormPart> listFormPart = a.result();
					response200SearchFormPart(listFormPart, b -> {
						if(b.succeeded()) {
							eventHandler.handle(Future.succeededFuture(b.result()));
						} else {
							errorFormPart(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorFormPart(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorFormPart(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200SearchFormPart(SearchList<FormPart> listFormPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listFormPart.getSiteRequest_();
			QueryResponse responseSearch = listFormPart.getQueryResponse();
			SolrDocumentList solrDocuments = listFormPart.getSolrDocumentList();
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
			listFormPart.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listFormPart.getFields();
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
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// SearchPage //

	@Override
	public void searchpageFormPartId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageFormPart(operationRequest, eventHandler);
	}

	@Override
	public void searchpageFormPart(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForFormPart(siteContext, operationRequest);
			sqlFormPart(siteRequest, a -> {
				if(a.succeeded()) {
					userFormPart(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchFormPart(siteRequest, false, true, "/form-part", c -> {
								if(c.succeeded()) {
									SearchList<FormPart> listFormPart = c.result();
									response200SearchPageFormPart(listFormPart, d -> {
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
																errorFormPart(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											errorFormPart(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorFormPart(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorFormPart(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorFormPart(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorFormPart(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200SearchPageFormPart(SearchList<FormPart> listFormPart, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listFormPart.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listFormPart.getSiteRequest_(), buffer);
			FormPartPage page = new FormPartPage();
			SolrDocument pageSolrDocument = new SolrDocument();

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/form-part");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			page.setListFormPart(listFormPart);
			page.initDeepFormPartPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, new CaseInsensitiveHeaders())));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public String varIndexedFormPart(String entityVar) {
		switch(entityVar) {
			case "pk":
				return "pk_indexed_long";
			case "id":
				return "id_indexed_string";
			case "created":
				return "created_indexed_date";
			case "modified":
				return "modified_indexed_date";
			case "archived":
				return "archived_indexed_boolean";
			case "deleted":
				return "deleted_indexed_boolean";
			case "classCanonicalName":
				return "classCanonicalName_indexed_string";
			case "classSimpleName":
				return "classSimpleName_indexed_string";
			case "classCanonicalNames":
				return "classCanonicalNames_indexed_strings";
			case "objectTitle":
				return "objectTitle_indexed_string";
			case "objectId":
				return "objectId_indexed_string";
			case "objectSuggest":
				return "objectSuggest_indexed_string";
			case "pageUrl":
				return "pageUrl_indexed_string";
			case "formPartKey":
				return "formPartKey_indexed_long";
			case "enrollmentFormKey":
				return "enrollmentFormKey_indexed_long";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	public String varSearchFormPart(String entityVar) {
		switch(entityVar) {
			case "objectSuggest":
				return "objectSuggest_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	public String varSuggereFormPart(String entityVar) {
		switch(entityVar) {
			case "objectSuggest":
				return "objectSuggest_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	// Partagé //

	public void errorFormPart(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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

	public void sqlFormPart(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public SiteRequestEnUS generateSiteRequestEnUSForFormPart(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForFormPart(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForFormPart(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userFormPart(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
							siteUser.setPk(pkUser);

							sqlConnection.queryWithParams(
									SiteContextEnUS.SQL_define
									, new JsonArray(Arrays.asList(pkUser, pkUser, pkUser))
									, defineAsync
							-> {
								if(defineAsync.succeeded()) {
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

	public void aSearchFormPart(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String classApiUriMethod, Handler<AsyncResult<SearchList<FormPart>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<FormPart> listSearch = new SearchList<FormPart>();
			listSearch.setPopulate(populate);
			listSearch.setStore(store);
			listSearch.setQuery("*:*");
			listSearch.setC(FormPart.class);
			if(entityList != null)
				listSearch.addFields(entityList);
			listSearch.addSort("created_indexed_date", ORDER.desc);
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
								varIndexed = "*".equals(entityVar) ? entityVar : varSearchFormPart(entityVar);
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
								varIndexed = varIndexedFormPart(entityVar);
								listSearch.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueSort = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = varIndexedFormPart(entityVar);
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
			listSearch.initDeepForClass(siteRequest);
			eventHandler.handle(Future.succeededFuture(listSearch));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void defineFormPart(FormPart o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
							o.defineForClass(definition.getString(0), definition.getString(1));
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

	public void attributeFormPart(FormPart o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void indexFormPart(FormPart o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
