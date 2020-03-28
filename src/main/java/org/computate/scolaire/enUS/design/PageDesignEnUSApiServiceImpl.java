package org.computate.scolaire.enUS.design;

import org.apache.solr.client.solrj.util.ClientUtils;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.search.SearchList;

/**
 * Translate: false
 **/
public class PageDesignEnUSApiServiceImpl extends PageDesignEnUSGenApiServiceImpl {

	public PageDesignEnUSApiServiceImpl(SiteContextEnUS siteContext) {
		super(siteContext);
	}

	@Override public void aSearchPageDesignVar(String classApiUriMethod, SearchList<PageDesign> searchList, String var, String value) {
		if ("/page".equals(classApiUriMethod)) {
			if("design".equals(var))
				searchList.addFilterQuery("pageDesignCompleteName_indexed_string:" + ClientUtils.escapeQueryChars(value));
		}
		super.aSearchPageDesignVar(classApiUriMethod, searchList, var, value);
	}
	@Override public void aSearchPageDesignFq(String classApiUriMethod, SearchList<PageDesign> searchList, String entityVar, String valueIndexed, String varIndexed) {
		if ("/page".equals(classApiUriMethod)) {
			// skip
		}
		else {
			super.aSearchPageDesignFq(classApiUriMethod, searchList, entityVar, valueIndexed, varIndexed);
		}
	}
}
