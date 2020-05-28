package org.computate.scolaire.enUS.user;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.design.PageDesign;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.year.SchoolYear;

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
		l.setRows(1000);

		List<String> roles = Arrays.asList("SiteAdmin");
		if(
				!CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), roles)
				&& !CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), roles)
				) {
			l.addFilterQuery("sessionId_indexed_string:" + ClientUtils.escapeQueryChars(Optional.ofNullable(siteRequest_.getSessionId()).orElse("-----")));
		}
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

	@Override public void htmlBodySiteUserGenPage() {
		super.htmlBodySiteUserGenPage();

		writeConfigurePayments();
		writeSchoolReports();
	}

	public void writeConfigurePayments() {
		SiteConfig siteConfig = siteRequest_.getSiteConfig_();
		SiteUser siteUser = siteRequest_.getSiteUser();
		if(siteUser != null) {
			String customerProfileId = siteUser.getCustomerProfileId();
			if(customerProfileId != null) {
				MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
				merchantAuthenticationType.setName(siteConfig.getAuthorizeApiLoginId());
				merchantAuthenticationType.setTransactionKey(siteConfig.getAuthorizeTransactionKey());
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
						{ e("h1").f();
							{ e("a").a("href", "/user").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
								if(contextIconCssClasses != null)
									e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
								e("span").a("class", " ").f().sx("make payments").g("span");
							} g("a");
						} g("h1");
						{ e("div").a("class", "").f();
							e("div").a("class", "w3-large font-weight-bold ").f().sx("Manage username and password").g("div");
							e("a").a("target", "_blank").a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ").a("href", siteConfig.getAuthUrl() + "/realms/", siteConfig.getAuthRealm(), "/account").f().sx("Manage user profile").g("a");
						} g("div");
						{ e("div").a("class", "").f();
							e("div").a("class", "w3-large font-weight-bold ").f().sx("Configure payment profile with authorize.net").g("div");
							{ e("form").a("method", "post").a("target", "_blank").a("action", siteConfig.getAuthorizeUrl() + "/customer/manage").f();
								e("input").a("type", "hidden").a("name", "token").a("value", profilePageResponse.getToken()).fg();
								e("button").a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ").a("type", "submit").f().sx("Manage payment profile").g("button");
							} g("form");
						} g("div");
					}
				}
			}
		}
	}

	public void writeSchoolReports() {
		if(siteRequest_.getUserRealmRoles().contains("SiteAdmin") || siteRequest_.getUserResourceRoles().contains("SiteAdmin")) {
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
