package org.computate.scolaire.enUS.child;

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
import org.computate.scolaire.enUS.user.SiteUserEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.writer.AllWriter;


/**
 * Translate: false
 * CanonicalName.frFR: org.computate.scolaire.frFR.enfant.EnfantScolaireFrFRGenApiServiceImpl
 **/
public class SchoolChildEnUSGenApiServiceImpl implements SchoolChildEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolChildEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SchoolChildEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public SchoolChildEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
	}

	// POST //

	@Override
	public void postSchoolChild(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest, body);

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

			sqlSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolChild(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							postSchoolChildFuture(siteRequest, c -> {
								if(c.succeeded()) {
									SchoolChild schoolChild = c.result();
									apiRequestSchoolChild(schoolChild);
									postSchoolChildResponse(schoolChild, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postSchoolChild succeeded. "));
										} else {
											LOGGER.error(String.format("postSchoolChild failed. ", d.cause()));
											errorSchoolChild(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolChild(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolChild(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolChild(null, eventHandler, Future.failedFuture(e));
		}
	}


	public Future<SchoolChild> postSchoolChildFuture(SiteRequestEnUS siteRequest, Handler<AsyncResult<SchoolChild>> eventHandler) {
		Promise<SchoolChild> promise = Promise.promise();
		try {
			createSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolChild schoolChild = a.result();
					sqlPOSTSchoolChild(schoolChild, b -> {
						if(b.succeeded()) {
							defineIndexSchoolChild(schoolChild, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(schoolChild));
									promise.complete(schoolChild);
								} else {
									errorSchoolChild(siteRequest, null, c);
								}
							});
						} else {
							errorSchoolChild(siteRequest, null, b);
						}
					});
				} else {
					errorSchoolChild(siteRequest, null, a);
				}
			});
		} catch(Exception e) {
			errorSchoolChild(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTSchoolChild(SchoolChild o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
							postSqlParams.addAll(Arrays.asList("childKey", l, "enrollmentKeys", pk));
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
					case "personBirthDate":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("personBirthDate", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
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

	public void postSchoolChildResponse(SchoolChild schoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = schoolChild.getSiteRequest_();
		response200POSTSchoolChild(schoolChild, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								ApiRequest apiRequest = apiRequestSchoolChild(schoolChild);
								schoolChild.apiRequestSchoolChild();
								siteRequest.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolChild(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200POSTSchoolChild(SchoolChild o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
	public void putimportSchoolChild(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest, body);

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

			sqlSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolChild(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									aSearchSchoolChild(siteRequest, false, true, null, d -> {
										if(d.succeeded()) {
											SearchList<SchoolChild> listSchoolChild = d.result();
											WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
											workerExecutor.executeBlocking(
												blockingCodeHandler -> {
													sqlSchoolChild(siteRequest, e -> {
														if(e.succeeded()) {
															try {
																listPUTImportSchoolChild(apiRequest, listSchoolChild, f -> {
																	if(f.succeeded()) {
																		putimportSchoolChildResponse(listSchoolChild, g -> {
																			if(g.succeeded()) {
																				eventHandler.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("putimportSchoolChild succeeded. "));
																			} else {
																				LOGGER.error(String.format("putimportSchoolChild failed. ", g.cause()));
																				errorSchoolChild(siteRequest, eventHandler, d);
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
											errorSchoolChild(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolChild(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolChild(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolChild(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPUTImportSchoolChild(ApiRequest apiRequest, SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		jsonArray.forEach(o -> {
			JsonObject jsonObject = (JsonObject)o;
			futures.add(
				putimportSchoolChildFuture(siteRequest, jsonObject, a -> {
					if(a.succeeded()) {
						SchoolChild schoolChild = a.result();
						apiRequestSchoolChild(schoolChild);
					} else {
						errorSchoolChild(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + jsonArray.size());
				response200PUTImportSchoolChild(listSchoolChild, eventHandler);
			} else {
				errorSchoolChild(apiRequest.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolChild> putimportSchoolChildFuture(SiteRequestEnUS siteRequest, JsonObject jsonObject, Handler<AsyncResult<SchoolChild>> eventHandler) {
		Promise<SchoolChild> promise = Promise.promise();
		try {

			createSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolChild schoolChild = a.result();
					sqlPUTImportSchoolChild(schoolChild, jsonObject, b -> {
						if(b.succeeded()) {
							defineSchoolChild(schoolChild, c -> {
								if(c.succeeded()) {
									attributeSchoolChild(schoolChild, d -> {
										if(d.succeeded()) {
											indexSchoolChild(schoolChild, e -> {
												if(e.succeeded()) {
													eventHandler.handle(Future.succeededFuture(schoolChild));
													promise.complete(schoolChild);
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
			errorSchoolChild(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTImportSchoolChild(SchoolChild o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "enrollmentKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
							searchList.setQuery("*:*");
							searchList.setStore(true);
							searchList.setC(SchoolEnrollment.class);
							searchList.addFilterQuery("inheritPk_indexed_long:" + l);
							searchList.initDeepSearchList(siteRequest);
							if(searchList.size() == 1) {
								putSql.append(SiteContextEnUS.SQL_addA);
								putSqlParams.addAll(Arrays.asList("childKey", searchList.get(0).getPk(), "enrollmentKeys", pk));
							}
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
					case "personBirthDate":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personBirthDate", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
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

	public void putimportSchoolChildResponse(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		response200PUTImportSchoolChild(listSchoolChild, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolChild(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PUTImportSchoolChild(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(apiRequest).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTMerge //

	@Override
	public void putmergeSchoolChild(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest, body);

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

			sqlSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolChild(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									aSearchSchoolChild(siteRequest, false, true, null, d -> {
										if(d.succeeded()) {
											SearchList<SchoolChild> listSchoolChild = d.result();
											WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
											workerExecutor.executeBlocking(
												blockingCodeHandler -> {
													sqlSchoolChild(siteRequest, e -> {
														if(e.succeeded()) {
															try {
																listPUTMergeSchoolChild(apiRequest, listSchoolChild, f -> {
																	if(f.succeeded()) {
																		putmergeSchoolChildResponse(listSchoolChild, g -> {
																			if(g.succeeded()) {
																				eventHandler.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("putmergeSchoolChild succeeded. "));
																			} else {
																				LOGGER.error(String.format("putmergeSchoolChild failed. ", g.cause()));
																				errorSchoolChild(siteRequest, eventHandler, d);
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
											errorSchoolChild(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolChild(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolChild(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolChild(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPUTMergeSchoolChild(ApiRequest apiRequest, SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		jsonArray.forEach(o -> {
			JsonObject jsonObject = (JsonObject)o;
			futures.add(
				putmergeSchoolChildFuture(siteRequest, jsonObject, a -> {
					if(a.succeeded()) {
						SchoolChild schoolChild = a.result();
						apiRequestSchoolChild(schoolChild);
					} else {
						errorSchoolChild(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + jsonArray.size());
				response200PUTMergeSchoolChild(listSchoolChild, eventHandler);
			} else {
				errorSchoolChild(apiRequest.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolChild> putmergeSchoolChildFuture(SiteRequestEnUS siteRequest, JsonObject jsonObject, Handler<AsyncResult<SchoolChild>> eventHandler) {
		Promise<SchoolChild> promise = Promise.promise();
		try {

			createSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolChild schoolChild = a.result();
					sqlPUTMergeSchoolChild(schoolChild, jsonObject, b -> {
						if(b.succeeded()) {
							defineSchoolChild(schoolChild, c -> {
								if(c.succeeded()) {
									attributeSchoolChild(schoolChild, d -> {
										if(d.succeeded()) {
											indexSchoolChild(schoolChild, e -> {
												if(e.succeeded()) {
													eventHandler.handle(Future.succeededFuture(schoolChild));
													promise.complete(schoolChild);
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
			errorSchoolChild(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTMergeSchoolChild(SchoolChild o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "enrollmentKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("childKey", l, "enrollmentKeys", pk));
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
					case "personBirthDate":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personBirthDate", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
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

	public void putmergeSchoolChildResponse(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		response200PUTMergeSchoolChild(listSchoolChild, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolChild(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PUTMergeSchoolChild(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(apiRequest).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTCopy //

	@Override
	public void putcopySchoolChild(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest, body);

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

			sqlSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolChild(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									aSearchSchoolChild(siteRequest, false, true, null, d -> {
										if(d.succeeded()) {
											SearchList<SchoolChild> listSchoolChild = d.result();
											WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
											workerExecutor.executeBlocking(
												blockingCodeHandler -> {
													sqlSchoolChild(siteRequest, e -> {
														if(e.succeeded()) {
															try {
																listPUTCopySchoolChild(apiRequest, listSchoolChild, f -> {
																	if(f.succeeded()) {
																		putcopySchoolChildResponse(listSchoolChild, g -> {
																			if(g.succeeded()) {
																				eventHandler.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("putcopySchoolChild succeeded. "));
																			} else {
																				LOGGER.error(String.format("putcopySchoolChild failed. ", g.cause()));
																				errorSchoolChild(siteRequest, eventHandler, d);
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
											errorSchoolChild(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolChild(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolChild(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolChild(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPUTCopySchoolChild(ApiRequest apiRequest, SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		listSchoolChild.getList().forEach(o -> {
			futures.add(
				putcopySchoolChildFuture(siteRequest, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						SchoolChild schoolChild = a.result();
						apiRequestSchoolChild(schoolChild);
					} else {
						errorSchoolChild(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchoolChild.size());
				if(listSchoolChild.next()) {
					siteRequest.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
					listPUTCopySchoolChild(apiRequest, listSchoolChild, eventHandler);
				} else {
					response200PUTCopySchoolChild(listSchoolChild, eventHandler);
				}
			} else {
				errorSchoolChild(listSchoolChild.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolChild> putcopySchoolChildFuture(SiteRequestEnUS siteRequest, JsonObject jsonObject, Handler<AsyncResult<SchoolChild>> eventHandler) {
		Promise<SchoolChild> promise = Promise.promise();
		try {

			jsonObject.put("saves", Optional.ofNullable(jsonObject.getJsonArray("saves")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("saves").add(o.getKey());
			});

			createSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolChild schoolChild = a.result();
					sqlPUTCopySchoolChild(schoolChild, jsonObject, b -> {
						if(b.succeeded()) {
							defineSchoolChild(schoolChild, c -> {
								if(c.succeeded()) {
									attributeSchoolChild(schoolChild, d -> {
										if(d.succeeded()) {
											indexSchoolChild(schoolChild, e -> {
												if(e.succeeded()) {
													eventHandler.handle(Future.succeededFuture(schoolChild));
													promise.complete(schoolChild);
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
			errorSchoolChild(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopySchoolChild(SchoolChild o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "enrollmentKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("childKey", l, "enrollmentKeys", pk));
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
					case "personBirthDate":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("personBirthDate", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
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

	public void putcopySchoolChildResponse(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		response200PUTCopySchoolChild(listSchoolChild, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolChild(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PUTCopySchoolChild(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(apiRequest).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSchoolChild(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest, body);

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

			sqlSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolChild(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									aSearchSchoolChild(siteRequest, false, true, null, d -> {
										if(d.succeeded()) {
											SearchList<SchoolChild> listSchoolChild = d.result();
											SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolChild.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
											Date date = null;
											if(facets != null)
												date = (Date)facets.get("max_modified");
											String dt;
											if(date == null)
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
											else
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
											listSchoolChild.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));

											if(listSchoolChild.size() == 1) {
												SchoolChild o = listSchoolChild.get(0);
												apiRequest.setPk(o.getPk());
												apiRequest.setOriginal(o);
												apiRequestSchoolChild(o);
											o.apiRequestSchoolChild();
											}
											WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
											workerExecutor.executeBlocking(
												blockingCodeHandler -> {
													sqlSchoolChild(siteRequest, e -> {
														if(e.succeeded()) {
															try {
																listPATCHSchoolChild(apiRequest, listSchoolChild, dt, f -> {
																	if(f.succeeded()) {
																		patchSchoolChildResponse(listSchoolChild, g -> {
																			if(g.succeeded()) {
																				eventHandler.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("patchSchoolChild succeeded. "));
																			} else {
																				LOGGER.error(String.format("patchSchoolChild failed. ", g.cause()));
																				errorSchoolChild(siteRequest, eventHandler, d);
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
											errorSchoolChild(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolChild(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolChild(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolChild(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPATCHSchoolChild(ApiRequest apiRequest, SearchList<SchoolChild> listSchoolChild, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		listSchoolChild.getList().forEach(o -> {
			futures.add(
				patchSchoolChildFuture(o, a -> {
					if(a.succeeded()) {
							SchoolChild schoolChild = a.result();
							apiRequestSchoolChild(schoolChild);
					} else {
						errorSchoolChild(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchoolChild.size());
				if(listSchoolChild.next(dt)) {
					siteRequest.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
					listPATCHSchoolChild(apiRequest, listSchoolChild, dt, eventHandler);
				} else {
					response200PATCHSchoolChild(listSchoolChild, eventHandler);
				}
			} else {
				errorSchoolChild(listSchoolChild.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolChild> patchSchoolChildFuture(SchoolChild o, Handler<AsyncResult<SchoolChild>> eventHandler) {
		Promise<SchoolChild> promise = Promise.promise();
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			sqlPATCHSchoolChild(o, a -> {
				if(a.succeeded()) {
					SchoolChild schoolChild = a.result();
					defineSchoolChild(schoolChild, b -> {
						if(b.succeeded()) {
							attributeSchoolChild(schoolChild, c -> {
								if(c.succeeded()) {
									indexSchoolChild(schoolChild, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(schoolChild));
											promise.complete(schoolChild);
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
			errorSchoolChild(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHSchoolChild(SchoolChild o, Handler<AsyncResult<SchoolChild>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject requestJson = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = requestJson.fieldNames();
			SchoolChild o2 = new SchoolChild();

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.child.SchoolChild"));
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
						patchSqlParams.addAll(Arrays.asList("childKey", Long.parseLong(requestJson.getString(methodName)), "enrollmentKeys", pk));
						break;
					case "addAllEnrollmentKeys":
						JsonArray addAllEnrollmentKeysValues = requestJson.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllEnrollmentKeysValues.size(); i++) {
							patchSql.append(SiteContextEnUS.SQL_setA2);
							patchSqlParams.addAll(Arrays.asList("childKey", addAllEnrollmentKeysValues.getString(i), "enrollmentKeys", pk));
						}
						break;
					case "setEnrollmentKeys":
						JsonArray setEnrollmentKeysValues = requestJson.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("childKey", Long.parseLong(requestJson.getString(methodName)), "enrollmentKeys", pk));
						for(Integer i = 0; i <  setEnrollmentKeysValues.size(); i++) {
							patchSql.append(SiteContextEnUS.SQL_setA2);
							patchSqlParams.addAll(Arrays.asList("childKey", setEnrollmentKeysValues.getString(i), "enrollmentKeys", pk));
						}
						break;
					case "removeEnrollmentKeys":
						patchSql.append(SiteContextEnUS.SQL_removeA);
						patchSqlParams.addAll(Arrays.asList("childKey", Long.parseLong(requestJson.getString(methodName)), "enrollmentKeys", pk));
						break;
					case "setPersonFirstName":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personFirstName"));
						} else {
							o2.setPersonFirstName(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personFirstName", o2.jsonPersonFirstName(), pk));
						}
						break;
					case "setPersonFirstNamePreferred":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personFirstNamePreferred"));
						} else {
							o2.setPersonFirstNamePreferred(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personFirstNamePreferred", o2.jsonPersonFirstNamePreferred(), pk));
						}
						break;
					case "setFamilyName":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familyName"));
						} else {
							o2.setFamilyName(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familyName", o2.jsonFamilyName(), pk));
						}
						break;
					case "setPersonBirthDate":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "personBirthDate"));
						} else {
							o2.setPersonBirthDate(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("personBirthDate", o2.jsonPersonBirthDate(), pk));
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
					SchoolChild o3 = new SchoolChild();
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

	public void patchSchoolChildResponse(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		response200PATCHSchoolChild(listSchoolChild, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolChild(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PATCHSchoolChild(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			JsonObject json = JsonObject.mapFrom(apiRequest);
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSchoolChild(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest);
			sqlSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolChild(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolChild(siteRequest, false, true, null, c -> {
								if(c.succeeded()) {
									SearchList<SchoolChild> listSchoolChild = c.result();
									getSchoolChildResponse(listSchoolChild, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getSchoolChild succeeded. "));
										} else {
											LOGGER.error(String.format("getSchoolChild failed. ", d.cause()));
											errorSchoolChild(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolChild(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolChild(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolChild(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void getSchoolChildResponse(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		response200GETSchoolChild(listSchoolChild, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolChild(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200GETSchoolChild(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
			SolrDocumentList solrDocuments = listSchoolChild.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listSchoolChild.getList().stream().findFirst().orElse(null));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchSchoolChild(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest);
			sqlSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolChild(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolChild(siteRequest, false, true, "/api/child", c -> {
								if(c.succeeded()) {
									SearchList<SchoolChild> listSchoolChild = c.result();
									searchSchoolChildResponse(listSchoolChild, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchSchoolChild succeeded. "));
										} else {
											LOGGER.error(String.format("searchSchoolChild failed. ", d.cause()));
											errorSchoolChild(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolChild(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolChild(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolChild(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void searchSchoolChildResponse(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		response200SearchSchoolChild(listSchoolChild, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolChild(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200SearchSchoolChild(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
			QueryResponse responseSearch = listSchoolChild.getQueryResponse();
			SolrDocumentList solrDocuments = listSchoolChild.getSolrDocumentList();
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
			listSchoolChild.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listSchoolChild.getFields();
				if(fls.size() > 0) {
					Set<String> fieldNames = new HashSet<String>();
					fieldNames.addAll(json2.fieldNames());
					if(fls.size() == 1 && fls.get(0).equals("saves")) {
						fls.addAll(json2.getJsonArray("saves").stream().map(s -> s.toString()).collect(Collectors.toList()));
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
	public void searchpageSchoolChildId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageSchoolChild(operationRequest, eventHandler);
	}

	@Override
	public void searchpageSchoolChild(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest);
			sqlSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolChild(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolChild(siteRequest, false, true, "/child", c -> {
								if(c.succeeded()) {
									SearchList<SchoolChild> listSchoolChild = c.result();
									searchpageSchoolChildResponse(listSchoolChild, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchpageSchoolChild succeeded. "));
										} else {
											LOGGER.error(String.format("searchpageSchoolChild failed. ", d.cause()));
											errorSchoolChild(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolChild(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolChild(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolChild(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void searchpageSchoolChildResponse(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		response200SearchPageSchoolChild(listSchoolChild, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorSchoolChild(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200SearchPageSchoolChild(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listSchoolChild.getSiteRequest_(), buffer);
			ChildPage page = new ChildPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/child");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listSchoolChild.size() == 1)
				siteRequest.setRequestPk(listSchoolChild.get(0).getPk());
			siteRequest.setW(w);
			page.setListSchoolChild(listSchoolChild);
			page.setSiteRequest_(siteRequest);
			page.initDeepChildPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<SchoolChild> defineIndexSchoolChild(SchoolChild schoolChild, Handler<AsyncResult<SchoolChild>> eventHandler) {
		Promise<SchoolChild> promise = Promise.promise();
		SiteRequestEnUS siteRequest = schoolChild.getSiteRequest_();
		defineSchoolChild(schoolChild, c -> {
			if(c.succeeded()) {
				attributeSchoolChild(schoolChild, d -> {
					if(d.succeeded()) {
						indexSchoolChild(schoolChild, e -> {
							if(e.succeeded()) {
								eventHandler.handle(Future.succeededFuture(schoolChild));
								promise.complete(schoolChild);
							} else {
								errorSchoolChild(siteRequest, null, e);
							}
						});
					} else {
						errorSchoolChild(siteRequest, null, d);
					}
				});
			} else {
				errorSchoolChild(siteRequest, null, c);
			}
		});
		return promise.future();
	}

	public void createSchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<SchoolChild>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(SchoolChild.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				SchoolChild o = new SchoolChild();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public ApiRequest apiRequestSchoolChild(SchoolChild o) {
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

	public void errorSchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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

	public void sqlSchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolChild(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolChild(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userSchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
								userSchoolChildDefine(siteRequest, jsonObject, false);

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
										userService.sqlPOSTSiteUser(siteUser, c -> {
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
																		errorSchoolChild(siteRequest, eventHandler, f);
																	}
																});
															} else {
																errorSchoolChild(siteRequest, eventHandler, e);
															}
														});
													} else {
														errorSchoolChild(siteRequest, eventHandler, d);
													}
												});
											} else {
												errorSchoolChild(siteRequest, eventHandler, c);
											}
										});
									} else {
										errorSchoolChild(siteRequest, eventHandler, b);
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
								Boolean define = userSchoolChildDefine(siteRequest, jsonObject, true);
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

									userService.sqlPATCHSiteUser(siteUser, c -> {
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
																	errorSchoolChild(siteRequest, eventHandler, f);
																}
															});
														} else {
															errorSchoolChild(siteRequest, eventHandler, e);
														}
													});
												} else {
													errorSchoolChild(siteRequest, eventHandler, d);
												}
											});
										} else {
											errorSchoolChild(siteRequest, eventHandler, c);
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

	public Boolean userSchoolChildDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void aSearchSchoolChildQ(String classApiUriMethod, SearchList<SchoolChild> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
			searchList.setHighlight(true);
			searchList.setHighlightSnippets(3);
			searchList.addHighlightField(varIndexed);
			searchList.setParam("hl.encoder", "html");
		}
	}

	public void aSearchSchoolChildFq(String classApiUriMethod, SearchList<SchoolChild> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
	}

	public void aSearchSchoolChildSort(String classApiUriMethod, SearchList<SchoolChild> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addSort(varIndexed, ORDER.valueOf(valueIndexed));
	}

	public void aSearchSchoolChildRows(String classApiUriMethod, SearchList<SchoolChild> searchList, Integer valueRows) {
		searchList.setRows(valueRows);
	}

	public void aSearchSchoolChildStart(String classApiUriMethod, SearchList<SchoolChild> searchList, Integer valueStart) {
		searchList.setStart(valueStart);
	}

	public void aSearchSchoolChildVar(String classApiUriMethod, SearchList<SchoolChild> searchList, String var, String value) {
		searchList.getSiteRequest_().getRequestVars().put(var, value);
	}

	public void aSearchSchoolChild(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String classApiUriMethod, Handler<AsyncResult<SearchList<SchoolChild>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<SchoolChild> searchList = new SearchList<SchoolChild>();
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.setQuery("*:*");
			searchList.setC(SchoolChild.class);
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
								varIndexed = "*".equals(entityVar) ? entityVar : SchoolChild.varSearchSchoolChild(entityVar);
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								valueIndexed = StringUtils.isEmpty(valueIndexed) ? "*" : valueIndexed;
								aSearchSchoolChildQ(classApiUriMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "fq":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = SchoolChild.varIndexedSchoolChild(entityVar);
								aSearchSchoolChildFq(classApiUriMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = SchoolChild.varIndexedSchoolChild(entityVar);
								aSearchSchoolChildSort(classApiUriMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "start":
								valueStart = (Integer)paramObject;
								aSearchSchoolChildStart(classApiUriMethod, searchList, valueStart);
								break;
							case "rows":
								valueRows = (Integer)paramObject;
								aSearchSchoolChildRows(classApiUriMethod, searchList, valueRows);
								break;
							case "var":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								aSearchSchoolChildVar(classApiUriMethod, searchList, entityVar, valueIndexed);
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

	public void defineSchoolChild(SchoolChild o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void attributeSchoolChild(SchoolChild o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void indexSchoolChild(SchoolChild o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			if(BooleanUtils.isFalse(Optional.ofNullable(siteRequest.getApiRequest_()).map(ApiRequest::getEmpty).orElse(null))) {
				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolChild(siteContext, siteRequest.getOperationRequest(), new JsonObject());
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				SearchList<SchoolChild> searchList = new SearchList<SchoolChild>();
				searchList.setPopulate(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolChild.class);
				searchList.addFilterQuery("modified_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(siteRequest.getApiRequest_().getCreated().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				searchList.add("json.facet", "{enrollmentKeys:{terms:{field:enrollmentKeys_indexed_longs, limit:1000}}}");
				searchList.setRows(1000);
				searchList.initDeepSearchList(siteRequest2);
				List<Future> futures = new ArrayList<>();

				{
					SchoolEnrollmentEnUSGenApiServiceImpl service = new SchoolEnrollmentEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
					for(Long pk : o.getEnrollmentKeys()) {
						SchoolEnrollment o2 = new SchoolEnrollment();

						o2.setPk(pk);
						o2.setSiteRequest_(siteRequest2);
						futures.add(
							service.patchSchoolEnrollmentFuture(o2, a -> {
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

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						LOGGER.info("Refresh relations succeeded. ");
						SchoolChildEnUSGenApiServiceImpl service = new SchoolChildEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
						List<Future> futures2 = new ArrayList<>();
						for(SchoolChild o2 : searchList.getList()) {
							futures2.add(
								service.patchSchoolChildFuture(o2, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("SchoolChild %s refreshed. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("SchoolChild %s failed. ", o2.getPk()));
										eventHandler.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Refresh SchoolChild succeeded. ");
								eventHandler.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Refresh relations failed. ", b.cause());
								errorSchoolChild(siteRequest2, eventHandler, b);
							}
						});
					} else {
						LOGGER.error("Refresh relations failed. ", a.cause());
						errorSchoolChild(siteRequest2, eventHandler, a);
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
