package org.computate.scolaire.enUS.child;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.age.SchoolAge;
import org.computate.scolaire.enUS.block.SchoolBlock;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.search.SearchList;

public class SchoolChild extends SchoolChildGen<Cluster> {

	protected void _blockKeys(Wrap<Long> c) {
	}

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

	protected void _blockSearch(SearchList<SchoolBlock> l) {
		l.setQuery("*:*");
		l.addFilterQuery("childKey_indexed_long:" + pk);
		l.setC(SchoolBlock.class);
		l.setStore(true);
		l.setFacet(true);
		l.addFacetField("ecoleCle_indexed_long");
		l.addFacetField("anneeCle_indexed_long");
		l.addFacetField("saisonCle_indexed_long");
		l.addFacetField("sessionCle_indexed_long");
		l.addFacetField("ageCle_indexed_long");
	}

	protected void _blocs(List<SchoolBlock> c) {
		blocks.addAll(blockSearch.getList());
	}

	protected void _schoolKeys(List<Long> l) {
		l.addAll(blocRecherche.getQueryResponse().getFacetField("schoolKey_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	protected void _anneeCles(List<Long> l) {
		l.addAll(blocRecherche.getQueryResponse().getFacetField("anneeCle_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	protected void _seasonKeys(List<Long> l) {
		l.addAll(blocRecherche.getQueryResponse().getFacetField("seasonKey_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	protected void _sessionKeys(List<Long> l) {
		l.addAll(blocRecherche.getQueryResponse().getFacetField("sessionKey_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
	}

	protected void _ageKeys(List<Long> l) {
		l.addAll(blocRecherche.getQueryResponse().getFacetField("ageKey_indexed_long").getValues().stream().map(o -> Long.parseLong(o.getName())).collect(Collectors.toList()));
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

	protected void _blocNameComplete(Wrap<String> c) {
		String o;
		String weekdays = "";
		if(blockMonday) weekdays += " Mo";
		if(blockTuesday) weekdays += " Tu";
		if(blockWednesday) weekdays += " We";
		if(blockThursday) weekdays += " Th";
		if(blockFriday) weekdays += " Fr";
		weekdays = StringUtils.replace(StringUtils.trim(weekdays), " ", "/");
		if(blockPricePerMonth == null)
			o = String.format("%s - %s %s %s", strBlockTimeStart(), strBlockTimeEnd(), weekdays, ageNameComplete);
		else
			o = String.format("%s - %s %s %s/month %s", strBlockTimeStart(), strBlockTimeEnd(), weekdays, strBlockPricePerMonth(), ageNameComplete);
		c.o(o);
	}

	protected void _blocId(Wrap<String> c) {
		if(blocNameComplete != null) {
			String s = Normalizer.normalize(blocNameComplete, Normalizer.Form.NFD);
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
		if(blocId != null) {
			String o = siteRequest_.getSiteConfig_().getSiteBaseUrl() + "/block/" + blocId;
			c.o(o);
		}
	}

	protected void _objectSuggest(Wrap<String> c) { 
		c.o(blocNameComplete);
	}

	@Override()
	protected void  _classCanonicalNames(List<String> l) {
		l.add(SchoolBlock.class.getCanonicalName());
		super._classCanonicalNames(l);
	}
}
