package org.computate.scolaire.enUS.year;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrDocument;
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

	protected void _schoolSearch(SearchList<School> l) {
		l.setQuery("*:*");
		l.addFilterQuery("yearKeys_indexed_longs:" + pk);
		l.setC(School.class);
	}

	protected void _schoolDocument(Wrap<SolrDocument> c) {
		if(schoolSearch.getSolrDocumentList().size() > 0) {
			c.o(schoolSearch.getSolrDocumentList().get(0));
		}
	}

	protected void _schoolNameComplete(Wrap<String> c) {
		if(schoolDocument != null)
			c.o((String)schoolDocument.get("schoolNameComplete_stored_string"));
	}

	protected void _yearStart(Wrap<LocalDate> c) {}

	protected void _yearEnd(Wrap<LocalDate> c) {
		if(yearStart != null)
			c.o(yearStart.plusYears(1));
	}

	protected void _yearNameShort(Wrap<String> c) {
		String o = "year";

		if(yearStart != null && yearEnd != null)
			o = String.format("%d-%d", yearStart.getYear(), yearEnd.getYear());
		else if(yearStart != null)
			o = String.format("%d year", yearStart.getYear());
		else if(yearEnd != null)
			o = String.format("%d year", yearEnd.getYear());

		c.o(o);
	}

	protected void _yearNameComplete(Wrap<String> c) {
		String o = "year";

		if(yearStart != null && yearEnd != null)
			o = String.format("%d-%d school year", yearStart.getYear(), yearEnd.getYear());
		else if(yearStart != null)
			o = String.format("%d school year", yearStart.getYear());
		else if(yearEnd != null)
			o = String.format("%d school year", yearEnd.getYear());

		if(schoolNameComplete != null)
			o += String.format(" at %s", schoolNameComplete);

		c.o(o);
	}

	protected void _yearId(Wrap<String> c) {
		if(yearNameComplete != null) {
			String s = Normalizer.normalize(yearNameComplete, Normalizer.Form.NFD);
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
			String o = siteRequest_.getSiteConfig_().getSiteBaseUrl() + "/enUS/year/" + yearId;
			c.o(o);
		}
	}

	protected void _objectSuggest(Wrap<String> c) { 
		c.o(yearNameComplete);
	}

	@Override()
	protected void  _classCanonicalNames(List<String> l) {
		l.add(SchoolYear.class.getCanonicalName());
		super._classCanonicalNames(l);
	}
}
