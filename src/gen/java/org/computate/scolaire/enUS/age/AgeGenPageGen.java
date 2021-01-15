package org.computate.scolaire.enUS.age;

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
import org.computate.scolaire.enUS.age.SchoolAge;
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
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.search.SearchList;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.age.AgeGenPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class AgeGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(AgeGenPage.class);

	///////////////////
	// listSchoolAge //
	///////////////////

	/**	 The entity listSchoolAge
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolAge> listSchoolAge;
	@JsonIgnore
	public Wrap<SearchList<SchoolAge>> listSchoolAgeWrap = new Wrap<SearchList<SchoolAge>>().p(this).c(SearchList.class).var("listSchoolAge").o(listSchoolAge);

	/**	<br/> The entity listSchoolAge
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.age.AgeGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolAge">Find the entity listSchoolAge in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _listSchoolAge(Wrap<SearchList<SchoolAge>> c);

	public SearchList<SchoolAge> getListSchoolAge() {
		return listSchoolAge;
	}

	public void setListSchoolAge(SearchList<SchoolAge> listSchoolAge) {
		this.listSchoolAge = listSchoolAge;
		this.listSchoolAgeWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolAge> staticSetListSchoolAge(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected AgeGenPage listSchoolAgeInit() {
		if(!listSchoolAgeWrap.alreadyInitialized) {
			_listSchoolAge(listSchoolAgeWrap);
			if(listSchoolAge == null)
				setListSchoolAge(listSchoolAgeWrap.o);
		}
		if(listSchoolAge != null)
			listSchoolAge.initDeepForClass(siteRequest_);
		listSchoolAgeWrap.alreadyInitialized(true);
		return (AgeGenPage)this;
	}

	////////////////
	// schoolAge_ //
	////////////////

	/**	 The entity schoolAge_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolAge schoolAge_;
	@JsonIgnore
	public Wrap<SchoolAge> schoolAge_Wrap = new Wrap<SchoolAge>().p(this).c(SchoolAge.class).var("schoolAge_").o(schoolAge_);

	/**	<br/> The entity schoolAge_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.age.AgeGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAge_">Find the entity schoolAge_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolAge_(Wrap<SchoolAge> c);

	public SchoolAge getSchoolAge_() {
		return schoolAge_;
	}

	public void setSchoolAge_(SchoolAge schoolAge_) {
		this.schoolAge_ = schoolAge_;
		this.schoolAge_Wrap.alreadyInitialized = true;
	}
	public static SchoolAge staticSetSchoolAge_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected AgeGenPage schoolAge_Init() {
		if(!schoolAge_Wrap.alreadyInitialized) {
			_schoolAge_(schoolAge_Wrap);
			if(schoolAge_ == null)
				setSchoolAge_(schoolAge_Wrap.o);
		}
		schoolAge_Wrap.alreadyInitialized(true);
		return (AgeGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedAgeGenPage = false;

	public AgeGenPage initDeepAgeGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedAgeGenPage) {
			alreadyInitializedAgeGenPage = true;
			initDeepAgeGenPage();
		}
		return (AgeGenPage)this;
	}

	public void initDeepAgeGenPage() {
		initAgeGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initAgeGenPage() {
		listSchoolAgeInit();
		schoolAge_Init();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepAgeGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestAgeGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolAge != null)
			listSchoolAge.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestAgeGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainAgeGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainAgeGenPage(String var) {
		AgeGenPage oAgeGenPage = (AgeGenPage)this;
		switch(var) {
			case "listSchoolAge":
				return oAgeGenPage.listSchoolAge;
			case "schoolAge_":
				return oAgeGenPage.schoolAge_;
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
				o = attributeAgeGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeAgeGenPage(String var, Object val) {
		AgeGenPage oAgeGenPage = (AgeGenPage)this;
		switch(var) {
			default:
				return super.attributeClusterPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetAgeGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetAgeGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSetClusterPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrAgeGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrAgeGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrAgeGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrAgeGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqAgeGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqAgeGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
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
					o = defineAgeGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineAgeGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsAgeGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsAgeGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptAgeGenPage();
		super.htmlScript();
	}

	public void htmlScriptAgeGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyAgeGenPage();
		super.htmlBody();
	}

	public void htmlBodyAgeGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlAgeGenPage();
		super.html();
	}

	public void htmlAgeGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaAgeGenPage();
		super.htmlMeta();
	}

	public void htmlMetaAgeGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesAgeGenPage();
		super.htmlStyles();
	}

	public void htmlStylesAgeGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleAgeGenPage();
		super.htmlStyle();
	}

	public void htmlStyleAgeGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestAgeGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof AgeGenPage) {
			AgeGenPage original = (AgeGenPage)o;
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
		if(!(o instanceof AgeGenPage))
			return false;
		AgeGenPage that = (AgeGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("AgeGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
