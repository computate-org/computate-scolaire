package org.computate.scolaire.enUS.dad;

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
 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true">Find the class  in Solr. </a>
 * <br/>
 **/
public abstract class SchoolDadGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolDad.class);

	public static final List<String> ROLES = Arrays.asList("SiteManager");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String SchoolDad_AName = "a dad";
	public static final String SchoolDad_This = "this ";
	public static final String SchoolDad_ThisName = "this dad";
	public static final String SchoolDad_A = "a ";
	public static final String SchoolDad_TheName = "the dad";
	public static final String SchoolDad_NameSingular = "dad";
	public static final String SchoolDad_NamePlural = "dads";
	public static final String SchoolDad_NameActual = "current dad";
	public static final String SchoolDad_AllName = "all the dads";
	public static final String SchoolDad_SearchAllNameBy = "search dads by ";
	public static final String SchoolDad_Title = "dads";
	public static final String SchoolDad_ThePluralName = "the dads";
	public static final String SchoolDad_NoNameFound = "no dad found";
	public static final String SchoolDad_NameVar = "dad";
	public static final String SchoolDad_OfName = "of dad";
	public static final String SchoolDad_ANameAdjective = "a dad";
	public static final String SchoolDad_NameAdjectiveSingular = "dad";
	public static final String SchoolDad_NameAdjectivePlural = "dads";
	public static final String SchoolDad_Color = "light-blue";
	public static final String SchoolDad_IconGroup = "regular";
	public static final String SchoolDad_IconName = "male";

	////////////
	// dadKey //
	////////////

	/**	 The entity dadKey
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long dadKey;
	@JsonIgnore
	public Wrap<Long> dadKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("dadKey").o(dadKey);

	/**	<br/> The entity dadKey
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dadKey">Find the entity dadKey in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _dadKey(Wrap<Long> c);

	public Long getDadKey() {
		return dadKey;
	}

	public void setDadKey(Long dadKey) {
		this.dadKey = dadKey;
		this.dadKeyWrap.alreadyInitialized = true;
	}
	public void setDadKey(String o) {
		this.dadKey = SchoolDad.staticSetDadKey(siteRequest_, o);
		this.dadKeyWrap.alreadyInitialized = true;
	}
	public static Long staticSetDadKey(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	protected SchoolDad dadKeyInit() {
		if(!dadKeyWrap.alreadyInitialized) {
			_dadKey(dadKeyWrap);
			if(dadKey == null)
				setDadKey(dadKeyWrap.o);
		}
		dadKeyWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static Long staticSolrDadKey(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrDadKey(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDadKey(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrDadKey(siteRequest_, SchoolDad.staticSolrDadKey(siteRequest_, SchoolDad.staticSetDadKey(siteRequest_, o)));
	}

	public Long solrDadKey() {
		return SchoolDad.staticSolrDadKey(siteRequest_, dadKey);
	}

	public String strDadKey() {
		return dadKey == null ? "" : dadKey.toString();
	}

	public String jsonDadKey() {
		return dadKey == null ? "" : dadKey.toString();
	}

	public String nomAffichageDadKey() {
		return "key";
	}

	public String htmTooltipDadKey() {
		return null;
	}

	public String htmDadKey() {
		return dadKey == null ? "" : StringEscapeUtils.escapeHtml4(strDadKey());
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Find the entity enrollmentKeys in Solr</a>
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
		Long l = SchoolDad.staticSetEnrollmentKeys(siteRequest_, o);
		if(l != null)
			addEnrollmentKeys(l);
		this.enrollmentKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetEnrollmentKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolDad addEnrollmentKeys(Long...objets) {
		for(Long o : objets) {
			addEnrollmentKeys(o);
		}
		return (SchoolDad)this;
	}
	public SchoolDad addEnrollmentKeys(Long o) {
		if(o != null && !enrollmentKeys.contains(o))
			this.enrollmentKeys.add(o);
		return (SchoolDad)this;
	}
	public void setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
	}
	public SchoolDad addEnrollmentKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEnrollmentKeys(p);
		}
		return (SchoolDad)this;
	}
	protected SchoolDad enrollmentKeysInit() {
		if(!enrollmentKeysWrap.alreadyInitialized) {
			_enrollmentKeys(enrollmentKeys);
		}
		enrollmentKeysWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static Long staticSolrEnrollmentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrEnrollmentKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqEnrollmentKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrEnrollmentKeys(siteRequest_, SchoolDad.staticSolrEnrollmentKeys(siteRequest_, SchoolDad.staticSetEnrollmentKeys(siteRequest_, o)));
	}

	public List<Long> solrEnrollmentKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : enrollmentKeys) {
			l.add(SchoolDad.staticSolrEnrollmentKeys(siteRequest_, o));
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
		SchoolDad s = (SchoolDad)this;
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
				a("oninput", "suggestSchoolDadEnrollmentKeys($(this).val() ? searchSchoolEnrollmentFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'dadKeys:" + pk + "'}", "], $('#listSchoolDadEnrollmentKeys_", classApiMethodMethod, "'), ", pk, "); ");

				fg();

		} else {
			e("span").a("class", "varSchoolDad", pk, "EnrollmentKeys ").f().sx(htmEnrollmentKeys()).g("span");
		}
	}

	public void htmEnrollmentKeys(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolDadEnrollmentKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/enrollment?fq=dadKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue-gray w3-hover-blue-gray ").f();
								e("i").a("class", "fas fa-edit ").f().g("i");
								sx("enrollments");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate enrollments to this dad");
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
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolDadEnrollmentKeys_", classApiMethodMethod).f();
								} g("ul");
								{
									if("Page".equals(classApiMethodMethod)) {
										{ e("div").a("class", "w3-cell-row ").f();
											e("button")
												.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
												.a("id", classApiMethodMethod, "_enrollmentKeys_add")
												.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolEnrollmentVals({ dadKeys: [ \"", pk, "\" ] }, function() {}, function() { addError($('#", classApiMethodMethod, "enrollmentKeys')); });")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familySort">Find the entity familySort in Solr</a>
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
		this.familySort = SchoolDad.staticSetFamilySort(siteRequest_, o);
		this.familySortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetFamilySort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolDad familySortInit() {
		if(!familySortWrap.alreadyInitialized) {
			_familySort(familySortWrap);
			if(familySort == null)
				setFamilySort(familySortWrap.o);
		}
		familySortWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static Integer staticSolrFamilySort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrFamilySort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilySort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrFamilySort(siteRequest_, SchoolDad.staticSolrFamilySort(siteRequest_, SchoolDad.staticSetFamilySort(siteRequest_, o)));
	}

	public Integer solrFamilySort() {
		return SchoolDad.staticSolrFamilySort(siteRequest_, familySort);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Find the entity schoolSort in Solr</a>
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
		this.schoolSort = SchoolDad.staticSetSchoolSort(siteRequest_, o);
		this.schoolSortWrap.alreadyInitialized = true;
	}
	public static Integer staticSetSchoolSort(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Integer.parseInt(o);
		return null;
	}
	protected SchoolDad schoolSortInit() {
		if(!schoolSortWrap.alreadyInitialized) {
			_schoolSort(schoolSortWrap);
			if(schoolSort == null)
				setSchoolSort(schoolSortWrap.o);
		}
		schoolSortWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static Integer staticSolrSchoolSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o;
	}

	public static String staticSolrStrSchoolSort(SiteRequestEnUS siteRequest_, Integer o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolSort(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrSchoolSort(siteRequest_, SchoolDad.staticSolrSchoolSort(siteRequest_, SchoolDad.staticSetSchoolSort(siteRequest_, o)));
	}

	public Integer solrSchoolSort() {
		return SchoolDad.staticSolrSchoolSort(siteRequest_, schoolSort);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSearch">Find the entity enrollmentSearch in Solr</a>
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
	protected SchoolDad enrollmentSearchInit() {
		if(!enrollmentSearchWrap.alreadyInitialized) {
			_enrollmentSearch(enrollmentSearch);
		}
		enrollmentSearch.initDeepForClass(siteRequest_);
		enrollmentSearchWrap.alreadyInitialized(true);
		return (SchoolDad)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollments">Find the entity enrollments in Solr</a>
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
	public SchoolDad addEnrollments(SchoolEnrollment...objets) {
		for(SchoolEnrollment o : objets) {
			addEnrollments(o);
		}
		return (SchoolDad)this;
	}
	public SchoolDad addEnrollments(SchoolEnrollment o) {
		if(o != null && !enrollments.contains(o))
			this.enrollments.add(o);
		return (SchoolDad)this;
	}
	protected SchoolDad enrollmentsInit() {
		if(!enrollmentsWrap.alreadyInitialized) {
			_enrollments(enrollments);
		}
		enrollmentsWrap.alreadyInitialized(true);
		return (SchoolDad)this;
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userKeys">Find the entity userKeys in Solr</a>
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
		Long l = SchoolDad.staticSetUserKeys(siteRequest_, o);
		if(l != null)
			addUserKeys(l);
		this.userKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetUserKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolDad addUserKeys(Long...objets) {
		for(Long o : objets) {
			addUserKeys(o);
		}
		return (SchoolDad)this;
	}
	public SchoolDad addUserKeys(Long o) {
		if(o != null && !userKeys.contains(o))
			this.userKeys.add(o);
		return (SchoolDad)this;
	}
	public void setUserKeys(JsonArray objets) {
		userKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUserKeys(o);
		}
	}
	public SchoolDad addUserKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addUserKeys(p);
		}
		return (SchoolDad)this;
	}
	protected SchoolDad userKeysInit() {
		if(!userKeysWrap.alreadyInitialized) {
			_userKeys(userKeys);
		}
		userKeysWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static Long staticSolrUserKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrUserKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqUserKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrUserKeys(siteRequest_, SchoolDad.staticSolrUserKeys(siteRequest_, SchoolDad.staticSetUserKeys(siteRequest_, o)));
	}

	public List<Long> solrUserKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : userKeys) {
			l.add(SchoolDad.staticSolrUserKeys(siteRequest_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKeys">Find the entity schoolKeys in Solr</a>
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
		Long l = SchoolDad.staticSetSchoolKeys(siteRequest_, o);
		if(l != null)
			addSchoolKeys(l);
		this.schoolKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetSchoolKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolDad addSchoolKeys(Long...objets) {
		for(Long o : objets) {
			addSchoolKeys(o);
		}
		return (SchoolDad)this;
	}
	public SchoolDad addSchoolKeys(Long o) {
		if(o != null && !schoolKeys.contains(o))
			this.schoolKeys.add(o);
		return (SchoolDad)this;
	}
	public void setSchoolKeys(JsonArray objets) {
		schoolKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSchoolKeys(o);
		}
	}
	public SchoolDad addSchoolKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSchoolKeys(p);
		}
		return (SchoolDad)this;
	}
	protected SchoolDad schoolKeysInit() {
		if(!schoolKeysWrap.alreadyInitialized) {
			_schoolKeys(schoolKeys);
		}
		schoolKeysWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static Long staticSolrSchoolKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSchoolKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSchoolKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrSchoolKeys(siteRequest_, SchoolDad.staticSolrSchoolKeys(siteRequest_, SchoolDad.staticSetSchoolKeys(siteRequest_, o)));
	}

	public List<Long> solrSchoolKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : schoolKeys) {
			l.add(SchoolDad.staticSolrSchoolKeys(siteRequest_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKeys">Find the entity yearKeys in Solr</a>
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
		Long l = SchoolDad.staticSetYearKeys(siteRequest_, o);
		if(l != null)
			addYearKeys(l);
		this.yearKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetYearKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolDad addYearKeys(Long...objets) {
		for(Long o : objets) {
			addYearKeys(o);
		}
		return (SchoolDad)this;
	}
	public SchoolDad addYearKeys(Long o) {
		if(o != null && !yearKeys.contains(o))
			this.yearKeys.add(o);
		return (SchoolDad)this;
	}
	public void setYearKeys(JsonArray objets) {
		yearKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addYearKeys(o);
		}
	}
	public SchoolDad addYearKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addYearKeys(p);
		}
		return (SchoolDad)this;
	}
	protected SchoolDad yearKeysInit() {
		if(!yearKeysWrap.alreadyInitialized) {
			_yearKeys(yearKeys);
		}
		yearKeysWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static Long staticSolrYearKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrYearKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqYearKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrYearKeys(siteRequest_, SchoolDad.staticSolrYearKeys(siteRequest_, SchoolDad.staticSetYearKeys(siteRequest_, o)));
	}

	public List<Long> solrYearKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : yearKeys) {
			l.add(SchoolDad.staticSolrYearKeys(siteRequest_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKeys">Find the entity seasonKeys in Solr</a>
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
		Long l = SchoolDad.staticSetSeasonKeys(siteRequest_, o);
		if(l != null)
			addSeasonKeys(l);
		this.seasonKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetSeasonKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolDad addSeasonKeys(Long...objets) {
		for(Long o : objets) {
			addSeasonKeys(o);
		}
		return (SchoolDad)this;
	}
	public SchoolDad addSeasonKeys(Long o) {
		if(o != null && !seasonKeys.contains(o))
			this.seasonKeys.add(o);
		return (SchoolDad)this;
	}
	public void setSeasonKeys(JsonArray objets) {
		seasonKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSeasonKeys(o);
		}
	}
	public SchoolDad addSeasonKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSeasonKeys(p);
		}
		return (SchoolDad)this;
	}
	protected SchoolDad seasonKeysInit() {
		if(!seasonKeysWrap.alreadyInitialized) {
			_seasonKeys(seasonKeys);
		}
		seasonKeysWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static Long staticSolrSeasonKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSeasonKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSeasonKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrSeasonKeys(siteRequest_, SchoolDad.staticSolrSeasonKeys(siteRequest_, SchoolDad.staticSetSeasonKeys(siteRequest_, o)));
	}

	public List<Long> solrSeasonKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : seasonKeys) {
			l.add(SchoolDad.staticSolrSeasonKeys(siteRequest_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKeys">Find the entity sessionKeys in Solr</a>
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
		Long l = SchoolDad.staticSetSessionKeys(siteRequest_, o);
		if(l != null)
			addSessionKeys(l);
		this.sessionKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetSessionKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolDad addSessionKeys(Long...objets) {
		for(Long o : objets) {
			addSessionKeys(o);
		}
		return (SchoolDad)this;
	}
	public SchoolDad addSessionKeys(Long o) {
		if(o != null && !sessionKeys.contains(o))
			this.sessionKeys.add(o);
		return (SchoolDad)this;
	}
	public void setSessionKeys(JsonArray objets) {
		sessionKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionKeys(o);
		}
	}
	public SchoolDad addSessionKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSessionKeys(p);
		}
		return (SchoolDad)this;
	}
	protected SchoolDad sessionKeysInit() {
		if(!sessionKeysWrap.alreadyInitialized) {
			_sessionKeys(sessionKeys);
		}
		sessionKeysWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static Long staticSolrSessionKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrSessionKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqSessionKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrSessionKeys(siteRequest_, SchoolDad.staticSolrSessionKeys(siteRequest_, SchoolDad.staticSetSessionKeys(siteRequest_, o)));
	}

	public List<Long> solrSessionKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : sessionKeys) {
			l.add(SchoolDad.staticSolrSessionKeys(siteRequest_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKeys">Find the entity ageKeys in Solr</a>
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
		Long l = SchoolDad.staticSetAgeKeys(siteRequest_, o);
		if(l != null)
			addAgeKeys(l);
		this.ageKeysWrap.alreadyInitialized = true;
	}
	public static Long staticSetAgeKeys(SiteRequestEnUS siteRequest_, String o) {
		if(NumberUtils.isParsable(o))
			return Long.parseLong(o);
		return null;
	}
	public SchoolDad addAgeKeys(Long...objets) {
		for(Long o : objets) {
			addAgeKeys(o);
		}
		return (SchoolDad)this;
	}
	public SchoolDad addAgeKeys(Long o) {
		if(o != null && !ageKeys.contains(o))
			this.ageKeys.add(o);
		return (SchoolDad)this;
	}
	public void setAgeKeys(JsonArray objets) {
		ageKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAgeKeys(o);
		}
	}
	public SchoolDad addAgeKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAgeKeys(p);
		}
		return (SchoolDad)this;
	}
	protected SchoolDad ageKeysInit() {
		if(!ageKeysWrap.alreadyInitialized) {
			_ageKeys(ageKeys);
		}
		ageKeysWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static Long staticSolrAgeKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o;
	}

	public static String staticSolrStrAgeKeys(SiteRequestEnUS siteRequest_, Long o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqAgeKeys(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrAgeKeys(siteRequest_, SchoolDad.staticSolrAgeKeys(siteRequest_, SchoolDad.staticSetAgeKeys(siteRequest_, o)));
	}

	public List<Long> solrAgeKeys() {
		List<Long> l = new ArrayList<Long>();
		for(Long o : ageKeys) {
			l.add(SchoolDad.staticSolrAgeKeys(siteRequest_, o));
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personFirstName">Find the entity personFirstName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personFirstName(Wrap<String> c);

	public String getPersonFirstName() {
		return personFirstName;
	}
	public void setPersonFirstName(String o) {
		this.personFirstName = SchoolDad.staticSetPersonFirstName(siteRequest_, o);
		this.personFirstNameWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolDad personFirstNameInit() {
		if(!personFirstNameWrap.alreadyInitialized) {
			_personFirstName(personFirstNameWrap);
			if(personFirstName == null)
				setPersonFirstName(personFirstNameWrap.o);
		}
		personFirstNameWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static String staticSolrPersonFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonFirstName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonFirstName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrPersonFirstName(siteRequest_, SchoolDad.staticSolrPersonFirstName(siteRequest_, SchoolDad.staticSetPersonFirstName(siteRequest_, o)));
	}

	public String solrPersonFirstName() {
		return SchoolDad.staticSolrPersonFirstName(siteRequest_, personFirstName);
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
		SchoolDad s = (SchoolDad)this;
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
					a("class", "setPersonFirstName classSchoolDad inputSchoolDad", pk, "PersonFirstName w3-input w3-border ");
					a("name", "setPersonFirstName");
				} else {
					a("class", "valuePersonFirstName w3-input w3-border classSchoolDad inputSchoolDad", pk, "PersonFirstName w3-input w3-border ");
					a("name", "personFirstName");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonFirstName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personFirstName')); }, function() { addError($('#", classApiMethodMethod, "_personFirstName')); }); ");
				}
				a("value", strPersonFirstName())
			.fg();

		} else {
			e("span").a("class", "varSchoolDad", pk, "PersonFirstName ").f().sx(htmPersonFirstName()).g("span");
		}
	}

	public void htmPersonFirstName(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolDadPersonFirstName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-blue ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-blue ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personFirstName')); $('#", classApiMethodMethod, "_personFirstName').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolDadForm :input[name=pk]').val() }], 'setPersonFirstName', null, function() { addGlow($('#", classApiMethodMethod, "_personFirstName')); }, function() { addError($('#", classApiMethodMethod, "_personFirstName')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personFirstNamePreferred">Find the entity personFirstNamePreferred in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personFirstNamePreferred(Wrap<String> c);

	public String getPersonFirstNamePreferred() {
		return personFirstNamePreferred;
	}
	public void setPersonFirstNamePreferred(String o) {
		this.personFirstNamePreferred = SchoolDad.staticSetPersonFirstNamePreferred(siteRequest_, o);
		this.personFirstNamePreferredWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolDad personFirstNamePreferredInit() {
		if(!personFirstNamePreferredWrap.alreadyInitialized) {
			_personFirstNamePreferred(personFirstNamePreferredWrap);
			if(personFirstNamePreferred == null)
				setPersonFirstNamePreferred(personFirstNamePreferredWrap.o);
		}
		personFirstNamePreferredWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static String staticSolrPersonFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonFirstNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrPersonFirstNamePreferred(siteRequest_, SchoolDad.staticSolrPersonFirstNamePreferred(siteRequest_, SchoolDad.staticSetPersonFirstNamePreferred(siteRequest_, o)));
	}

	public String solrPersonFirstNamePreferred() {
		return SchoolDad.staticSolrPersonFirstNamePreferred(siteRequest_, personFirstNamePreferred);
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
		SchoolDad s = (SchoolDad)this;
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
					a("class", "setPersonFirstNamePreferred classSchoolDad inputSchoolDad", pk, "PersonFirstNamePreferred w3-input w3-border ");
					a("name", "setPersonFirstNamePreferred");
				} else {
					a("class", "valuePersonFirstNamePreferred w3-input w3-border classSchoolDad inputSchoolDad", pk, "PersonFirstNamePreferred w3-input w3-border ");
					a("name", "personFirstNamePreferred");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonFirstNamePreferred', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personFirstNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_personFirstNamePreferred')); }); ");
				}
				a("value", strPersonFirstNamePreferred())
			.fg();

		} else {
			e("span").a("class", "varSchoolDad", pk, "PersonFirstNamePreferred ").f().sx(htmPersonFirstNamePreferred()).g("span");
		}
	}

	public void htmPersonFirstNamePreferred(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolDadPersonFirstNamePreferred").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-blue ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-blue ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personFirstNamePreferred')); $('#", classApiMethodMethod, "_personFirstNamePreferred').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolDadForm :input[name=pk]').val() }], 'setPersonFirstNamePreferred', null, function() { addGlow($('#", classApiMethodMethod, "_personFirstNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_personFirstNamePreferred')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyName">Find the entity familyName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _familyName(Wrap<String> c);

	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String o) {
		this.familyName = SchoolDad.staticSetFamilyName(siteRequest_, o);
		this.familyNameWrap.alreadyInitialized = true;
	}
	public static String staticSetFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolDad familyNameInit() {
		if(!familyNameWrap.alreadyInitialized) {
			_familyName(familyNameWrap);
			if(familyName == null)
				setFamilyName(familyNameWrap.o);
		}
		familyNameWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static String staticSolrFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqFamilyName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrFamilyName(siteRequest_, SchoolDad.staticSolrFamilyName(siteRequest_, SchoolDad.staticSetFamilyName(siteRequest_, o)));
	}

	public String solrFamilyName() {
		return SchoolDad.staticSolrFamilyName(siteRequest_, familyName);
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
		SchoolDad s = (SchoolDad)this;
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
					a("class", "setFamilyName classSchoolDad inputSchoolDad", pk, "FamilyName w3-input w3-border ");
					a("name", "setFamilyName");
				} else {
					a("class", "valueFamilyName w3-input w3-border classSchoolDad inputSchoolDad", pk, "FamilyName w3-input w3-border ");
					a("name", "familyName");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilyName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_familyName')); }, function() { addError($('#", classApiMethodMethod, "_familyName')); }); ");
				}
				a("value", strFamilyName())
			.fg();

		} else {
			e("span").a("class", "varSchoolDad", pk, "FamilyName ").f().sx(htmFamilyName()).g("span");
		}
	}

	public void htmFamilyName(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolDadFamilyName").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-blue ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-blue ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_familyName')); $('#", classApiMethodMethod, "_familyName').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolDadForm :input[name=pk]').val() }], 'setFamilyName', null, function() { addGlow($('#", classApiMethodMethod, "_familyName')); }, function() { addError($('#", classApiMethodMethod, "_familyName')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personCompleteName">Find the entity personCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personCompleteName(Wrap<String> c);

	public String getPersonCompleteName() {
		return personCompleteName;
	}
	public void setPersonCompleteName(String o) {
		this.personCompleteName = SchoolDad.staticSetPersonCompleteName(siteRequest_, o);
		this.personCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolDad personCompleteNameInit() {
		if(!personCompleteNameWrap.alreadyInitialized) {
			_personCompleteName(personCompleteNameWrap);
			if(personCompleteName == null)
				setPersonCompleteName(personCompleteNameWrap.o);
		}
		personCompleteNameWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static String staticSolrPersonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrPersonCompleteName(siteRequest_, SchoolDad.staticSolrPersonCompleteName(siteRequest_, SchoolDad.staticSetPersonCompleteName(siteRequest_, o)));
	}

	public String solrPersonCompleteName() {
		return SchoolDad.staticSolrPersonCompleteName(siteRequest_, personCompleteName);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personCompleteNamePreferred">Find the entity personCompleteNamePreferred in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personCompleteNamePreferred(Wrap<String> c);

	public String getPersonCompleteNamePreferred() {
		return personCompleteNamePreferred;
	}
	public void setPersonCompleteNamePreferred(String o) {
		this.personCompleteNamePreferred = SchoolDad.staticSetPersonCompleteNamePreferred(siteRequest_, o);
		this.personCompleteNamePreferredWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolDad personCompleteNamePreferredInit() {
		if(!personCompleteNamePreferredWrap.alreadyInitialized) {
			_personCompleteNamePreferred(personCompleteNamePreferredWrap);
			if(personCompleteNamePreferred == null)
				setPersonCompleteNamePreferred(personCompleteNamePreferredWrap.o);
		}
		personCompleteNamePreferredWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static String staticSolrPersonCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonCompleteNamePreferred(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrPersonCompleteNamePreferred(siteRequest_, SchoolDad.staticSolrPersonCompleteNamePreferred(siteRequest_, SchoolDad.staticSetPersonCompleteNamePreferred(siteRequest_, o)));
	}

	public String solrPersonCompleteNamePreferred() {
		return SchoolDad.staticSolrPersonCompleteNamePreferred(siteRequest_, personCompleteNamePreferred);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personFormalName">Find the entity personFormalName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personFormalName(Wrap<String> c);

	public String getPersonFormalName() {
		return personFormalName;
	}
	public void setPersonFormalName(String o) {
		this.personFormalName = SchoolDad.staticSetPersonFormalName(siteRequest_, o);
		this.personFormalNameWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonFormalName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolDad personFormalNameInit() {
		if(!personFormalNameWrap.alreadyInitialized) {
			_personFormalName(personFormalNameWrap);
			if(personFormalName == null)
				setPersonFormalName(personFormalNameWrap.o);
		}
		personFormalNameWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static String staticSolrPersonFormalName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonFormalName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonFormalName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrPersonFormalName(siteRequest_, SchoolDad.staticSolrPersonFormalName(siteRequest_, SchoolDad.staticSetPersonFormalName(siteRequest_, o)));
	}

	public String solrPersonFormalName() {
		return SchoolDad.staticSolrPersonFormalName(siteRequest_, personFormalName);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personOccupation">Find the entity personOccupation in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personOccupation(Wrap<String> c);

	public String getPersonOccupation() {
		return personOccupation;
	}
	public void setPersonOccupation(String o) {
		this.personOccupation = SchoolDad.staticSetPersonOccupation(siteRequest_, o);
		this.personOccupationWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonOccupation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolDad personOccupationInit() {
		if(!personOccupationWrap.alreadyInitialized) {
			_personOccupation(personOccupationWrap);
			if(personOccupation == null)
				setPersonOccupation(personOccupationWrap.o);
		}
		personOccupationWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static String staticSolrPersonOccupation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonOccupation(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonOccupation(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrPersonOccupation(siteRequest_, SchoolDad.staticSolrPersonOccupation(siteRequest_, SchoolDad.staticSetPersonOccupation(siteRequest_, o)));
	}

	public String solrPersonOccupation() {
		return SchoolDad.staticSolrPersonOccupation(siteRequest_, personOccupation);
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

	public void inputPersonOccupation(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "occupation")
				.a("id", classApiMethodMethod, "_personOccupation");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setPersonOccupation classSchoolDad inputSchoolDad", pk, "PersonOccupation w3-input w3-border ");
					a("name", "setPersonOccupation");
				} else {
					a("class", "valuePersonOccupation w3-input w3-border classSchoolDad inputSchoolDad", pk, "PersonOccupation w3-input w3-border ");
					a("name", "personOccupation");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonOccupation', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personOccupation')); }, function() { addError($('#", classApiMethodMethod, "_personOccupation')); }); ");
				}
				a("value", strPersonOccupation())
			.fg();

		} else {
			e("span").a("class", "varSchoolDad", pk, "PersonOccupation ").f().sx(htmPersonOccupation()).g("span");
		}
	}

	public void htmPersonOccupation(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolDadPersonOccupation").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-blue ").f();
							e("label").a("for", classApiMethodMethod, "_personOccupation").a("class", "").f().sx("occupation").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonOccupation(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-blue ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personOccupation')); $('#", classApiMethodMethod, "_personOccupation').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolDadForm :input[name=pk]').val() }], 'setPersonOccupation', null, function() { addGlow($('#", classApiMethodMethod, "_personOccupation')); }, function() { addError($('#", classApiMethodMethod, "_personOccupation')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personPhoneNumber">Find the entity personPhoneNumber in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personPhoneNumber(Wrap<String> c);

	public String getPersonPhoneNumber() {
		return personPhoneNumber;
	}
	public void setPersonPhoneNumber(String o) {
		this.personPhoneNumber = SchoolDad.staticSetPersonPhoneNumber(siteRequest_, o);
		this.personPhoneNumberWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolDad personPhoneNumberInit() {
		if(!personPhoneNumberWrap.alreadyInitialized) {
			_personPhoneNumber(personPhoneNumberWrap);
			if(personPhoneNumber == null)
				setPersonPhoneNumber(personPhoneNumberWrap.o);
		}
		personPhoneNumberWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static String staticSolrPersonPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonPhoneNumber(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrPersonPhoneNumber(siteRequest_, SchoolDad.staticSolrPersonPhoneNumber(siteRequest_, SchoolDad.staticSetPersonPhoneNumber(siteRequest_, o)));
	}

	public String solrPersonPhoneNumber() {
		return SchoolDad.staticSolrPersonPhoneNumber(siteRequest_, personPhoneNumber);
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
		SchoolDad s = (SchoolDad)this;
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
					a("class", "setPersonPhoneNumber classSchoolDad inputSchoolDad", pk, "PersonPhoneNumber w3-input w3-border ");
					a("name", "setPersonPhoneNumber");
				} else {
					a("class", "valuePersonPhoneNumber w3-input w3-border classSchoolDad inputSchoolDad", pk, "PersonPhoneNumber w3-input w3-border ");
					a("name", "personPhoneNumber");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonPhoneNumber', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personPhoneNumber')); }, function() { addError($('#", classApiMethodMethod, "_personPhoneNumber')); }); ");
				}
				a("value", strPersonPhoneNumber())
			.fg();

		} else {
			e("span").a("class", "varSchoolDad", pk, "PersonPhoneNumber ").f().sx(htmPersonPhoneNumber()).g("span");
		}
	}

	public void htmPersonPhoneNumber(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolDadPersonPhoneNumber").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-blue ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-blue ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personPhoneNumber')); $('#", classApiMethodMethod, "_personPhoneNumber').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolDadForm :input[name=pk]').val() }], 'setPersonPhoneNumber', null, function() { addGlow($('#", classApiMethodMethod, "_personPhoneNumber')); }, function() { addError($('#", classApiMethodMethod, "_personPhoneNumber')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personEmail">Find the entity personEmail in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personEmail(Wrap<String> c);

	public String getPersonEmail() {
		return personEmail;
	}
	public void setPersonEmail(String o) {
		this.personEmail = SchoolDad.staticSetPersonEmail(siteRequest_, o);
		this.personEmailWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonEmail(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolDad personEmailInit() {
		if(!personEmailWrap.alreadyInitialized) {
			_personEmail(personEmailWrap);
			if(personEmail == null)
				setPersonEmail(personEmailWrap.o);
		}
		personEmailWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static String staticSolrPersonEmail(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonEmail(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonEmail(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrPersonEmail(siteRequest_, SchoolDad.staticSolrPersonEmail(siteRequest_, SchoolDad.staticSetPersonEmail(siteRequest_, o)));
	}

	public String solrPersonEmail() {
		return SchoolDad.staticSolrPersonEmail(siteRequest_, personEmail);
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

	public void inputPersonEmail(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("input")
				.a("type", "text")
				.a("placeholder", "email")
				.a("id", classApiMethodMethod, "_personEmail");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setPersonEmail classSchoolDad inputSchoolDad", pk, "PersonEmail w3-input w3-border ");
					a("name", "setPersonEmail");
				} else {
					a("class", "valuePersonEmail w3-input w3-border classSchoolDad inputSchoolDad", pk, "PersonEmail w3-input w3-border ");
					a("name", "personEmail");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonEmail', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personEmail')); }, function() { addError($('#", classApiMethodMethod, "_personEmail')); }); ");
				}
				a("value", strPersonEmail())
			.fg();

		} else {
			e("span").a("class", "varSchoolDad", pk, "PersonEmail ").f().sx(htmPersonEmail()).g("span");
		}
	}

	public void htmPersonEmail(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolDadPersonEmail").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-blue ").f();
							e("label").a("for", classApiMethodMethod, "_personEmail").a("class", "").f().sx("email").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonEmail(classApiMethodMethod);
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-blue ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personEmail')); $('#", classApiMethodMethod, "_personEmail').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolDadForm :input[name=pk]').val() }], 'setPersonEmail', null, function() { addGlow($('#", classApiMethodMethod, "_personEmail')); }, function() { addError($('#", classApiMethodMethod, "_personEmail')); }); ")
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personRelation">Find the entity personRelation in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _personRelation(Wrap<String> c);

	public String getPersonRelation() {
		return personRelation;
	}
	public void setPersonRelation(String o) {
		this.personRelation = SchoolDad.staticSetPersonRelation(siteRequest_, o);
		this.personRelationWrap.alreadyInitialized = true;
	}
	public static String staticSetPersonRelation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolDad personRelationInit() {
		if(!personRelationWrap.alreadyInitialized) {
			_personRelation(personRelationWrap);
			if(personRelation == null)
				setPersonRelation(personRelationWrap.o);
		}
		personRelationWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static String staticSolrPersonRelation(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPersonRelation(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonRelation(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrPersonRelation(siteRequest_, SchoolDad.staticSolrPersonRelation(siteRequest_, SchoolDad.staticSetPersonRelation(siteRequest_, o)));
	}

	public String solrPersonRelation() {
		return SchoolDad.staticSolrPersonRelation(siteRequest_, personRelation);
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personSms">Find the entity personSms in Solr</a>
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
		this.personSms = SchoolDad.staticSetPersonSms(siteRequest_, o);
		this.personSmsWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPersonSms(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolDad personSmsInit() {
		if(!personSmsWrap.alreadyInitialized) {
			_personSms(personSmsWrap);
			if(personSms == null)
				setPersonSms(personSmsWrap.o);
		}
		personSmsWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static Boolean staticSolrPersonSms(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonSms(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonSms(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrPersonSms(siteRequest_, SchoolDad.staticSolrPersonSms(siteRequest_, SchoolDad.staticSetPersonSms(siteRequest_, o)));
	}

	public Boolean solrPersonSms() {
		return SchoolDad.staticSolrPersonSms(siteRequest_, personSms);
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

	public void inputPersonSms(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_personSms")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_personSms");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setPersonSms classSchoolDad inputSchoolDad", pk, "PersonSms w3-input w3-border ");
				a("name", "setPersonSms");
			} else {
				a("class", "valuePersonSms classSchoolDad inputSchoolDad", pk, "PersonSms w3-input w3-border ");
				a("name", "personSms");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonSms', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_personSms')); }, function() { addError($('#", classApiMethodMethod, "_personSms')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getPersonSms() != null && getPersonSms())
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
			e("span").a("class", "varSchoolDad", pk, "PersonSms ").f().sx(htmPersonSms()).g("span");
		}
	}

	public void htmPersonSms(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolDadPersonSms").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-blue ").f();
							e("label").a("for", classApiMethodMethod, "_personSms").a("class", "").f().sx("text me").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonSms(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personReceiveEmail">Find the entity personReceiveEmail in Solr</a>
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
		this.personReceiveEmail = SchoolDad.staticSetPersonReceiveEmail(siteRequest_, o);
		this.personReceiveEmailWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPersonReceiveEmail(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolDad personReceiveEmailInit() {
		if(!personReceiveEmailWrap.alreadyInitialized) {
			_personReceiveEmail(personReceiveEmailWrap);
			if(personReceiveEmail == null)
				setPersonReceiveEmail(personReceiveEmailWrap.o);
		}
		personReceiveEmailWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static Boolean staticSolrPersonReceiveEmail(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonReceiveEmail(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonReceiveEmail(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrPersonReceiveEmail(siteRequest_, SchoolDad.staticSolrPersonReceiveEmail(siteRequest_, SchoolDad.staticSetPersonReceiveEmail(siteRequest_, o)));
	}

	public Boolean solrPersonReceiveEmail() {
		return SchoolDad.staticSolrPersonReceiveEmail(siteRequest_, personReceiveEmail);
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

	public void inputPersonReceiveEmail(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			if("Page".equals(classApiMethodMethod)) {
				e("input")
					.a("type", "checkbox")
					.a("id", classApiMethodMethod, "_personReceiveEmail")
					.a("value", "true");
			} else {
				e("select")
					.a("id", classApiMethodMethod, "_personReceiveEmail");
			}
			if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
				a("class", "setPersonReceiveEmail classSchoolDad inputSchoolDad", pk, "PersonReceiveEmail w3-input w3-border ");
				a("name", "setPersonReceiveEmail");
			} else {
				a("class", "valuePersonReceiveEmail classSchoolDad inputSchoolDad", pk, "PersonReceiveEmail w3-input w3-border ");
				a("name", "personReceiveEmail");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonReceiveEmail', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_personReceiveEmail')); }, function() { addError($('#", classApiMethodMethod, "_personReceiveEmail')); }); ");
			}
			if("Page".equals(classApiMethodMethod)) {
				if(getPersonReceiveEmail() != null && getPersonReceiveEmail())
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
			e("span").a("class", "varSchoolDad", pk, "PersonReceiveEmail ").f().sx(htmPersonReceiveEmail()).g("span");
		}
	}

	public void htmPersonReceiveEmail(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolDadPersonReceiveEmail").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-blue ").f();
							e("label").a("for", classApiMethodMethod, "_personReceiveEmail").a("class", "").f().sx("receive email").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputPersonReceiveEmail(classApiMethodMethod);
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
		} g("div");
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personEmergencyContact">Find the entity personEmergencyContact in Solr</a>
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
		this.personEmergencyContact = SchoolDad.staticSetPersonEmergencyContact(siteRequest_, o);
		this.personEmergencyContactWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPersonEmergencyContact(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolDad personEmergencyContactInit() {
		if(!personEmergencyContactWrap.alreadyInitialized) {
			_personEmergencyContact(personEmergencyContactWrap);
			if(personEmergencyContact == null)
				setPersonEmergencyContact(personEmergencyContactWrap.o);
		}
		personEmergencyContactWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static Boolean staticSolrPersonEmergencyContact(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonEmergencyContact(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonEmergencyContact(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrPersonEmergencyContact(siteRequest_, SchoolDad.staticSolrPersonEmergencyContact(siteRequest_, SchoolDad.staticSetPersonEmergencyContact(siteRequest_, o)));
	}

	public Boolean solrPersonEmergencyContact() {
		return SchoolDad.staticSolrPersonEmergencyContact(siteRequest_, personEmergencyContact);
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
		SchoolDad s = (SchoolDad)this;
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
				a("class", "setPersonEmergencyContact classSchoolDad inputSchoolDad", pk, "PersonEmergencyContact w3-input w3-border ");
				a("name", "setPersonEmergencyContact");
			} else {
				a("class", "valuePersonEmergencyContact classSchoolDad inputSchoolDad", pk, "PersonEmergencyContact w3-input w3-border ");
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
			e("span").a("class", "varSchoolDad", pk, "PersonEmergencyContact ").f().sx(htmPersonEmergencyContact()).g("span");
		}
	}

	public void htmPersonEmergencyContact(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolDadPersonEmergencyContact").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-blue ").f();
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personPickup">Find the entity personPickup in Solr</a>
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
		this.personPickup = SchoolDad.staticSetPersonPickup(siteRequest_, o);
		this.personPickupWrap.alreadyInitialized = true;
	}
	public static Boolean staticSetPersonPickup(SiteRequestEnUS siteRequest_, String o) {
		return Boolean.parseBoolean(o);
	}
	protected SchoolDad personPickupInit() {
		if(!personPickupWrap.alreadyInitialized) {
			_personPickup(personPickupWrap);
			if(personPickup == null)
				setPersonPickup(personPickupWrap.o);
		}
		personPickupWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static Boolean staticSolrPersonPickup(SiteRequestEnUS siteRequest_, Boolean o) {
		return o;
	}

	public static String staticSolrStrPersonPickup(SiteRequestEnUS siteRequest_, Boolean o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPersonPickup(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrPersonPickup(siteRequest_, SchoolDad.staticSolrPersonPickup(siteRequest_, SchoolDad.staticSetPersonPickup(siteRequest_, o)));
	}

	public Boolean solrPersonPickup() {
		return SchoolDad.staticSolrPersonPickup(siteRequest_, personPickup);
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
		SchoolDad s = (SchoolDad)this;
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
				a("class", "setPersonPickup classSchoolDad inputSchoolDad", pk, "PersonPickup w3-input w3-border ");
				a("name", "setPersonPickup");
			} else {
				a("class", "valuePersonPickup classSchoolDad inputSchoolDad", pk, "PersonPickup w3-input w3-border ");
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
			e("span").a("class", "varSchoolDad", pk, "PersonPickup ").f().sx(htmPersonPickup()).g("span");
		}
	}

	public void htmPersonPickup(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolDadPersonPickup").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-blue ").f();
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
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:photo">Find the entity photo in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _photo(Wrap<String> c);

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String o) {
		this.photo = SchoolDad.staticSetPhoto(siteRequest_, o);
		this.photoWrap.alreadyInitialized = true;
	}
	public static String staticSetPhoto(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolDad photoInit() {
		if(!photoWrap.alreadyInitialized) {
			_photo(photoWrap);
			if(photo == null)
				setPhoto(photoWrap.o);
		}
		photoWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static String staticSolrPhoto(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrPhoto(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqPhoto(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrPhoto(siteRequest_, SchoolDad.staticSolrPhoto(siteRequest_, SchoolDad.staticSetPhoto(siteRequest_, o)));
	}

	public String solrPhoto() {
		return SchoolDad.staticSolrPhoto(siteRequest_, photo);
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
		SchoolDad s = (SchoolDad)this;
		if(
				userKeys.contains(siteRequest_.getUserKey())
				|| Objects.equals(sessionId, siteRequest_.getSessionId())
				|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
				|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
		) {
			e("div").a("class", "imageBase64Div1SchoolDad_photo").a("id", "imageBase64Div1SchoolDad", pk, "photo").f();
				e("h5").f().sx("Upload image").g("h5"); 
				e("form").a("method", "POST").a("enctype", "multipart/form-data").a("action", "/photo").a("class", "").f();
				e("input").a("type", "hidden").a("name", "pk").a("value", pk).fg(); 
				e("input").a("type", "hidden").a("name", "classSimpleName").a("value", "SchoolDad").fg(); 
				e("input").a("name", "file").a("type", "file").a("onchange", "$.ajax({ type: 'POST', enctype: 'multipart/form-data', url: '/photo', data: new FormData(this.form), processData: false, contentType: false}); ").fg(); 
				g("form");
				e("img").a("id", "imageBase64ImgSchoolDad", pk, "photo");
					a("class", "imgSchoolDad", pk, "Photo w3-image ");
					a("src", StringUtils.isBlank(photo) ? "data:image/png;base64," : photo).a("alt", "");
				fg();
			g("div");
		} else {
			e("span").a("class", "varSchoolDad", pk, "Photo ").f().sx(htmPhoto()).g("span");
		}
	}

	public void htmPhoto(String classApiMethodMethod) {
		SchoolDad s = (SchoolDad)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolDadPhoto").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-light-blue ").f();
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
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-light-blue ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_photo')); $('#", classApiMethodMethod, "_photo').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolDadForm :input[name=pk]').val() }], 'setPhoto', null, function() { addGlow($('#", classApiMethodMethod, "_photo')); }, function() { addError($('#", classApiMethodMethod, "_photo')); }); ")
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

	/////////////////////
	// dadCompleteName //
	/////////////////////

	/**	 The entity dadCompleteName
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String dadCompleteName;
	@JsonIgnore
	public Wrap<String> dadCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("dadCompleteName").o(dadCompleteName);

	/**	<br/> The entity dadCompleteName
	 *  is defined as null before being initialized. 
	 * <br/><a href="http://localhost:8983/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dadCompleteName">Find the entity dadCompleteName in Solr</a>
	 * <br/>
	 * @param c is for wrapping a value to assign to this entity during initialization. 
	 **/
	protected abstract void _dadCompleteName(Wrap<String> c);

	public String getDadCompleteName() {
		return dadCompleteName;
	}
	public void setDadCompleteName(String o) {
		this.dadCompleteName = SchoolDad.staticSetDadCompleteName(siteRequest_, o);
		this.dadCompleteNameWrap.alreadyInitialized = true;
	}
	public static String staticSetDadCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}
	protected SchoolDad dadCompleteNameInit() {
		if(!dadCompleteNameWrap.alreadyInitialized) {
			_dadCompleteName(dadCompleteNameWrap);
			if(dadCompleteName == null)
				setDadCompleteName(dadCompleteNameWrap.o);
		}
		dadCompleteNameWrap.alreadyInitialized(true);
		return (SchoolDad)this;
	}

	public static String staticSolrDadCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o;
	}

	public static String staticSolrStrDadCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return o == null ? null : o.toString();
	}

	public static String staticSolrFqDadCompleteName(SiteRequestEnUS siteRequest_, String o) {
		return SchoolDad.staticSolrStrDadCompleteName(siteRequest_, SchoolDad.staticSolrDadCompleteName(siteRequest_, SchoolDad.staticSetDadCompleteName(siteRequest_, o)));
	}

	public String solrDadCompleteName() {
		return SchoolDad.staticSolrDadCompleteName(siteRequest_, dadCompleteName);
	}

	public String strDadCompleteName() {
		return dadCompleteName == null ? "" : dadCompleteName;
	}

	public String jsonDadCompleteName() {
		return dadCompleteName == null ? "" : dadCompleteName;
	}

	public String nomAffichageDadCompleteName() {
		return "name";
	}

	public String htmTooltipDadCompleteName() {
		return null;
	}

	public String htmDadCompleteName() {
		return dadCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strDadCompleteName());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSchoolDad = false;

	public SchoolDad initDeepSchoolDad(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSchoolDad) {
			alreadyInitializedSchoolDad = true;
			initDeepSchoolDad();
		}
		return (SchoolDad)this;
	}

	public void initDeepSchoolDad() {
		initSchoolDad();
		super.initDeepCluster(siteRequest_);
	}

	public void initSchoolDad() {
		dadKeyInit();
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
		dadCompleteNameInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSchoolDad(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSchoolDad(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
		if(enrollmentSearch != null)
			enrollmentSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSchoolDad(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSchoolDad(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSchoolDad(String var) {
		SchoolDad oSchoolDad = (SchoolDad)this;
		switch(var) {
			case "dadKey":
				return oSchoolDad.dadKey;
			case "enrollmentKeys":
				return oSchoolDad.enrollmentKeys;
			case "familySort":
				return oSchoolDad.familySort;
			case "schoolSort":
				return oSchoolDad.schoolSort;
			case "enrollmentSearch":
				return oSchoolDad.enrollmentSearch;
			case "enrollments":
				return oSchoolDad.enrollments;
			case "userKeys":
				return oSchoolDad.userKeys;
			case "schoolKeys":
				return oSchoolDad.schoolKeys;
			case "yearKeys":
				return oSchoolDad.yearKeys;
			case "seasonKeys":
				return oSchoolDad.seasonKeys;
			case "sessionKeys":
				return oSchoolDad.sessionKeys;
			case "ageKeys":
				return oSchoolDad.ageKeys;
			case "personFirstName":
				return oSchoolDad.personFirstName;
			case "personFirstNamePreferred":
				return oSchoolDad.personFirstNamePreferred;
			case "familyName":
				return oSchoolDad.familyName;
			case "personCompleteName":
				return oSchoolDad.personCompleteName;
			case "personCompleteNamePreferred":
				return oSchoolDad.personCompleteNamePreferred;
			case "personFormalName":
				return oSchoolDad.personFormalName;
			case "personOccupation":
				return oSchoolDad.personOccupation;
			case "personPhoneNumber":
				return oSchoolDad.personPhoneNumber;
			case "personEmail":
				return oSchoolDad.personEmail;
			case "personRelation":
				return oSchoolDad.personRelation;
			case "personSms":
				return oSchoolDad.personSms;
			case "personReceiveEmail":
				return oSchoolDad.personReceiveEmail;
			case "personEmergencyContact":
				return oSchoolDad.personEmergencyContact;
			case "personPickup":
				return oSchoolDad.personPickup;
			case "photo":
				return oSchoolDad.photo;
			case "dadCompleteName":
				return oSchoolDad.dadCompleteName;
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
				o = attributeSchoolDad(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSchoolDad(String var, Object val) {
		SchoolDad oSchoolDad = (SchoolDad)this;
		switch(var) {
			case "enrollmentKeys":
				oSchoolDad.addEnrollmentKeys((Long)val);
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
		return staticSetSchoolDad(entityVar,  siteRequest_, o);
	}
	public static Object staticSetSchoolDad(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "dadKey":
			return SchoolDad.staticSetDadKey(siteRequest_, o);
		case "enrollmentKeys":
			return SchoolDad.staticSetEnrollmentKeys(siteRequest_, o);
		case "familySort":
			return SchoolDad.staticSetFamilySort(siteRequest_, o);
		case "schoolSort":
			return SchoolDad.staticSetSchoolSort(siteRequest_, o);
		case "userKeys":
			return SchoolDad.staticSetUserKeys(siteRequest_, o);
		case "schoolKeys":
			return SchoolDad.staticSetSchoolKeys(siteRequest_, o);
		case "yearKeys":
			return SchoolDad.staticSetYearKeys(siteRequest_, o);
		case "seasonKeys":
			return SchoolDad.staticSetSeasonKeys(siteRequest_, o);
		case "sessionKeys":
			return SchoolDad.staticSetSessionKeys(siteRequest_, o);
		case "ageKeys":
			return SchoolDad.staticSetAgeKeys(siteRequest_, o);
		case "personFirstName":
			return SchoolDad.staticSetPersonFirstName(siteRequest_, o);
		case "personFirstNamePreferred":
			return SchoolDad.staticSetPersonFirstNamePreferred(siteRequest_, o);
		case "familyName":
			return SchoolDad.staticSetFamilyName(siteRequest_, o);
		case "personCompleteName":
			return SchoolDad.staticSetPersonCompleteName(siteRequest_, o);
		case "personCompleteNamePreferred":
			return SchoolDad.staticSetPersonCompleteNamePreferred(siteRequest_, o);
		case "personFormalName":
			return SchoolDad.staticSetPersonFormalName(siteRequest_, o);
		case "personOccupation":
			return SchoolDad.staticSetPersonOccupation(siteRequest_, o);
		case "personPhoneNumber":
			return SchoolDad.staticSetPersonPhoneNumber(siteRequest_, o);
		case "personEmail":
			return SchoolDad.staticSetPersonEmail(siteRequest_, o);
		case "personRelation":
			return SchoolDad.staticSetPersonRelation(siteRequest_, o);
		case "personSms":
			return SchoolDad.staticSetPersonSms(siteRequest_, o);
		case "personReceiveEmail":
			return SchoolDad.staticSetPersonReceiveEmail(siteRequest_, o);
		case "personEmergencyContact":
			return SchoolDad.staticSetPersonEmergencyContact(siteRequest_, o);
		case "personPickup":
			return SchoolDad.staticSetPersonPickup(siteRequest_, o);
		case "photo":
			return SchoolDad.staticSetPhoto(siteRequest_, o);
		case "dadCompleteName":
			return SchoolDad.staticSetDadCompleteName(siteRequest_, o);
			default:
				return Cluster.staticSetCluster(entityVar,  siteRequest_, o);
		}
	}

	////////////////
	// staticSolr //
	////////////////

	public static Object staticSolrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrSchoolDad(entityVar,  siteRequest_, o);
	}
	public static Object staticSolrSchoolDad(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "dadKey":
			return SchoolDad.staticSolrDadKey(siteRequest_, (Long)o);
		case "enrollmentKeys":
			return SchoolDad.staticSolrEnrollmentKeys(siteRequest_, (Long)o);
		case "familySort":
			return SchoolDad.staticSolrFamilySort(siteRequest_, (Integer)o);
		case "schoolSort":
			return SchoolDad.staticSolrSchoolSort(siteRequest_, (Integer)o);
		case "userKeys":
			return SchoolDad.staticSolrUserKeys(siteRequest_, (Long)o);
		case "schoolKeys":
			return SchoolDad.staticSolrSchoolKeys(siteRequest_, (Long)o);
		case "yearKeys":
			return SchoolDad.staticSolrYearKeys(siteRequest_, (Long)o);
		case "seasonKeys":
			return SchoolDad.staticSolrSeasonKeys(siteRequest_, (Long)o);
		case "sessionKeys":
			return SchoolDad.staticSolrSessionKeys(siteRequest_, (Long)o);
		case "ageKeys":
			return SchoolDad.staticSolrAgeKeys(siteRequest_, (Long)o);
		case "personFirstName":
			return SchoolDad.staticSolrPersonFirstName(siteRequest_, (String)o);
		case "personFirstNamePreferred":
			return SchoolDad.staticSolrPersonFirstNamePreferred(siteRequest_, (String)o);
		case "familyName":
			return SchoolDad.staticSolrFamilyName(siteRequest_, (String)o);
		case "personCompleteName":
			return SchoolDad.staticSolrPersonCompleteName(siteRequest_, (String)o);
		case "personCompleteNamePreferred":
			return SchoolDad.staticSolrPersonCompleteNamePreferred(siteRequest_, (String)o);
		case "personFormalName":
			return SchoolDad.staticSolrPersonFormalName(siteRequest_, (String)o);
		case "personOccupation":
			return SchoolDad.staticSolrPersonOccupation(siteRequest_, (String)o);
		case "personPhoneNumber":
			return SchoolDad.staticSolrPersonPhoneNumber(siteRequest_, (String)o);
		case "personEmail":
			return SchoolDad.staticSolrPersonEmail(siteRequest_, (String)o);
		case "personRelation":
			return SchoolDad.staticSolrPersonRelation(siteRequest_, (String)o);
		case "personSms":
			return SchoolDad.staticSolrPersonSms(siteRequest_, (Boolean)o);
		case "personReceiveEmail":
			return SchoolDad.staticSolrPersonReceiveEmail(siteRequest_, (Boolean)o);
		case "personEmergencyContact":
			return SchoolDad.staticSolrPersonEmergencyContact(siteRequest_, (Boolean)o);
		case "personPickup":
			return SchoolDad.staticSolrPersonPickup(siteRequest_, (Boolean)o);
		case "photo":
			return SchoolDad.staticSolrPhoto(siteRequest_, (String)o);
		case "dadCompleteName":
			return SchoolDad.staticSolrDadCompleteName(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrCluster(entityVar,  siteRequest_, o);
		}
	}

	///////////////////
	// staticSolrStr //
	///////////////////

	public static String staticSolrStrForClass(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		return staticSolrStrSchoolDad(entityVar,  siteRequest_, o);
	}
	public static String staticSolrStrSchoolDad(String entityVar, SiteRequestEnUS siteRequest_, Object o) {
		switch(entityVar) {
		case "dadKey":
			return SchoolDad.staticSolrStrDadKey(siteRequest_, (Long)o);
		case "enrollmentKeys":
			return SchoolDad.staticSolrStrEnrollmentKeys(siteRequest_, (Long)o);
		case "familySort":
			return SchoolDad.staticSolrStrFamilySort(siteRequest_, (Integer)o);
		case "schoolSort":
			return SchoolDad.staticSolrStrSchoolSort(siteRequest_, (Integer)o);
		case "userKeys":
			return SchoolDad.staticSolrStrUserKeys(siteRequest_, (Long)o);
		case "schoolKeys":
			return SchoolDad.staticSolrStrSchoolKeys(siteRequest_, (Long)o);
		case "yearKeys":
			return SchoolDad.staticSolrStrYearKeys(siteRequest_, (Long)o);
		case "seasonKeys":
			return SchoolDad.staticSolrStrSeasonKeys(siteRequest_, (Long)o);
		case "sessionKeys":
			return SchoolDad.staticSolrStrSessionKeys(siteRequest_, (Long)o);
		case "ageKeys":
			return SchoolDad.staticSolrStrAgeKeys(siteRequest_, (Long)o);
		case "personFirstName":
			return SchoolDad.staticSolrStrPersonFirstName(siteRequest_, (String)o);
		case "personFirstNamePreferred":
			return SchoolDad.staticSolrStrPersonFirstNamePreferred(siteRequest_, (String)o);
		case "familyName":
			return SchoolDad.staticSolrStrFamilyName(siteRequest_, (String)o);
		case "personCompleteName":
			return SchoolDad.staticSolrStrPersonCompleteName(siteRequest_, (String)o);
		case "personCompleteNamePreferred":
			return SchoolDad.staticSolrStrPersonCompleteNamePreferred(siteRequest_, (String)o);
		case "personFormalName":
			return SchoolDad.staticSolrStrPersonFormalName(siteRequest_, (String)o);
		case "personOccupation":
			return SchoolDad.staticSolrStrPersonOccupation(siteRequest_, (String)o);
		case "personPhoneNumber":
			return SchoolDad.staticSolrStrPersonPhoneNumber(siteRequest_, (String)o);
		case "personEmail":
			return SchoolDad.staticSolrStrPersonEmail(siteRequest_, (String)o);
		case "personRelation":
			return SchoolDad.staticSolrStrPersonRelation(siteRequest_, (String)o);
		case "personSms":
			return SchoolDad.staticSolrStrPersonSms(siteRequest_, (Boolean)o);
		case "personReceiveEmail":
			return SchoolDad.staticSolrStrPersonReceiveEmail(siteRequest_, (Boolean)o);
		case "personEmergencyContact":
			return SchoolDad.staticSolrStrPersonEmergencyContact(siteRequest_, (Boolean)o);
		case "personPickup":
			return SchoolDad.staticSolrStrPersonPickup(siteRequest_, (Boolean)o);
		case "photo":
			return SchoolDad.staticSolrStrPhoto(siteRequest_, (String)o);
		case "dadCompleteName":
			return SchoolDad.staticSolrStrDadCompleteName(siteRequest_, (String)o);
			default:
				return Cluster.staticSolrStrCluster(entityVar,  siteRequest_, o);
		}
	}

	//////////////////
	// staticSolrFq //
	//////////////////

	public static String staticSolrFqForClass(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		return staticSolrFqSchoolDad(entityVar,  siteRequest_, o);
	}
	public static String staticSolrFqSchoolDad(String entityVar, SiteRequestEnUS siteRequest_, String o) {
		switch(entityVar) {
		case "dadKey":
			return SchoolDad.staticSolrFqDadKey(siteRequest_, o);
		case "enrollmentKeys":
			return SchoolDad.staticSolrFqEnrollmentKeys(siteRequest_, o);
		case "familySort":
			return SchoolDad.staticSolrFqFamilySort(siteRequest_, o);
		case "schoolSort":
			return SchoolDad.staticSolrFqSchoolSort(siteRequest_, o);
		case "userKeys":
			return SchoolDad.staticSolrFqUserKeys(siteRequest_, o);
		case "schoolKeys":
			return SchoolDad.staticSolrFqSchoolKeys(siteRequest_, o);
		case "yearKeys":
			return SchoolDad.staticSolrFqYearKeys(siteRequest_, o);
		case "seasonKeys":
			return SchoolDad.staticSolrFqSeasonKeys(siteRequest_, o);
		case "sessionKeys":
			return SchoolDad.staticSolrFqSessionKeys(siteRequest_, o);
		case "ageKeys":
			return SchoolDad.staticSolrFqAgeKeys(siteRequest_, o);
		case "personFirstName":
			return SchoolDad.staticSolrFqPersonFirstName(siteRequest_, o);
		case "personFirstNamePreferred":
			return SchoolDad.staticSolrFqPersonFirstNamePreferred(siteRequest_, o);
		case "familyName":
			return SchoolDad.staticSolrFqFamilyName(siteRequest_, o);
		case "personCompleteName":
			return SchoolDad.staticSolrFqPersonCompleteName(siteRequest_, o);
		case "personCompleteNamePreferred":
			return SchoolDad.staticSolrFqPersonCompleteNamePreferred(siteRequest_, o);
		case "personFormalName":
			return SchoolDad.staticSolrFqPersonFormalName(siteRequest_, o);
		case "personOccupation":
			return SchoolDad.staticSolrFqPersonOccupation(siteRequest_, o);
		case "personPhoneNumber":
			return SchoolDad.staticSolrFqPersonPhoneNumber(siteRequest_, o);
		case "personEmail":
			return SchoolDad.staticSolrFqPersonEmail(siteRequest_, o);
		case "personRelation":
			return SchoolDad.staticSolrFqPersonRelation(siteRequest_, o);
		case "personSms":
			return SchoolDad.staticSolrFqPersonSms(siteRequest_, o);
		case "personReceiveEmail":
			return SchoolDad.staticSolrFqPersonReceiveEmail(siteRequest_, o);
		case "personEmergencyContact":
			return SchoolDad.staticSolrFqPersonEmergencyContact(siteRequest_, o);
		case "personPickup":
			return SchoolDad.staticSolrFqPersonPickup(siteRequest_, o);
		case "photo":
			return SchoolDad.staticSolrFqPhoto(siteRequest_, o);
		case "dadCompleteName":
			return SchoolDad.staticSolrFqDadCompleteName(siteRequest_, o);
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
					o = defineSchoolDad(v, val);
				else if(o instanceof Cluster) {
					Cluster oCluster = (Cluster)o;
					o = oCluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolDad(String var, String val) {
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
			case "personOccupation":
				if(val != null)
					setPersonOccupation(val);
				saves.add(var);
				return val;
			case "personPhoneNumber":
				if(val != null)
					setPersonPhoneNumber(val);
				saves.add(var);
				return val;
			case "personEmail":
				if(val != null)
					setPersonEmail(val);
				saves.add(var);
				return val;
			case "personSms":
				if(val != null)
					setPersonSms(val);
				saves.add(var);
				return val;
			case "personReceiveEmail":
				if(val != null)
					setPersonReceiveEmail(val);
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
		populateSchoolDad(solrDocument);
	}
	public void populateSchoolDad(SolrDocument solrDocument) {
		SchoolDad oSchoolDad = (SchoolDad)this;
		saves = (List<String>)solrDocument.get("saves_stored_strings");
		if(saves != null) {

			if(saves.contains("dadKey")) {
				Long dadKey = (Long)solrDocument.get("dadKey_stored_long");
				if(dadKey != null)
					oSchoolDad.setDadKey(dadKey);
			}

			List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
			if(enrollmentKeys != null)
				oSchoolDad.enrollmentKeys.addAll(enrollmentKeys);

			if(saves.contains("familySort")) {
				Integer familySort = (Integer)solrDocument.get("familySort_stored_int");
				if(familySort != null)
					oSchoolDad.setFamilySort(familySort);
			}

			if(saves.contains("schoolSort")) {
				Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
				if(schoolSort != null)
					oSchoolDad.setSchoolSort(schoolSort);
			}

			if(saves.contains("userKeys")) {
				List<Long> userKeys = (List<Long>)solrDocument.get("userKeys_stored_longs");
				if(userKeys != null)
					oSchoolDad.userKeys.addAll(userKeys);
			}

			if(saves.contains("schoolKeys")) {
				List<Long> schoolKeys = (List<Long>)solrDocument.get("schoolKeys_stored_longs");
				if(schoolKeys != null)
					oSchoolDad.schoolKeys.addAll(schoolKeys);
			}

			if(saves.contains("yearKeys")) {
				List<Long> yearKeys = (List<Long>)solrDocument.get("yearKeys_stored_longs");
				if(yearKeys != null)
					oSchoolDad.yearKeys.addAll(yearKeys);
			}

			if(saves.contains("seasonKeys")) {
				List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
				if(seasonKeys != null)
					oSchoolDad.seasonKeys.addAll(seasonKeys);
			}

			if(saves.contains("sessionKeys")) {
				List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
				if(sessionKeys != null)
					oSchoolDad.sessionKeys.addAll(sessionKeys);
			}

			if(saves.contains("ageKeys")) {
				List<Long> ageKeys = (List<Long>)solrDocument.get("ageKeys_stored_longs");
				if(ageKeys != null)
					oSchoolDad.ageKeys.addAll(ageKeys);
			}

			if(saves.contains("personFirstName")) {
				String personFirstName = (String)solrDocument.get("personFirstName_stored_string");
				if(personFirstName != null)
					oSchoolDad.setPersonFirstName(personFirstName);
			}

			if(saves.contains("personFirstNamePreferred")) {
				String personFirstNamePreferred = (String)solrDocument.get("personFirstNamePreferred_stored_string");
				if(personFirstNamePreferred != null)
					oSchoolDad.setPersonFirstNamePreferred(personFirstNamePreferred);
			}

			if(saves.contains("familyName")) {
				String familyName = (String)solrDocument.get("familyName_stored_string");
				if(familyName != null)
					oSchoolDad.setFamilyName(familyName);
			}

			if(saves.contains("personCompleteName")) {
				String personCompleteName = (String)solrDocument.get("personCompleteName_stored_string");
				if(personCompleteName != null)
					oSchoolDad.setPersonCompleteName(personCompleteName);
			}

			if(saves.contains("personCompleteNamePreferred")) {
				String personCompleteNamePreferred = (String)solrDocument.get("personCompleteNamePreferred_stored_string");
				if(personCompleteNamePreferred != null)
					oSchoolDad.setPersonCompleteNamePreferred(personCompleteNamePreferred);
			}

			if(saves.contains("personFormalName")) {
				String personFormalName = (String)solrDocument.get("personFormalName_stored_string");
				if(personFormalName != null)
					oSchoolDad.setPersonFormalName(personFormalName);
			}

			if(saves.contains("personOccupation")) {
				String personOccupation = (String)solrDocument.get("personOccupation_stored_string");
				if(personOccupation != null)
					oSchoolDad.setPersonOccupation(personOccupation);
			}

			if(saves.contains("personPhoneNumber")) {
				String personPhoneNumber = (String)solrDocument.get("personPhoneNumber_stored_string");
				if(personPhoneNumber != null)
					oSchoolDad.setPersonPhoneNumber(personPhoneNumber);
			}

			if(saves.contains("personEmail")) {
				String personEmail = (String)solrDocument.get("personEmail_stored_string");
				if(personEmail != null)
					oSchoolDad.setPersonEmail(personEmail);
			}

			if(saves.contains("personRelation")) {
				String personRelation = (String)solrDocument.get("personRelation_stored_string");
				if(personRelation != null)
					oSchoolDad.setPersonRelation(personRelation);
			}

			if(saves.contains("personSms")) {
				Boolean personSms = (Boolean)solrDocument.get("personSms_stored_boolean");
				if(personSms != null)
					oSchoolDad.setPersonSms(personSms);
			}

			if(saves.contains("personReceiveEmail")) {
				Boolean personReceiveEmail = (Boolean)solrDocument.get("personReceiveEmail_stored_boolean");
				if(personReceiveEmail != null)
					oSchoolDad.setPersonReceiveEmail(personReceiveEmail);
			}

			if(saves.contains("personEmergencyContact")) {
				Boolean personEmergencyContact = (Boolean)solrDocument.get("personEmergencyContact_stored_boolean");
				if(personEmergencyContact != null)
					oSchoolDad.setPersonEmergencyContact(personEmergencyContact);
			}

			if(saves.contains("personPickup")) {
				Boolean personPickup = (Boolean)solrDocument.get("personPickup_stored_boolean");
				if(personPickup != null)
					oSchoolDad.setPersonPickup(personPickup);
			}

			if(saves.contains("photo")) {
				String photo = (String)solrDocument.get("photo_stored_string");
				if(photo != null)
					oSchoolDad.setPhoto(photo);
			}

			if(saves.contains("dadCompleteName")) {
				String dadCompleteName = (String)solrDocument.get("dadCompleteName_stored_string");
				if(dadCompleteName != null)
					oSchoolDad.setDadCompleteName(dadCompleteName);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.dad.SchoolDad"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			SchoolDad o = new SchoolDad();
			o.siteRequestSchoolDad(siteRequest);
			o.initDeepSchoolDad(siteRequest);
			o.indexSchoolDad();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexSchoolDad();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexSchoolDad(document);
	}

	public void indexSchoolDad(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolDad(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolDad() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolDad(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolDad(SolrInputDocument document) {
		if(dadKey != null) {
			document.addField("dadKey_indexed_long", dadKey);
			document.addField("dadKey_stored_long", dadKey);
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
		if(dadCompleteName != null) {
			document.addField("dadCompleteName_indexed_string", dadCompleteName);
			document.addField("dadCompleteName_stored_string", dadCompleteName);
		}
		super.indexCluster(document);

	}

	public void unindexSchoolDad() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepSchoolDad(siteRequest);
			SolrClient solrClient = siteContext.getSolrClient();
			solrClient.deleteById(id.toString());
			solrClient.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexedSchoolDad(String entityVar) {
		switch(entityVar) {
			case "dadKey":
				return "dadKey_indexed_long";
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
			case "dadCompleteName":
				return "dadCompleteName_indexed_string";
			default:
				return Cluster.varIndexedCluster(entityVar);
		}
	}

	public static String varSearchSchoolDad(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSearchCluster(entityVar);
		}
	}

	public static String varSuggestedSchoolDad(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSuggestedCluster(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrDocument solrDocument) {
		storeSchoolDad(solrDocument);
	}
	public void storeSchoolDad(SolrDocument solrDocument) {
		SchoolDad oSchoolDad = (SchoolDad)this;

		Long dadKey = (Long)solrDocument.get("dadKey_stored_long");
		if(dadKey != null)
			oSchoolDad.setDadKey(dadKey);

		List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
		if(enrollmentKeys != null)
			oSchoolDad.enrollmentKeys.addAll(enrollmentKeys);

		Integer familySort = (Integer)solrDocument.get("familySort_stored_int");
		if(familySort != null)
			oSchoolDad.setFamilySort(familySort);

		Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
		if(schoolSort != null)
			oSchoolDad.setSchoolSort(schoolSort);

		List<Long> userKeys = (List<Long>)solrDocument.get("userKeys_stored_longs");
		if(userKeys != null)
			oSchoolDad.userKeys.addAll(userKeys);

		List<Long> schoolKeys = (List<Long>)solrDocument.get("schoolKeys_stored_longs");
		if(schoolKeys != null)
			oSchoolDad.schoolKeys.addAll(schoolKeys);

		List<Long> yearKeys = (List<Long>)solrDocument.get("yearKeys_stored_longs");
		if(yearKeys != null)
			oSchoolDad.yearKeys.addAll(yearKeys);

		List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
		if(seasonKeys != null)
			oSchoolDad.seasonKeys.addAll(seasonKeys);

		List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
		if(sessionKeys != null)
			oSchoolDad.sessionKeys.addAll(sessionKeys);

		List<Long> ageKeys = (List<Long>)solrDocument.get("ageKeys_stored_longs");
		if(ageKeys != null)
			oSchoolDad.ageKeys.addAll(ageKeys);

		String personFirstName = (String)solrDocument.get("personFirstName_stored_string");
		if(personFirstName != null)
			oSchoolDad.setPersonFirstName(personFirstName);

		String personFirstNamePreferred = (String)solrDocument.get("personFirstNamePreferred_stored_string");
		if(personFirstNamePreferred != null)
			oSchoolDad.setPersonFirstNamePreferred(personFirstNamePreferred);

		String familyName = (String)solrDocument.get("familyName_stored_string");
		if(familyName != null)
			oSchoolDad.setFamilyName(familyName);

		String personCompleteName = (String)solrDocument.get("personCompleteName_stored_string");
		if(personCompleteName != null)
			oSchoolDad.setPersonCompleteName(personCompleteName);

		String personCompleteNamePreferred = (String)solrDocument.get("personCompleteNamePreferred_stored_string");
		if(personCompleteNamePreferred != null)
			oSchoolDad.setPersonCompleteNamePreferred(personCompleteNamePreferred);

		String personFormalName = (String)solrDocument.get("personFormalName_stored_string");
		if(personFormalName != null)
			oSchoolDad.setPersonFormalName(personFormalName);

		String personOccupation = (String)solrDocument.get("personOccupation_stored_string");
		if(personOccupation != null)
			oSchoolDad.setPersonOccupation(personOccupation);

		String personPhoneNumber = (String)solrDocument.get("personPhoneNumber_stored_string");
		if(personPhoneNumber != null)
			oSchoolDad.setPersonPhoneNumber(personPhoneNumber);

		String personEmail = (String)solrDocument.get("personEmail_stored_string");
		if(personEmail != null)
			oSchoolDad.setPersonEmail(personEmail);

		String personRelation = (String)solrDocument.get("personRelation_stored_string");
		if(personRelation != null)
			oSchoolDad.setPersonRelation(personRelation);

		Boolean personSms = (Boolean)solrDocument.get("personSms_stored_boolean");
		if(personSms != null)
			oSchoolDad.setPersonSms(personSms);

		Boolean personReceiveEmail = (Boolean)solrDocument.get("personReceiveEmail_stored_boolean");
		if(personReceiveEmail != null)
			oSchoolDad.setPersonReceiveEmail(personReceiveEmail);

		Boolean personEmergencyContact = (Boolean)solrDocument.get("personEmergencyContact_stored_boolean");
		if(personEmergencyContact != null)
			oSchoolDad.setPersonEmergencyContact(personEmergencyContact);

		Boolean personPickup = (Boolean)solrDocument.get("personPickup_stored_boolean");
		if(personPickup != null)
			oSchoolDad.setPersonPickup(personPickup);

		String photo = (String)solrDocument.get("photo_stored_string");
		if(photo != null)
			oSchoolDad.setPhoto(photo);

		String dadCompleteName = (String)solrDocument.get("dadCompleteName_stored_string");
		if(dadCompleteName != null)
			oSchoolDad.setDadCompleteName(dadCompleteName);

		super.storeCluster(solrDocument);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSchoolDad() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SchoolDad) {
			SchoolDad original = (SchoolDad)o;
			if(!Objects.equals(dadKey, original.getDadKey()))
				apiRequest.addVars("dadKey");
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
			if(!Objects.equals(dadCompleteName, original.getDadCompleteName()))
				apiRequest.addVars("dadCompleteName");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), dadKey, enrollmentKeys, familySort, schoolSort, userKeys, schoolKeys, yearKeys, seasonKeys, sessionKeys, ageKeys, personFirstName, personFirstNamePreferred, familyName, personCompleteName, personCompleteNamePreferred, personFormalName, personOccupation, personPhoneNumber, personEmail, personRelation, personSms, personReceiveEmail, personEmergencyContact, personPickup, photo, dadCompleteName);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SchoolDad))
			return false;
		SchoolDad that = (SchoolDad)o;
		return super.equals(o)
				&& Objects.equals( dadKey, that.dadKey )
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
				&& Objects.equals( dadCompleteName, that.dadCompleteName );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolDad { ");
		sb.append( "dadKey: " ).append(dadKey);
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
		sb.append( ", dadCompleteName: \"" ).append(dadCompleteName).append( "\"" );
		sb.append(" }");
		return sb.toString();
	}
}
