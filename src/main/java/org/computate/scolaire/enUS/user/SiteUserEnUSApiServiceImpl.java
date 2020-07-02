package org.computate.scolaire.enUS.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.solr.client.solrj.util.ClientUtils;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.search.SearchList;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
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
}
