package org.computate.scolaire.enUS.design;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import java.util.Arrays;
import org.computate.scolaire.enUS.cluster.Cluster;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.enUS.design.PageDesign;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.computate.scolaire.enUS.search.SearchList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class DesignDisplayGenPageGen<DEV> extends ClusterPage {

	////////////////////
	// listPageDesign //
	////////////////////

	/**	L'entité « listPageDesign »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<PageDesign> listPageDesign;
	@JsonIgnore
	public Wrap<SearchList<PageDesign>> listPageDesignWrap = new Wrap<SearchList<PageDesign>>().p(this).c(SearchList.class).var("listPageDesign").o(listPageDesign);

	/**	<br/>L'entité « listPageDesign »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listPageDesign">Trouver l'entité listPageDesign dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listPageDesign(Wrap<SearchList<PageDesign>> c);

	public SearchList<PageDesign> getListPageDesign() {
		return listPageDesign;
	}

	public void setListPageDesign(SearchList<PageDesign> listPageDesign) {
		this.listPageDesign = listPageDesign;
		this.listPageDesignWrap.alreadyInitialized = true;
	}
	protected DesignDisplayGenPage listPageDesignInit() {
		if(!listPageDesignWrap.alreadyInitialized) {
			_listPageDesign(listPageDesignWrap);
			if(listPageDesign == null)
				setListPageDesign(listPageDesignWrap.o);
		}
		if(listPageDesign != null)
			listPageDesign.initDeepForClass(siteRequest_);
		listPageDesignWrap.alreadyInitialized(true);
		return (DesignDisplayGenPage)this;
	}

	////////////////
	// pageDesign //
	////////////////

	/**	L'entité « pageDesign »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected PageDesign pageDesign;
	@JsonIgnore
	public Wrap<PageDesign> pageDesignWrap = new Wrap<PageDesign>().p(this).c(PageDesign.class).var("pageDesign").o(pageDesign);

	/**	<br/>L'entité « pageDesign »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageDesign">Trouver l'entité pageDesign dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _pageDesign(Wrap<PageDesign> c);

	public PageDesign getPageDesign() {
		return pageDesign;
	}

	public void setPageDesign(PageDesign pageDesign) {
		this.pageDesign = pageDesign;
		this.pageDesignWrap.alreadyInitialized = true;
	}
	protected DesignDisplayGenPage pageDesignInit() {
		if(!pageDesignWrap.alreadyInitialized) {
			_pageDesign(pageDesignWrap);
			if(pageDesign == null)
				setPageDesign(pageDesignWrap.o);
		}
		if(pageDesign != null)
			pageDesign.initDeepForClass(siteRequest_);
		pageDesignWrap.alreadyInitialized(true);
		return (DesignDisplayGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedDesignDisplayGenPage = false;

	public DesignDisplayGenPage initDeepDesignDisplayGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedDesignDisplayGenPage) {
			alreadyInitializedDesignDisplayGenPage = true;
			initDeepDesignDisplayGenPage();
		}
		return (DesignDisplayGenPage)this;
	}

	public void initDeepDesignDisplayGenPage() {
		initDesignDisplayGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initDesignDisplayGenPage() {
		listPageDesignInit();
		pageDesignInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepDesignDisplayGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestDesignDisplayGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listPageDesign != null)
			listPageDesign.setSiteRequest_(siteRequest_);
		if(pageDesign != null)
			pageDesign.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestDesignDisplayGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainDesignDisplayGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainDesignDisplayGenPage(String var) {
		DesignDisplayGenPage oDesignDisplayGenPage = (DesignDisplayGenPage)this;
		switch(var) {
			case "listPageDesign":
				return oDesignDisplayGenPage.listPageDesign;
			case "pageDesign":
				return oDesignDisplayGenPage.pageDesign;
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
				o = attributeDesignDisplayGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeDesignDisplayGenPage(String var, Object val) {
		DesignDisplayGenPage oDesignDisplayGenPage = (DesignDisplayGenPage)this;
		switch(var) {
			default:
				return super.attributeClusterPage(var, val);
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
					o = defineDesignDisplayGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineDesignDisplayGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsDesignDisplayGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsDesignDisplayGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptDesignDisplayGenPage();
		super.htmlScript();
	}

	public void htmlScriptDesignDisplayGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyDesignDisplayGenPage();
		super.htmlBody();
	}

	public void htmlBodyDesignDisplayGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlDesignDisplayGenPage();
		super.html();
	}

	public void htmlDesignDisplayGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaDesignDisplayGenPage();
		super.htmlMeta();
	}

	public void htmlMetaDesignDisplayGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesDesignDisplayGenPage();
		super.htmlStyles();
	}

	public void htmlStylesDesignDisplayGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleDesignDisplayGenPage();
		super.htmlStyle();
	}

	public void htmlStyleDesignDisplayGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestDesignDisplayGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof DesignDisplayGenPage) {
			DesignDisplayGenPage original = (DesignDisplayGenPage)o;
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
		if(!(o instanceof DesignDisplayGenPage))
			return false;
		DesignDisplayGenPage that = (DesignDisplayGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("DesignDisplayGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}