package org.computate.scolaire.enUS.user;

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
public class SiteUserGenPage extends SiteUserGenPageGen<ClusterPage> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSiteUser(Wrap<SearchList<SiteUser>> c) {
	}

	protected void _siteUser(Wrap<SiteUser> c) {
		if(listSiteUser != null && listSiteUser.size() == 1)
			c.o(listSiteUser.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
		if(siteUser != null)
			c.o("a site user");
		else if(listSiteUser == null || listSiteUser.size() == 0)
			c.o("no site user found");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(siteUser != null)
			c.o("");
		else if(listSiteUser == null || listSiteUser.size() == 0)
			c.o("no site user found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/enUS/user");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/enUS/user-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("regular");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("book");
	}

	@Override public void initDeepSiteUserGenPage() {
		initSiteUserGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsSiteUserGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/SiteUserPage.js").f().g("script");
	}

	@Override public void htmlScriptSiteUserGenPage() {
	}

	public void htmlFormPageSiteUser(SiteUser o) {
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
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("user ID").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strUserId()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("see archived").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strSeeArchived()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("see deleted").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strSeeDeleted()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTSiteUser(SiteUser o) {
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
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("user ID").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strUserId()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("see archived").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strSeeArchived()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("see deleted").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strSeeDeleted()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHSiteUser(SiteUser o) {
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
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("user ID").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strUserId()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("see archived").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strSeeArchived()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("see deleted").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strSeeDeleted()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormSearchSiteUser(SiteUser o) {
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
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("user ID").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strUserId()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("see archived").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strSeeArchived()).g("span");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "").f();
					e("label").a("class", "").f().sx("see deleted").g("label");
				} g("div");
				{ e("div").a("class", "").f();
					e("span").f().sx(o.strSeeDeleted()).g("span");
				} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodySiteUserGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSiteUser == null || listSiteUser.size() == 0) {

			{ e("h1").f();
				if(contextIconCssClasses != null)
					e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("no site user found").g("span");
			} g("h1");
		} else if(listSiteUser != null && listSiteUser.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*") && params.getJsonObject("query").getJsonArray("fq") == null) {
			if(pageH1 != null) {
				{ e("h1").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("a site user").g("span");
				} g("h1");
				SiteUser o = listSiteUser.get(0);
			}
		} else {

			{ e("h1").f();
				if(contextIconCssClasses != null)
					e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("site users").g("span");
			} g("h1");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listSiteUser.getQueryResponse().getHighlighting();
					for(int i = 0; i < listSiteUser.size(); i++) {
						SiteUser o = listSiteUser.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/enUS/user/" + o.getPk();
						{ e("tr").f();
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listSiteUser != null && listSiteUser.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*") && params.getJsonObject("query").getJsonArray("fq") == null) {
			SiteUser o = listSiteUser.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SiteUserForm").a("style", "display: inline-block; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valuePk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageSiteUser(o);
				}

				if(o != null)
					o.htmlBody();

			} g("div");
		}
		htmlBodyFormsSiteUserGenPage();
	}

	public void htmlBodyFormsSiteUserGenPage() {
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#patchSiteUserModal').show(); ")
			.f().sx("Modify the site users")
		.g("button");
		{ e("div").a("id", "patchSiteUserModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSiteUserModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modify the site users").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					SiteUser o = new SiteUser();

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchSiteUserFormFilters").f();
						htmlFormSearchSiteUser(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "aSearchSiteUser($('#patchSiteUserFormFilters')); ")
						.f().sx("Search the a site user")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchSiteUserFormValues").f();
						htmlFormPATCHSiteUser(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "patchSiteUser($('#patchSiteUserFormFilters'), $('#patchSiteUserFormValues')); ")
						.f().sx("Modify the site users")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

}
