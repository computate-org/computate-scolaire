package org.computate.scolaire.enUS.enrollment;

import org.computate.scolaire.enUS.year.SchoolYearEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.year.SchoolYear;
import org.computate.scolaire.enUS.block.SchoolBlockEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.block.SchoolBlock;
import org.computate.scolaire.enUS.child.SchoolChildEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.child.SchoolChild;
import org.computate.scolaire.enUS.mom.SchoolMomEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.mom.SchoolMom;
import org.computate.scolaire.enUS.dad.SchoolDadEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.dad.SchoolDad;
import org.computate.scolaire.enUS.guardian.SchoolGuardianEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import org.computate.scolaire.enUS.payment.SchoolPaymentEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import org.computate.scolaire.enUS.user.SiteUserEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.user.SiteUser;
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
 * CanonicalName.frFR: org.computate.scolaire.frFR.inscription.InscriptionScolaireFrFRGenApiServiceImpl
 **/
public class SchoolEnrollmentEnUSGenApiServiceImpl implements SchoolEnrollmentEnUSGenApiService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolEnrollmentEnUSGenApiServiceImpl.class);

	protected static final String SERVICE_ADDRESS = "SchoolEnrollmentEnUSApiServiceImpl";

	protected SiteContextEnUS siteContext;

	public SchoolEnrollmentEnUSGenApiServiceImpl(SiteContextEnUS siteContext) {
		this.siteContext = siteContext;
	}

	// POST //

	@Override
	public void postSchoolEnrollment(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("postSchoolEnrollment started. "));
			userSchoolEnrollment(siteRequest, b -> {
				if(b.succeeded()) {
					ApiRequest apiRequest = new ApiRequest();
					apiRequest.setRows(1);
					apiRequest.setNumFound(1L);
					apiRequest.setNumPATCH(0L);
					apiRequest.initDeepApiRequest(siteRequest);
					siteRequest.setApiRequest_(apiRequest);
					siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
					postSchoolEnrollmentFuture(siteRequest, false, c -> {
						if(c.succeeded()) {
							SchoolEnrollment schoolEnrollment = c.result();
							apiRequest.setPk(schoolEnrollment.getPk());
							postSchoolEnrollmentResponse(schoolEnrollment, d -> {
									if(d.succeeded()) {
									eventHandler.handle(Future.succeededFuture(d.result()));
									LOGGER.info(String.format("postSchoolEnrollment succeeded. "));
								} else {
									LOGGER.error(String.format("postSchoolEnrollment failed. ", d.cause()));
									errorSchoolEnrollment(siteRequest, eventHandler, d);
								}
							});
						} else {
							LOGGER.error(String.format("postSchoolEnrollment failed. ", c.cause()));
							errorSchoolEnrollment(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("postSchoolEnrollment failed. ", b.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public Future<SchoolEnrollment> postSchoolEnrollmentFuture(SiteRequestEnUS siteRequest, Boolean inheritPk, Handler<AsyncResult<SchoolEnrollment>> eventHandler) {
		Promise<SchoolEnrollment> promise = Promise.promise();
		try {
			sqlConnectionSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					sqlTransactionSchoolEnrollment(siteRequest, b -> {
						if(b.succeeded()) {
							createSchoolEnrollment(siteRequest, c -> {
								if(c.succeeded()) {
									SchoolEnrollment schoolEnrollment = c.result();
									sqlPOSTSchoolEnrollment(schoolEnrollment, inheritPk, d -> {
										if(d.succeeded()) {
											defineIndexSchoolEnrollment(schoolEnrollment, e -> {
												if(e.succeeded()) {
													ApiRequest apiRequest = siteRequest.getApiRequest_();
													if(apiRequest != null) {
														apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
														schoolEnrollment.apiRequestSchoolEnrollment();
														siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
													}
													eventHandler.handle(Future.succeededFuture(schoolEnrollment));
													promise.complete(schoolEnrollment);
												} else {
													LOGGER.error(String.format("postSchoolEnrollmentFuture failed. ", e.cause()));
													eventHandler.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											LOGGER.error(String.format("postSchoolEnrollmentFuture failed. ", d.cause()));
											eventHandler.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("postSchoolEnrollmentFuture failed. ", c.cause()));
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("postSchoolEnrollmentFuture failed. ", b.cause()));
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("postSchoolEnrollmentFuture failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("postSchoolEnrollmentFuture failed. ", e));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTSchoolEnrollment(SchoolEnrollment o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.inheritPk failed", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.archived failed", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.deleted failed", b.cause())));
							});
						}));
						break;
					case "yearKey":
						{
							Long l = Long.parseLong(jsonObject.getString(entityVar));
							if(l != null) {
								SearchList<SchoolYear> searchList = new SearchList<SchoolYear>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolYear.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "yearKey")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.yearKey failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolYear");
									}
								}
							}
						}
						break;
					case "blockKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolBlock.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "blockKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.blockKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolBlock");
									}
								}
							}
						}
						break;
					case "childKey":
						{
							Long l = Long.parseLong(jsonObject.getString(entityVar));
							if(l != null) {
								SearchList<SchoolChild> searchList = new SearchList<SchoolChild>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolChild.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "childKey", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childKey failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolChild");
									}
								}
							}
						}
						break;
					case "momKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolMom.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "momKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.momKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolMom");
									}
								}
							}
						}
						break;
					case "dadKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								SearchList<SchoolDad> searchList = new SearchList<SchoolDad>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolDad.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "dadKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.dadKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolDad");
									}
								}
							}
						}
						break;
					case "guardianKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolGuardian.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "guardianKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.guardianKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolGuardian");
									}
								}
							}
						}
						break;
					case "paymentKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolPayment.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKey", pk, "paymentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.paymentKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolPayment");
									}
								}
							}
						}
						break;
					case "userKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							if(l != null) {
								SearchList<SiteUser> searchList = new SearchList<SiteUser>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SiteUser.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "userKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.userKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SiteUser");
									}
								}
							}
						}
						break;
					case "childCompleteName":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childCompleteName", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childCompleteName failed", b.cause())));
							});
						}));
						break;
					case "childCompleteNamePreferred":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childCompleteNamePreferred", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childCompleteNamePreferred failed", b.cause())));
							});
						}));
						break;
					case "childBirthDate":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childBirthDate", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childBirthDate failed", b.cause())));
							});
						}));
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
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.schoolAddress failed", b.cause())));
							});
						}));
						break;
					case "enrollmentApproved":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentApproved", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentApproved failed", b.cause())));
							});
						}));
						break;
					case "enrollmentImmunizations":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentImmunizations", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentImmunizations failed", b.cause())));
							});
						}));
						break;
					case "familyMarried":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "familyMarried", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyMarried failed", b.cause())));
							});
						}));
						break;
					case "familySeparated":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "familySeparated", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familySeparated failed", b.cause())));
							});
						}));
						break;
					case "familyDivorced":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "familyDivorced", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyDivorced failed", b.cause())));
							});
						}));
						break;
					case "familyAddress":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "familyAddress", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyAddress failed", b.cause())));
							});
						}));
						break;
					case "familyHowDoYouKnowTheSchool":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "familyHowDoYouKnowTheSchool", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyHowDoYouKnowTheSchool failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSpecialConsiderations":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSpecialConsiderations", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSpecialConsiderations failed", b.cause())));
							});
						}));
						break;
					case "childMedicalConditions":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childMedicalConditions", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childMedicalConditions failed", b.cause())));
							});
						}));
						break;
					case "childPreviousSchoolsAttended":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childPreviousSchoolsAttended", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childPreviousSchoolsAttended failed", b.cause())));
							});
						}));
						break;
					case "childDescription":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childDescription", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childDescription failed", b.cause())));
							});
						}));
						break;
					case "childObjectives":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childObjectives", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childObjectives failed", b.cause())));
							});
						}));
						break;
					case "childPottyTrained":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childPottyTrained", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childPottyTrained failed", b.cause())));
							});
						}));
						break;
					case "enrollmentGroupName":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentGroupName", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentGroupName failed", b.cause())));
							});
						}));
						break;
					case "enrollmentPaymentEachMonth":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentPaymentEachMonth", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentPaymentEachMonth failed", b.cause())));
							});
						}));
						break;
					case "enrollmentPaymentComplete":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentPaymentComplete", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentPaymentComplete failed", b.cause())));
							});
						}));
						break;
					case "customerProfileId":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "customerProfileId", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.customerProfileId failed", b.cause())));
							});
						}));
						break;
					case "enrollmentChargeDate":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentChargeDate", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentChargeDate failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature1":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature1", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature1 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature2":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature2", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature2 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature3":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature3", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature3 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature4":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature4", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature4 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature5":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature5", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature5 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature6":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature6", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature6 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature7":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature7", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature7 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature8":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature8", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature8 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature9":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature9", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature9 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature10":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature10", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature10 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate1":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate1", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate1 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate2":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate2", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate2 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate3":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate3", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate3 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate4":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate4", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate4 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate5":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate5", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate5 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate6":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate6", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate6 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate7":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate7", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate7 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate8":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate8", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate8 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate9":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate9", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate9 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate10":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate10", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate10 failed", b.cause())));
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
					LOGGER.error(String.format("sqlPOSTSchoolEnrollment failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPOSTSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void postSchoolEnrollmentResponse(SchoolEnrollment schoolEnrollment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = schoolEnrollment.getSiteRequest_();
		try {
			response200POSTSchoolEnrollment(schoolEnrollment, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("postSchoolEnrollmentResponse failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchoolEnrollmentResponse failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200POSTSchoolEnrollment(SchoolEnrollment o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			JsonObject json = JsonObject.mapFrom(o);
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200POSTSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTImport //

	@Override
	public void putimportSchoolEnrollment(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putimportSchoolEnrollment started. "));
			userSchoolEnrollment(siteRequest, b -> {
				if(b.succeeded()) {
					putimportSchoolEnrollmentResponse(siteRequest, c -> {
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
										siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
										varsSchoolEnrollment(siteRequest, d -> {
											if(d.succeeded()) {
												listPUTImportSchoolEnrollment(apiRequest, siteRequest, e -> {
													if(e.succeeded()) {
														putimportSchoolEnrollmentResponse(siteRequest, f -> {
															if(e.succeeded()) {
																LOGGER.info(String.format("putimportSchoolEnrollment succeeded. "));
																blockingCodeHandler.handle(Future.succeededFuture(e.result()));
															} else {
																LOGGER.error(String.format("putimportSchoolEnrollment failed. ", f.cause()));
																errorSchoolEnrollment(siteRequest, null, f);
															}
														});
													} else {
														LOGGER.error(String.format("putimportSchoolEnrollment failed. ", e.cause()));
														errorSchoolEnrollment(siteRequest, null, e);
													}
												});
											} else {
												LOGGER.error(String.format("putimportSchoolEnrollment failed. ", d.cause()));
												errorSchoolEnrollment(siteRequest, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putimportSchoolEnrollment failed. ", ex));
										errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("putimportSchoolEnrollment failed. ", c.cause()));
							errorSchoolEnrollment(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("putimportSchoolEnrollment failed. ", b.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTImportSchoolEnrollment(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				json.put("created", json.getValue("created"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setApiRequest_(apiRequest);
				siteRequest2.setRequestVars(siteRequest.getRequestVars());

				SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolEnrollment.class);
				searchList.addFilterQuery("inheritPk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					SchoolEnrollment o = searchList.getList().stream().findFirst().orElse(null);
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
							patchSchoolEnrollmentFuture(o, true, a -> {
								if(a.succeeded()) {
								} else {
									LOGGER.error(String.format("listPUTImportSchoolEnrollment failed. ", a.cause()));
									errorSchoolEnrollment(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolEnrollmentFuture(siteRequest2, true, a -> {
							if(a.succeeded()) {
							} else {
								LOGGER.error(String.format("listPUTImportSchoolEnrollment failed. ", a.cause()));
								errorSchoolEnrollment(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
					response200PUTImportSchoolEnrollment(siteRequest, eventHandler);
				} else {
					LOGGER.error(String.format("listPUTImportSchoolEnrollment failed. ", a.cause()));
					errorSchoolEnrollment(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("listPUTImportSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putimportSchoolEnrollmentResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTImportSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putimportSchoolEnrollmentResponse failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolEnrollmentResponse failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTImportSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PUTImportSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTMerge //

	@Override
	public void putmergeSchoolEnrollment(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putmergeSchoolEnrollment started. "));
			userSchoolEnrollment(siteRequest, b -> {
				if(b.succeeded()) {
					putmergeSchoolEnrollmentResponse(siteRequest, c -> {
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
										siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
										varsSchoolEnrollment(siteRequest, d -> {
											if(d.succeeded()) {
												listPUTMergeSchoolEnrollment(apiRequest, siteRequest, e -> {
													if(e.succeeded()) {
														putmergeSchoolEnrollmentResponse(siteRequest, f -> {
															if(e.succeeded()) {
																LOGGER.info(String.format("putmergeSchoolEnrollment succeeded. "));
																blockingCodeHandler.handle(Future.succeededFuture(e.result()));
															} else {
																LOGGER.error(String.format("putmergeSchoolEnrollment failed. ", f.cause()));
																errorSchoolEnrollment(siteRequest, null, f);
															}
														});
													} else {
														LOGGER.error(String.format("putmergeSchoolEnrollment failed. ", e.cause()));
														errorSchoolEnrollment(siteRequest, null, e);
													}
												});
											} else {
												LOGGER.error(String.format("putmergeSchoolEnrollment failed. ", d.cause()));
												errorSchoolEnrollment(siteRequest, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putmergeSchoolEnrollment failed. ", ex));
										errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("putmergeSchoolEnrollment failed. ", c.cause()));
							errorSchoolEnrollment(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("putmergeSchoolEnrollment failed. ", b.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTMergeSchoolEnrollment(ApiRequest apiRequest, SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
		try {
			jsonArray.forEach(obj -> {
				JsonObject json = (JsonObject)obj;

				json.put("inheritPk", json.getValue("pk"));

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setApiRequest_(apiRequest);
				siteRequest2.setRequestVars(siteRequest.getRequestVars());

				SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolEnrollment.class);
				searchList.addFilterQuery("pk_indexed_long:" + json.getString("pk"));
				searchList.initDeepForClass(siteRequest2);

				if(searchList.size() == 1) {
					SchoolEnrollment o = searchList.getList().stream().findFirst().orElse(null);
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
							patchSchoolEnrollmentFuture(o, false, a -> {
								if(a.succeeded()) {
								} else {
									LOGGER.error(String.format("listPUTMergeSchoolEnrollment failed. ", a.cause()));
									errorSchoolEnrollment(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolEnrollmentFuture(siteRequest2, false, a -> {
							if(a.succeeded()) {
							} else {
								LOGGER.error(String.format("listPUTMergeSchoolEnrollment failed. ", a.cause()));
								errorSchoolEnrollment(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
					response200PUTMergeSchoolEnrollment(siteRequest, eventHandler);
				} else {
					LOGGER.error(String.format("listPUTMergeSchoolEnrollment failed. ", a.cause()));
					errorSchoolEnrollment(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("listPUTMergeSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putmergeSchoolEnrollmentResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTMergeSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putmergeSchoolEnrollmentResponse failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolEnrollmentResponse failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTMergeSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PUTMergeSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTCopy //

	@Override
	public void putcopySchoolEnrollment(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putcopySchoolEnrollment started. "));
			userSchoolEnrollment(siteRequest, b -> {
				if(b.succeeded()) {
					putcopySchoolEnrollmentResponse(siteRequest, c -> {
						if(c.succeeded()) {
							eventHandler.handle(Future.succeededFuture(c.result()));
							WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
							workerExecutor.executeBlocking(
								blockingCodeHandler -> {
									try {
										aSearchSchoolEnrollment(siteRequest, false, true, "/api/enrollment/copy", "PUTCopy", d -> {
											if(d.succeeded()) {
												SearchList<SchoolEnrollment> listSchoolEnrollment = d.result();
												ApiRequest apiRequest = new ApiRequest();
												apiRequest.setRows(listSchoolEnrollment.getRows());
												apiRequest.setNumFound(listSchoolEnrollment.getQueryResponse().getResults().getNumFound());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest);
												siteRequest.setApiRequest_(apiRequest);
												siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
												try {
													listPUTCopySchoolEnrollment(apiRequest, listSchoolEnrollment, e -> {
														if(e.succeeded()) {
															putcopySchoolEnrollmentResponse(siteRequest, f -> {
																if(f.succeeded()) {
																	LOGGER.info(String.format("putcopySchoolEnrollment succeeded. "));
																	blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																} else {
																	LOGGER.error(String.format("putcopySchoolEnrollment failed. ", f.cause()));
																	errorSchoolEnrollment(siteRequest, null, f);
																}
															});
														} else {
															LOGGER.error(String.format("putcopySchoolEnrollment failed. ", e.cause()));
															errorSchoolEnrollment(siteRequest, null, e);
														}
													});
												} catch(Exception ex) {
													LOGGER.error(String.format("putcopySchoolEnrollment failed. ", ex));
													errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
												}
											} else {
												LOGGER.error(String.format("putcopySchoolEnrollment failed. ", d.cause()));
												errorSchoolEnrollment(siteRequest, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("putcopySchoolEnrollment failed. ", ex));
										errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("putcopySchoolEnrollment failed. ", c.cause()));
							errorSchoolEnrollment(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("putcopySchoolEnrollment failed. ", b.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPUTCopySchoolEnrollment(ApiRequest apiRequest, SearchList<SchoolEnrollment> listSchoolEnrollment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolEnrollment.getSiteRequest_();
		listSchoolEnrollment.getList().forEach(o -> {
			SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), siteRequest.getJsonObject());
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			o.setSiteRequest_(siteRequest2);
			futures.add(
				putcopySchoolEnrollmentFuture(siteRequest, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
					} else {
						LOGGER.error(String.format("listPUTCopySchoolEnrollment failed. ", a.cause()));
						errorSchoolEnrollment(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchoolEnrollment.size());
				if(listSchoolEnrollment.next()) {
					listPUTCopySchoolEnrollment(apiRequest, listSchoolEnrollment, eventHandler);
				} else {
					response200PUTCopySchoolEnrollment(siteRequest, eventHandler);
				}
			} else {
				LOGGER.error(String.format("listPUTCopySchoolEnrollment failed. ", a.cause()));
				errorSchoolEnrollment(listSchoolEnrollment.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolEnrollment> putcopySchoolEnrollmentFuture(SiteRequestEnUS siteRequest, JsonObject jsonObject, Handler<AsyncResult<SchoolEnrollment>> eventHandler) {
		Promise<SchoolEnrollment> promise = Promise.promise();
		try {

			jsonObject.put("saves", Optional.ofNullable(jsonObject.getJsonArray("saves")).orElse(new JsonArray()));
			JsonObject jsonPatch = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonObject("patch")).orElse(new JsonObject());
			jsonPatch.stream().forEach(o -> {
				jsonObject.put(o.getKey(), o.getValue());
				jsonObject.getJsonArray("saves").add(o.getKey());
			});

			sqlConnectionSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					sqlTransactionSchoolEnrollment(siteRequest, b -> {
						if(b.succeeded()) {
							createSchoolEnrollment(siteRequest, c -> {
								if(c.succeeded()) {
									SchoolEnrollment schoolEnrollment = c.result();
									sqlPUTCopySchoolEnrollment(schoolEnrollment, jsonObject, d -> {
										if(d.succeeded()) {
											defineIndexSchoolEnrollment(schoolEnrollment, e -> {
												if(e.succeeded()) {
													ApiRequest apiRequest = siteRequest.getApiRequest_();
													if(apiRequest != null) {
														apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
														if(apiRequest.getNumFound() == 1L) {
															schoolEnrollment.apiRequestSchoolEnrollment();
														}
														siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
													}
													eventHandler.handle(Future.succeededFuture(schoolEnrollment));
													promise.complete(schoolEnrollment);
												} else {
													LOGGER.error(String.format("putcopySchoolEnrollmentFuture failed. ", e.cause()));
													eventHandler.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											LOGGER.error(String.format("putcopySchoolEnrollmentFuture failed. ", d.cause()));
											eventHandler.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("putcopySchoolEnrollmentFuture failed. ", c.cause()));
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("putcopySchoolEnrollmentFuture failed. ", b.cause()));
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("putcopySchoolEnrollmentFuture failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("putcopySchoolEnrollmentFuture failed. ", e));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopySchoolEnrollment(SchoolEnrollment o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.inheritPk failed", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.archived failed", b.cause())));
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
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.deleted failed", b.cause())));
							});
						}));
						break;
					case "yearKey":
							{
						Long l = Long.parseLong(jsonObject.getString(entityVar));
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_addA
									, Tuple.of(l, "enrollmentKeys", pk, "yearKey")
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.yearKey failed", b.cause())));
							});
						}));
						}
						break;
					case "blockKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_addA
										, Tuple.of(pk, "blockKeys", l, "enrollmentKeys")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.blockKeys failed", b.cause())));
								});
							}));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("SchoolBlock");
							}
						}
						break;
					case "childKey":
							{
						Long l = Long.parseLong(jsonObject.getString(entityVar));
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_addA
									, Tuple.of(pk, "childKey", l, "enrollmentKeys")
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childKey failed", b.cause())));
							});
						}));
						}
						break;
					case "momKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_addA
										, Tuple.of(l, "enrollmentKeys", pk, "momKeys")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.momKeys failed", b.cause())));
								});
							}));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("SchoolMom");
							}
						}
						break;
					case "dadKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_addA
										, Tuple.of(pk, "dadKeys", l, "enrollmentKeys")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.dadKeys failed", b.cause())));
								});
							}));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("SchoolDad");
							}
						}
						break;
					case "guardianKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_addA
										, Tuple.of(l, "enrollmentKeys", pk, "guardianKeys")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.guardianKeys failed", b.cause())));
								});
							}));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("SchoolGuardian");
							}
						}
						break;
					case "paymentKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_addA
										, Tuple.of(l, "enrollmentKey", pk, "paymentKeys")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.paymentKeys failed", b.cause())));
								});
							}));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("SchoolPayment");
							}
						}
						break;
					case "userKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_addA
										, Tuple.of(l, "enrollmentKeys", pk, "userKeys")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.userKeys failed", b.cause())));
								});
							}));
							if(!pks.contains(l)) {
								pks.add(l);
								classes.add("SiteUser");
							}
						}
						break;
					case "childCompleteName":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childCompleteName", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childCompleteName failed", b.cause())));
							});
						}));
						break;
					case "childCompleteNamePreferred":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childCompleteNamePreferred", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childCompleteNamePreferred failed", b.cause())));
							});
						}));
						break;
					case "childBirthDate":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childBirthDate", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childBirthDate failed", b.cause())));
							});
						}));
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
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.schoolAddress failed", b.cause())));
							});
						}));
						break;
					case "enrollmentApproved":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentApproved", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentApproved failed", b.cause())));
							});
						}));
						break;
					case "enrollmentImmunizations":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentImmunizations", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentImmunizations failed", b.cause())));
							});
						}));
						break;
					case "familyMarried":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "familyMarried", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyMarried failed", b.cause())));
							});
						}));
						break;
					case "familySeparated":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "familySeparated", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familySeparated failed", b.cause())));
							});
						}));
						break;
					case "familyDivorced":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "familyDivorced", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyDivorced failed", b.cause())));
							});
						}));
						break;
					case "familyAddress":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "familyAddress", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyAddress failed", b.cause())));
							});
						}));
						break;
					case "familyHowDoYouKnowTheSchool":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "familyHowDoYouKnowTheSchool", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyHowDoYouKnowTheSchool failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSpecialConsiderations":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSpecialConsiderations", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSpecialConsiderations failed", b.cause())));
							});
						}));
						break;
					case "childMedicalConditions":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childMedicalConditions", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childMedicalConditions failed", b.cause())));
							});
						}));
						break;
					case "childPreviousSchoolsAttended":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childPreviousSchoolsAttended", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childPreviousSchoolsAttended failed", b.cause())));
							});
						}));
						break;
					case "childDescription":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childDescription", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childDescription failed", b.cause())));
							});
						}));
						break;
					case "childObjectives":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childObjectives", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childObjectives failed", b.cause())));
							});
						}));
						break;
					case "childPottyTrained":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "childPottyTrained", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childPottyTrained failed", b.cause())));
							});
						}));
						break;
					case "enrollmentGroupName":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentGroupName", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentGroupName failed", b.cause())));
							});
						}));
						break;
					case "enrollmentPaymentEachMonth":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentPaymentEachMonth", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentPaymentEachMonth failed", b.cause())));
							});
						}));
						break;
					case "enrollmentPaymentComplete":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentPaymentComplete", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentPaymentComplete failed", b.cause())));
							});
						}));
						break;
					case "customerProfileId":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "customerProfileId", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.customerProfileId failed", b.cause())));
							});
						}));
						break;
					case "enrollmentChargeDate":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentChargeDate", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentChargeDate failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature1":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature1", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature1 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature2":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature2", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature2 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature3":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature3", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature3 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature4":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature4", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature4 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature5":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature5", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature5 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature6":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature6", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature6 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature7":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature7", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature7 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature8":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature8", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature8 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature9":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature9", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature9 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentSignature10":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentSignature10", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature10 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate1":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate1", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate1 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate2":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate2", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate2 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate3":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate3", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate3 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate4":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate4", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate4 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate5":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate5", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate5 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate6":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate6", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate6 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate7":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate7", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate7 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate8":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate8", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate8 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate9":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate9", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate9 failed", b.cause())));
							});
						}));
						break;
					case "enrollmentDate10":
						futures.add(Future.future(a -> {
							tx.preparedQuery(SiteContextEnUS.SQL_setD
									, Tuple.of(pk, "enrollmentDate10", Optional.ofNullable(jsonObject.getValue(entityVar)).map(s -> s.toString()).orElse(null))
									, b
							-> {
								if(b.succeeded())
									a.handle(Future.succeededFuture());
								else
									a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate10 failed", b.cause())));
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
					LOGGER.error(String.format("sqlPUTCopySchoolEnrollment failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPUTCopySchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void putcopySchoolEnrollmentResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTCopySchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("putcopySchoolEnrollmentResponse failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchoolEnrollmentResponse failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTCopySchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PUTCopySchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSchoolEnrollment(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("patchSchoolEnrollment started. "));
			userSchoolEnrollment(siteRequest, b -> {
				if(b.succeeded()) {
					patchSchoolEnrollmentResponse(siteRequest, c -> {
						if(c.succeeded()) {
							eventHandler.handle(Future.succeededFuture(c.result()));
							WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
							workerExecutor.executeBlocking(
								blockingCodeHandler -> {
									try {
										aSearchSchoolEnrollment(siteRequest, false, true, "/api/enrollment", "PATCH", d -> {
											if(d.succeeded()) {
												SearchList<SchoolEnrollment> listSchoolEnrollment = d.result();
												ApiRequest apiRequest = new ApiRequest();
												apiRequest.setRows(listSchoolEnrollment.getRows());
												apiRequest.setNumFound(listSchoolEnrollment.getQueryResponse().getResults().getNumFound());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest);
												siteRequest.setApiRequest_(apiRequest);
												siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
												SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolEnrollment.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
												Date date = null;
												if(facets != null)
													date = (Date)facets.get("max_modified");
												String dt;
												if(date == null)
													dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
												else
													dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
												listSchoolEnrollment.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));

												try {
													listPATCHSchoolEnrollment(apiRequest, listSchoolEnrollment, dt, e -> {
														if(e.succeeded()) {
															patchSchoolEnrollmentResponse(siteRequest, f -> {
																if(f.succeeded()) {
																	LOGGER.info(String.format("patchSchoolEnrollment succeeded. "));
																	blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																} else {
																	LOGGER.error(String.format("patchSchoolEnrollment failed. ", f.cause()));
																	errorSchoolEnrollment(siteRequest, null, f);
																}
															});
														} else {
															LOGGER.error(String.format("patchSchoolEnrollment failed. ", e.cause()));
															errorSchoolEnrollment(siteRequest, null, e);
														}
													});
												} catch(Exception ex) {
													LOGGER.error(String.format("patchSchoolEnrollment failed. ", ex));
													errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
												}
											} else {
												LOGGER.error(String.format("patchSchoolEnrollment failed. ", d.cause()));
												errorSchoolEnrollment(siteRequest, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("patchSchoolEnrollment failed. ", ex));
										errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("patchSchoolEnrollment failed. ", c.cause()));
							errorSchoolEnrollment(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("patchSchoolEnrollment failed. ", b.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPATCHSchoolEnrollment(ApiRequest apiRequest, SearchList<SchoolEnrollment> listSchoolEnrollment, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolEnrollment.getSiteRequest_();
		listSchoolEnrollment.getList().forEach(o -> {
			SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), siteRequest.getJsonObject());
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			o.setSiteRequest_(siteRequest2);
			futures.add(
				patchSchoolEnrollmentFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						errorSchoolEnrollment(siteRequest2, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listSchoolEnrollment.next(dt)) {
					listPATCHSchoolEnrollment(apiRequest, listSchoolEnrollment, dt, eventHandler);
				} else {
					response200PATCHSchoolEnrollment(siteRequest, eventHandler);
				}
			} else {
				LOGGER.error(String.format("listPATCHSchoolEnrollment failed. ", a.cause()));
				errorSchoolEnrollment(listSchoolEnrollment.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolEnrollment> patchSchoolEnrollmentFuture(SchoolEnrollment o, Boolean inheritPk, Handler<AsyncResult<SchoolEnrollment>> eventHandler) {
		Promise<SchoolEnrollment> promise = Promise.promise();
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			if(apiRequest != null && apiRequest.getNumFound() == 1L) {
				apiRequest.setOriginal(o);
				apiRequest.setPk(o.getPk());
			}
			sqlConnectionSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					sqlTransactionSchoolEnrollment(siteRequest, b -> {
						if(b.succeeded()) {
							sqlPATCHSchoolEnrollment(o, inheritPk, c -> {
								if(c.succeeded()) {
									SchoolEnrollment schoolEnrollment = c.result();
									defineIndexSchoolEnrollment(schoolEnrollment, d -> {
										if(d.succeeded()) {
											if(apiRequest != null) {
												apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
												if(apiRequest.getNumFound() == 1L) {
													schoolEnrollment.apiRequestSchoolEnrollment();
												}
												siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
											}
											eventHandler.handle(Future.succeededFuture(schoolEnrollment));
											promise.complete(schoolEnrollment);
										} else {
											LOGGER.error(String.format("patchSchoolEnrollmentFuture failed. ", d.cause()));
											eventHandler.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("patchSchoolEnrollmentFuture failed. ", c.cause()));
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("patchSchoolEnrollmentFuture failed. ", b.cause()));
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("patchSchoolEnrollmentFuture failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("patchSchoolEnrollmentFuture failed. ", e));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHSchoolEnrollment(SchoolEnrollment o, Boolean inheritPk, Handler<AsyncResult<SchoolEnrollment>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Transaction tx = siteRequest.getTx();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			Set<String> methodNames = jsonObject.fieldNames();
			SchoolEnrollment o2 = new SchoolEnrollment();
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.inheritPk failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.inheritPk failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.archived failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.archived failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.deleted failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.deleted failed", b.cause())));
								});
							}));
						}
						break;
					case "setYearKey":
						{
							Long l = o2.getYearKey();
							if(l != null && !l.equals(o.getYearKey())) {
								SearchList<SchoolYear> searchList = new SearchList<SchoolYear>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolYear.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									o2.setYearKey(jsonObject.getString(methodName));
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "yearKey")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.yearKey failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolYear");
									}
								}
							}
						}
						break;
					case "removeYearKey":
						{
							Long l = o2.getYearKey();
							if(l != null) {
								SearchList<SchoolYear> searchList = new SearchList<SchoolYear>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolYear.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && l2.equals(o.getYearKey())) {
									o2.setYearKey(jsonObject.getString(methodName));
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l2, "enrollmentKeys", pk, "yearKey")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.yearKey failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolYear");
									}
								}
							}
						}
						break;
					case "addBlockKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolBlock.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getBlockKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "blockKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.blockKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolBlock");
									}
								}
							}
						}
						break;
					case "addAllBlockKeys":
						JsonArray addAllBlockKeysValues = jsonObject.getJsonArray(methodName);
						if(addAllBlockKeysValues != null) {
							for(Integer i = 0; i <  addAllBlockKeysValues.size(); i++) {
								Long l = Long.parseLong(addAllBlockKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolBlock.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getBlockKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "blockKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.blockKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolBlock");
										}
									}
								}
							}
						}
						break;
					case "setBlockKeys":
						JsonArray setBlockKeysValues = jsonObject.getJsonArray(methodName);
						if(setBlockKeysValues != null) {
							for(Integer i = 0; i <  setBlockKeysValues.size(); i++) {
								Long l = Long.parseLong(setBlockKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolBlock.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getBlockKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "blockKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.blockKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolBlock");
										}
									}
								}
							}
						}
						if(o.getBlockKeys() != null) {
							for(Long l :  o.getBlockKeys()) {
								if(l != null && (setBlockKeysValues == null || !setBlockKeysValues.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(pk, "blockKeys", l, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.blockKeys failed", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removeBlockKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolBlock.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getBlockKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(pk, "blockKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.blockKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolBlock");
									}
								}
							}
						}
						break;
					case "setChildKey":
						{
							Long l = o2.getChildKey();
							if(l != null) {
								SearchList<SchoolChild> searchList = new SearchList<SchoolChild>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolChild.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !l2.equals(o.getChildKey())) {
									o2.setChildKey(jsonObject.getString(methodName));
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "childKey", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childKey failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolChild");
									}
								}
							}
						}
						break;
					case "removeChildKey":
						{
							Long l = o2.getChildKey();
							if(l != null) {
								SearchList<SchoolChild> searchList = new SearchList<SchoolChild>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolChild.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && l2.equals(o.getChildKey())) {
									o2.setChildKey(jsonObject.getString(methodName));
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(pk, "childKey", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childKey failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolChild");
									}
								}
							}
						}
						break;
					case "addMomKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolMom.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getMomKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "momKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.momKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolMom");
									}
								}
							}
						}
						break;
					case "addAllMomKeys":
						JsonArray addAllMomKeysValues = jsonObject.getJsonArray(methodName);
						if(addAllMomKeysValues != null) {
							for(Integer i = 0; i <  addAllMomKeysValues.size(); i++) {
								Long l = Long.parseLong(addAllMomKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolMom.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getMomKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "momKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.momKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolMom");
										}
									}
								}
							}
						}
						break;
					case "setMomKeys":
						JsonArray setMomKeysValues = jsonObject.getJsonArray(methodName);
						if(setMomKeysValues != null) {
							for(Integer i = 0; i <  setMomKeysValues.size(); i++) {
								Long l = Long.parseLong(setMomKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolMom.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getMomKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "momKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.momKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolMom");
										}
									}
								}
							}
						}
						if(o.getMomKeys() != null) {
							for(Long l :  o.getMomKeys()) {
								if(l != null && (setMomKeysValues == null || !setMomKeysValues.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l, "enrollmentKeys", pk, "momKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.momKeys failed", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removeMomKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolMom.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getMomKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l2, "enrollmentKeys", pk, "momKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.momKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolMom");
									}
								}
							}
						}
						break;
					case "addDadKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolDad> searchList = new SearchList<SchoolDad>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolDad.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getDadKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "dadKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.dadKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolDad");
									}
								}
							}
						}
						break;
					case "addAllDadKeys":
						JsonArray addAllDadKeysValues = jsonObject.getJsonArray(methodName);
						if(addAllDadKeysValues != null) {
							for(Integer i = 0; i <  addAllDadKeysValues.size(); i++) {
								Long l = Long.parseLong(addAllDadKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolDad> searchList = new SearchList<SchoolDad>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolDad.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getDadKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "dadKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.dadKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolDad");
										}
									}
								}
							}
						}
						break;
					case "setDadKeys":
						JsonArray setDadKeysValues = jsonObject.getJsonArray(methodName);
						if(setDadKeysValues != null) {
							for(Integer i = 0; i <  setDadKeysValues.size(); i++) {
								Long l = Long.parseLong(setDadKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolDad> searchList = new SearchList<SchoolDad>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolDad.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getDadKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "dadKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.dadKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolDad");
										}
									}
								}
							}
						}
						if(o.getDadKeys() != null) {
							for(Long l :  o.getDadKeys()) {
								if(l != null && (setDadKeysValues == null || !setDadKeysValues.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(pk, "dadKeys", l, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.dadKeys failed", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removeDadKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolDad> searchList = new SearchList<SchoolDad>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolDad.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getDadKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(pk, "dadKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.dadKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolDad");
									}
								}
							}
						}
						break;
					case "addGuardianKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolGuardian.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getGuardianKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "guardianKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.guardianKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolGuardian");
									}
								}
							}
						}
						break;
					case "addAllGuardianKeys":
						JsonArray addAllGuardianKeysValues = jsonObject.getJsonArray(methodName);
						if(addAllGuardianKeysValues != null) {
							for(Integer i = 0; i <  addAllGuardianKeysValues.size(); i++) {
								Long l = Long.parseLong(addAllGuardianKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolGuardian.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getGuardianKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "guardianKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.guardianKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolGuardian");
										}
									}
								}
							}
						}
						break;
					case "setGuardianKeys":
						JsonArray setGuardianKeysValues = jsonObject.getJsonArray(methodName);
						if(setGuardianKeysValues != null) {
							for(Integer i = 0; i <  setGuardianKeysValues.size(); i++) {
								Long l = Long.parseLong(setGuardianKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolGuardian.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getGuardianKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "guardianKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.guardianKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolGuardian");
										}
									}
								}
							}
						}
						if(o.getGuardianKeys() != null) {
							for(Long l :  o.getGuardianKeys()) {
								if(l != null && (setGuardianKeysValues == null || !setGuardianKeysValues.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l, "enrollmentKeys", pk, "guardianKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.guardianKeys failed", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removeGuardianKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolGuardian.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getGuardianKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l2, "enrollmentKeys", pk, "guardianKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.guardianKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolGuardian");
									}
								}
							}
						}
						break;
					case "addPaymentKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolPayment.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getPaymentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKey", pk, "paymentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.paymentKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolPayment");
									}
								}
							}
						}
						break;
					case "addAllPaymentKeys":
						JsonArray addAllPaymentKeysValues = jsonObject.getJsonArray(methodName);
						if(addAllPaymentKeysValues != null) {
							for(Integer i = 0; i <  addAllPaymentKeysValues.size(); i++) {
								Long l = Long.parseLong(addAllPaymentKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolPayment.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getPaymentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKey", pk, "paymentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.paymentKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolPayment");
										}
									}
								}
							}
						}
						break;
					case "setPaymentKeys":
						JsonArray setPaymentKeysValues = jsonObject.getJsonArray(methodName);
						if(setPaymentKeysValues != null) {
							for(Integer i = 0; i <  setPaymentKeysValues.size(); i++) {
								Long l = Long.parseLong(setPaymentKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolPayment.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getPaymentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKey", pk, "paymentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.paymentKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolPayment");
										}
									}
								}
							}
						}
						if(o.getPaymentKeys() != null) {
							for(Long l :  o.getPaymentKeys()) {
								if(l != null && (setPaymentKeysValues == null || !setPaymentKeysValues.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l, "enrollmentKey", pk, "paymentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.paymentKeys failed", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removePaymentKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolPayment.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getPaymentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l2, "enrollmentKey", pk, "paymentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.paymentKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolPayment");
									}
								}
							}
						}
						break;
					case "addUserKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SiteUser> searchList = new SearchList<SiteUser>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SiteUser.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getUserKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "userKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.userKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SiteUser");
									}
								}
							}
						}
						break;
					case "addAllUserKeys":
						JsonArray addAllUserKeysValues = jsonObject.getJsonArray(methodName);
						if(addAllUserKeysValues != null) {
							for(Integer i = 0; i <  addAllUserKeysValues.size(); i++) {
								Long l = Long.parseLong(addAllUserKeysValues.getString(i));
								if(l != null) {
									SearchList<SiteUser> searchList = new SearchList<SiteUser>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SiteUser.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getUserKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "userKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.userKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SiteUser");
										}
									}
								}
							}
						}
						break;
					case "setUserKeys":
						JsonArray setUserKeysValues = jsonObject.getJsonArray(methodName);
						if(setUserKeysValues != null) {
							for(Integer i = 0; i <  setUserKeysValues.size(); i++) {
								Long l = Long.parseLong(setUserKeysValues.getString(i));
								if(l != null) {
									SearchList<SiteUser> searchList = new SearchList<SiteUser>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SiteUser.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getUserKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "userKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.userKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SiteUser");
										}
									}
								}
							}
						}
						if(o.getUserKeys() != null) {
							for(Long l :  o.getUserKeys()) {
								if(l != null && (setUserKeysValues == null || !setUserKeysValues.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l, "enrollmentKeys", pk, "userKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.userKeys failed", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removeUserKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SiteUser> searchList = new SearchList<SiteUser>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SiteUser.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getUserKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l2, "enrollmentKeys", pk, "userKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.userKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SiteUser");
									}
								}
							}
						}
						break;
					case "setChildCompleteName":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childCompleteName")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childCompleteName failed", b.cause())));
								});
							}));
						} else {
							o2.setChildCompleteName(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childCompleteName", o2.jsonChildCompleteName())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childCompleteName failed", b.cause())));
								});
							}));
						}
						break;
					case "setChildCompleteNamePreferred":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childCompleteNamePreferred")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childCompleteNamePreferred failed", b.cause())));
								});
							}));
						} else {
							o2.setChildCompleteNamePreferred(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childCompleteNamePreferred", o2.jsonChildCompleteNamePreferred())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childCompleteNamePreferred failed", b.cause())));
								});
							}));
						}
						break;
					case "setChildBirthDate":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childBirthDate")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childBirthDate failed", b.cause())));
								});
							}));
						} else {
							o2.setChildBirthDate(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childBirthDate", o2.jsonChildBirthDate())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childBirthDate failed", b.cause())));
								});
							}));
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.schoolAddress failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.schoolAddress failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentApproved":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentApproved")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentApproved failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentApproved(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentApproved", o2.jsonEnrollmentApproved())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentApproved failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentImmunizations":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentImmunizations")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentImmunizations failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentImmunizations(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentImmunizations", o2.jsonEnrollmentImmunizations())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentImmunizations failed", b.cause())));
								});
							}));
						}
						break;
					case "setFamilyMarried":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "familyMarried")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyMarried failed", b.cause())));
								});
							}));
						} else {
							o2.setFamilyMarried(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "familyMarried", o2.jsonFamilyMarried())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyMarried failed", b.cause())));
								});
							}));
						}
						break;
					case "setFamilySeparated":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "familySeparated")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familySeparated failed", b.cause())));
								});
							}));
						} else {
							o2.setFamilySeparated(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "familySeparated", o2.jsonFamilySeparated())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familySeparated failed", b.cause())));
								});
							}));
						}
						break;
					case "setFamilyDivorced":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "familyDivorced")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyDivorced failed", b.cause())));
								});
							}));
						} else {
							o2.setFamilyDivorced(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "familyDivorced", o2.jsonFamilyDivorced())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyDivorced failed", b.cause())));
								});
							}));
						}
						break;
					case "setFamilyAddress":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "familyAddress")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyAddress failed", b.cause())));
								});
							}));
						} else {
							o2.setFamilyAddress(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "familyAddress", o2.jsonFamilyAddress())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyAddress failed", b.cause())));
								});
							}));
						}
						break;
					case "setFamilyHowDoYouKnowTheSchool":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "familyHowDoYouKnowTheSchool")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyHowDoYouKnowTheSchool failed", b.cause())));
								});
							}));
						} else {
							o2.setFamilyHowDoYouKnowTheSchool(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "familyHowDoYouKnowTheSchool", o2.jsonFamilyHowDoYouKnowTheSchool())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyHowDoYouKnowTheSchool failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSpecialConsiderations":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSpecialConsiderations")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSpecialConsiderations failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSpecialConsiderations(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSpecialConsiderations", o2.jsonEnrollmentSpecialConsiderations())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSpecialConsiderations failed", b.cause())));
								});
							}));
						}
						break;
					case "setChildMedicalConditions":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childMedicalConditions")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childMedicalConditions failed", b.cause())));
								});
							}));
						} else {
							o2.setChildMedicalConditions(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childMedicalConditions", o2.jsonChildMedicalConditions())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childMedicalConditions failed", b.cause())));
								});
							}));
						}
						break;
					case "setChildPreviousSchoolsAttended":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childPreviousSchoolsAttended")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childPreviousSchoolsAttended failed", b.cause())));
								});
							}));
						} else {
							o2.setChildPreviousSchoolsAttended(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childPreviousSchoolsAttended", o2.jsonChildPreviousSchoolsAttended())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childPreviousSchoolsAttended failed", b.cause())));
								});
							}));
						}
						break;
					case "setChildDescription":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childDescription")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childDescription failed", b.cause())));
								});
							}));
						} else {
							o2.setChildDescription(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childDescription", o2.jsonChildDescription())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childDescription failed", b.cause())));
								});
							}));
						}
						break;
					case "setChildObjectives":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childObjectives")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childObjectives failed", b.cause())));
								});
							}));
						} else {
							o2.setChildObjectives(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childObjectives", o2.jsonChildObjectives())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childObjectives failed", b.cause())));
								});
							}));
						}
						break;
					case "setChildPottyTrained":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childPottyTrained")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childPottyTrained failed", b.cause())));
								});
							}));
						} else {
							o2.setChildPottyTrained(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childPottyTrained", o2.jsonChildPottyTrained())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childPottyTrained failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentGroupName":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentGroupName")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentGroupName failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentGroupName(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentGroupName", o2.jsonEnrollmentGroupName())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentGroupName failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentPaymentEachMonth":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentPaymentEachMonth")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentPaymentEachMonth failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentPaymentEachMonth(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentPaymentEachMonth", o2.jsonEnrollmentPaymentEachMonth())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentPaymentEachMonth failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentPaymentComplete":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentPaymentComplete")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentPaymentComplete failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentPaymentComplete(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentPaymentComplete", o2.jsonEnrollmentPaymentComplete())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentPaymentComplete failed", b.cause())));
								});
							}));
						}
						break;
					case "setCustomerProfileId":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "customerProfileId")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.customerProfileId failed", b.cause())));
								});
							}));
						} else {
							o2.setCustomerProfileId(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "customerProfileId", o2.jsonCustomerProfileId())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.customerProfileId failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentChargeDate":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentChargeDate")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentChargeDate failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentChargeDate(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentChargeDate", o2.jsonEnrollmentChargeDate())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentChargeDate failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature1":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature1")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature1 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature1(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature1", o2.jsonEnrollmentSignature1())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature1 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature2":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature2")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature2 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature2(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature2", o2.jsonEnrollmentSignature2())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature2 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature3":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature3")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature3 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature3(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature3", o2.jsonEnrollmentSignature3())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature3 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature4":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature4")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature4 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature4(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature4", o2.jsonEnrollmentSignature4())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature4 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature5":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature5")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature5 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature5(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature5", o2.jsonEnrollmentSignature5())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature5 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature6":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature6")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature6 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature6(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature6", o2.jsonEnrollmentSignature6())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature6 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature7":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature7")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature7 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature7(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature7", o2.jsonEnrollmentSignature7())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature7 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature8":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature8")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature8 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature8(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature8", o2.jsonEnrollmentSignature8())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature8 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature9":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature9")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature9 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature9(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature9", o2.jsonEnrollmentSignature9())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature9 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature10":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature10")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature10 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature10(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature10", o2.jsonEnrollmentSignature10())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature10 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate1":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate1")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate1 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate1(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate1", o2.jsonEnrollmentDate1())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate1 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate2":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate2")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate2 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate2(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate2", o2.jsonEnrollmentDate2())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate2 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate3":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate3")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate3 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate3(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate3", o2.jsonEnrollmentDate3())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate3 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate4":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate4")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate4 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate4(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate4", o2.jsonEnrollmentDate4())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate4 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate5":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate5")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate5 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate5(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate5", o2.jsonEnrollmentDate5())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate5 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate6":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate6")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate6 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate6(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate6", o2.jsonEnrollmentDate6())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate6 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate7":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate7")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate7 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate7(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate7", o2.jsonEnrollmentDate7())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate7 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate8":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate8")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate8 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate8(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate8", o2.jsonEnrollmentDate8())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate8 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate9":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate9")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate9 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate9(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate9", o2.jsonEnrollmentDate9())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate9 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate10":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate10")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate10 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate10(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate10", o2.jsonEnrollmentDate10())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate10 failed", b.cause())));
								});
							}));
						}
						break;
				}
			}
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					SchoolEnrollment o3 = new SchoolEnrollment();
					o3.setSiteRequest_(o.getSiteRequest_());
					o3.setPk(pk);
					eventHandler.handle(Future.succeededFuture(o3));
				} else {
					LOGGER.error(String.format("sqlPATCHSchoolEnrollment failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPATCHSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void patchSchoolEnrollmentResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PATCHSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("patchSchoolEnrollmentResponse failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchoolEnrollmentResponse failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PATCHSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PATCHSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSchoolEnrollment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest);
		try {
			userSchoolEnrollment(siteRequest, b -> {
				if(b.succeeded()) {
					aSearchSchoolEnrollment(siteRequest, false, true, "/api/enrollment/{id}", "GET", c -> {
						if(c.succeeded()) {
							SearchList<SchoolEnrollment> listSchoolEnrollment = c.result();
							getSchoolEnrollmentResponse(listSchoolEnrollment, d -> {
								if(d.succeeded()) {
									eventHandler.handle(Future.succeededFuture(d.result()));
									LOGGER.info(String.format("getSchoolEnrollment succeeded. "));
								} else {
									LOGGER.error(String.format("getSchoolEnrollment failed. ", d.cause()));
									errorSchoolEnrollment(siteRequest, eventHandler, d);
								}
							});
						} else {
							LOGGER.error(String.format("getSchoolEnrollment failed. ", c.cause()));
							errorSchoolEnrollment(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("getSchoolEnrollment failed. ", b.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void getSchoolEnrollmentResponse(SearchList<SchoolEnrollment> listSchoolEnrollment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolEnrollment.getSiteRequest_();
		try {
			response200GETSchoolEnrollment(listSchoolEnrollment, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("getSchoolEnrollmentResponse failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchoolEnrollmentResponse failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200GETSchoolEnrollment(SearchList<SchoolEnrollment> listSchoolEnrollment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolEnrollment.getSiteRequest_();
			SolrDocumentList solrDocuments = listSchoolEnrollment.getSolrDocumentList();

			JsonObject json = JsonObject.mapFrom(listSchoolEnrollment.getList().stream().findFirst().orElse(null));
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200GETSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchSchoolEnrollment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest);
		try {
			userSchoolEnrollment(siteRequest, b -> {
				if(b.succeeded()) {
					aSearchSchoolEnrollment(siteRequest, false, true, "/api/enrollment", "Search", c -> {
						if(c.succeeded()) {
							SearchList<SchoolEnrollment> listSchoolEnrollment = c.result();
							searchSchoolEnrollmentResponse(listSchoolEnrollment, d -> {
								if(d.succeeded()) {
									eventHandler.handle(Future.succeededFuture(d.result()));
									LOGGER.info(String.format("searchSchoolEnrollment succeeded. "));
								} else {
									LOGGER.error(String.format("searchSchoolEnrollment failed. ", d.cause()));
									errorSchoolEnrollment(siteRequest, eventHandler, d);
								}
							});
						} else {
							LOGGER.error(String.format("searchSchoolEnrollment failed. ", c.cause()));
							errorSchoolEnrollment(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("searchSchoolEnrollment failed. ", b.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchSchoolEnrollmentResponse(SearchList<SchoolEnrollment> listSchoolEnrollment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolEnrollment.getSiteRequest_();
		try {
			response200SearchSchoolEnrollment(listSchoolEnrollment, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("searchSchoolEnrollmentResponse failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchoolEnrollmentResponse failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchSchoolEnrollment(SearchList<SchoolEnrollment> listSchoolEnrollment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolEnrollment.getSiteRequest_();
			QueryResponse responseSearch = listSchoolEnrollment.getQueryResponse();
			SolrDocumentList solrDocuments = listSchoolEnrollment.getSolrDocumentList();
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
			listSchoolEnrollment.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listSchoolEnrollment.getFields();
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
			LOGGER.error(String.format("response200SearchSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// AdminSearch //

	@Override
	public void adminsearchSchoolEnrollment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest);
		try {
			userSchoolEnrollment(siteRequest, b -> {
				if(b.succeeded()) {
					aSearchSchoolEnrollment(siteRequest, false, true, "/api/admin/enrollment", "AdminSearch", c -> {
						if(c.succeeded()) {
							SearchList<SchoolEnrollment> listSchoolEnrollment = c.result();
							adminsearchSchoolEnrollmentResponse(listSchoolEnrollment, d -> {
								if(d.succeeded()) {
									eventHandler.handle(Future.succeededFuture(d.result()));
									LOGGER.info(String.format("adminsearchSchoolEnrollment succeeded. "));
								} else {
									LOGGER.error(String.format("adminsearchSchoolEnrollment failed. ", d.cause()));
									errorSchoolEnrollment(siteRequest, eventHandler, d);
								}
							});
						} else {
							LOGGER.error(String.format("adminsearchSchoolEnrollment failed. ", c.cause()));
							errorSchoolEnrollment(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("adminsearchSchoolEnrollment failed. ", b.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("adminsearchSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void adminsearchSchoolEnrollmentResponse(SearchList<SchoolEnrollment> listSchoolEnrollment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolEnrollment.getSiteRequest_();
		try {
			response200AdminSearchSchoolEnrollment(listSchoolEnrollment, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("adminsearchSchoolEnrollmentResponse failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("adminsearchSchoolEnrollmentResponse failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200AdminSearchSchoolEnrollment(SearchList<SchoolEnrollment> listSchoolEnrollment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolEnrollment.getSiteRequest_();
			QueryResponse responseSearch = listSchoolEnrollment.getQueryResponse();
			SolrDocumentList solrDocuments = listSchoolEnrollment.getSolrDocumentList();
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
			listSchoolEnrollment.getList().stream().forEach(o -> {
				JsonObject json2 = JsonObject.mapFrom(o);
				List<String> fls = listSchoolEnrollment.getFields();
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
			LOGGER.error(String.format("response200AdminSearchSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCHPayments //

	@Override
	public void patchpaymentsSchoolEnrollment(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("patchpaymentsSchoolEnrollment started. "));
			userSchoolEnrollment(siteRequest, b -> {
				if(b.succeeded()) {
					patchpaymentsSchoolEnrollmentResponse(siteRequest, c -> {
						if(c.succeeded()) {
							eventHandler.handle(Future.succeededFuture(c.result()));
							WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
							workerExecutor.executeBlocking(
								blockingCodeHandler -> {
									try {
										aSearchSchoolEnrollment(siteRequest, false, true, "/enrollment/payments", "PATCHPayments", d -> {
											if(d.succeeded()) {
												SearchList<SchoolEnrollment> listSchoolEnrollment = d.result();
												ApiRequest apiRequest = new ApiRequest();
												apiRequest.setRows(listSchoolEnrollment.getRows());
												apiRequest.setNumFound(listSchoolEnrollment.getQueryResponse().getResults().getNumFound());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest);
												siteRequest.setApiRequest_(apiRequest);
												siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
												SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolEnrollment.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(null);
												Date date = null;
												if(facets != null)
													date = (Date)facets.get("max_modified");
												String dt;
												if(date == null)
													dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).minusNanos(1000));
												else
													dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
												listSchoolEnrollment.addFilterQuery(String.format("modified_indexed_date:[* TO %s]", dt));

												try {
													listPATCHPaymentsSchoolEnrollment(apiRequest, listSchoolEnrollment, dt, e -> {
														if(e.succeeded()) {
															patchpaymentsSchoolEnrollmentResponse(siteRequest, f -> {
																if(f.succeeded()) {
																	LOGGER.info(String.format("patchpaymentsSchoolEnrollment succeeded. "));
																	blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																} else {
																	LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", f.cause()));
																	errorSchoolEnrollment(siteRequest, null, f);
																}
															});
														} else {
															LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", e.cause()));
															errorSchoolEnrollment(siteRequest, null, e);
														}
													});
												} catch(Exception ex) {
													LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", ex));
													errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
												}
											} else {
												LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", d.cause()));
												errorSchoolEnrollment(siteRequest, null, d);
											}
										});
									} catch(Exception ex) {
										LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", ex));
										errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
									}
								}, resultHandler -> {
								}
							);
						} else {
							LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", c.cause()));
							errorSchoolEnrollment(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", b.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void listPATCHPaymentsSchoolEnrollment(ApiRequest apiRequest, SearchList<SchoolEnrollment> listSchoolEnrollment, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolEnrollment.getSiteRequest_();
		listSchoolEnrollment.getList().forEach(o -> {
			SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), siteRequest.getJsonObject());
			siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
			o.setSiteRequest_(siteRequest2);
			futures.add(
				patchpaymentsSchoolEnrollmentFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						errorSchoolEnrollment(siteRequest2, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				if(listSchoolEnrollment.next(dt)) {
					listPATCHPaymentsSchoolEnrollment(apiRequest, listSchoolEnrollment, dt, eventHandler);
				} else {
					response200PATCHPaymentsSchoolEnrollment(siteRequest, eventHandler);
				}
			} else {
				LOGGER.error(String.format("listPATCHPaymentsSchoolEnrollment failed. ", a.cause()));
				errorSchoolEnrollment(listSchoolEnrollment.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolEnrollment> patchpaymentsSchoolEnrollmentFuture(SchoolEnrollment o, Boolean inheritPk, Handler<AsyncResult<SchoolEnrollment>> eventHandler) {
		Promise<SchoolEnrollment> promise = Promise.promise();
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			if(apiRequest != null && apiRequest.getNumFound() == 1L) {
				apiRequest.setOriginal(o);
				apiRequest.setPk(o.getPk());
			}
			sqlConnectionSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					sqlTransactionSchoolEnrollment(siteRequest, b -> {
						if(b.succeeded()) {
							sqlPATCHPaymentsSchoolEnrollment(o, inheritPk, c -> {
								if(c.succeeded()) {
									SchoolEnrollment schoolEnrollment = c.result();
									defineIndexSchoolEnrollment(schoolEnrollment, d -> {
										if(d.succeeded()) {
											if(apiRequest != null) {
												apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
												if(apiRequest.getNumFound() == 1L) {
													schoolEnrollment.apiRequestSchoolEnrollment();
												}
												siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
											}
											eventHandler.handle(Future.succeededFuture(schoolEnrollment));
											promise.complete(schoolEnrollment);
										} else {
											LOGGER.error(String.format("patchpaymentsSchoolEnrollmentFuture failed. ", d.cause()));
											eventHandler.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("patchpaymentsSchoolEnrollmentFuture failed. ", c.cause()));
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("patchpaymentsSchoolEnrollmentFuture failed. ", b.cause()));
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("patchpaymentsSchoolEnrollmentFuture failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("patchpaymentsSchoolEnrollmentFuture failed. ", e));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHPaymentsSchoolEnrollment(SchoolEnrollment o, Boolean inheritPk, Handler<AsyncResult<SchoolEnrollment>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Transaction tx = siteRequest.getTx();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			Set<String> methodNames = jsonObject.fieldNames();
			SchoolEnrollment o2 = new SchoolEnrollment();
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.inheritPk failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.inheritPk failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.archived failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.archived failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.deleted failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.deleted failed", b.cause())));
								});
							}));
						}
						break;
					case "setYearKey":
						{
							Long l = o2.getYearKey();
							if(l != null && !l.equals(o.getYearKey())) {
								SearchList<SchoolYear> searchList = new SearchList<SchoolYear>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolYear.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null) {
									o2.setYearKey(jsonObject.getString(methodName));
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "yearKey")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.yearKey failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolYear");
									}
								}
							}
						}
						break;
					case "removeYearKey":
						{
							Long l = o2.getYearKey();
							if(l != null) {
								SearchList<SchoolYear> searchList = new SearchList<SchoolYear>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolYear.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && l2.equals(o.getYearKey())) {
									o2.setYearKey(jsonObject.getString(methodName));
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l2, "enrollmentKeys", pk, "yearKey")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.yearKey failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolYear");
									}
								}
							}
						}
						break;
					case "addBlockKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolBlock.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getBlockKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "blockKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.blockKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolBlock");
									}
								}
							}
						}
						break;
					case "addAllBlockKeys":
						JsonArray addAllBlockKeysValues = jsonObject.getJsonArray(methodName);
						if(addAllBlockKeysValues != null) {
							for(Integer i = 0; i <  addAllBlockKeysValues.size(); i++) {
								Long l = Long.parseLong(addAllBlockKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolBlock.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getBlockKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "blockKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.blockKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolBlock");
										}
									}
								}
							}
						}
						break;
					case "setBlockKeys":
						JsonArray setBlockKeysValues = jsonObject.getJsonArray(methodName);
						if(setBlockKeysValues != null) {
							for(Integer i = 0; i <  setBlockKeysValues.size(); i++) {
								Long l = Long.parseLong(setBlockKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolBlock.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getBlockKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "blockKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.blockKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolBlock");
										}
									}
								}
							}
						}
						if(o.getBlockKeys() != null) {
							for(Long l :  o.getBlockKeys()) {
								if(l != null && (setBlockKeysValues == null || !setBlockKeysValues.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(pk, "blockKeys", l, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.blockKeys failed", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removeBlockKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolBlock.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getBlockKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(pk, "blockKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.blockKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolBlock");
									}
								}
							}
						}
						break;
					case "setChildKey":
						{
							Long l = o2.getChildKey();
							if(l != null) {
								SearchList<SchoolChild> searchList = new SearchList<SchoolChild>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolChild.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !l2.equals(o.getChildKey())) {
									o2.setChildKey(jsonObject.getString(methodName));
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "childKey", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childKey failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolChild");
									}
								}
							}
						}
						break;
					case "removeChildKey":
						{
							Long l = o2.getChildKey();
							if(l != null) {
								SearchList<SchoolChild> searchList = new SearchList<SchoolChild>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolChild.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && l2.equals(o.getChildKey())) {
									o2.setChildKey(jsonObject.getString(methodName));
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(pk, "childKey", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childKey failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolChild");
									}
								}
							}
						}
						break;
					case "addMomKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolMom.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getMomKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "momKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.momKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolMom");
									}
								}
							}
						}
						break;
					case "addAllMomKeys":
						JsonArray addAllMomKeysValues = jsonObject.getJsonArray(methodName);
						if(addAllMomKeysValues != null) {
							for(Integer i = 0; i <  addAllMomKeysValues.size(); i++) {
								Long l = Long.parseLong(addAllMomKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolMom.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getMomKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "momKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.momKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolMom");
										}
									}
								}
							}
						}
						break;
					case "setMomKeys":
						JsonArray setMomKeysValues = jsonObject.getJsonArray(methodName);
						if(setMomKeysValues != null) {
							for(Integer i = 0; i <  setMomKeysValues.size(); i++) {
								Long l = Long.parseLong(setMomKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolMom.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getMomKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "momKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.momKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolMom");
										}
									}
								}
							}
						}
						if(o.getMomKeys() != null) {
							for(Long l :  o.getMomKeys()) {
								if(l != null && (setMomKeysValues == null || !setMomKeysValues.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l, "enrollmentKeys", pk, "momKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.momKeys failed", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removeMomKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolMom.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getMomKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l2, "enrollmentKeys", pk, "momKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.momKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolMom");
									}
								}
							}
						}
						break;
					case "addDadKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolDad> searchList = new SearchList<SchoolDad>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolDad.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getDadKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "dadKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.dadKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolDad");
									}
								}
							}
						}
						break;
					case "addAllDadKeys":
						JsonArray addAllDadKeysValues = jsonObject.getJsonArray(methodName);
						if(addAllDadKeysValues != null) {
							for(Integer i = 0; i <  addAllDadKeysValues.size(); i++) {
								Long l = Long.parseLong(addAllDadKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolDad> searchList = new SearchList<SchoolDad>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolDad.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getDadKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "dadKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.dadKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolDad");
										}
									}
								}
							}
						}
						break;
					case "setDadKeys":
						JsonArray setDadKeysValues = jsonObject.getJsonArray(methodName);
						if(setDadKeysValues != null) {
							for(Integer i = 0; i <  setDadKeysValues.size(); i++) {
								Long l = Long.parseLong(setDadKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolDad> searchList = new SearchList<SchoolDad>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolDad.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getDadKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(pk, "dadKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.dadKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolDad");
										}
									}
								}
							}
						}
						if(o.getDadKeys() != null) {
							for(Long l :  o.getDadKeys()) {
								if(l != null && (setDadKeysValues == null || !setDadKeysValues.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(pk, "dadKeys", l, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.dadKeys failed", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removeDadKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolDad> searchList = new SearchList<SchoolDad>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolDad.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getDadKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(pk, "dadKeys", l2, "enrollmentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.dadKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolDad");
									}
								}
							}
						}
						break;
					case "addGuardianKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolGuardian.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getGuardianKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "guardianKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.guardianKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolGuardian");
									}
								}
							}
						}
						break;
					case "addAllGuardianKeys":
						JsonArray addAllGuardianKeysValues = jsonObject.getJsonArray(methodName);
						if(addAllGuardianKeysValues != null) {
							for(Integer i = 0; i <  addAllGuardianKeysValues.size(); i++) {
								Long l = Long.parseLong(addAllGuardianKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolGuardian.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getGuardianKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "guardianKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.guardianKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolGuardian");
										}
									}
								}
							}
						}
						break;
					case "setGuardianKeys":
						JsonArray setGuardianKeysValues = jsonObject.getJsonArray(methodName);
						if(setGuardianKeysValues != null) {
							for(Integer i = 0; i <  setGuardianKeysValues.size(); i++) {
								Long l = Long.parseLong(setGuardianKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolGuardian.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getGuardianKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "guardianKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.guardianKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolGuardian");
										}
									}
								}
							}
						}
						if(o.getGuardianKeys() != null) {
							for(Long l :  o.getGuardianKeys()) {
								if(l != null && (setGuardianKeysValues == null || !setGuardianKeysValues.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l, "enrollmentKeys", pk, "guardianKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.guardianKeys failed", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removeGuardianKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolGuardian.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getGuardianKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l2, "enrollmentKeys", pk, "guardianKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.guardianKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolGuardian");
									}
								}
							}
						}
						break;
					case "addPaymentKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolPayment.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getPaymentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKey", pk, "paymentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.paymentKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolPayment");
									}
								}
							}
						}
						break;
					case "addAllPaymentKeys":
						JsonArray addAllPaymentKeysValues = jsonObject.getJsonArray(methodName);
						if(addAllPaymentKeysValues != null) {
							for(Integer i = 0; i <  addAllPaymentKeysValues.size(); i++) {
								Long l = Long.parseLong(addAllPaymentKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolPayment.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getPaymentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKey", pk, "paymentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.paymentKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolPayment");
										}
									}
								}
							}
						}
						break;
					case "setPaymentKeys":
						JsonArray setPaymentKeysValues = jsonObject.getJsonArray(methodName);
						if(setPaymentKeysValues != null) {
							for(Integer i = 0; i <  setPaymentKeysValues.size(); i++) {
								Long l = Long.parseLong(setPaymentKeysValues.getString(i));
								if(l != null) {
									SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SchoolPayment.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getPaymentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKey", pk, "paymentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.paymentKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SchoolPayment");
										}
									}
								}
							}
						}
						if(o.getPaymentKeys() != null) {
							for(Long l :  o.getPaymentKeys()) {
								if(l != null && (setPaymentKeysValues == null || !setPaymentKeysValues.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l, "enrollmentKey", pk, "paymentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.paymentKeys failed", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removePaymentKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolPayment.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getPaymentKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l2, "enrollmentKey", pk, "paymentKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.paymentKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SchoolPayment");
									}
								}
							}
						}
						break;
					case "addUserKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SiteUser> searchList = new SearchList<SiteUser>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SiteUser.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && !o.getUserKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "userKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.userKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SiteUser");
									}
								}
							}
						}
						break;
					case "addAllUserKeys":
						JsonArray addAllUserKeysValues = jsonObject.getJsonArray(methodName);
						if(addAllUserKeysValues != null) {
							for(Integer i = 0; i <  addAllUserKeysValues.size(); i++) {
								Long l = Long.parseLong(addAllUserKeysValues.getString(i));
								if(l != null) {
									SearchList<SiteUser> searchList = new SearchList<SiteUser>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SiteUser.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getUserKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "userKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.userKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SiteUser");
										}
									}
								}
							}
						}
						break;
					case "setUserKeys":
						JsonArray setUserKeysValues = jsonObject.getJsonArray(methodName);
						if(setUserKeysValues != null) {
							for(Integer i = 0; i <  setUserKeysValues.size(); i++) {
								Long l = Long.parseLong(setUserKeysValues.getString(i));
								if(l != null) {
									SearchList<SiteUser> searchList = new SearchList<SiteUser>();
									searchList.setQuery("*:*");
									searchList.setStore(true);
									searchList.setC(SiteUser.class);
									searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
									searchList.initDeepSearchList(siteRequest);
									Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
									if(l2 != null && !o.getUserKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_addA
												, Tuple.of(l2, "enrollmentKeys", pk, "userKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.userKeys failed", b.cause())));
										});
									}));
										if(!pks.contains(l2)) {
											pks.add(l2);
											classes.add("SiteUser");
										}
									}
								}
							}
						}
						if(o.getUserKeys() != null) {
							for(Long l :  o.getUserKeys()) {
								if(l != null && (setUserKeysValues == null || !setUserKeysValues.contains(l))) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l, "enrollmentKeys", pk, "userKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.userKeys failed", b.cause())));
										});
									}));
								}
							}
						}
						break;
					case "removeUserKeys":
						{
							Long l = Long.parseLong(jsonObject.getString(methodName));
							if(l != null) {
								SearchList<SiteUser> searchList = new SearchList<SiteUser>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SiteUser.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								Long l2 = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l2 != null && o.getUserKeys().contains(l2)) {
									futures.add(Future.future(a -> {
										tx.preparedQuery(SiteContextEnUS.SQL_removeA
												, Tuple.of(l2, "enrollmentKeys", pk, "userKeys")
												, b
										-> {
											if(b.succeeded())
												a.handle(Future.succeededFuture());
											else
												a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.userKeys failed", b.cause())));
										});
									}));
									if(!pks.contains(l2)) {
										pks.add(l2);
										classes.add("SiteUser");
									}
								}
							}
						}
						break;
					case "setChildCompleteName":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childCompleteName")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childCompleteName failed", b.cause())));
								});
							}));
						} else {
							o2.setChildCompleteName(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childCompleteName", o2.jsonChildCompleteName())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childCompleteName failed", b.cause())));
								});
							}));
						}
						break;
					case "setChildCompleteNamePreferred":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childCompleteNamePreferred")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childCompleteNamePreferred failed", b.cause())));
								});
							}));
						} else {
							o2.setChildCompleteNamePreferred(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childCompleteNamePreferred", o2.jsonChildCompleteNamePreferred())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childCompleteNamePreferred failed", b.cause())));
								});
							}));
						}
						break;
					case "setChildBirthDate":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childBirthDate")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childBirthDate failed", b.cause())));
								});
							}));
						} else {
							o2.setChildBirthDate(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childBirthDate", o2.jsonChildBirthDate())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childBirthDate failed", b.cause())));
								});
							}));
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.schoolAddress failed", b.cause())));
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
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.schoolAddress failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentApproved":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentApproved")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentApproved failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentApproved(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentApproved", o2.jsonEnrollmentApproved())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentApproved failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentImmunizations":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentImmunizations")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentImmunizations failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentImmunizations(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentImmunizations", o2.jsonEnrollmentImmunizations())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentImmunizations failed", b.cause())));
								});
							}));
						}
						break;
					case "setFamilyMarried":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "familyMarried")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyMarried failed", b.cause())));
								});
							}));
						} else {
							o2.setFamilyMarried(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "familyMarried", o2.jsonFamilyMarried())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyMarried failed", b.cause())));
								});
							}));
						}
						break;
					case "setFamilySeparated":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "familySeparated")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familySeparated failed", b.cause())));
								});
							}));
						} else {
							o2.setFamilySeparated(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "familySeparated", o2.jsonFamilySeparated())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familySeparated failed", b.cause())));
								});
							}));
						}
						break;
					case "setFamilyDivorced":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "familyDivorced")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyDivorced failed", b.cause())));
								});
							}));
						} else {
							o2.setFamilyDivorced(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "familyDivorced", o2.jsonFamilyDivorced())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyDivorced failed", b.cause())));
								});
							}));
						}
						break;
					case "setFamilyAddress":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "familyAddress")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyAddress failed", b.cause())));
								});
							}));
						} else {
							o2.setFamilyAddress(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "familyAddress", o2.jsonFamilyAddress())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyAddress failed", b.cause())));
								});
							}));
						}
						break;
					case "setFamilyHowDoYouKnowTheSchool":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "familyHowDoYouKnowTheSchool")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyHowDoYouKnowTheSchool failed", b.cause())));
								});
							}));
						} else {
							o2.setFamilyHowDoYouKnowTheSchool(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "familyHowDoYouKnowTheSchool", o2.jsonFamilyHowDoYouKnowTheSchool())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.familyHowDoYouKnowTheSchool failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSpecialConsiderations":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSpecialConsiderations")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSpecialConsiderations failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSpecialConsiderations(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSpecialConsiderations", o2.jsonEnrollmentSpecialConsiderations())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSpecialConsiderations failed", b.cause())));
								});
							}));
						}
						break;
					case "setChildMedicalConditions":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childMedicalConditions")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childMedicalConditions failed", b.cause())));
								});
							}));
						} else {
							o2.setChildMedicalConditions(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childMedicalConditions", o2.jsonChildMedicalConditions())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childMedicalConditions failed", b.cause())));
								});
							}));
						}
						break;
					case "setChildPreviousSchoolsAttended":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childPreviousSchoolsAttended")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childPreviousSchoolsAttended failed", b.cause())));
								});
							}));
						} else {
							o2.setChildPreviousSchoolsAttended(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childPreviousSchoolsAttended", o2.jsonChildPreviousSchoolsAttended())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childPreviousSchoolsAttended failed", b.cause())));
								});
							}));
						}
						break;
					case "setChildDescription":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childDescription")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childDescription failed", b.cause())));
								});
							}));
						} else {
							o2.setChildDescription(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childDescription", o2.jsonChildDescription())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childDescription failed", b.cause())));
								});
							}));
						}
						break;
					case "setChildObjectives":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childObjectives")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childObjectives failed", b.cause())));
								});
							}));
						} else {
							o2.setChildObjectives(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childObjectives", o2.jsonChildObjectives())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childObjectives failed", b.cause())));
								});
							}));
						}
						break;
					case "setChildPottyTrained":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "childPottyTrained")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childPottyTrained failed", b.cause())));
								});
							}));
						} else {
							o2.setChildPottyTrained(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "childPottyTrained", o2.jsonChildPottyTrained())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.childPottyTrained failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentGroupName":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentGroupName")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentGroupName failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentGroupName(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentGroupName", o2.jsonEnrollmentGroupName())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentGroupName failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentPaymentEachMonth":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentPaymentEachMonth")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentPaymentEachMonth failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentPaymentEachMonth(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentPaymentEachMonth", o2.jsonEnrollmentPaymentEachMonth())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentPaymentEachMonth failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentPaymentComplete":
						if(jsonObject.getBoolean(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentPaymentComplete")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentPaymentComplete failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentPaymentComplete(jsonObject.getBoolean(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentPaymentComplete", o2.jsonEnrollmentPaymentComplete())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentPaymentComplete failed", b.cause())));
								});
							}));
						}
						break;
					case "setCustomerProfileId":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "customerProfileId")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.customerProfileId failed", b.cause())));
								});
							}));
						} else {
							o2.setCustomerProfileId(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "customerProfileId", o2.jsonCustomerProfileId())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.customerProfileId failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentChargeDate":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentChargeDate")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentChargeDate failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentChargeDate(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentChargeDate", o2.jsonEnrollmentChargeDate())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentChargeDate failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature1":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature1")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature1 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature1(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature1", o2.jsonEnrollmentSignature1())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature1 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature2":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature2")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature2 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature2(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature2", o2.jsonEnrollmentSignature2())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature2 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature3":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature3")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature3 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature3(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature3", o2.jsonEnrollmentSignature3())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature3 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature4":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature4")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature4 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature4(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature4", o2.jsonEnrollmentSignature4())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature4 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature5":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature5")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature5 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature5(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature5", o2.jsonEnrollmentSignature5())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature5 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature6":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature6")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature6 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature6(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature6", o2.jsonEnrollmentSignature6())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature6 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature7":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature7")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature7 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature7(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature7", o2.jsonEnrollmentSignature7())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature7 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature8":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature8")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature8 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature8(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature8", o2.jsonEnrollmentSignature8())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature8 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature9":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature9")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature9 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature9(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature9", o2.jsonEnrollmentSignature9())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature9 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentSignature10":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentSignature10")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature10 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentSignature10(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentSignature10", o2.jsonEnrollmentSignature10())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentSignature10 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate1":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate1")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate1 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate1(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate1", o2.jsonEnrollmentDate1())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate1 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate2":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate2")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate2 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate2(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate2", o2.jsonEnrollmentDate2())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate2 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate3":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate3")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate3 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate3(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate3", o2.jsonEnrollmentDate3())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate3 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate4":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate4")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate4 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate4(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate4", o2.jsonEnrollmentDate4())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate4 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate5":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate5")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate5 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate5(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate5", o2.jsonEnrollmentDate5())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate5 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate6":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate6")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate6 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate6(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate6", o2.jsonEnrollmentDate6())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate6 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate7":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate7")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate7 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate7(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate7", o2.jsonEnrollmentDate7())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate7 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate8":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate8")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate8 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate8(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate8", o2.jsonEnrollmentDate8())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate8 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate9":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate9")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate9 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate9(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate9", o2.jsonEnrollmentDate9())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate9 failed", b.cause())));
								});
							}));
						}
						break;
					case "setEnrollmentDate10":
						if(jsonObject.getString(methodName) == null) {
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_removeD
										, Tuple.of(pk, "enrollmentDate10")
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate10 failed", b.cause())));
								});
							}));
						} else {
							o2.setEnrollmentDate10(jsonObject.getString(methodName));
							futures.add(Future.future(a -> {
								tx.preparedQuery(SiteContextEnUS.SQL_setD
										, Tuple.of(pk, "enrollmentDate10", o2.jsonEnrollmentDate10())
										, b
								-> {
									if(b.succeeded())
										a.handle(Future.succeededFuture());
									else
										a.handle(Future.failedFuture(new Exception("value SchoolEnrollment.enrollmentDate10 failed", b.cause())));
								});
							}));
						}
						break;
				}
			}
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
					SchoolEnrollment o3 = new SchoolEnrollment();
					o3.setSiteRequest_(o.getSiteRequest_());
					o3.setPk(pk);
					eventHandler.handle(Future.succeededFuture(o3));
				} else {
					LOGGER.error(String.format("sqlPATCHPaymentsSchoolEnrollment failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("sqlPATCHPaymentsSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void patchpaymentsSchoolEnrollmentResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PATCHPaymentsSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("patchpaymentsSchoolEnrollmentResponse failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchpaymentsSchoolEnrollmentResponse failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PATCHPaymentsSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			LOGGER.error(String.format("response200PATCHPaymentsSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// SearchPage //

	@Override
	public void searchpageSchoolEnrollmentId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		searchpageSchoolEnrollment(operationRequest, eventHandler);
	}

	@Override
	public void searchpageSchoolEnrollment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest);
		try {
			userSchoolEnrollment(siteRequest, b -> {
				if(b.succeeded()) {
					aSearchSchoolEnrollment(siteRequest, false, true, "/enrollment", "SearchPage", c -> {
						if(c.succeeded()) {
							SearchList<SchoolEnrollment> listSchoolEnrollment = c.result();
							searchpageSchoolEnrollmentResponse(listSchoolEnrollment, d -> {
								if(d.succeeded()) {
									eventHandler.handle(Future.succeededFuture(d.result()));
									LOGGER.info(String.format("searchpageSchoolEnrollment succeeded. "));
								} else {
									LOGGER.error(String.format("searchpageSchoolEnrollment failed. ", d.cause()));
									errorSchoolEnrollment(siteRequest, eventHandler, d);
								}
							});
						} else {
							LOGGER.error(String.format("searchpageSchoolEnrollment failed. ", c.cause()));
							errorSchoolEnrollment(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("searchpageSchoolEnrollment failed. ", b.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void searchpageSchoolEnrollmentResponse(SearchList<SchoolEnrollment> listSchoolEnrollment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolEnrollment.getSiteRequest_();
		try {
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(siteRequest, buffer);
			siteRequest.setW(w);
			response200SearchPageSchoolEnrollment(listSchoolEnrollment, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("searchpageSchoolEnrollmentResponse failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchoolEnrollmentResponse failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200SearchPageSchoolEnrollment(SearchList<SchoolEnrollment> listSchoolEnrollment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolEnrollment.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listSchoolEnrollment.getSiteRequest_(), buffer);
			EnrollmentPage page = new EnrollmentPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/enrollment");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listSchoolEnrollment.size() == 1)
				siteRequest.setRequestPk(listSchoolEnrollment.get(0).getPk());
			siteRequest.setW(w);
			page.setListSchoolEnrollment(listSchoolEnrollment);
			page.setSiteRequest_(siteRequest);
			page.initDeepEnrollmentPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			LOGGER.error(String.format("response200SearchPageSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// RefreshSearchPage //

	@Override
	public void refreshsearchpageSchoolEnrollmentId(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		refreshsearchpageSchoolEnrollment(operationRequest, eventHandler);
	}

	@Override
	public void refreshsearchpageSchoolEnrollment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest);
		try {
			userSchoolEnrollment(siteRequest, b -> {
				if(b.succeeded()) {
					aSearchSchoolEnrollment(siteRequest, false, true, "/refresh-enrollment", "RefreshSearchPage", c -> {
						if(c.succeeded()) {
							SearchList<SchoolEnrollment> listSchoolEnrollment = c.result();
							refreshsearchpageSchoolEnrollmentResponse(listSchoolEnrollment, d -> {
								if(d.succeeded()) {
									eventHandler.handle(Future.succeededFuture(d.result()));
									LOGGER.info(String.format("refreshsearchpageSchoolEnrollment succeeded. "));
								} else {
									LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", d.cause()));
									errorSchoolEnrollment(siteRequest, eventHandler, d);
								}
							});
						} else {
							LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", c.cause()));
							errorSchoolEnrollment(siteRequest, eventHandler, c);
						}
					});
				} else {
					LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", b.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, b);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}


	public void refreshsearchpageSchoolEnrollmentResponse(SearchList<SchoolEnrollment> listSchoolEnrollment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = listSchoolEnrollment.getSiteRequest_();
		try {
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(siteRequest, buffer);
			siteRequest.setW(w);
			response200RefreshSearchPageSchoolEnrollment(listSchoolEnrollment, a -> {
				if(a.succeeded()) {
					eventHandler.handle(Future.succeededFuture(a.result()));
				} else {
					LOGGER.error(String.format("refreshsearchpageSchoolEnrollmentResponse failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("refreshsearchpageSchoolEnrollmentResponse failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200RefreshSearchPageSchoolEnrollment(SearchList<SchoolEnrollment> listSchoolEnrollment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = listSchoolEnrollment.getSiteRequest_();
			Buffer buffer = Buffer.buffer();
			AllWriter w = AllWriter.create(listSchoolEnrollment.getSiteRequest_(), buffer);
			EnrollmentPage page = new EnrollmentPage();
			SolrDocument pageSolrDocument = new SolrDocument();
			CaseInsensitiveHeaders requestHeaders = new CaseInsensitiveHeaders();
			siteRequest.setRequestHeaders(requestHeaders);

			pageSolrDocument.setField("pageUri_frFR_stored_string", "/refresh-enrollment");
			page.setPageSolrDocument(pageSolrDocument);
			page.setW(w);
			if(listSchoolEnrollment.size() == 1)
				siteRequest.setRequestPk(listSchoolEnrollment.get(0).getPk());
			siteRequest.setW(w);
			page.setListSchoolEnrollment(listSchoolEnrollment);
			page.setSiteRequest_(siteRequest);
			page.initDeepEnrollmentPage(siteRequest);
			page.html();
			eventHandler.handle(Future.succeededFuture(new OperationResponse(200, "OK", buffer, requestHeaders)));
		} catch(Exception e) {
			LOGGER.error(String.format("response200RefreshSearchPageSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// General //

	public Future<SchoolEnrollment> defineIndexSchoolEnrollment(SchoolEnrollment schoolEnrollment, Handler<AsyncResult<SchoolEnrollment>> eventHandler) {
		Promise<SchoolEnrollment> promise = Promise.promise();
		SiteRequestEnUS siteRequest = schoolEnrollment.getSiteRequest_();
		defineSchoolEnrollment(schoolEnrollment, c -> {
			if(c.succeeded()) {
				attributeSchoolEnrollment(schoolEnrollment, d -> {
					if(d.succeeded()) {
						indexSchoolEnrollment(schoolEnrollment, e -> {
							if(e.succeeded()) {
								sqlCommitSchoolEnrollment(siteRequest, f -> {
									if(f.succeeded()) {
										sqlCloseSchoolEnrollment(siteRequest, g -> {
											if(g.succeeded()) {
												refreshSchoolEnrollment(schoolEnrollment, h -> {
													if(h.succeeded()) {
														eventHandler.handle(Future.succeededFuture(schoolEnrollment));
														promise.complete(schoolEnrollment);
													} else {
														LOGGER.error(String.format("refreshSchoolEnrollment failed. ", h.cause()));
														errorSchoolEnrollment(siteRequest, null, h);
													}
												});
											} else {
												LOGGER.error(String.format("defineIndexSchoolEnrollment failed. ", g.cause()));
												errorSchoolEnrollment(siteRequest, null, g);
											}
										});
									} else {
										LOGGER.error(String.format("defineIndexSchoolEnrollment failed. ", f.cause()));
										errorSchoolEnrollment(siteRequest, null, f);
									}
								});
							} else {
								LOGGER.error(String.format("defineIndexSchoolEnrollment failed. ", e.cause()));
								errorSchoolEnrollment(siteRequest, null, e);
							}
						});
					} else {
						LOGGER.error(String.format("defineIndexSchoolEnrollment failed. ", d.cause()));
						errorSchoolEnrollment(siteRequest, null, d);
					}
				});
			} else {
				LOGGER.error(String.format("defineIndexSchoolEnrollment failed. ", c.cause()));
				errorSchoolEnrollment(siteRequest, null, c);
			}
		});
		return promise.future();
	}

	public void createSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<SchoolEnrollment>> eventHandler) {
		try {
			Transaction tx = siteRequest.getTx();
			String userId = siteRequest.getUserId();
			ZonedDateTime created = Optional.ofNullable(siteRequest.getJsonObject()).map(j -> j.getString("created")).map(s -> ZonedDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of(siteRequest.getSiteConfig_().getSiteZone())))).orElse(ZonedDateTime.now(ZoneId.of(siteRequest.getSiteConfig_().getSiteZone())));

			tx.preparedQuery(
					SiteContextEnUS.SQL_create
					, Tuple.of(SchoolEnrollment.class.getCanonicalName(), userId, created.toOffsetDateTime())
					, Collectors.toList()
					, createAsync
			-> {
				if(createAsync.succeeded()) {
					Row createLine = createAsync.result().value().stream().findFirst().orElseGet(() -> null);
					Long pk = createLine.getLong(0);
					SchoolEnrollment o = new SchoolEnrollment();
					o.setPk(pk);
					o.setSiteRequest_(siteRequest);
					eventHandler.handle(Future.succeededFuture(o));
				} else {
					LOGGER.error(String.format("createSchoolEnrollment failed. ", createAsync.cause()));
					eventHandler.handle(Future.failedFuture(createAsync.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("createSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void errorSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler, AsyncResult<?> resultAsync) {
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
		sqlRollbackSchoolEnrollment(siteRequest, a -> {
			if(a.succeeded()) {
				LOGGER.info(String.format("sql rollback. "));
				sqlCloseSchoolEnrollment(siteRequest, b -> {
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

	public void sqlConnectionSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						LOGGER.error(String.format("sqlConnectionSchoolEnrollment failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlTransactionSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
			LOGGER.error(String.format("sqlTransactionSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlCommitSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						LOGGER.error(String.format("sqlCommitSchoolEnrollment failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlRollbackSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						LOGGER.error(String.format("sqlRollbackSchoolEnrollment failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("sqlSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void sqlCloseSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
			LOGGER.error(String.format("sqlCloseSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolEnrollment(SiteContextEnUS siteContext, OperationRequest operationRequest) {
		return generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, null);
	}

	public SiteRequestEnUS generateSiteRequestEnUSForSchoolEnrollment(SiteContextEnUS siteContext, OperationRequest operationRequest, JsonObject body) {
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

	public void userSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			String userId = siteRequest.getUserId();
			if(userId == null) {
				eventHandler.handle(Future.succeededFuture());
			} else {
				sqlConnectionSchoolEnrollment(siteRequest, a -> {
					if(a.succeeded()) {
						sqlTransactionSchoolEnrollment(siteRequest, b -> {
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
												userSchoolEnrollmentDefine(siteRequest, jsonObject, false);

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
																		errorSchoolEnrollment(siteRequest, eventHandler, e);
																	}
																});
															} else {
																errorSchoolEnrollment(siteRequest, eventHandler, d);
															}
														});
													} else {
														errorSchoolEnrollment(siteRequest, eventHandler, c);
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
												Boolean define = userSchoolEnrollmentDefine(siteRequest, jsonObject, true);
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
																	errorSchoolEnrollment(siteRequest, eventHandler, e);
																}
															});
														} else {
															errorSchoolEnrollment(siteRequest, eventHandler, d);
														}
													});
												} else {
													siteRequest.setSiteUser(siteUser1);
													siteRequest.setUserName(siteUser1.getUserName());
													siteRequest.setUserFirstName(siteUser1.getUserFirstName());
													siteRequest.setUserLastName(siteUser1.getUserLastName());
													siteRequest.setUserId(siteUser1.getUserId());
													siteRequest.setUserKey(siteUser1.getPk());
													sqlRollbackSchoolEnrollment(siteRequest, c -> {
														if(c.succeeded()) {
															eventHandler.handle(Future.succeededFuture());
														} else {
															eventHandler.handle(Future.failedFuture(c.cause()));
															errorSchoolEnrollment(siteRequest, eventHandler, c);
														}
													});
												}
											}
										} catch(Exception e) {
											LOGGER.error(String.format("userSchoolEnrollment failed. ", e));
											eventHandler.handle(Future.failedFuture(e));
										}
									} else {
										LOGGER.error(String.format("userSchoolEnrollment failed. ", selectCAsync.cause()));
										eventHandler.handle(Future.failedFuture(selectCAsync.cause()));
									}
								});
							} else {
								LOGGER.error(String.format("userSchoolEnrollment failed. ", b.cause()));
								eventHandler.handle(Future.failedFuture(b.cause()));
							}
						});
					} else {
						LOGGER.error(String.format("userSchoolEnrollment failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("userSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public Boolean userSchoolEnrollmentDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		if(patch) {
			return jsonObject.getString("setCustomerProfileId") == null;
		} else {
			return jsonObject.getString("customerProfileId") == null;
		}
	}

	public void aSearchSchoolEnrollmentQ(String uri, String apiMethod, SearchList<SchoolEnrollment> searchList, String entityVar, String valueIndexed, String varIndexed) {
		searchList.setQuery(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : ClientUtils.escapeQueryChars(valueIndexed)));
		if(!"*".equals(entityVar)) {
			searchList.setHighlight(true);
			searchList.setHighlightSnippets(3);
			searchList.addHighlightField(varIndexed);
			searchList.setParam("hl.encoder", "html");
		}
	}

	public void aSearchSchoolEnrollmentFq(String uri, String apiMethod, SearchList<SchoolEnrollment> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(valueIndexed));
	}

	public void aSearchSchoolEnrollmentSort(String uri, String apiMethod, SearchList<SchoolEnrollment> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if(varIndexed == null)
			throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
		searchList.addSort(varIndexed, ORDER.valueOf(valueIndexed));
	}

	public void aSearchSchoolEnrollmentRows(String uri, String apiMethod, SearchList<SchoolEnrollment> searchList, Integer valueRows) {
			searchList.setRows(apiMethod != null && apiMethod.contains("Search") ? valueRows : 10);
	}

	public void aSearchSchoolEnrollmentStart(String uri, String apiMethod, SearchList<SchoolEnrollment> searchList, Integer valueStart) {
		searchList.setStart(valueStart);
	}

	public void aSearchSchoolEnrollmentVar(String uri, String apiMethod, SearchList<SchoolEnrollment> searchList, String var, String value) {
		searchList.getSiteRequest_().getRequestVars().put(var, value);
	}

	public void aSearchSchoolEnrollmentUri(String uri, String apiMethod, SearchList<SchoolEnrollment> searchList) {
	}

	public void varsSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<SearchList<OperationResponse>>> eventHandler) {
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
					LOGGER.error(String.format("aSearchSchoolEnrollment failed. ", e));
					eventHandler.handle(Future.failedFuture(e));
				}
			});
			eventHandler.handle(Future.succeededFuture());
		} catch(Exception e) {
			LOGGER.error(String.format("aSearchSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void aSearchSchoolEnrollment(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String uri, String apiMethod, Handler<AsyncResult<SearchList<SchoolEnrollment>>> eventHandler) {
		try {
			OperationRequest operationRequest = siteRequest.getOperationRequest();
			String entityListStr = siteRequest.getOperationRequest().getParams().getJsonObject("query").getString("fl");
			String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
			SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
			searchList.setPopulate(populate);
			searchList.setStore(store);
			searchList.setQuery("*:*");
			searchList.setC(SchoolEnrollment.class);
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
								varIndexed = "*".equals(entityVar) ? entityVar : SchoolEnrollment.varSearchSchoolEnrollment(entityVar);
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								valueIndexed = StringUtils.isEmpty(valueIndexed) ? "*" : valueIndexed;
								aSearchSchoolEnrollmentQ(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "fq":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								varIndexed = SchoolEnrollment.varIndexedSchoolEnrollment(entityVar);
								aSearchSchoolEnrollmentFq(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "sort":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
								valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
								varIndexed = SchoolEnrollment.varIndexedSchoolEnrollment(entityVar);
								aSearchSchoolEnrollmentSort(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
								break;
							case "start":
								valueStart = (Integer)paramObject;
								aSearchSchoolEnrollmentStart(uri, apiMethod, searchList, valueStart);
								break;
							case "rows":
								valueRows = (Integer)paramObject;
								aSearchSchoolEnrollmentRows(uri, apiMethod, searchList, valueRows);
								break;
							case "var":
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								aSearchSchoolEnrollmentVar(uri, apiMethod, searchList, entityVar, valueIndexed);
								break;
						}
					}
					aSearchSchoolEnrollmentUri(uri, apiMethod, searchList);
				} catch(Exception e) {
					LOGGER.error(String.format("aSearchSchoolEnrollment failed. ", e));
					eventHandler.handle(Future.failedFuture(e));
				}
			});
			if(searchList.getSorts().size() == 0) {
				searchList.addSort("created_indexed_date", ORDER.desc);
			}
			searchList.initDeepForClass(siteRequest);
			eventHandler.handle(Future.succeededFuture(searchList));
		} catch(Exception e) {
			LOGGER.error(String.format("aSearchSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void defineSchoolEnrollment(SchoolEnrollment o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						LOGGER.error(String.format("defineSchoolEnrollment failed. ", e));
						eventHandler.handle(Future.failedFuture(e));
					}
				} else {
					LOGGER.error(String.format("defineSchoolEnrollment failed. ", defineAsync.cause()));
					eventHandler.handle(Future.failedFuture(defineAsync.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("defineSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void attributeSchoolEnrollment(SchoolEnrollment o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
						LOGGER.error(String.format("attributeSchoolEnrollment failed. ", attributeAsync.cause()));
						eventHandler.handle(Future.failedFuture(attributeAsync.cause()));
					}
				} catch(Exception e) {
					LOGGER.error(String.format("attributeSchoolEnrollment failed. ", e));
					eventHandler.handle(Future.failedFuture(e));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("attributeSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void indexSchoolEnrollment(SchoolEnrollment o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			eventHandler.handle(Future.succeededFuture());
		} catch(Exception e) {
			LOGGER.error(String.format("indexSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public void refreshSchoolEnrollment(SchoolEnrollment o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			List<Long> pks = Optional.ofNullable(apiRequest).map(r -> r.getPks()).orElse(new ArrayList<>());
			List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
			Boolean refresh = !"false".equals(siteRequest.getRequestVars().get("refresh"));
			if(refresh && BooleanUtils.isFalse(Optional.ofNullable(siteRequest.getApiRequest_()).map(ApiRequest::getEmpty).orElse(true))) {
				SearchList<SchoolEnrollment> searchList = new SearchList<SchoolEnrollment>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolEnrollment.class);
				searchList.addFilterQuery("modified_indexed_date:[" + DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(ZonedDateTime.ofInstant(siteRequest.getApiRequest_().getCreated().toInstant(), ZoneId.of("UTC"))) + " TO *]");
				searchList.add("json.facet", "{yearKey:{terms:{field:yearKey_indexed_longs, limit:1000}}}");
				searchList.add("json.facet", "{blockKeys:{terms:{field:blockKeys_indexed_longs, limit:1000}}}");
				searchList.add("json.facet", "{childKey:{terms:{field:childKey_indexed_longs, limit:1000}}}");
				searchList.add("json.facet", "{momKeys:{terms:{field:momKeys_indexed_longs, limit:1000}}}");
				searchList.add("json.facet", "{dadKeys:{terms:{field:dadKeys_indexed_longs, limit:1000}}}");
				searchList.add("json.facet", "{guardianKeys:{terms:{field:guardianKeys_indexed_longs, limit:1000}}}");
				searchList.add("json.facet", "{paymentKeys:{terms:{field:paymentKeys_indexed_longs, limit:1000}}}");
				searchList.add("json.facet", "{userKeys:{terms:{field:userKeys_indexed_longs, limit:1000}}}");
				searchList.setRows(1000);
				searchList.initDeepSearchList(siteRequest);
				List<Future> futures = new ArrayList<>();

				for(int i=0; i < pks.size(); i++) {
					Long pk2 = pks.get(i);
					String classSimpleName2 = classes.get(i);

					if("SchoolYear".equals(classSimpleName2) && pk2 != null) {
						SearchList<SchoolYear> searchList2 = new SearchList<SchoolYear>();
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolYear.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk2);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest);
						SchoolYear o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							SchoolYearEnUSGenApiServiceImpl service = new SchoolYearEnUSGenApiServiceImpl(siteRequest.getSiteContext_());
							SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), new JsonObject());
							ApiRequest apiRequest2 = new ApiRequest();
							apiRequest2.setRows(1);
							apiRequest2.setNumFound(1l);
							apiRequest2.setNumPATCH(0L);
							apiRequest2.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest2);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolYear", JsonObject.mapFrom(apiRequest2).toString());

							o2.setPk(pk2);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolYearFuture(o2, false, a -> {
									if(a.succeeded()) {
									} else {
										LOGGER.info(String.format("SchoolYear %s failed. ", pk2));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}

					if("SchoolBlock".equals(classSimpleName2) && pk2 != null) {
						SearchList<SchoolBlock> searchList2 = new SearchList<SchoolBlock>();
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolBlock.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk2);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest);
						SchoolBlock o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							SchoolBlockEnUSGenApiServiceImpl service = new SchoolBlockEnUSGenApiServiceImpl(siteRequest.getSiteContext_());
							SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), new JsonObject());
							ApiRequest apiRequest2 = new ApiRequest();
							apiRequest2.setRows(1);
							apiRequest2.setNumFound(1l);
							apiRequest2.setNumPATCH(0L);
							apiRequest2.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest2);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest2).toString());

							o2.setPk(pk2);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolBlockFuture(o2, false, a -> {
									if(a.succeeded()) {
									} else {
										LOGGER.info(String.format("SchoolBlock %s failed. ", pk2));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}

					if("SchoolChild".equals(classSimpleName2) && pk2 != null) {
						SearchList<SchoolChild> searchList2 = new SearchList<SchoolChild>();
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolChild.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk2);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest);
						SchoolChild o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							SchoolChildEnUSGenApiServiceImpl service = new SchoolChildEnUSGenApiServiceImpl(siteRequest.getSiteContext_());
							SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), new JsonObject());
							ApiRequest apiRequest2 = new ApiRequest();
							apiRequest2.setRows(1);
							apiRequest2.setNumFound(1l);
							apiRequest2.setNumPATCH(0L);
							apiRequest2.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest2);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest2).toString());

							o2.setPk(pk2);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolChildFuture(o2, false, a -> {
									if(a.succeeded()) {
									} else {
										LOGGER.info(String.format("SchoolChild %s failed. ", pk2));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}

					if("SchoolMom".equals(classSimpleName2) && pk2 != null) {
						SearchList<SchoolMom> searchList2 = new SearchList<SchoolMom>();
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolMom.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk2);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest);
						SchoolMom o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							SchoolMomEnUSGenApiServiceImpl service = new SchoolMomEnUSGenApiServiceImpl(siteRequest.getSiteContext_());
							SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), new JsonObject());
							ApiRequest apiRequest2 = new ApiRequest();
							apiRequest2.setRows(1);
							apiRequest2.setNumFound(1l);
							apiRequest2.setNumPATCH(0L);
							apiRequest2.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest2);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolMom", JsonObject.mapFrom(apiRequest2).toString());

							o2.setPk(pk2);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolMomFuture(o2, false, a -> {
									if(a.succeeded()) {
									} else {
										LOGGER.info(String.format("SchoolMom %s failed. ", pk2));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}

					if("SchoolDad".equals(classSimpleName2) && pk2 != null) {
						SearchList<SchoolDad> searchList2 = new SearchList<SchoolDad>();
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolDad.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk2);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest);
						SchoolDad o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							SchoolDadEnUSGenApiServiceImpl service = new SchoolDadEnUSGenApiServiceImpl(siteRequest.getSiteContext_());
							SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), new JsonObject());
							ApiRequest apiRequest2 = new ApiRequest();
							apiRequest2.setRows(1);
							apiRequest2.setNumFound(1l);
							apiRequest2.setNumPATCH(0L);
							apiRequest2.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest2);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolDad", JsonObject.mapFrom(apiRequest2).toString());

							o2.setPk(pk2);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolDadFuture(o2, false, a -> {
									if(a.succeeded()) {
									} else {
										LOGGER.info(String.format("SchoolDad %s failed. ", pk2));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}

					if("SchoolGuardian".equals(classSimpleName2) && pk2 != null) {
						SearchList<SchoolGuardian> searchList2 = new SearchList<SchoolGuardian>();
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolGuardian.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk2);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest);
						SchoolGuardian o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							SchoolGuardianEnUSGenApiServiceImpl service = new SchoolGuardianEnUSGenApiServiceImpl(siteRequest.getSiteContext_());
							SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), new JsonObject());
							ApiRequest apiRequest2 = new ApiRequest();
							apiRequest2.setRows(1);
							apiRequest2.setNumFound(1l);
							apiRequest2.setNumPATCH(0L);
							apiRequest2.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest2);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolGuardian", JsonObject.mapFrom(apiRequest2).toString());

							o2.setPk(pk2);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolGuardianFuture(o2, false, a -> {
									if(a.succeeded()) {
									} else {
										LOGGER.info(String.format("SchoolGuardian %s failed. ", pk2));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}

					if("SchoolPayment".equals(classSimpleName2) && pk2 != null) {
						SearchList<SchoolPayment> searchList2 = new SearchList<SchoolPayment>();
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolPayment.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk2);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest);
						SchoolPayment o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							SchoolPaymentEnUSGenApiServiceImpl service = new SchoolPaymentEnUSGenApiServiceImpl(siteRequest.getSiteContext_());
							SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), new JsonObject());
							ApiRequest apiRequest2 = new ApiRequest();
							apiRequest2.setRows(1);
							apiRequest2.setNumFound(1l);
							apiRequest2.setNumPATCH(0L);
							apiRequest2.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest2);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolPayment", JsonObject.mapFrom(apiRequest2).toString());

							o2.setPk(pk2);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolPaymentFuture(o2, false, a -> {
									if(a.succeeded()) {
									} else {
										LOGGER.info(String.format("SchoolPayment %s failed. ", pk2));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}

					if("SiteUser".equals(classSimpleName2) && pk2 != null) {
						SearchList<SiteUser> searchList2 = new SearchList<SiteUser>();
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SiteUser.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk2);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest);
						SiteUser o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							SiteUserEnUSGenApiServiceImpl service = new SiteUserEnUSGenApiServiceImpl(siteRequest.getSiteContext_());
							SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), new JsonObject());
							ApiRequest apiRequest2 = new ApiRequest();
							apiRequest2.setRows(1);
							apiRequest2.setNumFound(1l);
							apiRequest2.setNumPATCH(0L);
							apiRequest2.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest2);
							siteRequest2.getVertx().eventBus().publish("websocketSiteUser", JsonObject.mapFrom(apiRequest2).toString());

							o2.setPk(pk2);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSiteUserFuture(o2, false, a -> {
									if(a.succeeded()) {
									} else {
										LOGGER.info(String.format("SiteUser %s failed. ", pk2));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						SchoolEnrollmentEnUSApiServiceImpl service = new SchoolEnrollmentEnUSApiServiceImpl(siteRequest.getSiteContext_());
						List<Future> futures2 = new ArrayList<>();
						for(SchoolEnrollment o2 : searchList.getList()) {
							SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), new JsonObject());
							o2.setSiteRequest_(siteRequest2);
							futures2.add(
								service.patchSchoolEnrollmentFuture(o2, false, b -> {
									if(b.succeeded()) {
									} else {
										LOGGER.info(String.format("SchoolEnrollment %s failed. ", o2.getPk()));
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
								errorSchoolEnrollment(siteRequest, eventHandler, b);
							}
						});
					} else {
						LOGGER.error("Refresh relations failed. ", a.cause());
						errorSchoolEnrollment(siteRequest, eventHandler, a);
					}
				});
			} else {
				eventHandler.handle(Future.succeededFuture());
			}
		} catch(Exception e) {
			LOGGER.error(String.format("refreshSchoolEnrollment failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
		}
	}
}
