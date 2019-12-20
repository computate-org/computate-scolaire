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


/**
 * Translate: false
 **/
public class EnrollmentGenPage extends EnrollmentGenPageGen<ClusterPage> {

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
		c.o("/enrollment");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/enrollment-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("solid");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("edit");
	}

	@Override public void initDeepEnrollmentGenPage() {
		initEnrollmentGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsEnrollmentGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/EnrollmentPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/BlockPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/ChildPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/MomPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/DadPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/GuardianPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/PaymentPage.js").f().g("script");
	}

	@Override public void htmlScriptEnrollmentGenPage() {
		l("$(document).ready(function() {");
		tl(1, "suggestSchoolEnrollmentBlockKeys([{'name':'fq','value':'enrollmentKeys:", siteRequest_.getRequestPk(), "'}], $('#listSchoolEnrollmentBlockKeys_Page'), ", siteRequest_.getRequestPk(), "); ");
		tl(1, "suggestSchoolEnrollmentChildKey([{'name':'fq','value':'enrollmentKeys:", siteRequest_.getRequestPk(), "'}], $('#listSchoolEnrollmentChildKey_Page'), ", siteRequest_.getRequestPk(), "); ");
		tl(1, "suggestSchoolEnrollmentMomKeys([{'name':'fq','value':'enrollmentKeys:", siteRequest_.getRequestPk(), "'}], $('#listSchoolEnrollmentMomKeys_Page'), ", siteRequest_.getRequestPk(), "); ");
		tl(1, "suggestSchoolEnrollmentDadKeys([{'name':'fq','value':'enrollmentKeys:", siteRequest_.getRequestPk(), "'}], $('#listSchoolEnrollmentDadKeys_Page'), ", siteRequest_.getRequestPk(), "); ");
		tl(1, "suggestSchoolEnrollmentGuardianKeys([{'name':'fq','value':'enrollmentKeys:", siteRequest_.getRequestPk(), "'}], $('#listSchoolEnrollmentGuardianKeys_Page'), ", siteRequest_.getRequestPk(), "); ");
		tl(1, "suggestSchoolEnrollmentPaymentKeys([{'name':'fq','value':'enrollmentKeys:", siteRequest_.getRequestPk(), "'}], $('#listSchoolEnrollmentPaymentKeys_Page'), ", siteRequest_.getRequestPk(), "); ");
		tl(1, "websocketSchoolEnrollment(async function(patchRequest) {");
		tl(2, "var pk = patchRequest['pk'];");
		tl(2, "var pks = patchRequest['pks'];");
		tl(2, "var classes = patchRequest['classes'];");
		tl(2, "if(pks) {");
		tl(3, "for(i=0; i < pks.length; i++) {");
		tl(4, "var pk2 = pks[i];");
		tl(4, "var c = classes[i];");
		tl(4, "await window['patch' + c + 'Vals']( [ {name: 'fq', value: 'pk:' + pk2} ], {});");
		tl(3, "}");
		tl(2, "}");
		tl(2, "await patchSchoolEnrollmentVals( [ {name: 'fq', value: 'pk:' + pk} ], {});");
		tl(1, "});");
		l("});");
	}

	public void htmlFormPageSchoolEnrollment(SchoolEnrollment o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("Page");
			o.htmCreated("Page");
			o.htmModified("Page");
			o.htmObjectId("Page");
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
			o.htmChildKey("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMomKeys("Page");
			o.htmDadKeys("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGuardianKeys("Page");
			o.htmPaymentKeys("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentFormKey("Page");
		} g("div");
	}

	public void htmlFormPOSTSchoolEnrollment(SchoolEnrollment o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("POST");
			o.htmCreated("POST");
			o.htmModified("POST");
			o.htmObjectId("POST");
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
			o.htmChildKey("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMomKeys("POST");
			o.htmDadKeys("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGuardianKeys("POST");
			o.htmPaymentKeys("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentFormKey("POST");
		} g("div");
	}

	public void htmlFormPATCHSchoolEnrollment(SchoolEnrollment o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("PATCH");
			o.htmCreated("PATCH");
			o.htmModified("PATCH");
			o.htmObjectId("PATCH");
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
			o.htmChildKey("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMomKeys("PATCH");
			o.htmDadKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGuardianKeys("PATCH");
			o.htmPaymentKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentFormKey("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolAddress("PATCH");
			o.htmEnrollmentParentSignature("PATCH");
			o.htmEnrollmentParentDate("PATCH");
			o.htmEnrollmentCompleteName("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolEnrollment(SchoolEnrollment o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("Recherche");
			o.htmCreated("Recherche");
			o.htmModified("Recherche");
			o.htmObjectId("Recherche");
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
			o.htmChildKey("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmMomKeys("Recherche");
			o.htmDadKeys("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGuardianKeys("Recherche");
			o.htmPaymentKeys("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentFormKey("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolAddress("Recherche");
			o.htmEnrollmentParentSignature("Recherche");
			o.htmEnrollmentParentDate("Recherche");
			o.htmEnrollmentCompleteName("Recherche");
		} g("div");
	}

	@Override public void htmlBodyEnrollmentGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolEnrollment == null || listSchoolEnrollment.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/enrollment").a("class", "w3-bar-item w3-btn w3-center w3-block w3-purple w3-hover-purple ").f();
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
					{ e("a").a("href", "/enrollment").a("class", "w3-bar-item w3-btn w3-center w3-block w3-purple w3-hover-purple ").f();
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
					{ e("a").a("href", "/enrollment").a("class", "w3-bar-item w3-btn w3-center w3-block w3-purple w3-hover-purple ").f();
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
						{ e("a").a("href", "/enrollment?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enrollment?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/enrollment?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enrollment?q=", query, "&start=", start3, "&rows=", rows1).f();
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
						String uri = "/enrollment/" + o.getPk();
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
		htmlBodyFormsEnrollmentGenPage();
		htmlSuggestEnrollmentGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsEnrollmentGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
				.a("id", "refreshThisEnrollmentGenPage")
				.a("onclick", "patchSchoolEnrollmentVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisEnrollmentGenPage')); }, function() { addError($('#refreshThisEnrollmentGenPage')); }); return false; ").f();
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
	public static void htmlSuggestEnrollmentGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/enrollment").a("class", "").f();
					p.e("i").a("class", "fas fa-edit w3-padding-small ").f().g("i");
					p.sx("see all the enrollments");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "refreshAllEnrollmentGenPage", id).a("href", "/enrollment").a("class", "").a("onclick", "patchSchoolEnrollmentVals([], {}, function() { addGlow($('#refreshAllEnrollmentGenPage", id, "')); }, function() { addError($('#refreshAllEnrollmentGenPage", id, "')); }); return false; ").f();
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
					{ p.e("form").a("action", "").a("id", "suggestFormSchoolEnrollment", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/enrollment?q=objectSuggest:' + encodeURIComponent($('#suggestSchoolEnrollment", id, "').val()); return false; ").f();
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
