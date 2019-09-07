package org.computate.scolaire.enUS.season;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.year.SchoolYear;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.search.SearchList;

public class SchoolSeason extends SchoolSeasonGen<Cluster> {

	protected void _schoolKey(Wrap<Long> c) {
	}

	protected void _yearKey(Wrap<Long> c) {
	}

	protected void _seasonKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _enrollmentKeys(List<Long> o) {}

	protected void _sessionKeys(List<Long> o) {}

	protected void _educationSort(Wrap<Integer> c) {
		c.o(3);
	}

	protected void _schoolSort(Wrap<Integer> c) {
		c.o(3);
	}

	protected void _yearSort(Wrap<Integer> c) {
		c.o(3);
	}

	protected void _seasonSort(Wrap<Integer> c) {
		c.o(3);
	}

	protected void _yearSearch(SearchList<SchoolYear> l) {
		l.setQuery("*:*");
		l.addFilterQuery("seasonKeys_indexed_longs:" + pk);
		l.setC(SchoolYear.class);
		l.setStore(true);
	}

	protected void _year(Wrap<SchoolYear> c) {
		if(yearSearch.size() > 0) {
			c.o(yearSearch.get(0));
		}
	}

	protected void _schoolNameComplete(Wrap<String> c) {
		if(year != null)
			c.o((String)year.getSchoolNameComplete());
	}

	protected void _yearStart(Wrap<LocalDate> c) {
		if(year != null)
			c.o((LocalDate)year.getYearStart());
	}

	protected void _yearEnd(Wrap<LocalDate> c) {
		if(year != null)
			c.o((LocalDate)year.getYearStart());
	}

	protected void _seasonStartDay(Wrap<LocalDate> c) {}

	protected void _seasonSummer(Wrap<Boolean> c) {}

	protected void _seasonWinter(Wrap<Boolean> c) {}

	protected void _seasonEnrollmentFee(Wrap<BigDecimal> c) {}

	protected void _seasonNameComplete(Wrap<String> c) {
		String o;
		
		if(BooleanUtils.isTrue(seasonSummer))
			o = String.format("summer season starting %s at %s. ", strSeasonStartDay(), schoolNameComplete);
		if(BooleanUtils.isTrue(seasonWinter))
			o = String.format("school season starting %s at %s. ", strSeasonStartDay(), schoolNameComplete);
		else
			o = String.format("season starting %s at %s. ", strSeasonStartDay(), schoolNameComplete);
		
		c.o(o);
	}

	protected void _seasonId(Wrap<String> c) {
		if(seasonNameComplete != null) {
			String s = Normalizer.normalize(seasonNameComplete, Normalizer.Form.NFD);
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
		if(seasonId != null) {
			String o = siteRequest_.getSiteConfig_().getSiteBaseUrl() + "/enUS/season/" + seasonId;
			c.o(o);
		}
	}

	protected void _objectSuggest(Wrap<String> c) { 
		c.o(seasonNameComplete);
	}

	@Override()
	protected void  _classCanonicalNames(List<String> l) {
		l.add(SchoolSeason.class.getCanonicalName());
		super._classCanonicalNames(l);
	}
}
