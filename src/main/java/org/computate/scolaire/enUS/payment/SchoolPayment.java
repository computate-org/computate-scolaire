package org.computate.scolaire.enUS.payment;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.search.SearchList;

public class SchoolPayment extends SchoolPaymentGen<Cluster> {

	protected void _schoolKeys(List<Long> l) {
	}

	protected void _anneeCles(List<Long> l) {
	}

	protected void _seasonKeys(List<Long> l) {
	}

	protected void _sessionKeys(List<Long> l) {
	}

	protected void _ageKeys(List<Long> l) {
	}

	protected void _blockKeys(List<Long> l) {
	}

	protected void _enrollmentKeys(List<Long> l) {}

	protected void _paymentKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _childKeys(List<Long> o) {}

	protected void _momKeys(List<Long> o) {}

	protected void _dadKeys(List<Long> o) {}

	protected void _guardianKeys(List<Long> o) {}

	protected void _contactKeys(List<Long> o) {}

	protected void _enrollmentSearch(SearchList<SchoolEnrollment> l) {
		l.setQuery("*:*");
		l.addFilterQuery("paymentKeys_indexed_longs:" + pk);
		l.setC(SchoolEnrollment.class);
		l.setStore(true);
	}

	protected void _enrollment_(Wrap<SchoolEnrollment> c) {
		if(enrollmentSearch.size() > 0) {
			c.o(enrollmentSearch.get(0));
		}
	}

	protected void _paymentDescription(Wrap<String> c) {
	}

	protected void _paymentDate(Wrap<LocalDate> c) {
	}

	protected void _paymentAmount(Wrap<BigDecimal> c) {
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
