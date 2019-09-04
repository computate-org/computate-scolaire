package org.computate.scolaire.enUS.age;

import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import org.computate.scolaire.frFR.recherche.ResultatRecherche;
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
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.writer.AllWriter;


/**
 * Translate: false
 **/
public class SchoolAgeEnUSGenApiServiceImpl implements SchoolAgeEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolAgeEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SchoolAgeEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public SchoolAgeEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
		SchoolAgeEnUSGenApiService service = SchoolAgeEnUSGenApiService.createProxy(siteContext.getVertx(), SERVICE_ADDRESS);
	}

	// POST //

	@Override
	public void postSchoolAge(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest, body);
			sqlSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					createPOSTSchoolAge(siteRequest, b -> {
						if(b.succeeded()) {
							SchoolAge schoolAge = b.result();
							sqlPOSTSchoolAge(schoolAge, c -> {
								if(c.succeeded()) {
									defineSchoolAge(schoolAge, d -> {
										if(d.succeeded()) {
											attributeSchoolAge(schoolAge, e -> {
												if(e.succeeded()) {
													indexSchoolAge(schoolAge, f -> {
														if(f.succeeded()) {
															response200POSTSchoolAge(schoolAge, g -> {
																if(f.succeeded()) {
																	SQLConnection sqlConnection = siteRequest.getSqlConnection();
																	sqlConnection.commit(h -> {
																		if(a.succeeded()) {
																			sqlConnection.close(i -> {
																				if(a.succeeded()) {
																					eventHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					errorSchoolAge(siteRequest, eventHandler, i);
																				}
																			});
																		} else {
																			errorSchoolAge(siteRequest, eventHandler, h);
																		}
																	});
																} else {
																	errorSchoolAge(siteRequest, eventHandler, g);
																}
															});
														} else {
															errorSchoolAge(siteRequest, eventHandler, f);
														}
													});
												} else {
													errorSchoolAge(siteRequest, eventHandler, e);
												}
											});
										} else {
											errorSchoolAge(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolAge(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void createPOSTSchoolAge(SiteRequestEnUS siteRequest, Handler<AsyncResult<SchoolAge>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(SchoolAge.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				SchoolAge o = new SchoolAge();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlPOSTSchoolAge(SchoolAge o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "ageStart":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ageStart", jsonObject.getInteger(entityVar), pk));
						break;
					case "ageEnd":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ageEnd", jsonObject.getInteger(entityVar), pk));
						break;
					}
				}
			}
			sqlConnection.updateWithParams(
					postSql.toString()
					, new JsonArray(postSqlParams)
					, postAsync
			-> {
				eventHandler.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void response200POSTSchoolAge(SchoolAge o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSchoolAge(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest, body);
			sqlSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolAge(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolAge(siteRequest, false, true, null, c -> {
								if(c.succeeded()) {
									SearchList<SchoolAge> listSchoolAge = c.result();
									listPATCHSchoolAge(listSchoolAge, d -> {
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
																errorSchoolAge(siteRequest, eventHandler, f);
															}
														});
													} else {
														errorSchoolAge(siteRequest, eventHandler, e);
													}
												});
											}
										} else {
											errorSchoolAge(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolAge(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void listPATCHSchoolAge(SearchList<SchoolAge> listSchoolAge, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		listSchoolAge.getList().forEach(o -> {
			futures.add(
				futurePATCHSchoolAge(o, eventHandler)
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				response200PATCHSchoolAge(listSchoolAge, eventHandler);
			} else {
				errorSchoolAge(listSchoolAge.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolAge> futurePATCHSchoolAge(SchoolAge o,  Handler<AsyncResult<OperationResponse>> eventHandler) {
		Future<SchoolAge> future = Future.future();
		try {
			sqlPATCHSchoolAge(o, a -> {
				if(a.succeeded()) {
					SchoolAge schoolAge = a.result();
					defineSchoolAge(schoolAge, b -> {
						if(b.succeeded()) {
							attributeSchoolAge(schoolAge, c -> {
								if(c.succeeded()) {
									indexSchoolAge(schoolAge, d -> {
										if(d.succeeded()) {
											future.complete(o);
											eventHandler.handle(Future.succeededFuture(d.result()));
										} else {
											errorSchoolAge(o.getSiteRequest_(), eventHandler, d);
										}
									});
								} else {
									errorSchoolAge(o.getSiteRequest_(), eventHandler, c);
								}
							});
						} else {
							errorSchoolAge(o.getSiteRequest_(), eventHandler, b);
						}
					});
				} else {
					errorSchoolAge(o.getSiteRequest_(), eventHandler, a);
				}
			});
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public void sqlPATCHSchoolAge(SchoolAge o, Handler<AsyncResult<SchoolAge>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject requestJson = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = requestJson.fieldNames();
			SchoolAge o2 = new SchoolAge();

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.age.SchoolAge"));
			for(String methodName : methodNames) {
				switch(methodName) {
					case "setCreated":
						o2.setCreated(requestJson.getString(methodName));
						if(o2.getCreated() == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "created"));
						} else {
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("created", o2.strCreated(), pk));
						}
						break;
					case "setModified":
						o2.setModified(requestJson.getString(methodName));
						if(o2.getModified() == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "modified"));
						} else {
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("modified", o2.strModified(), pk));
						}
						break;
					case "setArchived":
						o2.setArchived(requestJson.getBoolean(methodName));
						if(o2.getArchived() == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "archived"));
						} else {
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("archived", o2.strArchived(), pk));
						}
						break;
					case "setDeleted":
						o2.setDeleted(requestJson.getBoolean(methodName));
						if(o2.getDeleted() == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "deleted"));
						} else {
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("deleted", o2.strDeleted(), pk));
						}
						break;
					case "setAgeStart":
						o2.setAgeStart(requestJson.getInteger(methodName));
						if(o2.getAgeStart() == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "ageStart"));
						} else {
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("ageStart", o2.strAgeStart(), pk));
						}
						break;
					case "setAgeEnd":
						o2.setAgeEnd(requestJson.getInteger(methodName));
						if(o2.getAgeEnd() == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "ageEnd"));
						} else {
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("ageEnd", o2.strAgeEnd(), pk));
						}
						break;
				}
			}
			sqlConnection.updateWithParams(
					patchSql.toString()
					, new JsonArray(patchSqlParams)
					, patchAsync
			-> {
				SchoolAge o3 = new SchoolAge();
				o3.setSiteRequest_(o.getSiteRequest_());
				o3.setPk(pk);
				eventHandler.handle(Future.succeededFuture(o3));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void response200PATCHSchoolAge(SearchList<SchoolAge> listSchoolAge, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolAge.getSiteRequest_();
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSchoolAge(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest);
			aSearchSchoolAge(siteRequest, false, true, null, a -> {
				if(a.succeeded()) {
					SearchList<SchoolAge> listSchoolAge = a.result();
					response200GETSchoolAge(listSchoolAge, b -> {
						if(b.succeeded()) {
							eventHandler.handle(Future.succeededFuture(b.result()));
						} else {
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolAge(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200GETSchoolAge(SearchList<SchoolAge> listSchoolAge, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolAge.getSiteRequest_();
			SolrDocumentList solrDocuments = listSchoolAge.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listSchoolAge.get(0));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteSchoolAge(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest);
			sqlSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					aSearchSchoolAge(siteRequest, false, true, null, b -> {
						if(b.succeeded()) {
							SearchList<SchoolAge> listSchoolAge = b.result();
							deleteDELETESchoolAge(siteRequest, c -> {
								if(c.succeeded()) {
									response200DELETESchoolAge(siteRequest, d -> {
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
																errorSchoolAge(siteRequest, eventHandler, f);
															}
														});
													} else {
														errorSchoolAge(siteRequest, eventHandler, e);
													}
												});
											}
										} else {
											errorSchoolAge(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolAge(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void deleteDELETESchoolAge(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			Long pk = siteRequest.getRequestPk();

			sqlConnection.updateWithParams(
					SiteContextEnUS.SQL_delete
					, new JsonArray(Arrays.asList(pk, SchoolAge.class.getCanonicalName(), pk, pk, pk, pk))
					, deleteAsync
			-> {
				eventHandler.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void response200DELETESchoolAge(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(json)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchSchoolAge(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest);
			aSearchSchoolAge(siteRequest, false, true, null, a -> {
				if(a.succeeded()) {
					SearchList<SchoolAge> listSchoolAge = a.result();
					response200SearchSchoolAge(listSchoolAge, b -> {
						if(b.succeeded()) {
							eventHandler.handle(Future.succeededFuture(b.result()));
						} else {
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolAge(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200SearchSchoolAge(SearchList<SchoolAge> listSchoolAge, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolAge.getSiteRequest_();
			QueryResponse responseSearch = listSchoolAge.getQueryResponse();
			SolrDocumentList solrDocuments = listSchoolAge.getSolrDocumentList();
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
			listSchoolAge.getList().stream().forEach(o -> {
				l.add(JsonObject.mapFrom(o));
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
	public void searchpageSchoolAgeId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageSchoolAge(operationRequest, eventHandler);
	}

	@Override
	public void searchpageSchoolAge(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest);
			sqlSchoolAge(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolAge(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchoolAge(siteRequest, false, true, "/enUS/age", c -> {
								if(c.succeeded()) {
									SearchList<SchoolAge> listSchoolAge = c.result();
									response200SearchPageSchoolAge(listSchoolAge, d -> {
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
																errorSchoolAge(siteRequest, eventHandler, f);
															}
														});
													} else {
														errorSchoolAge(siteRequest, eventHandler, e);
													}
												});
											}
										} else {
											errorSchoolAge(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchoolAge(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolAge(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolAge(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchoolAge(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200SearchPageSchoolAge(SearchList<SchoolAge> listSchoolAge, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolAge.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listSchoolAge.getSiteRequest_(), buffer);
			AgePage page = new AgePage();
			SolrDocument pageSolrDocument = new SolrDocument();

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/enUS/age");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			page.setListSchoolAge(listSchoolAge);
			page.initDeepAgePage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, new CaseInsensitiveHeaders())));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public String varIndexedSchoolAge(String entityVar) {
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
			case "schoolKey":
				return "schoolKey_indexed_long";
			case "seasonKey":
				return "seasonKey_indexed_long";
			case "sessionKey":
				return "sessionKey_indexed_long";
			case "ageKey":
				return "ageKey_indexed_long";
			case "enrollmentKeys":
				return "enrollmentKeys_indexed_longs";
			case "blockKeys":
				return "blockKeys_indexed_longs";
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
			case "schoolNameComplete":
				return "schoolNameComplete_indexed_string";
			case "yearStart":
				return "yearStart_indexed_date";
			case "yearEnd":
				return "yearEnd_indexed_date";
			case "seasonStart":
				return "seasonStart_indexed_date";
			case "seasonSummer":
				return "seasonSummer_indexed_boolean";
			case "seasonWinter":
				return "seasonWinter_indexed_boolean";
			case "seasonEnrollmentFee":
				return "seasonEnrollmentFee_indexed_double";
			case "seasonNameComplete":
				return "seasonNameComplete_indexed_string";
			case "seasonEnd":
				return "seasonEnd_indexed_date";
			case "sessionStartDay":
				return "sessionStartDay_indexed_date";
			case "sessionEndDay":
				return "sessionEndDay_indexed_date";
			case "sessionNameComplete":
				return "sessionNameComplete_indexed_string";
			case "ageStart":
				return "ageStart_indexed_int";
			case "ageEnd":
				return "ageEnd_indexed_int";
			case "ageNameComplete":
				return "ageNameComplete_indexed_string";
			case "ageId":
				return "ageId_indexed_string";
			case "pageUrl":
				return "pageUrl_indexed_string";
			case "objectSuggest":
				return "objectSuggest_indexed_string";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	public String varSearchSchoolAge(String entityVar) {
		switch(entityVar) {
			case "objectSuggest":
				return "objectSuggest_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	public String varSuggereSchoolAge(String entityVar) {
		switch(entityVar) {
			case "objectSuggest":
				return "objectSuggest_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	// Partagé //

	public void errorSchoolAge(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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

	public void sqlSchoolAge(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						eventHandler.handle(Future.failedFuture(sqlAsync.cause()));
					}
				});
			}
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolAge(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForSchoolAge(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolAge(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userSchoolAge(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
										eventHandler.handle(Future.failedFuture(defineAsync.cause()));
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
									eventHandler.handle(Future.failedFuture(defineAsync.cause()));
								}
							});
						}
					} else {
						eventHandler.handle(Future.failedFuture(selectCAsync.cause()));
					}
				});
			}
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void aSearchSchoolAge(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String classApiUriMethod, Handler<AsyncResult<SearchList<SchoolAge>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<SchoolAge> listSearch = new SearchList<SchoolAge>();
			listSearch.setPopulate(populate);
			listSearch.setStore(store);
			listSearch.setQuery("*:*");
			listSearch.setC(SchoolAge.class);
			if(entityList != null)
			listSearch.setFields(entityList);
			listSearch.addSort("archived_indexed_boolean", ORDER.asc);
			listSearch.addSort("deleted_indexed_boolean", ORDER.asc);
			listSearch.addFilterQuery("classCanonicalNames_indexed_strings:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.age.SchoolAge"));
			SiteUser siteUser = siteRequest.getSiteUser();
			if(siteUser != null && !siteUser.getSeeDeleted())
				listSearch.addFilterQuery("deleted_indexed_boolean:false");
			if(siteUser != null && !siteUser.getSeeArchived())
				listSearch.addFilterQuery("archived_indexed_boolean:false");

			String id = operationRequest.getParams().getJsonObject("path").getString("id");
			if(id != null) {
				listSearch.addFilterQuery("(id:" + ClientUtils.escapeQueryChars(id) + " OR ageId_indexed_string:" + ClientUtils.escapeQueryChars(id) + ")");
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
								varIndexed = "*".equals(entityVar) ? entityVar : varSearchSchoolAge(entityVar);
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
								varIndexed = varIndexedSchoolAge(entityVar);
								listSearch.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueSort = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = varIndexedSchoolAge(entityVar);
								listSearch.addSort(varIndexed, ORDER.valueOf(valueSort));
								break;
							case "fl":
								entityVar = StringUtils.trim((String)paramObject);
								varIndexed = varIndexedSchoolAge(entityVar);
								listSearch.addField(varIndexed);
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

	public void defineSchoolAge(SchoolAge o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					eventHandler.handle(Future.failedFuture(defineAsync.cause()));
				}
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void attributeSchoolAge(SchoolAge o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						eventHandler.handle(Future.failedFuture(attributeAsync.cause()));
					}
				} catch(Exception e) {
					eventHandler.handle(Future.failedFuture(e));
				}
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void indexSchoolAge(SchoolAge o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
