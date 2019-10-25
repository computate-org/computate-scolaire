package org.computate.scolaire.enUS.enrollment.form;

import java.text.Normalizer;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.year.SchoolYear;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.form.part.FormPart;
import org.computate.scolaire.enUS.search.SearchList;

public class EnrollmentForm extends EnrollmentFormGen<Cluster> {

	protected void _enrollmentFormKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _enrollmentKeys(List<Long> o) {}

	protected void _yearSearch(SearchList<SchoolYear> l) {
		l.setQuery("*:*");
		l.addFilterQuery("enrollmentFormKeys_indexed_longs:" + pk);
		l.setC(SchoolYear.class);
		l.setStore(true);
	}

	protected void _year_(Wrap<SchoolYear> c) {
		if(yearSearch.size() > 0) {
			c.o(yearSearch.get(0));
		}
	}

	protected void _formPartSearch(SearchList<FormPart> l) {
		l.setQuery("*:*");
		l.addFilterQuery("enrollmentFormKeys_indexed_longs:" + pk);
		l.setC(FormPart.class);
		l.setStore(true);
	}

	protected void _partFormList(Wrap<List<FormPart>> c) {
		c.o(formPartSearch.getList());
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

	protected void _yearShortName(Wrap<String> c) {
		if(year_ != null)
			c.o(year_.getYearShortName());
	}

	protected void _yearCompleteName(Wrap<String> c) {
		if(year_ != null)
			c.o(year_.getYearCompleteName());
	}

	protected void _enrollmentFormCompleteName(Wrap<String> c) {
		String o;
		if(yearShortName == null)
			o = String.format("enrollment form");
		else
			o = String.format("enrollment form for the ", yearShortName);
		c.o(o);
	}

	protected void _enrollmentFormId(Wrap<String> c) {
		if(enrollmentFormCompleteName != null) {
			String s = Normalizer.normalize(enrollmentFormCompleteName, Normalizer.Form.NFD);
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
		if(enrollmentFormId != null) {
			String o = siteRequest_.getSiteConfig_().getSiteBaseUrl() + "/enrollment-form/" + enrollmentFormId;
			c.o(o);
		}
	}

	protected void _objectSuggest(Wrap<String> c) { 
		c.o(enrollmentFormCompleteName);
	}

	@Override()
	protected void  _classCanonicalNames(List<String> l) {
		l.add(EnrollmentForm.class.getCanonicalName());
		super._classCanonicalNames(l);
	}
}
