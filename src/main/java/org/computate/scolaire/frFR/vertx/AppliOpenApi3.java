package org.computate.scolaire.frFR.vertx;  


import org.computate.scolaire.frFR.contexte.SiteContexteFrFR;
import org.computate.scolaire.frFR.couverture.Couverture;
import org.computate.scolaire.frFR.requete.RequeteSiteFrFR;
/**
 * NomCanonique.enUS: org.computate.scolaire.enUS.vertx.AppOpenApi3
 */
public class AppliOpenApi3 extends AppliOpenApi3Gen<AppliSwagger2> {  

	@Override protected void _apiVersion(Couverture<String> c) {
		c.o("3.0.0");
	}

	/**
	 * r: initLoin
	 * r.enUS: initDeep
	 * r: ecrire
	 * r.enUS: write
	 * r: AppliOpenApi3
	 * r.enUS: AppOpenApi3
	 * r: RequeteSiteFrFR
	 * r.enUS: SiteRequestEnUS
	 * r: SiteContexteFrFR
	 * r.enUS: SiteContextEnUS
	 * r: siteContexte
	 * r.enUS: siteContext
	 * r: requeteSite
	 * r.enUS: siteRequest
	 * r: SiteContexte
	 * r.enUS: SiteContext
	 * r: ConfigSite
	 * r.enUS: SiteConfig
	 */ 
	public static void main(String[] args) {
		AppliOpenApi3 api = new AppliOpenApi3();
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
		requeteSite.initLoinRequeteSiteFrFR();
		SiteContexteFrFR siteContexte = new SiteContexteFrFR();
		siteContexte.initLoinSiteContexteFrFR(requeteSite);
		api.initLoinAppliOpenApi3(requeteSite);
		requeteSite.setSiteContexte_(siteContexte);
		requeteSite.setConfigSite_(siteContexte.getConfigSite());
		api.ecrireOpenApi();
	}
}
