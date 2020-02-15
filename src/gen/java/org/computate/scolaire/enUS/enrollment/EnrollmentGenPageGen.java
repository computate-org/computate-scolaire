package org.computate.scolaire.enUS.enrollment;

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
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EnrollmentGenPageGen<DEV> extends ClusterPage {

	//////////////////////////
	// listSchoolEnrollment //
	//////////////////////////

	/**	L'entité « listSchoolEnrollment »
	 *	 is defined as null before being initialized. 
	 */
	protected SearchList<SchoolEnrollment> listSchoolEnrollment;
	@JsonIgnore
	public Wrap<SearchList<SchoolEnrollment>> listSchoolEnrollmentWrap = new Wrap<SearchList<SchoolEnrollment>>().p(this).c(SearchList.class).var("listSchoolEnrollment").o(listSchoolEnrollment);

	/**	<br/>L'entité « listSchoolEnrollment »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolEnrollment">Trouver l'entité listSchoolEnrollment dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listSchoolEnrollment(Wrap<SearchList<SchoolEnrollment>> c);

	public SearchList<SchoolEnrollment> getListSchoolEnrollment() {
		return listSchoolEnrollment;
	}

	public void setListSchoolEnrollment(SearchList<SchoolEnrollment> listSchoolEnrollment) {
		this.listSchoolEnrollment = listSchoolEnrollment;
		this.listSchoolEnrollmentWrap.alreadyInitialized = true;
	}
	protected EnrollmentGenPage listSchoolEnrollmentInit() {
		if(!listSchoolEnrollmentWrap.alreadyInitialized) {
			_listSchoolEnrollment(listSchoolEnrollmentWrap);
			if(listSchoolEnrollment == null)
				setListSchoolEnrollment(listSchoolEnrollmentWrap.o);
		}
		if(listSchoolEnrollment != null)
			listSchoolEnrollment.initDeepForClass(siteRequest_);
		listSchoolEnrollmentWrap.alreadyInitialized(true);
		return (EnrollmentGenPage)this;
	}

	//////////////////////
	// schoolEnrollment //
	//////////////////////

	/**	L'entité « schoolEnrollment »
	 *	 is defined as null before being initialized. 
	 */
	protected SchoolEnrollment schoolEnrollment;
	@JsonIgnore
	public Wrap<SchoolEnrollment> schoolEnrollmentWrap = new Wrap<SchoolEnrollment>().p(this).c(SchoolEnrollment.class).var("schoolEnrollment").o(schoolEnrollment);

	/**	<br/>L'entité « schoolEnrollment »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolEnrollment">Trouver l'entité schoolEnrollment dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolEnrollment(Wrap<SchoolEnrollment> c);

	public SchoolEnrollment getSchoolEnrollment() {
		return schoolEnrollment;
	}

	public void setSchoolEnrollment(SchoolEnrollment schoolEnrollment) {
		this.schoolEnrollment = schoolEnrollment;
		this.schoolEnrollmentWrap.alreadyInitialized = true;
	}
	protected EnrollmentGenPage schoolEnrollmentInit() {
		if(!schoolEnrollmentWrap.alreadyInitialized) {
			_schoolEnrollment(schoolEnrollmentWrap);
			if(schoolEnrollment == null)
				setSchoolEnrollment(schoolEnrollmentWrap.o);
		}
		if(schoolEnrollment != null)
			schoolEnrollment.initDeepForClass(siteRequest_);
		schoolEnrollmentWrap.alreadyInitialized(true);
		return (EnrollmentGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedEnrollmentGenPage = false;

	public EnrollmentGenPage initDeepEnrollmentGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedEnrollmentGenPage) {
			alreadyInitializedEnrollmentGenPage = true;
			initDeepEnrollmentGenPage();
		}
		return (EnrollmentGenPage)this;
	}

	public void initDeepEnrollmentGenPage() {
		initEnrollmentGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initEnrollmentGenPage() {
		listSchoolEnrollmentInit();
		schoolEnrollmentInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepEnrollmentGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestEnrollmentGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolEnrollment != null)
			listSchoolEnrollment.setSiteRequest_(siteRequest_);
		if(schoolEnrollment != null)
			schoolEnrollment.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestEnrollmentGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainEnrollmentGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainEnrollmentGenPage(String var) {
		EnrollmentGenPage oEnrollmentGenPage = (EnrollmentGenPage)this;
		switch(var) {
			case "listSchoolEnrollment":
				return oEnrollmentGenPage.listSchoolEnrollment;
			case "schoolEnrollment":
				return oEnrollmentGenPage.schoolEnrollment;
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
				o = attributeEnrollmentGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeEnrollmentGenPage(String var, Object val) {
		EnrollmentGenPage oEnrollmentGenPage = (EnrollmentGenPage)this;
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
					o = defineEnrollmentGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineEnrollmentGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsEnrollmentGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsEnrollmentGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEnrollmentGenPage();
		super.htmlScript();
	}

	public void htmlScriptEnrollmentGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEnrollmentGenPage();
		super.htmlBody();
	}

	public void htmlBodyEnrollmentGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlEnrollmentGenPage();
		super.html();
	}

	public void htmlEnrollmentGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaEnrollmentGenPage();
		super.htmlMeta();
	}

	public void htmlMetaEnrollmentGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesEnrollmentGenPage();
		super.htmlStyles();
	}

	public void htmlStylesEnrollmentGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleEnrollmentGenPage();
		super.htmlStyle();
	}

	public void htmlStyleEnrollmentGenPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestEnrollmentGenPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof EnrollmentGenPage) {
			EnrollmentGenPage original = (EnrollmentGenPage)o;
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
		if(!(o instanceof EnrollmentGenPage))
			return false;
		EnrollmentGenPage that = (EnrollmentGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EnrollmentGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
