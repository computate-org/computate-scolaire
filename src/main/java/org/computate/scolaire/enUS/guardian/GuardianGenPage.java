package org.computate.scolaire.enUS.guardian;

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
public class GuardianGenPage extends GuardianGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchoolGuardian(Wrap<SearchList<SchoolGuardian>> c) {
	}

	protected void _schoolGuardian(Wrap<SchoolGuardian> c) {
		if(listSchoolGuardian != null && listSchoolGuardian.size() == 1)
			c.o(listSchoolGuardian.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("guardians");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolGuardian != null && schoolGuardian.getGuardianCompleteName() != null)
			c.o(schoolGuardian.getGuardianCompleteName());
		else if(schoolGuardian != null)
			c.o("");
		else if(listSchoolGuardian == null || listSchoolGuardian.size() == 0)
			c.o("no guardian found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/guardian");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/guardian-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("regular");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("phone");
	}

	@Override public void initDeepGuardianGenPage() {
		initGuardianGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsGuardianGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/GuardianPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/EnrollmentPage.js").f().g("script");
	}

	@Override public void htmlScriptGuardianGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", siteRequest_.getRequestPk(), ";");
		tl(1, "suggestSchoolGuardianEnrollmentKeys([{'name':'fq','value':'guardianKeys:' + pk}], $('#listSchoolGuardianEnrollmentKeys_Page'), pk); ");
		tl(1, "websocketSchoolGuardian(websocketSchoolGuardianInner);");
		l("});");
	}

	public void htmlFormPageSchoolGuardian(SchoolGuardian o) {
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
			o.htmPersonFirstName("Page");
			o.htmFamilyName("Page");
			o.htmPersonFirstNamePreferred("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonPhoneNumber("Page");
			o.htmPersonRelation("Page");
			o.htmPersonEmergencyContact("Page");
			o.htmPersonPickup("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("Page");
		} g("div");
	}

	public void htmlFormPOSTSchoolGuardian(SchoolGuardian o) {
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
			o.htmPersonFirstName("POST");
			o.htmFamilyName("POST");
			o.htmPersonFirstNamePreferred("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonPhoneNumber("POST");
			o.htmPersonRelation("POST");
			o.htmPersonEmergencyContact("POST");
			o.htmPersonPickup("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("POST");
		} g("div");
	}

	public void htmlFormPATCHSchoolGuardian(SchoolGuardian o) {
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
			o.htmPersonFirstName("PATCH");
			o.htmFamilyName("PATCH");
			o.htmPersonFirstNamePreferred("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonPhoneNumber("PATCH");
			o.htmPersonRelation("PATCH");
			o.htmPersonEmergencyContact("PATCH");
			o.htmPersonPickup("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGuardianCompleteName("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolGuardian(SchoolGuardian o) {
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
			o.htmPersonFirstName("Recherche");
			o.htmFamilyName("Recherche");
			o.htmPersonFirstNamePreferred("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonPhoneNumber("Recherche");
			o.htmPersonRelation("Recherche");
			o.htmPersonEmergencyContact("Recherche");
			o.htmPersonPickup("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmGuardianCompleteName("Recherche");
		} g("div");
	}

	@Override public void htmlBodyGuardianGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolGuardian == null || listSchoolGuardian.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/guardian").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("guardians").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-yellow ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no guardian found").g("span");
				} g("span");
			} g("h2");
		} else if(listSchoolGuardian != null && listSchoolGuardian.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolGuardian o = listSchoolGuardian.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/guardian").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-yellow ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-yellow ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

				{ e("h1").f();
					{ e("a").a("href", "/guardian").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listSchoolGuardian.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listSchoolGuardian.getQuery(), "_suggested", "");
					Integer rows1 = listSchoolGuardian.getRows();
					Integer start1 = listSchoolGuardian.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/guardian?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/guardian?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/guardian?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/guardian?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-yellow w3-hover-yellow ").f();
					{ e("tr").f();
						e("th").f().sx("").g("th");
						e("th").f().sx("created").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listSchoolGuardian.getQueryResponse().getHighlighting();
					for(int i = 0; i < listSchoolGuardian.size(); i++) {
						SchoolGuardian o = listSchoolGuardian.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/guardian/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "far fa-phone w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.strGuardianCompleteName());
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

		if(listSchoolGuardian != null && listSchoolGuardian.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolGuardian o = listSchoolGuardian.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolGuardianForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageSchoolGuardian(o);
				}

			} g("div");

		}
		htmlBodyFormsGuardianGenPage();
		htmlSuggestGuardianGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsGuardianGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
				.a("id", "refreshThisGuardianGenPage")
				.a("onclick", "patchSchoolGuardianVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisGuardianGenPage')); }, function() { addError($('#refreshThisGuardianGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("refresh this guardian");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#postSchoolGuardianModal').show(); ")
			.f().sx("Create a guardian")
		.g("button");
		{ e("div").a("id", "postSchoolGuardianModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolGuardianModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Create a guardian").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolGuardian o = new SchoolGuardian();
					o.setSiteRequest_(siteRequest_);

					// Form POST
					{ e("div").a("id", "postSchoolGuardianForm").f();
						htmlFormPOSTSchoolGuardian(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "postSchoolGuardian($('#postSchoolGuardianForm')); ")
						.f().sx("Create a guardian")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#patchSchoolGuardianModal').show(); ")
			.f().sx("Modify the guardians")
		.g("button");
		{ e("div").a("id", "patchSchoolGuardianModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolGuardianModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modify the guardians").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolGuardian o = new SchoolGuardian();
					o.setSiteRequest_(siteRequest_);

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchSchoolGuardianFormFilters").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormSearchSchoolGuardian(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "aSearchSchoolGuardian($('#patchSchoolGuardianFormFilters')); ")
						.f().sx("Search the a guardian")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchSchoolGuardianFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSchoolGuardian(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "patchSchoolGuardian($('#patchSchoolGuardianFormFilters'), $('#patchSchoolGuardianFormValues'), function() {}, function() {}); ")
						.f().sx("Modify the guardians")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listSchoolGuardian != null && listSchoolGuardian.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
				.a("onclick", "$('#deleteSchoolGuardianModal').show(); ")
				.f().sx("Delete the guardians")
			.g("button");
			{ e("div").a("id", "deleteSchoolGuardianModal").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-yellow ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSchoolGuardianModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Delete the guardians").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolGuardian o = new SchoolGuardian();
						o.setSiteRequest_(siteRequest_);

						// Form DELETE
						{ e("div").a("id", "deleteSchoolGuardianForm").f();
							htmlFormPATCHSchoolGuardian(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
							.a("onclick", "deleteSchoolGuardian(", o.getPk(), "); ")
							.f().sx("Delete the guardians")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	**/
	public static void htmlSuggestGuardianGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/guardian").a("class", "").f();
					p.e("i").a("class", "far fa-phone w3-padding-small ").f().g("i");
					p.sx("see all the guardians");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "refreshAllGuardianGenPage", id).a("href", "/guardian").a("class", "").a("onclick", "patchSchoolGuardianVals([], {}, function() { addGlow($('#refreshAllGuardianGenPage", id, "')); }, function() { addError($('#refreshAllGuardianGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("refresh all the guardians");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("search guardians: ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggestFormSchoolGuardian", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/guardian?q=objectSuggest:' + encodeURIComponent($('#suggestSchoolGuardian", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("class", "suggestSchoolGuardian w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggestSchoolGuardian")
							.a("id", "suggestSchoolGuardian", id)
							.a("autocomplete", "off")
							.a("oninput", "suggestSchoolGuardianObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSchoolGuardian", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchoolGuardian", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
