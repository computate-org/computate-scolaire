package org.computate.scolaire.enUS.payment;

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
public class PaymentGenPage extends PaymentGenPageGen<ClusterPage> {

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("User");

	/**
	 * {@inheritDoc}
	 * 
	 **/
	protected void _listSchoolPayment(Wrap<SearchList<SchoolPayment>> c) {
	}

	protected void _schoolPayment_(Wrap<SchoolPayment> c) {
		if(listSchoolPayment != null && listSchoolPayment.size() == 1)
			c.o(listSchoolPayment.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("payments");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(schoolPayment_ != null && schoolPayment_.getPaymentCompleteName() != null)
			c.o(schoolPayment_.getPaymentCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolPayment_ != null && schoolPayment_.getPaymentCompleteName() != null)
			c.o(schoolPayment_.getPaymentCompleteName());
		else if(schoolPayment_ != null)
			c.o("payments");
		else if(listSchoolPayment == null || listSchoolPayment.size() == 0)
			c.o("no payment found");
		else
			c.o("payments");
	}

	@Override protected void _pageUri(Wrap<String> c) {
		c.o("/payment");
	}

	@Override protected void _pageImageUri(Wrap<String> c) {
			c.o("/png/payment-999.png");
	}

	@Override protected void _contextIconGroup(Wrap<String> c) {
			c.o("solid");
	}

	@Override protected void _contextIconName(Wrap<String> c) {
			c.o("search-dollar");
	}

	@Override public void initDeepPaymentGenPage() {
		initPaymentGenPage();
		super.initDeepPageLayout();
	}

	@Override public void htmlScriptsPaymentGenPage() {
		e("script").a("src", staticBaseUrl, "/js/enUS/PaymentPage.js").f().g("script");
		e("script").a("src", staticBaseUrl, "/js/enUS/EnrollmentPage.js").f().g("script");
	}

	@Override public void htmlScriptPaymentGenPage() {
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
			tl(2, "suggestSchoolPaymentEnrollmentKey([{'name':'fq','value':'paymentKeys:' + pk}], $('#listSchoolPaymentEnrollmentKey_Page'), pk, true); ");
		} else {
			tl(2, "suggestSchoolPaymentEnrollmentKey([{'name':'fq','value':'paymentKeys:' + pk}], $('#listSchoolPaymentEnrollmentKey_Page'), pk, false); ");
		}
		tl(1, "}");
		tl(1, "websocketSchoolPayment(websocketSchoolPaymentInner);");
		l("});");
	}

	public void htmlFormPageSchoolPayment(SchoolPayment o) {
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
			o.htmPaymentCash("Page");
			o.htmPaymentCheck("Page");
			o.htmPaymentECheck("Page");
			o.htmPaymentSystem("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaymentDescription("Page");
			o.htmPaymentBy("Page");
			o.htmEnrollmentPaymentEachMonth("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTransactionId("Page");
			o.htmCustomerProfileId("Page");
			o.htmTransactionStatus("Page");
			o.htmPaymentRecieved("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChargeAmount("Page");
			o.htmChargeFirstLast("Page");
			o.htmChargeEnrollment("Page");
			o.htmChargeMonth("Page");
			o.htmChargeLateFee("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKey("Page");
		} g("div");
	}

	public void htmlFormPOSTSchoolPayment(SchoolPayment o) {
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
			o.htmPaymentCash("POST");
			o.htmPaymentCheck("POST");
			o.htmPaymentECheck("POST");
			o.htmPaymentSystem("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaymentDescription("POST");
			o.htmPaymentBy("POST");
			o.htmEnrollmentPaymentEachMonth("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTransactionId("POST");
			o.htmCustomerProfileId("POST");
			o.htmTransactionStatus("POST");
			o.htmPaymentRecieved("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChargeAmount("POST");
			o.htmChargeFirstLast("POST");
			o.htmChargeEnrollment("POST");
			o.htmChargeMonth("POST");
			o.htmChargeLateFee("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKey("POST");
		} g("div");
	}

	public void htmlFormPUTImportSchoolPayment(SchoolPayment o) {
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

	public void htmlFormPUTMergeSchoolPayment(SchoolPayment o) {
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

	public void htmlFormPUTCopySchoolPayment(SchoolPayment o) {
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
			o.htmPaymentCash("PUTCopy");
			o.htmPaymentCheck("PUTCopy");
			o.htmPaymentECheck("PUTCopy");
			o.htmPaymentSystem("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaymentDescription("PUTCopy");
			o.htmPaymentBy("PUTCopy");
			o.htmEnrollmentPaymentEachMonth("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTransactionId("PUTCopy");
			o.htmCustomerProfileId("PUTCopy");
			o.htmTransactionStatus("PUTCopy");
			o.htmPaymentRecieved("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChargeAmount("PUTCopy");
			o.htmChargeFirstLast("PUTCopy");
			o.htmChargeEnrollment("PUTCopy");
			o.htmChargeMonth("PUTCopy");
			o.htmChargeLateFee("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKey("PUTCopy");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("PUTCopy");
			o.htmSessionId("PUTCopy");
			o.htmUserId("PUTCopy");
			o.htmUserKey("PUTCopy");
			o.htmChildCompleteNamePreferred("PUTCopy");
			o.htmChildBirthDate("PUTCopy");
			o.htmMomCompleteNamePreferred("PUTCopy");
			o.htmDadCompleteNamePreferred("PUTCopy");
			o.htmChargeAmountDue("PUTCopy");
			o.htmChargeAmountPassed("PUTCopy");
			o.htmChargeAmountNotPassed("PUTCopy");
			o.htmChargeAmountFuture("PUTCopy");
			o.htmPaymentShortName("PUTCopy");
		} g("div");
	}

	public void htmlFormPATCHSchoolPayment(SchoolPayment o) {
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
			o.htmPaymentCash("PATCH");
			o.htmPaymentCheck("PATCH");
			o.htmPaymentECheck("PATCH");
			o.htmPaymentSystem("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaymentDescription("PATCH");
			o.htmPaymentBy("PATCH");
			o.htmEnrollmentPaymentEachMonth("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTransactionId("PATCH");
			o.htmCustomerProfileId("PATCH");
			o.htmTransactionStatus("PATCH");
			o.htmPaymentRecieved("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChargeAmount("PATCH");
			o.htmChargeFirstLast("PATCH");
			o.htmChargeEnrollment("PATCH");
			o.htmChargeMonth("PATCH");
			o.htmChargeLateFee("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKey("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("PATCH");
			o.htmSessionId("PATCH");
			o.htmUserId("PATCH");
			o.htmUserKey("PATCH");
			o.htmChildCompleteNamePreferred("PATCH");
			o.htmChildBirthDate("PATCH");
			o.htmMomCompleteNamePreferred("PATCH");
			o.htmDadCompleteNamePreferred("PATCH");
			o.htmChargeAmountDue("PATCH");
			o.htmChargeAmountPassed("PATCH");
			o.htmChargeAmountNotPassed("PATCH");
			o.htmChargeAmountFuture("PATCH");
			o.htmPaymentShortName("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolPayment(SchoolPayment o) {
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
			o.htmPaymentCash("Search");
			o.htmPaymentCheck("Search");
			o.htmPaymentECheck("Search");
			o.htmPaymentSystem("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaymentDescription("Search");
			o.htmPaymentBy("Search");
			o.htmEnrollmentPaymentEachMonth("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTransactionId("Search");
			o.htmCustomerProfileId("Search");
			o.htmTransactionStatus("Search");
			o.htmPaymentRecieved("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChargeAmount("Search");
			o.htmChargeFirstLast("Search");
			o.htmChargeEnrollment("Search");
			o.htmChargeMonth("Search");
			o.htmChargeLateFee("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKey("Search");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmInheritPk("Search");
			o.htmSessionId("Search");
			o.htmUserId("Search");
			o.htmUserKey("Search");
			o.htmObjectTitle("Search");
			o.htmChildCompleteNamePreferred("Search");
			o.htmChildBirthDate("Search");
			o.htmMomCompleteNamePreferred("Search");
			o.htmDadCompleteNamePreferred("Search");
			o.htmChargeAmountDue("Search");
			o.htmChargeAmountPassed("Search");
			o.htmChargeAmountNotPassed("Search");
			o.htmChargeAmountFuture("Search");
			o.htmPaymentShortName("Search");
		} g("div");
	}

	@Override public void htmlBodyPaymentGenPage() {

		OperationRequest operationRequest = siteRequest_.getOperationRequest();
		JsonObject params = operationRequest.getParams();
		if(listSchoolPayment == null || listSchoolPayment.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/payment").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("payments").g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("h2").f();
				{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx("no payment found").g("span");
				} g("span");
			} g("h2");
		} else if(listSchoolPayment != null && listSchoolPayment.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolPayment o = listSchoolPayment.get(0);
			siteRequest_.setRequestPk(o.getPk());
			if(StringUtils.isNotEmpty(pageH1)) {
				{ e("h1").f();
					{ e("a").a("href", "/payment").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
						if(contextIconCssClasses != null)
							e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
						e("span").a("class", " ").f().sx(pageH1).g("span");
					} g("a");
				} g("h1");
			}
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			if(StringUtils.isNotEmpty(pageH2)) {
				{ e("h2").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
						e("span").a("class", " ").f().sx(pageH2).g("span");
					} g("span");
				} g("h2");
			}
			if(StringUtils.isNotEmpty(pageH3)) {
				{ e("h3").f();
					{ e("span").a("class", "w3-bar-item w3-padding w3-center w3-block w3-green ").f();
						e("span").a("class", " ").f().sx(pageH3).g("span");
					} g("span");
				} g("h3");
			}
		} else {

			{ e("h1").f();
				{ e("a").a("href", "/payment").a("class", "w3-bar-item w3-btn w3-center w3-block w3-green w3-hover-green ").f();
					if(contextIconCssClasses != null)
						e("i").a("class", contextIconCssClasses + " site-menu-icon ").f().g("i");
					e("span").a("class", " ").f().sx(pageH1).g("span");
				} g("a");
			} g("h1");
			e("div").a("class", "").f();
				{ e("div").f();
					JsonObject queryParams = Optional.ofNullable(operationRequest).map(OperationRequest::getParams).map(or -> or.getJsonObject("query")).orElse(new JsonObject());
					Long num = listSchoolPayment.getQueryResponse().getResults().getNumFound();
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

					Integer rows1 = Optional.ofNullable(listSchoolPayment).map(l -> l.getRows()).orElse(10);
					Integer start1 = Optional.ofNullable(listSchoolPayment).map(l -> l.getStart()).orElse(1);
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;
					StringBuilder fqs = new StringBuilder();
					for(String fq : Optional.ofNullable(listSchoolPayment).map(l -> l.getFilterQueries()).orElse(new String[0])) {
						if(!StringUtils.contains(fq, "(")) {
							String fq1 = StringUtils.substringBefore(fq, "_");
							String fq2 = StringUtils.substringAfter(fq, ":");
							if(!StringUtils.startsWithAny(fq, "classCanonicalNames_", "archived_", "deleted_", "sessionId", "userKeys"))
								fqs.append("&fq=").append(fq1).append(":").append(fq2);
						}
					}
					StringBuilder sorts = new StringBuilder();
					for(SortClause sort : Optional.ofNullable(listSchoolPayment).map(l -> l.getSorts()).orElse(Arrays.asList())) {
						sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
					}

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/payment?q=", query, fqs, sorts, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/payment?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/payment?q=", query, fqs, sorts, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/payment?q=", query, fqs, sorts, "&start=", start3, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-right ").f().g("i");
						} g("a");
					}
						e("span").f().sx((start1 + 1), " - ", (start1 + rows1), " of ", num).g("span");
				} g("div");
				table1PaymentGenPage();
		}

		if(listSchoolPayment != null && listSchoolPayment.size() == 1 && params.getJsonObject("query").getString("q").equals("*:*")) {
			SchoolPayment o = listSchoolPayment.first();

			{ e("div").a("class", "").f();

				if(o.getPk() != null) {
					{ e("form").a("action", "").a("id", "SchoolPaymentForm").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
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
					htmlFormPageSchoolPayment(o);
				}

			} g("div");

		}
		htmlBodyFormsPaymentGenPage();
		g("div");
	}

	public void table1PaymentGenPage() {
		{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
			table2PaymentGenPage();
		} g("table");
	}

	public void table2PaymentGenPage() {
		thead1PaymentGenPage();
		tbody1PaymentGenPage();
		tfoot1PaymentGenPage();
	}

	public void thead1PaymentGenPage() {
		{ e("thead").a("class", "w3-green w3-hover-green ").f();
			thead2PaymentGenPage();
		} g("thead");
	}

	public void thead2PaymentGenPage() {
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
			if(getColumnChargeAmount()) {
				e("th").f().sx("charge amount").g("th");
			}
			} g("tr");
	}

	public void tbody1PaymentGenPage() {
		{ e("tbody").f();
			tbody2PaymentGenPage();
		} g("tbody");
	}

	public void tbody2PaymentGenPage() {
		Map<String, Map<String, List<String>>> highlighting = listSchoolPayment.getQueryResponse().getHighlighting();
		for(int i = 0; i < listSchoolPayment.size(); i++) {
			SchoolPayment o = listSchoolPayment.getList().get(i);
			Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
			List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
			String uri = "/payment/" + o.getPk();
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
							e("i").a("class", "fas fa-search-dollar ").f().g("i");
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
				if(getColumnChargeAmount()) {
					{ e("td").f();
						{ e("a").a("href", uri).f();
							{ e("span").f();
								sx(o.strChargeAmount());
							} g("span");
						} g("a");
					} g("td");
				}
			} g("tr");
		}
	}

	public void tfoot1PaymentGenPage() {
		{ e("tfoot").a("class", "w3-green w3-hover-green ").f();
			tfoot2PaymentGenPage();
		} g("tfoot");
	}

	public void tfoot2PaymentGenPage() {
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolPayment.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
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
			if(getColumnChargeAmount()) {
				e("td").f();
				BigDecimal sum_chargeAmount = Optional.ofNullable((Double)facets.get("sum_chargeAmount")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING));
				e("span").a("class", "font-weight-bold ").f().sx(sum_chargeAmount).g("span");
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

	public Boolean getColumnChargeAmount() {
		return true;
	}

	public void htmlBodyFormsPaymentGenPage() {
		if(
				CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
				) {
			e("div").a("class", "w3-margin-top ").f();

			if(listSchoolPayment != null && listSchoolPayment.size() == 1) {
				{ e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
						.a("id", "refreshThisPaymentGenPage")
						.a("onclick", "patchSchoolPaymentVals( [ {name: 'fq', value: 'pk:' + " + siteRequest_.getRequestPk() + " } ], {}, function() { addGlow($('#refreshThisPaymentGenPage')); }, function() { addError($('#refreshThisPaymentGenPage')); }); return false; ").f();
						e("i").a("class", "fas fa-sync-alt ").f().g("i");
					sx("refresh this payment");
				} g("button");
			}

			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("onclick", "$('#postSchoolPaymentModal').show(); ")
				.f();
				e("i").a("class", "fas fa-file-plus ").f().g("i");
				sx("Create a payment");
			} g("button");
			{ e("div").a("id", "postSchoolPaymentModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postSchoolPaymentModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Create a payment").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolPayment o = new SchoolPayment();
							o.setSiteRequest_(siteRequest_);

							// Form POST
							{ e("div").a("id", "postSchoolPaymentForm").f();
								htmlFormPOSTSchoolPayment(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-green ")
								.a("onclick", "postSchoolPayment($('#postSchoolPaymentForm')); ")
								.f().sx("Create a payment")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("onclick", "$('#putimportSchoolPaymentModal').show(); ")
				.f();
				e("i").a("class", "fas fa-file-import ").f().g("i");
				sx("Import payments");
			} g("button");
			{ e("div").a("id", "putimportSchoolPaymentModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putimportSchoolPaymentModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Import payments").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolPayment o = new SchoolPayment();
							o.setSiteRequest_(siteRequest_);

							// Form PUT
							{ e("div").a("id", "putimportSchoolPaymentForm").f();
								htmlFormPUTImportSchoolPayment(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-green ")
								.a("onclick", "putimportSchoolPayment($('#putimportSchoolPaymentForm')); ")
								.f().sx("Import payments")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("onclick", "$('#putmergeSchoolPaymentModal').show(); ")
				.f();
				e("i").a("class", "fas fa-code-merge ").f().g("i");
				sx("Merge payments");
			} g("button");
			{ e("div").a("id", "putmergeSchoolPaymentModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putmergeSchoolPaymentModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Merge payments").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolPayment o = new SchoolPayment();
							o.setSiteRequest_(siteRequest_);

							// Form PUT
							{ e("div").a("id", "putmergeSchoolPaymentForm").f();
								htmlFormPUTMergeSchoolPayment(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-green ")
								.a("onclick", "putmergeSchoolPayment($('#putmergeSchoolPaymentForm')); ")
								.f().sx("Merge payments")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("onclick", "$('#putcopySchoolPaymentModal').show(); ")
				.f();
				e("i").a("class", "fas fa-copy ").f().g("i");
				sx("Duplicate payments");
			} g("button");
			{ e("div").a("id", "putcopySchoolPaymentModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putcopySchoolPaymentModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Duplicate payments").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolPayment o = new SchoolPayment();
							o.setSiteRequest_(siteRequest_);

							// Form PUT
							{ e("div").a("id", "putcopySchoolPaymentForm").f();
								htmlFormPUTCopySchoolPayment(o);
							} g("div");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-green ")
								.a("onclick", "putcopySchoolPayment($('#putcopySchoolPaymentForm'), ", schoolPayment_ == null ? "null" : schoolPayment_.getPk(), "); ")
								.f().sx("Duplicate payments")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			{ e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("onclick", "$('#patchSchoolPaymentModal').show(); ")
				.f();
				e("i").a("class", "fas fa-edit ").f().g("i");
				sx("Modify payments");
			} g("button");
			{ e("div").a("id", "patchSchoolPaymentModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolPaymentModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modify payments").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolPayment o = new SchoolPayment();
							o.setSiteRequest_(siteRequest_);

							// FormValues PATCH
							{ e("form").a("action", "").a("id", "patchSchoolPaymentFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPATCHSchoolPayment(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-green ")
								.a("onclick", "patchSchoolPayment(null, $('#patchSchoolPaymentFormValues'), ", Optional.ofNullable(schoolPayment_).map(SchoolPayment::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modify payments")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
		htmlSuggestedPaymentGenPage(this, null, listSchoolPayment);
	}

	/**
	**/
	public static void htmlSuggestedPaymentGenPage(PageLayout p, String id, SearchList<SchoolPayment> listSchoolPayment) {
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

			Integer rows1 = Optional.ofNullable(listSchoolPayment).map(l -> l.getRows()).orElse(10);
			Integer start1 = Optional.ofNullable(listSchoolPayment).map(l -> l.getStart()).orElse(1);
			Integer start2 = start1 - rows1;
			Integer start3 = start1 + rows1;
			Integer rows2 = rows1 / 2;
			Integer rows3 = rows1 * 2;
			start2 = start2 < 0 ? 0 : start2;
			StringBuilder fqs = new StringBuilder();
			for(String fq : Optional.ofNullable(listSchoolPayment).map(l -> l.getFilterQueries()).orElse(new String[0])) {
				if(!StringUtils.contains(fq, "(")) {
					String fq1 = StringUtils.substringBefore(fq, "_");
					String fq2 = StringUtils.substringAfter(fq, ":");
					if(!StringUtils.startsWithAny(fq, "classCanonicalNames_", "archived_", "deleted_", "sessionId", "userKeys"))
						fqs.append("&fq=").append(fq1).append(":").append(fq2);
				}
			}
			StringBuilder sorts = new StringBuilder();
			for(SortClause sort : Optional.ofNullable(listSchoolPayment).map(l -> l.getSorts()).orElse(Arrays.asList())) {
				sorts.append("&sort=").append(StringUtils.substringBefore(sort.getItem(), "_")).append(" ").append(sort.getOrder().name());
			}

			if(
					CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), PaymentGenPage.ROLES)
					|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), PaymentGenPage.ROLES)
					) {
					{ p.e("div").a("class", "").f();
						{ p.e("button").a("id", "refreshAllPaymentGenPage", id).a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ").a("onclick", "patchSchoolPaymentVals([], {}, function() { addGlow($('#refreshAllPaymentGenPage", id, "')); }, function() { addError($('#refreshAllPaymentGenPage", id, "')); }); ").f();
							p.e("i").a("class", "fas fa-sync-alt ").f().g("i");
							p.sx("refresh all the payments");
						} p.g("button");
					} p.g("div");
			}
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell ").f();
					{ p.e("span").f();
						p.sx("search payments: ");
					} p.g("span");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "w3-bar ").f();

				p.e("input")
					.a("type", "text")
					.a("class", "suggestSchoolPayment w3-input w3-border w3-bar-item ")
					.a("name", "suggestSchoolPayment")
					.a("id", "suggestSchoolPayment", id)
					.a("autocomplete", "off")
					.a("oninput", "suggestSchoolPaymentObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() }, { 'name': 'rows', 'value': '10' }, { 'name': 'fl', 'value': 'pk,pageUrlPk,paymentCompleteName' } ], $('#suggestListSchoolPayment", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
					.a("onkeyup", "if (event.keyCode === 13) { event.preventDefault(); window.location.href = '/payment?q=", query1, ":' + encodeURIComponent(this.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; }"); 
				if(listSchoolPayment != null)
					p.a("value", query2);
				p.fg();
				{ p.e("button")
					.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-green ")
					.a("onclick", "window.location.href = '/payment?q=", query1, ":' + encodeURIComponent(this.previousElementSibling.value) + '", fqs, sorts, "&start=", start2, "&rows=", rows1, "'; ") 
					.f();
					p.e("i").a("class", "fas fa-search ").f().g("i");
				} p.g("button");

			} p.g("div");
			{ p.e("div").a("class", "w3-cell-row ").f();
				{ p.e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
					{ p.e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggestListSchoolPayment", id).f();
					} p.g("ul");
				} p.g("div");
			} p.g("div");
			{ p.e("div").a("class", "").f();
				{ p.e("a").a("href", "/payment").a("class", "").f();
					p.e("i").a("class", "fas fa-search-dollar ").f().g("i");
					p.sx("see all the payments");
				} p.g("a");
			} p.g("div");
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

}
