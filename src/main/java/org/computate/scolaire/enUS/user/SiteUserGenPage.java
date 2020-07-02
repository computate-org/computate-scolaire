package org.computate.scolaire.enUS.user;

import org.computate.scolaire.enUS.cluster.ClusterPage;
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
import org.apache.commons.collections.CollectionUtils;
import java.util.Objects;
import org.apache.solr.client.solrj.SolrQuery.SortClause;


/**
 * Translate: false
 **/
public class SiteUserGenPage extends SiteUserGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin", "SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

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
			c.o("site users");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(siteUser != null && siteUser.getObjectTitle() != null)
			c.o(siteUser.getObjectTitle());
		else if(siteUser != null)
			c.o("");
		else if(listSiteUser == null || listSiteUser.size() == 0)
			c.o("no site user found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/user");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/user-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("regular");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("user-cog");
	}

	@Override public void initDeepSiteUserGenPage() {
		initSiteUserGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsSiteUserGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/SiteUserPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/EnrollmentPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/PaymentPage.js").f().g("script");
	}

	@Override public void htmlScriptSiteUserGenPage() {
		l("$(document).ready(function() {");
		tl(1, "document.onkeydown = function(evt) {");
		tl(2, "evt = evt || window.event;");
		tl(2, "var isEscape = false;");
		tl(2, "if ('key' in evt) {");
		tl(3, "isEscape = (evt.key === 'Escape' || evt.key === 'Esc');");
		tl(2, "} else {");
		tl(3, "isEscape = (evt.keyCode === 27);");
		tl(2, "}");
		tl(2, "if (isEscape) {");
		tl(3, "$('.w3-modal:visible').hide();");
		tl(2, "}");
		tl(1, "};");
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(siteRequest_.getRequestPk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			tl(2, "suggestSiteUserEnrollmentKeys([{'name':'fq','value':'userKeys:' + pk}], $('#listSiteUserEnrollmentKeys_Page'), pk, true); ");
		} else {
			tl(2, "suggestSiteUserEnrollmentKeys([{'name':'fq','value':'userKeys:' + pk}], $('#listSiteUserEnrollmentKeys_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			tl(2, "suggestSiteUserPaymentKeys([{'name':'fq','value':'userKeys:' + pk}], $('#listSiteUserPaymentKeys_Page'), pk, true); ");
		} else {
			tl(2, "suggestSiteUserPaymentKeys([{'name':'fq','value':'userKeys:' + pk}], $('#listSiteUserPaymentKeys_Page'), pk, false); ");
		}
		tl(1, "}");
		tl(1, "websocketSiteUser(websocketSiteUserInner);");
		l("});");
	}

	public void htmlFormPageSiteUser(SiteUser o) {
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
			o.htmUserReceiveEmails("Page");
			o.htmSeeArchived("Page");
			o.htmSeeDeleted("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCustomerProfileId1("Page");
			o.htmCustomerProfileId2("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("Page");
			o.htmPaymentKeys("Page");
		} g("div");
	}

	public void htmlFormPOSTSiteUser(SiteUser o) {
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
			o.htmUserReceiveEmails("POST");
			o.htmSeeArchived("POST");
			o.htmSeeDeleted("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCustomerProfileId1("POST");
			o.htmCustomerProfileId2("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("POST");
			o.htmPaymentKeys("POST");
		} g("div");
	}

	public void htmlFormPUTImportSiteUser(SiteUser o) {
		{ e("div").a("class", "w3-cell-row ").f();
			e("textarea")
				.a("class", "PUTImport_list w3-input w3-border ")
				.a("style", "height: 400px; ")
				.a("placeholder", "{ \"list\": [ { \"pk\": ... , \"saves\": [ ... ] }, ... ] }")
				;
				f();
			g("textarea");
		} g("div");
	}

	public void htmlFormPUTMergeSiteUser(SiteUser o) {
		{ e("div").a("class", "w3-cell-row ").f();
			e("textarea")
				.a("class", "PUTMerge_list w3-input w3-border ")
				.a("style", "height: 400px; ")
				.a("placeholder", "{ \"list\": [ { \"pk\": ... , \"saves\": [ ... ] }, ... ] }")
				;
				f();
			g("textarea");
		} g("div");
	}

	public void htmlFormPUTCopySiteUser(SiteUser o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PUTCopy");
			o.htmModified("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PUTCopy");
			o.htmDeleted("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUserReceiveEmails("PUTCopy");
			o.htmSeeArchived("PUTCopy");
			o.htmSeeDeleted("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCustomerProfileId1("PUTCopy");
			o.htmCustomerProfileId2("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("PUTCopy");
			o.htmPaymentKeys("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("PUTCopy");
			o.htmSessionId("PUTCopy");
			o.htmUserId("PUTCopy");
			o.htmUserKey("PUTCopy");
			o.htmUserName("PUTCopy");
			o.htmUserEmail("PUTCopy");
		} g("div");
	}

	public void htmlFormPATCHSiteUser(SiteUser o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PATCH");
			o.htmModified("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PATCH");
			o.htmDeleted("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUserReceiveEmails("PATCH");
			o.htmSeeArchived("PATCH");
			o.htmSeeDeleted("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCustomerProfileId1("PATCH");
			o.htmCustomerProfileId2("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("PATCH");
			o.htmPaymentKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("PATCH");
			o.htmSessionId("PATCH");
			o.htmUserId("PATCH");
			o.htmUserKey("PATCH");
			o.htmUserName("PATCH");
			o.htmUserEmail("PATCH");
		} g("div");
	}

	public void htmlFormSearchSiteUser(SiteUser o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPk("Search");
			o.htmCreated("Search");
			o.htmModified("Search");
			o.htmObjectId("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("Search");
			o.htmDeleted("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmUserReceiveEmails("Search");
			o.htmSeeArchived("Search");
			o.htmSeeDeleted("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCustomerProfileId1("Search");
			o.htmCustomerProfileId2("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("Search");
			o.htmPaymentKeys("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("Search");
			o.htmSessionId("Search");
			o.htmUserId("Search");
			o.htmUserKey("Search");
			o.htmObjectTitle("Search");
			o.htmUserName("Search");
			o.htmUserEmail("Search");
		} g("div");
	}

	@Override public void htmlBodySiteUserGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSiteUser == null || listSiteUser.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/user").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("site users").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-gray ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no site user found").g("span");
				} g("span");
			} g("h2");
		} else if(listSiteUser != null && listSiteUser.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SiteUser o = listSiteUser.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/user").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
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
				{ e("a").a("href", "/user").a("class", "w3-bar-item w3-btn w3-center w3-block w3-gray w3-hover-gray ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					JsonObject queryParams = Optional.ofNullable(operationRequest).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
					Long num = listSiteUser.getQueryResponse().getResults().getNumFound();
					String q = "*:*";
					String query1 = "objectText";
					String query2 = "";
					String query = "*:*";
					for(String paramName : queryParams.fieldNames()) {
						String entityVar = null;
						String valueIndexed = null;
						Object paramObjectValues = queryParams.getValue(paramName);
						JsonArray paramObjects = paramObjectValues instanceof JsonArray ? (JsonArray)paramObjectValues : new JsonArray().add(paramObjectValues);

						try {
							for(Object paramObject : paramObjects) {
								switch(paramName) {
									case "q":
										q = (String)paramObject;
										entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
										valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
										query1 = entityVar.equals("*") ? query1 : entityVar;
										query2 = valueIndexed;
										query = query1 + ":" + query2;
								}
							}
						} catch(Exception e) {
							ExceptionUtils.rethrow(e);
						}
					}

					Integer rows1 = Optional.ofNullable(listSiteUser).map(l -> l.getRows()).orElse(10);
					Integer start1 = Optional.ofNullable(listSiteUser).map(l -> l.getStart()).orElse(1);
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;
					StringBuilder fqs = new StringBuilder();
					for(String fq : Optional.ofNullable(listSiteUser).map(l -> l.getFilterQueries()).orElse(new String[0])) {
						if(!StringUtils.contains(fq, "(")) {
							String fq1 = StringUtils.substringBefore(fq, "_");
							String fq2 = StringUtils.substringAfter(fq, ":");
							if(!StringUtils.startsWithAny(fq, "classCanonicalNames_", "archived_", "deleted_", "sessionId", "userKeys"))
								fqs.append("&fq=").append(fq1).append(":").append(fq2);
						}
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : Optional.ofNullable(listSiteUser).map(l -> l.getSorts()).orElse(Arrays.asList())) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/user?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/user?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/user?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/user?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
				table1SiteUserGenPage();
		}

		if(listSiteUser != null && listSiteUser.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SiteUser o = listSiteUser.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SiteUserForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageSiteUser(o);
				}

				if(o != null)
					o.htmlBody();

			} g("div");

		}
		htmlBodyFormsSiteUserGenPage();
		g("div");
	}

	public void table1SiteUserGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2SiteUserGenPage();
		} g("table");
	}

	public void table2SiteUserGenPage() {
		thead1SiteUserGenPage();
		tbody1SiteUserGenPage();
		tfoot1SiteUserGenPage();
	}

	public void thead1SiteUserGenPage() {
		{ e("thead").a("class", "w3-gray w3-hover-gray ").f();
			thead2SiteUserGenPage();
		} g("thead");
	}

	public void thead2SiteUserGenPage() {
			{ e("tr").f();
			if(getColumnCreated()) {
				e("th").f().sx("created").g("th");
			}
			if(getColumnObjectTitle()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1SiteUserGenPage() {
		{ e("tbody").f();
			tbody2SiteUserGenPage();
		} g("tbody");
	}

	public void tbody2SiteUserGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listSiteUser.getQueryResponse().getHighlighting();
		for(int i = 0; i < listSiteUser.size(); i++) {
			SiteUser o = listSiteUser.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/user/" + o.getPk();
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
							e("i").a("class", "far fa-user-cog ").f().g("i");
							{ e("span").f();
								sx(o.strObjectTitle());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1SiteUserGenPage() {
		{ e("tfoot").a("class", "w3-gray w3-hover-gray ").f();
			tfoot2SiteUserGenPage();
		} g("tfoot");
	}

	public void tfoot2SiteUserGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSiteUser.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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

	public void htmlBodyFormsSiteUserGenPage() {
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listSiteUser != null && listSiteUser.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
						.a("id", "refreshThisSiteUserGenPage")
						.a("onclick", "patchSiteUserVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisSiteUserGenPage')); }, function() { addError($('#refreshThisSiteUserGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("refresh this site user");
				} g("button");
			}

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
				.a("onclick", "$('#patchSiteUserModal').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modify site users");
			} g("button");
			{ e("div").a("id", "patchSiteUserModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSiteUserModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modify site users").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SiteUser o = new SiteUser();
							o.setSiteRequest_(siteRequest_);

							// FormValues PATCH
							{ e("form").a("action", "").a("id", "patchSiteUserFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPATCHSiteUser(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-gray ")
								.a("onclick", "patchSiteUser(null, $('#patchSiteUserFormValues'), ", Optional.ofNullable(siteUser).map(SiteUser::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modify site users")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ")
				.a("onclick", "$('#postSiteUserModal').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Create a site user");
			} g("button");
			{ e("div").a("id", "postSiteUserModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-gray ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSiteUserModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Create a site user").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SiteUser o = new SiteUser();
							o.setSiteRequest_(siteRequest_);

							// Form POST
							{ e("div").a("id", "postSiteUserForm").f();
								htmlFormPOSTSiteUser(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-gray ")
								.a("onclick", "postSiteUser($('#postSiteUserForm')); ")
								.f().sx("Create a site user")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
		htmlSuggestedSiteUserGenPage(this, null, listSiteUser);
	}

	/**
	**/
	public static void htmlSuggestedSiteUserGenPage(PageLayout p, String id, SearchList<SiteUser> listSiteUser) {
		SiteRequestEnUS siteRequest_ = p.getSiteRequest_();
		try {
			OperationRequest operationRequest = siteRequest_.getOperationRequest();
			JsonObject queryParams = Optional.ofNullable(operationRequest).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
			String q = "*:*";
			String query1 = "objectText";
			String query2 = "";
			for(String paramName : queryParams.fieldNames()) {
				String entityVar = null;
				String valueIndexed = null;
				Object paramObjectValues = queryParams.getValue(paramName);
				JsonArray paramObjects = paramObjectValues instanceof JsonArray ? (JsonArray)paramObjectValues : new JsonArray().add(paramObjectValues);

				try {
					for(Object paramObject : paramObjects) {
						switch(paramName) {
							case "q":
								q = (String)paramObject;
								entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
								valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
								query1 = entityVar.equals("*") ? query1 : entityVar;
								query2 = valueIndexed.equals("*") ? "" : valueIndexed;
						}
					}
				} catch(Exception e) {
					ExceptionUtils.rethrow(e);
				}
			}

			Integer rows1 = Optional.ofNullable(listSiteUser).map(l -> l.getRows()).orElse(10);
			Integer start1 = Optional.ofNullable(listSiteUser).map(l -> l.getStart()).orElse(1);
			Integer start2 = start1 - rows1;
			Integer start3 = start1 + rows1;
			Integer rows2 = rows1 / 2;
			Integer rows3 = rows1 * 2;
			start2 = start2 < 0 ? 0 : start2;
			StringBuilder fqs = new StringBuilder();
			for(String fq : Optional.ofNullable(listSiteUser).map(l -> l.getFilterQueries()).orElse(new String[0])) {
				if(!StringUtils.contains(fq, "(")) {
					String fq1 = StringUtils.substringBefore(fq, "_");
					String fq2 = StringUtils.substringAfter(fq, ":");
					if(!StringUtils.startsWithAny(fq, "classCanonicalNames_", "archived_", "deleted_", "sessionId", "userKeys"))
						fqs.append("&fq=").append(fq1).append(":").append(fq2);
				}
			}
			StringBuilder sorts = new StringBuilder();
			for(SortClause sort : Optional.ofNullable(listSiteUser).map(l -> l.getSorts()).orElse(Arrays.asList())) {
				sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
			}

			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), SiteUserGenPage.ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), SiteUserGenPage.ROLES)
					) {
					{ p.e("div").a("class", "").f();
						{ p.e("button").a("id", "refreshAllSiteUserGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-gray ").a("onclick", "patchSiteUserVals([], {}, function() { addGlow($('#refreshAllSiteUserGenPage", id, "')); }, function() { addError($('#refreshAllSiteUserGenPage", id, "')); }); ").f();
							p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
							p.sx("refresh all the site users");
						} p.g("button");
					} p.g("div");
			}
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell ").f();
					{ p.e("span").f();
						p.sx("search site users: ");
					} p.g("span");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "w3-bar ").f();

				p.e("input")
					.a("type", "text")
					.a("class", "suggestSiteUser w3-input w3-border w3-bar-item ")
					.a("name", "suggestSiteUser")
					.a("id", "suggestSiteUser", id)
					.a("autocomplete", "off")
					.a("oninput", "suggestSiteUserObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSiteUser", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
					.a("onkeyup", "if (event.keyCode === 13) { event.preventDefault(); window.location.href = '/user?q=", query1, ":' + encodeURIComponent(this.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; }"); 
				if(listSiteUser != null)
					p.a("value", query2);
				p.fg();
				{ p.e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-gray ")
					.a("onclick", "window.location.href = '/user?q=", query1, ":' + encodeURIComponent(this.previousElementSibling.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; ") 
					.f();
					p.e("i").a("class", "fas fa-search ").f().g("i");
				} p.g("button");

			} p.g("div");
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
					{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSiteUser", id).f();
					} p.g("ul");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/user").a("class", "").f();
					p.e("i").a("class", "far fa-user-cog ").f().g("i");
					p.sx("see all the site users");
				} p.g("a");
			} p.g("div");
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

}
