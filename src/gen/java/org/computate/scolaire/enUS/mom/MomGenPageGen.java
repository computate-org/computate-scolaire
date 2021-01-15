package org.computate.scolaire.enUS.mom;

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
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.search.SearchList;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.mom.SchoolMom;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.mom.MomGenPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class MomGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(MomGenPage.class);

	///////////////////
	// listSchoolMom //
	///////////////////

	/**	 The entity listSchoolMom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolMom> listSchoolMom;
	@JsonIgnore
	public Wrap<SearchList<SchoolMom>> listSchoolMomWrap = new Wrap<SearchList<SchoolMom>>().p(this).c(SearchList.class).var("listSchoolMom").o(listSchoolMom);

	/**	<br/> The entity listSchoolMom
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.mom.MomGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolMom">Find the entity listSchoolMom in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _listSchoolMom(Wrap<SearchList<SchoolMom>> c);

	public SearchList<SchoolMom> getListSchoolMom() {
		return listSchoolMom;
	}

	public void setListSchoolMom(SearchList<SchoolMom> listSchoolMom) {
		this.listSchoolMom = listSchoolMom;
		this.listSchoolMomWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolMom> staticSetListSchoolMom(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected MomGenPage listSchoolMomInit() {
		if(!listSchoolMomWrap.alreadyInitialized) {
			_listSchoolMom(listSchoolMomWrap);
			if(listSchoolMom == null)
				setListSchoolMom(listSchoolMomWrap.o);
		}
		if(listSchoolMom != null)
			listSchoolMom.initDeepForClass(siteRequest_);
		listSchoolMomWrap.alreadyInitialized(true);
		return (MomGenPage)this;
	}

	////////////////
	// schoolMom_ //
	////////////////

	/**	 The entity schoolMom_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolMom schoolMom_;
	@JsonIgnore
	public Wrap<SchoolMom> schoolMom_Wrap = new Wrap<SchoolMom>().p(this).c(SchoolMom.class).var("schoolMom_").o(schoolMom_);

	/**	<br/> The entity schoolMom_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.mom.MomGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolMom_">Find the entity schoolMom_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolMom_(Wrap<SchoolMom> c);

	public SchoolMom getSchoolMom_() {
		return schoolMom_;
	}

	public void setSchoolMom_(SchoolMom schoolMom_) {
		this.schoolMom_ = schoolMom_;
		this.schoolMom_Wrap.alreadyInitialized = true;
	}
	public static SchoolMom staticSetSchoolMom_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected MomGenPage schoolMom_Init() {
		if(!schoolMom_Wrap.alreadyInitialized) {
			_schoolMom_(schoolMom_Wrap);
			if(schoolMom_ == null)
				setSchoolMom_(schoolMom_Wrap.o);
		}
		schoolMom_Wrap.alreadyInitialized(true);
		return (MomGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedMomGenPage = false;

	public MomGenPage initDeepMomGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedMomGenPage) {
			alreadyInitializedMomGenPage = true;
			initDeepMomGenPage();
		}
		return (MomGenPage)this;
	}

	public void initDeepMomGenPage() {
		initMomGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initMomGenPage() {
		listSchoolMomInit();
		schoolMom_Init();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepMomGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestMomGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolMom != null)
			listSchoolMom.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestMomGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainMomGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainMomGenPage(String var) {
		MomGenPage oMomGenPage = (MomGenPage)this;
		switch(var) {
			case "listSchoolMom":
				return oMomGenPage.listSchoolMom;
			case "schoolMom_":
				return oMomGenPage.schoolMom_;
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
				o = attributeMomGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeMomGenPage(String var, Object val) {
		MomGenPage oMomGenPage = (MomGenPage)this;
		switch(var) {
			default:
				return super.attributeClusterPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetMomGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetMomGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSetClusterPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrMomGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrMomGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrMomGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrMomGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqMomGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqMomGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
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
					o = defineMomGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineMomGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsMomGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsMomGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptMomGenPage();
		super.htmlScript();
	}

	public void htmlScriptMomGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyMomGenPage();
		super.htmlBody();
	}

	public void htmlBodyMomGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlMomGenPage();
		super.html();
	}

	public void htmlMomGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaMomGenPage();
		super.htmlMeta();
	}

	public void htmlMetaMomGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesMomGenPage();
		super.htmlStyles();
	}

	public void htmlStylesMomGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleMomGenPage();
		super.htmlStyle();
	}

	public void htmlStyleMomGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestMomGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof MomGenPage) {
			MomGenPage original = (MomGenPage)o;
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
		if(!(o instanceof MomGenPage))
			return false;
		MomGenPage that = (MomGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("MomGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
