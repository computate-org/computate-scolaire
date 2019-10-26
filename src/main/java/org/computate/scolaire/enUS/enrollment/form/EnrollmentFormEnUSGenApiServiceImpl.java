package org.computate.scolaire.enUS.enrollment.form;

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
public class EnrollmentFormEnUSGenApiServiceImpl implements EnrollmentFormEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentFormEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "EnrollmentFormEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public EnrollmentFormEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
		EnrollmentFormEnUSGenApiService service = EnrollmentFormEnUSGenApiService.createProxy(siteContext.getVertx(), SERVICE_ADDRESS);
	}

	// POST //

	@Override
	public void postEnrollmentForm(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentForm(siteContext, operationRequest, body);
			sqlEnrollmentForm(siteRequest, a -> {
				if(a.succeeded()) {
					createPOSTEnrollmentForm(siteRequest, b -> {
						if(b.succeeded()) {
							EnrollmentForm enrollmentForm = b.result();
							sqlPOSTEnrollmentForm(enrollmentForm, c -> {
								if(c.succeeded()) {
									defineEnrollmentForm(enrollmentForm, d -> {
										if(d.succeeded()) {
											attributeEnrollmentForm(enrollmentForm, e -> {
												if(e.succeeded()) {
													indexEnrollmentForm(enrollmentForm, f -> {
														if(f.succeeded()) {
															response200POSTEnrollmentForm(enrollmentForm, g -> {
																if(f.succeeded()) {
																	SQLConnection sqlConnection = siteRequest.getSqlConnection();
																	sqlConnection.commit(h -> {
																		if(a.succeeded()) {
																			sqlConnection.close(i -> {
																				if(a.succeeded()) {
																					eventHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					errorEnrollmentForm(siteRequest, eventHandler, i);
																				}
																			});
																		} else {
																			errorEnrollmentForm(siteRequest, eventHandler, h);
																		}
																	});
																} else {
																	errorEnrollmentForm(siteRequest, eventHandler, g);
																}
															});
														} else {
															errorEnrollmentForm(siteRequest, eventHandler, f);
														}
													});
												} else {
													errorEnrollmentForm(siteRequest, eventHandler, e);
												}
											});
										} else {
											errorEnrollmentForm(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorEnrollmentForm(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorEnrollmentForm(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorEnrollmentForm(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorEnrollmentForm(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void createPOSTEnrollmentForm(SiteRequestEnUS siteRequest, Handler<AsyncResult<EnrollmentForm>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(EnrollmentForm.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				EnrollmentForm o = new EnrollmentForm();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlPOSTEnrollmentForm(EnrollmentForm o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "formPartKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							postSql.append(SiteContextEnUS.SQL_addA);
							postSqlParams.addAll(Arrays.asList("enrollmentFormKey", l, "formPartKeys", pk));
						}
						break;
					case "enrollmentKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							postSql.append(SiteContextEnUS.SQL_addA);
							postSqlParams.addAll(Arrays.asList("blockKeys", l, "enrollmentKeys", pk));
						}
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

	public void response200POSTEnrollmentForm(EnrollmentForm o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
	public void patchEnrollmentForm(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentForm(siteContext, operationRequest, body);
			sqlEnrollmentForm(siteRequest, a -> {
				if(a.succeeded()) {
					userEnrollmentForm(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchEnrollmentForm(siteRequest, false, true, null, c -> {
								if(c.succeeded()) {
									SearchList<EnrollmentForm> listEnrollmentForm = c.result();
									SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listEnrollmentForm.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
									Date date = null;
									if(facets != null)
										date = (Date)facets.get("max_modified");
									String dt;
									if(date == null)
										dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
									else
										dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
									listEnrollmentForm.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));
									listPATCHEnrollmentForm(listEnrollmentForm, dt, d -> {
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
																errorEnrollmentForm(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											errorEnrollmentForm(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorEnrollmentForm(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorEnrollmentForm(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorEnrollmentForm(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorEnrollmentForm(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void listPATCHEnrollmentForm(SearchList<EnrollmentForm> listEnrollmentForm, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listEnrollmentForm.getSiteRequest_();
		listEnrollmentForm.getList().forEach(o -> {
			futures.add(
				futurePATCHEnrollmentForm(o, a -> {
					if(a.succeeded()) {
					} else {
						errorEnrollmentForm(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listEnrollmentForm.next(dt)) {
					listPATCHEnrollmentForm(listEnrollmentForm, dt, eventHandler);
				} else {
					response200PATCHEnrollmentForm(listEnrollmentForm, eventHandler);
				}
			} else {
				errorEnrollmentForm(listEnrollmentForm.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<EnrollmentForm> futurePATCHEnrollmentForm(EnrollmentForm o,  Handler<AsyncResult<OperationResponse>> eventHandler) {
		Future<EnrollmentForm> future = Future.future();
		try {
			sqlPATCHEnrollmentForm(o, a -> {
				if(a.succeeded()) {
					EnrollmentForm enrollmentForm = a.result();
					defineEnrollmentForm(enrollmentForm, b -> {
						if(b.succeeded()) {
							attributeEnrollmentForm(enrollmentForm, c -> {
								if(c.succeeded()) {
									indexEnrollmentForm(enrollmentForm, d -> {
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

	public void sqlPATCHEnrollmentForm(EnrollmentForm o, Handler<AsyncResult<EnrollmentForm>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject requestJson = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = requestJson.fieldNames();
			EnrollmentForm o2 = new EnrollmentForm();

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.enrollment.form.EnrollmentForm"));
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
					case "addFormPartKeys":
						patchSql.append(SiteContextEnUS.SQL_addA);
						patchSqlParams.addAll(Arrays.asList("enrollmentFormKey", Long.parseLong(requestJson.getString(methodName)), "formPartKeys", pk));
						break;
					case "addAllFormPartKeys":
						JsonArray addAllFormPartKeysValues = requestJson.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllFormPartKeysValues.size(); i++) {
							patchSql.append(SiteContextEnUS.SQL_setA2);
							patchSqlParams.addAll(Arrays.asList("enrollmentFormKey", addAllFormPartKeysValues.getString(i), "formPartKeys", pk));
						}
						break;
					case "setFormPartKeys":
						JsonArray setFormPartKeysValues = requestJson.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("enrollmentFormKey", Long.parseLong(requestJson.getString(methodName)), "formPartKeys", pk));
						for(Integer i = 0; i <  setFormPartKeysValues.size(); i++) {
							patchSql.append(SiteContextEnUS.SQL_setA2);
							patchSqlParams.addAll(Arrays.asList("enrollmentFormKey", setFormPartKeysValues.getString(i), "formPartKeys", pk));
						}
						break;
					case "removeFormPartKeys":
						patchSql.append(SiteContextEnUS.SQL_removeA);
						patchSqlParams.addAll(Arrays.asList("enrollmentFormKey", Long.parseLong(requestJson.getString(methodName)), "formPartKeys", pk));
						break;
					case "addEnrollmentKeys":
						patchSql.append(SiteContextEnUS.SQL_addA);
						patchSqlParams.addAll(Arrays.asList("blockKeys", Long.parseLong(requestJson.getString(methodName)), "enrollmentKeys", pk));
						break;
					case "addAllEnrollmentKeys":
						JsonArray addAllEnrollmentKeysValues = requestJson.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllEnrollmentKeysValues.size(); i++) {
							patchSql.append(SiteContextEnUS.SQL_setA2);
							patchSqlParams.addAll(Arrays.asList("blockKeys", addAllEnrollmentKeysValues.getString(i), "enrollmentKeys", pk));
						}
						break;
					case "setEnrollmentKeys":
						JsonArray setEnrollmentKeysValues = requestJson.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("blockKeys", Long.parseLong(requestJson.getString(methodName)), "enrollmentKeys", pk));
						for(Integer i = 0; i <  setEnrollmentKeysValues.size(); i++) {
							patchSql.append(SiteContextEnUS.SQL_setA2);
							patchSqlParams.addAll(Arrays.asList("blockKeys", setEnrollmentKeysValues.getString(i), "enrollmentKeys", pk));
						}
						break;
					case "removeEnrollmentKeys":
						patchSql.append(SiteContextEnUS.SQL_removeA);
						patchSqlParams.addAll(Arrays.asList("blockKeys", Long.parseLong(requestJson.getString(methodName)), "enrollmentKeys", pk));
						break;
				}
			}
			sqlConnection.queryWithParams(
					patchSql.toString()
					, new JsonArray(patchSqlParams)
					, patchAsync
			-> {
				if(patchAsync.succeeded()) {
					EnrollmentForm o3 = new EnrollmentForm();
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

	public void response200PATCHEnrollmentForm(SearchList<EnrollmentForm> listEnrollmentForm, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listEnrollmentForm.getSiteRequest_();
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getEnrollmentForm(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentForm(siteContext, operationRequest);
			aSearchEnrollmentForm(siteRequest, false, true, null, a -> {
				if(a.succeeded()) {
					SearchList<EnrollmentForm> listEnrollmentForm = a.result();
					response200GETEnrollmentForm(listEnrollmentForm, b -> {
						if(b.succeeded()) {
							eventHandler.handle(Future.succeededFuture(b.result()));
						} else {
							errorEnrollmentForm(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorEnrollmentForm(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorEnrollmentForm(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200GETEnrollmentForm(SearchList<EnrollmentForm> listEnrollmentForm, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listEnrollmentForm.getSiteRequest_();
			SolrDocumentList solrDocuments = listEnrollmentForm.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listEnrollmentForm.get(0));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteEnrollmentForm(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentForm(siteContext, operationRequest);
			sqlEnrollmentForm(siteRequest, a -> {
				if(a.succeeded()) {
					aSearchEnrollmentForm(siteRequest, false, true, null, b -> {
						if(b.succeeded()) {
							SearchList<EnrollmentForm> listEnrollmentForm = b.result();
							deleteDELETEEnrollmentForm(siteRequest, c -> {
								if(c.succeeded()) {
									response200DELETEEnrollmentForm(siteRequest, d -> {
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
																errorEnrollmentForm(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											errorEnrollmentForm(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorEnrollmentForm(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorEnrollmentForm(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorEnrollmentForm(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorEnrollmentForm(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void deleteDELETEEnrollmentForm(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			Long pk = siteRequest.getRequestPk();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_delete
					, new JsonArray(Arrays.asList(pk, EnrollmentForm.class.getCanonicalName(), pk, pk, pk, pk))
					, deleteAsync
			-> {
				eventHandler.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void response200DELETEEnrollmentForm(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchEnrollmentForm(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentForm(siteContext, operationRequest);
			aSearchEnrollmentForm(siteRequest, false, true, null, a -> {
				if(a.succeeded()) {
					SearchList<EnrollmentForm> listEnrollmentForm = a.result();
					response200SearchEnrollmentForm(listEnrollmentForm, b -> {
						if(b.succeeded()) {
							eventHandler.handle(Future.succeededFuture(b.result()));
						} else {
							errorEnrollmentForm(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorEnrollmentForm(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorEnrollmentForm(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200SearchEnrollmentForm(SearchList<EnrollmentForm> listEnrollmentForm, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listEnrollmentForm.getSiteRequest_();
			QueryResponse responseSearch = listEnrollmentForm.getQueryResponse();
			SolrDocumentList solrDocuments = listEnrollmentForm.getSolrDocumentList();
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
			listEnrollmentForm.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listEnrollmentForm.getFields();
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
	public void searchpageEnrollmentFormId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageEnrollmentForm(operationRequest, eventHandler);
	}

	@Override
	public void searchpageEnrollmentForm(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentForm(siteContext, operationRequest);
			sqlEnrollmentForm(siteRequest, a -> {
				if(a.succeeded()) {
					userEnrollmentForm(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchEnrollmentForm(siteRequest, false, true, "/enrollment-form", c -> {
								if(c.succeeded()) {
									SearchList<EnrollmentForm> listEnrollmentForm = c.result();
									response200SearchPageEnrollmentForm(listEnrollmentForm, d -> {
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
																errorEnrollmentForm(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											errorEnrollmentForm(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorEnrollmentForm(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorEnrollmentForm(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorEnrollmentForm(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorEnrollmentForm(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200SearchPageEnrollmentForm(SearchList<EnrollmentForm> listEnrollmentForm, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listEnrollmentForm.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listEnrollmentForm.getSiteRequest_(), buffer);
			EnrollmentFormPage page = new EnrollmentFormPage();
			SolrDocument pageSolrDocument = new SolrDocument();

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/enrollment-form");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			page.setListEnrollmentForm(listEnrollmentForm);
			page.initDeepEnrollmentFormPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, new CaseInsensitiveHeaders())));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public String varIndexedEnrollmentForm(String entityVar) {
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
			case "enrollmentFormKey":
				return "enrollmentFormKey_indexed_long";
			case "formPartKeys":
				return "formPartKeys_indexed_longs";
			case "enrollmentKeys":
				return "enrollmentKeys_indexed_longs";
			case "schoolKey":
				return "schoolKey_indexed_long";
			case "yearKey":
				return "yearKey_indexed_long";
			case "schoolCompleteName":
				return "schoolCompleteName_indexed_string";
			case "schoolLocation":
				return "schoolLocation_indexed_string";
			case "yearStart":
				return "yearStart_indexed_int";
			case "yearEnd":
				return "yearEnd_indexed_int";
			case "yearShortName":
				return "yearShortName_indexed_string";
			case "yearCompleteName":
				return "yearCompleteName_indexed_string";
			case "enrollmentFormCompleteName":
				return "enrollmentFormCompleteName_indexed_string";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	public String varSearchEnrollmentForm(String entityVar) {
		switch(entityVar) {
			case "objectSuggest":
				return "objectSuggest_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	public String varSuggereEnrollmentForm(String entityVar) {
		switch(entityVar) {
			case "objectSuggest":
				return "objectSuggest_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	// Partagé //

	public void errorEnrollmentForm(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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

	public void sqlEnrollmentForm(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public SiteRequestEnUS generateSiteRequestEnUSForEnrollmentForm(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForEnrollmentForm(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForEnrollmentForm(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userEnrollmentForm(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void aSearchEnrollmentForm(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String classApiUriMethod, Handler<AsyncResult<SearchList<EnrollmentForm>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<EnrollmentForm> listSearch = new SearchList<EnrollmentForm>();
			listSearch.setPopulate(populate);
			listSearch.setStore(store);
			listSearch.setQuery("*:*");
			listSearch.setC(EnrollmentForm.class);
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
								varIndexed = "*".equals(entityVar) ? entityVar : varSearchEnrollmentForm(entityVar);
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
								varIndexed = varIndexedEnrollmentForm(entityVar);
								listSearch.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueSort = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = varIndexedEnrollmentForm(entityVar);
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

	public void defineEnrollmentForm(EnrollmentForm o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void attributeEnrollmentForm(EnrollmentForm o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void indexEnrollmentForm(EnrollmentForm o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
