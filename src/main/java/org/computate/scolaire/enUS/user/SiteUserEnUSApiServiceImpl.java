package org.computate.scolaire.enUS.user;

import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.computate.scolaire.enUS.search.SearchList;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

/**
 * Traduire: false
 **/
public class SiteUserEnUSApiServiceImpl extends SiteUserEnUSGenApiServiceImpl {

	public SiteUserEnUSApiServiceImpl(SiteContextEnUS siteContexte) {
		super(siteContexte);
	}

	@Override
	public void aSearchSiteUser(SiteRequestEnUS siteRequest, Boolean populate, Boolean store, String classApiUriMethod,
			Handler<AsyncResult<SearchList<SiteUser>>> eventHandler) {
		SearchList<SiteUser> listSearch = new SearchList<SiteUser>();
		SiteUser siteUser = siteRequest.getSiteUser();
		if(siteUser != null) {
			listSearch.getList().add(siteUser);
		}
		listSearch.setQuery(null);
		listSearch.setC(SiteUser.class);
		listSearch.initDeepForClass(siteRequest);
		eventHandler.handle(Future.succeededFuture(listSearch));
	}
}
