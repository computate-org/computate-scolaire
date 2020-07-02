package org.computate.scolaire.enUS.block;

import org.computate.scolaire.enUS.enrollment.SchoolEnrollmentEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.age.SchoolAgeEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.age.SchoolAge;
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
 * CanonicalName.frFR: org.computate.scolaire.frFR.bloc.BlocScolaireFrFRGenApiServiceImpl
 **/
public class SchoolBlockEnUSGenApiServiceImpl implements SchoolBlockEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolBlockEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SchoolBlockEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public SchoolBlockEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
	}

	// POST //

	@Override
	public void postSchoolBlock(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("postSchoolBlock started. "));

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

			userSchoolBlock(siteRequest, b -> {
				if(b.succeeded()) {
					ApiRequest apiRequest = new ApiRequest();
					apiRequest.setRows(1);
					apiRequest.setNumFound(1L);
					apiRequest.setNumPATCH(0L);
					apiRequest.initDeepApiRequest(siteRequest);
					siteRequest.setApiRequest_(apiRequest);
					siteRequest.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
					postSchoolBlockFuture(siteRequest, false, c -> {
						if(c.succeeded()) {
							SchoolBlock schoolBlock = c.result();
							apiRequest.setPk(schoolBlock.getPk());
							postSchoolBlockResponse(schoolBlock, d -> {
									if(d.succeeded()) {
									eventHandler.handle(Future.succeededFuture(d.result()));
									LOGGER.info(String.format("postSchoolBlock succeeded. "));
								} else {
									LOGGER.error(String.format("postSchoolBlock failed. ", d.cause()));
									errorSchoolBlock(siteRequest, eventHandler, d);
								}
							});
						} else {
							LOGGER.error(String.format("postSchoolBlock failed. ", c.cause()));
							errorSchoolBlock(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("postSchoolBlock failed. ", b.cause()));
					errorSchoolBlock(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public Future<SchoolBlock> postSchoolBlockFuture(SiteRequestEnUS siteRequest, Boolean inheritPk, Handler<AsyncResult<SchoolBlock>> eventHandler) {
		Promise<SchoolBlock> promise = Promise.promise();
		try {
			sqlConnectionSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					sqlTransactionSchoolBlock(siteRequest, b -> {
						if(b.succeeded()) {
							createSchoolBlock(siteRequest, c -> {
								if(c.succeeded()) {
									SchoolBlock schoolBlock = c.result();
									sqlPOSTSchoolBlock(schoolBlock, inheritPk, d -> {
										if(d.succeeded()) {
											defineIndexSchoolBlock(schoolBlock, e -> {
												if(e.succeeded()) {
													ApiRequest apiRequest = siteRequest.getApiRequest_();
													if(apiRequest != null) {
														apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
														schoolBlock.apiRequestSchoolBlock();
														siteRequest.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
													}
													eventHandler.handle(Future.succeededFuture(schoolBlock));
													promise.complete(schoolBlock);
												} else {
													LOGGER.error(String.format("postSchoolBlockFuture failed. ", e.cause()));
													eventHandler.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											LOGGER.error(String.format("postSchoolBlockFuture failed. ", d.cause()));
											eventHandler.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("postSchoolBlockFuture failed. ", c.cause()));
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("postSchoolBlockFuture failed. ", b.cause()));
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("postSchoolBlockFuture failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("postSchoolBlockFuture failed. ", e));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTSchoolBlock(SchoolBlock o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.inheritPk failed", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.archived failed", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.deleted failed", b.cause())));
							});
						}));
						break;
					case "enrollmentKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolEnrollment.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "blockKeys", pk, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolBlock.enrollmentKeys failed", b.cause())));
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
					case "ageKey":
						{
							Long l = Long.parseLong(jsonObject.getString(entityVar));
							if(l != null) {
								SearchList<SchoolAge> searchList = new SearchList<SchoolAge>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolAge.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "ageKey", l2, "blockKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolBlock.ageKey failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolAge");
									}
								}
							}
						}
						break;
					case "schoolAddress":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "schoolAddress", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.schoolAddress failed", b.cause())));
							});
						}));
						break;
					case "blockStartTime":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockStartTime", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockStartTime failed", b.cause())));
							});
						}));
						break;
					case "blockEndTime":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockEndTime", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockEndTime failed", b.cause())));
							});
						}));
						break;
					case "blockPricePerMonth":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockPricePerMonth", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockPricePerMonth failed", b.cause())));
							});
						}));
						break;
					case "blockMonday":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockMonday", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockMonday failed", b.cause())));
							});
						}));
						break;
					case "blockTuesday":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockTuesday", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockTuesday failed", b.cause())));
							});
						}));
						break;
					case "blockWednesday":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockWednesday", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockWednesday failed", b.cause())));
							});
						}));
						break;
					case "blockThursday":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockThursday", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockThursday failed", b.cause())));
							});
						}));
						break;
					case "blockFriday":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockFriday", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockFriday failed", b.cause())));
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
					LOGGER.error(String.format("sqlPOSTSchoolBlock failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPOSTSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void postSchoolBlockResponse(SchoolBlock schoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = schoolBlock.getSiteRequest_();
		try {
			response200POSTSchoolBlock(schoolBlock, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("postSchoolBlockResponse failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchoolBlockResponse failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200POSTSchoolBlock(SchoolBlock o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(o);
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200POSTSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTImport //

	@Override
	public void putimportSchoolBlock(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putimportSchoolBlock started. "));

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

			userSchoolBlock(siteRequest, b -> {
				if(b.succeeded()) {
					putimportSchoolBlockResponse(siteRequest, c -> {
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
										siteRequest.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
										varsSchoolBlock(siteRequest, d -> {
											if(d.succeeded()) {
												listPUTImportSchoolBlock(apiRequest, siteRequest, e -> {
													if(e.succeeded()) {
														putimportSchoolBlockResponse(siteRequest, f -> {
															if(e.succeeded()) {
																LOGGER.info(String.format("putimportSchoolBlock succeeded. "));
																blockingCodeHandler.handle(Future.succeededFuture(e.result()));
															} else {
																LOGGER.error(String.format("putimportSchoolBlock failed. ", f.cause()));
																errorSchoolBlock(siteRequest, null, f);
															}
														});
													} else {
														LOGGER.error(String.format("putimportSchoolBlock failed. ", e.cause()));
														errorSchoolBlock(siteRequest, null, e);
													}
												});
											} else {
												LOGGER.error(String.format("putimportSchoolBlock failed. ", d.cause()));
												errorSchoolBlock(siteRequest, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putimportSchoolBlock failed. ", ex));
										errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("putimportSchoolBlock failed. ", c.cause()));
							errorSchoolBlock(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("putimportSchoolBlock failed. ", b.cause()));
					errorSchoolBlock(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTImportSchoolBlock(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				json.put("created", json.getValue("created"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolBlock(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setApiRequest_(apiRequest);
				siteRequest2.setRequestVars(siteRequest.getRequestVars());

				SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolBlock.class);
				searchList.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					SchoolBlock o = searchList.getList().stream().findFirst().orElse(null);
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
							patchSchoolBlockFuture(o, true, a -> {
								if(a.succeeded()) {
								} else {
									LOGGER.error(String.format("listPUTImportSchoolBlock failed. ", a.cause()));
									errorSchoolBlock(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolBlockFuture(siteRequest2, true, a -> {
							if(a.succeeded()) {
							} else {
								LOGGER.error(String.format("listPUTImportSchoolBlock failed. ", a.cause()));
								errorSchoolBlock(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
					response200PUTImportSchoolBlock(siteRequest, eventHandler);
				} else {
					LOGGER.error(String.format("listPUTImportSchoolBlock failed. ", a.cause()));
					errorSchoolBlock(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("listPUTImportSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putimportSchoolBlockResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTImportSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putimportSchoolBlockResponse failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolBlockResponse failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTImportSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PUTImportSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTMerge //

	@Override
	public void putmergeSchoolBlock(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putmergeSchoolBlock started. "));

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

			userSchoolBlock(siteRequest, b -> {
				if(b.succeeded()) {
					putmergeSchoolBlockResponse(siteRequest, c -> {
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
										siteRequest.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
										varsSchoolBlock(siteRequest, d -> {
											if(d.succeeded()) {
												listPUTMergeSchoolBlock(apiRequest, siteRequest, e -> {
													if(e.succeeded()) {
														putmergeSchoolBlockResponse(siteRequest, f -> {
															if(e.succeeded()) {
																LOGGER.info(String.format("putmergeSchoolBlock succeeded. "));
																blockingCodeHandler.handle(Future.succeededFuture(e.result()));
															} else {
																LOGGER.error(String.format("putmergeSchoolBlock failed. ", f.cause()));
																errorSchoolBlock(siteRequest, null, f);
															}
														});
													} else {
														LOGGER.error(String.format("putmergeSchoolBlock failed. ", e.cause()));
														errorSchoolBlock(siteRequest, null, e);
													}
												});
											} else {
												LOGGER.error(String.format("putmergeSchoolBlock failed. ", d.cause()));
												errorSchoolBlock(siteRequest, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putmergeSchoolBlock failed. ", ex));
										errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("putmergeSchoolBlock failed. ", c.cause()));
							errorSchoolBlock(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("putmergeSchoolBlock failed. ", b.cause()));
					errorSchoolBlock(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTMergeSchoolBlock(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolBlock(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setApiRequest_(apiRequest);
				siteRequest2.setRequestVars(siteRequest.getRequestVars());

				SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolBlock.class);
				searchList.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					SchoolBlock o = searchList.getList().stream().findFirst().orElse(null);
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
							patchSchoolBlockFuture(o, false, a -> {
								if(a.succeeded()) {
								} else {
									LOGGER.error(String.format("listPUTMergeSchoolBlock failed. ", a.cause()));
									errorSchoolBlock(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolBlockFuture(siteRequest2, false, a -> {
							if(a.succeeded()) {
							} else {
								LOGGER.error(String.format("listPUTMergeSchoolBlock failed. ", a.cause()));
								errorSchoolBlock(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
					response200PUTMergeSchoolBlock(siteRequest, eventHandler);
				} else {
					LOGGER.error(String.format("listPUTMergeSchoolBlock failed. ", a.cause()));
					errorSchoolBlock(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("listPUTMergeSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putmergeSchoolBlockResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTMergeSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putmergeSchoolBlockResponse failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolBlockResponse failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTMergeSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PUTMergeSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTCopy //

	@Override
	public void putcopySchoolBlock(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putcopySchoolBlock started. "));

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

			userSchoolBlock(siteRequest, b -> {
				if(b.succeeded()) {
					putcopySchoolBlockResponse(siteRequest, c -> {
						if(c.succeeded()) {
							eventHandler.handle(Future.succeededFuture(c.result()));
							WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
							workerExecutor.executeBlocking(
								blockingCodeHandler -> {
									try {
										aSearchSchoolBlock(siteRequest, false, true, "/api/block/copy", "PUTCopy", d -> {
											if(d.succeeded()) {
												SearchList<SchoolBlock> listSchoolBlock = d.result();
												ApiRequest apiRequest = new ApiRequest();
												apiRequest.setRows(listSchoolBlock.getRows());
												apiRequest.setNumFound(listSchoolBlock.getQueryResponse().getResults().getNumFound());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest);
												siteRequest.setApiRequest_(apiRequest);
												siteRequest.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
												try {
													listPUTCopySchoolBlock(apiRequest, listSchoolBlock, e -> {
														if(e.succeeded()) {
															putcopySchoolBlockResponse(siteRequest, f -> {
																if(f.succeeded()) {
																	LOGGER.info(String.format("putcopySchoolBlock succeeded. "));
																	blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																} else {
																	LOGGER.error(String.format("putcopySchoolBlock failed. ", f.cause()));
																	errorSchoolBlock(siteRequest, null, f);
																}
															});
														} else {
															LOGGER.error(String.format("putcopySchoolBlock failed. ", e.cause()));
															errorSchoolBlock(siteRequest, null, e);
														}
													});
												} catch(Exception ex) {
													LOGGER.error(String.format("putcopySchoolBlock failed. ", ex));
													errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
												}
											} else {
												LOGGER.error(String.format("putcopySchoolBlock failed. ", d.cause()));
												errorSchoolBlock(siteRequest, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putcopySchoolBlock failed. ", ex));
										errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("putcopySchoolBlock failed. ", c.cause()));
							errorSchoolBlock(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("putcopySchoolBlock failed. ", b.cause()));
					errorSchoolBlock(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTCopySchoolBlock(ApiRequest apiRequest, SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
		listSchoolBlock.getList().forEach(o -> {
			SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolBlock(siteContext, siteRequest.getOperationRequest(), siteRequest.getJsonObject());
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			o.setSiteRequest_(siteRequest2);
			futures.add(
				putcopySchoolBlockFuture(siteRequest, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
					} else {
						LOGGER.error(String.format("listPUTCopySchoolBlock failed. ", a.cause()));
						errorSchoolBlock(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchoolBlock.size());
				if(listSchoolBlock.next()) {
					listPUTCopySchoolBlock(apiRequest, listSchoolBlock, eventHandler);
				} else {
					response200PUTCopySchoolBlock(siteRequest, eventHandler);
				}
			} else {
				LOGGER.error(String.format("listPUTCopySchoolBlock failed. ", a.cause()));
				errorSchoolBlock(listSchoolBlock.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolBlock> putcopySchoolBlockFuture(SiteRequestEnUS siteRequest, JsonObject jsonObject, Handler<AsyncResult<SchoolBlock>> eventHandler) {
		Promise<SchoolBlock> promise = Promise.promise();
		try {

			jsonObject.put("saves", Optional.ofNullable(jsonObject.getJsonArray("saves")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("saves").add(o.getKey());
			});

			sqlConnectionSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					sqlTransactionSchoolBlock(siteRequest, b -> {
						if(b.succeeded()) {
							createSchoolBlock(siteRequest, c -> {
								if(c.succeeded()) {
									SchoolBlock schoolBlock = c.result();
									sqlPUTCopySchoolBlock(schoolBlock, jsonObject, d -> {
										if(d.succeeded()) {
											defineIndexSchoolBlock(schoolBlock, e -> {
												if(e.succeeded()) {
													ApiRequest apiRequest = siteRequest.getApiRequest_();
													if(apiRequest != null) {
														apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
														if(apiRequest.getNumFound() == 1L) {
															schoolBlock.apiRequestSchoolBlock();
														}
														siteRequest.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
													}
													eventHandler.handle(Future.succeededFuture(schoolBlock));
													promise.complete(schoolBlock);
												} else {
													LOGGER.error(String.format("putcopySchoolBlockFuture failed. ", e.cause()));
													eventHandler.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											LOGGER.error(String.format("putcopySchoolBlockFuture failed. ", d.cause()));
											eventHandler.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("putcopySchoolBlockFuture failed. ", c.cause()));
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("putcopySchoolBlockFuture failed. ", b.cause()));
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("putcopySchoolBlockFuture failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("putcopySchoolBlockFuture failed. ", e));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopySchoolBlock(SchoolBlock o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.inheritPk failed", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.archived failed", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.deleted failed", b.cause())));
							});
						}));
						break;
					case "enrollmentKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_addA
										, Tuple.of(l, "blockKeys", pk, "enrollmentKeys")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.enrollmentKeys failed", b.cause())));
								});
							}));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("SchoolEnrollment");
							}
						}
						break;
					case "ageKey":
							{
						Long l = Long.parseLong(jsonObject.getString(entityVar));
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_addA
									, Tuple.of(pk, "ageKey", l, "blockKeys")
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.ageKey failed", b.cause())));
							});
						}));
						}
						break;
					case "schoolAddress":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "schoolAddress", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.schoolAddress failed", b.cause())));
							});
						}));
						break;
					case "blockStartTime":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockStartTime", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockStartTime failed", b.cause())));
							});
						}));
						break;
					case "blockEndTime":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockEndTime", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockEndTime failed", b.cause())));
							});
						}));
						break;
					case "blockPricePerMonth":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockPricePerMonth", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockPricePerMonth failed", b.cause())));
							});
						}));
						break;
					case "blockMonday":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockMonday", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockMonday failed", b.cause())));
							});
						}));
						break;
					case "blockTuesday":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockTuesday", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockTuesday failed", b.cause())));
							});
						}));
						break;
					case "blockWednesday":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockWednesday", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockWednesday failed", b.cause())));
							});
						}));
						break;
					case "blockThursday":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockThursday", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockThursday failed", b.cause())));
							});
						}));
						break;
					case "blockFriday":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "blockFriday", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockFriday failed", b.cause())));
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
					LOGGER.error(String.format("sqlPUTCopySchoolBlock failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPUTCopySchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void putcopySchoolBlockResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTCopySchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putcopySchoolBlockResponse failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchoolBlockResponse failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTCopySchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PUTCopySchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSchoolBlock(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("patchSchoolBlock started. "));

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

			userSchoolBlock(siteRequest, b -> {
				if(b.succeeded()) {
					patchSchoolBlockResponse(siteRequest, c -> {
						if(c.succeeded()) {
							eventHandler.handle(Future.succeededFuture(c.result()));
							WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
							workerExecutor.executeBlocking(
								blockingCodeHandler -> {
									try {
										aSearchSchoolBlock(siteRequest, false, true, "/api/block", "PATCH", d -> {
											if(d.succeeded()) {
												SearchList<SchoolBlock> listSchoolBlock = d.result();
												ApiRequest apiRequest = new ApiRequest();
												apiRequest.setRows(listSchoolBlock.getRows());
												apiRequest.setNumFound(listSchoolBlock.getQueryResponse().getResults().getNumFound());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest);
												siteRequest.setApiRequest_(apiRequest);
												siteRequest.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
												SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolBlock.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
												Date date = null;
												if(facets != null)
													date = (Date)facets.get("max_modified");
												String dt;
												if(date == null)
													dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
												else
													dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
												listSchoolBlock.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));

												try {
													listPATCHSchoolBlock(apiRequest, listSchoolBlock, dt, e -> {
														if(e.succeeded()) {
															patchSchoolBlockResponse(siteRequest, f -> {
																if(f.succeeded()) {
																	LOGGER.info(String.format("patchSchoolBlock succeeded. "));
																	blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																} else {
																	LOGGER.error(String.format("patchSchoolBlock failed. ", f.cause()));
																	errorSchoolBlock(siteRequest, null, f);
																}
															});
														} else {
															LOGGER.error(String.format("patchSchoolBlock failed. ", e.cause()));
															errorSchoolBlock(siteRequest, null, e);
														}
													});
												} catch(Exception ex) {
													LOGGER.error(String.format("patchSchoolBlock failed. ", ex));
													errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
												}
											} else {
												LOGGER.error(String.format("patchSchoolBlock failed. ", d.cause()));
												errorSchoolBlock(siteRequest, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("patchSchoolBlock failed. ", ex));
										errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("patchSchoolBlock failed. ", c.cause()));
							errorSchoolBlock(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("patchSchoolBlock failed. ", b.cause()));
					errorSchoolBlock(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPATCHSchoolBlock(ApiRequest apiRequest, SearchList<SchoolBlock> listSchoolBlock, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
		listSchoolBlock.getList().forEach(o -> {
			SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolBlock(siteContext, siteRequest.getOperationRequest(), siteRequest.getJsonObject());
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			o.setSiteRequest_(siteRequest2);
			futures.add(
				patchSchoolBlockFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						errorSchoolBlock(siteRequest2, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listSchoolBlock.next(dt)) {
					listPATCHSchoolBlock(apiRequest, listSchoolBlock, dt, eventHandler);
				} else {
					response200PATCHSchoolBlock(siteRequest, eventHandler);
				}
			} else {
				LOGGER.error(String.format("listPATCHSchoolBlock failed. ", a.cause()));
				errorSchoolBlock(listSchoolBlock.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolBlock> patchSchoolBlockFuture(SchoolBlock o, Boolean inheritPk, Handler<AsyncResult<SchoolBlock>> eventHandler) {
		Promise<SchoolBlock> promise = Promise.promise();
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			if(apiRequest != null && apiRequest.getNumFound() == 1L) {
				apiRequest.setOriginal(o);
				apiRequest.setPk(o.getPk());
			}
			sqlConnectionSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					sqlTransactionSchoolBlock(siteRequest, b -> {
						if(b.succeeded()) {
							sqlPATCHSchoolBlock(o, inheritPk, c -> {
								if(c.succeeded()) {
									SchoolBlock schoolBlock = c.result();
									defineIndexSchoolBlock(schoolBlock, d -> {
										if(d.succeeded()) {
											if(apiRequest != null) {
												apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
												if(apiRequest.getNumFound() == 1L) {
													schoolBlock.apiRequestSchoolBlock();
												}
												siteRequest.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());
											}
											eventHandler.handle(Future.succeededFuture(schoolBlock));
											promise.complete(schoolBlock);
										} else {
											LOGGER.error(String.format("patchSchoolBlockFuture failed. ", d.cause()));
											eventHandler.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("patchSchoolBlockFuture failed. ", c.cause()));
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("patchSchoolBlockFuture failed. ", b.cause()));
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("patchSchoolBlockFuture failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("patchSchoolBlockFuture failed. ", e));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHSchoolBlock(SchoolBlock o, Boolean inheritPk, Handler<AsyncResult<SchoolBlock>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Transaction tx = siteRequest.getTx();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			Set<String> methodNames = jsonObject.fieldNames();
			SchoolBlock o2 = new SchoolBlock();
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
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.inheritPk failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.inheritPk failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.archived failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.archived failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.deleted failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.deleted failed", b.cause())));
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
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getEnrollmentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "blockKeys", pk, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolBlock.enrollmentKeys failed", b.cause())));
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
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getEnrollmentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "blockKeys", pk, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolBlock.enrollmentKeys failed", b.cause())));
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
						if(setEnrollmentKeysValues != null) {
							for(Integer i = 0; i <  setEnrollmentKeysValues.size(); i++) {
								Long l = Long.parseLong(setEnrollmentKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolEnrollment.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getEnrollmentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "blockKeys", pk, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolBlock.enrollmentKeys failed", b.cause())));
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
								if(l != null && (setEnrollmentKeysValues == null || !setEnrollmentKeysValues.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l, "blockKeys", pk, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolBlock.enrollmentKeys failed", b.cause())));
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
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getEnrollmentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l2, "blockKeys", pk, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolBlock.enrollmentKeys failed", b.cause())));
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
					case "setAgeKey":
						{
							Long l = o2.getAgeKey();
							if(l != null) {
								SearchList<SchoolAge> searchList = new SearchList<SchoolAge>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolAge.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !l2.equals(o.getAgeKey())) {
									o2.setAgeKey(jsonObject.getString(methodName));
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "ageKey", l2, "blockKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolBlock.ageKey failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolAge");
									}
								}
							}
						}
						break;
					case "removeAgeKey":
						{
							Long l = o2.getAgeKey();
							if(l != null) {
								SearchList<SchoolAge> searchList = new SearchList<SchoolAge>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolAge.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && l2.equals(o.getAgeKey())) {
									o2.setAgeKey(jsonObject.getString(methodName));
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(pk, "ageKey", l2, "blockKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolBlock.ageKey failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolAge");
									}
								}
							}
						}
						break;
					case "setSchoolAddress":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "schoolAddress")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.schoolAddress failed", b.cause())));
								});
							}));
						} else {
							o2.setSchoolAddress(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "schoolAddress", o2.jsonSchoolAddress())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.schoolAddress failed", b.cause())));
								});
							}));
						}
						break;
					case "setBlockStartTime":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "blockStartTime")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockStartTime failed", b.cause())));
								});
							}));
						} else {
							o2.setBlockStartTime(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "blockStartTime", o2.jsonBlockStartTime())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockStartTime failed", b.cause())));
								});
							}));
						}
						break;
					case "setBlockEndTime":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "blockEndTime")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockEndTime failed", b.cause())));
								});
							}));
						} else {
							o2.setBlockEndTime(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "blockEndTime", o2.jsonBlockEndTime())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockEndTime failed", b.cause())));
								});
							}));
						}
						break;
					case "setBlockPricePerMonth":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "blockPricePerMonth")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockPricePerMonth failed", b.cause())));
								});
							}));
						} else {
							o2.setBlockPricePerMonth(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "blockPricePerMonth", o2.jsonBlockPricePerMonth())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockPricePerMonth failed", b.cause())));
								});
							}));
						}
						break;
					case "setBlockMonday":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "blockMonday")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockMonday failed", b.cause())));
								});
							}));
						} else {
							o2.setBlockMonday(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "blockMonday", o2.jsonBlockMonday())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockMonday failed", b.cause())));
								});
							}));
						}
						break;
					case "setBlockTuesday":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "blockTuesday")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockTuesday failed", b.cause())));
								});
							}));
						} else {
							o2.setBlockTuesday(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "blockTuesday", o2.jsonBlockTuesday())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockTuesday failed", b.cause())));
								});
							}));
						}
						break;
					case "setBlockWednesday":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "blockWednesday")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockWednesday failed", b.cause())));
								});
							}));
						} else {
							o2.setBlockWednesday(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "blockWednesday", o2.jsonBlockWednesday())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockWednesday failed", b.cause())));
								});
							}));
						}
						break;
					case "setBlockThursday":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "blockThursday")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockThursday failed", b.cause())));
								});
							}));
						} else {
							o2.setBlockThursday(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "blockThursday", o2.jsonBlockThursday())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockThursday failed", b.cause())));
								});
							}));
						}
						break;
					case "setBlockFriday":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "blockFriday")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockFriday failed", b.cause())));
								});
							}));
						} else {
							o2.setBlockFriday(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "blockFriday", o2.jsonBlockFriday())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolBlock.blockFriday failed", b.cause())));
								});
							}));
						}
						break;
				}
			}
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					SchoolBlock o3 = new SchoolBlock();
					o3.setSiteRequest_(o.getSiteRequest_());
					o3.setPk(pk);
					eventHandler.handle(Future.succeededFuture(o3));
				} else {
					LOGGER.error(String.format("sqlPATCHSchoolBlock failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPATCHSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void patchSchoolBlockResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PATCHSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("patchSchoolBlockResponse failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchoolBlockResponse failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PATCHSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PATCHSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSchoolBlock(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest);
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

			userSchoolBlock(siteRequest, b -> {
				if(b.succeeded()) {
					aSearchSchoolBlock(siteRequest, false, true, "/api/block/{id}", "GET", c -> {
						if(c.succeeded()) {
							SearchList<SchoolBlock> listSchoolBlock = c.result();
							getSchoolBlockResponse(listSchoolBlock, d -> {
								if(d.succeeded()) {
									eventHandler.handle(Future.succeededFuture(d.result()));
									LOGGER.info(String.format("getSchoolBlock succeeded. "));
								} else {
									LOGGER.error(String.format("getSchoolBlock failed. ", d.cause()));
									errorSchoolBlock(siteRequest, eventHandler, d);
								}
							});
						} else {
							LOGGER.error(String.format("getSchoolBlock failed. ", c.cause()));
							errorSchoolBlock(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("getSchoolBlock failed. ", b.cause()));
					errorSchoolBlock(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void getSchoolBlockResponse(SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
		try {
			response200GETSchoolBlock(listSchoolBlock, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("getSchoolBlockResponse failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchoolBlockResponse failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200GETSchoolBlock(SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
			SolrDocumentList solrDocuments = listSchoolBlock.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listSchoolBlock.getList().stream().findFirst().orElse(null));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200GETSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchSchoolBlock(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest);
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

			userSchoolBlock(siteRequest, b -> {
				if(b.succeeded()) {
					aSearchSchoolBlock(siteRequest, false, true, "/api/block", "Search", c -> {
						if(c.succeeded()) {
							SearchList<SchoolBlock> listSchoolBlock = c.result();
							searchSchoolBlockResponse(listSchoolBlock, d -> {
								if(d.succeeded()) {
									eventHandler.handle(Future.succeededFuture(d.result()));
									LOGGER.info(String.format("searchSchoolBlock succeeded. "));
								} else {
									LOGGER.error(String.format("searchSchoolBlock failed. ", d.cause()));
									errorSchoolBlock(siteRequest, eventHandler, d);
								}
							});
						} else {
							LOGGER.error(String.format("searchSchoolBlock failed. ", c.cause()));
							errorSchoolBlock(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("searchSchoolBlock failed. ", b.cause()));
					errorSchoolBlock(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchSchoolBlockResponse(SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
		try {
			response200SearchSchoolBlock(listSchoolBlock, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("searchSchoolBlockResponse failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchoolBlockResponse failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchSchoolBlock(SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
			QueryResponse responseSearch = listSchoolBlock.getQueryResponse();
			SolrDocumentList solrDocuments = listSchoolBlock.getSolrDocumentList();
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
			listSchoolBlock.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listSchoolBlock.getFields();
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
			LOGGER.error(String.format("response200SearchSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// SearchPage //

	@Override
	public void searchpageSchoolBlockId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageSchoolBlock(operationRequest, eventHandler);
	}

	@Override
	public void searchpageSchoolBlock(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest);
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

			userSchoolBlock(siteRequest, b -> {
				if(b.succeeded()) {
					aSearchSchoolBlock(siteRequest, false, true, "/block", "SearchPage", c -> {
						if(c.succeeded()) {
							SearchList<SchoolBlock> listSchoolBlock = c.result();
							searchpageSchoolBlockResponse(listSchoolBlock, d -> {
								if(d.succeeded()) {
									eventHandler.handle(Future.succeededFuture(d.result()));
									LOGGER.info(String.format("searchpageSchoolBlock succeeded. "));
								} else {
									LOGGER.error(String.format("searchpageSchoolBlock failed. ", d.cause()));
									errorSchoolBlock(siteRequest, eventHandler, d);
								}
							});
						} else {
							LOGGER.error(String.format("searchpageSchoolBlock failed. ", c.cause()));
							errorSchoolBlock(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("searchpageSchoolBlock failed. ", b.cause()));
					errorSchoolBlock(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchoolBlock failed. ", ex));
			errorSchoolBlock(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchpageSchoolBlockResponse(SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
		try {
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(siteRequest, buffer);
			siteRequest.setW(w);
			response200SearchPageSchoolBlock(listSchoolBlock, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("searchpageSchoolBlockResponse failed. ", a.cause()));
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchoolBlockResponse failed. ", ex));
			errorSchoolBlock(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchPageSchoolBlock(SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listSchoolBlock.getSiteRequest_(), buffer);
			BlockPage page = new BlockPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/block");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listSchoolBlock.size() == 1)
				siteRequest.setRequestPk(listSchoolBlock.get(0).getPk());
			siteRequest.setW(w);
			page.setListSchoolBlock(listSchoolBlock);
			page.setSiteRequest_(siteRequest);
			page.initDeepBlockPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			LOGGER.error(String.format("response200SearchPageSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<SchoolBlock> defineIndexSchoolBlock(SchoolBlock schoolBlock, Handler<AsyncResult<SchoolBlock>> eventHandler) {
		Promise<SchoolBlock> promise = Promise.promise();
		SiteRequestEnUS siteRequest = schoolBlock.getSiteRequest_();
		defineSchoolBlock(schoolBlock, c -> {
			if(c.succeeded()) {
				attributeSchoolBlock(schoolBlock, d -> {
					if(d.succeeded()) {
						indexSchoolBlock(schoolBlock, e -> {
							if(e.succeeded()) {
								sqlCommitSchoolBlock(siteRequest, f -> {
									if(f.succeeded()) {
										sqlCloseSchoolBlock(siteRequest, g -> {
											if(g.succeeded()) {
												refreshSchoolBlock(schoolBlock, h -> {
													if(h.succeeded()) {
														eventHandler.handle(Future.succeededFuture(schoolBlock));
														promise.complete(schoolBlock);
													} else {
														LOGGER.error(String.format("refreshSchoolBlock failed. ", h.cause()));
														errorSchoolBlock(siteRequest, null, h);
													}
												});
											} else {
												LOGGER.error(String.format("defineIndexSchoolBlock failed. ", g.cause()));
												errorSchoolBlock(siteRequest, null, g);
											}
										});
									} else {
										LOGGER.error(String.format("defineIndexSchoolBlock failed. ", f.cause()));
										errorSchoolBlock(siteRequest, null, f);
									}
								});
							} else {
								LOGGER.error(String.format("defineIndexSchoolBlock failed. ", e.cause()));
								errorSchoolBlock(siteRequest, null, e);
							}
						});
					} else {
						LOGGER.error(String.format("defineIndexSchoolBlock failed. ", d.cause()));
						errorSchoolBlock(siteRequest, null, d);
					}
				});
			} else {
				LOGGER.error(String.format("defineIndexSchoolBlock failed. ", c.cause()));
				errorSchoolBlock(siteRequest, null, c);
			}
		});
		return promise.future();
	}

	public void createSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<SchoolBlock>> eventHandler) {
		try {
			Transaction tx = siteRequest.getTx();
			String userId = siteRequest.getUserId();
			ZonedDateTime created = Optional.ofNullable(siteRequest.getJsonObject()).map(j -> j.getString("created")).map(s -> ZonedDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of(siteRequest.getSiteConfig_().getSiteZone())))).orElse(ZonedDateTime.now(ZoneId.of(siteRequest.getSiteConfig_().getSiteZone())));

			tx.preparedQuery(
					SiteContextEnUS.SQL_create
					, Tuple.of(SchoolBlock.class.getCanonicalName(), userId, created.toOffsetDateTime())
					, Collectors.toList()
					, createAsync
			-> {
				if(createAsync.succeeded()) {
					Row createLine = createAsync.result().value().stream().findFirst().orElseGet(() -> null);
					Long pk = createLine.getLong(0);
					SchoolBlock o = new SchoolBlock();
					o.setPk(pk);
					o.setSiteRequest_(siteRequest);
					eventHandler.handle(Future.succeededFuture(o));
				} else {
					LOGGER.error(String.format("createSchoolBlock failed. ", createAsync.cause()));
					eventHandler.handle(Future.failedFuture(createAsync.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("createSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void errorSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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
		sqlRollbackSchoolBlock(siteRequest, a -> {
			if(a.succeeded()) {
				LOGGER.info(String.format("sql rollback. "));
				sqlCloseSchoolBlock(siteRequest, b -> {
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

	public void sqlConnectionSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						LOGGER.error(String.format("sqlConnectionSchoolBlock failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlTransactionSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
			LOGGER.error(String.format("sqlTransactionSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlCommitSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						LOGGER.error(String.format("sqlCommitSchoolBlock failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlRollbackSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						LOGGER.error(String.format("sqlRollbackSchoolBlock failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlCloseSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
			LOGGER.error(String.format("sqlCloseSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolBlock(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolBlock(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			String userId = siteRequest.getUserId();
			if(userId == null) {
				eventHandler.handle(Future.succeededFuture());
			} else {
				sqlConnectionSchoolBlock(siteRequest, a -> {
					if(a.succeeded()) {
						sqlTransactionSchoolBlock(siteRequest, b -> {
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
												userSchoolBlockDefine(siteRequest, jsonObject, false);

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
																		errorSchoolBlock(siteRequest, eventHandler, e);
																	}
																});
															} else {
																errorSchoolBlock(siteRequest, eventHandler, d);
															}
														});
													} else {
														errorSchoolBlock(siteRequest, eventHandler, c);
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
												Boolean define = userSchoolBlockDefine(siteRequest, jsonObject, true);
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
																	errorSchoolBlock(siteRequest, eventHandler, e);
																}
															});
														} else {
															errorSchoolBlock(siteRequest, eventHandler, d);
														}
													});
												} else {
													siteRequest.setSiteUser(siteUser1);
													siteRequest.setUserName(siteUser1.getUserName());
													siteRequest.setUserFirstName(siteUser1.getUserFirstName());
													siteRequest.setUserLastName(siteUser1.getUserLastName());
													siteRequest.setUserId(siteUser1.getUserId());
													siteRequest.setUserKey(siteUser1.getPk());
													sqlRollbackSchoolBlock(siteRequest, c -> {
														if(c.succeeded()) {
															eventHandler.handle(Future.succeededFuture());
														} else {
															eventHandler.handle(Future.failedFuture(c.cause()));
															errorSchoolBlock(siteRequest, eventHandler, c);
														}
													});
												}
											}
										} catch(Exception e) {
											LOGGER.error(String.format("userSchoolBlock failed. ", e));
											eventHandler.handle(Future.failedFuture(e));
										}
									} else {
										LOGGER.error(String.format("userSchoolBlock failed. ", selectCAsync.cause()));
										eventHandler.handle(Future.failedFuture(selectCAsync.cause()));
									}
								});
							} else {
								LOGGER.error(String.format("userSchoolBlock failed. ", b.cause()));
								eventHandler.handle(Future.failedFuture(b.cause()));
							}
						});
					} else {
						LOGGER.error(String.format("userSchoolBlock failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("userSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public Boolean userSchoolBlockDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
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

	public void aSearchSchoolBlockQ(String uri, String apiMethod, SearchList<SchoolBlock> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
			searchList.setHighlight(true);
			searchList.setHighlightSnippets(3);
			searchList.addHighlightField(varIndexed);
			searchList.setParam("hl.encoder", "html");
		}
	}

	public void aSearchSchoolBlockFq(String uri, String apiMethod, SearchList<SchoolBlock> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
	}

	public void aSearchSchoolBlockSort(String uri, String apiMethod, SearchList<SchoolBlock> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addSort(varIndexed, ORDER.valueOf(valueIndexed));
	}

	public void aSearchSchoolBlockRows(String uri, String apiMethod, SearchList<SchoolBlock> searchList, Integer valueRows) {
			searchList.setRows(apiMethod != null && apiMethod.contains("Search") ? valueRows : 10);
	}

	public void aSearchSchoolBlockStart(String uri, String apiMethod, SearchList<SchoolBlock> searchList, Integer valueStart) {
		searchList.setStart(valueStart);
	}

	public void aSearchSchoolBlockVar(String uri, String apiMethod, SearchList<SchoolBlock> searchList, String var, String value) {
		searchList.getSiteRequest_().getRequestVars().put(var, value);
	}

	public void aSearchSchoolBlockUri(String uri, String apiMethod, SearchList<SchoolBlock> searchList) {
	}

	public void varsSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<SearchList<OperationResponse>>> eventHandler) {
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
					LOGGER.error(String.format("aSearchSchoolBlock failed. ", e));
					eventHandler.handle(Future.failedFuture(e));
				}
			});
			eventHandler.handle(Future.succeededFuture());
		} catch(Exception e) {
			LOGGER.error(String.format("aSearchSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void aSearchSchoolBlock(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String uri, String apiMethod, Handler<AsyncResult<SearchList<SchoolBlock>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.setQuery("*:*");
			searchList.setC(SchoolBlock.class);
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
								varIndexed = "*".equals(entityVar) ? entityVar : SchoolBlock.varSearchSchoolBlock(entityVar);
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								valueIndexed = StringUtils.isEmpty(valueIndexed) ? "*" : valueIndexed;
								aSearchSchoolBlockQ(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "fq":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = SchoolBlock.varIndexedSchoolBlock(entityVar);
								aSearchSchoolBlockFq(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = SchoolBlock.varIndexedSchoolBlock(entityVar);
								aSearchSchoolBlockSort(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "start":
								valueStart = (Integer)paramObject;
								aSearchSchoolBlockStart(uri, apiMethod, searchList, valueStart);
								break;
							case "rows":
								valueRows = (Integer)paramObject;
								aSearchSchoolBlockRows(uri, apiMethod, searchList, valueRows);
								break;
							case "var":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								aSearchSchoolBlockVar(uri, apiMethod, searchList, entityVar, valueIndexed);
								break;
						}
					}
					aSearchSchoolBlockUri(uri, apiMethod, searchList);
				} catch(Exception e) {
					LOGGER.error(String.format("aSearchSchoolBlock failed. ", e));
					eventHandler.handle(Future.failedFuture(e));
				}
			});
			if(searchList.getSorts().size() == 0) {
				searchList.addSort("created_indexed_date", ORDER.desc);
			}
			searchList.initDeepForClass(siteRequest);
			eventHandler.handle(Future.succeededFuture(searchList));
		} catch(Exception e) {
			LOGGER.error(String.format("aSearchSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void defineSchoolBlock(SchoolBlock o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						LOGGER.error(String.format("defineSchoolBlock failed. ", e));
						eventHandler.handle(Future.failedFuture(e));
					}
				} else {
					LOGGER.error(String.format("defineSchoolBlock failed. ", defineAsync.cause()));
					eventHandler.handle(Future.failedFuture(defineAsync.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("defineSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void attributeSchoolBlock(SchoolBlock o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						LOGGER.error(String.format("attributeSchoolBlock failed. ", attributeAsync.cause()));
						eventHandler.handle(Future.failedFuture(attributeAsync.cause()));
					}
				} catch(Exception e) {
					LOGGER.error(String.format("attributeSchoolBlock failed. ", e));
					eventHandler.handle(Future.failedFuture(e));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("attributeSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void indexSchoolBlock(SchoolBlock o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			eventHandler.handle(Future.succeededFuture());
		} catch(Exception e) {
			LOGGER.error(String.format("indexSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void refreshSchoolBlock(SchoolBlock o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Boolean refresh = !"false".equals(siteRequest.getRequestVars().get("refresh"));
			if(refresh && BooleanUtils.isFalse(Optional.ofNullable(siteRequest.getApiRequest_()).map(ApiRequest::getEmpty).orElse(true))) {
				SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolBlock.class);
				searchList.addFilterQuery("modified_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(siteRequest.getApiRequest_().getCreated().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				searchList.add("json.facet", "{enrollmentKeys:{terms:{field:enrollmentKeys_indexed_longs, limit:1000}}}");
				searchList.add("json.facet", "{ageKey:{terms:{field:ageKey_indexed_longs, limit:1000}}}");
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
							SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolBlock(siteContext, siteRequest.getOperationRequest(), new JsonObject());
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

					if("SchoolAge".equals(classSimpleName2) && pk2 != null) {
						SearchList<SchoolAge> searchList2 = new SearchList<SchoolAge>();
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolAge.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk2);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest);
						SchoolAge o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							SchoolAgeEnUSGenApiServiceImpl service = new SchoolAgeEnUSGenApiServiceImpl(siteRequest.getSiteContext_());
							SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolBlock(siteContext, siteRequest.getOperationRequest(), new JsonObject());
							ApiRequest apiRequest2 = new ApiRequest();
							apiRequest2.setRows(1);
							apiRequest2.setNumFound(1l);
							apiRequest2.setNumPATCH(0L);
							apiRequest2.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest2);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolAge", JsonObject.mapFrom(apiRequest2).toString());

							o2.setPk(pk2);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolAgeFuture(o2, false, a -> {
									if(a.succeeded()) {
									} else {
										LOGGER.info(String.format("SchoolAge %s failed. ", pk2));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						SchoolBlockEnUSApiServiceImpl service = new SchoolBlockEnUSApiServiceImpl(siteRequest.getSiteContext_());
						List<Future> futures2 = new ArrayList<>();
						for(SchoolBlock o2 : searchList.getList()) {
							SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolBlock(siteContext, siteRequest.getOperationRequest(), new JsonObject());
							o2.setSiteRequest_(siteRequest2);
							futures2.add(
								service.patchSchoolBlockFuture(o2, false, b -> {
									if(b.succeeded()) {
									} else {
										LOGGER.info(String.format("SchoolBlock %s failed. ", o2.getPk()));
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
								errorSchoolBlock(siteRequest, eventHandler, b);
							}
						});
					} else {
						LOGGER.error("Refresh relations failed. ", a.cause());
						errorSchoolBlock(siteRequest, eventHandler, a);
					}
				});
			} else {
				eventHandler.handle(Future.succeededFuture());
			}
		} catch(Exception e) {
			LOGGER.error(String.format("refreshSchoolBlock failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}
}
