package org.computate.scolaire.enUS.design;

import org.computate.scolaire.enUS.wrap.Wrap;
import java.util.Arrays;
import org.apache.solr.common.util.SimpleOrderedMap;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import org.computate.scolaire.enUS.block.SchoolBlock;
import java.math.BigDecimal;
import java.lang.Long;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Locale;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import org.computate.scolaire.enUS.writer.AllWriter;
import java.math.MathContext;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.computate.scolaire.enUS.year.SchoolYear;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import java.util.List;
import org.computate.scolaire.enUS.guardian.SchoolGuardian;
import java.time.LocalDate;
import org.computate.scolaire.enUS.mom.SchoolMom;
import java.util.Optional;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.scolaire.enUS.payment.SchoolPayment;
import java.util.HashMap;
import org.computate.scolaire.enUS.html.part.HtmlPart;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import java.lang.String;
import org.computate.scolaire.enUS.dad.SchoolDad;
import org.computate.scolaire.enUS.cluster.Cluster;
import org.computate.scolaire.enUS.school.School;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.vertx.core.json.JsonArray;
import org.computate.scolaire.enUS.receipt.SchoolReceipt;
import java.time.temporal.ChronoUnit;
import org.computate.scolaire.enUS.design.DesignDisplayGenPage;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.math.NumberUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class DesignDisplayPageGen<DEV> extends DesignDisplayGenPage {
	protected static final Logger LOGGER = LoggerFactory.getLogger(DesignDisplayPage.class);

	//////////////////
	// pageDesignId //
	//////////////////

	/**	 The entity pageDesignId
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String pageDesignId;
	@JsonIgnore
	public Wrap<String> pageDesignIdWrap = new Wrap<String>().p(this).c(String.class).var("pageDesignId").o(pageDesignId);

	/**	<br/> The entity pageDesignId
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:pageDesignId">Find the entity pageDesignId in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _pageDesignId(Wrap<String> c);

	public String getPageDesignId() {
		return pageDesignId;
	}
	public void setPageDesignId(String o) {
		this.pageDesignId = DesignDisplayPage.staticSetPageDesignId(siteRequest_, o);
		this.pageDesignIdWrap.alreadyInitialized = true;
	}
	public static String staticSetPageDesignId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignDisplayPage pageDesignIdInit() {
		if(!pageDesignIdWrap.alreadyInitialized) {
			_pageDesignId(pageDesignIdWrap);
			if(pageDesignId == null)
				setPageDesignId(pageDesignIdWrap.o);
		}
		pageDesignIdWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static String staticSolrPageDesignId(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPageDesignId(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPageDesignId(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrPageDesignId(siteRequest_, DesignDisplayPage.staticSolrPageDesignId(siteRequest_, DesignDisplayPage.staticSetPageDesignId(siteRequest_, o)));
	}

	public String solrPageDesignId() {
		return DesignDisplayPage.staticSolrPageDesignId(siteRequest_, pageDesignId);
	}

	public String strPageDesignId() {
		return pageDesignId == null ? "" : pageDesignId;
	}

	public String jsonPageDesignId() {
		return pageDesignId == null ? "" : pageDesignId;
	}

	public String nomAffichagePageDesignId() {
		return null;
	}

	public String htmTooltipPageDesignId() {
		return null;
	}

	public String htmPageDesignId() {
		return pageDesignId == null ? "" : StringEscapeUtils.escapeHtml4(strPageDesignId());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSearch">Find the entity enrollmentSearch in Solr</a>
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
	protected DesignDisplayPage enrollmentSearchInit() {
		if(!enrollmentSearchWrap.alreadyInitialized) {
			_enrollmentSearch(enrollmentSearch);
		}
		enrollmentSearch.initDeepForClass(siteRequest_);
		enrollmentSearchWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	//////////////////////
	// schoolEnrollment //
	//////////////////////

	/**	 The entity schoolEnrollment
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolEnrollment schoolEnrollment;
	@JsonIgnore
	public Wrap<SchoolEnrollment> schoolEnrollmentWrap = new Wrap<SchoolEnrollment>().p(this).c(SchoolEnrollment.class).var("schoolEnrollment").o(schoolEnrollment);

	/**	<br/> The entity schoolEnrollment
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolEnrollment">Find the entity schoolEnrollment in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolEnrollment(Wrap<SchoolEnrollment> c);

	public SchoolEnrollment getSchoolEnrollment() {
		return schoolEnrollment;
	}

	public void setSchoolEnrollment(SchoolEnrollment schoolEnrollment) {
		this.schoolEnrollment = schoolEnrollment;
		this.schoolEnrollmentWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetSchoolEnrollment(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage schoolEnrollmentInit() {
		if(!schoolEnrollmentWrap.alreadyInitialized) {
			_schoolEnrollment(schoolEnrollmentWrap);
			if(schoolEnrollment == null)
				setSchoolEnrollment(schoolEnrollmentWrap.o);
		}
		if(schoolEnrollment != null)
			schoolEnrollment.initDeepForClass(siteRequest_);
		schoolEnrollmentWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollments">Find the entity enrollments in Solr</a>
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
	public DesignDisplayPage addEnrollments(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollments(o);
		}
		return (DesignDisplayPage)this;
	}
	public DesignDisplayPage addEnrollments(SchoolEnrollment o) {
		if(o != null && !enrollments.contains(o))
			this.enrollments.add(o);
		return (DesignDisplayPage)this;
	}
	protected DesignDisplayPage enrollmentsInit() {
		if(!enrollmentsWrap.alreadyInitialized) {
			_enrollments(enrollmentsWrap);
			if(enrollments == null)
				setEnrollments(enrollmentsWrap.o);
		}
		enrollmentsWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	//////////////////////
	// enrollmentBlocks //
	//////////////////////

	/**	 The entity enrollmentBlocks
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolEnrollment>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollmentBlocks = new ArrayList<SchoolEnrollment>();
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentBlocksWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollmentBlocks").o(enrollmentBlocks);

	/**	<br/> The entity enrollmentBlocks
	 *  It is constructed before being initialized with the constructor by default List<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentBlocks">Find the entity enrollmentBlocks in Solr</a>
	 * <br/>
	 * @param enrollmentBlocks is the entity already constructed. 
	 **/
	protected abstract void _enrollmentBlocks(List<SchoolEnrollment> c);

	public List<SchoolEnrollment> getEnrollmentBlocks() {
		return enrollmentBlocks;
	}

	public void setEnrollmentBlocks(List<SchoolEnrollment> enrollmentBlocks) {
		this.enrollmentBlocks = enrollmentBlocks;
		this.enrollmentBlocksWrap.alreadyInitialized = true;
	}
	public static List<SchoolEnrollment> staticSetEnrollmentBlocks(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignDisplayPage addEnrollmentBlocks(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollmentBlocks(o);
		}
		return (DesignDisplayPage)this;
	}
	public DesignDisplayPage addEnrollmentBlocks(SchoolEnrollment o) {
		if(o != null && !enrollmentBlocks.contains(o))
			this.enrollmentBlocks.add(o);
		return (DesignDisplayPage)this;
	}
	protected DesignDisplayPage enrollmentBlocksInit() {
		if(!enrollmentBlocksWrap.alreadyInitialized) {
			_enrollmentBlocks(enrollmentBlocks);
		}
		enrollmentBlocksWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	//////////////////////
	// enrollmentGroups //
	//////////////////////

	/**	 The entity enrollmentGroups
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollmentGroups;
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentGroupsWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollmentGroups").o(enrollmentGroups);

	/**	<br/> The entity enrollmentGroups
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentGroups">Find the entity enrollmentGroups in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentGroups(Wrap<List<SchoolEnrollment>> c);

	public List<SchoolEnrollment> getEnrollmentGroups() {
		return enrollmentGroups;
	}

	public void setEnrollmentGroups(List<SchoolEnrollment> enrollmentGroups) {
		this.enrollmentGroups = enrollmentGroups;
		this.enrollmentGroupsWrap.alreadyInitialized = true;
	}
	public static List<SchoolEnrollment> staticSetEnrollmentGroups(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignDisplayPage addEnrollmentGroups(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollmentGroups(o);
		}
		return (DesignDisplayPage)this;
	}
	public DesignDisplayPage addEnrollmentGroups(SchoolEnrollment o) {
		if(o != null && !enrollmentGroups.contains(o))
			this.enrollmentGroups.add(o);
		return (DesignDisplayPage)this;
	}
	protected DesignDisplayPage enrollmentGroupsInit() {
		if(!enrollmentGroupsWrap.alreadyInitialized) {
			_enrollmentGroups(enrollmentGroupsWrap);
			if(enrollmentGroups == null)
				setEnrollmentGroups(enrollmentGroupsWrap.o);
		}
		enrollmentGroupsWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	/////////////////////
	// enrollmentBlock //
	/////////////////////

	/**	 The entity enrollmentBlock
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolEnrollment enrollmentBlock;
	@JsonIgnore
	public Wrap<SchoolEnrollment> enrollmentBlockWrap = new Wrap<SchoolEnrollment>().p(this).c(SchoolEnrollment.class).var("enrollmentBlock").o(enrollmentBlock);

	/**	<br/> The entity enrollmentBlock
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentBlock">Find the entity enrollmentBlock in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentBlock(Wrap<SchoolEnrollment> c);

	public SchoolEnrollment getEnrollmentBlock() {
		return enrollmentBlock;
	}

	public void setEnrollmentBlock(SchoolEnrollment enrollmentBlock) {
		this.enrollmentBlock = enrollmentBlock;
		this.enrollmentBlockWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollmentBlock(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage enrollmentBlockInit() {
		if(!enrollmentBlockWrap.alreadyInitialized) {
			_enrollmentBlock(enrollmentBlockWrap);
			if(enrollmentBlock == null)
				setEnrollmentBlock(enrollmentBlockWrap.o);
		}
		if(enrollmentBlock != null)
			enrollmentBlock.initDeepForClass(siteRequest_);
		enrollmentBlockWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	/////////////////////
	// enrollmentGroup //
	/////////////////////

	/**	 The entity enrollmentGroup
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolEnrollment enrollmentGroup;
	@JsonIgnore
	public Wrap<SchoolEnrollment> enrollmentGroupWrap = new Wrap<SchoolEnrollment>().p(this).c(SchoolEnrollment.class).var("enrollmentGroup").o(enrollmentGroup);

	/**	<br/> The entity enrollmentGroup
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentGroup">Find the entity enrollmentGroup in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _enrollmentGroup(Wrap<SchoolEnrollment> c);

	public SchoolEnrollment getEnrollmentGroup() {
		return enrollmentGroup;
	}

	public void setEnrollmentGroup(SchoolEnrollment enrollmentGroup) {
		this.enrollmentGroup = enrollmentGroup;
		this.enrollmentGroupWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollmentGroup(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage enrollmentGroupInit() {
		if(!enrollmentGroupWrap.alreadyInitialized) {
			_enrollmentGroup(enrollmentGroupWrap);
			if(enrollmentGroup == null)
				setEnrollmentGroup(enrollmentGroupWrap.o);
		}
		if(enrollmentGroup != null)
			enrollmentGroup.initDeepForClass(siteRequest_);
		enrollmentGroupWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentEnrollment">Find the entity enrollmentEnrollment in Solr</a>
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
	protected DesignDisplayPage enrollmentEnrollmentInit() {
		if(!enrollmentEnrollmentWrap.alreadyInitialized) {
			_enrollmentEnrollment(enrollmentEnrollmentWrap);
			if(enrollmentEnrollment == null)
				setEnrollmentEnrollment(enrollmentEnrollmentWrap.o);
		}
		if(enrollmentEnrollment != null)
			enrollmentEnrollment.initDeepForClass(siteRequest_);
		enrollmentEnrollmentWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSearch">Find the entity yearSearch in Solr</a>
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
	protected DesignDisplayPage yearSearchInit() {
		if(!yearSearchWrap.alreadyInitialized) {
			_yearSearch(yearSearch);
		}
		yearSearch.initDeepForClass(siteRequest_);
		yearSearchWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	///////////
	// year_ //
	///////////

	/**	 The entity year_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolYear year_;
	@JsonIgnore
	public Wrap<SchoolYear> year_Wrap = new Wrap<SchoolYear>().p(this).c(SchoolYear.class).var("year_").o(year_);

	/**	<br/> The entity year_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:year_">Find the entity year_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _year_(Wrap<SchoolYear> c);

	public SchoolYear getYear_() {
		return year_;
	}

	public void setYear_(SchoolYear year_) {
		this.year_ = year_;
		this.year_Wrap.alreadyInitialized = true;
	}
	public static SchoolYear staticSetYear_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage year_Init() {
		if(!year_Wrap.alreadyInitialized) {
			_year_(year_Wrap);
			if(year_ == null)
				setYear_(year_Wrap.o);
		}
		year_Wrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	/////////////
	// yearKey //
	/////////////

	/**	 The entity yearKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long yearKey;
	@JsonIgnore
	public Wrap<Long> yearKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("yearKey").o(yearKey);

	/**	<br/> The entity yearKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKey">Find the entity yearKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearKey(Wrap<Long> c);

	public Long getYearKey() {
		return yearKey;
	}

	public void setYearKey(Long yearKey) {
		this.yearKey = yearKey;
		this.yearKeyWrap.alreadyInitialized = true;
	}
	public void setYearKey(String o) {
		this.yearKey = DesignDisplayPage.staticSetYearKey(siteRequest_, o);
		this.yearKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetYearKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected DesignDisplayPage yearKeyInit() {
		if(!yearKeyWrap.alreadyInitialized) {
			_yearKey(yearKeyWrap);
			if(yearKey == null)
				setYearKey(yearKeyWrap.o);
		}
		yearKeyWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static Long staticSolrYearKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrYearKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearKey(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrYearKey(siteRequest_, DesignDisplayPage.staticSolrYearKey(siteRequest_, DesignDisplayPage.staticSetYearKey(siteRequest_, o)));
	}

	public Long solrYearKey() {
		return DesignDisplayPage.staticSolrYearKey(siteRequest_, yearKey);
	}

	public String strYearKey() {
		return yearKey == null ? "" : yearKey.toString();
	}

	public String jsonYearKey() {
		return yearKey == null ? "" : yearKey.toString();
	}

	public String nomAffichageYearKey() {
		return null;
	}

	public String htmTooltipYearKey() {
		return null;
	}

	public String htmYearKey() {
		return yearKey == null ? "" : StringEscapeUtils.escapeHtml4(strYearKey());
	}

	/////////////
	// yearVar //
	/////////////

	/**	 The entity yearVar
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String yearVar;
	@JsonIgnore
	public Wrap<String> yearVarWrap = new Wrap<String>().p(this).c(String.class).var("yearVar").o(yearVar);

	/**	<br/> The entity yearVar
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearVar">Find the entity yearVar in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearVar(Wrap<String> c);

	public String getYearVar() {
		return yearVar;
	}
	public void setYearVar(String o) {
		this.yearVar = DesignDisplayPage.staticSetYearVar(siteRequest_, o);
		this.yearVarWrap.alreadyInitialized = true;
	}
	public static String staticSetYearVar(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignDisplayPage yearVarInit() {
		if(!yearVarWrap.alreadyInitialized) {
			_yearVar(yearVarWrap);
			if(yearVar == null)
				setYearVar(yearVarWrap.o);
		}
		yearVarWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static String staticSolrYearVar(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrYearVar(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearVar(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrYearVar(siteRequest_, DesignDisplayPage.staticSolrYearVar(siteRequest_, DesignDisplayPage.staticSetYearVar(siteRequest_, o)));
	}

	public String solrYearVar() {
		return DesignDisplayPage.staticSolrYearVar(siteRequest_, yearVar);
	}

	public String strYearVar() {
		return yearVar == null ? "" : yearVar;
	}

	public String jsonYearVar() {
		return yearVar == null ? "" : yearVar;
	}

	public String nomAffichageYearVar() {
		return null;
	}

	public String htmTooltipYearVar() {
		return null;
	}

	public String htmYearVar() {
		return yearVar == null ? "" : StringEscapeUtils.escapeHtml4(strYearVar());
	}

	//////////////////
	// schoolSearch //
	//////////////////

	/**	 The entity schoolSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<School>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<School> schoolSearch = new SearchList<School>();
	@JsonIgnore
	public Wrap<SearchList<School>> schoolSearchWrap = new Wrap<SearchList<School>>().p(this).c(SearchList.class).var("schoolSearch").o(schoolSearch);

	/**	<br/> The entity schoolSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<School>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSearch">Find the entity schoolSearch in Solr</a>
	 * <br/>
	 * @param schoolSearch is the entity already constructed. 
	 **/
	protected abstract void _schoolSearch(SearchList<School> l);

	public SearchList<School> getSchoolSearch() {
		return schoolSearch;
	}

	public void setSchoolSearch(SearchList<School> schoolSearch) {
		this.schoolSearch = schoolSearch;
		this.schoolSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<School> staticSetSchoolSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage schoolSearchInit() {
		if(!schoolSearchWrap.alreadyInitialized) {
			_schoolSearch(schoolSearch);
		}
		schoolSearch.initDeepForClass(siteRequest_);
		schoolSearchWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	/////////////
	// school_ //
	/////////////

	/**	 The entity school_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected School school_;
	@JsonIgnore
	public Wrap<School> school_Wrap = new Wrap<School>().p(this).c(School.class).var("school_").o(school_);

	/**	<br/> The entity school_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:school_">Find the entity school_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _school_(Wrap<School> c);

	public School getSchool_() {
		return school_;
	}

	public void setSchool_(School school_) {
		this.school_ = school_;
		this.school_Wrap.alreadyInitialized = true;
	}
	public static School staticSetSchool_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage school_Init() {
		if(!school_Wrap.alreadyInitialized) {
			_school_(school_Wrap);
			if(school_ == null)
				setSchool_(school_Wrap.o);
		}
		school_Wrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	///////////////////
	// paymentSearch //
	///////////////////

	/**	 The entity paymentSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolPayment>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolPayment> paymentSearch = new SearchList<SchoolPayment>();
	@JsonIgnore
	public Wrap<SearchList<SchoolPayment>> paymentSearchWrap = new Wrap<SearchList<SchoolPayment>>().p(this).c(SearchList.class).var("paymentSearch").o(paymentSearch);

	/**	<br/> The entity paymentSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolPayment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentSearch">Find the entity paymentSearch in Solr</a>
	 * <br/>
	 * @param paymentSearch is the entity already constructed. 
	 **/
	protected abstract void _paymentSearch(SearchList<SchoolPayment> l);

	public SearchList<SchoolPayment> getPaymentSearch() {
		return paymentSearch;
	}

	public void setPaymentSearch(SearchList<SchoolPayment> paymentSearch) {
		this.paymentSearch = paymentSearch;
		this.paymentSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolPayment> staticSetPaymentSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage paymentSearchInit() {
		if(!paymentSearchWrap.alreadyInitialized) {
			_paymentSearch(paymentSearch);
		}
		paymentSearch.initDeepForClass(siteRequest_);
		paymentSearchWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	///////////////
	// payments_ //
	///////////////

	/**	 The entity payments_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolPayment> payments_;
	@JsonIgnore
	public Wrap<List<SchoolPayment>> payments_Wrap = new Wrap<List<SchoolPayment>>().p(this).c(List.class).var("payments_").o(payments_);

	/**	<br/> The entity payments_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:payments_">Find the entity payments_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _payments_(Wrap<List<SchoolPayment>> c);

	public List<SchoolPayment> getPayments_() {
		return payments_;
	}

	public void setPayments_(List<SchoolPayment> payments_) {
		this.payments_ = payments_;
		this.payments_Wrap.alreadyInitialized = true;
	}
	public static List<SchoolPayment> staticSetPayments_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignDisplayPage addPayments_(SchoolPayment...objets) {
		for(SchoolPayment o : objets) {
			addPayments_(o);
		}
		return (DesignDisplayPage)this;
	}
	public DesignDisplayPage addPayments_(SchoolPayment o) {
		if(o != null && !payments_.contains(o))
			this.payments_.add(o);
		return (DesignDisplayPage)this;
	}
	protected DesignDisplayPage payments_Init() {
		if(!payments_Wrap.alreadyInitialized) {
			_payments_(payments_Wrap);
			if(payments_ == null)
				setPayments_(payments_Wrap.o);
		}
		payments_Wrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	//////////////
	// payment_ //
	//////////////

	/**	 The entity payment_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolPayment payment_;
	@JsonIgnore
	public Wrap<SchoolPayment> payment_Wrap = new Wrap<SchoolPayment>().p(this).c(SchoolPayment.class).var("payment_").o(payment_);

	/**	<br/> The entity payment_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:payment_">Find the entity payment_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _payment_(Wrap<SchoolPayment> c);

	public SchoolPayment getPayment_() {
		return payment_;
	}

	public void setPayment_(SchoolPayment payment_) {
		this.payment_ = payment_;
		this.payment_Wrap.alreadyInitialized = true;
	}
	public static SchoolPayment staticSetPayment_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage payment_Init() {
		if(!payment_Wrap.alreadyInitialized) {
			_payment_(payment_Wrap);
			if(payment_ == null)
				setPayment_(payment_Wrap.o);
		}
		payment_Wrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	///////////////////
	// paymentFacets //
	///////////////////

	/**	 The entity paymentFacets
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SimpleOrderedMap paymentFacets;
	@JsonIgnore
	public Wrap<SimpleOrderedMap> paymentFacetsWrap = new Wrap<SimpleOrderedMap>().p(this).c(SimpleOrderedMap.class).var("paymentFacets").o(paymentFacets);

	/**	<br/> The entity paymentFacets
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentFacets">Find the entity paymentFacets in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentFacets(Wrap<SimpleOrderedMap> c);

	public SimpleOrderedMap getPaymentFacets() {
		return paymentFacets;
	}

	public void setPaymentFacets(SimpleOrderedMap paymentFacets) {
		this.paymentFacets = paymentFacets;
		this.paymentFacetsWrap.alreadyInitialized = true;
	}
	public static SimpleOrderedMap staticSetPaymentFacets(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage paymentFacetsInit() {
		if(!paymentFacetsWrap.alreadyInitialized) {
			_paymentFacets(paymentFacetsWrap);
			if(paymentFacets == null)
				setPaymentFacets(paymentFacetsWrap.o);
		}
		paymentFacetsWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	////////////////////
	// paymentLastStr //
	////////////////////

	/**	 The entity paymentLastStr
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String paymentLastStr;
	@JsonIgnore
	public Wrap<String> paymentLastStrWrap = new Wrap<String>().p(this).c(String.class).var("paymentLastStr").o(paymentLastStr);

	/**	<br/> The entity paymentLastStr
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentLastStr">Find the entity paymentLastStr in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentLastStr(Wrap<String> c);

	public String getPaymentLastStr() {
		return paymentLastStr;
	}
	public void setPaymentLastStr(String o) {
		this.paymentLastStr = DesignDisplayPage.staticSetPaymentLastStr(siteRequest_, o);
		this.paymentLastStrWrap.alreadyInitialized = true;
	}
	public static String staticSetPaymentLastStr(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignDisplayPage paymentLastStrInit() {
		if(!paymentLastStrWrap.alreadyInitialized) {
			_paymentLastStr(paymentLastStrWrap);
			if(paymentLastStr == null)
				setPaymentLastStr(paymentLastStrWrap.o);
		}
		paymentLastStrWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static String staticSolrPaymentLastStr(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPaymentLastStr(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentLastStr(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrPaymentLastStr(siteRequest_, DesignDisplayPage.staticSolrPaymentLastStr(siteRequest_, DesignDisplayPage.staticSetPaymentLastStr(siteRequest_, o)));
	}

	public String solrPaymentLastStr() {
		return DesignDisplayPage.staticSolrPaymentLastStr(siteRequest_, paymentLastStr);
	}

	public String strPaymentLastStr() {
		return paymentLastStr == null ? "" : paymentLastStr;
	}

	public String jsonPaymentLastStr() {
		return paymentLastStr == null ? "" : paymentLastStr;
	}

	public String nomAffichagePaymentLastStr() {
		return null;
	}

	public String htmTooltipPaymentLastStr() {
		return null;
	}

	public String htmPaymentLastStr() {
		return paymentLastStr == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentLastStr());
	}

	///////////////////
	// paymentAmount //
	///////////////////

	/**	 The entity paymentAmount
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal paymentAmount;
	@JsonIgnore
	public Wrap<BigDecimal> paymentAmountWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("paymentAmount").o(paymentAmount);

	/**	<br/> The entity paymentAmount
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentAmount">Find the entity paymentAmount in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentAmount(Wrap<BigDecimal> c);

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
		this.paymentAmountWrap.alreadyInitialized = true;
	}
	public void setPaymentAmount(String o) {
		this.paymentAmount = DesignDisplayPage.staticSetPaymentAmount(siteRequest_, o);
		this.paymentAmountWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetPaymentAmount(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setPaymentAmount(Double o) {
			this.paymentAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paymentAmountWrap.alreadyInitialized = true;
	}
	public void setPaymentAmount(Integer o) {
			this.paymentAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paymentAmountWrap.alreadyInitialized = true;
	}
	protected DesignDisplayPage paymentAmountInit() {
		if(!paymentAmountWrap.alreadyInitialized) {
			_paymentAmount(paymentAmountWrap);
			if(paymentAmount == null)
				setPaymentAmount(paymentAmountWrap.o);
		}
		paymentAmountWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static Double staticSolrPaymentAmount(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrPaymentAmount(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentAmount(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrPaymentAmount(siteRequest_, DesignDisplayPage.staticSolrPaymentAmount(siteRequest_, DesignDisplayPage.staticSetPaymentAmount(siteRequest_, o)));
	}

	public Double solrPaymentAmount() {
		return DesignDisplayPage.staticSolrPaymentAmount(siteRequest_, paymentAmount);
	}

	public String strPaymentAmount() {
		return paymentAmount == null ? "" : paymentAmount.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonPaymentAmount() {
		return paymentAmount == null ? "" : paymentAmount.toString();
	}

	public String nomAffichagePaymentAmount() {
		return null;
	}

	public String htmTooltipPaymentAmount() {
		return null;
	}

	public String htmPaymentAmount() {
		return paymentAmount == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentAmount());
	}

	//////////////////
	// chargeAmount //
	//////////////////

	/**	 The entity chargeAmount
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal chargeAmount;
	@JsonIgnore
	public Wrap<BigDecimal> chargeAmountWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("chargeAmount").o(chargeAmount);

	/**	<br/> The entity chargeAmount
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeAmount">Find the entity chargeAmount in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeAmount(Wrap<BigDecimal> c);

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
		this.chargeAmountWrap.alreadyInitialized = true;
	}
	public void setChargeAmount(String o) {
		this.chargeAmount = DesignDisplayPage.staticSetChargeAmount(siteRequest_, o);
		this.chargeAmountWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetChargeAmount(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setChargeAmount(Double o) {
			this.chargeAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountWrap.alreadyInitialized = true;
	}
	public void setChargeAmount(Integer o) {
			this.chargeAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountWrap.alreadyInitialized = true;
	}
	protected DesignDisplayPage chargeAmountInit() {
		if(!chargeAmountWrap.alreadyInitialized) {
			_chargeAmount(chargeAmountWrap);
			if(chargeAmount == null)
				setChargeAmount(chargeAmountWrap.o);
		}
		chargeAmountWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static Double staticSolrChargeAmount(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargeAmount(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeAmount(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrChargeAmount(siteRequest_, DesignDisplayPage.staticSolrChargeAmount(siteRequest_, DesignDisplayPage.staticSetChargeAmount(siteRequest_, o)));
	}

	public Double solrChargeAmount() {
		return DesignDisplayPage.staticSolrChargeAmount(siteRequest_, chargeAmount);
	}

	public String strChargeAmount() {
		return chargeAmount == null ? "" : chargeAmount.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonChargeAmount() {
		return chargeAmount == null ? "" : chargeAmount.toString();
	}

	public String nomAffichageChargeAmount() {
		return null;
	}

	public String htmTooltipChargeAmount() {
		return null;
	}

	public String htmChargeAmount() {
		return chargeAmount == null ? "" : StringEscapeUtils.escapeHtml4(strChargeAmount());
	}

	////////////////////////
	// chargeAmountFuture //
	////////////////////////

	/**	 The entity chargeAmountFuture
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal chargeAmountFuture;
	@JsonIgnore
	public Wrap<BigDecimal> chargeAmountFutureWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("chargeAmountFuture").o(chargeAmountFuture);

	/**	<br/> The entity chargeAmountFuture
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeAmountFuture">Find the entity chargeAmountFuture in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeAmountFuture(Wrap<BigDecimal> c);

	public BigDecimal getChargeAmountFuture() {
		return chargeAmountFuture;
	}

	public void setChargeAmountFuture(BigDecimal chargeAmountFuture) {
		this.chargeAmountFuture = chargeAmountFuture;
		this.chargeAmountFutureWrap.alreadyInitialized = true;
	}
	public void setChargeAmountFuture(String o) {
		this.chargeAmountFuture = DesignDisplayPage.staticSetChargeAmountFuture(siteRequest_, o);
		this.chargeAmountFutureWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetChargeAmountFuture(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setChargeAmountFuture(Double o) {
			this.chargeAmountFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountFutureWrap.alreadyInitialized = true;
	}
	public void setChargeAmountFuture(Integer o) {
			this.chargeAmountFuture = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountFutureWrap.alreadyInitialized = true;
	}
	protected DesignDisplayPage chargeAmountFutureInit() {
		if(!chargeAmountFutureWrap.alreadyInitialized) {
			_chargeAmountFuture(chargeAmountFutureWrap);
			if(chargeAmountFuture == null)
				setChargeAmountFuture(chargeAmountFutureWrap.o);
		}
		chargeAmountFutureWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static Double staticSolrChargeAmountFuture(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargeAmountFuture(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeAmountFuture(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrChargeAmountFuture(siteRequest_, DesignDisplayPage.staticSolrChargeAmountFuture(siteRequest_, DesignDisplayPage.staticSetChargeAmountFuture(siteRequest_, o)));
	}

	public Double solrChargeAmountFuture() {
		return DesignDisplayPage.staticSolrChargeAmountFuture(siteRequest_, chargeAmountFuture);
	}

	public String strChargeAmountFuture() {
		return chargeAmountFuture == null ? "" : chargeAmountFuture.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonChargeAmountFuture() {
		return chargeAmountFuture == null ? "" : chargeAmountFuture.toString();
	}

	public String nomAffichageChargeAmountFuture() {
		return null;
	}

	public String htmTooltipChargeAmountFuture() {
		return null;
	}

	public String htmChargeAmountFuture() {
		return chargeAmountFuture == null ? "" : StringEscapeUtils.escapeHtml4(strChargeAmountFuture());
	}

	/////////////////////
	// chargeAmountDue //
	/////////////////////

	/**	 The entity chargeAmountDue
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal chargeAmountDue;
	@JsonIgnore
	public Wrap<BigDecimal> chargeAmountDueWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("chargeAmountDue").o(chargeAmountDue);

	/**	<br/> The entity chargeAmountDue
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargeAmountDue">Find the entity chargeAmountDue in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargeAmountDue(Wrap<BigDecimal> c);

	public BigDecimal getChargeAmountDue() {
		return chargeAmountDue;
	}

	public void setChargeAmountDue(BigDecimal chargeAmountDue) {
		this.chargeAmountDue = chargeAmountDue;
		this.chargeAmountDueWrap.alreadyInitialized = true;
	}
	public void setChargeAmountDue(String o) {
		this.chargeAmountDue = DesignDisplayPage.staticSetChargeAmountDue(siteRequest_, o);
		this.chargeAmountDueWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetChargeAmountDue(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setChargeAmountDue(Double o) {
			this.chargeAmountDue = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountDueWrap.alreadyInitialized = true;
	}
	public void setChargeAmountDue(Integer o) {
			this.chargeAmountDue = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargeAmountDueWrap.alreadyInitialized = true;
	}
	protected DesignDisplayPage chargeAmountDueInit() {
		if(!chargeAmountDueWrap.alreadyInitialized) {
			_chargeAmountDue(chargeAmountDueWrap);
			if(chargeAmountDue == null)
				setChargeAmountDue(chargeAmountDueWrap.o);
		}
		chargeAmountDueWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static Double staticSolrChargeAmountDue(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargeAmountDue(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargeAmountDue(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrChargeAmountDue(siteRequest_, DesignDisplayPage.staticSolrChargeAmountDue(siteRequest_, DesignDisplayPage.staticSetChargeAmountDue(siteRequest_, o)));
	}

	public Double solrChargeAmountDue() {
		return DesignDisplayPage.staticSolrChargeAmountDue(siteRequest_, chargeAmountDue);
	}

	public String strChargeAmountDue() {
		return chargeAmountDue == null ? "" : chargeAmountDue.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonChargeAmountDue() {
		return chargeAmountDue == null ? "" : chargeAmountDue.toString();
	}

	public String nomAffichageChargeAmountDue() {
		return null;
	}

	public String htmTooltipChargeAmountDue() {
		return null;
	}

	public String htmChargeAmountDue() {
		return chargeAmountDue == null ? "" : StringEscapeUtils.escapeHtml4(strChargeAmountDue());
	}

	////////////////
	// chargesNow //
	////////////////

	/**	 The entity chargesNow
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal chargesNow;
	@JsonIgnore
	public Wrap<BigDecimal> chargesNowWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("chargesNow").o(chargesNow);

	/**	<br/> The entity chargesNow
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:chargesNow">Find the entity chargesNow in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _chargesNow(Wrap<BigDecimal> c);

	public BigDecimal getChargesNow() {
		return chargesNow;
	}

	public void setChargesNow(BigDecimal chargesNow) {
		this.chargesNow = chargesNow;
		this.chargesNowWrap.alreadyInitialized = true;
	}
	public void setChargesNow(String o) {
		this.chargesNow = DesignDisplayPage.staticSetChargesNow(siteRequest_, o);
		this.chargesNowWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetChargesNow(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setChargesNow(Double o) {
			this.chargesNow = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargesNowWrap.alreadyInitialized = true;
	}
	public void setChargesNow(Integer o) {
			this.chargesNow = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.chargesNowWrap.alreadyInitialized = true;
	}
	protected DesignDisplayPage chargesNowInit() {
		if(!chargesNowWrap.alreadyInitialized) {
			_chargesNow(chargesNowWrap);
			if(chargesNow == null)
				setChargesNow(chargesNowWrap.o);
		}
		chargesNowWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static Double staticSolrChargesNow(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrChargesNow(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChargesNow(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrChargesNow(siteRequest_, DesignDisplayPage.staticSolrChargesNow(siteRequest_, DesignDisplayPage.staticSetChargesNow(siteRequest_, o)));
	}

	public Double solrChargesNow() {
		return DesignDisplayPage.staticSolrChargesNow(siteRequest_, chargesNow);
	}

	public String strChargesNow() {
		return chargesNow == null ? "" : chargesNow.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonChargesNow() {
		return chargesNow == null ? "" : chargesNow.toString();
	}

	public String nomAffichageChargesNow() {
		return null;
	}

	public String htmTooltipChargesNow() {
		return null;
	}

	public String htmChargesNow() {
		return chargesNow == null ? "" : StringEscapeUtils.escapeHtml4(strChargesNow());
	}

	/////////////////////
	// paymentsCurrent //
	/////////////////////

	/**	 The entity paymentsCurrent
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paymentsCurrent;
	@JsonIgnore
	public Wrap<Boolean> paymentsCurrentWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("paymentsCurrent").o(paymentsCurrent);

	/**	<br/> The entity paymentsCurrent
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentsCurrent">Find the entity paymentsCurrent in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentsCurrent(Wrap<Boolean> c);

	public Boolean getPaymentsCurrent() {
		return paymentsCurrent;
	}

	public void setPaymentsCurrent(Boolean paymentsCurrent) {
		this.paymentsCurrent = paymentsCurrent;
		this.paymentsCurrentWrap.alreadyInitialized = true;
	}
	public void setPaymentsCurrent(String o) {
		this.paymentsCurrent = DesignDisplayPage.staticSetPaymentsCurrent(siteRequest_, o);
		this.paymentsCurrentWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPaymentsCurrent(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignDisplayPage paymentsCurrentInit() {
		if(!paymentsCurrentWrap.alreadyInitialized) {
			_paymentsCurrent(paymentsCurrentWrap);
			if(paymentsCurrent == null)
				setPaymentsCurrent(paymentsCurrentWrap.o);
		}
		paymentsCurrentWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static Boolean staticSolrPaymentsCurrent(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaymentsCurrent(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentsCurrent(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrPaymentsCurrent(siteRequest_, DesignDisplayPage.staticSolrPaymentsCurrent(siteRequest_, DesignDisplayPage.staticSetPaymentsCurrent(siteRequest_, o)));
	}

	public Boolean solrPaymentsCurrent() {
		return DesignDisplayPage.staticSolrPaymentsCurrent(siteRequest_, paymentsCurrent);
	}

	public String strPaymentsCurrent() {
		return paymentsCurrent == null ? "" : paymentsCurrent.toString();
	}

	public String jsonPaymentsCurrent() {
		return paymentsCurrent == null ? "" : paymentsCurrent.toString();
	}

	public String nomAffichagePaymentsCurrent() {
		return null;
	}

	public String htmTooltipPaymentsCurrent() {
		return null;
	}

	public String htmPaymentsCurrent() {
		return paymentsCurrent == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentsCurrent());
	}

	//////////////////
	// paymentsLate //
	//////////////////

	/**	 The entity paymentsLate
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paymentsLate;
	@JsonIgnore
	public Wrap<Boolean> paymentsLateWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("paymentsLate").o(paymentsLate);

	/**	<br/> The entity paymentsLate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentsLate">Find the entity paymentsLate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentsLate(Wrap<Boolean> c);

	public Boolean getPaymentsLate() {
		return paymentsLate;
	}

	public void setPaymentsLate(Boolean paymentsLate) {
		this.paymentsLate = paymentsLate;
		this.paymentsLateWrap.alreadyInitialized = true;
	}
	public void setPaymentsLate(String o) {
		this.paymentsLate = DesignDisplayPage.staticSetPaymentsLate(siteRequest_, o);
		this.paymentsLateWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPaymentsLate(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignDisplayPage paymentsLateInit() {
		if(!paymentsLateWrap.alreadyInitialized) {
			_paymentsLate(paymentsLateWrap);
			if(paymentsLate == null)
				setPaymentsLate(paymentsLateWrap.o);
		}
		paymentsLateWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static Boolean staticSolrPaymentsLate(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaymentsLate(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentsLate(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrPaymentsLate(siteRequest_, DesignDisplayPage.staticSolrPaymentsLate(siteRequest_, DesignDisplayPage.staticSetPaymentsLate(siteRequest_, o)));
	}

	public Boolean solrPaymentsLate() {
		return DesignDisplayPage.staticSolrPaymentsLate(siteRequest_, paymentsLate);
	}

	public String strPaymentsLate() {
		return paymentsLate == null ? "" : paymentsLate.toString();
	}

	public String jsonPaymentsLate() {
		return paymentsLate == null ? "" : paymentsLate.toString();
	}

	public String nomAffichagePaymentsLate() {
		return null;
	}

	public String htmTooltipPaymentsLate() {
		return null;
	}

	public String htmPaymentsLate() {
		return paymentsLate == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentsLate());
	}

	////////////////////////
	// paymentsLateAmount //
	////////////////////////

	/**	 The entity paymentsLateAmount
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal paymentsLateAmount;
	@JsonIgnore
	public Wrap<BigDecimal> paymentsLateAmountWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("paymentsLateAmount").o(paymentsLateAmount);

	/**	<br/> The entity paymentsLateAmount
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentsLateAmount">Find the entity paymentsLateAmount in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentsLateAmount(Wrap<BigDecimal> c);

	public BigDecimal getPaymentsLateAmount() {
		return paymentsLateAmount;
	}

	public void setPaymentsLateAmount(BigDecimal paymentsLateAmount) {
		this.paymentsLateAmount = paymentsLateAmount;
		this.paymentsLateAmountWrap.alreadyInitialized = true;
	}
	public void setPaymentsLateAmount(String o) {
		this.paymentsLateAmount = DesignDisplayPage.staticSetPaymentsLateAmount(siteRequest_, o);
		this.paymentsLateAmountWrap.alreadyInitialized = true;
	}
	public static BigDecimal staticSetPaymentsLateAmount(SiteRequestEnUS siteRequest_, String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			return new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		return null;
	}
	public void setPaymentsLateAmount(Double o) {
			this.paymentsLateAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paymentsLateAmountWrap.alreadyInitialized = true;
	}
	public void setPaymentsLateAmount(Integer o) {
			this.paymentsLateAmount = new BigDecimal(o, MathContext.DECIMAL64).setScale(2, RoundingMode.HALF_UP);
		this.paymentsLateAmountWrap.alreadyInitialized = true;
	}
	protected DesignDisplayPage paymentsLateAmountInit() {
		if(!paymentsLateAmountWrap.alreadyInitialized) {
			_paymentsLateAmount(paymentsLateAmountWrap);
			if(paymentsLateAmount == null)
				setPaymentsLateAmount(paymentsLateAmountWrap.o);
		}
		paymentsLateAmountWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static Double staticSolrPaymentsLateAmount(SiteRequestEnUS siteRequest_, BigDecimal o) {
		return o == null ? null : o.doubleValue();
	}

	public static String staticSolrStrPaymentsLateAmount(SiteRequestEnUS siteRequest_, Double o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentsLateAmount(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrPaymentsLateAmount(siteRequest_, DesignDisplayPage.staticSolrPaymentsLateAmount(siteRequest_, DesignDisplayPage.staticSetPaymentsLateAmount(siteRequest_, o)));
	}

	public Double solrPaymentsLateAmount() {
		return DesignDisplayPage.staticSolrPaymentsLateAmount(siteRequest_, paymentsLateAmount);
	}

	public String strPaymentsLateAmount() {
		return paymentsLateAmount == null ? "" : paymentsLateAmount.setScale(2, RoundingMode.HALF_UP).toString();
	}

	public String jsonPaymentsLateAmount() {
		return paymentsLateAmount == null ? "" : paymentsLateAmount.toString();
	}

	public String nomAffichagePaymentsLateAmount() {
		return null;
	}

	public String htmTooltipPaymentsLateAmount() {
		return null;
	}

	public String htmPaymentsLateAmount() {
		return paymentsLateAmount == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentsLateAmount());
	}

	///////////////////
	// paymentsAhead //
	///////////////////

	/**	 The entity paymentsAhead
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean paymentsAhead;
	@JsonIgnore
	public Wrap<Boolean> paymentsAheadWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("paymentsAhead").o(paymentsAhead);

	/**	<br/> The entity paymentsAhead
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:paymentsAhead">Find the entity paymentsAhead in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _paymentsAhead(Wrap<Boolean> c);

	public Boolean getPaymentsAhead() {
		return paymentsAhead;
	}

	public void setPaymentsAhead(Boolean paymentsAhead) {
		this.paymentsAhead = paymentsAhead;
		this.paymentsAheadWrap.alreadyInitialized = true;
	}
	public void setPaymentsAhead(String o) {
		this.paymentsAhead = DesignDisplayPage.staticSetPaymentsAhead(siteRequest_, o);
		this.paymentsAheadWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPaymentsAhead(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected DesignDisplayPage paymentsAheadInit() {
		if(!paymentsAheadWrap.alreadyInitialized) {
			_paymentsAhead(paymentsAheadWrap);
			if(paymentsAhead == null)
				setPaymentsAhead(paymentsAheadWrap.o);
		}
		paymentsAheadWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static Boolean staticSolrPaymentsAhead(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPaymentsAhead(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPaymentsAhead(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrPaymentsAhead(siteRequest_, DesignDisplayPage.staticSolrPaymentsAhead(siteRequest_, DesignDisplayPage.staticSetPaymentsAhead(siteRequest_, o)));
	}

	public Boolean solrPaymentsAhead() {
		return DesignDisplayPage.staticSolrPaymentsAhead(siteRequest_, paymentsAhead);
	}

	public String strPaymentsAhead() {
		return paymentsAhead == null ? "" : paymentsAhead.toString();
	}

	public String jsonPaymentsAhead() {
		return paymentsAhead == null ? "" : paymentsAhead.toString();
	}

	public String nomAffichagePaymentsAhead() {
		return null;
	}

	public String htmTooltipPaymentsAhead() {
		return null;
	}

	public String htmPaymentsAhead() {
		return paymentsAhead == null ? "" : StringEscapeUtils.escapeHtml4(strPaymentsAhead());
	}

	///////////////////
	// receiptSearch //
	///////////////////

	/**	 The entity receiptSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolReceipt>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolReceipt> receiptSearch = new SearchList<SchoolReceipt>();
	@JsonIgnore
	public Wrap<SearchList<SchoolReceipt>> receiptSearchWrap = new Wrap<SearchList<SchoolReceipt>>().p(this).c(SearchList.class).var("receiptSearch").o(receiptSearch);

	/**	<br/> The entity receiptSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolReceipt>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:receiptSearch">Find the entity receiptSearch in Solr</a>
	 * <br/>
	 * @param receiptSearch is the entity already constructed. 
	 **/
	protected abstract void _receiptSearch(SearchList<SchoolReceipt> l);

	public SearchList<SchoolReceipt> getReceiptSearch() {
		return receiptSearch;
	}

	public void setReceiptSearch(SearchList<SchoolReceipt> receiptSearch) {
		this.receiptSearch = receiptSearch;
		this.receiptSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolReceipt> staticSetReceiptSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage receiptSearchInit() {
		if(!receiptSearchWrap.alreadyInitialized) {
			_receiptSearch(receiptSearch);
		}
		receiptSearch.initDeepForClass(siteRequest_);
		receiptSearchWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	///////////////
	// receipts_ //
	///////////////

	/**	 The entity receipts_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolReceipt> receipts_;
	@JsonIgnore
	public Wrap<List<SchoolReceipt>> receipts_Wrap = new Wrap<List<SchoolReceipt>>().p(this).c(List.class).var("receipts_").o(receipts_);

	/**	<br/> The entity receipts_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:receipts_">Find the entity receipts_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _receipts_(Wrap<List<SchoolReceipt>> c);

	public List<SchoolReceipt> getReceipts_() {
		return receipts_;
	}

	public void setReceipts_(List<SchoolReceipt> receipts_) {
		this.receipts_ = receipts_;
		this.receipts_Wrap.alreadyInitialized = true;
	}
	public static List<SchoolReceipt> staticSetReceipts_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignDisplayPage addReceipts_(SchoolReceipt...objets) {
		for(SchoolReceipt o : objets) {
			addReceipts_(o);
		}
		return (DesignDisplayPage)this;
	}
	public DesignDisplayPage addReceipts_(SchoolReceipt o) {
		if(o != null && !receipts_.contains(o))
			this.receipts_.add(o);
		return (DesignDisplayPage)this;
	}
	protected DesignDisplayPage receipts_Init() {
		if(!receipts_Wrap.alreadyInitialized) {
			_receipts_(receipts_Wrap);
			if(receipts_ == null)
				setReceipts_(receipts_Wrap.o);
		}
		receipts_Wrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	//////////////
	// receipt_ //
	//////////////

	/**	 The entity receipt_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolReceipt receipt_;
	@JsonIgnore
	public Wrap<SchoolReceipt> receipt_Wrap = new Wrap<SchoolReceipt>().p(this).c(SchoolReceipt.class).var("receipt_").o(receipt_);

	/**	<br/> The entity receipt_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:receipt_">Find the entity receipt_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _receipt_(Wrap<SchoolReceipt> c);

	public SchoolReceipt getReceipt_() {
		return receipt_;
	}

	public void setReceipt_(SchoolReceipt receipt_) {
		this.receipt_ = receipt_;
		this.receipt_Wrap.alreadyInitialized = true;
	}
	public static SchoolReceipt staticSetReceipt_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage receipt_Init() {
		if(!receipt_Wrap.alreadyInitialized) {
			_receipt_(receipt_Wrap);
			if(receipt_ == null)
				setReceipt_(receipt_Wrap.o);
		}
		receipt_Wrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	///////////////
	// emailFrom //
	///////////////

	/**	 The entity emailFrom
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailFrom;
	@JsonIgnore
	public Wrap<String> emailFromWrap = new Wrap<String>().p(this).c(String.class).var("emailFrom").o(emailFrom);

	/**	<br/> The entity emailFrom
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailFrom">Find the entity emailFrom in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailFrom(Wrap<String> c);

	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String o) {
		this.emailFrom = DesignDisplayPage.staticSetEmailFrom(siteRequest_, o);
		this.emailFromWrap.alreadyInitialized = true;
	}
	public static String staticSetEmailFrom(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignDisplayPage emailFromInit() {
		if(!emailFromWrap.alreadyInitialized) {
			_emailFrom(emailFromWrap);
			if(emailFrom == null)
				setEmailFrom(emailFromWrap.o);
		}
		emailFromWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static String staticSolrEmailFrom(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEmailFrom(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEmailFrom(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrEmailFrom(siteRequest_, DesignDisplayPage.staticSolrEmailFrom(siteRequest_, DesignDisplayPage.staticSetEmailFrom(siteRequest_, o)));
	}

	public String solrEmailFrom() {
		return DesignDisplayPage.staticSolrEmailFrom(siteRequest_, emailFrom);
	}

	public String strEmailFrom() {
		return emailFrom == null ? "" : emailFrom;
	}

	public String jsonEmailFrom() {
		return emailFrom == null ? "" : emailFrom;
	}

	public String nomAffichageEmailFrom() {
		return null;
	}

	public String htmTooltipEmailFrom() {
		return null;
	}

	public String htmEmailFrom() {
		return emailFrom == null ? "" : StringEscapeUtils.escapeHtml4(strEmailFrom());
	}

	///////////////////
	// emailToSchool //
	///////////////////

	/**	 The entity emailToSchool
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailToSchool;
	@JsonIgnore
	public Wrap<String> emailToSchoolWrap = new Wrap<String>().p(this).c(String.class).var("emailToSchool").o(emailToSchool);

	/**	<br/> The entity emailToSchool
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailToSchool">Find the entity emailToSchool in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailToSchool(Wrap<String> c);

	public String getEmailToSchool() {
		return emailToSchool;
	}
	public void setEmailToSchool(String o) {
		this.emailToSchool = DesignDisplayPage.staticSetEmailToSchool(siteRequest_, o);
		this.emailToSchoolWrap.alreadyInitialized = true;
	}
	public static String staticSetEmailToSchool(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignDisplayPage emailToSchoolInit() {
		if(!emailToSchoolWrap.alreadyInitialized) {
			_emailToSchool(emailToSchoolWrap);
			if(emailToSchool == null)
				setEmailToSchool(emailToSchoolWrap.o);
		}
		emailToSchoolWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static String staticSolrEmailToSchool(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEmailToSchool(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEmailToSchool(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrEmailToSchool(siteRequest_, DesignDisplayPage.staticSolrEmailToSchool(siteRequest_, DesignDisplayPage.staticSetEmailToSchool(siteRequest_, o)));
	}

	public String solrEmailToSchool() {
		return DesignDisplayPage.staticSolrEmailToSchool(siteRequest_, emailToSchool);
	}

	public String strEmailToSchool() {
		return emailToSchool == null ? "" : emailToSchool;
	}

	public String jsonEmailToSchool() {
		return emailToSchool == null ? "" : emailToSchool;
	}

	public String nomAffichageEmailToSchool() {
		return null;
	}

	public String htmTooltipEmailToSchool() {
		return null;
	}

	public String htmEmailToSchool() {
		return emailToSchool == null ? "" : StringEscapeUtils.escapeHtml4(strEmailToSchool());
	}

	////////////////////
	// emailToAddress //
	////////////////////

	/**	 The entity emailToAddress
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailToAddress;
	@JsonIgnore
	public Wrap<String> emailToAddressWrap = new Wrap<String>().p(this).c(String.class).var("emailToAddress").o(emailToAddress);

	/**	<br/> The entity emailToAddress
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailToAddress">Find the entity emailToAddress in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailToAddress(Wrap<String> c);

	public String getEmailToAddress() {
		return emailToAddress;
	}
	public void setEmailToAddress(String o) {
		this.emailToAddress = DesignDisplayPage.staticSetEmailToAddress(siteRequest_, o);
		this.emailToAddressWrap.alreadyInitialized = true;
	}
	public static String staticSetEmailToAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignDisplayPage emailToAddressInit() {
		if(!emailToAddressWrap.alreadyInitialized) {
			_emailToAddress(emailToAddressWrap);
			if(emailToAddress == null)
				setEmailToAddress(emailToAddressWrap.o);
		}
		emailToAddressWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static String staticSolrEmailToAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEmailToAddress(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEmailToAddress(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrEmailToAddress(siteRequest_, DesignDisplayPage.staticSolrEmailToAddress(siteRequest_, DesignDisplayPage.staticSetEmailToAddress(siteRequest_, o)));
	}

	public String solrEmailToAddress() {
		return DesignDisplayPage.staticSolrEmailToAddress(siteRequest_, emailToAddress);
	}

	public String strEmailToAddress() {
		return emailToAddress == null ? "" : emailToAddress;
	}

	public String jsonEmailToAddress() {
		return emailToAddress == null ? "" : emailToAddress;
	}

	public String nomAffichageEmailToAddress() {
		return null;
	}

	public String htmTooltipEmailToAddress() {
		return null;
	}

	public String htmEmailToAddress() {
		return emailToAddress == null ? "" : StringEscapeUtils.escapeHtml4(strEmailToAddress());
	}

	/////////////////
	// emailToName //
	/////////////////

	/**	 The entity emailToName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailToName;
	@JsonIgnore
	public Wrap<String> emailToNameWrap = new Wrap<String>().p(this).c(String.class).var("emailToName").o(emailToName);

	/**	<br/> The entity emailToName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailToName">Find the entity emailToName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailToName(Wrap<String> c);

	public String getEmailToName() {
		return emailToName;
	}
	public void setEmailToName(String o) {
		this.emailToName = DesignDisplayPage.staticSetEmailToName(siteRequest_, o);
		this.emailToNameWrap.alreadyInitialized = true;
	}
	public static String staticSetEmailToName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignDisplayPage emailToNameInit() {
		if(!emailToNameWrap.alreadyInitialized) {
			_emailToName(emailToNameWrap);
			if(emailToName == null)
				setEmailToName(emailToNameWrap.o);
		}
		emailToNameWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static String staticSolrEmailToName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEmailToName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEmailToName(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrEmailToName(siteRequest_, DesignDisplayPage.staticSolrEmailToName(siteRequest_, DesignDisplayPage.staticSetEmailToName(siteRequest_, o)));
	}

	public String solrEmailToName() {
		return DesignDisplayPage.staticSolrEmailToName(siteRequest_, emailToName);
	}

	public String strEmailToName() {
		return emailToName == null ? "" : emailToName;
	}

	public String jsonEmailToName() {
		return emailToName == null ? "" : emailToName;
	}

	public String nomAffichageEmailToName() {
		return null;
	}

	public String htmTooltipEmailToName() {
		return null;
	}

	public String htmEmailToName() {
		return emailToName == null ? "" : StringEscapeUtils.escapeHtml4(strEmailToName());
	}

	//////////////////
	// emailMessage //
	//////////////////

	/**	 The entity emailMessage
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String emailMessage;
	@JsonIgnore
	public Wrap<String> emailMessageWrap = new Wrap<String>().p(this).c(String.class).var("emailMessage").o(emailMessage);

	/**	<br/> The entity emailMessage
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:emailMessage">Find the entity emailMessage in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _emailMessage(Wrap<String> c);

	public String getEmailMessage() {
		return emailMessage;
	}
	public void setEmailMessage(String o) {
		this.emailMessage = DesignDisplayPage.staticSetEmailMessage(siteRequest_, o);
		this.emailMessageWrap.alreadyInitialized = true;
	}
	public static String staticSetEmailMessage(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignDisplayPage emailMessageInit() {
		if(!emailMessageWrap.alreadyInitialized) {
			_emailMessage(emailMessageWrap);
			if(emailMessage == null)
				setEmailMessage(emailMessageWrap.o);
		}
		emailMessageWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static String staticSolrEmailMessage(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrEmailMessage(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEmailMessage(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrEmailMessage(siteRequest_, DesignDisplayPage.staticSolrEmailMessage(siteRequest_, DesignDisplayPage.staticSetEmailMessage(siteRequest_, o)));
	}

	public String solrEmailMessage() {
		return DesignDisplayPage.staticSolrEmailMessage(siteRequest_, emailMessage);
	}

	public String strEmailMessage() {
		return emailMessage == null ? "" : emailMessage;
	}

	public String jsonEmailMessage() {
		return emailMessage == null ? "" : emailMessage;
	}

	public String nomAffichageEmailMessage() {
		return null;
	}

	public String htmTooltipEmailMessage() {
		return null;
	}

	public String htmEmailMessage() {
		return emailMessage == null ? "" : StringEscapeUtils.escapeHtml4(strEmailMessage());
	}

	///////////////
	// schoolKey //
	///////////////

	/**	 The entity schoolKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long schoolKey;
	@JsonIgnore
	public Wrap<Long> schoolKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("schoolKey").o(schoolKey);

	/**	<br/> The entity schoolKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Find the entity schoolKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolKey(Wrap<Long> c);

	public Long getSchoolKey() {
		return schoolKey;
	}

	public void setSchoolKey(Long schoolKey) {
		this.schoolKey = schoolKey;
		this.schoolKeyWrap.alreadyInitialized = true;
	}
	public void setSchoolKey(String o) {
		this.schoolKey = DesignDisplayPage.staticSetSchoolKey(siteRequest_, o);
		this.schoolKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetSchoolKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected DesignDisplayPage schoolKeyInit() {
		if(!schoolKeyWrap.alreadyInitialized) {
			_schoolKey(schoolKeyWrap);
			if(schoolKey == null)
				setSchoolKey(schoolKeyWrap.o);
		}
		schoolKeyWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static Long staticSolrSchoolKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSchoolKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolKey(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrSchoolKey(siteRequest_, DesignDisplayPage.staticSolrSchoolKey(siteRequest_, DesignDisplayPage.staticSetSchoolKey(siteRequest_, o)));
	}

	public Long solrSchoolKey() {
		return DesignDisplayPage.staticSolrSchoolKey(siteRequest_, schoolKey);
	}

	public String strSchoolKey() {
		return schoolKey == null ? "" : schoolKey.toString();
	}

	public String jsonSchoolKey() {
		return schoolKey == null ? "" : schoolKey.toString();
	}

	public String nomAffichageSchoolKey() {
		return null;
	}

	public String htmTooltipSchoolKey() {
		return null;
	}

	public String htmSchoolKey() {
		return schoolKey == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolKey());
	}

	////////////////
	// schoolName //
	////////////////

	/**	 The entity schoolName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolName;
	@JsonIgnore
	public Wrap<String> schoolNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolName").o(schoolName);

	/**	<br/> The entity schoolName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolName">Find the entity schoolName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolName(Wrap<String> c);

	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String o) {
		this.schoolName = DesignDisplayPage.staticSetSchoolName(siteRequest_, o);
		this.schoolNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignDisplayPage schoolNameInit() {
		if(!schoolNameWrap.alreadyInitialized) {
			_schoolName(schoolNameWrap);
			if(schoolName == null)
				setSchoolName(schoolNameWrap.o);
		}
		schoolNameWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static String staticSolrSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolName(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrSchoolName(siteRequest_, DesignDisplayPage.staticSolrSchoolName(siteRequest_, DesignDisplayPage.staticSetSchoolName(siteRequest_, o)));
	}

	public String solrSchoolName() {
		return DesignDisplayPage.staticSolrSchoolName(siteRequest_, schoolName);
	}

	public String strSchoolName() {
		return schoolName == null ? "" : schoolName;
	}

	public String jsonSchoolName() {
		return schoolName == null ? "" : schoolName;
	}

	public String nomAffichageSchoolName() {
		return null;
	}

	public String htmTooltipSchoolName() {
		return null;
	}

	public String htmSchoolName() {
		return schoolName == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolName());
	}

	////////////////////////
	// schoolCompleteName //
	////////////////////////

	/**	 The entity schoolCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolCompleteName;
	@JsonIgnore
	public Wrap<String> schoolCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolCompleteName").o(schoolCompleteName);

	/**	<br/> The entity schoolCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolCompleteName">Find the entity schoolCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolCompleteName(Wrap<String> c);

	public String getSchoolCompleteName() {
		return schoolCompleteName;
	}
	public void setSchoolCompleteName(String o) {
		this.schoolCompleteName = DesignDisplayPage.staticSetSchoolCompleteName(siteRequest_, o);
		this.schoolCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignDisplayPage schoolCompleteNameInit() {
		if(!schoolCompleteNameWrap.alreadyInitialized) {
			_schoolCompleteName(schoolCompleteNameWrap);
			if(schoolCompleteName == null)
				setSchoolCompleteName(schoolCompleteNameWrap.o);
		}
		schoolCompleteNameWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static String staticSolrSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrSchoolCompleteName(siteRequest_, DesignDisplayPage.staticSolrSchoolCompleteName(siteRequest_, DesignDisplayPage.staticSetSchoolCompleteName(siteRequest_, o)));
	}

	public String solrSchoolCompleteName() {
		return DesignDisplayPage.staticSolrSchoolCompleteName(siteRequest_, schoolCompleteName);
	}

	public String strSchoolCompleteName() {
		return schoolCompleteName == null ? "" : schoolCompleteName;
	}

	public String jsonSchoolCompleteName() {
		return schoolCompleteName == null ? "" : schoolCompleteName;
	}

	public String nomAffichageSchoolCompleteName() {
		return null;
	}

	public String htmTooltipSchoolCompleteName() {
		return null;
	}

	public String htmSchoolCompleteName() {
		return schoolCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolCompleteName());
	}

	////////////////////
	// schoolLocation //
	////////////////////

	/**	 The entity schoolLocation
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolLocation;
	@JsonIgnore
	public Wrap<String> schoolLocationWrap = new Wrap<String>().p(this).c(String.class).var("schoolLocation").o(schoolLocation);

	/**	<br/> The entity schoolLocation
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolLocation">Find the entity schoolLocation in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolLocation(Wrap<String> c);

	public String getSchoolLocation() {
		return schoolLocation;
	}
	public void setSchoolLocation(String o) {
		this.schoolLocation = DesignDisplayPage.staticSetSchoolLocation(siteRequest_, o);
		this.schoolLocationWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignDisplayPage schoolLocationInit() {
		if(!schoolLocationWrap.alreadyInitialized) {
			_schoolLocation(schoolLocationWrap);
			if(schoolLocation == null)
				setSchoolLocation(schoolLocationWrap.o);
		}
		schoolLocationWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static String staticSolrSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolLocation(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrSchoolLocation(siteRequest_, DesignDisplayPage.staticSolrSchoolLocation(siteRequest_, DesignDisplayPage.staticSetSchoolLocation(siteRequest_, o)));
	}

	public String solrSchoolLocation() {
		return DesignDisplayPage.staticSolrSchoolLocation(siteRequest_, schoolLocation);
	}

	public String strSchoolLocation() {
		return schoolLocation == null ? "" : schoolLocation;
	}

	public String jsonSchoolLocation() {
		return schoolLocation == null ? "" : schoolLocation;
	}

	public String nomAffichageSchoolLocation() {
		return null;
	}

	public String htmTooltipSchoolLocation() {
		return null;
	}

	public String htmSchoolLocation() {
		return schoolLocation == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolLocation());
	}

	///////////////////
	// schoolAddress //
	///////////////////

	/**	 The entity schoolAddress
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolAddress;
	@JsonIgnore
	public Wrap<String> schoolAddressWrap = new Wrap<String>().p(this).c(String.class).var("schoolAddress").o(schoolAddress);

	/**	<br/> The entity schoolAddress
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAddress">Find the entity schoolAddress in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolAddress(Wrap<String> c);

	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String o) {
		this.schoolAddress = DesignDisplayPage.staticSetSchoolAddress(siteRequest_, o);
		this.schoolAddressWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignDisplayPage schoolAddressInit() {
		if(!schoolAddressWrap.alreadyInitialized) {
			_schoolAddress(schoolAddressWrap);
			if(schoolAddress == null)
				setSchoolAddress(schoolAddressWrap.o);
		}
		schoolAddressWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static String staticSolrSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolAddress(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrSchoolAddress(siteRequest_, DesignDisplayPage.staticSolrSchoolAddress(siteRequest_, DesignDisplayPage.staticSetSchoolAddress(siteRequest_, o)));
	}

	public String solrSchoolAddress() {
		return DesignDisplayPage.staticSolrSchoolAddress(siteRequest_, schoolAddress);
	}

	public String strSchoolAddress() {
		return schoolAddress == null ? "" : schoolAddress;
	}

	public String jsonSchoolAddress() {
		return schoolAddress == null ? "" : schoolAddress;
	}

	public String nomAffichageSchoolAddress() {
		return null;
	}

	public String htmTooltipSchoolAddress() {
		return null;
	}

	public String htmSchoolAddress() {
		return schoolAddress == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolAddress());
	}

	///////////////////////
	// schoolPhoneNumber //
	///////////////////////

	/**	 The entity schoolPhoneNumber
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolPhoneNumber;
	@JsonIgnore
	public Wrap<String> schoolPhoneNumberWrap = new Wrap<String>().p(this).c(String.class).var("schoolPhoneNumber").o(schoolPhoneNumber);

	/**	<br/> The entity schoolPhoneNumber
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPhoneNumber">Find the entity schoolPhoneNumber in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolPhoneNumber(Wrap<String> c);

	public String getSchoolPhoneNumber() {
		return schoolPhoneNumber;
	}
	public void setSchoolPhoneNumber(String o) {
		this.schoolPhoneNumber = DesignDisplayPage.staticSetSchoolPhoneNumber(siteRequest_, o);
		this.schoolPhoneNumberWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignDisplayPage schoolPhoneNumberInit() {
		if(!schoolPhoneNumberWrap.alreadyInitialized) {
			_schoolPhoneNumber(schoolPhoneNumberWrap);
			if(schoolPhoneNumber == null)
				setSchoolPhoneNumber(schoolPhoneNumberWrap.o);
		}
		schoolPhoneNumberWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static String staticSolrSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrSchoolPhoneNumber(siteRequest_, DesignDisplayPage.staticSolrSchoolPhoneNumber(siteRequest_, DesignDisplayPage.staticSetSchoolPhoneNumber(siteRequest_, o)));
	}

	public String solrSchoolPhoneNumber() {
		return DesignDisplayPage.staticSolrSchoolPhoneNumber(siteRequest_, schoolPhoneNumber);
	}

	public String strSchoolPhoneNumber() {
		return schoolPhoneNumber == null ? "" : schoolPhoneNumber;
	}

	public String jsonSchoolPhoneNumber() {
		return schoolPhoneNumber == null ? "" : schoolPhoneNumber;
	}

	public String nomAffichageSchoolPhoneNumber() {
		return null;
	}

	public String htmTooltipSchoolPhoneNumber() {
		return null;
	}

	public String htmSchoolPhoneNumber() {
		return schoolPhoneNumber == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolPhoneNumber());
	}

	/////////////////////////////
	// schoolAdministratorName //
	/////////////////////////////

	/**	 The entity schoolAdministratorName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolAdministratorName;
	@JsonIgnore
	public Wrap<String> schoolAdministratorNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolAdministratorName").o(schoolAdministratorName);

	/**	<br/> The entity schoolAdministratorName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAdministratorName">Find the entity schoolAdministratorName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolAdministratorName(Wrap<String> c);

	public String getSchoolAdministratorName() {
		return schoolAdministratorName;
	}
	public void setSchoolAdministratorName(String o) {
		this.schoolAdministratorName = DesignDisplayPage.staticSetSchoolAdministratorName(siteRequest_, o);
		this.schoolAdministratorNameWrap.alreadyInitialized = true;
	}
	public static String staticSetSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected DesignDisplayPage schoolAdministratorNameInit() {
		if(!schoolAdministratorNameWrap.alreadyInitialized) {
			_schoolAdministratorName(schoolAdministratorNameWrap);
			if(schoolAdministratorName == null)
				setSchoolAdministratorName(schoolAdministratorNameWrap.o);
		}
		schoolAdministratorNameWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static String staticSolrSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolAdministratorName(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrSchoolAdministratorName(siteRequest_, DesignDisplayPage.staticSolrSchoolAdministratorName(siteRequest_, DesignDisplayPage.staticSetSchoolAdministratorName(siteRequest_, o)));
	}

	public String solrSchoolAdministratorName() {
		return DesignDisplayPage.staticSolrSchoolAdministratorName(siteRequest_, schoolAdministratorName);
	}

	public String strSchoolAdministratorName() {
		return schoolAdministratorName == null ? "" : schoolAdministratorName;
	}

	public String jsonSchoolAdministratorName() {
		return schoolAdministratorName == null ? "" : schoolAdministratorName;
	}

	public String nomAffichageSchoolAdministratorName() {
		return null;
	}

	public String htmTooltipSchoolAdministratorName() {
		return null;
	}

	public String htmSchoolAdministratorName() {
		return schoolAdministratorName == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolAdministratorName());
	}

	///////////////
	// yearStart //
	///////////////

	/**	 The entity yearStart
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer yearStart;
	@JsonIgnore
	public Wrap<Integer> yearStartWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearStart").o(yearStart);

	/**	<br/> The entity yearStart
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Find the entity yearStart in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearStart(Wrap<Integer> c);

	public Integer getYearStart() {
		return yearStart;
	}

	public void setYearStart(Integer yearStart) {
		this.yearStart = yearStart;
		this.yearStartWrap.alreadyInitialized = true;
	}
	public void setYearStart(String o) {
		this.yearStart = DesignDisplayPage.staticSetYearStart(siteRequest_, o);
		this.yearStartWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearStart(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected DesignDisplayPage yearStartInit() {
		if(!yearStartWrap.alreadyInitialized) {
			_yearStart(yearStartWrap);
			if(yearStart == null)
				setYearStart(yearStartWrap.o);
		}
		yearStartWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static Integer staticSolrYearStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearStart(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearStart(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrYearStart(siteRequest_, DesignDisplayPage.staticSolrYearStart(siteRequest_, DesignDisplayPage.staticSetYearStart(siteRequest_, o)));
	}

	public Integer solrYearStart() {
		return DesignDisplayPage.staticSolrYearStart(siteRequest_, yearStart);
	}

	public String strYearStart() {
		return yearStart == null ? "" : yearStart.toString();
	}

	public String jsonYearStart() {
		return yearStart == null ? "" : yearStart.toString();
	}

	public String nomAffichageYearStart() {
		return null;
	}

	public String htmTooltipYearStart() {
		return null;
	}

	public String htmYearStart() {
		return yearStart == null ? "" : StringEscapeUtils.escapeHtml4(strYearStart());
	}

	/////////////
	// yearEnd //
	/////////////

	/**	 The entity yearEnd
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer yearEnd;
	@JsonIgnore
	public Wrap<Integer> yearEndWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearEnd").o(yearEnd);

	/**	<br/> The entity yearEnd
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Find the entity yearEnd in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _yearEnd(Wrap<Integer> c);

	public Integer getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(Integer yearEnd) {
		this.yearEnd = yearEnd;
		this.yearEndWrap.alreadyInitialized = true;
	}
	public void setYearEnd(String o) {
		this.yearEnd = DesignDisplayPage.staticSetYearEnd(siteRequest_, o);
		this.yearEndWrap.alreadyInitialized = true;
	}
	public static Integer staticSetYearEnd(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected DesignDisplayPage yearEndInit() {
		if(!yearEndWrap.alreadyInitialized) {
			_yearEnd(yearEndWrap);
			if(yearEnd == null)
				setYearEnd(yearEndWrap.o);
		}
		yearEndWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static Integer staticSolrYearEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrYearEnd(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearEnd(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrYearEnd(siteRequest_, DesignDisplayPage.staticSolrYearEnd(siteRequest_, DesignDisplayPage.staticSetYearEnd(siteRequest_, o)));
	}

	public Integer solrYearEnd() {
		return DesignDisplayPage.staticSolrYearEnd(siteRequest_, yearEnd);
	}

	public String strYearEnd() {
		return yearEnd == null ? "" : yearEnd.toString();
	}

	public String jsonYearEnd() {
		return yearEnd == null ? "" : yearEnd.toString();
	}

	public String nomAffichageYearEnd() {
		return null;
	}

	public String htmTooltipYearEnd() {
		return null;
	}

	public String htmYearEnd() {
		return yearEnd == null ? "" : StringEscapeUtils.escapeHtml4(strYearEnd());
	}

	/////////////////////
	// seasonStartDate //
	/////////////////////

	/**	 The entity seasonStartDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate seasonStartDate;
	@JsonIgnore
	public Wrap<LocalDate> seasonStartDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("seasonStartDate").o(seasonStartDate);

	/**	<br/> The entity seasonStartDate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonStartDate">Find the entity seasonStartDate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _seasonStartDate(Wrap<LocalDate> c);

	public LocalDate getSeasonStartDate() {
		return seasonStartDate;
	}

	public void setSeasonStartDate(LocalDate seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	public void setSeasonStartDate(Instant o) {
		this.seasonStartDate = o == null ? null : LocalDate.from(o);
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setSeasonStartDate(String o) {
		this.seasonStartDate = DesignDisplayPage.staticSetSeasonStartDate(siteRequest_, o);
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetSeasonStartDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setSeasonStartDate(Date o) {
		this.seasonStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.seasonStartDateWrap.alreadyInitialized = true;
	}
	protected DesignDisplayPage seasonStartDateInit() {
		if(!seasonStartDateWrap.alreadyInitialized) {
			_seasonStartDate(seasonStartDateWrap);
			if(seasonStartDate == null)
				setSeasonStartDate(seasonStartDateWrap.o);
		}
		seasonStartDateWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	public static Date staticSolrSeasonStartDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrSeasonStartDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqSeasonStartDate(SiteRequestEnUS siteRequest_, String o) {
		return DesignDisplayPage.staticSolrStrSeasonStartDate(siteRequest_, DesignDisplayPage.staticSolrSeasonStartDate(siteRequest_, DesignDisplayPage.staticSetSeasonStartDate(siteRequest_, o)));
	}

	public Date solrSeasonStartDate() {
		return DesignDisplayPage.staticSolrSeasonStartDate(siteRequest_, seasonStartDate);
	}

	public String strSeasonStartDate() {
		return seasonStartDate == null ? "" : seasonStartDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonSeasonStartDate() {
		return seasonStartDate == null ? "" : seasonStartDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSeasonStartDate() {
		return null;
	}

	public String htmTooltipSeasonStartDate() {
		return null;
	}

	public String htmSeasonStartDate() {
		return seasonStartDate == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonStartDate());
	}

	//////////
	// mom_ //
	//////////

	/**	 The entity mom_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolMom mom_;
	@JsonIgnore
	public Wrap<SchoolMom> mom_Wrap = new Wrap<SchoolMom>().p(this).c(SchoolMom.class).var("mom_").o(mom_);

	/**	<br/> The entity mom_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:mom_">Find the entity mom_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _mom_(Wrap<SchoolMom> c);

	public SchoolMom getMom_() {
		return mom_;
	}

	public void setMom_(SchoolMom mom_) {
		this.mom_ = mom_;
		this.mom_Wrap.alreadyInitialized = true;
	}
	public static SchoolMom staticSetMom_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage mom_Init() {
		if(!mom_Wrap.alreadyInitialized) {
			_mom_(mom_Wrap);
			if(mom_ == null)
				setMom_(mom_Wrap.o);
		}
		mom_Wrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	//////////
	// dad_ //
	//////////

	/**	 The entity dad_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolDad dad_;
	@JsonIgnore
	public Wrap<SchoolDad> dad_Wrap = new Wrap<SchoolDad>().p(this).c(SchoolDad.class).var("dad_").o(dad_);

	/**	<br/> The entity dad_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dad_">Find the entity dad_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _dad_(Wrap<SchoolDad> c);

	public SchoolDad getDad_() {
		return dad_;
	}

	public void setDad_(SchoolDad dad_) {
		this.dad_ = dad_;
		this.dad_Wrap.alreadyInitialized = true;
	}
	public static SchoolDad staticSetDad_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage dad_Init() {
		if(!dad_Wrap.alreadyInitialized) {
			_dad_(dad_Wrap);
			if(dad_ == null)
				setDad_(dad_Wrap.o);
		}
		dad_Wrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	///////////////
	// guardian_ //
	///////////////

	/**	 The entity guardian_
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolGuardian guardian_;
	@JsonIgnore
	public Wrap<SchoolGuardian> guardian_Wrap = new Wrap<SchoolGuardian>().p(this).c(SchoolGuardian.class).var("guardian_").o(guardian_);

	/**	<br/> The entity guardian_
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardian_">Find the entity guardian_ in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _guardian_(Wrap<SchoolGuardian> c);

	public SchoolGuardian getGuardian_() {
		return guardian_;
	}

	public void setGuardian_(SchoolGuardian guardian_) {
		this.guardian_ = guardian_;
		this.guardian_Wrap.alreadyInitialized = true;
	}
	public static SchoolGuardian staticSetGuardian_(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage guardian_Init() {
		if(!guardian_Wrap.alreadyInitialized) {
			_guardian_(guardian_Wrap);
			if(guardian_ == null)
				setGuardian_(guardian_Wrap.o);
		}
		guardian_Wrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	/////////////////
	// blockSearch //
	/////////////////

	/**	 The entity blockSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolBlock>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolBlock> blockSearch = new SearchList<SchoolBlock>();
	@JsonIgnore
	public Wrap<SearchList<SchoolBlock>> blockSearchWrap = new Wrap<SearchList<SchoolBlock>>().p(this).c(SearchList.class).var("blockSearch").o(blockSearch);

	/**	<br/> The entity blockSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolBlock>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockSearch">Find the entity blockSearch in Solr</a>
	 * <br/>
	 * @param blockSearch is the entity already constructed. 
	 **/
	protected abstract void _blockSearch(SearchList<SchoolBlock> l);

	public SearchList<SchoolBlock> getBlockSearch() {
		return blockSearch;
	}

	public void setBlockSearch(SearchList<SchoolBlock> blockSearch) {
		this.blockSearch = blockSearch;
		this.blockSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<SchoolBlock> staticSetBlockSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage blockSearchInit() {
		if(!blockSearchWrap.alreadyInitialized) {
			_blockSearch(blockSearch);
		}
		blockSearch.initDeepForClass(siteRequest_);
		blockSearchWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	////////////
	// blocks //
	////////////

	/**	 The entity blocks
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolBlock> blocks;
	@JsonIgnore
	public Wrap<List<SchoolBlock>> blocksWrap = new Wrap<List<SchoolBlock>>().p(this).c(List.class).var("blocks").o(blocks);

	/**	<br/> The entity blocks
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blocks">Find the entity blocks in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blocks(Wrap<List<SchoolBlock>> c);

	public List<SchoolBlock> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<SchoolBlock> blocks) {
		this.blocks = blocks;
		this.blocksWrap.alreadyInitialized = true;
	}
	public static List<SchoolBlock> staticSetBlocks(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignDisplayPage addBlocks(SchoolBlock...objets) {
		for(SchoolBlock o : objets) {
			addBlocks(o);
		}
		return (DesignDisplayPage)this;
	}
	public DesignDisplayPage addBlocks(SchoolBlock o) {
		if(o != null && !blocks.contains(o))
			this.blocks.add(o);
		return (DesignDisplayPage)this;
	}
	protected DesignDisplayPage blocksInit() {
		if(!blocksWrap.alreadyInitialized) {
			_blocks(blocksWrap);
			if(blocks == null)
				setBlocks(blocksWrap.o);
		}
		blocksWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	//////////////////
	// seasonBlocks //
	//////////////////

	/**	 The entity seasonBlocks
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolBlock>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolBlock> seasonBlocks = new ArrayList<SchoolBlock>();
	@JsonIgnore
	public Wrap<List<SchoolBlock>> seasonBlocksWrap = new Wrap<List<SchoolBlock>>().p(this).c(List.class).var("seasonBlocks").o(seasonBlocks);

	/**	<br/> The entity seasonBlocks
	 *  It is constructed before being initialized with the constructor by default List<SchoolBlock>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonBlocks">Find the entity seasonBlocks in Solr</a>
	 * <br/>
	 * @param seasonBlocks is the entity already constructed. 
	 **/
	protected abstract void _seasonBlocks(List<SchoolBlock> l);

	public List<SchoolBlock> getSeasonBlocks() {
		return seasonBlocks;
	}

	public void setSeasonBlocks(List<SchoolBlock> seasonBlocks) {
		this.seasonBlocks = seasonBlocks;
		this.seasonBlocksWrap.alreadyInitialized = true;
	}
	public static List<SchoolBlock> staticSetSeasonBlocks(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignDisplayPage addSeasonBlocks(SchoolBlock...objets) {
		for(SchoolBlock o : objets) {
			addSeasonBlocks(o);
		}
		return (DesignDisplayPage)this;
	}
	public DesignDisplayPage addSeasonBlocks(SchoolBlock o) {
		if(o != null && !seasonBlocks.contains(o))
			this.seasonBlocks.add(o);
		return (DesignDisplayPage)this;
	}
	protected DesignDisplayPage seasonBlocksInit() {
		if(!seasonBlocksWrap.alreadyInitialized) {
			_seasonBlocks(seasonBlocks);
		}
		seasonBlocksWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	/////////////////
	// seasonBlock //
	/////////////////

	/**	 The entity seasonBlock
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolBlock seasonBlock;
	@JsonIgnore
	public Wrap<SchoolBlock> seasonBlockWrap = new Wrap<SchoolBlock>().p(this).c(SchoolBlock.class).var("seasonBlock").o(seasonBlock);

	/**	<br/> The entity seasonBlock
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonBlock">Find the entity seasonBlock in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _seasonBlock(Wrap<SchoolBlock> c);

	public SchoolBlock getSeasonBlock() {
		return seasonBlock;
	}

	public void setSeasonBlock(SchoolBlock seasonBlock) {
		this.seasonBlock = seasonBlock;
		this.seasonBlockWrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetSeasonBlock(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage seasonBlockInit() {
		if(!seasonBlockWrap.alreadyInitialized) {
			_seasonBlock(seasonBlockWrap);
			if(seasonBlock == null)
				setSeasonBlock(seasonBlockWrap.o);
		}
		if(seasonBlock != null)
			seasonBlock.initDeepForClass(siteRequest_);
		seasonBlockWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	//////////////////
	// sessionBlock //
	//////////////////

	/**	 The entity sessionBlock
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolBlock sessionBlock;
	@JsonIgnore
	public Wrap<SchoolBlock> sessionBlockWrap = new Wrap<SchoolBlock>().p(this).c(SchoolBlock.class).var("sessionBlock").o(sessionBlock);

	/**	<br/> The entity sessionBlock
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionBlock">Find the entity sessionBlock in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _sessionBlock(Wrap<SchoolBlock> c);

	public SchoolBlock getSessionBlock() {
		return sessionBlock;
	}

	public void setSessionBlock(SchoolBlock sessionBlock) {
		this.sessionBlock = sessionBlock;
		this.sessionBlockWrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetSessionBlock(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage sessionBlockInit() {
		if(!sessionBlockWrap.alreadyInitialized) {
			_sessionBlock(sessionBlockWrap);
			if(sessionBlock == null)
				setSessionBlock(sessionBlockWrap.o);
		}
		if(sessionBlock != null)
			sessionBlock.initDeepForClass(siteRequest_);
		sessionBlockWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	//////////////
	// ageBlock //
	//////////////

	/**	 The entity ageBlock
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolBlock ageBlock;
	@JsonIgnore
	public Wrap<SchoolBlock> ageBlockWrap = new Wrap<SchoolBlock>().p(this).c(SchoolBlock.class).var("ageBlock").o(ageBlock);

	/**	<br/> The entity ageBlock
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageBlock">Find the entity ageBlock in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _ageBlock(Wrap<SchoolBlock> c);

	public SchoolBlock getAgeBlock() {
		return ageBlock;
	}

	public void setAgeBlock(SchoolBlock ageBlock) {
		this.ageBlock = ageBlock;
		this.ageBlockWrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetAgeBlock(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage ageBlockInit() {
		if(!ageBlockWrap.alreadyInitialized) {
			_ageBlock(ageBlockWrap);
			if(ageBlock == null)
				setAgeBlock(ageBlockWrap.o);
		}
		if(ageBlock != null)
			ageBlock.initDeepForClass(siteRequest_);
		ageBlockWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	////////////////
	// blockBlock //
	////////////////

	/**	 The entity blockBlock
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SchoolBlock blockBlock;
	@JsonIgnore
	public Wrap<SchoolBlock> blockBlockWrap = new Wrap<SchoolBlock>().p(this).c(SchoolBlock.class).var("blockBlock").o(blockBlock);

	/**	<br/> The entity blockBlock
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:blockBlock">Find the entity blockBlock in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _blockBlock(Wrap<SchoolBlock> c);

	public SchoolBlock getBlockBlock() {
		return blockBlock;
	}

	public void setBlockBlock(SchoolBlock blockBlock) {
		this.blockBlock = blockBlock;
		this.blockBlockWrap.alreadyInitialized = true;
	}
	public static SchoolBlock staticSetBlockBlock(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage blockBlockInit() {
		if(!blockBlockWrap.alreadyInitialized) {
			_blockBlock(blockBlockWrap);
			if(blockBlock == null)
				setBlockBlock(blockBlockWrap.o);
		}
		if(blockBlock != null)
			blockBlock.initDeepForClass(siteRequest_);
		blockBlockWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	////////////////////
	// htmlPartSearch //
	////////////////////

	/**	 The entity htmlPartSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<HtmlPart>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected SearchList<HtmlPart> htmlPartSearch = new SearchList<HtmlPart>();
	@JsonIgnore
	public Wrap<SearchList<HtmlPart>> htmlPartSearchWrap = new Wrap<SearchList<HtmlPart>>().p(this).c(SearchList.class).var("htmlPartSearch").o(htmlPartSearch);

	/**	<br/> The entity htmlPartSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<HtmlPart>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlPartSearch">Find the entity htmlPartSearch in Solr</a>
	 * <br/>
	 * @param htmlPartSearch is the entity already constructed. 
	 **/
	protected abstract void _htmlPartSearch(SearchList<HtmlPart> l);

	public SearchList<HtmlPart> getHtmlPartSearch() {
		return htmlPartSearch;
	}

	public void setHtmlPartSearch(SearchList<HtmlPart> htmlPartSearch) {
		this.htmlPartSearch = htmlPartSearch;
		this.htmlPartSearchWrap.alreadyInitialized = true;
	}
	public static SearchList<HtmlPart> staticSetHtmlPartSearch(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	protected DesignDisplayPage htmlPartSearchInit() {
		if(!htmlPartSearchWrap.alreadyInitialized) {
			_htmlPartSearch(htmlPartSearch);
		}
		htmlPartSearch.initDeepForClass(siteRequest_);
		htmlPartSearchWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	//////////////////
	// htmlPartList //
	//////////////////

	/**	 The entity htmlPartList
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<HtmlPart> htmlPartList;
	@JsonIgnore
	public Wrap<List<HtmlPart>> htmlPartListWrap = new Wrap<List<HtmlPart>>().p(this).c(List.class).var("htmlPartList").o(htmlPartList);

	/**	<br/> The entity htmlPartList
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.design.DesignDisplayPage&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:htmlPartList">Find the entity htmlPartList in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _htmlPartList(Wrap<List<HtmlPart>> c);

	public List<HtmlPart> getHtmlPartList() {
		return htmlPartList;
	}

	public void setHtmlPartList(List<HtmlPart> htmlPartList) {
		this.htmlPartList = htmlPartList;
		this.htmlPartListWrap.alreadyInitialized = true;
	}
	public static List<HtmlPart> staticSetHtmlPartList(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public DesignDisplayPage addHtmlPartList(HtmlPart...objets) {
		for(HtmlPart o : objets) {
			addHtmlPartList(o);
		}
		return (DesignDisplayPage)this;
	}
	public DesignDisplayPage addHtmlPartList(HtmlPart o) {
		if(o != null && !htmlPartList.contains(o))
			this.htmlPartList.add(o);
		return (DesignDisplayPage)this;
	}
	protected DesignDisplayPage htmlPartListInit() {
		if(!htmlPartListWrap.alreadyInitialized) {
			_htmlPartList(htmlPartListWrap);
			if(htmlPartList == null)
				setHtmlPartList(htmlPartListWrap.o);
		}
		htmlPartListWrap.alreadyInitialized(true);
		return (DesignDisplayPage)this;
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedDesignDisplayPage = false;

	public DesignDisplayPage initDeepDesignDisplayPage(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedDesignDisplayPage) {
			alreadyInitializedDesignDisplayPage = true;
			initDeepDesignDisplayPage();
		}
		return (DesignDisplayPage)this;
	}

	public void initDeepDesignDisplayPage() {
		initDesignDisplayPage();
		super.initDeepDesignDisplayGenPage(siteRequest_);
	}

	public void initDesignDisplayPage() {
		pageDesignIdInit();
		enrollmentSearchInit();
		schoolEnrollmentInit();
		enrollmentsInit();
		enrollmentBlocksInit();
		enrollmentGroupsInit();
		enrollmentBlockInit();
		enrollmentGroupInit();
		enrollmentEnrollmentInit();
		yearSearchInit();
		year_Init();
		yearKeyInit();
		yearVarInit();
		schoolSearchInit();
		school_Init();
		paymentSearchInit();
		payments_Init();
		payment_Init();
		paymentFacetsInit();
		paymentLastStrInit();
		paymentAmountInit();
		chargeAmountInit();
		chargeAmountFutureInit();
		chargeAmountDueInit();
		chargesNowInit();
		paymentsCurrentInit();
		paymentsLateInit();
		paymentsLateAmountInit();
		paymentsAheadInit();
		receiptSearchInit();
		receipts_Init();
		receipt_Init();
		emailFromInit();
		emailToSchoolInit();
		emailToAddressInit();
		emailToNameInit();
		emailMessageInit();
		schoolKeyInit();
		schoolNameInit();
		schoolCompleteNameInit();
		schoolLocationInit();
		schoolAddressInit();
		schoolPhoneNumberInit();
		schoolAdministratorNameInit();
		yearStartInit();
		yearEndInit();
		seasonStartDateInit();
		mom_Init();
		dad_Init();
		guardian_Init();
		blockSearchInit();
		blocksInit();
		seasonBlocksInit();
		seasonBlockInit();
		sessionBlockInit();
		ageBlockInit();
		blockBlockInit();
		htmlPartSearchInit();
		htmlPartListInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepDesignDisplayPage(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestDesignDisplayPage(SiteRequestEnUS siteRequest_) {
			super.siteRequestDesignDisplayGenPage(siteRequest_);
		if(enrollmentSearch != null)
			enrollmentSearch.setSiteRequest_(siteRequest_);
		if(schoolEnrollment != null)
			schoolEnrollment.setSiteRequest_(siteRequest_);
		if(enrollmentBlock != null)
			enrollmentBlock.setSiteRequest_(siteRequest_);
		if(enrollmentGroup != null)
			enrollmentGroup.setSiteRequest_(siteRequest_);
		if(enrollmentEnrollment != null)
			enrollmentEnrollment.setSiteRequest_(siteRequest_);
		if(yearSearch != null)
			yearSearch.setSiteRequest_(siteRequest_);
		if(schoolSearch != null)
			schoolSearch.setSiteRequest_(siteRequest_);
		if(paymentSearch != null)
			paymentSearch.setSiteRequest_(siteRequest_);
		if(receiptSearch != null)
			receiptSearch.setSiteRequest_(siteRequest_);
		if(blockSearch != null)
			blockSearch.setSiteRequest_(siteRequest_);
		if(seasonBlock != null)
			seasonBlock.setSiteRequest_(siteRequest_);
		if(sessionBlock != null)
			sessionBlock.setSiteRequest_(siteRequest_);
		if(ageBlock != null)
			ageBlock.setSiteRequest_(siteRequest_);
		if(blockBlock != null)
			blockBlock.setSiteRequest_(siteRequest_);
		if(htmlPartSearch != null)
			htmlPartSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestDesignDisplayPage(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainDesignDisplayPage(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainDesignDisplayPage(String var) {
		DesignDisplayPage oDesignDisplayPage = (DesignDisplayPage)this;
		switch(var) {
			case "pageDesignId":
				return oDesignDisplayPage.pageDesignId;
			case "enrollmentSearch":
				return oDesignDisplayPage.enrollmentSearch;
			case "schoolEnrollment":
				return oDesignDisplayPage.schoolEnrollment;
			case "enrollments":
				return oDesignDisplayPage.enrollments;
			case "enrollmentBlocks":
				return oDesignDisplayPage.enrollmentBlocks;
			case "enrollmentGroups":
				return oDesignDisplayPage.enrollmentGroups;
			case "enrollmentBlock":
				return oDesignDisplayPage.enrollmentBlock;
			case "enrollmentGroup":
				return oDesignDisplayPage.enrollmentGroup;
			case "enrollmentEnrollment":
				return oDesignDisplayPage.enrollmentEnrollment;
			case "yearSearch":
				return oDesignDisplayPage.yearSearch;
			case "year_":
				return oDesignDisplayPage.year_;
			case "yearKey":
				return oDesignDisplayPage.yearKey;
			case "yearVar":
				return oDesignDisplayPage.yearVar;
			case "schoolSearch":
				return oDesignDisplayPage.schoolSearch;
			case "school_":
				return oDesignDisplayPage.school_;
			case "paymentSearch":
				return oDesignDisplayPage.paymentSearch;
			case "payments_":
				return oDesignDisplayPage.payments_;
			case "payment_":
				return oDesignDisplayPage.payment_;
			case "paymentFacets":
				return oDesignDisplayPage.paymentFacets;
			case "paymentLastStr":
				return oDesignDisplayPage.paymentLastStr;
			case "paymentAmount":
				return oDesignDisplayPage.paymentAmount;
			case "chargeAmount":
				return oDesignDisplayPage.chargeAmount;
			case "chargeAmountFuture":
				return oDesignDisplayPage.chargeAmountFuture;
			case "chargeAmountDue":
				return oDesignDisplayPage.chargeAmountDue;
			case "chargesNow":
				return oDesignDisplayPage.chargesNow;
			case "paymentsCurrent":
				return oDesignDisplayPage.paymentsCurrent;
			case "paymentsLate":
				return oDesignDisplayPage.paymentsLate;
			case "paymentsLateAmount":
				return oDesignDisplayPage.paymentsLateAmount;
			case "paymentsAhead":
				return oDesignDisplayPage.paymentsAhead;
			case "receiptSearch":
				return oDesignDisplayPage.receiptSearch;
			case "receipts_":
				return oDesignDisplayPage.receipts_;
			case "receipt_":
				return oDesignDisplayPage.receipt_;
			case "emailFrom":
				return oDesignDisplayPage.emailFrom;
			case "emailToSchool":
				return oDesignDisplayPage.emailToSchool;
			case "emailToAddress":
				return oDesignDisplayPage.emailToAddress;
			case "emailToName":
				return oDesignDisplayPage.emailToName;
			case "emailMessage":
				return oDesignDisplayPage.emailMessage;
			case "schoolKey":
				return oDesignDisplayPage.schoolKey;
			case "schoolName":
				return oDesignDisplayPage.schoolName;
			case "schoolCompleteName":
				return oDesignDisplayPage.schoolCompleteName;
			case "schoolLocation":
				return oDesignDisplayPage.schoolLocation;
			case "schoolAddress":
				return oDesignDisplayPage.schoolAddress;
			case "schoolPhoneNumber":
				return oDesignDisplayPage.schoolPhoneNumber;
			case "schoolAdministratorName":
				return oDesignDisplayPage.schoolAdministratorName;
			case "yearStart":
				return oDesignDisplayPage.yearStart;
			case "yearEnd":
				return oDesignDisplayPage.yearEnd;
			case "seasonStartDate":
				return oDesignDisplayPage.seasonStartDate;
			case "mom_":
				return oDesignDisplayPage.mom_;
			case "dad_":
				return oDesignDisplayPage.dad_;
			case "guardian_":
				return oDesignDisplayPage.guardian_;
			case "blockSearch":
				return oDesignDisplayPage.blockSearch;
			case "blocks":
				return oDesignDisplayPage.blocks;
			case "seasonBlocks":
				return oDesignDisplayPage.seasonBlocks;
			case "seasonBlock":
				return oDesignDisplayPage.seasonBlock;
			case "sessionBlock":
				return oDesignDisplayPage.sessionBlock;
			case "ageBlock":
				return oDesignDisplayPage.ageBlock;
			case "blockBlock":
				return oDesignDisplayPage.blockBlock;
			case "htmlPartSearch":
				return oDesignDisplayPage.htmlPartSearch;
			case "htmlPartList":
				return oDesignDisplayPage.htmlPartList;
			default:
				return super.obtainDesignDisplayGenPage(var);
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
				o = attributeDesignDisplayPage(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeDesignDisplayPage(String var, Object val) {
		DesignDisplayPage oDesignDisplayPage = (DesignDisplayPage)this;
		switch(var) {
			default:
				return super.attributeDesignDisplayGenPage(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetDesignDisplayPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSetDesignDisplayPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "pageDesignId":
			return DesignDisplayPage.staticSetPageDesignId(siteRequest_, o);
		case "yearKey":
			return DesignDisplayPage.staticSetYearKey(siteRequest_, o);
		case "yearVar":
			return DesignDisplayPage.staticSetYearVar(siteRequest_, o);
		case "paymentLastStr":
			return DesignDisplayPage.staticSetPaymentLastStr(siteRequest_, o);
		case "paymentAmount":
			return DesignDisplayPage.staticSetPaymentAmount(siteRequest_, o);
		case "chargeAmount":
			return DesignDisplayPage.staticSetChargeAmount(siteRequest_, o);
		case "chargeAmountFuture":
			return DesignDisplayPage.staticSetChargeAmountFuture(siteRequest_, o);
		case "chargeAmountDue":
			return DesignDisplayPage.staticSetChargeAmountDue(siteRequest_, o);
		case "chargesNow":
			return DesignDisplayPage.staticSetChargesNow(siteRequest_, o);
		case "paymentsCurrent":
			return DesignDisplayPage.staticSetPaymentsCurrent(siteRequest_, o);
		case "paymentsLate":
			return DesignDisplayPage.staticSetPaymentsLate(siteRequest_, o);
		case "paymentsLateAmount":
			return DesignDisplayPage.staticSetPaymentsLateAmount(siteRequest_, o);
		case "paymentsAhead":
			return DesignDisplayPage.staticSetPaymentsAhead(siteRequest_, o);
		case "emailFrom":
			return DesignDisplayPage.staticSetEmailFrom(siteRequest_, o);
		case "emailToSchool":
			return DesignDisplayPage.staticSetEmailToSchool(siteRequest_, o);
		case "emailToAddress":
			return DesignDisplayPage.staticSetEmailToAddress(siteRequest_, o);
		case "emailToName":
			return DesignDisplayPage.staticSetEmailToName(siteRequest_, o);
		case "emailMessage":
			return DesignDisplayPage.staticSetEmailMessage(siteRequest_, o);
		case "schoolKey":
			return DesignDisplayPage.staticSetSchoolKey(siteRequest_, o);
		case "schoolName":
			return DesignDisplayPage.staticSetSchoolName(siteRequest_, o);
		case "schoolCompleteName":
			return DesignDisplayPage.staticSetSchoolCompleteName(siteRequest_, o);
		case "schoolLocation":
			return DesignDisplayPage.staticSetSchoolLocation(siteRequest_, o);
		case "schoolAddress":
			return DesignDisplayPage.staticSetSchoolAddress(siteRequest_, o);
		case "schoolPhoneNumber":
			return DesignDisplayPage.staticSetSchoolPhoneNumber(siteRequest_, o);
		case "schoolAdministratorName":
			return DesignDisplayPage.staticSetSchoolAdministratorName(siteRequest_, o);
		case "yearStart":
			return DesignDisplayPage.staticSetYearStart(siteRequest_, o);
		case "yearEnd":
			return DesignDisplayPage.staticSetYearEnd(siteRequest_, o);
		case "seasonStartDate":
			return DesignDisplayPage.staticSetSeasonStartDate(siteRequest_, o);
			default:
				return DesignDisplayGenPage.staticSetDesignDisplayGenPage(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrDesignDisplayPage(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrDesignDisplayPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "pageDesignId":
			return DesignDisplayPage.staticSolrPageDesignId(siteRequest_, (String)o);
		case "yearKey":
			return DesignDisplayPage.staticSolrYearKey(siteRequest_, (Long)o);
		case "yearVar":
			return DesignDisplayPage.staticSolrYearVar(siteRequest_, (String)o);
		case "paymentLastStr":
			return DesignDisplayPage.staticSolrPaymentLastStr(siteRequest_, (String)o);
		case "paymentAmount":
			return DesignDisplayPage.staticSolrPaymentAmount(siteRequest_, (BigDecimal)o);
		case "chargeAmount":
			return DesignDisplayPage.staticSolrChargeAmount(siteRequest_, (BigDecimal)o);
		case "chargeAmountFuture":
			return DesignDisplayPage.staticSolrChargeAmountFuture(siteRequest_, (BigDecimal)o);
		case "chargeAmountDue":
			return DesignDisplayPage.staticSolrChargeAmountDue(siteRequest_, (BigDecimal)o);
		case "chargesNow":
			return DesignDisplayPage.staticSolrChargesNow(siteRequest_, (BigDecimal)o);
		case "paymentsCurrent":
			return DesignDisplayPage.staticSolrPaymentsCurrent(siteRequest_, (Boolean)o);
		case "paymentsLate":
			return DesignDisplayPage.staticSolrPaymentsLate(siteRequest_, (Boolean)o);
		case "paymentsLateAmount":
			return DesignDisplayPage.staticSolrPaymentsLateAmount(siteRequest_, (BigDecimal)o);
		case "paymentsAhead":
			return DesignDisplayPage.staticSolrPaymentsAhead(siteRequest_, (Boolean)o);
		case "emailFrom":
			return DesignDisplayPage.staticSolrEmailFrom(siteRequest_, (String)o);
		case "emailToSchool":
			return DesignDisplayPage.staticSolrEmailToSchool(siteRequest_, (String)o);
		case "emailToAddress":
			return DesignDisplayPage.staticSolrEmailToAddress(siteRequest_, (String)o);
		case "emailToName":
			return DesignDisplayPage.staticSolrEmailToName(siteRequest_, (String)o);
		case "emailMessage":
			return DesignDisplayPage.staticSolrEmailMessage(siteRequest_, (String)o);
		case "schoolKey":
			return DesignDisplayPage.staticSolrSchoolKey(siteRequest_, (Long)o);
		case "schoolName":
			return DesignDisplayPage.staticSolrSchoolName(siteRequest_, (String)o);
		case "schoolCompleteName":
			return DesignDisplayPage.staticSolrSchoolCompleteName(siteRequest_, (String)o);
		case "schoolLocation":
			return DesignDisplayPage.staticSolrSchoolLocation(siteRequest_, (String)o);
		case "schoolAddress":
			return DesignDisplayPage.staticSolrSchoolAddress(siteRequest_, (String)o);
		case "schoolPhoneNumber":
			return DesignDisplayPage.staticSolrSchoolPhoneNumber(siteRequest_, (String)o);
		case "schoolAdministratorName":
			return DesignDisplayPage.staticSolrSchoolAdministratorName(siteRequest_, (String)o);
		case "yearStart":
			return DesignDisplayPage.staticSolrYearStart(siteRequest_, (Integer)o);
		case "yearEnd":
			return DesignDisplayPage.staticSolrYearEnd(siteRequest_, (Integer)o);
		case "seasonStartDate":
			return DesignDisplayPage.staticSolrSeasonStartDate(siteRequest_, (LocalDate)o);
			default:
				return DesignDisplayGenPage.staticSolrDesignDisplayGenPage(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrDesignDisplayPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrDesignDisplayPage(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "pageDesignId":
			return DesignDisplayPage.staticSolrStrPageDesignId(siteRequest_, (String)o);
		case "yearKey":
			return DesignDisplayPage.staticSolrStrYearKey(siteRequest_, (Long)o);
		case "yearVar":
			return DesignDisplayPage.staticSolrStrYearVar(siteRequest_, (String)o);
		case "paymentLastStr":
			return DesignDisplayPage.staticSolrStrPaymentLastStr(siteRequest_, (String)o);
		case "paymentAmount":
			return DesignDisplayPage.staticSolrStrPaymentAmount(siteRequest_, (Double)o);
		case "chargeAmount":
			return DesignDisplayPage.staticSolrStrChargeAmount(siteRequest_, (Double)o);
		case "chargeAmountFuture":
			return DesignDisplayPage.staticSolrStrChargeAmountFuture(siteRequest_, (Double)o);
		case "chargeAmountDue":
			return DesignDisplayPage.staticSolrStrChargeAmountDue(siteRequest_, (Double)o);
		case "chargesNow":
			return DesignDisplayPage.staticSolrStrChargesNow(siteRequest_, (Double)o);
		case "paymentsCurrent":
			return DesignDisplayPage.staticSolrStrPaymentsCurrent(siteRequest_, (Boolean)o);
		case "paymentsLate":
			return DesignDisplayPage.staticSolrStrPaymentsLate(siteRequest_, (Boolean)o);
		case "paymentsLateAmount":
			return DesignDisplayPage.staticSolrStrPaymentsLateAmount(siteRequest_, (Double)o);
		case "paymentsAhead":
			return DesignDisplayPage.staticSolrStrPaymentsAhead(siteRequest_, (Boolean)o);
		case "emailFrom":
			return DesignDisplayPage.staticSolrStrEmailFrom(siteRequest_, (String)o);
		case "emailToSchool":
			return DesignDisplayPage.staticSolrStrEmailToSchool(siteRequest_, (String)o);
		case "emailToAddress":
			return DesignDisplayPage.staticSolrStrEmailToAddress(siteRequest_, (String)o);
		case "emailToName":
			return DesignDisplayPage.staticSolrStrEmailToName(siteRequest_, (String)o);
		case "emailMessage":
			return DesignDisplayPage.staticSolrStrEmailMessage(siteRequest_, (String)o);
		case "schoolKey":
			return DesignDisplayPage.staticSolrStrSchoolKey(siteRequest_, (Long)o);
		case "schoolName":
			return DesignDisplayPage.staticSolrStrSchoolName(siteRequest_, (String)o);
		case "schoolCompleteName":
			return DesignDisplayPage.staticSolrStrSchoolCompleteName(siteRequest_, (String)o);
		case "schoolLocation":
			return DesignDisplayPage.staticSolrStrSchoolLocation(siteRequest_, (String)o);
		case "schoolAddress":
			return DesignDisplayPage.staticSolrStrSchoolAddress(siteRequest_, (String)o);
		case "schoolPhoneNumber":
			return DesignDisplayPage.staticSolrStrSchoolPhoneNumber(siteRequest_, (String)o);
		case "schoolAdministratorName":
			return DesignDisplayPage.staticSolrStrSchoolAdministratorName(siteRequest_, (String)o);
		case "yearStart":
			return DesignDisplayPage.staticSolrStrYearStart(siteRequest_, (Integer)o);
		case "yearEnd":
			return DesignDisplayPage.staticSolrStrYearEnd(siteRequest_, (Integer)o);
		case "seasonStartDate":
			return DesignDisplayPage.staticSolrStrSeasonStartDate(siteRequest_, (Date)o);
			default:
				return DesignDisplayGenPage.staticSolrStrDesignDisplayGenPage(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqDesignDisplayPage(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqDesignDisplayPage(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "pageDesignId":
			return DesignDisplayPage.staticSolrFqPageDesignId(siteRequest_, o);
		case "yearKey":
			return DesignDisplayPage.staticSolrFqYearKey(siteRequest_, o);
		case "yearVar":
			return DesignDisplayPage.staticSolrFqYearVar(siteRequest_, o);
		case "paymentLastStr":
			return DesignDisplayPage.staticSolrFqPaymentLastStr(siteRequest_, o);
		case "paymentAmount":
			return DesignDisplayPage.staticSolrFqPaymentAmount(siteRequest_, o);
		case "chargeAmount":
			return DesignDisplayPage.staticSolrFqChargeAmount(siteRequest_, o);
		case "chargeAmountFuture":
			return DesignDisplayPage.staticSolrFqChargeAmountFuture(siteRequest_, o);
		case "chargeAmountDue":
			return DesignDisplayPage.staticSolrFqChargeAmountDue(siteRequest_, o);
		case "chargesNow":
			return DesignDisplayPage.staticSolrFqChargesNow(siteRequest_, o);
		case "paymentsCurrent":
			return DesignDisplayPage.staticSolrFqPaymentsCurrent(siteRequest_, o);
		case "paymentsLate":
			return DesignDisplayPage.staticSolrFqPaymentsLate(siteRequest_, o);
		case "paymentsLateAmount":
			return DesignDisplayPage.staticSolrFqPaymentsLateAmount(siteRequest_, o);
		case "paymentsAhead":
			return DesignDisplayPage.staticSolrFqPaymentsAhead(siteRequest_, o);
		case "emailFrom":
			return DesignDisplayPage.staticSolrFqEmailFrom(siteRequest_, o);
		case "emailToSchool":
			return DesignDisplayPage.staticSolrFqEmailToSchool(siteRequest_, o);
		case "emailToAddress":
			return DesignDisplayPage.staticSolrFqEmailToAddress(siteRequest_, o);
		case "emailToName":
			return DesignDisplayPage.staticSolrFqEmailToName(siteRequest_, o);
		case "emailMessage":
			return DesignDisplayPage.staticSolrFqEmailMessage(siteRequest_, o);
		case "schoolKey":
			return DesignDisplayPage.staticSolrFqSchoolKey(siteRequest_, o);
		case "schoolName":
			return DesignDisplayPage.staticSolrFqSchoolName(siteRequest_, o);
		case "schoolCompleteName":
			return DesignDisplayPage.staticSolrFqSchoolCompleteName(siteRequest_, o);
		case "schoolLocation":
			return DesignDisplayPage.staticSolrFqSchoolLocation(siteRequest_, o);
		case "schoolAddress":
			return DesignDisplayPage.staticSolrFqSchoolAddress(siteRequest_, o);
		case "schoolPhoneNumber":
			return DesignDisplayPage.staticSolrFqSchoolPhoneNumber(siteRequest_, o);
		case "schoolAdministratorName":
			return DesignDisplayPage.staticSolrFqSchoolAdministratorName(siteRequest_, o);
		case "yearStart":
			return DesignDisplayPage.staticSolrFqYearStart(siteRequest_, o);
		case "yearEnd":
			return DesignDisplayPage.staticSolrFqYearEnd(siteRequest_, o);
		case "seasonStartDate":
			return DesignDisplayPage.staticSolrFqSeasonStartDate(siteRequest_, o);
			default:
				return DesignDisplayGenPage.staticSolrFqDesignDisplayGenPage(entityVar,  siteRequest_, o);
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
					o = defineDesignDisplayPage(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineDesignDisplayPage(String var, String val) {
		switch(var) {
			default:
				return super.defineDesignDisplayGenPage(var, val);
		}
	}

	/////////////////
	// htmlScripts //
	/////////////////

	@Override public void htmlScripts() {
		htmlScriptsDesignDisplayPage();
		super.htmlScripts();
	}

	public void htmlScriptsDesignDisplayPage() {
	}

	////////////////
	// htmlScript //
	////////////////

	@Override public void htmlScript() {
		htmlScriptDesignDisplayPage();
		super.htmlScript();
	}

	public void htmlScriptDesignDisplayPage() {
	}

	//////////////
	// htmlBody //
	//////////////

	@Override public void htmlBody() {
		htmlBodyDesignDisplayPage();
		super.htmlBody();
	}

	public void htmlBodyDesignDisplayPage() {
	}

	//////////
	// html //
	//////////

	@Override public void html() {
		htmlDesignDisplayPage();
		super.html();
	}

	public void htmlDesignDisplayPage() {
	}

	//////////////
	// htmlMeta //
	//////////////

	@Override public void htmlMeta() {
		htmlMetaDesignDisplayPage();
		super.htmlMeta();
	}

	public void htmlMetaDesignDisplayPage() {
	}

	////////////////
	// htmlStyles //
	////////////////

	@Override public void htmlStyles() {
		htmlStylesDesignDisplayPage();
		super.htmlStyles();
	}

	public void htmlStylesDesignDisplayPage() {
	}

	///////////////
	// htmlStyle //
	///////////////

	@Override public void htmlStyle() {
		htmlStyleDesignDisplayPage();
		super.htmlStyle();
	}

	public void htmlStyleDesignDisplayPage() {
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestDesignDisplayPage() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof DesignDisplayPage) {
			DesignDisplayPage original = (DesignDisplayPage)o;
			super.apiRequestDesignDisplayGenPage();
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
		if(!(o instanceof DesignDisplayPage))
			return false;
		DesignDisplayPage that = (DesignDisplayPage)o;
		return super.equals(o);
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("DesignDisplayPage { ");
		sb.append(" }");
		return sb.toString();
	}
}
