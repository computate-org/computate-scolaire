package org.computate.scolaire.enUS.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
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
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.impl.OAuth2TokenImpl;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.Transaction;
import io.vertx.sqlclient.Tuple;
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
		Boolean defineProfile3 = userSiteUserDefineProfile(3, siteRequest, jsonObject, patch);
		Boolean defineProfile4 = userSiteUserDefineProfile(4, siteRequest, jsonObject, patch);
		if(defineProfile1 || defineProfile2 || defineProfile3 || defineProfile4) 
			return true;
		else 
			return enrollmentList.size() > 0;
	}

	public Boolean userSiteUserDefineProfile(Integer schoolNumber, SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		SiteConfig siteConfig = siteRequest.getSiteConfig_();
		String authorizeApiLoginId = (String)siteConfig.obtainSiteConfig("authorizeApiLoginId" + schoolNumber);
		String authorizeTransactionKey = (String)siteConfig.obtainSiteConfig("authorizeTransactionKey" + schoolNumber);
		String authorizeEnvironment = siteConfig.getAuthorizeEnvironment();

		if(StringUtils.isNotBlank(authorizeEnvironment) && authorizeApiLoginId != null && authorizeTransactionKey != null) {
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
					profile.setMerchantCustomerId(StringUtils.substring(jsonObject.getString("setUserCompleteName"), 0, 20));
				}
				else {
					profile.setEmail(jsonObject.getString("userEmail"));
					profile.setDescription(jsonObject.getString("userId"));
					profile.setMerchantCustomerId(StringUtils.substring(jsonObject.getString("userCompleteName"), 0, 20));
				}
				createCustomerProfileRequest.setProfile(profile);
		
				CreateCustomerProfileController controller = new CreateCustomerProfileController(createCustomerProfileRequest);
				GetTransactionListForCustomerController.setEnvironment(Environment.valueOf(authorizeEnvironment));
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

	@Override
	public void userSiteUser(SiteRequestEnUS siteRequest, Handler<AsyncResult<OperationResponse>> eventHandler) {
		try {
			String userId = siteRequest.getUserId();
			if(userId == null) {
				eventHandler.handle(Future.succeededFuture());
			} else {
				sqlConnectionSiteUser(siteRequest, a -> {
					if(a.succeeded()) {
						sqlTransactionSiteUser(siteRequest, b -> {
							if(b.succeeded()) {
								Transaction tx = siteRequest.getTx();
								tx.preparedQuery("select c.pk, c.current, c.canonical_name, c.created, c.user_id, d1.value as customerprofileid1, d2.value as customerprofileid2, d3.value as customerprofileid3, d4.value as customerprofileid4 from c left outer join d d1 on c.pk=d1.pk_c and d1.path='customerProfileId1' left join d d2 on c.pk=d2.pk_c and d2.path='customerProfileId2' left join d d3 on c.pk=d3.pk_c and d3.path='customerProfileId3' left join d d4 on c.pk=d4.pk_c and d4.path='customerProfileId4' where canonical_name=$1 and user_id=$2;")
										.collecting(Collectors.toList())
										.execute(Tuple.of("org.computate.scolaire.enUS.user.SiteUser", userId)
										, selectCAsync
								-> {
									if(selectCAsync.succeeded()) {
										try {
											Row userValues = selectCAsync.result().value().stream().findFirst().orElse(null);
											SiteUserEnUSApiServiceImpl userService = new SiteUserEnUSApiServiceImpl(siteContext);
											if(userValues == null) {
												JsonObject userVertx = siteRequest.getOperationRequest().getUser();
												OAuth2TokenImpl token = new OAuth2TokenImpl(siteContext.getAuthProvider(), userVertx);
												JsonObject jsonPrincipal = token.accessToken();

												JsonObject jsonObject = new JsonObject();
												jsonObject.put("userName", jsonPrincipal.getString("preferred_username"));
												jsonObject.put("userFirstName", jsonPrincipal.getString("given_name"));
												jsonObject.put("userLastName", jsonPrincipal.getString("family_name"));
												jsonObject.put("userCompleteName", jsonPrincipal.getString("name"));
												jsonObject.put("userId", jsonPrincipal.getString("sub"));
												jsonObject.put("userEmail", jsonPrincipal.getString("email"));
												userSiteUserDefine(siteRequest, jsonObject, false);

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
																		siteRequest.setUserEmail(jsonPrincipal.getString("email"));
																		siteRequest.setUserId(jsonPrincipal.getString("sub"));
																		siteRequest.setUserKey(siteUser.getPk());
																		eventHandler.handle(Future.succeededFuture());
																	} else {
																		errorSiteUser(siteRequest, eventHandler, e);
																	}
																});
															} else {
																errorSiteUser(siteRequest, eventHandler, d);
															}
														});
													} else {
														errorSiteUser(siteRequest, eventHandler, c);
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
												OAuth2TokenImpl token = new OAuth2TokenImpl(siteContext.getAuthProvider(), userVertx);
												JsonObject jsonPrincipal = token.accessToken();

												JsonObject jsonObject = new JsonObject();
												jsonObject.put("setUserName", jsonPrincipal.getString("preferred_username"));
												jsonObject.put("setUserFirstName", jsonPrincipal.getString("given_name"));
												jsonObject.put("setUserLastName", jsonPrincipal.getString("family_name"));
												jsonObject.put("setUserCompleteName", jsonPrincipal.getString("name"));
												jsonObject.put("setCustomerProfileId1", userValues.getString("customerprofileid1"));
												jsonObject.put("setCustomerProfileId2", userValues.getString("customerprofileid2"));
												jsonObject.put("setCustomerProfileId3", userValues.getString("customerprofileid3"));
												jsonObject.put("setCustomerProfileId4", userValues.getString("customerprofileid4"));
												jsonObject.put("setUserId", jsonPrincipal.getString("sub"));
												jsonObject.put("setUserEmail", jsonPrincipal.getString("email"));
												Boolean define = userSiteUserDefine(siteRequest, jsonObject, true);
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
																	errorSiteUser(siteRequest, eventHandler, e);
																}
															});
														} else {
															errorSiteUser(siteRequest, eventHandler, d);
														}
													});
												} else {
													siteRequest.setSiteUser(siteUser1);
													siteRequest.setUserName(siteUser1.getUserName());
													siteRequest.setUserFirstName(siteUser1.getUserFirstName());
													siteRequest.setUserLastName(siteUser1.getUserLastName());
													siteRequest.setUserId(siteUser1.getUserId());
													siteRequest.setUserKey(siteUser1.getPk());
													sqlRollbackSiteUser(siteRequest, c -> {
														if(c.succeeded()) {
															eventHandler.handle(Future.succeededFuture());
														} else {
															eventHandler.handle(Future.failedFuture(c.cause()));
															errorSiteUser(siteRequest, eventHandler, c);
														}
													});
												}
											}
										} catch(Exception e) {
											LOGGER.error(String.format("userSiteUser failed. ", e));
											eventHandler.handle(Future.failedFuture(e));
										}
									} else {
										LOGGER.error(String.format("userSiteUser failed. ", selectCAsync.cause()));
										eventHandler.handle(Future.failedFuture(selectCAsync.cause()));
									}
								});
							} else {
								LOGGER.error(String.format("userSiteUser failed. ", b.cause()));
								eventHandler.handle(Future.failedFuture(b.cause()));
							}
						});
					} else {
						LOGGER.error(String.format("userSiteUser failed. ", a.cause()));
						eventHandler.handle(Future.failedFuture(a.cause()));
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error(String.format("userSiteUser failed. ", e));
			eventHandler.handle(Future.failedFuture(e));
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
						aSearchSiteUser(siteRequest, false, true, false, "/user", "SearchPage", c -> {
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
												Future.future(d -> {
//												enrollmentService.enrollmentChargesFuture(schoolEnrollment, d -> {
//													if(d.succeeded()) {
//														enrollmentService.authorizeNetEnrollmentPaymentsFuture(schoolEnrollment, e -> {
//															if(e.succeeded()) {
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
																				if(g.succeeded()) {
																				} else {
																					LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", g.cause()));
																					errorSiteUser(siteRequest, eventHandler, g);
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
//															} else {
//																LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", e.cause()));
//																errorSiteUser(siteRequest, eventHandler, e);
//															}
//														});
//													} else {
//														LOGGER.error(String.format("refreshsearchpageSchoolEnrollment failed. ", d.cause()));
//														errorSiteUser(siteRequest, eventHandler, d);
//													}
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

	@Override public Future<SiteUser> patchSiteUserFuture(SiteUser o, Boolean inheritPk, Handler<AsyncResult<SiteUser>> eventHandler) {
		Promise<SiteUser> promise = Promise.promise();
		SiteRequestEnUS siteRequest = o.getSiteRequest_();
		try {
			ApiRequest apiRequest = siteRequest.getApiRequest_();
			if(apiRequest != null && apiRequest.getNumFound() == 1L) {
				apiRequest.setOriginal(o);
				apiRequest.setPk(o.getPk());
			}
			JsonObject jsonObject = siteRequest.getJsonObject();
			if(CollectionUtils.containsAny(jsonObject.fieldNames(), Arrays.asList("setCustomerProfileId1", "setCustomerProfileId2", "setCustomerProfileId3", "setCustomerProfileId4", "setCustomerProfileId5", "setCustomerProfileId6", "setCustomerProfileId7", "setCustomerProfileId8", "setCustomerProfileId9", "setCustomerProfileId10"))) {
				throw new RuntimeException("Error processing request. ");
			}
			sqlConnectionSiteUser(siteRequest, a -> {
				if(a.succeeded()) {
					sqlTransactionSiteUser(siteRequest, b -> {
						if(b.succeeded()) {
							sqlPATCHSiteUser(o, inheritPk, c -> {
								if(c.succeeded()) {
									SiteUser siteUser = c.result();
									defineIndexSiteUser(siteUser, d -> {
										if(d.succeeded()) {
											if(apiRequest != null) {
												apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
												if(apiRequest.getNumFound() == 1L) {
													siteUser.apiRequestSiteUser();
												}
												siteRequest.getVertx().eventBus().publish("websocketSiteUser", JsonObject.mapFrom(apiRequest).toString());
											}
											eventHandler.handle(Future.succeededFuture(siteUser));
											promise.complete(siteUser);
										} else {
											LOGGER.error(String.format("patchSiteUserFuture failed. ", d.cause()));
											eventHandler.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("patchSiteUserFuture failed. ", c.cause()));
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("patchSiteUserFuture failed. ", b.cause()));
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("patchSiteUserFuture failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("patchSiteUserFuture failed. ", e));
			errorSiteUser(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}

	@Override public Future<SiteUser> postSiteUserFuture(SiteRequestEnUS siteRequest, Boolean inheritPk, Handler<AsyncResult<SiteUser>> eventHandler) {
		Promise<SiteUser> promise = Promise.promise();
		JsonObject jsonObject = siteRequest.getJsonObject();
		if(CollectionUtils.containsAny(jsonObject.fieldNames(), Arrays.asList("customerProfileId1", "customerProfileId2", "customerProfileId3", "customerProfileId4", "customerProfileId5", "customerProfileId6", "customerProfileId7", "customerProfileId8", "customerProfileId9", "customerProfileId10"))) {
			throw new RuntimeException("Error processing request. ");
		}
		try {
			sqlConnectionSiteUser(siteRequest, a -> {
				if(a.succeeded()) {
					sqlTransactionSiteUser(siteRequest, b -> {
						if(b.succeeded()) {
							createSiteUser(siteRequest, c -> {
								if(c.succeeded()) {
									SiteUser siteUser = c.result();
									sqlPOSTSiteUser(siteUser, inheritPk, d -> {
										if(d.succeeded()) {
											defineIndexSiteUser(siteUser, e -> {
												if(e.succeeded()) {
													ApiRequest apiRequest = siteRequest.getApiRequest_();
													if(apiRequest != null) {
														apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
														siteUser.apiRequestSiteUser();
														siteRequest.getVertx().eventBus().publish("websocketSiteUser", JsonObject.mapFrom(apiRequest).toString());
													}
													eventHandler.handle(Future.succeededFuture(siteUser));
													promise.complete(siteUser);
												} else {
													LOGGER.error(String.format("postSiteUserFuture failed. ", e.cause()));
													eventHandler.handle(Future.failedFuture(e.cause()));
												}
											});
										} else {
											LOGGER.error(String.format("postSiteUserFuture failed. ", d.cause()));
											eventHandler.handle(Future.failedFuture(d.cause()));
										}
									});
								} else {
									LOGGER.error(String.format("postSiteUserFuture failed. ", c.cause()));
									eventHandler.handle(Future.failedFuture(c.cause()));
								}
							});
						} else {
							LOGGER.error(String.format("postSiteUserFuture failed. ", b.cause()));
							eventHandler.handle(Future.failedFuture(b.cause()));
						}
					});
				} else {
					LOGGER.error(String.format("postSiteUserFuture failed. ", a.cause()));
					eventHandler.handle(Future.failedFuture(a.cause()));
				}
			});
		} catch(Exception e) {
			LOGGER.error(String.format("postSiteUserFuture failed. ", e));
			errorSiteUser(siteRequest, null, Future.failedFuture(e));
		}
		return promise.future();
	}
}
