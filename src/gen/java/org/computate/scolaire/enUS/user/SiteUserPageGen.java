package org.computate.scolaire.enUS.user;

import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.computate.scolaire.enUS.cluster.Cluster;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.MathContext;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.computate.scolaire.enUS.enrollment.design.EnrollmentDesign;
import org.computate.scolaire.enUS.year.SchoolYear;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import org.computate.scolaire.enUS.user.SiteUserGenPage;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.computate.scolaire.enUS.search.SearchList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SiteUserPageGen<DEV> extends SiteUserGenPage {

	////////////////////////////
	// enrollmentDesignSearch //
	////////////////////////////

	/**	L'entité « enrollmentDesignSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<EnrollmentDesign>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<EnrollmentDesign> enrollmentDesignSearch = new SearchList<EnrollmentDesign>();
	@JsonIgnore
	public Wrap<SearchList<EnrollmentDesign>> enrollmentDesignSearchWrap = new Wrap<SearchList<EnrollmentDesign>>().p(this).c(SearchList.class).var("enrollmentDesignSearch").o(enrollmentDesignSearch);

	/**	<br/>L'entité « enrollmentDesignSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<EnrollmentDesign>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDesignSearch">Trouver l'entité enrollmentDesignSearch dans Solr</a>
	 * <br/>
	 * @param enrollmentDesignSearch est l'entité déjà construit. 
	 **/
	protected abstract void _enrollmentDesignSearch(SearchList<EnrollmentDesign> l);

	public SearchList<EnrollmentDesign> getEnrollmentDesignSearch() {
		return enrollmentDesignSearch;
	}

	public void setEnrollmentDesignSearch(SearchList<EnrollmentDesign> enrollmentDesignSearch) {
		this.enrollmentDesignSearch = enrollmentDesignSearch;
		this.enrollmentDesignSearchWrap.alreadyInitialized = true;
	}
	protected SiteUserPage enrollmentDesignSearchInit() {
		if(!enrollmentDesignSearchWrap.alreadyInitialized) {
			_enrollmentDesignSearch(enrollmentDesignSearch);
		}
		enrollmentDesignSearch.initDeepForClass(siteRequest_);
		enrollmentDesignSearchWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	///////////////////////
	// enrollmentDesigns //
	///////////////////////

	/**	L'entité « enrollmentDesigns »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<EnrollmentDesign> enrollmentDesigns;
	@JsonIgnore
	public Wrap<List<EnrollmentDesign>> enrollmentDesignsWrap = new Wrap<List<EnrollmentDesign>>().p(this).c(List.class).var("enrollmentDesigns").o(enrollmentDesigns);

	/**	<br/>L'entité « enrollmentDesigns »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentDesigns">Trouver l'entité enrollmentDesigns dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentDesigns(Wrap<List<EnrollmentDesign>> c);

	public List<EnrollmentDesign> getEnrollmentDesigns() {
		return enrollmentDesigns;
	}

	public void setEnrollmentDesigns(List<EnrollmentDesign> enrollmentDesigns) {
		this.enrollmentDesigns = enrollmentDesigns;
		this.enrollmentDesignsWrap.alreadyInitialized = true;
	}
	public SiteUserPage addEnrollmentDesigns(EnrollmentDesign...objets) {
		for(EnrollmentDesign o : objets) {
			addEnrollmentDesigns(o);
		}
		return (SiteUserPage)this;
	}
	public SiteUserPage addEnrollmentDesigns(EnrollmentDesign o) {
		if(o != null && !enrollmentDesigns.contains(o))
			this.enrollmentDesigns.add(o);
		return (SiteUserPage)this;
	}
	protected SiteUserPage enrollmentDesignsInit() {
		if(!enrollmentDesignsWrap.alreadyInitialized) {
			_enrollmentDesigns(enrollmentDesignsWrap);
			if(enrollmentDesigns == null)
				setEnrollmentDesigns(enrollmentDesignsWrap.o);
		}
		enrollmentDesignsWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	////////////////
	// yearSearch //
	////////////////

	/**	L'entité « yearSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolYear>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolYear> yearSearch = new SearchList<SchoolYear>();
	@JsonIgnore
	public Wrap<SearchList<SchoolYear>> yearSearchWrap = new Wrap<SearchList<SchoolYear>>().p(this).c(SearchList.class).var("yearSearch").o(yearSearch);

	/**	<br/>L'entité « yearSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolYear>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSearch">Trouver l'entité yearSearch dans Solr</a>
	 * <br/>
	 * @param yearSearch est l'entité déjà construit. 
	 **/
	protected abstract void _yearSearch(SearchList<SchoolYear> l);

	public SearchList<SchoolYear> getYearSearch() {
		return yearSearch;
	}

	public void setYearSearch(SearchList<SchoolYear> yearSearch) {
		this.yearSearch = yearSearch;
		this.yearSearchWrap.alreadyInitialized = true;
	}
	protected SiteUserPage yearSearchInit() {
		if(!yearSearchWrap.alreadyInitialized) {
			_yearSearch(yearSearch);
		}
		yearSearch.initDeepForClass(siteRequest_);
		yearSearchWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	///////////
	// years //
	///////////

	/**	L'entité « years »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolYear> years;
	@JsonIgnore
	public Wrap<List<SchoolYear>> yearsWrap = new Wrap<List<SchoolYear>>().p(this).c(List.class).var("years").o(years);

	/**	<br/>L'entité « years »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:years">Trouver l'entité years dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _years(Wrap<List<SchoolYear>> c);

	public List<SchoolYear> getYears() {
		return years;
	}

	public void setYears(List<SchoolYear> years) {
		this.years = years;
		this.yearsWrap.alreadyInitialized = true;
	}
	public SiteUserPage addYears(SchoolYear...objets) {
		for(SchoolYear o : objets) {
			addYears(o);
		}
		return (SiteUserPage)this;
	}
	public SiteUserPage addYears(SchoolYear o) {
		if(o != null && !years.contains(o))
			this.years.add(o);
		return (SiteUserPage)this;
	}
	protected SiteUserPage yearsInit() {
		if(!yearsWrap.alreadyInitialized) {
			_years(yearsWrap);
			if(years == null)
				setYears(yearsWrap.o);
		}
		yearsWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	/////////////////
	// schoolYears //
	/////////////////

	/**	L'entité « schoolYears »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolYear>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolYear> schoolYears = new java.util.ArrayList<org.computate.scolaire.enUS.year.SchoolYear>();
	@JsonIgnore
	public Wrap<List<SchoolYear>> schoolYearsWrap = new Wrap<List<SchoolYear>>().p(this).c(List.class).var("schoolYears").o(schoolYears);

	/**	<br/>L'entité « schoolYears »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolYear>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolYears">Trouver l'entité schoolYears dans Solr</a>
	 * <br/>
	 * @param schoolYears est l'entité déjà construit. 
	 **/
	protected abstract void _schoolYears(List<SchoolYear> l);

	public List<SchoolYear> getSchoolYears() {
		return schoolYears;
	}

	public void setSchoolYears(List<SchoolYear> schoolYears) {
		this.schoolYears = schoolYears;
		this.schoolYearsWrap.alreadyInitialized = true;
	}
	public SiteUserPage addSchoolYears(SchoolYear...objets) {
		for(SchoolYear o : objets) {
			addSchoolYears(o);
		}
		return (SiteUserPage)this;
	}
	public SiteUserPage addSchoolYears(SchoolYear o) {
		if(o != null && !schoolYears.contains(o))
			this.schoolYears.add(o);
		return (SiteUserPage)this;
	}
	protected SiteUserPage schoolYearsInit() {
		if(!schoolYearsWrap.alreadyInitialized) {
			_schoolYears(schoolYears);
		}
		schoolYearsWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSiteUserPage = false;

	public SiteUserPage initDeepSiteUserPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSiteUserPage) {
			alreadyInitializedSiteUserPage = true;
			initDeepSiteUserPage();
		}
		return (SiteUserPage)this;
	}

	public void initDeepSiteUserPage() {
		initSiteUserPage();
		super.initDeepSiteUserGenPage(siteRequest_);
	}

	public void initSiteUserPage() {
		enrollmentDesignSearchInit();
		enrollmentDesignsInit();
		yearSearchInit();
		yearsInit();
		schoolYearsInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSiteUserPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSiteUserPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestSiteUserGenPage(siteRequest_);
		if(enrollmentDesignSearch != null)
			enrollmentDesignSearch.setSiteRequest_(siteRequest_);
		if(yearSearch != null)
			yearSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSiteUserPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSiteUserPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSiteUserPage(String var) {
		SiteUserPage oSiteUserPage = (SiteUserPage)this;
		switch(var) {
			case "enrollmentDesignSearch":
				return oSiteUserPage.enrollmentDesignSearch;
			case "enrollmentDesigns":
				return oSiteUserPage.enrollmentDesigns;
			case "yearSearch":
				return oSiteUserPage.yearSearch;
			case "years":
				return oSiteUserPage.years;
			case "schoolYears":
				return oSiteUserPage.schoolYears;
			default:
				return super.obtainSiteUserGenPage(var);
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
				o = attributeSiteUserPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSiteUserPage(String var, Object val) {
		SiteUserPage oSiteUserPage = (SiteUserPage)this;
		switch(var) {
			default:
				return super.attributeSiteUserGenPage(var, val);
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
					o = defineSiteUserPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSiteUserPage(String var, String val) {
		switch(var) {
			default:
				return super.defineSiteUserGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsSiteUserPage();
		super.htmlScripts();
	}

	public void htmlScriptsSiteUserPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptSiteUserPage();
		super.htmlScript();
	}

	public void htmlScriptSiteUserPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodySiteUserPage();
		super.htmlBody();
	}

	public void htmlBodySiteUserPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlSiteUserPage();
		super.html();
	}

	public void htmlSiteUserPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaSiteUserPage();
		super.htmlMeta();
	}

	public void htmlMetaSiteUserPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesSiteUserPage();
		super.htmlStyles();
	}

	public void htmlStylesSiteUserPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleSiteUserPage();
		super.htmlStyle();
	}

	public void htmlStyleSiteUserPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSiteUserPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SiteUserPage) {
			SiteUserPage original = (SiteUserPage)o;
			super.apiRequestSiteUserGenPage();
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
		if(!(o instanceof SiteUserPage))
			return false;
		SiteUserPage that = (SiteUserPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SiteUserPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
