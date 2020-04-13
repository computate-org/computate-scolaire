package org.computate.scolaire.enUS.block;

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
public class BlockGenPage extends BlockGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchoolBlock(Wrap<SearchList<SchoolBlock>> c) {
	}

	protected void _schoolBlock(Wrap<SchoolBlock> c) {
		if(listSchoolBlock != null && listSchoolBlock.size() == 1)
			c.o(listSchoolBlock.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("blocks");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(schoolBlock != null && schoolBlock.getBlockCompleteName() != null)
			c.o(schoolBlock.getBlockCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolBlock != null && schoolBlock.getBlockCompleteName() != null)
			c.o(schoolBlock.getBlockCompleteName());
		else if(schoolBlock != null)
			c.o("");
		else if(listSchoolBlock == null || listSchoolBlock.size() == 0)
			c.o("no block found");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/block");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/block-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("regular");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("bell");
	}

	@Override public void initDeepBlockGenPage() {
		initBlockGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsBlockGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/BlockPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/EnrollmentPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/AgePage.js").f().g("script");
	}

	@Override public void htmlScriptBlockGenPage() {
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
			tl(2, "suggestSchoolBlockAgeKey([{'name':'fq','value':'blockKeys:' + pk}], $('#listSchoolBlockAgeKey_Page'), pk, true); ");
		} else {
			tl(2, "suggestSchoolBlockAgeKey([{'name':'fq','value':'blockKeys:' + pk}], $('#listSchoolBlockAgeKey_Page'), pk, false); ");
		}
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			tl(2, "suggestSchoolBlockEnrollmentKeys([{'name':'fq','value':'blockKeys:' + pk}], $('#listSchoolBlockEnrollmentKeys_Page'), pk, true); ");
		} else {
			tl(2, "suggestSchoolBlockEnrollmentKeys([{'name':'fq','value':'blockKeys:' + pk}], $('#listSchoolBlockEnrollmentKeys_Page'), pk, false); ");
		}
		tl(1, "}");
		tl(1, "websocketSchoolBlock(websocketSchoolBlockInner);");
		l("});");
	}

	public void htmlFormPageSchoolBlock(SchoolBlock o) {
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
			o.htmBlockStartTime("Page");
			o.htmBlockEndTime("Page");
			o.htmBlockPricePerMonth("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockMonday("Page");
			o.htmBlockTuesday("Page");
			o.htmBlockWednesday("Page");
			o.htmBlockThursday("Page");
			o.htmBlockFriday("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeKey("Page");
			o.htmEnrollmentKeys("Page");
		} g("div");
	}

	public void htmlFormPOSTSchoolBlock(SchoolBlock o) {
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
			o.htmBlockStartTime("POST");
			o.htmBlockEndTime("POST");
			o.htmBlockPricePerMonth("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockMonday("POST");
			o.htmBlockTuesday("POST");
			o.htmBlockWednesday("POST");
			o.htmBlockThursday("POST");
			o.htmBlockFriday("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeKey("POST");
			o.htmEnrollmentKeys("POST");
		} g("div");
	}

	public void htmlFormPUTImportSchoolBlock(SchoolBlock o) {
		{
			{ e("div").a("class", "w3-cell-row ").f();
				e("textarea")
					.a("class", "PUTImport_list")
					.a("placeholder", "{ \"list\": [ { \"pk\": ... , \"saves\": [ ... ] }, ... ] }")
					;
					f();
				g("textarea");
			} g("div");
		}
	}

	public void htmlFormPUTMergeSchoolBlock(SchoolBlock o) {
		{
			{ e("div").a("class", "w3-cell-row ").f();
				e("textarea")
					.a("class", "PUTMerge_list")
					.a("placeholder", "{ \"list\": [ { \"pk\": ... , \"saves\": [ ... ] }, ... ] }")
					;
					f();
				g("textarea");
			} g("div");
		}
	}

	public void htmlFormPUTCopySchoolBlock(SchoolBlock o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PUTCopy");
			o.htmModified("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PUTCopy");
			o.htmDeleted("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockStartTime("PUTCopy");
			o.htmBlockEndTime("PUTCopy");
			o.htmBlockPricePerMonth("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockMonday("PUTCopy");
			o.htmBlockTuesday("PUTCopy");
			o.htmBlockWednesday("PUTCopy");
			o.htmBlockThursday("PUTCopy");
			o.htmBlockFriday("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeKey("PUTCopy");
			o.htmEnrollmentKeys("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolAddress("PUTCopy");
		} g("div");
	}

	public void htmlFormPATCHSchoolBlock(SchoolBlock o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PATCH");
			o.htmModified("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PATCH");
			o.htmDeleted("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockStartTime("PATCH");
			o.htmBlockEndTime("PATCH");
			o.htmBlockPricePerMonth("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockMonday("PATCH");
			o.htmBlockTuesday("PATCH");
			o.htmBlockWednesday("PATCH");
			o.htmBlockThursday("PATCH");
			o.htmBlockFriday("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeKey("PATCH");
			o.htmEnrollmentKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolAddress("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolBlock(SchoolBlock o) {
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
			o.htmBlockStartTime("Search");
			o.htmBlockEndTime("Search");
			o.htmBlockPricePerMonth("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmBlockMonday("Search");
			o.htmBlockTuesday("Search");
			o.htmBlockWednesday("Search");
			o.htmBlockThursday("Search");
			o.htmBlockFriday("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmAgeKey("Search");
			o.htmEnrollmentKeys("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjectTitle("Search");
			o.htmSchoolAddress("Search");
		} g("div");
	}

	@Override public void htmlBodyBlockGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolBlock == null || listSchoolBlock.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/block").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("blocks").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-indigo ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no block found").g("span");
				} g("span");
			} g("h2");
		} else if(listSchoolBlock != null && listSchoolBlock.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolBlock o = listSchoolBlock.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/block").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
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
				{ e("a").a("href", "/block").a("class", "w3-bar-item w3-btn w3-center w3-block w3-indigo w3-hover-indigo ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					JsonObject queryParams = Optional.ofNullable(operationRequest).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
					Long num = listSchoolBlock.getQueryResponse().getResults().getNumFound();
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

					Integer rows1 = Optional.ofNullable(listSchoolBlock).map(l -> l.getRows()).orElse(10);
					Integer start1 = Optional.ofNullable(listSchoolBlock).map(l -> l.getStart()).orElse(1);
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;
					StringBuilder fqs = new StringBuilder();
					for(String fq : Optional.ofNullable(listSchoolBlock).map(l -> l.getFilterQueries()).orElse(new String[0])) {
						if(!StringUtils.contains(fq, "(")) {
							String fq1 = StringUtils.substringBefore(fq, "_");
							String fq2 = StringUtils.substringAfter(fq, ":");
							if(!StringUtils.startsWithAny(fq, "classCanonicalNames_", "archived_", "deleted_", "sessionId", "userKeys"))
								fqs.append("&fq=").append(fq1).append(":").append(fq2);
						}
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : Optional.ofNullable(listSchoolBlock).map(l -> l.getSorts()).orElse(Arrays.asList())) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/block?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/block?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/block?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/block?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
				table1BlockGenPage();
		}

		if(listSchoolBlock != null && listSchoolBlock.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolBlock o = listSchoolBlock.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolBlockForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageSchoolBlock(o);
				}

			} g("div");

		}
		htmlBodyFormsBlockGenPage();
		g("div");
	}

	public void table1BlockGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2BlockGenPage();
		} g("table");
	}

	public void table2BlockGenPage() {
		thead1BlockGenPage();
		tbody1BlockGenPage();
		tfoot1BlockGenPage();
	}

	public void thead1BlockGenPage() {
		{ e("thead").a("class", "w3-indigo w3-hover-indigo ").f();
			thead2BlockGenPage();
		} g("thead");
	}

	public void thead2BlockGenPage() {
			{ e("tr").f();
			if(getColumnCreated()) {
				e("th").f().sx("created").g("th");
			}
			if(getColumnObjectTitle()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1BlockGenPage() {
		{ e("tbody").f();
			tbody2BlockGenPage();
		} g("tbody");
	}

	public void tbody2BlockGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listSchoolBlock.getQueryResponse().getHighlighting();
		for(int i = 0; i < listSchoolBlock.size(); i++) {
			SchoolBlock o = listSchoolBlock.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/block/" + o.getPk();
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
							e("i").a("class", "far fa-bell ").f().g("i");
							{ e("span").f();
								sx(o.strObjectTitle());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1BlockGenPage() {
		{ e("tfoot").a("class", "w3-indigo w3-hover-indigo ").f();
			tfoot2BlockGenPage();
		} g("tfoot");
	}

	public void tfoot2BlockGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolBlock.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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

	public void htmlBodyFormsBlockGenPage() {
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listSchoolBlock != null && listSchoolBlock.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
						.a("id", "refreshThisBlockGenPage")
						.a("onclick", "patchSchoolBlockVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisBlockGenPage')); }, function() { addError($('#refreshThisBlockGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("refresh this block");
				} g("button");
			}

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("onclick", "$('#postSchoolBlockModal').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Create a block");
			} g("button");
			{ e("div").a("id", "postSchoolBlockModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-indigo ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolBlockModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Create a block").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolBlock o = new SchoolBlock();
							o.setSiteRequest_(siteRequest_);

							// Form POST
							{ e("div").a("id", "postSchoolBlockForm").f();
								htmlFormPOSTSchoolBlock(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
								.a("onclick", "postSchoolBlock($('#postSchoolBlockForm')); ")
								.f().sx("Create a block")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("onclick", "$('#putimportSchoolBlockModal').show(); ")
				.f();
				e("i").a("class", "fas fa-file-import ").f().g("i");
				sx("Import blocks");
			} g("button");
			{ e("div").a("id", "putimportSchoolBlockModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-indigo ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putimportSchoolBlockModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Import blocks").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolBlock o = new SchoolBlock();
							o.setSiteRequest_(siteRequest_);

							// Form PUT
							{ e("div").a("id", "putimportSchoolBlockForm").f();
								htmlFormPUTImportSchoolBlock(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
								.a("onclick", "putimportSchoolBlock($('#putimportSchoolBlockForm')); ")
								.f().sx("Import blocks")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("onclick", "$('#putmergeSchoolBlockModal').show(); ")
				.f();
				e("i").a("class", "fas fa-code-merge ").f().g("i");
				sx("Merge blocks");
			} g("button");
			{ e("div").a("id", "putmergeSchoolBlockModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-indigo ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putmergeSchoolBlockModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Merge blocks").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolBlock o = new SchoolBlock();
							o.setSiteRequest_(siteRequest_);

							// Form PUT
							{ e("div").a("id", "putmergeSchoolBlockForm").f();
								htmlFormPUTMergeSchoolBlock(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
								.a("onclick", "putmergeSchoolBlock($('#putmergeSchoolBlockForm')); ")
								.f().sx("Merge blocks")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("onclick", "$('#putcopySchoolBlockModal').show(); ")
				.f();
				e("i").a("class", "fas fa-copy ").f().g("i");
				sx("Duplicate blocks");
			} g("button");
			{ e("div").a("id", "putcopySchoolBlockModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-indigo ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putcopySchoolBlockModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Duplicate blocks").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolBlock o = new SchoolBlock();
							o.setSiteRequest_(siteRequest_);

							// Form PUT
							{ e("div").a("id", "putcopySchoolBlockForm").f();
								htmlFormPUTCopySchoolBlock(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
								.a("onclick", "putcopySchoolBlock(", o.getPk(), ", $('#putcopySchoolBlockForm')); ")
								.f().sx("Duplicate blocks")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
				.a("onclick", "$('#patchSchoolBlockModal').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modify blocks");
			} g("button");
			{ e("div").a("id", "patchSchoolBlockModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-indigo ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolBlockModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modify blocks").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolBlock o = new SchoolBlock();
							o.setSiteRequest_(siteRequest_);

							// FormValues PATCH
							{ e("form").a("action", "").a("id", "patchSchoolBlockFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPATCHSchoolBlock(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-indigo ")
								.a("onclick", "patchSchoolBlock(null, $('#patchSchoolBlockFormValues'), ", Optional.ofNullable(schoolBlock).map(SchoolBlock::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modify blocks")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
		htmlSuggestedBlockGenPage(this, null, listSchoolBlock);
	}

	/**
	**/
	public static void htmlSuggestedBlockGenPage(PageLayout p, String id, SearchList<SchoolBlock> listSchoolBlock) {
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

			Integer rows1 = Optional.ofNullable(listSchoolBlock).map(l -> l.getRows()).orElse(10);
			Integer start1 = Optional.ofNullable(listSchoolBlock).map(l -> l.getStart()).orElse(1);
			Integer start2 = start1 - rows1;
			Integer start3 = start1 + rows1;
			Integer rows2 = rows1 / 2;
			Integer rows3 = rows1 * 2;
			start2 = start2 < 0 ? 0 : start2;
			StringBuilder fqs = new StringBuilder();
			for(String fq : Optional.ofNullable(listSchoolBlock).map(l -> l.getFilterQueries()).orElse(new String[0])) {
				if(!StringUtils.contains(fq, "(")) {
					String fq1 = StringUtils.substringBefore(fq, "_");
					String fq2 = StringUtils.substringAfter(fq, ":");
					if(!StringUtils.startsWithAny(fq, "classCanonicalNames_", "archived_", "deleted_", "sessionId", "userKeys"))
						fqs.append("&fq=").append(fq1).append(":").append(fq2);
				}
			}
			StringBuilder sorts = new StringBuilder();
			for(SortClause sort : Optional.ofNullable(listSchoolBlock).map(l -> l.getSorts()).orElse(Arrays.asList())) {
				sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
			}

			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), BlockGenPage.ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), BlockGenPage.ROLES)
					) {
					{ p.e("div").a("class", "").f();
						{ p.e("button").a("id", "refreshAllBlockGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ").a("onclick", "patchSchoolBlockVals([], {}, function() { addGlow($('#refreshAllBlockGenPage", id, "')); }, function() { addError($('#refreshAllBlockGenPage", id, "')); }); ").f();
							p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
							p.sx("refresh all the blocks");
						} p.g("button");
					} p.g("div");
			}
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell ").f();
					{ p.e("span").f();
						p.sx("search blocks: ");
					} p.g("span");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "w3-bar ").f();

				p.e("input")
					.a("type", "text")
					.a("class", "suggestSchoolBlock w3-input w3-border w3-bar-item ")
					.a("name", "suggestSchoolBlock")
					.a("id", "suggestSchoolBlock", id)
					.a("autocomplete", "off")
					.a("oninput", "suggestSchoolBlockObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSchoolBlock", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
					.a("onkeyup", "if (event.keyCode === 13) { event.preventDefault(); window.location.href = '/block?q=", query1, ":' + encodeURIComponent(this.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; }"); 
				if(listSchoolBlock != null)
					p.a("value", query2);
				p.fg();
				{ p.e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-indigo ")
					.a("onclick", "window.location.href = '/block?q=", query1, ":' + encodeURIComponent(this.previousElementSibling.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; ") 
					.f();
					p.e("i").a("class", "fas fa-search ").f().g("i");
				} p.g("button");

			} p.g("div");
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
					{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchoolBlock", id).f();
					} p.g("ul");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/block").a("class", "").f();
					p.e("i").a("class", "far fa-bell ").f().g("i");
					p.sx("see all the blocks");
				} p.g("a");
			} p.g("div");
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

}
