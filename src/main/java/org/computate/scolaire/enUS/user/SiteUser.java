package org.computate.scolaire.enUS.user;

import java.util.List;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.wrap.Wrap;

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

	protected void _paymentKeys(List<Long> o) {}

	protected void _userName(Wrap<String> c) {
		String o = siteRequest_.getUserName();
		c.o(o);
	}

	protected void _userEmail(Wrap<String> c) {
	}

	protected void _userFirstName(Wrap<String> c) {
		String o = siteRequest_.getUserFirstName();
		c.o(o);
	}

	protected void _userLastName(Wrap<String> c) {
		String o = siteRequest_.getUserLastName();
		c.o(o);
	}

	protected void _userFullName(Wrap<String> c) {
		String o = siteRequest_.getUserFullName();
		c.o(o);
	}

	protected void _userSite(Wrap<String> c) {
	}

	protected void _customerProfileId1(Wrap<String> c) {
	}

	protected void _customerProfileId2(Wrap<String> c) {
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

	public void  htmlBody() {
		super.htmlBody();
	}
}
