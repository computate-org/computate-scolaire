package org.computate.scolaire.enUS.school;

import java.util.List;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;

/**
 * Model: true
 * Api: true
 * Page: true
 * Saved: true
 * Color: pink
 * IconGroup: regular
 * IconName: school
 * Role.enUS: SiteManager
 * ApiUri.enUS: /api/school
 * ApiTag.enUS: School
 * AName.enUS: a school
 * Role.frFR: SiteManager
 * ApiUri.frFR: /api/ecole
 * ApiTag.frFR: Ecole
 * AName.frFR: une école
 * CanonicalName: org.computate.scolaire.frFR.ecole.Ecole
 **/
public class School extends SchoolGen<Cluster> {

	protected void _schoolKey(Wrap<Long> c) {
		c.o(pk);
	}

	protected void _yearKeys(List<Long> o) {}

	protected void _seasonKeys(List<Long> o) {}

	protected void _sessionKeys(List<Long> o) {}

	protected void _ageGroupKeys(List<Long> o) {}

	protected void _blockKeys(List<Long> o) {}

	protected void _childKeys(List<Long> o) {}

	protected void _educationSort(Wrap<Integer> c) {
		c.o(1);
	}

	protected void _schoolSort(Wrap<Integer> c) {
		c.o(1);
	}

	protected void _schoolName(Wrap<String> c) {
	}

	protected void _schoolPhoneNumber(Wrap<String> c) {
	}

	protected void _schoolForm(Wrap<String> c) {
		c.o("main-enrollment-form");
	}

	protected void _schoolNumber(Wrap<Integer> c) {
	}

	protected void _schoolAdministratorName(Wrap<String> c) {
	}

	protected void _schoolEmail(Wrap<String> c) {
	}

	protected void _schoolEmailFrom(Wrap<String> c) {
	}

	protected void _schoolEmailTo(Wrap<String> c) {
	}

	protected void _schoolLocation(Wrap<String> c) {
	}

	protected void _schoolAddress(Wrap<String> c) {
	}

	protected void _schoolShortName(Wrap<String> c) {
		if(schoolLocation != null)
			c.o(schoolLocation);
		else 
			c.o(schoolName);
	}

	protected void _schoolCompleteName(Wrap<String> c) {
		if(schoolLocation != null)
			c.o(String.format("%s in %s", schoolName, schoolLocation));
		else 
			c.o(schoolName);
	}

	@Override()
	protected void  _objectTitle(Wrap<String> c) {
		c.o(schoolCompleteName);
	}
}
