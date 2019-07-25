package org.computate.scolaire.enUS.vertx;

import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

public class AppOpenApi3 extends AppOpenApi3Gen<AppSwagger2> {

	@Override()
	protected void  _apiVersion(Wrap<String> c) {
		c.o("3.0.0");
	}

	public static void  main(String[] args) {
		AppOpenApi3 api = new AppOpenApi3();
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
		siteRequest.initDeepSiteRequestEnUS();
		SiteContextEnUS siteContext = new SiteContextEnUS();
		siteContext.initDeepSiteContextEnUS(siteRequest);
		api.initDeepAppOpenApi3(siteRequest);
		siteRequest.setSiteContext_(siteContext);
		siteRequest.setSiteConfig_(siteContext.getSiteConfig());
		api.writeOpenApi();
	}
}
