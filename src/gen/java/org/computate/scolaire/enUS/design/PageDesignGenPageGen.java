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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesignGenPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class PageDesignGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(PageDesignGenPage.class);

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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesignGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listPageDesign">Find the entity listPageDesign in Solr</a>
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
	protected PageDesignGenPage listPageDesignInit() {
		if(!listPageDesignWrap.alreadyInitialized) {
			_listPageDesign(listPageDesignWrap);
			if(listPageDesign == null)
				setListPageDesign(listPageDesignWrap.o);
		}
		if(listPageDesign != null)
			listPageDesign.initDeepForClass(siteRequest_);
		listPageDesignWrap.alreadyInitialized(true);
		return (PageDesignGenPage)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.PageDesignGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageDesign_">Find the entity pageDesign_ in Solr</a>
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
	protected PageDesignGenPage pageDesign_Init() {
		if(!pageDesign_Wrap.alreadyInitialized) {
			_pageDesign_(pageDesign_Wrap);
			if(pageDesign_ == null)
				setPageDesign_(pageDesign_Wrap.o);
		}
		pageDesign_Wrap.alreadyInitialized(true);
		return (PageDesignGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedPageDesignGenPage = false;

	public PageDesignGenPage initDeepPageDesignGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedPageDesignGenPage) {
			alreadyInitializedPageDesignGenPage = true;
			initDeepPageDesignGenPage();
		}
		return (PageDesignGenPage)this;
	}

	public void initDeepPageDesignGenPage() {
		initPageDesignGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initPageDesignGenPage() {
		listPageDesignInit();
		pageDesign_Init();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepPageDesignGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestPageDesignGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listPageDesign != null)
			listPageDesign.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestPageDesignGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainPageDesignGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainPageDesignGenPage(String var) {
		PageDesignGenPage oPageDesignGenPage = (PageDesignGenPage)this;
		switch(var) {
			case "listPageDesign":
				return oPageDesignGenPage.listPageDesign;
			case "pageDesign_":
				return oPageDesignGenPage.pageDesign_;
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
				o = attributePageDesignGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributePageDesignGenPage(String var, Object val) {
		PageDesignGenPage oPageDesignGenPage = (PageDesignGenPage)this;
		switch(var) {
			default:
				return super.attributeClusterPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetPageDesignGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetPageDesignGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSetClusterPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrPageDesignGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrPageDesignGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrPageDesignGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrPageDesignGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqPageDesignGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqPageDesignGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
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
					o = definePageDesignGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definePageDesignGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsPageDesignGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsPageDesignGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptPageDesignGenPage();
		super.htmlScript();
	}

	public void htmlScriptPageDesignGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyPageDesignGenPage();
		super.htmlBody();
	}

	public void htmlBodyPageDesignGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlPageDesignGenPage();
		super.html();
	}

	public void htmlPageDesignGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaPageDesignGenPage();
		super.htmlMeta();
	}

	public void htmlMetaPageDesignGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesPageDesignGenPage();
		super.htmlStyles();
	}

	public void htmlStylesPageDesignGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStylePageDesignGenPage();
		super.htmlStyle();
	}

	public void htmlStylePageDesignGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestPageDesignGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof PageDesignGenPage) {
			PageDesignGenPage original = (PageDesignGenPage)o;
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
		if(!(o instanceof PageDesignGenPage))
			return false;
		PageDesignGenPage that = (PageDesignGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("PageDesignGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
