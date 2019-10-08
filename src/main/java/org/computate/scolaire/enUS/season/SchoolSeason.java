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

	protected void _schoolCompleteName(Wrap<String> c) {
		if(year_ != null)
			c.o(year_.getSchoolCompleteName());
	}

	protected void _schoolLocation(Wrap<String> c) {
		if(year_ != null)
			c.o((String)year_.getSchoolLocation());
	}

	protected void _yearStart(Wrap<Integer> c) {
		if(year_ != null)
			c.o(year_.getYearStart());
	}

	protected void _yearEnd(Wrap<Integer> c) {
		if(year_ != null)
			c.o(year_.getYearStart());
	}

	protected void _seasonStartDate(Wrap<LocalDate> c) {}

	protected void _seasonSummer(Wrap<Boolean> c) {}

	protected void _seasonWinter(Wrap<Boolean> c) {}

	protected void _seasonEnrollmentFee(Wrap<BigDecimal> c) {}

	protected void _seasonCompleteName(Wrap<String> c) {
		String o;
		
		if(BooleanUtils.isTrue(seasonSummer))
			o = String.format("%s summer season at %s", strSeasonStartDate(), schoolCompleteName);
		if(BooleanUtils.isTrue(seasonWinter))
			o = String.format("%s school season at %s", strSeasonStartDate(), schoolCompleteName);
		else
			o = String.format("%s season at %s", strSeasonStartDate(), schoolCompleteName);
		
		c.o(o);
	}

	protected void _seasonId(Wrap<String> c) {
		if(seasonCompleteName != null) {
			String s = Normalizer.normalize(seasonCompleteName, Normalizer.Form.NFD);
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
			String o = siteRequest_.getSiteConfig_().getSiteBaseUrl() + "/season/" + seasonId;
			c.o(o);
		}
	}

	protected void _objectSuggest(Wrap<String> c) { 
		c.o(seasonCompleteName);
	}

	@Override()
	protected void  _classCanonicalNames(List<String> l) {
		l.add(SchoolSeason.class.getCanonicalName());
		super._classCanonicalNames(l);
	}
}
