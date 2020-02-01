package org.computate.scolaire.enUS.mom;

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
import java.util.Optional;


/**
 * Translate: false
 **/
public class MomGenPage extends MomGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchoolMom(Wrap<SearchList<SchoolMom>> c) {
	}

	protected void _schoolMom(Wrap<SchoolMom> c) {
		if(listSchoolMom != null && listSchoolMom.size() == 1)
			c.o(listSchoolMom.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("moms");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(schoolMom != null && schoolMom.getMomCompleteName() != null)
			c.o(schoolMom.getMomCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolMom != null && schoolMom.getMomCompleteName() != null)
			c.o(schoolMom.getMomCompleteName());
		else if(schoolMom != null)
			c.o("");
		else if(listSchoolMom == null || listSchoolMom.size() == 0)
			c.o("no mom found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/mom");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/mom-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("regular");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("female");
	}

	@Override public void initDeepMomGenPage() {
		initMomGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsMomGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/MomPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/EnrollmentPage.js").f().g("script");
	}

	@Override public void htmlScriptMomGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(siteRequest_.getRequestPk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(2, "suggestSchoolMomEnrollmentKeys([{'name':'fq','value':'momKeys:' + pk}], $('#listSchoolMomEnrollmentKeys_Page'), pk); ");
		tl(1, "}");
		tl(1, "websocketSchoolMom(websocketSchoolMomInner);");
		l("});");
	}

	public void htmlFormPageSchoolMom(SchoolMom o) {
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
			o.htmPersonFirstName("Page");
			o.htmFamilyName("Page");
			o.htmPersonFirstNamePreferred("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonEmail("Page");
			o.htmPersonPhoneNumber("Page");
			o.htmPersonOccupation("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonSms("Page");
			o.htmPersonReceiveEmail("Page");
			o.htmPersonEmergencyContact("Page");
			o.htmPersonPickup("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("Page");
		} g("div");
	}

	public void htmlFormPOSTSchoolMom(SchoolMom o) {
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
			o.htmPersonFirstName("POST");
			o.htmFamilyName("POST");
			o.htmPersonFirstNamePreferred("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonEmail("POST");
			o.htmPersonPhoneNumber("POST");
			o.htmPersonOccupation("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonSms("POST");
			o.htmPersonReceiveEmail("POST");
			o.htmPersonEmergencyContact("POST");
			o.htmPersonPickup("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("POST");
		} g("div");
	}

	public void htmlFormPUTSchoolMom(SchoolMom o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PUT");
			o.htmModified("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PUT");
			o.htmDeleted("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonFirstName("PUT");
			o.htmFamilyName("PUT");
			o.htmPersonFirstNamePreferred("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonEmail("PUT");
			o.htmPersonPhoneNumber("PUT");
			o.htmPersonOccupation("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonSms("PUT");
			o.htmPersonReceiveEmail("PUT");
			o.htmPersonEmergencyContact("PUT");
			o.htmPersonPickup("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("PUT");
		} g("div");
	}

	public void htmlFormPATCHSchoolMom(SchoolMom o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PATCH");
			o.htmModified("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PATCH");
			o.htmDeleted("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonFirstName("PATCH");
			o.htmFamilyName("PATCH");
			o.htmPersonFirstNamePreferred("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonEmail("PATCH");
			o.htmPersonPhoneNumber("PATCH");
			o.htmPersonOccupation("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonSms("PATCH");
			o.htmPersonReceiveEmail("PATCH");
			o.htmPersonEmergencyContact("PATCH");
			o.htmPersonPickup("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolMom(SchoolMom o) {
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
			o.htmPersonFirstName("Recherche");
			o.htmFamilyName("Recherche");
			o.htmPersonFirstNamePreferred("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonEmail("Recherche");
			o.htmPersonPhoneNumber("Recherche");
			o.htmPersonOccupation("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonSms("Recherche");
			o.htmPersonReceiveEmail("Recherche");
			o.htmPersonEmergencyContact("Recherche");
			o.htmPersonPickup("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjectTitle("Recherche");
		} g("div");
	}

	@Override public void htmlBodyMomGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolMom == null || listSchoolMom.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/mom").a("class", "w3-bar-item w3-btn w3-center w3-block w3-pink w3-hover-pink ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("moms").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-pink ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no mom found").g("span");
				} g("span");
			} g("h2");
		} else if(listSchoolMom != null && listSchoolMom.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolMom o = listSchoolMom.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/mom").a("class", "w3-bar-item w3-btn w3-center w3-block w3-pink w3-hover-pink ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-pink ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-pink ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/mom").a("class", "w3-bar-item w3-btn w3-center w3-block w3-pink w3-hover-pink ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listSchoolMom.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listSchoolMom.getQuery(), "_suggested", "");
					Integer rows1 = listSchoolMom.getRows();
					Integer start1 = listSchoolMom.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/mom?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/mom?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/mom?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/mom?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-pink w3-hover-pink ").f();
					{ e("tr").f();
						e("th").f().sx("created").g("th");
						e("th").f().sx("").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listSchoolMom.getQueryResponse().getHighlighting();
					for(int i = 0; i < listSchoolMom.size(); i++) {
						SchoolMom o = listSchoolMom.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/mom/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									{ e("span").f();
										sx(o.strCreated());
									} g("span");
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "far fa-female ").f().g("i");
									{ e("span").f();
										sx(o.strObjectTitle());
									} g("span");
								} g("a");
							} g("td");
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listSchoolMom != null && listSchoolMom.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolMom o = listSchoolMom.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolMomForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageSchoolMom(o);
				}

			} g("div");

		}
		htmlBodyFormsMomGenPage();
		htmlSuggestMomGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsMomGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
				.a("id", "refreshThisMomGenPage")
				.a("onclick", "patchSchoolMomVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisMomGenPage')); }, function() { addError($('#refreshThisMomGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt ").f().g("i");
			sx("refresh this mom");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#postSchoolMomModal').show(); ")
			.f().sx("Create a mom")
		.g("button");
		{ e("div").a("id", "postSchoolMomModal").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-pink ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolMomModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Create a mom").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolMom o = new SchoolMom();
						o.setSiteRequest_(siteRequest_);

						// Form POST
						{ e("div").a("id", "postSchoolMomForm").f();
							htmlFormPOSTSchoolMom(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-pink ")
							.a("onclick", "postSchoolMom($('#postSchoolMomForm')); ")
							.f().sx("Create a mom")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#putSchoolMomModal').show(); ")
			.f().sx("Duplicate the moms")
		.g("button");
		{ e("div").a("id", "putSchoolMomModal").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-pink ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putSchoolMomModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Duplicate the moms").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolMom o = new SchoolMom();
						o.setSiteRequest_(siteRequest_);

						// FormValues PUT
						{ e("form").a("action", "").a("id", "putSchoolMomFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPUTSchoolMom(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-pink ")
							.a("onclick", "putSchoolMom($('#putSchoolMomFormValues')); ")
							.f().sx("Duplicate the moms")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#patchSchoolMomModal').show(); ")
			.f().sx("Modify the moms")
		.g("button");
		{ e("div").a("id", "patchSchoolMomModal").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-pink ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolMomModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Modify the moms").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolMom o = new SchoolMom();
						o.setSiteRequest_(siteRequest_);

						// FormValues PATCH
						{ e("form").a("action", "").a("id", "patchSchoolMomFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPATCHSchoolMom(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-pink ")
							.a("onclick", "patchSchoolMom($('#patchSchoolMomFormFilters'), $('#patchSchoolMomFormValues'), function() {}, function() {}); ")
							.f().sx("Modify the moms")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		if(listSchoolMom != null && listSchoolMom.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
				.a("onclick", "$('#deleteSchoolMomModal').show(); ")
				.f().sx("Delete the moms")
			.g("button");
			{ e("div").a("id", "deleteSchoolMomModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-pink ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSchoolMomModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Delete the moms").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolMom o = new SchoolMom();
							o.setSiteRequest_(siteRequest_);

							// Form DELETE
							{ e("div").a("id", "deleteSchoolMomForm").f();
								htmlFormPATCHSchoolMom(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-pink ")
								.a("onclick", "deleteSchoolMom(", o.getPk(), "); ")
								.f().sx("Delete the moms")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	**/
	public static void htmlSuggestMomGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/mom").a("class", "").f();
					p.e("i").a("class", "far fa-female ").f().g("i");
					p.sx("see all the moms");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("id", "refreshAllMomGenPage", id).a("href", "/mom").a("class", "").a("onclick", "patchSchoolMomVals([], {}, function() { addGlow($('#refreshAllMomGenPage", id, "')); }, function() { addError($('#refreshAllMomGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
					p.sx("refresh all the moms");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("search moms: ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-bar ").f();

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("class", "suggestSchoolMom w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggestSchoolMom")
				.a("id", "suggestSchoolMom", id)
				.a("autocomplete", "off")
				.a("oninput", "suggestSchoolMomObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSchoolMom", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
				.fg();

		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchoolMom", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
