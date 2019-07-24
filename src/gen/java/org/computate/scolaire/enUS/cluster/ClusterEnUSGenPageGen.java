package org.computate.scolaire.enUS.cluster;

import java.util.Objects;
import org.computate.scolaire.enUS.writer.AllWriter;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.page.PageLayout;
import org.apache.commons.text.StringEscapeUtils;
import java.lang.String;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import org.apache.commons.lang3.StringUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.ClusterEnUSGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ClusterEnUSGenPageGen<DEV> extends PageLayout {

	/////////////////
	// listCluster //
	/////////////////

	/**	L'entité « listCluster »
	 *	 is defined as null before being initialized. 
	 */
	protected SearchList<Cluster> listCluster;
	public Wrap<SearchList<Cluster>> listClusterWrap = new Wrap<SearchList<Cluster>>().p(this).c(SearchList.class).var("listCluster").o(listCluster);

	/**	<br/>L'entité « listCluster »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.ClusterEnUSGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listCluster">Trouver l'entité listCluster dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listCluster(Wrap<SearchList<Cluster>> c);

	public SearchList<Cluster> getListCluster() {
		return listCluster;
	}

	public void setListCluster(SearchList<Cluster> listCluster) {
		this.listCluster = listCluster;
		this.listClusterWrap.alreadyInitialized = true;
	}
	protected ClusterEnUSGenPage listClusterInit() {
		if(!listClusterWrap.alreadyInitialized) {
			_listCluster(listClusterWrap);
			if(listCluster == null)
				setListCluster(listClusterWrap.o);
		}
		if(listCluster != null)
			listCluster.initDeepForClass(null);
		listClusterWrap.alreadyInitialized(true);
		return (ClusterEnUSGenPage)this;
	}

	/////////////
	// cluster //
	/////////////

	/**	L'entité « cluster »
	 *	 is defined as null before being initialized. 
	 */
	protected Cluster cluster;
	public Wrap<Cluster> clusterWrap = new Wrap<Cluster>().p(this).c(Cluster.class).var("cluster").o(cluster);

	/**	<br/>L'entité « cluster »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.ClusterEnUSGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:cluster">Trouver l'entité cluster dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _cluster(Wrap<Cluster> c);

	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
		this.clusterWrap.alreadyInitialized = true;
	}
	protected ClusterEnUSGenPage clusterInit() {
		if(!clusterWrap.alreadyInitialized) {
			_cluster(clusterWrap);
			if(cluster == null)
				setCluster(clusterWrap.o);
		}
		if(cluster != null)
			cluster.initDeepForClass(null);
		clusterWrap.alreadyInitialized(true);
		return (ClusterEnUSGenPage)this;
	}

	////////////////////
	// pageUriCluster //
	////////////////////

	/**	L'entité « pageUriCluster »
	 *	 is defined as null before being initialized. 
	 */
	protected String pageUriCluster;
	public Wrap<String> pageUriClusterWrap = new Wrap<String>().p(this).c(String.class).var("pageUriCluster").o(pageUriCluster);

	/**	<br/>L'entité « pageUriCluster »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.ClusterEnUSGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageUriCluster">Trouver l'entité pageUriCluster dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageUriCluster(Wrap<String> c);

	public String getPageUriCluster() {
		return pageUriCluster;
	}

	public void setPageUriCluster(String pageUriCluster) {
		this.pageUriCluster = pageUriCluster;
		this.pageUriClusterWrap.alreadyInitialized = true;
	}
	protected ClusterEnUSGenPage pageUriClusterInit() {
		if(!pageUriClusterWrap.alreadyInitialized) {
			_pageUriCluster(pageUriClusterWrap);
			if(pageUriCluster == null)
				setPageUriCluster(pageUriClusterWrap.o);
		}
		pageUriClusterWrap.alreadyInitialized(true);
		return (ClusterEnUSGenPage)this;
	}

	public String solrPageUriCluster() {
		return pageUriCluster;
	}

	public String strPageUriCluster() {
		return pageUriCluster == null ? "" : pageUriCluster;
	}

	public String nomAffichagePageUriCluster() {
		return null;
	}

	public String htmTooltipPageUriCluster() {
		return null;
	}

	public String htmPageUriCluster() {
		return pageUriCluster == null ? "" : StringEscapeUtils.escapeHtml4(strPageUriCluster());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedClusterEnUSGenPage = false;

	public ClusterEnUSGenPage initDeepClusterEnUSGenPage(SiteRequestEnUS siteRequest_) {
		if(!alreadyInitializedClusterEnUSGenPage) {
			alreadyInitializedClusterEnUSGenPage = true;
			initDeepClusterEnUSGenPage();
		}
		return (ClusterEnUSGenPage)this;
	}

	public void initDeepClusterEnUSGenPage() {
		super.initDeepPageLayout(siteRequest_);
		initClusterEnUSGenPage();
	}

	public void initClusterEnUSGenPage() {
		listClusterInit();
		clusterInit();
		pageUriClusterInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepClusterEnUSGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainClusterEnUSGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainClusterEnUSGenPage(String var) {
		ClusterEnUSGenPage oClusterEnUSGenPage = (ClusterEnUSGenPage)this;
		switch(var) {
			case "listCluster":
				return oClusterEnUSGenPage.listCluster;
			case "cluster":
				return oClusterEnUSGenPage.cluster;
			case "pageUriCluster":
				return oClusterEnUSGenPage.pageUriCluster;
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
				o = attributeClusterEnUSGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeClusterEnUSGenPage(String var, Object val) {
		ClusterEnUSGenPage oClusterEnUSGenPage = (ClusterEnUSGenPage)this;
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
					o = defineClusterEnUSGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineClusterEnUSGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definePageLayout(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsClusterEnUSGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsClusterEnUSGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptClusterEnUSGenPage();
		super.htmlScript();
	}

	public void htmlScriptClusterEnUSGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyClusterEnUSGenPage();
		super.htmlBody();
	}

	public void htmlBodyClusterEnUSGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlClusterEnUSGenPage();
		super.html();
	}

	public void htmlClusterEnUSGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaClusterEnUSGenPage();
		super.htmlMeta();
	}

	public void htmlMetaClusterEnUSGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesClusterEnUSGenPage();
		super.htmlStyles();
	}

	public void htmlStylesClusterEnUSGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleClusterEnUSGenPage();
		super.htmlStyle();
	}

	public void htmlStyleClusterEnUSGenPage() {
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
		if(!(o instanceof ClusterEnUSGenPage))
			return false;
		ClusterEnUSGenPage that = (ClusterEnUSGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("ClusterEnUSGenPage {");
		sb.append(" }");
		return sb.toString();
	}
}
