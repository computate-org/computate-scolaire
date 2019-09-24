package org.computate.scolaire.enUS.child;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.block.SchoolBlock;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.search.SearchList;

public class SchoolChild extends SchoolChildGen<Cluster> {

	protected void _childKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _enrollmentKeys(List<Long> o) {}

	protected void _familySort(Wrap<Integer> c) {
		c.o(1);
	}

	protected void _schoolSort(Wrap<Integer> c) {
		c.o(1);
	}

	protected void _enrollmentSearch(SearchList<SchoolEnrollment> l) {
		l.setQuery("*:*");
		l.addFilterQuery("childKey_indexed_long:" + pk);
		l.setC(SchoolEnrollment.class);
		l.setStore(true);
		l.setFacet(true);
		l.addFacetField("ecoleCle_indexed_long");
		l.addFacetField("anneeCle_indexed_long");
		l.addFacetField("saisonCle_indexed_long");
		l.addFacetField("sessionCle_indexed_long");
		l.addFacetField("ageCle_indexed_long");
	}

	protected void _inscriptions(List<SchoolEnrollment> l) {
		l.addAll(enrollmentSearch.getList());
	}

	protected void _schoolKeys(List<Long> l) {
		l.addAll(enrollmentSearch.getQueryResponse().getFacetField("schoolKey_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	protected void _yearKeys(List<Long> l) {
		l.addAll(enrollmentSearch.getQueryResponse().getFacetField("yearKey_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	protected void _seasonKeys(List<Long> l) {
		l.addAll(enrollmentSearch.getQueryResponse().getFacetField("seasonKey_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	protected void _sessionKeys(List<Long> l) {
		l.addAll(enrollmentSearch.getQueryResponse().getFacetField("sessionKey_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	protected void _ageKeys(List<Long> l) {
		l.addAll(enrollmentSearch.getQueryResponse().getFacetField("ageKey_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	protected void _personFirstName(Wrap<String> c) {
	}

	protected void _familyName(Wrap<String> c) {
	}

	protected void _personCompleteName(Wrap<String> c) {
		c.o(String.format("%s %s", personFirstName, familyName));
	}

	protected void _personCompleteNamePreferred(Wrap<String> c) {
		c.o(String.format("%s %s", personFirstName, familyName));
	}

	protected void _personFormalName(Wrap<String> c) {
		c.o(String.format("%s %s", personFirstName, familyName));
	}

	protected void _personBirthdate(Wrap<LocalDate> c) {
	}

	protected void _childMedicalConditions(Wrap<String> c) {
	}

	protected void _childPreviousSchoolsAttended(Wrap<String> c) {
	}

	protected void _childDescription(Wrap<String> c) {
	}

	protected void _childObjectives(Wrap<String> c) {
	}

	protected void _enfantVaccinsAJour(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _childPottyTrained(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _childCompleteName(Wrap<String> c) {
		c.o(personCompleteName);
	}

	protected void _childId(Wrap<String> c) {
		if(childCompleteName != null) {
			String s = Normalizer.normalize(childCompleteName, Normalizer.Form.NFD);
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
		if(childId != null) {
			String o = siteRequest_.getSiteConfig_().getSiteBaseUrl() + "/chilc/" + childId;
			c.o(o);
		}
	}

	protected void _objectSuggest(Wrap<String> c) { 
		c.o(childCompleteName);
	}

	@Override()
	protected void  _classCanonicalNames(List<String> l) {
		l.add(SchoolChild.class.getCanonicalName());
		super._classCanonicalNames(l);
	}
}
