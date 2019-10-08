package org.computate.scolaire.enUS.session;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.season.SchoolSeason;

public class SchoolSession extends SchoolSessionGen<Cluster> {

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

	protected void _schoolKey(Wrap<Long> c) {
		if(season_ != null)
			c.o(season_.getSchoolKey());
	}

	protected void _yearKey(Wrap<Long> c) {
		if(season_ != null)
			c.o(season_.getYearKey());
	}

	protected void _seasonKey(Wrap<Long> c) {
		if(season_ != null)
			c.o(season_.getSeasonKey());
	}

	protected void _schoolCompleteName(Wrap<String> c) {
		if(season_ != null)
			c.o((String)season_.getSchoolCompleteName());
	}

	protected void _schoolLocation(Wrap<String> c) {
		if(season_ != null)
			c.o((String)season_.getSchoolLocation());
	}

	protected void _yearStart(Wrap<Integer> c) {
		if(season_ != null)
			c.o(season_.getYearStart());
	}

	protected void _yearEnd(Wrap<Integer> c) {
		if(season_ != null)
			c.o(season_.getYearStart());
	}

	protected void _seasonStartDate(Wrap<LocalDate> c) {
		if(season_ != null)
			c.o(season_.getSeasonStartDate());
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

	protected void _seasonCompleteName(Wrap<String> c) {
		if(season_ != null)
			c.o(season_.getSeasonCompleteName());
	}

	protected void _sessionStartDay(Wrap<LocalDate> c) {}

	@Override()
	public SchoolSession setSessionStartDay(String o) {
		if(StringUtils.contains(o, " "))
			o = StringUtils.substringBefore(o, " ");
		try {
			return super.setSessionStartDay(o);
		} catch (Exception e) {
			setSessionStartDay(LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(o)));
			return this;
		}
	}

	@Override()
	public SchoolSession setSessionEndDay(String o) {
		if(StringUtils.contains(o, " "))
			o = StringUtils.substringBefore(o, " ");
		try {
			return super.setSessionEndDay(o);
		} catch (Exception e) {
			setSessionEndDay(LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(o)));
			return this;
		}
	}

	protected void _sessionEndDay(Wrap<LocalDate> c) {}

	protected void _sessionCompleteName(Wrap<String> c) {
		String o;

		if(BooleanUtils.isTrue(seasonSummer))
			o = String.format("%s - %s summer session at %s", strSessionStartDay(), strSessionEndDay(), schoolCompleteName);
		if(BooleanUtils.isTrue(seasonWinter))
			o = String.format("%s - %s school session at %s", strSessionStartDay(), strSessionEndDay(), schoolCompleteName);
		else
			o = String.format("%s - %s session at %s", strSessionStartDay(), strSessionEndDay(), schoolCompleteName);

		c.o(o);

	}

	protected void _sessionId(Wrap<String> c) {
		if(sessionCompleteName != null) {
			String s = Normalizer.normalize(sessionCompleteName, Normalizer.Form.NFD);
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
		c.o(sessionCompleteName);
	}

	@Override()
	protected void  _classCanonicalNames(List<String> l) {
		l.add(SchoolSession.class.getCanonicalName());
		super._classCanonicalNames(l);
	}
}
