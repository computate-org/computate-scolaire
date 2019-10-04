package org.computate.scolaire.enUS.age;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.season.SchoolSeason;
import org.computate.scolaire.enUS.session.SchoolSession;

public class SchoolAge extends SchoolAgeGen<Cluster> {

	protected void _schoolKey(Wrap<Long> c) {
	}

	protected void _anneeCle(Wrap<Long> c) {
	}

	protected void _seasonKey(Wrap<Long> c) {
	}

	protected void _sessionKey(Wrap<Long> c) {
	}

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

	protected void _sessionSearch(SearchList<SchoolSession> l) {
		l.setQuery("*:*");
		l.addFilterQuery("ageKeys_indexed_longs:" + pk);
		l.setC(SchoolSession.class);
		l.setStore(true);
	}

	protected void _session(Wrap<SchoolSession> c) {
		if(sessionSearch.size() > 0) {
			c.o(sessionSearch.get(0));
		}
	}

	protected void _schoolCompleteName(Wrap<String> c) {
		if(session != null)
			c.o((String)session.getSchoolCompleteName());
	}

	protected void _yearStart(Wrap<Integer> c) {
		if(session != null)
			c.o(session.getYearStart());
	}

	protected void _yearEnd(Wrap<Integer> c) {
		if(session != null)
			c.o(session.getYearStart());
	}

	protected void _seasonStartDay(Wrap<LocalDate> c) {
		if(session != null)
			c.o(session.getSeasonStartDay());
	}

	protected void _seasonSummer(Wrap<Boolean> c) {
		if(session != null)
			c.o(session.getSeasonSummer());
	}

	protected void _seasonWinter(Wrap<Boolean> c) {
		if(session != null)
			c.o(session.getSeasonWinter());
	}

	protected void _seasonEnrollmentFee(Wrap<BigDecimal> c) {
		if(session != null)
			c.o(session.getSeasonEnrollmentFee());
	}

	protected void _seasonCompleteName(Wrap<String> c) {
		if(session != null)
			c.o(session.getSeasonCompleteName());
	}

	protected void _sessionStartDay(Wrap<LocalDate> c) {
		if(session != null)
			c.o((LocalDate)session.getSessionStartDay());
	}

	protected void _sessionEndDay(Wrap<LocalDate> c) {
		if(session != null)
			c.o((LocalDate)session.getSessionEndDay());
	}

	protected void _sessionCompleteName(Wrap<String> c) {
		if(session != null)
			c.o(session.getSessionCompleteName());
	}

	protected void _ageStart(Wrap<Integer> c) {
	}

	protected void _ageEnd(Wrap<Integer> c) {
	}

	protected void _ageCompleteName(Wrap<String> c) {
		String o;
		o = String.format("ages %s - %s during %s", strAgeStart(), strAgeEnd(), sessionCompleteName);
		c.o(o);
	}

	protected void _ageId(Wrap<String> c) {
		if(ageCompleteName != null) {
			String s = Normalizer.normalize(ageCompleteName, Normalizer.Form.NFD);
			s = StringUtils.lowerCase(s);
			s = StringUtils.trim(s);
			s = StringUtils.replacePattern(s, "\\s{1,}", "-");
			s = StringUtils.replacePattern(s, "[^\\w-]", "");
			s = StringUtils.replacePattern(s, "-{2,}", "-");
			c.o(s);
		}
		else if(pk != null){
			c.o(pk.toString());
		}
	}

	protected void _pageUrl(Wrap<String> c) {
		if(ageId != null) {
			String o = siteRequest_.getSiteConfig_().getSiteBaseUrl() + "/age/" + ageId;
			c.o(o);
		}
	}

	protected void _objectSuggest(Wrap<String> c) { 
		c.o(ageCompleteName);
	}

	@Override()
	protected void  _classCanonicalNames(List<String> l) {
		l.add(SchoolAge.class.getCanonicalName());
		super._classCanonicalNames(l);
	}
}
