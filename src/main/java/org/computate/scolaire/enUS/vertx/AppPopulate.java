package org.computate.scolaire.enUS.vertx;

import org.computate.scolaire.enUS.config.SiteConfig;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**
 * CanonicalName: org.computate.scolaire.frFR.vertx.AppliPeupler
 **/
public class AppPopulate extends AppPopulateGen<Object> {

	protected void _siteRequest_(Wrap<SiteRequestEnUS> c) {
	}

	protected void _siteContext(SiteContextEnUS o) {
	}

	protected void _siteConfig(Wrap<SiteConfig> c) {
		c.o(siteContext.getSiteConfig());
	}

	public static void  main(String[] args) throws Exception, Exception {
		AppPopulate api = new AppPopulate();
		api.initDeepAppPopulate();
		api.populateData();
	}

	public void  populateData() throws Exception, Exception {

	}
}
