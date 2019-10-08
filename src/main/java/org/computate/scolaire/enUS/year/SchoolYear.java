package org.computate.scolaire.enUS.year;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.school.School;
import org.computate.scolaire.enUS.search.SearchList;

public class SchoolYear extends SchoolYearGen<Cluster> {

	protected void _schoolKey(Wrap<Long> c) {
	}

	protected void _yearKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _enrollmentKeys(List<Long> o) {}

	protected void _seasonKeys(List<Long> o) {}

	protected void _educationSort(Wrap<Integer> c) {
		c.o(2);
	}

	protected void _schoolSort(Wrap<Integer> c) {
		c.o(2);
	}

	protected void _yearSort(Wrap<Integer> c) {
		c.o(2);
	}

	protected void _schoolSearch(SearchList<School> l) {
		l.setQuery("*:*");
		l.addFilterQuery("yearKeys_indexed_longs:" + pk);
		l.setC(School.class);
		l.setStore(true);
	}

	protected void _school_(Wrap<School> c) {
		if(schoolSearch.size() > 0) {
			c.o(schoolSearch.get(0));
		}
	}

	protected void _schoolCompleteName(Wrap<String> c) {
		if(school_ != null)
			c.o((String)school_.getSchoolCompleteName());
	}

	protected void _schoolLocation(Wrap<String> c) {
		if(school_ != null)
			c.o((String)school_.getSchoolLocation());
	}

	protected void _yearStart(Wrap<Integer> c) {}

	protected void _yearEnd(Wrap<Integer> c) {
		if(yearStart != null)
			c.o(yearStart + 1);
	}

	protected void _yearShortName(Wrap<String> c) {
		String o = "year";

		if(yearStart != null && yearEnd != null)
			o = String.format("%d-%d", yearStart, yearEnd);
		else if(yearStart != null)
			o = String.format("%d year", yearStart);
		else if(yearEnd != null)
			o = String.format("%d year", yearEnd);

		c.o(o);
	}

	protected void _yearCompleteName(Wrap<String> c) {
		String o = "year";

		if(yearStart != null && yearEnd != null)
			o = String.format("%d-%d school year", yearStart, yearEnd);
		else if(yearStart != null)
			o = String.format("%d school year", yearStart);
		else if(yearEnd != null)
			o = String.format("%d school year", yearEnd);

		if(schoolCompleteName != null)
			o += String.format(" at %s", schoolCompleteName);

		c.o(o);
	}

	protected void _yearId(Wrap<String> c) {
		if(yearCompleteName != null) {
			String s = Normalizer.normalize(yearCompleteName, Normalizer.Form.NFD);
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
		if(yearId != null) {
			String o = siteRequest_.getSiteConfig_().getSiteBaseUrl() + "/year/" + yearId;
			c.o(o);
		}
	}

	protected void _objectSuggest(Wrap<String> c) { 
		c.o(yearCompleteName);
	}

	@Override()
	protected void  _classCanonicalNames(List<String> l) {
		l.add(SchoolYear.class.getCanonicalName());
		super._classCanonicalNames(l);
	}
}
