package org.computate.scolaire.enUS.design;

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
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.computate.scolaire.enUS.design.PageDesign;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.search.SearchList;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailGenPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class DesignEmailGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(DesignEmailGenPage.class);

	////////////////////
	// listPageDesign //
	////////////////////

	/**	 The entity listPageDesign
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<PageDesign> listPageDesign;
	@JsonIgnore
	public Wrap<SearchList<PageDesign>> listPageDesignWrap = new Wrap<SearchList<PageDesign>>().p(this).c(SearchList.class).var("listPageDesign").o(listPageDesign);

	/**	<br/> The entity listPageDesign
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listPageDesign">Find the entity listPageDesign in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _listPageDesign(Wrap<SearchList<PageDesign>> c);

	public SearchList<PageDesign> getListPageDesign() {
		return listPageDesign;
	}

	public void setListPageDesign(SearchList<PageDesign> listPageDesign) {
		this.listPageDesign = listPageDesign;
		this.listPageDesignWrap.alreadyInitialized = true;
	}
	public static SearchList<PageDesign> staticSetListPageDesign(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailGenPage listPageDesignInit() {
		if(!listPageDesignWrap.alreadyInitialized) {
			_listPageDesign(listPageDesignWrap);
			if(listPageDesign == null)
				setListPageDesign(listPageDesignWrap.o);
		}
		if(listPageDesign != null)
			listPageDesign.initDeepForClass(siteRequest_);
		listPageDesignWrap.alreadyInitialized(true);
		return (DesignEmailGenPage)this;
	}

	/////////////////
	// pageDesign_ //
	/////////////////

	/**	 The entity pageDesign_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected PageDesign pageDesign_;
	@JsonIgnore
	public Wrap<PageDesign> pageDesign_Wrap = new Wrap<PageDesign>().p(this).c(PageDesign.class).var("pageDesign_").o(pageDesign_);

	/**	<br/> The entity pageDesign_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignEmailGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageDesign_">Find the entity pageDesign_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageDesign_(Wrap<PageDesign> c);

	public PageDesign getPageDesign_() {
		return pageDesign_;
	}

	public void setPageDesign_(PageDesign pageDesign_) {
		this.pageDesign_ = pageDesign_;
		this.pageDesign_Wrap.alreadyInitialized = true;
	}
	public static PageDesign staticSetPageDesign_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignEmailGenPage pageDesign_Init() {
		if(!pageDesign_Wrap.alreadyInitialized) {
			_pageDesign_(pageDesign_Wrap);
			if(pageDesign_ == null)
				setPageDesign_(pageDesign_Wrap.o);
		}
		pageDesign_Wrap.alreadyInitialized(true);
		return (DesignEmailGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedDesignEmailGenPage = false;

	public DesignEmailGenPage initDeepDesignEmailGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedDesignEmailGenPage) {
			alreadyInitializedDesignEmailGenPage = true;
			initDeepDesignEmailGenPage();
		}
		return (DesignEmailGenPage)this;
	}

	public void initDeepDesignEmailGenPage() {
		initDesignEmailGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initDesignEmailGenPage() {
		listPageDesignInit();
		pageDesign_Init();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepDesignEmailGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestDesignEmailGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listPageDesign != null)
			listPageDesign.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestDesignEmailGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainDesignEmailGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
			else if(o instanceof Map) {
				Map<?, ?> map = (Map<?, ?>)o;
				o = map.get(v);
			}
		}
		return o;
	}
	public Object obtainDesignEmailGenPage(String var) {
		DesignEmailGenPage oDesignEmailGenPage = (DesignEmailGenPage)this;
		switch(var) {
			case "listPageDesign":
				return oDesignEmailGenPage.listPageDesign;
			case "pageDesign_":
				return oDesignEmailGenPage.pageDesign_;
			default:
				return super.obtainClusterPage(var);
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
				o = attributeDesignEmailGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeDesignEmailGenPage(String var, Object val) {
		DesignEmailGenPage oDesignEmailGenPage = (DesignEmailGenPage)this;
		switch(var) {
			default:
				return super.attributeClusterPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetDesignEmailGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetDesignEmailGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSetClusterPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrDesignEmailGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrDesignEmailGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrDesignEmailGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrDesignEmailGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqDesignEmailGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqDesignEmailGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrFqClusterPage(entityVar,  siteRequest_, o);
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
					o = defineDesignEmailGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineDesignEmailGenPage(String var, String val) {
		switch(var.toLowerCase()) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	@Override public boolean defineForClass(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = defineDesignEmailGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineDesignEmailGenPage(String var, Object val) {
		switch(var.toLowerCase()) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsDesignEmailGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsDesignEmailGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptDesignEmailGenPage();
		super.htmlScript();
	}

	public void htmlScriptDesignEmailGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyDesignEmailGenPage();
		super.htmlBody();
	}

	public void htmlBodyDesignEmailGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlDesignEmailGenPage();
		super.html();
	}

	public void htmlDesignEmailGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaDesignEmailGenPage();
		super.htmlMeta();
	}

	public void htmlMetaDesignEmailGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesDesignEmailGenPage();
		super.htmlStyles();
	}

	public void htmlStylesDesignEmailGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleDesignEmailGenPage();
		super.htmlStyle();
	}

	public void htmlStyleDesignEmailGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestDesignEmailGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof DesignEmailGenPage) {
			DesignEmailGenPage original = (DesignEmailGenPage)o;
			super.apiRequestClusterPage();
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
		if(!(o instanceof DesignEmailGenPage))
			return false;
		DesignEmailGenPage that = (DesignEmailGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("DesignEmailGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
