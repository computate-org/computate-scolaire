package org.computate.scolaire.enUS.html.part;

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
public class HtmlPartGenPage extends HtmlPartGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listHtmlPart(Wrap<SearchList<HtmlPart>> c) {
	}

	protected void _htmlPart(Wrap<HtmlPart> c) {
		if(listHtmlPart != null && listHtmlPart.size() == 1)
			c.o(listHtmlPart.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("HTML parts");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(htmlPart != null && htmlPart.getObjectTitle() != null)
			c.o(htmlPart.getObjectTitle());
		else if(htmlPart != null)
			c.o("");
		else if(listHtmlPart == null || listHtmlPart.size() == 0)
			c.o("no HTML part found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/html-part");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/html-part-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("regular");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("sun");
	}

	@Override public void initDeepHtmlPartGenPage() {
		initHtmlPartGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsHtmlPartGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/HtmlPartPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/EnrollmentDesignPage.js").f().g("script");
	}

	@Override public void htmlScriptHtmlPartGenPage() {
		l("$(document).ready(function() {");
		tl(1, "suggestHtmlPartEnrollmentDesignKey($('#formHtmlPartEnrollmentDesignKey'), $('#listHtmlPartEnrollmentDesignKey_Page')); ");
		tl(1, "websocketHtmlPart(); ");
		l("});");
	}

	public void htmlFormPageHtmlPart(HtmlPart o) {
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
			o.htmEnrollmentDesignKey("Page");
			o.htmHtmlLink("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlElement("Page");
			o.htmHtmlId("Page");
			o.htmHtmlClasses("Page");
			o.htmHtmlStyle("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlBefore("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlAfter("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlText("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVar("Page");
			o.htmHtmlVarInput("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSort1("Page");
			o.htmSort2("Page");
			o.htmSort3("Page");
			o.htmSort4("Page");
			o.htmSort5("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSort6("Page");
			o.htmSort7("Page");
			o.htmSort8("Page");
			o.htmSort9("Page");
			o.htmSort10("Page");
		} g("div");
	}

	public void htmlFormPOSTHtmlPart(HtmlPart o) {
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
			o.htmEnrollmentDesignKey("POST");
			o.htmHtmlLink("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlElement("POST");
			o.htmHtmlId("POST");
			o.htmHtmlClasses("POST");
			o.htmHtmlStyle("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlBefore("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlAfter("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlText("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVar("POST");
			o.htmHtmlVarInput("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSort1("POST");
			o.htmSort2("POST");
			o.htmSort3("POST");
			o.htmSort4("POST");
			o.htmSort5("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSort6("POST");
			o.htmSort7("POST");
			o.htmSort8("POST");
			o.htmSort9("POST");
			o.htmSort10("POST");
		} g("div");
	}

	public void htmlFormPATCHHtmlPart(HtmlPart o) {
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
			o.htmEnrollmentDesignKey("PATCH");
			o.htmHtmlLink("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlElement("PATCH");
			o.htmHtmlId("PATCH");
			o.htmHtmlClasses("PATCH");
			o.htmHtmlStyle("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlBefore("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlAfter("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlText("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVar("PATCH");
			o.htmHtmlVarInput("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSort1("PATCH");
			o.htmSort2("PATCH");
			o.htmSort3("PATCH");
			o.htmSort4("PATCH");
			o.htmSort5("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSort6("PATCH");
			o.htmSort7("PATCH");
			o.htmSort8("PATCH");
			o.htmSort9("PATCH");
			o.htmSort10("PATCH");
		} g("div");
	}

	public void htmlFormSearchHtmlPart(HtmlPart o) {
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
			o.htmEnrollmentDesignKey("Recherche");
			o.htmHtmlLink("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlElement("Recherche");
			o.htmHtmlId("Recherche");
			o.htmHtmlClasses("Recherche");
			o.htmHtmlStyle("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlBefore("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlAfter("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlText("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmHtmlVar("Recherche");
			o.htmHtmlVarInput("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSort1("Recherche");
			o.htmSort2("Recherche");
			o.htmSort3("Recherche");
			o.htmSort4("Recherche");
			o.htmSort5("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSort6("Recherche");
			o.htmSort7("Recherche");
			o.htmSort8("Recherche");
			o.htmSort9("Recherche");
			o.htmSort10("Recherche");
		} g("div");
	}

	@Override public void htmlBodyHtmlPartGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listHtmlPart == null || listHtmlPart.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/html-part").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("HTML parts").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-yellow ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no HTML part found").g("span");
				} g("span");
			} g("h2");
		} else if(listHtmlPart != null && listHtmlPart.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			HtmlPart o = listHtmlPart.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/html-part").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
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
					{ e("a").a("href", "/html-part").a("class", "w3-bar-item w3-btn w3-center w3-block w3-yellow w3-hover-yellow ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listHtmlPart.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listHtmlPart.getQuery(), "_suggested", "");
					Integer rows1 = listHtmlPart.getRows();
					Integer start1 = listHtmlPart.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/html-part?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/html-part?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/html-part?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/html-part?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-yellow w3-hover-yellow ").f();
					{ e("tr").f();
						e("th").f().sx("created").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listHtmlPart.getQueryResponse().getHighlighting();
					for(int i = 0; i < listHtmlPart.size(); i++) {
						HtmlPart o = listHtmlPart.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/html-part/" + o.getPk();
						{ e("tr").f();
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

		if(listHtmlPart != null && listHtmlPart.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			HtmlPart o = listHtmlPart.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "HtmlPartForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valuePk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageHtmlPart(o);
				}

			} g("div");

		}
		htmlBodyFormsHtmlPartGenPage();
		htmlSuggestHtmlPartGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsHtmlPartGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
				.a("id", "refreshThisHtmlPartGenPage")
				.a("onclick", "patchHtmlPartVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisHtmlPartGenPage')); }, function() { addError($('#refreshThisHtmlPartGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
			sx("refresh this HTML part");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#postHtmlPartModal').show(); ")
			.f().sx("Create an HTML part")
		.g("button");
		{ e("div").a("id", "postHtmlPartModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postHtmlPartModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Create an HTML part").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					HtmlPart o = new HtmlPart();
					o.setSiteRequest_(siteRequest_);

					// Form POST
					{ e("div").a("id", "postHtmlPartForm").f();
						htmlFormPOSTHtmlPart(o);
					} g("div");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "postHtmlPart($('#postHtmlPartForm')); ")
						.f().sx("Create an HTML part")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
			.a("onclick", "$('#patchHtmlPartModal').show(); ")
			.f().sx("Modify the HTML parts")
		.g("button");
		{ e("div").a("id", "patchHtmlPartModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-yellow ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchHtmlPartModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "w3-padding ").f().sx("Modify the HTML parts").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					HtmlPart o = new HtmlPart();
					o.setSiteRequest_(siteRequest_);

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchHtmlPartFormFilters").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormSearchHtmlPart(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "aSearchHtmlPart($('#patchHtmlPartFormFilters')); ")
						.f().sx("Search the an HTML part")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchHtmlPartFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHHtmlPart(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
						.a("onclick", "patchHtmlPart($('#patchHtmlPartFormFilters'), $('#patchHtmlPartFormValues'), function() {}, function() {}); ")
						.f().sx("Modify the HTML parts")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		if(listHtmlPart != null && listHtmlPart.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
				.a("onclick", "$('#deleteHtmlPartModal').show(); ")
				.f().sx("Delete the HTML parts")
			.g("button");
			{ e("div").a("id", "deleteHtmlPartModal").a("class", "w3-modal ").f();
				{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-yellow ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteHtmlPartModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Delete the HTML parts").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						HtmlPart o = new HtmlPart();
						o.setSiteRequest_(siteRequest_);

						// Form DELETE
						{ e("div").a("id", "deleteHtmlPartForm").f();
							htmlFormPATCHHtmlPart(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
							.a("onclick", "deleteHtmlPart(", o.getPk(), "); ")
							.f().sx("Delete the HTML parts")
						.g("button");

					} g("div");
				} g("div");
			} g("div");

		}
		g("div");
	}

	/**
	**/
	public static void htmlSuggestHtmlPartGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("href", "/html-part").a("class", "").f();
					p.e("i").a("class", "far fa-sun w3-padding-small ").f().g("i");
					p.sx("see all the HTML parts");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("a").a("id", "refreshAllHtmlPartGenPage", id).a("href", "/html-part").a("class", "").a("onclick", "patchHtmlPartVals([], {}, function() { addGlow($('#refreshAllHtmlPartGenPage", id, "')); }, function() { addError($('#refreshAllHtmlPartGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					p.sx("refresh all the HTML parts");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("search HTML parts: ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("div").a("class", "w3-cell-row ").f();

					p.e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ p.e("form").a("action", "").a("id", "suggestFormHtmlPart", id).a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); window.location.href='/html-part?q=objectSuggest:' + encodeURIComponent($('#suggestHtmlPart", id, "').val()); return false; ").f();
						p.e("input")
							.a("type", "text")
							.a("class", "suggestHtmlPart w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggestHtmlPart")
							.a("id", "suggestHtmlPart", id)
							.a("autocomplete", "off")
							.a("oninput", "suggestHtmlPartObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListHtmlPart", id, "')); ")
							.fg();

					} p.g("form");
				} p.g("div");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row w3-padding ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListHtmlPart", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
