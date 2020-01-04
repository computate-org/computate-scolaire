package org.computate.scolaire.enUS.age;

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
public class AgeGenPage extends AgeGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchoolAge(Wrap<SearchList<SchoolAge>> c) {
	}

	protected void _schoolAge(Wrap<SchoolAge> c) {
		if(listSchoolAge != null && listSchoolAge.size() == 1)
			c.o(listSchoolAge.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("ages");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(schoolAge != null && schoolAge.getAgeCompleteName() != null)
			c.o(schoolAge.getAgeCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolAge != null && schoolAge.getAgeCompleteName() != null)
			c.o(schoolAge.getAgeCompleteName());
		else if(schoolAge != null)
			c.o("");
		else if(listSchoolAge == null || listSchoolAge.size() == 0)
			c.o("no age found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/age");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/age-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("duotone");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("birthday-cake");
	}

	@Override public void initDeepAgeGenPage() {
		initAgeGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsAgeGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/AgePage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/BlockPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/SessionPage.js").f().g("script");
	}

	@Override public void htmlScriptAgeGenPage() {
		l("$(document).ready(function() {");
		tl(1, "var pk = ", siteRequest_.getRequestPk(), ";");
		tl(1, "suggestSchoolAgeSessionKey([{'name':'fq','value':'ageKeys:' + pk}], $('#listSchoolAgeSessionKey_Page'), pk); ");
		tl(1, "suggestSchoolAgeBlockKeys([{'name':'fq','value':'ageKey:' + pk}], $('#listSchoolAgeBlockKeys_Page'), pk); ");
		tl(1, "websocketSchoolAge(async function(patchRequest) {");
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
		tl(2, "await patchSchoolAgeVals( [ {name: 'fq', value: 'pk:' + pk} ], {});");
		tl(1, "});");
		l("});");
	}

	public void htmlFormPageSchoolAge(SchoolAge o) {
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
			o.htmAgeStart("Page");
			o.htmAgeEnd("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionKey("Page");
			o.htmBlockKeys("Page");
		} g("div");
	}

	public void htmlFormPOSTSchoolAge(SchoolAge o) {
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
			o.htmAgeStart("POST");
			o.htmAgeEnd("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionKey("POST");
			o.htmBlockKeys("POST");
		} g("div");
	}

	public void htmlFormPATCHSchoolAge(SchoolAge o) {
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
			o.htmAgeStart("PATCH");
			o.htmAgeEnd("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionKey("PATCH");
			o.htmBlockKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolAddress("PATCH");
			o.htmAgeCompleteName("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolAge(SchoolAge o) {
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
			o.htmAgeStart("Recherche");
			o.htmAgeEnd("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionKey("Recherche");
			o.htmBlockKeys("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolAddress("Recherche");
			o.htmAgeCompleteName("Recherche");
		} g("div");
	}

	@Override public void htmlBodyAgeGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolAge == null || listSchoolAge.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/age").a("class", "w3-bar-item w3-btn w3-center w3-block w3-blue w3-hover-blue ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("ages").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-blue ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no age found").g("span");
				} g("span");
			} g("h2");
		} else if(listSchoolAge != null && listSchoolAge.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolAge o = listSchoolAge.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/age").a("class", "w3-bar-item w3-btn w3-center w3-block w3-blue w3-hover-blue ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-blue ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-blue ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

				{ e("h1").f();
					{ e("a").a("href", "/age").a("class", "w3-bar-item w3-btn w3-center w3-block w3-blue w3-hover-blue ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listSchoolAge.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listSchoolAge.getQuery(), "_suggested", "");
					Integer rows1 = listSchoolAge.getRows();
					Integer start1 = listSchoolAge.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/age?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/age?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/age?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/age?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-blue w3-hover-blue ").f();
					{ e("tr").f();
						e("th").f().sx("name").g("th");
						e("th").f().sx("created").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listSchoolAge.getQueryResponse().getHighlighting();
					for(int i = 0; i < listSchoolAge.size(); i++) {
						SchoolAge o = listSchoolAge.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/age/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "fad fa-birthday-cake w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.strAgeCompleteName());
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

		if(listSchoolAge != null && listSchoolAge.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolAge o = listSchoolAge.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolAgeForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageSchoolAge(o);
				}

			} g("div");

		}
		htmlBodyFormsAgeGenPage();
		htmlSuggestAgeGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsAgeGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
				.a("id", "refreshThisAgeGenPage")
				.a("onclick", "patchSchoolAgeVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisAgeGenPage')); }, function() { addError($('#refreshThisAgeGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("refresh this age");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
			.a("onclick", "$('#postSchoolAgeModal').show(); ")
			.f().sx("Create an age")
		.g("button");
		{ e("div").a("id", "postSchoolAgeModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-blue ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolAgeModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Create an age").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolAge o = new SchoolAge();
					o.setSiteRequest_(siteRequest_);

					// Form POST
					{ e("div").a("id", "postSchoolAgeForm").f();
						htmlFormPOSTSchoolAge(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
						.a("onclick", "postSchoolAge($('#postSchoolAgeForm')); ")
						.f().sx("Create an age")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
			.a("onclick", "$('#patchSchoolAgeModal').show(); ")
			.f().sx("Modify the ages")
		.g("button");
		{ e("div").a("id", "patchSchoolAgeModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-blue ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolAgeModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modify the ages").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolAge o = new SchoolAge();
					o.setSiteRequest_(siteRequest_);

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchSchoolAgeFormFilters").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormSearchSchoolAge(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
						.a("onclick", "aSearchSchoolAge($('#patchSchoolAgeFormFilters')); ")
						.f().sx("Search the an age")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchSchoolAgeFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSchoolAge(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
						.a("onclick", "patchSchoolAge($('#patchSchoolAgeFormFilters'), $('#patchSchoolAgeFormValues'), function() {}, function() {}); ")
						.f().sx("Modify the ages")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listSchoolAge != null && listSchoolAge.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
				.a("onclick", "$('#deleteSchoolAgeModal').show(); ")
				.f().sx("Delete the ages")
			.g("button");
			{ e("div").a("id", "deleteSchoolAgeModal").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-blue ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSchoolAgeModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Delete the ages").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolAge o = new SchoolAge();
						o.setSiteRequest_(siteRequest_);

						// Form DELETE
						{ e("div").a("id", "deleteSchoolAgeForm").f();
							htmlFormPATCHSchoolAge(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
							.a("onclick", "deleteSchoolAge(", o.getPk(), "); ")
							.f().sx("Delete the ages")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	**/
	public static void htmlSuggestAgeGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/age").a("class", "").f();
					p.e("i").a("class", "fad fa-birthday-cake w3-padding-small ").f().g("i");
					p.sx("see all the ages");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "refreshAllAgeGenPage", id).a("href", "/age").a("class", "").a("onclick", "patchSchoolAgeVals([], {}, function() { addGlow($('#refreshAllAgeGenPage", id, "')); }, function() { addError($('#refreshAllAgeGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("refresh all the ages");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("search ages: ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggestFormSchoolAge", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/age?q=objectSuggest:' + encodeURIComponent($('#suggestSchoolAge", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("class", "suggestSchoolAge w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggestSchoolAge")
							.a("id", "suggestSchoolAge", id)
							.a("autocomplete", "off")
							.a("oninput", "suggestSchoolAgeObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSchoolAge", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchoolAge", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
