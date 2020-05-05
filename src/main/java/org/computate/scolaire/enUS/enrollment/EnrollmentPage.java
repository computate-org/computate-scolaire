package org.computate.scolaire.enUS.enrollment;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.user.SiteUser;

import net.authorize.Environment;
import net.authorize.api.contract.v1.ArrayOfLineItem;
import net.authorize.api.contract.v1.ArrayOfSetting;
import net.authorize.api.contract.v1.CustomerProfilePaymentType;
import net.authorize.api.contract.v1.GetHostedPaymentPageRequest;
import net.authorize.api.contract.v1.GetHostedPaymentPageResponse;
import net.authorize.api.contract.v1.LineItemType;
import net.authorize.api.contract.v1.MerchantAuthenticationType;
import net.authorize.api.contract.v1.MessageTypeEnum;
import net.authorize.api.contract.v1.OrderType;
import net.authorize.api.contract.v1.SettingType;
import net.authorize.api.contract.v1.TransactionRequestType;
import net.authorize.api.contract.v1.TransactionTypeEnum;
import net.authorize.api.controller.GetHostedPaymentPageController;
import net.authorize.api.controller.base.ApiOperationBase;

/**
 * Translate: false
 **/
public class EnrollmentPage extends EnrollmentPageGen<EnrollmentGenPage> {

	protected void _listSchoolPayment(SearchList<SchoolPayment> searchList) {
		if(listSchoolEnrollment != null && listSchoolEnrollment.size() == 1) {
			SchoolEnrollment schoolEnrollment = listSchoolEnrollment.get(0);
			searchList.setStore(true);
			searchList.setQuery("*:*");
			searchList.setC(SchoolPayment.class);
			searchList.addFilterQuery("enrollmentKey_indexed_long:" + schoolEnrollment.getPk());
			searchList.add("json.facet", "{max_modified:'max(modified_indexed_date)'}");
			searchList.add("json.facet", "{terms_childCompleteNamePreferred:{terms:{field:childCompleteNamePreferred_indexed_string}}}");
			searchList.add("json.facet", "{sum_paymentAmount:'sum(paymentAmount_indexed_double)'}");
			searchList.add("json.facet", "{sum_chargeAmount:'sum(chargeAmount_indexed_double)'}");
			searchList.add("json.facet", "{sum_chargeAmountFuture:'sum(chargeAmountFuture_indexed_double)'}");
		}
	}

	public void writeMakePayment(BigDecimal amount) {
		SiteConfig siteConfig = siteRequest_.getSiteConfig_();
		SiteUser siteUser = siteRequest_.getSiteUser();
		if(siteUser != null) {
			String customerProfileId = siteUser.getCustomerProfileId();
			if(customerProfileId != null) {
				MerchantAuthenticationType merchantAuthenticationType = new MerchantAuthenticationType();
				merchantAuthenticationType.setName(siteConfig.getAuthorizeApiLoginId());
				merchantAuthenticationType.setTransactionKey(siteConfig.getAuthorizeTransactionKey());
				ApiOperationBase.setMerchantAuthentication(merchantAuthenticationType);
				
				GetHostedPaymentPageRequest hostedPaymentPageRequest = new GetHostedPaymentPageRequest();
				hostedPaymentPageRequest.setMerchantAuthentication(merchantAuthenticationType);

				ArrayOfSetting settings = new ArrayOfSetting();
				{
					SettingType settingType = new SettingType();
					String hostedPaymentReturnOptionsStr = "{ \"showReceipt\":false, \"url\": \"%s/enrollment/payment-sent/%s?var=transactionId:%s\", \"cancelUrl\": \"%s/%s\" }";
					String hostedPaymentReturnOptions = String.format(hostedPaymentReturnOptionsStr, siteConfig.getSiteBaseUrl(), schoolEnrollment.getPk(), siteConfig.getSiteBaseUrl(), siteRequest_.getUserKey());
					settingType.setSettingName("hostedPaymentReturnOptions");
					settingType.setSettingValue(hostedPaymentReturnOptions);
					settings.getSetting().add(settingType);
				}
//				{
//					SettingType settingType = new SettingType();
//					settingType.setSettingName("hostedProfileValidationMode");
//					settingType.setSettingValue("testMode");
//					settings.getSetting().add(settingType);
//				}
//				{
//					SettingType settingType = new SettingType();
//					settingType.setSettingName("hostedProfileBillingAddressOptions");
//					settingType.setSettingValue("showNone");
//					settings.getSetting().add(settingType);
//				}
//				{
//					SettingType settingType = new SettingType();
//					settingType.setSettingName("hostedProfileCardCodeRequired");
//					settingType.setSettingValue("true");
//					settings.getSetting().add(settingType);
//				}
				hostedPaymentPageRequest.setHostedPaymentSettings(settings);
				TransactionRequestType transactionRequest = new TransactionRequestType();
				// This is the default transaction type. 
				// When using AIM, if the x_type field is not sent to us, the type will default to AUTH_CAPTURE. 
				// Simple Checkout uses AUTH_CAPTURE only. 
				// The Virtual Terminal defaults to AUTH_CAPTURE unless you select a different transaction type.
				// With an AUTH_CAPTURE transaction, the process is completely automatic. 
				// The transaction is submitted to your processor for authorization and, 
				// if approved, is placed in your Unsettled Transactions with the status Captured Pending Settlement. 
				// The transaction will settle at your next batch. 
				// Settlement occurs every 24 hours, within 24 hours of your Transaction Cut-off Time.
				// See: https://support.authorize.net/s/article/What-Are-the-Transaction-Types-That-Can-Be-Submitted
				transactionRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
				transactionRequest.setAmount(amount);
				ArrayOfLineItem lineItems = new ArrayOfLineItem();
				LineItemType lineItem = new LineItemType();
				DateTimeFormatter fd = DateTimeFormatter.ofPattern("MMM yyyy", Locale.US);
				LocalDate now = LocalDate.now();
				LocalDate chargeEndDate = siteConfig.getPaymentNext();
//				lineItem.setName(String.format("Payment for ", fd.format(chargeEndDate)));
//				lineItem.setDescription(String.format("Payment for ", fd.format(chargeEndDate)));
//				lineItem.setItemId(schoolEnrollment.getPk().toString());
//				lineItem.setQuantity(new BigDecimal(1));
//				lineItems.getLineItem().add(lineItem);
//				transactionRequest.setLineItems(lineItems);
				CustomerProfilePaymentType profile = new CustomerProfilePaymentType();
				profile.setCustomerProfileId(siteUser.getCustomerProfileId());
				transactionRequest.setProfile(profile);
				OrderType order = new OrderType();
				order.setDescription(String.format("%s payment for %s", fd.format(chargeEndDate), schoolEnrollment.getChildCompleteNamePreferred()));
				transactionRequest.setOrder(order);
				hostedPaymentPageRequest.setTransactionRequest(transactionRequest);
		
				GetHostedPaymentPageController controller = new GetHostedPaymentPageController(hostedPaymentPageRequest);
				GetHostedPaymentPageController.setEnvironment(Environment.valueOf(siteConfig.getAuthorizeEnvironment()));
				controller.execute();
				if(controller.getErrorResponse() != null)
					throw new RuntimeException(controller.getResults().toString());
				else {
					GetHostedPaymentPageResponse response = controller.getApiResponse();
					if(MessageTypeEnum.ERROR.equals(response.getMessages().getResultCode())) {
//						throw new RuntimeException(response.getMessages().getMessage().stream().findFirst().map(m -> String.format("%s %s", m.getCode(), m.getText())).orElse("GetHostedPaymentPageRequest failed. "));
						System.err.println(response.getMessages().getMessage().stream().findFirst().map(m -> String.format("%s %s", m.getCode(), m.getText())).orElse("GetHostedPaymentPageRequest failed. "));
					}
					else {
						{ e("div").a("class", "").f();
							{ e("form").a("method", "post").a("target", "_blank").a("action", siteConfig.getAuthorizeUrl() + "/payment/payment").f();
								e("input").a("type", "hidden").a("name", "token").a("value", response.getToken()).fg();
								e("button").a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ").a("type", "submit").f().sx("Make a payment").g("button");
							} g("form");
							e("div").a("class", "").f().sx("Click here to make a payment with authorize.net. ").g("div");
						} g("div");
					}
				}
			}
		}
	}

	@Override public void htmlFormPageSchoolEnrollment(SchoolEnrollment o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile  ").f();
				{ e("div").a("class", "w3-padding ").f();
					e("a").a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ").a("href", "/page/main-enrollment-form?var=enrollmentKey:", o.getPk()).f().sx("View enrollment form").g("a");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile  ").f();
				{ e("div").a("class", "w3-padding ").f();
					e("a").a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
					.a("href", "/pdf/main-enrollment-form?var=enrollmentKey:", o.getPk(), "&var=emailDesignId:email-enrollment&var=emailSubject:Enrollment for ", urlEncode(schoolEnrollment.getChildCompleteNamePreferred()), "%20asks%20${schoolName}%20a%20question.&var=emailToAddress:' + encodeURIComponent($('#email-questions-email').val()) + '&var=emailToName:' + encodeURIComponent($('#email-questions-name').val()) + '&var=emailMessage:' + encodeURIComponent($('#email-questions-question')")
//					.a("onclick", "window.location.href = '/email/enrollment-sent?var=enrollmentKey:", o.getPk(), "&var=emailDesignId:email-enrollment&var=emailSubject:Enrollment for ' + encodeURIComponent($('#childCompleteName').val()) + 'at ", urlEncode(school_.getSchoolCompleteName()), "&var=emailToAddress:' + encodeURIComponent($('#email-questions-email').val()) + '&var=emailToName:' + encodeURIComponent($('#email-questions-name').val()) + '&var=emailMessage:' + encodeURIComponent($('#email-questions-question').val()); ")
					.f().sx("View enrollment PDF").g("a");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile  ").f();
				SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolPayment.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
				BigDecimal sum_paymentAmount = Optional.ofNullable((Double)facets.get("sum_paymentAmount")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2));
				BigDecimal sum_chargeAmount = Optional.ofNullable((Double)facets.get("sum_chargeAmount")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2));
				BigDecimal sum_chargeAmountFuture = Optional.ofNullable((Double)facets.get("sum_chargeAmountFuture")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2));

				{ e("div").a("class", "w3-padding ").f();
					if(sum_chargeAmount.subtract(sum_paymentAmount).compareTo(BigDecimal.ZERO) <= 0) {
						e("div").a("class", "w3-panel w3-green ").f();
						sx("You are current with all payments. Thank you! ");
						g("div");
					}
					else {
						if(sum_chargeAmount.subtract(sum_chargeAmountFuture).subtract(sum_paymentAmount).compareTo(BigDecimal.ZERO) > 0) {
							BigDecimal amount = sum_chargeAmount.subtract(sum_chargeAmountFuture).subtract(sum_paymentAmount);
							e("div").a("class", "w3-panel w3-red ").f();
							sx(String.format("You are late on payments for $%s. ", amount));
							g("div");
						}
						BigDecimal amount = sum_chargeAmount.subtract(sum_paymentAmount);
						e("div").a("class", "w3-panel w3-blue ").f();
						sx(String.format("Please pay the upcoming charges of $%s by the payment date to avoid any late fees. ", amount));
						g("div");
						writeMakePayment(amount);
					}
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile  ").f();
				e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-blue-gray ")
					.a("onclick", "patchpaymentsSchoolEnrollmentVals([{name:'fq', value:'pk:", schoolEnrollment.getPk(), "'}], {}); ")
					.f().sx("Refresh payments for this enrollment")
				.g("button");
			} g("div");
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile  ").f();
					{ e("div").a("class", "").f();
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-blue-gray ")
							.a("onclick", "postSchoolPaymentVals({enrollmentKey:'", schoolEnrollment.getPk(), "',chargeLateFee:true,chargeAmount:'5',paymentDescription:'Late pick-up/drop-off fee',paymentDate:'", LocalDate.now().toString(), "'}); ")
							.f().sx("Add a $5 late pick-up/drop-off fee")
						.g("button");
					} g("div");
					{ e("div").a("class", "").f();
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-blue-gray ")
							.a("onclick", "postSchoolPaymentVals({enrollmentKey:'", schoolEnrollment.getPk(), "',chargeLateFee:true,chargeAmount:'10',paymentDescription:'Late pick-up/drop-off fee',paymentDate:'", LocalDate.now().toString(), "'}); ")
							.f().sx("Add a $10 late pick-up/drop-off fee")
						.g("button");
					} g("div");
					{ e("div").a("class", "").f();
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-blue-gray ")
							.a("onclick", "postSchoolPaymentVals({enrollmentKey:'", schoolEnrollment.getPk(), "',chargeLateFee:true,chargeAmount:'15',paymentDescription:'Late pick-up/drop-off fee',paymentDate:'", LocalDate.now().toString(), "'}); ")
							.f().sx("Add a $15 late pick-up/drop-off fee")
						.g("button");
					} g("div");
					{ e("div").a("class", "").f();
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-blue-gray ")
							.a("onclick", "postSchoolPaymentVals({enrollmentKey:'", schoolEnrollment.getPk(), "',chargeLateFee:true,chargeAmount:'20',paymentDescription:'Late pick-up/drop-off fee',paymentDate:'", LocalDate.now().toString(), "'}); ")
							.f().sx("Add a $20 late pick-up/drop-off fee")
						.g("button");
					} g("div");
				} g("div");
			}
		} g("div");
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			super.htmlFormPageSchoolEnrollment(o);
		}
	}
}
