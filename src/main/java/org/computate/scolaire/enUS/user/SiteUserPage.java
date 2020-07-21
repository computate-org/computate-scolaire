package org.computate.scolaire.enUS.user;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.computate.scolaire.enUS.child.SchoolChild;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.design.PageDesign;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.year.SchoolYear;

import io.vertx.ext.web.api.OperationRequest;
import net.authorize.Environment;
import net.authorize.api.contract.v1.ArrayOfSetting;
import net.authorize.api.contract.v1.GetHostedProfilePageRequest;
import net.authorize.api.contract.v1.GetHostedProfilePageResponse;
import net.authorize.api.contract.v1.MerchantAuthenticationType;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.SettingType;
import net.authorize.api.controller.GetHostedProfilePageController;
import net.authorize.api.controller.GetTransactionListForCustomerController;
import net.authorize.api.controller.base.ApiOperationBase;

/**
 * Translate: false
 **/ 
public class SiteUserPage extends SiteUserPageGen<SiteUserGenPage> {

	@Override public void htmlFormPageSiteUser(SiteUser o) {
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			super.htmlFormPageSiteUser(o);
		}
		else {
			{ e("div").a("class", "w3-cell-row ").f();
				o.htmEnrollmentKeys("Page");
				o.htmPaymentKeys("Page");
			} g("div");
		}
	}

	@Override public void htmlBodyFormsSiteUserGenPage() {
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			super.htmlBodyFormsSiteUserGenPage();
		}
	}

	protected void _pageDesignSearch(SearchList<PageDesign> l) {
		l.setQuery("*:*");
		l.addFilterQuery("deleted_indexed_boolean:false");
		l.addFilterQuery("archived_indexed_boolean:false");
		l.addFilterQuery("designHidden_indexed_boolean:false");
		l.setC(PageDesign.class);
		l.setStore(true);
		l.addSort("pageDesignCompleteName_indexed_string", ORDER.asc);
		l.setRows(1000);

		List<String> roles = Arrays.asList("SiteAdmin");
		if(
				!CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), roles)
				&& !CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), roles)
				) {
			l.addFilterQuery("sessionId_indexed_string:" + ClientUtils.escapeQueryChars(Optional.ofNullable(siteRequest_.getSessionId()).orElse("-----")));
		}
	}

	protected void _pageDesigns(Wrap<List<PageDesign>> c) {
		c.o(pageDesignSearch.getList());
	}

	protected void _yearSearch(SearchList<SchoolYear> l) {
		l.setQuery("*:*");
		l.addFilterQuery("deleted_indexed_boolean:false");
		l.addFilterQuery("archived_indexed_boolean:false");
		l.addFilterQuery("yearStart_indexed_int:[" + LocalDate.now().plusMonths(1).minusYears(1).getYear() + " TO " + LocalDate.now().plusMonths(1).getYear() + "]");
		l.setC(SchoolYear.class);
		l.setStore(true);
		l.addSort("schoolName_indexed_string", ORDER.asc);
		l.addSort("schoolLocation_indexed_string", ORDER.asc);
		l.addSort("yearStart_indexed_int", ORDER.desc);
		l.setRows(10000);

		List<String> roles = Arrays.asList("SiteAdmin");
		if(
				!CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), roles)
				&& !CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), roles)
				) {
			l.addFilterQuery("sessionId_indexed_string:" + ClientUtils.escapeQueryChars(Optional.ofNullable(siteRequest_.getSessionId()).orElse("-----")));
		}
		l.addFilterQuery("archived_indexed_boolean:false");
		l.addFilterQuery("deleted_indexed_boolean:false");
	}

	protected void _years(Wrap<List<SchoolYear>> c) {
		Integer i = 0;
		Integer size = yearSearch.size();
		String schoolNameBefore = null;
		String schoolNameCurrent = null;
		String schoolLocationBefore = null;
		String schoolLocationCurrent = null;
		SchoolYear year = null;
		List<SchoolYear> yearYears = null;

		years = yearSearch.getList();
		c.o(years);
	
		if(years.size() > 0) {
			year = years.get(i);
			schoolNameCurrent = year.getSchoolName();
			schoolLocationCurrent = year.getSchoolLocation();
			while(i < size) {
				year = years.get(i);
				schoolNameCurrent = year.getSchoolName();
				schoolLocationCurrent = year.getSchoolLocation();
				if(!Objects.equals(schoolNameCurrent, schoolNameBefore) || !Objects.equals(schoolLocationCurrent, schoolLocationBefore)) {
					schoolNameBefore = year.getSchoolName();
					schoolLocationBefore = year.getSchoolLocation();
					yearYears = year.getYearYears();
					schoolYears.add(year);
				}
				if(yearYears != null)
					yearYears.add(year);
				i++;
			}
		}
	}

	protected void _schoolYears(List<SchoolYear> l) {
	}

	protected void _enrollmentSearch(SearchList<SchoolEnrollment> l) {
		if(siteRequest_.getUserRealmRoles().contains("SiteManager") || siteRequest_.getUserResourceRoles().contains("SiteManager")) {
			l.setStore(true);
			l.setQuery("*:*");
			l.setC(SchoolEnrollment.class);
			l.setRows(1000);
	
			List<String> roles = Arrays.asList("SiteManager");
			if(
					!CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), roles)
					&& !CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), roles)
					) {
				l.addFilterQuery(
					"sessionId_indexed_string:" + ClientUtils.escapeQueryChars(Optional.ofNullable(siteRequest_.getSessionId()).orElse("-----"))
							+ " OR userKeys_indexed_longs:" + Optional.ofNullable(siteRequest_.getUserKey()).orElse(0L)
				);
			}
	
			l.addSort("schoolName_indexed_string", ORDER.asc);
			l.addSort("schoolLocation_indexed_string", ORDER.asc);
			l.addSort("enrollmentApproved_indexed_date", ORDER.asc);
			l.addSort("yearStart_indexed_int", ORDER.desc);
			l.addSort("created_indexed_int", ORDER.desc);

			l.addFields("schoolKey_stored_long", "enrollmentApproved_stored_boolean", "yearKey_stored_long", "yearStart_stored_int", "yearEnd_stored_int", "childCompleteNamePreferred_stored_string", "pageUrlPk_stored_string");
			l.setRows(1000);

			l.addFilterQuery("yearStart_indexed_int:[" + LocalDate.now().plusMonths(1).minusYears(1).getYear() + " TO " + LocalDate.now().plusMonths(1).getYear() + "]");
	
			for(String var : siteRequest_.getRequestVars().keySet()) {
				String val = siteRequest_.getRequestVars().get(var);
				if(!"design".equals(var)) {
					String varIndexed = SchoolEnrollment.varIndexedSchoolEnrollment(var);
					if(varIndexed != null)
						l.addFilterQuery(varIndexed + ":" + ClientUtils.escapeQueryChars(val));
				}
			}
		}
	}

	protected void _enrollmentSchools(List<SchoolEnrollment> c) {
	}

	protected void _enrollmentApprovals(Wrap<List<SchoolEnrollment>> c) {
	}

	protected void _enrollmentYears(Wrap<List<SchoolEnrollment>> c) {
	}

	protected void _enrollmentSchool(Wrap<SchoolEnrollment> c) {
	}

	protected void _enrollmentApproval(Wrap<SchoolEnrollment> c) {
	}

	protected void _enrollmentYear(Wrap<SchoolEnrollment> c) {
	}

	protected void _enrollmentEnrollment(Wrap<SchoolEnrollment> c) {
	}

	protected void _enrollments(Wrap<List<SchoolEnrollment>> c) {
		Integer i = 0;
		Integer size = enrollmentSearch.size();
		Long schoolKeyBefore = null;
		Long schoolKeyCurrent = null;
		Boolean approvedBefore = null;
		Boolean approvedCurrent = null;
		Long yearKeyBefore = null;
		Long yearKeyCurrent = null;
		SchoolEnrollment enrollment = null;
		List<SchoolEnrollment> enrollmentEnrollments = null;
		Integer enrollmentNumber = null;

		enrollments = enrollmentSearch.getList();
		c.o(enrollments);
		if(size > 0) {
			enrollment = enrollments.get(i);
			schoolKeyCurrent = enrollment.getSchoolKey();
			approvedCurrent = enrollment.getEnrollmentApproved();
			yearKeyCurrent = enrollment.getYearKey();
			while(i < size) {
				enrollment = enrollments.get(i);
				schoolKeyCurrent = enrollment.getSchoolKey();
				approvedCurrent = enrollment.getEnrollmentApproved();
				yearKeyCurrent = enrollment.getYearKey();
				if(schoolKeyBefore == null || ObjectUtils.compare(schoolKeyCurrent, schoolKeyBefore) != 0) {
					schoolKeyBefore = enrollment.getSchoolKey();
					approvedBefore = null;
					yearKeyBefore = null;
					enrollmentApprovals = enrollment.getEnrollmentApprovals();
					enrollmentSchools.add(enrollment);
				}
				while(i < size) {
					enrollment = enrollments.get(i);
					schoolKeyCurrent = enrollment.getSchoolKey();
					approvedCurrent = enrollment.getEnrollmentApproved();
					yearKeyCurrent = enrollment.getYearKey();
					if(approvedBefore == null || ObjectUtils.compare(approvedCurrent, approvedBefore) != 0) {
						approvedBefore = enrollment.getEnrollmentApproved();
						yearKeyBefore = null;
						enrollmentYears = enrollment.getEnrollmentYears();
						enrollmentApprovals.add(enrollment);
					}
					while(i < size) {
						enrollment = enrollments.get(i);
						schoolKeyCurrent = enrollment.getSchoolKey();
						approvedCurrent = enrollment.getEnrollmentApproved();
						yearKeyCurrent = enrollment.getYearKey();
						if(yearKeyBefore == null || ObjectUtils.compare(yearKeyCurrent, yearKeyBefore) != 0) {
							yearKeyBefore = enrollment.getYearKey();
							enrollmentEnrollments = enrollment.getEnrollmentEnrollments();
							enrollmentYears.add(enrollment);
							enrollmentNumber = 1;
						}
						enrollment.setEnrollmentKey(enrollment.getPk());
						enrollment.setEnrollmentNumber(enrollmentNumber);
						enrollmentEnrollments.add(enrollment);
						enrollmentNumber++;
						i++;
						if((i + 1) > size)
							break;
						enrollment = enrollments.get(i);
						schoolKeyCurrent = enrollment.getSchoolKey();
						approvedCurrent = enrollment.getEnrollmentApproved();
						yearKeyCurrent = enrollment.getYearKey();
						if(ObjectUtils.compare(approvedCurrent, approvedBefore) != 0)
							break;
						if(ObjectUtils.compare(schoolKeyCurrent, schoolKeyBefore) != 0)
							break;
					}
					enrollment.setEnrollmentKey(enrollment.getPk());
					enrollment.setEnrollmentNumber(enrollmentNumber);
					if(ObjectUtils.compare(schoolKeyCurrent, schoolKeyBefore) != 0)
						break;
				}
				enrollment.setEnrollmentKey(enrollment.getPk());
				enrollment.setEnrollmentNumber(enrollmentNumber);
			}
		}
	}

	protected void _enrollments_(Wrap<List<SchoolEnrollment>> c) {
	}

	@Override public void htmlBodySiteUserGenPage() {
		SiteConfig siteConfig = siteRequest_.getSiteConfig_();

		writeSchoolEnrollments();
		writeSchoolReports();

		if(enrollments_ != null) {
			e("h3").a("class", "w3-block w3-gray w3-padding w3-center ").f().sx("Enrollments and account info").g("h3");
			{ e("div").a("class", "w3-margin w3-row ").f();
				for(SchoolEnrollment enrollment : enrollments_) {
					{ e("div").a("class", "w3-half w3-card ").f();
						{ e("div").a("class", "w3-header w3-padding ").f();
							{ e("div").a("class", "w3-block w3-purple w3-padding w3-center ").f();
								e("a").a("href", enrollment.getPageUrlPk()).f().sx(enrollment.getEnrollmentCompleteName()).g("a");
							} g("div");
						} g("div");
						{ e("table").a("class", "w3-padding w3-table ").f();
							{ e("tr").a("class", "").f();
								{ e("td").a("class", "w3-cell font-weight-bold ").f();
									e("span").f().sx("Last payment").g("span");
								} g("td");
								{ e("td").a("class", "w3-cell w3-left ").f();
									e("div").f().sx(enrollment.getPaymentLastStr()).g("div");
								} g("td");
							} g("tr");
							{ e("tr").a("class", "").f();
								{ e("td").a("class", "w3-cell font-weight-bold ").f();
									e("span").f().sx("Balance").g("span");
								} g("td");
								{ e("td").a("class", "w3-cell w3-left ").f();
									e("div").f().sx("$", enrollment.getChargesNow()).g("div");
									if(enrollment.getPaymentsCurrent()) {
										e("div").a("class", "w3-text-green ").f();
										sx("You are current with all payments. Thank you! ");
										g("div");
									}
									else {
										{ e("div").a("class", "w3-text-green ").f();
											if(enrollment.getPaymentsLate()) {
												e("span").a("class", "w3-text-red ").f();
												sx(String.format("You are late on payments for $%s. ", enrollment.getPaymentsLateAmount()));
												g("span");
											}
											writeMakePayment(enrollment.getSchoolNumber(), enrollment.getChargesNow(), enrollment.getPk(), enrollment.getChildCompleteNamePreferred());
										} g("div");
									}
								} g("td");
							} g("tr");
							{ e("tr").a("class", "").f();
								{ e("td").a("class", "w3-cell font-weight-bold ").f();
									e("span").f().sx("Credit card").g("span");
								} g("td");
								{ e("td").a("class", "w3-cell w3-left ").f();
									writeConfigurePayments(enrollment.getSchoolNumber());
								} g("td");
							} g("tr");
						} g("table");
					} g("div");
				}
			} g("div");
		}

		{ e("div").a("class", "w3-margin-top ").f();
			e("h3").a("class", "w3-block w3-gray w3-padding w3-center ").f().sx("Manage username and password").g("h3");
			{ e("div").a("class", "w3-margin ").f();
				e("a").a("target", "_blank").a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ").a("href", siteConfig.getAuthUrl() + "/realms/", siteConfig.getAuthRealm(), "/account").f().sx("Manage user profile").g("a");
			} g("div");
		} g("div");

		super.htmlBodySiteUserGenPage();
	}

	public void writeConfigurePayments(Integer schoolNumber) {
		SiteConfig siteConfig = siteRequest_.getSiteConfig_();
		String authorizeApiLoginId = (String)siteConfig.obtainSiteConfig("authorizeApiLoginId" + schoolNumber);
		String authorizeTransactionKey = (String)siteConfig.obtainSiteConfig("authorizeTransactionKey" + schoolNumber);
		String schoolLocation = (String)siteConfig.obtainSiteConfig("schoolLocation" + schoolNumber);

		if(authorizeApiLoginId != null && authorizeTransactionKey != null) {
			SiteUser siteUser = siteRequest_.getSiteUser();

			if(siteUser != null) {
				String customerProfileId = (String)siteUser.obtainSiteUser("customerProfileId" + schoolNumber);

				if(customerProfileId != null) {
					MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
					merchantAuthenticationType.setName(authorizeApiLoginId);
					merchantAuthenticationType.setTransactionKey(authorizeTransactionKey);
					ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);
					
					GetHostedProfilePageRequest createCustomerProfileRequest = new GetHostedProfilePageRequest();
					createCustomerProfileRequest.setMerchantAuthentication(merchantAuthenticationType);
					createCustomerProfileRequest.setCustomerProfileId(customerProfileId);
					ArrayOfSetting settings = new ArrayOfSetting();
					{
						SettingType settingType = new SettingType();
						settingType.setSettingName("hostedProfileManageOptions");
						settingType.setSettingValue("showPayment");
						settings.getSetting().add(settingType);
					}
					{
						SettingType settingType = new SettingType();
						settingType.setSettingName("hostedProfileValidationMode");
						settingType.setSettingValue("testMode");
						settings.getSetting().add(settingType);
					}
					{
						SettingType settingType = new SettingType();
						settingType.setSettingName("hostedProfileBillingAddressOptions");
						settingType.setSettingValue("showNone");
						settings.getSetting().add(settingType);
					}
					{
						SettingType settingType = new SettingType();
						settingType.setSettingName("hostedProfileCardCodeRequired");
						settingType.setSettingValue("true");
						settings.getSetting().add(settingType);
					}
					createCustomerProfileRequest.setHostedProfileSettings(settings);
			
					GetHostedProfilePageController controller = new GetHostedProfilePageController(createCustomerProfileRequest);
					GetTransactionListForCustomerController.setEnvironment(Environment.valueOf(siteConfig.getAuthorizeEnvironment()));
					controller.execute();
					if(controller.getErrorResponse() != null)
						controller.toString();
		//				throw new RuntimeException(controller.getResults().toString());
					else {
						GetHostedProfilePageResponse profilePageResponse = controller.getApiResponse();
						if(MessageTypeEnum.ERROR.equals(profilePageResponse.getMessages().getResultCode())) {
	//						throw new RuntimeException(response.getMessages().getMessage().stream().findFirst().map(m -> String.format("%s %s", m.getCode(), m.getText())).orElse("GetHostedProfilePageRequest failed. "));
							System.err.println(profilePageResponse.getMessages().getMessage().stream().findFirst().map(m -> String.format("%s %s", m.getCode(), m.getText())).orElse("GetHostedProfilePageRequest failed. "));
						}
						else {
							{ e("div").a("class", "").f();
//								e("div").a("class", "").f().sx("Configure payment profile for the ", schoolLocation, " location").g("div");
								{ e("form").a("method", "post").a("target", "_blank").a("action", siteConfig.getAuthorizeUrl() + "/customer/manage").f();
									e("input").a("type", "hidden").a("name", "token").a("value", profilePageResponse.getToken()).fg();
									e("button").a("class", "w3-button w3-light-gray w3-text-purple text-decoration-underline ").a("type", "submit").f().sx("Edit credit card for ", schoolLocation, "").g("button");
								} g("form");
							} g("div");
						}
					}
				}
			}
		}
	}

	public void writeSchoolEnrollments() {
		if(siteRequest_.getUserRealmRoles().contains("SiteManager") || siteRequest_.getUserResourceRoles().contains("SiteManager")) {
			{ e("h1").f();
				{ e("a").a("href", "/user").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("school enrollments").g("span");
				} g("a");
			} g("h1");
			{ e("div").a("class", "w3-row w3-mobile ").f();
				for(SchoolEnrollment enrollmentSchool : enrollmentSchools) {
					{ e("div").a("class", "w3-half ").f();
						{ e("fieldset").a("class", "").f();
							e("legend").a("class", "font-weight-bold ").f().sx(enrollmentSchool.getSchoolLocation()).g("legend");
							{ e("table").a("class", "w3-table ").f();
								{ e("thead").a("class", "").f();
									{ e("tr").a("class", "").f();
										for(SchoolEnrollment enrollmentApproval : enrollmentSchool.getEnrollmentApprovals()) {
											if(enrollmentApproval.getEnrollmentApproved()) {
												for(SchoolEnrollment enrollmentYear : enrollmentApproval.getEnrollmentYears()) {
													e("td").a("class", "font-weight-bold ").f().sx(enrollmentYear.getYearStart(), "-", enrollmentYear.getYearEnd()).g("td");
												}
											}
											else {
												e("td").a("class", "font-weight-bold ").f().sx("New").g("td");
											}
										}
									} g("tr");
								} g("thead");
								{ e("tbody").a("class", "").f();
									{ e("tr").a("class", "").f();
										for(SchoolEnrollment enrollmentApproval : enrollmentSchool.getEnrollmentApprovals()) {
											if(enrollmentApproval.getEnrollmentApproved()) {
												for(SchoolEnrollment enrollmentYear : enrollmentApproval.getEnrollmentYears()) {
													{ e("td").a("style", "padding: 0; ").a("class", "").f();
														{ e("div").a("class", "").f();
															{ e("table").a("class", "w3-table ").f();
																for(SchoolEnrollment enrollmentEnrollment : enrollmentYear.getEnrollmentEnrollments()) {
																	{ e("tr").a("class", "").f();
																		{ e("td").a("style", "padding: 0; ").a("class", "").f();
																			{ e("a").a("href", enrollmentEnrollment.getPageUrlPk()).f();
																				e("span").a("class", "").f().sx(enrollmentEnrollment.getEnrollmentNumber(), ". ").g("span");
																			} g("a");
																		} g("td");
																		{ e("td").a("style", "padding: 0; ").a("class", "").f();
																			{ e("a").a("href", enrollmentEnrollment.getPageUrlPk()).f();
																				e("span").a("class", "").f().sx(enrollmentEnrollment.getChildCompleteNamePreferred()).g("span");
																			} g("a");
																		} g("td");
																	} g("tr");
																}
															} g("table");
														} g("div");
													} g("td");
												}
											}
											else {
												{ e("td").a("style", "padding: 0; ").a("class", "").f();
													{ e("div").a("class", "").f();
														{ e("table").a("class", "w3-table ").f();
															for(SchoolEnrollment enrollmentYear : enrollmentApproval.getEnrollmentYears()) {
																for(SchoolEnrollment enrollmentEnrollment : enrollmentYear.getEnrollmentEnrollments()) {
																	{ e("tr").a("class", "").f();
																		{ e("td").a("style", "padding: 0; ").a("class", "").f();
																			{ e("a").a("href", enrollmentEnrollment.getPageUrlPk()).f();
																				e("span").a("class", "").f().sx(enrollmentEnrollment.getEnrollmentNumber(), ". ").g("span");
																			} g("a");
																		} g("td");
																		{ e("td").a("style", "padding: 0; ").a("class", "").f();
																			{ e("a").a("href", enrollmentEnrollment.getPageUrlPk()).f();
																				e("span").a("class", "").f().sx(enrollmentEnrollment.getChildCompleteNamePreferred()).g("span");
																			} g("a");
																		} g("td");
																	} g("tr");
																}
															}
														} g("table");
													} g("div");
												} g("td");
											}
										}
									} g("tr");
								} g("tbody");
							} g("table");
						} g("fieldset");
					} g("div");
				}
			} g("div");
		}
	}

	public void writeSchoolReports() {
		if(siteRequest_.getUserRealmRoles().contains("SiteManager") || siteRequest_.getUserResourceRoles().contains("SiteManager")) {
			{ e("h1").f();
				{ e("a").a("href", "/user").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("school reports").g("span");
				} g("a");
			} g("h1");
			{ e("div").a("class", "").f();
				for(SchoolYear schoolYear : schoolYears) {
					{ e("div").a("class", "w3-cell-row w3-mobile w3-center w3-margin ").f();
						e("div").a("class", "w3-large font-weight-bold ").f().sx(schoolYear.getSchoolName()).g("div");
						e("div").a("class", "w3-xxlarge font-weight-bold ").f().sx(schoolYear.getSchoolLocation()).g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							for(SchoolYear yearYear : schoolYear.getYearYears()) {
								if(yearYear.getSchoolName() != null && yearYear.getSchoolLocation() != null) {
									{ e("div").a("class", "w3-cell w3-mobile ").f();
										for(PageDesign pageDesign : pageDesigns) {
											try {
												String url = "/pdf?var=design:" + URLEncoder.encode(pageDesign.getPageDesignCompleteName(), "UTF-8") + "&var=schoolName:" + URLEncoder.encode(yearYear.getSchoolName(), "UTF-8") + "&var=schoolLocation:" + URLEncoder.encode(yearYear.getSchoolLocation(), "UTF-8") + "&var=yearStart:" + yearYear.getYearStart();
												{ e("div").a("class", "w3-cell-row ").f();
													{ e("a").a("href", url).a("class", "").f();
														e("span").a("class", " ").f().sx(pageDesign.getPageDesignCompleteName(), " ", yearYear.getYearStart(), "-", yearYear.getYearEnd()).g("span");
													} g("a");
												} g("div");
											} catch (UnsupportedEncodingException e) {
												ExceptionUtils.rethrow(e);
											}
										}
									} g("div");
								}
							}
						} g("div");
					} g("div");
				}
			} g("div");
		}
	}
}
