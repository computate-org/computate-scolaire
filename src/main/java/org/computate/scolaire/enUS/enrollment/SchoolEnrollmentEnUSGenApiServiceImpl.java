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
			sqlSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
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
									apiRequestSchoolEnrollment(schoolEnrollment);
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
				} else {
					LOGGER.error(String.format("postSchoolEnrollment failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
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
			createSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolEnrollment schoolEnrollment = a.result();
					sqlPOSTSchoolEnrollment(schoolEnrollment, inheritPk, b -> {
						if(b.succeeded()) {
							defineIndexSchoolEnrollment(schoolEnrollment, c -> {
								if(c.succeeded()) {
									ApiRequest apiRequest = siteRequest.getApiRequest_();
									if(apiRequest != null) {
										apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
										schoolEnrollment.apiRequestSchoolEnrollment();
										siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
									}
									eventHandler.handle(Future.succeededFuture(schoolEnrollment));
									promise.complete(schoolEnrollment);
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
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPOSTSchoolEnrollment(SchoolEnrollment o, Boolean inheritPk, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			StringBuilder postSql = new StringBuilder();
			List<Object> postSqlParams = new ArrayList<Object>();

			if(siteRequest.getSessionId() != null) {
				postSql.append(SiteContextEnUS.SQL_setD);
				postSqlParams.addAll(Arrays.asList("sessionId", siteRequest.getSessionId(), pk));
			}
			if(siteRequest.getUserId() != null) {
				postSql.append(SiteContextEnUS.SQL_setD);
				postSqlParams.addAll(Arrays.asList("userId", siteRequest.getUserId(), pk));
			}
			if(siteRequest.getUserKey() != null) {
				postSql.append(SiteContextEnUS.SQL_setD);
				postSqlParams.addAll(Arrays.asList("userKey", siteRequest.getUserKey(), pk));

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
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("inheritPk", jsonObject.getString(entityVar), pk));
						break;
					case "created":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("created", jsonObject.getString(entityVar), pk));
						break;
					case "modified":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("modified", jsonObject.getString(entityVar), pk));
						break;
					case "archived":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("archived", jsonObject.getBoolean(entityVar), pk));
						break;
					case "deleted":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("deleted", jsonObject.getBoolean(entityVar), pk));
						break;
					case "sessionId":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("sessionId", jsonObject.getString(entityVar), pk));
						break;
					case "userId":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("userId", jsonObject.getString(entityVar), pk));
						break;
					case "userKey":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("userKey", jsonObject.getString(entityVar), pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "yearKey", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("blockKeys", pk, "enrollmentKeys", l));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("childKey", pk, "enrollmentKeys", l));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "momKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("dadKeys", pk, "enrollmentKeys", l));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "guardianKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("enrollmentKey", l, "paymentKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									postSql.append(SiteContextEnUS.SQL_addA);
									postSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "userKeys", pk));
								}
							}
						}
						break;
					case "childCompleteName":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("childCompleteName", jsonObject.getString(entityVar), pk));
						break;
					case "childCompleteNamePreferred":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("childCompleteNamePreferred", jsonObject.getString(entityVar), pk));
						break;
					case "childBirthDate":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("childBirthDate", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "schoolAddress":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("schoolAddress", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentApproved":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentApproved", jsonObject.getBoolean(entityVar), pk));
						break;
					case "enrollmentImmunizations":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentImmunizations", jsonObject.getBoolean(entityVar), pk));
						break;
					case "familyMarried":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("familyMarried", jsonObject.getBoolean(entityVar), pk));
						break;
					case "familySeparated":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("familySeparated", jsonObject.getBoolean(entityVar), pk));
						break;
					case "familyDivorced":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("familyDivorced", jsonObject.getBoolean(entityVar), pk));
						break;
					case "familyAddress":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("familyAddress", jsonObject.getString(entityVar), pk));
						break;
					case "familyHowDoYouKnowTheSchool":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("familyHowDoYouKnowTheSchool", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSpecialConsiderations":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentSpecialConsiderations", jsonObject.getString(entityVar), pk));
						break;
					case "childMedicalConditions":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("childMedicalConditions", jsonObject.getString(entityVar), pk));
						break;
					case "childPreviousSchoolsAttended":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("childPreviousSchoolsAttended", jsonObject.getString(entityVar), pk));
						break;
					case "childDescription":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("childDescription", jsonObject.getString(entityVar), pk));
						break;
					case "childObjectives":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("childObjectives", jsonObject.getString(entityVar), pk));
						break;
					case "childPottyTrained":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("childPottyTrained", jsonObject.getBoolean(entityVar), pk));
						break;
					case "enrollmentGroupName":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentGroupName", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentPaymentEachMonth":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentPaymentEachMonth", jsonObject.getBoolean(entityVar), pk));
						break;
					case "enrollmentPaymentComplete":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentPaymentComplete", jsonObject.getBoolean(entityVar), pk));
						break;
					case "customerProfileId":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("customerProfileId", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentChargeDate":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentChargeDate", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentSignature1":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentSignature1", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature2":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentSignature2", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature3":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentSignature3", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature4":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentSignature4", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature5":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentSignature5", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature6":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentSignature6", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature7":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentSignature7", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature8":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentSignature8", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature9":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentSignature9", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature10":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentSignature10", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentDate1":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentDate1", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate2":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentDate2", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate3":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentDate3", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate4":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentDate4", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate5":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentDate5", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate6":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentDate6", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate7":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentDate7", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate8":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentDate8", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate9":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentDate9", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate10":
						postSql.append(SiteContextEnUS.SQL_setD);
						postSqlParams.addAll(Arrays.asList("enrollmentDate10", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
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

	public void postSchoolEnrollmentResponse(SchoolEnrollment schoolEnrollment, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = schoolEnrollment.getSiteRequest_();
		try {
			response200POSTSchoolEnrollment(schoolEnrollment, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolEnrollment(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolEnrollment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("postSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200POSTSchoolEnrollment(SchoolEnrollment o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
	public void putimportSchoolEnrollment(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putimportSchoolEnrollment started. "));
			sqlSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolEnrollment(siteRequest, b -> {
						if(b.succeeded()) {
							putimportSchoolEnrollmentResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
											try {
												ApiRequest apiRequest = new ApiRequest();
												JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												apiRequest.setRows(jsonArray.size());
												apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
												sqlSchoolEnrollment(siteRequest2, d -> {
													if(d.succeeded()) {
														listPUTImportSchoolEnrollment(apiRequest, siteRequest2, e -> {
															if(e.succeeded()) {
																putimportSchoolEnrollmentResponse(siteRequest2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putimportSchoolEnrollment succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putimportSchoolEnrollment failed. ", f.cause()));
																		errorSchoolEnrollment(siteRequest2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putimportSchoolEnrollment failed. ", e.cause()));
																errorSchoolEnrollment(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putimportSchoolEnrollment failed. ", d.cause()));
														errorSchoolEnrollment(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putimportSchoolEnrollment failed. ", ex));
												errorSchoolEnrollment(siteRequest2, null, Future.failedFuture(ex));
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
				} else {
					LOGGER.error(String.format("putimportSchoolEnrollment failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
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

				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), json);
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());

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
						for(String f : Optional.ofNullable(o.getSaves()).orElse(Arrays.asList())) {
							if(!json.fieldNames().contains(f))
								json2.putNull("set" + StringUtils.capitalize(f));
						}
						siteRequest2.setJsonObject(json2);
						futures.add(
							patchSchoolEnrollmentFuture(o, true, a -> {
								if(a.succeeded()) {
									SchoolEnrollment schoolEnrollment = a.result();
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									siteRequest2.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
								} else {
									errorSchoolEnrollment(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolEnrollmentFuture(siteRequest2, true, a -> {
							if(a.succeeded()) {
								SchoolEnrollment schoolEnrollment = a.result();
								apiRequestSchoolEnrollment(schoolEnrollment);
							} else {
								errorSchoolEnrollment(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
								apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
								siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
					response200PUTImportSchoolEnrollment(siteRequest, eventHandler);
				} else {
					errorSchoolEnrollment(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putimportSchoolEnrollmentResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTImportSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolEnrollment(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolEnrollment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putimportSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTImportSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTMerge //

	@Override
	public void putmergeSchoolEnrollment(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putmergeSchoolEnrollment started. "));
			sqlSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolEnrollment(siteRequest, b -> {
						if(b.succeeded()) {
							putmergeSchoolEnrollmentResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
											try {
												ApiRequest apiRequest = new ApiRequest();
												JsonArray jsonArray = Optional.ofNullable(siteRequest2.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
												apiRequest.setRows(jsonArray.size());
												apiRequest.setNumFound(new Integer(jsonArray.size()).longValue());
												apiRequest.setNumPATCH(0L);
												apiRequest.initDeepApiRequest(siteRequest2);
												siteRequest2.setApiRequest_(apiRequest);
												siteRequest2.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
												sqlSchoolEnrollment(siteRequest2, d -> {
													if(d.succeeded()) {
														listPUTMergeSchoolEnrollment(apiRequest, siteRequest2, e -> {
															if(e.succeeded()) {
																putmergeSchoolEnrollmentResponse(siteRequest2, f -> {
																	if(f.succeeded()) {
																		LOGGER.info(String.format("putmergeSchoolEnrollment succeeded. "));
																		blockingCodeHandler.handle(Future.succeededFuture(f.result()));
																	} else {
																		LOGGER.error(String.format("putmergeSchoolEnrollment failed. ", f.cause()));
																		errorSchoolEnrollment(siteRequest2, null, f);
																	}
																});
															} else {
																LOGGER.error(String.format("putmergeSchoolEnrollment failed. ", e.cause()));
																errorSchoolEnrollment(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putmergeSchoolEnrollment failed. ", d.cause()));
														errorSchoolEnrollment(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putmergeSchoolEnrollment failed. ", ex));
												errorSchoolEnrollment(siteRequest2, null, Future.failedFuture(ex));
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
				} else {
					LOGGER.error(String.format("putmergeSchoolEnrollment failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
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
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());

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
						for(String f : Optional.ofNullable(o.getSaves()).orElse(Arrays.asList())) {
							if(!json.fieldNames().contains(f))
								json2.putNull("set" + StringUtils.capitalize(f));
						}
						siteRequest2.setJsonObject(json2);
						futures.add(
							patchSchoolEnrollmentFuture(o, false, a -> {
								if(a.succeeded()) {
									SchoolEnrollment schoolEnrollment = a.result();
									apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
									siteRequest2.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
								} else {
									errorSchoolEnrollment(siteRequest2, eventHandler, a);
								}
							})
						);
					}
				} else {
					futures.add(
						postSchoolEnrollmentFuture(siteRequest2, false, a -> {
							if(a.succeeded()) {
								SchoolEnrollment schoolEnrollment = a.result();
								apiRequestSchoolEnrollment(schoolEnrollment);
							} else {
								errorSchoolEnrollment(siteRequest2, eventHandler, a);
							}
						})
					);
				}
			});
			CompositeFuture.all(futures).setHandler( a -> {
				if(a.succeeded()) {
								apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
								siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
					response200PUTMergeSchoolEnrollment(siteRequest, eventHandler);
				} else {
					errorSchoolEnrollment(apiRequest.getSiteRequest_(), eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}

	public void putmergeSchoolEnrollmentResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTMergeSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolEnrollment(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolEnrollment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putmergeSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTMergeSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PUTCopy //

	@Override
	public void putcopySchoolEnrollment(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("putcopySchoolEnrollment started. "));
			sqlSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolEnrollment(siteRequest, b -> {
						if(b.succeeded()) {
							putcopySchoolEnrollmentResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
											try {
												aSearchSchoolEnrollment(siteRequest2, false, true, "/api/enrollment/copy", "PUTCopy", d -> {
													if(d.succeeded()) {
														SearchList<SchoolEnrollment> listSchoolEnrollment = d.result();
														ApiRequest apiRequest = new ApiRequest();
														apiRequest.setRows(listSchoolEnrollment.getRows());
														apiRequest.setNumFound(listSchoolEnrollment.getQueryResponse().getResults().getNumFound());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest2);
														siteRequest2.setApiRequest_(apiRequest);
														siteRequest2.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
														sqlSchoolEnrollment(siteRequest2, e -> {
															if(e.succeeded()) {
																try {
																	listPUTCopySchoolEnrollment(apiRequest, listSchoolEnrollment, f -> {
																		if(f.succeeded()) {
																			putcopySchoolEnrollmentResponse(siteRequest2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("putcopySchoolEnrollment succeeded. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("putcopySchoolEnrollment failed. ", g.cause()));
																					errorSchoolEnrollment(siteRequest2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("putcopySchoolEnrollment failed. ", f.cause()));
																			errorSchoolEnrollment(siteRequest2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("putcopySchoolEnrollment failed. ", ex));
																	errorSchoolEnrollment(siteRequest2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("putcopySchoolEnrollment failed. ", e.cause()));
																errorSchoolEnrollment(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("putcopySchoolEnrollment failed. ", d.cause()));
														errorSchoolEnrollment(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("putcopySchoolEnrollment failed. ", ex));
												errorSchoolEnrollment(siteRequest2, null, Future.failedFuture(ex));
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
				} else {
					LOGGER.error(String.format("putcopySchoolEnrollment failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
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
			futures.add(
				putcopySchoolEnrollmentFuture(siteRequest, JsonObject.mapFrom(o), a -> {
					if(a.succeeded()) {
						SchoolEnrollment schoolEnrollment = a.result();
						apiRequestSchoolEnrollment(schoolEnrollment);
					} else {
						errorSchoolEnrollment(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchoolEnrollment.size());
				siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
				if(listSchoolEnrollment.next()) {
					listPUTCopySchoolEnrollment(apiRequest, listSchoolEnrollment, eventHandler);
				} else {
					response200PUTCopySchoolEnrollment(siteRequest, eventHandler);
				}
			} else {
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

			createSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					SchoolEnrollment schoolEnrollment = a.result();
					sqlPUTCopySchoolEnrollment(schoolEnrollment, jsonObject, b -> {
						if(b.succeeded()) {
							defineSchoolEnrollment(schoolEnrollment, c -> {
								if(c.succeeded()) {
									attributeSchoolEnrollment(schoolEnrollment, d -> {
										if(d.succeeded()) {
											indexSchoolEnrollment(schoolEnrollment, e -> {
												if(e.succeeded()) {
													eventHandler.handle(Future.succeededFuture(schoolEnrollment));
													promise.complete(schoolEnrollment);
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
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPUTCopySchoolEnrollment(SchoolEnrollment o, JsonObject jsonObject, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
					case "inheritPk":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("inheritPk", jsonObject.getString(entityVar), pk));
						break;
					case "created":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("created", jsonObject.getString(entityVar), pk));
						break;
					case "modified":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("modified", jsonObject.getString(entityVar), pk));
						break;
					case "archived":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("archived", jsonObject.getBoolean(entityVar), pk));
						break;
					case "deleted":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("deleted", jsonObject.getBoolean(entityVar), pk));
						break;
					case "sessionId":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("sessionId", jsonObject.getString(entityVar), pk));
						break;
					case "userId":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("userId", jsonObject.getString(entityVar), pk));
						break;
					case "userKey":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("userKey", jsonObject.getString(entityVar), pk));
						break;
					case "yearKey":
						putSql.append(SiteContextEnUS.SQL_addA);
						putSqlParams.addAll(Arrays.asList("enrollmentKeys", Long.parseLong(jsonObject.getString(entityVar)), "yearKey", pk));
						break;
					case "blockKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("blockKeys", pk, "enrollmentKeys", l));
						}
						break;
					case "childKey":
						putSql.append(SiteContextEnUS.SQL_addA);
						putSqlParams.addAll(Arrays.asList("childKey", pk, "enrollmentKeys", Long.parseLong(jsonObject.getString(entityVar))));
						break;
					case "momKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "momKeys", pk));
						}
						break;
					case "dadKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("dadKeys", pk, "enrollmentKeys", l));
						}
						break;
					case "guardianKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "guardianKeys", pk));
						}
						break;
					case "paymentKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("enrollmentKey", l, "paymentKeys", pk));
						}
						break;
					case "userKeys":
						for(Long l : jsonObject.getJsonArray(entityVar).stream().map(a -> Long.parseLong((String)a)).collect(Collectors.toList())) {
							putSql.append(SiteContextEnUS.SQL_addA);
							putSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "userKeys", pk));
						}
						break;
					case "childCompleteName":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("childCompleteName", jsonObject.getString(entityVar), pk));
						break;
					case "childCompleteNamePreferred":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("childCompleteNamePreferred", jsonObject.getString(entityVar), pk));
						break;
					case "childBirthDate":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("childBirthDate", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "schoolAddress":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("schoolAddress", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentApproved":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentApproved", jsonObject.getBoolean(entityVar), pk));
						break;
					case "enrollmentImmunizations":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentImmunizations", jsonObject.getBoolean(entityVar), pk));
						break;
					case "familyMarried":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("familyMarried", jsonObject.getBoolean(entityVar), pk));
						break;
					case "familySeparated":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("familySeparated", jsonObject.getBoolean(entityVar), pk));
						break;
					case "familyDivorced":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("familyDivorced", jsonObject.getBoolean(entityVar), pk));
						break;
					case "familyAddress":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("familyAddress", jsonObject.getString(entityVar), pk));
						break;
					case "familyHowDoYouKnowTheSchool":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("familyHowDoYouKnowTheSchool", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSpecialConsiderations":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentSpecialConsiderations", jsonObject.getString(entityVar), pk));
						break;
					case "childMedicalConditions":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("childMedicalConditions", jsonObject.getString(entityVar), pk));
						break;
					case "childPreviousSchoolsAttended":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("childPreviousSchoolsAttended", jsonObject.getString(entityVar), pk));
						break;
					case "childDescription":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("childDescription", jsonObject.getString(entityVar), pk));
						break;
					case "childObjectives":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("childObjectives", jsonObject.getString(entityVar), pk));
						break;
					case "childPottyTrained":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("childPottyTrained", jsonObject.getBoolean(entityVar), pk));
						break;
					case "enrollmentGroupName":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentGroupName", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentPaymentEachMonth":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentPaymentEachMonth", jsonObject.getBoolean(entityVar), pk));
						break;
					case "enrollmentPaymentComplete":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentPaymentComplete", jsonObject.getBoolean(entityVar), pk));
						break;
					case "customerProfileId":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("customerProfileId", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentChargeDate":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentChargeDate", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentSignature1":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentSignature1", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature2":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentSignature2", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature3":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentSignature3", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature4":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentSignature4", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature5":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentSignature5", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature6":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentSignature6", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature7":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentSignature7", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature8":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentSignature8", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature9":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentSignature9", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentSignature10":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentSignature10", jsonObject.getString(entityVar), pk));
						break;
					case "enrollmentDate1":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentDate1", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate2":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentDate2", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate3":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentDate3", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate4":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentDate4", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate5":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentDate5", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate6":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentDate6", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate7":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentDate7", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate8":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentDate8", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate9":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentDate9", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
						break;
					case "enrollmentDate10":
						putSql.append(SiteContextEnUS.SQL_setD);
						putSqlParams.addAll(Arrays.asList("enrollmentDate10", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(jsonObject.getString(entityVar))), pk));
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

	public void putcopySchoolEnrollmentResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PUTCopySchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolEnrollment(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolEnrollment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("putcopySchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PUTCopySchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// PATCH //

	@Override
	public void patchSchoolEnrollment(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("patchSchoolEnrollment started. "));
			sqlSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolEnrollment(siteRequest, b -> {
						if(b.succeeded()) {
							patchSchoolEnrollmentResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
											try {
												aSearchSchoolEnrollment(siteRequest2, false, true, "/api/enrollment", "PATCH", d -> {
													if(d.succeeded()) {
														SearchList<SchoolEnrollment> listSchoolEnrollment = d.result();
														ApiRequest apiRequest = new ApiRequest();
														apiRequest.setRows(listSchoolEnrollment.getRows());
														apiRequest.setNumFound(listSchoolEnrollment.getQueryResponse().getResults().getNumFound());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest2);
														siteRequest2.setApiRequest_(apiRequest);
														siteRequest2.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
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

														SchoolEnrollment o = listSchoolEnrollment.getList().stream().findFirst().orElse(null);
														sqlSchoolEnrollment(siteRequest2, e -> {
															if(e.succeeded()) {
																try {
																	listPATCHSchoolEnrollment(apiRequest, listSchoolEnrollment, dt, f -> {
																		if(f.succeeded()) {
																			patchSchoolEnrollmentResponse(siteRequest2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("patchSchoolEnrollment succeeded. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("patchSchoolEnrollment failed. ", g.cause()));
																					errorSchoolEnrollment(siteRequest2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("patchSchoolEnrollment failed. ", f.cause()));
																			errorSchoolEnrollment(siteRequest2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("patchSchoolEnrollment failed. ", ex));
																	errorSchoolEnrollment(siteRequest2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("patchSchoolEnrollment failed. ", e.cause()));
																errorSchoolEnrollment(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("patchSchoolEnrollment failed. ", d.cause()));
														errorSchoolEnrollment(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("patchSchoolEnrollment failed. ", ex));
												errorSchoolEnrollment(siteRequest2, null, Future.failedFuture(ex));
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
				} else {
					LOGGER.error(String.format("patchSchoolEnrollment failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
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
			futures.add(
				patchSchoolEnrollmentFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						errorSchoolEnrollment(siteRequest, eventHandler, a);
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
			sqlPATCHSchoolEnrollment(o, inheritPk, a -> {
				if(a.succeeded()) {
					SchoolEnrollment schoolEnrollment = a.result();
					defineSchoolEnrollment(schoolEnrollment, b -> {
						if(b.succeeded()) {
							attributeSchoolEnrollment(schoolEnrollment, c -> {
								if(c.succeeded()) {
									indexSchoolEnrollment(schoolEnrollment, d -> {
										if(d.succeeded()) {
											if(apiRequest != null) {
												apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
												apiRequestSchoolEnrollment(schoolEnrollment);
												if(apiRequest.getNumFound() == 1L) {
													schoolEnrollment.apiRequestSchoolEnrollment();
												}
												siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
											}
											eventHandler.handle(Future.succeededFuture(schoolEnrollment));
											promise.complete(schoolEnrollment);
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
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHSchoolEnrollment(SchoolEnrollment o, Boolean inheritPk, Handler<AsyncResult<SchoolEnrollment>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = jsonObject.fieldNames();
			SchoolEnrollment o2 = new SchoolEnrollment();

			if(o.getUserId() == null && siteRequest.getUserId() != null) {
				patchSql.append(SiteContextEnUS.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("userId", siteRequest.getUserId(), pk));
			}
			if(o.getUserKey() == null && siteRequest.getUserKey() != null) {
				patchSql.append(SiteContextEnUS.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("userKey", siteRequest.getUserKey(), pk));

				JsonArray userKeys = Optional.ofNullable(jsonObject.getJsonArray("addUserKeys")).orElse(null);
				if(userKeys != null && !userKeys.contains(siteRequest.getUserKey()))
					userKeys.add(siteRequest.getUserKey().toString());
				else
					jsonObject.put("addUserKeys", new JsonArray().add(siteRequest.getUserKey().toString()));
			}

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.enrollment.SchoolEnrollment"));
			for(String methodName : methodNames) {
				switch(methodName) {
					case "setInheritPk":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inheritPk"));
						} else {
							o2.setInheritPk(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inheritPk", o2.jsonInheritPk(), pk));
						}
						break;
					case "setCreated":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "created"));
						} else {
							o2.setCreated(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("created", o2.jsonCreated(), pk));
						}
						break;
					case "setModified":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "modified"));
						} else {
							o2.setModified(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("modified", o2.jsonModified(), pk));
						}
						break;
					case "setArchived":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "archived"));
						} else {
							o2.setArchived(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("archived", o2.jsonArchived(), pk));
						}
						break;
					case "setDeleted":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "deleted"));
						} else {
							o2.setDeleted(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("deleted", o2.jsonDeleted(), pk));
						}
						break;
					case "setSessionId":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sessionId"));
						} else {
							o2.setSessionId(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sessionId", o2.jsonSessionId(), pk));
						}
						break;
					case "setUserId":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "userId"));
						} else {
							o2.setUserId(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("userId", o2.jsonUserId(), pk));
						}
						break;
					case "setUserKey":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "userKey"));
						} else {
							o2.setUserKey(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("userKey", o2.jsonUserKey(), pk));
						}
						break;
					case "setYearKey":
						{
							Long l = o2.getYearKey();
							if(l != null) {
								SearchList<SchoolYear> searchList = new SearchList<SchoolYear>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolYear.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setYearKey(jsonObject.getString(methodName));
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "yearKey", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setYearKey(jsonObject.getString(methodName));
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "yearKey", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("blockKeys", pk, "enrollmentKeys", l));
								}
							}
						}
						break;
					case "addAllBlockKeys":
						JsonArray addAllBlockKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllBlockKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllBlockKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolBlock.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("blockKeys", pk, "enrollmentKeys", l));
								}
							}
						}
						break;
					case "setBlockKeys":
						JsonArray setBlockKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("blockKeys", pk, "enrollmentKeys"));
						for(Integer i = 0; i <  setBlockKeysValues.size(); i++) {
							Long l = Long.parseLong(setBlockKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolBlock.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("blockKeys", pk, "enrollmentKeys", l));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("blockKeys", pk, "enrollmentKeys", l));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setChildKey(jsonObject.getString(methodName));
									patchSql.append(SiteContextEnUS.SQL_setA1);
									patchSqlParams.addAll(Arrays.asList("childKey", pk, "enrollmentKeys", l));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setChildKey(jsonObject.getString(methodName));
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("childKey", pk, "enrollmentKeys", l));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "momKeys", pk));
								}
							}
						}
						break;
					case "addAllMomKeys":
						JsonArray addAllMomKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllMomKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllMomKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolMom.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "momKeys", pk));
								}
							}
						}
						break;
					case "setMomKeys":
						JsonArray setMomKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("enrollmentKeys", "momKeys", pk));
						for(Integer i = 0; i <  setMomKeysValues.size(); i++) {
							Long l = Long.parseLong(setMomKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolMom.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "momKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "momKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("dadKeys", pk, "enrollmentKeys", l));
								}
							}
						}
						break;
					case "addAllDadKeys":
						JsonArray addAllDadKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllDadKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllDadKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolDad> searchList = new SearchList<SchoolDad>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolDad.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("dadKeys", pk, "enrollmentKeys", l));
								}
							}
						}
						break;
					case "setDadKeys":
						JsonArray setDadKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("dadKeys", pk, "enrollmentKeys"));
						for(Integer i = 0; i <  setDadKeysValues.size(); i++) {
							Long l = Long.parseLong(setDadKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolDad> searchList = new SearchList<SchoolDad>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolDad.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("dadKeys", pk, "enrollmentKeys", l));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("dadKeys", pk, "enrollmentKeys", l));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "guardianKeys", pk));
								}
							}
						}
						break;
					case "addAllGuardianKeys":
						JsonArray addAllGuardianKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllGuardianKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllGuardianKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolGuardian.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "guardianKeys", pk));
								}
							}
						}
						break;
					case "setGuardianKeys":
						JsonArray setGuardianKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("enrollmentKeys", "guardianKeys", pk));
						for(Integer i = 0; i <  setGuardianKeysValues.size(); i++) {
							Long l = Long.parseLong(setGuardianKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolGuardian.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "guardianKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "guardianKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKey", l, "paymentKeys", pk));
								}
							}
						}
						break;
					case "addAllPaymentKeys":
						JsonArray addAllPaymentKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllPaymentKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllPaymentKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolPayment.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKey", l, "paymentKeys", pk));
								}
							}
						}
						break;
					case "setPaymentKeys":
						JsonArray setPaymentKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("enrollmentKey", "paymentKeys", pk));
						for(Integer i = 0; i <  setPaymentKeysValues.size(); i++) {
							Long l = Long.parseLong(setPaymentKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolPayment.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKey", l, "paymentKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKey", l, "paymentKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "userKeys", pk));
								}
							}
						}
						break;
					case "addAllUserKeys":
						JsonArray addAllUserKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllUserKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllUserKeysValues.getString(i));
							if(l != null) {
								SearchList<SiteUser> searchList = new SearchList<SiteUser>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SiteUser.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "userKeys", pk));
								}
							}
						}
						break;
					case "setUserKeys":
						JsonArray setUserKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("enrollmentKeys", "userKeys", pk));
						for(Integer i = 0; i <  setUserKeysValues.size(); i++) {
							Long l = Long.parseLong(setUserKeysValues.getString(i));
							if(l != null) {
								SearchList<SiteUser> searchList = new SearchList<SiteUser>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SiteUser.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "userKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "userKeys", pk));
								}
							}
						}
						break;
					case "setChildCompleteName":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childCompleteName"));
						} else {
							o2.setChildCompleteName(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childCompleteName", o2.jsonChildCompleteName(), pk));
						}
						break;
					case "setChildCompleteNamePreferred":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childCompleteNamePreferred"));
						} else {
							o2.setChildCompleteNamePreferred(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childCompleteNamePreferred", o2.jsonChildCompleteNamePreferred(), pk));
						}
						break;
					case "setChildBirthDate":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childBirthDate"));
						} else {
							o2.setChildBirthDate(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childBirthDate", o2.jsonChildBirthDate(), pk));
						}
						break;
					case "setSchoolAddress":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "schoolAddress"));
						} else {
							o2.setSchoolAddress(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("schoolAddress", o2.jsonSchoolAddress(), pk));
						}
						break;
					case "setEnrollmentApproved":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentApproved"));
						} else {
							o2.setEnrollmentApproved(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentApproved", o2.jsonEnrollmentApproved(), pk));
						}
						break;
					case "setEnrollmentImmunizations":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentImmunizations"));
						} else {
							o2.setEnrollmentImmunizations(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentImmunizations", o2.jsonEnrollmentImmunizations(), pk));
						}
						break;
					case "setFamilyMarried":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familyMarried"));
						} else {
							o2.setFamilyMarried(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familyMarried", o2.jsonFamilyMarried(), pk));
						}
						break;
					case "setFamilySeparated":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familySeparated"));
						} else {
							o2.setFamilySeparated(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familySeparated", o2.jsonFamilySeparated(), pk));
						}
						break;
					case "setFamilyDivorced":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familyDivorced"));
						} else {
							o2.setFamilyDivorced(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familyDivorced", o2.jsonFamilyDivorced(), pk));
						}
						break;
					case "setFamilyAddress":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familyAddress"));
						} else {
							o2.setFamilyAddress(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familyAddress", o2.jsonFamilyAddress(), pk));
						}
						break;
					case "setFamilyHowDoYouKnowTheSchool":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familyHowDoYouKnowTheSchool"));
						} else {
							o2.setFamilyHowDoYouKnowTheSchool(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familyHowDoYouKnowTheSchool", o2.jsonFamilyHowDoYouKnowTheSchool(), pk));
						}
						break;
					case "setEnrollmentSpecialConsiderations":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSpecialConsiderations"));
						} else {
							o2.setEnrollmentSpecialConsiderations(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSpecialConsiderations", o2.jsonEnrollmentSpecialConsiderations(), pk));
						}
						break;
					case "setChildMedicalConditions":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childMedicalConditions"));
						} else {
							o2.setChildMedicalConditions(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childMedicalConditions", o2.jsonChildMedicalConditions(), pk));
						}
						break;
					case "setChildPreviousSchoolsAttended":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childPreviousSchoolsAttended"));
						} else {
							o2.setChildPreviousSchoolsAttended(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childPreviousSchoolsAttended", o2.jsonChildPreviousSchoolsAttended(), pk));
						}
						break;
					case "setChildDescription":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childDescription"));
						} else {
							o2.setChildDescription(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childDescription", o2.jsonChildDescription(), pk));
						}
						break;
					case "setChildObjectives":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childObjectives"));
						} else {
							o2.setChildObjectives(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childObjectives", o2.jsonChildObjectives(), pk));
						}
						break;
					case "setChildPottyTrained":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childPottyTrained"));
						} else {
							o2.setChildPottyTrained(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childPottyTrained", o2.jsonChildPottyTrained(), pk));
						}
						break;
					case "setEnrollmentGroupName":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentGroupName"));
						} else {
							o2.setEnrollmentGroupName(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentGroupName", o2.jsonEnrollmentGroupName(), pk));
						}
						break;
					case "setEnrollmentPaymentEachMonth":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentPaymentEachMonth"));
						} else {
							o2.setEnrollmentPaymentEachMonth(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentPaymentEachMonth", o2.jsonEnrollmentPaymentEachMonth(), pk));
						}
						break;
					case "setEnrollmentPaymentComplete":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentPaymentComplete"));
						} else {
							o2.setEnrollmentPaymentComplete(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentPaymentComplete", o2.jsonEnrollmentPaymentComplete(), pk));
						}
						break;
					case "setCustomerProfileId":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "customerProfileId"));
						} else {
							o2.setCustomerProfileId(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("customerProfileId", o2.jsonCustomerProfileId(), pk));
						}
						break;
					case "setEnrollmentChargeDate":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentChargeDate"));
						} else {
							o2.setEnrollmentChargeDate(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentChargeDate", o2.jsonEnrollmentChargeDate(), pk));
						}
						break;
					case "setEnrollmentSignature1":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature1"));
						} else {
							o2.setEnrollmentSignature1(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature1", o2.jsonEnrollmentSignature1(), pk));
						}
						break;
					case "setEnrollmentSignature2":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature2"));
						} else {
							o2.setEnrollmentSignature2(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature2", o2.jsonEnrollmentSignature2(), pk));
						}
						break;
					case "setEnrollmentSignature3":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature3"));
						} else {
							o2.setEnrollmentSignature3(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature3", o2.jsonEnrollmentSignature3(), pk));
						}
						break;
					case "setEnrollmentSignature4":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature4"));
						} else {
							o2.setEnrollmentSignature4(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature4", o2.jsonEnrollmentSignature4(), pk));
						}
						break;
					case "setEnrollmentSignature5":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature5"));
						} else {
							o2.setEnrollmentSignature5(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature5", o2.jsonEnrollmentSignature5(), pk));
						}
						break;
					case "setEnrollmentSignature6":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature6"));
						} else {
							o2.setEnrollmentSignature6(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature6", o2.jsonEnrollmentSignature6(), pk));
						}
						break;
					case "setEnrollmentSignature7":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature7"));
						} else {
							o2.setEnrollmentSignature7(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature7", o2.jsonEnrollmentSignature7(), pk));
						}
						break;
					case "setEnrollmentSignature8":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature8"));
						} else {
							o2.setEnrollmentSignature8(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature8", o2.jsonEnrollmentSignature8(), pk));
						}
						break;
					case "setEnrollmentSignature9":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature9"));
						} else {
							o2.setEnrollmentSignature9(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature9", o2.jsonEnrollmentSignature9(), pk));
						}
						break;
					case "setEnrollmentSignature10":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature10"));
						} else {
							o2.setEnrollmentSignature10(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature10", o2.jsonEnrollmentSignature10(), pk));
						}
						break;
					case "setEnrollmentDate1":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate1"));
						} else {
							o2.setEnrollmentDate1(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate1", o2.jsonEnrollmentDate1(), pk));
						}
						break;
					case "setEnrollmentDate2":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate2"));
						} else {
							o2.setEnrollmentDate2(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate2", o2.jsonEnrollmentDate2(), pk));
						}
						break;
					case "setEnrollmentDate3":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate3"));
						} else {
							o2.setEnrollmentDate3(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate3", o2.jsonEnrollmentDate3(), pk));
						}
						break;
					case "setEnrollmentDate4":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate4"));
						} else {
							o2.setEnrollmentDate4(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate4", o2.jsonEnrollmentDate4(), pk));
						}
						break;
					case "setEnrollmentDate5":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate5"));
						} else {
							o2.setEnrollmentDate5(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate5", o2.jsonEnrollmentDate5(), pk));
						}
						break;
					case "setEnrollmentDate6":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate6"));
						} else {
							o2.setEnrollmentDate6(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate6", o2.jsonEnrollmentDate6(), pk));
						}
						break;
					case "setEnrollmentDate7":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate7"));
						} else {
							o2.setEnrollmentDate7(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate7", o2.jsonEnrollmentDate7(), pk));
						}
						break;
					case "setEnrollmentDate8":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate8"));
						} else {
							o2.setEnrollmentDate8(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate8", o2.jsonEnrollmentDate8(), pk));
						}
						break;
					case "setEnrollmentDate9":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate9"));
						} else {
							o2.setEnrollmentDate9(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate9", o2.jsonEnrollmentDate9(), pk));
						}
						break;
					case "setEnrollmentDate10":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate10"));
						} else {
							o2.setEnrollmentDate10(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate10", o2.jsonEnrollmentDate10(), pk));
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
					SchoolEnrollment o3 = new SchoolEnrollment();
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

	public void patchSchoolEnrollmentResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PATCHSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolEnrollment(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolEnrollment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PATCHSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// GET //

	@Override
	public void getSchoolEnrollment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest);
		try {
			sqlSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
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
				} else {
					LOGGER.error(String.format("getSchoolEnrollment failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
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
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolEnrollment(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolEnrollment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("getSchoolEnrollment failed. ", ex));
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
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	// Search //

	@Override
	public void searchSchoolEnrollment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest);
		try {
			sqlSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
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
				} else {
					LOGGER.error(String.format("searchSchoolEnrollment failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
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
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolEnrollment(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolEnrollment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchSchoolEnrollment failed. ", ex));
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

	// AdminSearch //

	@Override
	public void adminsearchSchoolEnrollment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest);
		try {
			sqlSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
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
				} else {
					LOGGER.error(String.format("adminsearchSchoolEnrollment failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
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
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolEnrollment(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolEnrollment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("adminsearchSchoolEnrollment failed. ", ex));
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

	// PATCHPayments //

	@Override
	public void patchpaymentsSchoolEnrollment(JsonObject body, OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
		try {
			LOGGER.info(String.format("patchpaymentsSchoolEnrollment started. "));
			sqlSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					userSchoolEnrollment(siteRequest, b -> {
						if(b.succeeded()) {
							patchpaymentsSchoolEnrollmentResponse(siteRequest, c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(c.result()));
									WorkerExecutor workerExecutor = siteContext.getWorkerExecutor();
									workerExecutor.executeBlocking(
										blockingCodeHandler -> {
											SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest, body);
											try {
												aSearchSchoolEnrollment(siteRequest2, false, true, "/enrollment/payments", "PATCHPayments", d -> {
													if(d.succeeded()) {
														SearchList<SchoolEnrollment> listSchoolEnrollment = d.result();
														ApiRequest apiRequest = new ApiRequest();
														apiRequest.setRows(listSchoolEnrollment.getRows());
														apiRequest.setNumFound(listSchoolEnrollment.getQueryResponse().getResults().getNumFound());
														apiRequest.setNumPATCH(0L);
														apiRequest.initDeepApiRequest(siteRequest2);
														siteRequest2.setApiRequest_(apiRequest);
														siteRequest2.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
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

														SchoolEnrollment o = listSchoolEnrollment.getList().stream().findFirst().orElse(null);
														sqlSchoolEnrollment(siteRequest2, e -> {
															if(e.succeeded()) {
																try {
																	listPATCHPaymentsSchoolEnrollment(apiRequest, listSchoolEnrollment, dt, f -> {
																		if(f.succeeded()) {
																			patchpaymentsSchoolEnrollmentResponse(siteRequest2, g -> {
																				if(g.succeeded()) {
																					LOGGER.info(String.format("patchpaymentsSchoolEnrollment succeeded. "));
																					blockingCodeHandler.handle(Future.succeededFuture(g.result()));
																				} else {
																					LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", g.cause()));
																					errorSchoolEnrollment(siteRequest2, null, g);
																				}
																			});
																		} else {
																			LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", f.cause()));
																			errorSchoolEnrollment(siteRequest2, null, f);
																		}
																	});
																} catch(Exception ex) {
																	LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", ex));
																	errorSchoolEnrollment(siteRequest2, null, Future.failedFuture(ex));
																}
															} else {
																LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", e.cause()));
																errorSchoolEnrollment(siteRequest2, null, e);
															}
														});
													} else {
														LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", d.cause()));
														errorSchoolEnrollment(siteRequest2, null, d);
													}
												});
											} catch(Exception ex) {
												LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", ex));
												errorSchoolEnrollment(siteRequest2, null, Future.failedFuture(ex));
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
				} else {
					LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
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
			futures.add(
				patchpaymentsSchoolEnrollmentFuture(o, false, a -> {
					if(a.succeeded()) {
					} else {
						errorSchoolEnrollment(siteRequest, eventHandler, a);
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
			sqlPATCHPaymentsSchoolEnrollment(o, inheritPk, a -> {
				if(a.succeeded()) {
					SchoolEnrollment schoolEnrollment = a.result();
					defineSchoolEnrollment(schoolEnrollment, b -> {
						if(b.succeeded()) {
							attributeSchoolEnrollment(schoolEnrollment, c -> {
								if(c.succeeded()) {
									indexSchoolEnrollment(schoolEnrollment, d -> {
										if(d.succeeded()) {
											if(apiRequest != null) {
												apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
												apiRequestSchoolEnrollment(schoolEnrollment);
												if(apiRequest.getNumFound() == 1L) {
													schoolEnrollment.apiRequestSchoolEnrollment();
												}
												siteRequest.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest).toString());
											}
											eventHandler.handle(Future.succeededFuture(schoolEnrollment));
											promise.complete(schoolEnrollment);
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
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	public void sqlPATCHPaymentsSchoolEnrollment(SchoolEnrollment o, Boolean inheritPk, Handler<AsyncResult<SchoolEnrollment>> eventHandler) {
		try {
			SiteRequestEnUS siteRequest = o.getSiteRequest_();
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			Long pk = o.getPk();
			JsonObject jsonObject = siteRequest.getJsonObject();
			StringBuilder patchSql = new StringBuilder();
			List<Object> patchSqlParams = new ArrayList<Object>();
			Set<String> methodNames = jsonObject.fieldNames();
			SchoolEnrollment o2 = new SchoolEnrollment();

			if(o.getUserId() == null && siteRequest.getUserId() != null) {
				patchSql.append(SiteContextEnUS.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("userId", siteRequest.getUserId(), pk));
			}
			if(o.getUserKey() == null && siteRequest.getUserKey() != null) {
				patchSql.append(SiteContextEnUS.SQL_setD);
				patchSqlParams.addAll(Arrays.asList("userKey", siteRequest.getUserKey(), pk));

				JsonArray userKeys = Optional.ofNullable(jsonObject.getJsonArray("addUserKeys")).orElse(null);
				if(userKeys != null && !userKeys.contains(siteRequest.getUserKey()))
					userKeys.add(siteRequest.getUserKey().toString());
				else
					jsonObject.put("addUserKeys", new JsonArray().add(siteRequest.getUserKey().toString()));
			}

			patchSql.append(SiteContextEnUS.SQL_modify);
			patchSqlParams.addAll(Arrays.asList(pk, "org.computate.scolaire.enUS.enrollment.SchoolEnrollment"));
			for(String methodName : methodNames) {
				switch(methodName) {
					case "setInheritPk":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "inheritPk"));
						} else {
							o2.setInheritPk(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("inheritPk", o2.jsonInheritPk(), pk));
						}
						break;
					case "setCreated":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "created"));
						} else {
							o2.setCreated(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("created", o2.jsonCreated(), pk));
						}
						break;
					case "setModified":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "modified"));
						} else {
							o2.setModified(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("modified", o2.jsonModified(), pk));
						}
						break;
					case "setArchived":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "archived"));
						} else {
							o2.setArchived(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("archived", o2.jsonArchived(), pk));
						}
						break;
					case "setDeleted":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "deleted"));
						} else {
							o2.setDeleted(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("deleted", o2.jsonDeleted(), pk));
						}
						break;
					case "setSessionId":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "sessionId"));
						} else {
							o2.setSessionId(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("sessionId", o2.jsonSessionId(), pk));
						}
						break;
					case "setUserId":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "userId"));
						} else {
							o2.setUserId(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("userId", o2.jsonUserId(), pk));
						}
						break;
					case "setUserKey":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "userKey"));
						} else {
							o2.setUserKey(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("userKey", o2.jsonUserKey(), pk));
						}
						break;
					case "setYearKey":
						{
							Long l = o2.getYearKey();
							if(l != null) {
								SearchList<SchoolYear> searchList = new SearchList<SchoolYear>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolYear.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setYearKey(jsonObject.getString(methodName));
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "yearKey", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setYearKey(jsonObject.getString(methodName));
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "yearKey", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("blockKeys", pk, "enrollmentKeys", l));
								}
							}
						}
						break;
					case "addAllBlockKeys":
						JsonArray addAllBlockKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllBlockKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllBlockKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolBlock.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("blockKeys", pk, "enrollmentKeys", l));
								}
							}
						}
						break;
					case "setBlockKeys":
						JsonArray setBlockKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("blockKeys", pk, "enrollmentKeys"));
						for(Integer i = 0; i <  setBlockKeysValues.size(); i++) {
							Long l = Long.parseLong(setBlockKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolBlock> searchList = new SearchList<SchoolBlock>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolBlock.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("blockKeys", pk, "enrollmentKeys", l));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("blockKeys", pk, "enrollmentKeys", l));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setChildKey(jsonObject.getString(methodName));
									patchSql.append(SiteContextEnUS.SQL_setA1);
									patchSqlParams.addAll(Arrays.asList("childKey", pk, "enrollmentKeys", l));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									o2.setChildKey(jsonObject.getString(methodName));
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("childKey", pk, "enrollmentKeys", l));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "momKeys", pk));
								}
							}
						}
						break;
					case "addAllMomKeys":
						JsonArray addAllMomKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllMomKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllMomKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolMom.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "momKeys", pk));
								}
							}
						}
						break;
					case "setMomKeys":
						JsonArray setMomKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("enrollmentKeys", "momKeys", pk));
						for(Integer i = 0; i <  setMomKeysValues.size(); i++) {
							Long l = Long.parseLong(setMomKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolMom> searchList = new SearchList<SchoolMom>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolMom.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "momKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "momKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("dadKeys", pk, "enrollmentKeys", l));
								}
							}
						}
						break;
					case "addAllDadKeys":
						JsonArray addAllDadKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllDadKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllDadKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolDad> searchList = new SearchList<SchoolDad>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolDad.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("dadKeys", pk, "enrollmentKeys", l));
								}
							}
						}
						break;
					case "setDadKeys":
						JsonArray setDadKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA1);
						patchSqlParams.addAll(Arrays.asList("dadKeys", pk, "enrollmentKeys"));
						for(Integer i = 0; i <  setDadKeysValues.size(); i++) {
							Long l = Long.parseLong(setDadKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolDad> searchList = new SearchList<SchoolDad>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolDad.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("dadKeys", pk, "enrollmentKeys", l));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("dadKeys", pk, "enrollmentKeys", l));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "guardianKeys", pk));
								}
							}
						}
						break;
					case "addAllGuardianKeys":
						JsonArray addAllGuardianKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllGuardianKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllGuardianKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolGuardian.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "guardianKeys", pk));
								}
							}
						}
						break;
					case "setGuardianKeys":
						JsonArray setGuardianKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("enrollmentKeys", "guardianKeys", pk));
						for(Integer i = 0; i <  setGuardianKeysValues.size(); i++) {
							Long l = Long.parseLong(setGuardianKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolGuardian> searchList = new SearchList<SchoolGuardian>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolGuardian.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "guardianKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "guardianKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKey", l, "paymentKeys", pk));
								}
							}
						}
						break;
					case "addAllPaymentKeys":
						JsonArray addAllPaymentKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllPaymentKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllPaymentKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolPayment.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKey", l, "paymentKeys", pk));
								}
							}
						}
						break;
					case "setPaymentKeys":
						JsonArray setPaymentKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("enrollmentKey", "paymentKeys", pk));
						for(Integer i = 0; i <  setPaymentKeysValues.size(); i++) {
							Long l = Long.parseLong(setPaymentKeysValues.getString(i));
							if(l != null) {
								SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SchoolPayment.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKey", l, "paymentKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKey", l, "paymentKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_addA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "userKeys", pk));
								}
							}
						}
						break;
					case "addAllUserKeys":
						JsonArray addAllUserKeysValues = jsonObject.getJsonArray(methodName);
						for(Integer i = 0; i <  addAllUserKeysValues.size(); i++) {
							Long l = Long.parseLong(addAllUserKeysValues.getString(i));
							if(l != null) {
								SearchList<SiteUser> searchList = new SearchList<SiteUser>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SiteUser.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "userKeys", pk));
								}
							}
						}
						break;
					case "setUserKeys":
						JsonArray setUserKeysValues = jsonObject.getJsonArray(methodName);
						patchSql.append(SiteContextEnUS.SQL_clearA2);
						patchSqlParams.addAll(Arrays.asList("enrollmentKeys", "userKeys", pk));
						for(Integer i = 0; i <  setUserKeysValues.size(); i++) {
							Long l = Long.parseLong(setUserKeysValues.getString(i));
							if(l != null) {
								SearchList<SiteUser> searchList = new SearchList<SiteUser>();
								searchList.setQuery("*:*");
								searchList.setStore(true);
								searchList.setC(SiteUser.class);
								searchList.addFilterQuery((inheritPk ? "inheritPk" : "pk") + "_indexed_long:" + l);
								searchList.initDeepSearchList(siteRequest);
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_setA2);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "userKeys", pk));
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
								l = Optional.ofNullable(searchList.getList().stream().findFirst().orElse(null)).map(a -> a.getPk()).orElse(null);
								if(l != null) {
									patchSql.append(SiteContextEnUS.SQL_removeA);
									patchSqlParams.addAll(Arrays.asList("enrollmentKeys", l, "userKeys", pk));
								}
							}
						}
						break;
					case "setChildCompleteName":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childCompleteName"));
						} else {
							o2.setChildCompleteName(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childCompleteName", o2.jsonChildCompleteName(), pk));
						}
						break;
					case "setChildCompleteNamePreferred":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childCompleteNamePreferred"));
						} else {
							o2.setChildCompleteNamePreferred(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childCompleteNamePreferred", o2.jsonChildCompleteNamePreferred(), pk));
						}
						break;
					case "setChildBirthDate":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childBirthDate"));
						} else {
							o2.setChildBirthDate(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childBirthDate", o2.jsonChildBirthDate(), pk));
						}
						break;
					case "setSchoolAddress":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "schoolAddress"));
						} else {
							o2.setSchoolAddress(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("schoolAddress", o2.jsonSchoolAddress(), pk));
						}
						break;
					case "setEnrollmentApproved":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentApproved"));
						} else {
							o2.setEnrollmentApproved(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentApproved", o2.jsonEnrollmentApproved(), pk));
						}
						break;
					case "setEnrollmentImmunizations":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentImmunizations"));
						} else {
							o2.setEnrollmentImmunizations(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentImmunizations", o2.jsonEnrollmentImmunizations(), pk));
						}
						break;
					case "setFamilyMarried":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familyMarried"));
						} else {
							o2.setFamilyMarried(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familyMarried", o2.jsonFamilyMarried(), pk));
						}
						break;
					case "setFamilySeparated":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familySeparated"));
						} else {
							o2.setFamilySeparated(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familySeparated", o2.jsonFamilySeparated(), pk));
						}
						break;
					case "setFamilyDivorced":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familyDivorced"));
						} else {
							o2.setFamilyDivorced(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familyDivorced", o2.jsonFamilyDivorced(), pk));
						}
						break;
					case "setFamilyAddress":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familyAddress"));
						} else {
							o2.setFamilyAddress(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familyAddress", o2.jsonFamilyAddress(), pk));
						}
						break;
					case "setFamilyHowDoYouKnowTheSchool":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "familyHowDoYouKnowTheSchool"));
						} else {
							o2.setFamilyHowDoYouKnowTheSchool(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("familyHowDoYouKnowTheSchool", o2.jsonFamilyHowDoYouKnowTheSchool(), pk));
						}
						break;
					case "setEnrollmentSpecialConsiderations":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSpecialConsiderations"));
						} else {
							o2.setEnrollmentSpecialConsiderations(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSpecialConsiderations", o2.jsonEnrollmentSpecialConsiderations(), pk));
						}
						break;
					case "setChildMedicalConditions":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childMedicalConditions"));
						} else {
							o2.setChildMedicalConditions(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childMedicalConditions", o2.jsonChildMedicalConditions(), pk));
						}
						break;
					case "setChildPreviousSchoolsAttended":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childPreviousSchoolsAttended"));
						} else {
							o2.setChildPreviousSchoolsAttended(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childPreviousSchoolsAttended", o2.jsonChildPreviousSchoolsAttended(), pk));
						}
						break;
					case "setChildDescription":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childDescription"));
						} else {
							o2.setChildDescription(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childDescription", o2.jsonChildDescription(), pk));
						}
						break;
					case "setChildObjectives":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childObjectives"));
						} else {
							o2.setChildObjectives(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childObjectives", o2.jsonChildObjectives(), pk));
						}
						break;
					case "setChildPottyTrained":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "childPottyTrained"));
						} else {
							o2.setChildPottyTrained(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("childPottyTrained", o2.jsonChildPottyTrained(), pk));
						}
						break;
					case "setEnrollmentGroupName":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentGroupName"));
						} else {
							o2.setEnrollmentGroupName(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentGroupName", o2.jsonEnrollmentGroupName(), pk));
						}
						break;
					case "setEnrollmentPaymentEachMonth":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentPaymentEachMonth"));
						} else {
							o2.setEnrollmentPaymentEachMonth(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentPaymentEachMonth", o2.jsonEnrollmentPaymentEachMonth(), pk));
						}
						break;
					case "setEnrollmentPaymentComplete":
						if(jsonObject.getBoolean(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentPaymentComplete"));
						} else {
							o2.setEnrollmentPaymentComplete(jsonObject.getBoolean(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentPaymentComplete", o2.jsonEnrollmentPaymentComplete(), pk));
						}
						break;
					case "setCustomerProfileId":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "customerProfileId"));
						} else {
							o2.setCustomerProfileId(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("customerProfileId", o2.jsonCustomerProfileId(), pk));
						}
						break;
					case "setEnrollmentChargeDate":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentChargeDate"));
						} else {
							o2.setEnrollmentChargeDate(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentChargeDate", o2.jsonEnrollmentChargeDate(), pk));
						}
						break;
					case "setEnrollmentSignature1":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature1"));
						} else {
							o2.setEnrollmentSignature1(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature1", o2.jsonEnrollmentSignature1(), pk));
						}
						break;
					case "setEnrollmentSignature2":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature2"));
						} else {
							o2.setEnrollmentSignature2(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature2", o2.jsonEnrollmentSignature2(), pk));
						}
						break;
					case "setEnrollmentSignature3":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature3"));
						} else {
							o2.setEnrollmentSignature3(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature3", o2.jsonEnrollmentSignature3(), pk));
						}
						break;
					case "setEnrollmentSignature4":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature4"));
						} else {
							o2.setEnrollmentSignature4(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature4", o2.jsonEnrollmentSignature4(), pk));
						}
						break;
					case "setEnrollmentSignature5":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature5"));
						} else {
							o2.setEnrollmentSignature5(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature5", o2.jsonEnrollmentSignature5(), pk));
						}
						break;
					case "setEnrollmentSignature6":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature6"));
						} else {
							o2.setEnrollmentSignature6(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature6", o2.jsonEnrollmentSignature6(), pk));
						}
						break;
					case "setEnrollmentSignature7":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature7"));
						} else {
							o2.setEnrollmentSignature7(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature7", o2.jsonEnrollmentSignature7(), pk));
						}
						break;
					case "setEnrollmentSignature8":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature8"));
						} else {
							o2.setEnrollmentSignature8(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature8", o2.jsonEnrollmentSignature8(), pk));
						}
						break;
					case "setEnrollmentSignature9":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature9"));
						} else {
							o2.setEnrollmentSignature9(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature9", o2.jsonEnrollmentSignature9(), pk));
						}
						break;
					case "setEnrollmentSignature10":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentSignature10"));
						} else {
							o2.setEnrollmentSignature10(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentSignature10", o2.jsonEnrollmentSignature10(), pk));
						}
						break;
					case "setEnrollmentDate1":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate1"));
						} else {
							o2.setEnrollmentDate1(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate1", o2.jsonEnrollmentDate1(), pk));
						}
						break;
					case "setEnrollmentDate2":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate2"));
						} else {
							o2.setEnrollmentDate2(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate2", o2.jsonEnrollmentDate2(), pk));
						}
						break;
					case "setEnrollmentDate3":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate3"));
						} else {
							o2.setEnrollmentDate3(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate3", o2.jsonEnrollmentDate3(), pk));
						}
						break;
					case "setEnrollmentDate4":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate4"));
						} else {
							o2.setEnrollmentDate4(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate4", o2.jsonEnrollmentDate4(), pk));
						}
						break;
					case "setEnrollmentDate5":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate5"));
						} else {
							o2.setEnrollmentDate5(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate5", o2.jsonEnrollmentDate5(), pk));
						}
						break;
					case "setEnrollmentDate6":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate6"));
						} else {
							o2.setEnrollmentDate6(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate6", o2.jsonEnrollmentDate6(), pk));
						}
						break;
					case "setEnrollmentDate7":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate7"));
						} else {
							o2.setEnrollmentDate7(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate7", o2.jsonEnrollmentDate7(), pk));
						}
						break;
					case "setEnrollmentDate8":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate8"));
						} else {
							o2.setEnrollmentDate8(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate8", o2.jsonEnrollmentDate8(), pk));
						}
						break;
					case "setEnrollmentDate9":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate9"));
						} else {
							o2.setEnrollmentDate9(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate9", o2.jsonEnrollmentDate9(), pk));
						}
						break;
					case "setEnrollmentDate10":
						if(jsonObject.getString(methodName) == null) {
							patchSql.append(SiteContextEnUS.SQL_removeD);
							patchSqlParams.addAll(Arrays.asList(pk, "enrollmentDate10"));
						} else {
							o2.setEnrollmentDate10(jsonObject.getString(methodName));
							patchSql.append(SiteContextEnUS.SQL_setD);
							patchSqlParams.addAll(Arrays.asList("enrollmentDate10", o2.jsonEnrollmentDate10(), pk));
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
					SchoolEnrollment o3 = new SchoolEnrollment();
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

	public void patchpaymentsSchoolEnrollmentResponse(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			response200PATCHPaymentsSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolEnrollment(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolEnrollment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("patchpaymentsSchoolEnrollment failed. ", ex));
			errorSchoolEnrollment(siteRequest, null, Future.failedFuture(ex));
		}
	}
	public void response200PATCHPaymentsSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			JsonObject json = new JsonObject();
			eventHandler.handle(Future.succeededFuture(OperationResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily()))));
		} catch(Exception e) {
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
			sqlSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
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
				} else {
					LOGGER.error(String.format("searchpageSchoolEnrollment failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
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
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolEnrollment(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolEnrollment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSchoolEnrollment failed. ", ex));
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
			sqlSchoolEnrollment(siteRequest, a -> {
				if(a.succeeded()) {
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
				} else {
					LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", a.cause()));
					errorSchoolEnrollment(siteRequest, eventHandler, a);
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
					SQLConnection sqlConnection = siteRequest.getSqlConnection();
					sqlConnection.commit(b -> {
						if(b.succeeded()) {
							sqlConnection.close(c -> {
								if(c.succeeded()) {
									eventHandler.handle(Future.succeededFuture(a.result()));
								} else {
									errorSchoolEnrollment(siteRequest, eventHandler, c);
								}
							});
						} else {
							errorSchoolEnrollment(siteRequest, eventHandler, b);
						}
					});
				} else {
					errorSchoolEnrollment(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", ex));
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
								eventHandler.handle(Future.succeededFuture(schoolEnrollment));
								promise.complete(schoolEnrollment);
							} else {
								errorSchoolEnrollment(siteRequest, null, e);
							}
						});
					} else {
						errorSchoolEnrollment(siteRequest, null, d);
					}
				});
			} else {
				errorSchoolEnrollment(siteRequest, null, c);
			}
		});
		return promise.future();
	}

	public void createSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<SchoolEnrollment>> eventHandler) {
		try {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			String userId = siteRequest.getUserId();

			sqlConnection.queryWithParams(
					SiteContextEnUS.SQL_create
					, new JsonArray(Arrays.asList(SchoolEnrollment.class.getCanonicalName(), userId))
					, createAsync
			-> {
				JsonArray createLine = createAsync.result().getResults().stream().findFirst().orElseGet(() -> null);
				Long pk = createLine.getLong(0);
				SchoolEnrollment o = new SchoolEnrollment();
				o.setPk(pk);
				o.setSiteRequest_(siteRequest);
				eventHandler.handle(Future.succeededFuture(o));
			});
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
		}
	}

	public ApiRequest apiRequestSchoolEnrollment(SchoolEnrollment o) {
		ApiRequest apiRequest = o.getSiteRequest_().getApiRequest_();
		if(apiRequest != null) {
			List<Long> pks = apiRequest.getPks();
			List<String> classes = apiRequest.getClasses();
			if(o.getYearKey() != null) {
				if(!pks.contains(o.getYearKey())) {
					pks.add(o.getYearKey());
					classes.add("SchoolYear");
				}
			}
			for(Long pk : o.getBlockKeys()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("SchoolBlock");
				}
			}
			if(o.getChildKey() != null) {
				if(!pks.contains(o.getChildKey())) {
					pks.add(o.getChildKey());
					classes.add("SchoolChild");
				}
			}
			for(Long pk : o.getMomKeys()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("SchoolMom");
				}
			}
			for(Long pk : o.getDadKeys()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("SchoolDad");
				}
			}
			for(Long pk : o.getGuardianKeys()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("SchoolGuardian");
				}
			}
			for(Long pk : o.getPaymentKeys()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("SchoolPayment");
				}
			}
			for(Long pk : o.getUserKeys()) {
				if(!pks.contains(pk)) {
					pks.add(pk);
					classes.add("SiteUser");
				}
			}
		}
		return apiRequest;
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
		if(siteRequest != null) {
			SQLConnection sqlConnection = siteRequest.getSqlConnection();
			if(sqlConnection != null) {
				sqlConnection.rollback(a -> {
					if(a.succeeded()) {
						LOGGER.info(String.format("sql rollback. "));
						sqlConnection.close(b -> {
							if(a.succeeded()) {
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
			} else {
				if(eventHandler != null)
					eventHandler.handle(Future.succeededFuture(responseOperation));
			}
		}
	}

	public void sqlSchoolEnrollment(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

								userService.createSiteUser(siteRequest2, b -> {
									if(b.succeeded()) {
										SiteUser siteUser = b.result();
										userService.sqlPOSTSiteUser(siteUser, false, c -> {
											if(c.succeeded()) {
												userService.defineIndexSiteUser(siteUser, d -> {
													if(d.succeeded()) {
														siteRequest.setSiteUser(siteUser);
														siteRequest.setUserName(jsonPrincipal.getString("preferred_username"));
														siteRequest.setUserFirstName(jsonPrincipal.getString("given_name"));
														siteRequest.setUserLastName(jsonPrincipal.getString("family_name"));
														siteRequest.setUserId(jsonPrincipal.getString("sub"));
														siteRequest.setUserKey(siteUser.getPk());
														eventHandler.handle(Future.succeededFuture());
													} else {
														errorSchoolEnrollment(siteRequest, eventHandler, d);
													}
												});
											} else {
												errorSchoolEnrollment(siteRequest, eventHandler, c);
											}
										});
									} else {
										errorSchoolEnrollment(siteRequest, eventHandler, b);
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

									userService.sqlPATCHSiteUser(siteUser, false, c -> {
										if(c.succeeded()) {
											SiteUser siteUser2 = c.result();
											userService.defineIndexSiteUser(siteUser2, d -> {
												if(d.succeeded()) {
													siteRequest.setSiteUser(siteUser2);
													siteRequest.setUserName(siteUser2.getUserName());
													siteRequest.setUserFirstName(siteUser2.getUserFirstName());
													siteRequest.setUserLastName(siteUser2.getUserLastName());
													siteRequest.setUserId(siteUser2.getUserId());
													siteRequest.setUserKey(siteUser2.getPk());
													eventHandler.handle(Future.succeededFuture());
												} else {
													errorSchoolEnrollment(siteRequest, eventHandler, d);
												}
											});
										} else {
											errorSchoolEnrollment(siteRequest, eventHandler, c);
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

	public void defineSchoolEnrollment(SchoolEnrollment o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void attributeSchoolEnrollment(SchoolEnrollment o, Handler<AsyncResult<OperationResponse>> eventHandler) {
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

	public void indexSchoolEnrollment(SchoolEnrollment o, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			o.initDeepForClass(siteRequest);
			o.indexForClass();
			if(BooleanUtils.isFalse(Optional.ofNullable(siteRequest.getApiRequest_()).map(ApiRequest::getEmpty).orElse(true))) {
				SiteRequestEnUS siteRequest2 = generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), new JsonObject());
				siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
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
				searchList.initDeepSearchList(siteRequest2);
				List<Future> futures = new ArrayList<>();

				{
					Long pk = o.getYearKey();
					SearchList<SchoolYear> searchList2 = new SearchList<SchoolYear>();
					if(pk != null) {
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolYear.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest2);
					}
					SchoolYear o2 = searchList2.getList().stream().findFirst().orElse(null);

					if(o2 != null) {
						SchoolYearEnUSGenApiServiceImpl service = new SchoolYearEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());

						ApiRequest apiRequest = new ApiRequest();
						apiRequest.setRows(1);
						apiRequest.setNumFound(1L);
						apiRequest.setNumPATCH(0L);
						apiRequest.initDeepApiRequest(siteRequest2);
						siteRequest2.setApiRequest_(apiRequest);
						siteRequest2.getVertx().eventBus().publish("websocketSchoolYear", JsonObject.mapFrom(apiRequest).toString());

						if(pk != null) {
							o2.setPk(pk);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolYearFuture(o2, false, a -> {
									if(a.succeeded()) {
										LOGGER.info(String.format("SchoolYear %s refreshed. ", pk));
									} else {
										LOGGER.info(String.format("SchoolYear %s failed. ", pk));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				{
					SchoolBlockEnUSGenApiServiceImpl service = new SchoolBlockEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
					for(Long pk : o.getBlockKeys()) {
					SearchList<SchoolBlock> searchList2 = new SearchList<SchoolBlock>();
					if(pk != null) {
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolBlock.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest2);
					}
					SchoolBlock o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1l);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolBlock", JsonObject.mapFrom(apiRequest).toString());

							o2.setPk(pk);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolBlockFuture(o2, false, a -> {
									if(a.succeeded()) {
										LOGGER.info(String.format("SchoolBlock %s refreshed. ", pk));
									} else {
										LOGGER.info(String.format("SchoolBlock %s failed. ", pk));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				{
					Long pk = o.getChildKey();
					SearchList<SchoolChild> searchList2 = new SearchList<SchoolChild>();
					if(pk != null) {
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolChild.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest2);
					}
					SchoolChild o2 = searchList2.getList().stream().findFirst().orElse(null);

					if(o2 != null) {
						SchoolChildEnUSGenApiServiceImpl service = new SchoolChildEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());

						ApiRequest apiRequest = new ApiRequest();
						apiRequest.setRows(1);
						apiRequest.setNumFound(1L);
						apiRequest.setNumPATCH(0L);
						apiRequest.initDeepApiRequest(siteRequest2);
						siteRequest2.setApiRequest_(apiRequest);
						siteRequest2.getVertx().eventBus().publish("websocketSchoolChild", JsonObject.mapFrom(apiRequest).toString());

						if(pk != null) {
							o2.setPk(pk);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolChildFuture(o2, false, a -> {
									if(a.succeeded()) {
										LOGGER.info(String.format("SchoolChild %s refreshed. ", pk));
									} else {
										LOGGER.info(String.format("SchoolChild %s failed. ", pk));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				{
					SchoolMomEnUSGenApiServiceImpl service = new SchoolMomEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
					for(Long pk : o.getMomKeys()) {
					SearchList<SchoolMom> searchList2 = new SearchList<SchoolMom>();
					if(pk != null) {
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolMom.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest2);
					}
					SchoolMom o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1l);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolMom", JsonObject.mapFrom(apiRequest).toString());

							o2.setPk(pk);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolMomFuture(o2, false, a -> {
									if(a.succeeded()) {
										LOGGER.info(String.format("SchoolMom %s refreshed. ", pk));
									} else {
										LOGGER.info(String.format("SchoolMom %s failed. ", pk));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				{
					SchoolDadEnUSGenApiServiceImpl service = new SchoolDadEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
					for(Long pk : o.getDadKeys()) {
					SearchList<SchoolDad> searchList2 = new SearchList<SchoolDad>();
					if(pk != null) {
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolDad.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest2);
					}
					SchoolDad o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1l);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolDad", JsonObject.mapFrom(apiRequest).toString());

							o2.setPk(pk);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolDadFuture(o2, false, a -> {
									if(a.succeeded()) {
										LOGGER.info(String.format("SchoolDad %s refreshed. ", pk));
									} else {
										LOGGER.info(String.format("SchoolDad %s failed. ", pk));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				{
					SchoolGuardianEnUSGenApiServiceImpl service = new SchoolGuardianEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
					for(Long pk : o.getGuardianKeys()) {
					SearchList<SchoolGuardian> searchList2 = new SearchList<SchoolGuardian>();
					if(pk != null) {
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolGuardian.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest2);
					}
					SchoolGuardian o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1l);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolGuardian", JsonObject.mapFrom(apiRequest).toString());

							o2.setPk(pk);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolGuardianFuture(o2, false, a -> {
									if(a.succeeded()) {
										LOGGER.info(String.format("SchoolGuardian %s refreshed. ", pk));
									} else {
										LOGGER.info(String.format("SchoolGuardian %s failed. ", pk));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				{
					SchoolPaymentEnUSGenApiServiceImpl service = new SchoolPaymentEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
					for(Long pk : o.getPaymentKeys()) {
					SearchList<SchoolPayment> searchList2 = new SearchList<SchoolPayment>();
					if(pk != null) {
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SchoolPayment.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest2);
					}
					SchoolPayment o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1l);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest);
							siteRequest2.getVertx().eventBus().publish("websocketSchoolPayment", JsonObject.mapFrom(apiRequest).toString());

							o2.setPk(pk);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSchoolPaymentFuture(o2, false, a -> {
									if(a.succeeded()) {
										LOGGER.info(String.format("SchoolPayment %s refreshed. ", pk));
									} else {
										LOGGER.info(String.format("SchoolPayment %s failed. ", pk));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				{
					SiteUserEnUSGenApiServiceImpl service = new SiteUserEnUSGenApiServiceImpl(siteRequest2.getSiteContext_());
					for(Long pk : o.getUserKeys()) {
					SearchList<SiteUser> searchList2 = new SearchList<SiteUser>();
					if(pk != null) {
						searchList2.setStore(true);
						searchList2.setQuery("*:*");
						searchList2.setC(SiteUser.class);
						searchList2.addFilterQuery("pk_indexed_long:" + pk);
						searchList2.setRows(1);
						searchList2.initDeepSearchList(siteRequest2);
					}
					SiteUser o2 = searchList2.getList().stream().findFirst().orElse(null);

						if(o2 != null) {
							ApiRequest apiRequest = new ApiRequest();
							apiRequest.setRows(1);
							apiRequest.setNumFound(1l);
							apiRequest.setNumPATCH(0L);
							apiRequest.initDeepApiRequest(siteRequest2);
							siteRequest2.setApiRequest_(apiRequest);
							siteRequest2.getVertx().eventBus().publish("websocketSiteUser", JsonObject.mapFrom(apiRequest).toString());

							o2.setPk(pk);
							o2.setSiteRequest_(siteRequest2);
							futures.add(
								service.patchSiteUserFuture(o2, false, a -> {
									if(a.succeeded()) {
										LOGGER.info(String.format("SiteUser %s refreshed. ", pk));
									} else {
										LOGGER.info(String.format("SiteUser %s failed. ", pk));
										eventHandler.handle(Future.failedFuture(a.cause()));
									}
								})
							);
						}
					}
				}

				CompositeFuture.all(futures).setHandler(a -> {
					if(a.succeeded()) {
						LOGGER.info("Refresh relations succeeded. ");
						SchoolEnrollmentEnUSApiServiceImpl service = new SchoolEnrollmentEnUSApiServiceImpl(siteRequest2.getSiteContext_());
						List<Future> futures2 = new ArrayList<>();
						for(SchoolEnrollment o2 : searchList.getList()) {
							futures2.add(
								service.patchSchoolEnrollmentFuture(o2, false, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("SchoolEnrollment %s refreshed. ", o2.getPk()));
									} else {
										LOGGER.info(String.format("SchoolEnrollment %s failed. ", o2.getPk()));
										eventHandler.handle(Future.failedFuture(b.cause()));
									}
								})
							);
						}

						CompositeFuture.all(futures2).setHandler(b -> {
							if(b.succeeded()) {
								LOGGER.info("Refresh SchoolEnrollment succeeded. ");
								eventHandler.handle(Future.succeededFuture());
							} else {
								LOGGER.error("Refresh relations failed. ", b.cause());
								errorSchoolEnrollment(siteRequest2, eventHandler, b);
							}
						});
					} else {
						LOGGER.error("Refresh relations failed. ", a.cause());
						errorSchoolEnrollment(siteRequest2, eventHandler, a);
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
