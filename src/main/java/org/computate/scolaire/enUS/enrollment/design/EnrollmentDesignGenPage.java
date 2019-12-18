package org.computate.scolaire.enUS.enrollment.design;

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
public class EnrollmentDesignGenPage extends EnrollmentDesignGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listEnrollmentDesign(Wrap<SearchList<EnrollmentDesign>> c) {
	}

	protected void _enrollmentDesign(Wrap<EnrollmentDesign> c) {
		if(listEnrollmentDesign != null && listEnrollmentDesign.size() == 1)
			c.o(listEnrollmentDesign.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("enrollment designs");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(enrollmentDesign != null && enrollmentDesign.getEnrollmentDesignCompleteName() != null)
			c.o(enrollmentDesign.getEnrollmentDesignCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(enrollmentDesign != null && enrollmentDesign.getEnrollmentDesignCompleteName() != null)
			c.o(enrollmentDesign.getEnrollmentDesignCompleteName());
		else if(enrollmentDesign != null)
			c.o("");
		else if(listEnrollmentDesign == null || listEnrollmentDesign.size() == 0)
			c.o("no enrollment design found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/enrollment-design");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/enrollment-design-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("regular");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("bell");
	}

	@Override public void initDeepEnrollmentDesignGenPage() {
		initEnrollmentDesignGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsEnrollmentDesignGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/EnrollmentDesignPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/HtmlPartPage.js").f().g("script");
	}

	@Override public void htmlScriptEnrollmentDesignGenPage() {
		l("$(document).ready(function() {");
		tl(1, "suggestEnrollmentDesignHtmlPartKeys([{'name':'fq','value':'enrollmentDesignKey:", siteRequest_.getRequestPk(), "'}], $('#listEnrollmentDesignHtmlPartKeys_Page'), ", siteRequest_.getRequestPk(), "); ");
		tl(1, "websocketEnrollmentDesign(async function(patchRequest) {");
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
		tl(2, "await patchEnrollmentDesignVals( [ {name: 'fq', value: 'pk:' + pk} ], {});");
		tl(1, "});");
		l("});");
	}

	public void htmlFormPageEnrollmentDesign(EnrollmentDesign o) {
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
			o.htmYearKey("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlPartKeys("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("Page");
		} g("div");
	}

	public void htmlFormPOSTEnrollmentDesign(EnrollmentDesign o) {
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
			o.htmYearKey("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlPartKeys("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("POST");
		} g("div");
	}

	public void htmlFormPATCHEnrollmentDesign(EnrollmentDesign o) {
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
			o.htmYearKey("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlPartKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentDesignCompleteName("PATCH");
		} g("div");
	}

	public void htmlFormSearchEnrollmentDesign(EnrollmentDesign o) {
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
			o.htmYearKey("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlPartKeys("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentDesignCompleteName("Recherche");
		} g("div");
	}

	@Override public void htmlBodyEnrollmentDesignGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listEnrollmentDesign == null || listEnrollmentDesign.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/enrollment-design").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("enrollment designs").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-indigo ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no enrollment design found").g("span");
				} g("span");
			} g("h2");
		} else if(listEnrollmentDesign != null && listEnrollmentDesign.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			EnrollmentDesign o = listEnrollmentDesign.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/enrollment-design").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-indigo ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-indigo ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

				{ e("h1").f();
					{ e("a").a("href", "/enrollment-design").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listEnrollmentDesign.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listEnrollmentDesign.getQuery(), "_suggested", "");
					Integer rows1 = listEnrollmentDesign.getRows();
					Integer start1 = listEnrollmentDesign.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enrollment-design?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enrollment-design?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/enrollment-design?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/enrollment-design?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-indigo w3-hover-indigo ").f();
					{ e("tr").f();
						e("th").f().sx("name").g("th");
						e("th").f().sx("created").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listEnrollmentDesign.getQueryResponse().getHighlighting();
					for(int i = 0; i < listEnrollmentDesign.size(); i++) {
						EnrollmentDesign o = listEnrollmentDesign.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/enrollment-design/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "far fa-bell w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.strEnrollmentDesignCompleteName());
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

		if(listEnrollmentDesign != null && listEnrollmentDesign.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			EnrollmentDesign o = listEnrollmentDesign.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "EnrollmentDesignForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageEnrollmentDesign(o);
				}

			} g("div");

		}
		htmlBodyFormsEnrollmentDesignGenPage();
		htmlSuggestEnrollmentDesignGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsEnrollmentDesignGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("id", "refreshThisEnrollmentDesignGenPage")
				.a("onclick", "patchEnrollmentDesignVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisEnrollmentDesignGenPage')); }, function() { addError($('#refreshThisEnrollmentDesignGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("refresh this enrollment design");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#postEnrollmentDesignModal').show(); ")
			.f().sx("Create an enrollment design")
		.g("button");
		{ e("div").a("id", "postEnrollmentDesignModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-indigo ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postEnrollmentDesignModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Create an enrollment design").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					EnrollmentDesign o = new EnrollmentDesign();
					o.setSiteRequest_(siteRequest_);

					// Form POST
					{ e("div").a("id", "postEnrollmentDesignForm").f();
						htmlFormPOSTEnrollmentDesign(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
						.a("onclick", "postEnrollmentDesign($('#postEnrollmentDesignForm')); ")
						.f().sx("Create an enrollment design")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
			.a("onclick", "$('#patchEnrollmentDesignModal').show(); ")
			.f().sx("Modify the enrollment designs")
		.g("button");
		{ e("div").a("id", "patchEnrollmentDesignModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-indigo ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchEnrollmentDesignModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modify the enrollment designs").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					EnrollmentDesign o = new EnrollmentDesign();
					o.setSiteRequest_(siteRequest_);

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchEnrollmentDesignFormFilters").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormSearchEnrollmentDesign(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
						.a("onclick", "aSearchEnrollmentDesign($('#patchEnrollmentDesignFormFilters')); ")
						.f().sx("Search the an enrollment design")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchEnrollmentDesignFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHEnrollmentDesign(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
						.a("onclick", "patchEnrollmentDesign($('#patchEnrollmentDesignFormFilters'), $('#patchEnrollmentDesignFormValues'), function() {}, function() {}); ")
						.f().sx("Modify the enrollment designs")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listEnrollmentDesign != null && listEnrollmentDesign.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("onclick", "$('#deleteEnrollmentDesignModal').show(); ")
				.f().sx("Delete the enrollment designs")
			.g("button");
			{ e("div").a("id", "deleteEnrollmentDesignModal").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-indigo ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteEnrollmentDesignModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Delete the enrollment designs").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						EnrollmentDesign o = new EnrollmentDesign();
						o.setSiteRequest_(siteRequest_);

						// Form DELETE
						{ e("div").a("id", "deleteEnrollmentDesignForm").f();
							htmlFormPATCHEnrollmentDesign(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
							.a("onclick", "deleteEnrollmentDesign(", o.getPk(), "); ")
							.f().sx("Delete the enrollment designs")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	**/
	public static void htmlSuggestEnrollmentDesignGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/enrollment-design").a("class", "").f();
					p.e("i").a("class", "far fa-bell w3-padding-small ").f().g("i");
					p.sx("see all the enrollment designs");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "refreshAllEnrollmentDesignGenPage", id).a("href", "/enrollment-design").a("class", "").a("onclick", "patchEnrollmentDesignVals([], {}, function() { addGlow($('#refreshAllEnrollmentDesignGenPage", id, "')); }, function() { addError($('#refreshAllEnrollmentDesignGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("refresh all the enrollment designs");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("search enrollment designs: ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggestFormEnrollmentDesign", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/enrollment-design?q=objectSuggest:' + encodeURIComponent($('#suggestEnrollmentDesign", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("class", "suggestEnrollmentDesign w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggestEnrollmentDesign")
							.a("id", "suggestEnrollmentDesign", id)
							.a("autocomplete", "off")
							.a("oninput", "suggestEnrollmentDesignObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListEnrollmentDesign", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListEnrollmentDesign", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
