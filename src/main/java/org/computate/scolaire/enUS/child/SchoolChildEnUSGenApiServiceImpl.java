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
import java.math.RoundingMode;
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
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest, body);
		siteRequest.setRequestUri("/api/child");
		siteRequest.setRequestMethod("POST");
		try {
			LOGGER.info(String.format("postSchoolChild started. "));
			{
				userSchoolChild(siteRequest, b -> {
					if(b.succeeded()) {
						ApiRequest apiRequest = new ApiRequest();
						apiRequest.setRows(1);
						apiRequest.setNumFound(1L);
						apiRequest.setNumPATCH(0L);
						apiRequest.initDeepApiRequest(siteRequest);
						siteRequest.setApiRequest_(apiRequest);
						siteRequest.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
						postSchoolChildFuture(siteRequest, false, c -> {
							if(c.succeeded()) {
								SchoolChild schoolChild = c.result();
								apiRequest.setPk(schoolChild.getPk());
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
								LOGGER.error(String.format("postSchoolChild failed. ", c.cause()));
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						LOGGER.error(String.format("postSchoolChild failed. ", b.cause()));
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			}
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchoolChild failed. ", ex));
			errorSchoolChild(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public Future<SchoolChild> postSchoolChildFuture(SiteRequestEnUS siteRequest, Boolean inheritPk, Handler<AsyncResult<SchoolChild>> eventHandler) {
		Promise<SchoolChild> promise = Promise.promise();
		try {
			sqlConnectionSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					sqlTransactionSchoolChild(siteRequest, b -> {
						if(b.succeeded()) {
							createSchoolChild(siteRequest, c -> {
								if(c.succeeded()) {
									SchoolChild schoolChild = c.result();
									sqlPOSTSchoolChild(schoolChild, inheritPk, d -> {
										if(d.succeeded()) {
											defineIndexSchoolChild(schoolChild, e -> {
												if(e.succeeded()) {
													ApiRequest apiRequest = siteRequest.getApiRequest_();
													if(apiRequest != null) {
														apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
														schoolChild.apiRequestSchoolChild();
														siteRequest.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
													}
													eventHandler.handle(Future.succeededFuture(schoolChild));
													promise.complete(schoolChild);
												} else {
													LOGGER.error(String.format("postSchoolChildFuture failed. ", e.cause()));
													eventHandler.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											LOGGER.error(String.format("postSchoolChildFuture failed. ", d.cause()));
											eventHandler.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("postSchoolChildFuture failed. ", c.cause()));
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("postSchoolChildFuture failed. ", b.cause()));
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("postSchoolChildFuture failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("postSchoolChildFuture failed. ", e));
			errorSchoolChild(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTSchoolChild(SchoolChild o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
				, Tuple.of(pk, "userKey", siteRequest.getUserKey().toString())
							, b
					-> {
						if(b.succeeded())
							a.handle(Future.succeededFuture());
						else
							a.handle(Future.failedFuture(b.cause()));
					});
				}));

				JsonArray userKeys = Optional.ofNullable(jsonObject.getJsonArray("userKeys")).orElse(null);
				if(userKeys != null && !userKeys.contains(siteRequest.getUserKey()))
					userKeys.add(siteRequest.getUserKey().toString());
				else
					jsonObject.put("userKeys", new JsonArray().add(siteRequest.getUserKey().toString()));
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
									a.handle(Future.failedFuture(new Exception("value SchoolChild.inheritPk failed", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("value SchoolChild.archived failed", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("value SchoolChild.deleted failed", b.cause())));
							});
						}));
						break;
					case "enrollmentKeys":
						for(Long l : Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolEnrollment.class);
								searchList.addFilterQuery("deleted_indexed_boolean:false");
								searchList.addFilterQuery("archived_indexed_boolean:false");
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "childKey", pk, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolChild.enrollmentKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolEnrollment");
									}
								}
							}
						}
						break;
					case "personFirstName":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "personFirstName", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolChild.personFirstName failed", b.cause())));
							});
						}));
						break;
					case "personFirstNamePreferred":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "personFirstNamePreferred", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolChild.personFirstNamePreferred failed", b.cause())));
							});
						}));
						break;
					case "familyName":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "familyName", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolChild.familyName failed", b.cause())));
							});
						}));
						break;
					case "personBirthDate":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "personBirthDate", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolChild.personBirthDate failed", b.cause())));
							});
						}));
						break;
					case "photo":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "photo", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolChild.photo failed", b.cause())));
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
					LOGGER.error(String.format("sqlPOSTSchoolChild failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPOSTSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void postSchoolChildResponse(SchoolChild schoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = schoolChild.getSiteRequest_();
		try {
			response200POSTSchoolChild(schoolChild, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("postSchoolChildResponse failed. ", a.cause()));
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchoolChildResponse failed. ", ex));
			errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200POSTSchoolChild(SchoolChild o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(o);
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200POSTSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTImport //

	@Override
	public void putimportSchoolChild(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest, body);
		siteRequest.setRequestUri("/api/child/import");
		siteRequest.setRequestMethod("PUTImport");
		try {
			LOGGER.info(String.format("putimportSchoolChild started. "));
			{
				userSchoolChild(siteRequest, b -> {
					if(b.succeeded()) {
						putimportSchoolChildResponse(siteRequest, c -> {
							if(c.succeeded()) {
								eventHandler.handle(Future.succeededFuture(c.result()));
								WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
								workerExecutor.executeBlocking(
									blockingCodeHandler -> {
										try {
											ApiRequest apiRequest = new ApiRequest();
											JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
											apiRequest.setRows(jsonArray.size());
											apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
											apiRequest.setNumPATCH(0L);
											apiRequest.initDeepApiRequest(siteRequest);
											siteRequest.setApiRequest_(apiRequest);
											siteRequest.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
											varsSchoolChild(siteRequest, d -> {
												if(d.succeeded()) {
													listPUTImportSchoolChild(apiRequest, siteRequest, e -> {
														if(e.succeeded()) {
															putimportSchoolChildResponse(siteRequest, f -> {
																if(e.succeeded()) {
																	LOGGER.info(String.format("putimportSchoolChild succeeded. "));
																	blockingCodeHandler.handle(Future.succeededFuture(e.result()));
																} else {
																	LOGGER.error(String.format("putimportSchoolChild failed. ", f.cause()));
																	errorSchoolChild(siteRequest, null, f);
																}
															});
														} else {
															LOGGER.error(String.format("putimportSchoolChild failed. ", e.cause()));
															errorSchoolChild(siteRequest, null, e);
														}
													});
												} else {
													LOGGER.error(String.format("putimportSchoolChild failed. ", d.cause()));
													errorSchoolChild(siteRequest, null, d);
												}
											});
										} catch(Exception ex) {
											LOGGER.error(String.format("putimportSchoolChild failed. ", ex));
											errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
										}
									}, resultHandler -> {
									}
								);
							} else {
								LOGGER.error(String.format("putimportSchoolChild failed. ", c.cause()));
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						LOGGER.error(String.format("putimportSchoolChild failed. ", b.cause()));
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			}
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolChild failed. ", ex));
			errorSchoolChild(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTImportSchoolChild(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				json.put("created", json.getValue("created"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolChild(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setApiRequest_(apiRequest);
				siteRequest2.setRequestVars(siteRequest.getRequestVars());

				SearchList<SchoolChild> searchList = new SearchList<SchoolChild>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolChild.class);
				searchList.addFilterQuery("deleted_indexed_boolean:false");
				searchList.addFilterQuery("archived_indexed_boolean:false");
				searchList.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					SchoolChild o = searchList.getList().stream().findFirst().orElse(null);
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
							patchSchoolChildFuture(o, true, a -> {
								if(a.succeeded()) {
								} else {
									LOGGER.error(String.format("listPUTImportSchoolChild failed. ", a.cause()));
									errorSchoolChild(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolChildFuture(siteRequest2, true, a -> {
							if(a.succeeded()) {
							} else {
								LOGGER.error(String.format("listPUTImportSchoolChild failed. ", a.cause()));
								errorSchoolChild(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
					response200PUTImportSchoolChild(siteRequest, eventHandler);
				} else {
					LOGGER.error(String.format("listPUTImportSchoolChild failed. ", a.cause()));
					errorSchoolChild(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("listPUTImportSchoolChild failed. ", ex));
			errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putimportSchoolChildResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTImportSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putimportSchoolChildResponse failed. ", a.cause()));
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolChildResponse failed. ", ex));
			errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTImportSchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PUTImportSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTMerge //

	@Override
	public void putmergeSchoolChild(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest, body);
		siteRequest.setRequestUri("/api/child/merge");
		siteRequest.setRequestMethod("PUTMerge");
		try {
			LOGGER.info(String.format("putmergeSchoolChild started. "));
			{
				userSchoolChild(siteRequest, b -> {
					if(b.succeeded()) {
						putmergeSchoolChildResponse(siteRequest, c -> {
							if(c.succeeded()) {
								eventHandler.handle(Future.succeededFuture(c.result()));
								WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
								workerExecutor.executeBlocking(
									blockingCodeHandler -> {
										try {
											ApiRequest apiRequest = new ApiRequest();
											JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
											apiRequest.setRows(jsonArray.size());
											apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
											apiRequest.setNumPATCH(0L);
											apiRequest.initDeepApiRequest(siteRequest);
											siteRequest.setApiRequest_(apiRequest);
											siteRequest.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
											varsSchoolChild(siteRequest, d -> {
												if(d.succeeded()) {
													listPUTMergeSchoolChild(apiRequest, siteRequest, e -> {
														if(e.succeeded()) {
															putmergeSchoolChildResponse(siteRequest, f -> {
																if(e.succeeded()) {
																	LOGGER.info(String.format("putmergeSchoolChild succeeded. "));
																	blockingCodeHandler.handle(Future.succeededFuture(e.result()));
																} else {
																	LOGGER.error(String.format("putmergeSchoolChild failed. ", f.cause()));
																	errorSchoolChild(siteRequest, null, f);
																}
															});
														} else {
															LOGGER.error(String.format("putmergeSchoolChild failed. ", e.cause()));
															errorSchoolChild(siteRequest, null, e);
														}
													});
												} else {
													LOGGER.error(String.format("putmergeSchoolChild failed. ", d.cause()));
													errorSchoolChild(siteRequest, null, d);
												}
											});
										} catch(Exception ex) {
											LOGGER.error(String.format("putmergeSchoolChild failed. ", ex));
											errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
										}
									}, resultHandler -> {
									}
								);
							} else {
								LOGGER.error(String.format("putmergeSchoolChild failed. ", c.cause()));
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						LOGGER.error(String.format("putmergeSchoolChild failed. ", b.cause()));
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			}
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolChild failed. ", ex));
			errorSchoolChild(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTMergeSchoolChild(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolChild(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setApiRequest_(apiRequest);
				siteRequest2.setRequestVars(siteRequest.getRequestVars());

				SearchList<SchoolChild> searchList = new SearchList<SchoolChild>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolChild.class);
				searchList.addFilterQuery("deleted_indexed_boolean:false");
				searchList.addFilterQuery("archived_indexed_boolean:false");
				searchList.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					SchoolChild o = searchList.getList().stream().findFirst().orElse(null);
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
							patchSchoolChildFuture(o, false, a -> {
								if(a.succeeded()) {
								} else {
									LOGGER.error(String.format("listPUTMergeSchoolChild failed. ", a.cause()));
									errorSchoolChild(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolChildFuture(siteRequest2, false, a -> {
							if(a.succeeded()) {
							} else {
								LOGGER.error(String.format("listPUTMergeSchoolChild failed. ", a.cause()));
								errorSchoolChild(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
					response200PUTMergeSchoolChild(siteRequest, eventHandler);
				} else {
					LOGGER.error(String.format("listPUTMergeSchoolChild failed. ", a.cause()));
					errorSchoolChild(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("listPUTMergeSchoolChild failed. ", ex));
			errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putmergeSchoolChildResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTMergeSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putmergeSchoolChildResponse failed. ", a.cause()));
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolChildResponse failed. ", ex));
			errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTMergeSchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PUTMergeSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTCopy //

	@Override
	public void putcopySchoolChild(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest, body);
		siteRequest.setRequestUri("/api/child/copy");
		siteRequest.setRequestMethod("PUTCopy");
		try {
			LOGGER.info(String.format("putcopySchoolChild started. "));
			{
				userSchoolChild(siteRequest, b -> {
					if(b.succeeded()) {
						putcopySchoolChildResponse(siteRequest, c -> {
							if(c.succeeded()) {
								eventHandler.handle(Future.succeededFuture(c.result()));
								WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
								workerExecutor.executeBlocking(
									blockingCodeHandler -> {
										try {
											aSearchSchoolChild(siteRequest, false, true, "/api/child/copy", "PUTCopy", d -> {
												if(d.succeeded()) {
													SearchList<SchoolChild> listSchoolChild = d.result();
													ApiRequest apiRequest = new ApiRequest();
													apiRequest.setRows(listSchoolChild.getRows());
													apiRequest.setNumFound(listSchoolChild.getQueryResponse().getResults().getNumFound());
													apiRequest.setNumPATCH(0L);
													apiRequest.initDeepApiRequest(siteRequest);
													siteRequest.setApiRequest_(apiRequest);
													siteRequest.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
													try {
														listPUTCopySchoolChild(apiRequest, listSchoolChild, e -> {
															if(e.succeeded()) {
																putcopySchoolChildResponse(siteRequest, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putcopySchoolChild succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putcopySchoolChild failed. ", f.cause()));
																		errorSchoolChild(siteRequest, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putcopySchoolChild failed. ", e.cause()));
																errorSchoolChild(siteRequest, null, e);
															}
														});
													} catch(Exception ex) {
														LOGGER.error(String.format("putcopySchoolChild failed. ", ex));
														errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
													}
												} else {
													LOGGER.error(String.format("putcopySchoolChild failed. ", d.cause()));
													errorSchoolChild(siteRequest, null, d);
												}
											});
										} catch(Exception ex) {
											LOGGER.error(String.format("putcopySchoolChild failed. ", ex));
											errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
										}
									}, resultHandler -> {
									}
								);
							} else {
								LOGGER.error(String.format("putcopySchoolChild failed. ", c.cause()));
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						LOGGER.error(String.format("putcopySchoolChild failed. ", b.cause()));
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			}
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchoolChild failed. ", ex));
			errorSchoolChild(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTCopySchoolChild(ApiRequest apiRequest, SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		listSchoolChild.getList().forEach(o -> {
			SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolChild(siteContext, siteRequest.getOperationRequest(), siteRequest.getJsonObject());
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			o.setSiteRequest_(siteRequest2);
			futures.add(
				putcopySchoolChildFuture(siteRequest2, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
					} else {
						LOGGER.error(String.format("listPUTCopySchoolChild failed. ", a.cause()));
						errorSchoolChild(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchoolChild.size());
				if(listSchoolChild.next()) {
					listPUTCopySchoolChild(apiRequest, listSchoolChild, eventHandler);
				} else {
					response200PUTCopySchoolChild(siteRequest, eventHandler);
				}
			} else {
				LOGGER.error(String.format("listPUTCopySchoolChild failed. ", a.cause()));
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
				if(o.getValue() == null)
					jsonObject.remove(o.getKey());
				else
					jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("saves").add(o.getKey());
			});

			sqlConnectionSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					sqlTransactionSchoolChild(siteRequest, b -> {
						if(b.succeeded()) {
							createSchoolChild(siteRequest, c -> {
								if(c.succeeded()) {
									SchoolChild schoolChild = c.result();
									sqlPUTCopySchoolChild(schoolChild, jsonObject, d -> {
										if(d.succeeded()) {
											defineIndexSchoolChild(schoolChild, e -> {
												if(e.succeeded()) {
													ApiRequest apiRequest = siteRequest.getApiRequest_();
													if(apiRequest != null) {
														apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
														if(apiRequest.getNumFound() == 1L) {
															schoolChild.apiRequestSchoolChild();
														}
														siteRequest.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
													}
													eventHandler.handle(Future.succeededFuture(schoolChild));
													promise.complete(schoolChild);
												} else {
													LOGGER.error(String.format("putcopySchoolChildFuture failed. ", e.cause()));
													eventHandler.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											LOGGER.error(String.format("putcopySchoolChildFuture failed. ", d.cause()));
											eventHandler.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("putcopySchoolChildFuture failed. ", c.cause()));
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("putcopySchoolChildFuture failed. ", b.cause()));
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("putcopySchoolChildFuture failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("putcopySchoolChildFuture failed. ", e));
			errorSchoolChild(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopySchoolChild(SchoolChild o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
									a.handle(Future.failedFuture(new Exception("value SchoolChild.inheritPk failed", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("value SchoolChild.archived failed", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("value SchoolChild.deleted failed", b.cause())));
							});
						}));
						break;
					case "enrollmentKeys":
						for(Long l : Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_addA
										, Tuple.of(l, "childKey", pk, "enrollmentKeys")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolChild.enrollmentKeys failed", b.cause())));
								});
							}));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("SchoolEnrollment");
							}
						}
						break;
					case "personFirstName":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "personFirstName", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolChild.personFirstName failed", b.cause())));
							});
						}));
						break;
					case "personFirstNamePreferred":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "personFirstNamePreferred", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolChild.personFirstNamePreferred failed", b.cause())));
							});
						}));
						break;
					case "familyName":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "familyName", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolChild.familyName failed", b.cause())));
							});
						}));
						break;
					case "personBirthDate":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "personBirthDate", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolChild.personBirthDate failed", b.cause())));
							});
						}));
						break;
					case "photo":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "photo", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolChild.photo failed", b.cause())));
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
					LOGGER.error(String.format("sqlPUTCopySchoolChild failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPUTCopySchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void putcopySchoolChildResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTCopySchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putcopySchoolChildResponse failed. ", a.cause()));
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchoolChildResponse failed. ", ex));
			errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTCopySchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PUTCopySchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSchoolChild(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest, body);
		siteRequest.setRequestUri("/api/child");
		siteRequest.setRequestMethod("PATCH");
		try {
			LOGGER.info(String.format("patchSchoolChild started. "));
			{
				userSchoolChild(siteRequest, b -> {
					if(b.succeeded()) {
						patchSchoolChildResponse(siteRequest, c -> {
							if(c.succeeded()) {
								eventHandler.handle(Future.succeededFuture(c.result()));
								WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
								workerExecutor.executeBlocking(
									blockingCodeHandler -> {
										try {
											aSearchSchoolChild(siteRequest, false, true, "/api/child", "PATCH", d -> {
												if(d.succeeded()) {
													SearchList<SchoolChild> listSchoolChild = d.result();
													ApiRequest apiRequest = new ApiRequest();
													apiRequest.setRows(listSchoolChild.getRows());
													apiRequest.setNumFound(listSchoolChild.getQueryResponse().getResults().getNumFound());
													apiRequest.setNumPATCH(0L);
													apiRequest.initDeepApiRequest(siteRequest);
													siteRequest.setApiRequest_(apiRequest);
													siteRequest.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
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

													try {
														listPATCHSchoolChild(apiRequest, listSchoolChild, dt, e -> {
															if(e.succeeded()) {
																patchSchoolChildResponse(siteRequest, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("patchSchoolChild succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("patchSchoolChild failed. ", f.cause()));
																		errorSchoolChild(siteRequest, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("patchSchoolChild failed. ", e.cause()));
																errorSchoolChild(siteRequest, null, e);
															}
														});
													} catch(Exception ex) {
														LOGGER.error(String.format("patchSchoolChild failed. ", ex));
														errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
													}
										} else {
													LOGGER.error(String.format("patchSchoolChild failed. ", d.cause()));
													errorSchoolChild(siteRequest, null, d);
												}
											});
										} catch(Exception ex) {
											LOGGER.error(String.format("patchSchoolChild failed. ", ex));
											errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
										}
									}, resultHandler -> {
									}
								);
							} else {
								LOGGER.error(String.format("patchSchoolChild failed. ", c.cause()));
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						LOGGER.error(String.format("patchSchoolChild failed. ", b.cause()));
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			}
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchoolChild failed. ", ex));
			errorSchoolChild(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPATCHSchoolChild(ApiRequest apiRequest, SearchList<SchoolChild> listSchoolChild, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		listSchoolChild.getList().forEach(o -> {
			SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolChild(siteContext, siteRequest.getOperationRequest(), siteRequest.getJsonObject());
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			o.setSiteRequest_(siteRequest2);
			futures.add(
				patchSchoolChildFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						errorSchoolChild(siteRequest2, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listSchoolChild.next(dt)) {
					listPATCHSchoolChild(apiRequest, listSchoolChild, dt, eventHandler);
				} else {
					response200PATCHSchoolChild(siteRequest, eventHandler);
				}
			} else {
				LOGGER.error(String.format("listPATCHSchoolChild failed. ", a.cause()));
				errorSchoolChild(listSchoolChild.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolChild> patchSchoolChildFuture(SchoolChild o, Boolean inheritPk, Handler<AsyncResult<SchoolChild>> eventHandler) {
		Promise<SchoolChild> promise = Promise.promise();
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			if(apiRequest != null && apiRequest.getNumFound() == 1L) {
				apiRequest.setOriginal(o);
				apiRequest.setPk(o.getPk());
			}
			sqlConnectionSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					sqlTransactionSchoolChild(siteRequest, b -> {
						if(b.succeeded()) {
							sqlPATCHSchoolChild(o, inheritPk, c -> {
								if(c.succeeded()) {
									SchoolChild schoolChild = c.result();
									defineIndexSchoolChild(schoolChild, d -> {
										if(d.succeeded()) {
											if(apiRequest != null) {
												apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
												if(apiRequest.getNumFound() == 1L) {
													schoolChild.apiRequestSchoolChild();
												}
												siteRequest.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());
											}
											eventHandler.handle(Future.succeededFuture(schoolChild));
											promise.complete(schoolChild);
										} else {
											LOGGER.error(String.format("patchSchoolChildFuture failed. ", d.cause()));
											eventHandler.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("patchSchoolChildFuture failed. ", c.cause()));
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("patchSchoolChildFuture failed. ", b.cause()));
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("patchSchoolChildFuture failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("patchSchoolChildFuture failed. ", e));
			errorSchoolChild(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHSchoolChild(SchoolChild o, Boolean inheritPk, Handler<AsyncResult<SchoolChild>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Transaction tx = siteRequest.getTx();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			Set<String> methodNames = jsonObject.fieldNames();
			SchoolChild o2 = new SchoolChild();
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
				, Tuple.of(pk, "userKey", siteRequest.getUserKey().toString())
							, b
					-> {
						if(b.succeeded())
							a.handle(Future.succeededFuture());
						else
							a.handle(Future.failedFuture(b.cause()));
					});
				}));

				JsonArray userKeys = Optional.ofNullable(jsonObject.getJsonArray("addUserKeys")).orElse(null);
				if(userKeys != null && !userKeys.contains(siteRequest.getUserKey()))
					userKeys.add(siteRequest.getUserKey().toString());
				else
					jsonObject.put("addUserKeys", new JsonArray().add(siteRequest.getUserKey().toString()));
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
										a.handle(Future.failedFuture(new Exception("value SchoolChild.inheritPk failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolChild.inheritPk failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolChild.archived failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolChild.archived failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolChild.deleted failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolChild.deleted failed", b.cause())));
								});
							}));
						}
						break;
					case "addEnrollmentKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolEnrollment.class);
								searchList.addFilterQuery("deleted_indexed_boolean:false");
								searchList.addFilterQuery("archived_indexed_boolean:false");
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getEnrollmentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "childKey", pk, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolChild.enrollmentKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolEnrollment");
									}
								}
							}
						}
						break;
					case "addAllEnrollmentKeys":
						JsonArray addAllEnrollmentKeysValues = jsonObject.getJsonArray(methodName);
						if(addAllEnrollmentKeysValues != null) {
							for(Integer i = 0; i <  addAllEnrollmentKeysValues.size(); i++) {
								Long l = Long.parseLong(addAllEnrollmentKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolEnrollment.class);
									searchList.addFilterQuery("deleted_indexed_boolean:false");
									searchList.addFilterQuery("archived_indexed_boolean:false");
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getEnrollmentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "childKey", pk, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolChild.enrollmentKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolEnrollment");
										}
									}
								}
							}
						}
						break;
					case "setEnrollmentKeys":
						JsonArray setEnrollmentKeysValues = jsonObject.getJsonArray(methodName);
						JsonArray setEnrollmentKeysValues2 = new JsonArray();
						if(setEnrollmentKeysValues != null) {
							for(Integer i = 0; i <  setEnrollmentKeysValues.size(); i++) {
								Long l = Long.parseLong(setEnrollmentKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolEnrollment.class);
									searchList.addFilterQuery("deleted_indexed_boolean:false");
									searchList.addFilterQuery("archived_indexed_boolean:false");
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null)
										setEnrollmentKeysValues2.add(l2);
									if(l2 != null && !o.getEnrollmentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "childKey", pk, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolChild.enrollmentKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolEnrollment");
										}
									}
								}
							}
						}
						if(o.getEnrollmentKeys() != null) {
							for(Long l :  o.getEnrollmentKeys()) {
								if(l != null && (setEnrollmentKeysValues == null || !setEnrollmentKeysValues2.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l, "childKey", pk, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolChild.enrollmentKeys failed", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removeEnrollmentKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolEnrollment.class);
								searchList.addFilterQuery("deleted_indexed_boolean:false");
								searchList.addFilterQuery("archived_indexed_boolean:false");
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getEnrollmentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l2, "childKey", pk, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolChild.enrollmentKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolEnrollment");
									}
								}
							}
						}
						break;
					case "setPersonFirstName":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "personFirstName")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolChild.personFirstName failed", b.cause())));
								});
							}));
						} else {
							o2.setPersonFirstName(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "personFirstName", o2.jsonPersonFirstName())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolChild.personFirstName failed", b.cause())));
								});
							}));
						}
						break;
					case "setPersonFirstNamePreferred":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "personFirstNamePreferred")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolChild.personFirstNamePreferred failed", b.cause())));
								});
							}));
						} else {
							o2.setPersonFirstNamePreferred(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "personFirstNamePreferred", o2.jsonPersonFirstNamePreferred())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolChild.personFirstNamePreferred failed", b.cause())));
								});
							}));
						}
						break;
					case "setFamilyName":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "familyName")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolChild.familyName failed", b.cause())));
								});
							}));
						} else {
							o2.setFamilyName(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "familyName", o2.jsonFamilyName())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolChild.familyName failed", b.cause())));
								});
							}));
						}
						break;
					case "setPersonBirthDate":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "personBirthDate")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolChild.personBirthDate failed", b.cause())));
								});
							}));
						} else {
							o2.setPersonBirthDate(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "personBirthDate", o2.jsonPersonBirthDate())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolChild.personBirthDate failed", b.cause())));
								});
							}));
						}
						break;
					case "setPhoto":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "photo")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolChild.photo failed", b.cause())));
								});
							}));
						} else {
							o2.setPhoto(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "photo", o2.jsonPhoto())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolChild.photo failed", b.cause())));
								});
							}));
						}
						break;
				}
			}
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					SchoolChild o3 = new SchoolChild();
					o3.setSiteRequest_(o.getSiteRequest_());
					o3.setPk(pk);
					eventHandler.handle(Future.succeededFuture(o3));
				} else {
					LOGGER.error(String.format("sqlPATCHSchoolChild failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPATCHSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void patchSchoolChildResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PATCHSchoolChild(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("patchSchoolChildResponse failed. ", a.cause()));
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchoolChildResponse failed. ", ex));
			errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PATCHSchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PATCHSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSchoolChild(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest);
		siteRequest.setRequestUri("/api/child/{id}");
		siteRequest.setRequestMethod("GET");
		try {
			{
				userSchoolChild(siteRequest, b -> {
					if(b.succeeded()) {
						aSearchSchoolChild(siteRequest, false, true, "/api/child/{id}", "GET", c -> {
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
								LOGGER.error(String.format("getSchoolChild failed. ", c.cause()));
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						LOGGER.error(String.format("getSchoolChild failed. ", b.cause()));
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			}
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchoolChild failed. ", ex));
			errorSchoolChild(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void getSchoolChildResponse(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		try {
			response200GETSchoolChild(listSchoolChild, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("getSchoolChildResponse failed. ", a.cause()));
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchoolChildResponse failed. ", ex));
			errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200GETSchoolChild(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
			SolrDocumentList solrDocuments = listSchoolChild.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listSchoolChild.getList().stream().findFirst().orElse(null));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200GETSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchSchoolChild(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest);
		siteRequest.setRequestUri("/api/child");
		siteRequest.setRequestMethod("Search");
		try {
			{
				userSchoolChild(siteRequest, b -> {
					if(b.succeeded()) {
						aSearchSchoolChild(siteRequest, false, true, "/api/child", "Search", c -> {
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
								LOGGER.error(String.format("searchSchoolChild failed. ", c.cause()));
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						LOGGER.error(String.format("searchSchoolChild failed. ", b.cause()));
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			}
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchoolChild failed. ", ex));
			errorSchoolChild(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchSchoolChildResponse(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		try {
			response200SearchSchoolChild(listSchoolChild, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("searchSchoolChildResponse failed. ", a.cause()));
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchoolChildResponse failed. ", ex));
			errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
		}
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
					if(fls.size() == 1 && fls.stream().findFirst().orElse(null).equals("saves")) {
						fieldNames.removeAll(Optional.ofNullable(json2.getJsonArray("saves")).orElse(new JsonArray()).stream().map(s -> s.toString()).collect(Collectors.toList()));
						fieldNames.remove("pk");
						fieldNames.remove("created");
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
			LOGGER.error(String.format("response200SearchSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// AdminSearch //

	@Override
	public void adminsearchSchoolChild(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest);
		siteRequest.setRequestUri("/api/admin/child");
		siteRequest.setRequestMethod("AdminSearch");
		try {
			{
				userSchoolChild(siteRequest, b -> {
					if(b.succeeded()) {
						aSearchSchoolChild(siteRequest, false, true, "/api/admin/child", "AdminSearch", c -> {
							if(c.succeeded()) {
								SearchList<SchoolChild> listSchoolChild = c.result();
								adminsearchSchoolChildResponse(listSchoolChild, d -> {
									if(d.succeeded()) {
										eventHandler.handle(Future.succeededFuture(d.result()));
										LOGGER.info(String.format("adminsearchSchoolChild succeeded. "));
									} else {
										LOGGER.error(String.format("adminsearchSchoolChild failed. ", d.cause()));
										errorSchoolChild(siteRequest, eventHandler, d);
									}
								});
							} else {
								LOGGER.error(String.format("adminsearchSchoolChild failed. ", c.cause()));
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						LOGGER.error(String.format("adminsearchSchoolChild failed. ", b.cause()));
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			}
		} catch(Exception ex) {
			LOGGER.error(String.format("adminsearchSchoolChild failed. ", ex));
			errorSchoolChild(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void adminsearchSchoolChildResponse(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		try {
			response200AdminSearchSchoolChild(listSchoolChild, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("adminsearchSchoolChildResponse failed. ", a.cause()));
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("adminsearchSchoolChildResponse failed. ", ex));
			errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200AdminSearchSchoolChild(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					if(fls.size() == 1 && fls.stream().findFirst().orElse(null).equals("saves")) {
						fieldNames.removeAll(Optional.ofNullable(json2.getJsonArray("saves")).orElse(new JsonArray()).stream().map(s -> s.toString()).collect(Collectors.toList()));
						fieldNames.remove("pk");
						fieldNames.remove("created");
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
			LOGGER.error(String.format("response200AdminSearchSchoolChild failed. ", e));
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
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolChild(siteContext, operationRequest);
		siteRequest.setRequestUri("/child");
		siteRequest.setRequestMethod("SearchPage");
		try {
			{
				userSchoolChild(siteRequest, b -> {
					if(b.succeeded()) {
						aSearchSchoolChild(siteRequest, false, true, "/child", "SearchPage", c -> {
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
								LOGGER.error(String.format("searchpageSchoolChild failed. ", c.cause()));
								errorSchoolChild(siteRequest, eventHandler, c);
							}
						});
					} else {
						LOGGER.error(String.format("searchpageSchoolChild failed. ", b.cause()));
						errorSchoolChild(siteRequest, eventHandler, b);
					}
				});
			}
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchoolChild failed. ", ex));
			errorSchoolChild(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchpageSchoolChildPageInit(ChildPage page, SearchList<SchoolChild> listSchoolChild) {
	}
	public void searchpageSchoolChildResponse(SearchList<SchoolChild> listSchoolChild, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolChild.getSiteRequest_();
		try {
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(siteRequest, buffer);
			siteRequest.setW(w);
			response200SearchPageSchoolChild(listSchoolChild, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("searchpageSchoolChildResponse failed. ", a.cause()));
					errorSchoolChild(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchoolChildResponse failed. ", ex));
			errorSchoolChild(siteRequest, null, Future.failedFuture(ex));
		}
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
			searchpageSchoolChildPageInit(page, listSchoolChild);
			page.initDeepChildPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			LOGGER.error(String.format("response200SearchPageSchoolChild failed. ", e));
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
								sqlCommitSchoolChild(siteRequest, f -> {
									if(f.succeeded()) {
										sqlCloseSchoolChild(siteRequest, g -> {
											if(g.succeeded()) {
												refreshSchoolChild(schoolChild, h -> {
													if(h.succeeded()) {
														eventHandler.handle(Future.succeededFuture(schoolChild));
														promise.complete(schoolChild);
													} else {
														LOGGER.error(String.format("refreshSchoolChild failed. ", h.cause()));
														errorSchoolChild(siteRequest, null, h);
													}
												});
											} else {
												LOGGER.error(String.format("defineIndexSchoolChild failed. ", g.cause()));
												errorSchoolChild(siteRequest, null, g);
											}
										});
									} else {
										LOGGER.error(String.format("defineIndexSchoolChild failed. ", f.cause()));
										errorSchoolChild(siteRequest, null, f);
									}
								});
							} else {
								LOGGER.error(String.format("defineIndexSchoolChild failed. ", e.cause()));
								errorSchoolChild(siteRequest, null, e);
							}
						});
					} else {
						LOGGER.error(String.format("defineIndexSchoolChild failed. ", d.cause()));
						errorSchoolChild(siteRequest, null, d);
					}
				});
			} else {
				LOGGER.error(String.format("defineIndexSchoolChild failed. ", c.cause()));
				errorSchoolChild(siteRequest, null, c);
			}
		});
		return promise.future();
	}

	public void createSchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<SchoolChild>> eventHandler) {
		try {
			Transaction tx = siteRequest.getTx();
			String userId = siteRequest.getUserId();
			ZonedDateTime created = Optional.ofNullable(siteRequest.getJsonObject()).map(j -> j.getString("created")).map(s -> ZonedDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of(siteRequest.getSiteConfig_().getSiteZone())))).orElse(ZonedDateTime.now(ZoneId.of(siteRequest.getSiteConfig_().getSiteZone())));

			tx.preparedQuery(
					SiteContextEnUS.SQL_create
					, Tuple.of(SchoolChild.class.getCanonicalName(), userId, created.toOffsetDateTime())
					, Collectors.toList()
					, createAsync
			-> {
				if(createAsync.succeeded()) {
					Row createLine = createAsync.result().value().stream().findFirst().orElseGet(() -> null);
					Long pk = createLine.getLong(0);
					SchoolChild o = new SchoolChild();
					o.setPk(pk);
					o.setSiteRequest_(siteRequest);
					eventHandler.handle(Future.succeededFuture(o));
				} else {
					LOGGER.error(String.format("createSchoolChild failed. ", createAsync.cause()));
					eventHandler.handle(Future.failedFuture(createAsync.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("createSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void errorSchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
		Throwable e = resultAsync.cause();
		JsonObject json = new JsonObject()
				.put("error", new JsonObject()
				.put("message", Optional.ofNullable(e).map(Throwable::getMessage).orElse(null))
				.put("userName", siteRequest.getUserName())
				.put("userFullName", siteRequest.getUserFullName())
				.put("requestUri", siteRequest.getRequestUri())
				.put("requestMethod", siteRequest.getRequestMethod())
				.put("params", Optional.ofNullable(siteRequest.getOperationRequest()).map(o -> o.getParams()).orElse(null))
				);
		ExceptionUtils.printRootCauseStackTrace(e);
		OperationResponse responseOperation = new OperationResponse(400, "BAD REQUEST", 
				Buffer.buffer().appendString(json.encodePrettily())
				, new CaseInsensitiveHeaders().add("Content-Type", "application/json")
		);
		SiteConfig siteConfig = siteRequest.getSiteConfig_();
		SiteContextEnUS siteContext = siteRequest.getSiteContext_();
		MailClient mailClient = siteContext.getMailClient();
		MailMessage message = new MailMessage();
		message.setFrom(siteConfig.getEmailFrom());
		message.setTo(siteConfig.getEmailAdmin());
		if(e != null)
			message.setText(String.format("%s\n\n%s", json.encodePrettily(), ExceptionUtils.getStackTrace(e)));
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
		sqlRollbackSchoolChild(siteRequest, a -> {
			if(a.succeeded()) {
				LOGGER.info(String.format("sql rollback. "));
				sqlCloseSchoolChild(siteRequest, b -> {
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

	public void sqlConnectionSchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						LOGGER.error(String.format("sqlConnectionSchoolChild failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlTransactionSchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
			LOGGER.error(String.format("sqlTransactionSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlCommitSchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						LOGGER.error(String.format("sqlCommitSchoolChild failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlRollbackSchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						LOGGER.error(String.format("sqlRollbackSchoolChild failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlCloseSchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
			LOGGER.error(String.format("sqlCloseSchoolChild failed. ", e));
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
			String userId = siteRequest.getUserId();
			if(userId == null) {
				eventHandler.handle(Future.succeededFuture());
			} else {
				sqlConnectionSchoolChild(siteRequest, a -> {
					if(a.succeeded()) {
						sqlTransactionSchoolChild(siteRequest, b -> {
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
												jsonObject.put("userCompleteName", jsonPrincipal.getString("name"));
												jsonObject.put("userId", jsonPrincipal.getString("sub"));
												jsonObject.put("userEmail", jsonPrincipal.getString("email"));
												userSchoolChildDefine(siteRequest, jsonObject, false);

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
												jsonObject.put("setCustomerProfileId1", Optional.ofNullable(siteUser1).map(u -> u.getCustomerProfileId1()).orElse(null));
												jsonObject.put("setCustomerProfileId2", Optional.ofNullable(siteUser1).map(u -> u.getCustomerProfileId2()).orElse(null));
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
																	errorSchoolChild(siteRequest, eventHandler, e);
																}
															});
														} else {
															errorSchoolChild(siteRequest, eventHandler, d);
														}
													});
												} else {
													siteRequest.setSiteUser(siteUser1);
													siteRequest.setUserName(siteUser1.getUserName());
													siteRequest.setUserFirstName(siteUser1.getUserFirstName());
													siteRequest.setUserLastName(siteUser1.getUserLastName());
													siteRequest.setUserId(siteUser1.getUserId());
													siteRequest.setUserKey(siteUser1.getPk());
													sqlRollbackSchoolChild(siteRequest, c -> {
														if(c.succeeded()) {
															eventHandler.handle(Future.succeededFuture());
														} else {
															eventHandler.handle(Future.failedFuture(c.cause()));
															errorSchoolChild(siteRequest, eventHandler, c);
														}
													});
												}
											}
										} catch(Exception e) {
											LOGGER.error(String.format("userSchoolChild failed. ", e));
											eventHandler.handle(Future.failedFuture(e));
										}
									} else {
										LOGGER.error(String.format("userSchoolChild failed. ", selectCAsync.cause()));
										eventHandler.handle(Future.failedFuture(selectCAsync.cause()));
									}
								});
							} else {
								LOGGER.error(String.format("userSchoolChild failed. ", b.cause()));
								eventHandler.handle(Future.failedFuture(b.cause()));
							}
						});
					} else {
						LOGGER.error(String.format("userSchoolChild failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("userSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public Boolean userSchoolChildDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			if(jsonObject.getString("setCustomerProfileId1") == null)
				return true;
			if(jsonObject.getString("setCustomerProfileId2") == null)
				return true;
			return false;
		} else {
			if(jsonObject.getString("setCustomerProfileId1") == null)
				return true;
			if(jsonObject.getString("setCustomerProfileId2") == null)
				return true;
			return false;
		}
	}

	public void aSearchSchoolChildQ(String uri, String apiMethod, SearchList<SchoolChild> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
		}
	}

	public void aSearchSchoolChildFq(String uri, String apiMethod, SearchList<SchoolChild> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		if(StringUtils.startsWith(valueIndexed, "[")) {
			String[] fqs = StringUtils.substringBefore(StringUtils.substringAfter(valueIndexed, "["), "]").split(" TO ");
			if(fqs.length != 2)
				throw new RuntimeException(String.format("\"%s\" invalid range query. ", valueIndexed));
			String fq1 = fqs[0].equals("*") ? fqs[0] : SchoolChild.staticSolrFqForClass(entityVar, searchList.getSiteRequest_(), fqs[0]);
			String fq2 = fqs[1].equals("*") ? fqs[1] : SchoolChild.staticSolrFqForClass(entityVar, searchList.getSiteRequest_(), fqs[1]);
			searchList.addFilterQuery(varIndexed + ":[" + fq1 + " TO " + fq2 + "]");
		} else {
			searchList.addFilterQuery(varIndexed + ":" + SchoolChild.staticSolrFqForClass(entityVar, searchList.getSiteRequest_(), valueIndexed));
		}
	}

	public void aSearchSchoolChildSort(String uri, String apiMethod, SearchList<SchoolChild> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addSort(varIndexed, ORDER.valueOf(valueIndexed));
	}

	public void aSearchSchoolChildRows(String uri, String apiMethod, SearchList<SchoolChild> searchList, Integer valueRows) {
			searchList.setRows(apiMethod != null && apiMethod.contains("Search") ? valueRows : 10);
	}

	public void aSearchSchoolChildStart(String uri, String apiMethod, SearchList<SchoolChild> searchList, Integer valueStart) {
		searchList.setStart(valueStart);
	}

	public void aSearchSchoolChildVar(String uri, String apiMethod, SearchList<SchoolChild> searchList, String var, String value) {
		searchList.getSiteRequest_().getRequestVars().put(var, value);
	}

	public void aSearchSchoolChildUri(String uri, String apiMethod, SearchList<SchoolChild> searchList) {
	}

	public void varsSchoolChild(SiteRequestEnUS siteRequest, Handler<AsyncResult<SearchList<OperationResponse>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();

			operationRequest.getParams().getJsonObject("query").forEach(paramRequest -> {
				String entityVar = null;
				String valueIndexed = null;
				String paramName = paramRequest.getKey();
				Object paramValuesObject = paramRequest.getValue();
				JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);

				try {
					for(Object paramObject : paramObjects) {
						switch(paramName) {
							case "var":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								siteRequest.getRequestVars().put(entityVar, valueIndexed);
								break;
						}
					}
				} catch(Exception e) {
					LOGGER.error(String.format("aSearchSchoolChild failed. ", e));
					eventHandler.handle(Future.failedFuture(e));
				}
			});
			eventHandler.handle(Future.succeededFuture());
		} catch(Exception e) {
			LOGGER.error(String.format("aSearchSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void aSearchSchoolChild(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String uri, String apiMethod, Handler<AsyncResult<SearchList<SchoolChild>>> eventHandler) {
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

			List<String> roles = Arrays.asList("SiteManager");
			if(
					!CollectionUtils.containsAny(siteRequest.getUserResourceRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest.getUserRealmRoles(), roles)
					) {
				searchList.addFilterQuery("sessionId_indexed_string:" + ClientUtils.escapeQueryChars(Optional.ofNullable(siteRequest.getSessionId()).orElse("-----")) + " OR " + "sessionId_indexed_string:" + ClientUtils.escapeQueryChars(Optional.ofNullable(siteRequest.getSessionIdBefore()).orElse("-----"))
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
								aSearchSchoolChildQ(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "fq":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = SchoolChild.varIndexedSchoolChild(entityVar);
								aSearchSchoolChildFq(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = SchoolChild.varIndexedSchoolChild(entityVar);
								aSearchSchoolChildSort(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "start":
								valueStart = (Integer)paramObject;
								aSearchSchoolChildStart(uri, apiMethod, searchList, valueStart);
								break;
							case "rows":
								valueRows = (Integer)paramObject;
								aSearchSchoolChildRows(uri, apiMethod, searchList, valueRows);
								break;
							case "var":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								aSearchSchoolChildVar(uri, apiMethod, searchList, entityVar, valueIndexed);
								break;
						}
					}
					aSearchSchoolChildUri(uri, apiMethod, searchList);
				} catch(Exception e) {
					LOGGER.error(String.format("aSearchSchoolChild failed. ", e));
					eventHandler.handle(Future.failedFuture(e));
				}
			});
			if("*:*".equals(searchList.getQuery()) && searchList.getSorts().size() == 0) {
				searchList.addSort("created_indexed_date", ORDER.desc);
			}
			searchList.initDeepForClass(siteRequest);
			eventHandler.handle(Future.succeededFuture(searchList));
		} catch(Exception e) {
			LOGGER.error(String.format("aSearchSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void defineSchoolChild(SchoolChild o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
								LOGGER.error(String.format("defineSchoolChild failed. ", e));
								LOGGER.error(e);
							}
						}
						eventHandler.handle(Future.succeededFuture());
					} catch(Exception e) {
						LOGGER.error(String.format("defineSchoolChild failed. ", e));
						eventHandler.handle(Future.failedFuture(e));
					}
				} else {
					LOGGER.error(String.format("defineSchoolChild failed. ", defineAsync.cause()));
					eventHandler.handle(Future.failedFuture(defineAsync.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("defineSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void attributeSchoolChild(SchoolChild o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						LOGGER.error(String.format("attributeSchoolChild failed. ", attributeAsync.cause()));
						eventHandler.handle(Future.failedFuture(attributeAsync.cause()));
					}
				} catch(Exception e) {
					LOGGER.error(String.format("attributeSchoolChild failed. ", e));
					eventHandler.handle(Future.failedFuture(e));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("attributeSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void indexSchoolChild(SchoolChild o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			eventHandler.handle(Future.succeededFuture());
		} catch(Exception e) {
			LOGGER.error(String.format("indexSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void refreshSchoolChild(SchoolChild o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Boolean refresh = !"false".equals(siteRequest.getRequestVars().get("refresh"));
			if(refresh && BooleanUtils.isFalse(Optional.ofNullable(siteRequest.getApiRequest_()).map(ApiRequest::getEmpty).orElse(true))) {
				SearchList<SchoolChild> searchList = new SearchList<SchoolChild>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolChild.class);
				searchList.addFilterQuery("modified_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(siteRequest.getApiRequest_().getCreated().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				searchList.add("json.facet", "{enrollmentKeys:{terms:{field:enrollmentKeys_indexed_longs, limit:1000}}}");
				searchList.setRows(1000);
				searchList.initDeepSearchList(siteRequest);
				List<Future> futures = new ArrayList<>();

				for(int i=0; i < pks.size(); i++) {
					Long pk2 = pks.get(i);
					String classSimpleName2 = classes.get(i);

					if("SchoolEnrollment".equals(classSimpleName2) && pk2 != null) {
						SearchList<SchoolEnrollment> searchList2 = new SearchList<SchoolEnrollment>();
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolEnrollment.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk2);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest);
						SchoolEnrollment o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							SchoolEnrollmentEnUSGenApiServiceImpl service = new SchoolEnrollmentEnUSGenApiServiceImpl(siteRequest.getSiteContext_());
							SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolChild(siteContext, siteRequest.getOperationRequest(), new JsonObject());
							ApiRequest apiRequest2 = new ApiRequest();
							apiRequest2.setRows(1);
							apiRequest2.setNumFound(1l);
							apiRequest2.setNumPATCH(0L);
							apiRequest2.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest2);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest2).toString());

							o2.setPk(pk2);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolEnrollmentFuture(o2, false, a -> {
									if(a.succeeded()) {
									} else {
										LOGGER.info(String.format("SchoolEnrollment %s failed. ", pk2));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						SchoolChildEnUSApiServiceImpl service = new SchoolChildEnUSApiServiceImpl(siteRequest.getSiteContext_());
						List<Future> futures2 = new ArrayList<>();
						for(SchoolChild o2 : searchList.getList()) {
							SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolChild(siteContext, siteRequest.getOperationRequest(), new JsonObject());
							o2.setSiteRequest_(siteRequest2);
							futures2.add(
								service.patchSchoolChildFuture(o2, false, b -> {
									if(b.succeeded()) {
									} else {
										LOGGER.info(String.format("SchoolChild %s failed. ", o2.getPk()));
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
								errorSchoolChild(siteRequest, eventHandler, b);
							}
						});
					} else {
						LOGGER.error("Refresh relations failed. ", a.cause());
						errorSchoolChild(siteRequest, eventHandler, a);
					}
				});
			} else {
				eventHandler.handle(Future.succeededFuture());
			}
		} catch(Exception e) {
			LOGGER.error(String.format("refreshSchoolChild failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}
}
