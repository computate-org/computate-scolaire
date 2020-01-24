package org.computate.scolaire.enUS.year;

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
public class YearGenPage extends YearGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchoolYear(Wrap<SearchList<SchoolYear>> c) {
	}

	protected void _schoolYear(Wrap<SchoolYear> c) {
		if(listSchoolYear != null && listSchoolYear.size() == 1)
			c.o(listSchoolYear.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("years");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(schoolYear != null && schoolYear.getYearCompleteName() != null)
			c.o(schoolYear.getYearCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolYear != null && schoolYear.getYearCompleteName() != null)
			c.o(schoolYear.getYearCompleteName());
		else if(schoolYear != null)
			c.o("");
		else if(listSchoolYear == null || listSchoolYear.size() == 0)
			c.o("no year found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/year");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/year-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("regular");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("calendar-check");
	}

	@Override public void initDeepYearGenPage() {
		initYearGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsYearGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/YearPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/SchoolPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/SeasonPage.js").f().g("script");
	}

	@Override public void htmlScriptYearGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", siteRequest_.getRequestPk(), ";");
		tl(1, "suggestSchoolYearSchoolKey([{'name':'fq','value':'yearKeys:' + pk}], $('#listSchoolYearSchoolKey_Page'), pk); ");
		tl(1, "suggestSchoolYearSeasonKeys([{'name':'fq','value':'yearKey:' + pk}], $('#listSchoolYearSeasonKeys_Page'), pk); ");
		tl(1, "websocketSchoolYear(websocketSchoolYearInner);");
		l("});");
	}

	public void htmlFormPageSchoolYear(SchoolYear o) {
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
			o.htmYearStart("Page");
			o.htmYearEnd("Page");
			o.htmYearEnrollmentFee("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolKey("Page");
			o.htmSeasonKeys("Page");
			o.htmEnrollmentFormKey("Page");
		} g("div");
	}

	public void htmlFormPOSTSchoolYear(SchoolYear o) {
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
			o.htmYearStart("POST");
			o.htmYearEnd("POST");
			o.htmYearEnrollmentFee("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolKey("POST");
			o.htmSeasonKeys("POST");
			o.htmEnrollmentFormKey("POST");
		} g("div");
	}

	public void htmlFormPATCHSchoolYear(SchoolYear o) {
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
			o.htmYearStart("PATCH");
			o.htmYearEnd("PATCH");
			o.htmYearEnrollmentFee("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolKey("PATCH");
			o.htmSeasonKeys("PATCH");
			o.htmEnrollmentFormKey("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmYearCompleteName("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolYear(SchoolYear o) {
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
			o.htmYearStart("Recherche");
			o.htmYearEnd("Recherche");
			o.htmYearEnrollmentFee("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolKey("Recherche");
			o.htmSeasonKeys("Recherche");
			o.htmEnrollmentFormKey("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmYearCompleteName("Recherche");
		} g("div");
	}

	@Override public void htmlBodyYearGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolYear == null || listSchoolYear.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/year").a("class", "w3-bar-item w3-btn w3-center w3-block w3-orange w3-hover-orange ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("years").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-orange ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no year found").g("span");
				} g("span");
			} g("h2");
		} else if(listSchoolYear != null && listSchoolYear.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolYear o = listSchoolYear.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/year").a("class", "w3-bar-item w3-btn w3-center w3-block w3-orange w3-hover-orange ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-orange ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-orange ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

				{ e("h1").f();
					{ e("a").a("href", "/year").a("class", "w3-bar-item w3-btn w3-center w3-block w3-orange w3-hover-orange ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listSchoolYear.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listSchoolYear.getQuery(), "_suggested", "");
					Integer rows1 = listSchoolYear.getRows();
					Integer start1 = listSchoolYear.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/year?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/year?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/year?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/year?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-orange w3-hover-orange ").f();
					{ e("tr").f();
						e("th").f().sx("").g("th");
						e("th").f().sx("created").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listSchoolYear.getQueryResponse().getHighlighting();
					for(int i = 0; i < listSchoolYear.size(); i++) {
						SchoolYear o = listSchoolYear.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/year/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "far fa-calendar-check w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.strYearCompleteName());
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

		if(listSchoolYear != null && listSchoolYear.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolYear o = listSchoolYear.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolYearForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageSchoolYear(o);
				}

			} g("div");

		}
		htmlBodyFormsYearGenPage();
		htmlSuggestYearGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsYearGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
				.a("id", "refreshThisYearGenPage")
				.a("onclick", "patchSchoolYearVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisYearGenPage')); }, function() { addError($('#refreshThisYearGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("refresh this year");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
			.a("onclick", "$('#postSchoolYearModal').show(); ")
			.f().sx("Create a year")
		.g("button");
		{ e("div").a("id", "postSchoolYearModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-orange ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolYearModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Create a year").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolYear o = new SchoolYear();
					o.setSiteRequest_(siteRequest_);

					// Form POST
					{ e("div").a("id", "postSchoolYearForm").f();
						htmlFormPOSTSchoolYear(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
						.a("onclick", "postSchoolYear($('#postSchoolYearForm')); ")
						.f().sx("Create a year")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
			.a("onclick", "$('#patchSchoolYearModal').show(); ")
			.f().sx("Modify the years")
		.g("button");
		{ e("div").a("id", "patchSchoolYearModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-orange ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolYearModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modify the years").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolYear o = new SchoolYear();
					o.setSiteRequest_(siteRequest_);

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchSchoolYearFormFilters").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormSearchSchoolYear(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
						.a("onclick", "aSearchSchoolYear($('#patchSchoolYearFormFilters')); ")
						.f().sx("Search the a year")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchSchoolYearFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSchoolYear(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
						.a("onclick", "patchSchoolYear($('#patchSchoolYearFormFilters'), $('#patchSchoolYearFormValues'), function() {}, function() {}); ")
						.f().sx("Modify the years")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listSchoolYear != null && listSchoolYear.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
				.a("onclick", "$('#deleteSchoolYearModal').show(); ")
				.f().sx("Delete the years")
			.g("button");
			{ e("div").a("id", "deleteSchoolYearModal").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-orange ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSchoolYearModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Delete the years").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolYear o = new SchoolYear();
						o.setSiteRequest_(siteRequest_);

						// Form DELETE
						{ e("div").a("id", "deleteSchoolYearForm").f();
							htmlFormPATCHSchoolYear(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-orange ")
							.a("onclick", "deleteSchoolYear(", o.getPk(), "); ")
							.f().sx("Delete the years")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	**/
	public static void htmlSuggestYearGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/year").a("class", "").f();
					p.e("i").a("class", "far fa-calendar-check w3-padding-small ").f().g("i");
					p.sx("see all the years");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "refreshAllYearGenPage", id).a("href", "/year").a("class", "").a("onclick", "patchSchoolYearVals([], {}, function() { addGlow($('#refreshAllYearGenPage", id, "')); }, function() { addError($('#refreshAllYearGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("refresh all the years");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("search years: ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggestFormSchoolYear", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/year?q=objectSuggest:' + encodeURIComponent($('#suggestSchoolYear", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("class", "suggestSchoolYear w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggestSchoolYear")
							.a("id", "suggestSchoolYear", id)
							.a("autocomplete", "off")
							.a("oninput", "suggestSchoolYearObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSchoolYear", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchoolYear", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
