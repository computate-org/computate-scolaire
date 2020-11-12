package org.computate.scolaire.enUS.user;

import org.computate.scolaire.enUS.wrap.Wrap;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import org.computate.scolaire.enUS.user.SiteUserGenPage;
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
import org.computate.scolaire.enUS.design.PageDesign;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.computate.scolaire.enUS.year.SchoolYear;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import java.util.List;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class SiteUserPageGen<DEV> extends SiteUserGenPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SiteUserPage.class);

	//////////////////////
	// pageDesignSearch //
	//////////////////////

	/**	 The entity pageDesignSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<PageDesign>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<PageDesign> pageDesignSearch = new SearchList<PageDesign>();
	@JsonIgnore
	public Wrap<SearchList<PageDesign>> pageDesignSearchWrap = new Wrap<SearchList<PageDesign>>().p(this).c(SearchList.class).var("pageDesignSearch").o(pageDesignSearch);

	/**	<br/> The entity pageDesignSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<PageDesign>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageDesignSearch">Find the entity pageDesignSearch in Solr</a>
	 * <br/>
	 * @param pageDesignSearch is the entity already constructed. 
	 **/
	protected abstract void _pageDesignSearch(SearchList<PageDesign> l);

	public SearchList<PageDesign> getPageDesignSearch() {
		return pageDesignSearch;
	}

	public void setPageDesignSearch(SearchList<PageDesign> pageDesignSearch) {
		this.pageDesignSearch = pageDesignSearch;
		this.pageDesignSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<PageDesign> staticSetPageDesignSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected SiteUserPage pageDesignSearchInit() {
		if(!pageDesignSearchWrap.alreadyInitialized) {
			_pageDesignSearch(pageDesignSearch);
		}
		pageDesignSearch.initDeepForClass(siteRequest_);
		pageDesignSearchWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	/////////////////
	// pageDesigns //
	/////////////////

	/**	 The entity pageDesigns
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<PageDesign> pageDesigns;
	@JsonIgnore
	public Wrap<List<PageDesign>> pageDesignsWrap = new Wrap<List<PageDesign>>().p(this).c(List.class).var("pageDesigns").o(pageDesigns);

	/**	<br/> The entity pageDesigns
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageDesigns">Find the entity pageDesigns in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageDesigns(Wrap<List<PageDesign>> c);

	public List<PageDesign> getPageDesigns() {
		return pageDesigns;
	}

	public void setPageDesigns(List<PageDesign> pageDesigns) {
		this.pageDesigns = pageDesigns;
		this.pageDesignsWrap.alreadyInitialized = true;
	}
	public static List<PageDesign> staticSetPageDesigns(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SiteUserPage addPageDesigns(PageDesign...objets) {
		for(PageDesign o : objets) {
			addPageDesigns(o);
		}
		return (SiteUserPage)this;
	}
	public SiteUserPage addPageDesigns(PageDesign o) {
		if(o != null && !pageDesigns.contains(o))
			this.pageDesigns.add(o);
		return (SiteUserPage)this;
	}
	protected SiteUserPage pageDesignsInit() {
		if(!pageDesignsWrap.alreadyInitialized) {
			_pageDesigns(pageDesignsWrap);
			if(pageDesigns == null)
				setPageDesigns(pageDesignsWrap.o);
		}
		pageDesignsWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	////////////////
	// yearSearch //
	////////////////

	/**	 The entity yearSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolYear>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolYear> yearSearch = new SearchList<SchoolYear>();
	@JsonIgnore
	public Wrap<SearchList<SchoolYear>> yearSearchWrap = new Wrap<SearchList<SchoolYear>>().p(this).c(SearchList.class).var("yearSearch").o(yearSearch);

	/**	<br/> The entity yearSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolYear>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSearch">Find the entity yearSearch in Solr</a>
	 * <br/>
	 * @param yearSearch is the entity already constructed. 
	 **/
	protected abstract void _yearSearch(SearchList<SchoolYear> l);

	public SearchList<SchoolYear> getYearSearch() {
		return yearSearch;
	}

	public void setYearSearch(SearchList<SchoolYear> yearSearch) {
		this.yearSearch = yearSearch;
		this.yearSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolYear> staticSetYearSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	/**	 The entity years
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolYear> years;
	@JsonIgnore
	public Wrap<List<SchoolYear>> yearsWrap = new Wrap<List<SchoolYear>>().p(this).c(List.class).var("years").o(years);

	/**	<br/> The entity years
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:years">Find the entity years in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _years(Wrap<List<SchoolYear>> c);

	public List<SchoolYear> getYears() {
		return years;
	}

	public void setYears(List<SchoolYear> years) {
		this.years = years;
		this.yearsWrap.alreadyInitialized = true;
	}
	public static List<SchoolYear> staticSetYears(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	/**	 The entity schoolYears
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolYear>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolYear> schoolYears = new ArrayList<SchoolYear>();
	@JsonIgnore
	public Wrap<List<SchoolYear>> schoolYearsWrap = new Wrap<List<SchoolYear>>().p(this).c(List.class).var("schoolYears").o(schoolYears);

	/**	<br/> The entity schoolYears
	 *  It is constructed before being initialized with the constructor by default List<SchoolYear>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolYears">Find the entity schoolYears in Solr</a>
	 * <br/>
	 * @param schoolYears is the entity already constructed. 
	 **/
	protected abstract void _schoolYears(List<SchoolYear> l);

	public List<SchoolYear> getSchoolYears() {
		return schoolYears;
	}

	public void setSchoolYears(List<SchoolYear> schoolYears) {
		this.schoolYears = schoolYears;
		this.schoolYearsWrap.alreadyInitialized = true;
	}
	public static List<SchoolYear> staticSetSchoolYears(SiteRequestEnUS siteRequest_, String o) {
		return null;
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

	//////////////////////
	// enrollmentSearch //
	//////////////////////

	/**	 The entity enrollmentSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolEnrollment>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolEnrollment> enrollmentSearch = new SearchList<SchoolEnrollment>();
	@JsonIgnore
	public Wrap<SearchList<SchoolEnrollment>> enrollmentSearchWrap = new Wrap<SearchList<SchoolEnrollment>>().p(this).c(SearchList.class).var("enrollmentSearch").o(enrollmentSearch);

	/**	<br/> The entity enrollmentSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSearch">Find the entity enrollmentSearch in Solr</a>
	 * <br/>
	 * @param enrollmentSearch is the entity already constructed. 
	 **/
	protected abstract void _enrollmentSearch(SearchList<SchoolEnrollment> l);

	public SearchList<SchoolEnrollment> getEnrollmentSearch() {
		return enrollmentSearch;
	}

	public void setEnrollmentSearch(SearchList<SchoolEnrollment> enrollmentSearch) {
		this.enrollmentSearch = enrollmentSearch;
		this.enrollmentSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolEnrollment> staticSetEnrollmentSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected SiteUserPage enrollmentSearchInit() {
		if(!enrollmentSearchWrap.alreadyInitialized) {
			_enrollmentSearch(enrollmentSearch);
		}
		enrollmentSearch.initDeepForClass(siteRequest_);
		enrollmentSearchWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	///////////////////////
	// enrollmentSchools //
	///////////////////////

	/**	 The entity enrollmentSchools
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolEnrollment>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollmentSchools = new ArrayList<SchoolEnrollment>();
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentSchoolsWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollmentSchools").o(enrollmentSchools);

	/**	<br/> The entity enrollmentSchools
	 *  It is constructed before being initialized with the constructor by default List<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSchools">Find the entity enrollmentSchools in Solr</a>
	 * <br/>
	 * @param enrollmentSchools is the entity already constructed. 
	 **/
	protected abstract void _enrollmentSchools(List<SchoolEnrollment> c);

	public List<SchoolEnrollment> getEnrollmentSchools() {
		return enrollmentSchools;
	}

	public void setEnrollmentSchools(List<SchoolEnrollment> enrollmentSchools) {
		this.enrollmentSchools = enrollmentSchools;
		this.enrollmentSchoolsWrap.alreadyInitialized = true;
	}
	public static List<SchoolEnrollment> staticSetEnrollmentSchools(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SiteUserPage addEnrollmentSchools(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollmentSchools(o);
		}
		return (SiteUserPage)this;
	}
	public SiteUserPage addEnrollmentSchools(SchoolEnrollment o) {
		if(o != null && !enrollmentSchools.contains(o))
			this.enrollmentSchools.add(o);
		return (SiteUserPage)this;
	}
	protected SiteUserPage enrollmentSchoolsInit() {
		if(!enrollmentSchoolsWrap.alreadyInitialized) {
			_enrollmentSchools(enrollmentSchools);
		}
		enrollmentSchoolsWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	/////////////////////////
	// enrollmentApprovals //
	/////////////////////////

	/**	 The entity enrollmentApprovals
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollmentApprovals;
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentApprovalsWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollmentApprovals").o(enrollmentApprovals);

	/**	<br/> The entity enrollmentApprovals
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentApprovals">Find the entity enrollmentApprovals in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentApprovals(Wrap<List<SchoolEnrollment>> c);

	public List<SchoolEnrollment> getEnrollmentApprovals() {
		return enrollmentApprovals;
	}

	public void setEnrollmentApprovals(List<SchoolEnrollment> enrollmentApprovals) {
		this.enrollmentApprovals = enrollmentApprovals;
		this.enrollmentApprovalsWrap.alreadyInitialized = true;
	}
	public static List<SchoolEnrollment> staticSetEnrollmentApprovals(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SiteUserPage addEnrollmentApprovals(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollmentApprovals(o);
		}
		return (SiteUserPage)this;
	}
	public SiteUserPage addEnrollmentApprovals(SchoolEnrollment o) {
		if(o != null && !enrollmentApprovals.contains(o))
			this.enrollmentApprovals.add(o);
		return (SiteUserPage)this;
	}
	protected SiteUserPage enrollmentApprovalsInit() {
		if(!enrollmentApprovalsWrap.alreadyInitialized) {
			_enrollmentApprovals(enrollmentApprovalsWrap);
			if(enrollmentApprovals == null)
				setEnrollmentApprovals(enrollmentApprovalsWrap.o);
		}
		enrollmentApprovalsWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	/////////////////////
	// enrollmentYears //
	/////////////////////

	/**	 The entity enrollmentYears
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollmentYears;
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentYearsWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollmentYears").o(enrollmentYears);

	/**	<br/> The entity enrollmentYears
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentYears">Find the entity enrollmentYears in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentYears(Wrap<List<SchoolEnrollment>> c);

	public List<SchoolEnrollment> getEnrollmentYears() {
		return enrollmentYears;
	}

	public void setEnrollmentYears(List<SchoolEnrollment> enrollmentYears) {
		this.enrollmentYears = enrollmentYears;
		this.enrollmentYearsWrap.alreadyInitialized = true;
	}
	public static List<SchoolEnrollment> staticSetEnrollmentYears(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SiteUserPage addEnrollmentYears(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollmentYears(o);
		}
		return (SiteUserPage)this;
	}
	public SiteUserPage addEnrollmentYears(SchoolEnrollment o) {
		if(o != null && !enrollmentYears.contains(o))
			this.enrollmentYears.add(o);
		return (SiteUserPage)this;
	}
	protected SiteUserPage enrollmentYearsInit() {
		if(!enrollmentYearsWrap.alreadyInitialized) {
			_enrollmentYears(enrollmentYearsWrap);
			if(enrollmentYears == null)
				setEnrollmentYears(enrollmentYearsWrap.o);
		}
		enrollmentYearsWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	//////////////////////
	// enrollmentSchool //
	//////////////////////

	/**	 The entity enrollmentSchool
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolEnrollment enrollmentSchool;
	@JsonIgnore
	public Wrap<SchoolEnrollment> enrollmentSchoolWrap = new Wrap<SchoolEnrollment>().p(this).c(SchoolEnrollment.class).var("enrollmentSchool").o(enrollmentSchool);

	/**	<br/> The entity enrollmentSchool
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSchool">Find the entity enrollmentSchool in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentSchool(Wrap<SchoolEnrollment> c);

	public SchoolEnrollment getEnrollmentSchool() {
		return enrollmentSchool;
	}

	public void setEnrollmentSchool(SchoolEnrollment enrollmentSchool) {
		this.enrollmentSchool = enrollmentSchool;
		this.enrollmentSchoolWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollmentSchool(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected SiteUserPage enrollmentSchoolInit() {
		if(!enrollmentSchoolWrap.alreadyInitialized) {
			_enrollmentSchool(enrollmentSchoolWrap);
			if(enrollmentSchool == null)
				setEnrollmentSchool(enrollmentSchoolWrap.o);
		}
		if(enrollmentSchool != null)
			enrollmentSchool.initDeepForClass(siteRequest_);
		enrollmentSchoolWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	////////////////////////
	// enrollmentApproval //
	////////////////////////

	/**	 The entity enrollmentApproval
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolEnrollment enrollmentApproval;
	@JsonIgnore
	public Wrap<SchoolEnrollment> enrollmentApprovalWrap = new Wrap<SchoolEnrollment>().p(this).c(SchoolEnrollment.class).var("enrollmentApproval").o(enrollmentApproval);

	/**	<br/> The entity enrollmentApproval
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentApproval">Find the entity enrollmentApproval in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentApproval(Wrap<SchoolEnrollment> c);

	public SchoolEnrollment getEnrollmentApproval() {
		return enrollmentApproval;
	}

	public void setEnrollmentApproval(SchoolEnrollment enrollmentApproval) {
		this.enrollmentApproval = enrollmentApproval;
		this.enrollmentApprovalWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollmentApproval(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected SiteUserPage enrollmentApprovalInit() {
		if(!enrollmentApprovalWrap.alreadyInitialized) {
			_enrollmentApproval(enrollmentApprovalWrap);
			if(enrollmentApproval == null)
				setEnrollmentApproval(enrollmentApprovalWrap.o);
		}
		if(enrollmentApproval != null)
			enrollmentApproval.initDeepForClass(siteRequest_);
		enrollmentApprovalWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	////////////////////
	// enrollmentYear //
	////////////////////

	/**	 The entity enrollmentYear
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolEnrollment enrollmentYear;
	@JsonIgnore
	public Wrap<SchoolEnrollment> enrollmentYearWrap = new Wrap<SchoolEnrollment>().p(this).c(SchoolEnrollment.class).var("enrollmentYear").o(enrollmentYear);

	/**	<br/> The entity enrollmentYear
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentYear">Find the entity enrollmentYear in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentYear(Wrap<SchoolEnrollment> c);

	public SchoolEnrollment getEnrollmentYear() {
		return enrollmentYear;
	}

	public void setEnrollmentYear(SchoolEnrollment enrollmentYear) {
		this.enrollmentYear = enrollmentYear;
		this.enrollmentYearWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollmentYear(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected SiteUserPage enrollmentYearInit() {
		if(!enrollmentYearWrap.alreadyInitialized) {
			_enrollmentYear(enrollmentYearWrap);
			if(enrollmentYear == null)
				setEnrollmentYear(enrollmentYearWrap.o);
		}
		if(enrollmentYear != null)
			enrollmentYear.initDeepForClass(siteRequest_);
		enrollmentYearWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	//////////////////////////
	// enrollmentEnrollment //
	//////////////////////////

	/**	 The entity enrollmentEnrollment
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolEnrollment enrollmentEnrollment;
	@JsonIgnore
	public Wrap<SchoolEnrollment> enrollmentEnrollmentWrap = new Wrap<SchoolEnrollment>().p(this).c(SchoolEnrollment.class).var("enrollmentEnrollment").o(enrollmentEnrollment);

	/**	<br/> The entity enrollmentEnrollment
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentEnrollment">Find the entity enrollmentEnrollment in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentEnrollment(Wrap<SchoolEnrollment> c);

	public SchoolEnrollment getEnrollmentEnrollment() {
		return enrollmentEnrollment;
	}

	public void setEnrollmentEnrollment(SchoolEnrollment enrollmentEnrollment) {
		this.enrollmentEnrollment = enrollmentEnrollment;
		this.enrollmentEnrollmentWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollmentEnrollment(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected SiteUserPage enrollmentEnrollmentInit() {
		if(!enrollmentEnrollmentWrap.alreadyInitialized) {
			_enrollmentEnrollment(enrollmentEnrollmentWrap);
			if(enrollmentEnrollment == null)
				setEnrollmentEnrollment(enrollmentEnrollmentWrap.o);
		}
		if(enrollmentEnrollment != null)
			enrollmentEnrollment.initDeepForClass(siteRequest_);
		enrollmentEnrollmentWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	/////////////////
	// enrollments //
	/////////////////

	/**	 The entity enrollments
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollments;
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentsWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollments").o(enrollments);

	/**	<br/> The entity enrollments
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollments">Find the entity enrollments in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollments(Wrap<List<SchoolEnrollment>> c);

	public List<SchoolEnrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<SchoolEnrollment> enrollments) {
		this.enrollments = enrollments;
		this.enrollmentsWrap.alreadyInitialized = true;
	}
	public static List<SchoolEnrollment> staticSetEnrollments(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SiteUserPage addEnrollments(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollments(o);
		}
		return (SiteUserPage)this;
	}
	public SiteUserPage addEnrollments(SchoolEnrollment o) {
		if(o != null && !enrollments.contains(o))
			this.enrollments.add(o);
		return (SiteUserPage)this;
	}
	protected SiteUserPage enrollmentsInit() {
		if(!enrollmentsWrap.alreadyInitialized) {
			_enrollments(enrollmentsWrap);
			if(enrollments == null)
				setEnrollments(enrollmentsWrap.o);
		}
		enrollmentsWrap.alreadyInitialized(true);
		return (SiteUserPage)this;
	}

	//////////////////
	// enrollments_ //
	//////////////////

	/**	 The entity enrollments_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollments_;
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollments_Wrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollments_").o(enrollments_);

	/**	<br/> The entity enrollments_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.user.SiteUserPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollments_">Find the entity enrollments_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollments_(Wrap<List<SchoolEnrollment>> c);

	public List<SchoolEnrollment> getEnrollments_() {
		return enrollments_;
	}

	public void setEnrollments_(List<SchoolEnrollment> enrollments_) {
		this.enrollments_ = enrollments_;
		this.enrollments_Wrap.alreadyInitialized = true;
	}
	public static List<SchoolEnrollment> staticSetEnrollments_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SiteUserPage addEnrollments_(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollments_(o);
		}
		return (SiteUserPage)this;
	}
	public SiteUserPage addEnrollments_(SchoolEnrollment o) {
		if(o != null && !enrollments_.contains(o))
			this.enrollments_.add(o);
		return (SiteUserPage)this;
	}
	protected SiteUserPage enrollments_Init() {
		if(!enrollments_Wrap.alreadyInitialized) {
			_enrollments_(enrollments_Wrap);
			if(enrollments_ == null)
				setEnrollments_(enrollments_Wrap.o);
		}
		enrollments_Wrap.alreadyInitialized(true);
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
		pageDesignSearchInit();
		pageDesignsInit();
		yearSearchInit();
		yearsInit();
		schoolYearsInit();
		enrollmentSearchInit();
		enrollmentSchoolsInit();
		enrollmentApprovalsInit();
		enrollmentYearsInit();
		enrollmentSchoolInit();
		enrollmentApprovalInit();
		enrollmentYearInit();
		enrollmentEnrollmentInit();
		enrollmentsInit();
		enrollments_Init();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSiteUserPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSiteUserPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestSiteUserGenPage(siteRequest_);
		if(pageDesignSearch != null)
			pageDesignSearch.setSiteRequest_(siteRequest_);
		if(yearSearch != null)
			yearSearch.setSiteRequest_(siteRequest_);
		if(enrollmentSearch != null)
			enrollmentSearch.setSiteRequest_(siteRequest_);
		if(enrollmentSchool != null)
			enrollmentSchool.setSiteRequest_(siteRequest_);
		if(enrollmentApproval != null)
			enrollmentApproval.setSiteRequest_(siteRequest_);
		if(enrollmentYear != null)
			enrollmentYear.setSiteRequest_(siteRequest_);
		if(enrollmentEnrollment != null)
			enrollmentEnrollment.setSiteRequest_(siteRequest_);
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
			case "pageDesignSearch":
				return oSiteUserPage.pageDesignSearch;
			case "pageDesigns":
				return oSiteUserPage.pageDesigns;
			case "yearSearch":
				return oSiteUserPage.yearSearch;
			case "years":
				return oSiteUserPage.years;
			case "schoolYears":
				return oSiteUserPage.schoolYears;
			case "enrollmentSearch":
				return oSiteUserPage.enrollmentSearch;
			case "enrollmentSchools":
				return oSiteUserPage.enrollmentSchools;
			case "enrollmentApprovals":
				return oSiteUserPage.enrollmentApprovals;
			case "enrollmentYears":
				return oSiteUserPage.enrollmentYears;
			case "enrollmentSchool":
				return oSiteUserPage.enrollmentSchool;
			case "enrollmentApproval":
				return oSiteUserPage.enrollmentApproval;
			case "enrollmentYear":
				return oSiteUserPage.enrollmentYear;
			case "enrollmentEnrollment":
				return oSiteUserPage.enrollmentEnrollment;
			case "enrollments":
				return oSiteUserPage.enrollments;
			case "enrollments_":
				return oSiteUserPage.enrollments_;
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

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetSiteUserPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSiteUserPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return SiteUserGenPage.staticSetSiteUserGenPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrSiteUserPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrSiteUserPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return SiteUserGenPage.staticSolrSiteUserGenPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrSiteUserPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrSiteUserPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
			default:
				return SiteUserGenPage.staticSolrStrSiteUserGenPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqSiteUserPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqSiteUserPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
			default:
				return SiteUserGenPage.staticSolrFqSiteUserGenPage(entityVar,  siteRequest_, o);
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
