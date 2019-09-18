package org.computate.scolaire.enUS.enrollment;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.math.MathContext;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.cluster.ClusterPage;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentGenPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:listSchoolEnrollment">Trouver l'entité listSchoolEnrollment dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _listSchoolEnrollment(Wrap<SearchList<SchoolEnrollment>> c);

	public SearchList<SchoolEnrollment> getListSchoolEnrollment() {
		return listSchoolEnrollment;
	}

	public void setListSchoolEnrollment(SearchList<SchoolEnrollment> listSchoolEnrollment) {
		this.listSchoolEnrollment = listSchoolEnrollment;
		this.listSchoolEnrollmentWrap.dejaInitialise = true;
	}
	protected EnrollmentGenPage listSchoolEnrollmentInit() {
		if(!listSchoolEnrollmentWrap.dejaInitialise) {
			_listSchoolEnrollment(listSchoolEnrollmentWrap);
			if(listSchoolEnrollment == null)
				setListSchoolEnrollment(listSchoolEnrollmentWrap.o);
		}
		listSchoolEnrollmentWrap.dejaInitialise(true);
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_frFR_indexed_string:org.computate.scolaire.enUS.enrollment.EnrollmentGenPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_frFR_indexed_string:schoolEnrollment">Trouver l'entité schoolEnrollment dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolEnrollment(Wrap<SchoolEnrollment> c);

	public SchoolEnrollment getSchoolEnrollment() {
		return schoolEnrollment;
	}

	public void setSchoolEnrollment(SchoolEnrollment schoolEnrollment) {
		this.schoolEnrollment = schoolEnrollment;
		this.schoolEnrollmentWrap.dejaInitialise = true;
	}
	protected EnrollmentGenPage schoolEnrollmentInit() {
		if(!schoolEnrollmentWrap.dejaInitialise) {
			_schoolEnrollment(schoolEnrollmentWrap);
			if(schoolEnrollment == null)
				setSchoolEnrollment(schoolEnrollmentWrap.o);
		}
		schoolEnrollmentWrap.dejaInitialise(true);
		return (EnrollmentGenPage)this;
	}

	//////////////
	// initLoin //
	//////////////

	protected boolean dejaInitialiseEnrollmentGenPage = false;

	public EnrollmentGenPage initLoinEnrollmentGenPage(SiteRequestEnUS requeteSite_) {
		setRequeteSite_(requeteSite_);
		if(!dejaInitialiseEnrollmentGenPage) {
			dejaInitialiseEnrollmentGenPage = true;
			initLoinEnrollmentGenPage();
		}
		return (EnrollmentGenPage)this;
	}

	public void initLoinEnrollmentGenPage() {
		super.initLoinClusterPage(requeteSite_);
		initEnrollmentGenPage();
	}

	public void initEnrollmentGenPage() {
		listSchoolEnrollmentInit();
		schoolEnrollmentInit();
	}

	@Override public void initLoinPourClasse(SiteRequestEnUS requeteSite_) {
		initLoinEnrollmentGenPage(requeteSite_);
	}

	/////////////////
	// requeteSite //
	/////////////////

	public void requeteSiteEnrollmentGenPage(SiteRequestEnUS requeteSite_) {
			super.requeteSiteClusterPage(requeteSite_);
	}

	public void requeteSitePourClasse(SiteRequestEnUS requeteSite_) {
		requeteSiteEnrollmentGenPage(requeteSite_);
	}

	/////////////
	// obtenir //
	/////////////

	@Override public Object obtenirPourClasse(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtenirEnrollmentGenPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtenirPourClasse(v);
			}
		}
		return o;
	}
	public Object obtenirEnrollmentGenPage(String var) {
		EnrollmentGenPage oEnrollmentGenPage = (EnrollmentGenPage)this;
		switch(var) {
			case "listSchoolEnrollment":
				return oEnrollmentGenPage.listSchoolEnrollment;
			case "schoolEnrollment":
				return oEnrollmentGenPage.schoolEnrollment;
			default:
				return super.obtenirClusterPage(var);
		}
	}

	///////////////
	// attribuer //
	///////////////

	@Override public boolean attribuerPourClasse(String var, Object val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = attribuerEnrollmentGenPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attribuerPourClasse(v, val);
			}
		}
		return o != null;
	}
	public Object attribuerEnrollmentGenPage(String var, Object val) {
		EnrollmentGenPage oEnrollmentGenPage = (EnrollmentGenPage)this;
		switch(var) {
			default:
				return super.attribuerClusterPage(var, val);
		}
	}

	/////////////
	// definir //
	/////////////

	@Override public boolean definirPourClasse(String var, String val) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		if(val != null) {
			for(String v : vars) {
				if(o == null)
					o = definirEnrollmentGenPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.definirPourClasse(v, val);
				}
			}
		}
		return o != null;
	}
	public Object definirEnrollmentGenPage(String var, String val) {
		switch(var) {
			default:
				return super.definirClusterPage(var, val);
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
