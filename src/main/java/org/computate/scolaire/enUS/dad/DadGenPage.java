package org.computate.scolaire.enUS.dad;

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
public class DadGenPage extends DadGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

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
			c.o("dads");
		else if(listSchoolDad == null || listSchoolDad.size() == 0)
			c.o("no dad found");
		else
			c.o("dads");
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
			tl(2, "suggestSchoolDadEnrollmentKeys([{'name':'fq','value':'dadKeys:' + pk}], $('#listSchoolDadEnrollmentKeys_Page'), pk, true); ");
		} else {
			tl(2, "suggestSchoolDadEnrollmentKeys([{'name':'fq','value':'dadKeys:' + pk}], $('#listSchoolDadEnrollmentKeys_Page'), pk, false); ");
		}
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
			o.htmPhoto("Page");
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
			o.htmPhoto("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("POST");
		} g("div");
	}

	public void htmlFormPUTImportSchoolDad(SchoolDad o) {
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

	public void htmlFormPUTMergeSchoolDad(SchoolDad o) {
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

	public void htmlFormPUTCopySchoolDad(SchoolDad o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PUTCopy");
			o.htmModified("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PUTCopy");
			o.htmDeleted("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonFirstName("PUTCopy");
			o.htmFamilyName("PUTCopy");
			o.htmPersonFirstNamePreferred("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonEmail("PUTCopy");
			o.htmPersonPhoneNumber("PUTCopy");
			o.htmPersonOccupation("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonSms("PUTCopy");
			o.htmPersonEmergencyContact("PUTCopy");
			o.htmPersonReceiveEmail("PUTCopy");
			o.htmPersonPickup("PUTCopy");
			o.htmPhoto("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("PUTCopy");
			o.htmSessionId("PUTCopy");
			o.htmUserId("PUTCopy");
			o.htmUserKey("PUTCopy");
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
			o.htmPhoto("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("PATCH");
			o.htmSessionId("PATCH");
			o.htmUserId("PATCH");
			o.htmUserKey("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolDad(SchoolDad o) {
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
			o.htmPersonFirstName("Search");
			o.htmFamilyName("Search");
			o.htmPersonFirstNamePreferred("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonEmail("Search");
			o.htmPersonPhoneNumber("Search");
			o.htmPersonOccupation("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPersonSms("Search");
			o.htmPersonEmergencyContact("Search");
			o.htmPersonReceiveEmail("Search");
			o.htmPersonPickup("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKeys("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("Search");
			o.htmSessionId("Search");
			o.htmUserId("Search");
			o.htmUserKey("Search");
			o.htmObjectTitle("Search");
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
					JsonObject queryParams = Optional.ofNullable(operationRequest).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
					Long num = listSchoolDad.getQueryResponse().getResults().getNumFound();
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

					Integer rows1 = Optional.ofNullable(listSchoolDad).map(l -> l.getRows()).orElse(10);
					Integer start1 = Optional.ofNullable(listSchoolDad).map(l -> l.getStart()).orElse(1);
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;
					StringBuilder fqs = new StringBuilder();
					for(String fq : Optional.ofNullable(listSchoolDad).map(l -> l.getFilterQueries()).orElse(new String[0])) {
						if(!StringUtils.contains(fq, "(")) {
							String fq1 = StringUtils.substringBefore(fq, "_");
							String fq2 = StringUtils.substringAfter(fq, ":");
							if(!StringUtils.startsWithAny(fq, "classCanonicalNames_", "archived_", "deleted_", "sessionId", "userKeys"))
								fqs.append("&fq=").append(fq1).append(":").append(fq2);
						}
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : Optional.ofNullable(listSchoolDad).map(l -> l.getSorts()).orElse(Arrays.asList())) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/dad?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/dad?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/dad?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/dad?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
				table1DadGenPage();
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
		g("div");
	}

	public void table1DadGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2DadGenPage();
		} g("table");
	}

	public void table2DadGenPage() {
		thead1DadGenPage();
		tbody1DadGenPage();
		tfoot1DadGenPage();
	}

	public void thead1DadGenPage() {
		{ e("thead").a("class", "w3-light-blue w3-hover-light-blue ").f();
			thead2DadGenPage();
		} g("thead");
	}

	public void thead2DadGenPage() {
			{ e("tr").f();
			if(getColumnCreated()) {
				e("th").f().sx("created").g("th");
			}
			if(getColumnObjectTitle()) {
				e("th").f().sx("").g("th");
			}
			} g("tr");
	}

	public void tbody1DadGenPage() {
		{ e("tbody").f();
			tbody2DadGenPage();
		} g("tbody");
	}

	public void tbody2DadGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listSchoolDad.getQueryResponse().getHighlighting();
		for(int i = 0; i < listSchoolDad.size(); i++) {
			SchoolDad o = listSchoolDad.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/dad/" + o.getPk();
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
							e("i").a("class", "far fa-male ").f().g("i");
							{ e("span").f();
								sx(o.strObjectTitle());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1DadGenPage() {
		{ e("tfoot").a("class", "w3-light-blue w3-hover-light-blue ").f();
			tfoot2DadGenPage();
		} g("tfoot");
	}

	public void tfoot2DadGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolDad.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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

	public void htmlBodyFormsDadGenPage() {
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listSchoolDad != null && listSchoolDad.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
						.a("id", "refreshThisDadGenPage")
						.a("onclick", "patchSchoolDadVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisDadGenPage')); }, function() { addError($('#refreshThisDadGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("refresh this dad");
				} g("button");
			}

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
				.a("onclick", "$('#postSchoolDadModal').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Create a dad");
			} g("button");
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


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
				.a("onclick", "$('#putimportSchoolDadModal').show(); ")
				.f();
				e("i").a("class", "fas fa-file-import ").f().g("i");
				sx("Import dads");
			} g("button");
			{ e("div").a("id", "putimportSchoolDadModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-blue ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putimportSchoolDadModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Import dads").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolDad o = new SchoolDad();
							o.setSiteRequest_(siteRequest_);

							// Form PUT
							{ e("div").a("id", "putimportSchoolDadForm").f();
								htmlFormPUTImportSchoolDad(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-blue ")
								.a("onclick", "putimportSchoolDad($('#putimportSchoolDadForm')); ")
								.f().sx("Import dads")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
				.a("onclick", "$('#putmergeSchoolDadModal').show(); ")
				.f();
				e("i").a("class", "fas fa-code-merge ").f().g("i");
				sx("Merge dads");
			} g("button");
			{ e("div").a("id", "putmergeSchoolDadModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-blue ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putmergeSchoolDadModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Merge dads").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolDad o = new SchoolDad();
							o.setSiteRequest_(siteRequest_);

							// Form PUT
							{ e("div").a("id", "putmergeSchoolDadForm").f();
								htmlFormPUTMergeSchoolDad(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-blue ")
								.a("onclick", "putmergeSchoolDad($('#putmergeSchoolDadForm')); ")
								.f().sx("Merge dads")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
				.a("onclick", "$('#putcopySchoolDadModal').show(); ")
				.f();
				e("i").a("class", "fas fa-copy ").f().g("i");
				sx("Duplicate dads");
			} g("button");
			{ e("div").a("id", "putcopySchoolDadModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-blue ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putcopySchoolDadModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Duplicate dads").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolDad o = new SchoolDad();
							o.setSiteRequest_(siteRequest_);

							// Form PUT
							{ e("div").a("id", "putcopySchoolDadForm").f();
								htmlFormPUTCopySchoolDad(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-blue ")
								.a("onclick", "putcopySchoolDad($('#putcopySchoolDadForm'), ", schoolDad == null ? "null" : schoolDad.getPk(), "); ")
								.f().sx("Duplicate dads")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
				.a("onclick", "$('#patchSchoolDadModal').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modify dads");
			} g("button");
			{ e("div").a("id", "patchSchoolDadModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-blue ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolDadModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modify dads").g("h2");
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
								.a("onclick", "patchSchoolDad(null, $('#patchSchoolDadFormValues'), ", Optional.ofNullable(schoolDad).map(SchoolDad::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modify dads")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
		htmlSuggestedDadGenPage(this, null, listSchoolDad);
	}

	/**
	**/
	public static void htmlSuggestedDadGenPage(PageLayout p, String id, SearchList<SchoolDad> listSchoolDad) {
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

			Integer rows1 = Optional.ofNullable(listSchoolDad).map(l -> l.getRows()).orElse(10);
			Integer start1 = Optional.ofNullable(listSchoolDad).map(l -> l.getStart()).orElse(1);
			Integer start2 = start1 - rows1;
			Integer start3 = start1 + rows1;
			Integer rows2 = rows1 / 2;
			Integer rows3 = rows1 * 2;
			start2 = start2 < 0 ? 0 : start2;
			StringBuilder fqs = new StringBuilder();
			for(String fq : Optional.ofNullable(listSchoolDad).map(l -> l.getFilterQueries()).orElse(new String[0])) {
				if(!StringUtils.contains(fq, "(")) {
					String fq1 = StringUtils.substringBefore(fq, "_");
					String fq2 = StringUtils.substringAfter(fq, ":");
					if(!StringUtils.startsWithAny(fq, "classCanonicalNames_", "archived_", "deleted_", "sessionId", "userKeys"))
						fqs.append("&fq=").append(fq1).append(":").append(fq2);
				}
			}
			StringBuilder sorts = new StringBuilder();
			for(SortClause sort : Optional.ofNullable(listSchoolDad).map(l -> l.getSorts()).orElse(Arrays.asList())) {
				sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
			}

			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), DadGenPage.ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), DadGenPage.ROLES)
					) {
					{ p.e("div").a("class", "").f();
						{ p.e("button").a("id", "refreshAllDadGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ").a("onclick", "patchSchoolDadVals([], {}, function() { addGlow($('#refreshAllDadGenPage", id, "')); }, function() { addError($('#refreshAllDadGenPage", id, "')); }); ").f();
							p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
							p.sx("refresh all the dads");
						} p.g("button");
					} p.g("div");
			}
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell ").f();
					{ p.e("span").f();
						p.sx("search dads: ");
					} p.g("span");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "w3-bar ").f();

				p.e("input")
					.a("type", "text")
					.a("class", "suggestSchoolDad w3-input w3-border w3-bar-item ")
					.a("name", "suggestSchoolDad")
					.a("id", "suggestSchoolDad", id)
					.a("autocomplete", "off")
					.a("oninput", "suggestSchoolDadObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSchoolDad", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
					.a("onkeyup", "if (event.keyCode === 13) { event.preventDefault(); window.location.href = '/dad?q=", query1, ":' + encodeURIComponent(this.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; }"); 
				if(listSchoolDad != null)
					p.a("value", query2);
				p.fg();
				{ p.e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-blue ")
					.a("onclick", "window.location.href = '/dad?q=", query1, ":' + encodeURIComponent(this.previousElementSibling.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; ") 
					.f();
					p.e("i").a("class", "fas fa-search ").f().g("i");
				} p.g("button");

			} p.g("div");
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
					{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchoolDad", id).f();
					} p.g("ul");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/dad").a("class", "").f();
					p.e("i").a("class", "far fa-male ").f().g("i");
					p.sx("see all the dads");
				} p.g("a");
			} p.g("div");
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

}
