package org.computate.scolaire.enUS.receipt;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.school.School;
import org.computate.scolaire.enUS.search.SearchList;

/**
 * Model: true
 * Api: true
 * Page: true
 * Saved: true
 * Color: light-green
 * IconGroup: solid
 * IconName: file-invoice-dollar
 * Role.enUS: SiteManager
 * ApiUri.enUS: /api/receipt
 * ApiTag.enUS: Receipt
 * AName.enUS: a receipt
 * Role.frFR: SiteManager
 * ApiUri.frFR: /api/recu
 * ApiTag.frFR: Reçu
 * AName.frFR: un reçu
 * CanonicalName: org.computate.scolaire.frFR.recu.RecuScolaire
 **/
public class SchoolReceipt extends SchoolReceiptGen<Cluster> {

	protected void _receiptKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _schoolKey(Wrap<Long> c) {
	}

	protected void _schoolSearch(SearchList<School> l) {
		l.setQuery("*:*");
		l.addFilterQuery("pk_indexed_long:" + schoolKey);
		l.setC(School.class);
		l.setStore(true);
	}

	protected void _school_(Wrap<School> c) {
		if(schoolSearch.size() > 0) {
			c.o(schoolSearch.get(0));
		}
	}

	protected void _schoolAddress(Wrap<String> c) {
		if(school_ != null)
			c.o(school_.getSchoolAddress());
	}

	protected void _schoolPhoneNumber(Wrap<String> c) {
		if(school_ != null)
			c.o(school_.getSchoolPhoneNumber());
	}

	protected void _paymentDate(Wrap<LocalDate> c) {
	}

	protected void _paymentYear(Wrap<Integer> c) {
		if(paymentDate != null)
			c.o(paymentDate.getYear());
	}

	protected void _paymentAmount(Wrap<BigDecimal> c) {
	}

	protected void _paymentDescription(Wrap<String> c) {
	}

	protected void _paymentShortName(Wrap<String> c) {
		NumberFormat fn = NumberFormat.getCurrencyInstance(Locale.US);
		DateTimeFormatter fd2 = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.US);
		fn.setMaximumFractionDigits(0);

		StringBuilder o = new StringBuilder();
		if(paymentDate != null)
			o.append(paymentDate.format(fd2));
		if(paymentAmount != null) {
			o.append(" ").append(fn.format(paymentAmount));
			o.append(" payment");
		}
		if(!StringUtils.isEmpty(paymentDescription))
			o.append(" ").append(paymentDescription);
		if(o.length() == 0)
			o.append("new");
		c.o(o.toString());
	}

	protected void _paymentCompleteName(Wrap<String> c) {
		NumberFormat fn = NumberFormat.getCurrencyInstance(Locale.US);
		DateTimeFormatter fd2 = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.US);
		fn.setMaximumFractionDigits(0);

		StringBuilder o = new StringBuilder();
		if(paymentDate != null)
			o.append(paymentDate.format(fd2));
		if(paymentAmount != null) {
			o.append(" ").append(fn.format(paymentAmount));
			o.append(" payment");
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
		DateTimeFormatter fd2 = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.US);
		fn.setMaximumFractionDigits(0);

		StringBuilder o = new StringBuilder();
		if(paymentDate != null)
			o.append(paymentDate.format(fd2));
		if(paymentAmount != null) {
			o.append(" ").append(fn.format(paymentAmount));
			o.append(" payment");
		}
		if(!StringUtils.isEmpty(paymentDescription))
			o.append(" ").append(paymentDescription);
		if(o.length() == 0)
			o.append("new");
		c.o(o.toString());
	}
}
