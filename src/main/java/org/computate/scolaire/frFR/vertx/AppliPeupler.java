package org.computate.scolaire.frFR.vertx; 


import org.computate.scolaire.frFR.config.ConfigSite;
import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;

/**
 * NomCanonique.enUS: org.computate.enUS.academic.vertx.AppPopulate
 */
public class AppliPeupler extends AppliPeuplerGen<Object> {   

	/**
	 * Var.enUS: siteRequest_
	 */
	protected void _requeteSite_(Couverture<RequeteSiteFrFR> c) throws Exception {
	}

	/**
	 * Var.enUS: siteContext
	 */
	protected void _siteContexte(SiteContexteFrFR o) throws Exception {
	}

	/**
	 * Var.enUS: siteConfig
	 * r: siteContexte
	 * r.enUS: siteContext
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 */
	protected void _configSite(Couverture<ConfigSite> c) throws Exception {
		c.o(siteContexte.getConfigSite());
	}

	/**
	 * r: AppliPeupler
	 * r.enUS: AppPopulate
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: peuplerDonnees
	 * r.enUS: populateData
	 */
	public static void main(String[] args) throws Exception {
		AppliPeupler api = new AppliPeupler();
		api.initLoinAppliPeupler();
		api.peuplerDonnees();
	}

	/**
	 * Var.enUS: populateData
	 */
	public void peuplerDonnees() throws Exception {

	}
}
