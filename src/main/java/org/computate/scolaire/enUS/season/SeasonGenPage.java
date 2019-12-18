package org.computate.scolaire.enUS.season;

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
public class SeasonGenPage extends SeasonGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchoolSeason(Wrap<SearchList<SchoolSeason>> c) {
	}

	protected void _schoolSeason(Wrap<SchoolSeason> c) {
		if(listSchoolSeason != null && listSchoolSeason.size() == 1)
			c.o(listSchoolSeason.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("school seasons");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(schoolSeason != null && schoolSeason.getSeasonCompleteName() != null)
			c.o(schoolSeason.getSeasonCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolSeason != null && schoolSeason.getSeasonCompleteName() != null)
			c.o(schoolSeason.getSeasonCompleteName());
		else if(schoolSeason != null)
			c.o("");
		else if(listSchoolSeason == null || listSchoolSeason.size() == 0)
			c.o("no season found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/season");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/season-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("regular");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("sun");
	}

	@Override public void initDeepSeasonGenPage() {
		initSeasonGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsSeasonGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/SeasonPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/SessionPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/YearPage.js").f().g("script");
	}

	@Override public void htmlScriptSeasonGenPage() {
		l("$(document).ready(function() {");
		tl(1, "suggestSchoolSeasonSessionKeys([{'name':'fq','value':'seasonKey:", siteRequest_.getRequestPk(), "'}], $('#listSchoolSeasonSessionKeys_Page'), ", siteRequest_.getRequestPk(), "); ");
		tl(1, "suggestSchoolSeasonYearKey([{'name':'fq','value':'seasonKeys:", siteRequest_.getRequestPk(), "'}], $('#listSchoolSeasonYearKey_Page'), ", siteRequest_.getRequestPk(), "); ");
		tl(1, "websocketSchoolSeason(async function(patchRequest) {");
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
		tl(2, "await patchSchoolSeasonVals( [ {name: 'fq', value: 'pk:' + pk} ], {});");
		tl(1, "});");
		l("});");
	}

	public void htmlFormPageSchoolSeason(SchoolSeason o) {
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
			o.htmSeasonStartDate("Page");
			o.htmSeasonSummer("Page");
			o.htmSeasonWinter("Page");
			o.htmSeasonEnrollmentFee("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionKeys("Page");
			o.htmYearKey("Page");
		} g("div");
	}

	public void htmlFormPOSTSchoolSeason(SchoolSeason o) {
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
			o.htmSeasonStartDate("POST");
			o.htmSeasonSummer("POST");
			o.htmSeasonWinter("POST");
			o.htmSeasonEnrollmentFee("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionKeys("POST");
			o.htmYearKey("POST");
		} g("div");
	}

	public void htmlFormPATCHSchoolSeason(SchoolSeason o) {
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
			o.htmSeasonStartDate("PATCH");
			o.htmSeasonSummer("PATCH");
			o.htmSeasonWinter("PATCH");
			o.htmSeasonEnrollmentFee("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionKeys("PATCH");
			o.htmYearKey("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSeasonCompleteName("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolSeason(SchoolSeason o) {
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
			o.htmSeasonStartDate("Recherche");
			o.htmSeasonSummer("Recherche");
			o.htmSeasonWinter("Recherche");
			o.htmSeasonEnrollmentFee("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionKeys("Recherche");
			o.htmYearKey("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSeasonCompleteName("Recherche");
		} g("div");
	}

	@Override public void htmlBodySeasonGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolSeason == null || listSchoolSeason.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/season").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("school seasons").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-yellow ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no season found").g("span");
				} g("span");
			} g("h2");
		} else if(listSchoolSeason != null && listSchoolSeason.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolSeason o = listSchoolSeason.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/season").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
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
					{ e("a").a("href", "/season").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listSchoolSeason.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listSchoolSeason.getQuery(), "_suggested", "");
					Integer rows1 = listSchoolSeason.getRows();
					Integer start1 = listSchoolSeason.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/season?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/season?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/season?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/season?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-yellow w3-hover-yellow ").f();
					{ e("tr").f();
						e("th").f().sx("name").g("th");
						e("th").f().sx("created").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listSchoolSeason.getQueryResponse().getHighlighting();
					for(int i = 0; i < listSchoolSeason.size(); i++) {
						SchoolSeason o = listSchoolSeason.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/season/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "far fa-sun w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.strSeasonCompleteName());
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

		if(listSchoolSeason != null && listSchoolSeason.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolSeason o = listSchoolSeason.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolSeasonForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageSchoolSeason(o);
				}

			} g("div");

		}
		htmlBodyFormsSeasonGenPage();
		htmlSuggestSeasonGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsSeasonGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
				.a("id", "refreshThisSeasonGenPage")
				.a("onclick", "patchSchoolSeasonVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisSeasonGenPage')); }, function() { addError($('#refreshThisSeasonGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("refresh this season");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#postSchoolSeasonModal').show(); ")
			.f().sx("Create a season")
		.g("button");
		{ e("div").a("id", "postSchoolSeasonModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolSeasonModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Create a season").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolSeason o = new SchoolSeason();
					o.setSiteRequest_(siteRequest_);

					// Form POST
					{ e("div").a("id", "postSchoolSeasonForm").f();
						htmlFormPOSTSchoolSeason(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "postSchoolSeason($('#postSchoolSeasonForm')); ")
						.f().sx("Create a season")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#patchSchoolSeasonModal').show(); ")
			.f().sx("Modify the seasons")
		.g("button");
		{ e("div").a("id", "patchSchoolSeasonModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolSeasonModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modify the seasons").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolSeason o = new SchoolSeason();
					o.setSiteRequest_(siteRequest_);

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchSchoolSeasonFormFilters").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormSearchSchoolSeason(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "aSearchSchoolSeason($('#patchSchoolSeasonFormFilters')); ")
						.f().sx("Search the a season")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchSchoolSeasonFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSchoolSeason(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "patchSchoolSeason($('#patchSchoolSeasonFormFilters'), $('#patchSchoolSeasonFormValues'), function() {}, function() {}); ")
						.f().sx("Modify the seasons")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listSchoolSeason != null && listSchoolSeason.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
				.a("onclick", "$('#deleteSchoolSeasonModal').show(); ")
				.f().sx("Delete the seasons")
			.g("button");
			{ e("div").a("id", "deleteSchoolSeasonModal").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-yellow ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSchoolSeasonModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Delete the seasons").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolSeason o = new SchoolSeason();
						o.setSiteRequest_(siteRequest_);

						// Form DELETE
						{ e("div").a("id", "deleteSchoolSeasonForm").f();
							htmlFormPATCHSchoolSeason(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
							.a("onclick", "deleteSchoolSeason(", o.getPk(), "); ")
							.f().sx("Delete the seasons")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	**/
	public static void htmlSuggestSeasonGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/season").a("class", "").f();
					p.e("i").a("class", "far fa-sun w3-padding-small ").f().g("i");
					p.sx("see all the seasons");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "refreshAllSeasonGenPage", id).a("href", "/season").a("class", "").a("onclick", "patchSchoolSeasonVals([], {}, function() { addGlow($('#refreshAllSeasonGenPage", id, "')); }, function() { addError($('#refreshAllSeasonGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("refresh all the seasons");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("search school seasons: ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggestFormSchoolSeason", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/season?q=objectSuggest:' + encodeURIComponent($('#suggestSchoolSeason", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("class", "suggestSchoolSeason w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggestSchoolSeason")
							.a("id", "suggestSchoolSeason", id)
							.a("autocomplete", "off")
							.a("oninput", "suggestSchoolSeasonObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSchoolSeason", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchoolSeason", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
