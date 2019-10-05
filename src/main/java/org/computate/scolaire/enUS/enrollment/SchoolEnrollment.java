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
import org.computate.scolaire.enUS.child.SchoolChild;
import org.computate.scolaire.enUS.search.SearchList;

public class SchoolEnrollment extends SchoolEnrollmentGen<Cluster> {

	protected void _enrollmentKey(Wrap<Long> c) {
		c.o(pk);
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

	protected void _block(Wrap<SchoolBlock> c) {
		if(blockSearch.size() > 0) {
			c.o(blockSearch.get(0));
		}
	}

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
	}

	protected void _childKey(Wrap<Long> c) {}

	protected void _momKeys(List<Long> o) {}

	protected void _dadKeys(List<Long> o) {}

	protected void _guardianKeys(List<Long> o) {}

	protected void _paymentKeys(List<Long> o) {}

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
			l.addFilterQuery("enrollmentKeys_indexed_longs:" + pk);
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

	protected void _childCompleteName(Wrap<String> c) {
		if(child_ != null)
			c.o((String)child_.getPersonCompleteName());
	}

	protected void _schoolCompleteName(Wrap<String> c) {
		if(block != null)
			c.o((String)block.getSchoolCompleteName());
	}

	protected void _yearStart(Wrap<Integer> c) {
		if(block != null)
			c.o(block.getYearStart());
	}

	protected void _yearEnd(Wrap<Integer> c) {
		if(block != null)
			c.o(block.getYearStart());
	}

	protected void _seasonStartDate(Wrap<LocalDate> c) {
		if(block != null)
			c.o(block.getSeasonStartDate());
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

	protected void _seasonCompleteName(Wrap<String> c) {
		if(block != null)
			c.o(block.getSeasonCompleteName());
	}

	protected void _sessionStartDay(Wrap<LocalDate> c) {
		if(block != null)
			c.o((LocalDate)block.getSessionStartDay());
	}

	protected void _sessionEndDay(Wrap<LocalDate> c) {
		if(block != null)
			c.o((LocalDate)block.getSessionEndDay());
	}

	protected void _ageCompleteName(Wrap<String> c) {
		if(block != null)
			c.o(block.getAgeCompleteName());
	}

	protected void _ageStart(Wrap<Integer> c) {
		if(block != null)
			c.o(block.getAgeStart());
	}

	protected void _ageEnd(Wrap<Integer> c) {
		if(block != null)
			c.o(block.getAgeEnd());
	}

	protected void _blockStartTime(Wrap<LocalTime> c) {
		if(block != null)
			c.o(block.getBlockStartTime());
	}

	protected void _blockEndTime(Wrap<LocalTime> c) {
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

	protected void _familyMarried(Wrap<Boolean> c) {
	}

	protected void _familySeparated(Wrap<Boolean> c) {
	}

	protected void _familyDivorced(Wrap<Boolean> c) {
	}

	protected void _familyAddress(Wrap<String> c) {
	}

	protected void _familyHowDoYouKnowTheSchool(Wrap<String> c) {
	}

	protected void _enrollmentSpecialConsiderations(Wrap<String> c) {
	}

	protected void _enrollmentGroupName(Wrap<String> c) {
	}

	protected void _enrollmentPaymentEachMonth(Wrap<Boolean> c) {
	}

	protected void _enrollmentPaymentComplete(Wrap<Boolean> c) {
	}

	protected void _enrollmentCompleteName(Wrap<String> c) {
		String o;
		if(child_ != null)
			o = String.format("enrollment for the child %s", child_.getPersonCompleteNamePreferred());
		else
			o = String.format("enrollment %s", pk);
		c.o(o);
	}

	protected void _enrollmentId(Wrap<String> c) {
		if(enrollmentCompleteName != null) {
			String s = Normalizer.normalize(enrollmentCompleteName, Normalizer.Form.NFD);
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
		if(enrollmentId != null) {
			String o = siteRequest_.getSiteConfig_().getSiteBaseUrl() + "/enrollment/" + enrollmentId;
			c.o(o);
		}
	}

	protected void _objectSuggest(Wrap<String> c) { 
		c.o(enrollmentCompleteName);
	}

	@Override()
	protected void  _classCanonicalNames(List<String> l) {
		l.add(SchoolEnrollment.class.getCanonicalName());
		super._classCanonicalNames(l);
	}
}
