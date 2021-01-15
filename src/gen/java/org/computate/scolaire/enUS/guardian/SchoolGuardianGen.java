package org.computate.scolaire.enUS.guardian;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import java.util.HashMap;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.text.NumberFormat;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.apache.commons.collections.CollectionUtils;
import java.lang.Long;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.solr.client.solrj.SolrClient;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.solr.client.solrj.util.ClientUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class SchoolGuardianGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolGuardian.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String SchoolGuardian_AName = "a guardian";
	public static final String SchoolGuardian_This = "this ";
	public static final String SchoolGuardian_ThisName = "this guardian";
	public static final String SchoolGuardian_A = "a ";
	public static final String SchoolGuardian_TheName = "the guardian";
	public static final String SchoolGuardian_NameSingular = "guardian";
	public static final String SchoolGuardian_NamePlural = "guardians";
	public static final String SchoolGuardian_NameActual = "current guardian";
	public static final String SchoolGuardian_AllName = "all the guardians";
	public static final String SchoolGuardian_SearchAllNameBy = "search guardians by ";
	public static final String SchoolGuardian_Title = "guardians";
	public static final String SchoolGuardian_ThePluralName = "the guardians";
	public static final String SchoolGuardian_NoNameFound = "no guardian found";
	public static final String SchoolGuardian_NameVar = "guardian";
	public static final String SchoolGuardian_OfName = "of guardian";
	public static final String SchoolGuardian_ANameAdjective = "a guardian";
	public static final String SchoolGuardian_NameAdjectiveSingular = "guardian";
	public static final String SchoolGuardian_NameAdjectivePlural = "guardians";
	public static final String SchoolGuardian_Color = "yellow";
	public static final String SchoolGuardian_IconGroup = "regular";
	public static final String SchoolGuardian_IconName = "phone";

	/////////////////
	// guardianKey //
	/////////////////

	/**	 The entity guardianKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long guardianKey;
	@JsonIgnore
	public Wrap<Long> guardianKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("guardianKey").o(guardianKey);

	/**	<br/> The entity guardianKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardianKey">Find the entity guardianKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _guardianKey(Wrap<Long> c);

	public Long getGuardianKey() {
		return guardianKey;
	}

	public void setGuardianKey(Long guardianKey) {
		this.guardianKey = guardianKey;
		this.guardianKeyWrap.alreadyInitialized = true;
	}
	public void setGuardianKey(String o) {
		this.guardianKey = SchoolGuardian.staticSetGuardianKey(siteRequest_, o);
		this.guardianKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetGuardianKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SchoolGuardian guardianKeyInit() {
		if(!guardianKeyWrap.alreadyInitialized) {
			_guardianKey(guardianKeyWrap);
			if(guardianKey == null)
				setGuardianKey(guardianKeyWrap.o);
		}
		guardianKeyWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static Long staticSolrGuardianKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrGuardianKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqGuardianKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrGuardianKey(siteRequest_, SchoolGuardian.staticSolrGuardianKey(siteRequest_, SchoolGuardian.staticSetGuardianKey(siteRequest_, o)));
	}

	public Long solrGuardianKey() {
		return SchoolGuardian.staticSolrGuardianKey(siteRequest_, guardianKey);
	}

	public String strGuardianKey() {
		return guardianKey == null ? "" : guardianKey.toString();
	}

	public String jsonGuardianKey() {
		return guardianKey == null ? "" : guardianKey.toString();
	}

	public String nomAffichageGuardianKey() {
		return "key";
	}

	public String htmTooltipGuardianKey() {
		return null;
	}

	public String htmGuardianKey() {
		return guardianKey == null ? "" : StringEscapeUtils.escapeHtml4(strGuardianKey());
	}

	////////////////////
	// enrollmentKeys //
	////////////////////

	/**	 The entity enrollmentKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> enrollmentKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> enrollmentKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("enrollmentKeys").o(enrollmentKeys);

	/**	<br/> The entity enrollmentKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Find the entity enrollmentKeys in Solr</a>
	 * <br/>
	 * @param enrollmentKeys is the entity already constructed. 
	 **/
	protected abstract void _enrollmentKeys(List<Long> o);

	public List<Long> getEnrollmentKeys() {
		return enrollmentKeys;
	}

	public void setEnrollmentKeys(List<Long> enrollmentKeys) {
		this.enrollmentKeys = enrollmentKeys;
		this.enrollmentKeysWrap.alreadyInitialized = true;
	}
	public void setEnrollmentKeys(String o) {
		Long l = SchoolGuardian.staticSetEnrollmentKeys(siteRequest_, o);
		if(l != null)
			addEnrollmentKeys(l);
		this.enrollmentKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetEnrollmentKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolGuardian addEnrollmentKeys(Long...objets) {
		for(Long o : objets) {
			addEnrollmentKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addEnrollmentKeys(Long o) {
		if(o != null && !enrollmentKeys.contains(o))
			this.enrollmentKeys.add(o);
		return (SchoolGuardian)this;
	}
	public void setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
	}
	public SchoolGuardian addEnrollmentKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEnrollmentKeys(p);
		}
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian enrollmentKeysInit() {
		if(!enrollmentKeysWrap.alreadyInitialized) {
			_enrollmentKeys(enrollmentKeys);
		}
		enrollmentKeysWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static Long staticSolrEnrollmentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrEnrollmentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrEnrollmentKeys(siteRequest_, SchoolGuardian.staticSolrEnrollmentKeys(siteRequest_, SchoolGuardian.staticSetEnrollmentKeys(siteRequest_, o)));
	}

	public List<Long> solrEnrollmentKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : enrollmentKeys) {
			l.add(SchoolGuardian.staticSolrEnrollmentKeys(siteRequest_, o));
		}
		return l;
	}

	public String strEnrollmentKeys() {
		return enrollmentKeys == null ? "" : enrollmentKeys.toString();
	}

	public String jsonEnrollmentKeys() {
		return enrollmentKeys == null ? "" : enrollmentKeys.toString();
	}

	public String nomAffichageEnrollmentKeys() {
		return "enrollments";
	}

	public String htmTooltipEnrollmentKeys() {
		return null;
	}

	public String htmEnrollmentKeys() {
		return enrollmentKeys == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentKeys());
	}

	public void inputEnrollmentKeys(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
			if("PUTCopy".equals(classApiMethodMethod)) {
				{ e("div").f();
					e("input")
						.a("type", "checkbox")
						.a("id", classApiMethodMethod, "_enrollmentKeys_clear")
						.a("class", "enrollmentKeys_clear ")
						.fg();
					e("label").a("for", "classApiMethodMethod, \"_enrollmentKeys_clear").f().sx("clear").g("label");
				} g("div");
			}
			e("input")
				.a("type", "text")
				.a("placeholder", "enrollments")
				.a("class", "valueObjectSuggest suggestEnrollmentKeys w3-input w3-border w3-cell w3-cell-middle ")
				.a("name", "setEnrollmentKeys")
				.a("id", classApiMethodMethod, "_enrollmentKeys")
				.a("autocomplete", "off");
				a("oninput", "suggestSchoolGuardianEnrollmentKeys($(this).val() ? searchSchoolEnrollmentFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'guardianKeys:" + pk + "'}", "], $('#listSchoolGuardianEnrollmentKeys_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varSchoolGuardian", pk, "EnrollmentKeys ").f().sx(htmEnrollmentKeys()).g("span");
		}
	}

	public void htmEnrollmentKeys(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolGuardianEnrollmentKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/enrollment?fq=guardianKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue-gray w3-hover-blue-gray ").f();
								e("i").a("class", "fas fa-edit ").f().g("i");
								sx("enrollments");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate enrollments to this guardian");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputEnrollmentKeys(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolGuardianEnrollmentKeys_", classApiMethodMethod).f();
								} g("ul");
								{
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
												.a("id", classApiMethodMethod, "_enrollmentKeys_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolEnrollmentVals({ guardianKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "enrollmentKeys')); });")
												.f().sx("add an enrollment")
											.g("button");
										} g("div");
									}
								}
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////
	// familySort //
	////////////////

	/**	 The entity familySort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer familySort;
	@JsonIgnore
	public Wrap<Integer> familySortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("familySort").o(familySort);

	/**	<br/> The entity familySort
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familySort">Find the entity familySort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _familySort(Wrap<Integer> c);

	public Integer getFamilySort() {
		return familySort;
	}

	public void setFamilySort(Integer familySort) {
		this.familySort = familySort;
		this.familySortWrap.alreadyInitialized = true;
	}
	public void setFamilySort(String o) {
		this.familySort = SchoolGuardian.staticSetFamilySort(siteRequest_, o);
		this.familySortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetFamilySort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolGuardian familySortInit() {
		if(!familySortWrap.alreadyInitialized) {
			_familySort(familySortWrap);
			if(familySort == null)
				setFamilySort(familySortWrap.o);
		}
		familySortWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static Integer staticSolrFamilySort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrFamilySort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilySort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrFamilySort(siteRequest_, SchoolGuardian.staticSolrFamilySort(siteRequest_, SchoolGuardian.staticSetFamilySort(siteRequest_, o)));
	}

	public Integer solrFamilySort() {
		return SchoolGuardian.staticSolrFamilySort(siteRequest_, familySort);
	}

	public String strFamilySort() {
		return familySort == null ? "" : familySort.toString();
	}

	public String jsonFamilySort() {
		return familySort == null ? "" : familySort.toString();
	}

	public String nomAffichageFamilySort() {
		return null;
	}

	public String htmTooltipFamilySort() {
		return null;
	}

	public String htmFamilySort() {
		return familySort == null ? "" : StringEscapeUtils.escapeHtml4(strFamilySort());
	}

	////////////////
	// schoolSort //
	////////////////

	/**	 The entity schoolSort
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer schoolSort;
	@JsonIgnore
	public Wrap<Integer> schoolSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("schoolSort").o(schoolSort);

	/**	<br/> The entity schoolSort
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Find the entity schoolSort in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _schoolSort(Wrap<Integer> c);

	public Integer getSchoolSort() {
		return schoolSort;
	}

	public void setSchoolSort(Integer schoolSort) {
		this.schoolSort = schoolSort;
		this.schoolSortWrap.alreadyInitialized = true;
	}
	public void setSchoolSort(String o) {
		this.schoolSort = SchoolGuardian.staticSetSchoolSort(siteRequest_, o);
		this.schoolSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSchoolSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolGuardian schoolSortInit() {
		if(!schoolSortWrap.alreadyInitialized) {
			_schoolSort(schoolSortWrap);
			if(schoolSort == null)
				setSchoolSort(schoolSortWrap.o);
		}
		schoolSortWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static Integer staticSolrSchoolSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSchoolSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrSchoolSort(siteRequest_, SchoolGuardian.staticSolrSchoolSort(siteRequest_, SchoolGuardian.staticSetSchoolSort(siteRequest_, o)));
	}

	public Integer solrSchoolSort() {
		return SchoolGuardian.staticSolrSchoolSort(siteRequest_, schoolSort);
	}

	public String strSchoolSort() {
		return schoolSort == null ? "" : schoolSort.toString();
	}

	public String jsonSchoolSort() {
		return schoolSort == null ? "" : schoolSort.toString();
	}

	public String nomAffichageSchoolSort() {
		return null;
	}

	public String htmTooltipSchoolSort() {
		return null;
	}

	public String htmSchoolSort() {
		return schoolSort == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolSort());
	}

	//////////////////////
	// enrollmentSearch //
	//////////////////////

	/**	 The entity enrollmentSearch
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolEnrollment>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolEnrollment> enrollmentSearch = new SearchList<SchoolEnrollment>();
	@JsonIgnore
	public Wrap<SearchList<SchoolEnrollment>> enrollmentSearchWrap = new Wrap<SearchList<SchoolEnrollment>>().p(this).c(SearchList.class).var("enrollmentSearch").o(enrollmentSearch);

	/**	<br/> The entity enrollmentSearch
	 *  It is constructed before being initialized with the constructor by default SearchList<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSearch">Find the entity enrollmentSearch in Solr</a>
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
	protected SchoolGuardian enrollmentSearchInit() {
		if(!enrollmentSearchWrap.alreadyInitialized) {
			_enrollmentSearch(enrollmentSearch);
		}
		enrollmentSearch.initDeepForClass(siteRequest_);
		enrollmentSearchWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	/////////////////
	// enrollments //
	/////////////////

	/**	 The entity enrollments
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolEnrollment>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollments = new ArrayList<SchoolEnrollment>();
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentsWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollments").o(enrollments);

	/**	<br/> The entity enrollments
	 *  It is constructed before being initialized with the constructor by default List<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollments">Find the entity enrollments in Solr</a>
	 * <br/>
	 * @param enrollments is the entity already constructed. 
	 **/
	protected abstract void _enrollments(List<SchoolEnrollment> l);

	public List<SchoolEnrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<SchoolEnrollment> enrollments) {
		this.enrollments = enrollments;
		this.enrollmentsWrap.alreadyInitialized = true;
	}
	public static SchoolEnrollment staticSetEnrollments(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SchoolGuardian addEnrollments(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollments(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addEnrollments(SchoolEnrollment o) {
		if(o != null && !enrollments.contains(o))
			this.enrollments.add(o);
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian enrollmentsInit() {
		if(!enrollmentsWrap.alreadyInitialized) {
			_enrollments(enrollments);
		}
		enrollmentsWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	//////////////
	// userKeys //
	//////////////

	/**	 The entity userKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> userKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> userKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("userKeys").o(userKeys);

	/**	<br/> The entity userKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userKeys">Find the entity userKeys in Solr</a>
	 * <br/>
	 * @param userKeys is the entity already constructed. 
	 **/
	protected abstract void _userKeys(List<Long> l);

	public List<Long> getUserKeys() {
		return userKeys;
	}

	public void setUserKeys(List<Long> userKeys) {
		this.userKeys = userKeys;
		this.userKeysWrap.alreadyInitialized = true;
	}
	public void setUserKeys(String o) {
		Long l = SchoolGuardian.staticSetUserKeys(siteRequest_, o);
		if(l != null)
			addUserKeys(l);
		this.userKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetUserKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolGuardian addUserKeys(Long...objets) {
		for(Long o : objets) {
			addUserKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addUserKeys(Long o) {
		if(o != null && !userKeys.contains(o))
			this.userKeys.add(o);
		return (SchoolGuardian)this;
	}
	public void setUserKeys(JsonArray objets) {
		userKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUserKeys(o);
		}
	}
	public SchoolGuardian addUserKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addUserKeys(p);
		}
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian userKeysInit() {
		if(!userKeysWrap.alreadyInitialized) {
			_userKeys(userKeys);
		}
		userKeysWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static Long staticSolrUserKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrUserKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUserKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrUserKeys(siteRequest_, SchoolGuardian.staticSolrUserKeys(siteRequest_, SchoolGuardian.staticSetUserKeys(siteRequest_, o)));
	}

	public List<Long> solrUserKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : userKeys) {
			l.add(SchoolGuardian.staticSolrUserKeys(siteRequest_, o));
		}
		return l;
	}

	public String strUserKeys() {
		return userKeys == null ? "" : userKeys.toString();
	}

	public String jsonUserKeys() {
		return userKeys == null ? "" : userKeys.toString();
	}

	public String nomAffichageUserKeys() {
		return null;
	}

	public String htmTooltipUserKeys() {
		return null;
	}

	public String htmUserKeys() {
		return userKeys == null ? "" : StringEscapeUtils.escapeHtml4(strUserKeys());
	}

	////////////////
	// schoolKeys //
	////////////////

	/**	 The entity schoolKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> schoolKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> schoolKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("schoolKeys").o(schoolKeys);

	/**	<br/> The entity schoolKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKeys">Find the entity schoolKeys in Solr</a>
	 * <br/>
	 * @param schoolKeys is the entity already constructed. 
	 **/
	protected abstract void _schoolKeys(List<Long> l);

	public List<Long> getSchoolKeys() {
		return schoolKeys;
	}

	public void setSchoolKeys(List<Long> schoolKeys) {
		this.schoolKeys = schoolKeys;
		this.schoolKeysWrap.alreadyInitialized = true;
	}
	public void setSchoolKeys(String o) {
		Long l = SchoolGuardian.staticSetSchoolKeys(siteRequest_, o);
		if(l != null)
			addSchoolKeys(l);
		this.schoolKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetSchoolKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolGuardian addSchoolKeys(Long...objets) {
		for(Long o : objets) {
			addSchoolKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addSchoolKeys(Long o) {
		if(o != null && !schoolKeys.contains(o))
			this.schoolKeys.add(o);
		return (SchoolGuardian)this;
	}
	public void setSchoolKeys(JsonArray objets) {
		schoolKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSchoolKeys(o);
		}
	}
	public SchoolGuardian addSchoolKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSchoolKeys(p);
		}
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian schoolKeysInit() {
		if(!schoolKeysWrap.alreadyInitialized) {
			_schoolKeys(schoolKeys);
		}
		schoolKeysWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static Long staticSolrSchoolKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSchoolKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrSchoolKeys(siteRequest_, SchoolGuardian.staticSolrSchoolKeys(siteRequest_, SchoolGuardian.staticSetSchoolKeys(siteRequest_, o)));
	}

	public List<Long> solrSchoolKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : schoolKeys) {
			l.add(SchoolGuardian.staticSolrSchoolKeys(siteRequest_, o));
		}
		return l;
	}

	public String strSchoolKeys() {
		return schoolKeys == null ? "" : schoolKeys.toString();
	}

	public String jsonSchoolKeys() {
		return schoolKeys == null ? "" : schoolKeys.toString();
	}

	public String nomAffichageSchoolKeys() {
		return "schools";
	}

	public String htmTooltipSchoolKeys() {
		return null;
	}

	public String htmSchoolKeys() {
		return schoolKeys == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolKeys());
	}

	//////////////
	// yearKeys //
	//////////////

	/**	 The entity yearKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> yearKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> yearKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("yearKeys").o(yearKeys);

	/**	<br/> The entity yearKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKeys">Find the entity yearKeys in Solr</a>
	 * <br/>
	 * @param yearKeys is the entity already constructed. 
	 **/
	protected abstract void _yearKeys(List<Long> l);

	public List<Long> getYearKeys() {
		return yearKeys;
	}

	public void setYearKeys(List<Long> yearKeys) {
		this.yearKeys = yearKeys;
		this.yearKeysWrap.alreadyInitialized = true;
	}
	public void setYearKeys(String o) {
		Long l = SchoolGuardian.staticSetYearKeys(siteRequest_, o);
		if(l != null)
			addYearKeys(l);
		this.yearKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetYearKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolGuardian addYearKeys(Long...objets) {
		for(Long o : objets) {
			addYearKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addYearKeys(Long o) {
		if(o != null && !yearKeys.contains(o))
			this.yearKeys.add(o);
		return (SchoolGuardian)this;
	}
	public void setYearKeys(JsonArray objets) {
		yearKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addYearKeys(o);
		}
	}
	public SchoolGuardian addYearKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addYearKeys(p);
		}
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian yearKeysInit() {
		if(!yearKeysWrap.alreadyInitialized) {
			_yearKeys(yearKeys);
		}
		yearKeysWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static Long staticSolrYearKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrYearKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrYearKeys(siteRequest_, SchoolGuardian.staticSolrYearKeys(siteRequest_, SchoolGuardian.staticSetYearKeys(siteRequest_, o)));
	}

	public List<Long> solrYearKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : yearKeys) {
			l.add(SchoolGuardian.staticSolrYearKeys(siteRequest_, o));
		}
		return l;
	}

	public String strYearKeys() {
		return yearKeys == null ? "" : yearKeys.toString();
	}

	public String jsonYearKeys() {
		return yearKeys == null ? "" : yearKeys.toString();
	}

	public String nomAffichageYearKeys() {
		return "years";
	}

	public String htmTooltipYearKeys() {
		return null;
	}

	public String htmYearKeys() {
		return yearKeys == null ? "" : StringEscapeUtils.escapeHtml4(strYearKeys());
	}

	////////////////
	// seasonKeys //
	////////////////

	/**	 The entity seasonKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> seasonKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> seasonKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("seasonKeys").o(seasonKeys);

	/**	<br/> The entity seasonKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKeys">Find the entity seasonKeys in Solr</a>
	 * <br/>
	 * @param seasonKeys is the entity already constructed. 
	 **/
	protected abstract void _seasonKeys(List<Long> l);

	public List<Long> getSeasonKeys() {
		return seasonKeys;
	}

	public void setSeasonKeys(List<Long> seasonKeys) {
		this.seasonKeys = seasonKeys;
		this.seasonKeysWrap.alreadyInitialized = true;
	}
	public void setSeasonKeys(String o) {
		Long l = SchoolGuardian.staticSetSeasonKeys(siteRequest_, o);
		if(l != null)
			addSeasonKeys(l);
		this.seasonKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetSeasonKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolGuardian addSeasonKeys(Long...objets) {
		for(Long o : objets) {
			addSeasonKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addSeasonKeys(Long o) {
		if(o != null && !seasonKeys.contains(o))
			this.seasonKeys.add(o);
		return (SchoolGuardian)this;
	}
	public void setSeasonKeys(JsonArray objets) {
		seasonKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSeasonKeys(o);
		}
	}
	public SchoolGuardian addSeasonKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSeasonKeys(p);
		}
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian seasonKeysInit() {
		if(!seasonKeysWrap.alreadyInitialized) {
			_seasonKeys(seasonKeys);
		}
		seasonKeysWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static Long staticSolrSeasonKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSeasonKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSeasonKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrSeasonKeys(siteRequest_, SchoolGuardian.staticSolrSeasonKeys(siteRequest_, SchoolGuardian.staticSetSeasonKeys(siteRequest_, o)));
	}

	public List<Long> solrSeasonKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : seasonKeys) {
			l.add(SchoolGuardian.staticSolrSeasonKeys(siteRequest_, o));
		}
		return l;
	}

	public String strSeasonKeys() {
		return seasonKeys == null ? "" : seasonKeys.toString();
	}

	public String jsonSeasonKeys() {
		return seasonKeys == null ? "" : seasonKeys.toString();
	}

	public String nomAffichageSeasonKeys() {
		return "seasons";
	}

	public String htmTooltipSeasonKeys() {
		return null;
	}

	public String htmSeasonKeys() {
		return seasonKeys == null ? "" : StringEscapeUtils.escapeHtml4(strSeasonKeys());
	}

	/////////////////
	// sessionKeys //
	/////////////////

	/**	 The entity sessionKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> sessionKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> sessionKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("sessionKeys").o(sessionKeys);

	/**	<br/> The entity sessionKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKeys">Find the entity sessionKeys in Solr</a>
	 * <br/>
	 * @param sessionKeys is the entity already constructed. 
	 **/
	protected abstract void _sessionKeys(List<Long> l);

	public List<Long> getSessionKeys() {
		return sessionKeys;
	}

	public void setSessionKeys(List<Long> sessionKeys) {
		this.sessionKeys = sessionKeys;
		this.sessionKeysWrap.alreadyInitialized = true;
	}
	public void setSessionKeys(String o) {
		Long l = SchoolGuardian.staticSetSessionKeys(siteRequest_, o);
		if(l != null)
			addSessionKeys(l);
		this.sessionKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetSessionKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolGuardian addSessionKeys(Long...objets) {
		for(Long o : objets) {
			addSessionKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addSessionKeys(Long o) {
		if(o != null && !sessionKeys.contains(o))
			this.sessionKeys.add(o);
		return (SchoolGuardian)this;
	}
	public void setSessionKeys(JsonArray objets) {
		sessionKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionKeys(o);
		}
	}
	public SchoolGuardian addSessionKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSessionKeys(p);
		}
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian sessionKeysInit() {
		if(!sessionKeysWrap.alreadyInitialized) {
			_sessionKeys(sessionKeys);
		}
		sessionKeysWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static Long staticSolrSessionKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSessionKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrSessionKeys(siteRequest_, SchoolGuardian.staticSolrSessionKeys(siteRequest_, SchoolGuardian.staticSetSessionKeys(siteRequest_, o)));
	}

	public List<Long> solrSessionKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : sessionKeys) {
			l.add(SchoolGuardian.staticSolrSessionKeys(siteRequest_, o));
		}
		return l;
	}

	public String strSessionKeys() {
		return sessionKeys == null ? "" : sessionKeys.toString();
	}

	public String jsonSessionKeys() {
		return sessionKeys == null ? "" : sessionKeys.toString();
	}

	public String nomAffichageSessionKeys() {
		return "sessions";
	}

	public String htmTooltipSessionKeys() {
		return null;
	}

	public String htmSessionKeys() {
		return sessionKeys == null ? "" : StringEscapeUtils.escapeHtml4(strSessionKeys());
	}

	/////////////
	// ageKeys //
	/////////////

	/**	 The entity ageKeys
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> ageKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> ageKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("ageKeys").o(ageKeys);

	/**	<br/> The entity ageKeys
	 *  It is constructed before being initialized with the constructor by default List<Long>(). 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKeys">Find the entity ageKeys in Solr</a>
	 * <br/>
	 * @param ageKeys is the entity already constructed. 
	 **/
	protected abstract void _ageKeys(List<Long> l);

	public List<Long> getAgeKeys() {
		return ageKeys;
	}

	public void setAgeKeys(List<Long> ageKeys) {
		this.ageKeys = ageKeys;
		this.ageKeysWrap.alreadyInitialized = true;
	}
	public void setAgeKeys(String o) {
		Long l = SchoolGuardian.staticSetAgeKeys(siteRequest_, o);
		if(l != null)
			addAgeKeys(l);
		this.ageKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetAgeKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolGuardian addAgeKeys(Long...objets) {
		for(Long o : objets) {
			addAgeKeys(o);
		}
		return (SchoolGuardian)this;
	}
	public SchoolGuardian addAgeKeys(Long o) {
		if(o != null && !ageKeys.contains(o))
			this.ageKeys.add(o);
		return (SchoolGuardian)this;
	}
	public void setAgeKeys(JsonArray objets) {
		ageKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAgeKeys(o);
		}
	}
	public SchoolGuardian addAgeKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAgeKeys(p);
		}
		return (SchoolGuardian)this;
	}
	protected SchoolGuardian ageKeysInit() {
		if(!ageKeysWrap.alreadyInitialized) {
			_ageKeys(ageKeys);
		}
		ageKeysWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static Long staticSolrAgeKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrAgeKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrAgeKeys(siteRequest_, SchoolGuardian.staticSolrAgeKeys(siteRequest_, SchoolGuardian.staticSetAgeKeys(siteRequest_, o)));
	}

	public List<Long> solrAgeKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : ageKeys) {
			l.add(SchoolGuardian.staticSolrAgeKeys(siteRequest_, o));
		}
		return l;
	}

	public String strAgeKeys() {
		return ageKeys == null ? "" : ageKeys.toString();
	}

	public String jsonAgeKeys() {
		return ageKeys == null ? "" : ageKeys.toString();
	}

	public String nomAffichageAgeKeys() {
		return "ages";
	}

	public String htmTooltipAgeKeys() {
		return null;
	}

	public String htmAgeKeys() {
		return ageKeys == null ? "" : StringEscapeUtils.escapeHtml4(strAgeKeys());
	}

	/////////////////////
	// personFirstName //
	/////////////////////

	/**	 The entity personFirstName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personFirstName;
	@JsonIgnore
	public Wrap<String> personFirstNameWrap = new Wrap<String>().p(this).c(String.class).var("personFirstName").o(personFirstName);

	/**	<br/> The entity personFirstName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personFirstName">Find the entity personFirstName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personFirstName(Wrap<String> c);

	public String getPersonFirstName() {
		return personFirstName;
	}
	public void setPersonFirstName(String o) {
		this.personFirstName = SchoolGuardian.staticSetPersonFirstName(siteRequest_, o);
		this.personFirstNameWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolGuardian personFirstNameInit() {
		if(!personFirstNameWrap.alreadyInitialized) {
			_personFirstName(personFirstNameWrap);
			if(personFirstName == null)
				setPersonFirstName(personFirstNameWrap.o);
		}
		personFirstNameWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static String staticSolrPersonFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonFirstName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrPersonFirstName(siteRequest_, SchoolGuardian.staticSolrPersonFirstName(siteRequest_, SchoolGuardian.staticSetPersonFirstName(siteRequest_, o)));
	}

	public String solrPersonFirstName() {
		return SchoolGuardian.staticSolrPersonFirstName(siteRequest_, personFirstName);
	}

	public String strPersonFirstName() {
		return personFirstName == null ? "" : personFirstName;
	}

	public String jsonPersonFirstName() {
		return personFirstName == null ? "" : personFirstName;
	}

	public String nomAffichagePersonFirstName() {
		return "first name";
	}

	public String htmTooltipPersonFirstName() {
		return null;
	}

	public String htmPersonFirstName() {
		return personFirstName == null ? "" : StringEscapeUtils.escapeHtml4(strPersonFirstName());
	}

	public void inputPersonFirstName(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "first name")
				.a("id", classApiMethodMethod, "_personFirstName");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setPersonFirstName classSchoolGuardian inputSchoolGuardian", pk, "PersonFirstName w3-input w3-border ");
					a("name", "setPersonFirstName");
				} else {
					a("class", "valuePersonFirstName w3-input w3-border classSchoolGuardian inputSchoolGuardian", pk, "PersonFirstName w3-input w3-border ");
					a("name", "personFirstName");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonFirstName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personFirstName')); }, function() { addError($('#", classApiMethodMethod, "_personFirstName')); }); ");
				}
				a("value", strPersonFirstName())
			.fg();

		} else {
			e("span").a("class", "varSchoolGuardian", pk, "PersonFirstName ").f().sx(htmPersonFirstName()).g("span");
		}
	}

	public void htmPersonFirstName(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolGuardianPersonFirstName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classApiMethodMethod, "_personFirstName").a("class", "").f().sx("first name").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonFirstName(classApiMethodMethod);
							} g("div");
							if(
									userKeys.contains(siteRequest_.getUserKey())
									|| Objects.equals(sessionId, siteRequest_.getSessionId())
									|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
							) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personFirstName')); $('#", classApiMethodMethod, "_personFirstName').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolGuardianForm :input[name=pk]').val() }], 'setPersonFirstName', null, function() { addGlow($('#", classApiMethodMethod, "_personFirstName')); }, function() { addError($('#", classApiMethodMethod, "_personFirstName')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////////////
	// personFirstNamePreferred //
	//////////////////////////////

	/**	 The entity personFirstNamePreferred
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personFirstNamePreferred;
	@JsonIgnore
	public Wrap<String> personFirstNamePreferredWrap = new Wrap<String>().p(this).c(String.class).var("personFirstNamePreferred").o(personFirstNamePreferred);

	/**	<br/> The entity personFirstNamePreferred
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personFirstNamePreferred">Find the entity personFirstNamePreferred in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personFirstNamePreferred(Wrap<String> c);

	public String getPersonFirstNamePreferred() {
		return personFirstNamePreferred;
	}
	public void setPersonFirstNamePreferred(String o) {
		this.personFirstNamePreferred = SchoolGuardian.staticSetPersonFirstNamePreferred(siteRequest_, o);
		this.personFirstNamePreferredWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolGuardian personFirstNamePreferredInit() {
		if(!personFirstNamePreferredWrap.alreadyInitialized) {
			_personFirstNamePreferred(personFirstNamePreferredWrap);
			if(personFirstNamePreferred == null)
				setPersonFirstNamePreferred(personFirstNamePreferredWrap.o);
		}
		personFirstNamePreferredWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static String staticSolrPersonFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrPersonFirstNamePreferred(siteRequest_, SchoolGuardian.staticSolrPersonFirstNamePreferred(siteRequest_, SchoolGuardian.staticSetPersonFirstNamePreferred(siteRequest_, o)));
	}

	public String solrPersonFirstNamePreferred() {
		return SchoolGuardian.staticSolrPersonFirstNamePreferred(siteRequest_, personFirstNamePreferred);
	}

	public String strPersonFirstNamePreferred() {
		return personFirstNamePreferred == null ? "" : personFirstNamePreferred;
	}

	public String jsonPersonFirstNamePreferred() {
		return personFirstNamePreferred == null ? "" : personFirstNamePreferred;
	}

	public String nomAffichagePersonFirstNamePreferred() {
		return "preferred first name";
	}

	public String htmTooltipPersonFirstNamePreferred() {
		return null;
	}

	public String htmPersonFirstNamePreferred() {
		return personFirstNamePreferred == null ? "" : StringEscapeUtils.escapeHtml4(strPersonFirstNamePreferred());
	}

	public void inputPersonFirstNamePreferred(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "preferred first name")
				.a("id", classApiMethodMethod, "_personFirstNamePreferred");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setPersonFirstNamePreferred classSchoolGuardian inputSchoolGuardian", pk, "PersonFirstNamePreferred w3-input w3-border ");
					a("name", "setPersonFirstNamePreferred");
				} else {
					a("class", "valuePersonFirstNamePreferred w3-input w3-border classSchoolGuardian inputSchoolGuardian", pk, "PersonFirstNamePreferred w3-input w3-border ");
					a("name", "personFirstNamePreferred");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonFirstNamePreferred', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personFirstNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_personFirstNamePreferred')); }); ");
				}
				a("value", strPersonFirstNamePreferred())
			.fg();

		} else {
			e("span").a("class", "varSchoolGuardian", pk, "PersonFirstNamePreferred ").f().sx(htmPersonFirstNamePreferred()).g("span");
		}
	}

	public void htmPersonFirstNamePreferred(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolGuardianPersonFirstNamePreferred").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classApiMethodMethod, "_personFirstNamePreferred").a("class", "").f().sx("preferred first name").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonFirstNamePreferred(classApiMethodMethod);
							} g("div");
							if(
									userKeys.contains(siteRequest_.getUserKey())
									|| Objects.equals(sessionId, siteRequest_.getSessionId())
									|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
							) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personFirstNamePreferred')); $('#", classApiMethodMethod, "_personFirstNamePreferred').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolGuardianForm :input[name=pk]').val() }], 'setPersonFirstNamePreferred', null, function() { addGlow($('#", classApiMethodMethod, "_personFirstNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_personFirstNamePreferred')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////
	// familyName //
	////////////////

	/**	 The entity familyName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String familyName;
	@JsonIgnore
	public Wrap<String> familyNameWrap = new Wrap<String>().p(this).c(String.class).var("familyName").o(familyName);

	/**	<br/> The entity familyName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyName">Find the entity familyName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _familyName(Wrap<String> c);

	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String o) {
		this.familyName = SchoolGuardian.staticSetFamilyName(siteRequest_, o);
		this.familyNameWrap.alreadyInitialized = true;
	}
	public static String staticSetFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolGuardian familyNameInit() {
		if(!familyNameWrap.alreadyInitialized) {
			_familyName(familyNameWrap);
			if(familyName == null)
				setFamilyName(familyNameWrap.o);
		}
		familyNameWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static String staticSolrFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrFamilyName(siteRequest_, SchoolGuardian.staticSolrFamilyName(siteRequest_, SchoolGuardian.staticSetFamilyName(siteRequest_, o)));
	}

	public String solrFamilyName() {
		return SchoolGuardian.staticSolrFamilyName(siteRequest_, familyName);
	}

	public String strFamilyName() {
		return familyName == null ? "" : familyName;
	}

	public String jsonFamilyName() {
		return familyName == null ? "" : familyName;
	}

	public String nomAffichageFamilyName() {
		return "last name";
	}

	public String htmTooltipFamilyName() {
		return null;
	}

	public String htmFamilyName() {
		return familyName == null ? "" : StringEscapeUtils.escapeHtml4(strFamilyName());
	}

	public void inputFamilyName(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "last name")
				.a("id", classApiMethodMethod, "_familyName");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setFamilyName classSchoolGuardian inputSchoolGuardian", pk, "FamilyName w3-input w3-border ");
					a("name", "setFamilyName");
				} else {
					a("class", "valueFamilyName w3-input w3-border classSchoolGuardian inputSchoolGuardian", pk, "FamilyName w3-input w3-border ");
					a("name", "familyName");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilyName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_familyName')); }, function() { addError($('#", classApiMethodMethod, "_familyName')); }); ");
				}
				a("value", strFamilyName())
			.fg();

		} else {
			e("span").a("class", "varSchoolGuardian", pk, "FamilyName ").f().sx(htmFamilyName()).g("span");
		}
	}

	public void htmFamilyName(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolGuardianFamilyName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classApiMethodMethod, "_familyName").a("class", "").f().sx("last name").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputFamilyName(classApiMethodMethod);
							} g("div");
							if(
									userKeys.contains(siteRequest_.getUserKey())
									|| Objects.equals(sessionId, siteRequest_.getSessionId())
									|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
							) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_familyName')); $('#", classApiMethodMethod, "_familyName').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolGuardianForm :input[name=pk]').val() }], 'setFamilyName', null, function() { addGlow($('#", classApiMethodMethod, "_familyName')); }, function() { addError($('#", classApiMethodMethod, "_familyName')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	////////////////////////
	// personCompleteName //
	////////////////////////

	/**	 The entity personCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personCompleteName;
	@JsonIgnore
	public Wrap<String> personCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("personCompleteName").o(personCompleteName);

	/**	<br/> The entity personCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personCompleteName">Find the entity personCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personCompleteName(Wrap<String> c);

	public String getPersonCompleteName() {
		return personCompleteName;
	}
	public void setPersonCompleteName(String o) {
		this.personCompleteName = SchoolGuardian.staticSetPersonCompleteName(siteRequest_, o);
		this.personCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolGuardian personCompleteNameInit() {
		if(!personCompleteNameWrap.alreadyInitialized) {
			_personCompleteName(personCompleteNameWrap);
			if(personCompleteName == null)
				setPersonCompleteName(personCompleteNameWrap.o);
		}
		personCompleteNameWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static String staticSolrPersonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrPersonCompleteName(siteRequest_, SchoolGuardian.staticSolrPersonCompleteName(siteRequest_, SchoolGuardian.staticSetPersonCompleteName(siteRequest_, o)));
	}

	public String solrPersonCompleteName() {
		return SchoolGuardian.staticSolrPersonCompleteName(siteRequest_, personCompleteName);
	}

	public String strPersonCompleteName() {
		return personCompleteName == null ? "" : personCompleteName;
	}

	public String jsonPersonCompleteName() {
		return personCompleteName == null ? "" : personCompleteName;
	}

	public String nomAffichagePersonCompleteName() {
		return "complete name";
	}

	public String htmTooltipPersonCompleteName() {
		return null;
	}

	public String htmPersonCompleteName() {
		return personCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strPersonCompleteName());
	}

	/////////////////////////////////
	// personCompleteNamePreferred //
	/////////////////////////////////

	/**	 The entity personCompleteNamePreferred
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personCompleteNamePreferred;
	@JsonIgnore
	public Wrap<String> personCompleteNamePreferredWrap = new Wrap<String>().p(this).c(String.class).var("personCompleteNamePreferred").o(personCompleteNamePreferred);

	/**	<br/> The entity personCompleteNamePreferred
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personCompleteNamePreferred">Find the entity personCompleteNamePreferred in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personCompleteNamePreferred(Wrap<String> c);

	public String getPersonCompleteNamePreferred() {
		return personCompleteNamePreferred;
	}
	public void setPersonCompleteNamePreferred(String o) {
		this.personCompleteNamePreferred = SchoolGuardian.staticSetPersonCompleteNamePreferred(siteRequest_, o);
		this.personCompleteNamePreferredWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolGuardian personCompleteNamePreferredInit() {
		if(!personCompleteNamePreferredWrap.alreadyInitialized) {
			_personCompleteNamePreferred(personCompleteNamePreferredWrap);
			if(personCompleteNamePreferred == null)
				setPersonCompleteNamePreferred(personCompleteNamePreferredWrap.o);
		}
		personCompleteNamePreferredWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static String staticSolrPersonCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrPersonCompleteNamePreferred(siteRequest_, SchoolGuardian.staticSolrPersonCompleteNamePreferred(siteRequest_, SchoolGuardian.staticSetPersonCompleteNamePreferred(siteRequest_, o)));
	}

	public String solrPersonCompleteNamePreferred() {
		return SchoolGuardian.staticSolrPersonCompleteNamePreferred(siteRequest_, personCompleteNamePreferred);
	}

	public String strPersonCompleteNamePreferred() {
		return personCompleteNamePreferred == null ? "" : personCompleteNamePreferred;
	}

	public String jsonPersonCompleteNamePreferred() {
		return personCompleteNamePreferred == null ? "" : personCompleteNamePreferred;
	}

	public String nomAffichagePersonCompleteNamePreferred() {
		return "complete name preferred";
	}

	public String htmTooltipPersonCompleteNamePreferred() {
		return null;
	}

	public String htmPersonCompleteNamePreferred() {
		return personCompleteNamePreferred == null ? "" : StringEscapeUtils.escapeHtml4(strPersonCompleteNamePreferred());
	}

	//////////////////////
	// personFormalName //
	//////////////////////

	/**	 The entity personFormalName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personFormalName;
	@JsonIgnore
	public Wrap<String> personFormalNameWrap = new Wrap<String>().p(this).c(String.class).var("personFormalName").o(personFormalName);

	/**	<br/> The entity personFormalName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personFormalName">Find the entity personFormalName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personFormalName(Wrap<String> c);

	public String getPersonFormalName() {
		return personFormalName;
	}
	public void setPersonFormalName(String o) {
		this.personFormalName = SchoolGuardian.staticSetPersonFormalName(siteRequest_, o);
		this.personFormalNameWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonFormalName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolGuardian personFormalNameInit() {
		if(!personFormalNameWrap.alreadyInitialized) {
			_personFormalName(personFormalNameWrap);
			if(personFormalName == null)
				setPersonFormalName(personFormalNameWrap.o);
		}
		personFormalNameWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static String staticSolrPersonFormalName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonFormalName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonFormalName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrPersonFormalName(siteRequest_, SchoolGuardian.staticSolrPersonFormalName(siteRequest_, SchoolGuardian.staticSetPersonFormalName(siteRequest_, o)));
	}

	public String solrPersonFormalName() {
		return SchoolGuardian.staticSolrPersonFormalName(siteRequest_, personFormalName);
	}

	public String strPersonFormalName() {
		return personFormalName == null ? "" : personFormalName;
	}

	public String jsonPersonFormalName() {
		return personFormalName == null ? "" : personFormalName;
	}

	public String nomAffichagePersonFormalName() {
		return "formal name";
	}

	public String htmTooltipPersonFormalName() {
		return null;
	}

	public String htmPersonFormalName() {
		return personFormalName == null ? "" : StringEscapeUtils.escapeHtml4(strPersonFormalName());
	}

	//////////////////////
	// personOccupation //
	//////////////////////

	/**	 The entity personOccupation
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personOccupation;
	@JsonIgnore
	public Wrap<String> personOccupationWrap = new Wrap<String>().p(this).c(String.class).var("personOccupation").o(personOccupation);

	/**	<br/> The entity personOccupation
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personOccupation">Find the entity personOccupation in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personOccupation(Wrap<String> c);

	public String getPersonOccupation() {
		return personOccupation;
	}
	public void setPersonOccupation(String o) {
		this.personOccupation = SchoolGuardian.staticSetPersonOccupation(siteRequest_, o);
		this.personOccupationWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonOccupation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolGuardian personOccupationInit() {
		if(!personOccupationWrap.alreadyInitialized) {
			_personOccupation(personOccupationWrap);
			if(personOccupation == null)
				setPersonOccupation(personOccupationWrap.o);
		}
		personOccupationWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static String staticSolrPersonOccupation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonOccupation(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonOccupation(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrPersonOccupation(siteRequest_, SchoolGuardian.staticSolrPersonOccupation(siteRequest_, SchoolGuardian.staticSetPersonOccupation(siteRequest_, o)));
	}

	public String solrPersonOccupation() {
		return SchoolGuardian.staticSolrPersonOccupation(siteRequest_, personOccupation);
	}

	public String strPersonOccupation() {
		return personOccupation == null ? "" : personOccupation;
	}

	public String jsonPersonOccupation() {
		return personOccupation == null ? "" : personOccupation;
	}

	public String nomAffichagePersonOccupation() {
		return "occupation";
	}

	public String htmTooltipPersonOccupation() {
		return null;
	}

	public String htmPersonOccupation() {
		return personOccupation == null ? "" : StringEscapeUtils.escapeHtml4(strPersonOccupation());
	}

	///////////////////////
	// personPhoneNumber //
	///////////////////////

	/**	 The entity personPhoneNumber
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personPhoneNumber;
	@JsonIgnore
	public Wrap<String> personPhoneNumberWrap = new Wrap<String>().p(this).c(String.class).var("personPhoneNumber").o(personPhoneNumber);

	/**	<br/> The entity personPhoneNumber
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personPhoneNumber">Find the entity personPhoneNumber in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personPhoneNumber(Wrap<String> c);

	public String getPersonPhoneNumber() {
		return personPhoneNumber;
	}
	public void setPersonPhoneNumber(String o) {
		this.personPhoneNumber = SchoolGuardian.staticSetPersonPhoneNumber(siteRequest_, o);
		this.personPhoneNumberWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolGuardian personPhoneNumberInit() {
		if(!personPhoneNumberWrap.alreadyInitialized) {
			_personPhoneNumber(personPhoneNumberWrap);
			if(personPhoneNumber == null)
				setPersonPhoneNumber(personPhoneNumberWrap.o);
		}
		personPhoneNumberWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static String staticSolrPersonPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrPersonPhoneNumber(siteRequest_, SchoolGuardian.staticSolrPersonPhoneNumber(siteRequest_, SchoolGuardian.staticSetPersonPhoneNumber(siteRequest_, o)));
	}

	public String solrPersonPhoneNumber() {
		return SchoolGuardian.staticSolrPersonPhoneNumber(siteRequest_, personPhoneNumber);
	}

	public String strPersonPhoneNumber() {
		return personPhoneNumber == null ? "" : personPhoneNumber;
	}

	public String jsonPersonPhoneNumber() {
		return personPhoneNumber == null ? "" : personPhoneNumber;
	}

	public String nomAffichagePersonPhoneNumber() {
		return "phone number";
	}

	public String htmTooltipPersonPhoneNumber() {
		return null;
	}

	public String htmPersonPhoneNumber() {
		return personPhoneNumber == null ? "" : StringEscapeUtils.escapeHtml4(strPersonPhoneNumber());
	}

	public void inputPersonPhoneNumber(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "phone number")
				.a("id", classApiMethodMethod, "_personPhoneNumber");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setPersonPhoneNumber classSchoolGuardian inputSchoolGuardian", pk, "PersonPhoneNumber w3-input w3-border ");
					a("name", "setPersonPhoneNumber");
				} else {
					a("class", "valuePersonPhoneNumber w3-input w3-border classSchoolGuardian inputSchoolGuardian", pk, "PersonPhoneNumber w3-input w3-border ");
					a("name", "personPhoneNumber");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonPhoneNumber', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personPhoneNumber')); }, function() { addError($('#", classApiMethodMethod, "_personPhoneNumber')); }); ");
				}
				a("value", strPersonPhoneNumber())
			.fg();

		} else {
			e("span").a("class", "varSchoolGuardian", pk, "PersonPhoneNumber ").f().sx(htmPersonPhoneNumber()).g("span");
		}
	}

	public void htmPersonPhoneNumber(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolGuardianPersonPhoneNumber").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classApiMethodMethod, "_personPhoneNumber").a("class", "").f().sx("phone number").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonPhoneNumber(classApiMethodMethod);
							} g("div");
							if(
									userKeys.contains(siteRequest_.getUserKey())
									|| Objects.equals(sessionId, siteRequest_.getSessionId())
									|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
							) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personPhoneNumber')); $('#", classApiMethodMethod, "_personPhoneNumber').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolGuardianForm :input[name=pk]').val() }], 'setPersonPhoneNumber', null, function() { addGlow($('#", classApiMethodMethod, "_personPhoneNumber')); }, function() { addError($('#", classApiMethodMethod, "_personPhoneNumber')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	/////////////////
	// personEmail //
	/////////////////

	/**	 The entity personEmail
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personEmail;
	@JsonIgnore
	public Wrap<String> personEmailWrap = new Wrap<String>().p(this).c(String.class).var("personEmail").o(personEmail);

	/**	<br/> The entity personEmail
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personEmail">Find the entity personEmail in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personEmail(Wrap<String> c);

	public String getPersonEmail() {
		return personEmail;
	}
	public void setPersonEmail(String o) {
		this.personEmail = SchoolGuardian.staticSetPersonEmail(siteRequest_, o);
		this.personEmailWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonEmail(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolGuardian personEmailInit() {
		if(!personEmailWrap.alreadyInitialized) {
			_personEmail(personEmailWrap);
			if(personEmail == null)
				setPersonEmail(personEmailWrap.o);
		}
		personEmailWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static String staticSolrPersonEmail(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonEmail(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonEmail(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrPersonEmail(siteRequest_, SchoolGuardian.staticSolrPersonEmail(siteRequest_, SchoolGuardian.staticSetPersonEmail(siteRequest_, o)));
	}

	public String solrPersonEmail() {
		return SchoolGuardian.staticSolrPersonEmail(siteRequest_, personEmail);
	}

	public String strPersonEmail() {
		return personEmail == null ? "" : personEmail;
	}

	public String jsonPersonEmail() {
		return personEmail == null ? "" : personEmail;
	}

	public String nomAffichagePersonEmail() {
		return "email";
	}

	public String htmTooltipPersonEmail() {
		return null;
	}

	public String htmPersonEmail() {
		return personEmail == null ? "" : StringEscapeUtils.escapeHtml4(strPersonEmail());
	}

	////////////////////
	// personRelation //
	////////////////////

	/**	 The entity personRelation
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personRelation;
	@JsonIgnore
	public Wrap<String> personRelationWrap = new Wrap<String>().p(this).c(String.class).var("personRelation").o(personRelation);

	/**	<br/> The entity personRelation
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personRelation">Find the entity personRelation in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personRelation(Wrap<String> c);

	public String getPersonRelation() {
		return personRelation;
	}
	public void setPersonRelation(String o) {
		this.personRelation = SchoolGuardian.staticSetPersonRelation(siteRequest_, o);
		this.personRelationWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonRelation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolGuardian personRelationInit() {
		if(!personRelationWrap.alreadyInitialized) {
			_personRelation(personRelationWrap);
			if(personRelation == null)
				setPersonRelation(personRelationWrap.o);
		}
		personRelationWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static String staticSolrPersonRelation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonRelation(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonRelation(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrPersonRelation(siteRequest_, SchoolGuardian.staticSolrPersonRelation(siteRequest_, SchoolGuardian.staticSetPersonRelation(siteRequest_, o)));
	}

	public String solrPersonRelation() {
		return SchoolGuardian.staticSolrPersonRelation(siteRequest_, personRelation);
	}

	public String strPersonRelation() {
		return personRelation == null ? "" : personRelation;
	}

	public String jsonPersonRelation() {
		return personRelation == null ? "" : personRelation;
	}

	public String nomAffichagePersonRelation() {
		return "relation";
	}

	public String htmTooltipPersonRelation() {
		return null;
	}

	public String htmPersonRelation() {
		return personRelation == null ? "" : StringEscapeUtils.escapeHtml4(strPersonRelation());
	}

	public void inputPersonRelation(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "relation")
				.a("id", classApiMethodMethod, "_personRelation");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setPersonRelation classSchoolGuardian inputSchoolGuardian", pk, "PersonRelation w3-input w3-border ");
					a("name", "setPersonRelation");
				} else {
					a("class", "valuePersonRelation w3-input w3-border classSchoolGuardian inputSchoolGuardian", pk, "PersonRelation w3-input w3-border ");
					a("name", "personRelation");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonRelation', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personRelation')); }, function() { addError($('#", classApiMethodMethod, "_personRelation')); }); ");
				}
				a("value", strPersonRelation())
			.fg();

		} else {
			e("span").a("class", "varSchoolGuardian", pk, "PersonRelation ").f().sx(htmPersonRelation()).g("span");
		}
	}

	public void htmPersonRelation(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolGuardianPersonRelation").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classApiMethodMethod, "_personRelation").a("class", "").f().sx("relation").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonRelation(classApiMethodMethod);
							} g("div");
							if(
									userKeys.contains(siteRequest_.getUserKey())
									|| Objects.equals(sessionId, siteRequest_.getSessionId())
									|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
							) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personRelation')); $('#", classApiMethodMethod, "_personRelation').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolGuardianForm :input[name=pk]').val() }], 'setPersonRelation', null, function() { addGlow($('#", classApiMethodMethod, "_personRelation')); }, function() { addError($('#", classApiMethodMethod, "_personRelation')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////////
	// personSms //
	///////////////

	/**	 The entity personSms
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean personSms;
	@JsonIgnore
	public Wrap<Boolean> personSmsWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("personSms").o(personSms);

	/**	<br/> The entity personSms
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personSms">Find the entity personSms in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personSms(Wrap<Boolean> c);

	public Boolean getPersonSms() {
		return personSms;
	}

	public void setPersonSms(Boolean personSms) {
		this.personSms = personSms;
		this.personSmsWrap.alreadyInitialized = true;
	}
	public void setPersonSms(String o) {
		this.personSms = SchoolGuardian.staticSetPersonSms(siteRequest_, o);
		this.personSmsWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPersonSms(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolGuardian personSmsInit() {
		if(!personSmsWrap.alreadyInitialized) {
			_personSms(personSmsWrap);
			if(personSms == null)
				setPersonSms(personSmsWrap.o);
		}
		personSmsWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static Boolean staticSolrPersonSms(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonSms(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonSms(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrPersonSms(siteRequest_, SchoolGuardian.staticSolrPersonSms(siteRequest_, SchoolGuardian.staticSetPersonSms(siteRequest_, o)));
	}

	public Boolean solrPersonSms() {
		return SchoolGuardian.staticSolrPersonSms(siteRequest_, personSms);
	}

	public String strPersonSms() {
		return personSms == null ? "" : personSms.toString();
	}

	public String jsonPersonSms() {
		return personSms == null ? "" : personSms.toString();
	}

	public String nomAffichagePersonSms() {
		return "text me";
	}

	public String htmTooltipPersonSms() {
		return null;
	}

	public String htmPersonSms() {
		return personSms == null ? "" : StringEscapeUtils.escapeHtml4(strPersonSms());
	}

	////////////////////////
	// personReceiveEmail //
	////////////////////////

	/**	 The entity personReceiveEmail
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean personReceiveEmail;
	@JsonIgnore
	public Wrap<Boolean> personReceiveEmailWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("personReceiveEmail").o(personReceiveEmail);

	/**	<br/> The entity personReceiveEmail
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personReceiveEmail">Find the entity personReceiveEmail in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personReceiveEmail(Wrap<Boolean> c);

	public Boolean getPersonReceiveEmail() {
		return personReceiveEmail;
	}

	public void setPersonReceiveEmail(Boolean personReceiveEmail) {
		this.personReceiveEmail = personReceiveEmail;
		this.personReceiveEmailWrap.alreadyInitialized = true;
	}
	public void setPersonReceiveEmail(String o) {
		this.personReceiveEmail = SchoolGuardian.staticSetPersonReceiveEmail(siteRequest_, o);
		this.personReceiveEmailWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPersonReceiveEmail(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolGuardian personReceiveEmailInit() {
		if(!personReceiveEmailWrap.alreadyInitialized) {
			_personReceiveEmail(personReceiveEmailWrap);
			if(personReceiveEmail == null)
				setPersonReceiveEmail(personReceiveEmailWrap.o);
		}
		personReceiveEmailWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static Boolean staticSolrPersonReceiveEmail(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonReceiveEmail(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonReceiveEmail(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrPersonReceiveEmail(siteRequest_, SchoolGuardian.staticSolrPersonReceiveEmail(siteRequest_, SchoolGuardian.staticSetPersonReceiveEmail(siteRequest_, o)));
	}

	public Boolean solrPersonReceiveEmail() {
		return SchoolGuardian.staticSolrPersonReceiveEmail(siteRequest_, personReceiveEmail);
	}

	public String strPersonReceiveEmail() {
		return personReceiveEmail == null ? "" : personReceiveEmail.toString();
	}

	public String jsonPersonReceiveEmail() {
		return personReceiveEmail == null ? "" : personReceiveEmail.toString();
	}

	public String nomAffichagePersonReceiveEmail() {
		return "receive email";
	}

	public String htmTooltipPersonReceiveEmail() {
		return null;
	}

	public String htmPersonReceiveEmail() {
		return personReceiveEmail == null ? "" : StringEscapeUtils.escapeHtml4(strPersonReceiveEmail());
	}

	////////////////////////////
	// personEmergencyContact //
	////////////////////////////

	/**	 The entity personEmergencyContact
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean personEmergencyContact;
	@JsonIgnore
	public Wrap<Boolean> personEmergencyContactWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("personEmergencyContact").o(personEmergencyContact);

	/**	<br/> The entity personEmergencyContact
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personEmergencyContact">Find the entity personEmergencyContact in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personEmergencyContact(Wrap<Boolean> c);

	public Boolean getPersonEmergencyContact() {
		return personEmergencyContact;
	}

	public void setPersonEmergencyContact(Boolean personEmergencyContact) {
		this.personEmergencyContact = personEmergencyContact;
		this.personEmergencyContactWrap.alreadyInitialized = true;
	}
	public void setPersonEmergencyContact(String o) {
		this.personEmergencyContact = SchoolGuardian.staticSetPersonEmergencyContact(siteRequest_, o);
		this.personEmergencyContactWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPersonEmergencyContact(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolGuardian personEmergencyContactInit() {
		if(!personEmergencyContactWrap.alreadyInitialized) {
			_personEmergencyContact(personEmergencyContactWrap);
			if(personEmergencyContact == null)
				setPersonEmergencyContact(personEmergencyContactWrap.o);
		}
		personEmergencyContactWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static Boolean staticSolrPersonEmergencyContact(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonEmergencyContact(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonEmergencyContact(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrPersonEmergencyContact(siteRequest_, SchoolGuardian.staticSolrPersonEmergencyContact(siteRequest_, SchoolGuardian.staticSetPersonEmergencyContact(siteRequest_, o)));
	}

	public Boolean solrPersonEmergencyContact() {
		return SchoolGuardian.staticSolrPersonEmergencyContact(siteRequest_, personEmergencyContact);
	}

	public String strPersonEmergencyContact() {
		return personEmergencyContact == null ? "" : personEmergencyContact.toString();
	}

	public String jsonPersonEmergencyContact() {
		return personEmergencyContact == null ? "" : personEmergencyContact.toString();
	}

	public String nomAffichagePersonEmergencyContact() {
		return "contact in case of emergency";
	}

	public String htmTooltipPersonEmergencyContact() {
		return null;
	}

	public String htmPersonEmergencyContact() {
		return personEmergencyContact == null ? "" : StringEscapeUtils.escapeHtml4(strPersonEmergencyContact());
	}

	public void inputPersonEmergencyContact(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_personEmergencyContact")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_personEmergencyContact");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setPersonEmergencyContact classSchoolGuardian inputSchoolGuardian", pk, "PersonEmergencyContact w3-input w3-border ");
				a("name", "setPersonEmergencyContact");
			} else {
				a("class", "valuePersonEmergencyContact classSchoolGuardian inputSchoolGuardian", pk, "PersonEmergencyContact w3-input w3-border ");
				a("name", "personEmergencyContact");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonEmergencyContact', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_personEmergencyContact')); }, function() { addError($('#", classApiMethodMethod, "_personEmergencyContact')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getPersonEmergencyContact() != null && getPersonEmergencyContact())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
			e("span").a("class", "varSchoolGuardian", pk, "PersonEmergencyContact ").f().sx(htmPersonEmergencyContact()).g("span");
		}
	}

	public void htmPersonEmergencyContact(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolGuardianPersonEmergencyContact").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classApiMethodMethod, "_personEmergencyContact").a("class", "").f().sx("contact in case of emergency").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonEmergencyContact(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////
	// personPickup //
	//////////////////

	/**	 The entity personPickup
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean personPickup;
	@JsonIgnore
	public Wrap<Boolean> personPickupWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("personPickup").o(personPickup);

	/**	<br/> The entity personPickup
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personPickup">Find the entity personPickup in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personPickup(Wrap<Boolean> c);

	public Boolean getPersonPickup() {
		return personPickup;
	}

	public void setPersonPickup(Boolean personPickup) {
		this.personPickup = personPickup;
		this.personPickupWrap.alreadyInitialized = true;
	}
	public void setPersonPickup(String o) {
		this.personPickup = SchoolGuardian.staticSetPersonPickup(siteRequest_, o);
		this.personPickupWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPersonPickup(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolGuardian personPickupInit() {
		if(!personPickupWrap.alreadyInitialized) {
			_personPickup(personPickupWrap);
			if(personPickup == null)
				setPersonPickup(personPickupWrap.o);
		}
		personPickupWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static Boolean staticSolrPersonPickup(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonPickup(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonPickup(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrPersonPickup(siteRequest_, SchoolGuardian.staticSolrPersonPickup(siteRequest_, SchoolGuardian.staticSetPersonPickup(siteRequest_, o)));
	}

	public Boolean solrPersonPickup() {
		return SchoolGuardian.staticSolrPersonPickup(siteRequest_, personPickup);
	}

	public String strPersonPickup() {
		return personPickup == null ? "" : personPickup.toString();
	}

	public String jsonPersonPickup() {
		return personPickup == null ? "" : personPickup.toString();
	}

	public String nomAffichagePersonPickup() {
		return "authorized to pickup";
	}

	public String htmTooltipPersonPickup() {
		return null;
	}

	public String htmPersonPickup() {
		return personPickup == null ? "" : StringEscapeUtils.escapeHtml4(strPersonPickup());
	}

	public void inputPersonPickup(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_personPickup")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_personPickup");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setPersonPickup classSchoolGuardian inputSchoolGuardian", pk, "PersonPickup w3-input w3-border ");
				a("name", "setPersonPickup");
			} else {
				a("class", "valuePersonPickup classSchoolGuardian inputSchoolGuardian", pk, "PersonPickup w3-input w3-border ");
				a("name", "personPickup");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonPickup', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_personPickup')); }, function() { addError($('#", classApiMethodMethod, "_personPickup')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getPersonPickup() != null && getPersonPickup())
					a("checked", "checked");
				fg();
			} else {
				f();
				e("option").a("value", "").a("selected", "selected").f().g("option");
				e("option").a("value", "true").f().sx("true").g("option");
				e("option").a("value", "false").f().sx("false").g("option");
				g("select");
			}

		} else {
			e("span").a("class", "varSchoolGuardian", pk, "PersonPickup ").f().sx(htmPersonPickup()).g("span");
		}
	}

	public void htmPersonPickup(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolGuardianPersonPickup").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classApiMethodMethod, "_personPickup").a("class", "").f().sx("authorized to pickup").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonPickup(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	///////////
	// photo //
	///////////

	/**	 The entity photo
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String photo;
	@JsonIgnore
	public Wrap<String> photoWrap = new Wrap<String>().p(this).c(String.class).var("photo").o(photo);

	/**	<br/> The entity photo
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:photo">Find the entity photo in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _photo(Wrap<String> c);

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String o) {
		this.photo = SchoolGuardian.staticSetPhoto(siteRequest_, o);
		this.photoWrap.alreadyInitialized = true;
	}
	public static String staticSetPhoto(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolGuardian photoInit() {
		if(!photoWrap.alreadyInitialized) {
			_photo(photoWrap);
			if(photo == null)
				setPhoto(photoWrap.o);
		}
		photoWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static String staticSolrPhoto(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPhoto(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPhoto(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrPhoto(siteRequest_, SchoolGuardian.staticSolrPhoto(siteRequest_, SchoolGuardian.staticSetPhoto(siteRequest_, o)));
	}

	public String solrPhoto() {
		return SchoolGuardian.staticSolrPhoto(siteRequest_, photo);
	}

	public String strPhoto() {
		return photo == null ? "" : photo;
	}

	public String jsonPhoto() {
		return photo == null ? "" : photo;
	}

	public String nomAffichagePhoto() {
		return "photo";
	}

	public String htmTooltipPhoto() {
		return null;
	}

	public String htmPhoto() {
		return photo == null ? "" : StringEscapeUtils.escapeHtml4(strPhoto());
	}

	public void inputPhoto(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("div").a("class", "imageBase64Div1SchoolGuardian_photo").a("id", "imageBase64Div1SchoolGuardian", pk, "photo").f();
				e("h5").f().sx("Upload image").g("h5"); 
				e("form").a("method", "POST").a("enctype", "multipart/form-data").a("action", "/photo").a("class", "").f();
				e("input").a("type", "hidden").a("name", "pk").a("value", pk).fg(); 
				e("input").a("type", "hidden").a("name", "classSimpleName").a("value", "SchoolGuardian").fg(); 
				e("input").a("name", "file").a("type", "file").a("onchange", "$.ajax({ type: 'POST', enctype: 'multipart/form-data', url: '/photo', data: new FormData(this.form), processData: false, contentType: false}); ").fg(); 
				g("form");
				e("img").a("id", "imageBase64ImgSchoolGuardian", pk, "photo");
					a("class", "imgSchoolGuardian", pk, "Photo w3-image ");
					a("src", StringUtils.isBlank(photo) ? "data:image/png;base64," : photo).a("alt", "");
				fg();
			g("div");
		} else {
			e("span").a("class", "varSchoolGuardian", pk, "Photo ").f().sx(htmPhoto()).g("span");
		}
	}

	public void htmPhoto(String classApiMethodMethod) {
		SchoolGuardian s = (SchoolGuardian)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolGuardianPhoto").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-yellow ").f();
							e("label").a("for", classApiMethodMethod, "_photo").a("class", "").f().sx("photo").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPhoto(classApiMethodMethod);
							} g("div");
							if(
									userKeys.contains(siteRequest_.getUserKey())
									|| Objects.equals(sessionId, siteRequest_.getSessionId())
									|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
									|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
							) {
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-yellow ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_photo')); $('#", classApiMethodMethod, "_photo').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolGuardianForm :input[name=pk]').val() }], 'setPhoto', null, function() { addGlow($('#", classApiMethodMethod, "_photo')); }, function() { addError($('#", classApiMethodMethod, "_photo')); }); ")
											.f();
											e("i").a("class", "far fa-eraser ").f().g("i");
										} g("button");
									} g("div");
								}
							}
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
	}

	//////////////////////////
	// guardianCompleteName //
	//////////////////////////

	/**	 The entity guardianCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String guardianCompleteName;
	@JsonIgnore
	public Wrap<String> guardianCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("guardianCompleteName").o(guardianCompleteName);

	/**	<br/> The entity guardianCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.guardian.SchoolGuardian&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:guardianCompleteName">Find the entity guardianCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _guardianCompleteName(Wrap<String> c);

	public String getGuardianCompleteName() {
		return guardianCompleteName;
	}
	public void setGuardianCompleteName(String o) {
		this.guardianCompleteName = SchoolGuardian.staticSetGuardianCompleteName(siteRequest_, o);
		this.guardianCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetGuardianCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolGuardian guardianCompleteNameInit() {
		if(!guardianCompleteNameWrap.alreadyInitialized) {
			_guardianCompleteName(guardianCompleteNameWrap);
			if(guardianCompleteName == null)
				setGuardianCompleteName(guardianCompleteNameWrap.o);
		}
		guardianCompleteNameWrap.alreadyInitialized(true);
		return (SchoolGuardian)this;
	}

	public static String staticSolrGuardianCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrGuardianCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqGuardianCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolGuardian.staticSolrStrGuardianCompleteName(siteRequest_, SchoolGuardian.staticSolrGuardianCompleteName(siteRequest_, SchoolGuardian.staticSetGuardianCompleteName(siteRequest_, o)));
	}

	public String solrGuardianCompleteName() {
		return SchoolGuardian.staticSolrGuardianCompleteName(siteRequest_, guardianCompleteName);
	}

	public String strGuardianCompleteName() {
		return guardianCompleteName == null ? "" : guardianCompleteName;
	}

	public String jsonGuardianCompleteName() {
		return guardianCompleteName == null ? "" : guardianCompleteName;
	}

	public String nomAffichageGuardianCompleteName() {
		return null;
	}

	public String htmTooltipGuardianCompleteName() {
		return null;
	}

	public String htmGuardianCompleteName() {
		return guardianCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strGuardianCompleteName());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSchoolGuardian = false;

	public SchoolGuardian initDeepSchoolGuardian(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSchoolGuardian) {
			alreadyInitializedSchoolGuardian = true;
			initDeepSchoolGuardian();
		}
		return (SchoolGuardian)this;
	}

	public void initDeepSchoolGuardian() {
		initSchoolGuardian();
		super.initDeepCluster(siteRequest_);
	}

	public void initSchoolGuardian() {
		guardianKeyInit();
		enrollmentKeysInit();
		familySortInit();
		schoolSortInit();
		enrollmentSearchInit();
		enrollmentsInit();
		userKeysInit();
		schoolKeysInit();
		yearKeysInit();
		seasonKeysInit();
		sessionKeysInit();
		ageKeysInit();
		personFirstNameInit();
		personFirstNamePreferredInit();
		familyNameInit();
		personCompleteNameInit();
		personCompleteNamePreferredInit();
		personFormalNameInit();
		personOccupationInit();
		personPhoneNumberInit();
		personEmailInit();
		personRelationInit();
		personSmsInit();
		personReceiveEmailInit();
		personEmergencyContactInit();
		personPickupInit();
		photoInit();
		guardianCompleteNameInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSchoolGuardian(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSchoolGuardian(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
		if(enrollmentSearch != null)
			enrollmentSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSchoolGuardian(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSchoolGuardian(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSchoolGuardian(String var) {
		SchoolGuardian oSchoolGuardian = (SchoolGuardian)this;
		switch(var) {
			case "guardianKey":
				return oSchoolGuardian.guardianKey;
			case "enrollmentKeys":
				return oSchoolGuardian.enrollmentKeys;
			case "familySort":
				return oSchoolGuardian.familySort;
			case "schoolSort":
				return oSchoolGuardian.schoolSort;
			case "enrollmentSearch":
				return oSchoolGuardian.enrollmentSearch;
			case "enrollments":
				return oSchoolGuardian.enrollments;
			case "userKeys":
				return oSchoolGuardian.userKeys;
			case "schoolKeys":
				return oSchoolGuardian.schoolKeys;
			case "yearKeys":
				return oSchoolGuardian.yearKeys;
			case "seasonKeys":
				return oSchoolGuardian.seasonKeys;
			case "sessionKeys":
				return oSchoolGuardian.sessionKeys;
			case "ageKeys":
				return oSchoolGuardian.ageKeys;
			case "personFirstName":
				return oSchoolGuardian.personFirstName;
			case "personFirstNamePreferred":
				return oSchoolGuardian.personFirstNamePreferred;
			case "familyName":
				return oSchoolGuardian.familyName;
			case "personCompleteName":
				return oSchoolGuardian.personCompleteName;
			case "personCompleteNamePreferred":
				return oSchoolGuardian.personCompleteNamePreferred;
			case "personFormalName":
				return oSchoolGuardian.personFormalName;
			case "personOccupation":
				return oSchoolGuardian.personOccupation;
			case "personPhoneNumber":
				return oSchoolGuardian.personPhoneNumber;
			case "personEmail":
				return oSchoolGuardian.personEmail;
			case "personRelation":
				return oSchoolGuardian.personRelation;
			case "personSms":
				return oSchoolGuardian.personSms;
			case "personReceiveEmail":
				return oSchoolGuardian.personReceiveEmail;
			case "personEmergencyContact":
				return oSchoolGuardian.personEmergencyContact;
			case "personPickup":
				return oSchoolGuardian.personPickup;
			case "photo":
				return oSchoolGuardian.photo;
			case "guardianCompleteName":
				return oSchoolGuardian.guardianCompleteName;
			default:
				return super.obtainCluster(var);
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
				o = attributeSchoolGuardian(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSchoolGuardian(String var, Object val) {
		SchoolGuardian oSchoolGuardian = (SchoolGuardian)this;
		switch(var) {
			case "enrollmentKeys":
				oSchoolGuardian.addEnrollmentKeys((Long)val);
				if(!saves.contains(var))
					saves.add(var);
				return val;
			default:
				return super.attributeCluster(var, val);
		}
	}

	///////////////
	// staticSet //
	///////////////

	public static Object staticSetForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSetSchoolGuardian(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSchoolGuardian(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "guardianKey":
			return SchoolGuardian.staticSetGuardianKey(siteRequest_, o);
		case "enrollmentKeys":
			return SchoolGuardian.staticSetEnrollmentKeys(siteRequest_, o);
		case "familySort":
			return SchoolGuardian.staticSetFamilySort(siteRequest_, o);
		case "schoolSort":
			return SchoolGuardian.staticSetSchoolSort(siteRequest_, o);
		case "userKeys":
			return SchoolGuardian.staticSetUserKeys(siteRequest_, o);
		case "schoolKeys":
			return SchoolGuardian.staticSetSchoolKeys(siteRequest_, o);
		case "yearKeys":
			return SchoolGuardian.staticSetYearKeys(siteRequest_, o);
		case "seasonKeys":
			return SchoolGuardian.staticSetSeasonKeys(siteRequest_, o);
		case "sessionKeys":
			return SchoolGuardian.staticSetSessionKeys(siteRequest_, o);
		case "ageKeys":
			return SchoolGuardian.staticSetAgeKeys(siteRequest_, o);
		case "personFirstName":
			return SchoolGuardian.staticSetPersonFirstName(siteRequest_, o);
		case "personFirstNamePreferred":
			return SchoolGuardian.staticSetPersonFirstNamePreferred(siteRequest_, o);
		case "familyName":
			return SchoolGuardian.staticSetFamilyName(siteRequest_, o);
		case "personCompleteName":
			return SchoolGuardian.staticSetPersonCompleteName(siteRequest_, o);
		case "personCompleteNamePreferred":
			return SchoolGuardian.staticSetPersonCompleteNamePreferred(siteRequest_, o);
		case "personFormalName":
			return SchoolGuardian.staticSetPersonFormalName(siteRequest_, o);
		case "personOccupation":
			return SchoolGuardian.staticSetPersonOccupation(siteRequest_, o);
		case "personPhoneNumber":
			return SchoolGuardian.staticSetPersonPhoneNumber(siteRequest_, o);
		case "personEmail":
			return SchoolGuardian.staticSetPersonEmail(siteRequest_, o);
		case "personRelation":
			return SchoolGuardian.staticSetPersonRelation(siteRequest_, o);
		case "personSms":
			return SchoolGuardian.staticSetPersonSms(siteRequest_, o);
		case "personReceiveEmail":
			return SchoolGuardian.staticSetPersonReceiveEmail(siteRequest_, o);
		case "personEmergencyContact":
			return SchoolGuardian.staticSetPersonEmergencyContact(siteRequest_, o);
		case "personPickup":
			return SchoolGuardian.staticSetPersonPickup(siteRequest_, o);
		case "photo":
			return SchoolGuardian.staticSetPhoto(siteRequest_, o);
		case "guardianCompleteName":
			return SchoolGuardian.staticSetGuardianCompleteName(siteRequest_, o);
			default:
				return Cluster.staticSetCluster(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrSchoolGuardian(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrSchoolGuardian(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "guardianKey":
			return SchoolGuardian.staticSolrGuardianKey(siteRequest_, (Long)o);
		case "enrollmentKeys":
			return SchoolGuardian.staticSolrEnrollmentKeys(siteRequest_, (Long)o);
		case "familySort":
			return SchoolGuardian.staticSolrFamilySort(siteRequest_, (Integer)o);
		case "schoolSort":
			return SchoolGuardian.staticSolrSchoolSort(siteRequest_, (Integer)o);
		case "userKeys":
			return SchoolGuardian.staticSolrUserKeys(siteRequest_, (Long)o);
		case "schoolKeys":
			return SchoolGuardian.staticSolrSchoolKeys(siteRequest_, (Long)o);
		case "yearKeys":
			return SchoolGuardian.staticSolrYearKeys(siteRequest_, (Long)o);
		case "seasonKeys":
			return SchoolGuardian.staticSolrSeasonKeys(siteRequest_, (Long)o);
		case "sessionKeys":
			return SchoolGuardian.staticSolrSessionKeys(siteRequest_, (Long)o);
		case "ageKeys":
			return SchoolGuardian.staticSolrAgeKeys(siteRequest_, (Long)o);
		case "personFirstName":
			return SchoolGuardian.staticSolrPersonFirstName(siteRequest_, (String)o);
		case "personFirstNamePreferred":
			return SchoolGuardian.staticSolrPersonFirstNamePreferred(siteRequest_, (String)o);
		case "familyName":
			return SchoolGuardian.staticSolrFamilyName(siteRequest_, (String)o);
		case "personCompleteName":
			return SchoolGuardian.staticSolrPersonCompleteName(siteRequest_, (String)o);
		case "personCompleteNamePreferred":
			return SchoolGuardian.staticSolrPersonCompleteNamePreferred(siteRequest_, (String)o);
		case "personFormalName":
			return SchoolGuardian.staticSolrPersonFormalName(siteRequest_, (String)o);
		case "personOccupation":
			return SchoolGuardian.staticSolrPersonOccupation(siteRequest_, (String)o);
		case "personPhoneNumber":
			return SchoolGuardian.staticSolrPersonPhoneNumber(siteRequest_, (String)o);
		case "personEmail":
			return SchoolGuardian.staticSolrPersonEmail(siteRequest_, (String)o);
		case "personRelation":
			return SchoolGuardian.staticSolrPersonRelation(siteRequest_, (String)o);
		case "personSms":
			return SchoolGuardian.staticSolrPersonSms(siteRequest_, (Boolean)o);
		case "personReceiveEmail":
			return SchoolGuardian.staticSolrPersonReceiveEmail(siteRequest_, (Boolean)o);
		case "personEmergencyContact":
			return SchoolGuardian.staticSolrPersonEmergencyContact(siteRequest_, (Boolean)o);
		case "personPickup":
			return SchoolGuardian.staticSolrPersonPickup(siteRequest_, (Boolean)o);
		case "photo":
			return SchoolGuardian.staticSolrPhoto(siteRequest_, (String)o);
		case "guardianCompleteName":
			return SchoolGuardian.staticSolrGuardianCompleteName(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrCluster(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrSchoolGuardian(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrSchoolGuardian(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "guardianKey":
			return SchoolGuardian.staticSolrStrGuardianKey(siteRequest_, (Long)o);
		case "enrollmentKeys":
			return SchoolGuardian.staticSolrStrEnrollmentKeys(siteRequest_, (Long)o);
		case "familySort":
			return SchoolGuardian.staticSolrStrFamilySort(siteRequest_, (Integer)o);
		case "schoolSort":
			return SchoolGuardian.staticSolrStrSchoolSort(siteRequest_, (Integer)o);
		case "userKeys":
			return SchoolGuardian.staticSolrStrUserKeys(siteRequest_, (Long)o);
		case "schoolKeys":
			return SchoolGuardian.staticSolrStrSchoolKeys(siteRequest_, (Long)o);
		case "yearKeys":
			return SchoolGuardian.staticSolrStrYearKeys(siteRequest_, (Long)o);
		case "seasonKeys":
			return SchoolGuardian.staticSolrStrSeasonKeys(siteRequest_, (Long)o);
		case "sessionKeys":
			return SchoolGuardian.staticSolrStrSessionKeys(siteRequest_, (Long)o);
		case "ageKeys":
			return SchoolGuardian.staticSolrStrAgeKeys(siteRequest_, (Long)o);
		case "personFirstName":
			return SchoolGuardian.staticSolrStrPersonFirstName(siteRequest_, (String)o);
		case "personFirstNamePreferred":
			return SchoolGuardian.staticSolrStrPersonFirstNamePreferred(siteRequest_, (String)o);
		case "familyName":
			return SchoolGuardian.staticSolrStrFamilyName(siteRequest_, (String)o);
		case "personCompleteName":
			return SchoolGuardian.staticSolrStrPersonCompleteName(siteRequest_, (String)o);
		case "personCompleteNamePreferred":
			return SchoolGuardian.staticSolrStrPersonCompleteNamePreferred(siteRequest_, (String)o);
		case "personFormalName":
			return SchoolGuardian.staticSolrStrPersonFormalName(siteRequest_, (String)o);
		case "personOccupation":
			return SchoolGuardian.staticSolrStrPersonOccupation(siteRequest_, (String)o);
		case "personPhoneNumber":
			return SchoolGuardian.staticSolrStrPersonPhoneNumber(siteRequest_, (String)o);
		case "personEmail":
			return SchoolGuardian.staticSolrStrPersonEmail(siteRequest_, (String)o);
		case "personRelation":
			return SchoolGuardian.staticSolrStrPersonRelation(siteRequest_, (String)o);
		case "personSms":
			return SchoolGuardian.staticSolrStrPersonSms(siteRequest_, (Boolean)o);
		case "personReceiveEmail":
			return SchoolGuardian.staticSolrStrPersonReceiveEmail(siteRequest_, (Boolean)o);
		case "personEmergencyContact":
			return SchoolGuardian.staticSolrStrPersonEmergencyContact(siteRequest_, (Boolean)o);
		case "personPickup":
			return SchoolGuardian.staticSolrStrPersonPickup(siteRequest_, (Boolean)o);
		case "photo":
			return SchoolGuardian.staticSolrStrPhoto(siteRequest_, (String)o);
		case "guardianCompleteName":
			return SchoolGuardian.staticSolrStrGuardianCompleteName(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqSchoolGuardian(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqSchoolGuardian(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "guardianKey":
			return SchoolGuardian.staticSolrFqGuardianKey(siteRequest_, o);
		case "enrollmentKeys":
			return SchoolGuardian.staticSolrFqEnrollmentKeys(siteRequest_, o);
		case "familySort":
			return SchoolGuardian.staticSolrFqFamilySort(siteRequest_, o);
		case "schoolSort":
			return SchoolGuardian.staticSolrFqSchoolSort(siteRequest_, o);
		case "userKeys":
			return SchoolGuardian.staticSolrFqUserKeys(siteRequest_, o);
		case "schoolKeys":
			return SchoolGuardian.staticSolrFqSchoolKeys(siteRequest_, o);
		case "yearKeys":
			return SchoolGuardian.staticSolrFqYearKeys(siteRequest_, o);
		case "seasonKeys":
			return SchoolGuardian.staticSolrFqSeasonKeys(siteRequest_, o);
		case "sessionKeys":
			return SchoolGuardian.staticSolrFqSessionKeys(siteRequest_, o);
		case "ageKeys":
			return SchoolGuardian.staticSolrFqAgeKeys(siteRequest_, o);
		case "personFirstName":
			return SchoolGuardian.staticSolrFqPersonFirstName(siteRequest_, o);
		case "personFirstNamePreferred":
			return SchoolGuardian.staticSolrFqPersonFirstNamePreferred(siteRequest_, o);
		case "familyName":
			return SchoolGuardian.staticSolrFqFamilyName(siteRequest_, o);
		case "personCompleteName":
			return SchoolGuardian.staticSolrFqPersonCompleteName(siteRequest_, o);
		case "personCompleteNamePreferred":
			return SchoolGuardian.staticSolrFqPersonCompleteNamePreferred(siteRequest_, o);
		case "personFormalName":
			return SchoolGuardian.staticSolrFqPersonFormalName(siteRequest_, o);
		case "personOccupation":
			return SchoolGuardian.staticSolrFqPersonOccupation(siteRequest_, o);
		case "personPhoneNumber":
			return SchoolGuardian.staticSolrFqPersonPhoneNumber(siteRequest_, o);
		case "personEmail":
			return SchoolGuardian.staticSolrFqPersonEmail(siteRequest_, o);
		case "personRelation":
			return SchoolGuardian.staticSolrFqPersonRelation(siteRequest_, o);
		case "personSms":
			return SchoolGuardian.staticSolrFqPersonSms(siteRequest_, o);
		case "personReceiveEmail":
			return SchoolGuardian.staticSolrFqPersonReceiveEmail(siteRequest_, o);
		case "personEmergencyContact":
			return SchoolGuardian.staticSolrFqPersonEmergencyContact(siteRequest_, o);
		case "personPickup":
			return SchoolGuardian.staticSolrFqPersonPickup(siteRequest_, o);
		case "photo":
			return SchoolGuardian.staticSolrFqPhoto(siteRequest_, o);
		case "guardianCompleteName":
			return SchoolGuardian.staticSolrFqGuardianCompleteName(siteRequest_, o);
			default:
				return Cluster.staticSolrFqCluster(entityVar,  siteRequest_, o);
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
					o = defineSchoolGuardian(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolGuardian(String var, String val) {
		switch(var) {
			case "personFirstName":
				if(val != null)
					setPersonFirstName(val);
				saves.add(var);
				return val;
			case "personFirstNamePreferred":
				if(val != null)
					setPersonFirstNamePreferred(val);
				saves.add(var);
				return val;
			case "familyName":
				if(val != null)
					setFamilyName(val);
				saves.add(var);
				return val;
			case "personPhoneNumber":
				if(val != null)
					setPersonPhoneNumber(val);
				saves.add(var);
				return val;
			case "personRelation":
				if(val != null)
					setPersonRelation(val);
				saves.add(var);
				return val;
			case "personEmergencyContact":
				if(val != null)
					setPersonEmergencyContact(val);
				saves.add(var);
				return val;
			case "personPickup":
				if(val != null)
					setPersonPickup(val);
				saves.add(var);
				return val;
			case "photo":
				if(val != null)
					setPhoto(val);
				saves.add(var);
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateSchoolGuardian(solrDocument);
	}
	public void populateSchoolGuardian(SolrDocument solrDocument) {
		SchoolGuardian oSchoolGuardian = (SchoolGuardian)this;
		saves = (List<String>)solrDocument.get("saves_stored_strings");
		if(saves != null) {

			if(saves.contains("guardianKey")) {
				Long guardianKey = (Long)solrDocument.get("guardianKey_stored_long");
				if(guardianKey != null)
					oSchoolGuardian.setGuardianKey(guardianKey);
			}

			List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
			if(enrollmentKeys != null)
				oSchoolGuardian.enrollmentKeys.addAll(enrollmentKeys);

			if(saves.contains("familySort")) {
				Integer familySort = (Integer)solrDocument.get("familySort_stored_int");
				if(familySort != null)
					oSchoolGuardian.setFamilySort(familySort);
			}

			if(saves.contains("schoolSort")) {
				Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
				if(schoolSort != null)
					oSchoolGuardian.setSchoolSort(schoolSort);
			}

			if(saves.contains("userKeys")) {
				List<Long> userKeys = (List<Long>)solrDocument.get("userKeys_stored_longs");
				if(userKeys != null)
					oSchoolGuardian.userKeys.addAll(userKeys);
			}

			if(saves.contains("schoolKeys")) {
				List<Long> schoolKeys = (List<Long>)solrDocument.get("schoolKeys_stored_longs");
				if(schoolKeys != null)
					oSchoolGuardian.schoolKeys.addAll(schoolKeys);
			}

			if(saves.contains("yearKeys")) {
				List<Long> yearKeys = (List<Long>)solrDocument.get("yearKeys_stored_longs");
				if(yearKeys != null)
					oSchoolGuardian.yearKeys.addAll(yearKeys);
			}

			if(saves.contains("seasonKeys")) {
				List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
				if(seasonKeys != null)
					oSchoolGuardian.seasonKeys.addAll(seasonKeys);
			}

			if(saves.contains("sessionKeys")) {
				List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
				if(sessionKeys != null)
					oSchoolGuardian.sessionKeys.addAll(sessionKeys);
			}

			if(saves.contains("ageKeys")) {
				List<Long> ageKeys = (List<Long>)solrDocument.get("ageKeys_stored_longs");
				if(ageKeys != null)
					oSchoolGuardian.ageKeys.addAll(ageKeys);
			}

			if(saves.contains("personFirstName")) {
				String personFirstName = (String)solrDocument.get("personFirstName_stored_string");
				if(personFirstName != null)
					oSchoolGuardian.setPersonFirstName(personFirstName);
			}

			if(saves.contains("personFirstNamePreferred")) {
				String personFirstNamePreferred = (String)solrDocument.get("personFirstNamePreferred_stored_string");
				if(personFirstNamePreferred != null)
					oSchoolGuardian.setPersonFirstNamePreferred(personFirstNamePreferred);
			}

			if(saves.contains("familyName")) {
				String familyName = (String)solrDocument.get("familyName_stored_string");
				if(familyName != null)
					oSchoolGuardian.setFamilyName(familyName);
			}

			if(saves.contains("personCompleteName")) {
				String personCompleteName = (String)solrDocument.get("personCompleteName_stored_string");
				if(personCompleteName != null)
					oSchoolGuardian.setPersonCompleteName(personCompleteName);
			}

			if(saves.contains("personCompleteNamePreferred")) {
				String personCompleteNamePreferred = (String)solrDocument.get("personCompleteNamePreferred_stored_string");
				if(personCompleteNamePreferred != null)
					oSchoolGuardian.setPersonCompleteNamePreferred(personCompleteNamePreferred);
			}

			if(saves.contains("personFormalName")) {
				String personFormalName = (String)solrDocument.get("personFormalName_stored_string");
				if(personFormalName != null)
					oSchoolGuardian.setPersonFormalName(personFormalName);
			}

			if(saves.contains("personOccupation")) {
				String personOccupation = (String)solrDocument.get("personOccupation_stored_string");
				if(personOccupation != null)
					oSchoolGuardian.setPersonOccupation(personOccupation);
			}

			if(saves.contains("personPhoneNumber")) {
				String personPhoneNumber = (String)solrDocument.get("personPhoneNumber_stored_string");
				if(personPhoneNumber != null)
					oSchoolGuardian.setPersonPhoneNumber(personPhoneNumber);
			}

			if(saves.contains("personEmail")) {
				String personEmail = (String)solrDocument.get("personEmail_stored_string");
				if(personEmail != null)
					oSchoolGuardian.setPersonEmail(personEmail);
			}

			if(saves.contains("personRelation")) {
				String personRelation = (String)solrDocument.get("personRelation_stored_string");
				if(personRelation != null)
					oSchoolGuardian.setPersonRelation(personRelation);
			}

			if(saves.contains("personSms")) {
				Boolean personSms = (Boolean)solrDocument.get("personSms_stored_boolean");
				if(personSms != null)
					oSchoolGuardian.setPersonSms(personSms);
			}

			if(saves.contains("personReceiveEmail")) {
				Boolean personReceiveEmail = (Boolean)solrDocument.get("personReceiveEmail_stored_boolean");
				if(personReceiveEmail != null)
					oSchoolGuardian.setPersonReceiveEmail(personReceiveEmail);
			}

			if(saves.contains("personEmergencyContact")) {
				Boolean personEmergencyContact = (Boolean)solrDocument.get("personEmergencyContact_stored_boolean");
				if(personEmergencyContact != null)
					oSchoolGuardian.setPersonEmergencyContact(personEmergencyContact);
			}

			if(saves.contains("personPickup")) {
				Boolean personPickup = (Boolean)solrDocument.get("personPickup_stored_boolean");
				if(personPickup != null)
					oSchoolGuardian.setPersonPickup(personPickup);
			}

			if(saves.contains("photo")) {
				String photo = (String)solrDocument.get("photo_stored_string");
				if(photo != null)
					oSchoolGuardian.setPhoto(photo);
			}

			if(saves.contains("guardianCompleteName")) {
				String guardianCompleteName = (String)solrDocument.get("guardianCompleteName_stored_string");
				if(guardianCompleteName != null)
					oSchoolGuardian.setGuardianCompleteName(guardianCompleteName);
			}
		}

		super.populateCluster(solrDocument);
	}

	/////////////
	// index //
	/////////////

	public static void index() {
		try {
			SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.getSiteConfig().setConfigPath("/usr/local/src/computate-scolaire/config/computate-scolaire.config");
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			SolrQuery solrQuery = new SolrQuery();
			solrQuery.setQuery("*:*");
			solrQuery.setRows(1);
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.guardian.SchoolGuardian"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			SchoolGuardian o = new SchoolGuardian();
			o.siteRequestSchoolGuardian(siteRequest);
			o.initDeepSchoolGuardian(siteRequest);
			o.indexSchoolGuardian();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexSchoolGuardian();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexSchoolGuardian(document);
	}

	public void indexSchoolGuardian(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolGuardian(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolGuardian() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolGuardian(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolGuardian(SolrInputDocument document) {
		if(guardianKey != null) {
			document.addField("guardianKey_indexed_long", guardianKey);
			document.addField("guardianKey_stored_long", guardianKey);
		}
		if(enrollmentKeys != null) {
			for(java.lang.Long o : enrollmentKeys) {
				document.addField("enrollmentKeys_indexed_longs", o);
			}
			for(java.lang.Long o : enrollmentKeys) {
				document.addField("enrollmentKeys_stored_longs", o);
			}
		}
		if(familySort != null) {
			document.addField("familySort_indexed_int", familySort);
			document.addField("familySort_stored_int", familySort);
		}
		if(schoolSort != null) {
			document.addField("schoolSort_indexed_int", schoolSort);
			document.addField("schoolSort_stored_int", schoolSort);
		}
		if(userKeys != null) {
			for(java.lang.Long o : userKeys) {
				document.addField("userKeys_indexed_longs", o);
			}
			for(java.lang.Long o : userKeys) {
				document.addField("userKeys_stored_longs", o);
			}
		}
		if(schoolKeys != null) {
			for(java.lang.Long o : schoolKeys) {
				document.addField("schoolKeys_indexed_longs", o);
			}
			for(java.lang.Long o : schoolKeys) {
				document.addField("schoolKeys_stored_longs", o);
			}
		}
		if(yearKeys != null) {
			for(java.lang.Long o : yearKeys) {
				document.addField("yearKeys_indexed_longs", o);
			}
			for(java.lang.Long o : yearKeys) {
				document.addField("yearKeys_stored_longs", o);
			}
		}
		if(seasonKeys != null) {
			for(java.lang.Long o : seasonKeys) {
				document.addField("seasonKeys_indexed_longs", o);
			}
			for(java.lang.Long o : seasonKeys) {
				document.addField("seasonKeys_stored_longs", o);
			}
		}
		if(sessionKeys != null) {
			for(java.lang.Long o : sessionKeys) {
				document.addField("sessionKeys_indexed_longs", o);
			}
			for(java.lang.Long o : sessionKeys) {
				document.addField("sessionKeys_stored_longs", o);
			}
		}
		if(ageKeys != null) {
			for(java.lang.Long o : ageKeys) {
				document.addField("ageKeys_indexed_longs", o);
			}
			for(java.lang.Long o : ageKeys) {
				document.addField("ageKeys_stored_longs", o);
			}
		}
		if(personFirstName != null) {
			document.addField("personFirstName_indexed_string", personFirstName);
			document.addField("personFirstName_stored_string", personFirstName);
		}
		if(personFirstNamePreferred != null) {
			document.addField("personFirstNamePreferred_indexed_string", personFirstNamePreferred);
			document.addField("personFirstNamePreferred_stored_string", personFirstNamePreferred);
		}
		if(familyName != null) {
			document.addField("familyName_indexed_string", familyName);
			document.addField("familyName_stored_string", familyName);
		}
		if(personCompleteName != null) {
			document.addField("personCompleteName_indexed_string", personCompleteName);
			document.addField("personCompleteName_stored_string", personCompleteName);
		}
		if(personCompleteNamePreferred != null) {
			document.addField("personCompleteNamePreferred_indexed_string", personCompleteNamePreferred);
			document.addField("personCompleteNamePreferred_stored_string", personCompleteNamePreferred);
		}
		if(personFormalName != null) {
			document.addField("personFormalName_indexed_string", personFormalName);
			document.addField("personFormalName_stored_string", personFormalName);
		}
		if(personOccupation != null) {
			document.addField("personOccupation_indexed_string", personOccupation);
			document.addField("personOccupation_stored_string", personOccupation);
		}
		if(personPhoneNumber != null) {
			document.addField("personPhoneNumber_indexed_string", personPhoneNumber);
			document.addField("personPhoneNumber_stored_string", personPhoneNumber);
		}
		if(personEmail != null) {
			document.addField("personEmail_indexed_string", personEmail);
			document.addField("personEmail_stored_string", personEmail);
		}
		if(personRelation != null) {
			document.addField("personRelation_indexed_string", personRelation);
			document.addField("personRelation_stored_string", personRelation);
		}
		if(personSms != null) {
			document.addField("personSms_indexed_boolean", personSms);
			document.addField("personSms_stored_boolean", personSms);
		}
		if(personReceiveEmail != null) {
			document.addField("personReceiveEmail_indexed_boolean", personReceiveEmail);
			document.addField("personReceiveEmail_stored_boolean", personReceiveEmail);
		}
		if(personEmergencyContact != null) {
			document.addField("personEmergencyContact_indexed_boolean", personEmergencyContact);
			document.addField("personEmergencyContact_stored_boolean", personEmergencyContact);
		}
		if(personPickup != null) {
			document.addField("personPickup_indexed_boolean", personPickup);
			document.addField("personPickup_stored_boolean", personPickup);
		}
		if(photo != null) {
			document.addField("photo_stored_string", photo);
		}
		if(guardianCompleteName != null) {
			document.addField("guardianCompleteName_indexed_string", guardianCompleteName);
			document.addField("guardianCompleteName_stored_string", guardianCompleteName);
		}
		super.indexCluster(document);

	}

	public void unindexSchoolGuardian() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepSchoolGuardian(siteRequest);
			SolrClient solrClient = siteContext.getSolrClient();
			solrClient.deleteById(id.toString());
			solrClient.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexedSchoolGuardian(String entityVar) {
		switch(entityVar) {
			case "guardianKey":
				return "guardianKey_indexed_long";
			case "enrollmentKeys":
				return "enrollmentKeys_indexed_longs";
			case "familySort":
				return "familySort_indexed_int";
			case "schoolSort":
				return "schoolSort_indexed_int";
			case "userKeys":
				return "userKeys_indexed_longs";
			case "schoolKeys":
				return "schoolKeys_indexed_longs";
			case "yearKeys":
				return "yearKeys_indexed_longs";
			case "seasonKeys":
				return "seasonKeys_indexed_longs";
			case "sessionKeys":
				return "sessionKeys_indexed_longs";
			case "ageKeys":
				return "ageKeys_indexed_longs";
			case "personFirstName":
				return "personFirstName_indexed_string";
			case "personFirstNamePreferred":
				return "personFirstNamePreferred_indexed_string";
			case "familyName":
				return "familyName_indexed_string";
			case "personCompleteName":
				return "personCompleteName_indexed_string";
			case "personCompleteNamePreferred":
				return "personCompleteNamePreferred_indexed_string";
			case "personFormalName":
				return "personFormalName_indexed_string";
			case "personOccupation":
				return "personOccupation_indexed_string";
			case "personPhoneNumber":
				return "personPhoneNumber_indexed_string";
			case "personEmail":
				return "personEmail_indexed_string";
			case "personRelation":
				return "personRelation_indexed_string";
			case "personSms":
				return "personSms_indexed_boolean";
			case "personReceiveEmail":
				return "personReceiveEmail_indexed_boolean";
			case "personEmergencyContact":
				return "personEmergencyContact_indexed_boolean";
			case "personPickup":
				return "personPickup_indexed_boolean";
			case "guardianCompleteName":
				return "guardianCompleteName_indexed_string";
			default:
				return Cluster.varIndexedCluster(entityVar);
		}
	}

	public static String varSearchSchoolGuardian(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSearchCluster(entityVar);
		}
	}

	public static String varSuggestedSchoolGuardian(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSuggestedCluster(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrDocument solrDocument) {
		storeSchoolGuardian(solrDocument);
	}
	public void storeSchoolGuardian(SolrDocument solrDocument) {
		SchoolGuardian oSchoolGuardian = (SchoolGuardian)this;

		Long guardianKey = (Long)solrDocument.get("guardianKey_stored_long");
		if(guardianKey != null)
			oSchoolGuardian.setGuardianKey(guardianKey);

		List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
		if(enrollmentKeys != null)
			oSchoolGuardian.enrollmentKeys.addAll(enrollmentKeys);

		Integer familySort = (Integer)solrDocument.get("familySort_stored_int");
		if(familySort != null)
			oSchoolGuardian.setFamilySort(familySort);

		Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
		if(schoolSort != null)
			oSchoolGuardian.setSchoolSort(schoolSort);

		List<Long> userKeys = (List<Long>)solrDocument.get("userKeys_stored_longs");
		if(userKeys != null)
			oSchoolGuardian.userKeys.addAll(userKeys);

		List<Long> schoolKeys = (List<Long>)solrDocument.get("schoolKeys_stored_longs");
		if(schoolKeys != null)
			oSchoolGuardian.schoolKeys.addAll(schoolKeys);

		List<Long> yearKeys = (List<Long>)solrDocument.get("yearKeys_stored_longs");
		if(yearKeys != null)
			oSchoolGuardian.yearKeys.addAll(yearKeys);

		List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
		if(seasonKeys != null)
			oSchoolGuardian.seasonKeys.addAll(seasonKeys);

		List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
		if(sessionKeys != null)
			oSchoolGuardian.sessionKeys.addAll(sessionKeys);

		List<Long> ageKeys = (List<Long>)solrDocument.get("ageKeys_stored_longs");
		if(ageKeys != null)
			oSchoolGuardian.ageKeys.addAll(ageKeys);

		String personFirstName = (String)solrDocument.get("personFirstName_stored_string");
		if(personFirstName != null)
			oSchoolGuardian.setPersonFirstName(personFirstName);

		String personFirstNamePreferred = (String)solrDocument.get("personFirstNamePreferred_stored_string");
		if(personFirstNamePreferred != null)
			oSchoolGuardian.setPersonFirstNamePreferred(personFirstNamePreferred);

		String familyName = (String)solrDocument.get("familyName_stored_string");
		if(familyName != null)
			oSchoolGuardian.setFamilyName(familyName);

		String personCompleteName = (String)solrDocument.get("personCompleteName_stored_string");
		if(personCompleteName != null)
			oSchoolGuardian.setPersonCompleteName(personCompleteName);

		String personCompleteNamePreferred = (String)solrDocument.get("personCompleteNamePreferred_stored_string");
		if(personCompleteNamePreferred != null)
			oSchoolGuardian.setPersonCompleteNamePreferred(personCompleteNamePreferred);

		String personFormalName = (String)solrDocument.get("personFormalName_stored_string");
		if(personFormalName != null)
			oSchoolGuardian.setPersonFormalName(personFormalName);

		String personOccupation = (String)solrDocument.get("personOccupation_stored_string");
		if(personOccupation != null)
			oSchoolGuardian.setPersonOccupation(personOccupation);

		String personPhoneNumber = (String)solrDocument.get("personPhoneNumber_stored_string");
		if(personPhoneNumber != null)
			oSchoolGuardian.setPersonPhoneNumber(personPhoneNumber);

		String personEmail = (String)solrDocument.get("personEmail_stored_string");
		if(personEmail != null)
			oSchoolGuardian.setPersonEmail(personEmail);

		String personRelation = (String)solrDocument.get("personRelation_stored_string");
		if(personRelation != null)
			oSchoolGuardian.setPersonRelation(personRelation);

		Boolean personSms = (Boolean)solrDocument.get("personSms_stored_boolean");
		if(personSms != null)
			oSchoolGuardian.setPersonSms(personSms);

		Boolean personReceiveEmail = (Boolean)solrDocument.get("personReceiveEmail_stored_boolean");
		if(personReceiveEmail != null)
			oSchoolGuardian.setPersonReceiveEmail(personReceiveEmail);

		Boolean personEmergencyContact = (Boolean)solrDocument.get("personEmergencyContact_stored_boolean");
		if(personEmergencyContact != null)
			oSchoolGuardian.setPersonEmergencyContact(personEmergencyContact);

		Boolean personPickup = (Boolean)solrDocument.get("personPickup_stored_boolean");
		if(personPickup != null)
			oSchoolGuardian.setPersonPickup(personPickup);

		String photo = (String)solrDocument.get("photo_stored_string");
		if(photo != null)
			oSchoolGuardian.setPhoto(photo);

		String guardianCompleteName = (String)solrDocument.get("guardianCompleteName_stored_string");
		if(guardianCompleteName != null)
			oSchoolGuardian.setGuardianCompleteName(guardianCompleteName);

		super.storeCluster(solrDocument);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSchoolGuardian() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SchoolGuardian) {
			SchoolGuardian original = (SchoolGuardian)o;
			if(!Objects.equals(guardianKey, original.getGuardianKey()))
				apiRequest.addVars("guardianKey");
			if(!Objects.equals(enrollmentKeys, original.getEnrollmentKeys()))
				apiRequest.addVars("enrollmentKeys");
			if(!Objects.equals(familySort, original.getFamilySort()))
				apiRequest.addVars("familySort");
			if(!Objects.equals(schoolSort, original.getSchoolSort()))
				apiRequest.addVars("schoolSort");
			if(!Objects.equals(userKeys, original.getUserKeys()))
				apiRequest.addVars("userKeys");
			if(!Objects.equals(schoolKeys, original.getSchoolKeys()))
				apiRequest.addVars("schoolKeys");
			if(!Objects.equals(yearKeys, original.getYearKeys()))
				apiRequest.addVars("yearKeys");
			if(!Objects.equals(seasonKeys, original.getSeasonKeys()))
				apiRequest.addVars("seasonKeys");
			if(!Objects.equals(sessionKeys, original.getSessionKeys()))
				apiRequest.addVars("sessionKeys");
			if(!Objects.equals(ageKeys, original.getAgeKeys()))
				apiRequest.addVars("ageKeys");
			if(!Objects.equals(personFirstName, original.getPersonFirstName()))
				apiRequest.addVars("personFirstName");
			if(!Objects.equals(personFirstNamePreferred, original.getPersonFirstNamePreferred()))
				apiRequest.addVars("personFirstNamePreferred");
			if(!Objects.equals(familyName, original.getFamilyName()))
				apiRequest.addVars("familyName");
			if(!Objects.equals(personCompleteName, original.getPersonCompleteName()))
				apiRequest.addVars("personCompleteName");
			if(!Objects.equals(personCompleteNamePreferred, original.getPersonCompleteNamePreferred()))
				apiRequest.addVars("personCompleteNamePreferred");
			if(!Objects.equals(personFormalName, original.getPersonFormalName()))
				apiRequest.addVars("personFormalName");
			if(!Objects.equals(personOccupation, original.getPersonOccupation()))
				apiRequest.addVars("personOccupation");
			if(!Objects.equals(personPhoneNumber, original.getPersonPhoneNumber()))
				apiRequest.addVars("personPhoneNumber");
			if(!Objects.equals(personEmail, original.getPersonEmail()))
				apiRequest.addVars("personEmail");
			if(!Objects.equals(personRelation, original.getPersonRelation()))
				apiRequest.addVars("personRelation");
			if(!Objects.equals(personSms, original.getPersonSms()))
				apiRequest.addVars("personSms");
			if(!Objects.equals(personReceiveEmail, original.getPersonReceiveEmail()))
				apiRequest.addVars("personReceiveEmail");
			if(!Objects.equals(personEmergencyContact, original.getPersonEmergencyContact()))
				apiRequest.addVars("personEmergencyContact");
			if(!Objects.equals(personPickup, original.getPersonPickup()))
				apiRequest.addVars("personPickup");
			if(!Objects.equals(photo, original.getPhoto()))
				apiRequest.addVars("photo");
			if(!Objects.equals(guardianCompleteName, original.getGuardianCompleteName()))
				apiRequest.addVars("guardianCompleteName");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), guardianKey, enrollmentKeys, familySort, schoolSort, userKeys, schoolKeys, yearKeys, seasonKeys, sessionKeys, ageKeys, personFirstName, personFirstNamePreferred, familyName, personCompleteName, personCompleteNamePreferred, personFormalName, personOccupation, personPhoneNumber, personEmail, personRelation, personSms, personReceiveEmail, personEmergencyContact, personPickup, photo, guardianCompleteName);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SchoolGuardian))
			return false;
		SchoolGuardian that = (SchoolGuardian)o;
		return super.equals(o)
				&& Objects.equals( guardianKey, that.guardianKey )
				&& Objects.equals( enrollmentKeys, that.enrollmentKeys )
				&& Objects.equals( familySort, that.familySort )
				&& Objects.equals( schoolSort, that.schoolSort )
				&& Objects.equals( userKeys, that.userKeys )
				&& Objects.equals( schoolKeys, that.schoolKeys )
				&& Objects.equals( yearKeys, that.yearKeys )
				&& Objects.equals( seasonKeys, that.seasonKeys )
				&& Objects.equals( sessionKeys, that.sessionKeys )
				&& Objects.equals( ageKeys, that.ageKeys )
				&& Objects.equals( personFirstName, that.personFirstName )
				&& Objects.equals( personFirstNamePreferred, that.personFirstNamePreferred )
				&& Objects.equals( familyName, that.familyName )
				&& Objects.equals( personCompleteName, that.personCompleteName )
				&& Objects.equals( personCompleteNamePreferred, that.personCompleteNamePreferred )
				&& Objects.equals( personFormalName, that.personFormalName )
				&& Objects.equals( personOccupation, that.personOccupation )
				&& Objects.equals( personPhoneNumber, that.personPhoneNumber )
				&& Objects.equals( personEmail, that.personEmail )
				&& Objects.equals( personRelation, that.personRelation )
				&& Objects.equals( personSms, that.personSms )
				&& Objects.equals( personReceiveEmail, that.personReceiveEmail )
				&& Objects.equals( personEmergencyContact, that.personEmergencyContact )
				&& Objects.equals( personPickup, that.personPickup )
				&& Objects.equals( photo, that.photo )
				&& Objects.equals( guardianCompleteName, that.guardianCompleteName );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolGuardian { ");
		sb.append( "guardianKey: " ).append(guardianKey);
		sb.append( ", enrollmentKeys: " ).append(enrollmentKeys);
		sb.append( ", familySort: " ).append(familySort);
		sb.append( ", schoolSort: " ).append(schoolSort);
		sb.append( ", userKeys: " ).append(userKeys);
		sb.append( ", schoolKeys: " ).append(schoolKeys);
		sb.append( ", yearKeys: " ).append(yearKeys);
		sb.append( ", seasonKeys: " ).append(seasonKeys);
		sb.append( ", sessionKeys: " ).append(sessionKeys);
		sb.append( ", ageKeys: " ).append(ageKeys);
		sb.append( ", personFirstName: \"" ).append(personFirstName).append( "\"" );
		sb.append( ", personFirstNamePreferred: \"" ).append(personFirstNamePreferred).append( "\"" );
		sb.append( ", familyName: \"" ).append(familyName).append( "\"" );
		sb.append( ", personCompleteName: \"" ).append(personCompleteName).append( "\"" );
		sb.append( ", personCompleteNamePreferred: \"" ).append(personCompleteNamePreferred).append( "\"" );
		sb.append( ", personFormalName: \"" ).append(personFormalName).append( "\"" );
		sb.append( ", personOccupation: \"" ).append(personOccupation).append( "\"" );
		sb.append( ", personPhoneNumber: \"" ).append(personPhoneNumber).append( "\"" );
		sb.append( ", personEmail: \"" ).append(personEmail).append( "\"" );
		sb.append( ", personRelation: \"" ).append(personRelation).append( "\"" );
		sb.append( ", personSms: " ).append(personSms);
		sb.append( ", personReceiveEmail: " ).append(personReceiveEmail);
		sb.append( ", personEmergencyContact: " ).append(personEmergencyContact);
		sb.append( ", personPickup: " ).append(personPickup);
		sb.append( ", photo: \"" ).append(photo).append( "\"" );
		sb.append( ", guardianCompleteName: \"" ).append(guardianCompleteName).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
