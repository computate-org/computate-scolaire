package org.computate.scolaire.enUS.age;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.page.PageLayout;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.session.SchoolSession;

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

	protected void _sessionSearch(SearchList<SchoolSession> l) {
		l.setQuery("*:*");
		l.addFilterQuery("ageKeys_indexed_longs:" + pk);
		l.setC(SchoolSession.class);
		l.setStore(true);
	}

	protected void _session_(Wrap<SchoolSession> c) {
		if(sessionSearch.size() > 0) {
			c.o(sessionSearch.get(0));
		}
	}

	protected void _schoolKey(Wrap<Long> c) {
		if(session_ != null)
			c.o(session_.getSchoolKey());
	}

	protected void _yearKey(Wrap<Long> c) {
		if(session_ != null)
			c.o(session_.getYearKey());
	}

	protected void _seasonKey(Wrap<Long> c) {
		if(session_ != null)
			c.o(session_.getSeasonKey());
	}

	protected void _schoolName(Wrap<String> c) {
		if(session_ != null)
			c.o(session_.getSchoolName());
	}

	protected void _schoolCompleteName(Wrap<String> c) {
		if(session_ != null)
			c.o((String)session_.getSchoolCompleteName());
	}

	protected void _schoolLocation(Wrap<String> c) {
		if(session_ != null)
			c.o((String)session_.getSchoolLocation());
	}

	protected void _schoolAddress(Wrap<String> c) {
		if(session_ != null)
			c.o((String)session_.getSchoolAddress());
	}

	protected void _schoolPhoneNumber(Wrap<String> c) {
		if(session_ != null)
			c.o((String)session_.getSchoolPhoneNumber());
	}

	protected void _schoolAdministratorName(Wrap<String> c) {
		if(session_ != null)
			c.o((String)session_.getSchoolAdministratorName());
	}

	protected void _yearStart(Wrap<Integer> c) {
		if(session_ != null)
			c.o(session_.getYearStart());
	}

	protected void _yearEnd(Wrap<Integer> c) {
		if(session_ != null)
			c.o(session_.getYearEnd());
	}

	protected void _seasonStartDate(Wrap<LocalDate> c) {
		if(session_ != null)
			c.o(session_.getSeasonStartDate());
	}

	protected void _seasonSummer(Wrap<Boolean> c) {
		if(session_ != null)
			c.o(session_.getSeasonSummer());
	}

	protected void _seasonWinter(Wrap<Boolean> c) {
		if(session_ != null)
			c.o(session_.getSeasonWinter());
	}

	protected void _seasonEnrollmentFee(Wrap<BigDecimal> c) {
		if(session_ != null)
			c.o(session_.getSeasonEnrollmentFee());
	}

	protected void _seasonShortName(Wrap<String> c) {
		if(session_ != null)
			c.o(session_.getSeasonShortName());
	}

	protected void _seasonCompleteName(Wrap<String> c) {
		if(session_ != null)
			c.o(session_.getSeasonCompleteName());
	}

	protected void _sessionStartDate(Wrap<LocalDate> c) {
		if(session_ != null)
			c.o((LocalDate)session_.getSessionStartDate());
	}

	protected void _sessionEndDate(Wrap<LocalDate> c) {
		if(session_ != null)
			c.o((LocalDate)session_.getSessionEndDate());
	}

	protected void _sessionCompleteName(Wrap<String> c) {
		if(session_ != null)
			c.o(session_.getSessionCompleteName());
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
		o = String.format("ages %s-%s during the %s", strAgeStart(), strAgeEnd(), sessionCompleteName);
		c.o(o);
	}

	@Override()
	protected void  _objectTitle(Wrap<String> c) {
		c.o(ageCompleteName);
	}
}
