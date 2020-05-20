package org.computate.scolaire.enUS.block;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.age.SchoolAge;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.search.SearchList;

public class SchoolBlock extends SchoolBlockGen<Cluster> {

	protected void _blockKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _childKey(Wrap<Long> c) {
	}

	protected void _enrollmentKeys(List<Long> o) {}

	protected void _enrollmentKey(Wrap<Long> o) {}

	protected void _enrollmentAttribute(Wrap<Boolean> o) {}

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

	protected void _ageKey(Wrap<Long> c) {
	}

	protected void _ageSearch(SearchList<SchoolAge> l) {
		l.setQuery("*:*");
		l.addFilterQuery("blockKeys_indexed_longs:" + pk);
		l.setC(SchoolAge.class);
		l.setStore(true);
	}

	protected void _age_(Wrap<SchoolAge> c) {
		if(ageSearch.size() > 0) {
			c.o(ageSearch.get(0));
		}
	}

	protected void _schoolKey(Wrap<Long> c) {
		if(age_ != null)
			c.o(age_.getSchoolKey());
	}

	protected void _yearKey(Wrap<Long> c) {
		if(age_ != null)
			c.o(age_.getYearKey());
	}

	protected void _seasonKey(Wrap<Long> c) {
		if(age_ != null)
			c.o(age_.getSeasonKey());
	}

	protected void _sessionKey(Wrap<Long> c) {
		if(age_ != null)
			c.o(age_.getSessionKey());
	}

	protected void _schoolName(Wrap<String> c) {
		if(age_ != null)
			c.o(age_.getSchoolName());
	}

	protected void _schoolCompleteName(Wrap<String> c) {
		if(age_ != null)
			c.o((String)age_.getSchoolCompleteName());
	}

	protected void _schoolLocation(Wrap<String> c) {
		if(age_ != null)
			c.o((String)age_.getSchoolLocation());
	}

	protected void _schoolAddress(Wrap<String> c) {
		if(age_ != null)
			c.o((String)age_.getSchoolAddress());
	}

	protected void _schoolPhoneNumber(Wrap<String> c) {
		if(age_ != null)
			c.o((String)age_.getSchoolPhoneNumber());
	}

	protected void _schoolAdministratorName(Wrap<String> c) {
		if(age_ != null)
			c.o((String)age_.getSchoolAdministratorName());
	}

	protected void _yearStart(Wrap<Integer> c) {
		if(age_ != null)
			c.o(age_.getYearStart());
	}

	protected void _yearEnd(Wrap<Integer> c) {
		if(age_ != null)
			c.o(age_.getYearEnd());
	}

	protected void _seasonStartDate(Wrap<LocalDate> c) {
		if(age_ != null)
			c.o(age_.getSeasonStartDate());
	}

	protected void _seasonSummer(Wrap<Boolean> c) {
		if(age_ != null)
			c.o(age_.getSeasonSummer());
	}

	protected void _seasonWinter(Wrap<Boolean> c) {
		if(age_ != null)
			c.o(age_.getSeasonWinter());
	}

	protected void _yearEnrollmentFee(Wrap<BigDecimal> c) {
		if(age_ != null)
			c.o(age_.getYearEnrollmentFee());
	}

	protected void _seasonShortName(Wrap<String> c) {
		if(age_ != null)
			c.o(age_.getSeasonShortName());
	}

	protected void _seasonCompleteName(Wrap<String> c) {
		if(age_ != null)
			c.o(age_.getSeasonCompleteName());
	}

	protected void _sessionStartDate(Wrap<LocalDate> c) {
		if(age_ != null)
			c.o((LocalDate)age_.getSessionStartDate());
	}

	protected void _sessionEndDate(Wrap<LocalDate> c) {
		if(age_ != null)
			c.o((LocalDate)age_.getSessionEndDate());
	}

	protected void _ageShortName(Wrap<String> c) {
		if(age_ != null)
			c.o(age_.getAgeShortName());
	}

	protected void _ageCompleteName(Wrap<String> c) {
		if(age_ != null)
			c.o(age_.getAgeCompleteName());
	}

	protected void _ageStart(Wrap<Integer> c) {
		if(age_ != null)
			c.o(age_.getAgeStart());
	}

	protected void _ageEnd(Wrap<Integer> c) {
		if(age_ != null)
			c.o(age_.getAgeEnd());
	}

	protected void _blockStartTime(Wrap<LocalTime> c) {
	}

	protected void _blockEndTime(Wrap<LocalTime> c) {
	}

	protected void _blockPricePerMonth(Wrap<BigDecimal> c) {
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

	protected void _blockTotalPrice(Wrap<BigDecimal> c) {
		if(blockPricePerMonth != null && sessionStartDate != null && sessionEndDate != null) {
			c.o(blockPricePerMonth.multiply(new BigDecimal(ChronoUnit.MONTHS.between(sessionStartDate, sessionEndDate))));
		}
	}

	protected void _sessionBlocks(List<SchoolBlock> l) {}

	protected void _ageBlocks(List<SchoolBlock> l) {}

	protected void _blockBlocks(List<SchoolBlock> l) {}

	protected void _blockShortName(Wrap<String> c) {
		String o;
		String weekdays = "";
		if(BooleanUtils.isTrue(blockSunday)) weekdays += " Su";
		if(BooleanUtils.isTrue(blockMonday)) weekdays += " Mo";
		if(BooleanUtils.isTrue(blockTuesday)) weekdays += " Tu";
		if(BooleanUtils.isTrue(blockWednesday)) weekdays += " We";
		if(BooleanUtils.isTrue(blockThursday)) weekdays += " Th";
		if(BooleanUtils.isTrue(blockFriday)) weekdays += " Fr";
		if(BooleanUtils.isTrue(blockSaturday)) weekdays += " Sa";
		weekdays = StringUtils.replace(StringUtils.trim(weekdays), " ", "/");
		if(blockPricePerMonth == null)
			o = String.format("%s %s at %s", strBlockStartTime(), strBlockEndTime(), weekdays, schoolCompleteName);
		else
			o = String.format("%s - %s %s ($%s/month) at %s", strBlockStartTime(), strBlockEndTime(), weekdays, strBlockPricePerMonth(), schoolCompleteName);
		c.o(o);
	}

	protected void _blockAdminName(Wrap<String> c) {
		String o;
		String weekdays = "";
		if(BooleanUtils.isTrue(blockSunday)) weekdays += " Su";
		if(BooleanUtils.isTrue(blockMonday)) weekdays += " M";
		if(BooleanUtils.isTrue(blockTuesday)) weekdays += " Tu";
		if(BooleanUtils.isTrue(blockWednesday)) weekdays += " W";
		if(BooleanUtils.isTrue(blockThursday)) weekdays += " Th";
		if(BooleanUtils.isTrue(blockFriday)) weekdays += " F";
		if(BooleanUtils.isTrue(blockSaturday)) weekdays += " S";
		weekdays = StringUtils.trim(weekdays);
			o = String.format("%s %s - %s", weekdays, strBlockStartTime(), strBlockEndTime());
		c.o(o);
	}

	protected void _blockCompleteName(Wrap<String> c) {
		String o;
		String weekdays = "";
		if(BooleanUtils.isTrue(blockSunday)) weekdays += " Su";
		if(BooleanUtils.isTrue(blockMonday)) weekdays += " Mo";
		if(BooleanUtils.isTrue(blockTuesday)) weekdays += " Tu";
		if(BooleanUtils.isTrue(blockWednesday)) weekdays += " We";
		if(BooleanUtils.isTrue(blockThursday)) weekdays += " Th";
		if(BooleanUtils.isTrue(blockFriday)) weekdays += " Fr";
		if(BooleanUtils.isTrue(blockSaturday)) weekdays += " Sa";
		weekdays = StringUtils.replace(StringUtils.trim(weekdays), " ", "/");
		if(blockPricePerMonth == null)
			o = String.format("%s - %s %s %s", strBlockStartTime(), strBlockEndTime(), weekdays, ageCompleteName);
		else
			o = String.format("%s - %s %s ($%s/month) %s", strBlockStartTime(), strBlockEndTime(), weekdays, strBlockPricePerMonth(), ageCompleteName);
		c.o(o);
	}

	@Override()
	protected void  _objectTitle(Wrap<String> c) {
		c.o(blockCompleteName);
	}

	@Override()
	public String strBlockPricePerMonth() {
		return blockPricePerMonth == null ? "" : blockPricePerMonth.setScale(0).toString();
	}

	public void  inputEnrollmentAttribute(String classeApiMethodeMethode) {
		e("input")
			.a("type", "checkbox")
//			.a("id", classeApiMethodeMethode, "_inscriptionApprouve")
			.a("class", "setEnrollmentApproved")
			.a("name", "setEnrollmentApproved")
			.a("onchange", "patchSchoolEnrollmentVals([{ name: 'fq', value: 'pk:", enrollmentKey, "' }], { [($(this).prop('checked') ? 'add' : 'remove') + 'BlockKeys']: '", pk, "' } ); ");
			if(enrollmentAttribute)
				a("checked", "checked");
		fg();

	}
}
