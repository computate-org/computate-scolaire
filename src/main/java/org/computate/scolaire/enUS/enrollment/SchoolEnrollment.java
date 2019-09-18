package org.computate.scolaire.enUS.enrollment;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.age.SchoolAge;
import org.computate.scolaire.enUS.block.SchoolBlock;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.search.SearchList;

public class SchoolEnrollment extends SchoolEnrollmentGen<Cluster> {

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

	protected void _blockKeys(List<Long> o) {}

	protected void _childKeys(List<Long> o) {}

	protected void _momKeys(List<Long> o) {}

	protected void _dadKeys(List<Long> o) {}

	protected void _guardianKeys(List<Long> o) {}

	protected void _contactKeys(List<Long> o) {}

	protected void _familyKey(Wrap<Long> c) {
	}

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

	protected void _blockSearch(SearchList<SchoolBlock> l) {
		l.setQuery("*:*");
		l.addFilterQuery("enrollmentKeys_indexed_longs:" + pk);
		l.setC(BlockAge.class);
		l.setStore(true);
	}

	protected void _block(Wrap<SchoolBlock> c) {
		if(blockSearch.size() > 0) {
			c.o(blockSearch.get(0));
		}
	}

	protected void _schoolNameComplete(Wrap<String> c) {
		if(block != null)
			c.o((String)block.getSchoolNameComplete());
	}

	protected void _yearStart(Wrap<LocalDate> c) {
		if(block != null)
			c.o((LocalDate)block.getYearStart());
	}

	protected void _yearEnd(Wrap<LocalDate> c) {
		if(block != null)
			c.o(block.getYearStart());
	}

	protected void _seasonStartDay(Wrap<LocalDate> c) {
		if(block != null)
			c.o(block.getSeasonStartDay());
	}

	protected void _seasonSummer(Wrap<Boolean> c) {
		if(block != null)
			c.o(block.getSeasonSummer());
	}

	protected void _seasonWinter(Wrap<Boolean> c) {
		if(block != null)
			c.o(block.getSeasonWinter());
	}

	protected void _seasonEnrollmentFee(Wrap<BigDecimal> c) {
		if(block != null)
			c.o(block.getSeasonEnrollmentFee());
	}

	protected void _seasonNameComplete(Wrap<String> c) {
		if(block != null)
			c.o(block.getSeasonNameComplete());
	}

	protected void _ageStartDay(Wrap<LocalDate> c) {
		if(block != null)
			c.o((LocalDate)block.getSessionStartDay());
	}

	protected void _sessionEndDay(Wrap<LocalDate> c) {
		if(block != null)
			c.o((LocalDate)block.getSessionEndDay());
	}

	protected void _ageNameComplete(Wrap<String> c) {
		if(block != null)
			c.o(block.getAgeNameComplete());
	}

	protected void _ageStart(Wrap<Integer> c) {
		if(block != null)
			c.o(block.getAgeStart());
	}

	protected void _ageEnd(Wrap<Integer> c) {
		if(block != null)
			c.o(block.getAgeEnd());
	}

	protected void _blockTimeStart(Wrap<LocalTime> c) {
		if(bloc != null)
			c.o(bloc.getBlockStartTime());
	}

	protected void _blockTimeEnd(Wrap<LocalTime> c) {
		if(block != null)
			c.o(block.getBlockEndTime());
	}

	protected void _blockPricePerMonth(Wrap<BigDecimal> c) {
		if(block != null)
			c.o(block.getBlockPricePerMonth());
	}

	protected void _blockSunday(Wrap<Boolean> c) {
		if(block != null)
			c.o(block.getBlockSunday());
	}

	protected void _blockMonday(Wrap<Boolean> c) {
		if(block != null)
			c.o(block.getBlockMonday());
	}

	protected void _blockTuesday(Wrap<Boolean> c) {
		if(block != null)
			c.o(block.getBlockTuesday());
	}

	protected void _blockWednesday(Wrap<Boolean> c) {
		if(block != null)
			c.o(block.getBlockWednesday());
	}

	protected void _blockThursday(Wrap<Boolean> c) {
		if(block != null)
			c.o(block.getBlockThursday());
	}

	protected void _blockFriday(Wrap<Boolean> c) {
		if(block != null)
			c.o(block.getBlockFriday());
	}

	protected void _blockSaturday(Wrap<Boolean> c) {
		if(block != null)
			c.o(block.getBlockSaturday());
	}

	protected void _enrollmentApproved(Wrap<Boolean> c) {
	}

	protected void _enrollmentImmunizations(Wrap<Boolean> c) {
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
