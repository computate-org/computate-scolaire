package org.computate.scolaire.enUS.user;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollmentEnUSApiServiceImpl;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import org.computate.scolaire.enUS.payment.SchoolPaymentEnUSGenApiServiceImpl;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.computate.scolaire.enUS.search.SearchList;

import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;
import net.authorize.Environment;
import net.authorize.api.contract.v1.CreateCustomerProfileRequest;
import net.authorize.api.contract.v1.CreateCustomerProfileResponse;
import net.authorize.api.contract.v1.CustomerProfileType;
import net.authorize.api.contract.v1.MerchantAuthenticationType;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.controller.CreateCustomerProfileController;
import net.authorize.api.controller.GetTransactionListForCustomerController;
import net.authorize.api.controller.base.ApiOperationBase;

/**
 * Traduire: false
 * CanonicalName.frFR: org.computate.scolaire.frFR.utilisateur.UtilisateurSiteFrFRApiServiceImpl
 **/
public class SiteUserEnUSApiServiceImpl extends SiteUserEnUSGenApiServiceImpl {

	public SiteUserEnUSApiServiceImpl(SiteContextEnUS siteContexte) {
		super(siteContexte);
	}

	@Override public Boolean userSiteUserDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		String sessionIdBefore = siteRequest.getSessionIdBefore();
		String sessionId = siteRequest.getSessionId();

		SearchList<SchoolEnrollment> enrollmentList = new SearchList<SchoolEnrollment>();
		if(sessionIdBefore != null) {
			enrollmentList.setStore(true);
			enrollmentList.setQuery("*:*");
			enrollmentList.setC(SchoolEnrollment.class);
			enrollmentList.setSiteRequest_(siteRequest);
			enrollmentList.addFilterQuery(
					"sessionId_indexed_string:" + ClientUtils.escapeQueryChars(sessionIdBefore) 
					+ " OR sessionId_indexed_string:" + ClientUtils.escapeQueryChars(sessionId)
					+ " OR enrollmentEmails_indexed_strings:" + ClientUtils.escapeQueryChars(StringUtils.lowerCase(jsonObject.getString(patch ? "setUserEmail" : "userEmail")))
					);
//			enrollmentList.addFilterQuery("!userId_indexed_string:[* TO *]");
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
					jsonObject.put("setCustomerProfileId" + schoolNumber, customerProfileId);
				else
					jsonObject.put("customerProfileId" + schoolNumber, customerProfileId);
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

	@Override public void searchpageSiteUserPageInit(SiteUserPage page, SearchList<SiteUser> listSiteUser) {
		if(listSiteUser.size() == 1)
			page.setEnrollments_(listSiteUser.first().getEnrollments_());
	}

	@Override
	public void searchpageSiteUser(OperationRequest operationRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		SiteRequestEnUS siteRequest = generateSiteRequestEnUSForSiteUser(siteContext, operationRequest);
		try {
			{
				userSiteUser(siteRequest, b -> {
					if(b.succeeded()) {
						aSearchSiteUser(siteRequest, false, true, "/user", "SearchPage", c -> {
							if(c.succeeded()) {
								SearchList<SiteUser> listSiteUser = c.result();

								if(listSiteUser.size() == 1) {
									SiteUser siteUser = listSiteUser.first();
									try {
										SchoolEnrollmentEnUSApiServiceImpl enrollmentService = new SchoolEnrollmentEnUSApiServiceImpl(siteContext);
										SearchList<SchoolEnrollment> enrollmentSearch = new SearchList<>();
										List<Future> futures = new ArrayList<>();

										enrollmentSearch.setStore(true);
										enrollmentSearch.setQuery("*:*");
										enrollmentSearch.setC(SchoolEnrollment.class);
										enrollmentSearch.setRows(1000);
										enrollmentSearch.addFilterQuery("userKeys_indexed_longs:" + siteUser.getPk());
								
										enrollmentSearch.addSort("seasonStartDate_indexed_date", ORDER.asc);
										enrollmentSearch.addSort("childCompleteNamePreferred_indexed_int", ORDER.asc);
										
										enrollmentSearch.initDeepForClass(siteRequest);

										List<SchoolEnrollment> enrollments = enrollmentSearch.getList();
										siteUser.setEnrollments_(enrollments);

										enrollments.forEach(schoolEnrollment -> {
											futures.add(

												enrollmentService.enrollmentChargesFuture(schoolEnrollment, d -> {
													if(d.succeeded()) {
														enrollmentService.authorizeNetEnrollmentPaymentsFuture(schoolEnrollment, e -> {
															if(e.succeeded()) {
																LOGGER.info("Creating payments for customer %s succeeded. ");
																List<Future> futures2 = new ArrayList<>();
										
																SearchList<SchoolPayment> searchList2 = new SearchList<SchoolPayment>();
																searchList2.setStore(true);
																searchList2.setQuery("*:*");
																searchList2.setC(SchoolPayment.class);
																searchList2.addFilterQuery("enrollmentKey_indexed_long:" + schoolEnrollment.getPk());
																searchList2.setRows(100);
																searchList2.initDeepSearchList(siteRequest);
										
																for(SchoolPayment o2 : searchList2.getList()) {
																	SchoolPaymentEnUSGenApiServiceImpl service = new SchoolPaymentEnUSGenApiServiceImpl(siteRequest.getSiteContext_());
																	SiteRequestEnUS siteRequest2 = enrollmentService.generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), new JsonObject());
																	ApiRequest apiRequest2 = new ApiRequest();
																	apiRequest2.setRows(1);
																	apiRequest2.setNumFound(1l);
																	apiRequest2.setNumPATCH(0L);
																	apiRequest2.initDeepApiRequest(siteRequest2);
																	siteRequest2.setApiRequest_(apiRequest2);
																	siteRequest2.getVertx().eventBus().publish("websocketSchoolPayment", JsonObject.mapFrom(apiRequest2).toString());
										
																	o2.setSiteRequest_(siteRequest2);
																	futures2.add(
																		service.patchSchoolPaymentFuture(o2, false, a -> {
																			if(a.succeeded()) {
																			} else {
																				LOGGER.info(String.format("SchoolPayment %s failed. ", o2.getPk()));
																				eventHandler.handle(Future.failedFuture(a.cause()));
																			}
																		})
																	);
																}
				
																CompositeFuture.all(futures2).setHandler(f -> {
																	if(f.succeeded()) {
																		SiteRequestEnUS siteRequest2 = enrollmentService.generateSiteRequestEnUSForSchoolEnrollment(siteContext, siteRequest.getOperationRequest(), new JsonObject());
																		ApiRequest apiRequest2 = new ApiRequest();
																		apiRequest2.setRows(1);
																		apiRequest2.setNumFound(1l);
																		apiRequest2.setNumPATCH(0L);
																		apiRequest2.initDeepApiRequest(siteRequest2);
																		siteRequest2.setApiRequest_(apiRequest2);
																		siteRequest2.getVertx().eventBus().publish("websocketSchoolEnrollment", JsonObject.mapFrom(apiRequest2).toString());
											
																		schoolEnrollment.setSiteRequest_(siteRequest2);
						
																		enrollmentService.patchSchoolEnrollmentFuture(schoolEnrollment, false, g -> {
																			if(g.succeeded()) {
																				LOGGER.info("Refreshing enrollment succeeded. ");
																				if(e.succeeded()) {
																				} else {
																					LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", e.cause()));
																					errorSiteUser(siteRequest, eventHandler, e);
																				}
																			} else {
																				LOGGER.error("Refreshing enrollment succeeded. ", g.cause());
																				errorSiteUser(siteRequest, eventHandler, g);
																			}
																		});
																	} else {
																		LOGGER.error("Refresh relations failed. ", f.cause());
																		errorSiteUser(siteRequest, eventHandler, f);
																	}
																});
															} else {
																LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", e.cause()));
																errorSiteUser(siteRequest, eventHandler, e);
															}
														});
													} else {
														LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", d.cause()));
														errorSiteUser(siteRequest, eventHandler, d);
													}
												})
											);
										});
										CompositeFuture.all(futures).setHandler( a -> {
											if(a.succeeded()) {
												searchpageSiteUserResponse(listSiteUser, d -> {
													if(d.succeeded()) {
														eventHandler.handle(Future.succeededFuture(d.result()));
														LOGGER.info(String.format("searchpageSiteUser succeeded. "));
													} else {
														LOGGER.error(String.format("searchpageSiteUser failed. ", d.cause()));
														errorSiteUser(siteRequest, eventHandler, d);
													}
												});
											} else {
												LOGGER.error(String.format("listPATCHSiteUser failed. ", a.cause()));
												errorSiteUser(listSiteUser.getSiteRequest_(), eventHandler, a);
											}
										});
									} catch(Exception e) {
										LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", e));
										errorSiteUser(siteRequest, eventHandler, Future.failedFuture(e));
									}
								}
								else {
									searchpageSiteUserResponse(listSiteUser, d -> {
										if(d.succeeded()) {
											eventHandler.handle(Future.succeededFuture(d.result()));
											LOGGER.info(String.format("searchpageSiteUser succeeded. "));
										} else {
											LOGGER.error(String.format("searchpageSiteUser failed. ", d.cause()));
											errorSiteUser(siteRequest, eventHandler, d);
										}
									});
								}
							} else {
								LOGGER.error(String.format("searchpageSiteUser failed. ", c.cause()));
								errorSiteUser(siteRequest, eventHandler, c);
							}
						});
					} else {
						LOGGER.error(String.format("searchpageSiteUser failed. ", b.cause()));
						errorSiteUser(siteRequest, eventHandler, b);
					}
				});
			}
		} catch(Exception ex) {
			LOGGER.error(String.format("searchpageSiteUser failed. ", ex));
			errorSiteUser(siteRequest, eventHandler, Future.failedFuture(ex));
		}
	}
}
