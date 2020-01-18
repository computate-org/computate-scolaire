package org.computate.scolaire.enUS.enrollment;

import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.page.PageLayout;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.core.json.JsonArray;
import java.net.URLDecoder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.List;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.page.PageLayout;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.core.json.JsonArray;
import java.net.URLDecoder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.List;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.page.PageLayout;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.core.json.JsonArray;
import java.net.URLDecoder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.List;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.page.PageLayout;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.core.json.JsonArray;
import java.net.URLDecoder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.List;


/**
 * Translate: false
 **/
public class EnrollmentEmailGenPage extends EnrollmentEmailGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchoolEnrollment(Wrap<SearchList<SchoolEnrollment>> c) {
	}

	protected void _schoolEnrollment(Wrap<SchoolEnrollment> c) {
		if(listSchoolEnrollment != null && listSchoolEnrollment.size() == 1)
			c.o(listSchoolEnrollment.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("enrollments");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(schoolEnrollment != null && schoolEnrollment.getEnrollmentCompleteName() != null)
			c.o(schoolEnrollment.getEnrollmentCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolEnrollment != null && schoolEnrollment.getEnrollmentCompleteName() != null)
			c.o(schoolEnrollment.getEnrollmentCompleteName());
		else if(schoolEnrollment != null)
			c.o("");
		else if(listSchoolEnrollment == null || listSchoolEnrollment.size() == 0)
			c.o("no enrollment found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/enrollment/email");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/enrollment/email-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("solid");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("edit");
	}

	@Override public void initDeepEnrollmentEmailGenPage() {
		initEnrollmentEmailGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsEnrollmentEmailGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/EnrollmentEmailPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/BlockPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/ChildPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/MomPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/DadPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/GuardianPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/PaymentPage.js").f().g("script");
	}

	@Override public void htmlScriptEnrollmentEmailGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", siteRequest_.getRequestPk(), ";");
		tl(1, "suggestSchoolEnrollmentBlockKeys([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentBlockKeys_Page'), pk); ");
		tl(1, "suggestSchoolEnrollmentChildKey([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentChildKey_Page'), pk); ");
		tl(1, "suggestSchoolEnrollmentMomKeys([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentMomKeys_Page'), pk); ");
		tl(1, "suggestSchoolEnrollmentDadKeys([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentDadKeys_Page'), pk); ");
		tl(1, "suggestSchoolEnrollmentGuardianKeys([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentGuardianKeys_Page'), pk); ");
		tl(1, "suggestSchoolEnrollmentPaymentKeys([{'name':'fq','value':'enrollmentKeys:' + pk}], $('#listSchoolEnrollmentPaymentKeys_Page'), pk); ");
		tl(1, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature1').jSignature({'height':200}); ");
		tl(1, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature2').jSignature({'height':200}); ");
		tl(1, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature3').jSignature({'height':200}); ");
		tl(1, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature4').jSignature({'height':200}); ");
		tl(1, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature5').jSignature({'height':200}); ");
		tl(1, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature6').jSignature({'height':200}); ");
		tl(1, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature7').jSignature({'height':200}); ");
		tl(1, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature8').jSignature({'height':200}); ");
		tl(1, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature9').jSignature({'height':200}); ");
		tl(1, "$('#inputSchoolEnrollment' + pk + 'enrollmentSignature10').jSignature({'height':200}); ");
		tl(1, "websocketSchoolEnrollment(websocketSchoolEnrollmentInner);");
		l("});");
	}

	public void htmlFormPageSchoolEnrollment(SchoolEnrollment o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("Page");
			o.htmPk("Page");
			o.htmObjectId("Page");
			o.htmModified("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("Page");
			o.htmDeleted("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentApproved("Page");
			o.htmEnrollmentImmunizations("Page");
			o.htmEnrollmentGroupName("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentPaymentComplete("Page");
			o.htmChildPottyTrained("Page");
			o.htmEnrollmentPaymentEachMonth("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyMarried("Page");
			o.htmFamilySeparated("Page");
			o.htmFamilyDivorced("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyAddress("Page");
			o.htmEnrollmentSpecialConsiderations("Page");
			o.htmFamilyHowDoYouKnowTheSchool("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockKeys("Page");
			o.htmChildMedicalConditions("Page");
			o.htmChildPreviousSchoolsAttended("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildDescription("Page");
			o.htmChildObjectives("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildKey("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMomKeys("Page");
			o.htmDadKeys("Page");
			o.htmEnrollmentFormKey("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGuardianKeys("Page");
			o.htmPaymentKeys("Page");
		} g("div");
	}

	public void htmlFormPOSTSchoolEnrollment(SchoolEnrollment o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("POST");
			o.htmPk("POST");
			o.htmObjectId("POST");
			o.htmModified("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("POST");
			o.htmDeleted("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentApproved("POST");
			o.htmEnrollmentImmunizations("POST");
			o.htmEnrollmentGroupName("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentPaymentComplete("POST");
			o.htmChildPottyTrained("POST");
			o.htmEnrollmentPaymentEachMonth("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyMarried("POST");
			o.htmFamilySeparated("POST");
			o.htmFamilyDivorced("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyAddress("POST");
			o.htmEnrollmentSpecialConsiderations("POST");
			o.htmFamilyHowDoYouKnowTheSchool("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockKeys("POST");
			o.htmChildMedicalConditions("POST");
			o.htmChildPreviousSchoolsAttended("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildDescription("POST");
			o.htmChildObjectives("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildKey("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMomKeys("POST");
			o.htmDadKeys("POST");
			o.htmEnrollmentFormKey("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGuardianKeys("POST");
			o.htmPaymentKeys("POST");
		} g("div");
	}

	public void htmlFormPATCHSchoolEnrollment(SchoolEnrollment o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PATCH");
			o.htmPk("PATCH");
			o.htmObjectId("PATCH");
			o.htmModified("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PATCH");
			o.htmDeleted("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentApproved("PATCH");
			o.htmEnrollmentImmunizations("PATCH");
			o.htmEnrollmentGroupName("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentPaymentComplete("PATCH");
			o.htmChildPottyTrained("PATCH");
			o.htmEnrollmentPaymentEachMonth("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyMarried("PATCH");
			o.htmFamilySeparated("PATCH");
			o.htmFamilyDivorced("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyAddress("PATCH");
			o.htmEnrollmentSpecialConsiderations("PATCH");
			o.htmFamilyHowDoYouKnowTheSchool("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockKeys("PATCH");
			o.htmChildMedicalConditions("PATCH");
			o.htmChildPreviousSchoolsAttended("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildDescription("PATCH");
			o.htmChildObjectives("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildKey("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMomKeys("PATCH");
			o.htmDadKeys("PATCH");
			o.htmEnrollmentFormKey("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGuardianKeys("PATCH");
			o.htmPaymentKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildCompleteName("PATCH");
			o.htmChildBirthDate("PATCH");
			o.htmSchoolAddress("PATCH");
			o.htmEnrollmentParentNames("PATCH");
			o.htmEnrollmentSignature1("PATCH");
			o.htmEnrollmentSignature2("PATCH");
			o.htmEnrollmentSignature3("PATCH");
			o.htmEnrollmentSignature4("PATCH");
			o.htmEnrollmentSignature5("PATCH");
			o.htmEnrollmentSignature6("PATCH");
			o.htmEnrollmentSignature7("PATCH");
			o.htmEnrollmentSignature8("PATCH");
			o.htmEnrollmentSignature9("PATCH");
			o.htmEnrollmentSignature10("PATCH");
			o.htmEnrollmentDate1("PATCH");
			o.htmEnrollmentDate2("PATCH");
			o.htmEnrollmentDate3("PATCH");
			o.htmEnrollmentDate4("PATCH");
			o.htmEnrollmentDate5("PATCH");
			o.htmEnrollmentDate6("PATCH");
			o.htmEnrollmentDate7("PATCH");
			o.htmEnrollmentDate8("PATCH");
			o.htmEnrollmentDate9("PATCH");
			o.htmEnrollmentDate10("PATCH");
			o.htmEnrollmentCompleteName("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolEnrollment(SchoolEnrollment o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("Recherche");
			o.htmPk("Recherche");
			o.htmObjectId("Recherche");
			o.htmModified("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("Recherche");
			o.htmDeleted("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentApproved("Recherche");
			o.htmEnrollmentImmunizations("Recherche");
			o.htmEnrollmentGroupName("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentPaymentComplete("Recherche");
			o.htmChildPottyTrained("Recherche");
			o.htmEnrollmentPaymentEachMonth("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyMarried("Recherche");
			o.htmFamilySeparated("Recherche");
			o.htmFamilyDivorced("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmFamilyAddress("Recherche");
			o.htmEnrollmentSpecialConsiderations("Recherche");
			o.htmFamilyHowDoYouKnowTheSchool("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockKeys("Recherche");
			o.htmChildMedicalConditions("Recherche");
			o.htmChildPreviousSchoolsAttended("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildDescription("Recherche");
			o.htmChildObjectives("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildKey("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMomKeys("Recherche");
			o.htmDadKeys("Recherche");
			o.htmEnrollmentFormKey("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGuardianKeys("Recherche");
			o.htmPaymentKeys("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildCompleteName("Recherche");
			o.htmChildBirthDate("Recherche");
			o.htmSchoolAddress("Recherche");
			o.htmEnrollmentParentNames("Recherche");
			o.htmEnrollmentDate1("Recherche");
			o.htmEnrollmentDate2("Recherche");
			o.htmEnrollmentDate3("Recherche");
			o.htmEnrollmentDate4("Recherche");
			o.htmEnrollmentDate5("Recherche");
			o.htmEnrollmentDate6("Recherche");
			o.htmEnrollmentDate7("Recherche");
			o.htmEnrollmentDate8("Recherche");
			o.htmEnrollmentDate9("Recherche");
			o.htmEnrollmentDate10("Recherche");
			o.htmEnrollmentCompleteName("Recherche");
		} g("div");
	}

	@Override public void htmlBodyEnrollmentEmailGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolEnrollment == null || listSchoolEnrollment.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/enrollment/email").a("class", "w3-bar-item w3-btn w3-center w3-block w3-purple w3-hover-purple ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("enrollments").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-purple ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no enrollment found").g("span");
				} g("span");
			} g("h2");
		} else if(listSchoolEnrollment != null && listSchoolEnrollment.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolEnrollment o = listSchoolEnrollment.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/enrollment/email").a("class", "w3-bar-item w3-btn w3-center w3-block w3-purple w3-hover-purple ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-purple ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-purple ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

				{ e("h1").f();
					{ e("a").a("href", "/enrollment/email").a("class", "w3-bar-item w3-btn w3-center w3-block w3-purple w3-hover-purple ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listSchoolEnrollment.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listSchoolEnrollment.getQuery(), "_suggested", "");
					Integer rows1 = listSchoolEnrollment.getRows();
					Integer start1 = listSchoolEnrollment.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enrollment/email?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enrollment/email?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/enrollment/email?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enrollment/email?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-purple w3-hover-purple ").f();
					{ e("tr").f();
						e("th").f().sx("name").g("th");
						e("th").f().sx("created").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listSchoolEnrollment.getQueryResponse().getHighlighting();
					for(int i = 0; i < listSchoolEnrollment.size(); i++) {
						SchoolEnrollment o = listSchoolEnrollment.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/enrollment/email/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "fas fa-edit w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.strEnrollmentCompleteName());
									} g("span");
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									{ e("span").f();
										sx(o.strCreated());
									} g("span");
								} g("a");
							} g("td");
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listSchoolEnrollment != null && listSchoolEnrollment.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolEnrollment o = listSchoolEnrollment.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolEnrollmentForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valuePk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
						e("input")
						.a("name", "focusId")
						.a("type", "hidden")
						.fg();
					} g("form");
					htmlFormPageSchoolEnrollment(o);
				}

			} g("div");

		}
		htmlBodyFormsEnrollmentEmailGenPage();
		htmlSuggestEnrollmentEmailGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsEnrollmentEmailGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
				.a("id", "refreshThisEnrollmentEmailGenPage")
				.a("onclick", "patchSchoolEnrollmentVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisEnrollmentEmailGenPage')); }, function() { addError($('#refreshThisEnrollmentEmailGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("refresh this enrollment");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
			.a("onclick", "$('#postSchoolEnrollmentModal').show(); ")
			.f().sx("Create an enrollment")
		.g("button");
		{ e("div").a("id", "postSchoolEnrollmentModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-purple ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolEnrollmentModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Create an enrollment").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolEnrollment o = new SchoolEnrollment();
					o.setSiteRequest_(siteRequest_);

					// Form POST
					{ e("div").a("id", "postSchoolEnrollmentForm").f();
						htmlFormPOSTSchoolEnrollment(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
						.a("onclick", "postSchoolEnrollment($('#postSchoolEnrollmentForm')); ")
						.f().sx("Create an enrollment")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
			.a("onclick", "$('#patchSchoolEnrollmentModal').show(); ")
			.f().sx("Modify the enrollments")
		.g("button");
		{ e("div").a("id", "patchSchoolEnrollmentModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-purple ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolEnrollmentModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modify the enrollments").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolEnrollment o = new SchoolEnrollment();
					o.setSiteRequest_(siteRequest_);

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchSchoolEnrollmentFormFilters").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormSearchSchoolEnrollment(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
						.a("onclick", "aSearchSchoolEnrollment($('#patchSchoolEnrollmentFormFilters')); ")
						.f().sx("Search the an enrollment")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchSchoolEnrollmentFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSchoolEnrollment(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
						.a("onclick", "patchSchoolEnrollment($('#patchSchoolEnrollmentFormFilters'), $('#patchSchoolEnrollmentFormValues'), function() {}, function() {}); ")
						.f().sx("Modify the enrollments")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listSchoolEnrollment != null && listSchoolEnrollment.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
				.a("onclick", "$('#deleteSchoolEnrollmentModal').show(); ")
				.f().sx("Delete the enrollments")
			.g("button");
			{ e("div").a("id", "deleteSchoolEnrollmentModal").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-purple ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSchoolEnrollmentModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Delete the enrollments").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolEnrollment o = new SchoolEnrollment();
						o.setSiteRequest_(siteRequest_);

						// Form DELETE
						{ e("div").a("id", "deleteSchoolEnrollmentForm").f();
							htmlFormPATCHSchoolEnrollment(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
							.a("onclick", "deleteSchoolEnrollment(", o.getPk(), "); ")
							.f().sx("Delete the enrollments")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	**/
	public static void htmlSuggestEnrollmentEmailGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/enrollment/email").a("class", "").f();
					p.e("i").a("class", "fas fa-edit w3-padding-small ").f().g("i");
					p.sx("see all the enrollments");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "refreshAllEnrollmentEmailGenPage", id).a("href", "/enrollment/email").a("class", "").a("onclick", "patchSchoolEnrollmentVals([], {}, function() { addGlow($('#refreshAllEnrollmentEmailGenPage", id, "')); }, function() { addError($('#refreshAllEnrollmentEmailGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("refresh all the enrollments");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("search enrollments: ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggestFormSchoolEnrollment", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/enrollment/email?q=objectSuggest:' + encodeURIComponent($('#suggestSchoolEnrollment", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("class", "suggestSchoolEnrollment w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggestSchoolEnrollment")
							.a("id", "suggestSchoolEnrollment", id)
							.a("autocomplete", "off")
							.a("oninput", "suggestSchoolEnrollmentObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSchoolEnrollment", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchoolEnrollment", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
