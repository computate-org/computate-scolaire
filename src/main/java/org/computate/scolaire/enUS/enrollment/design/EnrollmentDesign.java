package org.computate.scolaire.enUS.enrollment.design;

import java.util.List;
import org.computate.scolaire.enUS.year.SchoolYear;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import org.computate.scolaire.enUS.search.SearchList;

public class EnrollmentDesign extends EnrollmentDesignGen<Cluster> {

	protected void _enrollmentDesignKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _yearKey(Wrap<Long> c) {
	}

	protected void _htmlPartKeys(List<Long> o) {}

	protected void _enrollmentKeys(List<Long> o) {}

	protected void _yearSearch(SearchList<SchoolYear> l) {
		l.setQuery("*:*");
		l.addFilterQuery("enrollmentDesignKey_indexed_long:" + pk);
		l.setC(SchoolYear.class);
		l.setStore(true);
	}

	protected void _year_(Wrap<SchoolYear> c) {
		if(yearSearch.size() > 0) {
			c.o(yearSearch.get(0));
		}
	}

	protected void _htmlPartSearch(SearchList<HtmlPart> l) {
		l.setQuery("*:*");
		l.addFilterQuery("enrollmentDesignKey_indexed_long:" + pk);
		l.setC(HtmlPart.class);
		l.setStore(true);
	}

	protected void _htmlPartList(Wrap<List<HtmlPart>> c) {
		c.o(htmlPartSearch.getList());
	}

	protected void _schoolKey(Wrap<Long> c) {
		if(year_ != null)
			c.o(year_.getSchoolKey());
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

	protected void _yearShortName(Wrap<String> c) {
		if(year_ != null)
			c.o(year_.getYearShortName());
	}

	protected void _yearCompleteName(Wrap<String> c) {
		if(year_ != null)
			c.o(year_.getYearCompleteName());
	}

	protected void _enrollmentDesignCompleteName(Wrap<String> c) {
		String o;
		if(yearCompleteName == null)
			o = String.format("enrollment design");
		else
			o = String.format("enrollment design for the %s", yearCompleteName);
		c.o(o);
	}

	@Override()
	protected void  _objectTitle(Wrap<String> c) {
		c.o(enrollmentDesignCompleteName);
	}
}
