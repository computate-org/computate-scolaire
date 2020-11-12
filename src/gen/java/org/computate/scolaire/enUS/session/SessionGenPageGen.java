package org.computate.scolaire.enUS.session;

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
import org.computate.scolaire.enUS.session.SchoolSession;
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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.session.SessionGenPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class SessionGenPageGen<DEV> extends ClusterPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SessionGenPage.class);

	///////////////////////
	// listSchoolSession //
	///////////////////////

	/**	 The entity listSchoolSession
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolSession> listSchoolSession;
	@JsonIgnore
	public Wrap<SearchList<SchoolSession>> listSchoolSessionWrap = new Wrap<SearchList<SchoolSession>>().p(this).c(SearchList.class).var("listSchoolSession").o(listSchoolSession);

	/**	<br/> The entity listSchoolSession
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.session.SessionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolSession">Find the entity listSchoolSession in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _listSchoolSession(Wrap<SearchList<SchoolSession>> c);

	public SearchList<SchoolSession> getListSchoolSession() {
		return listSchoolSession;
	}

	public void setListSchoolSession(SearchList<SchoolSession> listSchoolSession) {
		this.listSchoolSession = listSchoolSession;
		this.listSchoolSessionWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolSession> staticSetListSchoolSession(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected SessionGenPage listSchoolSessionInit() {
		if(!listSchoolSessionWrap.alreadyInitialized) {
			_listSchoolSession(listSchoolSessionWrap);
			if(listSchoolSession == null)
				setListSchoolSession(listSchoolSessionWrap.o);
		}
		if(listSchoolSession != null)
			listSchoolSession.initDeepForClass(siteRequest_);
		listSchoolSessionWrap.alreadyInitialized(true);
		return (SessionGenPage)this;
	}

	////////////////////
	// schoolSession_ //
	////////////////////

	/**	 The entity schoolSession_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolSession schoolSession_;
	@JsonIgnore
	public Wrap<SchoolSession> schoolSession_Wrap = new Wrap<SchoolSession>().p(this).c(SchoolSession.class).var("schoolSession_").o(schoolSession_);

	/**	<br/> The entity schoolSession_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.session.SessionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSession_">Find the entity schoolSession_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolSession_(Wrap<SchoolSession> c);

	public SchoolSession getSchoolSession_() {
		return schoolSession_;
	}

	public void setSchoolSession_(SchoolSession schoolSession_) {
		this.schoolSession_ = schoolSession_;
		this.schoolSession_Wrap.alreadyInitialized = true;
	}
	public static SchoolSession staticSetSchoolSession_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected SessionGenPage schoolSession_Init() {
		if(!schoolSession_Wrap.alreadyInitialized) {
			_schoolSession_(schoolSession_Wrap);
			if(schoolSession_ == null)
				setSchoolSession_(schoolSession_Wrap.o);
		}
		schoolSession_Wrap.alreadyInitialized(true);
		return (SessionGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSessionGenPage = false;

	public SessionGenPage initDeepSessionGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSessionGenPage) {
			alreadyInitializedSessionGenPage = true;
			initDeepSessionGenPage();
		}
		return (SessionGenPage)this;
	}

	public void initDeepSessionGenPage() {
		initSessionGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initSessionGenPage() {
		listSchoolSessionInit();
		schoolSession_Init();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSessionGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSessionGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolSession != null)
			listSchoolSession.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSessionGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSessionGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSessionGenPage(String var) {
		SessionGenPage oSessionGenPage = (SessionGenPage)this;
		switch(var) {
			case "listSchoolSession":
				return oSessionGenPage.listSchoolSession;
			case "schoolSession_":
				return oSessionGenPage.schoolSession_;
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
				o = attributeSessionGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSessionGenPage(String var, Object val) {
		SessionGenPage oSessionGenPage = (SessionGenPage)this;
		switch(var) {
			default:
				return super.attributeClusterPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetSessionGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSessionGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSetClusterPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrSessionGenPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrSessionGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrSessionGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrSessionGenPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return ClusterPage.staticSolrStrClusterPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqSessionGenPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqSessionGenPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
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
					o = defineSessionGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSessionGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsSessionGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsSessionGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptSessionGenPage();
		super.htmlScript();
	}

	public void htmlScriptSessionGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodySessionGenPage();
		super.htmlBody();
	}

	public void htmlBodySessionGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlSessionGenPage();
		super.html();
	}

	public void htmlSessionGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaSessionGenPage();
		super.htmlMeta();
	}

	public void htmlMetaSessionGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesSessionGenPage();
		super.htmlStyles();
	}

	public void htmlStylesSessionGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleSessionGenPage();
		super.htmlStyle();
	}

	public void htmlStyleSessionGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSessionGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SessionGenPage) {
			SessionGenPage original = (SessionGenPage)o;
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
		if(!(o instanceof SessionGenPage))
			return false;
		SessionGenPage that = (SessionGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SessionGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
