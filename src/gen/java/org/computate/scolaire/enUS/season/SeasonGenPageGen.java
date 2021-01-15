package org.computate.scolaire.enUS.season;

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
import org.computate.scolaire.enUS.season.SchoolSeason;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SeasonGenPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class SeasonGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SeasonGenPage.class);

	//////////////////////
	// listSchoolSeason //
	//////////////////////

	/**	 The entity listSchoolSeason
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolSeason> listSchoolSeason;
	@JsonIgnore
	public Wrap<SearchList<SchoolSeason>> listSchoolSeasonWrap = new Wrap<SearchList<SchoolSeason>>().p(this).c(SearchList.class).var("listSchoolSeason").o(listSchoolSeason);

	/**	<br/> The entity listSchoolSeason
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SeasonGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolSeason">Find the entity listSchoolSeason in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _listSchoolSeason(Wrap<SearchList<SchoolSeason>> c);

	public SearchList<SchoolSeason> getListSchoolSeason() {
		return listSchoolSeason;
	}

	public void setListSchoolSeason(SearchList<SchoolSeason> listSchoolSeason) {
		this.listSchoolSeason = listSchoolSeason;
		this.listSchoolSeasonWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolSeason> staticSetListSchoolSeason(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected SeasonGenPage listSchoolSeasonInit() {
		if(!listSchoolSeasonWrap.alreadyInitialized) {
			_listSchoolSeason(listSchoolSeasonWrap);
			if(listSchoolSeason == null)
				setListSchoolSeason(listSchoolSeasonWrap.o);
		}
		if(listSchoolSeason != null)
			listSchoolSeason.initDeepForClass(siteRequest_);
		listSchoolSeasonWrap.alreadyInitialized(true);
		return (SeasonGenPage)this;
	}

	///////////////////
	// schoolSeason_ //
	///////////////////

	/**	 The entity schoolSeason_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolSeason schoolSeason_;
	@JsonIgnore
	public Wrap<SchoolSeason> schoolSeason_Wrap = new Wrap<SchoolSeason>().p(this).c(SchoolSeason.class).var("schoolSeason_").o(schoolSeason_);

	/**	<br/> The entity schoolSeason_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.season.SeasonGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSeason_">Find the entity schoolSeason_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolSeason_(Wrap<SchoolSeason> c);

	public SchoolSeason getSchoolSeason_() {
		return schoolSeason_;
	}

	public void setSchoolSeason_(SchoolSeason schoolSeason_) {
		this.schoolSeason_ = schoolSeason_;
		this.schoolSeason_Wrap.alreadyInitialized = true;
	}
	public static SchoolSeason staticSetSchoolSeason_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected SeasonGenPage schoolSeason_Init() {
		if(!schoolSeason_Wrap.alreadyInitialized) {
			_schoolSeason_(schoolSeason_Wrap);
			if(schoolSeason_ == null)
				setSchoolSeason_(schoolSeason_Wrap.o);
		}
		schoolSeason_Wrap.alreadyInitialized(true);
		return (SeasonGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSeasonGenPage = false;

	public SeasonGenPage initDeepSeasonGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSeasonGenPage) {
			alreadyInitializedSeasonGenPage = true;
			initDeepSeasonGenPage();
		}
		return (SeasonGenPage)this;
	}

	public void initDeepSeasonGenPage() {
		initSeasonGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initSeasonGenPage() {
		listSchoolSeasonInit();
		schoolSeason_Init();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSeasonGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSeasonGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolSeason != null)
			listSchoolSeason.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSeasonGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSeasonGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSeasonGenPage(String var) {
		SeasonGenPage oSeasonGenPage = (SeasonGenPage)this;
		switch(var) {
			case "listSchoolSeason":
				return oSeasonGenPage.listSchoolSeason;
			case "schoolSeason_":
				return oSeasonGenPage.schoolSeason_;
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
				o = attributeSeasonGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSeasonGenPage(String var, Object val) {
		SeasonGenPage oSeasonGenPage = (SeasonGenPage)this;
		switch(var) {
			default:
				return super.attributeClusterPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetSeasonGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSeasonGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSetClusterPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrSeasonGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrSeasonGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrSeasonGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrSeasonGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqSeasonGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqSeasonGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
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
					o = defineSeasonGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSeasonGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsSeasonGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsSeasonGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptSeasonGenPage();
		super.htmlScript();
	}

	public void htmlScriptSeasonGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodySeasonGenPage();
		super.htmlBody();
	}

	public void htmlBodySeasonGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlSeasonGenPage();
		super.html();
	}

	public void htmlSeasonGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaSeasonGenPage();
		super.htmlMeta();
	}

	public void htmlMetaSeasonGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesSeasonGenPage();
		super.htmlStyles();
	}

	public void htmlStylesSeasonGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleSeasonGenPage();
		super.htmlStyle();
	}

	public void htmlStyleSeasonGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSeasonGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SeasonGenPage) {
			SeasonGenPage original = (SeasonGenPage)o;
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
		if(!(o instanceof SeasonGenPage))
			return false;
		SeasonGenPage that = (SeasonGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SeasonGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
