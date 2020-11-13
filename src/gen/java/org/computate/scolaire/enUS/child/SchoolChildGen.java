package org.computate.scolaire.enUS.child;

import java.util.Arrays;
import java.util.Date;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.lang.Long;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Locale;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import java.util.List;
import java.time.LocalDate;
import org.apache.solr.client.solrj.SolrQuery;
import java.util.Optional;
import org.apache.solr.client.solrj.util.ClientUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.HashMap;
import org.computate.scolaire.enUS.writer.AllWriter;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.apache.commons.collections.CollectionUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.String;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.solr.client.solrj.SolrClient;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.math.NumberUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**	
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class SchoolChildGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolChild.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String SchoolChild_AName = "a child";
	public static final String SchoolChild_This = "this ";
	public static final String SchoolChild_ThisName = "this child";
	public static final String SchoolChild_A = "a ";
	public static final String SchoolChild_TheName = "the child";
	public static final String SchoolChild_NameSingular = "child";
	public static final String SchoolChild_NamePlural = "children";
	public static final String SchoolChild_NameActual = "current child";
	public static final String SchoolChild_AllName = "all the children";
	public static final String SchoolChild_SearchAllNameBy = "search children by ";
	public static final String SchoolChild_Title = "children";
	public static final String SchoolChild_ThePluralName = "the children";
	public static final String SchoolChild_NoNameFound = "no child found";
	public static final String SchoolChild_NameVar = "child";
	public static final String SchoolChild_OfName = "of child";
	public static final String SchoolChild_ANameAdjective = "a child";
	public static final String SchoolChild_NameAdjectiveSingular = "child";
	public static final String SchoolChild_NameAdjectivePlural = "children";
	public static final String SchoolChild_Color = "orange";
	public static final String SchoolChild_IconGroup = "regular";
	public static final String SchoolChild_IconName = "child";

	//////////////
	// childKey //
	//////////////

	/**	 The entity childKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long childKey;
	@JsonIgnore
	public Wrap<Long> childKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("childKey").o(childKey);

	/**	<br/> The entity childKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childKey">Find the entity childKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childKey(Wrap<Long> c);

	public Long getChildKey() {
		return childKey;
	}

	public void setChildKey(Long childKey) {
		this.childKey = childKey;
		this.childKeyWrap.alreadyInitialized = true;
	}
	public void setChildKey(String o) {
		this.childKey = SchoolChild.staticSetChildKey(siteRequest_, o);
		this.childKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetChildKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SchoolChild childKeyInit() {
		if(!childKeyWrap.alreadyInitialized) {
			_childKey(childKeyWrap);
			if(childKey == null)
				setChildKey(childKeyWrap.o);
		}
		childKeyWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static Long staticSolrChildKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrChildKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrChildKey(siteRequest_, SchoolChild.staticSolrChildKey(siteRequest_, SchoolChild.staticSetChildKey(siteRequest_, o)));
	}

	public Long solrChildKey() {
		return SchoolChild.staticSolrChildKey(siteRequest_, childKey);
	}

	public String strChildKey() {
		return childKey == null ? "" : childKey.toString();
	}

	public String jsonChildKey() {
		return childKey == null ? "" : childKey.toString();
	}

	public String nomAffichageChildKey() {
		return "key";
	}

	public String htmTooltipChildKey() {
		return null;
	}

	public String htmChildKey() {
		return childKey == null ? "" : StringEscapeUtils.escapeHtml4(strChildKey());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Find the entity enrollmentKeys in Solr</a>
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
		Long l = SchoolChild.staticSetEnrollmentKeys(siteRequest_, o);
		if(l != null)
			addEnrollmentKeys(l);
		this.enrollmentKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetEnrollmentKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolChild addEnrollmentKeys(Long...objets) {
		for(Long o : objets) {
			addEnrollmentKeys(o);
		}
		return (SchoolChild)this;
	}
	public SchoolChild addEnrollmentKeys(Long o) {
		if(o != null && !enrollmentKeys.contains(o))
			this.enrollmentKeys.add(o);
		return (SchoolChild)this;
	}
	public void setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
	}
	public SchoolChild addEnrollmentKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEnrollmentKeys(p);
		}
		return (SchoolChild)this;
	}
	protected SchoolChild enrollmentKeysInit() {
		if(!enrollmentKeysWrap.alreadyInitialized) {
			_enrollmentKeys(enrollmentKeys);
		}
		enrollmentKeysWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static Long staticSolrEnrollmentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrEnrollmentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrEnrollmentKeys(siteRequest_, SchoolChild.staticSolrEnrollmentKeys(siteRequest_, SchoolChild.staticSetEnrollmentKeys(siteRequest_, o)));
	}

	public List<Long> solrEnrollmentKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : enrollmentKeys) {
			l.add(SchoolChild.staticSolrEnrollmentKeys(siteRequest_, o));
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
		SchoolChild s = (SchoolChild)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "enrollments")
					.a("class", "valueObjectSuggest suggestEnrollmentKeys w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setEnrollmentKeys")
					.a("id", classApiMethodMethod, "_enrollmentKeys")
					.a("autocomplete", "off");
					if("Page".equals(classApiMethodMethod)) {
						a("oninput", "suggestSchoolChildEnrollmentKeys($(this).val() ? searchSchoolEnrollmentFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'childKey:" + pk + "'}", "], $('#listSchoolChildEnrollmentKeys_", classApiMethodMethod, "'), ", pk, "); ");
					}

				fg();

		} else {
			e("span").a("class", "varSchoolChild", pk, "EnrollmentKeys ").f().sx(htmEnrollmentKeys()).g("span");
		}
	}

	public void htmEnrollmentKeys(String classApiMethodMethod) {
		SchoolChild s = (SchoolChild)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolChildEnrollmentKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/enrollment?fq=childKey:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue-gray w3-hover-blue-gray ").f();
								e("i").a("class", "fas fa-edit ").f().g("i");
								sx("enrollments");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate enrollments to this child");
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
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolChildEnrollmentKeys_", classApiMethodMethod).f();
								} g("ul");
								{
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
											.a("id", classApiMethodMethod, "_enrollmentKeys_add")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolEnrollmentVals({ childKey: \"", pk, "\" }, function() {}, function() { addError($('#", classApiMethodMethod, "enrollmentKeys')); });")
											.f().sx("add an enrollment")
										.g("button");
									} g("div");
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familySort">Find the entity familySort in Solr</a>
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
		this.familySort = SchoolChild.staticSetFamilySort(siteRequest_, o);
		this.familySortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetFamilySort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolChild familySortInit() {
		if(!familySortWrap.alreadyInitialized) {
			_familySort(familySortWrap);
			if(familySort == null)
				setFamilySort(familySortWrap.o);
		}
		familySortWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static Integer staticSolrFamilySort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrFamilySort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilySort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrFamilySort(siteRequest_, SchoolChild.staticSolrFamilySort(siteRequest_, SchoolChild.staticSetFamilySort(siteRequest_, o)));
	}

	public Integer solrFamilySort() {
		return SchoolChild.staticSolrFamilySort(siteRequest_, familySort);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Find the entity schoolSort in Solr</a>
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
		this.schoolSort = SchoolChild.staticSetSchoolSort(siteRequest_, o);
		this.schoolSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSchoolSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolChild schoolSortInit() {
		if(!schoolSortWrap.alreadyInitialized) {
			_schoolSort(schoolSortWrap);
			if(schoolSort == null)
				setSchoolSort(schoolSortWrap.o);
		}
		schoolSortWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static Integer staticSolrSchoolSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSchoolSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrSchoolSort(siteRequest_, SchoolChild.staticSolrSchoolSort(siteRequest_, SchoolChild.staticSetSchoolSort(siteRequest_, o)));
	}

	public Integer solrSchoolSort() {
		return SchoolChild.staticSolrSchoolSort(siteRequest_, schoolSort);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSearch">Find the entity enrollmentSearch in Solr</a>
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
	protected SchoolChild enrollmentSearchInit() {
		if(!enrollmentSearchWrap.alreadyInitialized) {
			_enrollmentSearch(enrollmentSearch);
		}
		enrollmentSearch.initDeepForClass(siteRequest_);
		enrollmentSearchWrap.alreadyInitialized(true);
		return (SchoolChild)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollments">Find the entity enrollments in Solr</a>
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
	public static List<SchoolEnrollment> staticSetEnrollments(SiteRequestEnUS siteRequest_, String o) {
		return null;
	}
	public SchoolChild addEnrollments(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollments(o);
		}
		return (SchoolChild)this;
	}
	public SchoolChild addEnrollments(SchoolEnrollment o) {
		if(o != null && !enrollments.contains(o))
			this.enrollments.add(o);
		return (SchoolChild)this;
	}
	protected SchoolChild enrollmentsInit() {
		if(!enrollmentsWrap.alreadyInitialized) {
			_enrollments(enrollments);
		}
		enrollmentsWrap.alreadyInitialized(true);
		return (SchoolChild)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userKeys">Find the entity userKeys in Solr</a>
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
		Long l = SchoolChild.staticSetUserKeys(siteRequest_, o);
		if(l != null)
			addUserKeys(l);
		this.userKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetUserKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolChild addUserKeys(Long...objets) {
		for(Long o : objets) {
			addUserKeys(o);
		}
		return (SchoolChild)this;
	}
	public SchoolChild addUserKeys(Long o) {
		if(o != null && !userKeys.contains(o))
			this.userKeys.add(o);
		return (SchoolChild)this;
	}
	public void setUserKeys(JsonArray objets) {
		userKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUserKeys(o);
		}
	}
	public SchoolChild addUserKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addUserKeys(p);
		}
		return (SchoolChild)this;
	}
	protected SchoolChild userKeysInit() {
		if(!userKeysWrap.alreadyInitialized) {
			_userKeys(userKeys);
		}
		userKeysWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static Long staticSolrUserKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrUserKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUserKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrUserKeys(siteRequest_, SchoolChild.staticSolrUserKeys(siteRequest_, SchoolChild.staticSetUserKeys(siteRequest_, o)));
	}

	public List<Long> solrUserKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : userKeys) {
			l.add(SchoolChild.staticSolrUserKeys(siteRequest_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKeys">Find the entity schoolKeys in Solr</a>
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
		Long l = SchoolChild.staticSetSchoolKeys(siteRequest_, o);
		if(l != null)
			addSchoolKeys(l);
		this.schoolKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetSchoolKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolChild addSchoolKeys(Long...objets) {
		for(Long o : objets) {
			addSchoolKeys(o);
		}
		return (SchoolChild)this;
	}
	public SchoolChild addSchoolKeys(Long o) {
		if(o != null && !schoolKeys.contains(o))
			this.schoolKeys.add(o);
		return (SchoolChild)this;
	}
	public void setSchoolKeys(JsonArray objets) {
		schoolKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSchoolKeys(o);
		}
	}
	public SchoolChild addSchoolKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSchoolKeys(p);
		}
		return (SchoolChild)this;
	}
	protected SchoolChild schoolKeysInit() {
		if(!schoolKeysWrap.alreadyInitialized) {
			_schoolKeys(schoolKeys);
		}
		schoolKeysWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static Long staticSolrSchoolKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSchoolKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrSchoolKeys(siteRequest_, SchoolChild.staticSolrSchoolKeys(siteRequest_, SchoolChild.staticSetSchoolKeys(siteRequest_, o)));
	}

	public List<Long> solrSchoolKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : schoolKeys) {
			l.add(SchoolChild.staticSolrSchoolKeys(siteRequest_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKeys">Find the entity yearKeys in Solr</a>
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
		Long l = SchoolChild.staticSetYearKeys(siteRequest_, o);
		if(l != null)
			addYearKeys(l);
		this.yearKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetYearKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolChild addYearKeys(Long...objets) {
		for(Long o : objets) {
			addYearKeys(o);
		}
		return (SchoolChild)this;
	}
	public SchoolChild addYearKeys(Long o) {
		if(o != null && !yearKeys.contains(o))
			this.yearKeys.add(o);
		return (SchoolChild)this;
	}
	public void setYearKeys(JsonArray objets) {
		yearKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addYearKeys(o);
		}
	}
	public SchoolChild addYearKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addYearKeys(p);
		}
		return (SchoolChild)this;
	}
	protected SchoolChild yearKeysInit() {
		if(!yearKeysWrap.alreadyInitialized) {
			_yearKeys(yearKeys);
		}
		yearKeysWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static Long staticSolrYearKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrYearKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrYearKeys(siteRequest_, SchoolChild.staticSolrYearKeys(siteRequest_, SchoolChild.staticSetYearKeys(siteRequest_, o)));
	}

	public List<Long> solrYearKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : yearKeys) {
			l.add(SchoolChild.staticSolrYearKeys(siteRequest_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKeys">Find the entity seasonKeys in Solr</a>
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
		Long l = SchoolChild.staticSetSeasonKeys(siteRequest_, o);
		if(l != null)
			addSeasonKeys(l);
		this.seasonKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetSeasonKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolChild addSeasonKeys(Long...objets) {
		for(Long o : objets) {
			addSeasonKeys(o);
		}
		return (SchoolChild)this;
	}
	public SchoolChild addSeasonKeys(Long o) {
		if(o != null && !seasonKeys.contains(o))
			this.seasonKeys.add(o);
		return (SchoolChild)this;
	}
	public void setSeasonKeys(JsonArray objets) {
		seasonKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSeasonKeys(o);
		}
	}
	public SchoolChild addSeasonKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSeasonKeys(p);
		}
		return (SchoolChild)this;
	}
	protected SchoolChild seasonKeysInit() {
		if(!seasonKeysWrap.alreadyInitialized) {
			_seasonKeys(seasonKeys);
		}
		seasonKeysWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static Long staticSolrSeasonKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSeasonKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSeasonKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrSeasonKeys(siteRequest_, SchoolChild.staticSolrSeasonKeys(siteRequest_, SchoolChild.staticSetSeasonKeys(siteRequest_, o)));
	}

	public List<Long> solrSeasonKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : seasonKeys) {
			l.add(SchoolChild.staticSolrSeasonKeys(siteRequest_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKeys">Find the entity sessionKeys in Solr</a>
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
		Long l = SchoolChild.staticSetSessionKeys(siteRequest_, o);
		if(l != null)
			addSessionKeys(l);
		this.sessionKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetSessionKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolChild addSessionKeys(Long...objets) {
		for(Long o : objets) {
			addSessionKeys(o);
		}
		return (SchoolChild)this;
	}
	public SchoolChild addSessionKeys(Long o) {
		if(o != null && !sessionKeys.contains(o))
			this.sessionKeys.add(o);
		return (SchoolChild)this;
	}
	public void setSessionKeys(JsonArray objets) {
		sessionKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionKeys(o);
		}
	}
	public SchoolChild addSessionKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSessionKeys(p);
		}
		return (SchoolChild)this;
	}
	protected SchoolChild sessionKeysInit() {
		if(!sessionKeysWrap.alreadyInitialized) {
			_sessionKeys(sessionKeys);
		}
		sessionKeysWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static Long staticSolrSessionKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSessionKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrSessionKeys(siteRequest_, SchoolChild.staticSolrSessionKeys(siteRequest_, SchoolChild.staticSetSessionKeys(siteRequest_, o)));
	}

	public List<Long> solrSessionKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : sessionKeys) {
			l.add(SchoolChild.staticSolrSessionKeys(siteRequest_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKeys">Find the entity ageKeys in Solr</a>
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
		Long l = SchoolChild.staticSetAgeKeys(siteRequest_, o);
		if(l != null)
			addAgeKeys(l);
		this.ageKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetAgeKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolChild addAgeKeys(Long...objets) {
		for(Long o : objets) {
			addAgeKeys(o);
		}
		return (SchoolChild)this;
	}
	public SchoolChild addAgeKeys(Long o) {
		if(o != null && !ageKeys.contains(o))
			this.ageKeys.add(o);
		return (SchoolChild)this;
	}
	public void setAgeKeys(JsonArray objets) {
		ageKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAgeKeys(o);
		}
	}
	public SchoolChild addAgeKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAgeKeys(p);
		}
		return (SchoolChild)this;
	}
	protected SchoolChild ageKeysInit() {
		if(!ageKeysWrap.alreadyInitialized) {
			_ageKeys(ageKeys);
		}
		ageKeysWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static Long staticSolrAgeKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrAgeKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrAgeKeys(siteRequest_, SchoolChild.staticSolrAgeKeys(siteRequest_, SchoolChild.staticSetAgeKeys(siteRequest_, o)));
	}

	public List<Long> solrAgeKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : ageKeys) {
			l.add(SchoolChild.staticSolrAgeKeys(siteRequest_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personFirstName">Find the entity personFirstName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personFirstName(Wrap<String> c);

	public String getPersonFirstName() {
		return personFirstName;
	}
	public void setPersonFirstName(String o) {
		this.personFirstName = SchoolChild.staticSetPersonFirstName(siteRequest_, o);
		this.personFirstNameWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolChild personFirstNameInit() {
		if(!personFirstNameWrap.alreadyInitialized) {
			_personFirstName(personFirstNameWrap);
			if(personFirstName == null)
				setPersonFirstName(personFirstNameWrap.o);
		}
		personFirstNameWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static String staticSolrPersonFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonFirstName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrPersonFirstName(siteRequest_, SchoolChild.staticSolrPersonFirstName(siteRequest_, SchoolChild.staticSetPersonFirstName(siteRequest_, o)));
	}

	public String solrPersonFirstName() {
		return SchoolChild.staticSolrPersonFirstName(siteRequest_, personFirstName);
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
		SchoolChild s = (SchoolChild)this;
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
					a("class", "setPersonFirstName classSchoolChild inputSchoolChild", pk, "PersonFirstName w3-input w3-border ");
					a("name", "setPersonFirstName");
				} else {
					a("class", "valuePersonFirstName w3-input w3-border classSchoolChild inputSchoolChild", pk, "PersonFirstName w3-input w3-border ");
					a("name", "personFirstName");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonFirstName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personFirstName')); }, function() { addError($('#", classApiMethodMethod, "_personFirstName')); }); ");
				}
				a("value", strPersonFirstName())
			.fg();

		} else {
			e("span").a("class", "varSchoolChild", pk, "PersonFirstName ").f().sx(htmPersonFirstName()).g("span");
		}
	}

	public void htmPersonFirstName(String classApiMethodMethod) {
		SchoolChild s = (SchoolChild)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolChildPersonFirstName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personFirstName')); $('#", classApiMethodMethod, "_personFirstName').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolChildForm :input[name=pk]').val() }], 'setPersonFirstName', null, function() { addGlow($('#", classApiMethodMethod, "_personFirstName')); }, function() { addError($('#", classApiMethodMethod, "_personFirstName')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personFirstNamePreferred">Find the entity personFirstNamePreferred in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personFirstNamePreferred(Wrap<String> c);

	public String getPersonFirstNamePreferred() {
		return personFirstNamePreferred;
	}
	public void setPersonFirstNamePreferred(String o) {
		this.personFirstNamePreferred = SchoolChild.staticSetPersonFirstNamePreferred(siteRequest_, o);
		this.personFirstNamePreferredWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolChild personFirstNamePreferredInit() {
		if(!personFirstNamePreferredWrap.alreadyInitialized) {
			_personFirstNamePreferred(personFirstNamePreferredWrap);
			if(personFirstNamePreferred == null)
				setPersonFirstNamePreferred(personFirstNamePreferredWrap.o);
		}
		personFirstNamePreferredWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static String staticSolrPersonFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrPersonFirstNamePreferred(siteRequest_, SchoolChild.staticSolrPersonFirstNamePreferred(siteRequest_, SchoolChild.staticSetPersonFirstNamePreferred(siteRequest_, o)));
	}

	public String solrPersonFirstNamePreferred() {
		return SchoolChild.staticSolrPersonFirstNamePreferred(siteRequest_, personFirstNamePreferred);
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
		SchoolChild s = (SchoolChild)this;
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
					a("class", "setPersonFirstNamePreferred classSchoolChild inputSchoolChild", pk, "PersonFirstNamePreferred w3-input w3-border ");
					a("name", "setPersonFirstNamePreferred");
				} else {
					a("class", "valuePersonFirstNamePreferred w3-input w3-border classSchoolChild inputSchoolChild", pk, "PersonFirstNamePreferred w3-input w3-border ");
					a("name", "personFirstNamePreferred");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonFirstNamePreferred', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personFirstNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_personFirstNamePreferred')); }); ");
				}
				a("value", strPersonFirstNamePreferred())
			.fg();

		} else {
			e("span").a("class", "varSchoolChild", pk, "PersonFirstNamePreferred ").f().sx(htmPersonFirstNamePreferred()).g("span");
		}
	}

	public void htmPersonFirstNamePreferred(String classApiMethodMethod) {
		SchoolChild s = (SchoolChild)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolChildPersonFirstNamePreferred").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personFirstNamePreferred')); $('#", classApiMethodMethod, "_personFirstNamePreferred').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolChildForm :input[name=pk]').val() }], 'setPersonFirstNamePreferred', null, function() { addGlow($('#", classApiMethodMethod, "_personFirstNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_personFirstNamePreferred')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyName">Find the entity familyName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _familyName(Wrap<String> c);

	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String o) {
		this.familyName = SchoolChild.staticSetFamilyName(siteRequest_, o);
		this.familyNameWrap.alreadyInitialized = true;
	}
	public static String staticSetFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolChild familyNameInit() {
		if(!familyNameWrap.alreadyInitialized) {
			_familyName(familyNameWrap);
			if(familyName == null)
				setFamilyName(familyNameWrap.o);
		}
		familyNameWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static String staticSolrFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrFamilyName(siteRequest_, SchoolChild.staticSolrFamilyName(siteRequest_, SchoolChild.staticSetFamilyName(siteRequest_, o)));
	}

	public String solrFamilyName() {
		return SchoolChild.staticSolrFamilyName(siteRequest_, familyName);
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
		SchoolChild s = (SchoolChild)this;
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
					a("class", "setFamilyName classSchoolChild inputSchoolChild", pk, "FamilyName w3-input w3-border ");
					a("name", "setFamilyName");
				} else {
					a("class", "valueFamilyName w3-input w3-border classSchoolChild inputSchoolChild", pk, "FamilyName w3-input w3-border ");
					a("name", "familyName");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilyName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_familyName')); }, function() { addError($('#", classApiMethodMethod, "_familyName')); }); ");
				}
				a("value", strFamilyName())
			.fg();

		} else {
			e("span").a("class", "varSchoolChild", pk, "FamilyName ").f().sx(htmFamilyName()).g("span");
		}
	}

	public void htmFamilyName(String classApiMethodMethod) {
		SchoolChild s = (SchoolChild)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolChildFamilyName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_familyName')); $('#", classApiMethodMethod, "_familyName').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolChildForm :input[name=pk]').val() }], 'setFamilyName', null, function() { addGlow($('#", classApiMethodMethod, "_familyName')); }, function() { addError($('#", classApiMethodMethod, "_familyName')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personCompleteName">Find the entity personCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personCompleteName(Wrap<String> c);

	public String getPersonCompleteName() {
		return personCompleteName;
	}
	public void setPersonCompleteName(String o) {
		this.personCompleteName = SchoolChild.staticSetPersonCompleteName(siteRequest_, o);
		this.personCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolChild personCompleteNameInit() {
		if(!personCompleteNameWrap.alreadyInitialized) {
			_personCompleteName(personCompleteNameWrap);
			if(personCompleteName == null)
				setPersonCompleteName(personCompleteNameWrap.o);
		}
		personCompleteNameWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static String staticSolrPersonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrPersonCompleteName(siteRequest_, SchoolChild.staticSolrPersonCompleteName(siteRequest_, SchoolChild.staticSetPersonCompleteName(siteRequest_, o)));
	}

	public String solrPersonCompleteName() {
		return SchoolChild.staticSolrPersonCompleteName(siteRequest_, personCompleteName);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personCompleteNamePreferred">Find the entity personCompleteNamePreferred in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personCompleteNamePreferred(Wrap<String> c);

	public String getPersonCompleteNamePreferred() {
		return personCompleteNamePreferred;
	}
	public void setPersonCompleteNamePreferred(String o) {
		this.personCompleteNamePreferred = SchoolChild.staticSetPersonCompleteNamePreferred(siteRequest_, o);
		this.personCompleteNamePreferredWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolChild personCompleteNamePreferredInit() {
		if(!personCompleteNamePreferredWrap.alreadyInitialized) {
			_personCompleteNamePreferred(personCompleteNamePreferredWrap);
			if(personCompleteNamePreferred == null)
				setPersonCompleteNamePreferred(personCompleteNamePreferredWrap.o);
		}
		personCompleteNamePreferredWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static String staticSolrPersonCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrPersonCompleteNamePreferred(siteRequest_, SchoolChild.staticSolrPersonCompleteNamePreferred(siteRequest_, SchoolChild.staticSetPersonCompleteNamePreferred(siteRequest_, o)));
	}

	public String solrPersonCompleteNamePreferred() {
		return SchoolChild.staticSolrPersonCompleteNamePreferred(siteRequest_, personCompleteNamePreferred);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personFormalName">Find the entity personFormalName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personFormalName(Wrap<String> c);

	public String getPersonFormalName() {
		return personFormalName;
	}
	public void setPersonFormalName(String o) {
		this.personFormalName = SchoolChild.staticSetPersonFormalName(siteRequest_, o);
		this.personFormalNameWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonFormalName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolChild personFormalNameInit() {
		if(!personFormalNameWrap.alreadyInitialized) {
			_personFormalName(personFormalNameWrap);
			if(personFormalName == null)
				setPersonFormalName(personFormalNameWrap.o);
		}
		personFormalNameWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static String staticSolrPersonFormalName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonFormalName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonFormalName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrPersonFormalName(siteRequest_, SchoolChild.staticSolrPersonFormalName(siteRequest_, SchoolChild.staticSetPersonFormalName(siteRequest_, o)));
	}

	public String solrPersonFormalName() {
		return SchoolChild.staticSolrPersonFormalName(siteRequest_, personFormalName);
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

	/////////////////////
	// personBirthDate //
	/////////////////////

	/**	 The entity personBirthDate
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate personBirthDate;
	@JsonIgnore
	public Wrap<LocalDate> personBirthDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("personBirthDate").o(personBirthDate);

	/**	<br/> The entity personBirthDate
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personBirthDate">Find the entity personBirthDate in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personBirthDate(Wrap<LocalDate> c);

	public LocalDate getPersonBirthDate() {
		return personBirthDate;
	}

	public void setPersonBirthDate(LocalDate personBirthDate) {
		this.personBirthDate = personBirthDate;
		this.personBirthDateWrap.alreadyInitialized = true;
	}
	public void setPersonBirthDate(Instant o) {
		this.personBirthDate = o == null ? null : LocalDate.from(o);
		this.personBirthDateWrap.alreadyInitialized = true;
	}
	/** Example: 2011-12-03+01:00 **/
	public void setPersonBirthDate(String o) {
		this.personBirthDate = SchoolChild.staticSetPersonBirthDate(siteRequest_, o);
		this.personBirthDateWrap.alreadyInitialized = true;
	}
	public static LocalDate staticSetPersonBirthDate(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
	}
	public void setPersonBirthDate(Date o) {
		this.personBirthDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.personBirthDateWrap.alreadyInitialized = true;
	}
	protected SchoolChild personBirthDateInit() {
		if(!personBirthDateWrap.alreadyInitialized) {
			_personBirthDate(personBirthDateWrap);
			if(personBirthDate == null)
				setPersonBirthDate(personBirthDateWrap.o);
		}
		personBirthDateWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static Date staticSolrPersonBirthDate(SiteRequestEnUS siteRequest_, LocalDate o) {
		return o == null ? null : Date.from(o.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public static String staticSolrStrPersonBirthDate(SiteRequestEnUS siteRequest_, Date o) {
		return "\"" + DateTimeFormatter.ISO_DATE_TIME.format(o.toInstant().atOffset(ZoneOffset.UTC)) + "\"";
	}

	public static String staticSolrFqPersonBirthDate(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrPersonBirthDate(siteRequest_, SchoolChild.staticSolrPersonBirthDate(siteRequest_, SchoolChild.staticSetPersonBirthDate(siteRequest_, o)));
	}

	public Date solrPersonBirthDate() {
		return SchoolChild.staticSolrPersonBirthDate(siteRequest_, personBirthDate);
	}

	public String strPersonBirthDate() {
		return personBirthDate == null ? "" : personBirthDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonPersonBirthDate() {
		return personBirthDate == null ? "" : personBirthDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichagePersonBirthDate() {
		return "birth date";
	}

	public String htmTooltipPersonBirthDate() {
		return null;
	}

	public String htmPersonBirthDate() {
		return personBirthDate == null ? "" : StringEscapeUtils.escapeHtml4(strPersonBirthDate());
	}

	public void inputPersonBirthDate(String classApiMethodMethod) {
		SchoolChild s = (SchoolChild)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
					.a("type", "text")
					.a("class", "w3-input w3-border datepicker setPersonBirthDate classSchoolChild inputSchoolChild", pk, "PersonBirthDate w3-input w3-border ")
					.a("placeholder", "MM/DD/YYYY")
					.a("data-timeformat", "MM/dd/yyyy")
					.a("id", classApiMethodMethod, "_personBirthDate")
					.a("value", personBirthDate == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(personBirthDate));
			if("Page".equals(classApiMethodMethod)) {
				a("onclick", "removeGlow($(this)); ");
				a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonBirthDate', s, function() { addGlow($('#", classApiMethodMethod, "_personBirthDate')); }, function() { addError($('#", classApiMethodMethod, "_personBirthDate')); }); } ");
			}
			fg();
		} else {
			e("span").a("class", "varSchoolChild", pk, "PersonBirthDate ").f().sx(htmPersonBirthDate()).g("span");
		}
	}

	public void htmPersonBirthDate(String classApiMethodMethod) {
		SchoolChild s = (SchoolChild)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolChildPersonBirthDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classApiMethodMethod, "_personBirthDate").a("class", "").f().sx("birth date").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputPersonBirthDate(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personBirthDate')); $('#", classApiMethodMethod, "_personBirthDate').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolChildForm :input[name=pk]').val() }], 'setPersonBirthDate', null, function() { addGlow($('#", classApiMethodMethod, "_personBirthDate')); }, function() { addError($('#", classApiMethodMethod, "_personBirthDate')); }); ")
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

	/////////////////////////
	// personBirthDateYear //
	/////////////////////////

	/**	 The entity personBirthDateYear
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer personBirthDateYear;
	@JsonIgnore
	public Wrap<Integer> personBirthDateYearWrap = new Wrap<Integer>().p(this).c(Integer.class).var("personBirthDateYear").o(personBirthDateYear);

	/**	<br/> The entity personBirthDateYear
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personBirthDateYear">Find the entity personBirthDateYear in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personBirthDateYear(Wrap<Integer> c);

	public Integer getPersonBirthDateYear() {
		return personBirthDateYear;
	}

	public void setPersonBirthDateYear(Integer personBirthDateYear) {
		this.personBirthDateYear = personBirthDateYear;
		this.personBirthDateYearWrap.alreadyInitialized = true;
	}
	public void setPersonBirthDateYear(String o) {
		this.personBirthDateYear = SchoolChild.staticSetPersonBirthDateYear(siteRequest_, o);
		this.personBirthDateYearWrap.alreadyInitialized = true;
	}
	public static Integer staticSetPersonBirthDateYear(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolChild personBirthDateYearInit() {
		if(!personBirthDateYearWrap.alreadyInitialized) {
			_personBirthDateYear(personBirthDateYearWrap);
			if(personBirthDateYear == null)
				setPersonBirthDateYear(personBirthDateYearWrap.o);
		}
		personBirthDateYearWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static Integer staticSolrPersonBirthDateYear(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrPersonBirthDateYear(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonBirthDateYear(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrPersonBirthDateYear(siteRequest_, SchoolChild.staticSolrPersonBirthDateYear(siteRequest_, SchoolChild.staticSetPersonBirthDateYear(siteRequest_, o)));
	}

	public Integer solrPersonBirthDateYear() {
		return SchoolChild.staticSolrPersonBirthDateYear(siteRequest_, personBirthDateYear);
	}

	public String strPersonBirthDateYear() {
		return personBirthDateYear == null ? "" : personBirthDateYear.toString();
	}

	public String jsonPersonBirthDateYear() {
		return personBirthDateYear == null ? "" : personBirthDateYear.toString();
	}

	public String nomAffichagePersonBirthDateYear() {
		return null;
	}

	public String htmTooltipPersonBirthDateYear() {
		return null;
	}

	public String htmPersonBirthDateYear() {
		return personBirthDateYear == null ? "" : StringEscapeUtils.escapeHtml4(strPersonBirthDateYear());
	}

	////////////////////////////////
	// personBirthDateMonthOfYear //
	////////////////////////////////

	/**	 The entity personBirthDateMonthOfYear
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personBirthDateMonthOfYear;
	@JsonIgnore
	public Wrap<String> personBirthDateMonthOfYearWrap = new Wrap<String>().p(this).c(String.class).var("personBirthDateMonthOfYear").o(personBirthDateMonthOfYear);

	/**	<br/> The entity personBirthDateMonthOfYear
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personBirthDateMonthOfYear">Find the entity personBirthDateMonthOfYear in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personBirthDateMonthOfYear(Wrap<String> c);

	public String getPersonBirthDateMonthOfYear() {
		return personBirthDateMonthOfYear;
	}
	public void setPersonBirthDateMonthOfYear(String o) {
		this.personBirthDateMonthOfYear = SchoolChild.staticSetPersonBirthDateMonthOfYear(siteRequest_, o);
		this.personBirthDateMonthOfYearWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonBirthDateMonthOfYear(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolChild personBirthDateMonthOfYearInit() {
		if(!personBirthDateMonthOfYearWrap.alreadyInitialized) {
			_personBirthDateMonthOfYear(personBirthDateMonthOfYearWrap);
			if(personBirthDateMonthOfYear == null)
				setPersonBirthDateMonthOfYear(personBirthDateMonthOfYearWrap.o);
		}
		personBirthDateMonthOfYearWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static String staticSolrPersonBirthDateMonthOfYear(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonBirthDateMonthOfYear(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonBirthDateMonthOfYear(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrPersonBirthDateMonthOfYear(siteRequest_, SchoolChild.staticSolrPersonBirthDateMonthOfYear(siteRequest_, SchoolChild.staticSetPersonBirthDateMonthOfYear(siteRequest_, o)));
	}

	public String solrPersonBirthDateMonthOfYear() {
		return SchoolChild.staticSolrPersonBirthDateMonthOfYear(siteRequest_, personBirthDateMonthOfYear);
	}

	public String strPersonBirthDateMonthOfYear() {
		return personBirthDateMonthOfYear == null ? "" : personBirthDateMonthOfYear;
	}

	public String jsonPersonBirthDateMonthOfYear() {
		return personBirthDateMonthOfYear == null ? "" : personBirthDateMonthOfYear;
	}

	public String nomAffichagePersonBirthDateMonthOfYear() {
		return null;
	}

	public String htmTooltipPersonBirthDateMonthOfYear() {
		return null;
	}

	public String htmPersonBirthDateMonthOfYear() {
		return personBirthDateMonthOfYear == null ? "" : StringEscapeUtils.escapeHtml4(strPersonBirthDateMonthOfYear());
	}

	//////////////////////////////
	// personBirthDateDayOfWeek //
	//////////////////////////////

	/**	 The entity personBirthDateDayOfWeek
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personBirthDateDayOfWeek;
	@JsonIgnore
	public Wrap<String> personBirthDateDayOfWeekWrap = new Wrap<String>().p(this).c(String.class).var("personBirthDateDayOfWeek").o(personBirthDateDayOfWeek);

	/**	<br/> The entity personBirthDateDayOfWeek
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personBirthDateDayOfWeek">Find the entity personBirthDateDayOfWeek in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personBirthDateDayOfWeek(Wrap<String> c);

	public String getPersonBirthDateDayOfWeek() {
		return personBirthDateDayOfWeek;
	}
	public void setPersonBirthDateDayOfWeek(String o) {
		this.personBirthDateDayOfWeek = SchoolChild.staticSetPersonBirthDateDayOfWeek(siteRequest_, o);
		this.personBirthDateDayOfWeekWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonBirthDateDayOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolChild personBirthDateDayOfWeekInit() {
		if(!personBirthDateDayOfWeekWrap.alreadyInitialized) {
			_personBirthDateDayOfWeek(personBirthDateDayOfWeekWrap);
			if(personBirthDateDayOfWeek == null)
				setPersonBirthDateDayOfWeek(personBirthDateDayOfWeekWrap.o);
		}
		personBirthDateDayOfWeekWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static String staticSolrPersonBirthDateDayOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonBirthDateDayOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonBirthDateDayOfWeek(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrPersonBirthDateDayOfWeek(siteRequest_, SchoolChild.staticSolrPersonBirthDateDayOfWeek(siteRequest_, SchoolChild.staticSetPersonBirthDateDayOfWeek(siteRequest_, o)));
	}

	public String solrPersonBirthDateDayOfWeek() {
		return SchoolChild.staticSolrPersonBirthDateDayOfWeek(siteRequest_, personBirthDateDayOfWeek);
	}

	public String strPersonBirthDateDayOfWeek() {
		return personBirthDateDayOfWeek == null ? "" : personBirthDateDayOfWeek;
	}

	public String jsonPersonBirthDateDayOfWeek() {
		return personBirthDateDayOfWeek == null ? "" : personBirthDateDayOfWeek;
	}

	public String nomAffichagePersonBirthDateDayOfWeek() {
		return null;
	}

	public String htmTooltipPersonBirthDateDayOfWeek() {
		return null;
	}

	public String htmPersonBirthDateDayOfWeek() {
		return personBirthDateDayOfWeek == null ? "" : StringEscapeUtils.escapeHtml4(strPersonBirthDateDayOfWeek());
	}

	//////////////////////////
	// personAgeInSeptember //
	//////////////////////////

	/**	 The entity personAgeInSeptember
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personAgeInSeptember;
	@JsonIgnore
	public Wrap<String> personAgeInSeptemberWrap = new Wrap<String>().p(this).c(String.class).var("personAgeInSeptember").o(personAgeInSeptember);

	/**	<br/> The entity personAgeInSeptember
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personAgeInSeptember">Find the entity personAgeInSeptember in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personAgeInSeptember(Wrap<String> c);

	public String getPersonAgeInSeptember() {
		return personAgeInSeptember;
	}
	public void setPersonAgeInSeptember(String o) {
		this.personAgeInSeptember = SchoolChild.staticSetPersonAgeInSeptember(siteRequest_, o);
		this.personAgeInSeptemberWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonAgeInSeptember(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolChild personAgeInSeptemberInit() {
		if(!personAgeInSeptemberWrap.alreadyInitialized) {
			_personAgeInSeptember(personAgeInSeptemberWrap);
			if(personAgeInSeptember == null)
				setPersonAgeInSeptember(personAgeInSeptemberWrap.o);
		}
		personAgeInSeptemberWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static String staticSolrPersonAgeInSeptember(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonAgeInSeptember(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonAgeInSeptember(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrPersonAgeInSeptember(siteRequest_, SchoolChild.staticSolrPersonAgeInSeptember(siteRequest_, SchoolChild.staticSetPersonAgeInSeptember(siteRequest_, o)));
	}

	public String solrPersonAgeInSeptember() {
		return SchoolChild.staticSolrPersonAgeInSeptember(siteRequest_, personAgeInSeptember);
	}

	public String strPersonAgeInSeptember() {
		return personAgeInSeptember == null ? "" : personAgeInSeptember;
	}

	public String jsonPersonAgeInSeptember() {
		return personAgeInSeptember == null ? "" : personAgeInSeptember;
	}

	public String nomAffichagePersonAgeInSeptember() {
		return "age";
	}

	public String htmTooltipPersonAgeInSeptember() {
		return null;
	}

	public String htmPersonAgeInSeptember() {
		return personAgeInSeptember == null ? "" : StringEscapeUtils.escapeHtml4(strPersonAgeInSeptember());
	}

	public void inputPersonAgeInSeptember(String classApiMethodMethod) {
		SchoolChild s = (SchoolChild)this;
	}

	public void htmPersonAgeInSeptember(String classApiMethodMethod) {
		SchoolChild s = (SchoolChild)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			if("Page".equals(classApiMethodMethod)) {
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("class", "").f().sx("age").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").a("class", "varSchoolChild", pk, "PersonAgeInSeptember ").f().sx(strPersonAgeInSeptember()).g("span");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			}
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:photo">Find the entity photo in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _photo(Wrap<String> c);

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String o) {
		this.photo = SchoolChild.staticSetPhoto(siteRequest_, o);
		this.photoWrap.alreadyInitialized = true;
	}
	public static String staticSetPhoto(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolChild photoInit() {
		if(!photoWrap.alreadyInitialized) {
			_photo(photoWrap);
			if(photo == null)
				setPhoto(photoWrap.o);
		}
		photoWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static String staticSolrPhoto(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPhoto(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPhoto(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrPhoto(siteRequest_, SchoolChild.staticSolrPhoto(siteRequest_, SchoolChild.staticSetPhoto(siteRequest_, o)));
	}

	public String solrPhoto() {
		return SchoolChild.staticSolrPhoto(siteRequest_, photo);
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
		SchoolChild s = (SchoolChild)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("div").a("class", "imageBase64Div1SchoolChild_photo").a("id", "imageBase64Div1SchoolChild", pk, "photo").f();
				e("h5").f().sx("Upload image").g("h5"); 
				e("form").a("method", "POST").a("enctype", "multipart/form-data").a("action", "/photo").a("class", "").f();
				e("input").a("type", "hidden").a("name", "pk").a("value", pk).fg(); 
				e("input").a("type", "hidden").a("name", "classSimpleName").a("value", "SchoolChild").fg(); 
				e("input").a("name", "file").a("type", "file").a("onchange", "$.ajax({ type: 'POST', enctype: 'multipart/form-data', url: '/photo', data: new FormData(this.form), processData: false, contentType: false}); ").fg(); 
				g("form");
				e("img").a("id", "imageBase64ImgSchoolChild", pk, "photo");
					a("class", "imgSchoolChild", pk, "Photo w3-image ");
					a("src", StringUtils.isBlank(photo) ? "data:image/png;base64," : photo).a("alt", "");
				fg();
			g("div");
		} else {
			e("span").a("class", "varSchoolChild", pk, "Photo ").f().sx(htmPhoto()).g("span");
		}
	}

	public void htmPhoto(String classApiMethodMethod) {
		SchoolChild s = (SchoolChild)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolChildPhoto").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_photo')); $('#", classApiMethodMethod, "_photo').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolChildForm :input[name=pk]').val() }], 'setPhoto', null, function() { addGlow($('#", classApiMethodMethod, "_photo')); }, function() { addError($('#", classApiMethodMethod, "_photo')); }); ")
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

	///////////////////////
	// childCompleteName //
	///////////////////////

	/**	 The entity childCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String childCompleteName;
	@JsonIgnore
	public Wrap<String> childCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("childCompleteName").o(childCompleteName);

	/**	<br/> The entity childCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.child.SchoolChild&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:childCompleteName">Find the entity childCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _childCompleteName(Wrap<String> c);

	public String getChildCompleteName() {
		return childCompleteName;
	}
	public void setChildCompleteName(String o) {
		this.childCompleteName = SchoolChild.staticSetChildCompleteName(siteRequest_, o);
		this.childCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetChildCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolChild childCompleteNameInit() {
		if(!childCompleteNameWrap.alreadyInitialized) {
			_childCompleteName(childCompleteNameWrap);
			if(childCompleteName == null)
				setChildCompleteName(childCompleteNameWrap.o);
		}
		childCompleteNameWrap.alreadyInitialized(true);
		return (SchoolChild)this;
	}

	public static String staticSolrChildCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrChildCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqChildCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolChild.staticSolrStrChildCompleteName(siteRequest_, SchoolChild.staticSolrChildCompleteName(siteRequest_, SchoolChild.staticSetChildCompleteName(siteRequest_, o)));
	}

	public String solrChildCompleteName() {
		return SchoolChild.staticSolrChildCompleteName(siteRequest_, childCompleteName);
	}

	public String strChildCompleteName() {
		return childCompleteName == null ? "" : childCompleteName;
	}

	public String jsonChildCompleteName() {
		return childCompleteName == null ? "" : childCompleteName;
	}

	public String nomAffichageChildCompleteName() {
		return "name";
	}

	public String htmTooltipChildCompleteName() {
		return null;
	}

	public String htmChildCompleteName() {
		return childCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strChildCompleteName());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSchoolChild = false;

	public SchoolChild initDeepSchoolChild(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSchoolChild) {
			alreadyInitializedSchoolChild = true;
			initDeepSchoolChild();
		}
		return (SchoolChild)this;
	}

	public void initDeepSchoolChild() {
		initSchoolChild();
		super.initDeepCluster(siteRequest_);
	}

	public void initSchoolChild() {
		childKeyInit();
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
		personBirthDateInit();
		personBirthDateYearInit();
		personBirthDateMonthOfYearInit();
		personBirthDateDayOfWeekInit();
		personAgeInSeptemberInit();
		photoInit();
		childCompleteNameInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSchoolChild(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSchoolChild(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
		if(enrollmentSearch != null)
			enrollmentSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSchoolChild(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSchoolChild(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSchoolChild(String var) {
		SchoolChild oSchoolChild = (SchoolChild)this;
		switch(var) {
			case "childKey":
				return oSchoolChild.childKey;
			case "enrollmentKeys":
				return oSchoolChild.enrollmentKeys;
			case "familySort":
				return oSchoolChild.familySort;
			case "schoolSort":
				return oSchoolChild.schoolSort;
			case "enrollmentSearch":
				return oSchoolChild.enrollmentSearch;
			case "enrollments":
				return oSchoolChild.enrollments;
			case "userKeys":
				return oSchoolChild.userKeys;
			case "schoolKeys":
				return oSchoolChild.schoolKeys;
			case "yearKeys":
				return oSchoolChild.yearKeys;
			case "seasonKeys":
				return oSchoolChild.seasonKeys;
			case "sessionKeys":
				return oSchoolChild.sessionKeys;
			case "ageKeys":
				return oSchoolChild.ageKeys;
			case "personFirstName":
				return oSchoolChild.personFirstName;
			case "personFirstNamePreferred":
				return oSchoolChild.personFirstNamePreferred;
			case "familyName":
				return oSchoolChild.familyName;
			case "personCompleteName":
				return oSchoolChild.personCompleteName;
			case "personCompleteNamePreferred":
				return oSchoolChild.personCompleteNamePreferred;
			case "personFormalName":
				return oSchoolChild.personFormalName;
			case "personBirthDate":
				return oSchoolChild.personBirthDate;
			case "personBirthDateYear":
				return oSchoolChild.personBirthDateYear;
			case "personBirthDateMonthOfYear":
				return oSchoolChild.personBirthDateMonthOfYear;
			case "personBirthDateDayOfWeek":
				return oSchoolChild.personBirthDateDayOfWeek;
			case "personAgeInSeptember":
				return oSchoolChild.personAgeInSeptember;
			case "photo":
				return oSchoolChild.photo;
			case "childCompleteName":
				return oSchoolChild.childCompleteName;
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
				o = attributeSchoolChild(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSchoolChild(String var, Object val) {
		SchoolChild oSchoolChild = (SchoolChild)this;
		switch(var) {
			case "enrollmentKeys":
				oSchoolChild.addEnrollmentKeys((Long)val);
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
		return staticSetSchoolChild(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSchoolChild(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "childKey":
			return SchoolChild.staticSetChildKey(siteRequest_, o);
		case "enrollmentKeys":
			return SchoolChild.staticSetEnrollmentKeys(siteRequest_, o);
		case "familySort":
			return SchoolChild.staticSetFamilySort(siteRequest_, o);
		case "schoolSort":
			return SchoolChild.staticSetSchoolSort(siteRequest_, o);
		case "userKeys":
			return SchoolChild.staticSetUserKeys(siteRequest_, o);
		case "schoolKeys":
			return SchoolChild.staticSetSchoolKeys(siteRequest_, o);
		case "yearKeys":
			return SchoolChild.staticSetYearKeys(siteRequest_, o);
		case "seasonKeys":
			return SchoolChild.staticSetSeasonKeys(siteRequest_, o);
		case "sessionKeys":
			return SchoolChild.staticSetSessionKeys(siteRequest_, o);
		case "ageKeys":
			return SchoolChild.staticSetAgeKeys(siteRequest_, o);
		case "personFirstName":
			return SchoolChild.staticSetPersonFirstName(siteRequest_, o);
		case "personFirstNamePreferred":
			return SchoolChild.staticSetPersonFirstNamePreferred(siteRequest_, o);
		case "familyName":
			return SchoolChild.staticSetFamilyName(siteRequest_, o);
		case "personCompleteName":
			return SchoolChild.staticSetPersonCompleteName(siteRequest_, o);
		case "personCompleteNamePreferred":
			return SchoolChild.staticSetPersonCompleteNamePreferred(siteRequest_, o);
		case "personFormalName":
			return SchoolChild.staticSetPersonFormalName(siteRequest_, o);
		case "personBirthDate":
			return SchoolChild.staticSetPersonBirthDate(siteRequest_, o);
		case "personBirthDateYear":
			return SchoolChild.staticSetPersonBirthDateYear(siteRequest_, o);
		case "personBirthDateMonthOfYear":
			return SchoolChild.staticSetPersonBirthDateMonthOfYear(siteRequest_, o);
		case "personBirthDateDayOfWeek":
			return SchoolChild.staticSetPersonBirthDateDayOfWeek(siteRequest_, o);
		case "personAgeInSeptember":
			return SchoolChild.staticSetPersonAgeInSeptember(siteRequest_, o);
		case "photo":
			return SchoolChild.staticSetPhoto(siteRequest_, o);
		case "childCompleteName":
			return SchoolChild.staticSetChildCompleteName(siteRequest_, o);
			default:
				return Cluster.staticSetCluster(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrSchoolChild(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrSchoolChild(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "childKey":
			return SchoolChild.staticSolrChildKey(siteRequest_, (Long)o);
		case "enrollmentKeys":
			return SchoolChild.staticSolrEnrollmentKeys(siteRequest_, (Long)o);
		case "familySort":
			return SchoolChild.staticSolrFamilySort(siteRequest_, (Integer)o);
		case "schoolSort":
			return SchoolChild.staticSolrSchoolSort(siteRequest_, (Integer)o);
		case "userKeys":
			return SchoolChild.staticSolrUserKeys(siteRequest_, (Long)o);
		case "schoolKeys":
			return SchoolChild.staticSolrSchoolKeys(siteRequest_, (Long)o);
		case "yearKeys":
			return SchoolChild.staticSolrYearKeys(siteRequest_, (Long)o);
		case "seasonKeys":
			return SchoolChild.staticSolrSeasonKeys(siteRequest_, (Long)o);
		case "sessionKeys":
			return SchoolChild.staticSolrSessionKeys(siteRequest_, (Long)o);
		case "ageKeys":
			return SchoolChild.staticSolrAgeKeys(siteRequest_, (Long)o);
		case "personFirstName":
			return SchoolChild.staticSolrPersonFirstName(siteRequest_, (String)o);
		case "personFirstNamePreferred":
			return SchoolChild.staticSolrPersonFirstNamePreferred(siteRequest_, (String)o);
		case "familyName":
			return SchoolChild.staticSolrFamilyName(siteRequest_, (String)o);
		case "personCompleteName":
			return SchoolChild.staticSolrPersonCompleteName(siteRequest_, (String)o);
		case "personCompleteNamePreferred":
			return SchoolChild.staticSolrPersonCompleteNamePreferred(siteRequest_, (String)o);
		case "personFormalName":
			return SchoolChild.staticSolrPersonFormalName(siteRequest_, (String)o);
		case "personBirthDate":
			return SchoolChild.staticSolrPersonBirthDate(siteRequest_, (LocalDate)o);
		case "personBirthDateYear":
			return SchoolChild.staticSolrPersonBirthDateYear(siteRequest_, (Integer)o);
		case "personBirthDateMonthOfYear":
			return SchoolChild.staticSolrPersonBirthDateMonthOfYear(siteRequest_, (String)o);
		case "personBirthDateDayOfWeek":
			return SchoolChild.staticSolrPersonBirthDateDayOfWeek(siteRequest_, (String)o);
		case "personAgeInSeptember":
			return SchoolChild.staticSolrPersonAgeInSeptember(siteRequest_, (String)o);
		case "photo":
			return SchoolChild.staticSolrPhoto(siteRequest_, (String)o);
		case "childCompleteName":
			return SchoolChild.staticSolrChildCompleteName(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrCluster(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrSchoolChild(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrSchoolChild(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "childKey":
			return SchoolChild.staticSolrStrChildKey(siteRequest_, (Long)o);
		case "enrollmentKeys":
			return SchoolChild.staticSolrStrEnrollmentKeys(siteRequest_, (Long)o);
		case "familySort":
			return SchoolChild.staticSolrStrFamilySort(siteRequest_, (Integer)o);
		case "schoolSort":
			return SchoolChild.staticSolrStrSchoolSort(siteRequest_, (Integer)o);
		case "userKeys":
			return SchoolChild.staticSolrStrUserKeys(siteRequest_, (Long)o);
		case "schoolKeys":
			return SchoolChild.staticSolrStrSchoolKeys(siteRequest_, (Long)o);
		case "yearKeys":
			return SchoolChild.staticSolrStrYearKeys(siteRequest_, (Long)o);
		case "seasonKeys":
			return SchoolChild.staticSolrStrSeasonKeys(siteRequest_, (Long)o);
		case "sessionKeys":
			return SchoolChild.staticSolrStrSessionKeys(siteRequest_, (Long)o);
		case "ageKeys":
			return SchoolChild.staticSolrStrAgeKeys(siteRequest_, (Long)o);
		case "personFirstName":
			return SchoolChild.staticSolrStrPersonFirstName(siteRequest_, (String)o);
		case "personFirstNamePreferred":
			return SchoolChild.staticSolrStrPersonFirstNamePreferred(siteRequest_, (String)o);
		case "familyName":
			return SchoolChild.staticSolrStrFamilyName(siteRequest_, (String)o);
		case "personCompleteName":
			return SchoolChild.staticSolrStrPersonCompleteName(siteRequest_, (String)o);
		case "personCompleteNamePreferred":
			return SchoolChild.staticSolrStrPersonCompleteNamePreferred(siteRequest_, (String)o);
		case "personFormalName":
			return SchoolChild.staticSolrStrPersonFormalName(siteRequest_, (String)o);
		case "personBirthDate":
			return SchoolChild.staticSolrStrPersonBirthDate(siteRequest_, (Date)o);
		case "personBirthDateYear":
			return SchoolChild.staticSolrStrPersonBirthDateYear(siteRequest_, (Integer)o);
		case "personBirthDateMonthOfYear":
			return SchoolChild.staticSolrStrPersonBirthDateMonthOfYear(siteRequest_, (String)o);
		case "personBirthDateDayOfWeek":
			return SchoolChild.staticSolrStrPersonBirthDateDayOfWeek(siteRequest_, (String)o);
		case "personAgeInSeptember":
			return SchoolChild.staticSolrStrPersonAgeInSeptember(siteRequest_, (String)o);
		case "photo":
			return SchoolChild.staticSolrStrPhoto(siteRequest_, (String)o);
		case "childCompleteName":
			return SchoolChild.staticSolrStrChildCompleteName(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqSchoolChild(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqSchoolChild(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "childKey":
			return SchoolChild.staticSolrFqChildKey(siteRequest_, o);
		case "enrollmentKeys":
			return SchoolChild.staticSolrFqEnrollmentKeys(siteRequest_, o);
		case "familySort":
			return SchoolChild.staticSolrFqFamilySort(siteRequest_, o);
		case "schoolSort":
			return SchoolChild.staticSolrFqSchoolSort(siteRequest_, o);
		case "userKeys":
			return SchoolChild.staticSolrFqUserKeys(siteRequest_, o);
		case "schoolKeys":
			return SchoolChild.staticSolrFqSchoolKeys(siteRequest_, o);
		case "yearKeys":
			return SchoolChild.staticSolrFqYearKeys(siteRequest_, o);
		case "seasonKeys":
			return SchoolChild.staticSolrFqSeasonKeys(siteRequest_, o);
		case "sessionKeys":
			return SchoolChild.staticSolrFqSessionKeys(siteRequest_, o);
		case "ageKeys":
			return SchoolChild.staticSolrFqAgeKeys(siteRequest_, o);
		case "personFirstName":
			return SchoolChild.staticSolrFqPersonFirstName(siteRequest_, o);
		case "personFirstNamePreferred":
			return SchoolChild.staticSolrFqPersonFirstNamePreferred(siteRequest_, o);
		case "familyName":
			return SchoolChild.staticSolrFqFamilyName(siteRequest_, o);
		case "personCompleteName":
			return SchoolChild.staticSolrFqPersonCompleteName(siteRequest_, o);
		case "personCompleteNamePreferred":
			return SchoolChild.staticSolrFqPersonCompleteNamePreferred(siteRequest_, o);
		case "personFormalName":
			return SchoolChild.staticSolrFqPersonFormalName(siteRequest_, o);
		case "personBirthDate":
			return SchoolChild.staticSolrFqPersonBirthDate(siteRequest_, o);
		case "personBirthDateYear":
			return SchoolChild.staticSolrFqPersonBirthDateYear(siteRequest_, o);
		case "personBirthDateMonthOfYear":
			return SchoolChild.staticSolrFqPersonBirthDateMonthOfYear(siteRequest_, o);
		case "personBirthDateDayOfWeek":
			return SchoolChild.staticSolrFqPersonBirthDateDayOfWeek(siteRequest_, o);
		case "personAgeInSeptember":
			return SchoolChild.staticSolrFqPersonAgeInSeptember(siteRequest_, o);
		case "photo":
			return SchoolChild.staticSolrFqPhoto(siteRequest_, o);
		case "childCompleteName":
			return SchoolChild.staticSolrFqChildCompleteName(siteRequest_, o);
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
					o = defineSchoolChild(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolChild(String var, String val) {
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
			case "personBirthDate":
				if(val != null)
					setPersonBirthDate(val);
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
		populateSchoolChild(solrDocument);
	}
	public void populateSchoolChild(SolrDocument solrDocument) {
		SchoolChild oSchoolChild = (SchoolChild)this;
		saves = (List<String>)solrDocument.get("saves_stored_strings");
		if(saves != null) {

			if(saves.contains("childKey")) {
				Long childKey = (Long)solrDocument.get("childKey_stored_long");
				if(childKey != null)
					oSchoolChild.setChildKey(childKey);
			}

			List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
			if(enrollmentKeys != null)
				oSchoolChild.enrollmentKeys.addAll(enrollmentKeys);

			if(saves.contains("familySort")) {
				Integer familySort = (Integer)solrDocument.get("familySort_stored_int");
				if(familySort != null)
					oSchoolChild.setFamilySort(familySort);
			}

			if(saves.contains("schoolSort")) {
				Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
				if(schoolSort != null)
					oSchoolChild.setSchoolSort(schoolSort);
			}

			if(saves.contains("userKeys")) {
				List<Long> userKeys = (List<Long>)solrDocument.get("userKeys_stored_longs");
				if(userKeys != null)
					oSchoolChild.userKeys.addAll(userKeys);
			}

			if(saves.contains("schoolKeys")) {
				List<Long> schoolKeys = (List<Long>)solrDocument.get("schoolKeys_stored_longs");
				if(schoolKeys != null)
					oSchoolChild.schoolKeys.addAll(schoolKeys);
			}

			if(saves.contains("yearKeys")) {
				List<Long> yearKeys = (List<Long>)solrDocument.get("yearKeys_stored_longs");
				if(yearKeys != null)
					oSchoolChild.yearKeys.addAll(yearKeys);
			}

			if(saves.contains("seasonKeys")) {
				List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
				if(seasonKeys != null)
					oSchoolChild.seasonKeys.addAll(seasonKeys);
			}

			if(saves.contains("sessionKeys")) {
				List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
				if(sessionKeys != null)
					oSchoolChild.sessionKeys.addAll(sessionKeys);
			}

			if(saves.contains("ageKeys")) {
				List<Long> ageKeys = (List<Long>)solrDocument.get("ageKeys_stored_longs");
				if(ageKeys != null)
					oSchoolChild.ageKeys.addAll(ageKeys);
			}

			if(saves.contains("personFirstName")) {
				String personFirstName = (String)solrDocument.get("personFirstName_stored_string");
				if(personFirstName != null)
					oSchoolChild.setPersonFirstName(personFirstName);
			}

			if(saves.contains("personFirstNamePreferred")) {
				String personFirstNamePreferred = (String)solrDocument.get("personFirstNamePreferred_stored_string");
				if(personFirstNamePreferred != null)
					oSchoolChild.setPersonFirstNamePreferred(personFirstNamePreferred);
			}

			if(saves.contains("familyName")) {
				String familyName = (String)solrDocument.get("familyName_stored_string");
				if(familyName != null)
					oSchoolChild.setFamilyName(familyName);
			}

			if(saves.contains("personCompleteName")) {
				String personCompleteName = (String)solrDocument.get("personCompleteName_stored_string");
				if(personCompleteName != null)
					oSchoolChild.setPersonCompleteName(personCompleteName);
			}

			if(saves.contains("personCompleteNamePreferred")) {
				String personCompleteNamePreferred = (String)solrDocument.get("personCompleteNamePreferred_stored_string");
				if(personCompleteNamePreferred != null)
					oSchoolChild.setPersonCompleteNamePreferred(personCompleteNamePreferred);
			}

			if(saves.contains("personFormalName")) {
				String personFormalName = (String)solrDocument.get("personFormalName_stored_string");
				if(personFormalName != null)
					oSchoolChild.setPersonFormalName(personFormalName);
			}

			if(saves.contains("personBirthDate")) {
				Date personBirthDate = (Date)solrDocument.get("personBirthDate_stored_date");
				if(personBirthDate != null)
					oSchoolChild.setPersonBirthDate(personBirthDate);
			}

			if(saves.contains("personBirthDateYear")) {
				Integer personBirthDateYear = (Integer)solrDocument.get("personBirthDateYear_stored_int");
				if(personBirthDateYear != null)
					oSchoolChild.setPersonBirthDateYear(personBirthDateYear);
			}

			if(saves.contains("personBirthDateMonthOfYear")) {
				String personBirthDateMonthOfYear = (String)solrDocument.get("personBirthDateMonthOfYear_stored_string");
				if(personBirthDateMonthOfYear != null)
					oSchoolChild.setPersonBirthDateMonthOfYear(personBirthDateMonthOfYear);
			}

			if(saves.contains("personBirthDateDayOfWeek")) {
				String personBirthDateDayOfWeek = (String)solrDocument.get("personBirthDateDayOfWeek_stored_string");
				if(personBirthDateDayOfWeek != null)
					oSchoolChild.setPersonBirthDateDayOfWeek(personBirthDateDayOfWeek);
			}

			if(saves.contains("personAgeInSeptember")) {
				String personAgeInSeptember = (String)solrDocument.get("personAgeInSeptember_stored_string");
				if(personAgeInSeptember != null)
					oSchoolChild.setPersonAgeInSeptember(personAgeInSeptember);
			}

			if(saves.contains("photo")) {
				String photo = (String)solrDocument.get("photo_stored_string");
				if(photo != null)
					oSchoolChild.setPhoto(photo);
			}

			if(saves.contains("childCompleteName")) {
				String childCompleteName = (String)solrDocument.get("childCompleteName_stored_string");
				if(childCompleteName != null)
					oSchoolChild.setChildCompleteName(childCompleteName);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.child.SchoolChild"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			SchoolChild o = new SchoolChild();
			o.siteRequestSchoolChild(siteRequest);
			o.initDeepSchoolChild(siteRequest);
			o.indexSchoolChild();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexSchoolChild();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexSchoolChild(document);
	}

	public void indexSchoolChild(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolChild(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolChild() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolChild(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolChild(SolrInputDocument document) {
		if(childKey != null) {
			document.addField("childKey_indexed_long", childKey);
			document.addField("childKey_stored_long", childKey);
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
		if(personBirthDate != null) {
			document.addField("personBirthDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(personBirthDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("personBirthDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(personBirthDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(personBirthDateYear != null) {
			document.addField("personBirthDateYear_indexed_int", personBirthDateYear);
			document.addField("personBirthDateYear_stored_int", personBirthDateYear);
		}
		if(personBirthDateMonthOfYear != null) {
			document.addField("personBirthDateMonthOfYear_indexed_string", personBirthDateMonthOfYear);
			document.addField("personBirthDateMonthOfYear_stored_string", personBirthDateMonthOfYear);
		}
		if(personBirthDateDayOfWeek != null) {
			document.addField("personBirthDateDayOfWeek_indexed_string", personBirthDateDayOfWeek);
			document.addField("personBirthDateDayOfWeek_stored_string", personBirthDateDayOfWeek);
		}
		if(personAgeInSeptember != null) {
			document.addField("personAgeInSeptember_indexed_string", personAgeInSeptember);
			document.addField("personAgeInSeptember_stored_string", personAgeInSeptember);
		}
		if(photo != null) {
			document.addField("photo_stored_string", photo);
		}
		if(childCompleteName != null) {
			document.addField("childCompleteName_indexed_string", childCompleteName);
			document.addField("childCompleteName_stored_string", childCompleteName);
		}
		super.indexCluster(document);

	}

	public void unindexSchoolChild() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepSchoolChild(siteRequest);
			SolrClient solrClient = siteContext.getSolrClient();
			solrClient.deleteById(id.toString());
			solrClient.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexedSchoolChild(String entityVar) {
		switch(entityVar) {
			case "childKey":
				return "childKey_indexed_long";
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
			case "personBirthDate":
				return "personBirthDate_indexed_date";
			case "personBirthDateYear":
				return "personBirthDateYear_indexed_int";
			case "personBirthDateMonthOfYear":
				return "personBirthDateMonthOfYear_indexed_string";
			case "personBirthDateDayOfWeek":
				return "personBirthDateDayOfWeek_indexed_string";
			case "personAgeInSeptember":
				return "personAgeInSeptember_indexed_string";
			case "childCompleteName":
				return "childCompleteName_indexed_string";
			default:
				return Cluster.varIndexedCluster(entityVar);
		}
	}

	public static String varSearchSchoolChild(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSearchCluster(entityVar);
		}
	}

	public static String varSuggestedSchoolChild(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSuggestedCluster(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrDocument solrDocument) {
		storeSchoolChild(solrDocument);
	}
	public void storeSchoolChild(SolrDocument solrDocument) {
		SchoolChild oSchoolChild = (SchoolChild)this;

		Long childKey = (Long)solrDocument.get("childKey_stored_long");
		if(childKey != null)
			oSchoolChild.setChildKey(childKey);

		List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
		if(enrollmentKeys != null)
			oSchoolChild.enrollmentKeys.addAll(enrollmentKeys);

		Integer familySort = (Integer)solrDocument.get("familySort_stored_int");
		if(familySort != null)
			oSchoolChild.setFamilySort(familySort);

		Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
		if(schoolSort != null)
			oSchoolChild.setSchoolSort(schoolSort);

		List<Long> userKeys = (List<Long>)solrDocument.get("userKeys_stored_longs");
		if(userKeys != null)
			oSchoolChild.userKeys.addAll(userKeys);

		List<Long> schoolKeys = (List<Long>)solrDocument.get("schoolKeys_stored_longs");
		if(schoolKeys != null)
			oSchoolChild.schoolKeys.addAll(schoolKeys);

		List<Long> yearKeys = (List<Long>)solrDocument.get("yearKeys_stored_longs");
		if(yearKeys != null)
			oSchoolChild.yearKeys.addAll(yearKeys);

		List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
		if(seasonKeys != null)
			oSchoolChild.seasonKeys.addAll(seasonKeys);

		List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
		if(sessionKeys != null)
			oSchoolChild.sessionKeys.addAll(sessionKeys);

		List<Long> ageKeys = (List<Long>)solrDocument.get("ageKeys_stored_longs");
		if(ageKeys != null)
			oSchoolChild.ageKeys.addAll(ageKeys);

		String personFirstName = (String)solrDocument.get("personFirstName_stored_string");
		if(personFirstName != null)
			oSchoolChild.setPersonFirstName(personFirstName);

		String personFirstNamePreferred = (String)solrDocument.get("personFirstNamePreferred_stored_string");
		if(personFirstNamePreferred != null)
			oSchoolChild.setPersonFirstNamePreferred(personFirstNamePreferred);

		String familyName = (String)solrDocument.get("familyName_stored_string");
		if(familyName != null)
			oSchoolChild.setFamilyName(familyName);

		String personCompleteName = (String)solrDocument.get("personCompleteName_stored_string");
		if(personCompleteName != null)
			oSchoolChild.setPersonCompleteName(personCompleteName);

		String personCompleteNamePreferred = (String)solrDocument.get("personCompleteNamePreferred_stored_string");
		if(personCompleteNamePreferred != null)
			oSchoolChild.setPersonCompleteNamePreferred(personCompleteNamePreferred);

		String personFormalName = (String)solrDocument.get("personFormalName_stored_string");
		if(personFormalName != null)
			oSchoolChild.setPersonFormalName(personFormalName);

		Date personBirthDate = (Date)solrDocument.get("personBirthDate_stored_date");
		if(personBirthDate != null)
			oSchoolChild.setPersonBirthDate(personBirthDate);

		Integer personBirthDateYear = (Integer)solrDocument.get("personBirthDateYear_stored_int");
		if(personBirthDateYear != null)
			oSchoolChild.setPersonBirthDateYear(personBirthDateYear);

		String personBirthDateMonthOfYear = (String)solrDocument.get("personBirthDateMonthOfYear_stored_string");
		if(personBirthDateMonthOfYear != null)
			oSchoolChild.setPersonBirthDateMonthOfYear(personBirthDateMonthOfYear);

		String personBirthDateDayOfWeek = (String)solrDocument.get("personBirthDateDayOfWeek_stored_string");
		if(personBirthDateDayOfWeek != null)
			oSchoolChild.setPersonBirthDateDayOfWeek(personBirthDateDayOfWeek);

		String personAgeInSeptember = (String)solrDocument.get("personAgeInSeptember_stored_string");
		if(personAgeInSeptember != null)
			oSchoolChild.setPersonAgeInSeptember(personAgeInSeptember);

		String photo = (String)solrDocument.get("photo_stored_string");
		if(photo != null)
			oSchoolChild.setPhoto(photo);

		String childCompleteName = (String)solrDocument.get("childCompleteName_stored_string");
		if(childCompleteName != null)
			oSchoolChild.setChildCompleteName(childCompleteName);

		super.storeCluster(solrDocument);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSchoolChild() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SchoolChild) {
			SchoolChild original = (SchoolChild)o;
			if(!Objects.equals(childKey, original.getChildKey()))
				apiRequest.addVars("childKey");
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
			if(!Objects.equals(personBirthDate, original.getPersonBirthDate()))
				apiRequest.addVars("personBirthDate");
			if(!Objects.equals(personBirthDateYear, original.getPersonBirthDateYear()))
				apiRequest.addVars("personBirthDateYear");
			if(!Objects.equals(personBirthDateMonthOfYear, original.getPersonBirthDateMonthOfYear()))
				apiRequest.addVars("personBirthDateMonthOfYear");
			if(!Objects.equals(personBirthDateDayOfWeek, original.getPersonBirthDateDayOfWeek()))
				apiRequest.addVars("personBirthDateDayOfWeek");
			if(!Objects.equals(personAgeInSeptember, original.getPersonAgeInSeptember()))
				apiRequest.addVars("personAgeInSeptember");
			if(!Objects.equals(photo, original.getPhoto()))
				apiRequest.addVars("photo");
			if(!Objects.equals(childCompleteName, original.getChildCompleteName()))
				apiRequest.addVars("childCompleteName");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), childKey, enrollmentKeys, familySort, schoolSort, userKeys, schoolKeys, yearKeys, seasonKeys, sessionKeys, ageKeys, personFirstName, personFirstNamePreferred, familyName, personCompleteName, personCompleteNamePreferred, personFormalName, personBirthDate, personBirthDateYear, personBirthDateMonthOfYear, personBirthDateDayOfWeek, personAgeInSeptember, photo, childCompleteName);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SchoolChild))
			return false;
		SchoolChild that = (SchoolChild)o;
		return super.equals(o)
				&& Objects.equals( childKey, that.childKey )
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
				&& Objects.equals( personBirthDate, that.personBirthDate )
				&& Objects.equals( personBirthDateYear, that.personBirthDateYear )
				&& Objects.equals( personBirthDateMonthOfYear, that.personBirthDateMonthOfYear )
				&& Objects.equals( personBirthDateDayOfWeek, that.personBirthDateDayOfWeek )
				&& Objects.equals( personAgeInSeptember, that.personAgeInSeptember )
				&& Objects.equals( photo, that.photo )
				&& Objects.equals( childCompleteName, that.childCompleteName );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolChild { ");
		sb.append( "childKey: " ).append(childKey);
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
		sb.append( ", personBirthDate: " ).append(personBirthDate);
		sb.append( ", personBirthDateYear: " ).append(personBirthDateYear);
		sb.append( ", personBirthDateMonthOfYear: \"" ).append(personBirthDateMonthOfYear).append( "\"" );
		sb.append( ", personBirthDateDayOfWeek: \"" ).append(personBirthDateDayOfWeek).append( "\"" );
		sb.append( ", personAgeInSeptember: \"" ).append(personAgeInSeptember).append( "\"" );
		sb.append( ", photo: \"" ).append(photo).append( "\"" );
		sb.append( ", childCompleteName: \"" ).append(childCompleteName).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
