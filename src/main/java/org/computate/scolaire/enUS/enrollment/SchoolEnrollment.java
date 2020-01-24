package org.computate.scolaire.enUS.enrollment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.year.SchoolYear;
import org.computate.scolaire.enUS.block.SchoolBlock;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.child.SchoolChild;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import org.computate.scolaire.enUS.mom.SchoolMom;
import org.computate.scolaire.enUS.dad.SchoolDad;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.season.SchoolSeason;

public class SchoolEnrollment extends SchoolEnrollmentGen<Cluster> {

	protected void _enrollmentKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _yearKey(Wrap<Long> c) {
	}

	protected void _yearSearch(SearchList<SchoolYear> l) {
		if(yearKey != null) {
			l.setQuery("*:*");
			l.addFilterQuery("pk_indexed_long:" + yearKey);
			l.setC(SchoolYear.class);
			l.setStore(true);
		}
		else {
			l.setQuery(null);
		}
	}

	protected void _year_(Wrap<SchoolYear> c) {
		if(yearSearch.size() > 0) {
			c.o(yearSearch.get(0));
		}
	}

	protected void _blockKeys(List<Long> o) {}

	protected void _blockSearch(SearchList<SchoolBlock> l) {
		if(blockKeys.size() > 0) {
			l.setQuery("*:*");
			l.addFilterQuery("pk_indexed_long:(" + StringUtils.join(blockKeys, " ") + ")");
			l.setC(SchoolBlock.class);
			l.setStore(true);
		}
		else {
			l.setQuery(null);
		}
	}

	protected void _blocks_(Wrap<List<SchoolBlock>> c) {
		c.o(blockSearch.getList());
	}

	protected void _seasons_(List<SchoolSeason> c) {
	}

	protected void _block_(Wrap<SchoolBlock> c) {
		if(blockSearch.size() > 0) {
			c.o(blockSearch.get(0));
		}
	}

	protected void _schoolKey(Wrap<Long> c) {
		if(year_ != null)
			c.o(year_.getSchoolKey());
	}

	protected void _seasonKey(Wrap<Long> c) {
		if(block_ != null)
			c.o(block_.getSeasonKey());
	}

	protected void _sessionKey(Wrap<Long> c) {
		if(block_ != null)
			c.o(block_.getSessionKey());
	}

	protected void _ageKey(Wrap<Long> c) {
		if(block_ != null)
			c.o(block_.getAgeKey());
	}

	protected void _blockKey(Wrap<Long> c) {
		if(block_ != null)
			c.o(block_.getBlockKey());
	}

	protected void _childKey(Wrap<Long> c) {}

	protected void _momKeys(List<Long> o) {}

	protected void _dadKeys(List<Long> o) {}

	protected void _guardianKeys(List<Long> o) {}

	protected void _paymentKeys(List<Long> o) {}

	protected void _enrollmentFormKey(Wrap<Long> c) {
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

	protected void _childSearch(SearchList<SchoolChild> l) {
		if(childKey == null) {
			l.setQuery(null);
		}
		else {
			l.setQuery("*:*");
			l.addFilterQuery("pk_indexed_long:" + childKey);
			l.setC(SchoolChild.class);
			l.setStore(true);
		}
	}

	protected void _child_(Wrap<SchoolChild> c) {
		if(childSearch.size() > 0) {
			c.o(childSearch.get(0));
		}
	}

	protected void _momSearch(SearchList<SchoolMom> l) {
		l.setQuery("*:*");
		l.addFilterQuery("enrollmentKeys_indexed_longs:" + pk);
		l.setC(SchoolMom.class);
		l.setStore(true);
	}

	protected void _moms(Wrap<List<SchoolMom>> c) {
		c.o(momSearch.getList());
	}

	protected void _dadSearch(SearchList<SchoolDad> l) {
		l.setQuery("*:*");
		l.addFilterQuery("enrollmentKeys_indexed_longs:" + pk);
		l.setC(SchoolDad.class);
		l.setStore(true);
	}

	protected void _dads(Wrap<List<SchoolDad>> c) {
		c.o(dadSearch.getList());
	}

	protected void _guardianSearch(SearchList<SchoolGuardian> l) {
		l.setQuery("*:*");
		l.addFilterQuery("enrollmentKeys_indexed_longs:" + pk);
		l.setC(SchoolGuardian.class);
		l.setStore(true);
	}

	protected void _guardians(Wrap<List<SchoolGuardian>> c) {
		c.o(guardianSearch.getList());
	}

	protected void _childCompleteName(Wrap<String> c) {
		if(child_ != null)
			c.o(child_.getPersonCompleteName());
	}

	protected void _childBirthDate(Wrap<LocalDate> c) {
		if(child_ != null)
			c.o(child_.getPersonBirthDate());
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
			c.o(year_.getSchoolLocation());
	}

	protected void _schoolAddress(Wrap<String> c) {
		if(year_ != null)
			c.o(year_.getSchoolAddress());
	}

	protected void _schoolPhoneNumber(Wrap<String> c) {
		if(year_ != null)
			c.o(year_.getSchoolPhoneNumber());
	}

	protected void _schoolAdministratorName(Wrap<String> c) {
		if(year_ != null)
			c.o(year_.getSchoolAdministratorName());
	}

	protected void _yearStart(Wrap<Integer> c) {
		if(year_ != null)
			c.o(year_.getYearStart());
	}

	protected void _yearEnd(Wrap<Integer> c) {
		if(year_ != null)
			c.o(year_.getYearEnd());
	}

	protected void _seasonStartDate(Wrap<LocalDate> c) {
		if(block_ != null)
			c.o(block_.getSeasonStartDate());
	}

	protected void _seasonSummer(Wrap<Boolean> c) {
		if(block_ != null)
			c.o(block_.getSeasonSummer());
	}

	protected void _seasonWinter(Wrap<Boolean> c) {
		if(block_ != null)
			c.o(block_.getSeasonWinter());
	}

	protected void _yearEnrollmentFee(Wrap<BigDecimal> c) {
		if(year_ != null)
			c.o(year_.getYearEnrollmentFee());
	}

	protected void _seasonCompleteName(Wrap<String> c) {
		if(block_ != null)
			c.o(block_.getSeasonCompleteName());
	}

	protected void _sessionStartDate(Wrap<LocalDate> c) {
		if(block_ != null)
			c.o((LocalDate)block_.getSessionStartDate());
	}

	protected void _sessionEndDate(Wrap<LocalDate> c) {
		if(block_ != null)
			c.o((LocalDate)block_.getSessionEndDate());
	}

	protected void _ageCompleteName(Wrap<String> c) {
		if(block_ != null)
			c.o(block_.getAgeCompleteName());
	}

	protected void _ageStart(Wrap<Integer> c) {
		if(block_ != null)
			c.o(block_.getAgeStart());
	}

	protected void _ageEnd(Wrap<Integer> c) {
		if(block_ != null)
			c.o(block_.getAgeEnd());
	}

	protected void _blockStartTime(Wrap<LocalTime> c) {
		if(block_ != null)
			c.o(block_.getBlockStartTime());
	}

	protected void _blockEndTime(Wrap<LocalTime> c) {
		if(block_ != null)
			c.o(block_.getBlockEndTime());
	}

	protected void _blockPricePerMonth(Wrap<BigDecimal> c) {
		if(block_ != null)
			c.o(block_.getBlockPricePerMonth());
	}

	protected void _blockSunday(Wrap<Boolean> c) {
		if(block_ != null)
			c.o(block_.getBlockSunday());
	}

	protected void _blockMonday(Wrap<Boolean> c) {
		if(block_ != null)
			c.o(block_.getBlockMonday());
	}

	protected void _blockTuesday(Wrap<Boolean> c) {
		if(block_ != null)
			c.o(block_.getBlockTuesday());
	}

	protected void _blockWednesday(Wrap<Boolean> c) {
		if(block_ != null)
			c.o(block_.getBlockWednesday());
	}

	protected void _blockThursday(Wrap<Boolean> c) {
		if(block_ != null)
			c.o(block_.getBlockThursday());
	}

	protected void _blockFriday(Wrap<Boolean> c) {
		if(block_ != null)
			c.o(block_.getBlockFriday());
	}

	protected void _blockSaturday(Wrap<Boolean> c) {
		if(block_ != null)
			c.o(block_.getBlockSaturday());
	}

	protected void _blockTotalPrice(Wrap<BigDecimal> c) {
		if(block_ != null)
			c.o(block_.getBlockTotalPrice());
	}

	protected void _blockAdminName(Wrap<String> c) {
		if(block_ != null)
			c.o(block_.getBlockAdminName());
	}

	protected void _blockShortName(Wrap<String> c) {
		if(block_ != null)
			c.o(block_.getBlockShortName());
	}

	protected void _blockCompleteName(Wrap<String> c) {
		if(block_ != null)
			c.o(block_.getBlockCompleteName());
	}

	protected void _enrollmentApproved(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _enrollmentImmunizations(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _familyMarried(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _familySeparated(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _familyDivorced(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _familyAddress(Wrap<String> c) {
	}

	protected void _familyHowDoYouKnowTheSchool(Wrap<String> c) {
	}

	protected void _enrollmentSpecialConsiderations(Wrap<String> c) {
	}

	protected void _childMedicalConditions(Wrap<String> c) {
	}

	protected void _childPreviousSchoolsAttended(Wrap<String> c) {
	}

	protected void _childDescription(Wrap<String> c) {
	}

	protected void _childObjectives(Wrap<String> c) {
	}

	protected void _childPottyTrained(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _enrollmentGroupName(Wrap<String> c) {
	}

	protected void _enrollmentPaymentEachMonth(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _enrollmentPaymentComplete(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _enrollmentParentNames(Wrap<String> c) {
		StringBuilder b = new StringBuilder();
		for(SchoolMom o : moms) {
			if(b.length() > 0)
				b.append(", ");
			b.append(o.getPersonCompleteName());
		}
		for(SchoolDad o : dads) {
			if(b.length() > 0)
				b.append(", ");
			b.append(o.getPersonCompleteName());
		}
		c.o(b.toString());
	}

	protected void _enrollmentSignature1(Wrap<String> c) {
	}

	protected void _enrollmentSignature2(Wrap<String> c) {
	}

	protected void _enrollmentSignature3(Wrap<String> c) {
	}

	protected void _enrollmentSignature4(Wrap<String> c) {
	}

	protected void _enrollmentSignature5(Wrap<String> c) {
	}

	protected void _enrollmentSignature6(Wrap<String> c) {
	}

	protected void _enrollmentSignature7(Wrap<String> c) {
	}

	protected void _enrollmentSignature8(Wrap<String> c) {
	}

	protected void _enrollmentSignature9(Wrap<String> c) {
	}

	protected void _enrollmentSignature10(Wrap<String> c) {
	}

	protected void _enrollmentDate1(Wrap<LocalDate> c) {
	}

	protected void _enrollmentDate2(Wrap<LocalDate> c) {
	}

	protected void _enrollmentDate3(Wrap<LocalDate> c) {
	}

	protected void _enrollmentDate4(Wrap<LocalDate> c) {
	}

	protected void _enrollmentDate5(Wrap<LocalDate> c) {
	}

	protected void _enrollmentDate6(Wrap<LocalDate> c) {
	}

	protected void _enrollmentDate7(Wrap<LocalDate> c) {
	}

	protected void _enrollmentDate8(Wrap<LocalDate> c) {
	}

	protected void _enrollmentDate9(Wrap<LocalDate> c) {
	}

	protected void _enrollmentDate10(Wrap<LocalDate> c) {
	}

	protected void _enrollmentEnrollments(List<SchoolEnrollment> l) {}

	protected void _enrollmentNumber(Wrap<Integer> l) {}

	protected void _enrollmentCompleteName(Wrap<String> c) {
		String o;
		if(child_ != null)
			o = String.format("enrollment for the child %s", child_.getPersonCompleteNamePreferred());
		else
			o = String.format("enrollment %s", pk);
		c.o(o);
	}

	@Override()
	protected void  _objectTitle(Wrap<String> c) {
		c.o(enrollmentCompleteName);
	}
}
