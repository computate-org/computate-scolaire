package org.computate.scolaire.enUS.enrollment;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import org.computate.scolaire.enUS.search.SearchList;

/**
 * Translate: false
 **/
public class EnrollmentPage extends EnrollmentPageGen<EnrollmentGenPage> {

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

				{ e("div").a("class", "w3-padding ").f();
					if(o.getPaymentsCurrent()) {
						e("div").a("class", "w3-panel w3-green ").f();
						sx("You are current with all payments. Thank you! ");
						g("div");
					}
					else {
						if(o.getPaymentsLate()) {
							e("div").a("class", "w3-panel w3-red ").f();
							sx(String.format("You are late on payments. "));
							g("div");
						}
						e("div").a("class", "w3-panel w3-blue ").f();
						sx(String.format("Please pay the upcoming charges of $%s by the payment date to avoid any late fees. ", o.getChargesNow()));
						g("div");
						writeMakePayment(o.getSchoolNumber(), o.getChargesNow(), schoolEnrollment.getPk(), schoolEnrollment.getChildCompleteNamePreferred());
					}
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile w3-padding ").f();
				e("div").a("class", "").f().sx("It may take authorize.net a minute to record your payment in the system. You can refresh your authorize.net payments here: ").g("div");
				{ e("div").a("class", "").f();
					e("a")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-blue-gray ")
						.a("href", "/refresh-enrollment/" + schoolEnrollment.getPk())
						.f().sx("Refresh payments for this enrollment")
					.g("a");
				} g("div");
			} g("div");
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile w3-padding ").f();
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
		else {
			{ e("div").a("class", "w3-cell-row ").f();
				o.htmChildKey("Page");
				o.htmGuardianKeys("Page");
			} g("div");
			{ e("div").a("class", "w3-cell-row ").f();
				o.htmMomKeys("Page");
				o.htmDadKeys("Page");
			} g("div");
			{ e("div").a("class", "w3-cell-row ").f();
				o.htmPaymentKeys("Page");
			} g("div");
		}
	}
}
