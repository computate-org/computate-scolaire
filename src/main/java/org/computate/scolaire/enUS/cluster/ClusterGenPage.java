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
		if(cluster != null)
			c.o("a cluster");
		else if(listCluster == null || listCluster.size() == 0)
			c.o("no cluster found");
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
			c.o("no cluster found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/enUS/cluster");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/enUS/cluster-999.png");
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
		l("});");
	}

	public void htmlFormPageCluster(Cluster o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("class", "").f().sx("created").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strCreated()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-green ").f();
							e("label").a("class", "").f().sx("modified").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strModified()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTCluster(Cluster o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("created").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strCreated()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("modified").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strModified()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHCluster(Cluster o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("created").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strCreated()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("modified").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strModified()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormSearchCluster(Cluster o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("created").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strCreated()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-green ").f();
						e("label").a("class", "").f().sx("modified").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strModified()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodyClusterGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listCluster == null || listCluster.size() == 0) {

			{ e("h1").f();
				if(contextIconCssClasses != null)
					e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("no cluster found").g("span");
			} g("h1");
		} else if(listCluster != null && listCluster.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			if(pageH1 != null) {
				{ e("h1").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("a cluster").g("span");
				} g("h1");
				Cluster o = listCluster.get(0);
				siteRequest_.setRequestPk(o.getPk());
			}
		} else {

			{ e("h1").f();
				if(contextIconCssClasses != null)
					e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("clusters").g("span");
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

		if(listCluster != null && listCluster.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			Cluster o = listCluster.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "ClusterForm").a("style", "display: inline-block; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
		htmlBodyFormsClusterGenPage();
	}

	public void htmlBodyFormsClusterGenPage() {
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#postClusterModal').show(); ")
			.f().sx("Create a cluster")
		.g("button");
		{ e("div").a("id", "postClusterModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postClusterModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Create a cluster").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Cluster o = new Cluster();

					// Form POST
					{ e("form").a("action", "").a("id", "postClusterForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPOSTCluster(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "postCluster($('#postClusterForm')); ")
						.f().sx("Create a cluster")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#patchClusterModal').show(); ")
			.f().sx("Modify the clusters")
		.g("button");
		{ e("div").a("id", "patchClusterModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchClusterModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Modify the clusters").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Cluster o = new Cluster();

					// FormFilters PATCH
					{ e("form").a("action", "").a("id", "patchClusterFormFilters").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormSearchCluster(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "aSearchCluster($('#patchClusterFormFilters')); ")
						.f().sx("Search the a cluster")
					.g("button");


					// FormValues PATCH
					{ e("form").a("action", "").a("id", "patchClusterFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHCluster(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "patchCluster($('#patchClusterFormFilters'), $('#patchClusterFormValues')); ")
						.f().sx("Modify the clusters")
					.g("button");

				} g("div");
			} g("div");
		} g("div");


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
			.a("onclick", "$('#deleteClusterModal').show(); ")
			.f().sx("Delete the clusters")
		.g("button");
		{ e("div").a("id", "deleteClusterModal").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-green ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteClusterModal').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Delete the clusters").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					Cluster o = new Cluster();

					// Form DELETE
					{ e("form").a("action", "").a("id", "deleteClusterForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHCluster(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("onclick", "deleteCluster(); ")
						.f().sx("Delete the clusters")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

}
