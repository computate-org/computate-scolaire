package org.computate.scolaire.enUS.school;

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
import org.computate.scolaire.frFR.ecole.EcoleFrFRPage;
import org.computate.scolaire.enUS.school.EcoleEnUSPage;


/**
 * Translate: false
 **/
public class SchoolEnUSGenApiServiceImpl implements SchoolEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SchoolEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public SchoolEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
		SchoolEnUSGenApiService service = SchoolEnUSGenApiService.createProxy(siteContext.getVertx(), SERVICE_ADDRESS);
	}

	// RechercheEnUSPage //

	@Override
	public void rechercheenuspageEcoleId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		rechercheenuspageEcole(operationRequest, eventHandler);
	}

	@Override
	public void rechercheenuspageEcole(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest);
			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					userSchool(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchool(siteRequest, false, true, "/enUS/school", c -> {
								if(c.succeeded()) {
									SearchList<School> listSchool = c.result();
									response200RechercheEnUSPageSchool(listSchool, d -> {
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
																errorSchool(siteRequest, eventHandler, f);
															}
														});
													} else {
														errorSchool(siteRequest, eventHandler, e);
													}
												});
											}
										} else {
											errorSchool(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchool(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200RechercheEnUSPageSchool(SearchList<School> listSchool, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			Buffer buffer = Buffer.buffer();
			SiteRequestEnUS siteRequest = listSchool.getSiteRequest_();
			AllWriter w = AllWriter.create(listSchool.getSiteRequest_(), buffer);
			siteRequest.setW(w);
			EcoleEnUSPage page = new EcoleEnUSPage();
			SolrDocument pageSolrDocument = new SolrDocument();

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/enUS/school");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			page.setListSchool(listSchool);
			page.initDeepEcoleEnUSPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, new CaseInsensitiveHeaders())));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchEcole(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest, body);
			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					userSchool(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchSchool(siteRequest, false, true, null, c -> {
								if(c.succeeded()) {
									SearchList<School> listSchool = c.result();
									listPATCHSchool(listSchool, d -> {
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
																errorSchool(siteRequest, eventHandler, f);
															}
														});
													} else {
														errorSchool(siteRequest, eventHandler, e);
													}
												});
											}
										} else {
											errorSchool(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchool(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void listPATCHSchool(SearchList<School> listSchool, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		listSchool.getList().forEach(o -> {
			futures.add(
				sqlPATCHSchool(o).compose(
					a -> definePATCHSchool(a).compose(
						b -> indexPATCHSchool(b)
					)
				)
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				response200PATCHSchool(listSchool, eventHandler);
			} else {
				errorSchool(listSchool.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<School> sqlPATCHSchool(School o) {
		Future<School> future = Future.future();
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject requestJson = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = requestJson.fieldNames();
			School o2 = new School();

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.school.School"));
			for(String methodName : methodNames) {
				switch(methodName) {
					case "setCreated":
						o2.setCreated(requestJson.getInstant(methodName));
						patchSql.append(SiteContextEnUS.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("created", o2.getCreated(), pk));
						break;
					case "setModified":
						o2.setModified(requestJson.getInstant(methodName));
						patchSql.append(SiteContextEnUS.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("modified", o2.getModified(), pk));
						break;
					case "setArchived":
						o2.setArchived(requestJson.getBoolean(methodName));
						patchSql.append(SiteContextEnUS.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("archived", o2.getArchived(), pk));
						break;
					case "setDeleted":
						o2.setDeleted(requestJson.getBoolean(methodName));
						patchSql.append(SiteContextEnUS.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("deleted", o2.getDeleted(), pk));
						break;
					case "setEcoleNom":
						o2.setEcoleNom(requestJson.getString(methodName));
						patchSql.append(SiteContextEnUS.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("ecoleNom", o2.getEcoleNom(), pk));
						break;
					case "setEcoleNumeroTelephone":
						o2.setEcoleNumeroTelephone(requestJson.getString(methodName));
						patchSql.append(SiteContextEnUS.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("ecoleNumeroTelephone", o2.getEcoleNumeroTelephone(), pk));
						break;
					case "setEcoleAdministrateurNom":
						o2.setEcoleAdministrateurNom(requestJson.getString(methodName));
						patchSql.append(SiteContextEnUS.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("ecoleAdministrateurNom", o2.getEcoleAdministrateurNom(), pk));
						break;
					case "setEcoleAddresse":
						o2.setEcoleAddresse(requestJson.getString(methodName));
						patchSql.append(SiteContextEnUS.SQL_setD);
						patchSqlParams.addAll(Arrays.asList("ecoleAddresse", o2.getEcoleAddresse(), pk));
						break;
				}
			}
			sqlConnection.queryWithParams(
					patchSql.toString()
					, new JsonArray(patchSqlParams)
					, patchAsync
			-> {
				o2.setSiteRequest_(o.getSiteRequest_());
				o2.setPk(pk);
				future.complete(o2);
			});
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public Future<School> definePATCHSchool(School o) {
		Future<School> future = Future.future();
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
					for(JsonArray definition : defineAsync.result().getResults()) {
						o.defineForClass(definition.getString(0), definition.getString(1));
					}
					future.complete(o);
				} else {
			future.fail(defineAsync.cause());
				}
			});
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public Future<Void> indexPATCHSchool(School o) {
		Future<Void> future = Future.future();
		try {
			o.initDeepForClass(o.getSiteRequest_());
			o.indexForClass();
				future.complete();
			return future;
		} catch(Exception e) {
			return Future.failedFuture(e);
		}
	}

	public void response200PATCHSchool(SearchList<School> listSchool, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			Buffer buffer = Buffer.buffer();
			SiteRequestEnUS siteRequest = listSchool.getSiteRequest_();
			AllWriter w = AllWriter.create(listSchool.getSiteRequest_(), buffer);
			siteRequest.setW(w);
			buffer.appendString("{}");
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// POST //

	@Override
	public void postEcole(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest, body);
			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					createPOSTSchool(siteRequest, b -> {
						if(b.succeeded()) {
							School school = b.result();
							sqlPOSTSchool(school, c -> {
								if(c.succeeded()) {
									defineSchool(school, d -> {
										if(d.succeeded()) {
											attributeSchool(school, e -> {
												if(e.succeeded()) {
													indexSchool(school, f -> {
														if(f.succeeded()) {
															response200POSTSchool(school, g -> {
																if(f.succeeded()) {
																	SQLConnection sqlConnection = siteRequest.getSqlConnection();
																	sqlConnection.commit(h -> {
																		if(a.succeeded()) {
																			sqlConnection.close(i -> {
																				if(a.succeeded()) {
																					eventHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					errorSchool(siteRequest, eventHandler, i);
																				}
																			});
																		} else {
																			errorSchool(siteRequest, eventHandler, h);
																		}
																	});
																} else {
																	errorSchool(siteRequest, eventHandler, g);
																}
															});
														} else {
															errorSchool(siteRequest, eventHandler, f);
														}
													});
												} else {
													errorSchool(siteRequest, eventHandler, e);
												}
											});
										} else {
											errorSchool(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchool(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void createPOSTSchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<School>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(School.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				School o = new School();
				o.setPk(pk);
				o.initDeepSchool(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlPOSTSchool(School o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "ecoleNom":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ecoleNom", jsonObject.getString(entityVar), pk));
						break;
					case "ecoleNumeroTelephone":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ecoleNumeroTelephone", jsonObject.getString(entityVar), pk));
						break;
					case "ecoleAdministrateurNom":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ecoleAdministrateurNom", jsonObject.getString(entityVar), pk));
						break;
					case "ecoleAddresse":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("ecoleAddresse", jsonObject.getString(entityVar), pk));
						break;
					}
				}
			}
			sqlConnection.queryWithParams(
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

	public void response200POSTSchool(School o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			Buffer buffer = Buffer.buffer();
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			AllWriter w = AllWriter.create(o.getSiteRequest_(), buffer);
			siteRequest.setW(w);
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getEcole(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest);
			aSearchSchool(siteRequest, false, true, null, a -> {
				if(a.succeeded()) {
					SearchList<School> listSchool = a.result();
					response200GETSchool(listSchool, b -> {
						if(b.succeeded()) {
							eventHandler.handle(Future.succeededFuture(b.result()));
						} else {
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchool(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200GETSchool(SearchList<School> listSchool, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			Buffer buffer = Buffer.buffer();
			SiteRequestEnUS siteRequest = listSchool.getSiteRequest_();
			AllWriter w = AllWriter.create(listSchool.getSiteRequest_(), buffer);
			siteRequest.setW(w);
			SolrDocumentList solrDocuments = listSchool.getSolrDocumentList();

			if(listSchool.size() > 0) {
				SolrDocument solrDocument = solrDocuments.get(0);
				School o = listSchool.get(0);
				Object entityValue;
				Integer entityNumber = 0;

				w.l("{");

				entityValue = o.getPk();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"pk\": ", entityValue);

				entityValue = o.getCreated();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"created\": ", w.qjs(entityValue));

				entityValue = o.getModified();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"modified\": ", w.qjs(entityValue));

				entityValue = o.getArchived();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"archived\": ", entityValue);

				entityValue = o.getDeleted();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"deleted\": ", entityValue);

				entityValue = o.getClassCanonicalName();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"classCanonicalName\": ", w.qjs(entityValue));

				entityValue = o.getClassSimpleName();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"classSimpleName\": ", w.qjs(entityValue));

				{
					List<String> entityValues = o.getClassCanonicalNames();
					w.t(3, entityNumber++ == 0 ? "" : ", ");
					w.s("\"classCanonicalNames\": [");
					for(int k = 0; k < entityValues.size(); k++) {
						entityValue = entityValues.get(k);
						if(k > 0)
							w.s(", ");
						w.s("\"");
						w.s(((String)entityValue));
						w.s("\"");
					}
					w.l("]");
				}

				entityValue = o.getEcoleCle();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleCle\": ", entityValue);

				{
					List<Long> entityValues = o.getEnfantCles();
					w.t(3, entityNumber++ == 0 ? "" : ", ");
					w.s("\"enfantCles\": [");
					for(int k = 0; k < entityValues.size(); k++) {
						entityValue = entityValues.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entityValue).toString());
					}
					w.l("]");
				}

				{
					List<Long> entityValues = o.getBlocCles();
					w.t(3, entityNumber++ == 0 ? "" : ", ");
					w.s("\"blocCles\": [");
					for(int k = 0; k < entityValues.size(); k++) {
						entityValue = entityValues.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entityValue).toString());
					}
					w.l("]");
				}

				{
					List<Long> entityValues = o.getGroupeAgeCles();
					w.t(3, entityNumber++ == 0 ? "" : ", ");
					w.s("\"groupeAgeCles\": [");
					for(int k = 0; k < entityValues.size(); k++) {
						entityValue = entityValues.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entityValue).toString());
					}
					w.l("]");
				}

				{
					List<Long> entityValues = o.getSessionCles();
					w.t(3, entityNumber++ == 0 ? "" : ", ");
					w.s("\"sessionCles\": [");
					for(int k = 0; k < entityValues.size(); k++) {
						entityValue = entityValues.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entityValue).toString());
					}
					w.l("]");
				}

				{
					List<Long> entityValues = o.getSaisonCles();
					w.t(3, entityNumber++ == 0 ? "" : ", ");
					w.s("\"saisonCles\": [");
					for(int k = 0; k < entityValues.size(); k++) {
						entityValue = entityValues.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entityValue).toString());
					}
					w.l("]");
				}

				{
					List<Long> entityValues = o.getAnneeCles();
					w.t(3, entityNumber++ == 0 ? "" : ", ");
					w.s("\"anneeCles\": [");
					for(int k = 0; k < entityValues.size(); k++) {
						entityValue = entityValues.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entityValue).toString());
					}
					w.l("]");
				}

				entityValue = o.getScolaireTri();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"scolaireTri\": ", entityValue);

				entityValue = o.getEcoleTri();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleTri\": ", entityValue);

				entityValue = o.getEcoleNom();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleNom\": ", w.qjs(entityValue));

				entityValue = o.getEcoleNumeroTelephone();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleNumeroTelephone\": ", w.qjs(entityValue));

				entityValue = o.getEcoleAdministrateurNom();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleAdministrateurNom\": ", w.qjs(entityValue));

				entityValue = o.getEcoleAddresse();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleAddresse\": ", w.qjs(entityValue));

				entityValue = o.getEcoleNomCourt();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleNomCourt\": ", w.qjs(entityValue));

				entityValue = o.getEcoleId();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleId\": ", w.qjs(entityValue));

				entityValue = o.getPageUri();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"pageUri\": ", w.qjs(entityValue));

				w.l("}");
			}
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// DELETE //

	@Override
	public void deleteEcole(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest);
			sqlSchool(siteRequest, a -> {
				if(a.succeeded()) {
					aSearchSchool(siteRequest, false, true, null, b -> {
						if(b.succeeded()) {
							SearchList<School> listSchool = b.result();
							deleteDELETESchool(siteRequest, c -> {
								if(c.succeeded()) {
									response200DELETESchool(siteRequest, d -> {
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
																errorSchool(siteRequest, eventHandler, f);
															}
														});
													} else {
														errorSchool(siteRequest, eventHandler, e);
													}
												});
											}
										} else {
											errorSchool(siteRequest, eventHandler, d);
										}
									});
								} else {
									errorSchool(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchool(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void deleteDELETESchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();
			Long pk = siteRequest.getRequestPk();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_delete
					, new JsonArray(Arrays.asList(pk, School.class.getCanonicalName(), pk, pk, pk, pk))
					, deleteAsync
			-> {
				eventHandler.handle(Future.succeededFuture());
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void response200DELETESchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(siteRequest, buffer);
			siteRequest.setW(w);
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Recherche //

	@Override
	public void rechercheEcole(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchool(siteContext, operationRequest);
			aSearchSchool(siteRequest, false, true, null, a -> {
				if(a.succeeded()) {
					SearchList<School> listSchool = a.result();
					response200RechercheSchool(listSchool, b -> {
						if(b.succeeded()) {
							eventHandler.handle(Future.succeededFuture(b.result()));
						} else {
							errorSchool(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchool(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception e) {
			errorSchool(null, eventHandler, Future.failedFuture(e));
		}
	}

	public void response200RechercheSchool(SearchList<School> listSchool, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			Buffer buffer = Buffer.buffer();
			SiteRequestEnUS siteRequest = listSchool.getSiteRequest_();
			AllWriter w = AllWriter.create(listSchool.getSiteRequest_(), buffer);
			siteRequest.setW(w);
			QueryResponse responseSearch = listSchool.getQueryResponse();
			SolrDocumentList solrDocuments = listSchool.getSolrDocumentList();
			Long searchInMillis = Long.valueOf(responseSearch.getQTime());
			Long transmissionInMillis = responseSearch.getElapsedTime();
			Long startNum = responseSearch.getResults().getStart();
			Long foundNum = responseSearch.getResults().getNumFound();
			Integer returnedNum = responseSearch.getResults().size();
			String searchTime = String.format("%d.%03d sec", TimeUnit.MILLISECONDS.toSeconds(searchInMillis), TimeUnit.MILLISECONDS.toMillis(searchInMillis) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(searchInMillis)));
			String transmissionTime = String.format("%d.%03d sec", TimeUnit.MILLISECONDS.toSeconds(transmissionInMillis), TimeUnit.MILLISECONDS.toMillis(transmissionInMillis) - TimeUnit.SECONDS.toSeconds(TimeUnit.MILLISECONDS.toSeconds(transmissionInMillis)));
			Exception exceptionSearch = responseSearch.getException();

			w.l("{");
			w.tl(1, "\"startNum\": ", startNum);
			w.tl(1, ", \"foundNum\": ", foundNum);
			w.tl(1, ", \"returnedNum\": ", returnedNum);
			w.tl(1, ", \"searchTime\": ", w.q(searchTime));
			w.tl(1, ", \"transmissionTime\": ", w.q(transmissionTime));
			w.tl(1, ", \"list\": [");
			for(int i = 0; i < listSchool.size(); i++) {
				School o = listSchool.getList().get(i);
				Object entityValue;
				Integer entityNumber = 0;

				w.t(2);
				if(i > 0)
					w.s(", ");
				w.l("{");

				entityValue = o.getPk();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"pk\": ", entityValue);

				entityValue = o.getCreated();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"created\": ", w.qjs(entityValue));

				entityValue = o.getModified();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"modified\": ", w.qjs(entityValue));

				entityValue = o.getArchived();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"archived\": ", entityValue);

				entityValue = o.getDeleted();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"deleted\": ", entityValue);

				entityValue = o.getClassCanonicalName();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"classCanonicalName\": ", w.qjs(entityValue));

				entityValue = o.getClassSimpleName();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"classSimpleName\": ", w.qjs(entityValue));

				{
					List<String> entityValues = o.getClassCanonicalNames();
					w.t(3, entityNumber++ == 0 ? "" : ", ");
					w.s("\"classCanonicalNames\": [");
					for(int k = 0; k < entityValues.size(); k++) {
						entityValue = entityValues.get(k);
						if(k > 0)
							w.s(", ");
						w.s("\"");
						w.s(((String)entityValue));
						w.s("\"");
					}
					w.l("]");
				}

				entityValue = o.getEcoleCle();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleCle\": ", entityValue);

				{
					List<Long> entityValues = o.getEnfantCles();
					w.t(3, entityNumber++ == 0 ? "" : ", ");
					w.s("\"enfantCles\": [");
					for(int k = 0; k < entityValues.size(); k++) {
						entityValue = entityValues.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entityValue).toString());
					}
					w.l("]");
				}

				{
					List<Long> entityValues = o.getBlocCles();
					w.t(3, entityNumber++ == 0 ? "" : ", ");
					w.s("\"blocCles\": [");
					for(int k = 0; k < entityValues.size(); k++) {
						entityValue = entityValues.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entityValue).toString());
					}
					w.l("]");
				}

				{
					List<Long> entityValues = o.getGroupeAgeCles();
					w.t(3, entityNumber++ == 0 ? "" : ", ");
					w.s("\"groupeAgeCles\": [");
					for(int k = 0; k < entityValues.size(); k++) {
						entityValue = entityValues.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entityValue).toString());
					}
					w.l("]");
				}

				{
					List<Long> entityValues = o.getSessionCles();
					w.t(3, entityNumber++ == 0 ? "" : ", ");
					w.s("\"sessionCles\": [");
					for(int k = 0; k < entityValues.size(); k++) {
						entityValue = entityValues.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entityValue).toString());
					}
					w.l("]");
				}

				{
					List<Long> entityValues = o.getSaisonCles();
					w.t(3, entityNumber++ == 0 ? "" : ", ");
					w.s("\"saisonCles\": [");
					for(int k = 0; k < entityValues.size(); k++) {
						entityValue = entityValues.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entityValue).toString());
					}
					w.l("]");
				}

				{
					List<Long> entityValues = o.getAnneeCles();
					w.t(3, entityNumber++ == 0 ? "" : ", ");
					w.s("\"anneeCles\": [");
					for(int k = 0; k < entityValues.size(); k++) {
						entityValue = entityValues.get(k);
						if(k > 0)
							w.s(", ");
						w.s(((Long)entityValue).toString());
					}
					w.l("]");
				}

				entityValue = o.getScolaireTri();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"scolaireTri\": ", entityValue);

				entityValue = o.getEcoleTri();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleTri\": ", entityValue);

				entityValue = o.getEcoleNom();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleNom\": ", w.qjs(entityValue));

				entityValue = o.getEcoleNumeroTelephone();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleNumeroTelephone\": ", w.qjs(entityValue));

				entityValue = o.getEcoleAdministrateurNom();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleAdministrateurNom\": ", w.qjs(entityValue));

				entityValue = o.getEcoleAddresse();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleAddresse\": ", w.qjs(entityValue));

				entityValue = o.getEcoleNomCourt();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleNomCourt\": ", w.qjs(entityValue));

				entityValue = o.getEcoleId();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"ecoleId\": ", w.qjs(entityValue));

				entityValue = o.getPageUri();
				if(entityValue != null)
					w.tl(3, entityNumber++ == 0 ? "" : ", ", "\"pageUri\": ", w.qjs(entityValue));

				w.tl(2, "}");
			}
			w.tl(1, "]");
			if(exceptionSearch != null) {
				w.tl(1, ", \"exceptionSearch\": ", w.q(exceptionSearch.getMessage()));
			}
			w.l("}");
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(buffer)));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public String varIndexedSchool(String entityVar) {
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
			case "ecoleCle":
				return "ecoleCle_indexed_long";
			case "enfantCles":
				return "enfantCles_indexed_longs";
			case "blocCles":
				return "blocCles_indexed_longs";
			case "groupeAgeCles":
				return "groupeAgeCles_indexed_longs";
			case "sessionCles":
				return "sessionCles_indexed_longs";
			case "saisonCles":
				return "saisonCles_indexed_longs";
			case "anneeCles":
				return "anneeCles_indexed_longs";
			case "scolaireTri":
				return "scolaireTri_indexed_int";
			case "ecoleTri":
				return "ecoleTri_indexed_int";
			case "ecoleNom":
				return "ecoleNom_indexed_string";
			case "ecoleNumeroTelephone":
				return "ecoleNumeroTelephone_indexed_string";
			case "ecoleAdministrateurNom":
				return "ecoleAdministrateurNom_indexed_string";
			case "ecoleAddresse":
				return "ecoleAddresse_indexed_string";
			case "objetSuggere":
				return "objetSuggere_indexed_string";
			case "pageUri":
				return "pageUri_indexed_string";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	public String varSearchSchool(String entityVar) {
		switch(entityVar) {
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	public String varSuggereSchool(String entityVar) {
		switch(entityVar) {
			case "objetSuggere":
				return "objetSuggere_suggested";
			default:
				throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		}
	}

	// Partagé //

	public void errorSchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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

	public void sqlSchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public SiteRequestEnUS generateSiteRequestEnUSForSchool(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForSchool(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForSchool(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userSchool(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void aSearchSchool(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String classApiUriMethod, Handler<AsyncResult<SearchList<School>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<School> listSearch = new SearchList<School>();
			listSearch.setPopulate(populate);
			listSearch.setStore(store);
			listSearch.setQuery("*:*");
			listSearch.setC(School.class);
			if(entityList != null)
			listSearch.setFields(entityList);
			listSearch.addFilterQuery("classCanonicalName_indexed_string:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.school.School"));

			String pageUri = null;
			String id = operationRequest.getParams().getJsonObject("path").getString("id");
			if(id != null) {
				pageUri = classApiUriMethod + "/" + id;
				listSearch.addFilterQuery("pageUri_indexed_string:" + ClientUtils.escapeQueryChars(pageUri));
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
								varIndexed = "*".equals(entityVar) ? entityVar : varSearchSchool(entityVar);
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
								varIndexed = varIndexedSchool(entityVar);
								listSearch.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueSort = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = varIndexedSchool(entityVar);
								listSearch.addSort(varIndexed, ORDER.valueOf(valueSort));
								break;
							case "fl":
								entityVar = StringUtils.trim((String)paramObject);
								varIndexed = varIndexedSchool(entityVar);
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

	public void defineSchool(School o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					for(JsonArray definition : defineAsync.result().getResults()) {
						o.defineForClass(definition.getString(0), definition.getString(1));
					}
					eventHandler.handle(Future.succeededFuture());
				} else {
					eventHandler.handle(Future.failedFuture(defineAsync.cause()));
				}
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void attributeSchool(School o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_attribute
					, new JsonArray(Arrays.asList(pk, pk))
					, attributeAsync
			-> {
				if(attributeAsync.succeeded()) {
					if(attributeAsync.result() != null) {
						for(JsonArray definition : attributeAsync.result().getResults()) {
							o.attributeForClass(definition.getString(0), definition.getString(1));
						}
					}
					eventHandler.handle(Future.succeededFuture());
				} else {
					eventHandler.handle(Future.failedFuture(attributeAsync.cause()));
				}
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void indexSchool(School o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
