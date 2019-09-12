package org.computate.scolaire.enUS.bloc;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.age.SchoolAge;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.search.SearchList;

public class SchoolBlock extends SchoolBlockGen<Cluster> {

	protected void _schoolKey(Wrap<Long> c) {
	}

	protected void _anneeCle(Wrap<Long> c) {
	}

	protected void _seasonKey(Wrap<Long> c) {
	}

	protected void _sessionKey(Wrap<Long> c) {
	}

	protected void _ageKey(Wrap<Long> c) {
	}

	protected void _blockKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _enrollmentKeys(List<Long> o) {}

	protected void _educationSort(Wrap<Integer> c) {
		c.o(6);
	}

	protected void _schoolSort(Wrap<Integer> c) {
		c.o(6);
	}

	protected void _yearSort(Wrap<Integer> c) {
		c.o(6);
	}

	protected void _seasonSort(Wrap<Integer> c) {
		c.o(6);
	}

	protected void _sessionSort(Wrap<Integer> c) {
		c.o(6);
	}

	protected void _ageSort(Wrap<Integer> c) {
		c.o(6);
	}

	protected void _ageSearch(SearchList<SchoolAge> l) {
		l.setQuery("*:*");
		l.addFilterQuery("ageCles_indexed_longs:" + pk);
		l.setC(SchoolAge.class);
	}

	protected void _age(Wrap<SchoolAge> c) {
		if(ageSearch.size() > 0) {
			c.o(ageSearch.get(0));
		}
	}

	protected void _schoolNameComplete(Wrap<String> c) {
		if(age != null)
			c.o((String)age.getSchoolNameComplete());
	}

	protected void _yearStart(Wrap<LocalDate> c) {
		if(age != null)
			c.o((LocalDate)age.getYearStart());
	}

	protected void _yearEnd(Wrap<LocalDate> c) {
		if(age != null)
			c.o(age.getYearStart());
	}

	protected void _seasonStartDay(Wrap<LocalDate> c) {
		if(age != null)
			c.o(age.getSeasonStartDay());
	}

	protected void _seasonSummer(Wrap<Boolean> c) {
		if(age != null)
			c.o(age.getSeasonSummer());
	}

	protected void _seasonWinter(Wrap<Boolean> c) {
		if(age != null)
			c.o(age.getSeasonWinter());
	}

	protected void _seasonEnrollmentFee(Wrap<BigDecimal> c) {
		if(age != null)
			c.o(age.getSeasonEnrollmentFee());
	}

	protected void _seasonNameComplete(Wrap<String> c) {
		if(age != null)
			c.o(age.getSeasonNameComplete());
	}

	protected void _ageStartDay(Wrap<LocalDate> c) {
		if(age != null)
			c.o((LocalDate)age.getSessionStartDay());
	}

	protected void _sessionEndDay(Wrap<LocalDate> c) {
		if(age != null)
			c.o((LocalDate)age.getSessionEndDay());
	}

	protected void _ageNameComplete(Wrap<String> c) {
		if(age != null)
			c.o(age.getAgeNameComplete());
	}

	protected void _ageStart(Wrap<Integer> c) {
		if(age != null)
			c.o(age.getAgeStart());
	}

	protected void _ageEnd(Wrap<Integer> c) {
		if(age != null)
			c.o(age.getAgeEnd());
	}

	protected void _blockTimeStart(Wrap<LocalTime> c) {
	}

	protected void _blockTimeEnd(Wrap<LocalTime> c) {
	}

	protected void _blockSunday(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _blockMonday(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _blockTuesday(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _blockWednesday(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _blockThursday(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _blockFriday(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _blockSaturday(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _blockPricePerMonth(Wrap<BigDecimal> c) {
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
		o = String.format("%s - %s %s %s/month %s. ", strBlockTimeStart(), strBlockTimeEnd(), weekdays, strBlockPricePerMonth(), ageNameComplete);
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
			String o = siteRequest_.getSiteConfig_().getSiteBaseUrl() + "/bloc/" + blocId;
			c.o(o);
		}
	}

	protected void _objectSuggest(Wrap<String> c) { 
		c.o(blocNameComplete);
	}

	@Override()
	protected void  _classCanonicalNames(List<String> l) {
		l.add(BlocScolaire.class.getCanonicalName());
		super._classCanonicalNames(l);
	}
}
