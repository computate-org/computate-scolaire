package org.computate.scolaire.enUS.payment;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.search.SearchList;

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
	}

	protected void _enrollment_(Wrap<SchoolEnrollment> c) {
		if(enrollmentSearch.size() == 1) {
			c.o(enrollmentSearch.get(0));
		}
	}

	protected void _schoolKey(Wrap<Long> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getSchoolKey());
	}

	protected void _yearKey(Wrap<Long> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getYearKey());
	}

	protected void _seasonKey(Wrap<Long> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getSeasonKey());
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

	protected void _seasonSummer(Wrap<Boolean> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getSeasonSummer());
	}

	protected void _seasonWinter(Wrap<Boolean> c) {
		if(enrollment_ != null)
			c.o(enrollment_.getSeasonWinter());
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

	protected void _enrollmentPaymentComplete(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _paymentDescription(Wrap<String> c) {
	}

	protected void _paymentDate(Wrap<LocalDate> c) {
	}

	protected void _paymentAmount(Wrap<BigDecimal> c) {
	}

	protected void _chargeAmount(Wrap<BigDecimal> c) {
	}

	protected void _chargeAmountFuture(Wrap<BigDecimal> c) {
	}

	protected void _chargeEnrollment(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _chargeFirstLast(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _chargeMonth(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _paymentCash(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _paymentCheck(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _paymentSystem(Wrap<Boolean> c) {
		c.o(false);
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

	protected void _paymentCompleteName(Wrap<String> c) {
		NumberFormat f = NumberFormat.getCurrencyInstance(Locale.US);
		f.setMaximumFractionDigits(0);

		StringBuilder o = new StringBuilder();
		if(enrollment_ != null)
			o.append(enrollment_.getChildCompleteName());
		if(paymentDate != null)
			o.append(" ").append(strPaymentDate());
		if(paymentAmount != null)
			o.append(" ").append(f.format(paymentAmount));
		if(BooleanUtils.isTrue(paymentCheck))
			o.append(" by check");
		if(BooleanUtils.isTrue(paymentCash))
			o.append(" by cash");
		if(BooleanUtils.isTrue(paymentSystem))
			o.append(" by authorize.net");
		if(!StringUtils.isEmpty(paymentDescription))
			o.append(" ").append(paymentDescription);
		c.o(o.toString());
	}

	@Override()
	protected void  _objectTitle(Wrap<String> c) {
		c.o(paymentCompleteName);
	}
}
