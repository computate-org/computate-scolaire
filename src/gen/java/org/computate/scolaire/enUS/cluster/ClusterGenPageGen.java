package org.computate.scolaire.enUS.cluster;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.computate.scolaire.enUS.search.SearchList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import org.computate.scolaire.enUS.page.PageLayout;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.ClusterGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class ClusterGenPageGen<DEV> extends PageLayout {

	/////////////////
	// listCluster //
	/////////////////

	/**	L'entité « listCluster »
	 *	 is defined as null before being initialized. 
	 */
	protected SearchList<Cluster> listCluster;
	@JsonIgnore
	public Wrap<SearchList<Cluster>> listClusterWrap = new Wrap<SearchList<Cluster>>().p(this).c(SearchList.class).var("listCluster").o(listCluster);

	/**	<br/>L'entité « listCluster »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.ClusterGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listCluster">Trouver l'entité listCluster dans Solr</a>
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
	protected ClusterGenPage listClusterInit() {
		if(!listClusterWrap.alreadyInitialized) {
			_listCluster(listClusterWrap);
			if(listCluster == null)
				setListCluster(listClusterWrap.o);
		}
		if(listCluster != null)
			listCluster.initDeepForClass(siteRequest_);
		listClusterWrap.alreadyInitialized(true);
		return (ClusterGenPage)this;
	}

	/////////////
	// cluster //
	/////////////

	/**	L'entité « cluster »
	 *	 is defined as null before being initialized. 
	 */
	protected Cluster cluster;
	@JsonIgnore
	public Wrap<Cluster> clusterWrap = new Wrap<Cluster>().p(this).c(Cluster.class).var("cluster").o(cluster);

	/**	<br/>L'entité « cluster »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.ClusterGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:cluster">Trouver l'entité cluster dans Solr</a>
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
	protected ClusterGenPage clusterInit() {
		if(!clusterWrap.alreadyInitialized) {
			_cluster(clusterWrap);
			if(cluster == null)
				setCluster(clusterWrap.o);
		}
		if(cluster != null)
			cluster.initDeepForClass(siteRequest_);
		clusterWrap.alreadyInitialized(true);
		return (ClusterGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedClusterGenPage = false;

	public ClusterGenPage initDeepClusterGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedClusterGenPage) {
			alreadyInitializedClusterGenPage = true;
			initDeepClusterGenPage();
		}
		return (ClusterGenPage)this;
	}

	public void initDeepClusterGenPage() {
		initClusterGenPage();
		super.initDeepPageLayout(siteRequest_);
	}

	public void initClusterGenPage() {
		listClusterInit();
		clusterInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepClusterGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestClusterGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestPageLayout(siteRequest_);
		if(listCluster != null)
			listCluster.setSiteRequest_(siteRequest_);
		if(cluster != null)
			cluster.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestClusterGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainClusterGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainClusterGenPage(String var) {
		ClusterGenPage oClusterGenPage = (ClusterGenPage)this;
		switch(var) {
			case "listCluster":
				return oClusterGenPage.listCluster;
			case "cluster":
				return oClusterGenPage.cluster;
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
				o = attributeClusterGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeClusterGenPage(String var, Object val) {
		ClusterGenPage oClusterGenPage = (ClusterGenPage)this;
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
					o = defineClusterGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineClusterGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definePageLayout(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsClusterGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsClusterGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptClusterGenPage();
		super.htmlScript();
	}

	public void htmlScriptClusterGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyClusterGenPage();
		super.htmlBody();
	}

	public void htmlBodyClusterGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlClusterGenPage();
		super.html();
	}

	public void htmlClusterGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaClusterGenPage();
		super.htmlMeta();
	}

	public void htmlMetaClusterGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesClusterGenPage();
		super.htmlStyles();
	}

	public void htmlStylesClusterGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleClusterGenPage();
		super.htmlStyle();
	}

	public void htmlStyleClusterGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestClusterGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof ClusterGenPage) {
			ClusterGenPage original = (ClusterGenPage)o;
			super.apiRequestPageLayout();
		}
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
		if(!(o instanceof ClusterGenPage))
			return false;
		ClusterGenPage that = (ClusterGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("ClusterGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
