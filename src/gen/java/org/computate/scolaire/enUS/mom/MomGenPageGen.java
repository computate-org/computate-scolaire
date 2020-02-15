package org.computate.scolaire.enUS.mom;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.enUS.request.api.ApiRequest;
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
import org.computate.scolaire.enUS.mom.SchoolMom;
import java.util.Optional;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.mom.MomGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class MomGenPageGen<DEV> extends ClusterPage {

	///////////////////
	// listSchoolMom //
	///////////////////

	/**	L'entité « listSchoolMom »
	 *	 is defined as null before being initialized. 
	 */
	protected SearchList<SchoolMom> listSchoolMom;
	@JsonIgnore
	public Wrap<SearchList<SchoolMom>> listSchoolMomWrap = new Wrap<SearchList<SchoolMom>>().p(this).c(SearchList.class).var("listSchoolMom").o(listSchoolMom);

	/**	<br/>L'entité « listSchoolMom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.mom.MomGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolMom">Trouver l'entité listSchoolMom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listSchoolMom(Wrap<SearchList<SchoolMom>> c);

	public SearchList<SchoolMom> getListSchoolMom() {
		return listSchoolMom;
	}

	public void setListSchoolMom(SearchList<SchoolMom> listSchoolMom) {
		this.listSchoolMom = listSchoolMom;
		this.listSchoolMomWrap.alreadyInitialized = true;
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

	///////////////
	// schoolMom //
	///////////////

	/**	L'entité « schoolMom »
	 *	 is defined as null before being initialized. 
	 */
	protected SchoolMom schoolMom;
	@JsonIgnore
	public Wrap<SchoolMom> schoolMomWrap = new Wrap<SchoolMom>().p(this).c(SchoolMom.class).var("schoolMom").o(schoolMom);

	/**	<br/>L'entité « schoolMom »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.mom.MomGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolMom">Trouver l'entité schoolMom dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolMom(Wrap<SchoolMom> c);

	public SchoolMom getSchoolMom() {
		return schoolMom;
	}

	public void setSchoolMom(SchoolMom schoolMom) {
		this.schoolMom = schoolMom;
		this.schoolMomWrap.alreadyInitialized = true;
	}
	protected MomGenPage schoolMomInit() {
		if(!schoolMomWrap.alreadyInitialized) {
			_schoolMom(schoolMomWrap);
			if(schoolMom == null)
				setSchoolMom(schoolMomWrap.o);
		}
		if(schoolMom != null)
			schoolMom.initDeepForClass(siteRequest_);
		schoolMomWrap.alreadyInitialized(true);
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
		schoolMomInit();
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
		if(schoolMom != null)
			schoolMom.setSiteRequest_(siteRequest_);
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
			case "schoolMom":
				return oMomGenPage.schoolMom;
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
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
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
