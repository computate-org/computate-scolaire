package org.computate.scolaire.enUS.user;

import java.util.Objects;
import org.computate.scolaire.enUS.cluster.ClusterEnUSPage;
import org.computate.scolaire.enUS.writer.AllWriter;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.user.SiteUser;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.search.SearchList;
import org.apache.commons.text.StringEscapeUtils;
import java.lang.String;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.UtilisateurSiteEnUSGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class UtilisateurSiteEnUSGenPageGen<DEV> extends ClusterEnUSPage {

	//////////////////
	// listSiteUser //
	//////////////////

	/**	L'entité « listSiteUser »
	 *	 is defined as null before being initialized. 
	 */
	protected SearchList<SiteUser> listSiteUser;
	public Wrap<SearchList<SiteUser>> listSiteUserWrap = new Wrap<SearchList<SiteUser>>().p(this).c(SearchList.class).var("listSiteUser").o(listSiteUser);

	/**	<br/>L'entité « listSiteUser »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.UtilisateurSiteEnUSGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSiteUser">Trouver l'entité listSiteUser dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listSiteUser(Wrap<SearchList<SiteUser>> c);

	public SearchList<SiteUser> getListSiteUser() {
		return listSiteUser;
	}

	public void setListSiteUser(SearchList<SiteUser> listSiteUser) {
		this.listSiteUser = listSiteUser;
		this.listSiteUserWrap.alreadyInitialized = true;
	}
	protected UtilisateurSiteEnUSGenPage listSiteUserInit() {
		if(!listSiteUserWrap.alreadyInitialized) {
			_listSiteUser(listSiteUserWrap);
			if(listSiteUser == null)
				setListSiteUser(listSiteUserWrap.o);
		}
		if(listSiteUser != null)
			listSiteUser.initDeepForClass(null);
		listSiteUserWrap.alreadyInitialized(true);
		return (UtilisateurSiteEnUSGenPage)this;
	}

	//////////////
	// siteUser //
	//////////////

	/**	L'entité « siteUser »
	 *	 is defined as null before being initialized. 
	 */
	protected SiteUser siteUser;
	public Wrap<SiteUser> siteUserWrap = new Wrap<SiteUser>().p(this).c(SiteUser.class).var("siteUser").o(siteUser);

	/**	<br/>L'entité « siteUser »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.UtilisateurSiteEnUSGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:siteUser">Trouver l'entité siteUser dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _siteUser(Wrap<SiteUser> c);

	public SiteUser getSiteUser() {
		return siteUser;
	}

	public void setSiteUser(SiteUser siteUser) {
		this.siteUser = siteUser;
		this.siteUserWrap.alreadyInitialized = true;
	}
	protected UtilisateurSiteEnUSGenPage siteUserInit() {
		if(!siteUserWrap.alreadyInitialized) {
			_siteUser(siteUserWrap);
			if(siteUser == null)
				setSiteUser(siteUserWrap.o);
		}
		if(siteUser != null)
			siteUser.initDeepForClass(null);
		siteUserWrap.alreadyInitialized(true);
		return (UtilisateurSiteEnUSGenPage)this;
	}

	/////////////////////
	// pageUriSiteUser //
	/////////////////////

	/**	L'entité « pageUriSiteUser »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUriSiteUser;
	public Wrap<String> pageUriSiteUserWrap = new Wrap<String>().p(this).c(String.class).var("pageUriSiteUser").o(pageUriSiteUser);

	/**	<br/>L'entité « pageUriSiteUser »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.UtilisateurSiteEnUSGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUriSiteUser">Trouver l'entité pageUriSiteUser dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUriSiteUser(Wrap<String> c);

	public String getPageUriSiteUser() {
		return pageUriSiteUser;
	}

	public void setPageUriSiteUser(String pageUriSiteUser) {
		this.pageUriSiteUser = pageUriSiteUser;
		this.pageUriSiteUserWrap.alreadyInitialized = true;
	}
	protected UtilisateurSiteEnUSGenPage pageUriSiteUserInit() {
		if(!pageUriSiteUserWrap.alreadyInitialized) {
			_pageUriSiteUser(pageUriSiteUserWrap);
			if(pageUriSiteUser == null)
				setPageUriSiteUser(pageUriSiteUserWrap.o);
		}
		pageUriSiteUserWrap.alreadyInitialized(true);
		return (UtilisateurSiteEnUSGenPage)this;
	}

	public String solrPageUriSiteUser() {
		return pageUriSiteUser;
	}

	public String strPageUriSiteUser() {
		return pageUriSiteUser == null ? "" : pageUriSiteUser;
	}

	public String nomAffichagePageUriSiteUser() {
		return null;
	}

	public String htmTooltipPageUriSiteUser() {
		return null;
	}

	public String htmPageUriSiteUser() {
		return pageUriSiteUser == null ? "" : StringEscapeUtils.escapeHtml4(strPageUriSiteUser());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedUtilisateurSiteEnUSGenPage = false;

	public UtilisateurSiteEnUSGenPage initDeepUtilisateurSiteEnUSGenPage(SiteRequestEnUS siteRequest_) {
		if(!alreadyInitializedUtilisateurSiteEnUSGenPage) {
			alreadyInitializedUtilisateurSiteEnUSGenPage = true;
			initDeepUtilisateurSiteEnUSGenPage();
		}
		return (UtilisateurSiteEnUSGenPage)this;
	}

	public void initDeepUtilisateurSiteEnUSGenPage() {
		super.initDeepClusterEnUSPage(siteRequest_);
		initUtilisateurSiteEnUSGenPage();
	}

	public void initUtilisateurSiteEnUSGenPage() {
		listSiteUserInit();
		siteUserInit();
		pageUriSiteUserInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepUtilisateurSiteEnUSGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainUtilisateurSiteEnUSGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainUtilisateurSiteEnUSGenPage(String var) {
		UtilisateurSiteEnUSGenPage oUtilisateurSiteEnUSGenPage = (UtilisateurSiteEnUSGenPage)this;
		switch(var) {
			case "listSiteUser":
				return oUtilisateurSiteEnUSGenPage.listSiteUser;
			case "siteUser":
				return oUtilisateurSiteEnUSGenPage.siteUser;
			case "pageUriSiteUser":
				return oUtilisateurSiteEnUSGenPage.pageUriSiteUser;
			default:
				return super.obtainClusterEnUSPage(var);
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
				o = attributeUtilisateurSiteEnUSGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeUtilisateurSiteEnUSGenPage(String var, Object val) {
		UtilisateurSiteEnUSGenPage oUtilisateurSiteEnUSGenPage = (UtilisateurSiteEnUSGenPage)this;
		switch(var) {
			default:
				return super.attributeClusterEnUSPage(var, val);
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
					o = defineUtilisateurSiteEnUSGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineUtilisateurSiteEnUSGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterEnUSPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsUtilisateurSiteEnUSGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsUtilisateurSiteEnUSGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptUtilisateurSiteEnUSGenPage();
		super.htmlScript();
	}

	public void htmlScriptUtilisateurSiteEnUSGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyUtilisateurSiteEnUSGenPage();
		super.htmlBody();
	}

	public void htmlBodyUtilisateurSiteEnUSGenPage() {
		if(siteUser != null)
			siteUser.htmlBody();
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlUtilisateurSiteEnUSGenPage();
		super.html();
	}

	public void htmlUtilisateurSiteEnUSGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaUtilisateurSiteEnUSGenPage();
		super.htmlMeta();
	}

	public void htmlMetaUtilisateurSiteEnUSGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesUtilisateurSiteEnUSGenPage();
		super.htmlStyles();
	}

	public void htmlStylesUtilisateurSiteEnUSGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleUtilisateurSiteEnUSGenPage();
		super.htmlStyle();
	}

	public void htmlStyleUtilisateurSiteEnUSGenPage() {
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
		if(!(o instanceof UtilisateurSiteEnUSGenPage))
			return false;
		UtilisateurSiteEnUSGenPage that = (UtilisateurSiteEnUSGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("UtilisateurSiteEnUSGenPage {");
		sb.append(" }");
		return sb.toString();
	}
}
