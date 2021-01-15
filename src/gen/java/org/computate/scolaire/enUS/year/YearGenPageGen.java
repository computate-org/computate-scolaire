package org.computate.scolaire.enUS.year;

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
import org.computate.scolaire.enUS.year.SchoolYear;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.YearGenPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class YearGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(YearGenPage.class);

	////////////////////
	// listSchoolYear //
	////////////////////

	/**	 The entity listSchoolYear
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolYear> listSchoolYear;
	@JsonIgnore
	public Wrap<SearchList<SchoolYear>> listSchoolYearWrap = new Wrap<SearchList<SchoolYear>>().p(this).c(SearchList.class).var("listSchoolYear").o(listSchoolYear);

	/**	<br/> The entity listSchoolYear
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.YearGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolYear">Find the entity listSchoolYear in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _listSchoolYear(Wrap<SearchList<SchoolYear>> c);

	public SearchList<SchoolYear> getListSchoolYear() {
		return listSchoolYear;
	}

	public void setListSchoolYear(SearchList<SchoolYear> listSchoolYear) {
		this.listSchoolYear = listSchoolYear;
		this.listSchoolYearWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolYear> staticSetListSchoolYear(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected YearGenPage listSchoolYearInit() {
		if(!listSchoolYearWrap.alreadyInitialized) {
			_listSchoolYear(listSchoolYearWrap);
			if(listSchoolYear == null)
				setListSchoolYear(listSchoolYearWrap.o);
		}
		if(listSchoolYear != null)
			listSchoolYear.initDeepForClass(siteRequest_);
		listSchoolYearWrap.alreadyInitialized(true);
		return (YearGenPage)this;
	}

	/////////////////
	// schoolYear_ //
	/////////////////

	/**	 The entity schoolYear_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolYear schoolYear_;
	@JsonIgnore
	public Wrap<SchoolYear> schoolYear_Wrap = new Wrap<SchoolYear>().p(this).c(SchoolYear.class).var("schoolYear_").o(schoolYear_);

	/**	<br/> The entity schoolYear_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.YearGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolYear_">Find the entity schoolYear_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolYear_(Wrap<SchoolYear> c);

	public SchoolYear getSchoolYear_() {
		return schoolYear_;
	}

	public void setSchoolYear_(SchoolYear schoolYear_) {
		this.schoolYear_ = schoolYear_;
		this.schoolYear_Wrap.alreadyInitialized = true;
	}
	public static SchoolYear staticSetSchoolYear_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected YearGenPage schoolYear_Init() {
		if(!schoolYear_Wrap.alreadyInitialized) {
			_schoolYear_(schoolYear_Wrap);
			if(schoolYear_ == null)
				setSchoolYear_(schoolYear_Wrap.o);
		}
		schoolYear_Wrap.alreadyInitialized(true);
		return (YearGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedYearGenPage = false;

	public YearGenPage initDeepYearGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedYearGenPage) {
			alreadyInitializedYearGenPage = true;
			initDeepYearGenPage();
		}
		return (YearGenPage)this;
	}

	public void initDeepYearGenPage() {
		initYearGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initYearGenPage() {
		listSchoolYearInit();
		schoolYear_Init();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepYearGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestYearGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolYear != null)
			listSchoolYear.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestYearGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainYearGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainYearGenPage(String var) {
		YearGenPage oYearGenPage = (YearGenPage)this;
		switch(var) {
			case "listSchoolYear":
				return oYearGenPage.listSchoolYear;
			case "schoolYear_":
				return oYearGenPage.schoolYear_;
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
				o = attributeYearGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeYearGenPage(String var, Object val) {
		YearGenPage oYearGenPage = (YearGenPage)this;
		switch(var) {
			default:
				return super.attributeClusterPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetYearGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetYearGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSetClusterPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrYearGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrYearGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrYearGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrYearGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqYearGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqYearGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
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
					o = defineYearGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineYearGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsYearGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsYearGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptYearGenPage();
		super.htmlScript();
	}

	public void htmlScriptYearGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyYearGenPage();
		super.htmlBody();
	}

	public void htmlBodyYearGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlYearGenPage();
		super.html();
	}

	public void htmlYearGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaYearGenPage();
		super.htmlMeta();
	}

	public void htmlMetaYearGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesYearGenPage();
		super.htmlStyles();
	}

	public void htmlStylesYearGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleYearGenPage();
		super.htmlStyle();
	}

	public void htmlStyleYearGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestYearGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof YearGenPage) {
			YearGenPage original = (YearGenPage)o;
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
		if(!(o instanceof YearGenPage))
			return false;
		YearGenPage that = (YearGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("YearGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
