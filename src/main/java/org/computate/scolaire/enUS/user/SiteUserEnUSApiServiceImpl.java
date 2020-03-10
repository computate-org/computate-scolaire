package org.computate.scolaire.enUS.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.search.SearchList;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
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
 **/
public class SiteUserEnUSApiServiceImpl extends SiteUserEnUSGenApiServiceImpl {

	public SiteUserEnUSApiServiceImpl(SiteContextEnUS siteContexte) {
		super(siteContexte);
	}

	@Override
	public void aSearchSiteUser(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String classApiUriMethod,
			Handler<AsyncResult<SearchList<SiteUser>>> eventHandler) {
		SearchList<SiteUser> listSearch = new SearchList<SiteUser>();
		SiteUser siteUser = siteRequest.getSiteUser();
		if(siteUser != null) {
			listSearch.getList().add(siteUser);
		}
		listSearch.setQuery(null);
		listSearch.setC(SiteUser.class);
		listSearch.initDeepForClass(siteRequest);
		eventHandler.handle(Future.succeededFuture(listSearch));
	}

	@Override public Boolean userSiteUserDefine(SiteRequestEnUS siteRequest, JsonObject jsonObject, Boolean patch) {
		String customerProfileId = jsonObject.getString("customerProfileId");
		if(customerProfileId == null) {
			SiteConfig siteConfig = siteRequest.getSiteConfig_();
			MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
			merchantAuthenticationType.setName(siteConfig.getAuthorizeApiLoginId());
			merchantAuthenticationType.setTransactionKey(siteConfig.getAuthorizeTransactionKey());
			ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);
			
			CreateCustomerProfileRequest createCustomerProfileRequest = new CreateCustomerProfileRequest();
			createCustomerProfileRequest.setMerchantAuthentication(merchantAuthenticationType);
			CustomerProfileType profile = new CustomerProfileType();
			profile.setEmail(jsonObject.getString("email"));
			profile.setDescription(jsonObject.getString("userId"));
			profile.setMerchantCustomerId(jsonObject.getString("userCompleteName"));
			createCustomerProfileRequest.setProfile(profile);
	
			CreateCustomerProfileController controller = new CreateCustomerProfileController(createCustomerProfileRequest);
			GetTransactionListForCustomerController.setEnvironment(Environment.PRODUCTION);
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
					throw new RuntimeException(response.getMessages().getMessage().stream().findFirst().map(m -> String.format("%s %s", m.getCode(), m.getText())).orElse("CreateCustomerProfileRequest failed. "));
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
}
