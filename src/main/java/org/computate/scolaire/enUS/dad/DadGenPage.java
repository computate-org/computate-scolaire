package org.computate.scolaire.enUS.dad;

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
public class DadGenPage extends DadGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchoolDad(Wrap<SearchList<SchoolDad>> c) {
	}

	protected void _schoolDad(Wrap<SchoolDad> c) {
		if(listSchoolDad != null && listSchoolDad.size() == 1)
			c.o(listSchoolDad.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("dads");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(schoolDad != null && schoolDad.getDadCompleteName() != null)
			c.o(schoolDad.getDadCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolDad != null && schoolDad.getDadCompleteName() != null)
			c.o(schoolDad.getDadCompleteName());
		else if(schoolDad != null)
			c.o("");
		else if(listSchoolDad == null || listSchoolDad.size() == 0)
			c.o("no dad found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/dad");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/dad-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("regular");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("male");
	}

	@Override public void initDeepDadGenPage() {
		initDadGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsDadGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/DadPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/EnrollmentPage.js").f().g("script");
	}

	@Override public void htmlScriptDadGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(siteRequest_.getRequestPk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(2, "suggestSchoolDadEnrollmentKeys([{'name':'fq','value':'dadKeys:' + pk}], $('#listSchoolDadEnrollmentKeys_Page'), pk); ");
		tl(1, "}");
		tl(1, "websocketSchoolDad(websocketSchoolDadInner);");
		l("});");
	}

	public void htmlFormPageSchoolDad(SchoolDad o) {
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
			o.htmPersonEmergencyContact("Page");
			o.htmPersonReceiveEmail("Page");
			o.htmPersonPickup("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("Page");
		} g("div");
	}

	public void htmlFormPOSTSchoolDad(SchoolDad o) {
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
			o.htmPersonEmergencyContact("POST");
			o.htmPersonReceiveEmail("POST");
			o.htmPersonPickup("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("POST");
		} g("div");
	}

	public void htmlFormPUTSchoolDad(SchoolDad o) {
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
			o.htmPersonEmergencyContact("PUT");
			o.htmPersonReceiveEmail("PUT");
			o.htmPersonPickup("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("PUT");
		} g("div");
	}

	public void htmlFormPATCHSchoolDad(SchoolDad o) {
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
			o.htmPersonEmergencyContact("PATCH");
			o.htmPersonReceiveEmail("PATCH");
			o.htmPersonPickup("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolDad(SchoolDad o) {
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
			o.htmPersonEmergencyContact("Recherche");
			o.htmPersonReceiveEmail("Recherche");
			o.htmPersonPickup("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjectTitle("Recherche");
		} g("div");
	}

	@Override public void htmlBodyDadGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolDad == null || listSchoolDad.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/dad").a("class", "w3-bar-item w3-btn w3-center w3-block w3-light-blue w3-hover-light-blue ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("dads").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-light-blue ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no dad found").g("span");
				} g("span");
			} g("h2");
		} else if(listSchoolDad != null && listSchoolDad.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolDad o = listSchoolDad.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/dad").a("class", "w3-bar-item w3-btn w3-center w3-block w3-light-blue w3-hover-light-blue ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-light-blue ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-light-blue ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/dad").a("class", "w3-bar-item w3-btn w3-center w3-block w3-light-blue w3-hover-light-blue ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listSchoolDad.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listSchoolDad.getQuery(), "_suggested", "");
					Integer rows1 = listSchoolDad.getRows();
					Integer start1 = listSchoolDad.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/dad?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/dad?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/dad?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/dad?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").a("class", "w3-light-blue w3-hover-light-blue ").f();
					{ e("tr").f();
						e("th").f().sx("created").g("th");
						e("th").f().sx("").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listSchoolDad.getQueryResponse().getHighlighting();
					for(int i = 0; i < listSchoolDad.size(); i++) {
						SchoolDad o = listSchoolDad.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/dad/" + o.getPk();
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
									e("i").a("class", "far fa-male ").f().g("i");
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

		if(listSchoolDad != null && listSchoolDad.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolDad o = listSchoolDad.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolDadForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageSchoolDad(o);
				}

			} g("div");

		}
		htmlBodyFormsDadGenPage();
		htmlSuggestDadGenPage(this, null);
		g("div");
	}

	public void htmlBodyFormsDadGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		{ e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
				.a("id", "refreshThisDadGenPage")
				.a("onclick", "patchSchoolDadVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisDadGenPage')); }, function() { addError($('#refreshThisDadGenPage')); }); return false; ").f();
				e("i").a("class", "fas fa-sync-alt ").f().g("i");
			sx("refresh this dad");
		} g("button");

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
			.a("onclick", "$('#postSchoolDadModal').show(); ")
			.f().sx("Create a dad")
		.g("button");
		{ e("div").a("id", "postSchoolDadModal").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-light-blue ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolDadModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Create a dad").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolDad o = new SchoolDad();
						o.setSiteRequest_(siteRequest_);

						// Form POST
						{ e("div").a("id", "postSchoolDadForm").f();
							htmlFormPOSTSchoolDad(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-blue ")
							.a("onclick", "postSchoolDad($('#postSchoolDadForm')); ")
							.f().sx("Create a dad")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
			.a("onclick", "$('#putSchoolDadModal').show(); ")
			.f().sx("Duplicate the dads")
		.g("button");
		{ e("div").a("id", "putSchoolDadModal").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-light-blue ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putSchoolDadModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Duplicate the dads").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolDad o = new SchoolDad();
						o.setSiteRequest_(siteRequest_);

						// FormValues PUT
						{ e("form").a("action", "").a("id", "putSchoolDadFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPUTSchoolDad(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-blue ")
							.a("onclick", "putSchoolDad($('#putSchoolDadFormValues')); ")
							.f().sx("Duplicate the dads")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
			.a("onclick", "$('#patchSchoolDadModal').show(); ")
			.f().sx("Modify the dads")
		.g("button");
		{ e("div").a("id", "patchSchoolDadModal").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-light-blue ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolDadModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Modify the dads").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						SchoolDad o = new SchoolDad();
						o.setSiteRequest_(siteRequest_);

						// FormValues PATCH
						{ e("form").a("action", "").a("id", "patchSchoolDadFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPATCHSchoolDad(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-blue ")
							.a("onclick", "patchSchoolDad($('#patchSchoolDadFormFilters'), $('#patchSchoolDadFormValues'), function() {}, function() {}); ")
							.f().sx("Modify the dads")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		if(listSchoolDad != null && listSchoolDad.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
				.a("onclick", "$('#deleteSchoolDadModal').show(); ")
				.f().sx("Delete the dads")
			.g("button");
			{ e("div").a("id", "deleteSchoolDadModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-blue ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSchoolDadModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Delete the dads").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolDad o = new SchoolDad();
							o.setSiteRequest_(siteRequest_);

							// Form DELETE
							{ e("div").a("id", "deleteSchoolDadForm").f();
								htmlFormPATCHSchoolDad(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-blue ")
								.a("onclick", "deleteSchoolDad(", o.getPk(), "); ")
								.f().sx("Delete the dads")
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
	public static void htmlSuggestDadGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/dad").a("class", "").f();
					p.e("i").a("class", "far fa-male ").f().g("i");
					p.sx("see all the dads");
				} p.g("a");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("id", "refreshAllDadGenPage", id).a("href", "/dad").a("class", "").a("onclick", "patchSchoolDadVals([], {}, function() { addGlow($('#refreshAllDadGenPage", id, "')); }, function() { addError($('#refreshAllDadGenPage", id, "')); }); return false; ").f();
					p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
					p.sx("refresh all the dads");
				} p.g("a");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("search dads: ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-bar ").f();

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("class", "suggestSchoolDad w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggestSchoolDad")
				.a("id", "suggestSchoolDad", id)
				.a("autocomplete", "off")
				.a("oninput", "suggestSchoolDadObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSchoolDad", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
				.fg();

		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchoolDad", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
	}

}
