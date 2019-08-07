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
		if(school != null)
			c.o("a school");
		else if(listSchool == null || listSchool.size() == 0)
			c.o("no school found");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(school != null)
			c.o("");
		else if(listSchool == null || listSchool.size() == 0)
			c.o("no school found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/enUS/school");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/enUS/school-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("regular");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("fort-awesome");
	}

	@Override public void initDeepSchoolGenPage() {
		initSchoolGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsSchoolGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/SchoolPage.js").f().g("script");
	}

	@Override public void htmlScriptSchoolGenPage() {
	}

	public void htmlFormPageSchool(School o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("created").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strCreated()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("modified").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strModified()).g("span");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("action", "").a("id", "schoolNameForm").a("style", "display: inline-block; ").f();
					e("label").a("for", "Page_schoolName").a("class", "").f().sx("name of the school").g("label");

					e("input")
						.a("type", "text")
						.a("placeholder", "name of the school")
						.a("class", "setSchoolName w3-input w3-border ")
						.a("name", "setSchoolName")
						.a("id", "Page_schoolName")
						.a("onclick", "removeGlow($(this)); ")
						.a("onchange", "patchSchool($('#SchoolForm'), $('#schoolNameForm')); ")
						.a("value", o.strSchoolName())
					.fg();

				} g("form");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("action", "").a("id", "schoolPhoneNumberForm").a("style", "display: inline-block; ").f();
					e("label").a("for", "Page_schoolPhoneNumber").a("class", "").f().sx("phone number").g("label");

					e("input")
						.a("type", "text")
						.a("placeholder", "phone number")
						.a("class", "setSchoolPhoneNumber w3-input w3-border ")
						.a("name", "setSchoolPhoneNumber")
						.a("id", "Page_schoolPhoneNumber")
						.a("onclick", "removeGlow($(this)); ")
						.a("onchange", "patchSchool($('#SchoolForm'), $('#schoolPhoneNumberForm')); ")
						.a("value", o.strSchoolPhoneNumber())
					.fg();

				} g("form");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("action", "").a("id", "schoolAdministratorNameForm").a("style", "display: inline-block; ").f();
					e("label").a("for", "Page_schoolAdministratorName").a("class", "").f().sx("administrator of the school").g("label");

					e("input")
						.a("type", "text")
						.a("placeholder", "administrator of the school")
						.a("class", "setSchoolAdministratorName w3-input w3-border ")
						.a("name", "setSchoolAdministratorName")
						.a("id", "Page_schoolAdministratorName")
						.a("onclick", "removeGlow($(this)); ")
						.a("onchange", "patchSchool($('#SchoolForm'), $('#schoolAdministratorNameForm')); ")
						.a("value", o.strSchoolAdministratorName())
					.fg();

				} g("form");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("form").a("action", "").a("id", "schoolAddressForm").a("style", "display: inline-block; ").f();
					e("label").a("for", "Page_schoolAddress").a("class", "").f().sx("Address").g("label");

					e("textarea")
						.a("placeholder", "Address")
						.a("class", "setSchoolAddress w3-input w3-border ")
						.a("name", "setSchoolAddress")
						.a("id", "Page_schoolAddress")
						.a("onclick", "removeGlow($(this)); ")
						.a("onchange", "patchSchool($('#SchoolForm'), $('#schoolAddressForm')); ")
					.f().sx(o.strSchoolAddress()).g("textarea");

				} g("form");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTSchool(School o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("created").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strCreated()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("modified").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strModified()).g("span");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "POST_schoolName").a("class", "").f().sx("name of the school").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "name of the school")
					.a("class", "valueSchoolName w3-input w3-border ")
					.a("name", "schoolName")
					.a("id", "POST_schoolName")
					.a("value", o.strSchoolName())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "POST_schoolPhoneNumber").a("class", "").f().sx("phone number").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "phone number")
					.a("class", "valueSchoolPhoneNumber w3-input w3-border ")
					.a("name", "schoolPhoneNumber")
					.a("id", "POST_schoolPhoneNumber")
					.a("value", o.strSchoolPhoneNumber())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "POST_schoolAdministratorName").a("class", "").f().sx("administrator of the school").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "administrator of the school")
					.a("class", "valueSchoolAdministratorName w3-input w3-border ")
					.a("name", "schoolAdministratorName")
					.a("id", "POST_schoolAdministratorName")
					.a("value", o.strSchoolAdministratorName())
				.fg();

			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "POST_schoolAddress").a("class", "").f().sx("Address").g("label");

				e("textarea")
					.a("placeholder", "Address")
					.a("class", "valueSchoolAddress w3-input w3-border ")
					.a("name", "schoolAddress")
					.a("id", "POST_schoolAddress")
				.f().sx(o.strSchoolAddress()).g("textarea");

			} g("div");
		} g("div");
	}

	public void htmlFormPATCHSchool(School o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("created").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strCreated()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("modified").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strModified()).g("span");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "PATCH_schoolName").a("class", "").f().sx("name of the school").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "name of the school")
					.a("class", "setSchoolName w3-input w3-border ")
					.a("name", "setSchoolName")
					.a("id", "PATCH_schoolName")
					.a("value", o.strSchoolName())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "PATCH_schoolPhoneNumber").a("class", "").f().sx("phone number").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "phone number")
					.a("class", "setSchoolPhoneNumber w3-input w3-border ")
					.a("name", "setSchoolPhoneNumber")
					.a("id", "PATCH_schoolPhoneNumber")
					.a("value", o.strSchoolPhoneNumber())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "PATCH_schoolAdministratorName").a("class", "").f().sx("administrator of the school").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "administrator of the school")
					.a("class", "setSchoolAdministratorName w3-input w3-border ")
					.a("name", "setSchoolAdministratorName")
					.a("id", "PATCH_schoolAdministratorName")
					.a("value", o.strSchoolAdministratorName())
				.fg();

			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "PATCH_schoolAddress").a("class", "").f().sx("Address").g("label");

				e("textarea")
					.a("placeholder", "Address")
					.a("class", "setSchoolAddress w3-input w3-border ")
					.a("name", "setSchoolAddress")
					.a("id", "PATCH_schoolAddress")
				.f().sx(o.strSchoolAddress()).g("textarea");

			} g("div");
		} g("div");
	}

	public void htmlFormSearchSchool(School o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("created").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strCreated()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("modified").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strModified()).g("span");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "Recherche_schoolName").a("class", "").f().sx("name of the school").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "name of the school")
					.a("class", "valueSchoolName w3-input w3-border ")
					.a("name", "schoolName")
					.a("id", "Recherche_schoolName")
					.a("value", o.strSchoolName())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "Recherche_schoolPhoneNumber").a("class", "").f().sx("phone number").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "phone number")
					.a("class", "valueSchoolPhoneNumber w3-input w3-border ")
					.a("name", "schoolPhoneNumber")
					.a("id", "Recherche_schoolPhoneNumber")
					.a("value", o.strSchoolPhoneNumber())
				.fg();

			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "Recherche_schoolAdministratorName").a("class", "").f().sx("administrator of the school").g("label");

				e("input")
					.a("type", "text")
					.a("placeholder", "administrator of the school")
					.a("class", "valueSchoolAdministratorName w3-input w3-border ")
					.a("name", "schoolAdministratorName")
					.a("id", "Recherche_schoolAdministratorName")
					.a("value", o.strSchoolAdministratorName())
				.fg();

			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				e("label").a("for", "Recherche_schoolAddress").a("class", "").f().sx("Address").g("label");

				e("textarea")
					.a("placeholder", "Address")
					.a("class", "valueSchoolAddress w3-input w3-border ")
					.a("name", "schoolAddress")
					.a("id", "Recherche_schoolAddress")
				.f().sx(o.strSchoolAddress()).g("textarea");

			} g("div");
		} g("div");
	}

	@Override public void htmlBodySchoolGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchool == null || listSchool.size() == 0) {

			{ e("h1").f();
				if(contextIconCssClasses != null)
					e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("no school found").g("span");
			} g("h1");
		} else if(listSchool != null && listSchool.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*") && params.getJsonObject("query").getJsonArray("fq") == null) {
			if(pageH1 != null) {
				{ e("h1").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("a school").g("span");
				} g("h1");
				School o = listSchool.get(0);
			}
		} else {

			{ e("h1").f();
				if(contextIconCssClasses != null)
					e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("schools").g("span");
			} g("h1");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
						e("th").f().sx("name of the school").g("th");
						e("th").f().sx("phone number").g("th");
						e("th").f().sx("Address").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listSchool.getQueryResponse().getHighlighting();
					for(int i = 0; i < listSchool.size(); i++) {
						School o = listSchool.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = o.getPageUri();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSchoolName());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSchoolPhoneNumber());
								} g("a");
							} g("td");
							{ e("td").f();
								{ e("a").a("href", uri).f();
									sx(o.getSchoolAddress());
								} g("a");
							} g("td");
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listSchool != null && listSchool.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*") && params.getJsonObject("query").getJsonArray("fq") == null) {
			School o = listSchool.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolForm").a("style", "display: inline-block; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valuePk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageSchool(o);
				}

				if(o != null)
					o.htmlBody();

			} g("div");
		}
		htmlBodyFormsSchoolGenPage();
	}

	public void htmlBodyFormsSchoolGenPage() {
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#postSchoolModal').show(); ")
			.f().sx("Create a school")
		.g("button");
		{ e("div").a("id", "postSchoolModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Create a school").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					School o = new School();

					// Form POST
					{ e("form").a("action", "").a("id", "postSchoolForm").f();
						htmlFormPOSTSchool(o);
					} g("form");
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
					e("h2").a("class", "").f().sx("Modify the schools").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					School o = new School();

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchSchoolFormFilters").f();
						htmlFormSearchSchool(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "aSearchSchool($('#patchSchoolFormFilters')); ")
						.f().sx("Search the a school")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchSchoolFormValues").f();
						htmlFormPATCHSchool(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "patchSchool($('#patchSchoolFormFilters'), $('#patchSchoolFormValues')); ")
						.f().sx("Modify the schools")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
			.a("onclick", "$('#deleteSchoolModal').show(); ")
			.f().sx("Delete the schools")
		.g("button");
		{ e("div").a("id", "deleteSchoolModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-pink ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteSchoolModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Delete the schools").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					School o = new School();

					// Form DELETE
					{ e("form").a("action", "").a("id", "deleteSchoolForm").f();
						htmlFormPATCHSchool(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
						.a("onclick", "deleteSchool(); ")
						.f().sx("Delete the schools")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

}
