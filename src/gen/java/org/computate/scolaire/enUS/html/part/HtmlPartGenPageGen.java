package org.computate.scolaire.enUS.html.part;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.computate.scolaire.enUS.search.SearchList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPartGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class HtmlPartGenPageGen<DEV> extends ClusterPage {

	//////////////////
	// listHtmlPart //
	//////////////////

	/**	L'entité « listHtmlPart »
	 *	 is defined as null before being initialized. 
	 */
	protected SearchList<HtmlPart> listHtmlPart;
	@JsonIgnore
	public Wrap<SearchList<HtmlPart>> listHtmlPartWrap = new Wrap<SearchList<HtmlPart>>().p(this).c(SearchList.class).var("listHtmlPart").o(listHtmlPart);

	/**	<br/>L'entité « listHtmlPart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPartGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listHtmlPart">Trouver l'entité listHtmlPart dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listHtmlPart(Wrap<SearchList<HtmlPart>> c);

	public SearchList<HtmlPart> getListHtmlPart() {
		return listHtmlPart;
	}

	public void setListHtmlPart(SearchList<HtmlPart> listHtmlPart) {
		this.listHtmlPart = listHtmlPart;
		this.listHtmlPartWrap.alreadyInitialized = true;
	}
	protected HtmlPartGenPage listHtmlPartInit() {
		if(!listHtmlPartWrap.alreadyInitialized) {
			_listHtmlPart(listHtmlPartWrap);
			if(listHtmlPart == null)
				setListHtmlPart(listHtmlPartWrap.o);
		}
		if(listHtmlPart != null)
			listHtmlPart.initDeepForClass(siteRequest_);
		listHtmlPartWrap.alreadyInitialized(true);
		return (HtmlPartGenPage)this;
	}

	//////////////
	// htmlPart //
	//////////////

	/**	L'entité « htmlPart »
	 *	 is defined as null before being initialized. 
	 */
	protected HtmlPart htmlPart;
	@JsonIgnore
	public Wrap<HtmlPart> htmlPartWrap = new Wrap<HtmlPart>().p(this).c(HtmlPart.class).var("htmlPart").o(htmlPart);

	/**	<br/>L'entité « htmlPart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.html.part.HtmlPartGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlPart">Trouver l'entité htmlPart dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _htmlPart(Wrap<HtmlPart> c);

	public HtmlPart getHtmlPart() {
		return htmlPart;
	}

	public void setHtmlPart(HtmlPart htmlPart) {
		this.htmlPart = htmlPart;
		this.htmlPartWrap.alreadyInitialized = true;
	}
	protected HtmlPartGenPage htmlPartInit() {
		if(!htmlPartWrap.alreadyInitialized) {
			_htmlPart(htmlPartWrap);
			if(htmlPart == null)
				setHtmlPart(htmlPartWrap.o);
		}
		if(htmlPart != null)
			htmlPart.initDeepForClass(siteRequest_);
		htmlPartWrap.alreadyInitialized(true);
		return (HtmlPartGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedHtmlPartGenPage = false;

	public HtmlPartGenPage initDeepHtmlPartGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedHtmlPartGenPage) {
			alreadyInitializedHtmlPartGenPage = true;
			initDeepHtmlPartGenPage();
		}
		return (HtmlPartGenPage)this;
	}

	public void initDeepHtmlPartGenPage() {
		initHtmlPartGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initHtmlPartGenPage() {
		listHtmlPartInit();
		htmlPartInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepHtmlPartGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestHtmlPartGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listHtmlPart != null)
			listHtmlPart.setSiteRequest_(siteRequest_);
		if(htmlPart != null)
			htmlPart.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestHtmlPartGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainHtmlPartGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainHtmlPartGenPage(String var) {
		HtmlPartGenPage oHtmlPartGenPage = (HtmlPartGenPage)this;
		switch(var) {
			case "listHtmlPart":
				return oHtmlPartGenPage.listHtmlPart;
			case "htmlPart":
				return oHtmlPartGenPage.htmlPart;
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
				o = attributeHtmlPartGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeHtmlPartGenPage(String var, Object val) {
		HtmlPartGenPage oHtmlPartGenPage = (HtmlPartGenPage)this;
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
					o = defineHtmlPartGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineHtmlPartGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsHtmlPartGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsHtmlPartGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptHtmlPartGenPage();
		super.htmlScript();
	}

	public void htmlScriptHtmlPartGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyHtmlPartGenPage();
		super.htmlBody();
	}

	public void htmlBodyHtmlPartGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlHtmlPartGenPage();
		super.html();
	}

	public void htmlHtmlPartGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaHtmlPartGenPage();
		super.htmlMeta();
	}

	public void htmlMetaHtmlPartGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesHtmlPartGenPage();
		super.htmlStyles();
	}

	public void htmlStylesHtmlPartGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleHtmlPartGenPage();
		super.htmlStyle();
	}

	public void htmlStyleHtmlPartGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestHtmlPartGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = (HtmlPartGenPage)Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof HtmlPartGenPage) {
			HtmlPartGenPage original = (HtmlPartGenPage)o;
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
		if(!(o instanceof HtmlPartGenPage))
			return false;
		HtmlPartGenPage that = (HtmlPartGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("HtmlPartGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
