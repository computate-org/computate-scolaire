package org.computate.scolaire.enUS.guardian;

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
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.search.SearchList;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.GuardianGenPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class GuardianGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(GuardianGenPage.class);

	////////////////////////
	// listSchoolGuardian //
	////////////////////////

	/**	 The entity listSchoolGuardian
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolGuardian> listSchoolGuardian;
	@JsonIgnore
	public Wrap<SearchList<SchoolGuardian>> listSchoolGuardianWrap = new Wrap<SearchList<SchoolGuardian>>().p(this).c(SearchList.class).var("listSchoolGuardian").o(listSchoolGuardian);

	/**	<br/> The entity listSchoolGuardian
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.GuardianGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolGuardian">Find the entity listSchoolGuardian in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _listSchoolGuardian(Wrap<SearchList<SchoolGuardian>> c);

	public SearchList<SchoolGuardian> getListSchoolGuardian() {
		return listSchoolGuardian;
	}

	public void setListSchoolGuardian(SearchList<SchoolGuardian> listSchoolGuardian) {
		this.listSchoolGuardian = listSchoolGuardian;
		this.listSchoolGuardianWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolGuardian> staticSetListSchoolGuardian(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected GuardianGenPage listSchoolGuardianInit() {
		if(!listSchoolGuardianWrap.alreadyInitialized) {
			_listSchoolGuardian(listSchoolGuardianWrap);
			if(listSchoolGuardian == null)
				setListSchoolGuardian(listSchoolGuardianWrap.o);
		}
		if(listSchoolGuardian != null)
			listSchoolGuardian.initDeepForClass(siteRequest_);
		listSchoolGuardianWrap.alreadyInitialized(true);
		return (GuardianGenPage)this;
	}

	/////////////////////
	// schoolGuardian_ //
	/////////////////////

	/**	 The entity schoolGuardian_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolGuardian schoolGuardian_;
	@JsonIgnore
	public Wrap<SchoolGuardian> schoolGuardian_Wrap = new Wrap<SchoolGuardian>().p(this).c(SchoolGuardian.class).var("schoolGuardian_").o(schoolGuardian_);

	/**	<br/> The entity schoolGuardian_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.GuardianGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolGuardian_">Find the entity schoolGuardian_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolGuardian_(Wrap<SchoolGuardian> c);

	public SchoolGuardian getSchoolGuardian_() {
		return schoolGuardian_;
	}

	public void setSchoolGuardian_(SchoolGuardian schoolGuardian_) {
		this.schoolGuardian_ = schoolGuardian_;
		this.schoolGuardian_Wrap.alreadyInitialized = true;
	}
	public static SchoolGuardian staticSetSchoolGuardian_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected GuardianGenPage schoolGuardian_Init() {
		if(!schoolGuardian_Wrap.alreadyInitialized) {
			_schoolGuardian_(schoolGuardian_Wrap);
			if(schoolGuardian_ == null)
				setSchoolGuardian_(schoolGuardian_Wrap.o);
		}
		schoolGuardian_Wrap.alreadyInitialized(true);
		return (GuardianGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedGuardianGenPage = false;

	public GuardianGenPage initDeepGuardianGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedGuardianGenPage) {
			alreadyInitializedGuardianGenPage = true;
			initDeepGuardianGenPage();
		}
		return (GuardianGenPage)this;
	}

	public void initDeepGuardianGenPage() {
		initGuardianGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initGuardianGenPage() {
		listSchoolGuardianInit();
		schoolGuardian_Init();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepGuardianGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestGuardianGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolGuardian != null)
			listSchoolGuardian.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestGuardianGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainGuardianGenPage(v);
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
	public Object obtainGuardianGenPage(String var) {
		GuardianGenPage oGuardianGenPage = (GuardianGenPage)this;
		switch(var) {
			case "listSchoolGuardian":
				return oGuardianGenPage.listSchoolGuardian;
			case "schoolGuardian_":
				return oGuardianGenPage.schoolGuardian_;
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
				o = attributeGuardianGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeGuardianGenPage(String var, Object val) {
		GuardianGenPage oGuardianGenPage = (GuardianGenPage)this;
		switch(var) {
			default:
				return super.attributeClusterPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetGuardianGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetGuardianGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSetClusterPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrGuardianGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrGuardianGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrGuardianGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrGuardianGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqGuardianGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqGuardianGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
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
					o = defineGuardianGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineGuardianGenPage(String var, String val) {
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
					o = defineGuardianGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineGuardianGenPage(String var, Object val) {
		switch(var.toLowerCase()) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsGuardianGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsGuardianGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptGuardianGenPage();
		super.htmlScript();
	}

	public void htmlScriptGuardianGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyGuardianGenPage();
		super.htmlBody();
	}

	public void htmlBodyGuardianGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlGuardianGenPage();
		super.html();
	}

	public void htmlGuardianGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaGuardianGenPage();
		super.htmlMeta();
	}

	public void htmlMetaGuardianGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesGuardianGenPage();
		super.htmlStyles();
	}

	public void htmlStylesGuardianGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleGuardianGenPage();
		super.htmlStyle();
	}

	public void htmlStyleGuardianGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestGuardianGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof GuardianGenPage) {
			GuardianGenPage original = (GuardianGenPage)o;
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
		if(!(o instanceof GuardianGenPage))
			return false;
		GuardianGenPage that = (GuardianGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("GuardianGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
