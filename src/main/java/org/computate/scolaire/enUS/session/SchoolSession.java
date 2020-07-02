package org.computate.scolaire.enUS.session;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.season.SchoolSeason;

/**
 * Model: true
 * Api: true
 * Page: true
 * Saved: true
 * Color: green
 * IconGroup: duotone
 * IconName: graduation-cap
 * Role.enUS: SiteAdmin
 * ApiUri.enUS: /api/session
 * ApiTag.enUS: Session
 * AName.enUS: a session
 * Role.frFR: SiteAdmin
 * ApiUri.frFR: /api/session
 * ApiTag.frFR: Session
 * AName.frFR: une session
 * CanonicalName: org.computate.scolaire.frFR.session.SessionScolaire
 **/
public class SchoolSession extends SchoolSessionGen<Cluster> {

	protected void _sessionKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _enrollmentKeys(List<Long> o) {}

	protected void _ageKeys(List<Long> o) {}

	protected void _educationSort(Wrap<Integer> c) {
		c.o(4);
	}

	protected void _schoolSort(Wrap<Integer> c) {
		c.o(4);
	}

	protected void _yearSort(Wrap<Integer> c) {
		c.o(4);
	}

	protected void _seasonSort(Wrap<Integer> c) {
		c.o(4);
	}

	protected void _sessionSort(Wrap<Integer> c) {
		c.o(4);
	}

	protected void _seasonKey(Wrap<Long> c) {
	}

	protected void _seasonSearch(SearchList<SchoolSeason> l) {
		l.setQuery("*:*");
		if(pk != null)
			l.addFilterQuery("sessionKeys_indexed_longs:" + pk);
		l.setC(SchoolSeason.class);
		l.setStore(true);
	}

	protected void _season_(Wrap<SchoolSeason> c) {
		if(seasonSearch.size() > 0) {
			c.o(seasonSearch.get(0));
		}
	}

	protected void _schoolKey(Wrap<Long> c) {
		if(season_ != null)
			c.o(season_.getSchoolKey());
	}

	protected void _yearKey(Wrap<Long> c) {
		if(season_ != null)
			c.o(season_.getYearKey());
	}

	protected void _schoolName(Wrap<String> c) {
		if(season_ != null)
			c.o(season_.getSchoolName());
	}

	protected void _schoolCompleteName(Wrap<String> c) {
		if(season_ != null)
			c.o((String)season_.getSchoolCompleteName());
	}

	protected void _schoolLocation(Wrap<String> c) {
		if(season_ != null)
			c.o((String)season_.getSchoolLocation());
	}

	protected void _schoolAddress(Wrap<String> c) {
		if(season_ != null)
			c.o((String)season_.getSchoolAddress());
	}

	protected void _schoolPhoneNumber(Wrap<String> c) {
		if(season_ != null)
			c.o((String)season_.getSchoolPhoneNumber());
	}

	protected void _schoolNumber(Wrap<Integer> c) {
		if(season_ != null)
			c.o((Integer)season_.getSchoolNumber());
	}

	protected void _schoolAdministratorName(Wrap<String> c) {
		if(season_ != null)
			c.o((String)season_.getSchoolAdministratorName());
	}

	protected void _yearStart(Wrap<Integer> c) {
		if(season_ != null)
			c.o(season_.getYearStart());
	}

	protected void _yearEnd(Wrap<Integer> c) {
		if(season_ != null)
			c.o(season_.getYearEnd());
	}

	protected void _seasonStartDate(Wrap<LocalDate> c) {
		if(season_ != null)
			c.o(season_.getSeasonStartDate());
	}

	protected void _seasonSummer(Wrap<Boolean> c) {
		if(season_ != null)
			c.o(season_.getSeasonSummer());
	}

	protected void _seasonWinter(Wrap<Boolean> c) {
		if(season_ != null)
			c.o(season_.getSeasonWinter());
	}

	protected void _yearEnrollmentFee(Wrap<BigDecimal> c) {
		if(season_ != null)
			c.o(season_.getYearEnrollmentFee());
	}

	protected void _seasonShortName(Wrap<String> c) {
		if(season_ != null)
			c.o(season_.getSeasonShortName());
	}

	protected void _seasonCompleteName(Wrap<String> c) {
		if(season_ != null)
			c.o(season_.getSeasonCompleteName());
	}

	protected void _sessionStartDate(Wrap<LocalDate> c) {}

	@Override()
	public SchoolSession setSessionStartDate(String o) {
		if(StringUtils.contains(o, " "))
			o = StringUtils.substringBefore(o, " ");
		try {
			return super.setSessionStartDate(o);
		} catch (Exception e) {
			setSessionStartDate(LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(o)));
			return this;
		}
	}

	@Override()
	public SchoolSession setSessionEndDate(String o) {
		if(StringUtils.contains(o, " "))
			o = StringUtils.substringBefore(o, " ");
		try {
			return super.setSessionEndDate(o);
		} catch (Exception e) {
			setSessionEndDate(LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(o)));
			return this;
		}
	}

	protected void _sessionEndDate(Wrap<LocalDate> c) {}

	protected void _sessionShortName(Wrap<String> c) {
		String o;

		if(BooleanUtils.isTrue(seasonSummer))
			o = String.format("%s - %s summer session at %s", strSessionStartDate(), strSessionEndDate(), schoolCompleteName);
		if(BooleanUtils.isTrue(seasonWinter))
			o = String.format("%s - %s school session at %s", strSessionStartDate(), strSessionEndDate(), schoolCompleteName);
		else
			o = String.format("%s - %s session at %s", strSessionStartDate(), strSessionEndDate(), schoolCompleteName);

		c.o(o);
	}

	protected void _sessionCompleteName(Wrap<String> c) {
		String o;

		if(BooleanUtils.isTrue(seasonSummer))
			o = String.format("%s - %s summer session at %s", strSessionStartDate(), strSessionEndDate(), schoolCompleteName);
		if(BooleanUtils.isTrue(seasonWinter))
			o = String.format("%s - %s school session at %s", strSessionStartDate(), strSessionEndDate(), schoolCompleteName);
		else
			o = String.format("%s - %s session at %s", strSessionStartDate(), strSessionEndDate(), schoolCompleteName);

		c.o(o);
	}

	@Override()
	protected void  _objectTitle(Wrap<String> c) {
		c.o(sessionCompleteName);
	}
}
