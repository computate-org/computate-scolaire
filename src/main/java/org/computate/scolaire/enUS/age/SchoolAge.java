package org.computate.scolaire.enUS.age;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.computate.scolaire.enUS.year.SchoolYear;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.page.PageLayout;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.session.SchoolSession;

/**
 * Model: true
 * Api: true
 * Page: true
 * Saved: true
 * Color: blue
 * IconGroup: duotone
 * IconName: birthday-cake
 * Role.enUS: SiteAdmin
 * ApiUri.enUS: /api/age
 * ApiTag.enUS: Age
 * AName.enUS: an age
 * Role.frFR: SiteAdmin
 * ApiUri.frFR: /api/age
 * ApiTag.frFR: Age
 * AName.frFR: un âge
 * CanonicalName: org.computate.scolaire.frFR.age.AgeScolaire
 **/
public class SchoolAge extends SchoolAgeGen<Cluster> {

	protected void _ageKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _enrollmentKeys(List<Long> o) {}

	protected void _blockKeys(List<Long> o) {}

	protected void _educationSort(Wrap<Integer> c) {
		c.o(5);
	}

	protected void _schoolSort(Wrap<Integer> c) {
		c.o(5);
	}

	protected void _yearSort(Wrap<Integer> c) {
		c.o(5);
	}

	protected void _seasonSort(Wrap<Integer> c) {
		c.o(5);
	}

	protected void _sessionSort(Wrap<Integer> c) {
		c.o(5);
	}

	protected void _sessionKey(Wrap<Long> c) {
	}

	protected void _yearSearch(SearchList<SchoolYear> l) {
		l.setQuery("*:*");
		l.addFilterQuery("ageKeys_indexed_longs:" + pk);
		l.setC(SchoolYear.class);
		l.setStore(true);
	}

	protected void _year_(Wrap<SchoolYear> c) {
		if(yearSearch.size() > 0) {
			c.o(yearSearch.get(0));
		}
	}

	protected void _schoolKey(Wrap<Long> c) {
		if(year_ != null)
			c.o(year_.getSchoolKey());
	}

	protected void _yearKey(Wrap<Long> c) {
		if(year_ != null)
			c.o(year_.getYearKey());
	}

	protected void _schoolName(Wrap<String> c) {
		if(year_ != null)
			c.o(year_.getSchoolName());
	}

	protected void _schoolCompleteName(Wrap<String> c) {
		if(year_ != null)
			c.o((String)year_.getSchoolCompleteName());
	}

	protected void _schoolLocation(Wrap<String> c) {
		if(year_ != null)
			c.o((String)year_.getSchoolLocation());
	}

	protected void _schoolAddress(Wrap<String> c) {
		if(year_ != null)
			c.o((String)year_.getSchoolAddress());
	}

	protected void _schoolPhoneNumber(Wrap<String> c) {
		if(year_ != null)
			c.o((String)year_.getSchoolPhoneNumber());
	}

	protected void _schoolNumber(Wrap<Integer> c) {
		if(year_ != null)
			c.o((Integer)year_.getSchoolNumber());
	}

	protected void _schoolAdministratorName(Wrap<String> c) {
		if(year_ != null)
			c.o((String)year_.getSchoolAdministratorName());
	}

	protected void _yearStart(Wrap<Integer> c) {
		if(year_ != null)
			c.o(year_.getYearStart());
	}

	protected void _yearEnd(Wrap<Integer> c) {
		if(year_ != null)
			c.o(year_.getYearEnd());
	}

	protected void _seasonStartDate(Wrap<LocalDate> c) {
		if(year_ != null)
			c.o(year_.getSeasonStartDate());
	}

	protected void _yearEnrollmentFee(Wrap<BigDecimal> c) {
		if(year_ != null)
			c.o(year_.getYearEnrollmentFee());
	}

	protected void _yearShortName(Wrap<String> c) {
		if(year_ != null)
			c.o(year_.getYearShortName());
	}

	protected void _yearCompleteName(Wrap<String> c) {
		if(year_ != null)
			c.o(year_.getYearCompleteName());
	}

	protected void _sessionStartDate(Wrap<LocalDate> c) {
		if(year_ != null)
			c.o((LocalDate)year_.getSessionStartDate());
	}

	protected void _sessionEndDate(Wrap<LocalDate> c) {
		if(year_ != null)
			c.o((LocalDate)year_.getSessionEndDate());
	}

	protected void _ageStart(Wrap<Integer> c) {
	}

	protected void _ageEnd(Wrap<Integer> c) {
	}

	protected void _ageShortName(Wrap<String> c) {
		String o;
		o = String.format("%s-%s year olds (%s - %s)", strAgeStart(), strAgeEnd(), sessionStartDate == null ? "" : PageLayout.FORMATMonthYear.format(sessionStartDate), sessionEndDate == null ? "" : PageLayout.FORMATMonthYear.format(sessionEndDate));
		c.o(o);
	}

	protected void _ageCompleteName(Wrap<String> c) {
		String o;
		o = String.format("ages %s-%s during the %s", strAgeStart(), strAgeEnd(), yearCompleteName);
		c.o(o);
	}

	@Override()
	protected void  _objectTitle(Wrap<String> c) {
		c.o(ageCompleteName);
	}
}
