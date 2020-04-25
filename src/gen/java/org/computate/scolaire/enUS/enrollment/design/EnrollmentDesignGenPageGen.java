package org.computate.scolaire.enUS.enrollment.design;

import org.computate.scolaire.enUS.wrap.Wrap;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.enUS.enrollment.design.EnrollmentDesign;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.search.SearchList;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.design.EnrollmentDesignGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EnrollmentDesignGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentDesignGenPage.class);

	//////////////////////////
	// listEnrollmentDesign //
	//////////////////////////

	/**	L'entité « listEnrollmentDesign »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<EnrollmentDesign> listEnrollmentDesign;
	@JsonIgnore
	public Wrap<SearchList<EnrollmentDesign>> listEnrollmentDesignWrap = new Wrap<SearchList<EnrollmentDesign>>().p(this).c(SearchList.class).var("listEnrollmentDesign").o(listEnrollmentDesign);

	/**	<br/>L'entité « listEnrollmentDesign »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.design.EnrollmentDesignGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listEnrollmentDesign">Trouver l'entité listEnrollmentDesign dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listEnrollmentDesign(Wrap<SearchList<EnrollmentDesign>> c);

	public SearchList<EnrollmentDesign> getListEnrollmentDesign() {
		return listEnrollmentDesign;
	}

	public void setListEnrollmentDesign(SearchList<EnrollmentDesign> listEnrollmentDesign) {
		this.listEnrollmentDesign = listEnrollmentDesign;
		this.listEnrollmentDesignWrap.alreadyInitialized = true;
	}
	protected EnrollmentDesignGenPage listEnrollmentDesignInit() {
		if(!listEnrollmentDesignWrap.alreadyInitialized) {
			_listEnrollmentDesign(listEnrollmentDesignWrap);
			if(listEnrollmentDesign == null)
				setListEnrollmentDesign(listEnrollmentDesignWrap.o);
		}
		if(listEnrollmentDesign != null)
			listEnrollmentDesign.initDeepForClass(siteRequest_);
		listEnrollmentDesignWrap.alreadyInitialized(true);
		return (EnrollmentDesignGenPage)this;
	}

	//////////////////////
	// enrollmentDesign //
	//////////////////////

	/**	L'entité « enrollmentDesign »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected EnrollmentDesign enrollmentDesign;
	@JsonIgnore
	public Wrap<EnrollmentDesign> enrollmentDesignWrap = new Wrap<EnrollmentDesign>().p(this).c(EnrollmentDesign.class).var("enrollmentDesign").o(enrollmentDesign);

	/**	<br/>L'entité « enrollmentDesign »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.design.EnrollmentDesignGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDesign">Trouver l'entité enrollmentDesign dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentDesign(Wrap<EnrollmentDesign> c);

	public EnrollmentDesign getEnrollmentDesign() {
		return enrollmentDesign;
	}

	public void setEnrollmentDesign(EnrollmentDesign enrollmentDesign) {
		this.enrollmentDesign = enrollmentDesign;
		this.enrollmentDesignWrap.alreadyInitialized = true;
	}
	protected EnrollmentDesignGenPage enrollmentDesignInit() {
		if(!enrollmentDesignWrap.alreadyInitialized) {
			_enrollmentDesign(enrollmentDesignWrap);
			if(enrollmentDesign == null)
				setEnrollmentDesign(enrollmentDesignWrap.o);
		}
		if(enrollmentDesign != null)
			enrollmentDesign.initDeepForClass(siteRequest_);
		enrollmentDesignWrap.alreadyInitialized(true);
		return (EnrollmentDesignGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedEnrollmentDesignGenPage = false;

	public EnrollmentDesignGenPage initDeepEnrollmentDesignGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedEnrollmentDesignGenPage) {
			alreadyInitializedEnrollmentDesignGenPage = true;
			initDeepEnrollmentDesignGenPage();
		}
		return (EnrollmentDesignGenPage)this;
	}

	public void initDeepEnrollmentDesignGenPage() {
		initEnrollmentDesignGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initEnrollmentDesignGenPage() {
		listEnrollmentDesignInit();
		enrollmentDesignInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepEnrollmentDesignGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestEnrollmentDesignGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listEnrollmentDesign != null)
			listEnrollmentDesign.setSiteRequest_(siteRequest_);
		if(enrollmentDesign != null)
			enrollmentDesign.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestEnrollmentDesignGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainEnrollmentDesignGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainEnrollmentDesignGenPage(String var) {
		EnrollmentDesignGenPage oEnrollmentDesignGenPage = (EnrollmentDesignGenPage)this;
		switch(var) {
			case "listEnrollmentDesign":
				return oEnrollmentDesignGenPage.listEnrollmentDesign;
			case "enrollmentDesign":
				return oEnrollmentDesignGenPage.enrollmentDesign;
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
				o = attributeEnrollmentDesignGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeEnrollmentDesignGenPage(String var, Object val) {
		EnrollmentDesignGenPage oEnrollmentDesignGenPage = (EnrollmentDesignGenPage)this;
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
					o = defineEnrollmentDesignGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineEnrollmentDesignGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsEnrollmentDesignGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsEnrollmentDesignGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEnrollmentDesignGenPage();
		super.htmlScript();
	}

	public void htmlScriptEnrollmentDesignGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEnrollmentDesignGenPage();
		super.htmlBody();
	}

	public void htmlBodyEnrollmentDesignGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlEnrollmentDesignGenPage();
		super.html();
	}

	public void htmlEnrollmentDesignGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaEnrollmentDesignGenPage();
		super.htmlMeta();
	}

	public void htmlMetaEnrollmentDesignGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesEnrollmentDesignGenPage();
		super.htmlStyles();
	}

	public void htmlStylesEnrollmentDesignGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleEnrollmentDesignGenPage();
		super.htmlStyle();
	}

	public void htmlStyleEnrollmentDesignGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestEnrollmentDesignGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof EnrollmentDesignGenPage) {
			EnrollmentDesignGenPage original = (EnrollmentDesignGenPage)o;
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
		if(!(o instanceof EnrollmentDesignGenPage))
			return false;
		EnrollmentDesignGenPage that = (EnrollmentDesignGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EnrollmentDesignGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
