package org.computate.enUS.school.vertx;

import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

public class AppOpenApi3 extends AppOpenApi3Gen<AppliSwagger2> {

	@Override()
	protected void  _apiVersion(Wrap<String> c) {
		c.o("3.0.0");
	}

	public static void  main(String[] args) {
		AppliOpenApi3 api = new AppliOpenApi3();
		RequeteSiteFrFR requeteSite = new RequeteSiteFrFR();
		requeteSite.initDeepRequeteSiteFrFR();
		SiteContexteFrFR siteContexte = new SiteContexteFrFR();
		siteContexte.initDeepSiteContexteFrFR(requeteSite);
		api.initDeepAppliOpenApi3(requeteSite);
		requeteSite.setSiteContexte_(siteContexte);
		requeteSite.setConfigSite_(siteContexte.getConfigSite());
		api.writeOpenApi();
	}
}
