package org.computate.scolaire.enUS.payment;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.util.SimpleOrderedMap;

/**
 * Translate: false
 **/
public class PaymentPage extends PaymentPageGen<PaymentGenPage> {

	@Override public Boolean getColumnCreated() {
		return false;
	}

	@Override public Boolean getColumnObjectTitle() {
		return false;
	}

	@Override public void table1PaymentGenPage() {
		SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolPayment.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
		BigDecimal sum_paymentAmount = Optional.ofNullable((Double)facets.get("sum_paymentAmount")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2));
		BigDecimal sum_chargeAmount = Optional.ofNullable((Double)facets.get("sum_chargeAmount")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2));
		BigDecimal sum_chargeAmountFuture = Optional.ofNullable((Double)facets.get("sum_chargeAmountFuture")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2));
		BigDecimal sum_chargeAmountDue = Optional.ofNullable((Double)facets.get("sum_chargeAmountDue")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2));
		if(sum_chargeAmount.subtract(sum_paymentAmount).subtract(sum_chargeAmountFuture).compareTo(BigDecimal.ZERO) <= 0) {
			e("div").a("class", "w3-panel w3-green ").f();
			sx("You are current with all payments. Thank you! ");
			g("div");
		}
		else {
			if(sum_chargeAmount.subtract(sum_chargeAmountDue).subtract(sum_chargeAmountFuture).subtract(sum_paymentAmount).compareTo(BigDecimal.ZERO) > 0) {
				BigDecimal amount = sum_chargeAmount.subtract(sum_chargeAmountFuture).subtract(sum_paymentAmount);
				e("div").a("class", "w3-panel w3-red ").f();
				sx(String.format("You are late on payments for $%s. ", amount));
				g("div");
			}
			BigDecimal amount = sum_chargeAmount.subtract(sum_paymentAmount).subtract(sum_chargeAmountFuture);
			e("div").a("class", "w3-panel w3-blue ").f();
			sx(String.format("Please pay the upcoming charges of $%s by the payment date to avoid any late fees. ", amount));
			g("div");
			if(listSchoolPayment.size() > 0) {
				SchoolPayment payment = listSchoolPayment.first();
				Long enrollmentKey = payment.getEnrollmentKey();
				String childCompleteNamePreferred = payment.getChildCompleteNamePreferred();
				if(enrollmentKey != null && childCompleteNamePreferred != null) {
					writeMakePayment(amount, enrollmentKey, childCompleteNamePreferred);
				}
			}
		}
		super.table1PaymentGenPage();
	}

	@Override public void tfoot2PaymentGenPage() {
		super.tfoot2PaymentGenPage();
		{ e("tr").f();
			SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(listSchoolPayment.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
			BigDecimal sum_paymentAmount = Optional.ofNullable((Double)facets.get("sum_paymentAmount")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2));
			BigDecimal sum_chargeAmount = Optional.ofNullable((Double)facets.get("sum_chargeAmount")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2));
			List<String> childCompleteNamesPreferred = Optional.ofNullable((SimpleOrderedMap)facets.get("terms_childCompleteNamePreferred")).map(m -> ((List<SimpleOrderedMap>)m.get("buckets"))).orElse(Arrays.asList()).stream().collect(Collectors.mapping(m -> ((String)m.get("val")), Collectors.toList()));
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
				if(childCompleteNamesPreferred.size() == 1)
					e("span").a("class", "font-weight-bold ").f().sx(String.format("Balance for %s: ", childCompleteNamesPreferred.get(0))).g("span");
				else
					e("span").a("class", "font-weight-bold ").f().sx("Balance: ").g("span");
				g("td");
			}
			if(getColumnChargeAmount()) {
				e("td").f();
				e("span").a("class", "font-weight-bold ").f().sx(sum_chargeAmount.subtract(sum_paymentAmount)).g("span");
				g("td");
			}
		} g("tr");
	}
}
