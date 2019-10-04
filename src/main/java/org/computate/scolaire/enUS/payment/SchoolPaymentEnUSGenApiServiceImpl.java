package org.computate.scolaire.enUS.payment;

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
public class SchoolPaymentEnUSGenApiServiceImpl implements SchoolPaymentEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolPaymentEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SchoolPaymentEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public SchoolPaymentEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
		SchoolPaymentEnUSGenApiService service = SchoolPaymentEnUSGenApiService.createProxy(siteContext.getVertx(), SERVICE_ADDRESS);
	}

	// POST //

	@Override
	public void postSchoolPayment(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolPayment(siteContext, operationRequest, body);
			sqlSchoolPayment(siteRequest, a -> {
				if(a.succeeded()) {
					createPOSTSchoolPayment(siteRequest, b -> {
						if(b.succeeded()) {
							SchoolPayment schoolPayment = b.result();
							sqlPOSTSchoolPayment(schoolPayment, c -> {
								if(c.succeeded()) {
									defineSchoolPayment(schoolPayment, d -> {
										if(d.succeeded()) {
											attributeSchoolPayment(schoolPayment, e -> {
												if(e.succeeded()) {
													indexSchoolPayment(schoolPayment, f -> {
														if(f.succeeded()) {
															response200POSTSchoolPayment(schoolPayment, g -> {
																if(f.succeeded()) {
																	SQLConnection sqlConnection = siteRequest.getSqlConnection();
																	sqlConnection.commit(h -> {
																		if(a.succeeded()) {
																			sqlConnection.close(i -> {
																				if(a.succeeded()) {
																					eventHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					errorSchoolPayment(siteRequest, eventHandler, i);
																				}
																			});
																		} else {
																			errorSchoolPayment(siteRequest, eventHandler, h);
																		}
																	});
																} else {
																	errorSchoolPayment(siteRequest, eventHandler, g);
																}
															});
														} else {
															errorSchoolPayment(siteRequest, eventHandler, f);
														}
													});
												} else {
													errorSchoolPayment(siteRequest, eventHandler, e);
												}
											});
										} else {
											errorSchoolPayment(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolPayment(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolPayment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolPayment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolPayment(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void createPOSTSchoolPayment(SiteRequestEnUS siteRequest, Handler<AsyncResult<SchoolPayment>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(SchoolPayment.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				SchoolPayment o = new SchoolPayment();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlPOSTSchoolPayment(SchoolPayment o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "enrollmentKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							postSql.append(SiteContextEnUS.SQL_addA);
							postSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "paymentKeys", l));
						}
						break;
					case "paymentDescription":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("paymentDescription", jsonObject.getString(entityVar), pk));
						break;
					case "paymentDate":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("paymentDate", jsonObject.getString(entityVar), pk));
						break;
					case "paymentAmount":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("paymentAmount", jsonObject.getString(entityVar), pk));
						break;
					case "paymentCash":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("paymentCash", jsonObject.getBoolean(entityVar), pk));
						break;
					case "paymentCheck":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("paymentCheck", jsonObject.getBoolean(entityVar), pk));
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

	public void response200POSTSchoolPayment(SchoolPayment o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
	public void patchSchoolPayment(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolPayment(siteContext, operationRequest, body);
			sqlSchoolPayment(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolPayment(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolPayment(siteRequest, false, true, null, c -> {
								if(c.succeeded()) {
									SearchList<SchoolPayment> listSchoolPayment = c.result();
									SimpleOrderedMap facets = (SimpleOrderedMap)listSchoolPayment.getQueryResponse().getResponse().get("facets");
									Date date = (Date)facets.get("max_modified");
									String dateStr;
									if(date == null)
										dateStr = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
									else
										dateStr = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
									listPATCHSchoolPayment(listSchoolPayment, dateStr, d -> {
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
																errorSchoolPayment(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											errorSchoolPayment(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolPayment(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolPayment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolPayment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolPayment(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void listPATCHSchoolPayment(SearchList<SchoolPayment> listSchoolPayment, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolPayment.getSiteRequest_();
		listSchoolPayment.getList().forEach(o -> {
			futures.add(
				futurePATCHSchoolPayment(o, a -> {
					if(a.succeeded()) {
					} else {
						errorSchoolPayment(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listSchoolPayment.next(dt)) {
					listPATCHSchoolPayment(listSchoolPayment, dt, eventHandler);
				} else {
					response200PATCHSchoolPayment(listSchoolPayment, eventHandler);
				}
			} else {
				errorSchoolPayment(listSchoolPayment.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolPayment> futurePATCHSchoolPayment(SchoolPayment o,  Handler<AsyncResult<OperationResponse>> eventHandler) {
		Future<SchoolPayment> future = Future.future();
		try {
			sqlPATCHSchoolPayment(o, a -> {
				if(a.succeeded()) {
					SchoolPayment schoolPayment = a.result();
					defineSchoolPayment(schoolPayment, b -> {
						if(b.succeeded()) {
							attributeSchoolPayment(schoolPayment, c -> {
								if(c.succeeded()) {
									indexSchoolPayment(schoolPayment, d -> {
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

	public void sqlPATCHSchoolPayment(SchoolPayment o, Handler<AsyncResult<SchoolPayment>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject requestJson = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = requestJson.fieldNames();
			SchoolPayment o2 = new SchoolPayment();

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.payment.SchoolPayment"));
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
					case "addEnrollmentKeys":
						patchSql.append(SiteContextEnUS.SQL_addA);
						patchSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "paymentKeys", Long.parseLong(requestJson.getString(methodName))));
						break;
					case "addAllEnrollmentKeys":
						JsonArray addAllEnrollmentKeysValues = requestJson.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllEnrollmentKeysValues.size(); i++) {
							patchSql.append(SiteContextEnUS.SQL_addA);
							patchSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "paymentKeys", addAllEnrollmentKeysValues.getString(i)));
						}
						break;
					case "setEnrollmentKeys":
						JsonArray setEnrollmentKeysValues = requestJson.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "paymentKeys"));
						for(Integer i = 0; i <  setEnrollmentKeysValues.size(); i++) {
							patchSql.append(SiteContextEnUS.SQL_addA);
							patchSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "paymentKeys", setEnrollmentKeysValues.getString(i)));
						}
						break;
					case "removeEnrollmentKeys":
						patchSql.append(SiteContextEnUS.SQL_removeA);
						patchSqlParams.addAll(Arrays.asList("enrollmentKeys", pk, "paymentKeys", Long.parseLong(requestJson.getString(methodName))));
						break;
					case "setPaymentDescription":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "paymentDescription"));
						} else {
							o2.setPaymentDescription(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("paymentDescription", o2.jsonPaymentDescription(), pk));
						}
						break;
					case "setPaymentDate":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "paymentDate"));
						} else {
							o2.setPaymentDate(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("paymentDate", o2.jsonPaymentDate(), pk));
						}
						break;
					case "setPaymentAmount":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "paymentAmount"));
						} else {
							o2.setPaymentAmount(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("paymentAmount", o2.jsonPaymentAmount(), pk));
						}
						break;
					case "setPaymentCash":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "paymentCash"));
						} else {
							o2.setPaymentCash(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("paymentCash", o2.jsonPaymentCash(), pk));
						}
						break;
					case "setPaymentCheck":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "paymentCheck"));
						} else {
							o2.setPaymentCheck(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("paymentCheck", o2.jsonPaymentCheck(), pk));
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
					SchoolPayment o3 = new SchoolPayment();
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

	public void response200PATCHSchoolPayment(SearchList<SchoolPayment> listSchoolPayment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolPayment.getSiteRequest_();
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSchoolPayment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolPayment(siteContext, operationRequest);
			aSearchSchoolPayment(siteRequest, false, true, null, a -> {
				if(a.succeeded()) {
					SearchList<SchoolPayment> listSchoolPayment = a.result();
					response200GETSchoolPayment(listSchoolPayment, b -> {
						if(b.succeeded()) {
							eventHandler.handle(Future.succeededFuture(b.result()));
						} else {
							errorSchoolPayment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolPayment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolPayment(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200GETSchoolPayment(SearchList<SchoolPayment> listSchoolPayment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolPayment.getSiteRequest_();
			SolrDocumentList solrDocuments = listSchoolPayment.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listSchoolPayment.get(0));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteSchoolPayment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolPayment(siteContext, operationRequest);
			sqlSchoolPayment(siteRequest, a -> {
				if(a.succeeded()) {
					aSearchSchoolPayment(siteRequest, false, true, null, b -> {
						if(b.succeeded()) {
							SearchList<SchoolPayment> listSchoolPayment = b.result();
							deleteDELETESchoolPayment(siteRequest, c -> {
								if(c.succeeded()) {
									response200DELETESchoolPayment(siteRequest, d -> {
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
																errorSchoolPayment(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											errorSchoolPayment(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolPayment(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolPayment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolPayment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolPayment(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void deleteDELETESchoolPayment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			Long pk = siteRequest.getRequestPk();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_delete
					, new JsonArray(Arrays.asList(pk, SchoolPayment.class.getCanonicalName(), pk, pk, pk, pk))
					, deleteAsync
			-> {
				eventHandler.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void response200DELETESchoolPayment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchSchoolPayment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolPayment(siteContext, operationRequest);
			aSearchSchoolPayment(siteRequest, false, true, null, a -> {
				if(a.succeeded()) {
					SearchList<SchoolPayment> listSchoolPayment = a.result();
					response200SearchSchoolPayment(listSchoolPayment, b -> {
						if(b.succeeded()) {
							eventHandler.handle(Future.succeededFuture(b.result()));
						} else {
							errorSchoolPayment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolPayment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolPayment(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200SearchSchoolPayment(SearchList<SchoolPayment> listSchoolPayment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolPayment.getSiteRequest_();
			QueryResponse responseSearch = listSchoolPayment.getQueryResponse();
			SolrDocumentList solrDocuments = listSchoolPayment.getSolrDocumentList();
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
			listSchoolPayment.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listSchoolPayment.getFields();
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
	public void searchpageSchoolPaymentId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageSchoolPayment(operationRequest, eventHandler);
	}

	@Override
	public void searchpageSchoolPayment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolPayment(siteContext, operationRequest);
			sqlSchoolPayment(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolPayment(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolPayment(siteRequest, false, true, "/payment", c -> {
								if(c.succeeded()) {
									SearchList<SchoolPayment> listSchoolPayment = c.result();
									response200SearchPageSchoolPayment(listSchoolPayment, d -> {
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
																errorSchoolPayment(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											errorSchoolPayment(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolPayment(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolPayment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolPayment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolPayment(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200SearchPageSchoolPayment(SearchList<SchoolPayment> listSchoolPayment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolPayment.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listSchoolPayment.getSiteRequest_(), buffer);
			PaymentPage page = new PaymentPage();
			SolrDocument pageSolrDocument = new SolrDocument();

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/payment");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			page.setListSchoolPayment(listSchoolPayment);
			page.initDeepPaymentPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, new CaseInsensitiveHeaders())));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public String varIndexedSchoolPayment(String entityVar) {
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
			case "schoolKeys":
				return "schoolKeys_indexed_longs";
			case "seasonKeys":
				return "seasonKeys_indexed_longs";
			case "sessionKeys":
				return "sessionKeys_indexed_longs";
			case "ageKeys":
				return "ageKeys_indexed_longs";
			case "blockKeys":
				return "blockKeys_indexed_longs";
			case "enrollmentKeys":
				return "enrollmentKeys_indexed_longs";
			case "paymentKey":
				return "paymentKey_indexed_long";
			case "childKeys":
				return "childKeys_indexed_longs";
			case "momKeys":
				return "momKeys_indexed_longs";
			case "dadKeys":
				return "dadKeys_indexed_longs";
			case "guardianKeys":
				return "guardianKeys_indexed_longs";
			case "contactKeys":
				return "contactKeys_indexed_longs";
			case "paymentDescription":
				return "paymentDescription_indexed_string";
			case "paymentDate":
				return "paymentDate_indexed_date";
			case "paymentAmount":
				return "paymentAmount_indexed_double";
			case "paymentCash":
				return "paymentCash_indexed_boolean";
			case "paymentCheck":
				return "paymentCheck_indexed_boolean";
			case "paymentSystem":
				return "paymentSystem_indexed_boolean";
			case "paymentCompleteName":
				return "paymentCompleteName_indexed_string";
			case "paymentId":
				return "paymentId_indexed_string";
			case "pageUrl":
				return "pageUrl_indexed_string";
			case "objectSuggest":
				return "objectSuggest_indexed_string";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	public String varSearchSchoolPayment(String entityVar) {
		switch(entityVar) {
			case "objectSuggest":
				return "objectSuggest_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	public String varSuggereSchoolPayment(String entityVar) {
		switch(entityVar) {
			case "objectSuggest":
				return "objectSuggest_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	// Partagé //

	public void errorSchoolPayment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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

	public void sqlSchoolPayment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolPayment(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForSchoolPayment(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolPayment(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userSchoolPayment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void aSearchSchoolPayment(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String classApiUriMethod, Handler<AsyncResult<SearchList<SchoolPayment>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<SchoolPayment> listSearch = new SearchList<SchoolPayment>();
			listSearch.setPopulate(populate);
			listSearch.setStore(store);
			listSearch.setQuery("*:*");
			listSearch.setC(SchoolPayment.class);
			if(entityList != null)
				listSearch.addFields(entityList);
			listSearch.addSort("created_indexed_date", ORDER.desc);
			listSearch.set("json.facet", "{max_modified:'max(modified_indexed_date)'}");

			String id = operationRequest.getParams().getJsonObject("path").getString("id");
			if(id != null) {
				listSearch.addFilterQuery("(id:" + ClientUtils.escapeQueryChars(id) + " OR paymentId_indexed_string:" + ClientUtils.escapeQueryChars(id) + ")");
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
								varIndexed = "*".equals(entityVar) ? entityVar : varSearchSchoolPayment(entityVar);
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
								varIndexed = varIndexedSchoolPayment(entityVar);
								listSearch.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueSort = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = varIndexedSchoolPayment(entityVar);
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

	public void defineSchoolPayment(SchoolPayment o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void attributeSchoolPayment(SchoolPayment o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void indexSchoolPayment(SchoolPayment o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
