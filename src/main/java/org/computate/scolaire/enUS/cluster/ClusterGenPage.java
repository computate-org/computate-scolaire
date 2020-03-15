package org.computate.scolaire.enUS.cluster;

import org.computate.scolaire.enUS.page.PageLayout;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.user.SiteUser;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.wrap.Wrap;
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
import org.apache.solr.common.util.SimpleOrderedMap;
import java.util.stream.Collectors;
import java.util.Arrays;
import org.apache.solr.client.solrj.response.QueryResponse;
import java.math.BigDecimal;
import java.math.MathContext;


/**
 * Translate: false
 **/
public class ClusterGenPage extends ClusterGenPageGen<PageLayout> {

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
			c.o("clusters");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(cluster != null && cluster.getObjectTitle() != null)
			c.o(cluster.getObjectTitle());
		else if(cluster != null)
			c.o("");
		else if(listCluster == null || listCluster.size() == 0)
			c.o("no cluster found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/cluster");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/cluster-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("regular");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("fort-awesome");
	}

	@Override public void initDeepClusterGenPage() {
		initClusterGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsClusterGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/ClusterPage.js").f().g("script");
	}

	@Override public void htmlScriptClusterGenPage() {
		l("$(document).ready(function() {");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(siteRequest_.getRequestPk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(1, "}");
		tl(1, "websocketCluster(websocketClusterInner);");
		l("});");
	}

	public void htmlFormPageCluster(Cluster o) {
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
	}

	public void htmlFormPOSTCluster(Cluster o) {
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
	}

	public void htmlFormPUTCluster(Cluster o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PUT");
			o.htmModified("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PUT");
			o.htmDeleted("PUT");
		} g("div");
	}

	public void htmlFormPATCHCluster(Cluster o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PATCH");
			o.htmModified("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PATCH");
			o.htmDeleted("PATCH");
		} g("div");
	}

	public void htmlFormSearchCluster(Cluster o) {
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
			o.htmObjectTitle("Recherche");
		} g("div");
	}

	@Override public void htmlBodyClusterGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listCluster == null || listCluster.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/cluster").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("clusters").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-gray ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no cluster found").g("span");
				} g("span");
			} g("h2");
		} else if(listCluster != null && listCluster.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			Cluster o = listCluster.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/cluster").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-gray ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-gray ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/cluster").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					Long num = listCluster.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listCluster.getQuery(), "_suggested", "");
					Integer rows1 = listCluster.getRows();
					Integer start1 = listCluster.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/cluster?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/cluster?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/cluster?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/cluster?q=", query, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
				table1ClusterGenPage();
		}

		if(listCluster != null && listCluster.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			Cluster o = listCluster.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "ClusterForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageCluster(o);
				}

			} g("div");

		}
		htmlBodyFormsClusterGenPage();
		htmlSuggestClusterGenPage(this, null);
		g("div");
	}

	public void table1ClusterGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2ClusterGenPage();
		} g("table");
	}

	public void table2ClusterGenPage() {
		thead1ClusterGenPage();
		tbody1ClusterGenPage();
		tfoot1ClusterGenPage();
	}

	public void thead1ClusterGenPage() {
		{ e("thead").a("class", "w3-gray w3-hover-gray ").f();
			thead2ClusterGenPage();
		} g("thead");
	}

	public void thead2ClusterGenPage() {
			{ e("tr").f();
			if(getColumnCreated()) {
				e("th").f().sx("created").g("th");
			}
			if(getColumnObjectTitle()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1ClusterGenPage() {
		{ e("tbody").f();
			tbody2ClusterGenPage();
		} g("tbody");
	}

	public void tbody2ClusterGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listCluster.getQueryResponse().getHighlighting();
		for(int i = 0; i < listCluster.size(); i++) {
			Cluster o = listCluster.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/cluster/" + o.getPk();
			{ e("tr").f();
				if(getColumnCreated()) {
					{ e("td").f();
						{ e("a").a("href", uri).f();
							{ e("span").f();
								sx(o.strCreated());
							} g("span");
						} g("a");
					} g("td");
				}
				if(getColumnObjectTitle()) {
					{ e("td").f();
						{ e("a").a("href", uri).f();
							e("i").a("class", "far fa-fort-awesome ").f().g("i");
							{ e("span").f();
								sx(o.strObjectTitle());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1ClusterGenPage() {
		{ e("tfoot").a("class", "w3-gray w3-hover-gray ").f();
			tfoot2ClusterGenPage();
		} g("tfoot");
	}

	public void tfoot2ClusterGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listCluster.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
			if(getColumnCreated()) {
				e("td").f();
				g("td");
			}
			if(getColumnObjectTitle()) {
				e("td").f();
				g("td");
			}
		} g("tr");
	}

	public Boolean getColumnCreated() {
		return true;
	}

	public Boolean getColumnObjectTitle() {
		return true;
	}

	public void htmlBodyFormsClusterGenPage() {
		e("div").a("class", "w3-margin-top ").f();

		if(listCluster != null && listCluster.size() == 1) {
			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
					.a("id", "refreshThisClusterGenPage")
					.a("onclick", "patchClusterVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisClusterGenPage')); }, function() { addError($('#refreshThisClusterGenPage')); }); return false; ").f();
					e("i").a("class", "fas fa-sync-alt ").f().g("i");
				sx("refresh this cluster");
			} g("button");
		}

		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
			.a("onclick", "$('#postClusterModal').show(); ")
			.f().sx("Create a cluster")
		.g("button");
		{ e("div").a("id", "postClusterModal").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-gray ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postClusterModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Create a cluster").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						Cluster o = new Cluster();
						o.setSiteRequest_(siteRequest_);

						// Form POST
						{ e("div").a("id", "postClusterForm").f();
							htmlFormPOSTCluster(o);
						} g("div");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-gray ")
							.a("onclick", "postCluster($('#postClusterForm')); ")
							.f().sx("Create a cluster")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
			.a("onclick", "$('#putClusterModal').show(); ")
			.f().sx("Duplicate the clusters")
		.g("button");
		{ e("div").a("id", "putClusterModal").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-gray ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putClusterModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Duplicate the clusters").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						Cluster o = new Cluster();
						o.setSiteRequest_(siteRequest_);

						// FormValues PUT
						{ e("form").a("action", "").a("id", "putClusterFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPUTCluster(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-gray ")
							.a("onclick", "putCluster($('#putClusterFormValues'), ", Optional.ofNullable(cluster).map(Cluster::getPk).map(a -> a.toString()).orElse("null"), "); ")
							.f().sx("Duplicate the clusters")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
			.a("onclick", "$('#patchClusterModal').show(); ")
			.f().sx("Modify the clusters")
		.g("button");
		{ e("div").a("id", "patchClusterModal").a("class", "w3-modal w3-padding-32 ").f();
			{ e("div").a("class", "w3-modal-content ").f();
				{ e("div").a("class", "w3-card-4 ").f();
					{ e("header").a("class", "w3-container w3-gray ").f();
						e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchClusterModal').hide(); ").f().sx("×").g("span");
						e("h2").a("class", "w3-padding ").f().sx("Modify the clusters").g("h2");
					} g("header");
					{ e("div").a("class", "w3-container ").f();
						Cluster o = new Cluster();
						o.setSiteRequest_(siteRequest_);

						// FormValues PATCH
						{ e("form").a("action", "").a("id", "patchClusterFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
							htmlFormPATCHCluster(o);
						} g("form");
						e("button")
							.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-gray ")
							.a("onclick", "patchCluster($('#patchClusterFormFilters'), $('#patchClusterFormValues'), ", Optional.ofNullable(cluster).map(Cluster::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
							.f().sx("Modify the clusters")
						.g("button");

					} g("div");
				} g("div");
			} g("div");
		} g("div");


		if(listCluster != null && listCluster.size() == 1) {
			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
				.a("onclick", "$('#deleteClusterModal').show(); ")
				.f().sx("Delete the clusters")
			.g("button");
			{ e("div").a("id", "deleteClusterModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteClusterModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Delete the clusters").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							Cluster o = new Cluster();
							o.setSiteRequest_(siteRequest_);

							// Form DELETE
							{ e("div").a("id", "deleteClusterForm").f();
								htmlFormPATCHCluster(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-gray ")
								.a("onclick", "deleteCluster(", o.getPk(), "); ")
								.f().sx("Delete the clusters")
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
	public static void htmlSuggestClusterGenPage(PageLayout p, String id) {
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("id", "refreshAllClusterGenPage", id).a("href", "/cluster").a("class", "").a("onclick", "patchClusterVals([], {}, function() { addGlow($('#refreshAllClusterGenPage", id, "')); }, function() { addError($('#refreshAllClusterGenPage", id, "')); }); return false; ").f();
				p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
				p.sx("refresh all the clusters");
			} p.g("a");
		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell ").f();
				{ p.e("span").f();
					p.sx("search clusters: ");
				} p.g("span");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "w3-bar ").f();

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("class", "suggestCluster w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggestCluster")
				.a("id", "suggestCluster", id)
				.a("autocomplete", "off")
				.a("oninput", "suggestClusterObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListCluster", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
				.fg();

		} p.g("div");
		{ p.e("div").a("class", "w3-cell-row ").f();
			{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListCluster", id).f();
				} p.g("ul");
			} p.g("div");
		} p.g("div");
		{ p.e("div").a("class", "").f();
			{ p.e("a").a("href", "/cluster").a("class", "").f();
				p.e("i").a("class", "far fa-fort-awesome ").f().g("i");
				p.sx("see all the clusters");
			} p.g("a");
		} p.g("div");
	}

}
