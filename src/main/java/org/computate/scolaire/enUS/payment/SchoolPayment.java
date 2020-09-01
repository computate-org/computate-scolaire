package org.computate.scolaire.enUS.payment;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.search.SearchList;

/**
 * Model: true
 * Api: true
 * Page: true
 * Saved: true
 * Color: green
 * IconGroup: solid
 * IconName: search-dollar
 * Role.enUS: SiteAdmin
 * ApiUri.enUS: /api/payment
 * ApiTag.enUS: Payment
 * AName.enUS: a payment
 * Role.frFR: SiteAdmin
 * ApiUri.frFR: /api/paiement
 * ApiTag.frFR: Paiement
 * AName.frFR: un paiement
 * CanonicalName: org.computate.scolaire.frFR.paiement.PaiementScolaire
 **/
public class SchoolPayment extends SchoolPaymentGen<Cluster> {

	protected void _paymentKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _enrollmentKey(Wrap<Long> c) {}

	protected void _enrollmentSearch(SearchList<SchoolEnrollment> l) {
		l.setQuery("*:*");
		l.addFilterQuery("paymentKeys_indexed_longs:" + pk);
		l.setC(SchoolEnrollment.class);
		l.setStore(true);
		l.addFacetField("userKeys_indexed_longs");
	}

	protected void _enrollment_(Wrap<SchoolEnrollment> c) {
		if(enrollmentSearch.size() == 1) {
			c.o(enrollmentSearch.get(0));
		}
	}

	protected void _schoolNumber(Wrap<Integer> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getSchoolNumber());
	}

	protected void _userKeys(List<Long> l) {
		l.addAll(enrollmentSearch.getQueryResponse().getFacetField("userKeys_indexed_longs").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	protected void _schoolKey(Wrap<Long> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getSchoolKey());
	}

	protected void _schoolAddress(Wrap<String> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getSchoolAddress());
	}

	protected void _schoolPhoneNumber(Wrap<String> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getSchoolPhoneNumber());
	}

	protected void _yearKey(Wrap<Long> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getYearKey());
	}

	protected void _sessionKey(Wrap<Long> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getSessionKey());
	}

	protected void _ageKey(Wrap<Long> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getAgeKey());
	}

	protected void _blockKey(Wrap<Long> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getBlockKey());
	}

	protected void _childKey(Wrap<Long> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getChildKey());
	}

	protected void _momKeys(List<Long> l) {
		if(enrollment_ != null)
			l.addAll(enrollment_.getMomKeys());
	}

	protected void _dadKeys(List<Long> l) {
		if(enrollment_ != null)
			l.addAll(enrollment_.getDadKeys());
	}

	protected void _guardianKeys(List<Long> l) {
		if(enrollment_ != null)
			l.addAll(enrollment_.getGuardianKeys());
	}

	protected void _childCompleteNamePreferred(Wrap<String> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getChildCompleteNamePreferred());
	}

	protected void _childBirthDate(Wrap<LocalDate> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getChildBirthDate());
	}

	protected void _momCompleteNamePreferred(Wrap<String> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getMomCompleteNamePreferred());
	}

	protected void _dadCompleteNamePreferred(Wrap<String> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getDadCompleteNamePreferred());
	}

	protected void _schoolName(Wrap<String> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getSchoolName());
	}

	protected void _schoolCompleteName(Wrap<String> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getSchoolCompleteName());
	}

	protected void _schoolLocation(Wrap<String> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getSchoolLocation());
	}

	protected void _yearStart(Wrap<Integer> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getYearStart());
	}

	protected void _yearEnd(Wrap<Integer> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getYearEnd());
	}

	protected void _seasonStartDate(Wrap<LocalDate> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getSeasonStartDate());
	}

	protected void _yearEnrollmentFee(Wrap<BigDecimal> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getYearEnrollmentFee());
	}

	protected void _sessionStartDate(Wrap<LocalDate> c) {
		if(enrollment_ != null)
			c.o((LocalDate)enrollment_.getSessionStartDate());
	}

	protected void _sessionEndDate(Wrap<LocalDate> c) {
		if(enrollment_ != null)
			c.o((LocalDate)enrollment_.getSessionEndDate());
	}

	protected void _ageStart(Wrap<Integer> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getAgeStart());
	}

	protected void _ageEnd(Wrap<Integer> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getAgeEnd());
	}

	protected void _blockStartTime(Wrap<LocalTime> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getBlockStartTime());
	}

	protected void _blockEndTime(Wrap<LocalTime> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getBlockEndTime());
	}

	protected void _blockPricePerMonth(Wrap<BigDecimal> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getBlockPricePerMonth());
	}

	protected void _blockTotalPrice(Wrap<BigDecimal> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getBlockTotalPrice());
	}

	protected void _enrollmentPaymentEachMonth(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _paymentDescription(Wrap<String> c) {
	}

	protected void _paymentDate(Wrap<LocalDate> c) {
	}

	protected void _paymentYear(Wrap<Integer> c) {
		if(paymentDate != null)
			c.o(paymentDate.getYear());
	}

	protected void _paymentAmount(Wrap<BigDecimal> c) {
	}

	protected void _paymentCash(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _paymentCheck(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _paymentECheck(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _paymentSystem(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _paymentType(Wrap<String> c) {
		if(BooleanUtils.isTrue(paymentCheck))
			c.o("check");
		else if(BooleanUtils.isTrue(paymentCash))
			c.o("cash");
		else if(BooleanUtils.isTrue(paymentSystem))
			c.o("authorize.net");
		else if(BooleanUtils.isTrue(paymentECheck))
			c.o("check");
	}

	protected void _paymentBy(Wrap<String> c) {
	}

	protected void _transactionId(Wrap<String> c) {
	}

	protected void _customerProfileId(Wrap<String> c) {
	}

	protected void _transactionStatus(Wrap<String> c) {
	}

	protected void _paymentRecieved(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _chargeAmount(Wrap<BigDecimal> c) {
	}

	protected void _chargeFirstLast(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _chargeEnrollment(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _chargeMonth(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _chargeLateFee(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _now(Wrap<LocalDate> c) {
		SiteConfig siteConfig = siteRequest_.getSiteConfig_();
		ZoneId zoneId = ZoneId.of(siteConfig.getSiteZone());
		LocalDate now = LocalDate.now(zoneId);
		c.o(now);
	}

	protected void _paymentDay(Wrap<Integer> c) {
		SiteConfig siteConfig = siteRequest_.getSiteConfig_();
		Integer paymentDay = siteConfig.getPaymentDay();
		c.o(paymentDay);
	}

	protected void _paymentNext(Wrap<LocalDate> c) {
		c.o(now.getDayOfMonth() < paymentDay ? now.withDayOfMonth(paymentDay) : now.plusMonths(1).withDayOfMonth(paymentDay));
	}

	protected void _chargeAmountDue(Wrap<BigDecimal> c) {
		if(chargeAmount != null 
				&& (
						chargeEnrollment && paymentDate.compareTo(now.plusDays(15)) <= 0 && paymentDate.withDayOfMonth(paymentDay).compareTo(now) >= 0
						|| chargeFirstLast && paymentDate.compareTo(now.plusDays(15)) <= 0 && paymentDate.withDayOfMonth(paymentDay).compareTo(now) >= 0
						|| paymentDate != null && paymentDate.compareTo(now.plusDays(15)) <= 0 && paymentDate.compareTo(now) >= 0
						|| chargeLateFee
				) && (chargeLateFee || chargeEnrollment || chargeFirstLast || paymentDate.compareTo(paymentNext.minusMonths(1)) > 0) && paymentDate.compareTo(paymentNext) <= 0)
			c.o(chargeAmount);
	}

	protected void _chargeAmountPassed(Wrap<BigDecimal> c) {
		if(chargeAmount != null && (chargeLateFee || paymentDate != null && paymentDate.compareTo(now) < 0))
			c.o(chargeAmount);
	}

	protected void _chargeAmountNotPassed(Wrap<BigDecimal> c) {
		if(chargeAmount != null && !chargeLateFee && paymentDate != null && paymentDate.compareTo(now) >= 0)
			c.o(chargeAmount);
	}

	protected void _chargeAmountFuture(Wrap<BigDecimal> c) {
		if(chargeAmount != null && paymentDate != null 
				&& !chargeLateFee
				&& paymentDate.compareTo(now.plusDays(15)) > 0)
//				&& paymentDate.compareTo(paymentNext) > 0)
			c.o(chargeAmount);
	}

	protected void _paymentShortName(Wrap<String> c) {
		NumberFormat fn = NumberFormat.getCurrencyInstance(Locale.US);
		DateTimeFormatter fd = DateTimeFormatter.ofPattern("MMMM", Locale.US);
		DateTimeFormatter fd2 = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.US);
		fn.setMaximumFractionDigits(2);

		StringBuilder o = new StringBuilder();
		if(chargeAmount != null) {
			if(enrollment_ != null && chargeFirstLast && enrollment_.getSessionStartDate() != null && enrollment_.getSessionEndDate() != null)
				o.append(String.format("%s + %s tuition", fd.format(enrollment_.getSessionStartDate().plusWeeks(1)), fd.format(enrollment_.getSessionEndDate())));
			else if(enrollment_ != null && chargeEnrollment && enrollment_.getSessionStartDate() != null && enrollment_.getSessionEndDate() != null)
				o.append(String.format("%s-%s enrollment fee", enrollment_.getSessionStartDate().getYear(), enrollment_.getSessionEndDate().getYear()));
			else if(enrollment_ != null && chargeLateFee && chargeAmount != null)
				o.append(String.format("%s late fee", fn.format(chargeAmount)));
			else if(paymentDate != null)
				o.append(String.format("%s tuition", fd.format(paymentDate.plusMonths(1))));

			if(childCompleteNamePreferred != null)
				o.append(String.format(" for %s", childCompleteNamePreferred));
		}
		if(chargeAmount != null && paymentAmount != null) {
			o.append(" and ");
		}
		if(paymentAmount != null) {
			o.append(" ").append(fn.format(paymentAmount));
			o.append(" payment");
			if(childCompleteNamePreferred != null)
				o.append(String.format(" for %s", childCompleteNamePreferred));
			if(BooleanUtils.isTrue(paymentCheck))
				o.append(" by check");
			if(BooleanUtils.isTrue(paymentCash))
				o.append(" by cash");
			if(BooleanUtils.isTrue(paymentSystem))
				o.append(" by authorize.net");
			if(BooleanUtils.isTrue(paymentECheck))
				o.append(" by e-check");
		}
		if(!StringUtils.isEmpty(paymentDescription))
			o.append(" ").append(paymentDescription);
		if(o.length() == 0)
			o.append("new");
		c.o(o.toString());
	}

	protected void _paymentCompleteName(Wrap<String> c) {
		NumberFormat fn = NumberFormat.getCurrencyInstance(Locale.US);
		DateTimeFormatter fd = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.US);
		DateTimeFormatter fd2 = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.US);
		fn.setMaximumFractionDigits(2);
		StringBuilder o = new StringBuilder();

		if(paymentDate != null)
			o.append(paymentDate.format(fd2));
		if(chargeAmount != null) {
			if(enrollment_ != null && chargeFirstLast && enrollment_.getSessionStartDate() != null && enrollment_.getSessionEndDate() != null)
				o.append(" ").append(String.format("%s %s + %s tuition", fn.format(chargeAmount), fd.format(enrollment_.getSessionStartDate()), fd.format(enrollment_.getSessionEndDate())));
			else if(enrollment_ != null && chargeEnrollment && enrollment_.getSessionStartDate() != null && enrollment_.getSessionEndDate() != null)
				o.append(" ").append(String.format("%s %s-%s enrollment fee", fn.format(chargeAmount), fd.format(enrollment_.getSessionStartDate()), fd.format(enrollment_.getSessionEndDate())));
			else if(enrollment_ != null && chargeLateFee && chargeAmount != null)
				o.append(" ").append(String.format("%s late fee", fn.format(chargeAmount)));
			else if(chargeAmount != null && paymentDate != null)
				o.append(" ").append(String.format("%s %s tuition", fn.format(chargeAmount), fd.format(paymentDate.plusMonths(1))));

			if(childCompleteNamePreferred != null)
				o.append(String.format(" for %s", childCompleteNamePreferred));
		}
		if(chargeAmount != null && paymentAmount != null) {
			o.append(" and ");
		}
		if(paymentAmount != null) {
			o.append(" ").append(fn.format(paymentAmount));
			o.append(" payment");
			if(childCompleteNamePreferred != null)
				o.append(String.format(" for %s", childCompleteNamePreferred));
			if(BooleanUtils.isTrue(paymentCheck))
				o.append(" by check");
			if(BooleanUtils.isTrue(paymentCash))
				o.append(" by cash");
			if(BooleanUtils.isTrue(paymentSystem))
				o.append(" by authorize.net");
			if(BooleanUtils.isTrue(paymentECheck))
				o.append(" by e-check");
		}
		if(!StringUtils.isEmpty(paymentDescription))
			o.append(" ").append(paymentDescription);
		if(o.length() == 0)
			o.append("new");
		c.o(o.toString());
	}

	@Override()
	protected void  _objectText(Wrap<String> c) {
		NumberFormat fn = NumberFormat.getCurrencyInstance(Locale.US);
		DateTimeFormatter fd = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.US);
		DateTimeFormatter fd2 = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.US);
		fn.setMaximumFractionDigits(2);
		StringBuilder o = new StringBuilder();

		if(paymentDate != null)
			o.append(paymentDate.format(fd2));
		if(chargeAmount != null) {
			if(enrollment_ != null && chargeFirstLast && enrollment_.getSessionStartDate() != null && enrollment_.getSessionEndDate() != null)
				o.append(" ").append(String.format("%s + %s tuition", fn.format(chargeAmount), fd.format(enrollment_.getSessionStartDate()), fd.format(enrollment_.getSessionEndDate())));
			else if(enrollment_ != null && chargeEnrollment && enrollment_.getSessionStartDate() != null && enrollment_.getSessionEndDate() != null)
				o.append(" ").append(String.format("%s-%s enrollment fee", fn.format(chargeAmount), fd.format(enrollment_.getSessionStartDate()), fd.format(enrollment_.getSessionEndDate())));
			else if(enrollment_ != null && chargeLateFee && chargeAmount != null)
				o.append(" ").append(String.format("%s late fee", fn.format(chargeAmount)));
			else if(chargeAmount != null && paymentDate != null)
				o.append(" ").append(String.format("%s tuition", fn.format(chargeAmount), fd.format(paymentDate.plusMonths(1))));

			if(childCompleteNamePreferred != null)
				o.append(String.format(" for %s", childCompleteNamePreferred));
		}
		if(chargeAmount != null && paymentAmount != null) {
			o.append(" and ");
		}
		if(paymentAmount != null) {
			o.append(" ").append(fn.format(paymentAmount));
			o.append(" payment");
			if(childCompleteNamePreferred != null)
				o.append(String.format(" for %s", childCompleteNamePreferred));
			if(BooleanUtils.isTrue(paymentCheck))
				o.append(" by check");
			if(BooleanUtils.isTrue(paymentCash))
				o.append(" by cash");
			if(BooleanUtils.isTrue(paymentSystem))
				o.append(" by authorize.net");
			if(BooleanUtils.isTrue(paymentECheck))
				o.append(" by e-check");
		}
		if(!StringUtils.isEmpty(paymentDescription))
			o.append(" ").append(paymentDescription);
		if(o.length() == 0)
			o.append("new");
		c.o(o.toString());
	}
}
