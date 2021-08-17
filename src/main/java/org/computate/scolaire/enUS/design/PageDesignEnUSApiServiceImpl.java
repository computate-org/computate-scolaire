package org.computate.scolaire.enUS.design;

import org.apache.solr.client.solrj.util.ClientUtils;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.search.SearchList;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.web.api.OperationRequest;
import io.vertx.ext.web.api.OperationResponse;

/**
 * Translate: false
 * CanonicalName.frFR: org.computate.scolaire.frFR.design.DesignPageFrFRApiServiceImpl
 **/
public class PageDesignEnUSApiServiceImpl extends PageDesignEnUSGenApiServiceImpl {

	public PageDesignEnUSApiServiceImpl(SiteContextEnUS siteContext) {
		super(siteContext);
	}

	@Override public void aSearchPageDesignVar(String uri, String apiMethod, SearchList<PageDesign> searchList, String var, String value) {
		if ("/page".equals(uri) || "/pdf".equals(uri) || "/email".equals(uri) || "/csv".equals(uri)) {
			if("design".equals(var))
				searchList.addFilterQuery("pageDesignCompleteName_indexed_string:" + ClientUtils.escapeQueryChars(value));
		}
		super.aSearchPageDesignVar(uri, apiMethod, searchList, var, value);
	}
	@Override public void aSearchPageDesignFq(String uri, String apiMethod, SearchList<PageDesign> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if ("/page".equals(uri) || "/pdf".equals(uri) || "/email".equals(uri) || "/csv".equals(uri)) {
			// skip
		}
		else {
			super.aSearchPageDesignFq(uri, apiMethod, searchList, entityVar, valueIndexed, varIndexed);
		}
	}
	@Override
	public void aSearchPageDesignUri(String uri, String apiMethod, SearchList<PageDesign> searchList) {
		if ("/".equals(uri)) {
			searchList.addFilterQuery("pageDesignCompleteName_indexed_string:" + ClientUtils.escapeQueryChars("home page"));
		}
	}

	@Override
	public void designcsvsearchpagePageDesignResponse(SearchList<PageDesign> listPageDesign, Handler<AsyncResult<OperationResponse>> eventHandler) {
		super.designdisplaysearchpagePageDesignResponse(listPageDesign, eventHandler);
	}
}
