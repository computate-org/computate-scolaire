package org.computate.scolaire.enUS.year;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.school.School;

public class SchoolYear extends SchoolYearGen<Cluster> {

	protected void _schoolKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _yearKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _enrollmentKeys(List<Long> o) {}

	protected void _seasonKeys(List<Long> o) {}

	protected void _yearStart(Wrap<LocalDate> c) {}

	protected void _yearEnd(Wrap<LocalDate> c) {
		if(yearStart != null)
			c.o(yearStart.plusYears(1));
	}

	protected void _school(Wrap<School> c) {
	}

	protected void _yearShortName(Wrap<String> c) {
		String o = "année";

		if(yearStart != null && yearEnd != null)
			o = String.format("%d-%d", yearStart.getYear(), yearEnd.getYear());
		else if(yearStart != null)
			o = String.format("%d year", yearStart.getYear());
		else if(yearEnd != null)
			o = String.format("%d year", yearEnd.getYear());

		c.o(o);
	}

	protected void _yearCompleteName(Wrap<String> c) {
		String o = "année";

		if(yearStart != null && yearEnd != null)
			o = String.format("%d-%d school year", yearStart.getYear(), yearEnd.getYear());
		else if(yearStart != null)
			o = String.format("%d school year", yearStart.getYear());
		else if(yearEnd != null)
			o = String.format("%d school year", yearEnd.getYear());

		if(school != null)
			o += String.format(" at %s", school.getSchoolName());

		c.o(o);
	}

	protected void _objectSuggest(Wrap<String> c) { 
		c.o(yearCompleteName);
	}
}
