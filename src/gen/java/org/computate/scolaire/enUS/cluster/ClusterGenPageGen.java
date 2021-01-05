package org.computate.scolaire.enUS.cluster;

import org.computate.scolaire.enUS.wrap.Wrap;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.search.SearchList;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.page.PageLayout;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.ClusterGenPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class ClusterGenPageGen<DEV> extends PageLayout {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ClusterGenPage.class);

	/////////////////
	// listCluster //
	/////////////////

	/**	 The entity listCluster
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<Cluster> listCluster;
	@JsonIgnore
	public Wrap<SearchList<Cluster>> listClusterWrap = new Wrap<SearchList<Cluster>>().p(this).c(SearchList.class).var("listCluster").o(listCluster);

	/**	<br/> The entity listCluster
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.ClusterGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listCluster">Find the entity listCluster in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _listCluster(Wrap<SearchList<Cluster>> c);

	public SearchList<Cluster> getListCluster() {
		return listCluster;
	}

	public void setListCluster(SearchList<Cluster> listCluster) {
		this.listCluster = listCluster;
		this.listClusterWrap.alreadyInitialized = true;
	}
	public static SearchList<Cluster> staticSetListCluster(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	//////////////
	// cluster_ //
	//////////////

	/**	 The entity cluster_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Cluster cluster_;
	@JsonIgnore
	public Wrap<Cluster> cluster_Wrap = new Wrap<Cluster>().p(this).c(Cluster.class).var("cluster_").o(cluster_);

	/**	<br/> The entity cluster_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.cluster.ClusterGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:cluster_">Find the entity cluster_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _cluster_(Wrap<Cluster> c);

	public Cluster getCluster_() {
		return cluster_;
	}

	public void setCluster_(Cluster cluster_) {
		this.cluster_ = cluster_;
		this.cluster_Wrap.alreadyInitialized = true;
	}
	public static Cluster staticSetCluster_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected ClusterGenPage cluster_Init() {
		if(!cluster_Wrap.alreadyInitialized) {
			_cluster_(cluster_Wrap);
			if(cluster_ == null)
				setCluster_(cluster_Wrap.o);
		}
		cluster_Wrap.alreadyInitialized(true);
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
		cluster_Init();
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
			case "cluster_":
				return oClusterGenPage.cluster_;
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

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetClusterGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetClusterGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrClusterGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrClusterGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return PageLayout.staticSolrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrClusterGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrClusterGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return PageLayout.staticSolrStrPageLayout(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqClusterGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqClusterGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return PageLayout.staticSolrFqPageLayout(entityVar,  siteRequest_, o);
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
