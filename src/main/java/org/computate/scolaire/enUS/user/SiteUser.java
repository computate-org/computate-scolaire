package org.computate.scolaire.enUS.user;

import java.util.List;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;

/**
 * Model: true
 * Api: true
 * Page: true
 * Saved: true
 * RoleUser: true
 * Color: gray
 * IconGroup: regular
 * IconName: user-cog
 * Role.enUS: SiteAdmin
 * Role.enUS: SiteAdmin
 * ApiUri.enUS: /api/user
 * ApiTag.enUS: User
 * AName.enUS: a site user
 * Role.frFR: SiteAdmin
 * Role.frFR: SiteAdmin
 * ApiUri.frFR: /api/utilisateur
 * ApiTag.frFR: Utilisateur
 * AName.frFR: un utilisateur du site
 * CanonicalName: org.computate.scolaire.frFR.utilisateur.UtilisateurSite
 **/
public class SiteUser extends SiteUserGen<Cluster> {

	protected void _userKeys(List<Long> l) {
		l.add(pk);
	}

	protected void _enrollmentKeys(List<Long> o) {}

	protected void _enrollments_(Wrap<List<SchoolEnrollment>> o) {}

	protected void _paymentKeys(List<Long> o) {}

	protected void _userName(Wrap<String> c) {
	}

	protected void _userEmail(Wrap<String> c) {
	}

	protected void _userFirstName(Wrap<String> c) {
	}

	protected void _userLastName(Wrap<String> c) {
	}

	protected void _userFullName(Wrap<String> c) {
	}

	protected void _userSite(Wrap<String> c) {
	}

	protected void _customerProfileId1(Wrap<String> c) {
	}

	protected void _customerProfileId2(Wrap<String> c) {
	}

	protected void _customerProfileId3(Wrap<String> c) {
	}

	protected void _customerProfileId4(Wrap<String> c) {
	}

	protected void _customerProfileId5(Wrap<String> c) {
	}

	protected void _customerProfileId6(Wrap<String> c) {
	}

	protected void _customerProfileId7(Wrap<String> c) {
	}

	protected void _customerProfileId8(Wrap<String> c) {
	}

	protected void _customerProfileId9(Wrap<String> c) {
	}

	protected void _customerProfileId10(Wrap<String> c) {
	}

	protected void _userReceiveEmails(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _seeArchived(Wrap<Boolean> c) {
		c.o(false);
	}

	protected void _seeDeleted(Wrap<Boolean> c) {
		c.o(false);
	}

	@Override()
	protected void  _objectTitle(Wrap<String> c) {
		c.o(userFullName + " " + userEmail + " " + userName);
	}
}
