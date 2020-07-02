package org.computate.scolaire.enUS.season;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.year.SchoolYear;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.session.SchoolSession;

/**
 * Model: true
 * Api: true
 * Page: true
 * Saved: true
 * Color: yellow
 * IconGroup: regular
 * IconName: sun
 * Role.enUS: SiteAdmin
 * ApiUri.enUS: /api/season
 * ApiTag.enUS: Season
 * AName.enUS: a season
 * Role.frFR: SiteAdmin
 * ApiUri.frFR: /api/saison
 * ApiTag.frFR: Saison
 * AName.frFR: une saison
 * CanonicalName: org.computate.scolaire.frFR.saison.SaisonScolaire
 **/
public class SchoolSeason extends SchoolSeasonGen<Cluster> {

	protected void _seasonKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _enrollmentKeys(List<Long> o) {}

	protected void _yearKey(Wrap<Long> c) {
	}

	protected void _sessionKeys(List<Long> o) {}

	protected void _educationSort(Wrap<Integer> c) {
		c.o(3);
	}

	protected void _schoolSort(Wrap<Integer> c) {
		c.o(3);
	}

	protected void _yearSort(Wrap<Integer> c) {
		c.o(3);
	}

	protected void _seasonSort(Wrap<Integer> c) {
		c.o(3);
	}

	protected void _yearSearch(SearchList<SchoolYear> l) {
		l.setQuery("*:*");
		l.addFilterQuery("seasonKeys_indexed_longs:" + pk);
		l.setC(SchoolYear.class);
		l.setStore(true);
	}

	protected void _year_(Wrap<SchoolYear> c) {
		if(yearSearch.size() > 0) {
			c.o(yearSearch.get(0));
		}
	}

	protected void _schoolKey(Wrap<Long> c) {
		if(year_ != null)
			c.o(year_.getSchoolKey());
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
			c.o((String)year_.getSchoolLocation());
	}

	protected void _schoolAddress(Wrap<String> c) {
		if(year_ != null)
			c.o((String)year_.getSchoolAddress());
	}

	protected void _schoolPhoneNumber(Wrap<String> c) {
		if(year_ != null)
			c.o((String)year_.getSchoolPhoneNumber());
	}

	protected void _schoolNumber(Wrap<Integer> c) {
		if(year_ != null)
			c.o((Integer)year_.getSchoolNumber());
	}

	protected void _schoolAdministratorName(Wrap<String> c) {
		if(year_ != null)
			c.o((String)year_.getSchoolAdministratorName());
	}

	protected void _yearStart(Wrap<Integer> c) {
		if(year_ != null)
			c.o(year_.getYearStart());
	}

	protected void _yearEnd(Wrap<Integer> c) {
		if(year_ != null)
			c.o(year_.getYearEnd());
	}

	protected void _yearEnrollmentFee(Wrap<BigDecimal> c) {
		if(year_ != null)
			c.o(year_.getYearEnrollmentFee());
	}

	protected void _seasonStartDate(Wrap<LocalDate> c) {}

	@Override()
	public SchoolSeason setSeasonStartDate(String o) {
		if(StringUtils.contains(o, " "))
			o = StringUtils.substringBefore(o, " ");
		try {
			return super.setSeasonStartDate(o);
		} catch (Exception e) {
			setSeasonStartDate(LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(o)));
			return this;
		}
	}

	protected void _seasonSummer(Wrap<Boolean> c) {}

	protected void _seasonWinter(Wrap<Boolean> c) {}

	protected void _seasonFuture(Wrap<Boolean> c) {}

	protected void _seasonShortName(Wrap<String> c) {
		String o;
		
		if(BooleanUtils.isTrue(seasonFuture))
			o = String.format("Additional classes coming during the %s-%s school year at %s", yearStart, yearEnd, schoolName);
		else if(BooleanUtils.isTrue(seasonSummer))
			o = String.format("Summer season classes (one time registration fee $%s)", strYearEnrollmentFee());
		else if(BooleanUtils.isTrue(seasonWinter))
			o = String.format("Regular school year classes (one time registration fee $%s)", strYearEnrollmentFee());
		else
			o = String.format("%s season at %s", strSeasonStartDate(), schoolCompleteName);
		
		c.o(o);
	}

	protected void _seasonCompleteName(Wrap<String> c) {
		String o;
		
		if(BooleanUtils.isTrue(seasonFuture))
			o = String.format("Additional classes coming during the %s-%s school year at %s", yearStart, yearEnd, schoolCompleteName);
		else if(BooleanUtils.isTrue(seasonSummer))
			o = String.format("%s summer season at %s", yearEnd, schoolCompleteName);
		else if(BooleanUtils.isTrue(seasonWinter))
			o = String.format("%s-%s school season at %s", yearStart, yearEnd, schoolCompleteName);
		else
			o = String.format("%s season at %s", strSeasonStartDate(), schoolCompleteName);
		
		c.o(o);
	}

	@Override()
	protected void  _objectTitle(Wrap<String> c) {
		c.o(seasonCompleteName);
	}

	@Override()
	public String strYearEnrollmentFee() {
		return yearEnrollmentFee == null ? "" : yearEnrollmentFee.setScale(0).toString();
	}
}
