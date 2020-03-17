package org.computate.scolaire.enUS.payment;

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

	protected void _schoolPayment(Wrap<SchoolPayment> c) {
		if(listSchoolPayment != null && listSchoolPayment.size() == 1)
			c.o(listSchoolPayment.get(0));
	}

	@Override protected void _pageH1(Wrap<String> c) {
			c.o("payments");
	}

	@Override protected void _pageH2(Wrap<String> c) {
		if(schoolPayment != null && schoolPayment.getPaymentCompleteName() != null)
			c.o(schoolPayment.getPaymentCompleteName());
	}

	@Override protected void _pageH3(Wrap<String> c) {
		c.o("");
	}

	@Override protected void _pageTitle(Wrap<String> c) {
		if(schoolPayment != null && schoolPayment.getPaymentCompleteName() != null)
			c.o(schoolPayment.getPaymentCompleteName());
		else if(schoolPayment != null)
			c.o("");
		else if(listSchoolPayment == null || listSchoolPayment.size() == 0)
			c.o("no payment found");
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
		tl(1, "window.eventBus = new EventBus('/eventbus');");
		tl(1, "var pk = ", Optional.ofNullable(siteRequest_.getRequestPk()).map(l -> l.toString()).orElse("null"), ";");
		tl(1, "if(pk != null) {");
		tl(2, "suggestSchoolPaymentEnrollmentKey([{'name':'fq','value':'paymentKeys:' + pk}], $('#listSchoolPaymentEnrollmentKey_Page'), pk); ");
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
			o.htmPaymentSystem("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaymentDescription("Page");
			o.htmEnrollmentPaymentComplete("Page");
			o.htmPaymentBy("Page");
			o.htmEnrollmentPaymentEachMonth("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTransactionId("Page");
			o.htmCustomerProfileId("Page");
			o.htmTransactionStatus("Page");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKey("Page");
			o.htmPaymentRecieved("Page");
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
			o.htmPaymentSystem("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaymentDescription("POST");
			o.htmEnrollmentPaymentComplete("POST");
			o.htmPaymentBy("POST");
			o.htmEnrollmentPaymentEachMonth("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTransactionId("POST");
			o.htmCustomerProfileId("POST");
			o.htmTransactionStatus("POST");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKey("POST");
			o.htmPaymentRecieved("POST");
		} g("div");
	}

	public void htmlFormPUTSchoolPayment(SchoolPayment o) {
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmCreated("PUT");
			o.htmModified("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmArchived("PUT");
			o.htmDeleted("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaymentDate("PUT");
			o.htmPaymentAmount("PUT");
			o.htmPaymentCash("PUT");
			o.htmPaymentCheck("PUT");
			o.htmPaymentSystem("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaymentDescription("PUT");
			o.htmEnrollmentPaymentComplete("PUT");
			o.htmPaymentBy("PUT");
			o.htmEnrollmentPaymentEachMonth("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTransactionId("PUT");
			o.htmCustomerProfileId("PUT");
			o.htmTransactionStatus("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKey("PUT");
			o.htmPaymentRecieved("PUT");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildCompleteNamePreferred("PUT");
			o.htmChildBirthDate("PUT");
			o.htmMomCompleteNamePreferred("PUT");
			o.htmDadCompleteNamePreferred("PUT");
			o.htmChargeAmount("PUT");
			o.htmChargeAmountFuture("PUT");
			o.htmChargeEnrollment("PUT");
			o.htmChargeFirstLast("PUT");
			o.htmChargeMonth("PUT");
			o.htmPaymentShortName("PUT");
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
			o.htmPaymentSystem("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaymentDescription("PATCH");
			o.htmEnrollmentPaymentComplete("PATCH");
			o.htmPaymentBy("PATCH");
			o.htmEnrollmentPaymentEachMonth("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTransactionId("PATCH");
			o.htmCustomerProfileId("PATCH");
			o.htmTransactionStatus("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKey("PATCH");
			o.htmPaymentRecieved("PATCH");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmChildCompleteNamePreferred("PATCH");
			o.htmChildBirthDate("PATCH");
			o.htmMomCompleteNamePreferred("PATCH");
			o.htmDadCompleteNamePreferred("PATCH");
			o.htmChargeAmount("PATCH");
			o.htmChargeAmountFuture("PATCH");
			o.htmChargeEnrollment("PATCH");
			o.htmChargeFirstLast("PATCH");
			o.htmChargeMonth("PATCH");
			o.htmPaymentShortName("PATCH");
		} g("div");
	}

	public void htmlFormSearchSchoolPayment(SchoolPayment o) {
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
			o.htmPaymentDate("Recherche");
			o.htmPaymentAmount("Recherche");
			o.htmPaymentCash("Recherche");
			o.htmPaymentCheck("Recherche");
			o.htmPaymentSystem("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmPaymentDescription("Recherche");
			o.htmEnrollmentPaymentComplete("Recherche");
			o.htmPaymentBy("Recherche");
			o.htmEnrollmentPaymentEachMonth("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmTransactionId("Recherche");
			o.htmCustomerProfileId("Recherche");
			o.htmTransactionStatus("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmEnrollmentKey("Recherche");
			o.htmPaymentRecieved("Recherche");
		} g("div");
		{ e("div").a("class", "w3-cell-row ").f();
			o.htmObjectTitle("Recherche");
			o.htmChildCompleteNamePreferred("Recherche");
			o.htmChildBirthDate("Recherche");
			o.htmMomCompleteNamePreferred("Recherche");
			o.htmDadCompleteNamePreferred("Recherche");
			o.htmChargeAmount("Recherche");
			o.htmChargeAmountFuture("Recherche");
			o.htmChargeEnrollment("Recherche");
			o.htmChargeFirstLast("Recherche");
			o.htmChargeMonth("Recherche");
			o.htmPaymentShortName("Recherche");
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
					Long num = listSchoolPayment.getQueryResponse().getResults().getNumFound();
					String query = StringUtils.replace(listSchoolPayment.getQuery(), "_suggested", "");
					Integer rows1 = listSchoolPayment.getRows();
					Integer start1 = listSchoolPayment.getStart();
					Integer start2 = start1 - rows1;
					Integer start3 = start1 + rows1;
					Integer rows2 = rows1 / 2;
					Integer rows3 = rows1 * 2;
					start2 = start2 < 0 ? 0 : start2;

					if(start1 == 0) {
						e("i").a("class", "fas fa-arrow-square-left w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/payment?q=", query, "&start=", start2, "&rows=", rows1).f();
							e("i").a("class", "fas fa-arrow-square-left ").f().g("i");
						} g("a");
					}

					if(rows1 <= 1) {
						e("i").a("class", "fas fa-minus-square w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/payment?q=", query, "&start=", start1, "&rows=", rows2).f();
							e("i").a("class", "fas fa-minus-square ").f().g("i");
						} g("a");
					}

					{ e("a").a("href", "/payment?q=", query, "&start=", start1, "&rows=", rows3).f();
						e("i").a("class", "fas fa-plus-square ").f().g("i");
					} g("a");

					if(start3 >= num) {
						e("i").a("class", "fas fa-arrow-square-right w3-opacity ").f().g("i");
					} else {
						{ e("a").a("href", "/payment?q=", query, "&start=", start3, "&rows=", rows1).f();
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
		htmlSuggestPaymentGenPage(this, null);
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
				BigDecimal sum_paymentAmount = Optional.ofNullable((Double)facets.get("sum_paymentAmount")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2));
				e("span").a("class", "font-weight-bold ").f().sx(sum_paymentAmount).g("span");
				g("td");
			}
			if(getColumnChargeAmount()) {
				e("td").f();
				BigDecimal sum_chargeAmount = Optional.ofNullable((Double)facets.get("sum_chargeAmount")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2));
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

			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("onclick", "$('#postSchoolPaymentModal').show(); ")
				.f().sx("Create a payment")
			.g("button");
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


			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("onclick", "$('#putSchoolPaymentModal').show(); ")
				.f().sx("Duplicate the payments")
			.g("button");
			{ e("div").a("id", "putSchoolPaymentModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#putSchoolPaymentModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Duplicate the payments").g("h2");
						} g("header");
						{ e("div").a("class", "w3-container ").f();
							SchoolPayment o = new SchoolPayment();
							o.setSiteRequest_(siteRequest_);

							// FormValues PUT
							{ e("form").a("action", "").a("id", "putSchoolPaymentFormValues").a("onsubmit", "event.preventDefault(); return false; ").f();
								htmlFormPUTSchoolPayment(o);
							} g("form");
							e("button")
								.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-margin w3-green ")
								.a("onclick", "putSchoolPayment($('#putSchoolPaymentFormValues'), ", Optional.ofNullable(schoolPayment).map(SchoolPayment::getPk).map(a -> a.toString()).orElse("null"), "); ")
								.f().sx("Duplicate the payments")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");


			e("button")
				.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
				.a("onclick", "$('#patchSchoolPaymentModal').show(); ")
				.f().sx("Modify the payments")
			.g("button");
			{ e("div").a("id", "patchSchoolPaymentModal").a("class", "w3-modal w3-padding-32 ").f();
				{ e("div").a("class", "w3-modal-content ").f();
					{ e("div").a("class", "w3-card-4 ").f();
						{ e("header").a("class", "w3-container w3-green ").f();
							e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#patchSchoolPaymentModal').hide(); ").f().sx("×").g("span");
							e("h2").a("class", "w3-padding ").f().sx("Modify the payments").g("h2");
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
								.a("onclick", "patchSchoolPayment($('#patchSchoolPaymentFormFilters'), $('#patchSchoolPaymentFormValues'), ", Optional.ofNullable(schoolPayment).map(SchoolPayment::getPk).map(a -> a.toString()).orElse("null"), ", function() {}, function() {}); ")
								.f().sx("Modify the payments")
							.g("button");

						} g("div");
					} g("div");
				} g("div");
			} g("div");

			g("div");
		}
	}

	/**
	**/
	public static void htmlSuggestPaymentGenPage(PageLayout p, String id) {
		SiteRequestEnUS siteRequest_ = p.getSiteRequest_();
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

			{ p.e("span").a("class", "w3-bar-item w3-padding-small ").f();
				p.e("i").a("class", "far fa-search w3-xlarge w3-cell w3-cell-middle ").f().g("i");
			} p.g("span");
			p.e("input")
				.a("type", "text")
				.a("class", "suggestSchoolPayment w3-input w3-border w3-bar-item w3-padding-small ")
				.a("name", "suggestSchoolPayment")
				.a("id", "suggestSchoolPayment", id)
				.a("autocomplete", "off")
				.a("oninput", "suggestSchoolPaymentObjectSuggest( [ { 'name': 'q', 'value': 'objectSuggest:' + $(this).val() } ], $('#suggestListSchoolPayment", id, "'), ", p.getSiteRequest_().getRequestPk(), "); ")
				.fg();

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
	}

}
