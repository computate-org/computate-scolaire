package org.computate.scolaire.enUS.receipt;

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
import java.math.RoundingMode;
import java.math.MathContext;
import org.apache.commons.collections.CollectionUtils;
import java.util.Objects;
import org.apache.solr.client.solrj.SolrQuery.SortClause;


/**
 * Translate: false
 **/
public class ReceiptGenPage extends ReceiptGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchoolReceipt(Wrap<SearchList<SchoolReceipt>> c) {
	}

	protected void _schoolReceipt_(Wrap<SchoolReceipt> c) {
		if(listSchoolReceipt != null && listSchoolReceipt.size() == 1)
			c.o(listSchoolReceipt.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("receipts");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(schoolReceipt_ != null && schoolReceipt_.getPaymentCompleteName() != null)
			c.o(schoolReceipt_.getPaymentCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolReceipt_ != null && schoolReceipt_.getPaymentCompleteName() != null)
			c.o(schoolReceipt_.getPaymentCompleteName());
		else if(schoolReceipt_ != null)
			c.o("receipts");
		else if(listSchoolReceipt == null || listSchoolReceipt.size() == 0)
			c.o("no receipt found");
		else
			c.o("receipts");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/receipt");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/receipt-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("solid");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("file-invoice-dollar");
	}

	@Override public void initDeepReceiptGenPage() {
		initReceiptGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsReceiptGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/ReceiptPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/SchoolPage.js").f().g("script");
	}

	@Override public void htmlScriptReceiptGenPage() {
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
			tl(2, "suggestSchoolReceiptSchoolKey([{'name':'fq','value':'receiptKeys:' + pk}], $('#listSchoolReceiptSchoolKey_Page'), pk, true); ");
		} else {
			tl(2, "suggestSchoolReceiptSchoolKey([{'name':'fq','value':'receiptKeys:' + pk}], $('#listSchoolReceiptSchoolKey_Page'), pk, false); ");
		}
		tl(1, "}");
		tl(1, "websocketSchoolReceipt(websocketSchoolReceiptInner);");
		l("});");
	}

	public void htmlFormPageSchoolReceipt(SchoolReceipt o) {
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
			o.htmPaymentDate("Page");
			o.htmPaymentAmount("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolKey("Page");
			o.htmPaymentDescription("Page");
		} g("div");
	}

	public void htmlFormPOSTSchoolReceipt(SchoolReceipt o) {
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
			o.htmPaymentDate("POST");
			o.htmPaymentAmount("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolKey("POST");
			o.htmPaymentDescription("POST");
		} g("div");
	}

	public void htmlFormPUTImportSchoolReceipt(SchoolReceipt o) {
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

	public void htmlFormPUTMergeSchoolReceipt(SchoolReceipt o) {
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

	public void htmlFormPUTCopySchoolReceipt(SchoolReceipt o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PUTCopy");
			o.htmModified("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PUTCopy");
			o.htmDeleted("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaymentDate("PUTCopy");
			o.htmPaymentAmount("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolKey("PUTCopy");
			o.htmPaymentDescription("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("PUTCopy");
			o.htmSessionId("PUTCopy");
			o.htmUserId("PUTCopy");
			o.htmUserKey("PUTCopy");
			o.htmPaymentShortName("PUTCopy");
		} g("div");
	}

	public void htmlFormPATCHSchoolReceipt(SchoolReceipt o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PATCH");
			o.htmModified("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PATCH");
			o.htmDeleted("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaymentDate("PATCH");
			o.htmPaymentAmount("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolKey("PATCH");
			o.htmPaymentDescription("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("PATCH");
			o.htmSessionId("PATCH");
			o.htmUserId("PATCH");
			o.htmUserKey("PATCH");
			o.htmPaymentShortName("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolReceipt(SchoolReceipt o) {
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
			o.htmPaymentDate("Search");
			o.htmPaymentAmount("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmSchoolKey("Search");
			o.htmPaymentDescription("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("Search");
			o.htmSessionId("Search");
			o.htmUserId("Search");
			o.htmUserKey("Search");
			o.htmObjectTitle("Search");
			o.htmPaymentShortName("Search");
		} g("div");
	}

	@Override public void htmlBodyReceiptGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolReceipt == null || listSchoolReceipt.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/receipt").a("class", "w3-bar-item w3-btn w3-center w3-block w3-light-green w3-hover-light-green ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("receipts").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-light-green ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no receipt found").g("span");
				} g("span");
			} g("h2");
		} else if(listSchoolReceipt != null && listSchoolReceipt.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolReceipt o = listSchoolReceipt.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/receipt").a("class", "w3-bar-item w3-btn w3-center w3-block w3-light-green w3-hover-light-green ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-light-green ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-light-green ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/receipt").a("class", "w3-bar-item w3-btn w3-center w3-block w3-light-green w3-hover-light-green ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					JsonObject queryParams = Optional.ofNullable(operationRequest).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
					Long num = listSchoolReceipt.getQueryResponse().getResults().getNumFound();
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

					Integer rows1 = Optional.ofNullable(listSchoolReceipt).map(l -> l.getRows()).orElse(10);
					Integer start1 = Optional.ofNullable(listSchoolReceipt).map(l -> l.getStart()).orElse(1);
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;
					StringBuilder fqs = new StringBuilder();
					for(String fq : Optional.ofNullable(listSchoolReceipt).map(l -> l.getFilterQueries()).orElse(new String[0])) {
						if(!StringUtils.contains(fq, "(")) {
							String fq1 = StringUtils.substringBefore(fq, "_");
							String fq2 = StringUtils.substringAfter(fq, ":");
							if(!StringUtils.startsWithAny(fq, "classCanonicalNames_", "archived_", "deleted_", "sessionId", "userKeys"))
								fqs.append("&fq=").append(fq1).append(":").append(fq2);
						}
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : Optional.ofNullable(listSchoolReceipt).map(l -> l.getSorts()).orElse(Arrays.asList())) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/receipt?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/receipt?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/receipt?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/receipt?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
				table1ReceiptGenPage();
		}

		if(listSchoolReceipt != null && listSchoolReceipt.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolReceipt o = listSchoolReceipt.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolReceiptForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageSchoolReceipt(o);
				}

			} g("div");

		}
		htmlBodyFormsReceiptGenPage();
		g("div");
	}

	public void table1ReceiptGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2ReceiptGenPage();
		} g("table");
	}

	public void table2ReceiptGenPage() {
		thead1ReceiptGenPage();
		tbody1ReceiptGenPage();
		tfoot1ReceiptGenPage();
	}

	public void thead1ReceiptGenPage() {
		{ e("thead").a("class", "w3-light-green w3-hover-light-green ").f();
			thead2ReceiptGenPage();
		} g("thead");
	}

	public void thead2ReceiptGenPage() {
			{ e("tr").f();
			if(getColumnCreated()) {
				e("th").f().sx("created").g("th");
			}
			if(getColumnObjectTitle()) {
				e("th").f().sx("").g("th");
			}
			if(getColumnPaymentShortName()) {
				e("th").f().sx("name").g("th");
			}
			if(getColumnPaymentDate()) {
				e("th").f().sx("payment date").g("th");
			}
			if(getColumnPaymentAmount()) {
				e("th").f().sx("payment amount").g("th");
			}
			} g("tr");
	}

	public void tbody1ReceiptGenPage() {
		{ e("tbody").f();
			tbody2ReceiptGenPage();
		} g("tbody");
	}

	public void tbody2ReceiptGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listSchoolReceipt.getQueryResponse().getHighlighting();
		for(int i = 0; i < listSchoolReceipt.size(); i++) {
			SchoolReceipt o = listSchoolReceipt.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/receipt/" + o.getPk();
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
							e("i").a("class", "fas fa-file-invoice-dollar ").f().g("i");
							{ e("span").f();
								sx(o.strObjectTitle());
							} g("span");
						} g("a");
					} g("td");
				}
				if(getColumnPaymentShortName()) {
					{ e("td").f();
						{ e("a").a("href", uri).f();
							{ e("span").f();
								sx(o.strPaymentShortName());
							} g("span");
						} g("a");
					} g("td");
				}
				if(getColumnPaymentDate()) {
					{ e("td").f();
						{ e("a").a("href", uri).f();
							{ e("span").f();
								sx(o.strPaymentDate());
							} g("span");
						} g("a");
					} g("td");
				}
				if(getColumnPaymentAmount()) {
					{ e("td").f();
						{ e("a").a("href", uri).f();
							{ e("span").f();
								sx(o.strPaymentAmount());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1ReceiptGenPage() {
		{ e("tfoot").a("class", "w3-light-green w3-hover-light-green ").f();
			tfoot2ReceiptGenPage();
		} g("tfoot");
	}

	public void tfoot2ReceiptGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolReceipt.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
			if(getColumnCreated()) {
				e("td").f();
				g("td");
			}
			if(getColumnObjectTitle()) {
				e("td").f();
				g("td");
			}
			if(getColumnPaymentShortName()) {
				e("td").f();
				g("td");
			}
			if(getColumnPaymentDate()) {
				e("td").f();
				g("td");
			}
			if(getColumnPaymentAmount()) {
				e("td").f();
				BigDecimal sum_paymentAmount = Optional.ofNullable((Double)facets.get("sum_paymentAmount")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING));
				e("span").a("class", "font-weight-bold ").f().sx(sum_paymentAmount).g("span");
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

	public Boolean getColumnPaymentShortName() {
		return true;
	}

	public Boolean getColumnPaymentDate() {
		return true;
	}

	public Boolean getColumnPaymentAmount() {
		return true;
	}

	public void htmlBodyFormsReceiptGenPage() {
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listSchoolReceipt != null && listSchoolReceipt.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-green ")
						.a("id", "refreshThisReceiptGenPage")
						.a("onclick", "patchSchoolReceiptVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisReceiptGenPage')); }, function() { addError($('#refreshThisReceiptGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("refresh this receipt");
				} g("button");
			}

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-green ")
				.a("onclick", "$('#postSchoolReceiptModal').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Create a receipt");
			} g("button");
			{ e("div").a("id", "postSchoolReceiptModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolReceiptModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Create a receipt").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "postSchoolReceiptFormValues").f();
							SchoolReceipt o = new SchoolReceipt();
							o.setSiteRequest_(siteRequest_);

							// Form POST
							{ e("div").a("id", "postSchoolReceiptForm").f();
								htmlFormPOSTSchoolReceipt(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-green ")
								.a("onclick", "postSchoolReceipt($('#postSchoolReceiptForm')); ")
								.f().sx("Create a receipt")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-green ")
				.a("onclick", "$('#putimportSchoolReceiptModal').show(); ")
				.f();
				e("i").a("class", "fas fa-file-import ").f().g("i");
				sx("Import receipts");
			} g("button");
			{ e("div").a("id", "putimportSchoolReceiptModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putimportSchoolReceiptModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Import receipts").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "putimportSchoolReceiptFormValues").f();
							SchoolReceipt o = new SchoolReceipt();
							o.setSiteRequest_(siteRequest_);

							// Form PUT
							{ e("div").a("id", "putimportSchoolReceiptForm").f();
								htmlFormPUTImportSchoolReceipt(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-green ")
								.a("onclick", "putimportSchoolReceipt($('#putimportSchoolReceiptForm')); ")
								.f().sx("Import receipts")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-green ")
				.a("onclick", "$('#putmergeSchoolReceiptModal').show(); ")
				.f();
				e("i").a("class", "fas fa-code-merge ").f().g("i");
				sx("Merge receipts");
			} g("button");
			{ e("div").a("id", "putmergeSchoolReceiptModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putmergeSchoolReceiptModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Merge receipts").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "putmergeSchoolReceiptFormValues").f();
							SchoolReceipt o = new SchoolReceipt();
							o.setSiteRequest_(siteRequest_);

							// Form PUT
							{ e("div").a("id", "putmergeSchoolReceiptForm").f();
								htmlFormPUTMergeSchoolReceipt(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-green ")
								.a("onclick", "putmergeSchoolReceipt($('#putmergeSchoolReceiptForm')); ")
								.f().sx("Merge receipts")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-green ")
				.a("onclick", "$('#putcopySchoolReceiptModal').show(); ")
				.f();
				e("i").a("class", "fas fa-copy ").f().g("i");
				sx("Duplicate receipts");
			} g("button");
			{ e("div").a("id", "putcopySchoolReceiptModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putcopySchoolReceiptModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Duplicate receipts").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "putcopySchoolReceiptFormValues").f();
							SchoolReceipt o = new SchoolReceipt();
							o.setSiteRequest_(siteRequest_);

							// Form PUT
							{ e("div").a("id", "putcopySchoolReceiptForm").f();
								htmlFormPUTCopySchoolReceipt(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-green ")
								.a("onclick", "putcopySchoolReceipt($('#putcopySchoolReceiptForm'), ", schoolReceipt_ == null ? "null" : schoolReceipt_.getPk(), "); ")
								.f().sx("Duplicate receipts")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-green ")
				.a("onclick", "$('#patchSchoolReceiptModal').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modify receipts");
			} g("button");
			{ e("div").a("id", "patchSchoolReceiptModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-light-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolReceiptModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modify receipts").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").a("id", "patchSchoolReceiptFormValues").f();
							SchoolReceipt o = new SchoolReceipt();
							o.setSiteRequest_(siteRequest_);

							htmlFormPATCHSchoolReceipt(o);
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-light-green ")
								.a("onclick", "patchSchoolReceipt(null, $('#patchSchoolReceiptFormValues'), ", Optional.ofNullable(schoolReceipt_).map(SchoolReceipt::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modify receipts")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
		htmlSuggestedReceiptGenPage(this, null, listSchoolReceipt);
	}

	/**
	**/
	public static void htmlSuggestedReceiptGenPage(PageLayout p, String id, SearchList<SchoolReceipt> listSchoolReceipt) {
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

			Integer rows1 = Optional.ofNullable(listSchoolReceipt).map(l -> l.getRows()).orElse(10);
			Integer start1 = Optional.ofNullable(listSchoolReceipt).map(l -> l.getStart()).orElse(1);
			Integer start2 = start1 - rows1;
			Integer start3 = start1 + rows1;
			Integer rows2 = rows1 / 2;
			Integer rows3 = rows1 * 2;
			start2 = start2 < 0 ? 0 : start2;
			StringBuilder fqs = new StringBuilder();
			for(String fq : Optional.ofNullable(listSchoolReceipt).map(l -> l.getFilterQueries()).orElse(new String[0])) {
				if(!StringUtils.contains(fq, "(")) {
					String fq1 = StringUtils.substringBefore(fq, "_");
					String fq2 = StringUtils.substringAfter(fq, ":");
					if(!StringUtils.startsWithAny(fq, "classCanonicalNames_", "archived_", "deleted_", "sessionId", "userKeys"))
						fqs.append("&fq=").append(fq1).append(":").append(fq2);
				}
			}
			StringBuilder sorts = new StringBuilder();
			for(SortClause sort : Optional.ofNullable(listSchoolReceipt).map(l -> l.getSorts()).orElse(Arrays.asList())) {
				sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
			}

			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ReceiptGenPage.ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ReceiptGenPage.ROLES)
					) {
					{ p.e("div").a("class", "").f();
						{ p.e("button").a("id", "refreshAllReceiptGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-green ").a("onclick", "patchSchoolReceiptVals([], {}, function() { addGlow($('#refreshAllReceiptGenPage", id, "')); }, function() { addError($('#refreshAllReceiptGenPage", id, "')); }); ").f();
							p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
							p.sx("refresh all the receipts");
						} p.g("button");
					} p.g("div");
			}
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell ").f();
					{ p.e("span").f();
						p.sx("search receipts: ");
					} p.g("span");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "w3-bar ").f();

				p.e("input")
					.a("type", "text")
					.a("class", "suggestSchoolReceipt w3-input w3-border w3-bar-item ")
					.a("name", "suggestSchoolReceipt")
					.a("id", "suggestSchoolReceipt", id)
					.a("autocomplete", "off")
					.a("oninput", "suggestSchoolReceiptObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': 'pk,pageUrlPk,paymentCompleteName' } ], $('#suggestListSchoolReceipt", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
					.a("onkeyup", "if (event.keyCode === 13) { event.preventDefault(); window.location.href = '/receipt?q=", query1, ":' + encodeURIComponent(this.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; }"); 
				if(listSchoolReceipt != null)
					p.a("value", query2);
				p.fg();
				{ p.e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-green ")
					.a("onclick", "window.location.href = '/receipt?q=", query1, ":' + encodeURIComponent(this.previousElementSibling.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; ") 
					.f();
					p.e("i").a("class", "fas fa-search ").f().g("i");
				} p.g("button");

			} p.g("div");
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
					{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchoolReceipt", id).f();
					} p.g("ul");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/receipt").a("class", "").f();
					p.e("i").a("class", "fas fa-file-invoice-dollar ").f().g("i");
					p.sx("see all the receipts");
				} p.g("a");
			} p.g("div");
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

}
