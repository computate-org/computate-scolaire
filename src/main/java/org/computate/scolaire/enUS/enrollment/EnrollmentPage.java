package org.computate.scolaire.enUS.enrollment;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.computate.scolaire.enUS.school.School;

/**
 * Translate: false
 **/
public class EnrollmentPage extends EnrollmentPageGen<EnrollmentGenPage> {

	@Override public void htmlBodyFormsEnrollmentGenPage() {

		LocalDate now = LocalDate.now();
		LocalDate yearEndDate = now.with(TemporalAdjusters.firstDayOfMonth()).withMonth(6);
		yearEndDate = now.isBefore(yearEndDate) ? yearEndDate : yearEndDate.plusYears(1);

		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();
			if(schools != null) {
				for(School school : schools) {
					{ e("a")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
						.a("href", "/page/main-enrollment-form?var=schoolLocation:", school.getSchoolLocation(), "&var=yearEnd:", yearEndDate.getYear())
						.f();
						e("i").a("class", "fas fa-file-plus ").f().g("i");
						sx("Submit ", school.getSchoolLocation(), " Enrollment Form Online");
					} g("a");
				}
			}
			g("div");

			e("div").a("class", "w3-margin-top ").f();
			if(listSchoolEnrollment != null && listSchoolEnrollment.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
						.a("id", "refreshThisEnrollmentGenPage")
						.a("onclick", "patchSchoolEnrollmentVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisEnrollmentGenPage')); }, function() { addError($('#refreshThisEnrollmentGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("refresh this enrollment");
				} g("button");
			}

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
				.a("onclick", "$('#putimportSchoolEnrollmentModal').show(); ")
				.f();
				e("i").a("class", "fas fa-file-import ").f().g("i");
				sx("Import enrollments");
			} g("button");
			{ e("div").a("id", "putimportSchoolEnrollmentModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-blue-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putimportSchoolEnrollmentModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Import enrollments").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolEnrollment o = new SchoolEnrollment();
							o.setSiteRequest_(siteRequest_);

							// Form PUT
							{ e("div").a("id", "putimportSchoolEnrollmentForm").f();
								htmlFormPUTImportSchoolEnrollment(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-blue-gray ")
								.a("onclick", "putimportSchoolEnrollment($('#putimportSchoolEnrollmentForm')); ")
								.f().sx("Import enrollments")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
				.a("onclick", "$('#putmergeSchoolEnrollmentModal').show(); ")
				.f();
				e("i").a("class", "fas fa-code-merge ").f().g("i");
				sx("Merge enrollments");
			} g("button");
			{ e("div").a("id", "putmergeSchoolEnrollmentModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-blue-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putmergeSchoolEnrollmentModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Merge enrollments").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolEnrollment o = new SchoolEnrollment();
							o.setSiteRequest_(siteRequest_);

							// Form PUT
							{ e("div").a("id", "putmergeSchoolEnrollmentForm").f();
								htmlFormPUTMergeSchoolEnrollment(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-blue-gray ")
								.a("onclick", "putmergeSchoolEnrollment($('#putmergeSchoolEnrollmentForm')); ")
								.f().sx("Merge enrollments")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
				.a("onclick", "$('#putcopySchoolEnrollmentModal').show(); ")
				.f();
				e("i").a("class", "fas fa-copy ").f().g("i");
				sx("Duplicate enrollments");
			} g("button");
			{ e("div").a("id", "putcopySchoolEnrollmentModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-blue-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putcopySchoolEnrollmentModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Duplicate enrollments").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolEnrollment o = new SchoolEnrollment();
							o.setSiteRequest_(siteRequest_);

							// Form PUT
							{ e("div").a("id", "putcopySchoolEnrollmentForm").f();
								htmlFormPUTCopySchoolEnrollment(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-blue-gray ")
								.a("onclick", "putcopySchoolEnrollment($('#putcopySchoolEnrollmentForm'), ", schoolEnrollment_ == null ? "null" : schoolEnrollment_.getPk(), "); ")
								.f().sx("Duplicate enrollments")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
				.a("onclick", "$('#patchSchoolEnrollmentModal').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modify enrollments");
			} g("button");
			{ e("div").a("id", "patchSchoolEnrollmentModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-blue-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolEnrollmentModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modify enrollments").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "patchSchoolEnrollmentFormValues").f();
							SchoolEnrollment o = new SchoolEnrollment();
							o.setSiteRequest_(siteRequest_);

								htmlFormPATCHSchoolEnrollment(o);
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-blue-gray ")
								.a("onclick", "patchSchoolEnrollment(null, $('#patchSchoolEnrollmentFormValues'), ", Optional.ofNullable(schoolEnrollment_).map(SchoolEnrollment::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modify enrollments")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
		htmlSuggestedEnrollmentGenPage(this, null, listSchoolEnrollment);
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
					.a("href", "/pdf/main-enrollment-form?var=enrollmentKey:", o.getPk(), "&var=emailDesignId:email-enrollment&var=emailSubject:Enrollment for ", urlEncode(schoolEnrollment_.getChildCompleteNamePreferred()), "%20asks%20${schoolName}%20a%20question.&var=emailToAddress:' + encodeURIComponent($('#email-questions-email').val()) + '&var=emailToName:' + encodeURIComponent($('#email-questions-name').val()) + '&var=emailMessage:' + encodeURIComponent($('#email-questions-question')")
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
						writeMakePayment(o.getSchoolNumber(), o.getChargesNow(), schoolEnrollment_.getPk(), schoolEnrollment_.getChildCompleteNamePreferred());
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
						.a("href", "/refresh-enrollment/" + schoolEnrollment_.getPk())
						.f().sx("Refresh payments for this enrollment")
					.g("a");
				} g("div");
			} g("div");
			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
					) {
				{ e("div").a("class", "w3-cell-row ").f();
					{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile  ").f();
						{ e("div").a("class", "w3-padding ").f();
							e("a").a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ").a("href", "/pdf/tax-year?var=enrollmentKey:", o.getPk(), "&var=year:", schoolEnrollment_.getYearStart()).f().sx(schoolEnrollment_.getYearStart() + " tax form").g("a");
						} g("div");
						{ e("div").a("class", "w3-padding ").f();
							e("a").a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ").a("href", "/pdf/tax-year?var=enrollmentKey:", o.getPk(), "&var=year:", schoolEnrollment_.getYearEnd()).f().sx(schoolEnrollment_.getYearEnd() + " tax form").g("a");
						} g("div");
					} g("div");
				} g("div");
				LocalDate lateFeeDate = LocalDate.now().plusDays(15);
				{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile w3-padding ").f();
					{ e("div").a("class", "").f();
						e("a")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-blue-gray ")
							.a("href", "/refresh-enrollment/", schoolEnrollment_.getPk(), "?var=lateFeeAmount:5")
							.f().sx("Add a $5 late pick-up/drop-off fee")
						.g("a");
					} g("div");
					{ e("div").a("class", "").f();
						e("a")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-blue-gray ")
							.a("href", "/refresh-enrollment/", schoolEnrollment_.getPk(), "?var=lateFeeAmount:10")
							.f().sx("Add a $10 late pick-up/drop-off fee")
						.g("a");
					} g("div");
					{ e("div").a("class", "").f();
						e("a")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-blue-gray ")
							.a("href", "/refresh-enrollment/", schoolEnrollment_.getPk(), "?var=lateFeeAmount:15")
							.f().sx("Add a $15 late pick-up/drop-off fee")
						.g("a");
					} g("div");
					{ e("div").a("class", "").f();
						e("a")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-blue-gray ")
							.a("href", "/refresh-enrollment/", schoolEnrollment_.getPk(), "?var=lateFeeAmount:20")
							.f().sx("Add a $20 late pick-up/drop-off fee")
						.g("a");
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
