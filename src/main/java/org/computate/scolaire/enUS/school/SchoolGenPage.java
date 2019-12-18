package org.computate.scolaire.enUS.school;

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
public class SchoolGenPage extends SchoolGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchool(Wrap<SearchList<School>> c) {
	}

	protected void _school(Wrap<School> c) {
		if(listSchool != null && listSchool.size() == 1)
			c.o(listSchool.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("schools");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(school != null && school.getSchoolCompleteName() != null)
			c.o(school.getSchoolCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(school != null && school.getSchoolCompleteName() != null)
			c.o(school.getSchoolCompleteName());
		else if(school != null)
			c.o("");
		else if(listSchool == null || listSchool.size() == 0)
			c.o("no school found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/school");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/school-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("regular");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("school");
	}

	@Override public void initDeepSchoolGenPage() {
		initSchoolGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsSchoolGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/SchoolPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/YearPage.js").f().g("script");
	}

	@Override public void htmlScriptSchoolGenPage() {
		l("$(document).ready(function() {");
		tl(1, "suggestSchoolYearKeys([{'name':'fq','value':'schoolKey:", siteRequest_.getRequestPk(), "'}], $('#listSchoolYearKeys_Page'), ", siteRequest_.getRequestPk(), "); ");
		tl(1, "websocketSchool(async function(patchRequest) {");
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
		tl(2, "await patchSchoolVals( [ {name: 'fq', value: 'pk:' + pk} ], {});");
		tl(1, "});");
		l("});");
	}

	public void htmlFormPageSchool(School o) {
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
			o.htmSchoolName("Page");
			o.htmSchoolAdministratorName("Page");
			o.htmSchoolLocation("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolPhoneNumber("Page");
			o.htmSchoolAddress("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmYearKeys("Page");
		} g("div");
	}

	public void htmlFormPOSTSchool(School o) {
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
			o.htmSchoolName("POST");
			o.htmSchoolAdministratorName("POST");
			o.htmSchoolLocation("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolPhoneNumber("POST");
			o.htmSchoolAddress("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmYearKeys("POST");
		} g("div");
	}

	public void htmlFormPATCHSchool(School o) {
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
			o.htmSchoolName("PATCH");
			o.htmSchoolAdministratorName("PATCH");
			o.htmSchoolLocation("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolPhoneNumber("PATCH");
			o.htmSchoolAddress("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmYearKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolCompleteName("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchool(School o) {
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
			o.htmSchoolName("Recherche");
			o.htmSchoolAdministratorName("Recherche");
			o.htmSchoolLocation("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolPhoneNumber("Recherche");
			o.htmSchoolAddress("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmYearKeys("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolCompleteName("Recherche");
		} g("div");
	}

	@Override public void htmlBodySchoolGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchool == null || listSchool.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/school").a("class", "w3-bar-item w3-btn w3-center w3-block w3-pink w3-hover-pink ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("schools").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-pink ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no school found").g("span");
				} g("span");
			} g("h2");
		} else if(listSchool != null && listSchool.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			School o = listSchool.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/school").a("class", "w3-bar-item w3-btn w3-center w3-block w3-pink w3-hover-pink ").f();
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
					{ e("a").a("href", "/school").a("class", "w3-bar-item w3-btn w3-center w3-block w3-pink w3-hover-pink ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listSchool.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listSchool.getQuery(), "_suggested", "");
					Integer rows1 = listSchool.getRows();
					Integer start1 = listSchool.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/school?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/school?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/school?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/school?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-pink w3-hover-pink ").f();
					{ e("tr").f();
						e("th").f().sx("name").g("th");
						e("th").f().sx("created").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listSchool.getQueryResponse().getHighlighting();
					for(int i = 0; i < listSchool.size(); i++) {
						School o = listSchool.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/school/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "far fa-school w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.strSchoolCompleteName());
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

		if(listSchool != null && listSchool.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			School o = listSchool.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageSchool(o);
				}

			} g("div");

		}
		htmlBodyFormsSchoolGenPage();
		htmlSuggestSchoolGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsSchoolGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
				.a("id", "refreshThisSchoolGenPage")
				.a("onclick", "patchSchoolVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisSchoolGenPage')); }, function() { addError($('#refreshThisSchoolGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("refresh this school");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#postSchoolModal').show(); ")
			.f().sx("Create a school")
		.g("button");
		{ e("div").a("id", "postSchoolModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Create a school").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					School o = new School();
					o.setSiteRequest_(siteRequest_);

					// Form POST
					{ e("div").a("id", "postSchoolForm").f();
						htmlFormPOSTSchool(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "postSchool($('#postSchoolForm')); ")
						.f().sx("Create a school")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#patchSchoolModal').show(); ")
			.f().sx("Modify the schools")
		.g("button");
		{ e("div").a("id", "patchSchoolModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modify the schools").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					School o = new School();
					o.setSiteRequest_(siteRequest_);

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchSchoolFormFilters").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormSearchSchool(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "aSearchSchool($('#patchSchoolFormFilters')); ")
						.f().sx("Search the a school")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchSchoolFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSchool(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "patchSchool($('#patchSchoolFormFilters'), $('#patchSchoolFormValues'), function() {}, function() {}); ")
						.f().sx("Modify the schools")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listSchool != null && listSchool.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
				.a("onclick", "$('#deleteSchoolModal').show(); ")
				.f().sx("Delete the schools")
			.g("button");
			{ e("div").a("id", "deleteSchoolModal").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-pink ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSchoolModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Delete the schools").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						School o = new School();
						o.setSiteRequest_(siteRequest_);

						// Form DELETE
						{ e("div").a("id", "deleteSchoolForm").f();
							htmlFormPATCHSchool(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
							.a("onclick", "deleteSchool(", o.getPk(), "); ")
							.f().sx("Delete the schools")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	**/
	public static void htmlSuggestSchoolGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/school").a("class", "").f();
					p.e("i").a("class", "far fa-school w3-padding-small ").f().g("i");
					p.sx("see all the schools");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "refreshAllSchoolGenPage", id).a("href", "/school").a("class", "").a("onclick", "patchSchoolVals([], {}, function() { addGlow($('#refreshAllSchoolGenPage", id, "')); }, function() { addError($('#refreshAllSchoolGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("refresh all the schools");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("search schools: ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggestFormSchool", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/school?q=objectSuggest:' + encodeURIComponent($('#suggestSchool", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("class", "suggestSchool w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggestSchool")
							.a("id", "suggestSchool", id)
							.a("autocomplete", "off")
							.a("oninput", "suggestSchoolObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSchool", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchool", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
