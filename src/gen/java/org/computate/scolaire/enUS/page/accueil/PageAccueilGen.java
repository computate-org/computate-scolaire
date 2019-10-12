package org.computate.scolaire.enUS.page.accueil;

import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.page.PageLayout;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.accueil.PageAccueil&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class PageAccueilGen<DEV> extends PageLayout {

	///////////////
	// pageTitre //
	///////////////

	/**	L'entité « pageTitre »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageTitre;
	@JsonIgnore
	public Wrap<String> pageTitreWrap = new Wrap<String>().p(this).c(String.class).var("pageTitre").o(pageTitre);

	/**	<br/>L'entité « pageTitre »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.accueil.PageAccueil&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageTitre">Trouver l'entité pageTitre dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageTitre(Wrap<String> c);

	public String getPageTitre() {
		return pageTitre;
	}

	public void setPageTitre(String pageTitre) {
		this.pageTitre = pageTitre;
		this.pageTitreWrap.alreadyInitialized = true;
	}
	protected PageAccueil pageTitreInit() {
		if(!pageTitreWrap.alreadyInitialized) {
			_pageTitre(pageTitreWrap);
			if(pageTitre == null)
				setPageTitre(pageTitreWrap.o);
		}
		pageTitreWrap.alreadyInitialized(true);
		return (PageAccueil)this;
	}

	public String solrPageTitre() {
		return pageTitre;
	}

	public String strPageTitre() {
		return pageTitre == null ? "" : pageTitre;
	}

	public String jsonPageTitre() {
		return pageTitre == null ? "" : pageTitre;
	}

	public String nomAffichagePageTitre() {
		return null;
	}

	public String htmTooltipPageTitre() {
		return null;
	}

	public String htmPageTitre() {
		return pageTitre == null ? "" : StringEscapeUtils.escapeHtml4(strPageTitre());
	}

	/////////////
	// pageUri //
	/////////////

	/**	L'entité « pageUri »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUri;
	@JsonIgnore
	public Wrap<String> pageUriWrap = new Wrap<String>().p(this).c(String.class).var("pageUri").o(pageUri);

	/**	<br/>L'entité « pageUri »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.page.accueil.PageAccueil&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUri">Trouver l'entité pageUri dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUri(Wrap<String> c);

	public String getPageUri() {
		return pageUri;
	}

	public void setPageUri(String pageUri) {
		this.pageUri = pageUri;
		this.pageUriWrap.alreadyInitialized = true;
	}
	protected PageAccueil pageUriInit() {
		if(!pageUriWrap.alreadyInitialized) {
			_pageUri(pageUriWrap);
			if(pageUri == null)
				setPageUri(pageUriWrap.o);
		}
		pageUriWrap.alreadyInitialized(true);
		return (PageAccueil)this;
	}

	public String solrPageUri() {
		return pageUri;
	}

	public String strPageUri() {
		return pageUri == null ? "" : pageUri;
	}

	public String jsonPageUri() {
		return pageUri == null ? "" : pageUri;
	}

	public String nomAffichagePageUri() {
		return null;
	}

	public String htmTooltipPageUri() {
		return null;
	}

	public String htmPageUri() {
		return pageUri == null ? "" : StringEscapeUtils.escapeHtml4(strPageUri());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedPageAccueil = false;

	public PageAccueil initDeepPageAccueil(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedPageAccueil) {
			alreadyInitializedPageAccueil = true;
			initDeepPageAccueil();
		}
		return (PageAccueil)this;
	}

	public void initDeepPageAccueil() {
		super.initDeepPageLayout(siteRequest_);
		initPageAccueil();
	}

	public void initPageAccueil() {
		pageTitreInit();
		pageUriInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepPageAccueil(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestPageAccueil(SiteRequestEnUS siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestPageAccueil(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainPageAccueil(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainPageAccueil(String var) {
		PageAccueil oPageAccueil = (PageAccueil)this;
		switch(var) {
			case "pageTitre":
				return oPageAccueil.pageTitre;
			case "pageUri":
				return oPageAccueil.pageUri;
			default:
				return super.obtainPageLayout(var);
		}
	}

	///////////////
	// attribute //
	///////////////

	@Override public boolean attributeForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attributePageAccueil(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributePageAccueil(String var, Object val) {
		PageAccueil oPageAccueil = (PageAccueil)this;
		switch(var) {
			default:
				return super.attributePageLayout(var, val);
		}
	}

	/////////////
	// define //
	/////////////

	@Override public boolean defineForClass(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definePageAccueil(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definePageAccueil(String var, String val) {
		switch(var) {
			default:
				return super.definePageLayout(var, val);
		}
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlPageAccueil();
		super.html();
	}

	public void htmlPageAccueil() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaPageAccueil();
		super.htmlMeta();
	}

	public void htmlMetaPageAccueil() {
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsPageAccueil();
		super.htmlScripts();
	}

	public void htmlScriptsPageAccueil() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptPageAccueil();
		super.htmlScript();
	}

	public void htmlScriptPageAccueil() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesPageAccueil();
		super.htmlStyles();
	}

	public void htmlStylesPageAccueil() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStylePageAccueil();
		super.htmlStyle();
	}

	public void htmlStylePageAccueil() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyPageAccueil();
		super.htmlBody();
	}

	public void htmlBodyPageAccueil() {
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode());
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof PageAccueil))
			return false;
		PageAccueil that = (PageAccueil)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PageAccueil { ");
		sb.append(" }");
		return sb.toString();
	}
}
