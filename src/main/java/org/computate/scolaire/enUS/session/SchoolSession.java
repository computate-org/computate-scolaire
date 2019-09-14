package org.computate.scolaire.enUS.session;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.lang3.BooleanUtils;
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
		if(pk != null)
			l.addFilterQuery("sessionKeys_indexed_longs:" + pk);
		l.setC(SchoolSeason.class);
		l.setStore(true);
	}

	protected void _season_(Wrap<SchoolSeason> c) {
		if(seasonSearch.size() > 0) {
			c.o(seasonSearch.get(0));
		}
	}

	protected void _schoolNameComplete(Wrap<String> c) {
		if(season_ != null)
			c.o((String)season_.getSchoolNameComplete());
	}

	protected void _yearStart(Wrap<LocalDate> c) {
		if(season_ != null)
			c.o((LocalDate)season_.getYearStart());
	}

	protected void _yearEnd(Wrap<LocalDate> c) {
		if(season_ != null)
			c.o(season_.getYearStart());
	}

	protected void _seasonStartDay(Wrap<LocalDate> c) {
		if(season_ != null)
			c.o(season_.getSeasonStartDay());
	}

	protected void _seasonSummer(Wrap<Boolean> c) {
		if(season_ != null)
			c.o(season_.getSeasonSummer());
	}

	protected void _seasonWinter(Wrap<Boolean> c) {
		if(season_ != null)
			c.o(season_.getSeasonWinter());
	}

	protected void _seasonEnrollmentFee(Wrap<BigDecimal> c) {
		if(season_ != null)
			c.o(season_.getSeasonEnrollmentFee());
	}

	protected void _seasonNameComplete(Wrap<String> c) {
		if(season_ != null)
			c.o(season_.getSeasonNameComplete());
	}

	protected void _seasonEnd(Wrap<LocalDate> c) {
		if(season_ != null)
			c.o((LocalDate)season_.getYearStart());
	}

	protected void _sessionStartDay(Wrap<LocalDate> c) {}

	protected void _sessionEndDay(Wrap<LocalDate> c) {}

	protected void _sessionNameComplete(Wrap<String> c) {
		String o;

		if(BooleanUtils.isTrue(seasonSummer))
			o = String.format("%s - %s summer session at %s. ", strSessionStartDay(), strSessionEndDay(), schoolNameComplete);
		if(BooleanUtils.isTrue(seasonWinter))
			o = String.format("%s - %s school session at %s. ", strSessionStartDay(), strSessionEndDay(), schoolNameComplete);
		else
			o = String.format("%s - %s session at %s. ", strSessionStartDay(), strSessionEndDay(), schoolNameComplete);

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
			String o = siteRequest_.getSiteConfig_().getSiteBaseUrl() + "/session/" + sessionId;
			c.o(o);
		}
	}

	protected void _objectSuggest(Wrap<String> c) { 
		c.o(sessionNameComplete);
	}

	@Override()
	protected void  _classCanonicalNames(List<String> l) {
		l.add(SchoolSession.class.getCanonicalName());
		super._classCanonicalNames(l);
	}
}