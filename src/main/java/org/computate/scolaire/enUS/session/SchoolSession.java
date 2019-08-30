package org.computate.scolaire.enUS.session;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.season.SchoolSeason;

public class SchoolSession extends SchoolSessionGen<Cluster> {

	protected void _schoolKey(Wrap<Long> c) {
	}

	protected void _anneeCle(Wrap<Long> c) {
	}

	protected void _seasonKey(Wrap<Long> c) {
	}

	protected void _sessionKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _enrollmentKeys(List<Long> o) {}

	protected void _ageKeys(List<Long> o) {}

	protected void _sessionKeys(List<Long> o) {}

	protected void _educationSort(Wrap<Integer> c) {
		c.o(4);
	}

	protected void _schoolSort(Wrap<Integer> c) {
		c.o(4);
	}

	protected void _yearSort(Wrap<Integer> c) {
		c.o(4);
	}

	protected void _seasonSort(Wrap<Integer> c) {
		c.o(4);
	}

	protected void _sessionSort(Wrap<Integer> c) {
		c.o(4);
	}

	protected void _seasonSearch(SearchList<SchoolSeason> l) {
		l.setQuery("*:*");
		l.addFilterQuery("sessionCles_indexed_longs:" + pk);
		l.setC(SchoolSeason.class);
	}

	protected void _season(Wrap<SchoolSeason> c) {
		if(seasonSearch.size() > 0) {
			c.o(seasonSearch.get(0));
		}
	}

	protected void _schoolNameComplete(Wrap<String> c) {
		if(season != null)
			c.o((String)season.getSchoolNameComplete());
	}

	protected void _yearStart(Wrap<LocalDate> c) {
		if(season != null)
			c.o((LocalDate)season.getYearStart());
	}

	protected void _yearEnd(Wrap<LocalDate> c) {
		if(season != null)
			c.o(season.getYearStart());
	}

	protected void _seasonStart(Wrap<LocalDate> c) {
		if(season != null)
			c.o(season.getSeasonStartDay());
	}

	protected void _seasonSummer(Wrap<Boolean> c) {
		if(season != null)
			c.o(season.getSeasonSummer());
	}

	protected void _seasonWinter(Wrap<Boolean> c) {
		if(season != null)
			c.o(season.getSeasonWinter());
	}

	protected void _seasonNameComplete(Wrap<String> c) {
		if(season != null)
			c.o(season.getSeasonNameComplete());
	}

	protected void _seasonEnd(Wrap<LocalDate> c) {
		if(season != null)
			c.o((LocalDate)season.getYearStart());
	}

	protected void _sessionStartDay(Wrap<LocalDate> c) {}

	protected void _sessionEndDay(Wrap<LocalDate> c) {}

	protected void _sessionSummer(Wrap<Boolean> c) {}

	protected void _sessionWinter(Wrap<Boolean> c) {}

	protected void _sessionNameComplete(Wrap<String> c) {
		String o;
		o = String.format("session %s - %s de la %s", strSessionStartDay(), strSessionEndDay(), seasonNameComplete);
		c.o(o);
	}

	protected void _sessionId(Wrap<String> c) {
		if(sessionNameComplete != null) {
			String s = Normalizer.normalize(sessionNameComplete, Normalizer.Form.NFD);
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
		if(sessionId != null) {
			String o = siteRequest_.getSiteConfig_().getSiteBaseUrl() + "/enUS/session/" + sessionId;
			c.o(o);
		}
	}

	protected void _objectSuggest(Wrap<String> c) { 
		c.o(sessionNameComplete);
	}

	@Override()
	protected void  _classCanonicalNames(List<String> l) {
		l.add(SchoolSeason.class.getCanonicalName());
		super._classCanonicalNames(l);
	}
}
