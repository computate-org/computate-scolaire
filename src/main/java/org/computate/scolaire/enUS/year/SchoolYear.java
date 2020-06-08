package org.computate.scolaire.enUS.year;

import java.math.BigDecimal;
import java.util.List;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.school.School;
import org.computate.scolaire.enUS.search.SearchList;

/**
 * Model: true
 * Api: true
 * Page: true
 * Saved: true
 * Color: orange
 * IconGroup: regular
 * IconName: calendar-check
 * Role.enUS: SiteAdmin
 * ApiUri.enUS: /api/year
 * ApiTag.enUS: Year
 * AName.enUS: a year
 * Role.frFR: SiteAdmin
 * ApiUri.frFR: /api/annee
 * ApiTag.frFR: Année
 * AName.frFR: une année
 * CanonicalName: org.computate.scolaire.frFR.annee.AnneeScolaire
 **/
public class SchoolYear extends SchoolYearGen<Cluster> {

	protected void _schoolKey(Wrap<Long> c) {
	}

	protected void _yearKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _enrollmentKeys(List<Long> o) {}

	protected void _seasonKeys(List<Long> o) {}

	protected void _educationSort(Wrap<Integer> c) {
		c.o(2);
	}

	protected void _schoolSort(Wrap<Integer> c) {
		c.o(2);
	}

	protected void _yearSort(Wrap<Integer> c) {
		c.o(2);
	}

	protected void _schoolSearch(SearchList<School> l) {
		l.setQuery("*:*");
		l.addFilterQuery("yearKeys_indexed_longs:" + pk);
		l.setC(School.class);
		l.setStore(true);
	}

	protected void _school_(Wrap<School> c) {
		if(schoolSearch.size() > 0) {
			c.o(schoolSearch.get(0));
		}
	}

	protected void _schoolName(Wrap<String> c) {
		if(school_ != null)
			c.o(school_.getSchoolName());
	}

	protected void _schoolCompleteName(Wrap<String> c) {
		if(school_ != null)
			c.o((String)school_.getSchoolCompleteName());
	}

	protected void _schoolLocation(Wrap<String> c) {
		if(school_ != null)
			c.o((String)school_.getSchoolLocation());
	}

	protected void _schoolAddress(Wrap<String> c) {
		if(school_ != null)
			c.o((String)school_.getSchoolAddress());
	}

	protected void _schoolPhoneNumber(Wrap<String> c) {
		if(school_ != null)
			c.o((String)school_.getSchoolPhoneNumber());
	}

	protected void _schoolAdministratorName(Wrap<String> c) {
		if(school_ != null)
			c.o((String)school_.getSchoolAdministratorName());
	}

	protected void _enrollmentFormKey(Wrap<Long> c) {
	}

	protected void _yearStart(Wrap<Integer> c) {}

	protected void _yearEnd(Wrap<Integer> c) {
		if(yearStart != null)
			c.o(yearStart + 1);
	}

	protected void _yearEnrollmentFee(Wrap<BigDecimal> c) {}

	protected void _yearYears(List<SchoolYear> l) {}

	protected void _yearShortName(Wrap<String> c) {
		String o = "year";

		if(yearStart != null && yearEnd != null)
			o = String.format("%d-%d", yearStart, yearEnd);
		else if(yearStart != null)
			o = String.format("%d year", yearStart);
		else if(yearEnd != null)
			o = String.format("%d year", yearEnd);

		c.o(o);
	}

	protected void _yearCompleteName(Wrap<String> c) {
		String o = "year";

		if(yearStart != null && yearEnd != null)
			o = String.format("%d-%d school year", yearStart, yearEnd);
		else if(yearStart != null)
			o = String.format("%d school year", yearStart);
		else if(yearEnd != null)
			o = String.format("%d school year", yearEnd);

		if(schoolCompleteName != null)
			o += String.format(" at %s", schoolCompleteName);

		c.o(o);
	}

	@Override()
	protected void  _objectTitle(Wrap<String> c) {
		c.o(yearCompleteName);
	}
}
