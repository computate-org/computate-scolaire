package org.computate.scolaire.enUS.enrollment.design;

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
 * CanonicalName.frFR: org.computate.scolaire.frFR.inscription.design.DesignInscriptionFrFRGenApiServiceImpl
 **/
public class EnrollmentDesignEnUSGenApiServiceImpl implements EnrollmentDesignEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentDesignEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "EnrollmentDesignEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public EnrollmentDesignEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
	}

	// POST //

	@Override
	public void postEnrollmentDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest, body);

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

			sqlEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userEnrollmentDesign(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							postEnrollmentDesignFuture(siteRequest, c -> {
								if(c.succeeded()) {
									EnrollmentDesign enrollmentDesign = c.result();
									apiRequestEnrollmentDesign(enrollmentDesign);
									postEnrollmentDesignResponse(enrollmentDesign, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("postEnrollmentDesign succeeded. "));
										} else {
											LOGGER.error(String.format("postEnrollmentDesign failed. ", d.cause()));
											errorEnrollmentDesign(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorEnrollmentDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorEnrollmentDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorEnrollmentDesign(null, eventHandler, Future.failedFuture(e));
		}
	}


	public Future<EnrollmentDesign> postEnrollmentDesignFuture(SiteRequestEnUS siteRequest, Handler<AsyncResult<EnrollmentDesign>> eventHandler) {
		Promise<EnrollmentDesign> promise = Promise.promise();
		try {
			createEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					EnrollmentDesign enrollmentDesign = a.result();
					sqlPOSTEnrollmentDesign(enrollmentDesign, false, b -> {
						if(b.succeeded()) {
							defineIndexEnrollmentDesign(enrollmentDesign, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(enrollmentDesign));
									promise.complete(enrollmentDesign);
								} else {
									errorEnrollmentDesign(siteRequest, null, c);
								}
							});
						} else {
							errorEnrollmentDesign(siteRequest, null, b);
						}
					});
				} else {
					errorEnrollmentDesign(siteRequest, null, a);
				}
			});
		} catch(Exception e) {
			errorEnrollmentDesign(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTEnrollmentDesign(EnrollmentDesign o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "enrollmentDesignCompleteName":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentDesignCompleteName", jsonObject.getString(entityVar), pk));
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

	public void postEnrollmentDesignResponse(EnrollmentDesign enrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = enrollmentDesign.getSiteRequest_();
		response200POSTEnrollmentDesign(enrollmentDesign, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								ApiRequest apiRequest = apiRequestEnrollmentDesign(enrollmentDesign);
								enrollmentDesign.apiRequestEnrollmentDesign();
								siteRequest.getVertx().eventBus().publish("websocketEnrollmentDesign", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorEnrollmentDesign(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorEnrollmentDesign(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorEnrollmentDesign(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200POSTEnrollmentDesign(EnrollmentDesign o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
	public void putimportEnrollmentDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest, body);

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

			sqlEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userEnrollmentDesign(siteRequest, b -> {
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
											sqlEnrollmentDesign(siteRequest, d -> {
												if(d.succeeded()) {
													try {
														listPUTImportEnrollmentDesign(apiRequest, siteRequest, e -> {
															if(e.succeeded()) {
																putimportEnrollmentDesignResponse(siteRequest, f -> {
																	if(f.succeeded()) {
																		eventHandler.handle(Future.succeededFuture(f.result()));
																		LOGGER.info(String.format("putimportEnrollmentDesign succeeded. "));
																	} else {
																		LOGGER.error(String.format("putimportEnrollmentDesign failed. ", f.cause()));
																		errorEnrollmentDesign(siteRequest, eventHandler, f);
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
									errorEnrollmentDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorEnrollmentDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorEnrollmentDesign(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPUTImportEnrollmentDesign(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		jsonArray.forEach(obj -> {
			JsonObject json = (JsonObject)obj;

			SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForEnrollmentDesign(siteContext, siteRequest.getOperationRequest(), json);
			siteRequest2.setSqlConnection(siteRequest.getSqlConnection());

			SearchList<EnrollmentDesign> searchList = new SearchList<EnrollmentDesign>();
			searchList.setStore(true);
			searchList.setQuery("*:*");
			searchList.setC(EnrollmentDesign.class);
			searchList.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
			searchList.initDeepForClass(siteRequest2);

			if(searchList.size() == 1) {
				EnrollmentDesign o = searchList.get(0);
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
					patchEnrollmentDesignFuture(o, a -> {
						if(a.succeeded()) {
							EnrollmentDesign enrollmentDesign = a.result();
							apiRequestEnrollmentDesign(enrollmentDesign);
						} else {
							errorEnrollmentDesign(siteRequest2, eventHandler, a);
						}
					})
				);
			} else {
				futures.add(
					postEnrollmentDesignFuture(siteRequest2, a -> {
						if(a.succeeded()) {
							EnrollmentDesign enrollmentDesign = a.result();
							apiRequestEnrollmentDesign(enrollmentDesign);
						} else {
							errorEnrollmentDesign(siteRequest2, eventHandler, a);
						}
					})
				);
			}
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + jsonArray.size());
				response200PUTImportEnrollmentDesign(siteRequest, eventHandler);
			} else {
				errorEnrollmentDesign(apiRequest.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public void sqlPUTImportEnrollmentDesign(EnrollmentDesign o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void putimportEnrollmentDesignResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		response200PUTImportEnrollmentDesign(siteRequest, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketEnrollmentDesign", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorEnrollmentDesign(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorEnrollmentDesign(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorEnrollmentDesign(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PUTImportEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(apiRequest).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTMerge //

	@Override
	public void putmergeEnrollmentDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest, body);

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

			sqlEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userEnrollmentDesign(siteRequest, b -> {
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
											sqlEnrollmentDesign(siteRequest, d -> {
												if(d.succeeded()) {
													try {
														listPUTMergeEnrollmentDesign(apiRequest, siteRequest, e -> {
															if(e.succeeded()) {
																putmergeEnrollmentDesignResponse(siteRequest, f -> {
																	if(f.succeeded()) {
																		eventHandler.handle(Future.succeededFuture(f.result()));
																		LOGGER.info(String.format("putmergeEnrollmentDesign succeeded. "));
																	} else {
																		LOGGER.error(String.format("putmergeEnrollmentDesign failed. ", f.cause()));
																		errorEnrollmentDesign(siteRequest, eventHandler, f);
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
									errorEnrollmentDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorEnrollmentDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorEnrollmentDesign(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPUTMergeEnrollmentDesign(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		jsonArray.forEach(obj -> {
			JsonObject json = (JsonObject)obj;

			SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForEnrollmentDesign(siteContext, siteRequest.getOperationRequest(), json);
			siteRequest2.setSqlConnection(siteRequest.getSqlConnection());

			SearchList<EnrollmentDesign> searchList = new SearchList<EnrollmentDesign>();
			searchList.setStore(true);
			searchList.setQuery("*:*");
			searchList.setC(EnrollmentDesign.class);
			searchList.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
			searchList.initDeepForClass(siteRequest2);

			if(searchList.size() == 1) {
				EnrollmentDesign o = searchList.get(0);
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
					patchEnrollmentDesignFuture(o, a -> {
						if(a.succeeded()) {
							EnrollmentDesign enrollmentDesign = a.result();
							apiRequestEnrollmentDesign(enrollmentDesign);
						} else {
							errorEnrollmentDesign(siteRequest2, eventHandler, a);
						}
					})
				);
			} else {
				futures.add(
					postEnrollmentDesignFuture(siteRequest2, a -> {
						if(a.succeeded()) {
							EnrollmentDesign enrollmentDesign = a.result();
							apiRequestEnrollmentDesign(enrollmentDesign);
						} else {
							errorEnrollmentDesign(siteRequest2, eventHandler, a);
						}
					})
				);
			}
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + jsonArray.size());
				response200PUTMergeEnrollmentDesign(siteRequest, eventHandler);
			} else {
				errorEnrollmentDesign(apiRequest.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public void sqlPUTMergeEnrollmentDesign(EnrollmentDesign o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void putmergeEnrollmentDesignResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		response200PUTMergeEnrollmentDesign(siteRequest, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketEnrollmentDesign", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorEnrollmentDesign(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorEnrollmentDesign(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorEnrollmentDesign(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PUTMergeEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(apiRequest).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTCopy //

	@Override
	public void putcopyEnrollmentDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest, body);

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

			sqlEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userEnrollmentDesign(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									aSearchEnrollmentDesign(siteRequest, false, true, null, d -> {
										if(d.succeeded()) {
											SearchList<EnrollmentDesign> listEnrollmentDesign = d.result();
											WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
											workerExecutor.executeBlocking(
												blockingCodeHandler -> {
													sqlEnrollmentDesign(siteRequest, e -> {
														if(e.succeeded()) {
															try {
																listPUTCopyEnrollmentDesign(apiRequest, listEnrollmentDesign, f -> {
																	if(f.succeeded()) {
																		putcopyEnrollmentDesignResponse(listEnrollmentDesign, g -> {
																			if(g.succeeded()) {
																				eventHandler.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("putcopyEnrollmentDesign succeeded. "));
																			} else {
																				LOGGER.error(String.format("putcopyEnrollmentDesign failed. ", g.cause()));
																				errorEnrollmentDesign(siteRequest, eventHandler, d);
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
											errorEnrollmentDesign(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorEnrollmentDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorEnrollmentDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorEnrollmentDesign(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPUTCopyEnrollmentDesign(ApiRequest apiRequest, SearchList<EnrollmentDesign> listEnrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
		listEnrollmentDesign.getList().forEach(o -> {
			futures.add(
				putcopyEnrollmentDesignFuture(siteRequest, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						EnrollmentDesign enrollmentDesign = a.result();
						apiRequestEnrollmentDesign(enrollmentDesign);
					} else {
						errorEnrollmentDesign(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listEnrollmentDesign.size());
				if(listEnrollmentDesign.next()) {
					siteRequest.getVertx().eventBus().publish("websocketEnrollmentDesign", JsonObject.mapFrom(apiRequest).toString());
					listPUTCopyEnrollmentDesign(apiRequest, listEnrollmentDesign, eventHandler);
				} else {
					response200PUTCopyEnrollmentDesign(listEnrollmentDesign, eventHandler);
				}
			} else {
				errorEnrollmentDesign(listEnrollmentDesign.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<EnrollmentDesign> putcopyEnrollmentDesignFuture(SiteRequestEnUS siteRequest, JsonObject jsonObject, Handler<AsyncResult<EnrollmentDesign>> eventHandler) {
		Promise<EnrollmentDesign> promise = Promise.promise();
		try {

			jsonObject.put("saves", Optional.ofNullable(jsonObject.getJsonArray("saves")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("saves").add(o.getKey());
			});

			createEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					EnrollmentDesign enrollmentDesign = a.result();
					sqlPUTCopyEnrollmentDesign(enrollmentDesign, jsonObject, b -> {
						if(b.succeeded()) {
							defineEnrollmentDesign(enrollmentDesign, c -> {
								if(c.succeeded()) {
									attributeEnrollmentDesign(enrollmentDesign, d -> {
										if(d.succeeded()) {
											indexEnrollmentDesign(enrollmentDesign, e -> {
												if(e.succeeded()) {
													eventHandler.handle(Future.succeededFuture(enrollmentDesign));
													promise.complete(enrollmentDesign);
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
			errorEnrollmentDesign(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopyEnrollmentDesign(EnrollmentDesign o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "enrollmentDesignCompleteName":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentDesignCompleteName", jsonObject.getString(entityVar), pk));
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

	public void putcopyEnrollmentDesignResponse(SearchList<EnrollmentDesign> listEnrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
		response200PUTCopyEnrollmentDesign(listEnrollmentDesign, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketEnrollmentDesign", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorEnrollmentDesign(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorEnrollmentDesign(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorEnrollmentDesign(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PUTCopyEnrollmentDesign(SearchList<EnrollmentDesign> listEnrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(apiRequest).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchEnrollmentDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest, body);

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

			sqlEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userEnrollmentDesign(siteRequest, b -> {
						if(b.succeeded()) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1L);
							apiRequest.initDeepApiRequest(siteRequest);
							siteRequest.setApiRequest_(apiRequest);
							SQLConnection sqlConnection = siteRequest.getSqlConnection();
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									aSearchEnrollmentDesign(siteRequest, false, true, null, d -> {
										if(d.succeeded()) {
											SearchList<EnrollmentDesign> listEnrollmentDesign = d.result();
											SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listEnrollmentDesign.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
											Date date = null;
											if(facets != null)
												date = (Date)facets.get("max_modified");
											String dt;
											if(date == null)
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
											else
												dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
											listEnrollmentDesign.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));

											if(listEnrollmentDesign.size() == 1) {
												EnrollmentDesign o = listEnrollmentDesign.get(0);
												apiRequest.setPk(o.getPk());
												apiRequest.setOriginal(o);
												apiRequestEnrollmentDesign(o);
											o.apiRequestEnrollmentDesign();
											}
											WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
											workerExecutor.executeBlocking(
												blockingCodeHandler -> {
													sqlEnrollmentDesign(siteRequest, e -> {
														if(e.succeeded()) {
															try {
																listPATCHEnrollmentDesign(apiRequest, listEnrollmentDesign, dt, f -> {
																	if(f.succeeded()) {
																		patchEnrollmentDesignResponse(listEnrollmentDesign, g -> {
																			if(g.succeeded()) {
																				eventHandler.handle(Future.succeededFuture(g.result()));
																				LOGGER.info(String.format("patchEnrollmentDesign succeeded. "));
																			} else {
																				LOGGER.error(String.format("patchEnrollmentDesign failed. ", g.cause()));
																				errorEnrollmentDesign(siteRequest, eventHandler, d);
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
											errorEnrollmentDesign(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorEnrollmentDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorEnrollmentDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorEnrollmentDesign(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void listPATCHEnrollmentDesign(ApiRequest apiRequest, SearchList<EnrollmentDesign> listEnrollmentDesign, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
		listEnrollmentDesign.getList().forEach(o -> {
			futures.add(
				patchEnrollmentDesignFuture(o, a -> {
					if(a.succeeded()) {
							EnrollmentDesign enrollmentDesign = a.result();
							apiRequestEnrollmentDesign(enrollmentDesign);
					} else {
						errorEnrollmentDesign(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listEnrollmentDesign.size());
				if(listEnrollmentDesign.next(dt)) {
					siteRequest.getVertx().eventBus().publish("websocketEnrollmentDesign", JsonObject.mapFrom(apiRequest).toString());
					listPATCHEnrollmentDesign(apiRequest, listEnrollmentDesign, dt, eventHandler);
				} else {
					response200PATCHEnrollmentDesign(listEnrollmentDesign, eventHandler);
				}
			} else {
				errorEnrollmentDesign(listEnrollmentDesign.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<EnrollmentDesign> patchEnrollmentDesignFuture(EnrollmentDesign o, Handler<AsyncResult<EnrollmentDesign>> eventHandler) {
		Promise<EnrollmentDesign> promise = Promise.promise();
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			sqlPATCHEnrollmentDesign(o, false, a -> {
				if(a.succeeded()) {
					EnrollmentDesign enrollmentDesign = a.result();
					defineEnrollmentDesign(enrollmentDesign, b -> {
						if(b.succeeded()) {
							attributeEnrollmentDesign(enrollmentDesign, c -> {
								if(c.succeeded()) {
									indexEnrollmentDesign(enrollmentDesign, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(enrollmentDesign));
											promise.complete(enrollmentDesign);
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
			errorEnrollmentDesign(null, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHEnrollmentDesign(EnrollmentDesign o, Boolean inheritPk, Handler<AsyncResult<EnrollmentDesign>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject requestJson = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = requestJson.fieldNames();
			EnrollmentDesign o2 = new EnrollmentDesign();

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.enrollment.design.EnrollmentDesign"));
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
					case "setEnrollmentDesignCompleteName":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDesignCompleteName"));
						} else {
							o2.setEnrollmentDesignCompleteName(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDesignCompleteName", o2.jsonEnrollmentDesignCompleteName(), pk));
						}
						break;
					case "setDesignHidden":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "designHidden"));
						} else {
							o2.setDesignHidden(requestJson.getBoolean(methodName));
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
					EnrollmentDesign o3 = new EnrollmentDesign();
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

	public void patchEnrollmentDesignResponse(SearchList<EnrollmentDesign> listEnrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
		response200PATCHEnrollmentDesign(listEnrollmentDesign, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								ApiRequest apiRequest = siteRequest.getApiRequest_();
								siteRequest.getVertx().eventBus().publish("websocketEnrollmentDesign", JsonObject.mapFrom(apiRequest).toString());
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorEnrollmentDesign(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorEnrollmentDesign(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorEnrollmentDesign(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200PATCHEnrollmentDesign(SearchList<EnrollmentDesign> listEnrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			JsonObject json = JsonObject.mapFrom(apiRequest);
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getEnrollmentDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest);

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

			sqlEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userEnrollmentDesign(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchEnrollmentDesign(siteRequest, false, true, null, c -> {
								if(c.succeeded()) {
									SearchList<EnrollmentDesign> listEnrollmentDesign = c.result();
									getEnrollmentDesignResponse(listEnrollmentDesign, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("getEnrollmentDesign succeeded. "));
										} else {
											LOGGER.error(String.format("getEnrollmentDesign failed. ", d.cause()));
											errorEnrollmentDesign(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorEnrollmentDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorEnrollmentDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorEnrollmentDesign(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void getEnrollmentDesignResponse(SearchList<EnrollmentDesign> listEnrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
		response200GETEnrollmentDesign(listEnrollmentDesign, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorEnrollmentDesign(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorEnrollmentDesign(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorEnrollmentDesign(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200GETEnrollmentDesign(SearchList<EnrollmentDesign> listEnrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
			SolrDocumentList solrDocuments = listEnrollmentDesign.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listEnrollmentDesign.getList().stream().findFirst().orElse(null));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchEnrollmentDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest);

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

			sqlEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userEnrollmentDesign(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchEnrollmentDesign(siteRequest, false, true, "/api/enrollment-design", c -> {
								if(c.succeeded()) {
									SearchList<EnrollmentDesign> listEnrollmentDesign = c.result();
									searchEnrollmentDesignResponse(listEnrollmentDesign, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchEnrollmentDesign succeeded. "));
										} else {
											LOGGER.error(String.format("searchEnrollmentDesign failed. ", d.cause()));
											errorEnrollmentDesign(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorEnrollmentDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorEnrollmentDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorEnrollmentDesign(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void searchEnrollmentDesignResponse(SearchList<EnrollmentDesign> listEnrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
		response200SearchEnrollmentDesign(listEnrollmentDesign, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorEnrollmentDesign(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorEnrollmentDesign(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorEnrollmentDesign(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200SearchEnrollmentDesign(SearchList<EnrollmentDesign> listEnrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
			QueryResponse responseSearch = listEnrollmentDesign.getQueryResponse();
			SolrDocumentList solrDocuments = listEnrollmentDesign.getSolrDocumentList();
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
			listEnrollmentDesign.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listEnrollmentDesign.getFields();
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
	public void searchpageEnrollmentDesignId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageEnrollmentDesign(operationRequest, eventHandler);
	}

	@Override
	public void searchpageEnrollmentDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest);

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

			sqlEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userEnrollmentDesign(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchEnrollmentDesign(siteRequest, false, true, "/enrollment-design", c -> {
								if(c.succeeded()) {
									SearchList<EnrollmentDesign> listEnrollmentDesign = c.result();
									searchpageEnrollmentDesignResponse(listEnrollmentDesign, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchpageEnrollmentDesign succeeded. "));
										} else {
											LOGGER.error(String.format("searchpageEnrollmentDesign failed. ", d.cause()));
											errorEnrollmentDesign(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorEnrollmentDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorEnrollmentDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorEnrollmentDesign(null, eventHandler, Future.failedFuture(e));
		}
	}


	public void searchpageEnrollmentDesignResponse(SearchList<EnrollmentDesign> listEnrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
		response200SearchPageEnrollmentDesign(listEnrollmentDesign, a -> {
			if(a.succeeded()) {
				SQLConnection sqlConnection = siteRequest.getSqlConnection();
				sqlConnection.commit(b -> {
					if(b.succeeded()) {
						sqlConnection.close(c -> {
							if(c.succeeded()) {
								eventHandler.handle(Future.succeededFuture(a.result()));
							} else {
								errorEnrollmentDesign(siteRequest, eventHandler, c);
							}
						});
					} else {
						errorEnrollmentDesign(siteRequest, eventHandler, b);
					}
				});
			} else {
				errorEnrollmentDesign(siteRequest, eventHandler, a);
			}
		});
	}
	public void response200SearchPageEnrollmentDesign(SearchList<EnrollmentDesign> listEnrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listEnrollmentDesign.getSiteRequest_(), buffer);
			EnrollmentDesignPage page = new EnrollmentDesignPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/enrollment-design");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listEnrollmentDesign.size() == 1)
				siteRequest.setRequestPk(listEnrollmentDesign.get(0).getPk());
			siteRequest.setW(w);
			page.setListEnrollmentDesign(listEnrollmentDesign);
			page.setSiteRequest_(siteRequest);
			page.initDeepEnrollmentDesignPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<EnrollmentDesign> defineIndexEnrollmentDesign(EnrollmentDesign enrollmentDesign, Handler<AsyncResult<EnrollmentDesign>> eventHandler) {
		Promise<EnrollmentDesign> promise = Promise.promise();
		SiteRequestEnUS siteRequest = enrollmentDesign.getSiteRequest_();
		defineEnrollmentDesign(enrollmentDesign, c -> {
			if(c.succeeded()) {
				attributeEnrollmentDesign(enrollmentDesign, d -> {
					if(d.succeeded()) {
						indexEnrollmentDesign(enrollmentDesign, e -> {
							if(e.succeeded()) {
								eventHandler.handle(Future.succeededFuture(enrollmentDesign));
								promise.complete(enrollmentDesign);
							} else {
								errorEnrollmentDesign(siteRequest, null, e);
							}
						});
					} else {
						errorEnrollmentDesign(siteRequest, null, d);
					}
				});
			} else {
				errorEnrollmentDesign(siteRequest, null, c);
			}
		});
		return promise.future();
	}

	public void createEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<EnrollmentDesign>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(EnrollmentDesign.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				EnrollmentDesign o = new EnrollmentDesign();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public ApiRequest apiRequestEnrollmentDesign(EnrollmentDesign o) {
		ApiRequest apiRequest = o.getSiteRequest_().getApiRequest_();
		if(apiRequest != null) {
			List<Long> pks = apiRequest.getPks();
			List<String> classes = apiRequest.getClasses();
		}
		return apiRequest;
	}

	public void errorEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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

	public void sqlEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public SiteRequestEnUS generateSiteRequestEnUSForEnrollmentDesign(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForEnrollmentDesign(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
								userEnrollmentDesignDefine(siteRequest, jsonObject, false);

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
																		errorEnrollmentDesign(siteRequest, eventHandler, f);
																	}
																});
															} else {
																errorEnrollmentDesign(siteRequest, eventHandler, e);
															}
														});
													} else {
														errorEnrollmentDesign(siteRequest, eventHandler, d);
													}
												});
											} else {
												errorEnrollmentDesign(siteRequest, eventHandler, c);
											}
										});
									} else {
										errorEnrollmentDesign(siteRequest, eventHandler, b);
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
								Boolean define = userEnrollmentDesignDefine(siteRequest, jsonObject, true);
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
																	errorEnrollmentDesign(siteRequest, eventHandler, f);
																}
															});
														} else {
															errorEnrollmentDesign(siteRequest, eventHandler, e);
														}
													});
												} else {
													errorEnrollmentDesign(siteRequest, eventHandler, d);
												}
											});
										} else {
											errorEnrollmentDesign(siteRequest, eventHandler, c);
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

	public Boolean userEnrollmentDesignDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void aSearchEnrollmentDesignQ(String classApiUriMethod, SearchList<EnrollmentDesign> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
			searchList.setHighlight(true);
			searchList.setHighlightSnippets(3);
			searchList.addHighlightField(varIndexed);
			searchList.setParam("hl.encoder", "html");
		}
	}

	public void aSearchEnrollmentDesignFq(String classApiUriMethod, SearchList<EnrollmentDesign> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
	}

	public void aSearchEnrollmentDesignSort(String classApiUriMethod, SearchList<EnrollmentDesign> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addSort(varIndexed, ORDER.valueOf(valueIndexed));
	}

	public void aSearchEnrollmentDesignRows(String classApiUriMethod, SearchList<EnrollmentDesign> searchList, Integer valueRows) {
		searchList.setRows(valueRows);
	}

	public void aSearchEnrollmentDesignStart(String classApiUriMethod, SearchList<EnrollmentDesign> searchList, Integer valueStart) {
		searchList.setStart(valueStart);
	}

	public void aSearchEnrollmentDesignVar(String classApiUriMethod, SearchList<EnrollmentDesign> searchList, String var, String value) {
		searchList.getSiteRequest_().getRequestVars().put(var, value);
	}

	public void aSearchEnrollmentDesign(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String classApiUriMethod, Handler<AsyncResult<SearchList<EnrollmentDesign>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<EnrollmentDesign> searchList = new SearchList<EnrollmentDesign>();
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.setQuery("*:*");
			searchList.setC(EnrollmentDesign.class);
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
								varIndexed = "*".equals(entityVar) ? entityVar : EnrollmentDesign.varSearchEnrollmentDesign(entityVar);
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								valueIndexed = StringUtils.isEmpty(valueIndexed) ? "*" : valueIndexed;
								aSearchEnrollmentDesignQ(classApiUriMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "fq":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = EnrollmentDesign.varIndexedEnrollmentDesign(entityVar);
								aSearchEnrollmentDesignFq(classApiUriMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = EnrollmentDesign.varIndexedEnrollmentDesign(entityVar);
								aSearchEnrollmentDesignSort(classApiUriMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "start":
								valueStart = (Integer)paramObject;
								aSearchEnrollmentDesignStart(classApiUriMethod, searchList, valueStart);
								break;
							case "rows":
								valueRows = (Integer)paramObject;
								aSearchEnrollmentDesignRows(classApiUriMethod, searchList, valueRows);
								break;
							case "var":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								aSearchEnrollmentDesignVar(classApiUriMethod, searchList, entityVar, valueIndexed);
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

	public void defineEnrollmentDesign(EnrollmentDesign o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void attributeEnrollmentDesign(EnrollmentDesign o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void indexEnrollmentDesign(EnrollmentDesign o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			if(BooleanUtils.isFalse(Optional.ofNullable(siteRequest.getApiRequest_()).map(ApiRequest::getEmpty).orElse(null))) {
				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForEnrollmentDesign(siteContext, siteRequest.getOperationRequest(), new JsonObject());
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				SearchList<EnrollmentDesign> searchList = new SearchList<EnrollmentDesign>();
				searchList.setPopulate(true);
				searchList.setQuery("*:*");
				searchList.setC(EnrollmentDesign.class);
				searchList.addFilterQuery("modified_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(siteRequest.getApiRequest_().getCreated().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				searchList.setRows(1000);
				searchList.initDeepSearchList(siteRequest2);
				List<Future> futures = new ArrayList<>();

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						LOGGER.info("Refresh relations succeeded. ");
						EnrollmentDesignEnUSGenApiServiceImpl service = new EnrollmentDesignEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
						List<Future> futures2 = new ArrayList<>();
						for(EnrollmentDesign o2 : searchList.getList()) {
							futures2.add(
								service.patchEnrollmentDesignFuture(o2, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("EnrollmentDesign %s refreshed. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("EnrollmentDesign %s failed. ", o2.getPk()));
										eventHandler.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Refresh EnrollmentDesign succeeded. ");
								eventHandler.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Refresh relations failed. ", b.cause());
								errorEnrollmentDesign(siteRequest2, eventHandler, b);
							}
						});
					} else {
						LOGGER.error("Refresh relations failed. ", a.cause());
						errorEnrollmentDesign(siteRequest2, eventHandler, a);
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
