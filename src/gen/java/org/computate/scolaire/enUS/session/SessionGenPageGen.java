package org.computate.scolaire.enUS.session;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.computate.scolaire.enUS.session.SchoolSession;
import org.apache.commons.text.StringEscapeUtils;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.session.SessionGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SessionGenPageGen<DEV> extends ClusterPage {

	///////////////////////
	// listSchoolSession //
	///////////////////////

	/**	L'entité « listSchoolSession »
	 *	 is defined as null before being initialized. 
	 */
	protected SearchList<SchoolSession> listSchoolSession;
	@JsonIgnore
	public Wrap<SearchList<SchoolSession>> listSchoolSessionWrap = new Wrap<SearchList<SchoolSession>>().p(this).c(SearchList.class).var("listSchoolSession").o(listSchoolSession);

	/**	<br/>L'entité « listSchoolSession »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.session.SessionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolSession">Trouver l'entité listSchoolSession dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listSchoolSession(Wrap<SearchList<SchoolSession>> c);

	public SearchList<SchoolSession> getListSchoolSession() {
		return listSchoolSession;
	}

	public void setListSchoolSession(SearchList<SchoolSession> listSchoolSession) {
		this.listSchoolSession = listSchoolSession;
		this.listSchoolSessionWrap.alreadyInitialized = true;
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

	///////////////////
	// schoolSession //
	///////////////////

	/**	L'entité « schoolSession »
	 *	 is defined as null before being initialized. 
	 */
	protected SchoolSession schoolSession;
	@JsonIgnore
	public Wrap<SchoolSession> schoolSessionWrap = new Wrap<SchoolSession>().p(this).c(SchoolSession.class).var("schoolSession").o(schoolSession);

	/**	<br/>L'entité « schoolSession »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.session.SessionGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSession">Trouver l'entité schoolSession dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolSession(Wrap<SchoolSession> c);

	public SchoolSession getSchoolSession() {
		return schoolSession;
	}

	public void setSchoolSession(SchoolSession schoolSession) {
		this.schoolSession = schoolSession;
		this.schoolSessionWrap.alreadyInitialized = true;
	}
	protected SessionGenPage schoolSessionInit() {
		if(!schoolSessionWrap.alreadyInitialized) {
			_schoolSession(schoolSessionWrap);
			if(schoolSession == null)
				setSchoolSession(schoolSessionWrap.o);
		}
		if(schoolSession != null)
			schoolSession.initDeepForClass(siteRequest_);
		schoolSessionWrap.alreadyInitialized(true);
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
		schoolSessionInit();
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
		if(schoolSession != null)
			schoolSession.setSiteRequest_(siteRequest_);
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
			case "schoolSession":
				return oSessionGenPage.schoolSession;
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
		SessionGenPage original = (SessionGenPage)Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(original != null) {
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
