package org.computate.scolaire.enUS.enrollment;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.computate.scolaire.enUS.year.SchoolYear;
import org.computate.scolaire.enUS.block.SchoolBlock;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.child.SchoolChild;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import org.computate.scolaire.enUS.mom.SchoolMom;
import org.computate.scolaire.enUS.page.PageLayout;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import org.computate.scolaire.enUS.dad.SchoolDad;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.season.SchoolSeason;

/**
 * Model: true
 * Api: true
 * Page: true
 * Saved: true
 * RoleSession: true
 * RoleUser: true
 * Color: blue-gray
 * IconGroup: solid
 * IconName: edit
 * Role.enUS: SiteManager
 * ApiUri.enUS: /api/enrollment
 * ApiTag.enUS: Enrollment
 * AName.enUS: an enrollment
 * Role.frFR: SiteManager
 * ApiUri.frFR: /api/inscription
 * ApiTag.frFR: Inscription
 * AName.frFR: une inscription
 * CanonicalName: org.computate.scolaire.frFR.inscription.InscriptionScolaire
 **/
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

	protected void _userKeys(List<Long> l) {
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

	protected void _feeSearch(SearchList<SchoolPayment> l) {
		l.setQuery("*:*");
		l.addFilterQuery("enrollmentKey_indexed_long:" + pk);
		l.addFilterQuery("chargeMonth_indexed_boolean:true");
		l.add("json.facet", "{'paymentDateMax':'max(paymentDate_indexed_date)'}");
		l.setC(SchoolPayment.class);
		l.setStore(true);
	}

	protected void _paymentSearch(SearchList<SchoolPayment> l) {
		l.setQuery("*:*");
		l.addFilterQuery("enrollmentKey_indexed_long:" + pk);
		l.add("json.facet", "{sum_paymentAmount:'sum(paymentAmount_indexed_double)'}");
		l.add("json.facet", "{sum_chargeAmount:'sum(chargeAmount_indexed_double)'}");
		l.add("json.facet", "{sum_chargeAmountDue:'sum(chargeAmountDue_indexed_double)'}");
		l.add("json.facet", "{sum_chargeAmountFuture:'sum(chargeAmountFuture_indexed_double)'}");
		l.addSort("paymentDate_indexed_date", ORDER.desc);
		l.setC(SchoolPayment.class);
		l.setStore(true);
	}

	protected void _childFirstName(Wrap<String> c) {
		if(child_ != null)
			c.o(child_.getPersonFirstName());
	}

	protected void _childFirstNamePreferred(Wrap<String> c) {
		if(child_ != null)
			c.o(child_.getPersonFirstNamePreferred());
	}

	protected void _childFamilyName(Wrap<String> c) {
		if(child_ != null)
			c.o(child_.getFamilyName());
	}

	protected void _momFirstName(Wrap<String> c) {
		if(moms.size() > 0)
			c.o(moms.get(0).getPersonFirstName());
	}

	protected void _momFirstNamePreferred(Wrap<String> c) {
		if(moms.size() > 0)
			c.o(moms.get(0).getPersonFirstNamePreferred());
	}

	protected void _momCompleteNamePreferred(Wrap<String> c) {
		if(moms.size() > 0)
			c.o(moms.get(0).getPersonCompleteNamePreferred());
	}

	protected void _dadFirstName(Wrap<String> c) {
		if(dads.size() > 0)
			c.o(dads.get(0).getPersonFirstName());
	}

	protected void _dadFirstNamePreferred(Wrap<String> c) {
		if(dads.size() > 0)
			c.o(dads.get(0).getPersonFirstNamePreferred());
	}

	protected void _dadCompleteNamePreferred(Wrap<String> c) {
		if(dads.size() > 0)
			c.o(dads.get(0).getPersonCompleteNamePreferred());
	}

	protected void _childCompleteName(Wrap<String> c) {
		if(child_ != null)
			c.o(child_.getPersonCompleteName());
	}

	protected void _childCompleteNamePreferred(Wrap<String> c) {
		if(child_ != null)
			c.o(child_.getPersonCompleteNamePreferred());
	}

	protected void _childBirthDate(Wrap<LocalDate> c) {
		if(child_ != null)
			c.o(child_.getPersonBirthDate());
	}

	protected void _childBirthDateYear(Wrap<Integer> c) {
		if(childBirthDate != null)
			c.o(childBirthDate.getYear());
	}

	protected void _childBirthDateMonthOfYear(Wrap<String> c) {
		if(childBirthDate != null)
			c.o(childBirthDate.format(DateTimeFormatter.ofPattern("MMMM", Locale.US)));
	}

	protected void _childBirthDateDayOfWeek(Wrap<String> c) {
		if(childBirthDate != null)
			c.o(childBirthDate.format(DateTimeFormatter.ofPattern("EEEE", Locale.US)));
	}

	@Override()
	public String strChildBirthDate() { 
		return childBirthDate == null ? "" : childBirthDate.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.US));
	}

	protected void _childBirthMonth(Wrap<Integer> c) {
		if(childBirthDate != null)
			c.o(childBirthDate.getMonthValue());
	}

	protected void _childBirthDay(Wrap<Integer> c) {
		if(childBirthDate != null)
			c.o(childBirthDate.getMonthValue());
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

	protected void _schoolForm(Wrap<String> c) {
		if(year_ != null)
			c.o(year_.getSchoolForm());
	}

	protected void _schoolNumber(Wrap<Integer> c) {
		if(year_ != null)
			c.o((Integer)year_.getSchoolNumber());
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

	protected void _yearEnrollmentFee(Wrap<BigDecimal> c) {
		if(year_ != null)
			c.o(year_.getYearEnrollmentFee());
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

	protected void _photo(Wrap<String> c) {
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

	protected void _enrollmentGroupColor(Wrap<String> c) {
		if(enrollmentGroupName != null)
			c.o(StringUtils.lowerCase(StringUtils.substringBefore(enrollmentGroupName, " ")));
	}

	protected void _enrollmentPaymentEachMonth(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _enrollmentPaymentComplete(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _customerProfileId(Wrap<String> c) {
	}

	protected void _enrollmentChargeDate(Wrap<LocalDate> c) {
		SimpleOrderedMap facets = (SimpleOrderedMap)Optional.ofNullable(feeSearch.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap());
		LocalDate o = Optional.ofNullable((Date)facets.get("paymentDateMax")).map(d -> d.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate()).orElse(null);
		c.o(o);
	}

	protected void _paymentFacets(Wrap<SimpleOrderedMap> c) {
		c.o((SimpleOrderedMap)Optional.ofNullable(paymentSearch.getQueryResponse()).map(QueryResponse::getResponse).map(r -> r.get("facets")).orElse(new SimpleOrderedMap()));
	}

	protected void _paymentLastStr(Wrap<String> c) {
		for(SchoolPayment p : paymentSearch.getList()) {
			if(p.getPaymentAmount() != null) {
				c.o(p.getPaymentShortName());
				return;
			}
		}
		c.o("none");
	}

	protected void _paymentAmount(Wrap<BigDecimal> c) {
		c.o(Optional.ofNullable((Double)paymentFacets.get("sum_paymentAmount")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)));
	}

	protected void _chargeAmount(Wrap<BigDecimal> c) {
		c.o(Optional.ofNullable((Double)paymentFacets.get("sum_chargeAmount")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)));
	}

	protected void _chargeAmountFuture(Wrap<BigDecimal> c) {
		c.o(Optional.ofNullable((Double)paymentFacets.get("sum_chargeAmountFuture")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)));
	}

	protected void _chargeAmountDue(Wrap<BigDecimal> c) {
		c.o(Optional.ofNullable((Double)paymentFacets.get("sum_chargeAmountDue")).map(d -> new BigDecimal(d, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)).orElse(new BigDecimal(0, MathContext.DECIMAL64).setScale(2, RoundingMode.CEILING)));
	}

	protected void _chargesNow(Wrap<BigDecimal> c) {
		c.o(chargeAmount.subtract(paymentAmount).subtract(chargeAmountFuture));
	}

	protected void _paymentsCurrent(Wrap<Boolean> c) {
		c.o(chargesNow.compareTo(BigDecimal.ZERO) <= 0);
	}

	protected void _paymentsLate(Wrap<Boolean> c) {
		c.o(chargesNow.subtract(chargeAmountDue).compareTo(BigDecimal.ZERO) > 0);
	}

	protected void _paymentsLateAmount(Wrap<BigDecimal> c) {
		if(paymentsLate)
			c.o(chargesNow.subtract(chargeAmountDue));
		else
			c.o(BigDecimal.ZERO);
	}

	protected void _paymentsAhead(Wrap<Boolean> c) {
		c.o(chargesNow.compareTo(BigDecimal.ZERO) < 0);
	}

	protected void _chargesCreated(Wrap<Boolean> c) {
		c.o(blockKeys.size() == 0 || chargeAmount.compareTo(BigDecimal.ZERO) > 0);
	}

	protected void _createdYear(Wrap<Integer> c) {
		if(created != null)
			c.o(created.getYear());
	}

	protected void _createdDayOfWeek(Wrap<String> c) {
		if(created != null)
			c.o(created.format(DateTimeFormatter.ofPattern("EEEE", Locale.US)));
	}

	protected void _createdMonthOfYear(Wrap<String> c) {
		if(created != null)
			c.o(created.format(DateTimeFormatter.ofPattern("MMMM", Locale.US)));
	}

	protected void _createdHourOfDay(Wrap<String> c) {
		if(created != null) {
			ZonedDateTime created1 = created.truncatedTo(ChronoUnit.HOURS);
			ZonedDateTime created2 = created1.plusHours(1);
			c.o(created1.format(PageLayout.FORMATTimeDisplay) + "-" + created2.format(PageLayout.FORMATTimeDisplay));
		}
	}

	protected void _enrollmentDaysOfWeek(List<String> l) {
		if(BooleanUtils.isTrue(blockSunday))
			l.add("Sunday");
		if(BooleanUtils.isTrue(blockMonday))
			l.add("Monday");
		if(BooleanUtils.isTrue(blockTuesday))
			l.add("Tuesday");
		if(BooleanUtils.isTrue(blockWednesday))
			l.add("Wednesday");
		if(BooleanUtils.isTrue(blockThursday))
			l.add("Thursday");
		if(BooleanUtils.isTrue(blockFriday))
			l.add("Friday");
		if(BooleanUtils.isTrue(blockSaturday))
			l.add("Saturday");
	}

	protected void _enrollmentParentNames(Wrap<String> c) {
		StringBuilder b = new StringBuilder();
		if(moms.size() == 0 && dads.size() == 0) {
			for(SchoolGuardian o : guardians) {
				if(o.getPersonCompleteNamePreferred() != null) {
					if(b.length() > 0)
						b.append(", ");
					b.append(o.getPersonCompleteNamePreferred());
				}
			}
		}
		else {
			for(SchoolMom o : moms) {
				if(o.getPersonCompleteNamePreferred() != null) {
					if(b.length() > 0)
						b.append(", ");
					b.append(o.getPersonCompleteNamePreferred());
				}
			}
			for(SchoolDad o : dads) {
				if(o.getPersonCompleteNamePreferred() != null) {
					if(b.length() > 0)
						b.append(", ");
					b.append(o.getPersonCompleteNamePreferred());
				}
			}
		}
		c.o(b.toString());
	}

	protected void _enrollmentEmails(List<String> l) {
		if(moms.size() == 0 && dads.size() == 0) {
			for(SchoolGuardian o : guardians) {
				String personEmail = o.getPersonEmail();
				if(StringUtils.isNotBlank(personEmail)) {
					l.add(o.getPersonEmail());
				}
			}
		}
		else {
			for(SchoolMom o : moms) {
				String personEmail = o.getPersonEmail();
				if(StringUtils.isNotBlank(personEmail)) {
					l.add(o.getPersonEmail());
				}
			}
			for(SchoolDad o : dads) {
				String personEmail = o.getPersonEmail();
				if(StringUtils.isNotBlank(personEmail)) {
					l.add(o.getPersonEmail());
				}
			}
		}
	}

	protected void _enrollmentEmail(Wrap<String> c) {
		c.o(enrollmentEmails.stream().findFirst().orElse(null));
	}

	protected void _enrollmentParentEmails(Wrap<String> c) {
		StringBuilder b = new StringBuilder();
		if(moms.size() == 0 && dads.size() == 0) {
			for(SchoolGuardian o : guardians) {
				String personEmail = o.getPersonEmail();
				if(StringUtils.isNotBlank(personEmail)) {
					if(b.length() > 0)
						b.append(", ");
					b.append(o.getPersonCompleteNamePreferred()).append(" <").append(o.getPersonEmail()).append(">");
				}
			}
		}
		else {
			for(SchoolMom o : moms) {
				String personEmail = o.getPersonEmail();
				if(StringUtils.isNotBlank(personEmail)) {
					if(b.length() > 0)
						b.append(", ");
					b.append(o.getPersonCompleteNamePreferred()).append(" <").append(o.getPersonEmail()).append(">");
				}
			}
			for(SchoolDad o : dads) {
				String personEmail = o.getPersonEmail();
				if(StringUtils.isNotBlank(personEmail)) {
					if(b.length() > 0)
						b.append(", ");
					b.append(o.getPersonCompleteNamePreferred()).append(" <").append(o.getPersonEmail()).append(">");
				}
			}
		}
		c.o(b.toString());
	}

	protected void _enrollmentPhoneNumbers(List<String> l) {
		if(moms.size() == 0 && dads.size() == 0) {
			for(SchoolGuardian o : guardians) {
				String personPhoneNumber = o.getPersonPhoneNumber();
				if(StringUtils.isNotBlank(personPhoneNumber)) {
					l.add(o.getPersonPhoneNumber());
				}
			}
		}
		else {
			for(SchoolMom o : moms) {
				String personPhoneNumber = o.getPersonPhoneNumber();
				if(StringUtils.isNotBlank(personPhoneNumber)) {
					l.add(o.getPersonPhoneNumber());
				}
			}
			for(SchoolDad o : dads) {
				String personPhoneNumber = o.getPersonPhoneNumber();
				if(StringUtils.isNotBlank(personPhoneNumber)) {
					l.add(o.getPersonPhoneNumber());
				}
			}
		}
	}

	protected void _enrollmentPhoneNumber(Wrap<String> c) {
		c.o(enrollmentPhoneNumbers.stream().findFirst().orElse(null));
	}

	protected void _enrollmentParentName(Wrap<String> c) {
		if(moms.size() == 0 && dads.size() == 0) {
			for(SchoolGuardian o : guardians) {
				String personneNomCompletPrefere = o.getPersonCompleteNamePreferred();
				if(StringUtils.isNotBlank(personneNomCompletPrefere)) {
					c.o(o.getPersonCompleteNamePreferred());
					return;
				}
			}
		}
		else {
			for(SchoolMom o : moms) {
				String personneNomCompletPrefere = o.getPersonCompleteNamePreferred();
				if(StringUtils.isNotBlank(personneNomCompletPrefere)) {
					c.o(o.getPersonCompleteNamePreferred());
					return;
				}
			}
			for(SchoolDad o : dads) {
				String personneNomCompletPrefere = o.getPersonCompleteNamePreferred();
				if(StringUtils.isNotBlank(personneNomCompletPrefere)) {
					c.o(o.getPersonCompleteNamePreferred());
					return;
				}
			}
		}
	}

	protected void _enrollmentParentNameLines(Wrap<String> c) {
		StringBuilder b = new StringBuilder();
		if(moms.size() == 0 && dads.size() == 0) {
			guardians.stream().forEach(o -> b.append(o.getPersonCompleteNamePreferred()).append("\n"));
		}
		else {
			moms.stream().forEach(o -> b.append(o.getPersonCompleteNamePreferred()).append("\n"));
			dads.stream().forEach(o -> b.append(o.getPersonCompleteNamePreferred()).append("\n"));
		}
		c.o(b.toString().trim());
	}

	protected void _enrollmentParentEmailLines(Wrap<String> c) {
		StringBuilder b = new StringBuilder();
		if(moms.size() == 0 && dads.size() == 0) {
			for(SchoolGuardian o : guardians) {
				String personneMail = o.getPersonEmail();
				if(StringUtils.isNotBlank(personneMail)) {
					b.append(o.getPersonEmail()).append("\n");
				}
			}
		}
		else {
			for(SchoolMom o : moms) {
				String personneMail = o.getPersonEmail();
				if(StringUtils.isNotBlank(personneMail)) {
					b.append(o.getPersonEmail()).append("\n");
				}
			}
			for(SchoolDad o : dads) {
				String personneMail = o.getPersonEmail();
				if(StringUtils.isNotBlank(personneMail)) {
					b.append(o.getPersonEmail()).append("\n");
				}
			}
		}
		c.o(b.toString().trim());
	}

	protected void _enrollmentParentDetailLines(Wrap<String> c) {
		StringBuilder b = new StringBuilder();
		if(moms.size() == 0 && dads.size() == 0) {
			guardians.stream().forEach(o -> b.append("G- ").append(o.getPersonCompleteNamePreferred()).append(" ").append(o.getPersonPhoneNumber()).append("\n"));
		}
		else {
			moms.stream().forEach(o -> b.append("M- ").append(o.getPersonCompleteNamePreferred()).append(" ").append(o.getPersonPhoneNumber()).append("\n"));
			dads.stream().forEach(o -> b.append("D- ").append(o.getPersonCompleteNamePreferred()).append(" ").append(o.getPersonPhoneNumber()).append("\n"));
		}
		c.o(b.toString().trim());
	}

	protected void _enrollmentPickupDetailLines(Wrap<String> c) {
		StringBuilder b = new StringBuilder();
		moms.stream().filter(o -> o.getPersonPickup()).forEach(o -> b.append(o.getPersonCompleteNamePreferred()).append(" ").append(o.getPersonPhoneNumber()).append("\n"));
		dads.stream().filter(o -> o.getPersonPickup()).forEach(o -> b.append(o.getPersonCompleteNamePreferred()).append(" ").append(o.getPersonPhoneNumber()).append("\n"));
		guardians.stream().filter(o -> o.getPersonPickup()).forEach(o -> b.append(o.getPersonCompleteNamePreferred()).append(" ").append(o.getPersonPhoneNumber()).append("\n"));
		c.o(b.toString().trim());
	}

	protected void _enrollmentEmergencyContactDetailLines(Wrap<String> c) {
		StringBuilder b = new StringBuilder();
		moms.stream().filter(o -> o.getPersonEmergencyContact()).forEach(o -> b.append(o.getPersonCompleteNamePreferred()).append(" ").append(o.getPersonPhoneNumber()).append("\n"));
		dads.stream().filter(o -> o.getPersonEmergencyContact()).forEach(o -> b.append(o.getPersonCompleteNamePreferred()).append(" ").append(o.getPersonPhoneNumber()).append("\n"));
		guardians.stream().filter(o -> o.getPersonEmergencyContact()).forEach(o -> b.append(o.getPersonCompleteNamePreferred()).append(" ").append(o.getPersonPhoneNumber()).append("\n"));
		c.o(b.toString().trim());
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

	protected void _enrollmentYears(List<SchoolEnrollment> l) {}

	protected void _enrollmentApprovals(List<SchoolEnrollment> l) {}

	protected void _enrollmentGroups(List<SchoolEnrollment> l) {}

	protected void _enrollmentEnrollments(List<SchoolEnrollment> l) {}

	protected void _childImmunizationsReceived(Wrap<String> c) {
		c.o(enrollmentImmunizations ? "yes" : "no");
	}

	protected void _childPhotosApproved(Wrap<String> c) {
		c.o(enrollmentDate9 != null ? "yes" : "no");
	}

	protected void _enrollmentNumber(Wrap<Integer> c) {}

	protected void _enrollmentCompleteName(Wrap<String> c) {
		String o;
		if(child_ != null)
			o = String.format("enrollment for the child %s %s %s", child_.getPersonCompleteNamePreferred(), year_ == null ? "" : year_.getYearShortName(), schoolLocation);
		else
			o = String.format("enrollment %s %s %s", pk, " ", year_ == null ? "" : year_.getYearShortName(), schoolLocation);
		c.o(o);
	}

	@Override()
	protected void  _objectTitle(Wrap<String> c) {
		c.o(enrollmentCompleteName);
	}
}
