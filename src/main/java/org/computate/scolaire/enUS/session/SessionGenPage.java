package org.computate.scolaire.enUS.session;

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
public class SessionGenPage extends SessionGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchoolSession(Wrap<SearchList<SchoolSession>> c) {
	}

	protected void _schoolSession(Wrap<SchoolSession> c) {
		if(listSchoolSession != null && listSchoolSession.size() == 1)
			c.o(listSchoolSession.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("sessions");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(schoolSession != null && schoolSession.getSessionCompleteName() != null)
			c.o(schoolSession.getSessionCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolSession != null && schoolSession.getSessionCompleteName() != null)
			c.o(schoolSession.getSessionCompleteName());
		else if(schoolSession != null)
			c.o("");
		else if(listSchoolSession == null || listSchoolSession.size() == 0)
			c.o("no session found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/session");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/session-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("duotone");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("graduation-cap");
	}

	@Override public void initDeepSessionGenPage() {
		initSessionGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsSessionGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/SessionPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/AgePage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/SeasonPage.js").f().g("script");
	}

	@Override public void htmlScriptSessionGenPage() {
		l("$(document).ready(function() {");
		tl(1, "suggestSchoolSessionSeasonKey([{'name':'fq','value':'sessionKeys:", siteRequest_.getRequestPk(), "'}], $('#listSchoolSessionSeasonKey_Page'), ", siteRequest_.getRequestPk(), "); ");
		tl(1, "suggestSchoolSessionAgeKeys([{'name':'fq','value':'sessionKey:", siteRequest_.getRequestPk(), "'}], $('#listSchoolSessionAgeKeys_Page'), ", siteRequest_.getRequestPk(), "); ");
		tl(1, "websocketSchoolSession(async function(patchRequest) {");
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
		tl(2, "await patchSchoolSessionVals( [ {name: 'fq', value: 'pk:' + pk} ], {});");
		tl(1, "});");
		l("});");
	}

	public void htmlFormPageSchoolSession(SchoolSession o) {
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
			o.htmSessionStartDay("Page");
			o.htmSessionEndDay("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSeasonKey("Page");
			o.htmAgeKeys("Page");
		} g("div");
	}

	public void htmlFormPOSTSchoolSession(SchoolSession o) {
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
			o.htmSessionStartDay("POST");
			o.htmSessionEndDay("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSeasonKey("POST");
			o.htmAgeKeys("POST");
		} g("div");
	}

	public void htmlFormPATCHSchoolSession(SchoolSession o) {
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
			o.htmSessionStartDay("PATCH");
			o.htmSessionEndDay("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSeasonKey("PATCH");
			o.htmAgeKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionCompleteName("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolSession(SchoolSession o) {
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
			o.htmSessionStartDay("Recherche");
			o.htmSessionEndDay("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSeasonKey("Recherche");
			o.htmAgeKeys("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSessionCompleteName("Recherche");
		} g("div");
	}

	@Override public void htmlBodySessionGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolSession == null || listSchoolSession.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/session").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("sessions").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no session found").g("span");
				} g("span");
			} g("h2");
		} else if(listSchoolSession != null && listSchoolSession.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolSession o = listSchoolSession.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/session").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

				{ e("h1").f();
					{ e("a").a("href", "/session").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listSchoolSession.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listSchoolSession.getQuery(), "_suggested", "");
					Integer rows1 = listSchoolSession.getRows();
					Integer start1 = listSchoolSession.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/session?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/session?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/session?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/session?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-green w3-hover-green ").f();
					{ e("tr").f();
						e("th").f().sx("name").g("th");
						e("th").f().sx("created").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listSchoolSession.getQueryResponse().getHighlighting();
					for(int i = 0; i < listSchoolSession.size(); i++) {
						SchoolSession o = listSchoolSession.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/session/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "fad fa-graduation-cap w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.strSessionCompleteName());
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

		if(listSchoolSession != null && listSchoolSession.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolSession o = listSchoolSession.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolSessionForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageSchoolSession(o);
				}

			} g("div");

		}
		htmlBodyFormsSessionGenPage();
		htmlSuggestSessionGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsSessionGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("id", "refreshThisSessionGenPage")
				.a("onclick", "patchSchoolSessionVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisSessionGenPage')); }, function() { addError($('#refreshThisSessionGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("refresh this session");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#postSchoolSessionModal').show(); ")
			.f().sx("Create a session")
		.g("button");
		{ e("div").a("id", "postSchoolSessionModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolSessionModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Create a session").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolSession o = new SchoolSession();
					o.setSiteRequest_(siteRequest_);

					// Form POST
					{ e("div").a("id", "postSchoolSessionForm").f();
						htmlFormPOSTSchoolSession(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "postSchoolSession($('#postSchoolSessionForm')); ")
						.f().sx("Create a session")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#patchSchoolSessionModal').show(); ")
			.f().sx("Modify the sessions")
		.g("button");
		{ e("div").a("id", "patchSchoolSessionModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolSessionModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modify the sessions").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SchoolSession o = new SchoolSession();
					o.setSiteRequest_(siteRequest_);

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchSchoolSessionFormFilters").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormSearchSchoolSession(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "aSearchSchoolSession($('#patchSchoolSessionFormFilters')); ")
						.f().sx("Search the a session")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchSchoolSessionFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHSchoolSession(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "patchSchoolSession($('#patchSchoolSessionFormFilters'), $('#patchSchoolSessionFormValues'), function() {}, function() {}); ")
						.f().sx("Modify the sessions")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listSchoolSession != null && listSchoolSession.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("onclick", "$('#deleteSchoolSessionModal').show(); ")
				.f().sx("Delete the sessions")
			.g("button");
			{ e("div").a("id", "deleteSchoolSessionModal").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-green ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSchoolSessionModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Delete the sessions").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolSession o = new SchoolSession();
						o.setSiteRequest_(siteRequest_);

						// Form DELETE
						{ e("div").a("id", "deleteSchoolSessionForm").f();
							htmlFormPATCHSchoolSession(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
							.a("onclick", "deleteSchoolSession(", o.getPk(), "); ")
							.f().sx("Delete the sessions")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	**/
	public static void htmlSuggestSessionGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/session").a("class", "").f();
					p.e("i").a("class", "fad fa-graduation-cap w3-padding-small ").f().g("i");
					p.sx("see all the sessions");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "refreshAllSessionGenPage", id).a("href", "/session").a("class", "").a("onclick", "patchSchoolSessionVals([], {}, function() { addGlow($('#refreshAllSessionGenPage", id, "')); }, function() { addError($('#refreshAllSessionGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("refresh all the sessions");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("search sessions: ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggestFormSchoolSession", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/session?q=objectSuggest:' + encodeURIComponent($('#suggestSchoolSession", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("class", "suggestSchoolSession w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggestSchoolSession")
							.a("id", "suggestSchoolSession", id)
							.a("autocomplete", "off")
							.a("oninput", "suggestSchoolSessionObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSchoolSession", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchoolSession", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
