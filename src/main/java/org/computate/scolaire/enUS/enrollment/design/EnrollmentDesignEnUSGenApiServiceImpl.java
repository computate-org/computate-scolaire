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
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.api.validation.HTTPRequestValidationHandler;
import io.vertx.ext.web.api.validation.ParameterTypeValidator;
import io.vertx.ext.web.api.validation.ValidationException;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Transaction;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Tuple;
import io.vertx.sqlclient.Row;
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
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("postEnrollmentDesign started. "));

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

			userEnrollmentDesign(siteRequest, b -> {
				if(b.succeeded()) {
					ApiRequest apiRequest = new ApiRequest();
					apiRequest.setRows(1);
					apiRequest.setNumFound(1L);
					apiRequest.setNumPATCH(0L);
					apiRequest.initDeepApiRequest(siteRequest);
					siteRequest.setApiRequest_(apiRequest);
					siteRequest.getVertx().eventBus().publish("websocketEnrollmentDesign", JsonObject.mapFrom(apiRequest).toString());
					postEnrollmentDesignFuture(siteRequest, false, c -> {
						if(c.succeeded()) {
							EnrollmentDesign enrollmentDesign = c.result();
							apiRequest.setPk(enrollmentDesign.getPk());
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
							LOGGER.error(String.format("postEnrollmentDesign failed. ", c.cause()));
							errorEnrollmentDesign(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("postEnrollmentDesign failed. ", b.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postEnrollmentDesign failed. ", ex));
			errorEnrollmentDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public Future<EnrollmentDesign> postEnrollmentDesignFuture(SiteRequestEnUS siteRequest, Boolean inheritPk, Handler<AsyncResult<EnrollmentDesign>> eventHandler) {
		Promise<EnrollmentDesign> promise = Promise.promise();
		try {
			sqlConnectionEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					sqlTransactionEnrollmentDesign(siteRequest, b -> {
						if(b.succeeded()) {
							createEnrollmentDesign(siteRequest, c -> {
								if(c.succeeded()) {
									EnrollmentDesign enrollmentDesign = c.result();
									sqlPOSTEnrollmentDesign(enrollmentDesign, inheritPk, d -> {
										if(d.succeeded()) {
											defineIndexEnrollmentDesign(enrollmentDesign, e -> {
												if(e.succeeded()) {
													ApiRequest apiRequest = siteRequest.getApiRequest_();
													if(apiRequest != null) {
														apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
														enrollmentDesign.apiRequestEnrollmentDesign();
														siteRequest.getVertx().eventBus().publish("websocketEnrollmentDesign", JsonObject.mapFrom(apiRequest).toString());
													}
													eventHandler.handle(Future.succeededFuture(enrollmentDesign));
													promise.complete(enrollmentDesign);
												} else {
													LOGGER.error(String.format("postEnrollmentDesignFuture failed. ", e.cause()));
													eventHandler.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											LOGGER.error(String.format("postEnrollmentDesignFuture failed. ", d.cause()));
											eventHandler.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("postEnrollmentDesignFuture failed. ", c.cause()));
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("postEnrollmentDesignFuture failed. ", b.cause()));
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("postEnrollmentDesignFuture failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("postEnrollmentDesignFuture failed. ", e));
			errorEnrollmentDesign(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTEnrollmentDesign(EnrollmentDesign o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Transaction tx = siteRequest.getTx();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			List<Future> futures = new ArrayList<>();

			if(siteRequest.getSessionId() != null) {
				futures.add(Future.future(a -> {
					tx.preparedQuery(SiteContextEnUS.SQL_setD
				, Tuple.of(pk, "sessionId", siteRequest.getSessionId())
							, b
					-> {
						if(b.succeeded())
							a.handle(Future.succeededFuture());
						else
							a.handle(Future.failedFuture(b.cause()));
					});
				}));
			}
			if(siteRequest.getUserId() != null) {
				futures.add(Future.future(a -> {
					tx.preparedQuery(SiteContextEnUS.SQL_setD
				, Tuple.of(pk, "userId", siteRequest.getUserId())
							, b
					-> {
						if(b.succeeded())
							a.handle(Future.succeededFuture());
						else
							a.handle(Future.failedFuture(b.cause()));
					});
				}));
			}
			if(siteRequest.getUserKey() != null) {
				futures.add(Future.future(a -> {
					tx.preparedQuery(SiteContextEnUS.SQL_setD
				, Tuple.of(pk, "userKey", siteRequest.getUserKey())
							, b
					-> {
						if(b.succeeded())
							a.handle(Future.succeededFuture());
						else
							a.handle(Future.failedFuture(b.cause()));
					});
				}));
			}

			if(jsonObject != null) {
				Set<String> entityVars = jsonObject.fieldNames();
				for(String entityVar : entityVars) {
					switch(entityVar) {
					case "inheritPk":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "inheritPk", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.inheritPk failed", b.cause())));
							});
						}));
						break;
					case "created":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "created", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.created failed", b.cause())));
							});
						}));
						break;
					case "modified":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "modified", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.modified failed", b.cause())));
							});
						}));
						break;
					case "archived":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "archived", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.archived failed", b.cause())));
							});
						}));
						break;
					case "deleted":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "deleted", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.deleted failed", b.cause())));
							});
						}));
						break;
					case "sessionId":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "sessionId", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.sessionId failed", b.cause())));
							});
						}));
						break;
					case "userId":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "userId", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.userId failed", b.cause())));
							});
						}));
						break;
					case "userKey":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "userKey", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.userKey failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDesignCompleteName":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDesignCompleteName", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.enrollmentDesignCompleteName failed", b.cause())));
							});
						}));
						break;
					case "designHidden":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "designHidden", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.designHidden failed", b.cause())));
							});
						}));
						break;
					}
				}
			}
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture());
				} else {
					LOGGER.error(String.format("sqlPOSTEnrollmentDesign failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPOSTEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void postEnrollmentDesignResponse(EnrollmentDesign enrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = enrollmentDesign.getSiteRequest_();
		try {
			response200POSTEnrollmentDesign(enrollmentDesign, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("postEnrollmentDesignResponse failed. ", a.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postEnrollmentDesignResponse failed. ", ex));
			errorEnrollmentDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200POSTEnrollmentDesign(EnrollmentDesign o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(o);
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200POSTEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTImport //

	@Override
	public void putimportEnrollmentDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putimportEnrollmentDesign started. "));

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

			userEnrollmentDesign(siteRequest, b -> {
				if(b.succeeded()) {
					putimportEnrollmentDesignResponse(siteRequest, c -> {
						if(c.succeeded()) {
							eventHandler.handle(Future.succeededFuture(c.result()));
							WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
							workerExecutor.executeBlocking(
								blockingCodeHandler -> {
									SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest, body);
									try {
										ApiRequest apiRequest = new ApiRequest();
										JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
										apiRequest.setRows(jsonArray.size());
										apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
										apiRequest.setNumPATCH(0L);
										apiRequest.initDeepApiRequest(siteRequest2);
										siteRequest2.setApiRequest_(apiRequest);
										siteRequest2.getVertx().eventBus().publish("websocketEnrollmentDesign", JsonObject.mapFrom(apiRequest).toString());
										sqlConnectionEnrollmentDesign(siteRequest2, d -> {
											if(d.succeeded()) {
												listPUTImportEnrollmentDesign(apiRequest, siteRequest2, e -> {
													if(e.succeeded()) {
														putimportEnrollmentDesignResponse(siteRequest2, f -> {
															if(f.succeeded()) {
																LOGGER.info(String.format("putimportEnrollmentDesign succeeded. "));
																blockingCodeHandler.handle(Future.succeededFuture(f.result()));
															} else {
																LOGGER.error(String.format("putimportEnrollmentDesign failed. ", f.cause()));
																errorEnrollmentDesign(siteRequest2, null, f);
															}
														});
													} else {
														LOGGER.error(String.format("putimportEnrollmentDesign failed. ", e.cause()));
														errorEnrollmentDesign(siteRequest2, null, e);
													}
												});
											} else {
												LOGGER.error(String.format("putimportEnrollmentDesign failed. ", d.cause()));
												errorEnrollmentDesign(siteRequest2, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putimportEnrollmentDesign failed. ", ex));
										errorEnrollmentDesign(siteRequest2, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("putimportEnrollmentDesign failed. ", c.cause()));
							errorEnrollmentDesign(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("putimportEnrollmentDesign failed. ", b.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportEnrollmentDesign failed. ", ex));
			errorEnrollmentDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTImportEnrollmentDesign(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForEnrollmentDesign(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				siteRequest2.setApiRequest_(apiRequest);

				SearchList<EnrollmentDesign> searchList = new SearchList<EnrollmentDesign>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(EnrollmentDesign.class);
				searchList.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					EnrollmentDesign o = searchList.getList().stream().findFirst().orElse(null);
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
							patchEnrollmentDesignFuture(o, true, a -> {
								if(a.succeeded()) {
								} else {
									LOGGER.error(String.format("listPUTImportEnrollmentDesign failed. ", a.cause()));
									errorEnrollmentDesign(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postEnrollmentDesignFuture(siteRequest2, true, a -> {
							if(a.succeeded()) {
							} else {
								LOGGER.error(String.format("listPUTImportEnrollmentDesign failed. ", a.cause()));
								errorEnrollmentDesign(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
					response200PUTImportEnrollmentDesign(siteRequest, eventHandler);
				} else {
					LOGGER.error(String.format("listPUTImportEnrollmentDesign failed. ", a.cause()));
					errorEnrollmentDesign(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("listPUTImportEnrollmentDesign failed. ", ex));
			errorEnrollmentDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putimportEnrollmentDesignResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTImportEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putimportEnrollmentDesignResponse failed. ", a.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportEnrollmentDesignResponse failed. ", ex));
			errorEnrollmentDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTImportEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PUTImportEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTMerge //

	@Override
	public void putmergeEnrollmentDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putmergeEnrollmentDesign started. "));

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

			userEnrollmentDesign(siteRequest, b -> {
				if(b.succeeded()) {
					putmergeEnrollmentDesignResponse(siteRequest, c -> {
						if(c.succeeded()) {
							eventHandler.handle(Future.succeededFuture(c.result()));
							WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
							workerExecutor.executeBlocking(
								blockingCodeHandler -> {
									SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest, body);
									try {
										ApiRequest apiRequest = new ApiRequest();
										JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
										apiRequest.setRows(jsonArray.size());
										apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
										apiRequest.setNumPATCH(0L);
										apiRequest.initDeepApiRequest(siteRequest2);
										siteRequest2.setApiRequest_(apiRequest);
										siteRequest2.getVertx().eventBus().publish("websocketEnrollmentDesign", JsonObject.mapFrom(apiRequest).toString());
										sqlConnectionEnrollmentDesign(siteRequest2, d -> {
											if(d.succeeded()) {
												listPUTMergeEnrollmentDesign(apiRequest, siteRequest2, e -> {
													if(e.succeeded()) {
														putmergeEnrollmentDesignResponse(siteRequest2, f -> {
															if(f.succeeded()) {
																LOGGER.info(String.format("putmergeEnrollmentDesign succeeded. "));
																blockingCodeHandler.handle(Future.succeededFuture(f.result()));
															} else {
																LOGGER.error(String.format("putmergeEnrollmentDesign failed. ", f.cause()));
																errorEnrollmentDesign(siteRequest2, null, f);
															}
														});
													} else {
														LOGGER.error(String.format("putmergeEnrollmentDesign failed. ", e.cause()));
														errorEnrollmentDesign(siteRequest2, null, e);
													}
												});
											} else {
												LOGGER.error(String.format("putmergeEnrollmentDesign failed. ", d.cause()));
												errorEnrollmentDesign(siteRequest2, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putmergeEnrollmentDesign failed. ", ex));
										errorEnrollmentDesign(siteRequest2, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("putmergeEnrollmentDesign failed. ", c.cause()));
							errorEnrollmentDesign(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("putmergeEnrollmentDesign failed. ", b.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeEnrollmentDesign failed. ", ex));
			errorEnrollmentDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTMergeEnrollmentDesign(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForEnrollmentDesign(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
				siteRequest2.setApiRequest_(apiRequest);

				SearchList<EnrollmentDesign> searchList = new SearchList<EnrollmentDesign>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(EnrollmentDesign.class);
				searchList.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					EnrollmentDesign o = searchList.getList().stream().findFirst().orElse(null);
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
							patchEnrollmentDesignFuture(o, false, a -> {
								if(a.succeeded()) {
								} else {
									LOGGER.error(String.format("listPUTMergeEnrollmentDesign failed. ", a.cause()));
									errorEnrollmentDesign(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postEnrollmentDesignFuture(siteRequest2, false, a -> {
							if(a.succeeded()) {
							} else {
								LOGGER.error(String.format("listPUTMergeEnrollmentDesign failed. ", a.cause()));
								errorEnrollmentDesign(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
					response200PUTMergeEnrollmentDesign(siteRequest, eventHandler);
				} else {
					LOGGER.error(String.format("listPUTMergeEnrollmentDesign failed. ", a.cause()));
					errorEnrollmentDesign(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("listPUTMergeEnrollmentDesign failed. ", ex));
			errorEnrollmentDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putmergeEnrollmentDesignResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTMergeEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putmergeEnrollmentDesignResponse failed. ", a.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeEnrollmentDesignResponse failed. ", ex));
			errorEnrollmentDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTMergeEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PUTMergeEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTCopy //

	@Override
	public void putcopyEnrollmentDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putcopyEnrollmentDesign started. "));

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

			userEnrollmentDesign(siteRequest, b -> {
				if(b.succeeded()) {
					putcopyEnrollmentDesignResponse(siteRequest, c -> {
						if(c.succeeded()) {
							eventHandler.handle(Future.succeededFuture(c.result()));
							WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
							workerExecutor.executeBlocking(
									blockingCodeHandler -> {
									SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest, body);
									try {
										aSearchEnrollmentDesign(siteRequest2, false, true, "/api/enrollment-design/copy", "PUTCopy", d -> {
											if(d.succeeded()) {
												SearchList<EnrollmentDesign> listEnrollmentDesign = d.result();
												ApiRequest apiRequest = new ApiRequest();
												apiRequest.setRows(listEnrollmentDesign.getRows());
												apiRequest.setNumFound(listEnrollmentDesign.getQueryResponse().getResults().getNumFound());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketEnrollmentDesign", JsonObject.mapFrom(apiRequest).toString());
												sqlConnectionEnrollmentDesign(siteRequest2, e -> {
													if(e.succeeded()) {
														try {
															listPUTCopyEnrollmentDesign(apiRequest, listEnrollmentDesign, f -> {
																if(f.succeeded()) {
																	putcopyEnrollmentDesignResponse(siteRequest2, g -> {
																		if(g.succeeded()) {
																			LOGGER.info(String.format("putcopyEnrollmentDesign succeeded. "));
																			blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																		} else {
																			LOGGER.error(String.format("putcopyEnrollmentDesign failed. ", g.cause()));
																			errorEnrollmentDesign(siteRequest2, null, g);
																		}
																	});
																} else {
																	LOGGER.error(String.format("putcopyEnrollmentDesign failed. ", f.cause()));
																	errorEnrollmentDesign(siteRequest2, null, f);
																}
															});
														} catch(Exception ex) {
															LOGGER.error(String.format("putcopyEnrollmentDesign failed. ", ex));
															errorEnrollmentDesign(siteRequest2, null, Future.failedFuture(ex));
														}
													} else {
														LOGGER.error(String.format("putcopyEnrollmentDesign failed. ", e.cause()));
														errorEnrollmentDesign(siteRequest2, null, e);
													}
												});
											} else {
												LOGGER.error(String.format("putcopyEnrollmentDesign failed. ", d.cause()));
												errorEnrollmentDesign(siteRequest2, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putcopyEnrollmentDesign failed. ", ex));
										errorEnrollmentDesign(siteRequest2, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("putcopyEnrollmentDesign failed. ", c.cause()));
							errorEnrollmentDesign(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("putcopyEnrollmentDesign failed. ", b.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopyEnrollmentDesign failed. ", ex));
			errorEnrollmentDesign(siteRequest, eventHandler, Future.failedFuture(ex));
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
					} else {
						LOGGER.error(String.format("listPUTCopyEnrollmentDesign failed. ", a.cause()));
						errorEnrollmentDesign(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listEnrollmentDesign.size());
				if(listEnrollmentDesign.next()) {
					listPUTCopyEnrollmentDesign(apiRequest, listEnrollmentDesign, eventHandler);
				} else {
					response200PUTCopyEnrollmentDesign(siteRequest, eventHandler);
				}
			} else {
				LOGGER.error(String.format("listPUTCopyEnrollmentDesign failed. ", a.cause()));
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

			sqlConnectionEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					sqlTransactionEnrollmentDesign(siteRequest, b -> {
						if(b.succeeded()) {
							createEnrollmentDesign(siteRequest, c -> {
								if(c.succeeded()) {
									EnrollmentDesign enrollmentDesign = c.result();
									sqlPUTCopyEnrollmentDesign(enrollmentDesign, jsonObject, d -> {
										if(d.succeeded()) {
											defineIndexEnrollmentDesign(enrollmentDesign, e -> {
												if(e.succeeded()) {
													ApiRequest apiRequest = siteRequest.getApiRequest_();
													if(apiRequest != null) {
														apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
														if(apiRequest.getNumFound() == 1L) {
															enrollmentDesign.apiRequestEnrollmentDesign();
														}
														siteRequest.getVertx().eventBus().publish("websocketEnrollmentDesign", JsonObject.mapFrom(apiRequest).toString());
													}
													eventHandler.handle(Future.succeededFuture(enrollmentDesign));
													promise.complete(enrollmentDesign);
												} else {
													LOGGER.error(String.format("putcopyEnrollmentDesignFuture failed. ", e.cause()));
													eventHandler.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											LOGGER.error(String.format("putcopyEnrollmentDesignFuture failed. ", d.cause()));
											eventHandler.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("putcopyEnrollmentDesignFuture failed. ", c.cause()));
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("putcopyEnrollmentDesignFuture failed. ", b.cause()));
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("putcopyEnrollmentDesignFuture failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("putcopyEnrollmentDesignFuture failed. ", e));
			errorEnrollmentDesign(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopyEnrollmentDesign(EnrollmentDesign o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Transaction tx = siteRequest.getTx();
			Long pk = o.getPk();
			List<Future> futures = new ArrayList<>();

			if(jsonObject != null) {
				JsonArray entityVars = jsonObject.getJsonArray("saves");
				for(Integer i = 0; i < entityVars.size(); i++) {
					String entityVar = entityVars.getString(i);
					switch(entityVar) {
					case "inheritPk":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "inheritPk", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.inheritPk failed", b.cause())));
							});
						}));
						break;
					case "created":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "created", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.created failed", b.cause())));
							});
						}));
						break;
					case "modified":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "modified", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.modified failed", b.cause())));
							});
						}));
						break;
					case "archived":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "archived", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.archived failed", b.cause())));
							});
						}));
						break;
					case "deleted":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "deleted", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.deleted failed", b.cause())));
							});
						}));
						break;
					case "sessionId":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "sessionId", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.sessionId failed", b.cause())));
							});
						}));
						break;
					case "userId":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "userId", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.userId failed", b.cause())));
							});
						}));
						break;
					case "userKey":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "userKey", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.userKey failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDesignCompleteName":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDesignCompleteName", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.enrollmentDesignCompleteName failed", b.cause())));
							});
						}));
						break;
					case "designHidden":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "designHidden", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.designHidden failed", b.cause())));
							});
						}));
						break;
					}
				}
			}
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture());
				} else {
					LOGGER.error(String.format("sqlPUTCopyEnrollmentDesign failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPUTCopyEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void putcopyEnrollmentDesignResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTCopyEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putcopyEnrollmentDesignResponse failed. ", a.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopyEnrollmentDesignResponse failed. ", ex));
			errorEnrollmentDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTCopyEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PUTCopyEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchEnrollmentDesign(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("patchEnrollmentDesign started. "));

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

			userEnrollmentDesign(siteRequest, b -> {
				if(b.succeeded()) {
					patchEnrollmentDesignResponse(siteRequest, c -> {
						if(c.succeeded()) {
							eventHandler.handle(Future.succeededFuture(c.result()));
							WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
							workerExecutor.executeBlocking(
								blockingCodeHandler -> {
									SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest, body);
									try {
										aSearchEnrollmentDesign(siteRequest2, false, true, "/api/enrollment-design", "PATCH", d -> {
											if(d.succeeded()) {
												SearchList<EnrollmentDesign> listEnrollmentDesign = d.result();
												ApiRequest apiRequest = new ApiRequest();
												apiRequest.setRows(listEnrollmentDesign.getRows());
												apiRequest.setNumFound(listEnrollmentDesign.getQueryResponse().getResults().getNumFound());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketEnrollmentDesign", JsonObject.mapFrom(apiRequest).toString());
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

												EnrollmentDesign o = listEnrollmentDesign.getList().stream().findFirst().orElse(null);
												sqlConnectionEnrollmentDesign(siteRequest2, e -> {
													if(e.succeeded()) {
														try {
															listPATCHEnrollmentDesign(apiRequest, listEnrollmentDesign, dt, f -> {
																if(f.succeeded()) {
																	patchEnrollmentDesignResponse(siteRequest2, g -> {
																												if(g.succeeded()) {
																			LOGGER.info(String.format("patchEnrollmentDesign succeeded. "));
																			blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																		} else {
																			LOGGER.error(String.format("patchEnrollmentDesign failed. ", g.cause()));
																			errorEnrollmentDesign(siteRequest2, null, g);
																		}
																	});
																} else {
																	LOGGER.error(String.format("patchEnrollmentDesign failed. ", f.cause()));
																	errorEnrollmentDesign(siteRequest2, null, f);
																}
															});
														} catch(Exception ex) {
															LOGGER.error(String.format("patchEnrollmentDesign failed. ", ex));
															errorEnrollmentDesign(siteRequest2, null, Future.failedFuture(ex));
														}
													} else {
														LOGGER.error(String.format("patchEnrollmentDesign failed. ", e.cause()));
														errorEnrollmentDesign(siteRequest2, null, e);
													}
												});
											} else {
												LOGGER.error(String.format("patchEnrollmentDesign failed. ", d.cause()));
												errorEnrollmentDesign(siteRequest2, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("patchEnrollmentDesign failed. ", ex));
										errorEnrollmentDesign(siteRequest2, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("patchEnrollmentDesign failed. ", c.cause()));
							errorEnrollmentDesign(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("patchEnrollmentDesign failed. ", b.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchEnrollmentDesign failed. ", ex));
			errorEnrollmentDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPATCHEnrollmentDesign(ApiRequest apiRequest, SearchList<EnrollmentDesign> listEnrollmentDesign, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
		listEnrollmentDesign.getList().forEach(o -> {
			futures.add(
				patchEnrollmentDesignFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						errorEnrollmentDesign(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listEnrollmentDesign.next(dt)) {
					listPATCHEnrollmentDesign(apiRequest, listEnrollmentDesign, dt, eventHandler);
				} else {
					response200PATCHEnrollmentDesign(siteRequest, eventHandler);
				}
			} else {
				LOGGER.error(String.format("listPATCHEnrollmentDesign failed. ", a.cause()));
				errorEnrollmentDesign(listEnrollmentDesign.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<EnrollmentDesign> patchEnrollmentDesignFuture(EnrollmentDesign o, Boolean inheritPk, Handler<AsyncResult<EnrollmentDesign>> eventHandler) {
		Promise<EnrollmentDesign> promise = Promise.promise();
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			if(apiRequest != null && apiRequest.getNumFound() == 1L) {
				apiRequest.setOriginal(o);
				apiRequest.setPk(o.getPk());
			}
			sqlConnectionEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					sqlTransactionEnrollmentDesign(siteRequest, b -> {
						if(b.succeeded()) {
							sqlPATCHEnrollmentDesign(o, inheritPk, c -> {
								if(c.succeeded()) {
									EnrollmentDesign enrollmentDesign = c.result();
									defineIndexEnrollmentDesign(enrollmentDesign, d -> {
										if(d.succeeded()) {
											if(apiRequest != null) {
												apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
												if(apiRequest.getNumFound() == 1L) {
													enrollmentDesign.apiRequestEnrollmentDesign();
												}
												siteRequest.getVertx().eventBus().publish("websocketEnrollmentDesign", JsonObject.mapFrom(apiRequest).toString());
											}
											eventHandler.handle(Future.succeededFuture(enrollmentDesign));
											promise.complete(enrollmentDesign);
										} else {
											LOGGER.error(String.format("patchEnrollmentDesignFuture failed. ", d.cause()));
											eventHandler.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("patchEnrollmentDesignFuture failed. ", c.cause()));
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("patchEnrollmentDesignFuture failed. ", b.cause()));
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("patchEnrollmentDesignFuture failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("patchEnrollmentDesignFuture failed. ", e));
			errorEnrollmentDesign(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHEnrollmentDesign(EnrollmentDesign o, Boolean inheritPk, Handler<AsyncResult<EnrollmentDesign>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Transaction tx = siteRequest.getTx();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			Set<String> methodNames = jsonObject.fieldNames();
			EnrollmentDesign o2 = new EnrollmentDesign();
			List<Future> futures = new ArrayList<>();

			if(o.getUserId() == null && siteRequest.getUserId() != null) {
				futures.add(Future.future(a -> {
					tx.preparedQuery(SiteContextEnUS.SQL_setD
							, Tuple.of(pk, "userId", siteRequest.getUserId())
							, b
					-> {
						if(b.succeeded())
							a.handle(Future.succeededFuture());
						else
							a.handle(Future.failedFuture(b.cause()));
					});
				}));
			}
			if(o.getUserKey() == null && siteRequest.getUserKey() != null) {
				futures.add(Future.future(a -> {
					tx.preparedQuery(SiteContextEnUS.SQL_setD
				, Tuple.of(pk, "userKey", siteRequest.getUserKey())
							, b
					-> {
						if(b.succeeded())
							a.handle(Future.succeededFuture());
						else
							a.handle(Future.failedFuture(b.cause()));
					});
				}));
			}

			for(String methodName : methodNames) {
				switch(methodName) {
					case "setInheritPk":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "inheritPk")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.inheritPk failed", b.cause())));
								});
							}));
						} else {
							o2.setInheritPk(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "inheritPk", o2.jsonInheritPk())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.inheritPk failed", b.cause())));
								});
							}));
						}
						break;
					case "setCreated":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "created")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.created failed", b.cause())));
								});
							}));
						} else {
							o2.setCreated(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "created", o2.jsonCreated())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.created failed", b.cause())));
								});
							}));
						}
						break;
					case "setModified":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "modified")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.modified failed", b.cause())));
								});
							}));
						} else {
							o2.setModified(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "modified", o2.jsonModified())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.modified failed", b.cause())));
								});
							}));
						}
						break;
					case "setArchived":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "archived")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.archived failed", b.cause())));
								});
							}));
						} else {
							o2.setArchived(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "archived", o2.jsonArchived())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.archived failed", b.cause())));
								});
							}));
						}
						break;
					case "setDeleted":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "deleted")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.deleted failed", b.cause())));
								});
							}));
						} else {
							o2.setDeleted(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "deleted", o2.jsonDeleted())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.deleted failed", b.cause())));
								});
							}));
						}
						break;
					case "setSessionId":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "sessionId")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.sessionId failed", b.cause())));
								});
							}));
						} else {
							o2.setSessionId(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "sessionId", o2.jsonSessionId())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.sessionId failed", b.cause())));
								});
							}));
						}
						break;
					case "setUserId":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "userId")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.userId failed", b.cause())));
								});
							}));
						} else {
							o2.setUserId(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "userId", o2.jsonUserId())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.userId failed", b.cause())));
								});
							}));
						}
						break;
					case "setUserKey":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "userKey")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.userKey failed", b.cause())));
								});
							}));
						} else {
							o2.setUserKey(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "userKey", o2.jsonUserKey())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.userKey failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDesignCompleteName":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDesignCompleteName")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.enrollmentDesignCompleteName failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDesignCompleteName(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDesignCompleteName", o2.jsonEnrollmentDesignCompleteName())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.enrollmentDesignCompleteName failed", b.cause())));
								});
							}));
						}
						break;
					case "setDesignHidden":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "designHidden")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.designHidden failed", b.cause())));
								});
							}));
						} else {
							o2.setDesignHidden(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "designHidden", o2.jsonDesignHidden())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value EnrollmentDesign.designHidden failed", b.cause())));
								});
							}));
						}
						break;
				}
			}
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					EnrollmentDesign o3 = new EnrollmentDesign();
					o3.setSiteRequest_(o.getSiteRequest_());
					o3.setPk(pk);
					eventHandler.handle(Future.succeededFuture(o3));
				} else {
					LOGGER.error(String.format("sqlPATCHEnrollmentDesign failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPATCHEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void patchEnrollmentDesignResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PATCHEnrollmentDesign(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("patchEnrollmentDesignResponse failed. ", a.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchEnrollmentDesignResponse failed. ", ex));
			errorEnrollmentDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PATCHEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PATCHEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getEnrollmentDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest);
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

			userEnrollmentDesign(siteRequest, b -> {
				if(b.succeeded()) {
					aSearchEnrollmentDesign(siteRequest, false, true, "/api/enrollment-design/{id}", "GET", c -> {
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
							LOGGER.error(String.format("getEnrollmentDesign failed. ", c.cause()));
							errorEnrollmentDesign(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("getEnrollmentDesign failed. ", b.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getEnrollmentDesign failed. ", ex));
			errorEnrollmentDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void getEnrollmentDesignResponse(SearchList<EnrollmentDesign> listEnrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
		try {
			response200GETEnrollmentDesign(listEnrollmentDesign, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("getEnrollmentDesignResponse failed. ", a.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getEnrollmentDesignResponse failed. ", ex));
			errorEnrollmentDesign(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200GETEnrollmentDesign(SearchList<EnrollmentDesign> listEnrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
			SolrDocumentList solrDocuments = listEnrollmentDesign.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listEnrollmentDesign.getList().stream().findFirst().orElse(null));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200GETEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchEnrollmentDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest);
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

			userEnrollmentDesign(siteRequest, b -> {
				if(b.succeeded()) {
					aSearchEnrollmentDesign(siteRequest, false, true, "/api/enrollment-design", "Search", c -> {
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
							LOGGER.error(String.format("searchEnrollmentDesign failed. ", c.cause()));
							errorEnrollmentDesign(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("searchEnrollmentDesign failed. ", b.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchEnrollmentDesign failed. ", ex));
			errorEnrollmentDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchEnrollmentDesignResponse(SearchList<EnrollmentDesign> listEnrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
		try {
			response200SearchEnrollmentDesign(listEnrollmentDesign, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("searchEnrollmentDesignResponse failed. ", a.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchEnrollmentDesignResponse failed. ", ex));
			errorEnrollmentDesign(siteRequest, null, Future.failedFuture(ex));
		}
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
			LOGGER.error(String.format("response200SearchEnrollmentDesign failed. ", e));
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
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForEnrollmentDesign(siteContext, operationRequest);
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

			userEnrollmentDesign(siteRequest, b -> {
				if(b.succeeded()) {
					aSearchEnrollmentDesign(siteRequest, false, true, "/enrollment-design", "SearchPage", c -> {
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
							LOGGER.error(String.format("searchpageEnrollmentDesign failed. ", c.cause()));
							errorEnrollmentDesign(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("searchpageEnrollmentDesign failed. ", b.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageEnrollmentDesign failed. ", ex));
			errorEnrollmentDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchpageEnrollmentDesignResponse(SearchList<EnrollmentDesign> listEnrollmentDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listEnrollmentDesign.getSiteRequest_();
		try {
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(siteRequest, buffer);
			siteRequest.setW(w);
			response200SearchPageEnrollmentDesign(listEnrollmentDesign, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("searchpageEnrollmentDesignResponse failed. ", a.cause()));
					errorEnrollmentDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageEnrollmentDesignResponse failed. ", ex));
			errorEnrollmentDesign(siteRequest, null, Future.failedFuture(ex));
		}
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
			LOGGER.error(String.format("response200SearchPageEnrollmentDesign failed. ", e));
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
								sqlCommitEnrollmentDesign(siteRequest, f -> {
									if(f.succeeded()) {
										sqlCloseEnrollmentDesign(siteRequest, g -> {
											if(g.succeeded()) {
												refreshEnrollmentDesign(enrollmentDesign, h -> {
													if(h.succeeded()) {
														eventHandler.handle(Future.succeededFuture(enrollmentDesign));
														promise.complete(enrollmentDesign);
													} else {
														LOGGER.error(String.format("refreshEnrollmentDesign failed. ", h.cause()));
														errorEnrollmentDesign(siteRequest, null, h);
													}
												});
											} else {
												LOGGER.error(String.format("defineIndexEnrollmentDesign failed. ", g.cause()));
												errorEnrollmentDesign(siteRequest, null, g);
											}
										});
									} else {
										LOGGER.error(String.format("defineIndexEnrollmentDesign failed. ", f.cause()));
										errorEnrollmentDesign(siteRequest, null, f);
									}
								});
							} else {
								LOGGER.error(String.format("defineIndexEnrollmentDesign failed. ", e.cause()));
								errorEnrollmentDesign(siteRequest, null, e);
							}
						});
					} else {
						LOGGER.error(String.format("defineIndexEnrollmentDesign failed. ", d.cause()));
						errorEnrollmentDesign(siteRequest, null, d);
					}
				});
			} else {
				LOGGER.error(String.format("defineIndexEnrollmentDesign failed. ", c.cause()));
				errorEnrollmentDesign(siteRequest, null, c);
			}
		});
		return promise.future();
	}

	public void createEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<EnrollmentDesign>> eventHandler) {
		try {
			Transaction tx = siteRequest.getTx();
			String userId = siteRequest.getUserId();

			tx.preparedQuery(
					SiteContextEnUS.SQL_create
					, Tuple.of(EnrollmentDesign.class.getCanonicalName(), userId)
					, Collectors.toList()
					, createAsync
			-> {
				Row createLine = createAsync.result().value().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				EnrollmentDesign o = new EnrollmentDesign();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			LOGGER.error(String.format("createEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void errorEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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
		sqlRollbackEnrollmentDesign(siteRequest, a -> {
			if(a.succeeded()) {
				LOGGER.info(String.format("sql rollback. "));
				sqlCloseEnrollmentDesign(siteRequest, b -> {
					if(b.succeeded()) {
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
	}

	public void sqlConnectionEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			PgPool pgPool = siteRequest.getSiteContext_().getPgPool();

			if(pgPool == null) {
				eventHandler.handle(Future.succeededFuture());
			} else {
				pgPool.getConnection(a -> {
					if(a.succeeded()) {
						SqlConnection sqlConnection = a.result();
						siteRequest.setSqlConnection(sqlConnection);
						eventHandler.handle(Future.succeededFuture());
					} else {
						LOGGER.error(String.format("sqlConnectionEnrollmentDesign failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlTransactionEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SqlConnection sqlConnection = siteRequest.getSqlConnection();

			if(sqlConnection == null) {
				eventHandler.handle(Future.succeededFuture());
			} else {
				Transaction tx = sqlConnection.begin();
				siteRequest.setTx(tx);
				eventHandler.handle(Future.succeededFuture());
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlTransactionEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlCommitEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			Transaction tx = siteRequest.getTx();

			if(tx == null) {
				eventHandler.handle(Future.succeededFuture());
			} else {
				tx.commit(a -> {
					if(a.succeeded()) {
						siteRequest.setTx(null);
						eventHandler.handle(Future.succeededFuture());
					} else if("Transaction already completed".equals(a.cause().getMessage())) {
						siteRequest.setTx(null);
						eventHandler.handle(Future.succeededFuture());
					} else {
						LOGGER.error(String.format("sqlCommitEnrollmentDesign failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlRollbackEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			Transaction tx = siteRequest.getTx();

			if(tx == null) {
				eventHandler.handle(Future.succeededFuture());
			} else {
				tx.rollback(a -> {
					if(a.succeeded()) {
						siteRequest.setTx(null);
						eventHandler.handle(Future.succeededFuture());
					} else if("Transaction already completed".equals(a.cause().getMessage())) {
						siteRequest.setTx(null);
						eventHandler.handle(Future.succeededFuture());
					} else {
						LOGGER.error(String.format("sqlRollbackEnrollmentDesign failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlCloseEnrollmentDesign(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SqlConnection sqlConnection = siteRequest.getSqlConnection();

			if(sqlConnection == null) {
				eventHandler.handle(Future.succeededFuture());
			} else {
				sqlConnection.close();
				siteRequest.setSqlConnection(null);
				eventHandler.handle(Future.succeededFuture());
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlCloseEnrollmentDesign failed. ", e));
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
			String userId = siteRequest.getUserId();
			if(userId == null) {
				eventHandler.handle(Future.succeededFuture());
			} else {
				sqlConnectionEnrollmentDesign(siteRequest, a -> {
					if(a.succeeded()) {
						sqlTransactionEnrollmentDesign(siteRequest, b -> {
							if(b.succeeded()) {
								Transaction tx = siteRequest.getTx();
								tx.preparedQuery(
										SiteContextEnUS.SQL_selectC
										, Tuple.of("org.computate.scolaire.enUS.user.SiteUser", userId)
										, Collectors.toList()
										, selectCAsync
								-> {
									if(selectCAsync.succeeded()) {
										try {
											Row userValues = selectCAsync.result().value().stream().findFirst().orElse(null);
											SiteUserEnUSApiServiceImpl userService = new SiteUserEnUSApiServiceImpl(siteContext);
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
												siteRequest2.setTx(siteRequest.getTx());
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

												userService.createSiteUser(siteRequest2, c -> {
													if(c.succeeded()) {
														SiteUser siteUser = c.result();
														userService.sqlPOSTSiteUser(siteUser, false, d -> {
															if(d.succeeded()) {
																userService.defineIndexSiteUser(siteUser, e -> {
																	if(e.succeeded()) {
																		siteRequest.setSiteUser(siteUser);
																		siteRequest.setUserName(jsonPrincipal.getString("preferred_username"));
																		siteRequest.setUserFirstName(jsonPrincipal.getString("given_name"));
																		siteRequest.setUserLastName(jsonPrincipal.getString("family_name"));
																		siteRequest.setUserId(jsonPrincipal.getString("sub"));
																		siteRequest.setUserKey(siteUser.getPk());
																		eventHandler.handle(Future.succeededFuture());
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
													siteRequest2.setTx(siteRequest.getTx());
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

													userService.sqlPATCHSiteUser(siteUser, false, d -> {
														if(d.succeeded()) {
															SiteUser siteUser2 = d.result();
															userService.defineIndexSiteUser(siteUser2, e -> {
																if(e.succeeded()) {
																	siteRequest.setSiteUser(siteUser2);
																	siteRequest.setUserName(siteUser2.getUserName());
																	siteRequest.setUserFirstName(siteUser2.getUserFirstName());
																	siteRequest.setUserLastName(siteUser2.getUserLastName());
																	siteRequest.setUserId(siteUser2.getUserId());
																	siteRequest.setUserKey(siteUser2.getPk());
																	eventHandler.handle(Future.succeededFuture());
																} else {
																	errorEnrollmentDesign(siteRequest, eventHandler, e);
																}
															});
														} else {
															errorEnrollmentDesign(siteRequest, eventHandler, d);
														}
													});
												} else {
													siteRequest.setSiteUser(siteUser1);
													siteRequest.setUserName(siteUser1.getUserName());
													siteRequest.setUserFirstName(siteUser1.getUserFirstName());
													siteRequest.setUserLastName(siteUser1.getUserLastName());
													siteRequest.setUserId(siteUser1.getUserId());
													siteRequest.setUserKey(siteUser1.getPk());
													sqlRollbackEnrollmentDesign(siteRequest, c -> {
														if(c.succeeded()) {
															eventHandler.handle(Future.succeededFuture());
														} else {
															eventHandler.handle(Future.failedFuture(c.cause()));
															errorEnrollmentDesign(siteRequest, eventHandler, c);
														}
													});
												}
											}
										} catch(Exception e) {
											LOGGER.error(String.format("userEnrollmentDesign failed. ", e));
											eventHandler.handle(Future.failedFuture(e));
										}
									} else {
										LOGGER.error(String.format("userEnrollmentDesign failed. ", selectCAsync.cause()));
										eventHandler.handle(Future.failedFuture(selectCAsync.cause()));
									}
								});
							} else {
								LOGGER.error(String.format("userEnrollmentDesign failed. ", b.cause()));
								eventHandler.handle(Future.failedFuture(b.cause()));
							}
						});
					} else {
						LOGGER.error(String.format("userEnrollmentDesign failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("userEnrollmentDesign failed. ", e));
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

	public void aSearchEnrollmentDesignQ(String uri, String apiMethod, SearchList<EnrollmentDesign> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
			searchList.setHighlight(true);
			searchList.setHighlightSnippets(3);
			searchList.addHighlightField(varIndexed);
			searchList.setParam("hl.encoder", "html");
		}
	}

	public void aSearchEnrollmentDesignFq(String uri, String apiMethod, SearchList<EnrollmentDesign> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
	}

	public void aSearchEnrollmentDesignSort(String uri, String apiMethod, SearchList<EnrollmentDesign> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addSort(varIndexed, ORDER.valueOf(valueIndexed));
	}

	public void aSearchEnrollmentDesignRows(String uri, String apiMethod, SearchList<EnrollmentDesign> searchList, Integer valueRows) {
			searchList.setRows(apiMethod != null && apiMethod.contains("Search") ? valueRows : 10);
	}

	public void aSearchEnrollmentDesignStart(String uri, String apiMethod, SearchList<EnrollmentDesign> searchList, Integer valueStart) {
		searchList.setStart(valueStart);
	}

	public void aSearchEnrollmentDesignVar(String uri, String apiMethod, SearchList<EnrollmentDesign> searchList, String var, String value) {
		searchList.getSiteRequest_().getRequestVars().put(var, value);
	}

	public void aSearchEnrollmentDesignUri(String uri, String apiMethod, SearchList<EnrollmentDesign> searchList) {
	}

	public void aSearchEnrollmentDesign(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String uri, String apiMethod, Handler<AsyncResult<SearchList<EnrollmentDesign>>> eventHandler) {
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
								aSearchEnrollmentDesignQ(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "fq":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = EnrollmentDesign.varIndexedEnrollmentDesign(entityVar);
								aSearchEnrollmentDesignFq(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = EnrollmentDesign.varIndexedEnrollmentDesign(entityVar);
								aSearchEnrollmentDesignSort(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "start":
								valueStart = (Integer)paramObject;
								aSearchEnrollmentDesignStart(uri, apiMethod, searchList, valueStart);
								break;
							case "rows":
								valueRows = (Integer)paramObject;
								aSearchEnrollmentDesignRows(uri, apiMethod, searchList, valueRows);
								break;
							case "var":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								aSearchEnrollmentDesignVar(uri, apiMethod, searchList, entityVar, valueIndexed);
								break;
						}
					}
					aSearchEnrollmentDesignUri(uri, apiMethod, searchList);
				} catch(Exception e) {
					LOGGER.error(String.format("aSearchEnrollmentDesign failed. ", e));
					eventHandler.handle(Future.failedFuture(e));
				}
			});
			if(searchList.getSorts().size() == 0) {
				searchList.addSort("created_indexed_date", ORDER.desc);
			}
			searchList.initDeepForClass(siteRequest);
			eventHandler.handle(Future.succeededFuture(searchList));
		} catch(Exception e) {
			LOGGER.error(String.format("aSearchEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void defineEnrollmentDesign(EnrollmentDesign o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			Transaction tx = siteRequest.getTx();
			Long pk = o.getPk();
			tx.preparedQuery(
					SiteContextEnUS.SQL_define
					, Tuple.of(pk)
					, Collectors.toList()
					, defineAsync
			-> {
				if(defineAsync.succeeded()) {
					try {
						for(Row definition : defineAsync.result().value()) {
							try {
								o.defineForClass(definition.getString(0), definition.getString(1));
							} catch(Exception e) {
								LOGGER.error(e);
							}
						}
						eventHandler.handle(Future.succeededFuture());
					} catch(Exception e) {
						LOGGER.error(String.format("defineEnrollmentDesign failed. ", e));
						eventHandler.handle(Future.failedFuture(e));
					}
				} else {
					LOGGER.error(String.format("defineEnrollmentDesign failed. ", defineAsync.cause()));
					eventHandler.handle(Future.failedFuture(defineAsync.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("defineEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void attributeEnrollmentDesign(EnrollmentDesign o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			Transaction tx = siteRequest.getTx();
			Long pk = o.getPk();
			tx.preparedQuery(
					SiteContextEnUS.SQL_attribute
					, Tuple.of(pk, pk)
					, Collectors.toList()
					, attributeAsync
			-> {
				try {
					if(attributeAsync.succeeded()) {
						if(attributeAsync.result() != null) {
							for(Row definition : attributeAsync.result().value()) {
								if(pk.equals(definition.getLong(0)))
									o.attributeForClass(definition.getString(2), definition.getLong(1));
								else
									o.attributeForClass(definition.getString(3), definition.getLong(0));
							}
						}
						eventHandler.handle(Future.succeededFuture());
					} else {
						LOGGER.error(String.format("attributeEnrollmentDesign failed. ", attributeAsync.cause()));
						eventHandler.handle(Future.failedFuture(attributeAsync.cause()));
					}
				} catch(Exception e) {
					LOGGER.error(String.format("attributeEnrollmentDesign failed. ", e));
					eventHandler.handle(Future.failedFuture(e));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("attributeEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void indexEnrollmentDesign(EnrollmentDesign o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			eventHandler.handle(Future.succeededFuture());
		} catch(Exception e) {
			LOGGER.error(String.format("indexEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void refreshEnrollmentDesign(EnrollmentDesign o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			if(BooleanUtils.isFalse(Optional.ofNullable(siteRequest.getApiRequest_()).map(ApiRequest::getEmpty).orElse(true))) {
				SearchList<EnrollmentDesign> searchList = new SearchList<EnrollmentDesign>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(EnrollmentDesign.class);
				searchList.addFilterQuery("modified_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(siteRequest.getApiRequest_().getCreated().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				searchList.setRows(1000);
				searchList.initDeepSearchList(siteRequest);
				List<Future> futures = new ArrayList<>();

				for(int i=0; i < pks.size(); i++) {
					Long pk2 = pks.get(i);
					String classSimpleName2 = classes.get(i);
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						EnrollmentDesignEnUSApiServiceImpl service = new EnrollmentDesignEnUSApiServiceImpl(siteRequest.getSiteContext_());
						List<Future> futures2 = new ArrayList<>();
						for(EnrollmentDesign o2 : searchList.getList()) {
							SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForEnrollmentDesign(siteContext, siteRequest.getOperationRequest(), new JsonObject());
							o2.setSiteRequest_(siteRequest2);
							futures2.add(
								service.patchEnrollmentDesignFuture(o2, false, b -> {
									if(b.succeeded()) {
									} else {
										LOGGER.info(String.format("EnrollmentDesign %s failed. ", o2.getPk()));
										eventHandler.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								eventHandler.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Refresh relations failed. ", b.cause());
								errorEnrollmentDesign(siteRequest, eventHandler, b);
							}
						});
					} else {
						LOGGER.error("Refresh relations failed. ", a.cause());
						errorEnrollmentDesign(siteRequest, eventHandler, a);
					}
				});
			} else {
				eventHandler.handle(Future.succeededFuture());
			}
		} catch(Exception e) {
			LOGGER.error(String.format("refreshEnrollmentDesign failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}
}
