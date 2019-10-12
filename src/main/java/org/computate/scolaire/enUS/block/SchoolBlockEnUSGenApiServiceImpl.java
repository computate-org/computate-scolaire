package org.computate.scolaire.enUS.block;

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
public class SchoolBlockEnUSGenApiServiceImpl implements SchoolBlockEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolBlockEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SchoolBlockEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public SchoolBlockEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
		SchoolBlockEnUSGenApiService service = SchoolBlockEnUSGenApiService.createProxy(siteContext.getVertx(), SERVICE_ADDRESS);
	}

	// POST //

	@Override
	public void postSchoolBlock(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
			sqlSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					createPOSTSchoolBlock(siteRequest, b -> {
						if(b.succeeded()) {
							SchoolBlock schoolBlock = b.result();
							sqlPOSTSchoolBlock(schoolBlock, c -> {
								if(c.succeeded()) {
									defineSchoolBlock(schoolBlock, d -> {
										if(d.succeeded()) {
											attributeSchoolBlock(schoolBlock, e -> {
												if(e.succeeded()) {
													indexSchoolBlock(schoolBlock, f -> {
														if(f.succeeded()) {
															response200POSTSchoolBlock(schoolBlock, g -> {
																if(f.succeeded()) {
																	SQLConnection sqlConnection = siteRequest.getSqlConnection();
																	sqlConnection.commit(h -> {
																		if(a.succeeded()) {
																			sqlConnection.close(i -> {
																				if(a.succeeded()) {
																					eventHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					errorSchoolBlock(siteRequest, eventHandler, i);
																				}
																			});
																		} else {
																			errorSchoolBlock(siteRequest, eventHandler, h);
																		}
																	});
																} else {
																	errorSchoolBlock(siteRequest, eventHandler, g);
																}
															});
														} else {
															errorSchoolBlock(siteRequest, eventHandler, f);
														}
													});
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
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolBlock(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void createPOSTSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<SchoolBlock>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(SchoolBlock.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				SchoolBlock o = new SchoolBlock();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlPOSTSchoolBlock(SchoolBlock o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
							postSqlParams.addAll(Arrays.asList("blockKeys", l, "enrollmentKeys", pk));
						}
						break;
					case "blockStartTime":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockStartTime", jsonObject.getString(entityVar), pk));
						break;
					case "blockEndTime":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockEndTime", jsonObject.getString(entityVar), pk));
						break;
					case "blockPricePerMonth":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockPricePerMonth", jsonObject.getString(entityVar), pk));
						break;
					case "blockSunday":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockSunday", jsonObject.getBoolean(entityVar), pk));
						break;
					case "blockMonday":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockMonday", jsonObject.getBoolean(entityVar), pk));
						break;
					case "blockTuesday":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockTuesday", jsonObject.getBoolean(entityVar), pk));
						break;
					case "blockWednesday":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockWednesday", jsonObject.getBoolean(entityVar), pk));
						break;
					case "blockThursday":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockThursday", jsonObject.getBoolean(entityVar), pk));
						break;
					case "blockFriday":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockFriday", jsonObject.getBoolean(entityVar), pk));
						break;
					case "blockSaturday":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("blockSaturday", jsonObject.getBoolean(entityVar), pk));
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

	public void response200POSTSchoolBlock(SchoolBlock o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
	public void patchSchoolBlock(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest, body);
			sqlSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolBlock(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolBlock(siteRequest, false, true, null, c -> {
								if(c.succeeded()) {
									SearchList<SchoolBlock> listSchoolBlock = c.result();
									SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolBlock.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
									Date date = null;
									if(facets != null)
										date = (Date)facets.get("max_modified");
									String dateStr;
									if(date == null)
										dateStr = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
									else
										dateStr = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
									listPATCHSchoolBlock(listSchoolBlock, dateStr, d -> {
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
																errorSchoolBlock(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											errorSchoolBlock(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolBlock(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void listPATCHSchoolBlock(SearchList<SchoolBlock> listSchoolBlock, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		listSchoolBlock.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));
		SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
		listSchoolBlock.getList().forEach(o -> {
			futures.add(
				futurePATCHSchoolBlock(o, a -> {
					if(a.succeeded()) {
					} else {
						errorSchoolBlock(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listSchoolBlock.next(dt)) {
					listPATCHSchoolBlock(listSchoolBlock, dt, eventHandler);
				} else {
					response200PATCHSchoolBlock(listSchoolBlock, eventHandler);
				}
			} else {
				errorSchoolBlock(listSchoolBlock.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolBlock> futurePATCHSchoolBlock(SchoolBlock o,  Handler<AsyncResult<OperationResponse>> eventHandler) {
		Future<SchoolBlock> future = Future.future();
		try {
			sqlPATCHSchoolBlock(o, a -> {
				if(a.succeeded()) {
					SchoolBlock schoolBlock = a.result();
					defineSchoolBlock(schoolBlock, b -> {
						if(b.succeeded()) {
							attributeSchoolBlock(schoolBlock, c -> {
								if(c.succeeded()) {
									indexSchoolBlock(schoolBlock, d -> {
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

	public void sqlPATCHSchoolBlock(SchoolBlock o, Handler<AsyncResult<SchoolBlock>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject requestJson = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = requestJson.fieldNames();
			SchoolBlock o2 = new SchoolBlock();

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.block.SchoolBlock"));
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
					case "setBlockStartTime":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockStartTime"));
						} else {
							o2.setBlockStartTime(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockStartTime", o2.jsonBlockStartTime(), pk));
						}
						break;
					case "setBlockEndTime":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockEndTime"));
						} else {
							o2.setBlockEndTime(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockEndTime", o2.jsonBlockEndTime(), pk));
						}
						break;
					case "setBlockPricePerMonth":
						if(requestJson.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockPricePerMonth"));
						} else {
							o2.setBlockPricePerMonth(requestJson.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockPricePerMonth", o2.jsonBlockPricePerMonth(), pk));
						}
						break;
					case "setBlockSunday":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockSunday"));
						} else {
							o2.setBlockSunday(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockSunday", o2.jsonBlockSunday(), pk));
						}
						break;
					case "setBlockMonday":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockMonday"));
						} else {
							o2.setBlockMonday(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockMonday", o2.jsonBlockMonday(), pk));
						}
						break;
					case "setBlockTuesday":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockTuesday"));
						} else {
							o2.setBlockTuesday(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockTuesday", o2.jsonBlockTuesday(), pk));
						}
						break;
					case "setBlockWednesday":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockWednesday"));
						} else {
							o2.setBlockWednesday(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockWednesday", o2.jsonBlockWednesday(), pk));
						}
						break;
					case "setBlockThursday":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockThursday"));
						} else {
							o2.setBlockThursday(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockThursday", o2.jsonBlockThursday(), pk));
						}
						break;
					case "setBlockFriday":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockFriday"));
						} else {
							o2.setBlockFriday(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockFriday", o2.jsonBlockFriday(), pk));
						}
						break;
					case "setBlockSaturday":
						if(requestJson.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "blockSaturday"));
						} else {
							o2.setBlockSaturday(requestJson.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("blockSaturday", o2.jsonBlockSaturday(), pk));
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
					SchoolBlock o3 = new SchoolBlock();
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

	public void response200PATCHSchoolBlock(SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSchoolBlock(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest);
			aSearchSchoolBlock(siteRequest, false, true, null, a -> {
				if(a.succeeded()) {
					SearchList<SchoolBlock> listSchoolBlock = a.result();
					response200GETSchoolBlock(listSchoolBlock, b -> {
						if(b.succeeded()) {
							eventHandler.handle(Future.succeededFuture(b.result()));
						} else {
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolBlock(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200GETSchoolBlock(SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
			SolrDocumentList solrDocuments = listSchoolBlock.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listSchoolBlock.get(0));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteSchoolBlock(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest);
			sqlSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					aSearchSchoolBlock(siteRequest, false, true, null, b -> {
						if(b.succeeded()) {
							SearchList<SchoolBlock> listSchoolBlock = b.result();
							deleteDELETESchoolBlock(siteRequest, c -> {
								if(c.succeeded()) {
									response200DELETESchoolBlock(siteRequest, d -> {
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
																errorSchoolBlock(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											errorSchoolBlock(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolBlock(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void deleteDELETESchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			Long pk = siteRequest.getRequestPk();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_delete
					, new JsonArray(Arrays.asList(pk, SchoolBlock.class.getCanonicalName(), pk, pk, pk, pk))
					, deleteAsync
			-> {
				eventHandler.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void response200DELETESchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchSchoolBlock(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest);
			aSearchSchoolBlock(siteRequest, false, true, null, a -> {
				if(a.succeeded()) {
					SearchList<SchoolBlock> listSchoolBlock = a.result();
					response200SearchSchoolBlock(listSchoolBlock, b -> {
						if(b.succeeded()) {
							eventHandler.handle(Future.succeededFuture(b.result()));
						} else {
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolBlock(null, eventHandler, Future.failedFuture(e));
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
	public void searchpageSchoolBlockId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageSchoolBlock(operationRequest, eventHandler);
	}

	@Override
	public void searchpageSchoolBlock(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolBlock(siteContext, operationRequest);
			sqlSchoolBlock(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolBlock(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolBlock(siteRequest, false, true, "/block", c -> {
								if(c.succeeded()) {
									SearchList<SchoolBlock> listSchoolBlock = c.result();
									response200SearchPageSchoolBlock(listSchoolBlock, d -> {
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
																errorSchoolBlock(siteRequest, eventHandler, f);
															}
														});
													} else {
														eventHandler.handle(Future.succeededFuture(d.result()));
													}
												});
											}
										} else {
											errorSchoolBlock(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolBlock(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolBlock(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolBlock(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolBlock(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200SearchPageSchoolBlock(SearchList<SchoolBlock> listSchoolBlock, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolBlock.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listSchoolBlock.getSiteRequest_(), buffer);
			BlockPage page = new BlockPage();
			SolrDocument pageSolrDocument = new SolrDocument();

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/block");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			page.setListSchoolBlock(listSchoolBlock);
			page.initDeepBlockPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, new CaseInsensitiveHeaders())));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public String varIndexedSchoolBlock(String entityVar) {
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
			case "blockKey":
				return "blockKey_indexed_long";
			case "childKey":
				return "childKey_indexed_long";
			case "enrollmentKeys":
				return "enrollmentKeys_indexed_longs";
			case "educationSort":
				return "educationSort_indexed_int";
			case "schoolSort":
				return "schoolSort_indexed_int";
			case "yearSort":
				return "yearSort_indexed_int";
			case "seasonSort":
				return "seasonSort_indexed_int";
			case "sessionSort":
				return "sessionSort_indexed_int";
			case "ageSort":
				return "ageSort_indexed_int";
			case "schoolKey":
				return "schoolKey_indexed_long";
			case "yearKey":
				return "yearKey_indexed_long";
			case "seasonKey":
				return "seasonKey_indexed_long";
			case "sessionKey":
				return "sessionKey_indexed_long";
			case "ageKey":
				return "ageKey_indexed_long";
			case "schoolCompleteName":
				return "schoolCompleteName_indexed_string";
			case "schoolLocation":
				return "schoolLocation_indexed_string";
			case "yearStart":
				return "yearStart_indexed_int";
			case "yearEnd":
				return "yearEnd_indexed_int";
			case "seasonStartDate":
				return "seasonStartDate_indexed_date";
			case "seasonSummer":
				return "seasonSummer_indexed_boolean";
			case "seasonWinter":
				return "seasonWinter_indexed_boolean";
			case "seasonEnrollmentFee":
				return "seasonEnrollmentFee_indexed_double";
			case "seasonCompleteName":
				return "seasonCompleteName_indexed_string";
			case "sessionStartDay":
				return "sessionStartDay_indexed_date";
			case "sessionEndDay":
				return "sessionEndDay_indexed_date";
			case "ageCompleteName":
				return "ageCompleteName_indexed_string";
			case "ageStart":
				return "ageStart_indexed_int";
			case "ageEnd":
				return "ageEnd_indexed_int";
			case "blockStartTime":
				return "blockStartTime_indexed_string";
			case "blockEndTime":
				return "blockEndTime_indexed_string";
			case "blockPricePerMonth":
				return "blockPricePerMonth_indexed_double";
			case "blockSunday":
				return "blockSunday_indexed_boolean";
			case "blockMonday":
				return "blockMonday_indexed_boolean";
			case "blockTuesday":
				return "blockTuesday_indexed_boolean";
			case "blockWednesday":
				return "blockWednesday_indexed_boolean";
			case "blockThursday":
				return "blockThursday_indexed_boolean";
			case "blockFriday":
				return "blockFriday_indexed_boolean";
			case "blockSaturday":
				return "blockSaturday_indexed_boolean";
			case "blockTotalPrice":
				return "blockTotalPrice_indexed_double";
			case "blockCompleteName":
				return "blockCompleteName_indexed_string";
			case "blocId":
				return "blocId_indexed_string";
			case "pageUrl":
				return "pageUrl_indexed_string";
			case "objectSuggest":
				return "objectSuggest_indexed_string";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	public String varSearchSchoolBlock(String entityVar) {
		switch(entityVar) {
			case "objectSuggest":
				return "objectSuggest_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	public String varSuggereSchoolBlock(String entityVar) {
		switch(entityVar) {
			case "objectSuggest":
				return "objectSuggest_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	// Partagé //

	public void errorSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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

	public void sqlSchoolBlock(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void aSearchSchoolBlock(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String classApiUriMethod, Handler<AsyncResult<SearchList<SchoolBlock>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<SchoolBlock> listSearch = new SearchList<SchoolBlock>();
			listSearch.setPopulate(populate);
			listSearch.setStore(store);
			listSearch.setQuery("*:*");
			listSearch.setC(SchoolBlock.class);
			if(entityList != null)
				listSearch.addFields(entityList);
			listSearch.addSort("created_indexed_date", ORDER.desc);
			listSearch.set("json.facet", "{max_modified:'max(modified_indexed_date)'}");

			String id = operationRequest.getParams().getJsonObject("path").getString("id");
			if(id != null) {
				listSearch.addFilterQuery("(id:" + ClientUtils.escapeQueryChars(id) + " OR blocId_indexed_string:" + ClientUtils.escapeQueryChars(id) + ")");
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
								varIndexed = "*".equals(entityVar) ? entityVar : varSearchSchoolBlock(entityVar);
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
								varIndexed = varIndexedSchoolBlock(entityVar);
								listSearch.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueSort = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = varIndexedSchoolBlock(entityVar);
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

	public void defineSchoolBlock(SchoolBlock o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void attributeSchoolBlock(SchoolBlock o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void indexSchoolBlock(SchoolBlock o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
