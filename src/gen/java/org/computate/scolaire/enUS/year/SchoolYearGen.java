package org.computate.scolaire.enUS.year;

import java.util.Arrays;
import java.util.Date;
import org.computate.scolaire.enUS.search.SearchList;
import org.computate.scolaire.enUS.contexte.SiteContextEnUS;
import org.computate.scolaire.enUS.school.School;
import org.computate.scolaire.enUS.request.api.ApiRequest;
import org.apache.commons.lang3.StringUtils;
import java.lang.Integer;
import java.math.BigDecimal;
import java.lang.Long;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.util.Locale;
import io.vertx.core.json.JsonObject;
import org.computate.scolaire.enUS.request.SiteRequestEnUS;
import java.time.ZoneOffset;
import io.vertx.core.logging.Logger;
import org.computate.scolaire.enUS.year.SchoolYear;
import java.math.MathContext;
import org.computate.scolaire.enUS.cluster.Cluster;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;
import java.util.List;
import org.computate.scolaire.enUS.age.SchoolAge;
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
 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true">Trouver la classe  dans Solr</a>
 * <br/>
 **/
public abstract class SchoolYearGen<DEV> extends Cluster {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SchoolYear.class);

	public static final List<String> ROLES = Arrays.asList("SiteAdmin");
	public static final List<String> ROLE_READS = Arrays.asList("");

	public static final String SchoolYear_AName = "a year";
	public static final String SchoolYear_This = "this ";
	public static final String SchoolYear_ThisName = "this year";
	public static final String SchoolYear_A = "a ";
	public static final String SchoolYear_TheName = "the year";
	public static final String SchoolYear_NameSingular = "year";
	public static final String SchoolYear_NamePlural = "years";
	public static final String SchoolYear_NameActual = "current year";
	public static final String SchoolYear_AllName = "all the years";
	public static final String SchoolYear_SearchAllNameBy = "search years by ";
	public static final String SchoolYear_ThePluralName = "the years";
	public static final String SchoolYear_NoNameFound = "no year found";
	public static final String SchoolYear_NameVar = "year";
	public static final String SchoolYear_OfName = "of year";
	public static final String SchoolYear_ANameAdjective = "a year";
	public static final String SchoolYear_NameAdjectiveSingular = "year";
	public static final String SchoolYear_NameAdjectivePlural = "years";
	public static final String SchoolYear_Color = "orange";
	public static final String SchoolYear_IconGroup = "regular";
	public static final String SchoolYear_IconName = "calendar-check";

	///////////////
	// schoolKey //
	///////////////

	/**	L'entité « schoolKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long schoolKey;
	@JsonIgnore
	public Wrap<Long> schoolKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("schoolKey").o(schoolKey);

	/**	<br/>L'entité « schoolKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolKey">Trouver l'entité schoolKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolKey(Wrap<Long> c);

	public Long getSchoolKey() {
		return schoolKey;
	}

	public void setSchoolKey(Long schoolKey) {
		this.schoolKey = schoolKey;
		this.schoolKeyWrap.alreadyInitialized = true;
	}
	public SchoolYear setSchoolKey(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolKey = Long.parseLong(o);
		this.schoolKeyWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	protected SchoolYear schoolKeyInit() {
		if(!schoolKeyWrap.alreadyInitialized) {
			_schoolKey(schoolKeyWrap);
			if(schoolKey == null)
				setSchoolKey(schoolKeyWrap.o);
		}
		schoolKeyWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public Long solrSchoolKey() {
		return schoolKey;
	}

	public String strSchoolKey() {
		return schoolKey == null ? "" : schoolKey.toString();
	}

	public String jsonSchoolKey() {
		return schoolKey == null ? "" : schoolKey.toString();
	}

	public String nomAffichageSchoolKey() {
		return "school";
	}

	public String htmTooltipSchoolKey() {
		return null;
	}

	public String htmSchoolKey() {
		return schoolKey == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolKey());
	}

	public void inputSchoolKey(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "school")
					.a("class", "valueObjectSuggest suggestSchoolKey w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setSchoolKey")
					.a("id", classApiMethodMethod, "_schoolKey")
					.a("autocomplete", "off")
					.a("oninput", "suggestSchoolYearSchoolKey($(this).val() ? searchSchoolFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'yearKeys:" + pk + "'}", "], $('#listSchoolYearSchoolKey_", classApiMethodMethod, "'), ", pk, "); ")
				.fg();

		}
	}

	public void htmSchoolKey(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolYearSchoolKey").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/school?fq=yearKeys:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-pink w3-hover-pink ").f();
								e("i").a("class", "far fa-school ").f().g("i");
								sx("school");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate a school to this year");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputSchoolKey(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolYearSchoolKey_", classApiMethodMethod).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), School.ROLES)
										|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), School.ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
											.a("id", classApiMethodMethod, "_schoolKey_add")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolVals({ yearKeys: \"", pk, "\" }, function() {}, function() { addError($('#", classApiMethodMethod, "schoolKey')); });")
											.f().sx("add a school")
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

	/////////////
	// yearKey //
	/////////////

	/**	L'entité « yearKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long yearKey;
	@JsonIgnore
	public Wrap<Long> yearKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("yearKey").o(yearKey);

	/**	<br/>L'entité « yearKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearKey">Trouver l'entité yearKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearKey(Wrap<Long> c);

	public Long getYearKey() {
		return yearKey;
	}

	public void setYearKey(Long yearKey) {
		this.yearKey = yearKey;
		this.yearKeyWrap.alreadyInitialized = true;
	}
	public SchoolYear setYearKey(String o) {
		if(NumberUtils.isParsable(o))
			this.yearKey = Long.parseLong(o);
		this.yearKeyWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	protected SchoolYear yearKeyInit() {
		if(!yearKeyWrap.alreadyInitialized) {
			_yearKey(yearKeyWrap);
			if(yearKey == null)
				setYearKey(yearKeyWrap.o);
		}
		yearKeyWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public Long solrYearKey() {
		return yearKey;
	}

	public String strYearKey() {
		return yearKey == null ? "" : yearKey.toString();
	}

	public String jsonYearKey() {
		return yearKey == null ? "" : yearKey.toString();
	}

	public String nomAffichageYearKey() {
		return "key";
	}

	public String htmTooltipYearKey() {
		return null;
	}

	public String htmYearKey() {
		return yearKey == null ? "" : StringEscapeUtils.escapeHtml4(strYearKey());
	}

	////////////////////
	// enrollmentKeys //
	////////////////////

	/**	L'entité « enrollmentKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> enrollmentKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> enrollmentKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("enrollmentKeys").o(enrollmentKeys);

	/**	<br/>L'entité « enrollmentKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentKeys">Trouver l'entité enrollmentKeys dans Solr</a>
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
	public SchoolYear addEnrollmentKeys(Long...objets) {
		for(Long o : objets) {
			addEnrollmentKeys(o);
		}
		return (SchoolYear)this;
	}
	public SchoolYear addEnrollmentKeys(Long o) {
		if(o != null && !enrollmentKeys.contains(o))
			this.enrollmentKeys.add(o);
		return (SchoolYear)this;
	}
	public SchoolYear setEnrollmentKeys(JsonArray objets) {
		enrollmentKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addEnrollmentKeys(o);
		}
		return (SchoolYear)this;
	}
	public SchoolYear addEnrollmentKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addEnrollmentKeys(p);
		}
		return (SchoolYear)this;
	}
	protected SchoolYear enrollmentKeysInit() {
		if(!enrollmentKeysWrap.alreadyInitialized) {
			_enrollmentKeys(enrollmentKeys);
		}
		enrollmentKeysWrap.alreadyInitialized(true);
		return (SchoolYear)this;
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
		return null;
	}

	public String htmTooltipEnrollmentKeys() {
		return null;
	}

	public String htmEnrollmentKeys() {
		return enrollmentKeys == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentKeys());
	}

	////////////////
	// seasonKeys //
	////////////////

	/**	L'entité « seasonKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> seasonKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> seasonKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("seasonKeys").o(seasonKeys);

	/**	<br/>L'entité « seasonKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:seasonKeys">Trouver l'entité seasonKeys dans Solr</a>
	 * <br/>
	 * @param seasonKeys est l'entité déjà construit. 
	 **/
	protected abstract void _seasonKeys(List<Long> o);

	public List<Long> getSeasonKeys() {
		return seasonKeys;
	}

	public void setSeasonKeys(List<Long> seasonKeys) {
		this.seasonKeys = seasonKeys;
		this.seasonKeysWrap.alreadyInitialized = true;
	}
	public SchoolYear addSeasonKeys(Long...objets) {
		for(Long o : objets) {
			addSeasonKeys(o);
		}
		return (SchoolYear)this;
	}
	public SchoolYear addSeasonKeys(Long o) {
		if(o != null && !seasonKeys.contains(o))
			this.seasonKeys.add(o);
		return (SchoolYear)this;
	}
	public SchoolYear setSeasonKeys(JsonArray objets) {
		seasonKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addSeasonKeys(o);
		}
		return (SchoolYear)this;
	}
	public SchoolYear addSeasonKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addSeasonKeys(p);
		}
		return (SchoolYear)this;
	}
	protected SchoolYear seasonKeysInit() {
		if(!seasonKeysWrap.alreadyInitialized) {
			_seasonKeys(seasonKeys);
		}
		seasonKeysWrap.alreadyInitialized(true);
		return (SchoolYear)this;
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

	/////////////
	// ageKeys //
	/////////////

	/**	L'entité « ageKeys »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 */
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected List<Long> ageKeys = new ArrayList<Long>();
	@JsonIgnore
	public Wrap<List<Long>> ageKeysWrap = new Wrap<List<Long>>().p(this).c(List.class).var("ageKeys").o(ageKeys);

	/**	<br/>L'entité « ageKeys »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<Long>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:ageKeys">Trouver l'entité ageKeys dans Solr</a>
	 * <br/>
	 * @param ageKeys est l'entité déjà construit. 
	 **/
	protected abstract void _ageKeys(List<Long> o);

	public List<Long> getAgeKeys() {
		return ageKeys;
	}

	public void setAgeKeys(List<Long> ageKeys) {
		this.ageKeys = ageKeys;
		this.ageKeysWrap.alreadyInitialized = true;
	}
	public SchoolYear addAgeKeys(Long...objets) {
		for(Long o : objets) {
			addAgeKeys(o);
		}
		return (SchoolYear)this;
	}
	public SchoolYear addAgeKeys(Long o) {
		if(o != null && !ageKeys.contains(o))
			this.ageKeys.add(o);
		return (SchoolYear)this;
	}
	public SchoolYear setAgeKeys(JsonArray objets) {
		ageKeys.clear();
		for(int i = 0; i < objets.size(); i++) {
			Long o = objets.getLong(i);
			addAgeKeys(o);
		}
		return (SchoolYear)this;
	}
	public SchoolYear addAgeKeys(String o) {
		if(NumberUtils.isParsable(o)) {
			Long p = Long.parseLong(o);
			addAgeKeys(p);
		}
		return (SchoolYear)this;
	}
	protected SchoolYear ageKeysInit() {
		if(!ageKeysWrap.alreadyInitialized) {
			_ageKeys(ageKeys);
		}
		ageKeysWrap.alreadyInitialized(true);
		return (SchoolYear)this;
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

	public void inputAgeKeys(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{
			e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
				e("input")
					.a("type", "text")
					.a("placeholder", "ages")
					.a("class", "valueObjectSuggest suggestAgeKeys w3-input w3-border w3-cell w3-cell-middle ")
					.a("name", "setAgeKeys")
					.a("id", classApiMethodMethod, "_ageKeys")
					.a("autocomplete", "off")
					.a("oninput", "suggestSchoolYearAgeKeys($(this).val() ? searchSchoolAgeFilters($(this.parentElement)) : [", pk == null ? "" : "{'name':'fq','value':'yearKey:" + pk + "'}", "], $('#listSchoolYearAgeKeys_", classApiMethodMethod, "'), ", pk, "); ")
				.fg();

		}
	}

	public void htmAgeKeys(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolYearAgeKeys").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "/age?fq=yearKey:", pk).a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-blue w3-hover-blue ").f();
								e("i").a("class", "fad fa-birthday-cake ").f().g("i");
								sx("ages");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relate ages to this year");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								inputAgeKeys(classApiMethodMethod);
								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listSchoolYearAgeKeys_", classApiMethodMethod).f();
								} g("ul");
								if(
										CollectionUtils.containsAny(siteRequest_.getUserResourceRoles(), SchoolAge.ROLES)
										|| CollectionUtils.containsAny(siteRequest_.getUserRealmRoles(), SchoolAge.ROLES)
										) {
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-blue ")
											.a("id", classApiMethodMethod, "_ageKeys_add")
											.a("onclick", "$(this).addClass('w3-disabled'); this.disabled = true; this.innerHTML = 'Sending…'; postSchoolAgeVals({ yearKey: \"", pk, "\" }, function() {}, function() { addError($('#", classApiMethodMethod, "ageKeys')); });")
											.f().sx("add an age")
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

	///////////////////
	// educationSort //
	///////////////////

	/**	L'entité « educationSort »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer educationSort;
	@JsonIgnore
	public Wrap<Integer> educationSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("educationSort").o(educationSort);

	/**	<br/>L'entité « educationSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:educationSort">Trouver l'entité educationSort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _educationSort(Wrap<Integer> c);

	public Integer getEducationSort() {
		return educationSort;
	}

	public void setEducationSort(Integer educationSort) {
		this.educationSort = educationSort;
		this.educationSortWrap.alreadyInitialized = true;
	}
	public SchoolYear setEducationSort(String o) {
		if(NumberUtils.isParsable(o))
			this.educationSort = Integer.parseInt(o);
		this.educationSortWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	protected SchoolYear educationSortInit() {
		if(!educationSortWrap.alreadyInitialized) {
			_educationSort(educationSortWrap);
			if(educationSort == null)
				setEducationSort(educationSortWrap.o);
		}
		educationSortWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public Integer solrEducationSort() {
		return educationSort;
	}

	public String strEducationSort() {
		return educationSort == null ? "" : educationSort.toString();
	}

	public String jsonEducationSort() {
		return educationSort == null ? "" : educationSort.toString();
	}

	public String nomAffichageEducationSort() {
		return null;
	}

	public String htmTooltipEducationSort() {
		return null;
	}

	public String htmEducationSort() {
		return educationSort == null ? "" : StringEscapeUtils.escapeHtml4(strEducationSort());
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
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSort">Trouver l'entité schoolSort dans Solr</a>
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
	public SchoolYear setSchoolSort(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolSort = Integer.parseInt(o);
		this.schoolSortWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	protected SchoolYear schoolSortInit() {
		if(!schoolSortWrap.alreadyInitialized) {
			_schoolSort(schoolSortWrap);
			if(schoolSort == null)
				setSchoolSort(schoolSortWrap.o);
		}
		schoolSortWrap.alreadyInitialized(true);
		return (SchoolYear)this;
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

	//////////////
	// yearSort //
	//////////////

	/**	L'entité « yearSort »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer yearSort;
	@JsonIgnore
	public Wrap<Integer> yearSortWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearSort").o(yearSort);

	/**	<br/>L'entité « yearSort »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearSort">Trouver l'entité yearSort dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearSort(Wrap<Integer> c);

	public Integer getYearSort() {
		return yearSort;
	}

	public void setYearSort(Integer yearSort) {
		this.yearSort = yearSort;
		this.yearSortWrap.alreadyInitialized = true;
	}
	public SchoolYear setYearSort(String o) {
		if(NumberUtils.isParsable(o))
			this.yearSort = Integer.parseInt(o);
		this.yearSortWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	protected SchoolYear yearSortInit() {
		if(!yearSortWrap.alreadyInitialized) {
			_yearSort(yearSortWrap);
			if(yearSort == null)
				setYearSort(yearSortWrap.o);
		}
		yearSortWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public Integer solrYearSort() {
		return yearSort;
	}

	public String strYearSort() {
		return yearSort == null ? "" : yearSort.toString();
	}

	public String jsonYearSort() {
		return yearSort == null ? "" : yearSort.toString();
	}

	public String nomAffichageYearSort() {
		return null;
	}

	public String htmTooltipYearSort() {
		return null;
	}

	public String htmYearSort() {
		return yearSort == null ? "" : StringEscapeUtils.escapeHtml4(strYearSort());
	}

	//////////////////
	// schoolSearch //
	//////////////////

	/**	L'entité « schoolSearch »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<School>(). 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected SearchList<School> schoolSearch = new SearchList<School>();
	@JsonIgnore
	public Wrap<SearchList<School>> schoolSearchWrap = new Wrap<SearchList<School>>().p(this).c(SearchList.class).var("schoolSearch").o(schoolSearch);

	/**	<br/>L'entité « schoolSearch »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut SearchList<School>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolSearch">Trouver l'entité schoolSearch dans Solr</a>
	 * <br/>
	 * @param schoolSearch est l'entité déjà construit. 
	 **/
	protected abstract void _schoolSearch(SearchList<School> l);

	public SearchList<School> getSchoolSearch() {
		return schoolSearch;
	}

	public void setSchoolSearch(SearchList<School> schoolSearch) {
		this.schoolSearch = schoolSearch;
		this.schoolSearchWrap.alreadyInitialized = true;
	}
	protected SchoolYear schoolSearchInit() {
		if(!schoolSearchWrap.alreadyInitialized) {
			_schoolSearch(schoolSearch);
		}
		schoolSearch.initDeepForClass(siteRequest_);
		schoolSearchWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	/////////////
	// school_ //
	/////////////

	/**	L'entité « school_ »
	 *	 is defined as null before being initialized. 
	 */
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	protected School school_;
	@JsonIgnore
	public Wrap<School> school_Wrap = new Wrap<School>().p(this).c(School.class).var("school_").o(school_);

	/**	<br/>L'entité « school_ »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:school_">Trouver l'entité school_ dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _school_(Wrap<School> c);

	public School getSchool_() {
		return school_;
	}

	public void setSchool_(School school_) {
		this.school_ = school_;
		this.school_Wrap.alreadyInitialized = true;
	}
	protected SchoolYear school_Init() {
		if(!school_Wrap.alreadyInitialized) {
			_school_(school_Wrap);
			if(school_ == null)
				setSchool_(school_Wrap.o);
		}
		school_Wrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	////////////////
	// schoolName //
	////////////////

	/**	L'entité « schoolName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolName;
	@JsonIgnore
	public Wrap<String> schoolNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolName").o(schoolName);

	/**	<br/>L'entité « schoolName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolName">Trouver l'entité schoolName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolName(Wrap<String> c);

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
		this.schoolNameWrap.alreadyInitialized = true;
	}
	protected SchoolYear schoolNameInit() {
		if(!schoolNameWrap.alreadyInitialized) {
			_schoolName(schoolNameWrap);
			if(schoolName == null)
				setSchoolName(schoolNameWrap.o);
		}
		schoolNameWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public String solrSchoolName() {
		return schoolName;
	}

	public String strSchoolName() {
		return schoolName == null ? "" : schoolName;
	}

	public String jsonSchoolName() {
		return schoolName == null ? "" : schoolName;
	}

	public String nomAffichageSchoolName() {
		return "r: EcoleNom";
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

	/**	L'entité « schoolCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolCompleteName;
	@JsonIgnore
	public Wrap<String> schoolCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolCompleteName").o(schoolCompleteName);

	/**	<br/>L'entité « schoolCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolCompleteName">Trouver l'entité schoolCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolCompleteName(Wrap<String> c);

	public String getSchoolCompleteName() {
		return schoolCompleteName;
	}

	public void setSchoolCompleteName(String schoolCompleteName) {
		this.schoolCompleteName = schoolCompleteName;
		this.schoolCompleteNameWrap.alreadyInitialized = true;
	}
	protected SchoolYear schoolCompleteNameInit() {
		if(!schoolCompleteNameWrap.alreadyInitialized) {
			_schoolCompleteName(schoolCompleteNameWrap);
			if(schoolCompleteName == null)
				setSchoolCompleteName(schoolCompleteNameWrap.o);
		}
		schoolCompleteNameWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public String solrSchoolCompleteName() {
		return schoolCompleteName;
	}

	public String strSchoolCompleteName() {
		return schoolCompleteName == null ? "" : schoolCompleteName;
	}

	public String jsonSchoolCompleteName() {
		return schoolCompleteName == null ? "" : schoolCompleteName;
	}

	public String nomAffichageSchoolCompleteName() {
		return "r: EcoleNomComplet";
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

	/**	L'entité « schoolLocation »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolLocation;
	@JsonIgnore
	public Wrap<String> schoolLocationWrap = new Wrap<String>().p(this).c(String.class).var("schoolLocation").o(schoolLocation);

	/**	<br/>L'entité « schoolLocation »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolLocation">Trouver l'entité schoolLocation dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolLocation(Wrap<String> c);

	public String getSchoolLocation() {
		return schoolLocation;
	}

	public void setSchoolLocation(String schoolLocation) {
		this.schoolLocation = schoolLocation;
		this.schoolLocationWrap.alreadyInitialized = true;
	}
	protected SchoolYear schoolLocationInit() {
		if(!schoolLocationWrap.alreadyInitialized) {
			_schoolLocation(schoolLocationWrap);
			if(schoolLocation == null)
				setSchoolLocation(schoolLocationWrap.o);
		}
		schoolLocationWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public String solrSchoolLocation() {
		return schoolLocation;
	}

	public String strSchoolLocation() {
		return schoolLocation == null ? "" : schoolLocation;
	}

	public String jsonSchoolLocation() {
		return schoolLocation == null ? "" : schoolLocation;
	}

	public String nomAffichageSchoolLocation() {
		return "location";
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

	/**	L'entité « schoolAddress »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolAddress;
	@JsonIgnore
	public Wrap<String> schoolAddressWrap = new Wrap<String>().p(this).c(String.class).var("schoolAddress").o(schoolAddress);

	/**	<br/>L'entité « schoolAddress »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAddress">Trouver l'entité schoolAddress dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolAddress(Wrap<String> c);

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
		this.schoolAddressWrap.alreadyInitialized = true;
	}
	protected SchoolYear schoolAddressInit() {
		if(!schoolAddressWrap.alreadyInitialized) {
			_schoolAddress(schoolAddressWrap);
			if(schoolAddress == null)
				setSchoolAddress(schoolAddressWrap.o);
		}
		schoolAddressWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public String solrSchoolAddress() {
		return schoolAddress;
	}

	public String strSchoolAddress() {
		return schoolAddress == null ? "" : schoolAddress;
	}

	public String jsonSchoolAddress() {
		return schoolAddress == null ? "" : schoolAddress;
	}

	public String nomAffichageSchoolAddress() {
		return "address";
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

	/**	L'entité « schoolPhoneNumber »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolPhoneNumber;
	@JsonIgnore
	public Wrap<String> schoolPhoneNumberWrap = new Wrap<String>().p(this).c(String.class).var("schoolPhoneNumber").o(schoolPhoneNumber);

	/**	<br/>L'entité « schoolPhoneNumber »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolPhoneNumber">Trouver l'entité schoolPhoneNumber dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolPhoneNumber(Wrap<String> c);

	public String getSchoolPhoneNumber() {
		return schoolPhoneNumber;
	}

	public void setSchoolPhoneNumber(String schoolPhoneNumber) {
		this.schoolPhoneNumber = schoolPhoneNumber;
		this.schoolPhoneNumberWrap.alreadyInitialized = true;
	}
	protected SchoolYear schoolPhoneNumberInit() {
		if(!schoolPhoneNumberWrap.alreadyInitialized) {
			_schoolPhoneNumber(schoolPhoneNumberWrap);
			if(schoolPhoneNumber == null)
				setSchoolPhoneNumber(schoolPhoneNumberWrap.o);
		}
		schoolPhoneNumberWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public String solrSchoolPhoneNumber() {
		return schoolPhoneNumber;
	}

	public String strSchoolPhoneNumber() {
		return schoolPhoneNumber == null ? "" : schoolPhoneNumber;
	}

	public String jsonSchoolPhoneNumber() {
		return schoolPhoneNumber == null ? "" : schoolPhoneNumber;
	}

	public String nomAffichageSchoolPhoneNumber() {
		return "phone number";
	}

	public String htmTooltipSchoolPhoneNumber() {
		return null;
	}

	public String htmSchoolPhoneNumber() {
		return schoolPhoneNumber == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolPhoneNumber());
	}

	//////////////////
	// schoolNumber //
	//////////////////

	/**	L'entité « schoolNumber »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer schoolNumber;
	@JsonIgnore
	public Wrap<Integer> schoolNumberWrap = new Wrap<Integer>().p(this).c(Integer.class).var("schoolNumber").o(schoolNumber);

	/**	<br/>L'entité « schoolNumber »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolNumber">Trouver l'entité schoolNumber dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolNumber(Wrap<Integer> c);

	public Integer getSchoolNumber() {
		return schoolNumber;
	}

	public void setSchoolNumber(Integer schoolNumber) {
		this.schoolNumber = schoolNumber;
		this.schoolNumberWrap.alreadyInitialized = true;
	}
	public SchoolYear setSchoolNumber(String o) {
		if(NumberUtils.isParsable(o))
			this.schoolNumber = Integer.parseInt(o);
		this.schoolNumberWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	protected SchoolYear schoolNumberInit() {
		if(!schoolNumberWrap.alreadyInitialized) {
			_schoolNumber(schoolNumberWrap);
			if(schoolNumber == null)
				setSchoolNumber(schoolNumberWrap.o);
		}
		schoolNumberWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public Integer solrSchoolNumber() {
		return schoolNumber;
	}

	public String strSchoolNumber() {
		return schoolNumber == null ? "" : schoolNumber.toString();
	}

	public String jsonSchoolNumber() {
		return schoolNumber == null ? "" : schoolNumber.toString();
	}

	public String nomAffichageSchoolNumber() {
		return null;
	}

	public String htmTooltipSchoolNumber() {
		return null;
	}

	public String htmSchoolNumber() {
		return schoolNumber == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolNumber());
	}

	/////////////////////////////
	// schoolAdministratorName //
	/////////////////////////////

	/**	L'entité « schoolAdministratorName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String schoolAdministratorName;
	@JsonIgnore
	public Wrap<String> schoolAdministratorNameWrap = new Wrap<String>().p(this).c(String.class).var("schoolAdministratorName").o(schoolAdministratorName);

	/**	<br/>L'entité « schoolAdministratorName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:schoolAdministratorName">Trouver l'entité schoolAdministratorName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _schoolAdministratorName(Wrap<String> c);

	public String getSchoolAdministratorName() {
		return schoolAdministratorName;
	}

	public void setSchoolAdministratorName(String schoolAdministratorName) {
		this.schoolAdministratorName = schoolAdministratorName;
		this.schoolAdministratorNameWrap.alreadyInitialized = true;
	}
	protected SchoolYear schoolAdministratorNameInit() {
		if(!schoolAdministratorNameWrap.alreadyInitialized) {
			_schoolAdministratorName(schoolAdministratorNameWrap);
			if(schoolAdministratorName == null)
				setSchoolAdministratorName(schoolAdministratorNameWrap.o);
		}
		schoolAdministratorNameWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public String solrSchoolAdministratorName() {
		return schoolAdministratorName;
	}

	public String strSchoolAdministratorName() {
		return schoolAdministratorName == null ? "" : schoolAdministratorName;
	}

	public String jsonSchoolAdministratorName() {
		return schoolAdministratorName == null ? "" : schoolAdministratorName;
	}

	public String nomAffichageSchoolAdministratorName() {
		return "administrator of the school";
	}

	public String htmTooltipSchoolAdministratorName() {
		return null;
	}

	public String htmSchoolAdministratorName() {
		return schoolAdministratorName == null ? "" : StringEscapeUtils.escapeHtml4(strSchoolAdministratorName());
	}

	///////////////////////
	// enrollmentFormKey //
	///////////////////////

	/**	L'entité « enrollmentFormKey »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Long enrollmentFormKey;
	@JsonIgnore
	public Wrap<Long> enrollmentFormKeyWrap = new Wrap<Long>().p(this).c(Long.class).var("enrollmentFormKey").o(enrollmentFormKey);

	/**	<br/>L'entité « enrollmentFormKey »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:enrollmentFormKey">Trouver l'entité enrollmentFormKey dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _enrollmentFormKey(Wrap<Long> c);

	public Long getEnrollmentFormKey() {
		return enrollmentFormKey;
	}

	public void setEnrollmentFormKey(Long enrollmentFormKey) {
		this.enrollmentFormKey = enrollmentFormKey;
		this.enrollmentFormKeyWrap.alreadyInitialized = true;
	}
	public SchoolYear setEnrollmentFormKey(String o) {
		if(NumberUtils.isParsable(o))
			this.enrollmentFormKey = Long.parseLong(o);
		this.enrollmentFormKeyWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	protected SchoolYear enrollmentFormKeyInit() {
		if(!enrollmentFormKeyWrap.alreadyInitialized) {
			_enrollmentFormKey(enrollmentFormKeyWrap);
			if(enrollmentFormKey == null)
				setEnrollmentFormKey(enrollmentFormKeyWrap.o);
		}
		enrollmentFormKeyWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public Long solrEnrollmentFormKey() {
		return enrollmentFormKey;
	}

	public String strEnrollmentFormKey() {
		return enrollmentFormKey == null ? "" : enrollmentFormKey.toString();
	}

	public String jsonEnrollmentFormKey() {
		return enrollmentFormKey == null ? "" : enrollmentFormKey.toString();
	}

	public String nomAffichageEnrollmentFormKey() {
		return "enrollment form";
	}

	public String htmTooltipEnrollmentFormKey() {
		return null;
	}

	public String htmEnrollmentFormKey() {
		return enrollmentFormKey == null ? "" : StringEscapeUtils.escapeHtml4(strEnrollmentFormKey());
	}

	//////////////////////
	// sessionStartDate //
	//////////////////////

	/**	L'entité « sessionStartDate »
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate sessionStartDate;
	@JsonIgnore
	public Wrap<LocalDate> sessionStartDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("sessionStartDate").o(sessionStartDate);

	/**	<br/>L'entité « sessionStartDate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionStartDate">Trouver l'entité sessionStartDate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionStartDate(Wrap<LocalDate> c);

	public LocalDate getSessionStartDate() {
		return sessionStartDate;
	}

	public void setSessionStartDate(LocalDate sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
		this.sessionStartDateWrap.alreadyInitialized = true;
	}
	public SchoolYear setSessionStartDate(Instant o) {
		this.sessionStartDate = o == null ? null : LocalDate.from(o);
		this.sessionStartDateWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolYear setSessionStartDate(String o) {
		this.sessionStartDate = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.sessionStartDateWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	public SchoolYear setSessionStartDate(Date o) {
		this.sessionStartDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionStartDateWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	protected SchoolYear sessionStartDateInit() {
		if(!sessionStartDateWrap.alreadyInitialized) {
			_sessionStartDate(sessionStartDateWrap);
			if(sessionStartDate == null)
				setSessionStartDate(sessionStartDateWrap.o);
		}
		sessionStartDateWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public Date solrSessionStartDate() {
		return sessionStartDate == null ? null : Date.from(sessionStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strSessionStartDate() {
		return sessionStartDate == null ? "" : sessionStartDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonSessionStartDate() {
		return sessionStartDate == null ? "" : sessionStartDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSessionStartDate() {
		return "start of the session";
	}

	public String htmTooltipSessionStartDate() {
		return null;
	}

	public String htmSessionStartDate() {
		return sessionStartDate == null ? "" : StringEscapeUtils.escapeHtml4(strSessionStartDate());
	}

	public void inputSessionStartDate(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setSessionStartDate classSchoolYear inputSchoolYear", pk, "SessionStartDate w3-input w3-border ")
				.a("placeholder", "MM/DD/YYYY")
				.a("data-timeformat", "MM/dd/yyyy")
				.a("id", classApiMethodMethod, "_sessionStartDate")
				.a("onclick", "removeGlow($(this)); ")
				.a("value", sessionStartDate == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(sessionStartDate))
				.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSessionStartDate', s, function() { addGlow($('#", classApiMethodMethod, "_sessionStartDate')); }, function() { addError($('#", classApiMethodMethod, "_sessionStartDate')); }); } ")
				.fg();
		}
	}

	public void htmSessionStartDate(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolYearSessionStartDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classApiMethodMethod, "_sessionStartDate").a("class", "").f().sx("start of the session").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputSessionStartDate(classApiMethodMethod);
							} g("div");
							{
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_sessionStartDate')); $('#", classApiMethodMethod, "_sessionStartDate').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolYearForm :input[name=pk]').val() }], 'setSessionStartDate', null, function() { addGlow($('#", classApiMethodMethod, "_sessionStartDate')); }, function() { addError($('#", classApiMethodMethod, "_sessionStartDate')); }); ")
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
	// sessionEndDate //
	////////////////////

	/**	L'entité « sessionEndDate »
	 *	 is defined as null before being initialized. 
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonInclude(Include.NON_NULL)
	protected LocalDate sessionEndDate;
	@JsonIgnore
	public Wrap<LocalDate> sessionEndDateWrap = new Wrap<LocalDate>().p(this).c(LocalDate.class).var("sessionEndDate").o(sessionEndDate);

	/**	<br/>L'entité « sessionEndDate »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:sessionEndDate">Trouver l'entité sessionEndDate dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _sessionEndDate(Wrap<LocalDate> c);

	public LocalDate getSessionEndDate() {
		return sessionEndDate;
	}

	public void setSessionEndDate(LocalDate sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
		this.sessionEndDateWrap.alreadyInitialized = true;
	}
	public SchoolYear setSessionEndDate(Instant o) {
		this.sessionEndDate = o == null ? null : LocalDate.from(o);
		this.sessionEndDateWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	/** Example: 2011-12-03+01:00 **/
	public SchoolYear setSessionEndDate(String o) {
		this.sessionEndDate = o == null ? null : LocalDate.parse(o, DateTimeFormatter.ISO_DATE);
		this.sessionEndDateWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	public SchoolYear setSessionEndDate(Date o) {
		this.sessionEndDate = o == null ? null : o.toInstant().atZone(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toLocalDate();
		this.sessionEndDateWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	protected SchoolYear sessionEndDateInit() {
		if(!sessionEndDateWrap.alreadyInitialized) {
			_sessionEndDate(sessionEndDateWrap);
			if(sessionEndDate == null)
				setSessionEndDate(sessionEndDateWrap.o);
		}
		sessionEndDateWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public Date solrSessionEndDate() {
		return sessionEndDate == null ? null : Date.from(sessionEndDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z")).toInstant());
	}

	public String strSessionEndDate() {
		return sessionEndDate == null ? "" : sessionEndDate.format(DateTimeFormatter.ofPattern("EEE MMM d, yyyy", Locale.forLanguageTag("en-US")));
	}

	public String jsonSessionEndDate() {
		return sessionEndDate == null ? "" : sessionEndDate.format(DateTimeFormatter.ISO_DATE);
	}

	public String nomAffichageSessionEndDate() {
		return "end of the session";
	}

	public String htmTooltipSessionEndDate() {
		return null;
	}

	public String htmSessionEndDate() {
		return sessionEndDate == null ? "" : StringEscapeUtils.escapeHtml4(strSessionEndDate());
	}

	public void inputSessionEndDate(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{
			e("input")
				.a("type", "text")
				.a("class", "w3-input w3-border datepicker setSessionEndDate classSchoolYear inputSchoolYear", pk, "SessionEndDate w3-input w3-border ")
				.a("placeholder", "MM/DD/YYYY")
				.a("data-timeformat", "MM/dd/yyyy")
				.a("id", classApiMethodMethod, "_sessionEndDate")
				.a("onclick", "removeGlow($(this)); ")
				.a("value", sessionEndDate == null ? "" : DateTimeFormatter.ofPattern("MM/dd/yyyy").format(sessionEndDate))
				.a("onchange", "var t = moment(this.value, 'MM/DD/YYYY'); if(t) { var s = t.format('YYYY-MM-DD'); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setSessionEndDate', s, function() { addGlow($('#", classApiMethodMethod, "_sessionEndDate')); }, function() { addError($('#", classApiMethodMethod, "_sessionEndDate')); }); } ")
				.fg();
		}
	}

	public void htmSessionEndDate(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolYearSessionEndDate").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classApiMethodMethod, "_sessionEndDate").a("class", "").f().sx("end of the session").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								inputSessionEndDate(classApiMethodMethod);
							} g("div");
							{
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_sessionEndDate')); $('#", classApiMethodMethod, "_sessionEndDate').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolYearForm :input[name=pk]').val() }], 'setSessionEndDate', null, function() { addGlow($('#", classApiMethodMethod, "_sessionEndDate')); }, function() { addError($('#", classApiMethodMethod, "_sessionEndDate')); }); ")
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
	// yearStart //
	///////////////

	/**	L'entité « yearStart »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer yearStart;
	@JsonIgnore
	public Wrap<Integer> yearStartWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearStart").o(yearStart);

	/**	<br/>L'entité « yearStart »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearStart">Trouver l'entité yearStart dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearStart(Wrap<Integer> c);

	public Integer getYearStart() {
		return yearStart;
	}

	public void setYearStart(Integer yearStart) {
		this.yearStart = yearStart;
		this.yearStartWrap.alreadyInitialized = true;
	}
	public SchoolYear setYearStart(String o) {
		if(NumberUtils.isParsable(o))
			this.yearStart = Integer.parseInt(o);
		this.yearStartWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	protected SchoolYear yearStartInit() {
		if(!yearStartWrap.alreadyInitialized) {
			_yearStart(yearStartWrap);
			if(yearStart == null)
				setYearStart(yearStartWrap.o);
		}
		yearStartWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public Integer solrYearStart() {
		return yearStart;
	}

	public String strYearStart() {
		return yearStart == null ? "" : yearStart.toString();
	}

	public String jsonYearStart() {
		return yearStart == null ? "" : yearStart.toString();
	}

	public String nomAffichageYearStart() {
		return "start of year";
	}

	public String htmTooltipYearStart() {
		return null;
	}

	public String htmYearStart() {
		return yearStart == null ? "" : StringEscapeUtils.escapeHtml4(strYearStart());
	}

	public void inputYearStart(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "start of year")
				.a("id", classApiMethodMethod, "_yearStart");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setYearStart classSchoolYear inputSchoolYear", pk, "YearStart w3-input w3-border ");
					a("name", "setYearStart");
				} else {
					a("class", "valueYearStart w3-input w3-border classSchoolYear inputSchoolYear", pk, "YearStart w3-input w3-border ");
					a("name", "yearStart");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setYearStart', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_yearStart')); }, function() { addError($('#", classApiMethodMethod, "_yearStart')); }); ");
				}
				a("value", strYearStart())
			.fg();

		}
	}

	public void htmYearStart(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolYearYearStart").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classApiMethodMethod, "_yearStart").a("class", "").f().sx("start of year").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputYearStart(classApiMethodMethod);
							} g("div");
							{
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_yearStart')); $('#", classApiMethodMethod, "_yearStart').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolYearForm :input[name=pk]').val() }], 'setYearStart', null, function() { addGlow($('#", classApiMethodMethod, "_yearStart')); }, function() { addError($('#", classApiMethodMethod, "_yearStart')); }); ")
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

	/////////////
	// yearEnd //
	/////////////

	/**	L'entité « yearEnd »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected Integer yearEnd;
	@JsonIgnore
	public Wrap<Integer> yearEndWrap = new Wrap<Integer>().p(this).c(Integer.class).var("yearEnd").o(yearEnd);

	/**	<br/>L'entité « yearEnd »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnd">Trouver l'entité yearEnd dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearEnd(Wrap<Integer> c);

	public Integer getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(Integer yearEnd) {
		this.yearEnd = yearEnd;
		this.yearEndWrap.alreadyInitialized = true;
	}
	public SchoolYear setYearEnd(String o) {
		if(NumberUtils.isParsable(o))
			this.yearEnd = Integer.parseInt(o);
		this.yearEndWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	protected SchoolYear yearEndInit() {
		if(!yearEndWrap.alreadyInitialized) {
			_yearEnd(yearEndWrap);
			if(yearEnd == null)
				setYearEnd(yearEndWrap.o);
		}
		yearEndWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public Integer solrYearEnd() {
		return yearEnd;
	}

	public String strYearEnd() {
		return yearEnd == null ? "" : yearEnd.toString();
	}

	public String jsonYearEnd() {
		return yearEnd == null ? "" : yearEnd.toString();
	}

	public String nomAffichageYearEnd() {
		return "end of year";
	}

	public String htmTooltipYearEnd() {
		return null;
	}

	public String htmYearEnd() {
		return yearEnd == null ? "" : StringEscapeUtils.escapeHtml4(strYearEnd());
	}

	public void inputYearEnd(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "end of year")
				.a("id", classApiMethodMethod, "_yearEnd");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setYearEnd classSchoolYear inputSchoolYear", pk, "YearEnd w3-input w3-border ");
					a("name", "setYearEnd");
				} else {
					a("class", "valueYearEnd w3-input w3-border classSchoolYear inputSchoolYear", pk, "YearEnd w3-input w3-border ");
					a("name", "yearEnd");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setYearEnd', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_yearEnd')); }, function() { addError($('#", classApiMethodMethod, "_yearEnd')); }); ");
				}
				a("value", strYearEnd())
			.fg();

		}
	}

	public void htmYearEnd(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolYearYearEnd").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classApiMethodMethod, "_yearEnd").a("class", "").f().sx("end of year").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputYearEnd(classApiMethodMethod);
							} g("div");
							{
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_yearEnd')); $('#", classApiMethodMethod, "_yearEnd').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolYearForm :input[name=pk]').val() }], 'setYearEnd', null, function() { addGlow($('#", classApiMethodMethod, "_yearEnd')); }, function() { addError($('#", classApiMethodMethod, "_yearEnd')); }); ")
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
	// yearEnrollmentFee //
	///////////////////////

	/**	L'entité « yearEnrollmentFee »
	 *	 is defined as null before being initialized. 
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@JsonInclude(Include.NON_NULL)
	protected BigDecimal yearEnrollmentFee;
	@JsonIgnore
	public Wrap<BigDecimal> yearEnrollmentFeeWrap = new Wrap<BigDecimal>().p(this).c(BigDecimal.class).var("yearEnrollmentFee").o(yearEnrollmentFee);

	/**	<br/>L'entité « yearEnrollmentFee »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearEnrollmentFee">Trouver l'entité yearEnrollmentFee dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearEnrollmentFee(Wrap<BigDecimal> c);

	public BigDecimal getYearEnrollmentFee() {
		return yearEnrollmentFee;
	}

	public void setYearEnrollmentFee(BigDecimal yearEnrollmentFee) {
		this.yearEnrollmentFee = yearEnrollmentFee;
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
	}
	public SchoolYear setYearEnrollmentFee(String o) {
		o = StringUtils.removeAll(o, "[^\\d\\.]");
		if(NumberUtils.isParsable(o))
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	public SchoolYear setYearEnrollmentFee(Double o) {
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	public SchoolYear setYearEnrollmentFee(Integer o) {
			this.yearEnrollmentFee = new BigDecimal(o, MathContext.DECIMAL64).setScale(2);
		this.yearEnrollmentFeeWrap.alreadyInitialized = true;
		return (SchoolYear)this;
	}
	protected SchoolYear yearEnrollmentFeeInit() {
		if(!yearEnrollmentFeeWrap.alreadyInitialized) {
			_yearEnrollmentFee(yearEnrollmentFeeWrap);
			if(yearEnrollmentFee == null)
				setYearEnrollmentFee(yearEnrollmentFeeWrap.o);
		}
		yearEnrollmentFeeWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public Double solrYearEnrollmentFee() {
		return yearEnrollmentFee == null ? null : yearEnrollmentFee.doubleValue();
	}

	public String strYearEnrollmentFee() {
		return yearEnrollmentFee == null ? "" : yearEnrollmentFee.setScale(2).toString();
	}

	public String jsonYearEnrollmentFee() {
		return yearEnrollmentFee == null ? "" : yearEnrollmentFee.toString();
	}

	public String nomAffichageYearEnrollmentFee() {
		return "enrollment fee";
	}

	public String htmTooltipYearEnrollmentFee() {
		return null;
	}

	public String htmYearEnrollmentFee() {
		return yearEnrollmentFee == null ? "" : StringEscapeUtils.escapeHtml4(strYearEnrollmentFee());
	}

	public void inputYearEnrollmentFee(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{
			e("input")
				.a("type", "text")
				.a("placeholder", "enrollment fee")
				.a("id", classApiMethodMethod, "_yearEnrollmentFee");
				if("Page".equals(classApiMethodMethod) || "PATCH".equals(classApiMethodMethod)) {
					a("class", "setYearEnrollmentFee classSchoolYear inputSchoolYear", pk, "YearEnrollmentFee w3-input w3-border ");
					a("name", "setYearEnrollmentFee");
				} else {
					a("class", "valueYearEnrollmentFee w3-input w3-border classSchoolYear inputSchoolYear", pk, "YearEnrollmentFee w3-input w3-border ");
					a("name", "yearEnrollmentFee");
				}
				if("Page".equals(classApiMethodMethod)) {
					a("onclick", "removeGlow($(this)); ");
					a("onchange", "patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:", pk, "' }], 'setYearEnrollmentFee', $(this).val(), function() { addGlow($('#", classApiMethodMethod, "_yearEnrollmentFee')); }, function() { addError($('#", classApiMethodMethod, "_yearEnrollmentFee')); }); ");
				}
				a("value", strYearEnrollmentFee())
			.fg();

		}
	}

	public void htmYearEnrollmentFee(String classApiMethodMethod) {
		SchoolYear s = (SchoolYear)this;
		{ e("div").a("class", "w3-cell w3-cell-top w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("id", "suggest", classApiMethodMethod, "SchoolYearYearEnrollmentFee").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-orange ").f();
							e("label").a("for", classApiMethodMethod, "_yearEnrollmentFee").a("class", "").f().sx("enrollment fee").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								inputYearEnrollmentFee(classApiMethodMethod);
							} g("div");
							{
								if("Page".equals(classApiMethodMethod)) {
									{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
										{ e("button")
											.a("tabindex", "-1")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-orange ")
										.a("onclick", "removeGlow($('#", classApiMethodMethod, "_yearEnrollmentFee')); $('#", classApiMethodMethod, "_yearEnrollmentFee').val(null); patch", getClass().getSimpleName(), "Val([{ name: 'fq', value: 'pk:' + $('#SchoolYearForm :input[name=pk]').val() }], 'setYearEnrollmentFee', null, function() { addGlow($('#", classApiMethodMethod, "_yearEnrollmentFee')); }, function() { addError($('#", classApiMethodMethod, "_yearEnrollmentFee')); }); ")
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
	// yearYears //
	///////////////

	/**	L'entité « yearYears »
	 *	Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolYear>(). 
	 */
	@JsonInclude(Include.NON_NULL)
	protected List<SchoolYear> yearYears = new ArrayList<SchoolYear>();
	@JsonIgnore
	public Wrap<List<SchoolYear>> yearYearsWrap = new Wrap<List<SchoolYear>>().p(this).c(List.class).var("yearYears").o(yearYears);

	/**	<br/>L'entité « yearYears »
	 * Il est construit avant d'être initialisé avec le constructeur par défaut List<SchoolYear>(). 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearYears">Trouver l'entité yearYears dans Solr</a>
	 * <br/>
	 * @param yearYears est l'entité déjà construit. 
	 **/
	protected abstract void _yearYears(List<SchoolYear> l);

	public List<SchoolYear> getYearYears() {
		return yearYears;
	}

	public void setYearYears(List<SchoolYear> yearYears) {
		this.yearYears = yearYears;
		this.yearYearsWrap.alreadyInitialized = true;
	}
	public SchoolYear addYearYears(SchoolYear...objets) {
		for(SchoolYear o : objets) {
			addYearYears(o);
		}
		return (SchoolYear)this;
	}
	public SchoolYear addYearYears(SchoolYear o) {
		if(o != null && !yearYears.contains(o))
			this.yearYears.add(o);
		return (SchoolYear)this;
	}
	protected SchoolYear yearYearsInit() {
		if(!yearYearsWrap.alreadyInitialized) {
			_yearYears(yearYears);
		}
		yearYearsWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	///////////////////
	// yearShortName //
	///////////////////

	/**	L'entité « yearShortName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String yearShortName;
	@JsonIgnore
	public Wrap<String> yearShortNameWrap = new Wrap<String>().p(this).c(String.class).var("yearShortName").o(yearShortName);

	/**	<br/>L'entité « yearShortName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearShortName">Trouver l'entité yearShortName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearShortName(Wrap<String> c);

	public String getYearShortName() {
		return yearShortName;
	}

	public void setYearShortName(String yearShortName) {
		this.yearShortName = yearShortName;
		this.yearShortNameWrap.alreadyInitialized = true;
	}
	protected SchoolYear yearShortNameInit() {
		if(!yearShortNameWrap.alreadyInitialized) {
			_yearShortName(yearShortNameWrap);
			if(yearShortName == null)
				setYearShortName(yearShortNameWrap.o);
		}
		yearShortNameWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public String solrYearShortName() {
		return yearShortName;
	}

	public String strYearShortName() {
		return yearShortName == null ? "" : yearShortName;
	}

	public String jsonYearShortName() {
		return yearShortName == null ? "" : yearShortName;
	}

	public String nomAffichageYearShortName() {
		return null;
	}

	public String htmTooltipYearShortName() {
		return null;
	}

	public String htmYearShortName() {
		return yearShortName == null ? "" : StringEscapeUtils.escapeHtml4(strYearShortName());
	}

	//////////////////////
	// yearCompleteName //
	//////////////////////

	/**	L'entité « yearCompleteName »
	 *	 is defined as null before being initialized. 
	 */
	@JsonInclude(Include.NON_NULL)
	protected String yearCompleteName;
	@JsonIgnore
	public Wrap<String> yearCompleteNameWrap = new Wrap<String>().p(this).c(String.class).var("yearCompleteName").o(yearCompleteName);

	/**	<br/>L'entité « yearCompleteName »
	 *  est défini comme null avant d'être initialisé. 
	 * <br/><a href="http://localhost:10383/solr/computate/select?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.scolaire.enUS.year.SchoolYear&fq=classeEtendGen_indexed_boolean:true&fq=entiteVar_enUS_indexed_string:yearCompleteName">Trouver l'entité yearCompleteName dans Solr</a>
	 * <br/>
	 * @param c est pour envelopper une valeur à assigner à cette entité lors de l'initialisation. 
	 **/
	protected abstract void _yearCompleteName(Wrap<String> c);

	public String getYearCompleteName() {
		return yearCompleteName;
	}

	public void setYearCompleteName(String yearCompleteName) {
		this.yearCompleteName = yearCompleteName;
		this.yearCompleteNameWrap.alreadyInitialized = true;
	}
	protected SchoolYear yearCompleteNameInit() {
		if(!yearCompleteNameWrap.alreadyInitialized) {
			_yearCompleteName(yearCompleteNameWrap);
			if(yearCompleteName == null)
				setYearCompleteName(yearCompleteNameWrap.o);
		}
		yearCompleteNameWrap.alreadyInitialized(true);
		return (SchoolYear)this;
	}

	public String solrYearCompleteName() {
		return yearCompleteName;
	}

	public String strYearCompleteName() {
		return yearCompleteName == null ? "" : yearCompleteName;
	}

	public String jsonYearCompleteName() {
		return yearCompleteName == null ? "" : yearCompleteName;
	}

	public String nomAffichageYearCompleteName() {
		return null;
	}

	public String htmTooltipYearCompleteName() {
		return null;
	}

	public String htmYearCompleteName() {
		return yearCompleteName == null ? "" : StringEscapeUtils.escapeHtml4(strYearCompleteName());
	}

	//////////////
	// initDeep //
	//////////////

	protected boolean alreadyInitializedSchoolYear = false;

	public SchoolYear initDeepSchoolYear(SiteRequestEnUS siteRequest_) {
		setSiteRequest_(siteRequest_);
		if(!alreadyInitializedSchoolYear) {
			alreadyInitializedSchoolYear = true;
			initDeepSchoolYear();
		}
		return (SchoolYear)this;
	}

	public void initDeepSchoolYear() {
		initSchoolYear();
		super.initDeepCluster(siteRequest_);
	}

	public void initSchoolYear() {
		schoolKeyInit();
		yearKeyInit();
		enrollmentKeysInit();
		seasonKeysInit();
		ageKeysInit();
		educationSortInit();
		schoolSortInit();
		yearSortInit();
		schoolSearchInit();
		school_Init();
		schoolNameInit();
		schoolCompleteNameInit();
		schoolLocationInit();
		schoolAddressInit();
		schoolPhoneNumberInit();
		schoolNumberInit();
		schoolAdministratorNameInit();
		enrollmentFormKeyInit();
		sessionStartDateInit();
		sessionEndDateInit();
		yearStartInit();
		yearEndInit();
		yearEnrollmentFeeInit();
		yearYearsInit();
		yearShortNameInit();
		yearCompleteNameInit();
	}

	@Override public void initDeepForClass(SiteRequestEnUS siteRequest_) {
		initDeepSchoolYear(siteRequest_);
	}

	/////////////////
	// siteRequest //
	/////////////////

	public void siteRequestSchoolYear(SiteRequestEnUS siteRequest_) {
			super.siteRequestCluster(siteRequest_);
		if(schoolSearch != null)
			schoolSearch.setSiteRequest_(siteRequest_);
	}

	public void siteRequestForClass(SiteRequestEnUS siteRequest_) {
		siteRequestSchoolYear(siteRequest_);
	}

	/////////////
	// obtain //
	/////////////

	@Override public Object obtainForClass(String var) {
		String[] vars = StringUtils.split(var, ".");
		Object o = null;
		for(String v : vars) {
			if(o == null)
				o = obtainSchoolYear(v);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.obtainForClass(v);
			}
		}
		return o;
	}
	public Object obtainSchoolYear(String var) {
		SchoolYear oSchoolYear = (SchoolYear)this;
		switch(var) {
			case "schoolKey":
				return oSchoolYear.schoolKey;
			case "yearKey":
				return oSchoolYear.yearKey;
			case "enrollmentKeys":
				return oSchoolYear.enrollmentKeys;
			case "seasonKeys":
				return oSchoolYear.seasonKeys;
			case "ageKeys":
				return oSchoolYear.ageKeys;
			case "educationSort":
				return oSchoolYear.educationSort;
			case "schoolSort":
				return oSchoolYear.schoolSort;
			case "yearSort":
				return oSchoolYear.yearSort;
			case "schoolSearch":
				return oSchoolYear.schoolSearch;
			case "school_":
				return oSchoolYear.school_;
			case "schoolName":
				return oSchoolYear.schoolName;
			case "schoolCompleteName":
				return oSchoolYear.schoolCompleteName;
			case "schoolLocation":
				return oSchoolYear.schoolLocation;
			case "schoolAddress":
				return oSchoolYear.schoolAddress;
			case "schoolPhoneNumber":
				return oSchoolYear.schoolPhoneNumber;
			case "schoolNumber":
				return oSchoolYear.schoolNumber;
			case "schoolAdministratorName":
				return oSchoolYear.schoolAdministratorName;
			case "enrollmentFormKey":
				return oSchoolYear.enrollmentFormKey;
			case "sessionStartDate":
				return oSchoolYear.sessionStartDate;
			case "sessionEndDate":
				return oSchoolYear.sessionEndDate;
			case "yearStart":
				return oSchoolYear.yearStart;
			case "yearEnd":
				return oSchoolYear.yearEnd;
			case "yearEnrollmentFee":
				return oSchoolYear.yearEnrollmentFee;
			case "yearYears":
				return oSchoolYear.yearYears;
			case "yearShortName":
				return oSchoolYear.yearShortName;
			case "yearCompleteName":
				return oSchoolYear.yearCompleteName;
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
				o = attributeSchoolYear(v, val);
			else if(o instanceof Cluster) {
				Cluster cluster = (Cluster)o;
				o = cluster.attributeForClass(v, val);
			}
		}
		return o != null;
	}
	public Object attributeSchoolYear(String var, Object val) {
		SchoolYear oSchoolYear = (SchoolYear)this;
		switch(var) {
			case "schoolKey":
				oSchoolYear.setSchoolKey((Long)val);
				if(!saves.contains(var))
					saves.add(var);
				return val;
			case "ageKeys":
				oSchoolYear.addAgeKeys((Long)val);
				if(!saves.contains(var))
					saves.add(var);
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
					o = defineSchoolYear(v, val);
				else if(o instanceof Cluster) {
					Cluster cluster = (Cluster)o;
					o = cluster.defineForClass(v, val);
				}
			}
		}
		return o != null;
	}
	public Object defineSchoolYear(String var, String val) {
		switch(var) {
			case "sessionStartDate":
				if(val != null)
					setSessionStartDate(val);
				saves.add(var);
				return val;
			case "sessionEndDate":
				if(val != null)
					setSessionEndDate(val);
				saves.add(var);
				return val;
			case "yearStart":
				if(val != null)
					setYearStart(val);
				saves.add(var);
				return val;
			case "yearEnd":
				if(val != null)
					setYearEnd(val);
				saves.add(var);
				return val;
			case "yearEnrollmentFee":
				if(val != null)
					setYearEnrollmentFee(val);
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
		populateSchoolYear(solrDocument);
	}
	public void populateSchoolYear(SolrDocument solrDocument) {
		SchoolYear oSchoolYear = (SchoolYear)this;
		saves = (List<String>)solrDocument.get("saves_stored_strings");
		if(saves != null) {

			Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
			if(schoolKey != null)
				oSchoolYear.setSchoolKey(schoolKey);

			if(saves.contains("yearKey")) {
				Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
				if(yearKey != null)
					oSchoolYear.setYearKey(yearKey);
			}

			if(saves.contains("enrollmentKeys")) {
				List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
				if(enrollmentKeys != null)
					oSchoolYear.enrollmentKeys.addAll(enrollmentKeys);
			}

			if(saves.contains("seasonKeys")) {
				List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
				if(seasonKeys != null)
					oSchoolYear.seasonKeys.addAll(seasonKeys);
			}

			List<Long> ageKeys = (List<Long>)solrDocument.get("ageKeys_stored_longs");
			if(ageKeys != null)
				oSchoolYear.ageKeys.addAll(ageKeys);

			if(saves.contains("educationSort")) {
				Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
				if(educationSort != null)
					oSchoolYear.setEducationSort(educationSort);
			}

			if(saves.contains("schoolSort")) {
				Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
				if(schoolSort != null)
					oSchoolYear.setSchoolSort(schoolSort);
			}

			if(saves.contains("yearSort")) {
				Integer yearSort = (Integer)solrDocument.get("yearSort_stored_int");
				if(yearSort != null)
					oSchoolYear.setYearSort(yearSort);
			}

			if(saves.contains("schoolName")) {
				String schoolName = (String)solrDocument.get("schoolName_stored_string");
				if(schoolName != null)
					oSchoolYear.setSchoolName(schoolName);
			}

			if(saves.contains("schoolCompleteName")) {
				String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
				if(schoolCompleteName != null)
					oSchoolYear.setSchoolCompleteName(schoolCompleteName);
			}

			if(saves.contains("schoolLocation")) {
				String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
				if(schoolLocation != null)
					oSchoolYear.setSchoolLocation(schoolLocation);
			}

			if(saves.contains("schoolAddress")) {
				String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
				if(schoolAddress != null)
					oSchoolYear.setSchoolAddress(schoolAddress);
			}

			if(saves.contains("schoolPhoneNumber")) {
				String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
				if(schoolPhoneNumber != null)
					oSchoolYear.setSchoolPhoneNumber(schoolPhoneNumber);
			}

			if(saves.contains("schoolNumber")) {
				Integer schoolNumber = (Integer)solrDocument.get("schoolNumber_stored_int");
				if(schoolNumber != null)
					oSchoolYear.setSchoolNumber(schoolNumber);
			}

			if(saves.contains("schoolAdministratorName")) {
				String schoolAdministratorName = (String)solrDocument.get("schoolAdministratorName_stored_string");
				if(schoolAdministratorName != null)
					oSchoolYear.setSchoolAdministratorName(schoolAdministratorName);
			}

			if(saves.contains("enrollmentFormKey")) {
				Long enrollmentFormKey = (Long)solrDocument.get("enrollmentFormKey_stored_long");
				if(enrollmentFormKey != null)
					oSchoolYear.setEnrollmentFormKey(enrollmentFormKey);
			}

			if(saves.contains("sessionStartDate")) {
				Date sessionStartDate = (Date)solrDocument.get("sessionStartDate_stored_date");
				if(sessionStartDate != null)
					oSchoolYear.setSessionStartDate(sessionStartDate);
			}

			if(saves.contains("sessionEndDate")) {
				Date sessionEndDate = (Date)solrDocument.get("sessionEndDate_stored_date");
				if(sessionEndDate != null)
					oSchoolYear.setSessionEndDate(sessionEndDate);
			}

			if(saves.contains("yearStart")) {
				Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
				if(yearStart != null)
					oSchoolYear.setYearStart(yearStart);
			}

			if(saves.contains("yearEnd")) {
				Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
				if(yearEnd != null)
					oSchoolYear.setYearEnd(yearEnd);
			}

			if(saves.contains("yearEnrollmentFee")) {
				Double yearEnrollmentFee = (Double)solrDocument.get("yearEnrollmentFee_stored_double");
				if(yearEnrollmentFee != null)
					oSchoolYear.setYearEnrollmentFee(yearEnrollmentFee);
			}

			if(saves.contains("yearShortName")) {
				String yearShortName = (String)solrDocument.get("yearShortName_stored_string");
				if(yearShortName != null)
					oSchoolYear.setYearShortName(yearShortName);
			}

			if(saves.contains("yearCompleteName")) {
				String yearCompleteName = (String)solrDocument.get("yearCompleteName_stored_string");
				if(yearCompleteName != null)
					oSchoolYear.setYearCompleteName(yearCompleteName);
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
			solrQuery.addFilterQuery("id:" + ClientUtils.escapeQueryChars("org.computate.scolaire.enUS.year.SchoolYear"));
			QueryResponse queryResponse = siteRequest.getSiteContext_().getSolrClient().query(solrQuery);
			if(queryResponse.getResults().size() > 0)
				siteRequest.setSolrDocument(queryResponse.getResults().get(0));
			SchoolYear o = new SchoolYear();
			o.siteRequestSchoolYear(siteRequest);
			o.initDeepSchoolYear(siteRequest);
			o.indexSchoolYear();
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}


	@Override public void indexForClass() {
		indexSchoolYear();
	}

	@Override public void indexForClass(SolrInputDocument document) {
		indexSchoolYear(document);
	}

	public void indexSchoolYear(SolrClient clientSolr) {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolYear(document);
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolYear() {
		try {
			SolrInputDocument document = new SolrInputDocument();
			indexSchoolYear(document);
			SolrClient clientSolr = siteRequest_.getSiteContext_().getSolrClient();
			clientSolr.add(document);
			clientSolr.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public void indexSchoolYear(SolrInputDocument document) {
		if(schoolKey != null) {
			document.addField("schoolKey_indexed_long", schoolKey);
			document.addField("schoolKey_stored_long", schoolKey);
		}
		if(yearKey != null) {
			document.addField("yearKey_indexed_long", yearKey);
			document.addField("yearKey_stored_long", yearKey);
		}
		if(enrollmentKeys != null) {
			for(java.lang.Long o : enrollmentKeys) {
				document.addField("enrollmentKeys_indexed_longs", o);
			}
			for(java.lang.Long o : enrollmentKeys) {
				document.addField("enrollmentKeys_stored_longs", o);
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
		if(ageKeys != null) {
			for(java.lang.Long o : ageKeys) {
				document.addField("ageKeys_indexed_longs", o);
			}
			for(java.lang.Long o : ageKeys) {
				document.addField("ageKeys_stored_longs", o);
			}
		}
		if(educationSort != null) {
			document.addField("educationSort_indexed_int", educationSort);
			document.addField("educationSort_stored_int", educationSort);
		}
		if(schoolSort != null) {
			document.addField("schoolSort_indexed_int", schoolSort);
			document.addField("schoolSort_stored_int", schoolSort);
		}
		if(yearSort != null) {
			document.addField("yearSort_indexed_int", yearSort);
			document.addField("yearSort_stored_int", yearSort);
		}
		if(schoolName != null) {
			document.addField("schoolName_indexed_string", schoolName);
			document.addField("schoolName_stored_string", schoolName);
		}
		if(schoolCompleteName != null) {
			document.addField("schoolCompleteName_indexed_string", schoolCompleteName);
			document.addField("schoolCompleteName_stored_string", schoolCompleteName);
		}
		if(schoolLocation != null) {
			document.addField("schoolLocation_indexed_string", schoolLocation);
			document.addField("schoolLocation_stored_string", schoolLocation);
		}
		if(schoolAddress != null) {
			document.addField("schoolAddress_indexed_string", schoolAddress);
			document.addField("schoolAddress_stored_string", schoolAddress);
		}
		if(schoolPhoneNumber != null) {
			document.addField("schoolPhoneNumber_indexed_string", schoolPhoneNumber);
			document.addField("schoolPhoneNumber_stored_string", schoolPhoneNumber);
		}
		if(schoolNumber != null) {
			document.addField("schoolNumber_indexed_int", schoolNumber);
			document.addField("schoolNumber_stored_int", schoolNumber);
		}
		if(schoolAdministratorName != null) {
			document.addField("schoolAdministratorName_indexed_string", schoolAdministratorName);
			document.addField("schoolAdministratorName_stored_string", schoolAdministratorName);
		}
		if(enrollmentFormKey != null) {
			document.addField("enrollmentFormKey_indexed_long", enrollmentFormKey);
			document.addField("enrollmentFormKey_stored_long", enrollmentFormKey);
		}
		if(sessionStartDate != null) {
			document.addField("sessionStartDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionStartDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionStartDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(sessionEndDate != null) {
			document.addField("sessionEndDate_indexed_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionEndDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
			document.addField("sessionEndDate_stored_date", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(sessionEndDate.atStartOfDay(ZoneId.of(siteRequest_.getSiteConfig_().getSiteZone())).toInstant().atZone(ZoneId.of("Z"))));
		}
		if(yearStart != null) {
			document.addField("yearStart_indexed_int", yearStart);
			document.addField("yearStart_stored_int", yearStart);
		}
		if(yearEnd != null) {
			document.addField("yearEnd_indexed_int", yearEnd);
			document.addField("yearEnd_stored_int", yearEnd);
		}
		if(yearEnrollmentFee != null) {
			document.addField("yearEnrollmentFee_indexed_double", yearEnrollmentFee.doubleValue());
			document.addField("yearEnrollmentFee_stored_double", yearEnrollmentFee.doubleValue());
		}
		if(yearShortName != null) {
			document.addField("yearShortName_indexed_string", yearShortName);
			document.addField("yearShortName_stored_string", yearShortName);
		}
		if(yearCompleteName != null) {
			document.addField("yearCompleteName_indexed_string", yearCompleteName);
			document.addField("yearCompleteName_stored_string", yearCompleteName);
		}
		super.indexCluster(document);

	}

	public void unindexSchoolYear() {
		try {
		SiteRequestEnUS siteRequest = new SiteRequestEnUS();
			siteRequest.initDeepSiteRequestEnUS();
			SiteContextEnUS siteContext = new SiteContextEnUS();
			siteContext.initDeepSiteContextEnUS();
			siteRequest.setSiteContext_(siteContext);
			siteRequest.setSiteConfig_(siteContext.getSiteConfig());
			initDeepSchoolYear(siteRequest);
			SolrClient solrClient = siteContext.getSolrClient();
			solrClient.deleteById(id.toString());
			solrClient.commit(false, false, true);
		} catch(Exception e) {
			ExceptionUtils.rethrow(e);
		}
	}

	public static String varIndexedSchoolYear(String entityVar) {
		switch(entityVar) {
			case "schoolKey":
				return "schoolKey_indexed_long";
			case "yearKey":
				return "yearKey_indexed_long";
			case "enrollmentKeys":
				return "enrollmentKeys_indexed_longs";
			case "seasonKeys":
				return "seasonKeys_indexed_longs";
			case "ageKeys":
				return "ageKeys_indexed_longs";
			case "educationSort":
				return "educationSort_indexed_int";
			case "schoolSort":
				return "schoolSort_indexed_int";
			case "yearSort":
				return "yearSort_indexed_int";
			case "schoolName":
				return "schoolName_indexed_string";
			case "schoolCompleteName":
				return "schoolCompleteName_indexed_string";
			case "schoolLocation":
				return "schoolLocation_indexed_string";
			case "schoolAddress":
				return "schoolAddress_indexed_string";
			case "schoolPhoneNumber":
				return "schoolPhoneNumber_indexed_string";
			case "schoolNumber":
				return "schoolNumber_indexed_int";
			case "schoolAdministratorName":
				return "schoolAdministratorName_indexed_string";
			case "enrollmentFormKey":
				return "enrollmentFormKey_indexed_long";
			case "sessionStartDate":
				return "sessionStartDate_indexed_date";
			case "sessionEndDate":
				return "sessionEndDate_indexed_date";
			case "yearStart":
				return "yearStart_indexed_int";
			case "yearEnd":
				return "yearEnd_indexed_int";
			case "yearEnrollmentFee":
				return "yearEnrollmentFee_indexed_double";
			case "yearShortName":
				return "yearShortName_indexed_string";
			case "yearCompleteName":
				return "yearCompleteName_indexed_string";
			default:
				return Cluster.varIndexedCluster(entityVar);
		}
	}

	public static String varSearchSchoolYear(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSearchCluster(entityVar);
		}
	}

	public static String varSuggestedSchoolYear(String entityVar) {
		switch(entityVar) {
			default:
				return Cluster.varSuggestedCluster(entityVar);
		}
	}

	/////////////
	// store //
	/////////////

	@Override public void storeForClass(SolrDocument solrDocument) {
		storeSchoolYear(solrDocument);
	}
	public void storeSchoolYear(SolrDocument solrDocument) {
		SchoolYear oSchoolYear = (SchoolYear)this;

		Long schoolKey = (Long)solrDocument.get("schoolKey_stored_long");
		if(schoolKey != null)
			oSchoolYear.setSchoolKey(schoolKey);

		Long yearKey = (Long)solrDocument.get("yearKey_stored_long");
		if(yearKey != null)
			oSchoolYear.setYearKey(yearKey);

		List<Long> enrollmentKeys = (List<Long>)solrDocument.get("enrollmentKeys_stored_longs");
		if(enrollmentKeys != null)
			oSchoolYear.enrollmentKeys.addAll(enrollmentKeys);

		List<Long> seasonKeys = (List<Long>)solrDocument.get("seasonKeys_stored_longs");
		if(seasonKeys != null)
			oSchoolYear.seasonKeys.addAll(seasonKeys);

		List<Long> ageKeys = (List<Long>)solrDocument.get("ageKeys_stored_longs");
		if(ageKeys != null)
			oSchoolYear.ageKeys.addAll(ageKeys);

		Integer educationSort = (Integer)solrDocument.get("educationSort_stored_int");
		if(educationSort != null)
			oSchoolYear.setEducationSort(educationSort);

		Integer schoolSort = (Integer)solrDocument.get("schoolSort_stored_int");
		if(schoolSort != null)
			oSchoolYear.setSchoolSort(schoolSort);

		Integer yearSort = (Integer)solrDocument.get("yearSort_stored_int");
		if(yearSort != null)
			oSchoolYear.setYearSort(yearSort);

		String schoolName = (String)solrDocument.get("schoolName_stored_string");
		if(schoolName != null)
			oSchoolYear.setSchoolName(schoolName);

		String schoolCompleteName = (String)solrDocument.get("schoolCompleteName_stored_string");
		if(schoolCompleteName != null)
			oSchoolYear.setSchoolCompleteName(schoolCompleteName);

		String schoolLocation = (String)solrDocument.get("schoolLocation_stored_string");
		if(schoolLocation != null)
			oSchoolYear.setSchoolLocation(schoolLocation);

		String schoolAddress = (String)solrDocument.get("schoolAddress_stored_string");
		if(schoolAddress != null)
			oSchoolYear.setSchoolAddress(schoolAddress);

		String schoolPhoneNumber = (String)solrDocument.get("schoolPhoneNumber_stored_string");
		if(schoolPhoneNumber != null)
			oSchoolYear.setSchoolPhoneNumber(schoolPhoneNumber);

		Integer schoolNumber = (Integer)solrDocument.get("schoolNumber_stored_int");
		if(schoolNumber != null)
			oSchoolYear.setSchoolNumber(schoolNumber);

		String schoolAdministratorName = (String)solrDocument.get("schoolAdministratorName_stored_string");
		if(schoolAdministratorName != null)
			oSchoolYear.setSchoolAdministratorName(schoolAdministratorName);

		Long enrollmentFormKey = (Long)solrDocument.get("enrollmentFormKey_stored_long");
		if(enrollmentFormKey != null)
			oSchoolYear.setEnrollmentFormKey(enrollmentFormKey);

		Date sessionStartDate = (Date)solrDocument.get("sessionStartDate_stored_date");
		if(sessionStartDate != null)
			oSchoolYear.setSessionStartDate(sessionStartDate);

		Date sessionEndDate = (Date)solrDocument.get("sessionEndDate_stored_date");
		if(sessionEndDate != null)
			oSchoolYear.setSessionEndDate(sessionEndDate);

		Integer yearStart = (Integer)solrDocument.get("yearStart_stored_int");
		if(yearStart != null)
			oSchoolYear.setYearStart(yearStart);

		Integer yearEnd = (Integer)solrDocument.get("yearEnd_stored_int");
		if(yearEnd != null)
			oSchoolYear.setYearEnd(yearEnd);

		Double yearEnrollmentFee = (Double)solrDocument.get("yearEnrollmentFee_stored_double");
		if(yearEnrollmentFee != null)
			oSchoolYear.setYearEnrollmentFee(yearEnrollmentFee);

		String yearShortName = (String)solrDocument.get("yearShortName_stored_string");
		if(yearShortName != null)
			oSchoolYear.setYearShortName(yearShortName);

		String yearCompleteName = (String)solrDocument.get("yearCompleteName_stored_string");
		if(yearCompleteName != null)
			oSchoolYear.setYearCompleteName(yearCompleteName);

		super.storeCluster(solrDocument);
	}

	//////////////////
	// apiRequest //
	//////////////////

	public void apiRequestSchoolYear() {
		ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(SiteRequestEnUS::getApiRequest_).orElse(null);
		Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
		if(o != null && o instanceof SchoolYear) {
			SchoolYear original = (SchoolYear)o;
			if(!Objects.equals(schoolKey, original.getSchoolKey()))
				apiRequest.addVars("schoolKey");
			if(!Objects.equals(ageKeys, original.getAgeKeys()))
				apiRequest.addVars("ageKeys");
			if(!Objects.equals(sessionStartDate, original.getSessionStartDate()))
				apiRequest.addVars("sessionStartDate");
			if(!Objects.equals(sessionEndDate, original.getSessionEndDate()))
				apiRequest.addVars("sessionEndDate");
			if(!Objects.equals(yearStart, original.getYearStart()))
				apiRequest.addVars("yearStart");
			if(!Objects.equals(yearEnd, original.getYearEnd()))
				apiRequest.addVars("yearEnd");
			if(!Objects.equals(yearEnrollmentFee, original.getYearEnrollmentFee()))
				apiRequest.addVars("yearEnrollmentFee");
			super.apiRequestCluster();
		}
	}

	//////////////
	// hashCode //
	//////////////

	@Override public int hashCode() {
		return Objects.hash(super.hashCode(), schoolKey, ageKeys, sessionStartDate, sessionEndDate, yearStart, yearEnd, yearEnrollmentFee);
	}

	////////////
	// equals //
	////////////

	@Override public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof SchoolYear))
			return false;
		SchoolYear that = (SchoolYear)o;
		return super.equals(o)
				&& Objects.equals( schoolKey, that.schoolKey )
				&& Objects.equals( ageKeys, that.ageKeys )
				&& Objects.equals( sessionStartDate, that.sessionStartDate )
				&& Objects.equals( sessionEndDate, that.sessionEndDate )
				&& Objects.equals( yearStart, that.yearStart )
				&& Objects.equals( yearEnd, that.yearEnd )
				&& Objects.equals( yearEnrollmentFee, that.yearEnrollmentFee );
	}

	//////////////
	// toString //
	//////////////

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		sb.append("SchoolYear { ");
		sb.append( "schoolKey: " ).append(schoolKey);
		sb.append( ", ageKeys: " ).append(ageKeys);
		sb.append( ", sessionStartDate: " ).append(sessionStartDate);
		sb.append( ", sessionEndDate: " ).append(sessionEndDate);
		sb.append( ", yearStart: " ).append(yearStart);
		sb.append( ", yearEnd: " ).append(yearEnd);
		sb.append( ", yearEnrollmentFee: " ).append(yearEnrollmentFee);
		sb.append(" }");
		return sb.toString();
	}
}
