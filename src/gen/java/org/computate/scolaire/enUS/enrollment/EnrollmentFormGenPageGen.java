package org.computate.scolaire.enUS.enrollment;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.apache.commons.text.StringEscapeUtils;
import org.computate.scolaire.enUS.request.patch.PatchRequest;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentFormGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class EnrollmentFormGenPageGen<DEV> extends ClusterPage {

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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentFormGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:listSchoolEnrollment">Trouver l'entité listSchoolEnrollment dans Solr</a>
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
	protected EnrollmentFormGenPage listSchoolEnrollmentInit() {
		if(!listSchoolEnrollmentWrap.alreadyInitialized) {
			_listSchoolEnrollment(listSchoolEnrollmentWrap);
			if(listSchoolEnrollment == null)
				setListSchoolEnrollment(listSchoolEnrollmentWrap.o);
		}
		if(listSchoolEnrollment != null)
			listSchoolEnrollment.initDeepForClass(siteRequest_);
		listSchoolEnrollmentWrap.alreadyInitialized(true);
		return (EnrollmentFormGenPage)this;
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentFormGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolEnrollment">Trouver l'entité schoolEnrollment dans Solr</a>
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
	protected EnrollmentFormGenPage schoolEnrollmentInit() {
		if(!schoolEnrollmentWrap.alreadyInitialized) {
			_schoolEnrollment(schoolEnrollmentWrap);
			if(schoolEnrollment == null)
				setSchoolEnrollment(schoolEnrollmentWrap.o);
		}
		if(schoolEnrollment != null)
			schoolEnrollment.initDeepForClass(siteRequest_);
		schoolEnrollmentWrap.alreadyInitialized(true);
		return (EnrollmentFormGenPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedEnrollmentFormGenPage = false;

	public EnrollmentFormGenPage initDeepEnrollmentFormGenPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedEnrollmentFormGenPage) {
			alreadyInitializedEnrollmentFormGenPage = true;
			initDeepEnrollmentFormGenPage();
		}
		return (EnrollmentFormGenPage)this;
	}

	public void initDeepEnrollmentFormGenPage() {
		initEnrollmentFormGenPage();
		super.initDeepClusterPage(siteRequest_);
	}

	public void initEnrollmentFormGenPage() {
		listSchoolEnrollmentInit();
		schoolEnrollmentInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepEnrollmentFormGenPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestEnrollmentFormGenPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestClusterPage(siteRequest_);
		if(listSchoolEnrollment != null)
			listSchoolEnrollment.setSiteRequest_(siteRequest_);
		if(schoolEnrollment != null)
			schoolEnrollment.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestEnrollmentFormGenPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainEnrollmentFormGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainEnrollmentFormGenPage(String var) {
		EnrollmentFormGenPage oEnrollmentFormGenPage = (EnrollmentFormGenPage)this;
		switch(var) {
			case "listSchoolEnrollment":
				return oEnrollmentFormGenPage.listSchoolEnrollment;
			case "schoolEnrollment":
				return oEnrollmentFormGenPage.schoolEnrollment;
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
				o = attributeEnrollmentFormGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeEnrollmentFormGenPage(String var, Object val) {
		EnrollmentFormGenPage oEnrollmentFormGenPage = (EnrollmentFormGenPage)this;
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
					o = defineEnrollmentFormGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineEnrollmentFormGenPage(String var, String val) {
		switch(var) {
			default:
				return super.defineClusterPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsEnrollmentFormGenPage();
		super.htmlScripts();
	}

	public void htmlScriptsEnrollmentFormGenPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptEnrollmentFormGenPage();
		super.htmlScript();
	}

	public void htmlScriptEnrollmentFormGenPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyEnrollmentFormGenPage();
		super.htmlBody();
	}

	public void htmlBodyEnrollmentFormGenPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlEnrollmentFormGenPage();
		super.html();
	}

	public void htmlEnrollmentFormGenPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaEnrollmentFormGenPage();
		super.htmlMeta();
	}

	public void htmlMetaEnrollmentFormGenPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesEnrollmentFormGenPage();
		super.htmlStyles();
	}

	public void htmlStylesEnrollmentFormGenPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleEnrollmentFormGenPage();
		super.htmlStyle();
	}

	public void htmlStyleEnrollmentFormGenPage() {
	}

	//////////////////
	// patchRequest //
	//////////////////

	public void patchRequestEnrollmentFormGenPage() {
		PatchRequest patchRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getPatchRequest_).orElse(null);
		EnrollmentFormGenPage original = (EnrollmentFormGenPage)Optional.ofNullable(patchRequest).map(PatchRequest::getOriginal).orElse(null);
		if(original != null) {
			super.patchRequestClusterPage();
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
		if(!(o instanceof EnrollmentFormGenPage))
			return false;
		EnrollmentFormGenPage that = (EnrollmentFormGenPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("EnrollmentFormGenPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
