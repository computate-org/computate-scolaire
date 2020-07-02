package org.computate.scolaire.enUS.enrollment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import org.computate.scolaire.enUS.payment.SchoolPaymentEnUSApiServiceImpl;
import org.computate.scolaire.enUS.payment.SchoolPaymentEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.user.SiteUser;

import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;
import net.authorize.Environment;
import net.authorize.api.contract.v1.ArrayOfTransactionSummaryType;
import net.authorize.api.contract.v1.CreateCustomerProfileRequest;
import net.authorize.api.contract.v1.CreateCustomerProfileResponse;
import net.authorize.api.contract.v1.CustomerProfileIdType;
import net.authorize.api.contract.v1.CustomerProfileType;
import net.authorize.api.contract.v1.GetTransactionDetailsRequest;
import net.authorize.api.contract.v1.GetTransactionDetailsResponse;
import net.authorize.api.contract.v1.GetTransactionListForCustomerRequest;
import net.authorize.api.contract.v1.GetTransactionListResponse;
import net.authorize.api.contract.v1.MerchantAuthenticationType;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.OrderExType;
import net.authorize.api.contract.v1.Paging;
import net.authorize.api.contract.v1.TransactionDetailsType;
import net.authorize.api.contract.v1.TransactionListOrderFieldEnum;
import net.authorize.api.contract.v1.TransactionListSorting;
import net.authorize.api.contract.v1.TransactionSummaryType;
import net.authorize.api.controller.CreateCustomerProfileController;
import net.authorize.api.controller.GetTransactionDetailsController;
import net.authorize.api.controller.GetTransactionListForCustomerController;
import net.authorize.api.controller.base.ApiOperationBase;

/**
 * Translate: false
 * CanonicalName.frFR: org.computate.scolaire.frFR.inscription.InscriptionScolaireFrFRApiServiceImpl
 **/
public class SchoolEnrollmentEnUSApiServiceImpl extends SchoolEnrollmentEnUSGenApiServiceImpl {

	public SchoolEnrollmentEnUSApiServiceImpl(SiteContextEnUS siteContext) {
		super(siteContext);
	}

	@Override public Boolean userSchoolEnrollmentDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		String sessionIdBefore = siteRequest.getSessionIdBefore();
		String sessionId = siteRequest.getSessionId();

		SearchList<SchoolEnrollment> enrollmentList = new SearchList<SchoolEnrollment>();
		if(sessionIdBefore != null) {
			enrollmentList.setStore(true);
			enrollmentList.setQuery("*:*");
			enrollmentList.setC(SchoolEnrollment.class);
			enrollmentList.setSiteRequest_(siteRequest);
			enrollmentList.addFilterQuery("(sessionId_indexed_string:" + ClientUtils.escapeQueryChars(sessionIdBefore) + " OR sessionId_indexed_string:" + ClientUtils.escapeQueryChars(sessionId) + ")");
			enrollmentList.addFilterQuery("!userId_indexed_string:[* TO *]");
			enrollmentList.initDeepForClass(siteRequest);
			for(SchoolEnrollment enrollment : enrollmentList.getList()) {
				if(patch)
					jsonObject.put("addEnrollmentKeys", enrollment.getPk().toString());
				else
					jsonObject.put("enrollmentKeys", new JsonArray().add(enrollment.getPk().toString()));
			}
		}

		Boolean defineProfile1 = userSiteUserDefineProfile(1, siteRequest, jsonObject, patch);
		Boolean defineProfile2 = userSiteUserDefineProfile(2, siteRequest, jsonObject, patch);
		if(defineProfile1 || defineProfile2) 
			return true;
		else 
			return enrollmentList.size() > 0;
	}

	public Boolean userSiteUserDefineProfile(Integer schoolNumber, SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		SiteConfig siteConfig = siteRequest.getSiteConfig_();
		String authorizeApiLoginId = (String)siteConfig.obtainSiteConfig("authorizeApiLoginId" + schoolNumber);
		String authorizeTransactionKey = (String)siteConfig.obtainSiteConfig("authorizeTransactionKey" + schoolNumber);

		if(authorizeApiLoginId != null && authorizeTransactionKey != null) {
			String customerProfileId;
			if(patch)
				customerProfileId = jsonObject.getString("setCustomerProfileId" + schoolNumber);
			else
				customerProfileId = jsonObject.getString("customerProfileId" + schoolNumber);
	
			if(customerProfileId == null) {
				MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
				merchantAuthenticationType.setName(authorizeApiLoginId);
				merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
				ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);
				
				CreateCustomerProfileRequest createCustomerProfileRequest = new CreateCustomerProfileRequest();
				createCustomerProfileRequest.setMerchantAuthentication(merchantAuthenticationType);
				CustomerProfileType profile = new CustomerProfileType();
				if(patch) {
					profile.setEmail(jsonObject.getString("setUserEmail"));
					profile.setDescription(jsonObject.getString("setUserId"));
					profile.setMerchantCustomerId(jsonObject.getString("setUserCompleteName"));
				}
				else {
					profile.setEmail(jsonObject.getString("userEmail"));
					profile.setDescription(jsonObject.getString("userId"));
					profile.setMerchantCustomerId(jsonObject.getString("userCompleteName"));
				}
				createCustomerProfileRequest.setProfile(profile);
		
				CreateCustomerProfileController controller = new CreateCustomerProfileController(createCustomerProfileRequest);
				GetTransactionListForCustomerController.setEnvironment(Environment.valueOf(siteConfig.getAuthorizeEnvironment()));
				controller.execute();
				if(controller.getErrorResponse() != null)
					throw new RuntimeException(controller.getResults().toString());
				CreateCustomerProfileResponse response = controller.getApiResponse();
				if(MessageTypeEnum.ERROR.equals(response.getMessages().getResultCode())) {
					String message = response.getMessages().getMessage().stream().findFirst().map(m -> m.getText()).orElse("");
					Matcher matcher = Pattern.compile("A duplicate record with ID (\\d+) already exists.").matcher(message);
					if(matcher.find()) {
						customerProfileId = matcher.group(1);
					}
					else {
	//					throw new RuntimeException(response.getMessages().getMessage().stream().findFirst().map(m -> String.format("%s %s", m.getCode(), m.getText())).orElse("CreateCustomerProfileRequest failed. "));
						LOGGER.error(response.getMessages().getMessage().stream().findFirst().map(m -> String.format("%s %s", m.getCode(), m.getText())).orElse("CreateCustomerProfileRequest failed. "));
					}
				}
				else {
					customerProfileId = response.getCustomerProfileId();
				}
				if(patch)
					jsonObject.put("setCustomerProfileId", customerProfileId);
				else
					jsonObject.put("customerProfileId", customerProfileId);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public void refreshsearchpageSchoolEnrollment(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSchoolEnrollment(siteContext, operationRequest);
		try {
			userSchoolEnrollment(siteRequest, b -> {
				if(b.succeeded()) {
					aSearchSchoolEnrollment(siteRequest, false, true, "/refresh-enrollment", "RefreshSearchPage", c -> {
						if(c.succeeded()) {
							try {
								SearchList<SchoolEnrollment> listSchoolEnrollment = c.result();

								if(listSchoolEnrollment.size() > 1) {
									throw new RuntimeException("The enrollment refresh page search returned more than one enrollment. ");
								}
								if(listSchoolEnrollment.size() == 0) {
									throw new RuntimeException("The enrollment refresh page search did not find any enrollment. ");
								}

								SchoolEnrollment schoolEnrollment = listSchoolEnrollment.first();

								enrollmentChargesFuture(schoolEnrollment, d -> {
									if(d.succeeded()) {
										authorizeNetEnrollmentPaymentsFuture(schoolEnrollment, e -> {
											if(e.succeeded()) {
												LOGGER.info("Creating payments for customer %s succeeded. ");
												refreshsearchpageSchoolEnrollmentResponse(listSchoolEnrollment, f -> {
													if(e.succeeded()) {
														eventHandler.handle(Future.succeededFuture(f.result()));
														LOGGER.info(String.format("refreshsearchpageSchoolEnrollment succeeded. "));
													} else {
														LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", f.cause()));
														errorSchoolEnrollment(siteRequest, eventHandler, f);
													}
												});
											} else {
												LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", e.cause()));
												errorSchoolEnrollment(siteRequest, eventHandler, e);
											}
										});
									} else {
										LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", d.cause()));
										errorSchoolEnrollment(siteRequest, eventHandler, d);
									}
								});
							} catch(Exception e) {
								LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", e));
								errorSchoolEnrollment(siteRequest, eventHandler, Future.failedFuture(e));
							}
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

	@Override public void listPATCHPaymentsSchoolEnrollment(ApiRequest apiRequest, SearchList<SchoolEnrollment> listSchoolEnrollment, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
		List<Future> futures = new ArrayList<>();
		SiteRequestEnUS siteRequest = listSchoolEnrollment.getSiteRequest_();

		listSchoolEnrollment.getList().forEach(o -> {
			futures.add(
					enrollmentChargesFuture(o, a -> {
					if(a.succeeded()) {
						authorizeNetEnrollmentPaymentsFuture(o, c -> {
							if(a.succeeded()) {
							} else {
								errorSchoolEnrollment(siteRequest, eventHandler, a);
							}
						});
					} else {
						errorSchoolEnrollment(siteRequest, eventHandler, a);
					}
				})
			);
		});
		CompositeFuture.all(futures).setHandler( a -> {
			if(a.succeeded()) {
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchoolEnrollment.size());
				response200PATCHPaymentsSchoolEnrollment(siteRequest, eventHandler);
			} else {
				errorSchoolEnrollment(listSchoolEnrollment.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	public Future<SchoolEnrollment> enrollmentChargesFuture(SchoolEnrollment schoolEnrollment, Handler<AsyncResult<SchoolEnrollment>> eventHandler) {
		SiteRequestEnUS siteRequest = schoolEnrollment.getSiteRequest_();
		SiteContextEnUS siteContextEnUS = siteRequest.getSiteContext_();
		SiteConfig siteConfig = siteRequest.getSiteConfig_();
		Integer paymentDay = siteConfig.getPaymentDay();
		Vertx vertx = siteRequest.getVertx();
		SchoolPaymentEnUSApiServiceImpl paymentService = new SchoolPaymentEnUSApiServiceImpl(siteContextEnUS);
		Promise<SchoolEnrollment> promise = Promise.promise();
		try {

			SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
			searchList.setStore(true);
			searchList.setQuery("*:*");
			searchList.setC(SchoolPayment.class);
			searchList.addFilterQuery("enrollmentKey_indexed_long:" + schoolEnrollment.getPk());
			searchList.add("json.facet", "{paymentDate:{terms:{field:paymentDate_indexed_date, limit:1000}}}");
			searchList.add("json.facet", "{chargeEnrollment:{terms:{field:chargeEnrollment_indexed_boolean, limit:1000}}}");
			searchList.add("json.facet", "{chargeFirstLast:{terms:{field:chargeFirstLast_indexed_boolean, limit:1000}}}");
			searchList.setRows(0);
			searchList.initDeepSearchList(siteRequest);

			SiteConfig configSite = siteContextEnUS.getSiteConfig();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(searchList.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
			Integer chargeEnrollment = Optional.ofNullable((SimpleOrderedMap)facets.get("chargeEnrollment")).map(m -> ((List<SimpleOrderedMap>)m.get("buckets"))).orElse(Arrays.asList()).stream().filter(m -> BooleanUtils.isTrue((Boolean)m.get("val"))).findFirst().map(m -> (Integer)m.get("count")).orElse(0);
			Integer chargeFirstLast = Optional.ofNullable((SimpleOrderedMap)facets.get("chargeFirstLast")).map(m -> ((List<SimpleOrderedMap>)m.get("buckets"))).orElse(Arrays.asList()).stream().filter(m -> BooleanUtils.isTrue((Boolean)m.get("val"))).findFirst().map(m -> (Integer)m.get("count")).orElse(0);
			List<LocalDate> paymentsDue = Optional.ofNullable((SimpleOrderedMap)facets.get("paymentDate")).map(m -> ((List<SimpleOrderedMap>)m.get("buckets"))).orElse(Arrays.asList()).stream().collect(Collectors.mapping(m -> ((Date)m.get("val")).toInstant().atZone(ZoneId.of(configSite.getSiteZone())).toLocalDate(), Collectors.toList()));
			LocalDate sessionStartDate = schoolEnrollment.getSessionStartDate();
			LocalDate sessionEndDate = schoolEnrollment.getSessionEndDate();
			LocalDate chargeStartDate = sessionStartDate == null ? null : (sessionStartDate.getDayOfMonth() < paymentDay ? sessionStartDate.withDayOfMonth(paymentDay).minusMonths(1) : sessionStartDate.withDayOfMonth(paymentDay));
			LocalDate chargeEndDate = sessionEndDate == null ? null : (sessionEndDate.getDayOfMonth() < paymentDay ? sessionEndDate.withDayOfMonth(paymentDay).minusMonths(1) : sessionEndDate.withDayOfMonth(paymentDay));
			BigDecimal blockPricePerMonth = schoolEnrollment.getBlockPricePerMonth();
			BigDecimal yearEnrollmentFee = schoolEnrollment.getYearEnrollmentFee();
			Long enrollmentKey = schoolEnrollment.getPk();
			Long numMonths = chargeStartDate == null ? null : ChronoUnit.MONTHS.between(chargeStartDate, chargeEndDate);
			List<Future> futures = new ArrayList<>();

			if(sessionStartDate != null && yearEnrollmentFee != null && chargeEnrollment == 0) {
				SchoolPayment o = new SchoolPayment();
				o.setSiteRequest_(siteRequest);
				o.setChargeAmount(yearEnrollmentFee);
				o.setPaymentDate(sessionStartDate);
				o.setCustomerProfileId(schoolEnrollment.getCustomerProfileId());
				o.setChargeEnrollment(true);
				o.setEnrollmentKey(schoolEnrollment.getPk());
				o.setEnrollment_(schoolEnrollment);

				SiteRequestEnUS siteRequest2 = siteRequest.copy();
				siteRequest2.setJsonObject(JsonObject.mapFrom(o));
				siteRequest2.initDeepSiteRequestEnUS(siteRequest);

				ApiRequest apiRequest2 = new ApiRequest();
				apiRequest2.setRows(1);
				apiRequest2.setNumFound(1L);
				apiRequest2.setNumPATCH(0L);
				apiRequest2.initDeepApiRequest(siteRequest2);
				siteRequest2.setApiRequest_(apiRequest2);

				futures.add(paymentService.postSchoolPaymentFuture(siteRequest2, false, b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("charge %s created for enrollment %s. ", sessionStartDate, enrollmentKey));
					} else {
						LOGGER.error(String.format("create fee %s failed for enrollment %s. ", sessionStartDate, enrollmentKey), b.cause());
						promise.fail(b.cause());
					}
				}));
			}
			if(blockPricePerMonth != null && chargeFirstLast == 0) {
				SchoolPayment o = new SchoolPayment();
				o.setSiteRequest_(siteRequest);
				o.setChargeAmount(blockPricePerMonth.multiply(BigDecimal.valueOf(2)));
				o.setPaymentDate(sessionStartDate);
				o.setCustomerProfileId(schoolEnrollment.getCustomerProfileId());
				o.setChargeFirstLast(true);
				o.setEnrollmentKey(schoolEnrollment.getPk());
				o.setEnrollment_(schoolEnrollment);

				SiteRequestEnUS siteRequest2 = siteRequest.copy();
				siteRequest2.setJsonObject(JsonObject.mapFrom(o));
				siteRequest2.initDeepSiteRequestEnUS(siteRequest);

				ApiRequest apiRequest2 = new ApiRequest();
				apiRequest2.setRows(1);
				apiRequest2.setNumFound(1L);
				apiRequest2.setNumPATCH(0L);
				apiRequest2.initDeepApiRequest(siteRequest2);
				siteRequest2.setApiRequest_(apiRequest2);

				futures.add(paymentService.postSchoolPaymentFuture(siteRequest2, false, b -> {
					if(b.succeeded()) {
						LOGGER.info(String.format("charge %s created for enrollment %s. ", sessionStartDate, enrollmentKey));
					} else {
						LOGGER.error(String.format("create fee %s failed for enrollment %s. ", sessionStartDate, enrollmentKey), b.cause());
						promise.fail(b.cause());
					}
				}));
			}
			if(numMonths != null) {
				for(long i = 0; i < numMonths; i++) {
					LocalDate paymentDate = chargeStartDate.plusMonths(i);
					if(!paymentsDue.contains(paymentDate)) {
						SchoolPayment o = new SchoolPayment();
						o.setSiteRequest_(siteRequest);
						o.setChargeAmount(schoolEnrollment.getBlockPricePerMonth());
						o.setPaymentDate(paymentDate);
						o.setCustomerProfileId(schoolEnrollment.getCustomerProfileId());
						o.setChargeMonth(true);
						o.setEnrollmentKey(schoolEnrollment.getPk());
						o.setEnrollment_(schoolEnrollment);
	
						SiteRequestEnUS siteRequest2 = siteRequest.copy();
						siteRequest2.setJsonObject(JsonObject.mapFrom(o));
						siteRequest2.initDeepSiteRequestEnUS(siteRequest);

						ApiRequest apiRequest2 = new ApiRequest();
						apiRequest2.setRows(1);
						apiRequest2.setNumFound(1L);
						apiRequest2.setNumPATCH(0L);
						apiRequest2.initDeepApiRequest(siteRequest2);
						siteRequest2.setApiRequest_(apiRequest2);
	
						futures.add(paymentService.postSchoolPaymentFuture(siteRequest2, false, b -> {
							if(b.succeeded()) {
								LOGGER.info(String.format("charge %s created for enrollment %s. ", paymentDate, enrollmentKey));
							} else {
								LOGGER.error(String.format("create fee %s failed for enrollment %s. ", paymentDate, enrollmentKey), b.cause());
								promise.fail(b.cause());
							}
						}));
					}
				}
			}
			CompositeFuture.all(futures).setHandler(b -> {
				if(b.succeeded()) {
					LOGGER.info(String.format("Charges created for enrollment %s. ", enrollmentKey));
					eventHandler.handle(Future.succeededFuture(schoolEnrollment));
					promise.complete();
				} else {
					LOGGER.error("Refresh relations failed. ", b.cause());
					eventHandler.handle(Future.failedFuture(b.cause()));
					promise.fail(b.cause());
				}
			});

			return promise.future();
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
			return Future.failedFuture(e);
		}
	}

	public Future<Void> authorizeNetEnrollmentPaymentsFuture(SchoolEnrollment schoolEnrollment, Handler<AsyncResult<Void>> eventHandler) {
		SiteRequestEnUS siteRequest = schoolEnrollment.getSiteRequest_();
		SiteContextEnUS siteContextEnUS = siteRequest.getSiteContext_();
		SiteUser siteUser = siteRequest.getSiteUser();
		SiteConfig siteConfig = siteRequest.getSiteConfig_();
		Promise<Void> promise = Promise.promise();
		Integer schoolNumber = schoolEnrollment.getSchoolNumber();
		MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
		String authorizeApiLoginId = (String)siteConfig.obtainSiteConfig("authorizeApiLoginId" + schoolNumber);
		String authorizeTransactionKey = (String)siteConfig.obtainSiteConfig("authorizeTransactionKey" + schoolNumber);
		merchantAuthenticationType.setName(authorizeApiLoginId);
		merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
		ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);
		String customerProfileId = Optional.ofNullable(siteUser).map(u -> (String)u.obtainSiteUser("customerProfileId" + schoolNumber)).orElse(null);

		if(authorizeTransactionKey == null) {
			String m = String.format("Missing an Authorize.net transaction key. ");
			LOGGER.error(m);
			eventHandler.handle(Future.failedFuture(m));
			promise.fail(m);
			return promise.future();
		}
		if(authorizeApiLoginId == null) {
			String m = String.format("Missing an Authorize.net api login ID. ");
			LOGGER.error(m);
			eventHandler.handle(Future.failedFuture(m));
			promise.fail(m);
			return promise.future();
		}
		if(siteUser == null) {
			String m = String.format("Only a logged in user can access this page. ");
			LOGGER.error(m);
			eventHandler.handle(Future.failedFuture(m));
			promise.fail(m);
			return promise.future();
		}
		else if(customerProfileId == null) {
			String m = String.format(String.format("The user %s does not have a customer profile ID. ", customerProfileId));
			LOGGER.error(m);
			eventHandler.handle(Future.failedFuture(m));
			promise.fail(m);
			return promise.future();
		}
		else {
			try {
				Paging paging = new Paging();
				paging.setLimit(1000);
				paging.setOffset(1);
				
				GetTransactionListForCustomerRequest getRequest = new GetTransactionListForCustomerRequest();
				getRequest.setMerchantAuthentication(merchantAuthenticationType);
				getRequest.setCustomerProfileId(customerProfileId);
	
				getRequest.setPaging(paging);
	
				TransactionListSorting sorting = new TransactionListSorting();
				sorting.setOrderBy(TransactionListOrderFieldEnum.SUBMIT_TIME_UTC);
				sorting.setOrderDescending(true);
	
				getRequest.setSorting(sorting);
	
				GetTransactionListForCustomerController controller = new GetTransactionListForCustomerController(getRequest);
				GetTransactionListForCustomerController.setEnvironment(Environment.valueOf(siteConfig.getAuthorizeEnvironment()));
				controller.execute();
				if(controller.getErrorResponse() != null)
					throw new RuntimeException(controller.getResults().toString());
	
				SchoolPaymentEnUSGenApiServiceImpl paymentService = new SchoolPaymentEnUSGenApiServiceImpl(siteContextEnUS);
	
				List<Future> futures = new ArrayList<>();
	
				GetTransactionListResponse getResponse = controller.getApiResponse();
				if (getResponse != null) {
	
					if (getResponse.getMessages().getResultCode() == MessageTypeEnum.OK) {
						List<TransactionSummaryType> transactions = Optional.ofNullable(getResponse).map(GetTransactionListResponse::getTransactions).map(ArrayOfTransactionSummaryType::getTransaction).orElse(Arrays.asList());
						LOGGER.info(String.format("There are %s transactions for client %s to load. ", transactions.size(), customerProfileId));
						for(TransactionSummaryType transaction : transactions) {
							futures.add(
								futureAuthorizeNetPayment(merchantAuthenticationType, paymentService, schoolEnrollment.getSiteRequest_(), transaction, schoolEnrollment, b -> {
									if(b.succeeded()) {
										LOGGER.info(String.format("transaction %s loaded. ", transaction.getTransId()));
									} else {
										LOGGER.error(String.format("payment future for transaction %s failed. ", transaction.getTransId()), b.cause());
									}
								})
							);
						}
						CompositeFuture.all(futures).setHandler(b -> {
							if(b.succeeded()) {
								eventHandler.handle(Future.succeededFuture());
								promise.complete();
								LOGGER.info(String.format("transactions for customer %s loaded. ", customerProfileId));
							} else {
								LOGGER.error(String.format("transactions for customer %s failed. ", customerProfileId));
								eventHandler.handle(Future.failedFuture(b.cause()));
								promise.fail(b.cause());
							}
						});
					}
					else {
						LOGGER.error(String.format("transactions for customer %s failed. ", customerProfileId));
						eventHandler.handle(Future.failedFuture(getResponse.getMessages().getMessage().stream().findFirst().map(m -> m.getText()).orElse("")));
					}
				}
				return promise.future();
			} catch(Exception e) {
				eventHandler.handle(Future.failedFuture(e));
				return Future.failedFuture(e);
			}
		}
	}

	public Future<Void> futureAuthorizeNetPayment(MerchantAuthenticationType merchantAuthenticationType, SchoolPaymentEnUSGenApiServiceImpl paymentService, SiteRequestEnUS siteRequest, TransactionSummaryType transactionSummary, SchoolEnrollment schoolEnrollment, Handler<AsyncResult<Void>> a) {
		Promise<Void> promise = Promise.promise();
		try {
			SiteConfig siteConfig = siteRequest.getSiteConfig_();
			GetTransactionDetailsRequest getRequest = new GetTransactionDetailsRequest();
			getRequest.setMerchantAuthentication(merchantAuthenticationType);
			getRequest.setTransId(transactionSummary.getTransId());
			GetTransactionDetailsController controller = new GetTransactionDetailsController(getRequest );
			GetTransactionDetailsController.setEnvironment(Environment.valueOf(siteConfig.getAuthorizeEnvironment()));
			controller.execute();
			if(controller.getErrorResponse() != null)
				throw new RuntimeException(controller.getResults().toString());
			GetTransactionDetailsResponse getResponse = controller.getApiResponse();
			TransactionDetailsType transaction = getResponse.getTransaction();
			String orderDescription = Optional.ofNullable(transaction).map(TransactionDetailsType::getOrder).map(OrderExType::getDescription).orElse("");
			Matcher enrollmentKeyMatcher = Pattern.compile("^(\\d+) payment for ").matcher(orderDescription);
			Boolean enrollmentKeyFound = enrollmentKeyMatcher.find();
			Long enrollmentKey = null;

			if(enrollmentKeyFound) {
				String enrollmentKeyStr = enrollmentKeyMatcher.group(1);
				if(NumberUtils.isCreatable(enrollmentKeyStr)) {
					enrollmentKey = Long.parseLong(enrollmentKeyStr);
					if(schoolEnrollment == null) {
						SearchList<SchoolEnrollment> searchListEnrollment = new SearchList<SchoolEnrollment>();
						searchListEnrollment.setStore(true);
						searchListEnrollment.setQuery("*:*");
						searchListEnrollment.setC(SchoolEnrollment.class);
						searchListEnrollment.addFilterQuery("pk_indexed_long:" + enrollmentKey);
						searchListEnrollment.initDeepSearchList(siteRequest);
	
						if(searchListEnrollment.getList().size() == 1) {
							schoolEnrollment = searchListEnrollment.first();
						}
						else {
							enrollmentKey = null;
						}
					}
					else if(!enrollmentKey.equals(schoolEnrollment.getPk()))
						enrollmentKey = null;
				}
			}

			if(enrollmentKey == null) {
				promise.complete();
			}
			else {
				XMLGregorianCalendar submitTimeLocal = transactionSummary.getSubmitTimeLocal();
				LocalDate paymentDate = submitTimeLocal.toGregorianCalendar().getTime().toInstant().atZone(ZoneId.of(siteContext.getSiteConfig().getSiteZone())).toLocalDate();
	
				SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolPayment.class);
				searchList.addFilterQuery("transactionId_indexed_string:" + ClientUtils.escapeQueryChars(transactionSummary.getTransId()));
				searchList.initDeepSearchList(siteRequest);

				if(searchList.size() == 0) {
					SchoolPayment payment = new SchoolPayment();
					payment.setSiteRequest_(siteRequest);
					payment.setPaymentAmount(transactionSummary.getSettleAmount());
					payment.setPaymentDate(paymentDate);
					payment.setPaymentSystem(true);
					payment.setTransactionId(transactionSummary.getTransId());
					payment.setCustomerProfileId(Optional.ofNullable(transactionSummary.getProfile()).map(CustomerProfileIdType::getCustomerProfileId).orElse(null));
					payment.setTransactionStatus(transactionSummary.getTransactionStatus());
					payment.setPaymentBy(String.format("%s %s", transactionSummary.getFirstName(), transactionSummary.getLastName()).trim());
					payment.setEnrollmentKey(enrollmentKey);

					SiteRequestEnUS siteRequest2 = paymentService.generateSiteRequestEnUSForSchoolPayment(siteContext, null, JsonObject.mapFrom(payment));
					paymentService.postSchoolPaymentFuture(siteRequest2, false, c -> {
						SchoolPayment payment2 = c.result();
						if(c.succeeded()) {
							a.handle(Future.succeededFuture());
							promise.complete();
							LOGGER.info(String.format("payment %s created for transaction %s. ", payment2.getPk(), transactionSummary.getTransId()));
						} else {
							LOGGER.error("creating payment %s failed for transaction %s. ", c.cause());
							promise.fail(c.cause());
						}
					});
				}
				else {
					promise.complete();
				}
			} 

			return promise.future();
		} catch(Exception e) {
			a.handle(Future.failedFuture(e));
			return Future.failedFuture(e);
		}
	}
}
