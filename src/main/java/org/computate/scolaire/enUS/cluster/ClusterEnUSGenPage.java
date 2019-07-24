package org.computate.scolaire.enUS.cluster;

import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.util.List;
import org.computate.scolaire.enUS.page.part.PagePart;
import java.lang.Long;
import java.lang.String;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.lang.Boolean;
import org.computate.scolaire.enUS.page.PageLayout;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.time.LocalDateTime;
import java.time.LocalDate;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.core.json.JsonArray;
import java.net.URLDecoder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;


/**
 * Translate: false
 **/
public class ClusterEnUSGenPage extends ClusterEnUSGenPageGen<PageLayout> {

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listCluster(Wrap<SearchList<Cluster>> c) {
	}

	protected void _cluster(Wrap<Cluster> c) {
		if(listCluster != null && listCluster.size() == 1)
			c.o(listCluster.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
		if(cluster != null)
			c.o("");
		else if(listCluster == null || listCluster.size() == 0)
			c.o("");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(cluster != null)
			c.o("");
		else if(listCluster == null || listCluster.size() == 0)
			c.o("");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/enUS/cluster");
	}

	@Override protected void _pageUriFrFR(Wrap<String> c) {
		c.o("/frFR/cluster");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/enUS/cluster-999.png");
	}

	@Override public void initDeepClusterEnUSGenPage() {
		initClusterEnUSGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsClusterEnUSGenPage() {
		e("script").a("src", staticBaseUrl, "/js/ClusterEnUSPage.js").f().g("script");
	}

	protected void _pageUriCluster(Wrap<String> c) {
			c.o("/enUS/cluster");
	}

	@Override public void htmlScriptClusterEnUSGenPage() {
	}

	public void htmlFormPageCluster(Cluster o) {
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
	}

	public void htmlFormPOSTCluster(Cluster o) {
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
	}

	public void htmlFormPATCHCluster(Cluster o) {
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
	}

	public void htmlFormSearchCluster(Cluster o) {
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
	}

	@Override public void htmlBodyClusterEnUSGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listCluster == null || listCluster.size() == 0) {

			{ e("h1").f();
				if(contextIconCssClasses != null)
					e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("").g("span");
			} g("h1");
		} else if(listCluster != null && listCluster.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*") && params.getJsonObject("query").getJsonArray("fq") == null) {
			if(pageH1 != null) {
				{ e("h1").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("").g("span");
				} g("h1");
				Cluster o = listCluster.get(0);
			}
		} else {

			{ e("h1").f();
				if(contextIconCssClasses != null)
					e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("").g("span");
			} g("h1");
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listCluster.getQueryResponse().getHighlighting();
					for(int i = 0; i < listCluster.size(); i++) {
						Cluster o = listCluster.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/enUS/cluster/" + o.getPk();
						{ e("tr").f();
						} g("tr");
					}
				} g("tbody");
			} g("table");
		}

		if(listCluster != null && listCluster.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*") && params.getJsonObject("query").getJsonArray("fq") == null) {
			Cluster o = listCluster.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "/api/cluster").a("id", "ClusterForm").a("style", "display: inline-block; ").f();
						e("input")
						.a("name", "pk")
						.a("class", "valuePk")
						.a("type", "hidden")
						.a("value", o.getPk())
						.fg();
					} g("form");
					htmlFormPageCluster(o);
				}

			} g("div");
		}
		htmlBodyFormsClusterEnUSGenPage();
	}

	public void htmlBodyFormsClusterEnUSGenPage() {
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3- ")
			.a("onclick", "$('#postClusterModale').show(); ")
			.f().sx("Create null")
		.g("button");
		{ e("div").a("id", "postClusterModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3- ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postClusterModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Create null").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Cluster o = new Cluster();

					// Form POST
					{ e("form").a("action", "/api/cluster").a("id", "postClusterForm").f();
						htmlFormPOSTCluster(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3- ")
						.a("onclick", "postCluster($('#postClusterForm')); ")
						.f().sx("Create null")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3- ")
			.a("onclick", "$('#patchClusterModale').show(); ")
			.f().sx("Modify the null")
		.g("button");
		{ e("div").a("id", "patchClusterModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3- ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchClusterModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modify the null").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Cluster o = new Cluster();

					// FormFilters PATCH
					{ e("form").a("action", "/api/cluster").a("id", "patchClusterFormFilters").f();
						htmlFormSearchCluster(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3- ")
						.a("onclick", "aSearchCluster($('#patchClusterFormFilters')); ")
						.f().sx("Search the null")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "/api/cluster").a("id", "patchClusterFormValues").f();
						htmlFormPATCHCluster(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3- ")
						.a("onclick", "patchCluster($('#patchClusterFormFilters'), $('#patchClusterFormValues')); ")
						.f().sx("Modify the null")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3- ")
			.a("onclick", "$('#deleteClusterModale').show(); ")
			.f().sx("Delete the null")
		.g("button");
		{ e("div").a("id", "deleteClusterModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3- ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteClusterModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Delete the null").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Cluster o = new Cluster();

					// Form DELETE
					{ e("form").a("action", "/api/cluster").a("id", "deleteClusterForm").f();
						htmlFormPATCHCluster(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3- ")
						.a("onclick", "deleteCluster(); ")
						.f().sx("Delete the null")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

}
