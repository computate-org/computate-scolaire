package org.computate.scolaire.enUS.season;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

	protected void _schoolName(Wrap<String> c) {
		if(year_ != null)
			c.o(year_.getSchoolName());
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

	@Override()
	public SchoolSeason setSeasonStartDate(String o) {
		if(StringUtils.contains(o, " "))
			o = StringUtils.substringBefore(o, " ");
		try {
			return super.setSeasonStartDate(o);
		} catch (Exception e) {
			setSeasonStartDate(LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(o)));
			return this;
		}
	}

	protected void _seasonSummer(Wrap<Boolean> c) {}

	protected void _seasonWinter(Wrap<Boolean> c) {}

	protected void _seasonEnrollmentFee(Wrap<BigDecimal> c) {}

	protected void _seasonCompleteName(Wrap<String> c) {
		String o;
		
		if(BooleanUtils.isTrue(seasonSummer))
			o = String.format("%s summer season at %s", strSeasonStartDate(), schoolCompleteName);
		else if(BooleanUtils.isTrue(seasonWinter))
			o = String.format("%s school season at %s", strSeasonStartDate(), schoolCompleteName);
		else
			o = String.format("%s season at %s", strSeasonStartDate(), schoolCompleteName);
		
		c.o(o);
	}

	@Override()
	protected void  _objectTitle(Wrap<String> c) {
		c.o(seasonCompleteName);
	}
}
