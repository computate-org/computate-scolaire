package org.computate.scolaire.enUS.design;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import org.computate.scolaire.enUS.payment.SchoolPaymentEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.search.SearchList;

import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;
import net.authorize.Environment;
import net.authorize.api.contract.v1.ArrayOfTransactionSummaryType;
import net.authorize.api.contract.v1.CustomerProfileIdType;
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
import net.authorize.api.controller.GetTransactionDetailsController;
import net.authorize.api.controller.GetTransactionListForCustomerController;
import net.authorize.api.controller.base.ApiOperationBase;

/**
 * Translate: false
 * CanonicalName.frFR: org.computate.scolaire.frFR.design.DesignPageFrFRApiServiceImpl
 **/
public class PageDesignEnUSApiServiceImpl extends PageDesignEnUSGenApiServiceImpl {

	public PageDesignEnUSApiServiceImpl(SiteContextEnUS siteContext) {
		super(siteContext);
	}

	@Override public void aSearchPageDesignVar(String uri, String apiMethod, SearchList<PageDesign> searchList, String var, String value) {
		if ("/page".equals(uri) || "/pdf".equals(uri) || "/email".equals(uri)) {
			if("design".equals(var))
				searchList.addFilterQuery("pageDesignCompleteName_indexed_string:" + ClientUtils.escapeQueryChars(value));
		}
		super.aSearchPageDesignVar(uri, apiMethod, searchList, var, value);
	}
	@Override public void aSearchPageDesignFq(String uri, String apiMethod, SearchList<PageDesign> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if ("/page".equals(uri) || "/pdf".equals(uri) || "/email".equals(uri)) {
			// skip
		}
		else {
			super.aSearchPageDesignFq(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
		}
	}
	@Override
	public void aSearchPageDesignUri(String uri, String apiMethod, SearchList<PageDesign> searchList) {
		if ("/".equals(uri)) {
			searchList.addFilterQuery("pageDesignCompleteName_indexed_string:" + ClientUtils.escapeQueryChars("home page"));
		}
	}

	@Override
	public void paymentsentsearchpagePageDesign(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForPageDesign(siteContext, operationRequest);
		try {
			sqlPageDesign(siteRequest, a -> {
				if(a.succeeded()) {
					userPageDesign(siteRequest, b -> {
						if(b.succeeded()) {
							aSearchPageDesign(siteRequest, false, true, "/payment-sent", "PaymentSentSearchPage", c -> {
								if(c.succeeded()) {
									SearchList<PageDesign> listPageDesign = c.result();
									String enrollmentKeyStr = siteRequest.getRequestVars().get("enrollmentKey");
									SearchList<SchoolEnrollment> enrollmentList = new SearchList<SchoolEnrollment>();
									List<String> roles = Arrays.asList("SiteAdmin");

									if(enrollmentKeyStr != null && NumberUtils.isCreatable(enrollmentKeyStr)) {
										enrollmentList.setStore(true);
										enrollmentList.setQuery("*:*");
										enrollmentList.setC(SchoolEnrollment.class);
										enrollmentList.setSiteRequest_(siteRequest);
										enrollmentList.addFilterQuery("pk_indexed_long:" + enrollmentKeyStr);
										if(
												!CollectionUtils.containsAny(siteRequest.getUserResourceRoles(), roles)
												&& !CollectionUtils.containsAny(siteRequest.getUserRealmRoles(), roles)
												) {
											enrollmentList.addFilterQuery("sessionId_indexed_string:" + ClientUtils.escapeQueryChars(Optional.ofNullable(siteRequest.getSessionId()).orElse("-----"))
													+ " OR userKeys_indexed_longs:" + Optional.ofNullable(siteRequest.getUserKey()).orElse(0L));
										}
									}
									enrollmentList.initDeepSearchList(siteRequest);
									if(enrollmentList.size() == 0) {
										LOGGER.error(String.format("paymentsentsearchpagePageDesign could not find the enrollment. "));
										errorPageDesign(siteRequest, eventHandler, c);
									}
									else {
										SchoolEnrollment schoolEnrollment = enrollmentList.first();
										SiteConfig siteConfig = siteRequest.getSiteConfig_();
										MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
										String authorizeApiLoginId = siteConfig.getAuthorizeApiLoginId();
										String authorizeTransactionKey = siteConfig.getAuthorizeTransactionKey();
										merchantAuthenticationType.setName(authorizeApiLoginId);
										merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
										ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);
	
										futureAuthorizeNetEnrollmentPayments(merchantAuthenticationType, schoolEnrollment, d -> {
											if(d.succeeded()) {
												LOGGER.info("Creating payments for customer %s succeeded. ");

												paymentsentsearchpagePageDesignResponse(listPageDesign, e -> {
													if(d.succeeded()) {
														eventHandler.handle(Future.succeededFuture(e.result()));
														LOGGER.info(String.format("paymentsentsearchpagePageDesign succeeded. "));
													} else {
														LOGGER.error(String.format("paymentsentsearchpagePageDesign failed. ", e.cause()));
														errorPageDesign(siteRequest, eventHandler, e);
													}
												});
											} else {
												LOGGER.error(String.format("paymentsentsearchpagePageDesign failed. ", d.cause()));
												errorPageDesign(siteRequest, eventHandler, d);
											}
										});
									}
								} else {
									LOGGER.error(String.format("paymentsentsearchpagePageDesign failed. ", c.cause()));
									errorPageDesign(siteRequest, eventHandler, c);
								}
							});
						} else {
							LOGGER.error(String.format("paymentsentsearchpagePageDesign failed. ", b.cause()));
							errorPageDesign(siteRequest, eventHandler, b);
						}
					});
				} else {
					LOGGER.error(String.format("paymentsentsearchpagePageDesign failed. ", a.cause()));
					errorPageDesign(siteRequest, eventHandler, a);
				}
			});
		} catch(Exception ex) {
			LOGGER.error(String.format("paymentsentsearchpagePageDesign failed. ", ex));
			errorPageDesign(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}

	public Future<Void> futureAuthorizeNetEnrollmentPayments(MerchantAuthenticationType merchantAuthenticationType, SchoolEnrollment schoolEnrollment, Handler<AsyncResult<Void>> eventHandler) {
		SiteRequestEnUS siteRequest = schoolEnrollment.getSiteRequest_();
		SiteContextEnUS siteContextEnUS = siteRequest.getSiteContext_();
		SiteConfig siteConfig = siteRequest.getSiteConfig_();
		Promise<Void> promise = Promise.promise();

		if(schoolEnrollment.getCustomerProfileId() == null) {
			eventHandler.handle(Future.succeededFuture());
			promise.complete();
			return promise.future();
		}
		else {
			try {
				Paging paging = new Paging();
				paging.setLimit(1000);
				paging.setOffset(1);
				
				GetTransactionListForCustomerRequest getRequest = new GetTransactionListForCustomerRequest();
				getRequest.setMerchantAuthentication(merchantAuthenticationType);
				getRequest.setCustomerProfileId(schoolEnrollment.getCustomerProfileId());
	
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
						LOGGER.info(String.format("There are %s transactions for client %s to load. ", transactions.size(), schoolEnrollment.getCustomerProfileId()));
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
								LOGGER.info(String.format("transactions for customer %s loaded. ", schoolEnrollment.getCustomerProfileId()));
							} else {
								LOGGER.error(String.format("transactions for customer %s failed. ", schoolEnrollment.getCustomerProfileId()));
								eventHandler.handle(Future.failedFuture(b.cause()));
								promise.fail(b.cause());
							}
						});
					}
					else {
						LOGGER.error(String.format("transactions for customer %s failed. ", schoolEnrollment.getCustomerProfileId()));
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
			Matcher enrollmentKeyMatcher = Pattern.compile("(\\d+) payment for ").matcher(orderDescription);
			Boolean enrollmentKeyFound = enrollmentKeyMatcher.find();
			Long enrollmentKey = null;

			if(!enrollmentKeyFound) {
				String enrollmentKeyStr = enrollmentKeyMatcher.group(1);
				if(NumberUtils.isCreatable(enrollmentKeyStr)) {
					enrollmentKey = Long.parseLong(enrollmentKeyStr);
					if(enrollmentKey != schoolEnrollment.getPk())
						enrollmentKey = null;
				}
			}

			if(enrollmentKey == null) {
				promise.complete();
			}
			else {
				XMLGregorianCalendar submitTimeLocal = transactionSummary.getSubmitTimeLocal();
				LocalDate paymentDate = submitTimeLocal.toGregorianCalendar().getTime().toInstant().atZone(ZoneId.of(siteContext.getSiteConfig().getSiteZone())).toLocalDate();
	
				if(schoolEnrollment.getSeasonStartDate().minusMonths(3).compareTo(paymentDate) > 0) {
					LOGGER.info(String.format("Older payment on %s ignored. ", paymentDate));
					promise.complete();
				}
				else if(schoolEnrollment.getSeasonStartDate().minusMonths(3).compareTo(paymentDate) > 0) {
					LOGGER.info(String.format("Older payment on %s ignored. ", paymentDate));
					promise.complete();
				}
				else {
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
						payment.setEnrollmentKey(schoolEnrollment.getPk());
	
						payment.setEnrollmentKey(enrollmentKey);
						paymentService.postSchoolPayment(JsonObject.mapFrom(payment), siteRequest.getOperationRequest(), c -> {
							if(c.succeeded()) {
								a.handle(Future.succeededFuture());
								promise.complete();
								LOGGER.info(String.format("payment %s created for transaction %s. ", c.result().getPayload().toJsonObject().getString("pk"), transactionSummary.getTransId()));
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
			} 

			return promise.future();
		} catch(Exception e) {
			a.handle(Future.failedFuture(e));
			return Future.failedFuture(e);
		}
	}
}
