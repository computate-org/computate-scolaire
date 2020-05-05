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
import java.util.stream.Collectors;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
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

import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationResponse;
import net.authorize.Environment;
import net.authorize.api.contract.v1.ArrayOfTransactionSummaryType;
import net.authorize.api.contract.v1.CustomerProfileIdType;
import net.authorize.api.contract.v1.GetTransactionDetailsResponse;
import net.authorize.api.contract.v1.GetTransactionListForCustomerRequest;
import net.authorize.api.contract.v1.GetTransactionListResponse;
import net.authorize.api.contract.v1.MerchantAuthenticationType;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.Paging;
import net.authorize.api.contract.v1.TransactionDetailsType;
import net.authorize.api.contract.v1.TransactionListOrderFieldEnum;
import net.authorize.api.contract.v1.TransactionListSorting;
import net.authorize.api.contract.v1.TransactionSummaryType;
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

	@Override public void listPATCHPaymentsSchoolEnrollment(ApiRequest apiRequest, SearchList<SchoolEnrollment> listSchoolEnrollment, String dt, Handler<AsyncResult<OperationResponse>> eventHandler) {
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
				apiRequest.setNumPATCH(apiRequest.getNumPATCH() + listSchoolEnrollment.size());
				response200PATCHPaymentsSchoolEnrollment(siteRequest, eventHandler);
			} else {
				errorSchoolEnrollment(listSchoolEnrollment.getSiteRequest_(), eventHandler, a);
			}
		});
	}

	@Override public Future<SchoolEnrollment> patchpaymentsSchoolEnrollmentFuture(SchoolEnrollment schoolEnrollment, Boolean inheritPk, Handler<AsyncResult<SchoolEnrollment>> eventHandler) {
		SiteRequestEnUS siteRequest = schoolEnrollment.getSiteRequest_();
		SiteContextEnUS siteContextEnUS = siteRequest.getSiteContext_();
		SiteConfig siteConfig = siteRequest.getSiteConfig_();
		Vertx vertx = siteRequest.getVertx();
		SchoolPaymentEnUSApiServiceImpl paymentService = new SchoolPaymentEnUSApiServiceImpl(siteContextEnUS);
		Promise<SchoolEnrollment> promise = Promise.promise();
		try {
			MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
			String authorizeApiLoginId = siteConfig.getAuthorizeApiLoginId();
			String authorizeTransactionKey = siteConfig.getAuthorizeTransactionKey();
			merchantAuthenticationType.setName(authorizeApiLoginId);
			merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
			ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);

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
			LocalDate chargeStartDate = sessionStartDate == null ? null : sessionStartDate.withDayOfMonth(25);
			LocalDate chargeEndDate = sessionStartDate == null ? null : sessionEndDate.minusMonths(1).withDayOfMonth(25);
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
					futureAuthorizeNetEnrollmentPayments(merchantAuthenticationType, schoolEnrollment, c -> {
						if(c.succeeded()) {
							LOGGER.info("Creating payments for customer %s succeeded. ");

							apiRequestSchoolEnrollment(schoolEnrollment);
			
							{
								SiteRequestEnUS siteRequest2 = paymentService.generateSiteRequestEnUSForSchoolPayment(siteContext, siteRequest.getOperationRequest(), new JsonObject());
	
								SearchList<SchoolPayment> searchList2 = new SearchList<SchoolPayment>();
								searchList2.setStore(true);
								searchList2.setQuery("*:*");
								searchList2.setC(SchoolPayment.class);
								searchList2.addFilterQuery("enrollmentKey_indexed_long:" + schoolEnrollment.getPk());
								searchList2.setRows(100);
								searchList2.initDeepSearchList(siteRequest2);
			
								for(SchoolPayment o2 : searchList2.getList()) {
									ApiRequest apiRequest2 = new ApiRequest();
									apiRequest2.setRows(1);
									apiRequest2.setNumFound(1l);
									apiRequest2.setNumPATCH(0L);
									apiRequest2.initDeepApiRequest(siteRequest2);
									siteRequest2.setApiRequest_(apiRequest2);
									siteRequest2.getVertx().eventBus().publish("websocketSchoolPayment", JsonObject.mapFrom(apiRequest2).toString());
									siteRequest2.setSqlConnection(siteRequest.getSqlConnection());
		
									futures.add(
											paymentService.patchSchoolPaymentFuture(o2, false, d -> {
											if(d.succeeded()) {
												LOGGER.info(String.format("SchoolPayment %s refreshed. ", o2.getPk()));
											} else {
												LOGGER.info(String.format("SchoolPayment %s failed. ", o2.getPk()));
												eventHandler.handle(Future.failedFuture(d.cause()));
											}
										})
									);
								}
							}
	
							CompositeFuture.all(futures).setHandler(d -> {
								if(d.succeeded()) {
									eventHandler.handle(Future.succeededFuture(schoolEnrollment));
									promise.complete();
									LOGGER.info("Refresh enrollment payments succeeded. ");
								} else {
									LOGGER.error("Refresh relations failed. ", d.cause());
									eventHandler.handle(Future.failedFuture(d.cause()));
								}
							});
						} else {
							LOGGER.error("Generating enrollment payments failed. ", c.cause());
							eventHandler.handle(Future.failedFuture(c.cause()));
							promise.fail(c.cause());
						}
					});
				} else {
					LOGGER.error(String.format("Creating charges has failed for enrollment %s. ", enrollmentKey), b.cause());
					eventHandler.handle(Future.failedFuture(b.cause()));
					promise.fail(b.cause());
				}
			});

			return promise.future();
		} catch(Exception e) {
			eventHandler.handle(Future.failedFuture(e));
			return Future.failedFuture(e);
		}

//		return super.patchpaymentsSchoolEnrollmentFuture(schoolEnrollment, eventHandler);
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
								futureAuthorizeNetPayment(paymentService, schoolEnrollment.getSiteRequest_(), transaction, schoolEnrollment, b -> {
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

	public Future<Void> futureAuthorizeNetPayment(SchoolPaymentEnUSGenApiServiceImpl paymentService, SiteRequestEnUS siteRequest, TransactionSummaryType transaction, SchoolEnrollment schoolEnrollment, Handler<AsyncResult<Void>> a) {
		Promise<Void> promise = Promise.promise();
		try {

			XMLGregorianCalendar submitTimeLocal = transaction.getSubmitTimeLocal();
			LocalDate paymentDate = submitTimeLocal.toGregorianCalendar().getTime().toInstant().atZone(ZoneId.of(siteContext.getSiteConfig().getSiteZone())).toLocalDate();
			SchoolPayment payment = new SchoolPayment();
			payment.setSiteRequest_(siteRequest);
			payment.setPaymentAmount(transaction.getSettleAmount());
			payment.setPaymentDate(paymentDate);
			payment.setPaymentSystem(true);
			payment.setTransactionId(transaction.getTransId());
			payment.setCustomerProfileId(Optional.ofNullable(transaction.getProfile()).map(CustomerProfileIdType::getCustomerProfileId).orElse(null));
			payment.setTransactionStatus(transaction.getTransactionStatus());
			payment.setPaymentBy(String.format("%s %s", transaction.getFirstName(), transaction.getLastName()).trim());
			payment.setEnrollmentKey(schoolEnrollment.getPk());

			if(schoolEnrollment.getSeasonStartDate().minusMonths(3).compareTo(paymentDate) > 0) {
				LOGGER.info(String.format("Older payment on %s ignored. ", paymentDate));
				promise.complete();
			}
			else {
				SearchList<SchoolPayment> searchList = new SearchList<SchoolPayment>();
				searchList.setStore(true);
				searchList.setQuery("*:*");
				searchList.setC(SchoolPayment.class);
				searchList.addFilterQuery("transactionId_indexed_string:" + ClientUtils.escapeQueryChars(transaction.getTransId()));
				searchList.initDeepSearchList(siteRequest);

				if(searchList.size() == 0) {
					String firstName = StringUtils.substringBefore(transaction.getFirstName(), " ");
					String lastName = transaction.getLastName();
					SearchList<SchoolEnrollment> searchListEnrollment = new SearchList<SchoolEnrollment>();
					searchListEnrollment.setStore(true);
					searchListEnrollment.setQuery("*:*");
					searchListEnrollment.setC(SchoolEnrollment.class);
					searchListEnrollment.addFilterQuery(
							"(childFirstName_indexed_string:" + ClientUtils.escapeQueryChars(firstName) 
							+ " OR childFirstNamePreferred_indexed_string:" + ClientUtils.escapeQueryChars(firstName) 
							+ " OR momFirstName_indexed_string:" + ClientUtils.escapeQueryChars(firstName) 
							+ " OR momFirstNamePreferred_indexed_string:" + ClientUtils.escapeQueryChars(firstName) 
							+ " OR dadFirstName_indexed_string:" + ClientUtils.escapeQueryChars(firstName) 
							+ " OR dadFirstNamePreferred_indexed_string:" + ClientUtils.escapeQueryChars(firstName) 
							+ ")");
					searchListEnrollment.addFilterQuery("childFamilyName_indexed_string:" + ClientUtils.escapeQueryChars(lastName));
					searchListEnrollment.initDeepSearchList(siteRequest);

					if(searchListEnrollment.getList().size() == 1) {
						Long enrollmentKey = searchListEnrollment.getList().get(0).getPk();
						payment.setEnrollmentKey(enrollmentKey);
					}
					paymentService.postSchoolPayment(JsonObject.mapFrom(payment), siteRequest.getOperationRequest(), c -> {
						if(c.succeeded()) {
							a.handle(Future.succeededFuture());
							promise.complete();
							LOGGER.info(String.format("payment %s created for transaction %s. ", c.result().getPayload().toJsonObject().getString("pk"), transaction.getTransId()));
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
