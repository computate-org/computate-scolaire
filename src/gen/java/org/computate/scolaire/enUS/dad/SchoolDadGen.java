package org.computate.scolaire.enUS.dad;

import java.util.Arrays;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.writer.AllWriter;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import io.vertx.core.logging.LoggerFactory;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.computate.scolaire.enUS.enrollment.SchoolEnrollment;
import org.computate.scolaire.enUS.wrap.Wrap;
import org.apache.commons.collections.CollectionUtils;
import java.lang.Long;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.Boolean;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.lang.String;
import io.vertx.core.logging.Logger;
import java.math.MathContext;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.util.Set;
import org.apache.commons.text.StringEscapeUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.solr.client.solrj.SolrClient;
import java.util.Objects;
import io.vertx.core.json.JsonArray;
import org.apache.solr.common.SolrDocument;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import io.vertx.ext.sql.SQLConnection;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.vertx.ext.sql.SQLClient;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrInputDocument;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**	
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SchoolDadGen<DEV> extends Cluster {
	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolDad.class);

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
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

	/**	L'entité « dadKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long dadKey;
	@JsonIgnore
	public Wrap<Long> dadKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("dadKey").o(dadKey);

	/**	<br/>L'entité « dadKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dadKey">Trouver l'entité dadKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _dadKey(Wrap<Long> c);

	public Long getDadKey() {
		return dadKey;
	}

	public void setDadKey(Long dadKey) {
		this.dadKey = dadKey;
		this.dadKeyWrap.alreadyInitialized = true;
	}
	public SchoolDad setDadKey(String o) {
		if(NumberUtils.isParsable(o))
			this.dadKey = Long.parseLong(o);
		this.dadKeyWrap.alreadyInitialized = true;
		return (SchoolDad)this;
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

	public Long solrDadKey() {
		return dadKey;
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

	/**	L'entité « enrollmentKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> enrollmentKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> enrollmentKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("enrollmentKeys").o(enrollmentKeys);

	/**	<br/>L'entité « enrollmentKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Trouver l'entité enrollmentKeys dans Solr</a>
	 * <br/>
	 * @param enrollmentKeys est l'entité déjà construit. 
	 **/
	protected abstract void _enrollmentKeys(List<Long> o);

	public List<Long> getEnrollmentKeys() {
		return enrollmentKeys;
	}

	public void setEnrollmentKeys(List<Long> enrollmentKeys) {
		this.enrollmentKeys = enrollmentKeys;
		this.enrollmentKeysWrap.alreadyInitialized = true;
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
	public SchoolDad setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
		return (SchoolDad)this;
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

	public List<Long> solrEnrollmentKeys() {
		return enrollmentKeys;
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
				e("input")
					.a("type", "text")
					.a("placeholder", "enrollments")
					.a("class", "valueObjectSuggest suggestEnrollmentKeys w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setEnrollmentKeys")
					.a("id", classApiMethodMethod, "_enrollmentKeys")
					.a("autocomplete", "off")
					.a("oninput", "suggestSchoolDadEnrollmentKeys($(this).val() ? searchSchoolEnrollmentFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'dadKeys:" + pk + "'}", "], $('#listSchoolDadEnrollmentKeys_", classApiMethodMethod, "'), ", pk, "); ")
				.fg();

		} else {
			sx(htmEnrollmentKeys());
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
								if(
										userKeys.contains(siteRequest_.getUserKey())
										|| Objects.equals(sessionId, siteRequest_.getSessionId())
										|| CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), ROLES)
										|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), ROLES)
								) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue-gray ")
											.a("onclick", "postSchoolEnrollmentVals({ dadKeys: [ \"", pk, "\" ] }, function() { patchSchoolDadVals([{ name: 'fq', value: 'pk:", pk, "' }], {}); }, function() { addError($('#", classApiMethodMethod, "enrollmentKeys')); });")
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

	/**	L'entité « familySort »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer familySort;
	@JsonIgnore
	public Wrap<Integer> familySortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("familySort").o(familySort);

	/**	<br/>L'entité « familySort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familySort">Trouver l'entité familySort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familySort(Wrap<Integer> c);

	public Integer getFamilySort() {
		return familySort;
	}

	public void setFamilySort(Integer familySort) {
		this.familySort = familySort;
		this.familySortWrap.alreadyInitialized = true;
	}
	public SchoolDad setFamilySort(String o) {
		if(NumberUtils.isParsable(o))
			this.familySort = Integer.parseInt(o);
		this.familySortWrap.alreadyInitialized = true;
		return (SchoolDad)this;
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

	public Integer solrFamilySort() {
		return familySort;
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

	/**	L'entité « schoolSort »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer schoolSort;
	@JsonIgnore
	public Wrap<Integer> schoolSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("schoolSort").o(schoolSort);

	/**	<br/>L'entité « schoolSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Trouver l'entité schoolSort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolSort(Wrap<Integer> c);

	public Integer getSchoolSort() {
		return schoolSort;
	}

	public void setSchoolSort(Integer schoolSort) {
		this.schoolSort = schoolSort;
		this.schoolSortWrap.alreadyInitialized = true;
	}
	public SchoolDad setSchoolSort(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolSort = Integer.parseInt(o);
		this.schoolSortWrap.alreadyInitialized = true;
		return (SchoolDad)this;
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

	public Integer solrSchoolSort() {
		return schoolSort;
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

	/**	L'entité « enrollmentSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolEnrollment>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<SchoolEnrollment> enrollmentSearch = new SearchList<SchoolEnrollment>();
	@JsonIgnore
	public Wrap<SearchList<SchoolEnrollment>> enrollmentSearchWrap = new Wrap<SearchList<SchoolEnrollment>>().p(this).c(SearchList.class).var("enrollmentSearch").o(enrollmentSearch);

	/**	<br/>L'entité « enrollmentSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentSearch">Trouver l'entité enrollmentSearch dans Solr</a>
	 * <br/>
	 * @param enrollmentSearch est l'entité déjà construit. 
	 **/
	protected abstract void _enrollmentSearch(SearchList<SchoolEnrollment> l);

	public SearchList<SchoolEnrollment> getEnrollmentSearch() {
		return enrollmentSearch;
	}

	public void setEnrollmentSearch(SearchList<SchoolEnrollment> enrollmentSearch) {
		this.enrollmentSearch = enrollmentSearch;
		this.enrollmentSearchWrap.alreadyInitialized = true;
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

	/**	L'entité « enrollments »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolEnrollment>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolEnrollment> enrollments = new java.util.ArrayList<org.computate.scolaire.enUS.enrollment.SchoolEnrollment>();
	@JsonIgnore
	public Wrap<List<SchoolEnrollment>> enrollmentsWrap = new Wrap<List<SchoolEnrollment>>().p(this).c(List.class).var("enrollments").o(enrollments);

	/**	<br/>L'entité « enrollments »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolEnrollment>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollments">Trouver l'entité enrollments dans Solr</a>
	 * <br/>
	 * @param enrollments est l'entité déjà construit. 
	 **/
	protected abstract void _enrollments(List<SchoolEnrollment> l);

	public List<SchoolEnrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<SchoolEnrollment> enrollments) {
		this.enrollments = enrollments;
		this.enrollmentsWrap.alreadyInitialized = true;
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

	/**	L'entité « userKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> userKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> userKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("userKeys").o(userKeys);

	/**	<br/>L'entité « userKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:userKeys">Trouver l'entité userKeys dans Solr</a>
	 * <br/>
	 * @param userKeys est l'entité déjà construit. 
	 **/
	protected abstract void _userKeys(List<Long> l);

	public List<Long> getUserKeys() {
		return userKeys;
	}

	public void setUserKeys(List<Long> userKeys) {
		this.userKeys = userKeys;
		this.userKeysWrap.alreadyInitialized = true;
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
	public SchoolDad setUserKeys(JsonArray objets) {
		userKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addUserKeys(o);
		}
		return (SchoolDad)this;
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

	public List<Long> solrUserKeys() {
		return userKeys;
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

	/**	L'entité « schoolKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> schoolKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> schoolKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("schoolKeys").o(schoolKeys);

	/**	<br/>L'entité « schoolKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKeys">Trouver l'entité schoolKeys dans Solr</a>
	 * <br/>
	 * @param schoolKeys est l'entité déjà construit. 
	 **/
	protected abstract void _schoolKeys(List<Long> l);

	public List<Long> getSchoolKeys() {
		return schoolKeys;
	}

	public void setSchoolKeys(List<Long> schoolKeys) {
		this.schoolKeys = schoolKeys;
		this.schoolKeysWrap.alreadyInitialized = true;
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
	public SchoolDad setSchoolKeys(JsonArray objets) {
		schoolKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSchoolKeys(o);
		}
		return (SchoolDad)this;
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

	public List<Long> solrSchoolKeys() {
		return schoolKeys;
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

	/**	L'entité « yearKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> yearKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> yearKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("yearKeys").o(yearKeys);

	/**	<br/>L'entité « yearKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKeys">Trouver l'entité yearKeys dans Solr</a>
	 * <br/>
	 * @param yearKeys est l'entité déjà construit. 
	 **/
	protected abstract void _yearKeys(List<Long> l);

	public List<Long> getYearKeys() {
		return yearKeys;
	}

	public void setYearKeys(List<Long> yearKeys) {
		this.yearKeys = yearKeys;
		this.yearKeysWrap.alreadyInitialized = true;
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
	public SchoolDad setYearKeys(JsonArray objets) {
		yearKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addYearKeys(o);
		}
		return (SchoolDad)this;
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

	public List<Long> solrYearKeys() {
		return yearKeys;
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

	/**	L'entité « seasonKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> seasonKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> seasonKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("seasonKeys").o(seasonKeys);

	/**	<br/>L'entité « seasonKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKeys">Trouver l'entité seasonKeys dans Solr</a>
	 * <br/>
	 * @param seasonKeys est l'entité déjà construit. 
	 **/
	protected abstract void _seasonKeys(List<Long> l);

	public List<Long> getSeasonKeys() {
		return seasonKeys;
	}

	public void setSeasonKeys(List<Long> seasonKeys) {
		this.seasonKeys = seasonKeys;
		this.seasonKeysWrap.alreadyInitialized = true;
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
	public SchoolDad setSeasonKeys(JsonArray objets) {
		seasonKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSeasonKeys(o);
		}
		return (SchoolDad)this;
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

	public List<Long> solrSeasonKeys() {
		return seasonKeys;
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

	/**	L'entité « sessionKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> sessionKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> sessionKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("sessionKeys").o(sessionKeys);

	/**	<br/>L'entité « sessionKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionKeys">Trouver l'entité sessionKeys dans Solr</a>
	 * <br/>
	 * @param sessionKeys est l'entité déjà construit. 
	 **/
	protected abstract void _sessionKeys(List<Long> l);

	public List<Long> getSessionKeys() {
		return sessionKeys;
	}

	public void setSessionKeys(List<Long> sessionKeys) {
		this.sessionKeys = sessionKeys;
		this.sessionKeysWrap.alreadyInitialized = true;
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
	public SchoolDad setSessionKeys(JsonArray objets) {
		sessionKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSessionKeys(o);
		}
		return (SchoolDad)this;
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

	public List<Long> solrSessionKeys() {
		return sessionKeys;
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

	/**	L'entité « ageKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> ageKeys = new java.util.ArrayList<java.lang.Long>();
	@JsonIgnore
	public Wrap<List<Long>> ageKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("ageKeys").o(ageKeys);

	/**	<br/>L'entité « ageKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKeys">Trouver l'entité ageKeys dans Solr</a>
	 * <br/>
	 * @param ageKeys est l'entité déjà construit. 
	 **/
	protected abstract void _ageKeys(List<Long> l);

	public List<Long> getAgeKeys() {
		return ageKeys;
	}

	public void setAgeKeys(List<Long> ageKeys) {
		this.ageKeys = ageKeys;
		this.ageKeysWrap.alreadyInitialized = true;
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
	public SchoolDad setAgeKeys(JsonArray objets) {
		ageKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAgeKeys(o);
		}
		return (SchoolDad)this;
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

	public List<Long> solrAgeKeys() {
		return ageKeys;
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

	/**	L'entité « personFirstName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personFirstName;
	@JsonIgnore
	public Wrap<String> personFirstNameWrap = new Wrap<String>().p(this).c(String.class).var("personFirstName").o(personFirstName);

	/**	<br/>L'entité « personFirstName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personFirstName">Trouver l'entité personFirstName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personFirstName(Wrap<String> c);

	public String getPersonFirstName() {
		return personFirstName;
	}

	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
		this.personFirstNameWrap.alreadyInitialized = true;
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

	public String solrPersonFirstName() {
		return personFirstName;
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
					a("class", "setPersonFirstName inputSchoolDad", pk, "PersonFirstName w3-input w3-border ");
					a("name", "setPersonFirstName");
				} else {
					a("class", "valuePersonFirstName w3-input w3-border inputSchoolDad", pk, "PersonFirstName w3-input w3-border ");
					a("name", "personFirstName");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patchSchoolDadVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonFirstName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personFirstName')); }, function() { addError($('#", classApiMethodMethod, "_personFirstName')); }); ");
				}
				a("value", strPersonFirstName())
			.fg();

		} else {
			sx(htmPersonFirstName());
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personFirstName')); $('#", classApiMethodMethod, "_personFirstName').val(null); patchSchoolDadVal([{ name: 'fq', value: 'pk:' + $('#SchoolDadForm :input[name=pk]').val() }], 'setPersonFirstName', null, function() { addGlow($('#", classApiMethodMethod, "_personFirstName')); }, function() { addError($('#", classApiMethodMethod, "_personFirstName')); }); ")
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

	/**	L'entité « personFirstNamePreferred »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personFirstNamePreferred;
	@JsonIgnore
	public Wrap<String> personFirstNamePreferredWrap = new Wrap<String>().p(this).c(String.class).var("personFirstNamePreferred").o(personFirstNamePreferred);

	/**	<br/>L'entité « personFirstNamePreferred »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personFirstNamePreferred">Trouver l'entité personFirstNamePreferred dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personFirstNamePreferred(Wrap<String> c);

	public String getPersonFirstNamePreferred() {
		return personFirstNamePreferred;
	}

	public void setPersonFirstNamePreferred(String personFirstNamePreferred) {
		this.personFirstNamePreferred = personFirstNamePreferred;
		this.personFirstNamePreferredWrap.alreadyInitialized = true;
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

	public String solrPersonFirstNamePreferred() {
		return personFirstNamePreferred;
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
					a("class", "setPersonFirstNamePreferred inputSchoolDad", pk, "PersonFirstNamePreferred w3-input w3-border ");
					a("name", "setPersonFirstNamePreferred");
				} else {
					a("class", "valuePersonFirstNamePreferred w3-input w3-border inputSchoolDad", pk, "PersonFirstNamePreferred w3-input w3-border ");
					a("name", "personFirstNamePreferred");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patchSchoolDadVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonFirstNamePreferred', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personFirstNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_personFirstNamePreferred')); }); ");
				}
				a("value", strPersonFirstNamePreferred())
			.fg();

		} else {
			sx(htmPersonFirstNamePreferred());
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personFirstNamePreferred')); $('#", classApiMethodMethod, "_personFirstNamePreferred').val(null); patchSchoolDadVal([{ name: 'fq', value: 'pk:' + $('#SchoolDadForm :input[name=pk]').val() }], 'setPersonFirstNamePreferred', null, function() { addGlow($('#", classApiMethodMethod, "_personFirstNamePreferred')); }, function() { addError($('#", classApiMethodMethod, "_personFirstNamePreferred')); }); ")
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

	/**	L'entité « familyName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String familyName;
	@JsonIgnore
	public Wrap<String> familyNameWrap = new Wrap<String>().p(this).c(String.class).var("familyName").o(familyName);

	/**	<br/>L'entité « familyName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:familyName">Trouver l'entité familyName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _familyName(Wrap<String> c);

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
		this.familyNameWrap.alreadyInitialized = true;
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

	public String solrFamilyName() {
		return familyName;
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
					a("class", "setFamilyName inputSchoolDad", pk, "FamilyName w3-input w3-border ");
					a("name", "setFamilyName");
				} else {
					a("class", "valueFamilyName w3-input w3-border inputSchoolDad", pk, "FamilyName w3-input w3-border ");
					a("name", "familyName");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patchSchoolDadVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setFamilyName', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_familyName')); }, function() { addError($('#", classApiMethodMethod, "_familyName')); }); ");
				}
				a("value", strFamilyName())
			.fg();

		} else {
			sx(htmFamilyName());
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_familyName')); $('#", classApiMethodMethod, "_familyName').val(null); patchSchoolDadVal([{ name: 'fq', value: 'pk:' + $('#SchoolDadForm :input[name=pk]').val() }], 'setFamilyName', null, function() { addGlow($('#", classApiMethodMethod, "_familyName')); }, function() { addError($('#", classApiMethodMethod, "_familyName')); }); ")
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

	/**	L'entité « personCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personCompleteName;
	@JsonIgnore
	public Wrap<String> personCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("personCompleteName").o(personCompleteName);

	/**	<br/>L'entité « personCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personCompleteName">Trouver l'entité personCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personCompleteName(Wrap<String> c);

	public String getPersonCompleteName() {
		return personCompleteName;
	}

	public void setPersonCompleteName(String personCompleteName) {
		this.personCompleteName = personCompleteName;
		this.personCompleteNameWrap.alreadyInitialized = true;
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

	public String solrPersonCompleteName() {
		return personCompleteName;
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

	/**	L'entité « personCompleteNamePreferred »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personCompleteNamePreferred;
	@JsonIgnore
	public Wrap<String> personCompleteNamePreferredWrap = new Wrap<String>().p(this).c(String.class).var("personCompleteNamePreferred").o(personCompleteNamePreferred);

	/**	<br/>L'entité « personCompleteNamePreferred »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personCompleteNamePreferred">Trouver l'entité personCompleteNamePreferred dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personCompleteNamePreferred(Wrap<String> c);

	public String getPersonCompleteNamePreferred() {
		return personCompleteNamePreferred;
	}

	public void setPersonCompleteNamePreferred(String personCompleteNamePreferred) {
		this.personCompleteNamePreferred = personCompleteNamePreferred;
		this.personCompleteNamePreferredWrap.alreadyInitialized = true;
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

	public String solrPersonCompleteNamePreferred() {
		return personCompleteNamePreferred;
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

	/**	L'entité « personFormalName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personFormalName;
	@JsonIgnore
	public Wrap<String> personFormalNameWrap = new Wrap<String>().p(this).c(String.class).var("personFormalName").o(personFormalName);

	/**	<br/>L'entité « personFormalName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personFormalName">Trouver l'entité personFormalName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personFormalName(Wrap<String> c);

	public String getPersonFormalName() {
		return personFormalName;
	}

	public void setPersonFormalName(String personFormalName) {
		this.personFormalName = personFormalName;
		this.personFormalNameWrap.alreadyInitialized = true;
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

	public String solrPersonFormalName() {
		return personFormalName;
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

	/**	L'entité « personOccupation »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personOccupation;
	@JsonIgnore
	public Wrap<String> personOccupationWrap = new Wrap<String>().p(this).c(String.class).var("personOccupation").o(personOccupation);

	/**	<br/>L'entité « personOccupation »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personOccupation">Trouver l'entité personOccupation dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personOccupation(Wrap<String> c);

	public String getPersonOccupation() {
		return personOccupation;
	}

	public void setPersonOccupation(String personOccupation) {
		this.personOccupation = personOccupation;
		this.personOccupationWrap.alreadyInitialized = true;
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

	public String solrPersonOccupation() {
		return personOccupation;
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
					a("class", "setPersonOccupation inputSchoolDad", pk, "PersonOccupation w3-input w3-border ");
					a("name", "setPersonOccupation");
				} else {
					a("class", "valuePersonOccupation w3-input w3-border inputSchoolDad", pk, "PersonOccupation w3-input w3-border ");
					a("name", "personOccupation");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patchSchoolDadVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonOccupation', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personOccupation')); }, function() { addError($('#", classApiMethodMethod, "_personOccupation')); }); ");
				}
				a("value", strPersonOccupation())
			.fg();

		} else {
			sx(htmPersonOccupation());
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personOccupation')); $('#", classApiMethodMethod, "_personOccupation').val(null); patchSchoolDadVal([{ name: 'fq', value: 'pk:' + $('#SchoolDadForm :input[name=pk]').val() }], 'setPersonOccupation', null, function() { addGlow($('#", classApiMethodMethod, "_personOccupation')); }, function() { addError($('#", classApiMethodMethod, "_personOccupation')); }); ")
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

	/**	L'entité « personPhoneNumber »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personPhoneNumber;
	@JsonIgnore
	public Wrap<String> personPhoneNumberWrap = new Wrap<String>().p(this).c(String.class).var("personPhoneNumber").o(personPhoneNumber);

	/**	<br/>L'entité « personPhoneNumber »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personPhoneNumber">Trouver l'entité personPhoneNumber dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personPhoneNumber(Wrap<String> c);

	public String getPersonPhoneNumber() {
		return personPhoneNumber;
	}

	public void setPersonPhoneNumber(String personPhoneNumber) {
		this.personPhoneNumber = personPhoneNumber;
		this.personPhoneNumberWrap.alreadyInitialized = true;
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

	public String solrPersonPhoneNumber() {
		return personPhoneNumber;
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
					a("class", "setPersonPhoneNumber inputSchoolDad", pk, "PersonPhoneNumber w3-input w3-border ");
					a("name", "setPersonPhoneNumber");
				} else {
					a("class", "valuePersonPhoneNumber w3-input w3-border inputSchoolDad", pk, "PersonPhoneNumber w3-input w3-border ");
					a("name", "personPhoneNumber");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patchSchoolDadVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonPhoneNumber', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personPhoneNumber')); }, function() { addError($('#", classApiMethodMethod, "_personPhoneNumber')); }); ");
				}
				a("value", strPersonPhoneNumber())
			.fg();

		} else {
			sx(htmPersonPhoneNumber());
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personPhoneNumber')); $('#", classApiMethodMethod, "_personPhoneNumber').val(null); patchSchoolDadVal([{ name: 'fq', value: 'pk:' + $('#SchoolDadForm :input[name=pk]').val() }], 'setPersonPhoneNumber', null, function() { addGlow($('#", classApiMethodMethod, "_personPhoneNumber')); }, function() { addError($('#", classApiMethodMethod, "_personPhoneNumber')); }); ")
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

	/**	L'entité « personEmail »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personEmail;
	@JsonIgnore
	public Wrap<String> personEmailWrap = new Wrap<String>().p(this).c(String.class).var("personEmail").o(personEmail);

	/**	<br/>L'entité « personEmail »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personEmail">Trouver l'entité personEmail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personEmail(Wrap<String> c);

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
		this.personEmailWrap.alreadyInitialized = true;
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

	public String solrPersonEmail() {
		return personEmail;
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
					a("class", "setPersonEmail inputSchoolDad", pk, "PersonEmail w3-input w3-border ");
					a("name", "setPersonEmail");
				} else {
					a("class", "valuePersonEmail w3-input w3-border inputSchoolDad", pk, "PersonEmail w3-input w3-border ");
					a("name", "personEmail");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patchSchoolDadVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonEmail', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_personEmail')); }, function() { addError($('#", classApiMethodMethod, "_personEmail')); }); ");
				}
				a("value", strPersonEmail())
			.fg();

		} else {
			sx(htmPersonEmail());
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
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_personEmail')); $('#", classApiMethodMethod, "_personEmail').val(null); patchSchoolDadVal([{ name: 'fq', value: 'pk:' + $('#SchoolDadForm :input[name=pk]').val() }], 'setPersonEmail', null, function() { addGlow($('#", classApiMethodMethod, "_personEmail')); }, function() { addError($('#", classApiMethodMethod, "_personEmail')); }); ")
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

	/**	L'entité « personRelation »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String personRelation;
	@JsonIgnore
	public Wrap<String> personRelationWrap = new Wrap<String>().p(this).c(String.class).var("personRelation").o(personRelation);

	/**	<br/>L'entité « personRelation »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personRelation">Trouver l'entité personRelation dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personRelation(Wrap<String> c);

	public String getPersonRelation() {
		return personRelation;
	}

	public void setPersonRelation(String personRelation) {
		this.personRelation = personRelation;
		this.personRelationWrap.alreadyInitialized = true;
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

	public String solrPersonRelation() {
		return personRelation;
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

	/**	L'entité « personSms »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean personSms;
	@JsonIgnore
	public Wrap<Boolean> personSmsWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("personSms").o(personSms);

	/**	<br/>L'entité « personSms »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personSms">Trouver l'entité personSms dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personSms(Wrap<Boolean> c);

	public Boolean getPersonSms() {
		return personSms;
	}

	public void setPersonSms(Boolean personSms) {
		this.personSms = personSms;
		this.personSmsWrap.alreadyInitialized = true;
	}
	public SchoolDad setPersonSms(String o) {
		this.personSms = Boolean.parseBoolean(o);
		this.personSmsWrap.alreadyInitialized = true;
		return (SchoolDad)this;
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

	public Boolean solrPersonSms() {
		return personSms;
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
				a("class", "setPersonSms inputSchoolDad", pk, "PersonSms w3-input w3-border ");
				a("name", "setPersonSms");
			} else {
				a("class", "valuePersonSms inputSchoolDad", pk, "PersonSms w3-input w3-border ");
				a("name", "personSms");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patchSchoolDadVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonSms', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_personSms')); }, function() { addError($('#", classApiMethodMethod, "_personSms')); }); ");
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
			sx(htmPersonSms());
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

	/**	L'entité « personReceiveEmail »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean personReceiveEmail;
	@JsonIgnore
	public Wrap<Boolean> personReceiveEmailWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("personReceiveEmail").o(personReceiveEmail);

	/**	<br/>L'entité « personReceiveEmail »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personReceiveEmail">Trouver l'entité personReceiveEmail dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personReceiveEmail(Wrap<Boolean> c);

	public Boolean getPersonReceiveEmail() {
		return personReceiveEmail;
	}

	public void setPersonReceiveEmail(Boolean personReceiveEmail) {
		this.personReceiveEmail = personReceiveEmail;
		this.personReceiveEmailWrap.alreadyInitialized = true;
	}
	public SchoolDad setPersonReceiveEmail(String o) {
		this.personReceiveEmail = Boolean.parseBoolean(o);
		this.personReceiveEmailWrap.alreadyInitialized = true;
		return (SchoolDad)this;
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

	public Boolean solrPersonReceiveEmail() {
		return personReceiveEmail;
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
				a("class", "setPersonReceiveEmail inputSchoolDad", pk, "PersonReceiveEmail w3-input w3-border ");
				a("name", "setPersonReceiveEmail");
			} else {
				a("class", "valuePersonReceiveEmail inputSchoolDad", pk, "PersonReceiveEmail w3-input w3-border ");
				a("name", "personReceiveEmail");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patchSchoolDadVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonReceiveEmail', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_personReceiveEmail')); }, function() { addError($('#", classApiMethodMethod, "_personReceiveEmail')); }); ");
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
			sx(htmPersonReceiveEmail());
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

	/**	L'entité « personEmergencyContact »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean personEmergencyContact;
	@JsonIgnore
	public Wrap<Boolean> personEmergencyContactWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("personEmergencyContact").o(personEmergencyContact);

	/**	<br/>L'entité « personEmergencyContact »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personEmergencyContact">Trouver l'entité personEmergencyContact dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personEmergencyContact(Wrap<Boolean> c);

	public Boolean getPersonEmergencyContact() {
		return personEmergencyContact;
	}

	public void setPersonEmergencyContact(Boolean personEmergencyContact) {
		this.personEmergencyContact = personEmergencyContact;
		this.personEmergencyContactWrap.alreadyInitialized = true;
	}
	public SchoolDad setPersonEmergencyContact(String o) {
		this.personEmergencyContact = Boolean.parseBoolean(o);
		this.personEmergencyContactWrap.alreadyInitialized = true;
		return (SchoolDad)this;
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

	public Boolean solrPersonEmergencyContact() {
		return personEmergencyContact;
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
				a("class", "setPersonEmergencyContact inputSchoolDad", pk, "PersonEmergencyContact w3-input w3-border ");
				a("name", "setPersonEmergencyContact");
			} else {
				a("class", "valuePersonEmergencyContact inputSchoolDad", pk, "PersonEmergencyContact w3-input w3-border ");
				a("name", "personEmergencyContact");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patchSchoolDadVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonEmergencyContact', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_personEmergencyContact')); }, function() { addError($('#", classApiMethodMethod, "_personEmergencyContact')); }); ");
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
			sx(htmPersonEmergencyContact());
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

	/**	L'entité « personPickup »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected Boolean personPickup;
	@JsonIgnore
	public Wrap<Boolean> personPickupWrap = new Wrap<Boolean>().p(this).c(Boolean.class).var("personPickup").o(personPickup);

	/**	<br/>L'entité « personPickup »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:personPickup">Trouver l'entité personPickup dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _personPickup(Wrap<Boolean> c);

	public Boolean getPersonPickup() {
		return personPickup;
	}

	public void setPersonPickup(Boolean personPickup) {
		this.personPickup = personPickup;
		this.personPickupWrap.alreadyInitialized = true;
	}
	public SchoolDad setPersonPickup(String o) {
		this.personPickup = Boolean.parseBoolean(o);
		this.personPickupWrap.alreadyInitialized = true;
		return (SchoolDad)this;
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

	public Boolean solrPersonPickup() {
		return personPickup;
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
				a("class", "setPersonPickup inputSchoolDad", pk, "PersonPickup w3-input w3-border ");
				a("name", "setPersonPickup");
			} else {
				a("class", "valuePersonPickup inputSchoolDad", pk, "PersonPickup w3-input w3-border ");
				a("name", "personPickup");
			}
			if("Page".equals(classApiMethodMethod)) {
				a("onchange", "patchSchoolDadVal([{ name: 'fq', value: 'pk:", pk, "' }], 'setPersonPickup', $(this).prop('checked'), function() { addGlow($('#", classApiMethodMethod, "_personPickup')); }, function() { addError($('#", classApiMethodMethod, "_personPickup')); }); ");
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
			sx(htmPersonPickup());
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

	/////////////////////
	// dadCompleteName //
	/////////////////////

	/**	L'entité « dadCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String dadCompleteName;
	@JsonIgnore
	public Wrap<String> dadCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("dadCompleteName").o(dadCompleteName);

	/**	<br/>L'entité « dadCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.dad.SchoolDad&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:dadCompleteName">Trouver l'entité dadCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _dadCompleteName(Wrap<String> c);

	public String getDadCompleteName() {
		return dadCompleteName;
	}

	public void setDadCompleteName(String dadCompleteName) {
		this.dadCompleteName = dadCompleteName;
		this.dadCompleteNameWrap.alreadyInitialized = true;
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

	public String solrDadCompleteName() {
		return dadCompleteName;
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
				if(!savesSchoolDad.contains(var))
					savesSchoolDad.add(var);
				return val;
			default:
				return super.attributeCluster(var, val);
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
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolDad(String var, String val) {
		switch(var) {
			case "personFirstName":
				setPersonFirstName(val);
				savesSchoolDad.add(var);
				return val;
			case "personFirstNamePreferred":
				setPersonFirstNamePreferred(val);
				savesSchoolDad.add(var);
				return val;
			case "familyName":
				setFamilyName(val);
				savesSchoolDad.add(var);
				return val;
			case "personOccupation":
				setPersonOccupation(val);
				savesSchoolDad.add(var);
				return val;
			case "personPhoneNumber":
				setPersonPhoneNumber(val);
				savesSchoolDad.add(var);
				return val;
			case "personEmail":
				setPersonEmail(val);
				savesSchoolDad.add(var);
				return val;
			case "personSms":
				setPersonSms(val);
				savesSchoolDad.add(var);
				return val;
			case "personReceiveEmail":
				setPersonReceiveEmail(val);
				savesSchoolDad.add(var);
				return val;
			case "personEmergencyContact":
				setPersonEmergencyContact(val);
				savesSchoolDad.add(var);
				return val;
			case "personPickup":
				setPersonPickup(val);
				savesSchoolDad.add(var);
				return val;
			default:
				return super.defineCluster(var, val);
		}
	}

	/////////////////
	// saves //
	/////////////////

	protected List<String> savesSchoolDad = new ArrayList<String>();

	/////////////
	// populate //
	/////////////

	@Override public void populateForClass(SolrDocument solrDocument) {
		populateSchoolDad(solrDocument);
	}
	public void populateSchoolDad(SolrDocument solrDocument) {
		SchoolDad oSchoolDad = (SchoolDad)this;
		savesSchoolDad = (List<String>)solrDocument.get("savesSchoolDad_stored_strings");
		if(savesSchoolDad != null) {

			if(savesSchoolDad.contains("dadKey")) {
				Long dadKey = (Long)solrDocument.get("dadKey_stored_long");
				if(dadKey != null)
					oSchoolDad.setDadKey(dadKey);
			}

			List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
			if(enrollmentKeys != null)
				oSchoolDad.enrollmentKeys.addAll(enrollmentKeys);

			if(savesSchoolDad.contains("familySort")) {
				Integer familySort = (Integer)solrDocument.get("familySort_stored_int");
				if(familySort != null)
					oSchoolDad.setFamilySort(familySort);
			}

			if(savesSchoolDad.contains("schoolSort")) {
				Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
				if(schoolSort != null)
					oSchoolDad.setSchoolSort(schoolSort);
			}

			if(savesSchoolDad.contains("userKeys")) {
				List<Long> userKeys = (List<Long>)solrDocument.get("userKeys_stored_longs");
				if(userKeys != null)
					oSchoolDad.userKeys.addAll(userKeys);
			}

			if(savesSchoolDad.contains("schoolKeys")) {
				List<Long> schoolKeys = (List<Long>)solrDocument.get("schoolKeys_stored_longs");
				if(schoolKeys != null)
					oSchoolDad.schoolKeys.addAll(schoolKeys);
			}

			if(savesSchoolDad.contains("yearKeys")) {
				List<Long> yearKeys = (List<Long>)solrDocument.get("yearKeys_stored_longs");
				if(yearKeys != null)
					oSchoolDad.yearKeys.addAll(yearKeys);
			}

			if(savesSchoolDad.contains("seasonKeys")) {
				List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
				if(seasonKeys != null)
					oSchoolDad.seasonKeys.addAll(seasonKeys);
			}

			if(savesSchoolDad.contains("sessionKeys")) {
				List<Long> sessionKeys = (List<Long>)solrDocument.get("sessionKeys_stored_longs");
				if(sessionKeys != null)
					oSchoolDad.sessionKeys.addAll(sessionKeys);
			}

			if(savesSchoolDad.contains("ageKeys")) {
				List<Long> ageKeys = (List<Long>)solrDocument.get("ageKeys_stored_longs");
				if(ageKeys != null)
					oSchoolDad.ageKeys.addAll(ageKeys);
			}

			if(savesSchoolDad.contains("personFirstName")) {
				String personFirstName = (String)solrDocument.get("personFirstName_stored_string");
				if(personFirstName != null)
					oSchoolDad.setPersonFirstName(personFirstName);
			}

			if(savesSchoolDad.contains("personFirstNamePreferred")) {
				String personFirstNamePreferred = (String)solrDocument.get("personFirstNamePreferred_stored_string");
				if(personFirstNamePreferred != null)
					oSchoolDad.setPersonFirstNamePreferred(personFirstNamePreferred);
			}

			if(savesSchoolDad.contains("familyName")) {
				String familyName = (String)solrDocument.get("familyName_stored_string");
				if(familyName != null)
					oSchoolDad.setFamilyName(familyName);
			}

			if(savesSchoolDad.contains("personCompleteName")) {
				String personCompleteName = (String)solrDocument.get("personCompleteName_stored_string");
				if(personCompleteName != null)
					oSchoolDad.setPersonCompleteName(personCompleteName);
			}

			if(savesSchoolDad.contains("personCompleteNamePreferred")) {
				String personCompleteNamePreferred = (String)solrDocument.get("personCompleteNamePreferred_stored_string");
				if(personCompleteNamePreferred != null)
					oSchoolDad.setPersonCompleteNamePreferred(personCompleteNamePreferred);
			}

			if(savesSchoolDad.contains("personFormalName")) {
				String personFormalName = (String)solrDocument.get("personFormalName_stored_string");
				if(personFormalName != null)
					oSchoolDad.setPersonFormalName(personFormalName);
			}

			if(savesSchoolDad.contains("personOccupation")) {
				String personOccupation = (String)solrDocument.get("personOccupation_stored_string");
				if(personOccupation != null)
					oSchoolDad.setPersonOccupation(personOccupation);
			}

			if(savesSchoolDad.contains("personPhoneNumber")) {
				String personPhoneNumber = (String)solrDocument.get("personPhoneNumber_stored_string");
				if(personPhoneNumber != null)
					oSchoolDad.setPersonPhoneNumber(personPhoneNumber);
			}

			if(savesSchoolDad.contains("personEmail")) {
				String personEmail = (String)solrDocument.get("personEmail_stored_string");
				if(personEmail != null)
					oSchoolDad.setPersonEmail(personEmail);
			}

			if(savesSchoolDad.contains("personRelation")) {
				String personRelation = (String)solrDocument.get("personRelation_stored_string");
				if(personRelation != null)
					oSchoolDad.setPersonRelation(personRelation);
			}

			if(savesSchoolDad.contains("personSms")) {
				Boolean personSms = (Boolean)solrDocument.get("personSms_stored_boolean");
				if(personSms != null)
					oSchoolDad.setPersonSms(personSms);
			}

			if(savesSchoolDad.contains("personReceiveEmail")) {
				Boolean personReceiveEmail = (Boolean)solrDocument.get("personReceiveEmail_stored_boolean");
				if(personReceiveEmail != null)
					oSchoolDad.setPersonReceiveEmail(personReceiveEmail);
			}

			if(savesSchoolDad.contains("personEmergencyContact")) {
				Boolean personEmergencyContact = (Boolean)solrDocument.get("personEmergencyContact_stored_boolean");
				if(personEmergencyContact != null)
					oSchoolDad.setPersonEmergencyContact(personEmergencyContact);
			}

			if(savesSchoolDad.contains("personPickup")) {
				Boolean personPickup = (Boolean)solrDocument.get("personPickup_stored_boolean");
				if(personPickup != null)
					oSchoolDad.setPersonPickup(personPickup);
			}

			if(savesSchoolDad.contains("dadCompleteName")) {
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
		if(savesSchoolDad != null)
			document.addField("savesSchoolDad_stored_strings", savesSchoolDad);

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
			if(!Objects.equals(enrollmentKeys, original.getEnrollmentKeys()))
				apiRequest.addVars("enrollmentKeys");
			if(!Objects.equals(personFirstName, original.getPersonFirstName()))
				apiRequest.addVars("personFirstName");
			if(!Objects.equals(personFirstNamePreferred, original.getPersonFirstNamePreferred()))
				apiRequest.addVars("personFirstNamePreferred");
			if(!Objects.equals(familyName, original.getFamilyName()))
				apiRequest.addVars("familyName");
			if(!Objects.equals(personOccupation, original.getPersonOccupation()))
				apiRequest.addVars("personOccupation");
			if(!Objects.equals(personPhoneNumber, original.getPersonPhoneNumber()))
				apiRequest.addVars("personPhoneNumber");
			if(!Objects.equals(personEmail, original.getPersonEmail()))
				apiRequest.addVars("personEmail");
			if(!Objects.equals(personSms, original.getPersonSms()))
				apiRequest.addVars("personSms");
			if(!Objects.equals(personReceiveEmail, original.getPersonReceiveEmail()))
				apiRequest.addVars("personReceiveEmail");
			if(!Objects.equals(personEmergencyContact, original.getPersonEmergencyContact()))
				apiRequest.addVars("personEmergencyContact");
			if(!Objects.equals(personPickup, original.getPersonPickup()))
				apiRequest.addVars("personPickup");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), enrollmentKeys, personFirstName, personFirstNamePreferred, familyName, personOccupation, personPhoneNumber, personEmail, personSms, personReceiveEmail, personEmergencyContact, personPickup);
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
				&& Objects.equals( enrollmentKeys, that.enrollmentKeys )
				&& Objects.equals( personFirstName, that.personFirstName )
				&& Objects.equals( personFirstNamePreferred, that.personFirstNamePreferred )
				&& Objects.equals( familyName, that.familyName )
				&& Objects.equals( personOccupation, that.personOccupation )
				&& Objects.equals( personPhoneNumber, that.personPhoneNumber )
				&& Objects.equals( personEmail, that.personEmail )
				&& Objects.equals( personSms, that.personSms )
				&& Objects.equals( personReceiveEmail, that.personReceiveEmail )
				&& Objects.equals( personEmergencyContact, that.personEmergencyContact )
				&& Objects.equals( personPickup, that.personPickup );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolDad { ");
		sb.append( "enrollmentKeys: " ).append(enrollmentKeys);
		sb.append( ", personFirstName: \"" ).append(personFirstName).append( "\"" );
		sb.append( ", personFirstNamePreferred: \"" ).append(personFirstNamePreferred).append( "\"" );
		sb.append( ", familyName: \"" ).append(familyName).append( "\"" );
		sb.append( ", personOccupation: \"" ).append(personOccupation).append( "\"" );
		sb.append( ", personPhoneNumber: \"" ).append(personPhoneNumber).append( "\"" );
		sb.append( ", personEmail: \"" ).append(personEmail).append( "\"" );
		sb.append( ", personSms: " ).append(personSms);
		sb.append( ", personReceiveEmail: " ).append(personReceiveEmail);
		sb.append( ", personEmergencyContact: " ).append(personEmergencyContact);
		sb.append( ", personPickup: " ).append(personPickup);
		sb.append(" }");
		return sb.toString();
	}
}
